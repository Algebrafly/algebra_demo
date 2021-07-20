/*
 * Copyright 2002-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.algebra.demo.auth;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Container annotation that aggregates several {@link DataFilter} annotations.
 *
 * <p>Can be used natively, declaring several nested {@link DataFilter} annotations.
 * Can also be used in conjunction with Java 8's support for repeatable annotations,
 * where {@link DataFilter} can simply be declared several times on the same method,
 * implicitly generating this container annotation.
 *
 * <p>This annotation may be used as a <em>meta-annotation</em> to create custom
 * <em>composed annotations</em>.
 *
 * @author al
 * @since 4.0
 * @see DataFilter
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(DataFilterConfiguration.class)
@Documented
public @interface EnableDataFilter {

}