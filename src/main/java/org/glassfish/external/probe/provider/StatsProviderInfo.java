/*
 * Copyright (c) 2009, 2018 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package org.glassfish.external.probe.provider;

/**
 *
 * @author abbagani
 */
public class StatsProviderInfo {

    public StatsProviderInfo(String configElement, PluginPoint pp,
                                    String subTreeRoot, Object statsProvider){
        this(configElement, pp, subTreeRoot, statsProvider, null);
    }

    public StatsProviderInfo(String configElement, PluginPoint pp,
                                    String subTreeRoot, Object statsProvider,
                                    String invokerId){
        this.configElement = configElement;
        this.pp = pp;
        this.subTreeRoot = subTreeRoot;
        this.statsProvider = statsProvider;
        this.invokerId = invokerId;
    }

    private String configElement;
    private PluginPoint pp;
    private String subTreeRoot;
    private Object statsProvider;
    private String configLevelStr = null;
    private final String invokerId;

    public String getConfigElement() {
        return configElement;
    }

    public PluginPoint getPluginPoint() {
        return pp;
    }

    public String getSubTreeRoot() {
        return subTreeRoot;
    }

    public Object getStatsProvider() {
        return statsProvider;
    }

    public String getConfigLevel() {
        return configLevelStr;
    }

    public void setConfigLevel(String configLevelStr) {
        this.configLevelStr = configLevelStr;
    }

    public String getInvokerId() {
        return invokerId;
    }

}
