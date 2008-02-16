/**
 * Copyright (c)2006 Beijing WenhuaOnline Sci-tech DevelopmentCo.,Ltd.
 * All rights reserved.
 *
 * User: zhangly
 * Date: 2007-10-23 18:39:24
 */

package com.ulearning.ulms.tools.study.info.exceptions;

import com.ulearning.ulms.core.exceptions.ULMSAppException;

public class StudyInfoAppException extends ULMSAppException
{
        /**
         * Constructor
         *
         * @param str a string that explains what the exception condition is
         */
        public StudyInfoAppException(String str)
        {
                super(str);
        }

        /**
         * Default constructor. Takes no arguments
         */
        public StudyInfoAppException()
        {
                super();
        }
}
