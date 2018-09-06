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
 * Specifies standard measurements of the upper and lower limits of the value of an attribute. 
 */
public interface BoundaryStatistic extends Statistic {
    /**
     * The upper limit of the value of this attribute. 
     */
    long getUpperBound();

    /**
     * The lower limit of the value of this attribute.The upper limit of the value of this attribute. 
     */
    long getLowerBound();
}
