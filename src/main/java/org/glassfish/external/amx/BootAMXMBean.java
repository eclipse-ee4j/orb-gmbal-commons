/*
 * Copyright (c) 2009, 2018 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package org.glassfish.external.amx;

import javax.management.ObjectName;
import javax.management.remote.JMXServiceURL;

/**
    MBean responsible for booting the AMX system.
    To get its ObjectName, use {@link AMXGlassfish#getBootAMXMBeanObjectName}.
 */
@org.glassfish.external.arc.Taxonomy(stability = org.glassfish.external.arc.Stability.UNCOMMITTED)
public interface BootAMXMBean 
{
    /**
    Start AMX and return the ObjectName of DomainRoot.
     */
    public ObjectName bootAMX();
    
    /** same as method above */
    public static final String BOOT_AMX_OPERATION_NAME = "bootAMX";

    public JMXServiceURL[] getJMXServiceURLs();
}




