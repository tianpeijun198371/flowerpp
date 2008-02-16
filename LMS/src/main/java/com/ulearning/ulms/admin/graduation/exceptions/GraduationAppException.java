/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-9-7
 * Time: 13:47:18
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.admin.graduation.exceptions;

import com.ulearning.ulms.core.exceptions.ULMSAppException;


public class GraduationAppException extends ULMSAppException
{
        /**
         * Constructor
         *
         * @param str a string that explains what the exception condition is
         */
        public GraduationAppException(String str)
        {
                super(str);
        }

        /**
         * Default constructor. Takes no arguments
         */
        public GraduationAppException()
        {
                super();
        }
}
