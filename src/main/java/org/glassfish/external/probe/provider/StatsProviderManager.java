/*
 * Copyright (c) 2009, 2018 Oracle and/or its affiliates. All rights reserved.
 * Copyright (c) 2024 Contributors to the Eclipse Foundation
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package org.glassfish.external.probe.provider;

import java.util.ArrayList;

/**
 *
 * @author abbagani
 */
public class StatsProviderManager {

   private StatsProviderManager(){
   }

   
   public static boolean register(String configElement, PluginPoint pp,
                                    String subTreeRoot, Object statsProvider) {
        return register(pp, configElement, subTreeRoot, statsProvider, null);
   }

   public static boolean register(PluginPoint pp, String configElement,
                                  String subTreeRoot, Object statsProvider,
                                  String invokerId) {
        StatsProviderInfo spInfo =
            new StatsProviderInfo(configElement, pp, subTreeRoot, statsProvider, invokerId);

        return registerStatsProvider(spInfo);
   }

   public static boolean register(String configElement, PluginPoint pp,
                                    String subTreeRoot, Object statsProvider,
                                    String configLevelStr) {
        return register(configElement, pp, subTreeRoot, statsProvider, configLevelStr, null);
   }

   public static boolean register(String configElement, PluginPoint pp,
                                    String subTreeRoot, Object statsProvider,
                                    String configLevelStr, String invokerId) {
        StatsProviderInfo spInfo =
            new StatsProviderInfo(configElement, pp, subTreeRoot, statsProvider, invokerId);
        spInfo.setConfigLevel(configLevelStr);

        return registerStatsProvider(spInfo);
   }

   private synchronized static boolean registerStatsProvider(StatsProviderInfo spInfo) {
      //Ideally want to start this in a thread, so we can reduce the startup time
      if (spmd == null) {
          //Make an entry into the toBeRegistered map
          toBeRegistered.add(spInfo);
      } else {
          spmd.register(spInfo);
          return true;
      }
       return false;
   }

   public synchronized static boolean unregister(Object statsProvider) {
      //Unregister the statsProvider if the delegate is not null
      if (spmd == null) {
          for (StatsProviderInfo spInfo : toBeRegistered) {
              if (spInfo.getStatsProvider() == statsProvider) {
                  toBeRegistered.remove(spInfo);
                  break;
              }
          }

      } else {
          spmd.unregister(statsProvider);
          return true;
      }
       return false;
   }


   public static boolean hasListeners(String probeStr) {
      //See if the probe has any listeners registered
      if (spmd == null) {
          return false;
      } else {
          return spmd.hasListeners(probeStr);
      }
   }


   public synchronized static void setStatsProviderManagerDelegate(
                                    StatsProviderManagerDelegate lspmd) {
      if (lspmd == null) {
          //Should log and throw an exception
          return;
      }

      //Assign the Delegate
      spmd = lspmd;

      //First register the pending StatsProviderRegistryElements
      for (StatsProviderInfo spInfo : toBeRegistered) {
          spmd.register(spInfo);
      }

      //Now that you registered the pending calls, Clear the toBeRegistered store
      toBeRegistered.clear();
   }

   static StatsProviderManagerDelegate spmd; // populate this during our initilaization process
   private static ArrayList<StatsProviderInfo> toBeRegistered = new ArrayList();
}
