/*******************************************************************************
 **
 ** Filename:  LMSCMIServlet.java
 **
 ** File Description:
 **
 ** This class defines the LMSCMIServlet that is used to handle the server side
 ** data model communication of the Sample RTE.
 **
 ** This servlet handles persistence of the AICC Data Model elements.
 ** Persistence is being handled via flat files and the
 ** built in Java serialization mechanism rather than via a database.
 **
 ** This servlet works in conjunction with the LMS APIAdapter Applet in the
 ** com.ulearning.ulms.scorm.lms.client package that is part of this sample.
 **
 ** Author: ADL Technical Team
 **
 ** Contract Number:
 ** Company Name: CTC
 **
 ** Module/Package Name:
 ** Module/Package Description:
 **
 ** Design Issues:
 **
 ** Implementation Issues:
 ** Known Problems:
 ** Side Effects:
 **
 ** References: ADL SCORM
 **
 /*******************************************************************************
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
 *******************************************************************************/
package com.ulearning.ulms.scorm.servlet;

import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.scorm.datamodels.SCODataManager;
import com.ulearning.ulms.scorm.datamodels.cmi.CMICore;
import com.ulearning.ulms.scorm.datamodels.cmi.CMITime;
import com.ulearning.ulms.scorm.helper.ScormHelper;
import com.ulearning.ulms.scorm.model.UserSCOModel;
import com.ulearning.ulms.scorm.util.ScormConstants;
import com.ulearning.ulms.util.LMSConstants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.Enumeration;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.lang.StringUtils;


public class LMSCMIServlet extends HttpServlet
{
        protected static Log logger = LogFactory.getLog(LMSCMIServlet.class);
        // These strings are being used to hold the location of the serialized core
        // data.
        private String scoFile;
        private String userID;
        private String nodeID;
        private String scoID;
        private boolean logoutFlag;
        private SCODataManager scoData;

        //This controls display of log messages to the java console

        /**
         * *************************************************************************
         * *
         * * Method:  doPost
         * * Input:   HttpServletRequest request, HttpServletResponse response
         * * Output:  none
         * *
         * * Description:
         * * This method handles post messages to the servlet.  This servlet will respond
         * * to the following commands:
         * *   cmigetcat
         * *   cmiputcat
         * *
         * * A real LMS would probably want to handle each request as a seperate servlet,
         * * but for the purpose of demonstrating a sample LMS it was easier to have a
         * * single servlet.
         * *
         * **************************************************************************
         */
        public void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException
        {
                try
                {
                        logger.info("[LMSCMIServlet]--requested session: " +
                                request.getRequestedSessionId());

                        if (request.isRequestedSessionIdFromCookie())
                        {
                                logger.info(
                                        "[LMSCMIServlet]--requested session id from cookie");
                        }

                        logger.info("[LMSCMIServlet]--query string: " +
                                request.getQueryString());
                        logger.info("[LMSCMIServlet]--header string: " +
                                request.getContextPath());
                        logger.info("[LMSCMIServlet]--cookie header: " +
                                request.getHeader("Cookie"));

                        for (Enumeration e = request.getHeaderNames(); e.hasMoreElements();)
                        {
                                logger.info("[LMSCMIServlet] -- header:" +
                                        (String) e.nextElement());
                        }

                        logger.info(
                                "[LMSCMIServlet]--POST received by LMSCMIServlet");

                        HttpSession session = request.getSession(false);

                        if (session == null)
                        {
                                logger.info(
                                        "[LMSCMIServlet]--this is bad - no session in cmi servlet");
                        }
                        else
                        {
                                logger.info("[LMSCMIServlet]--session id is: " +
                                        session.getId());
                        }

                        logger.info("[LMSCMIServlet]--about to check attributes");

                        for (Enumeration e = request.getHeaderNames(); e.hasMoreElements();)
                        {
                                logger.info("[LMSCMIServlet]--##getHeaderNames="+e.nextElement());
                        }

                        scoID = (String) session.getAttribute("SCOID");
                        nodeID = (String) session.getAttribute("NODEID");
                        userID = (String) request.getSession()
                                .getAttribute(LMSConstants.USERID_KEY);

                        logger.info("[LMSCMIServlet]--session scoID: " + scoID);
                        logger.info("[LMSCMIServlet]--session nodeID: " + nodeID);
                        logger.info("[LMSCMIServlet]--session userID: " + userID);

                        scoFile = ScormConstants.RTERoot + nodeID + File.separator +
                                userID + File.separator + scoID;
                        logger.info("[LMSCMIServlet]--scoFile: " + scoFile);

                        // Open the input stream and pull off the incomming command
                        ObjectInputStream in = new ObjectInputStream(request.getInputStream());

                        logger.info(
                                "[LMSCMIServlet]--Created REQUEST object INPUT stream successfully");

                        ObjectOutputStream out = new ObjectOutputStream(response.getOutputStream());

                        logger.info(
                                "[LMSCMIServlet]--Created RESPONSE object OUTPUT stream successfully");

                        String command = (String) in.readObject();

                        logger.info("[LMSCMIServlet]--Command to LMSCMIServlet is: " +
                                command);

                        // Process the incomming command accordingly
                        if (command.equalsIgnoreCase("cmiputcat"))
                        {
                                logoutFlag = false;

                                SCODataManager inSCOData = (SCODataManager) in.readObject();

                                logger.info(
                                        "[LMSCMIServlet]--LMSCMIServlet read in the SCODataManager object,Show:");
                                inSCOData.getCore().showData();
                                HandleData(inSCOData);

                                if (logoutFlag)
                                {
                                        session.setAttribute("EXITFLAG", "true");
                                }
                                else
                                {
                                        session.removeAttribute("EXITFLAG");
                                }
                        }
                        else if (command.equalsIgnoreCase("cmigetcat"))
                        {
                                FileInputStream fi = new FileInputStream(scoFile);

                                logger.info(
                                        "[LMSCMIServlet]--Created LMSSCODataFile FILE input stream successfully");

                                ObjectInputStream file_in = new ObjectInputStream(fi);

                                logger.info(
                                        "[LMSCMIServlet]--Created OBJECT input stream successfully");

                                scoData = (SCODataManager) file_in.readObject();
                                scoData.getCore().setSessionTime("00:00:00.0");

                                file_in.close();
                                out.writeObject(scoData);

                                System.out.print("LMSCMIServlet processed get for SCO Data\n");
                        }
                        else // invalid command sent, real LMS would handle this more gracefully
                        {
                                String err_msg = "invalid command";
                                out.writeObject(err_msg);
                        }

                        // Close the input and output streams
                        in.close();
                        out.close();
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                }
        } // end doPost

        /**
         * *************************************************************************
         * *
         * * Method:  HandleData
         * * Input:   SCODataManager scoData
         * * Output:  none
         * *
         * * Description:
         * * This method handles processing of the core data being sent from the lesson
         * * to the LMS.  The data needs to be processed and made persistant.
         * * For this example LMS only the core data has been implemented.
         * *
         * **************************************************************************
         */
        private void HandleData(SCODataManager inSCOData)
        {
                try
                {
                        String lessonStatus = "";
                        String lessonExit = "";
                        String lessonEntry = "";

                        // Handle some sequencing issues.  If the exit of the current
                        // SCO is set to "suspend", the user has hit the "Go Back" button
                        // on the current SCO.  When this happens, the mode of the SCO
                        // that would be served up prior to the current SCO is set to
                        // "review".  This is done so that the LMSLessonServlet will know
                        // to display the correct SCO.  Also, the current SCO entry will be
                        // set to "resume" because at some point the student will resume
                        // with the current SCO

                        // Need to get the core object off of the SCO Data Manager
                        // for the current SCO
                        CMICore lmsCore = inSCOData.getCore();

                        // Need to add the SCOs Session Time into the running total time
                        CMITime totalTime = new CMITime(lmsCore.getTotalTime().getValue());

                        logger.info("[LMSCMIServlet]--\tTotal time: " +
                                totalTime.toString());

                        CMITime sessionTime = new CMITime(lmsCore.getSessionTime().getValue());
                        logger.info("[LMSCMIServlet]--\tSession time: " +
                                sessionTime.toString());

                        totalTime.add(sessionTime);
                        lmsCore.setTotalTime(totalTime.toString());

                        logger.info("[LMSCMIServlet]--\t\tTotal time: " +
                                totalTime.toString());

                        // if ( lmsCore.getExit().getValue().equalsIgnoreCase( "suspend" ) )
                        // {
                        // lmsCore.setEntry( "resume" );
                        // if ( currentSCO != 0 )
                        // {
                        //    // Set the previous SCOs Lesson Mode to review
                        //    CMICore previousCore = lmsData[currentSCO - 1].getCore();
                        //    previousCore.setLessonMode( "review" );
                        //    lmsData[currentSCO - 1].setCore(previousCore);
                        //}
                        //}
                        if (lmsCore.getExit().getValue().equalsIgnoreCase("logout"))
                        {
                                // Nothing special needs to be done when a logout occurs
                                // Sequencing will go to the first SCO with a "review" mode or
                                // to the first SCO with an incomplete status.
                                //lmsCore.setExit( "" );
                                logoutFlag = true;
                        }

                        //else
                        //{
                        // User has hit the "continue" button on the current SCO.  The
                        // mode will be set to "normal" in case the mode was "review".
                        //    lmsCore.setLessonMode( "normal" );
                        //}
                        lessonStatus = lmsCore.getLessonStatus().getValue();
                        lessonExit = lmsCore.getExit().getValue();
                        lessonEntry = lmsCore.getEntry().getValue();

                        // Write out the updated data to disk
                        inSCOData.setCore(lmsCore);

                        logger.info(
                                "[LMSCMIServlet]HandleData--Saving Data to the File ...");
                        logger.info("[LMSCMIServlet]HandleData--Prior to Save");
                        logger.info(
                                "[LMSCMIServlet]HandleData--The SCO Data Manager for the current SCO contains " +
                                        "the following:");
                        inSCOData.getCore().showData();

                        // Write out the updated data to disk
                        FileOutputStream fo = new FileOutputStream(scoFile);
                        ObjectOutputStream out_file = new ObjectOutputStream(fo);
                        out_file.writeObject(inSCOData);
                        out_file.close();

                        logger.info("[LMSCMIServlet]HandleData--updateUserSCO: ");
                        logger.info("[LMSCMIServlet]HandleData--String userID=" +
                                userID);
                        logger.info("[LMSCMIServlet]HandleData--String scoID=" +
                                scoID);
                        logger.info(
                                "[LMSCMIServlet]HandleData--StringlessonStatus= " +
                                        lessonStatus);

                        // Now we need to update UserSCO
                        //ScormHelper.updateUserSCO(StringUtil.parseInt(userID),StringUtil.parseInt(scoID), StringUtil.parseInt(lessonStatus));
                        //Gets totalTime from database
                        /*UserSCOModel usco = ScormHelper.getUserSCO(StringUtil.parseInt(userID),StringUtil.parseInt(scoID));
                        if((usco != null) &&(usco.getExitString() != null)){
                            CMITime oldTotalTime = new CMITime(usco.getExitString());
                            totalTime.add(oldTotalTime);
                        }*/
                        ScormHelper.updateUserSCO(StringUtil.parseInt(userID),
                                StringUtil.parseInt(scoID), StringUtils.trimToEmpty(lessonStatus),
                                totalTime.toString());
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                }
        } // end HandleData
}
