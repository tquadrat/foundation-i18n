/*
 * ============================================================================
 * Copyright © 2002-2021 by Thomas Thrien.
 * All Rights Reserved.
 * ============================================================================
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
 *  <p>{@summary Use this annotation to define the text for a message that has
 *  to be translated. Texts for UI elements or alike will be defined with the
 *  annotation
 *  {@link Text}.}</p>
 *  <p>The build process will take care of this definition and creates the
 *  required resource bundle properties files.</p>
 *  <p>Use this annotation as follows:</p>
 *  <pre><code>  &#64;Message
 *  (
 *      description = "A message",
 *      translations =
 *      {
 *          &#64;Translation( language = "en", text = "This is an English message" ),
 *          &#64;Translation( language = "de", text = "Dies ist eine Nachricht in Deutsch" )
 *      }
 *  )
 *  public static final int MSG_MessageKey = 1704;</code></pre>
 *  <p>or</p>
 *  <pre><code>  &#64;Message
 *  (
 *      description = "A message",
 *      translations =
 *      {
 *          &#64;Translation( language = "en", text = "This is an English message" ),
 *          &#64;Translation( language = "de", text = "Dies ist eine Nachricht in Deutsch" )
 *      }
 *  )
 *  public static final String MSG_MessageKey = "AMessage";</code></pre>
 *  <p>The value for the constant together with the prefix defined with the
 *  annotation
 *  {@link MessagePrefix}
 *  has to be unique.</p>
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: Message.java 882 2021-02-27 19:01:25Z tquadrat $
 *  @since 0.1.0
 */
@Retention( SOURCE )
@Target( FIELD )
@ClassVersion( sourceVersion = "$Id: Message.java 882 2021-02-27 19:01:25Z tquadrat $" )
@API( status = STABLE, since = "0.1.0" )
public @interface Message
{
        /*------------*\
    ====** Attributes **=======================================================
        \*------------*/
    /**
     *  Returns the description for the text.
     *
     *  @return The description.
     */
    String description();

    /**
     *  Returns the list of valid translations for the message.
     *
     *  @return The translations.
     */
    Translation [] translations();
}
//  annotation Message

/*
 *  End of File
 */