/**
 * Copyright (c)2006 Beijing WenhuaOnline Sci-tech DevelopmentCo.,Ltd.
 * All rights reserved.
 *
 * User: fengch
 * Date: 2007-9-27 15:51:04
 */
package com.ulearning.ulms.scorm.util;

import com.ulearning.ulms.course.util.CourseContentKeys;


public class ScormType2CourseContentTypeUtil
{
        public static String convertToScormType(int courseContentType)
        {
                if (courseContentType == CourseContentKeys.AICC_NODETYPE)
                {
                        return ScormConstants.SCORMTYPE_AICC;
                }
                else if (courseContentType == CourseContentKeys.SCORM_NODETYPE)
                {
                        //todo:scorm2004
                        return ScormConstants.SCORMTYPE_SCORM_V12;
                }
                else
                {
                        return ScormConstants.SCORMTYPE_NA;
                }
        }

        public static int convertToCourseContentType(String scormType)
        {
                if (scormType.equals(ScormConstants.SCORMTYPE_AICC))
                {
                        return CourseContentKeys.AICC_NODETYPE;
                }
                else if (scormType.equals(ScormConstants.SCORMTYPE_SCORM_V12) ||
                        scormType.equals(ScormConstants.SCORMTYPE_SCORM_V2004))
                {
                        //todo:scorm2004
                        return CourseContentKeys.SCORM_NODETYPE;
                }
                else
                {
                        return CourseContentKeys.FILE_NODETYPE;
                }
        }
}
