/*******************************************************************************
 **
 ** Filename:  RTEFileHandler.java
 **
 ** File Description:
 **
 ** Author:  ADLI Project
 **
 ** Company Name: CTC
 **
 ** Module/Package Name: com.ulearning.ulms.scorm.tsuite.packaging
 ** Module/Package Description:
 **
 ** Design Issues:
 **
 ** Implementation Issues:
 ** Known Problems:
 ** Side Effects:
 **
 ** References:  SCORM
 **
 *******************************************************************************
 **
 ** Concurrent Technologies Corporation (CTC) grants you ("Licensee") a non-
 ** exclusive, royalty free, license to use, modify and redistribute this
 ** software in source and binary code form, provided that i) this copyright
 ** notice and license appear on all copies of the software; and ii) Licensee
 ** does not utilize the software in a manner which is disparaging to CTC.
 **
 ** This software is provided "AS IS," without a warranty of any kind.  ALL
 ** EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES, INCLUDING ANY
 ** IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE OR NON-
 ** INFRINGEMENT, ARE HEREBY EXCLUDED.  CTC AND ITS LICENSORS SHALL NOT BE LIABLE
 ** FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR
 ** DISTRIBUTING THE SOFTWARE OR ITS DERIVATIVES.  IN NO EVENT WILL CTC  OR ITS
 ** LICENSORS BE LIABLE FOR ANY LOST REVENUE, PROFIT OR DATA, OR FOR DIRECT,
 ** INDIRECT, SPECIAL, CONSEQUENTIAL, INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER
 ** CAUSED AND REGARDLESS OF THE THEORY OF LIABILITY, ARISING OUT OF THE USE OF
 ** OR INABILITY TO USE SOFTWARE, EVEN IF CTC HAS BEEN ADVISED OF THE POSSIBILITY
 ** OF SUCH DAMAGES.
 **
 *******************************************************************************
 **
 ** Date Changed   Author of Change  Reason for Changes
 ** ------------   ----------------  -------------------------------------------
 **
 *******************************************************************************/
package com.ulearning.ulms.scorm.handler;

import com.ulearning.ulms.core.util.Config;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.course.model.CourseContentForm;
import com.ulearning.ulms.scorm.adl.parsers.dom.ADLOrganizations;
import com.ulearning.ulms.scorm.dao.ScormDAO;
import com.ulearning.ulms.scorm.dao.ScormDAOFactory;
import com.ulearning.ulms.scorm.datamodels.*;
import com.ulearning.ulms.scorm.datamodels.cmi.*;
import com.ulearning.ulms.scorm.helper.ScormHelper;
import com.ulearning.ulms.scorm.util.ScormConstants;
import com.ulearning.ulms.user.bean.UserHelper;

//ADL imports
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;

//Native java imports
import java.lang.*;

import java.sql.*;

import java.util.*;


public class RTEFileHandler
{
        protected static Log logger = LogFactory.getLog(RTEFileHandler.class);
        private int userID;
        private int relationID;
        private int type;
        private int nodeID;
        private String RTERoot;
        private ScormDAO scormDAO;
        private String scoID;
        private String location;
        private String masteryScore;
        private String parameterString;
        private String lessonStatus;
        private String prerequisites;
        private String exit;
        private String entry;
        private String dataFromLMS;
        private String maxTimeAllowed;
        private String timeLimitAction;
        private int sequence;

        /**
         * ***********************************************************************
         * *
         * * Method:  CSFParser()
         * * Input:
         * *
         * * Output:
         * *
         * * Description:  Constructor.
         * *
         * *************************************************************************
         */
        public RTEFileHandler(int relationID, int type, int nodeID, int userID)
        {
                this.userID = userID;
                this.relationID = relationID;
                scoID = new String();
                this.type = type;
                this.nodeID = nodeID;
                location = new String();
                masteryScore = new String();
                parameterString = new String();
                lessonStatus = new String();
                prerequisites = new String();
                exit = new String();
                entry = new String();
                dataFromLMS = new String();
                maxTimeAllowed = new String();
                timeLimitAction = new String();
                sequence = 0;
                RTERoot = ScormConstants.RTERoot + nodeID + File.separator + userID;
                getDAO();
        }

        public void printRTEFileHandler()
        {
                logger.info("printRTEFileHandler userID:          " + userID);
                logger.info("printRTEFileHandler relationID:        " + relationID);
                logger.info("printRTEFileHandler type:        " + type);
                logger.info("printRTEFileHandler SampleRTERoot:   " + RTERoot);
        }

        public void initializeCourseFiles()
        {
                try
                {
                        logger.info(
                                "[RTEFileHandler]initializeCourseFiles----------init the usersco.");

                        File theRTESCODataDir = new File(RTERoot);

                        // The course directory should not exist yet
                        if (!theRTESCODataDir.isDirectory())
                        {
                                theRTESCODataDir.mkdirs();
                        }
                        else
                        {
                                logger.info(
                                        "In RTEFileHandler user directory already exists for new course???");
                        }

                        // Now determine sco items for the course and create a data file for each
                        ScormHelper.initUserSCO(userID, nodeID);
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                }
        }

        public void deleteCourseFiles()
        {
                try
                {
                        File theRTESCODataDir = new File(RTERoot);

                        File[] scoFiles = theRTESCODataDir.listFiles();

                        for (int i = 0; i < scoFiles.length; i++)
                        {
                                scoFiles[i].delete();
                        }

                        theRTESCODataDir.delete();
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                }
        }

        protected void getDAO()
        {
                if (scormDAO != null)
                {
                        return;
                }

                try
                {
                        scormDAO = ScormDAOFactory.getDAO();
                }
                catch (Exception ex)
                {
                        ex.printStackTrace();
                }
        }
}
