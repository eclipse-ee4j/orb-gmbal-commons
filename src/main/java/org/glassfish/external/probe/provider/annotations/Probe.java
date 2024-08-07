/*
 * Copyright (c) 2009, 2018 Oracle and/or its affiliates. All rights reserved.
 * Copyright (c) 2024 Contributors to the Eclipse Foundation
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package org.glassfish.external.probe.provider.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

/**
 *
 * @author Prashanth Abbagani
 *         Date: April 16, 2009
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Probe {

    String name() default "";
    boolean hidden() default false;
    boolean self() default false;
    String providerName() default "";
    String moduleName() default "";
    boolean stateful() default false;
    String profileNames() default "";
    boolean statefulReturn() default false;
    boolean statefulException() default false;
}
