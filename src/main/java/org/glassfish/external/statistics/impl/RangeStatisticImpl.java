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
import org.glassfish.external.statistics.RangeStatistic;
import java.util.Map;
import java.lang.reflect.*;

/** 
 * @author Sreenivas Munnangi
 */
public final class RangeStatisticImpl extends StatisticImpl 
    implements RangeStatistic, InvocationHandler {
    
    private long currentVal = 0L;
    private long highWaterMark = Long.MIN_VALUE;
    private long lowWaterMark = Long.MAX_VALUE;
    private final long initCurrentVal;
    private final long initHighWaterMark;
    private final long initLowWaterMark;

    private final RangeStatistic rs = 
            (RangeStatistic) Proxy.newProxyInstance(
            RangeStatistic.class.getClassLoader(),
            new Class[] { RangeStatistic.class },
            this);
    
    public RangeStatisticImpl(long curVal, long highMark, long lowMark, 
                              String name, String unit, String desc, 
                              long startTime, long sampleTime) {
        super(name, unit, desc, startTime, sampleTime);
        currentVal = curVal;
        initCurrentVal = curVal;
        highWaterMark = highMark;
        initHighWaterMark = highMark;
        lowWaterMark = lowMark;
        initLowWaterMark = lowMark;
    }

    public synchronized RangeStatistic getStatistic() {
        return rs;
    }
    
    public synchronized Map getStaticAsMap() {
        Map m = super.getStaticAsMap();
        m.put("current", getCurrent());
        m.put("lowwatermark", getLowWaterMark());
        m.put("highwatermark", getHighWaterMark());
        return m;
    }

    public synchronized long getCurrent() {
        return currentVal;
    }
    
    public synchronized void setCurrent(long curVal) {
        currentVal = curVal;
        lowWaterMark = (curVal >= lowWaterMark ? lowWaterMark : curVal);
        highWaterMark = (curVal >= highWaterMark ? curVal : highWaterMark);
        sampleTime = System.currentTimeMillis();
    }
    
    /**
     * Returns the highest value of this statistic, since measurement started.
     */
    public synchronized long getHighWaterMark() {
        return highWaterMark;
    }
    
    public synchronized void setHighWaterMark(long hwm) {
        highWaterMark = hwm;
    }
    
    /**
     * Returns the lowest value of this statistic, since measurement started.
     */
    public synchronized long getLowWaterMark() {
        return lowWaterMark;
    }
    
    public synchronized void setLowWaterMark(long lwm) {
        lowWaterMark = lwm;
    }
    
    @Override
    public synchronized void reset() {
        super.reset();
        currentVal = initCurrentVal;
        highWaterMark = initHighWaterMark;
        lowWaterMark = initLowWaterMark;
        sampleTime = -1L;
    }
    
    public synchronized String toString() {
        return super.toString() + NEWLINE + 
            "Current: " + getCurrent() + NEWLINE +
            "LowWaterMark: " + getLowWaterMark() + NEWLINE +
            "HighWaterMark: " + getHighWaterMark();
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
