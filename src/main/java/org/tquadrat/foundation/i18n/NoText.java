/*
 * ============================================================================
 *  Copyright © 2002-2024 by Thomas Thrien.
 *  All Rights Reserved.
 * ============================================================================
 *  Licensed to the public under the agreements of the GNU Lesser General Public
 *  License, version 3.0 (the "License"). You may obtain a copy of the License at
 *
 *       http://www.gnu.org/licenses/lgpl.html
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 *  WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 *  License for the specific language governing permissions and limitations
 *  under the License.
 */

package org.tquadrat.foundation.i18n;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.SOURCE;
import static org.apiguardian.api.API.Status.STABLE;
import static org.tquadrat.foundation.i18n.TextUse.TEXTUSE_DEFAULT;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;

/**
 *  <p>{@summary Marker for omitted texts.}</p>
 *  <p>Use it to annotate elements that are expected to have a certain text
 *  associated with, but this is not there – either because it was omitted
 *  completely, or because it is defined elsewhere.</p>
 *  <p>This annotation is more or less a formalised comment, it will not be
 *  processed in any way.</p>
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: NoText.java 1124 2024-03-19 11:02:47Z tquadrat $
 *  @since 0.4.6
 *
 *  @UMLGraph.link
 */
@Retention( SOURCE )
@Target( {FIELD, METHOD} )
@Repeatable( NoTexts.class )
@ClassVersion( sourceVersion = "$Id: NoText.java 1124 2024-03-19 11:02:47Z tquadrat $" )
@API( status = STABLE, since = "0.4.6" )
public @interface NoText
{
        /*------------*\
    ====** Attributes **=======================================================
        \*------------*/
    /**
     *  <p>{@summary The expected use of the omitted text; the default is
     *  {@link TextUse#TEXTUSE_DEFAULT}.}</p>
     *
     *  @return The text use.
     */
    TextUse use() default TEXTUSE_DEFAULT;

    /**
     *  Returns the explanation why the text was omitted here.
     *
     *  @return The description.
     */
    String explanation() default "";
}
//  @interface NoText

/*
 *  End of File
 */