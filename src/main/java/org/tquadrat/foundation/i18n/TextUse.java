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

import static org.apiguardian.api.API.Status.STABLE;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;

/**
 *  The uses for a text. It is used to compose the resource bundle key for
 *  text.
 *
 *  @see org.tquadrat.foundation.i18n.I18nUtil#composeTextKey(Class,TextUse,String)
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: TextUse.java 882 2021-02-27 19:01:25Z tquadrat $
 *  @since 0.1.0
 *
 *  @UMLGraph.link
 */
@ClassVersion( sourceVersion = "$Id: TextUse.java 882 2021-02-27 19:01:25Z tquadrat $" )
@API( status = STABLE, since = "0.1.0" )
public enum TextUse
{
        /*------------------*\
    ====** Enum Declaration **=================================================
        \*------------------*/
    /**
     *  The text for a button.
     */
    BUTTON,

    /**
     *  A caption for a GUI form.
     */
    CAPTION,

    /**
     *  A help text.
     */
    HELP,

    /**
     *  The name of a menu entry.
     */
    MENU,

    /**
     *  The localised name of a property.
     */
    NAME,

    /**
     *  A message, although these are usually <i>messages</i>, defined with
     *  {@link Message &#64;Message},
     *  and not <i>texts</i> that will be defined with
     *  {@link Text &#64;Text}.
     */
    MESSAGE,

    /**
     *  A prompt text.
     */
    PROMPT,

    /**
     *  The String representation for an {@code enum} value.
     */
    STRING,

    /**
     *  A title text (a window title, or a program title, or alike).
     */
    TITLE,

    /**
     *  A tooltip text.
     */
    TOOLTIP,

    /**
     *  A general purpose text.
     */
    TXT,

    /**
     *  The text is a usage message for an argument or an option on the command
     *  line.
     */
    USAGE,

    /**
     *  The default; should not be used.
     */
    TEXTUSE_DEFAULT
}
//  enum TextUse

/*
 *  End of File
 */