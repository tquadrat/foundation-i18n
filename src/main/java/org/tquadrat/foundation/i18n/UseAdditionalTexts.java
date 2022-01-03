/*
 * ============================================================================
 *  Copyright © 2002-2021 by Thomas Thrien.
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

import static java.lang.annotation.ElementType.MODULE;
import static java.lang.annotation.ElementType.PACKAGE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.SOURCE;
import static org.apiguardian.api.API.Status.STABLE;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;

/**
 *  <p>{@summary This optional annotation provides the location for the file
 *  {@value I18nUtil#ADDITIONAL_TEXT_FILE}
 *  if that is not stored at the default locations}, usually either the root of
 *  the source tree or the location that is provided through the annotation
 *  processor option
 *  {@value I18nUtil#ADDITIONAL_TEXT_LOCATION}.</p>
 *  <p>The annotation is mandatory if the respective project does not use any
 *  of the annotations</p>
 *  <ul>
 *      <li>{@link BaseBundleName &#64;BaseBundleName}</li>
 *      <li>{@link MessagePrefix &#64;MessagePrefix}</li>
 *      <li>{@link Message &#64;Message},
 *      {@link Text &#64;Text}
 *      or
 *      {@link Texts &#64;Texts}</li>
 *  </ul>
 *  <p>but defines texts or message in a
 *  {@value I18nUtil#ADDITIONAL_TEXT_FILE}
 *  file.</p>
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: UseAdditionalTexts.java 887 2021-03-28 19:25:19Z tquadrat $
 *  @since 0.1.0
 *
 *  @see I18nUtil#ADDITIONAL_TEXT_LOCATION
 *  @see I18nUtil#ADDITIONAL_TEXT_FILE
 *
 *  @UMLGraph.link
 */
@Documented
@Retention( SOURCE )
@Target( {TYPE, PACKAGE, MODULE} )
@ClassVersion( sourceVersion = "$Id: UseAdditionalTexts.java 887 2021-03-28 19:25:19Z tquadrat $" )
@API( status = STABLE, since = "0.1.0" )
public @interface UseAdditionalTexts
{
        /*------------*\
    ====** Attributes **=======================================================
        \*------------*/
    /**
     *  The location for the
     *  {@value I18nUtil#ADDITIONAL_TEXT_FILE}
     *  file.
     *
     *  @return The name for the folder that holdes the file with the
     *      additional texts.
     */
    public String location() default "";
}
//  @interface UseAdditionalTexts

/*
 *  End of File
 */