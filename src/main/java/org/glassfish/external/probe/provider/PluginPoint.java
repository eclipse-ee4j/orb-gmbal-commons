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
public enum PluginPoint {

    SERVER ("server", "server"),
    APPLICATIONS ("applications", "server/applications");

    String name;
    String path;

    PluginPoint(String lname, String lpath) {
        name = lname;
        path = lpath;
    }

    public String getName() {
        return name;
    }
    
    public String getPath() {
        return path;
    }
}
