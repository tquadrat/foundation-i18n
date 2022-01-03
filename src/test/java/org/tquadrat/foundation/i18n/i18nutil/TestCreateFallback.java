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
import static org.apiguardian.api.API.Status.STABLE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.tquadrat.foundation.i18n.I18nUtil.createFallback;
import static org.tquadrat.foundation.lang.CommonConstants.EMPTY_Object_ARRAY;
import static org.tquadrat.foundation.lang.CommonConstants.EMPTY_STRING;
import static org.tquadrat.foundation.util.StringUtils.format;

import java.time.Instant;
import java.util.Locale;

import org.apiguardian.api.API;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.exception.EmptyArgumentException;
import org.tquadrat.foundation.exception.NullArgumentException;
import org.tquadrat.foundation.testutil.TestBaseClass;

/**
 *  Some tests for
 *  {@link org.tquadrat.foundation.i18n.I18nUtil#createFallback(String, Object...)}.
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 */
@ClassVersion( sourceVersion = "$Id: TestCreateFallback.java 891 2021-04-02 08:07:35Z tquadrat $" )
@API( status = STABLE, since = "0.1.0" )
@DisplayName( "org.tquadrat.foundation.i18n.i18nutil.TestCreateFallback" )
public class TestCreateFallback extends TestBaseClass
{
        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  Test for
     *  {@link org.tquadrat.foundation.i18n.I18nUtil#createFallback(String, Object...)}.
     *
     *  @throws Exception   Something went wrong unexpectedly.
     */
    @Test
    final void testCreateFallback() throws Exception
    {
        skipThreadTest();

        final var key = "KEY";

        String actual, expected;
        Object [] args;

        expected = format( "[%s] – []", key );
        actual = createFallback( key );
        assertNotNull( actual );
        assertFalse( actual.isEmpty() );
        assertFalse( actual.isBlank() );
        assertEquals( expected, actual );

        args = EMPTY_Object_ARRAY;
        expected = format( "[%s] – []", key );
        actual = createFallback( key, args );
        assertNotNull( actual );
        assertFalse( actual.isEmpty() );
        assertFalse( actual.isBlank() );
        assertEquals( expected, actual );

        args = null;
        expected = format( "[%s] – null", key );
        actual = createFallback( key, args );
        assertNotNull( actual );
        assertFalse( actual.isEmpty() );
        assertFalse( actual.isBlank() );
        assertEquals( expected, actual );

        args = new Object[] {"arg1", "arg2", Instant.ofEpochSecond( 0 ), Locale.CANADA };
        expected = format( "[%s] – [arg1, arg2, 1970-01-01T00:00:00Z, en_CA]", key );
        actual = createFallback( key, args );
        assertNotNull( actual );
        assertFalse( actual.isEmpty() );
        assertFalse( actual.isBlank() );
        assertEquals( expected, actual );
    }   //  testCreateFallback()

    /**
     *  Test for
     *  {@link org.tquadrat.foundation.i18n.I18nUtil#createFallback(String, Object...)}.
     *
     *  @throws Exception   Something went wrong unexpectedly.
     */
    @Test
    final void testCreateFallbackWithEmptyArgument() throws Exception
    {
        skipThreadTest();

        final Class<? extends Throwable> expectedException = EmptyArgumentException.class;
        try
        {
            createFallback( EMPTY_STRING );
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
    }   //  testCreateFallbackWithEmptyArgument()

    /**
     *  Test for
     *  {@link org.tquadrat.foundation.i18n.I18nUtil#createFallback(String, Object...)}.
     *
     *  @throws Exception   Something went wrong unexpectedly.
     */
    @Test
    final void testCreateFallbackWithNullArgument() throws Exception
    {
        skipThreadTest();

        final Class<? extends Throwable> expectedException = NullArgumentException.class;
        try
        {
            createFallback( null );
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
    }   //  testCreateFallbackWithNullArgument(()
}
//  class TestCreateFallback

/*
 *  End of File
 */