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

import static java.lang.String.format;
import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.tquadrat.foundation.i18n.I18nUtil.composeTextKey;
import static org.tquadrat.foundation.i18n.I18nUtil.retrieveText;
import static org.tquadrat.foundation.lang.CommonConstants.EMPTY_STRING;

import java.time.Month;
import java.util.Map;
import java.util.ResourceBundle;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.tquadrat.foundation.exception.EmptyArgumentException;
import org.tquadrat.foundation.exception.NullArgumentException;
import org.tquadrat.foundation.i18n.I18nUtil;
import org.tquadrat.foundation.testutil.TestBaseClass;
import org.tquadrat.foundation.testutil.impl.ResourceBundleImpl;

/**
 *  Some tests for
 *  {@link I18nUtil}.
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 */
@DisplayName( "org.tquadrat.foundation.i18n.TestI18nUtil" )
public class TestRetrieveText extends TestBaseClass
{
        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  Creates an instance of
     *  {@link ResourceBundle}
     *  that does not have any contents.
     *
     *  @return The bundle instance.
     */
    private static final ResourceBundle createResourceBundle()
    {
        final var retValue = createResourceBundle( null );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  createResourceBundle()

    /**
     *  Creates an instance of
     *  {@link ResourceBundle}.
     *
     *  @param  texts   The texts; can be {@code null}.
     *
     *  @return The bundle instance.
     */
    private static final ResourceBundle createResourceBundle( final Map<String,Object> texts )
    {
        final ResourceBundle retValue = new ResourceBundleImpl( texts );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  createResourceBundle()

    /**
     *  Tests for
     *  {@link I18nUtil#retrieveText(ResourceBundle, String, Object...)}.
     *
     *  @throws Exception   Something unexpected went wrong.
     */
    @Test
    final void testRetrieveText1() throws Exception
    {
        skipThreadTest();

        ResourceBundle bundle;
        String key;
        Object [] args;

        String actual, expected;

        bundle = createResourceBundle();
        key = "key";
        args = new Object [] { "key" };

        expected = "[key] – [key]";
        actual = retrieveText( bundle, key, args );
        assertEquals( expected, actual );

        bundle = createResourceBundle( Map.of( "key", "Key '%s'" ) );
        key = "key";
        args = new Object [] { "key" };

        expected = "Key 'key'";
        actual = retrieveText( bundle, key, args );
        assertEquals( expected, actual );
    }   //  testRetrieveText1()

    /**
     *  Tests for
     *  {@link I18nUtil#retrieveText(ResourceBundle, String, Object...)}.
     *
     *  @throws Exception   Something unexpected went wrong.
     */
    @Test
    final void testRetrieveText1WithEmptyArgument() throws Exception
    {
        skipThreadTest();


        final Class<? extends Throwable> expectedException = EmptyArgumentException.class;

        final var bundle = createResourceBundle();
        final var key = EMPTY_STRING;
        final var args = new Object [] { "key" };

        try
        {
            retrieveText( bundle, key, args );
            fail( () -> format( MSG_ExceptionNotThrown, expectedException.getName() ) );
        }
        catch( final AssertionError e ) { throw e; }
        catch( final Throwable t )
        {
            final var isExpectedException = expectedException.isInstance( t );
            if( !isExpectedException )
            {
                t.printStackTrace( out );
            }
            assertTrue( isExpectedException, () -> format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
        }
    }   //  testRetrieveText1WithEmptyArgument()

    /**
     *  Tests for
     *  {@link I18nUtil#retrieveText(ResourceBundle, String, Object...)}.
     *
     *  @throws Exception   Something unexpected went wrong.
     */
    @Test
    final void testRetrieveText1WithNullArgument() throws Exception
    {
        skipThreadTest();

        ResourceBundle bundle;
        String key;
        Object [] args;

        final Class<? extends Throwable> expectedException = NullArgumentException.class;

        bundle = null;
        key = "key";
        args = new Object [] { "key" };

        try
        {
            retrieveText( bundle, key, args );
            fail( () -> format( MSG_ExceptionNotThrown, expectedException.getName() ) );
        }
        catch( final AssertionError e ) { throw e; }
        catch( final Throwable t )
        {
            final var isExpectedException = expectedException.isInstance( t );
            if( !isExpectedException )
            {
                t.printStackTrace( out );
            }
            assertTrue( isExpectedException, () -> format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
        }

        bundle = createResourceBundle();
        key = null;
        args = new Object [] { "key" };

        try
        {
            retrieveText( bundle, key, args );
            fail( () -> format( MSG_ExceptionNotThrown, expectedException.getName() ) );
        }
        catch( final AssertionError e ) { throw e; }
        catch( final Throwable t )
        {
            final var isExpectedException = expectedException.isInstance( t );
            if( !isExpectedException )
            {
                t.printStackTrace( out );
            }
            assertTrue( isExpectedException, () -> format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
        }

        bundle = createResourceBundle();
        key = "key";
        args = null;

        try
        {
            retrieveText( bundle, key, args );
            fail( () -> format( MSG_ExceptionNotThrown, expectedException.getName() ) );
        }
        catch( final AssertionError e ) { throw e; }
        catch( final Throwable t )
        {
            final var isExpectedException = expectedException.isInstance( t );
            if( !isExpectedException )
            {
                t.printStackTrace( out );
            }
            assertTrue( isExpectedException, () -> format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
        }
    }   //  testRetrieveText1WithNullArgument()

    /**
     *  Tests for
     *  {@link I18nUtil#retrieveText(ResourceBundle,Enum)}
     *
     *  @throws Exception   Something unexpected went wrong.
     */
    @Test
    final void testRetrieveText2() throws Exception
    {
        skipThreadTest();

        ResourceBundle bundle;
        Month value;

        String actual, expected;

        bundle = createResourceBundle();
        value = Month.JUNE;

        expected = "[%s] – []".formatted( composeTextKey( value ) );
        actual = retrieveText( bundle, value );
        assertEquals( expected, actual );

        value = Month.JUNE;
        expected = "Monat 'Juni'";
        bundle = createResourceBundle( Map.of( composeTextKey( value ), expected ) );

        actual = retrieveText( bundle, value );
        assertEquals( expected, actual );
    }   //  testRetrieveText2()

    /**
     *  Tests for
     *  {@link I18nUtil#retrieveText(ResourceBundle,Enum)}
     *
     *  @throws Exception   Something unexpected went wrong.
     */
    @Test
    final void testRetrieveText2WithNullArgument() throws Exception
    {
        skipThreadTest();

        ResourceBundle bundle;
        Month value;

        final Class<? extends Throwable> expectedException = NullArgumentException.class;

        bundle = null;
        value = Month.JUNE;

        try
        {
            retrieveText( bundle, value );
            fail( () -> format( MSG_ExceptionNotThrown, expectedException.getName() ) );
        }
        catch( final AssertionError e ) { throw e; }
        catch( final Throwable t )
        {
            final var isExpectedException = expectedException.isInstance( t );
            if( !isExpectedException )
            {
                t.printStackTrace( out );
            }
            assertTrue( isExpectedException, () -> format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
        }

        bundle = createResourceBundle();
        value = null;

        try
        {
            retrieveText( bundle, value );
            fail( () -> format( MSG_ExceptionNotThrown, expectedException.getName() ) );
        }
        catch( final AssertionError e ) { throw e; }
        catch( final Throwable t )
        {
            final var isExpectedException = expectedException.isInstance( t );
            if( !isExpectedException )
            {
                t.printStackTrace( out );
            }
            assertTrue( isExpectedException, () -> format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
        }
    }   //  testRetrieveText2WithNullArgument()
}
//  class TestI18nUtil

/*
 *  End of File
 */