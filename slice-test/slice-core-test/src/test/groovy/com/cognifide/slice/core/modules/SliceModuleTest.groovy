/*-
 * #%L
 * Slice - Core Tests
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
package com.cognifide.slice.core.modules

import com.cognifide.slice.test.setup.BaseSetup
import junit.framework.Assert

/**
 * User: krzysztof.ryk@solsoft.pl
 * Date: 4/22/15 9:06 AM
 */
class SliceModuleTest extends BaseSetup {

	def "Module is not null"() {
		expect: "Module is not null"
		Assert.assertNotNull(sliceModule)
	}


	def "Get Current Execution Context test"() {
		setup: "Get Current Execution Context"
		def currentExecutionContext = sliceModule.getCurrentExecutionContext()

		expect: "Current Execution Context is not null"
		Assert.assertNotNull(currentExecutionContext);
	}

	def "Get Current Resource Path by Current Execution Context"() {
		setup: "Get Current Execution Context"
		def currentExecutionContext = sliceModule.getCurrentExecutionContext()

		when: "Get Current Resource Path"
		sliceModule.getCurrentResourcePath(currentExecutionContext)

		then: "Empty Stack Exception should be thrown"
		thrown(EmptyStackException)
	}

	def "Get Requested Resource Path"() {
		def resourcePath = "/content/foo"

		setup: "Create a content for tests"
		pageBuilder.content {
			foo("foo") { "jcr:content"("prop1": "prop1Value") }
		}
		and: "Get a Resource from created content"
		def resource = resourceResolver.getResource(resourcePath)

		and: "Get Requested Resource Path"
		def requestedResourcePath = sliceModule.getRequestedResourcePath(resource)

		expect: "Resource is not null"
		Assert.assertNotNull(resource)

		and: "Resource Path is correct"
		Assert.assertEquals(resourcePath, requestedResourcePath)
	}

	def "Get Requested Resource Path by a null resource"() {
		setup: "Get Requested Resource Path by a null resource"
		def requestedResourcePath = sliceModule.getRequestedResourcePath(null)

		and: "Resource Path is null"
		Assert.assertNull(requestedResourcePath)
	}

	def "Get bundle"() {
		setup: "Get bundle"
		def bundle = sliceModule.getBundle()

		and: "Bundle is null"
		Assert.assertNull(bundle)
	}
}
