/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-7-2
 * Time: 10:05:18
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.admin.webconfig.webconfigitem.exceptions;

import com.ulearning.ulms.core.exceptions.ULMSAppException;


public class WebConfigItemAppException extends ULMSAppException
{
        /**
         * Constructor
         *
         * @param str a string that explains what the exception condition is
         */
        public WebConfigItemAppException(String str)
        {
                super(str);
        }

        /**
         * Default constructor. Takes no arguments
         */
        public WebConfigItemAppException()
        {
                super();
        }
}
