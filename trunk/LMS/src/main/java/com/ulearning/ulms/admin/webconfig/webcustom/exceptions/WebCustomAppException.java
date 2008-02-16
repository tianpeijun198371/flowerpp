/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-7-2
 * Time: 10:06:17
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.admin.webconfig.webcustom.exceptions;

import com.ulearning.ulms.core.exceptions.ULMSAppException;


public class WebCustomAppException extends ULMSAppException
{
        /**
         * Constructor
         *
         * @param str a string that explains what the exception condition is
         */
        public WebCustomAppException(String str)
        {
                super(str);
        }

        /**
         * Default constructor. Takes no arguments
         */
        public WebCustomAppException()
        {
                super();
        }
}
