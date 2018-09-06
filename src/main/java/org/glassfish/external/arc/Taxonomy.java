/*
 * Copyright (c) 2009, 2018 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package org.glassfish.external.arc;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
  Annotates classes, methods or fields as to the ARC taxonomy (stability level).
  Note that a class can be annotated as a whole, but that annotations on its methods
  and fields override the whole-class annotation.
  @author llc
 */
@Retention(RUNTIME)
@Documented
@Target( {METHOD, TYPE, ANNOTATION_TYPE, FIELD, PACKAGE})
public @interface Taxonomy  {
    public Stability stability() default Stability.UNSPECIFIED;
    public String description() default "";
}

