/**
 * ScormConstants.java.
 * User: fengch  Date: 2004-7-13
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.scorm.util;

import com.ulearning.ulms.core.util.Config;

import java.io.File;


public interface ScormConstants
{
        //aicc
        String SCORMTYPE_AICC = "aicc";

        //scorm1.2
        String SCORMTYPE_SCORM_V12 = "scorm v1.2";

        //scorm2004
        String SCORMTYPE_SCORM_V2004 = "scorm v2004";

        //非法类型
        String SCORMTYPE_NA = "Not Available";

        //sco
        String SCOTYPE_SCO = "sco";

        //asset
        String SCOTYPE_ASSET = "asset";

        //au
        String SCOTYPE_AU = "au";

        //block
        String SCOTYPE_BLOCK = "block";

        //Scorm的相关ID的Type:表示此课件隶属于课程内容
        String SCORM_RELATIONTYPE_COURSECONTENT = "0";

        /*
        *  to sigh the Asset itemType.
        */
        int TYPE_MASTER = 0;

        /*
        *  to sigh the SCO itemType.
        */
        int TYPE_COURSE = 1;

        /*
        *  to sigh the Asset itemType.
        */
        int ITEMTYPE_ASSET = 0;

        /*
        *  to sigh the SCO itemType.
        */
        int ITEMTYPE_SCO = 1;

        /*
        * to sign the lesson status:not attempted
        */
        int LESSONSTATUS_NOTATTEMPTED = 0;

        /*
        * to sign the lesson status:completed
        */
        int LESSONSTATUS_COMPLETED = 1;

        /*
        * to sign the lesson status: passed
        */
        int LESSONSTATUS_PASSED = 0;

        /*
        * to sign the lesson status:failed
        */
        int LESSONSTATUS_FAILED = 0;

        /*
        * return the Scorm Run Time Root Dir
        * e.g:  RTERoot + nodeID
        */
        String RTERoot = Config.getUploadPhysicalPath() + "Scorm" + File.separator +
                "RTERoot" + File.separator;

        /*
        * return the Scorm Import Dir
        * e.g:  RTERoot + nodeID
        */
        String ImportRoot = Config.getUploadPhysicalPath() + "Scorm" +
                File.separator + "ImportRoot" + File.separator;
}
