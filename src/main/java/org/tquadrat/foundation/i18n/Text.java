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
import static org.tquadrat.foundation.i18n.TextUse.TEXTUSE_DEFAULT;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;

/**
 *  <p>{@summary Use this annotation to define a text &ndash; usually for a UI
 *  element or alike &ndash; that has to be translated.} Messages will be
 *  defined with the annotation
 *  {@link Message}.</p>
 *  <p>The texts will be stored in a
 *  {@link java.util.ResourceBundle ResourceBundle}
 *  with the base bundle name that is identified through the annotation
 *  {@link BaseBundleName &#64;BaseBundleName}.
 *  The documentation for the method
 *  {@link java.util.ResourceBundle#getBundle(String,java.util.Locale,ClassLoader) getBundle()}
 *  describes the detection strategy. The build process takes care of the
 *  generation of the resource bundle properties file.</p>
 *  <p>Use this annotation as follows:</p>
 *  <pre><code>  &hellip;
 *  &#64;Text
 *  (
 *      description = "A text",
 *      translations =
 *      {
 *          &#64;Translation( language = "en", text = "English text" ),
 *          &#64;Translation( language = "de", text = "Text in Deutsch" )
 *      }
 *  )
 *  private static final String TXT_TextKey = I18nUtil.composeTextKey( SampleClass.class, TextUse.TXT, "TextKey" );
 *  &hellip;</code></pre>
 *  <p>For the generation, the content of the field {@code TXT_TextKey} is
 *  irrelevant, the key will be built from the name of the field plus the
 *  fully qualified name of the class. But for the retrieval of the text, the
 *  contents of the field needs to match that generated key.</p>
 *  <p>The prefix {@code TXT} is defined in
 *  {@link TextUse}
 *  and will be derived from the name; if this is not desired, an alternative
 *  form of the annotation can be used:</p>
 *  <pre><code>  &hellip;
 *  &#64;Text
 *  (
 *      &hellip;
 *  )
 *  private static final String m_TextKey = I18nUtil.composeTextKey( SampleClass.class, TextUse.TXT, "TextKey" );
 *  &hellip;</code></pre>
 *  <p>This works because {@code TXT} is the default for the text use.</p>
 *  <p>It is also possible to define the id and the text use explicitly:</p>
 *  <pre><code>  &hellip;
 *  &#64;Text
 *  (
 *      description = "A text",
 *      id = "TextKey",
 *      use = TextUse.TXT,
 *      translations =
 *      {
 *          &#64;Translation( language = "en", text = "English text" ),
 *          &#64;Translation( language = "de", text = "Text in Deutsch" )
 *      }
 *  )
 *  private static final String m_Text = I18nUtil.composeTextKey( SampleClass.class, TextUse.TXT, "TextKey" );
 *  &hellip;</code></pre>
 *  <p>This format is required when the annotation is applied to a method, in
 *  this case to a getter:</p>
 *  <pre><code>  &hellip;
 *  &#64;Text
 *  (
 *      description = "The name of the property",
 *      translations =
 *      {
 *          &#64;Translation( language = "en", text = "Property" ),
 *          &#64;Translation( language = "de", text = "Eigenschaft" )
 *      }
 *  )
 *  &#64;Text
 *  (
 *      description = "The caption for the property",
 *      use = TextUse.CAPTION,
 *      translations =
 *      {
 *          &#64;Translation( language = "en", text = "Property: " ),
 *          &#64;Translation( language = "de", text = "Eigenschaft: " )
 *      }
 *  )
 *  &#64;Text
 *  (
 *      description = "The tooltip for the property",
 *      use = TextUse.TOOLTIP,
 *      translations =
 *      {
 *          &#64;Translation( language = "en", text = "The property" ),
 *          &#64;Translation( language = "de", text = "Die Eigenschaft" )
 *      }
 *  )
 *  public String getProperty();
 *  &hellip;</code></pre>
 *  <p>This sample would generate texts with the keys below, given that the
 *  method belongs to the interface {@code com.sample.Example}:</p>
 *  <ul>
 *      <li>{@code com.sample.Example.NAME_Property}</li>
 *      <li>{@code com.sample.Example.CAPTION_Property}</li>
 *      <li>{@code com.sample.Example.TOOLTIP_Property}</li>
 *  </ul>
 *  <p>If the method is not a getter, a setter or an &quot;add&quot; method,
 *  both {@code id} and {@code use} are always mandatory.</p>
 *  <p>For {@code enum}s, this form is applicable:</p>
 *  <pre><code>  &hellip;
 *  public enum WhatEver
 *  {
 *      &#64;Text
 *      (
 *          description = "The name for an enum value",
 *          translations =
 *          {
 *              &#64;Translation( language = "en", text = "Enumeration Value" ),
 *              &#64;Translation( language = "de", text = "Aufz&auml;hlungswert" )
 *          }
 *      )
 *      ENUM_VALUE;
 *
 *      &hellip;
 *  }
 *  //  enum WhatEver</code></pre>
 *  <p>The argument for
 *  {@link java.util.ResourceBundle#getString(String) ResourceBundle.getString()}
 *  in this case can be composed through a call to
 *  {@link org.tquadrat.foundation.i18n.I18nUtil#composeTextKey(Enum) I18nUtil.composeTextKey()};
 *  that can be used for the implementation of
 *  {@code WhatEver.toString()} like this:</p>
 *  <pre><code>  &hellip;
 *  public final toString()
 *  {
 *      String key = composeTextKey( this );
 *      String retValue = getBundle().getString( key );
 *
 *      &#47;&#47;---* Done *-----------------------------------------------------------
 *      return retValue;
 *  }  &#47;&#47;  toString()
 *  &hellip;</code></pre>
 *  <p>Different from messages, it is somehow expected that the returned values
 *  may contain escape sequences that can be converted by a call to
 *  {@link String#translateEscapes()}.</p>
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: Text.java 882 2021-02-27 19:01:25Z tquadrat $
 *  @since 0.1.0
 */
@Retention( SOURCE )
@Target( {FIELD, METHOD} )
@Repeatable( Texts.class )
@ClassVersion( sourceVersion = "$Id: Text.java 882 2021-02-27 19:01:25Z tquadrat $" )
@API( status = STABLE, since = "0.1.0" )
public @interface Text
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
     *  <p>{@summary The use of the text; the default is
     *  {@link TextUse#TEXTUSE_DEFAULT}.}</p>
     *  <p>This will be replaced with
     *  {@link TextUse#NAME}
     *  if the annotated element is method for a property (a getter, setter or
     *  an &quot;add&quot; method),
     *  with
     *  {@link TextUse#STRING}
     *  if an {@code enum} is annotated, and with
     *  {@link TextUse#TXT}
     *  for any other case.</p>
     *
     *  @return The text use.
     */
    TextUse use() default TEXTUSE_DEFAULT;

    /**
     *  Returns the text id for the text. If empty, the <i>name</i> of the
     *  annotated field is used when the annotation is applied to field;
     *  otherwise the annotation processor will throw an error.
     *
     *  @return The id, if set; otherwise the empty String.
     */
    String id() default "";

    /**
     *  Returns the list of valid translations for the text.
     *
     *  @return The translations.
     */
    Translation [] translations();
}
//  annotation Text

/*
 *  End of File
 */