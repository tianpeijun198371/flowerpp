/**
 * CertAppExceprion.java.
 * User: huangsb  Date: 2004-4-26
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.admin.certificate.exceptions;

import com.ulearning.ulms.core.exceptions.ULMSAppException;


public class CertAppExceprion extends ULMSAppException
{
        /**
         * Constructor
         *
         * @param str a string that explains what the exception condition is
         */
        public CertAppExceprion(String str)
        {
                super(str);
        }

        /**
         * Default constructor. Takes no arguments
         */
        public CertAppExceprion()
        {
                super();
        }
}
