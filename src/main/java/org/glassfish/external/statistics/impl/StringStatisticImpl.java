/*
 * Copyright (c) 2009, 2018 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package org.glassfish.external.statistics.impl;
import org.glassfish.external.statistics.StringStatistic;
import java.util.Map;
import java.lang.reflect.*;

/** 
 * @author Sreenivas Munnangi
 */
public final class StringStatisticImpl extends StatisticImpl
    implements StringStatistic, InvocationHandler {
    
    private volatile String str = null;
    private final String initStr;

    private final StringStatistic ss = 
            (StringStatistic) Proxy.newProxyInstance(
            StringStatistic.class.getClassLoader(),
            new Class[] { StringStatistic.class },
            this);

    public StringStatisticImpl(String str, String name, String unit, 
                              String desc, long sampleTime, long startTime) {
        super(name, unit, desc, startTime, sampleTime);
        this.str = str;
        initStr = str;
    }
    
    public StringStatisticImpl(String name, String unit, String desc) {
        this("", name, unit, desc, System.currentTimeMillis(), System.currentTimeMillis());
    }
    
    public synchronized StringStatistic getStatistic() {
        return ss;
    }

    public synchronized Map getStaticAsMap() {
        Map m = super.getStaticAsMap();
        if (getCurrent() != null) {
            m.put("current", getCurrent());
        }
        return m;
    }

    public synchronized String toString() {
        return super.toString() + NEWLINE + "Current-value: " + getCurrent();
    }

    public String getCurrent() {
        return str;
    }

    public void setCurrent(String str) {
        this.str = str;
        sampleTime = System.currentTimeMillis();
    }

    @Override
    public synchronized void reset() {
        super.reset();
        this.str = initStr;
        sampleTime = -1L;
    }

    // todo: equals implementation
    public Object invoke(Object proxy, Method m, Object[] args) throws Throwable {
        checkMethod(m);

        Object result;
        try {
            result = m.invoke(this, args);
        } catch (InvocationTargetException e) {
            throw e.getTargetException();
        } catch (Exception e) {
            throw new RuntimeException("unexpected invocation exception: " +
                       e.getMessage());
        }
        return result;
    }
}
