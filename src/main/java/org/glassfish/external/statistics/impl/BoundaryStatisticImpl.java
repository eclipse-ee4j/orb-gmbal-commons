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
import org.glassfish.external.statistics.BoundaryStatistic;
import java.util.Map;
import java.lang.reflect.*;

/** 
 * @author Sreenivas Munnangi
 */
public final class BoundaryStatisticImpl extends StatisticImpl 
    implements BoundaryStatistic, InvocationHandler {
    
    private final long lowerBound;
    private final long upperBound;
	
    private final BoundaryStatistic bs = 
            (BoundaryStatistic) Proxy.newProxyInstance(
            BoundaryStatistic.class.getClassLoader(),
            new Class[] { BoundaryStatistic.class },
            this);

    public BoundaryStatisticImpl(long lower, long upper, String name,
                                 String unit, String desc, long startTime,
                                 long sampleTime) {
        super(name, unit, desc, startTime, sampleTime);
        upperBound = upper;
        lowerBound = lower;
    }

    public synchronized BoundaryStatistic getStatistic() {
        return bs;
    }
    
    public synchronized Map getStaticAsMap() {
        Map m = super.getStaticAsMap();
        m.put("lowerbound", getLowerBound());
        m.put("upperbound", getUpperBound());
        return m;
    }

    public synchronized long getLowerBound() {
        return lowerBound;
    }
    
    public synchronized long getUpperBound() {
        return upperBound;
    }

    @Override
    public synchronized void reset() {
        super.reset();
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
