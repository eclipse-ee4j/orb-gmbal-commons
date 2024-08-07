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

package org.glassfish.external.amx;

import javax.management.ObjectName;

/**
    Small utilities for AMXBooter and related.
 */
@org.glassfish.external.arc.Taxonomy(stability = org.glassfish.external.arc.Stability.UNCOMMITTED)
public final class AMXUtil
{
    private AMXUtil() {}
 
    /**
        Make a new ObjectName (unchecked exception).
     */
    public static ObjectName newObjectName(final String s)
    {
        try
        {
            return new ObjectName( s );
        }
        catch( final Exception e )
        {
            throw new RuntimeException("bad ObjectName", e);
        }
    }

    /**
        Make a new ObjectName (unchecked exception).
        @param domain
        @param props
     */
    public static ObjectName newObjectName(
            final String domain,
            final String props)
    {
        return newObjectName(domain + ":" + props);
    }

    /**
        Get the ObjectName of the MBeanServerDelegateObjectName.
     */
    public static ObjectName getMBeanServerDelegateObjectName()
    {
        return newObjectName( "JMImplementation:type=MBeanServerDelegate" );
    }

    public static String prop(final String key, final String value)
    {
        return key + "=" + value;
    }
}



















