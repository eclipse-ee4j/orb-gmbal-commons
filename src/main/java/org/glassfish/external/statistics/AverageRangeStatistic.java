/*
 * Copyright (c) 2004, 2018 Oracle and/or its affiliates. All rights reserved.
 * Copyright (c) 2024 Contributors to the Eclipse Foundation
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

/*
 * AverageRangeStatistic.java
 *
 * Created on May 11, 2004, 2:15 PM
 */

package org.glassfish.external.statistics;

/**
 * An interface that Specifies standard measurements of the lowest and highest
 * values an attribute has held as well as its current value.
 * Extending RangeStatistic, it also provides the average value.
 */

public interface AverageRangeStatistic extends RangeStatistic {
    
    long getAverage();
    
}
