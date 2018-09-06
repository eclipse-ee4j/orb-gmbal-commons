/*
 * Copyright (c) 2009, 2018 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package org.glassfish.external.statistics;

/**
 * Specifies standard measurements of the lowest and highest values an attribute has held as well as its current value. 
 */
public interface RangeStatistic extends Statistic {
    /**
     * The highest value this attribute has held since the beginninYg of the measurement. 
     */
    long getHighWaterMark();

    /**
     * The lowest value this attribute has held since the beginning of the measurement. 
     */
    long getLowWaterMark();

    /**
     * The current value of this attribute. 
     */
    long getCurrent();
}
