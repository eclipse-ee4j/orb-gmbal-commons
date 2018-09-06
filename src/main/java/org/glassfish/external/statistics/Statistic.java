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
 * The Statistic model and its sub-models specify the data models which are requried to be used to provide the performance data described by the specific attributes in the Stats models. 
 */
public interface Statistic {
    /**
     * The name of this Statistic. 
     */
    String getName();

    /**
     * The unit of measurement for this Statistic.
     * Valid values for TimeStatistic measurements are "HOUR", "MINUTE", "SECOND", "MILLISECOND", "MICROSECOND" and "NANOSECOND". 
     */
    String getUnit();

    /**
     * A human-readable description of the Statistic. 
     */
    String getDescription();

    /**
     * The time of the first measurement represented as a long, whose value is the number of milliseconds since January 1, 1970, 00:00:00. 
     */
    long getStartTime();

    /**
     * The time of the last measurement represented as a long, whose value is the number of milliseconds since January 1, 1970, 00:00:00. 
     */
    long getLastSampleTime();
}
