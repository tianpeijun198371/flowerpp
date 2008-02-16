/**
 * Created by IntelliJ IDEA.
 * DocContent: dengj
 * Date: Apr 8, 2004
 * Time: 9:17:53 AM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.tools.doc.doccontent.exceptions;

import com.ulearning.ulms.core.exceptions.ULMSAppException;


public class DocContentAppException extends ULMSAppException
{
        /**
         * Constructor
         *
         * @param str a string that explains what the exception condition is
         */
        public DocContentAppException(String str)
        {
                super(str);
        }

        /**
         * Default constructor. Takes no arguments
         */
        public DocContentAppException()
        {
                super();
        }
}
