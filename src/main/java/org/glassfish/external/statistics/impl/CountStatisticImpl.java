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
import org.glassfish.external.statistics.CountStatistic;
import java.util.Map;
import java.lang.reflect.*;

/** 
 * @author Sreenivas Munnangi
 */
public final class CountStatisticImpl extends StatisticImpl
    implements CountStatistic, InvocationHandler {
    
    private long count = 0L;
    private final long initCount;

    private final CountStatistic cs = 
            (CountStatistic) Proxy.newProxyInstance(
            CountStatistic.class.getClassLoader(),
            new Class[] { CountStatistic.class },
            this);

    public CountStatisticImpl(long countVal, String name, String unit, 
                              String desc, long sampleTime, long startTime) {
        super(name, unit, desc, startTime, sampleTime);
        count = countVal;
        initCount = countVal;
    }
    
    public CountStatisticImpl(String name, String unit, String desc) {
        this(0L, name, unit, desc, -1L, System.currentTimeMillis());
    }

    public synchronized CountStatistic getStatistic() {
        return cs;
    }

    public synchronized Map getStaticAsMap() {
        Map m = super.getStaticAsMap();
        m.put("count", getCount());
        return m;
    }

    public synchronized String toString() {
        return super.toString() + NEWLINE + "Count: " + getCount();
    }

    public synchronized long getCount() {
        return count;
    }

    public synchronized void setCount(long countVal) {
        count = countVal;
        sampleTime = System.currentTimeMillis();
    }

    public synchronized void increment() {
        count++;
        sampleTime = System.currentTimeMillis();
    }

    public synchronized void increment(long delta) {
        count = count + delta;
        sampleTime = System.currentTimeMillis();
    }

    public synchronized void decrement() {
        count--;
        sampleTime = System.currentTimeMillis();
    }

    @Override
    public synchronized void reset() {
        super.reset();
        count = initCount;
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
