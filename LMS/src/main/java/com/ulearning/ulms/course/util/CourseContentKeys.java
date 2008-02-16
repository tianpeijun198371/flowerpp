/**
 * CourseContentKeys.java.
 * User: fengch  Date: 2004-7-30
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.util;

public interface CourseContentKeys
{
        /*
        *  to sigh the MASTER's Content Type.
        */
        int TYPE_MASTER = 0;

        /*
        *  to sigh the COURSE's Content Type.
        */
        int TYPE_COURSE = 1;

        /*
        *  to sighthe PROGRAM's Content Type.
        */
        int TYPE_PROGRAM = 2;

        //NodeType==0: HyperLink
        int HYPERLINK_NODETYPE = 0;

        //NodeType==1: File
        int FILE_NODETYPE = 1;

        //NodeType==2: Scorm
        int SCORM_NODETYPE = 2;

        //NodeType==3: AICC
        int AICC_NODETYPE = 3;

        //NodeType==4: STREAMMEDIA
        int STREAMMEDIA_NODETYPE = 4;

        //NodeType==5: 新理念项目 试听视频或者讲义
        int SHITING_NODETYPE = 5;
}
