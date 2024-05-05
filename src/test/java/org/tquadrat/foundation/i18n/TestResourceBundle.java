/*
 * ============================================================================
 *  Copyright © 2002-2024 by Thomas Thrien.
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ResourceBundle;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.tquadrat.foundation.testutil.TestBaseClass;

/**
 *  Some basic tests for resource bundles.
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 */
@DisplayName( "org.tquadrat.foundation.i18n.TestResourceBundle" )
public class TestResourceBundle extends TestBaseClass
{
        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  Tests loading a resource string with a line break.
     *
     *  @throws Exception   Something went wrong unexpectedly.
     */
    @Test
    final void loadTwoLines() throws Exception
    {
        skipThreadTest();

        final var baseName = "com.foo.bar.Texts";
        final var key = "newline";
        final var expected = """
            first line
            second line""";

        final var resourceBundle = ResourceBundle.getBundle( baseName );
        assertNotNull( resourceBundle );

        assertTrue( resourceBundle.containsKey( key ) );
        final var current = resourceBundle.getString( key );
        assertNotNull( current );
        assertEquals( expected, current );
    }   //  loadTwoLines()
}
//  class TestResourceBundle

/*
 *  End of File
 */