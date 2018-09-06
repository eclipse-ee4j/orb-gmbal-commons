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
 * Specifies standard timing measurements. 
 */
public interface TimeStatistic extends Statistic {
    /**
     * Number of times the operation was invoked since the beginning of this measurement. 
     */
    long getCount();

    /**
     * The maximum amount of time taken to complete one invocation of this operation since the beginning of this measurement. 
     */
    long getMaxTime();

    /**
     * The minimum amount of time taken to complete one invocation of this operation since the beginning of this measurement. 
     */
    long getMinTime();

    /**
     * This is the sum total of time taken to complete every invocation of this operation since the beginning of this measurement. Dividing totalTime by count will give you the average execution time for this operation. 
     */
    long getTotalTime();
}
