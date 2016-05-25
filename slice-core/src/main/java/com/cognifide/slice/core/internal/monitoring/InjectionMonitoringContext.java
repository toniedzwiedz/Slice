/*-
 * #%L
 * Slice - Core
 * %%
 * Copyright (C) 2012 Cognifide Limited
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package com.cognifide.slice.core.internal.monitoring;

import java.util.LinkedList;

import com.cognifide.slice.api.execution.ExecutionContext;

public class InjectionMonitoringContext {
	private long startTime;

	private LinkedList<ExecutionContext> modelsStack;

	private Class<?> injecteeClass;

	public InjectionMonitoringContext() {
		startTime = System.nanoTime();
	}

	public long getElapsedTime() {
		return System.nanoTime() - startTime;
	}

	public void setModelsStack(LinkedList<ExecutionContext> models) {
		this.modelsStack = models;
	}

	public LinkedList<ExecutionContext> getModelsStack() {
		return modelsStack;
	}
}