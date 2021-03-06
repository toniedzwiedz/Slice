/*-
 * #%L
 * Slice - Core API
 * %%
 * Copyright (C) 2012 Wunderman Thompson Technology
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

package com.cognifide.slice.api.injector;

import aQute.bnd.annotation.ConsumerType;

/**
 * OSGi services implementing this interface are informed about the availability of injectors registered in
 * Slice. Listener can be used to implement initialization logic inside OSGi component that needs to use Slice
 * in their initial logic, as it can't be done in the component {@code @Activate} method:
 * 
 * <pre>
 * {@code {@literal @}Component
 * {@literal @}Service
 * public class MyComponent implements InjectorListener {
 * 
 *   // we need to have this atomic boolean, as the injector may become available a few times during component lifecycle
 *   private final AtomicBoolean initialized = new AtomicBoolean();
 * 
 *   {@literal @}Activate
 *   protected void activate() {
 *     initialized.set(false);
 *   }
 * 
 *   {@literal @}Override
 *   public void injectorAvailable(String injectorName) {
 *     if ("myapp".equals(injectorName) && !initialized.getAndSet()) {
 *             InjectorWithContext injector = InjectorUtil.getInjector("myapp", getResolver());
 *             // do something clever with the injector
 *         }
 *     }
 * }
 * }
 * </pre>
 * 
 * @author Tomasz Rękawek
 * 
 */
@ConsumerType
public interface InjectorListener {
	void injectorAvailable(String injectorName);
}
