/**
 * CreditCourseWiseAppException.java.
 * User: keyh  Date: 2004-9-2
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.courseconfig.exceptions;

import com.ulearning.ulms.core.exceptions.ULMSAppException;


public class CreditCourseWiseAppException extends ULMSAppException
{
        public CreditCourseWiseAppException()
        {
        }

        public CreditCourseWiseAppException(String msg)
        {
                super(msg);
        }

        public CreditCourseWiseAppException(String msg, Throwable nested)
        {
                super(msg, nested);
        }

        public CreditCourseWiseAppException(Throwable nested)
        {
                super(nested);
        }
}
