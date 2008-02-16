/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-6-24
 * Time: 13:40:27
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.user.exceptions;

import com.ulearning.ulms.core.exceptions.ULMSSysException;

public class UserSystemException extends ULMSSysException
{
        /**
         * Constructor
         *
         * @param str a string that explains what the exception condition is
         */
        public UserSystemException(String str)
        {
                super(str);
        }

        /**
         * Default constructor. Takes no arguments
         */
        public UserSystemException()
        {
                super();
        }

}
