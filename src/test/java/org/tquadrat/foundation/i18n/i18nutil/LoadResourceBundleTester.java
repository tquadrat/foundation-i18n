/*
 * ============================================================================
 *  Copyright © 2002-2022 by Thomas Thrien.
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

import static java.lang.System.err;
import static java.lang.System.out;
import static org.apiguardian.api.API.Status.STABLE;
import static org.tquadrat.foundation.i18n.I18nUtil.loadResourceBundle;
import static org.tquadrat.foundation.lang.DebugOutput.ifDebug;
import static org.tquadrat.foundation.lang.DebugOutput.isDebug;
import static org.tquadrat.foundation.lang.DebugOutput.isTest;

import java.util.Optional;
import java.util.ResourceBundle;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.annotation.PlaygroundClass;
import org.tquadrat.foundation.exception.PrivateConstructorForStaticClassCalledError;
import org.tquadrat.foundation.i18n.I18nUtil;

/**
 *  Playing around with
 *  {@link I18nUtil#load}
 *
 *  @version $Id: LoadResourceBundleTester.java 995 2022-01-23 01:09:35Z tquadrat $
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 */
@PlaygroundClass
@ClassVersion( sourceVersion = "$Id: LoadResourceBundleTester.java 995 2022-01-23 01:09:35Z tquadrat $" )
@API( status = STABLE, since = "0.1.0" )
public final class LoadResourceBundleTester
{
        /*--------------*\
    ====** Constructors **=====================================================
        \*--------------*/
    /**
     *  No instance allowed for this class.
     */
    private LoadResourceBundleTester() { throw new PrivateConstructorForStaticClassCalledError( LoadResourceBundleTester.class ); }

        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  The program entry point.
     *
     *  @param  args    The command line arguments.
     */
    @SuppressWarnings( "GrazieInspection" )
    public static final void main( final String... args )
    {
        try
        {
            out.printf( "isDebug = %b%n", isDebug() );
            out.printf( "isTest  = %b%n", isTest() );

            String bundleName;
            Optional<ResourceBundle> bundle;

            bundleName = "This.One.Does.Not.Exist";
            bundle = loadResourceBundle( bundleName );
            ifDebug( bundle::isPresent, "Found Bundle: %s"::formatted, bundleName );

            bundleName = "com.foo.bar.Texts";
            bundle = loadResourceBundle( bundleName );
            ifDebug( bundle::isPresent, "Found Bundle: %s"::formatted, bundleName );
        }
        catch( final Throwable t )
        {
            t.printStackTrace( err );
        }
    }   //  main()
}
//  class LoadResourceBundleTester

/*
 *  End of File
 */