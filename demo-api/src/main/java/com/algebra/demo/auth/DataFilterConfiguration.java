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

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

/**
 * @apiNote 启动时添加扫描
 * @since 2021/2/24 16:59
 * @author al
 * @version v1.0.0
 */
public class DataFilterConfiguration {

	@Bean(name = "dataFilterAspect")
	@Lazy
	public DataFilterAspect dataFilterAspect() {
		return new DataFilterAspect();
	}

	@Bean(name = "dataFilterHandler")
	@Lazy
	public DataFilterHandler dataFilterHandler() {
		return new DataFilterHandler();
	}

	@Bean(name = "dataFilterSqlInjector")
	@Lazy
	public DataFilterSqlInjector dataFilterSqlInjector() {
		return new DataFilterSqlInjector();
	}

}
