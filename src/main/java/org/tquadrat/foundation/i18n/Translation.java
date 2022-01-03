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
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.SOURCE;
import static org.apiguardian.api.API.Status.STABLE;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;

/**
 *  <p>{@summary Use this annotation to define a text for a message or a UI
 *  element that has to be translated.} The build process will take care of
 *  this definition and creates the required resource bundle properties
 *  files.</p>
 *  <p>This annotation can only be used in conjunction with the annotations
 *  {@link Text &#64;Text}
 *  and
 *  {@link Message &#64;Message}.</p>
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: Translation.java 882 2021-02-27 19:01:25Z tquadrat $
 *  @since 0.0.2
 */
@Retention( SOURCE )
@Target( {FIELD, METHOD} )
@ClassVersion( sourceVersion = "$Id: Translation.java 882 2021-02-27 19:01:25Z tquadrat $" )
@API( status = STABLE, since = "0.0.2" )
public @interface Translation
{
        /*------------*\
    ====** Attributes **=======================================================
        \*------------*/
    /**
     *  Returns the name of the language for this translation. At least, this
     *  has to be the ISO language code, but it can be extended to a full
     *  locale id with country code and extensions, if desired.
     *
     *  @see java.util.Locale
     *
     *  @return The locale.
     */
    String language();

    /**
     *  Returns the text itself.
     *
     *  @return The translated text for the language defined by the locale.
     */
    String text();
}
//  annotation Translation

/*
 *  End of File
 */