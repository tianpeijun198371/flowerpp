/**
 * Copyright (c)2006 Beijing WenhuaOnline Sci-tech DevelopmentCo.,Ltd.
 * All rights reserved.
 *
 * User: zhangly
 * Date: 2007-11-5 9:06:18
 */

package com.ulearning.ulms.tools.study.info.exceptions;

import com.ulearning.ulms.core.exceptions.ULMSSysException;

public class StudyInfoSysException extends ULMSSysException
{
         /**
         * Constructor
         *
         * @param str a string that explains what the exception condition is
         */
        public StudyInfoSysException(String str)
        {
                super(str);
        }

        /**
         * Default constructor. Takes no arguments
         */
        public StudyInfoSysException()
        {
                super();
       }
}
