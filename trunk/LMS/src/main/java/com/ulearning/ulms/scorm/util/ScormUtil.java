/**
 * Copyright (c)2006 Beijing WenhuaOnline Sci-tech DevelopmentCo.,Ltd.
 * All rights reserved.
 *
 * User: fengch
 * Date: 2007-10-9 14:32:16
 */
package com.ulearning.ulms.scorm.util;

import org.apache.commons.lang.StringUtils;


public class ScormUtil
{
        public static String getLessonStatusStr(String status)
        {
                status = StringUtils.trimToEmpty(status);

                String result = "<font color=blue><b>未学习</b></font>";

                if (status.equalsIgnoreCase(CMIConstants.STATUS_BROWSED))
                {
                        result = "<font color=green><b>已浏览</b></font>";
                }
                else if (status.equalsIgnoreCase(CMIConstants.STATUS_COMPLETED))
                {
                        result = "<font color=green><b>已完成</b></font>";
                }
                else if (status.equalsIgnoreCase(CMIConstants.STATUS_FAILED))
                {
                        result = "<font color=red><b>没通过</b></font>";
                }
                else if (status.equalsIgnoreCase(CMIConstants.STATUS_INCOMPLETE))
                {
                        result = "<font color=blue><b>学习中</b></font>";
                }
                else if (status.equalsIgnoreCase(CMIConstants.STATUS_NOTATTEMPTED))
                {
                        result = "<font color=red><b>未学习</b></font>";
                }
                else if (status.equalsIgnoreCase(CMIConstants.STATUS_PASSED))
                {
                        result = "<font color=green><b>已通过</b></font>";
                }

                return result;
        }
}
