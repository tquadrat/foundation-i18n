/*
 * ============================================================================
 * Copyright © 2002-2021 by Thomas Thrien.
 * All Rights Reserved.
 * ============================================================================
 *
 * Licensed to the public under the agreements of the GNU Lesser General Public
 * License, version 3.0 (the "License"). You may obtain a copy of the License at
 *
 *      http://www.gnu.org/licenses/lgpl.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package org.tquadrat.foundation.i18n;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.SOURCE;
import static org.apiguardian.api.API.Status.STABLE;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;

/**
 *  <p>{@summary The annotation is used to mark a String constant that holds
 *  the message prefix for the generated messages.} When the annotation
 *  processor finds more than one field with this annotation, the result is
 *  unpredictable.</p>
 *  <p>If this annotation is not used, the constant
 *  {@value org.tquadrat.foundation.i18n.I18nUtil#DEFAULT_MESSAGE_PREFIX}
 *  will be used instead.</p>
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: MessagePrefix.java 882 2021-02-27 19:01:25Z tquadrat $
 *  @since 0.1.0
 */
@Retention( SOURCE )
@Target( FIELD )
@ClassVersion( sourceVersion = "$Id: MessagePrefix.java 882 2021-02-27 19:01:25Z tquadrat $" )
@API( status = STABLE, since = "0.1.0" )
public @interface MessagePrefix
{ /* Empty */ }
//  class MessagePrefix

/*
 *  End of File
 */