/*
 * ============================================================================
 * Copyright © 2002-2022 by Thomas Thrien.
 * All Rights Reserved.
 * ============================================================================
 * Licensed to the public under the agreements of the GNU Lesser General Public
 * License, version 3.0 (the "License"). You may obtain a copy of the License at
 * http://www.gnu.org/licenses/lgpl.html
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

/**
 *  <p>{@summary An API for the localisation (&quot;l10n&quot;, although this
 *  abbreviation is rarely used) or internationalisation (&quot;i18n&quot;) of
 *  an application (and, with some limitations, of a library).} It provides
 *  annotations that allows to have multiple translations for a text directly
 *  in the source code. The annotation processor component provided with the
 *  project
 *  {@href https://tquadrat.github.io/foundation-i18n-ap org.tquadrat.foundation.i18n.ap}
 *  externalises these texts to regular resource bundle properties files.</p>
 *  <p>For the configuration of the annotation processor, refer to the
 *  documentation for that project.</p>
 *
 *  <h2>{@anchor #h2_i18n Internationalisation of Texts and Messages}</h2>
 *  <p>In Java, localisation or internationalisation is usually done by using
 *  an instance of
 *  {@link java.util.ResourceBundle}
 *  that is retrieved by a call to
 *  {@link java.util.ResourceBundle#getBundle(String)}.
 *  It will load the texts for the currently active
 *  {@link java.util.Locale Locale}
 *  (see
 *  {@link java.util.Locale#getDefault()}).</p>
 *  <p>There are several possible sources for the texts itself, but the most
 *  commonly used are Java properties files named following a special naming
 *  convention; for the file format refer to
 *  {@link java.util.Properties}.</p>
 *  <p>The challenge is to update these properties files in parallel to writing
 *  the code, ensuring that the texts are really there when needed.</p>
 *  <p>As a solution, this library provides two annotations (plus three helper
 *  annotations) that are processed by an annotation processor to create the
 *  resource bundle properties files during compilation.</p>
 *
 *  <h3>{@anchor #h3_i18n_annotations The Annotations for the I18N feature}</h3>
 *  <p>The internationalisation feature provides six annotations in total:</p>
 *  <ul>
 *      <li>{@href #h4_basebundlename <code>&#64;BaseBundleName</code>}</li>
 *      <li>{@href #h4_message <code>&#64;Message</code>}</li>
 *      <li>{@href #h4_message_prefix <code>&#64;MessagePrefix</code>}</li>
 *      <li>{@href #h4_text <code>&#64;Text</code>}</li>
 *      <li>{@href #h4_translation <code>&#64;Translation</code>}</li>
 *      <li>{@href #h4_useadditionaltexts <code>&#64;UseAdditionalTexts</code>}</li>
 *  </ul>
 *  <p>The annotations <code>&#64;BaseBundleName</code> and
 *  <code>&#64;MessagePrefix</code> are used to configure the generation
 *  process, and <code>&#64;UseAdditionalTexts</code> provides the location for
 *  additional texts (refer to respective chapter
 *  {@href #h3_additionaltexts below},
 *  while the remaining three do define a text or a message.</p>
 *  <p>The semantic differentiation between a <i>Text</i>and a <i>Message</i>
 *  is just high-handed: the assumption is that a message can be used freely at
 *  various locations in the code, while a text is unique for just one single
 *  context. This is reflected in the respective annotations.</p>
 *
 *  <h4><a id="h4_basebundlename"></a>{@link org.tquadrat.foundation.i18n.BaseBundleName &#64;BaseBundleName}</h4>
 *  <p>This annotation has to be applied to a {@code String} constant that
 *  holds the base bundle name; that name is basically a fully qualified Java
 *  class name, but it is not required (not even wanted) that this class
 *  exists.</p>
 *  <p>The annotation has two attributes:</p>
 *  <dl>
 *      <dt>{@anchor #dt_default_language <code>String defaultLanguage</code>}</dt>
 *      <dd><p>This is the ISO&nbsp;639-1two-letter-code for the default
 *      language; the resource bundle for this language is used when there is
 *      none for the current language or locale. Although it is said
 *      &quot;language&quot;, the value could be also a full fledged
 *      locale.</p>
 *      <p>The default value, that is used when the annotation was not applied,
 *      is &quot;{@code en}&quot;.</p></dd>
 *      <dt><code>boolean createResourceBundleProvider</code></dt>
 *      <dd><p>As the properties files for the resource bundles are resources that
 *      are now local to a module, a service is required to expose the resource
 *      bundle to other modules. This is done through an instance of
 *      {@link java.util.spi.ResourceBundleProvider}.</p>
 *      <p>This flag controls whether the annotation processor will generate
 *      such a resource bundle provider; the default is{@code false}.</p></dd>
 *  </dl>
 *  <p>If this annotation is not used at all, the constant
 *  {@value org.tquadrat.foundation.i18n.I18nUtil#DEFAULT_BASEBUNDLENAME}
 *  will be used for the base bundle name, the locale
 *  {@link java.util.Locale#ENGLISH ENGLISH}
 *  is used as the default language and no resource bundle provider will be
 *  generated by the annotation processor.</p>
 *  <p>The use of this annotation may look like this:</p>
 *
 *  <div class="source-container"><pre>  <span class="source-line-no">001</span>&hellip;
 *  <span class="source-line-no">002</span>&#47;**
 *  <span class="source-line-no">003</span> *  The base bundle name.
 *  <span class="source-line-no">004</span> *&#47;
 *  <span class="source-line-no">005</span>&#064;BaseBundleName( defaultLanguage = "de", createResourceBundleProvider = false )
 *  <span class="source-line-no">006</span>public static final String m_BaseBundleName = "com.test.Messages";
 *  <span class="source-line-no">007</span>
 *  <span class="source-line-no">008</span>&hellip;</pre></div>
 *
 *  <p>When you are using the translations for German (de) and English (en),
 *  the annotation processor will generate the properties files
 *  {@code com/test/Messages.properties} (containing the German texts and
 *  being the default or fallback resource) and
 *  {@code com/test/Message_en.properties} (with the English texts).</p>
 *
 *  <h4><a id="h4_message_prefix"></a>{@link org.tquadrat.foundation.i18n.MessagePrefix &#64;MessagePrefix}</h4>
 *  <p>The keys for messages are numbers or short Strings that will be prefixed
 *  with the value of the {@code String} that is annotated with this
 *  annotation. That value should somehow identify the source module so that
 *  the origin of the message can be identified easily when it shows up on the
 *  user's screen.</p>
 *  <p>The annotation is used as in the sample below:</p>
 *
 *  <div class="source-container"><pre>  <span class="source-line-no">001</span>&hellip;
 *  <span class="source-line-no">002</span>&#47;**
 *  <span class="source-line-no">003</span> *  The message prefix.
 *  <span class="source-line-no">004</span> *&#47;
 *  <span class="source-line-no">005</span>&#064;MessagePrefix
 *  <span class="source-line-no">006</span>public static final String m_MessagePrefix = "SFX";
 *  <span class="source-line-no">007</span>
 *  <span class="source-line-no">008</span>&hellip;</pre></div>
 *
 *  <p>For the message id 1, this will generate the message key
 *  {@code SFX_000001}.</p>
 *
 *  <h4><a id="h4_translation"></a>{@link org.tquadrat.foundation.i18n.Translation &#64;Translation}</h4>
 *  <p>This annotation does make sense only in conjunction with the annotations
 *  {@href #h4_message &#64;Message}
 *  and
 *  {@href #h4_text &#64;Text};
 *  it provides the concrete message text in its various translations.</p>
 *  <p>The annotation has the following attributes:</p>
 *  <dl>
 *      <dt>{@anchor #dt_language <code>String language</code>}</dt>
 *      <dd><p>This is the ISO&nbsp;639-1 two-letter-code for the language of the
 *      text; same as for the
 *      {@href #dt_default_language default language}
 *      this could be a full fledged locale String as well: think about the
 *      following case:</p>
 *
 *  <div class="source-container"><pre>  <span class="source-line-no">001</span>&hellip;
 *  <span class="source-line-no">002</span>translations =
 *  <span class="source-line-no">003</span>{
 *  <span class="source-line-no">004</span>    &#064;Translation( language = "de", text = "Farbe" ),
 *  <span class="source-line-no">005</span>    &#064;Translation( language = "en", text = "Colour" ),
 *  <span class="source-line-no">006</span>    &#064;Translation( language = "en_US", text = "Color" )
 *  <span class="source-line-no">007</span>}
 *  <span class="source-line-no">008</span>&hellip;</pre></div>
 *
 *      <p>Similar samples where the spelling differs from country to country
 *      even inside the same language do exist for several languages.</p></dd>
 *      <dt><code>String text</code></dt>
 *      <dd><p>This is the concrete text &ndash; or more correct, that is the
 *      {@code format} argument for a call to
 *      {@link java.util.Formatter#format(String, Object[]) Formatter.format()}.
 *      This means that it may contain the &quot;%&hellip;&quot; placeholders
 *      that are defined by
 *      {@link java.util.Formatter}.</p>
 *      <p>If the text will contain more than one placeholder they should be
 *      numbered (like &quot;%1$s&quot;) because different translation may
 *      require different sequences for the arguments that should replace the
 *      placeholders; for details, refer to the documentation of
 *      {@link java.util.Formatter}.</p></dd>
 * </dl>
 *
 *  <h4><a id="h4_message"></a>{@link org.tquadrat.foundation.i18n.Message &#64;Message}</h4>
 *  <p>This annotation, that will be applied to an {@code int} or
 *  {@code String} constant, defines the text for a message; the
 *  {@code description} attribute should describe the message context to allow
 *  translators to add further translations; the attribute {@code translations}
 *  contains the concrete texts as shown in the
 *  {@href #dt_language description for the <code>&#64;Translation</code>, above}.
 *  The whole thing may look like this:</p>
 *
 *  <div class="source-container"><pre>  <span class="source-line-no">001</span>&hellip;
 *  <span class="source-line-no">002</span>&#64;Message
 *  <span class="source-line-no">003</span>(
 *  <span class="source-line-no">004</span>    description = "This message indicates that the socket could not be opened to listen on the given port",
 *  <span class="source-line-no">005</span>    translations =
 *  <span class="source-line-no">006</span>    {
 *  <span class="source-line-no">007</span>        &#64;Translation( language = "en", text = "Cannot open socket on port '%d'" ),
 *  <span class="source-line-no">008</span>        &#64;Translation( language = "de", text = "Socket kann auf Port '%d' nicht geöffnet werden" )
 *  <span class="source-line-no">009</span>    }
 *  <span class="source-line-no">010</span>)
 *  <span class="source-line-no">011</span>public static final int MSG_CannotOpenSocket = 1704;
 *  <span class="source-line-no">012</span>&hellip;</pre></div>
 *
 *  <p>or</p>
 *
 *  <div class="source-container"><pre>  <span class="source-line-no">021</span>&hellip;
 *  <span class="source-line-no">022</span>&#64;Message
 *  <span class="source-line-no">023</span>(
 *  <span class="source-line-no">024</span>    description = "This message indicates that the host with the given name does not respond in time",
 *  <span class="source-line-no">025</span>    translations =
 *  <span class="source-line-no">026</span>    {
 *  <span class="source-line-no">027</span>        &#64;Translation( language = "en", text = "Host '%1$s' does not respond within %2$d milliseconds" ),
 *  <span class="source-line-no">028</span>        &#64;Translation( language = "de", text = "Der Host '%1$s' hat nicht innerhalb von %2$d ms geantwortet" )
 *  <span class="source-line-no">029</span>    }
 *  <span class="source-line-no">039</span>)
 *  <span class="source-line-no">040</span>public static final String MSG_NoResponseFromHost = "NoResponseFromHost";
 *  <span class="source-line-no">041</span>&hellip;</pre></div>
 *
 *  <p>The key for the resource bundle will be generated from <i>the contents of
 *  the constant</i> plus the
 *  {@href #h4_message_prefix message prefix}
 *  &ndash; this is different from the behaviour of the
 *  {@href #h4_text <code>&#64;Text</code> annotation}.</p>
 *
 *  <h4><a id="h4_text"></a>{@link org.tquadrat.foundation.i18n.Text &#64;Text}</h4>
 *  <p>Different from the
 *  {@href #h4_message <code>&#64;Message</code> annotation},
 *  this annotation can be applied to any kind of field, not only to constants.
 *  It has the following attributes:</p>
 *  <dl>
 *      <dt><code>String description</code></dt>
 *      <dd><p>As for
 *      {@href #h4_message <code>&#64;Message</code>},
 *      this should give translators some hints on how to translate the text
 *      into the target language.</p></dd>
 *      <dt><code>String id</code></dt>
 *      <dd><p>This optional attribute is the resource bundle key for the text;
 *      if missing, that key will be derived <i>from the name</i> of the
 *      annotated field.</p></dd>
 *      <dt><code>boolean addClass</code></dt>
 *      <dd><p>If this flag is {@code true}, the resource bundle key will be
 *      prepended by the fully qualified name of the class that contains the
 *      annotated field.</p></dd>
 *      <dt><code>&#64;Translation [] translations</code></dt>
 *      <dd><p>These are the translations for the text, as already described
 *      {@href #dt_language above}.</p></dd>
 *  </dl>
 *  <p>The simplest form to use this annotation looks like this:</p>
 *
 *  <div class="source-container"><pre>  <span class="source-line-no">021</span>&hellip;
 *  <span class="source-line-no">022</span>&#64;Text
 *  <span class="source-line-no">023</span>(
 *  <span class="source-line-no">024</span>    description = "The caption for the input field that takes the title of a book",
 *  <span class="source-line-no">025</span>    translations =
 *  <span class="source-line-no">026</span>    {
 *  <span class="source-line-no">027</span>        &#64;Translation( language = "en", text = "Book Title" ),
 *  <span class="source-line-no">028</span>        &#64;Translation( language = "de", text = "Buchtitel" )
 *  <span class="source-line-no">029</span>    }
 *  <span class="source-line-no">030</span>)
 *  <span class="source-line-no">031</span>private static final String CAPTION_BookTitle = I18nUtil.composeTextKey( BookEntryForm.class, TextUse.CAPTION, "BookTitle" );
 *  <span class="source-line-no">032</span>&hellip;</pre></div>
 *
 *  <p>For the generation of the resource bundle properties files, the content
 *  of the field {@code CAPTION_BookTitle} is irrelevant, the key will be built from
 *  the fully qualified name of the class that contains the field and its
 *  <i>name</i>; but for the retrieval of the text, the contents of the field
 *  needs to match the generated key.</p>
 *  <p>The prefix {@code CAPTION} is defined in
 *  {@link org.tquadrat.foundation.i18n.TextUse}
 *  and will be derived from the name of the field in this case. Alternatively,
 *  this form can be used that defines both {@code id} and {@code use}
 *  explicitly to get the same key:</p>
 *
 *  <div class="source-container"><pre>  <span class="source-line-no">021</span>&hellip;
 *  <span class="source-line-no">022</span>&#64;Text
 *  <span class="source-line-no">023</span>(
 *  <span class="source-line-no">024</span>    description = "The caption for the input field that takes the title of a book",
 *  <span class="source-line-no">025</span>    id = "BookTitle",
 *  <span class="source-line-no">026</span>    use = TextUse.CAPTION,
 *  <span class="source-line-no">027</span>    translations =
 *  <span class="source-line-no">028</span>    {
 *  <span class="source-line-no">029</span>        &#64;Translation( language = "en", text = "Book Title" ),
 *  <span class="source-line-no">030</span>        &#64;Translation( language = "de", text = "Buchtitel" )
 *  <span class="source-line-no">031</span>    }
 *  <span class="source-line-no">032</span>)
 *  <span class="source-line-no">033</span>private static final String m_BookTitleCaption = I18nUtil.composeTextKey( BookEntryForm.class, TextUse.CAPTION, "BookTitle" );
 *  <span class="source-line-no">034</span>&hellip;</pre></div>
 *
 *  <p>Sometimes, {@code enum} values should have a human readable
 *  representation that needs to be available in different translations. For an
 *  {@code enum} type named {@code Color} this would look like this:</p>
 *
 *  <div class="source-container"><pre>  <span class="source-line-no">101</span>&hellip;
 *  <span class="source-line-no">102</span>public enum Color
 *  <span class="source-line-no">103</span>{
 *  <span class="source-line-no">104</span>    &#64;Text
 *  <span class="source-line-no">105</span>    (
 *  <span class="source-line-no">106</span>        description = "The colour 'red'",
 *  <span class="source-line-no">107</span>        translations =
 *  <span class="source-line-no">108</span>        {
 *  <span class="source-line-no">109</span>            &#64;Translation( locale = "en", text = "red" ),
 *  <span class="source-line-no">110</span>            &#64;Translation( locale = "de", text = "rot" )
 *  <span class="source-line-no">111</span>        }
 *  <span class="source-line-no">112</span>    )
 *  <span class="source-line-no">113</span>    RED,
 *  <span class="source-line-no">114</span>
 *  <span class="source-line-no">115</span>    &#64;Text
 *  <span class="source-line-no">116</span>    (
 *  <span class="source-line-no">117</span>        description = "The colour 'yellow'",
 *  <span class="source-line-no">118</span>        translations =
 *  <span class="source-line-no">119</span>        {
 *  <span class="source-line-no">120</span>            &#64;Translation( locale = "en", text = "yellow" ),
 *  <span class="source-line-no">121</span>            &#64;Translation( locale = "de", text = "gelb" )
 *  <span class="source-line-no">122</span>        }
 *  <span class="source-line-no">123</span>    )
 *  <span class="source-line-no">124</span>    YELLOW,
 *  <span class="source-line-no">125</span>
 *  <span class="source-line-no">141</span>    &hellip;
 *  <span class="source-line-no">142</span>
 *  <span class="source-line-no">143</span>    &#64;Override
 *  <span class="source-line-no">144</span>    public final toString()
 *  <span class="source-line-no">145</span>    {
 *  <span class="source-line-no">146</span>        var bundle = ResourceBundle.getBundle( BASE_BUNDLE_NAME );
 *  <span class="source-line-no">147</span>        var key = I18nUtil.composeTextKey( this );
 *  <span class="source-line-no">148</span>        var retValue = I18nUtil.retrieveText( bundle, key );
 *  <span class="source-line-no">149</span>
 *  <span class="source-line-no">150</span>        &#47;&#47;---* Done *-----------------------------------------------------------
 *  <span class="source-line-no">151</span>        return retValue;
 *  <span class="source-line-no">152</span>    }   &#47;&#47;  toString()
 *  <span class="source-line-no">153</span>}
 *  <span class="source-line-no">154</span>//  enum Color
 *  <span class="source-line-no">155</span>&hellip;</pre></div>
 *
 *  <p>The argument for the call to
 *  {@link java.util.ResourceBundle#getString(String) ResourceBundle.getString()}
 *  can be calculated as</p>
 *  <pre><code>Color.class.getName() + ".STRING_" + getName();</code></pre>
 *  <p>This can be used for the implementation of {@code Color.toString()} as
 *  shown in the sample.</p>
 *  <p>Finally, the annotation can be applied to an arbitrary method, preferred
 *  to a <i>getter</i>. This can be used to provide usage texts for the
 *  definition of options and arguments, among other possibilities. The sample
 *  below assumes that the method {@code getOwner()} belongs to the interface
 *  {@code com.sample.Example}:</p>
 *
 *  <div class="source-container"><pre>  <span class="source-line-no">201</span>&hellip;
 *  <span class="source-line-no">202</span>&#64;Text
 *  <span class="source-line-no">203</span>(
 *  <span class="source-line-no">204</span>    description = "The name of the property 'owner'",
 *  <span class="source-line-no">205</span>    translations =
 *  <span class="source-line-no">206</span>    {
 *  <span class="source-line-no">207</span>        &#64;Translation( language = "en", text = "Proprietor/Proprietress" ),
 *  <span class="source-line-no">208</span>        &#64;Translation( language = "de", text = "Eigentümer/Eigentümerin" )
 *  <span class="source-line-no">209</span>    }
 *  <span class="source-line-no">210</span>)
 *  <span class="source-line-no">211</span>&#64;Text
 *  <span class="source-line-no">212</span>(
 *  <span class="source-line-no">213</span>    description = "The caption for the property 'owner'",
 *  <span class="source-line-no">214</span>    use = TextUse.CAPTION,
 *  <span class="source-line-no">215</span>    translations =
 *  <span class="source-line-no">216</span>    {
 *  <span class="source-line-no">217</span>        &#64;Translation( language = "en", text = "Proprietor (Lastname, Firstname): " ),
 *  <span class="source-line-no">218</span>        &#64;Translation( language = "de", text = "Eigentümer (Hausname, Vorname): " )
 *  <span class="source-line-no">219</span>    }
 *  <span class="source-line-no">220</span>)
 *  <span class="source-line-no">221</span>&#64;Text
 *  <span class="source-line-no">222</span>(
 *  <span class="source-line-no">223</span>    description = "The tooltip for the property 'owner'",
 *  <span class="source-line-no">224</span>    use = TextUse.TOOLTIP,
 *  <span class="source-line-no">225</span>    translations =
 *  <span class="source-line-no">226</span>    {
 *  <span class="source-line-no">227</span>        &#64;Translation( language = "en", text = "The name of the proprietor" ),
 *  <span class="source-line-no">228</span>        &#64;Translation( language = "de", text = "Der Name des Eigentümers" )
 *  <span class="source-line-no">229</span>    }
 *  <span class="source-line-no">230</span>)
 *  <span class="source-line-no">231</span>&#64;Text
 *  <span class="source-line-no">232</span>(
 *  <span class="source-line-no">233</span>    description = "The usage text for the property 'owner'",
 *  <span class="source-line-no">234</span>    use = TextUse.USAGE,
 *  <span class="source-line-no">235</span>    translations =
 *  <span class="source-line-no">236</span>    {
 *  <span class="source-line-no">237</span>        &#64;Translation( language = "en", text = "The name of the proprietor" ),
 *  <span class="source-line-no">238</span>        &#64;Translation( language = "de", text = "Der Name des Eigentümers" )
 *  <span class="source-line-no">239</span>    }
 *  <span class="source-line-no">240</span>)
 *  <span class="source-line-no">241</span>&#64;Option( name = "-o", aliases = {"--owner", "--proprietor"}, metavar = "NAME", usage = "The name of the proprietor", usageKey = "com.sample.Example.USAGE_Owner" )
 *  <span class="source-line-no">242</span>public String getOwner();
 *  <span class="source-line-no">243</span>&hellip;</pre></div>
 *
 *  <p>The generated resource bundle keys for the texts are:</p>
 *  <ul>
 *      <li>{@code com.sample.Example.NAME_Owner}</li>
 *      <li>{@code com.sample.Example.CAPTION_Owner}</li>
 *      <li>{@code com.sample.Example.TOOLTIP_Owner}</li>
 *      <li>{@code com.sample.Example.USAGE_Owner}</li>
 *  </ul>
 *  <p>This works because {@code com.sample.Example.getOwner()} is a
 *  <i>getter</i> method and the name of the property (&quot;owner&quot;) will
 *  be taken as the id.</p>
 *  <p>For method that are not getters, setters or &quot;add&quot; methods,
 *  {@code id} and {@code use} have to be set explicitly, otherwise an
 *  exception is thrown by the annotation processor.</p>
 *  <p>It is a bit clumsy that the generated key has to be guessed for the
 *  value of the {@code usageKey} attribute of the {@code @Option} and
 *  {@code @Argument} annotations (defined in {@code config} module). This is
 *  because the annotation processor cannot modify existing source code, and
 *  because annotation attributes do allow only compile time constants as
 *  values. That's also the reason why the resource bundle keys have to be
 *  generated from the annotation attributes, instead of taking the value of
 *  the field.</p>
 *
 *  <h3>{@anchor #h3_additionaltexts Additional Texts}</h3>
 *  <p>Sometimes it is not feasible or just not wanted to define the messages
 *  and texts as annotations to fields or methods. This is quite often the
 *  case for longer texts, like help output, or for the texts used to build a
 *  (G)UI.</p>
 *  <p>To address this, texts can be defined in file named
 *  {@value org.tquadrat.foundation.i18n.I18nUtil#ADDITIONAL_TEXT_FILE}.
 *  This file can be located in the root of the source tree, but its location
 *  can be also configured through the annotation processor option
 *  {@value org.tquadrat.foundation.i18n.I18nUtil#ADDITIONAL_TEXT_LOCATION},
 *  or it can be provided through the annotation
 *  {@link org.tquadrat.foundation.i18n.UseAdditionalTexts &#64;UseAdditionalTexts}.</p>
 *  <p>That file has to comply the DTD defined in
 *  {@href doc-files/AdditionalText.dtd.html <code>AdditionalText.dtd</code>}.</p>
 */

package org.tquadrat.foundation.i18n;

/*
 *  End of File
 */