/*-
 * #%L
 * Slice - Mapper
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

package com.cognifide.slice.mapper.impl.postprocessor;


import java.lang.reflect.Field;

import org.apache.sling.api.resource.Resource;

import com.cognifide.slice.mapper.annotation.Unescaped;
import com.cognifide.slice.mapper.api.processor.FieldPostProcessor;
import com.cognifide.slice.mapper.helper.Utf8StringEscapeUtils;

public class EscapeValuePostProcessor implements FieldPostProcessor {

	@Override
	public boolean accepts(final Resource resource, final Field field, final Object value) {
		return (value instanceof String) && !field.isAnnotationPresent(Unescaped.class);
	}

	@Override
	public Object processValue(final Resource resource, final Field field, final Object value) {
		return Utf8StringEscapeUtils.escapeUtf8Html((String) value);
	}

}
