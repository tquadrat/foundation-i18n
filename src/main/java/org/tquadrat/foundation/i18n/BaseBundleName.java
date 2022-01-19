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

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;

/**
 *  <p>{@summary This annotation is used to mark a String constant that holds
 *  the base bundle name for the resource bundle for the messages and texts.}
 *  When the annotation processor finds more than one field with this
 *  annotation, the result is unpredictable.</p>
 *  <p>The code for the default language is used when there is no resource
 *  bundle for the current language/locale setting. If not explicitly set, the
 *  default is &quot;{@code en}&quot;, the language code for English.</p>
 *  <p>If this annotation is not used at all, the constant
 *  {@value org.tquadrat.foundation.i18n.I18nUtil#DEFAULT_BASEBUNDLENAME}
 *  will be used for the base bundle name, the locale
 *  {@link java.util.Locale#ENGLISH ENGLISH}
 *  is used as the default language and no resource bundle provider will be
 *  generated by the annotation processor.</p>
 *  <p>When used with modules, the base bundle name should be a simple class
 *  name <i>without</i> a package name; otherwise a resource bundle provider
 *  has to be generated. This is because this library is one module, while the
 *  (generated) application resource bundle lives in another module, and is
 *  therfore not accessible. In opposite, a bundle without package is
 *  accessible from everywhere.</p>
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: BaseBundleName.java 993 2022-01-19 22:26:20Z tquadrat $
 *  @since 0.1.0
 */
@Documented
@Retention( SOURCE )
@Target( FIELD )
@ClassVersion( sourceVersion = "$Id: BaseBundleName.java 993 2022-01-19 22:26:20Z tquadrat $" )
@API( status = STABLE, since = "0.1.0" )
public @interface BaseBundleName
{
        /*------------*\
    ====** Attributes **=======================================================
        \*------------*/
    /**
     *  The flag that indicates whether a
     *  {@link java.util.spi.ResourceBundleProvider}
     *  should be generated by the annotation processor during compilation.
     *
     *  @return {@code true} if the resource bundle provider should be created,
     *      {@code false} (the default) otherwise.
     */
    boolean createResourceBundleProvider() default false;

    /**
     *  The default language.
     *
     *  @return The language code for the default language.
     */
    String defaultLanguage() default "en";
}
//  annotation BaseBundleName

/*
 *  End of File
 */