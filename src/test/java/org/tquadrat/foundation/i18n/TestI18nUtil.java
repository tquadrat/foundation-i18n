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

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.tquadrat.foundation.i18n.I18nUtil.composeTextKey;
import static org.tquadrat.foundation.i18n.I18nUtil.loadResourceBundle;
import static org.tquadrat.foundation.i18n.TextUse.BUTTON;
import static org.tquadrat.foundation.i18n.TextUse.STRING;
import static org.tquadrat.foundation.i18n.TextUse.TXT;
import static org.tquadrat.foundation.lang.CommonConstants.EMPTY_STRING;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.exception.EmptyArgumentException;
import org.tquadrat.foundation.exception.NullArgumentException;
import org.tquadrat.foundation.testutil.TestBaseClass;

/**
 *  Some tests for
 *  {@link I18nUtil}.
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 */
@ClassVersion( sourceVersion = "$Id: TestI18nUtil.java 1076 2023-10-03 18:36:07Z tquadrat $" )
@DisplayName( "org.tquadrat.foundation.i18n.TestI18nUtil" )
public class TestI18nUtil extends TestBaseClass
{
        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  Tests for
     *  {@link I18nUtil#composeTextKey(Class,TextUse,String)}.
     *
     *  @throws Exception   Something unexpected went wrong.
     */
    @Test
    final void testComposeTextKey1() throws Exception
    {
        skipThreadTest();

        final Class<?> sourceClass = getClass();
        final var use = TXT;
        final var id = "id";
        final var expected = format( "%s.%s_%s", sourceClass.getName(), use, id );
        final var actual = composeTextKey( sourceClass, use, id );
        assertEquals( expected, actual );
    }   //  testComposeTextKey1()

    /**
     *  Tests for
     *  {@link I18nUtil#composeTextKey(String,TextUse,String)}.
     *
     *  @throws Exception   Something unexpected went wrong.
     */
    @Test
    final void testComposeTextKey2() throws Exception
    {
        skipThreadTest();

        final var sourceClass = getClass().getName();
        final var use = TXT;
        final var id = "id";
        final var expected = format( "%s.%s_%s", sourceClass, use, id );
        final var actual = composeTextKey( sourceClass, use, id );
        assertEquals( expected, actual );
    }   //  testComposeTextKey2()

    /**
     *  Tests for
     *  {@link I18nUtil#composeTextKey(Enum)}.
     *
     *  @throws Exception   Something unexpected went wrong.
     */
    @Test
    final void testComposeTextKey3() throws Exception
    {
        skipThreadTest();

        final var value = BUTTON;
        final var sourceClass = value.getDeclaringClass().getName();
        final var use = STRING;
        final var id = value.name();
        final var expected = format( "%s.%s_%s", sourceClass, use, id );
        final var actual = composeTextKey( value );
        assertEquals( expected, actual );
    }   //  testComposeTextKey3()

    /**
     *  Tests for
     *  {@link I18nUtil#loadResourceBundle(String)} .
     *
     *  @throws Exception   Something unexpected went wrong.
     */
    @Test
    final void testLoadResourceBundle() throws Exception
    {
        skipThreadTest();

        final String bundleName;

        assertThrows( NullArgumentException.class, () -> loadResourceBundle( null ) );
        assertThrows( EmptyArgumentException.class, () -> loadResourceBundle( EMPTY_STRING ) );

        bundleName = "This.One.Does.Not.Exist";
        assertTrue( loadResourceBundle( bundleName ).isEmpty() );
    }   //  testLoadResourceBundle()

    /**
     *  Validates whether the class is static.
     */
    @Test
    final void validateClass()
    {
        assertTrue( validateAsStaticClass( I18nUtil.class ) );
    }   //  validateClass()
}
//  class TestI18nUtil

/*
 *  End of File
 */