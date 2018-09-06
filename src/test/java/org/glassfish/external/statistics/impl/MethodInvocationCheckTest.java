/*
 * Copyright (c) 2013, 2018 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package org.glassfish.external.statistics.impl;

import org.junit.Test;

import java.lang.reflect.Method;
import java.util.logging.Logger;

import static java.util.logging.Level.INFO;

/**
 * Since management-api code is being integrated into jdk it is important not to allow invoking any methods via
 * reflection. This tests additional checks added into method invoke
 */
public class MethodInvocationCheckTest {

    private static final Logger LOGGER = Logger.getLogger(MethodInvocationCheckTest.class.getName());

    public Throwable t = null;
    private int fails = 0;

    @Test
    public void test() throws Throwable {
        checkImpl(new AverageRangeStatisticImpl(0l, 0l, 0l, null, null, null, 0l, 0l));
        checkImpl(new BoundaryStatisticImpl(0l, 0l, null, null, null, 0l, 0l));
        checkImpl(new BoundedRangeStatisticImpl(0l, 0l, 0l, 0l, 0l, null, null, null, 0l, 0l));
        checkImpl(new CountStatisticImpl(0l, null, null, null, 0l, 0l));
        checkImpl(new RangeStatisticImpl(0l, 0l, 0l, null, null, null, 0l, 0l));
        checkImpl(new StringStatisticImpl(null, null, null));
        checkImpl(new TimeStatisticImpl(0l, 0l, 0l, 0l, null, null, null, 0l, 0l));

        // at least one check failed >> fail
        if (fails > 0) {
            LOGGER.log(INFO,  "failed checks total = " + fails);

            // can't be null
            throw t;
        }
    }

    private void checkImpl(StatisticImpl statistic) throws Throwable {
        Method unknownMethod = Unknown.class.getMethod("invokeme");
        LOGGER.log(INFO,  "unknownMethod = " + unknownMethod);
        checkExceptionThrown(new Unknown(), statistic, unknownMethod, null);

        Method staticMethod = Extended.class.getMethod("invokeme");
        LOGGER.log(INFO,  "staticMethod = " + staticMethod);
        checkExceptionThrown(null, statistic, staticMethod, new String[0]);

        checkExceptionThrown(null, statistic, null, null);
    }

    private void checkExceptionThrown(Object proxy, StatisticImpl statistic, Method method, Object[] args) throws Throwable {
        try {

            if (statistic instanceof AverageRangeStatisticImpl)
                ((AverageRangeStatisticImpl) statistic).invoke(proxy, method, args);
            else if (statistic instanceof BoundaryStatisticImpl)
                ((BoundaryStatisticImpl) statistic).invoke(proxy, method, args);
            else if (statistic instanceof BoundedRangeStatisticImpl)
                ((BoundedRangeStatisticImpl) statistic).invoke(proxy, method, args);
            else if (statistic instanceof CountStatisticImpl)
                ((CountStatisticImpl) statistic).invoke(proxy, method, args);
            else if (statistic instanceof RangeStatisticImpl)
                ((RangeStatisticImpl) statistic).invoke(proxy, method, args);
            else if (statistic instanceof StringStatisticImpl)
                ((StringStatisticImpl) statistic).invoke(proxy, method, args);
            else if (statistic instanceof TimeStatisticImpl)
                ((TimeStatisticImpl) statistic).invoke(proxy, method, args);
            else
                throw new IllegalStateException("Unknown tested object class: [" + statistic.getClass().getName() + "] - problem in test");

            LOGGER.log(INFO,  "TEST FAILED, expected exception not thrown.");
        } catch (Throwable throwable) {
            checkException(throwable);
        }
    }

    private void checkException(Throwable throwable) throws Throwable {

        if (!(throwable instanceof RuntimeException) || !"Invalid method on invoke".equals(throwable.getMessage())) {
            LOGGER.log(INFO,  "TEST FAILED, unexpected throwable cought.");
            throwable.printStackTrace();
            t = throwable;
            fails++;
        } else {
            LOGGER.log(INFO,  "TEST PASSED.");
        }

    }

    public static class Extended extends StatisticImpl {

        protected Extended(String name, String unit, String desc) {
            super(name, unit, desc);
        }

        @SuppressWarnings("unused")
        public static void invokeme() {
            throw new RuntimeException("This method shouldn't be invoked - it's static! If it is, it's failure!");
        }
    }

    public static class Unknown {

        @SuppressWarnings("unused")
        public void invokeme() {
            throw new RuntimeException("This method shouldn't be invoked - it's static! If it is, it's failure!");
        }
    }

    public static void main(String[] args) throws Throwable {
        new MethodInvocationCheckTest().test();
    }

}
