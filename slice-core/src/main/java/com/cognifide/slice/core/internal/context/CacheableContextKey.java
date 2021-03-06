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
package com.cognifide.slice.core.internal.context;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.google.inject.Key;

/**
 * This class presents a wrapper key used in CacheableContext map.
 * 
 * @author kamil.ciecierski
 */

public class CacheableContextKey {

	private final String path;

	private final Key<?> type;

	public CacheableContextKey(final String path, final Key<?> type) {
		this.path = path;
		this.type = type;
	}

	@Override
	public int hashCode() {
		HashCodeBuilder builder = new HashCodeBuilder();
		builder.append(path);
		builder.append(type);
		return builder.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		CacheableContextKey other = (CacheableContextKey) obj;
		EqualsBuilder equalsBuilder = new EqualsBuilder();
		equalsBuilder.append(path, other.path);
		equalsBuilder.append(type, other.type);
		return equalsBuilder.isEquals();
	}
}
