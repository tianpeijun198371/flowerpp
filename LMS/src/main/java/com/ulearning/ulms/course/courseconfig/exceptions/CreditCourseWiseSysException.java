/**
 * CreditCourseWiseSysException.java.
 * User: keyh  Date: 2004-9-2
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.courseconfig.exceptions;

import com.ulearning.ulms.core.exceptions.ULMSSysException;


public class CreditCourseWiseSysException extends ULMSSysException
{
        public CreditCourseWiseSysException()
        {
        }

        public CreditCourseWiseSysException(String msg)
        {
                super(msg);
        }

        public CreditCourseWiseSysException(String msg, Throwable nested)
        {
                super(msg, nested);
        }

        public CreditCourseWiseSysException(Throwable nested)
        {
                super(nested);
        }
}
