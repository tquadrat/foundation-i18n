/*
 * ============================================================================
 * Copyright © 2002-2023 by Thomas Thrien.
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
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.SOURCE;
import static org.apiguardian.api.API.Status.STABLE;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;

/**
 *  The container annotation for repeated
 *  {@link NoText &#64;NoText}
 *  annotations.
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: NoTexts.java 1123 2024-03-19 10:06:45Z tquadrat $
 *  @since 0.4.6
 */
@Retention( SOURCE )
@Target( {FIELD, METHOD} )
@ClassVersion( sourceVersion = "$Id: NoTexts.java 1123 2024-03-19 10:06:45Z tquadrat $" )
@API( status = STABLE, since = "0.4.6" )
public @interface NoTexts
{
        /*------------*\
    ====** Attributes **=======================================================
        \*------------*/
    /**
     *  The repeated
     *  {@link NoText &#64;NoText}
     *  annotations.
     *
     *  @return The {@code NoText} annotations.
     */
    public NoText [] value();
}
//  annotation NoTexts

/*
 *  End of File
 */