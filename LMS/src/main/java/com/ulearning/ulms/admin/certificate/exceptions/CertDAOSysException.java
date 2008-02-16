/**
 * CertDAOSysException.java.
 * User: huangsb  Date: 2004-4-26
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.admin.certificate.exceptions;

import com.ulearning.ulms.core.exceptions.ULMSSysException;


public class CertDAOSysException extends ULMSSysException
{
        /**
         * Constructor
         *
         * @param str a string that explains what the exception condition is
         */
        public CertDAOSysException(String str)
        {
                super(str);
        }

        public CertDAOSysException(String msg, Throwable nested)
        {
                super(msg, nested);
                super.setErrorKey("error.base.sys");
        }

        public CertDAOSysException(Throwable nested)
        {
                super(nested);
                super.setErrorKey("error.base.sys");
        }

        /**
         * Default constructor. Takes no arguments
         */
        public CertDAOSysException()
        {
                super();
        }
}
