/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-7-2
 * Time: 9:59:30
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.admin.webconfig.webcustomconfigitem.exceptions;

import com.ulearning.ulms.core.exceptions.ULMSAppException;
import com.ulearning.ulms.core.exceptions.ULMSAppException;


public class CustomConfigItemAppException extends ULMSAppException
{
        /**
         * Constructor
         *
         * @param str a string that explains what the exception condition is
         */
        public CustomConfigItemAppException(String str)
        {
                super(str);
        }

        /**
         * Default constructor. Takes no arguments
         */
        public CustomConfigItemAppException()
        {
                super();
        }
}
