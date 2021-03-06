/*-
 * #%L
 * Slice - Persistence
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
package com.cognifide.slice.persistence.impl.serializer;

import com.cognifide.slice.mapper.annotation.JcrProperty;
import com.cognifide.slice.mapper.annotation.SliceResource;
import com.cognifide.slice.persistence.api.SerializerContext;
import com.cognifide.slice.persistence.api.serializer.ObjectSerializer;
import org.apache.commons.lang.StringUtils;
import org.apache.sling.api.resource.ModifiableValueMap;
import org.apache.sling.api.resource.PersistenceException;
import org.apache.sling.api.resource.Resource;

import java.lang.reflect.Field;

public class RecursiveSerializer implements ObjectSerializer {

	@Override
	public int getPriority() {
		return Integer.MAX_VALUE;
	}

	@Override
	public boolean accepts(Class<?> clazz) {
		return clazz.isAnnotationPresent(SliceResource.class);
	}

	@Override
	public void serialize(String childName, Object object, Resource parent, SerializerContext ctx)
			throws PersistenceException {
		if (ctx.alreadySerialized(object)) {
			return;
		}

		if (object == null) {
			removeProperty(parent, childName);
		} else {
			serializeObject(parent, childName, object, ctx);
		}
	}

	private void removeProperty(Resource parent, String childName) {
		final ModifiableValueMap map = parent.adaptTo(ModifiableValueMap.class);
		if (map.containsKey(childName)) {
			map.remove(childName);
		}
	}

	private void serializeObject(Resource parent, String childName, Object object, SerializerContext ctx)
			throws PersistenceException {
		Resource child = parent.getChild(childName);
		if (child == null) {
			child = parent.getResourceResolver().create(parent, childName, ctx.getInitialProperties());
		}
		for (final Field field : object.getClass().getDeclaredFields()) {
			if (!field.isAnnotationPresent(JcrProperty.class)) {
				continue;
			}

			final String propertyName = retrievePropertyName(field);
			field.setAccessible(true);
			Object fieldValue;
			try {
				fieldValue = field.get(object);
			} catch (IllegalAccessException e) {
				throw new PersistenceException("Can't get field", e);
			}

			ctx.getFacade().serializeField(field, propertyName, fieldValue, child, ctx);
		}
	}

	private String retrievePropertyName(Field field) {
		final JcrProperty jcrPropAnnotation = field.getAnnotation(JcrProperty.class);
		final String overridingPropertyName = jcrPropAnnotation.value();
		final String fieldName = field.getName();

		return StringUtils.defaultIfEmpty(overridingPropertyName, fieldName);
	}
}
