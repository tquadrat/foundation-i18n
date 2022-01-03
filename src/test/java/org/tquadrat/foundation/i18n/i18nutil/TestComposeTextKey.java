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

package org.tquadrat.foundation.i18n.i18nutil;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.tquadrat.foundation.i18n.I18nUtil.composeTextKey;
import static org.tquadrat.foundation.lang.CommonConstants.EMPTY_STRING;
import static org.tquadrat.foundation.util.StringUtils.format;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.exception.EmptyArgumentException;
import org.tquadrat.foundation.exception.NullArgumentException;
import org.tquadrat.foundation.i18n.I18nUtil;
import org.tquadrat.foundation.i18n.TextUse;
import org.tquadrat.foundation.testutil.TestBaseClass;

/**
 *  Tests for
 *  {@link I18nUtil#composeTextKey(Class, TextUse, String)},
 *  {@link I18nUtil#composeTextKey(String, TextUse, String)}
 *  and
 *  {@link I18nUtil#composeTextKey(Enum)}.
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 */
@ClassVersion( sourceVersion = "$Id: TestComposeTextKey.java 888 2021-03-29 21:59:02Z tquadrat $" )
@DisplayName( "org.tquadrat.foundation.i18n.i18nutil.TestComposeTextKey" )
public class TestComposeTextKey extends TestBaseClass
{
        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  Tests for
     *  {@link I18nUtil#composeTextKey(Class, TextUse, String)}
     *  {@link I18nUtil#composeTextKey(String, TextUse, String)}
     *  and
     *  {@link I18nUtil#composeTextKey(Enum)}.
     *
     *  @throws Exception   Something went wrong unexpectedly.
     */
    @Test
    final void testComposeTextKey() throws Exception
    {
        skipThreadTest();

        final Class<?> sourceClass;
        final String sourceClassName;
        final TextUse use;
        final String id;

        String actual, expected;

        sourceClass = I18nUtil.class;
        sourceClassName = sourceClass.getName();
        use = TextUse.TXT;
        id = "id";

        expected = format( "%s.%s_%s", sourceClassName, use.name(), id );
        actual = composeTextKey( sourceClass, use, id );
        assertEquals( expected, actual );
        actual = composeTextKey( sourceClassName, use, id );
        assertEquals( expected, actual );

        expected = format( "%s.%s_%s", use.getClass().getName(), TextUse.STRING.name(), use.name() );
        actual = composeTextKey( use );
        assertEquals( expected, actual );
    }   //  testComposeTextKey()

    /**
     *  Tests for
     *  {@link I18nUtil#composeTextKey(Class, TextUse, String)}
     *  and
     *  {@link I18nUtil#composeTextKey(String, TextUse, String)}
     *
     *  @throws Exception   Something went wrong unexpectedly.
     */
    @Test
    final void testComposeTextKeyWithEmptyArgument() throws Exception
    {
        skipThreadTest();

        final Class<? extends Throwable> expectedException = EmptyArgumentException.class;

        final Class<?> sourceClass;
        String sourceClassName;
        final var use = TextUse.STRING;
        String id;

        sourceClass = I18nUtil.class;
        sourceClassName = sourceClass.getName();
        id = EMPTY_STRING;

        try
        {
            final var key = composeTextKey( sourceClass, use, id );
            assertNotNull( key );
            fail( () -> format( MSG_ExceptionNotThrown, expectedException.getName() ) );
        }
        catch( final AssertionError e )
        {
            throw e;
        }
        catch( final Throwable t )
        {
            final var isExpectedException = expectedException.isInstance( t );
            if( !isExpectedException )
            { t.printStackTrace( out ); }
            assertTrue( isExpectedException, () -> format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
        }
        try
        {
            final var key = composeTextKey( sourceClassName, use, id );
            assertNotNull( key );
            fail( () -> format( MSG_ExceptionNotThrown, expectedException.getName() ) );
        }
        catch( final AssertionError e )
        {
            throw e;
        }
        catch( final Throwable t )
        {
            final var isExpectedException = expectedException.isInstance( t );
            if( !isExpectedException )
            { t.printStackTrace( out ); }
            assertTrue( isExpectedException, () -> format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
        }

        sourceClassName = EMPTY_STRING;
        id = "id";

        try
        {
            final var key = composeTextKey( sourceClassName, use, id );
            assertNotNull( key );
            fail( () -> format( MSG_ExceptionNotThrown, expectedException.getName() ) );
        }
        catch( final AssertionError e )
        {
            throw e;
        }
        catch( final Throwable t )
        {
            final var isExpectedException = expectedException.isInstance( t );
            if( !isExpectedException )
            { t.printStackTrace( out ); }
            assertTrue( isExpectedException, () -> format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
        }
    }   //  testComposeTextKeyWithEmptyArgument()

    /**
     *  Tests for
     *  {@link I18nUtil#composeTextKey(Class, TextUse, String)}
     *  {@link I18nUtil#composeTextKey(String, TextUse, String)}
     *  and
     *  {@link I18nUtil#composeTextKey(Enum)}.
     *
     *  @throws Exception   Something went wrong unexpectedly.
     */
    @Test
    final void testComposeTextKeyWithNullArgument() throws Exception
    {
        skipThreadTest();

        final Class<? extends Throwable> expectedException = NullArgumentException.class;

        Class<?> sourceClass;
        String sourceClassName;
        TextUse use;
        String id;

        sourceClass = I18nUtil.class;
        sourceClassName = sourceClass.getName();
        use = TextUse.STRING;
        id = null;

        try
        {
            final var key = composeTextKey( sourceClass, use, id );
            assertNotNull( key );
            fail( () -> format( MSG_ExceptionNotThrown, expectedException.getName() ) );
        }
        catch( final AssertionError e )
        {
            throw e;
        }
        catch( final Throwable t )
        {
            final var isExpectedException = expectedException.isInstance( t );
            if( !isExpectedException )
            { t.printStackTrace( out ); }
            assertTrue( isExpectedException, () -> format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
        }
        try
        {
            final var key = composeTextKey( sourceClassName, use, id );
            assertNotNull( key );
            fail( () -> format( MSG_ExceptionNotThrown, expectedException.getName() ) );
        }
        catch( final AssertionError e )
        {
            throw e;
        }
        catch( final Throwable t )
        {
            final var isExpectedException = expectedException.isInstance( t );
            if( !isExpectedException )
            { t.printStackTrace( out ); }
            assertTrue( isExpectedException, () -> format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
        }

        sourceClass = null;
        sourceClassName = null;
        id = "id";

        try
        {
            final var key = composeTextKey( sourceClass, use, id );
            assertNotNull( key );
            fail( () -> format( MSG_ExceptionNotThrown, expectedException.getName() ) );
        }
        catch( final AssertionError e )
        {
            throw e;
        }
        catch( final Throwable t )
        {
            final var isExpectedException = expectedException.isInstance( t );
            if( !isExpectedException )
            { t.printStackTrace( out ); }
            assertTrue( isExpectedException, () -> format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
        }
        try
        {
            final var key = composeTextKey( sourceClassName, use, id );
            assertNotNull( key );
            fail( () -> format( MSG_ExceptionNotThrown, expectedException.getName() ) );
        }
        catch( final AssertionError e )
        {
            throw e;
        }
        catch( final Throwable t )
        {
            final var isExpectedException = expectedException.isInstance( t );
            if( !isExpectedException )
            { t.printStackTrace( out ); }
            assertTrue( isExpectedException, () -> format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
        }

        sourceClass = I18nUtil.class;
        sourceClassName = sourceClass.getName();
        use = null;
        id = "id";

        try
        {
            final var key = composeTextKey( sourceClass, use, id );
            assertNotNull( key );
            fail( () -> format( MSG_ExceptionNotThrown, expectedException.getName() ) );
        }
        catch( final AssertionError e )
        {
            throw e;
        }
        catch( final Throwable t )
        {
            final var isExpectedException = expectedException.isInstance( t );
            if( !isExpectedException )
            { t.printStackTrace( out ); }
            assertTrue( isExpectedException, () -> format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
        }
        try
        {
            final var key = composeTextKey( sourceClassName, use, id );
            assertNotNull( key );
            fail( () -> format( MSG_ExceptionNotThrown, expectedException.getName() ) );
        }
        catch( final AssertionError e )
        {
            throw e;
        }
        catch( final Throwable t )
        {
            final var isExpectedException = expectedException.isInstance( t );
            if( !isExpectedException )
            { t.printStackTrace( out ); }
            assertTrue( isExpectedException, () -> format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
        }
        try
        {
            final var key = composeTextKey( use );
            assertNotNull( key );
            fail( () -> format( MSG_ExceptionNotThrown, expectedException.getName() ) );
        }
        catch( final AssertionError e )
        {
            throw e;
        }
        catch( final Throwable t )
        {
            final var isExpectedException = expectedException.isInstance( t );
            if( !isExpectedException )
            { t.printStackTrace( out ); }
            assertTrue( isExpectedException, () -> format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
        }
    }   //  testComposeTextKeyWithNullArgument()
}
//  class TestComposeTextKey

/*
 *  End of File
 */