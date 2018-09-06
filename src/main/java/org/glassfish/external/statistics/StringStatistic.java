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
 * Custom statistic type created for the Sun ONE Application Server.
 * The goal is to be able to represent changing attribute values that are strings
 * in the form of Statistics. Semantically, it is analogous to a {@link CountStatistic},
 * the only difference being in the value that is returned. Unlike a CountStatistic
 * (which always is unidirectional), this Statistic type is not having any
 * specified direction, simply because there is no natural order. An example
 * of the values that an instance of this statistic type can assume is: A State
 * Statistic which can have "CONNECTED, CLOSED, DISCONNECTED" as the permissible
 * values and the current value can be any one of them (and them only).
 * The permissible values are upto a particular implementation.
 */

public interface StringStatistic extends Statistic {
    /**
     * Returns the String value of the statistic
     */
    String getCurrent();
}
