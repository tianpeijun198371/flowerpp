/**
 * GradeWeightingFactorSysException.java.
 * User: keyh  Date: 2004-9-2
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.courseconfig.exceptions;

import com.ulearning.ulms.core.exceptions.ULMSSysException;


public class GradeWeightingFactorSysException extends ULMSSysException
{
        public GradeWeightingFactorSysException()
        {
        }

        public GradeWeightingFactorSysException(String msg)
        {
                super(msg);
        }

        public GradeWeightingFactorSysException(String msg, Throwable nested)
        {
                super(msg, nested);
        }

        public GradeWeightingFactorSysException(Throwable nested)
        {
                super(nested);
        }
}
