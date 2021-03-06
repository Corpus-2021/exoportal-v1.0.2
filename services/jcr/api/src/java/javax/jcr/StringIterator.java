/*
 * $Id: StringIterator.java,v 1.2 2004/07/24 00:16:21 benjmestrallet Exp $
 *
 * Copyright 2002-2004 Day Management AG, Switzerland.
 *
 * Licensed under the Day RI License, Version 2.0 (the "License"),
 * as a reference implementation of the following specification:
 *
 *   Content Repository API for Java Technology, revision 0.12
 *        <http://www.jcp.org/en/jsr/detail?id=170>
 *
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License files at
 *
 *     http://www.day.com/content/en/licenses/day-ri-license-2.0
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package javax.jcr;

/**
 * Allows easy iteration through a list of <code>String</code>s
 * with <code>nextString</code> as well as a <code>skip</code> method.
 *
 * @author Stefan Guggisberg
 */
public interface StringIterator extends RangeIterator {

  /**
   * Returns the next <code>String</code> in the iteration.
   *
   * @return the next <code>String</code> in the iteration.
   * @throws java.util.NoSuchElementException
   *          if iteration has no more <code>String</code>s.
   */
  public String nextString();
}