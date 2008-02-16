/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-8-20
 * Time: 10:11:14
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.admin.colsignup.colsigndetail.exceptions;

import com.ulearning.ulms.core.exceptions.ULMSSysException;


public class ColSignDetailDAOSysException extends ULMSSysException
{
        /**
         * Constructor
         *
         * @param str a string that explains what the exception condition is
         */
        public ColSignDetailDAOSysException(String str)
        {
                super(str);
        }

        /**
         * Default constructor. Takes no arguments
         */
        public ColSignDetailDAOSysException()
        {
                super();
        }
}
