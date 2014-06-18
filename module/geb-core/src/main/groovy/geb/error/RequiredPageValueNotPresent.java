/* Copyright 2009 the original author or authors.
 *
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
 */
package geb.error;

import geb.content.PageContentTemplate;
import org.codehaus.groovy.runtime.DefaultGroovyMethods;

public class RequiredPageValueNotPresent extends GebAssertionError {

	private final PageContentTemplate template;

	public RequiredPageValueNotPresent(PageContentTemplate template, Object[] args) {
		super(String.format("Template '%s' returned null for args: '%s'", template, DefaultGroovyMethods.toString(args)));
		this.template = template;
	}

	public PageContentTemplate getTemplate() {
		return template;
	}
}