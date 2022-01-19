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

import static java.lang.System.setProperty;
import static org.apiguardian.api.API.Status.DEPRECATED;
import static org.apiguardian.api.API.Status.INTERNAL;
import static org.apiguardian.api.API.Status.STABLE;
import static org.tquadrat.foundation.i18n.TextUse.STRING;
import static org.tquadrat.foundation.lang.CommonConstants.ISO8859_1;
import static org.tquadrat.foundation.lang.CommonConstants.PROPERTY_RESOURCEBUNDLE_ENCODING;
import static org.tquadrat.foundation.lang.DebugOutput.ifDebug;
import static org.tquadrat.foundation.lang.Objects.requireNonNullArgument;
import static org.tquadrat.foundation.lang.Objects.requireNotEmptyArgument;
import static org.tquadrat.foundation.util.StringUtils.format;
import static org.tquadrat.foundation.util.StringUtils.isNotEmptyOrBlank;

import java.util.MissingResourceException;
import java.util.Optional;
import java.util.ResourceBundle;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.annotation.UtilityClass;
import org.tquadrat.foundation.exception.PrivateConstructorForStaticClassCalledError;
import org.tquadrat.foundation.lang.Objects;

/**
 *  Utilities that are related to the i18n feature.
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: I18nUtil.java 993 2022-01-19 22:26:20Z tquadrat $
 *  @since 0.1.0
 *
 *  @UMLGraph.link
 */
@UtilityClass
@ClassVersion( sourceVersion = "$Id: I18nUtil.java 993 2022-01-19 22:26:20Z tquadrat $" )
@API( status = STABLE, since = "0.1.0" )
public final class I18nUtil
{
        /*-----------*\
    ====** Constants **========================================================
        \*-----------*/
    /**
     *  The name for the file with the additional texts: {@value}.
     */
    @API( status = STABLE, since = "0.1.0" )
    public static final String ADDITIONAL_TEXT_FILE = "AdditionalTexts.xml";

    /**
     *  The name of the annotation processor option that provides the location
     *  for the file with the additional texts
     *  ({@value org.tquadrat.foundation.i18n.I18nUtil#ADDITIONAL_TEXT_FILE}):
     *  {@value}.
     */
    @API( status = STABLE, since = "0.1.0" )
    public static final String ADDITIONAL_TEXT_LOCATION = "org.tquadrat.foundation.i18n.ap.textLocation";

    /**
     *  The default name for the resource bundle: {@value}.
     */
    @API( status = STABLE, since = "0.1.0" )
    public static final String DEFAULT_BASEBUNDLENAME = "MessagesAndTexts";

    /**
     *  The default message prefix: {@value}.
     */
    @API( status = STABLE, since = "0.1.0" )
    public static final String DEFAULT_MESSAGE_PREFIX = "MSG";

        /*--------------*\
    ====** Constructors **=====================================================
        \*--------------*/
    /**
     *  No instance allowed for this class.
     */
    private I18nUtil() { throw new PrivateConstructorForStaticClassCalledError( I18nUtil.class ); }

        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  <p>{@summary Composes a message key.}</p>
     *  <p>The format for the key is like this</p>
     *  <pre><code>&lt;<i>message_prefix</i>&gt;-&lt;<i>id</i>&gt;</code></pre>
     *
     *  @param  messagePrefix   The message prefix.
     *  @param  id  The message id.
     *  @return The message key.
     */
    @API( status = STABLE, since = "0.1.0" )
    public static final String composeMessageKey( final String messagePrefix, final String id )
    {
        final var retValue = format( "%s-%s", requireNotEmptyArgument( messagePrefix, "messagePrefix" ), requireNotEmptyArgument( id, "id" ) );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  composeMessageKey()

    /**
     *  <p>{@summary Composes a message key.}</p>
     *  <p>The format for the key is like this</p>
     *  <pre><code>&lt;<i>message_prefix</i>&gt;-&lt;<i>id</i>&gt;</code></pre>
     *  <p>The id will be a six-digit number, prepended with zeroes if
     *  required.</p>
     *
     *  @param  messagePrefix   The message prefix.
     *  @param  id  The message id.
     *  @return The message key.
     */
    @API( status = STABLE, since = "0.1.0" )
    public static final String composeMessageKey( final String messagePrefix, final int id )
    {
        final var retValue = format( "%s-%06d", requireNotEmptyArgument( messagePrefix, "messagePrefix" ), id );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  composeMessageKey()

    /**
     *  <p>{@summary Composes the resource bundle key for a text.}</p>
     *  <p>The format for the key is like this:</p>
     *  <pre><code>&lt;<i>class_name</i>&gt;.&lt;<i>use</i>&gt;_&lt;<i>id</i>&gt;</code></pre>
     *
     *  @param  sourceClass The class where the text was defined.
     *  @param  use The text use.
     *  @param  id  The id for the text, as from
     *      {@link org.tquadrat.foundation.i18n.Text#id() &#64;Text.id}.
     *  @return The text key.
     */
    @API( status = STABLE, since = "0.1.0" )
    public static final String composeTextKey( final Class<?> sourceClass, final TextUse use, final String id )
    {
        final var retValue = composeTextKey( requireNonNullArgument( sourceClass, "sourceClass" ).getName(), use, id );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  composeTextKey()

    /**
     *  <p>{@summary Composes the resource bundle key for a text.}</p>
     *  <p>The format for the key is like this:</p>
     *  <pre><code>&lt;<i>class_name</i>&gt;.&lt;<i>use</i>&gt;_&lt;<i>id</i>&gt;</code></pre>
     *
     *  @param  sourceClass The name of the class where the text was defined.
     *  @param  use The text use.
     *  @param  id  The id for the text, as from
     *      {@link org.tquadrat.foundation.i18n.Text#id() &#64;Text.id}.
     *  @return The text key.
     */
    @API( status = STABLE, since = "0.1.0" )
    public static final String composeTextKey( final String sourceClass, final TextUse use, final String id )
    {
        final var retValue = format( "%s.%s_%s", requireNotEmptyArgument( sourceClass, "sourceClass" ), requireNonNullArgument( use, "use" ).name(), requireNotEmptyArgument( id, "name" ) );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  composeTextKey()

    /**
     *  <p>{@summary Composes the resource bundle key for an <code>enum</code>
     *  value.}</p>
     *  <p>The format for the key is like this:</p>
     *  <pre><code>&lt;<i>class_name</i>&gt;.STRING_&lt;<i>name</i>&gt;</code></pre>
     *
     *  @param  <E> The type of the {@code enum} value.
     *  @param  value   The {@code enum} value.
     *  @return The text key.
     *
     *  @see Enum#name()
     */
    @API( status = STABLE, since = "0.1.0" )
    public static final <E extends Enum<?>> String composeTextKey( final E value )
    {
        final var sourceClass = requireNonNullArgument( value, "value" ).getDeclaringClass();
        final var id = value.name();
        final var use = STRING;
        final var retValue = composeTextKey( sourceClass, use, id );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  composeTextKey()

    /**
     *  Creates the fallback text or message when the resource bundle does not
     *  have a text for the given key.
     *
     *  @param  key The failed key.
     *  @param  args    The arguments.
     *  @return The fallback text.
     */
    @API( status = STABLE, since = "0.1.0" )
    public static final String createFallback( final String key, final Object... args )
    {
        final var retValue = format( "[%s] – %s", requireNotEmptyArgument( key, "key" ), Objects.toString( args ) );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  createFallback()

    /**
     *  Loads the resource bundle with the given base bundle name. If there is
     *  no resource bundle for the given base bundle name, the return value is
     *  {@linkplain Optional#empty() empty}.
     *
     *  @param  baseBundleName  The base bundle name.
     *  @return An instance of
     *      {@link Optional}
     *      that holds the resource bundle.
     */
    @SuppressWarnings( "AssignmentToNull" )
    @API( status = STABLE, since = "0.1.0" )
    public static final Optional<ResourceBundle> loadResourceBundle( final String baseBundleName )
    {
        //---* Force the use of UTF-8 for the resource bundle files *----------
        setProperty( PROPERTY_RESOURCEBUNDLE_ENCODING, ISO8859_1.name() );

        ResourceBundle bundle;
        try
        {
            final var bundleName = requireNotEmptyArgument( baseBundleName, "baseBundleName" );
            bundle = ResourceBundle.getBundle( bundleName );
        }
        catch( final MissingResourceException e )
        {
            ifDebug( e );
            bundle = null;
        }

        final var retValue = Optional.ofNullable( bundle );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  loadResourceBundle()

    /**
     *  Returns the message for the given key, or the alternative text.
     *
     *  @param  bundle  The resource bundle.
     *  @param  message The message.
     *  @param  messageKey  The resource bundle key for the alternative
     *      message.
     *  @param  args    The argument for the alternative message.
     *  @return The resolved message.
     *
     *  @deprecated Use
     *      {@link #resolveText(Optional, String, String, Object...)}
     *      instead.
     */
    @SuppressWarnings( {"OptionalUsedAsFieldOrParameterType", "BoundedWildcard"} )
    @Deprecated( forRemoval = true, since = "0.0.2" )
    @API( status = DEPRECATED, since = "0.0.2" )
    public static final String resolveMessage( final Optional<ResourceBundle> bundle, final String message, final String messageKey, final Object... args )
    {
        final var retValue = requireNonNullArgument( bundle, "bundle" ).isPresent() && isNotEmptyOrBlank( messageKey )
            ? retrieveText( bundle.get(), messageKey, args )
            : requireNotEmptyArgument( message, "message" );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  resolveMessage()

    /**
     *  Returns the message for the given key, or the alternative text.
     *
     *  @param  bundle  The resource bundle.
     *  @param  message The message.
     *  @param  messageKey  The resource bundle key for the alternative
     *      message.
     *  @param  args    The argument for the alternative message.
     *  @return The resolved message.
     *
     *  @deprecated Use
     *      {@link #resolveText(Optional, Optional, Optional, Object...)}
     *      instead.
     */
    @SuppressWarnings( "OptionalUsedAsFieldOrParameterType" )
    @Deprecated( forRemoval = true, since = "0.0.2" )
    @API( status = DEPRECATED, since = "0.0.2" )
    public static final String resolveMessage( final Optional<ResourceBundle> bundle, final Optional<String> message, final Optional<String> messageKey, final Object... args )
    {
        final var _message = requireNonNullArgument( message, "message" ).orElse( "[MissingMessage] – %s" );
        final var _messageKey = requireNonNullArgument( messageKey, "messageKey" ).orElse( "MissingMessage" );
        final var retValue = resolveMessage( bundle, _message, _messageKey, args );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  resolveMessage()

    /**
     *  <p>{@summary Returns the Text for the given key, or the alternative
     *  text.} This method is primarily used internally by the library, but can
     *  be also useful in other scenarios where the presence of a
     *  {@link ResourceBundle}
     *  is not guaranteed.</p>
     *  <p>If there is a resource bundle,
     *  {@link #retrieveText(ResourceBundle, String, Object...)}
     *  should be used instead.</p>
     *
     *  @note   <code>text</code> is used as is! The arguments will be applied
     *      only to a text that will be retrieved from the resource bundle!
     *
     *  @param  bundle  The resource bundle.
     *  @param  text    The alternative text that is used if there is no
     *      resource bundle, or that bundle does not contain a text for the
     *      given key.
     *  @param  textKey The resource bundle key for the text.
     *  @param  args    The argument for the retrieved text.
     *  @return The resolved text.
     */
    @SuppressWarnings( {"OptionalUsedAsFieldOrParameterType", "BoundedWildcard"} )
    @API( status = STABLE, since = "0.1.0" )
    public static final String resolveText( final Optional<ResourceBundle> bundle, final String text, final String textKey, final Object... args )
    {
        final var retValue = requireNonNullArgument( bundle, "bundle" ).isPresent() && isNotEmptyOrBlank( textKey )
            ? retrieveText( bundle.get(), textKey, args )
            : requireNotEmptyArgument( text, "text" );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  resolveText()

    /**
     *  <p>{@summary Returns the Text for the given key, or the alternative
     *  text.} This method is primarily used internally by the library, but can
     *  be also useful in other scenarios where the presence of a
     *  {@link ResourceBundle}
     *  is not guaranteed.</p>
     *  <p>If there is a resource bundle,
     *  {@link #retrieveText(ResourceBundle, String, Object...)}
     *  should be used instead.</p>
     *
     *  @note   <code>text</code> is used as is! The arguments will be applied
     *      only to a text that will be retrieved from the resource bundle!
     *
     *  @param  bundle  The resource bundle.
     *  @param  text    The alternative text that is used if there is no
     *      resource bundle, or that bundle does not contain a text for the
     *      given key.
     *  @param  textKey The resource bundle key for the text.
     *  @param  args    The argument for the retrieved text.
     *  @return The resolved text.
     */
    @SuppressWarnings( "OptionalUsedAsFieldOrParameterType" )
    @API( status = STABLE, since = "0.1.0" )
    public static final String resolveText( final Optional<ResourceBundle> bundle, final Optional<String> text, final Optional<String> textKey, final Object... args )
    {
        final var _message = requireNonNullArgument( text, "text" ).orElse( createFallback( "MissingText", args ) );
        final var _messageKey = requireNonNullArgument( textKey, "textKey" ).orElse( "MissingTextKey" );
        final var retValue = resolveText( bundle, _message, _messageKey, args );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  resolveText()

    /**
     *  <p>{@summary Returns the Text for the given {@code enum} value.} This
     *  method is primarily used internally by the library, but can be also
     *  useful in other scenarios where the presence of a
     *  {@link ResourceBundle}
     *  is not guaranteed.</p>
     *  <p>If there is a resource bundle,
     *  {@link #retrieveText(ResourceBundle, Enum)}
     *  should be used instead.</p>
     *
     *  @param  <E> The type of the {@code enum} value.
     *  @param  bundle  The resource bundle.
     *  @param  value   The {@code enum} value.
     *  @return The resolved text.
     */
    @SuppressWarnings( {"OptionalUsedAsFieldOrParameterType", "BoundedWildcard"} )
    @API( status = STABLE, since = "0.1.0" )
    public static final <E extends Enum<?>> String resolveText( final Optional<ResourceBundle> bundle, final E value )
    {
        final var retValue = requireNonNullArgument( bundle, "bundle" ).isPresent()
             ? retrieveText( bundle.get(), value )
             : requireNotEmptyArgument( value, "value" ).name();

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  resolveText()

    /**
     *  <p>{@summary Retrieves the message with the given key from the given
     *  resource bundle and applies the given arguments to it.}</p>
     *  <p>If the resource bundle does not contain a message for the given key,
     *  the key itself will be returned, appended with the arguments.</p>
     *
     *  @param  bundle  The resource bundle.
     *  @param  messagePrefix   The message prefix.
     *  @param  id  The id for the message.
     *  @param  addKey  The recommended value is {@code true}; this means that
     *      the message will be prefixed with the generated message key.
     *  @param  args    The arguments for the message.
     *  @return The text.
     */
    public static final String retrieveMessage( final ResourceBundle bundle, final String messagePrefix, final int id, final boolean addKey, final Object... args )
    {
        final var retValue = retrieveMessage( bundle, composeMessageKey( messagePrefix, id ), addKey, args );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  retrieveMessage()

    /**
     *  <p>{@summary Retrieves the message with the given key from the given
     *  resource bundle and applies the given arguments to it.}</p>
     *  <p>If the resource bundle does not contain a message for the given key,
     *  the key itself will be returned, appended with the arguments.</p>
     *
     *  @param  bundle  The resource bundle.
     *  @param  messagePrefix   The message prefix.
     *  @param  id  The id for the message.
     *  @param  addKey  The recommended value is {@code true}; this means that
     *      the message will be prefixed with the generated message key.
     *  @param  args    The arguments for the message.
     *  @return The text.
     *
     *  @see Objects#toString(Object)
     */
    public static final String retrieveMessage( final ResourceBundle bundle, final String messagePrefix, final String id, final boolean addKey, final Object... args )
    {
        final var retValue = retrieveMessage( bundle, composeMessageKey( messagePrefix, id ), addKey, args );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  retrieveMessage()

    /**
     *  The internal implementation for
     *  {@link #retrieveMessage(ResourceBundle, String, int, boolean, Object...)}
     *  and
     *  {@link #retrieveMessage(ResourceBundle, String, String, boolean, Object...)}.
     *
     *  @param  bundle  The resource bundle.
     *  @param  key The key for the message.
     *  @param  addKey  The recommended value is {@code true}; this means that
     *      the message will be prefixed with the generated message key.
     *  @param  args    The arguments for the message.
     *  @return The text.
     */
    @API( status = INTERNAL, since = "0.1.0", consumers = "retrieveMessage()" )
    private static final String retrieveMessage( final ResourceBundle bundle, final String key, final boolean addKey, final Object... args )
    {
        final var message = retrieveText( bundle, key, args );
        final var retValue = addKey
             ? format( "[%s] %s", key, message )
             : message;

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  retrieveMessage()

    /**
     *  <p>{@summary Retrieves the text with the given key from the given
     *  resource bundle and applies the given arguments to it.}</p>
     *  <p>If the resource bundle does not contain a text for the given key,
     *  the key itself will be returned, appended with the arguments.</p>
     *
     *  @param  bundle  The resource bundle.
     *  @param  key The key for the text.
     *  @param  args    The arguments for the text.
     *  @return The text.
     *
     *  @see Objects#toString(Object)
     */
    public static final String retrieveText( final ResourceBundle bundle, final String key, final Object... args )
    {
        requireNonNullArgument( args, "args" );
        String retValue;
        try
        {
            final var format = requireNonNullArgument( bundle, "bundle" ).getString( requireNotEmptyArgument( key, "key" ) );
            retValue = format( format, args ).translateEscapes();
        }
        catch( @SuppressWarnings( "unused" ) final MissingResourceException e )
        {
            retValue = createFallback( key, args );
        }

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  retrieveText()

    /**
     *  <p>{@summary Retrieves the text for the given {@code enum} value from
     *  the given resource bundle.}</p>
     *  <p>If the resource bundle does not contain a text for the {@code enum},
     *  the text key for it will be returned.</p>
     *
     *  @param  <E> The type of the {@code enum} value.
     *  @param  bundle  The resource bundle.
     *  @param  value   The {@code enum} value.
     *  @return The text.
     */
    public static final <E extends Enum<?>> String retrieveText( final ResourceBundle bundle, final E value )
    {
        final var retValue = retrieveText( bundle, composeTextKey( requireNonNullArgument( value, "value" ) ) );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  retrieveText()
}
//  class I18nUtil

/*
 *  End of File
 */