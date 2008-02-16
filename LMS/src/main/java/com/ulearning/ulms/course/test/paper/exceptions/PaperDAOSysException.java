/**
 * PaperDAOSysException.java.
 * User: huangsb  Date: 2004-6-15
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.course.test.paper.exceptions;

import com.ulearning.ulms.core.exceptions.ULMSSysException;


public class PaperDAOSysException extends ULMSSysException
{
        /**
         * Default constructor. Takes no arguments
         */
        public PaperDAOSysException()
        {
                super();
        }

        /**
         * @param str a string that explains what the exception condition is
         */
        public PaperDAOSysException(String str)
        {
                super(str);
        }
}
