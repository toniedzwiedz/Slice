/*-
 * #%L
 * Slice - Core
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
package com.cognifide.slice.core.internal.module;

import com.cognifide.slice.mapper.annotation.JcrProperty;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

public class AnnotationReaderTest {

	private AnnotationReader annotationReader;

	@Before
	public void setUp() throws Exception {
		annotationReader = new AnnotationReader();
	}

	@Test
	public void testVisit() {
		annotationReader.visit(1, 1, "/com/cognifide/slice/mapper/annotation/JcrProperty/", "string2",
				"string3", new String[] {});
		Assert.assertFalse(annotationReader.isAnnotationPresent(JcrProperty.class));
	}

	@Test
	public void testVisitAnnotation() throws Exception {
		annotationReader.visitAnnotation("/com/cognifide/slice/mapper/annotation/JcrProperty/", false);
		Assert.assertTrue(annotationReader.isAnnotationPresent(JcrProperty.class));
	}

	@Test
	public void testIsAnnotationPresent() throws Exception {
		Assert.assertFalse(annotationReader.isAnnotationPresent(JcrProperty.class));
		annotationReader.visitAnnotation("/com/cognifide/slice/mapper/annotation/JcrProperty/", false);
		Assert.assertTrue(annotationReader.isAnnotationPresent(JcrProperty.class));
	}
}