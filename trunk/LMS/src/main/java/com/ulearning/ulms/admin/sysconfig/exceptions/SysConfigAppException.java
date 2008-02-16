/**
 * SysConfigAppException.java.
 * User: huangsb  Date: 2004-4-27
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.admin.sysconfig.exceptions;

import com.ulearning.ulms.core.exceptions.ULMSAppException;


public class SysConfigAppException extends ULMSAppException
{
        /**
         * Constructor
         *
         * @param str a string that explains what the exception condition is
         */
        public SysConfigAppException(String str)
        {
                super(str);
        }

        /**
         * Default constructor. Takes no arguments
         */
        public SysConfigAppException()
        {
                super();
        }
}
