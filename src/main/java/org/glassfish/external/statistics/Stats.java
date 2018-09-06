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

public interface Stats {

    /**
     * Get a Statistic by name. 
     */
    Statistic getStatistic(String statisticName);

    /**
     * Returns an array of Strings which are the names of the attributes from the specific Stats submodel that this object supports. Attributes named in the list must correspond to attributes that will return a Statistic object of the appropriate type which contains valid performance data.  The return value of attributes in the Stats submodel that are not included in the statisticNames list must be null. For each name in the statisticNames list there must be one Statistic with the same name in the statistics list. 
     */
    String [] getStatisticNames();

    /**
     * Returns an array containing all of the Statistic objects supported by this Stats object. 
     */
    Statistic[] getStatistics();
}
