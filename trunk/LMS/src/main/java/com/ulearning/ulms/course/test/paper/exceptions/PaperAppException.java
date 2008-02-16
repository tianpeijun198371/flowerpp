/**
 * PaperAppException.java.
 * User: huangsb  Date: 2004-6-15
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.course.test.paper.exceptions;

import com.ulearning.ulms.core.exceptions.ULMSAppException;


public class PaperAppException extends ULMSAppException
{
        /**
         * Default constructor. Takes no arguments
         */
        public PaperAppException()
        {
                super();
        }

        /**
         * @param str a string that explains what the exception condition is
         */
        public PaperAppException(String str)
        {
                super(str);
        }
}
