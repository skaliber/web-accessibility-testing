/* Copyright 2011 eBay Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *    http://www.apache.org/licenses/LICENSE-2.0
 *    
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.a11ytesting.filter;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Element checker annotation used to identify which element class a
 * checking method supports. 
 * 
 * @author dallison
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface ElementChecker {
	/**
	 * Set the filter type to use in the calling method to select the correct
	 * element type. If no element filter is specified it is assumed that the method
	 * is intended to be run on every element of the class.
	 */
	Class<? extends ElementFilter> filter() default ElementFilter.class;
}
