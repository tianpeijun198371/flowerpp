/**
 * Copyright (c)2006 Beijing WenhuaOnline Sci-tech DevelopmentCo.,Ltd.
 * All rights reserved.
 *
 * User: fengch
 * Date: 2007-9-21 14:05:59
 */
package com.ulearning.ulms.scorm.helper;

import com.ulearning.ulms.scorm.dao.*;
import com.ulearning.ulms.scorm.datamodels.SCODataManager;
import com.ulearning.ulms.scorm.datamodels.cmi.*;
import com.ulearning.ulms.scorm.exceptions.ScormSysException;
import com.ulearning.ulms.scorm.model.NewScorm;
import com.ulearning.ulms.scorm.model.NewScormScoes;
import com.ulearning.ulms.scorm.model.NewScormScoesTrack;
import com.ulearning.ulms.scorm.util.CMIConstants;
import com.ulearning.ulms.scorm.util.ScormConstants;
import com.ulearning.ulms.user.bean.UserHelper;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;

import java.util.List;

import javax.servlet.http.HttpSession;


public class NewScormHelper
{
        protected static Log logger = LogFactory.getLog(NewScormHelper.class);
        private static NewScormDAO newScormDAO;
        private static NewScormScoesDAO newScormScoesDAO;
        private static NewScormScoesTrackDAO newScormScoesTrackDAO;

        static
        {
                try
                {
                        newScormDAO = NewScormDAOFactory.getDAO();
                        newScormScoesDAO = NewScormScoesDAOFactory.getDAO();
                        newScormScoesTrackDAO = NewScormScoesTrackDAOFactory.getDAO();
                }
                catch (Exception ex)
                {
                        ex.printStackTrace();
                }
        }

        /**
         * this method is used to initialize the data model with temporary data.
         *
         * @param this_userID
         * @param this_location
         * @param this_dataFromLMS
         * @param this_masteryScore
         * @param this_maxTimeAllowed
         * @param this_timeLimitAction
         * @return
         */
        public static SCODataManager initSCOData(String this_userID,
                                                 String this_credit, String this_lessonStatus, String this_entry,
                                                 String this_location, String session_time, String this_dataFromLMS,
                                                 String this_masteryScore, String this_maxTimeAllowed,
                                                 String this_timeLimitAction)
        {
                SCODataManager tmpSCOData = new SCODataManager();

                try
                {
                        logger.info("initSCOData-----start!");
                        logger.info("initSCOData-----getting user info");

                        String studentName = UserHelper.getUser(String.valueOf(this_userID))
                                .getName();
                        logger.info("initSCOData-----student name: " + studentName);

                        // Build Core
                        CMIScore tmpScore = new CMIScore();
                        tmpScore.setRaw("0");
                        tmpScore.setMax("0");
                        tmpScore.setMin("0");

                        CMICore tmpCore = new CMICore();
                        tmpCore.setStudentId(this_userID);
                        tmpCore.setStudentName(studentName);
                        tmpCore.setLessonLocation(this_location);
                        tmpCore.setCredit(this_credit);
                        tmpCore.setLessonStatus(this_lessonStatus);
                        tmpCore.setEntry(this_entry);

                        if ((session_time != null) && !session_time.equals(""))
                        {
                                tmpCore.setSessionTime(session_time);
                        }

                        tmpCore.setTotalTime("00:00:00.0");
                        tmpCore.setLessonMode(CMIConstants.MODE_NORMAL);
                        tmpCore.setExit("");
                        tmpCore.setScore(tmpScore);
                        tmpSCOData.setCore(tmpCore);

                        // Build Suspend Data
                        CMISuspendData tmpSuspendData = new CMISuspendData();
                        tmpSuspendData.setSuspendData("");
                        tmpSCOData.setSuspendData(tmpSuspendData);

                        // Build Launch Data
                        CMILaunchData tmpLaunchData = new CMILaunchData();
                        tmpLaunchData.setLaunchData(this_dataFromLMS);
                        tmpSCOData.setLaunchData(tmpLaunchData);

                        // Build Comments
                        CMICommentsFromLms tmpComments = new CMICommentsFromLms("");
                        tmpSCOData.setCommentsFromLMS(tmpComments);

                        // Build Objective Data
                        /*
                          CMIObjectives tmpObjectives = new CMIObjectives();
                          CMIObjectiveData tmpObjectiveData = new CMIObjectiveData();
                          tmpObjectiveData.setId("O001");
                          tmpObjectiveData.setScore(tmpScore);
                          tmpObjectiveData.setStatus("not attempted");
                          tmpObjectives.setObjectives(tmpObjectiveData,0);
                          tmpSCOData.setObjectives(tmpObjectives);
                        */

                        // Build Student Data
                        CMIStudentData tmpStudentData = new CMIStudentData();

                        tmpStudentData.setMasteryScore(this_masteryScore);
                        tmpStudentData.setMaxTimeAllowed(this_maxTimeAllowed);
                        tmpStudentData.setTimeLimitAction(this_timeLimitAction);
                        tmpSCOData.setStudentData(tmpStudentData);

                        // Build Student Preference
                        CMIStudentPreference tmpStudentPreference = new CMIStudentPreference();
                        tmpStudentPreference.setAudio("3");
                        tmpStudentPreference.setLanguage("Chinese");
                        tmpStudentPreference.setSpeed("4");
                        tmpStudentPreference.setText("10");
                        tmpSCOData.setStudentPreference(tmpStudentPreference);
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                }

                logger.info("initSCOData-----end!");

                return tmpSCOData;
        }

        /**
         * 返回学生在sco的状态（SCODataManager）
         *
         * @param userId
         * @param scoId
         * @return
         */
        public static SCODataManager getSCOData_bak(Integer courseContentNodeID,
                                                    Integer userId, Integer scoId) throws ScormSysException
        {
                SCODataManager rteSCOData = null;

                try
                {
                        String RTESCODataFile = ScormConstants.RTERoot +
                                courseContentNodeID + File.separator + userId + File.separator +
                                scoId;
                        File theRTESCODataFile = new File(RTESCODataFile);

                        if (theRTESCODataFile.exists())
                        {
                                logger.info("读取文件！");
                                rteSCOData = readSCODataFromFile(courseContentNodeID, userId,
                                        scoId);
                        }
                        else
                        {
                                logger.info("读取数据库！");

                                String location;
                                float masteryScore = 0;
                                String parameterString = "";
                                String lessonStatus = CMIConstants.STATUS_NOTATTEMPTED;
                                String prerequisites = "";
                                String exit = CMIConstants.EXIT_LOGOUT;
                                String credit = CMIConstants.CREDIT_NOCREDIT;
                                String entry = CMIConstants.ENTRY_ABINITIO;
                                String dataFromLMS = "";
                                String maxTimeAllowed = "";
                                String timeLimitAction = CMIConstants.TIMELIMITACTION_EXIT_NOMESSAGE;
                                String mode = CMIConstants.MODE_NORMAL;
                                String session_time = "";
                                int sequence = 0;

                                NewScorm scorm = getNewScorm(scoId);
                                NewScormScoes sco = getNewScormScoes(scoId);
                                masteryScore = (sco.getMasteryScore() != null)
                                        ? sco.getMasteryScore().floatValue() : 0;
                                parameterString = sco.getParameterString();
                                prerequisites = sco.getPrerequisites();
                                dataFromLMS = sco.getDataFromLMS();
                                maxTimeAllowed = sco.getMaxTimeAllowed();
                                timeLimitAction = sco.getTimeLimitAction();
                                location = sco.getLaunch();

                                List tracks = getNewScormScoesTracksByUserAndSco(userId, scoId);

                                for (int i = 0; i < tracks.size(); i++)
                                {
                                        NewScormScoesTrack track = (NewScormScoesTrack) tracks.get(i);
                                        String element = StringUtils.trimToEmpty(track.getElement());
                                        String value = StringUtils.trimToEmpty(track.getValue());

                                        if (element.equalsIgnoreCase("cmi.core.student_id"))
                                        {
                                        }
                                        else if (element.equalsIgnoreCase("cmi.core.student_name"))
                                        {
                                        }
                                        else if (element.equalsIgnoreCase(
                                                "cmi.core.lesson_location"))
                                        {
                                                location = value;
                                        }
                                        else if (element.equalsIgnoreCase("cmi.core.credit"))
                                        {
                                                credit = value;
                                        }
                                        else if (element.equalsIgnoreCase(
                                                "cmi.core.lesson_status"))
                                        {
                                                lessonStatus = value;
                                        }
                                        else if (element.equalsIgnoreCase("cmi.core.entry"))
                                        {
                                                entry = value;
                                        }
                                        else if (element.equalsIgnoreCase("cmi.core.total_time"))
                                        {
                                        }
                                        else if (element.equalsIgnoreCase("cmi.core.lesson_mode"))
                                        {
                                                mode = value;
                                        }
                                        else if (element.equalsIgnoreCase("cmi.core.exit"))
                                        {
                                                exit = value;
                                        }
                                        else if (element.equalsIgnoreCase("cmi.core.session_time"))
                                        {
                                                session_time = value;
                                        }
                                        else if (element.equalsIgnoreCase("cmi.core.score.raw"))
                                        {
                                        }
                                        else if (element.equalsIgnoreCase("cmi.core.score.min"))
                                        {
                                        }
                                        else if (element.equalsIgnoreCase("cmi.core.score.max"))
                                        {
                                        }
                                        else if (element.equalsIgnoreCase("cmi.suspend_data"))
                                        {
                                        }
                                        else if (element.equalsIgnoreCase("cmi.launch_data"))
                                        {
                                        }
                                        else if (element.equalsIgnoreCase("cmi.comments"))
                                        {
                                        }
                                        else if (element.equalsIgnoreCase("cmi.comments_from_lms"))
                                        {
                                        }
                                        else if (element.equalsIgnoreCase(
                                                "cmi.student_data._children"))
                                        {
                                        }
                                        else if (element.equalsIgnoreCase(
                                                "cmi.student_data._count"))
                                        {
                                        }
                                        else if (element.equalsIgnoreCase(
                                                "cmi.student_data.mastery_score"))
                                        {
                                        }
                                        else if (element.equalsIgnoreCase(
                                                "cmi.student_data.max_time_allowed"))
                                        {
                                        }
                                        else if (element.equalsIgnoreCase(
                                                "cmi.student_data.time_limit_action"))
                                        {
                                        }
                                        else if (element.equalsIgnoreCase(
                                                "cmi.student_preference._children"))
                                        {
                                        }
                                        else if (element.equalsIgnoreCase(
                                                "cmi.studnet_preference._count"))
                                        {
                                        }
                                        else if (element.equalsIgnoreCase(
                                                "cmi.student_data.max_time_allowed"))
                                        {
                                        }
                                        else if (element.equalsIgnoreCase(
                                                "cmi.student_preference.language"))
                                        {
                                        }
                                        else if (element.equalsIgnoreCase(
                                                "cmi.student_preference.speed"))
                                        {
                                        }
                                        else if (element.equalsIgnoreCase(
                                                "cmi.student_preference.text"))
                                        {
                                        }
                                }

                                rteSCOData = initSCOData(String.valueOf(userId), credit,
                                        lessonStatus, entry, location, session_time,
                                        dataFromLMS, String.valueOf(masteryScore),
                                        maxTimeAllowed, timeLimitAction);

                                logger.info("insertUserSCO--RTESCODataFile>" + RTESCODataFile +
                                        "<");
                                // Write out the data to disk using serialization
                                writeSCODataFromFile(rteSCOData, courseContentNodeID, userId,
                                        scoId);
                        }
                }
                catch (Exception se)
                {
                        se.printStackTrace();
                        throw new ScormSysException(se);
                }

                return rteSCOData;
        }

        /**
         * 返回学生在sco的状态（SCODataManager）
         *
         * @param userId
         * @param scoId
         * @return
         */
        public static SCODataManager getSCOData(Integer userId, Integer scormId,
                                                Integer scoId) throws ScormSysException
        {
                SCODataManager rteSCOData = null;

                try
                {
                        logger.info("读取数据库！");

                        String location;
                        float masteryScore = 0;
                        String parameterString = "";
                        String lessonStatus = CMIConstants.STATUS_NOTATTEMPTED;
                        String prerequisites = "";
                        String exit = CMIConstants.EXIT_LOGOUT;
                        String credit = CMIConstants.CREDIT_NOCREDIT;
                        String entry = CMIConstants.ENTRY_ABINITIO;
                        String dataFromLMS = "";
                        String maxTimeAllowed = "";
                        String timeLimitAction = CMIConstants.TIMELIMITACTION_EXIT_NOMESSAGE;
                        String mode = CMIConstants.MODE_NORMAL;
                        String session_time = "";
                        int sequence = 0;

                        NewScorm scorm = getNewScorm(scoId);
                        NewScormScoes sco = getNewScormScoes(scoId);
                        masteryScore = (sco.getMasteryScore() != null)
                                ? sco.getMasteryScore().floatValue() : 0;
                        parameterString = sco.getParameterString();
                        prerequisites = sco.getPrerequisites();
                        dataFromLMS = sco.getDataFromLMS();
                        maxTimeAllowed = sco.getMaxTimeAllowed();
                        timeLimitAction = sco.getTimeLimitAction();
                        location = sco.getLaunch();

                        List tracks = getNewScormScoesTracksByUserAndSco(userId, scoId);

                        for (int i = 0; i < tracks.size(); i++)
                        {
                                NewScormScoesTrack track = (NewScormScoesTrack) tracks.get(i);
                                String element = StringUtils.trimToEmpty(track.getElement());
                                String value = StringUtils.trimToEmpty(track.getValue());

                                if (element.equalsIgnoreCase("cmi.core.student_id"))
                                {
                                }
                                else if (element.equalsIgnoreCase("cmi.core.student_name"))
                                {
                                }
                                else if (element.equalsIgnoreCase("cmi.core.lesson_location"))
                                {
                                        location = value;
                                }
                                else if (element.equalsIgnoreCase("cmi.core.credit"))
                                {
                                        credit = value;
                                }
                                else if (element.equalsIgnoreCase("cmi.core.lesson_status"))
                                {
                                        lessonStatus = value;
                                }
                                else if (element.equalsIgnoreCase("cmi.core.entry"))
                                {
                                        entry = value;
                                }
                                else if (element.equalsIgnoreCase("cmi.core.total_time"))
                                {
                                }
                                else if (element.equalsIgnoreCase("cmi.core.lesson_mode"))
                                {
                                        mode = value;
                                }
                                else if (element.equalsIgnoreCase("cmi.core.exit"))
                                {
                                        exit = value;
                                }
                                else if (element.equalsIgnoreCase("cmi.core.session_time"))
                                {
                                        session_time = value;
                                }
                                else if (element.equalsIgnoreCase("cmi.core.score.raw"))
                                {
                                }
                                else if (element.equalsIgnoreCase("cmi.core.score.min"))
                                {
                                }
                                else if (element.equalsIgnoreCase("cmi.core.score.max"))
                                {
                                }
                                else if (element.equalsIgnoreCase("cmi.suspend_data"))
                                {
                                }
                                else if (element.equalsIgnoreCase("cmi.launch_data"))
                                {
                                }
                                else if (element.equalsIgnoreCase("cmi.comments"))
                                {
                                }
                                else if (element.equalsIgnoreCase("cmi.comments_from_lms"))
                                {
                                }
                                else if (element.equalsIgnoreCase(
                                        "cmi.student_data._children"))
                                {
                                }
                                else if (element.equalsIgnoreCase("cmi.student_data._count"))
                                {
                                }
                                else if (element.equalsIgnoreCase(
                                        "cmi.student_data.mastery_score"))
                                {
                                }
                                else if (element.equalsIgnoreCase(
                                        "cmi.student_data.max_time_allowed"))
                                {
                                }
                                else if (element.equalsIgnoreCase(
                                        "cmi.student_data.time_limit_action"))
                                {
                                }
                                else if (element.equalsIgnoreCase(
                                        "cmi.student_preference._children"))
                                {
                                }
                                else if (element.equalsIgnoreCase(
                                        "cmi.studnet_preference._count"))
                                {
                                }
                                else if (element.equalsIgnoreCase(
                                        "cmi.student_data.max_time_allowed"))
                                {
                                }
                                else if (element.equalsIgnoreCase(
                                        "cmi.student_preference.language"))
                                {
                                }
                                else if (element.equalsIgnoreCase(
                                        "cmi.student_preference.speed"))
                                {
                                }
                                else if (element.equalsIgnoreCase(
                                        "cmi.student_preference.text"))
                                {
                                }
                        }

                        rteSCOData = initSCOData(String.valueOf(userId), credit,
                                lessonStatus, entry, location, session_time, dataFromLMS,
                                String.valueOf(masteryScore), maxTimeAllowed,
                                timeLimitAction);
                }
                catch (Exception se)
                {
                        se.printStackTrace();
                        throw new ScormSysException(se);
                }

                return rteSCOData;
        }

        /**
         * 返回学生在sco的状态（SCODataManager）
         *
         * @param userId
         * @param scoId
         * @return
         */
        public static SCODataManager getSCOData(Integer userId, Integer scormId,
                                                Integer scoId, HttpSession session) throws ScormSysException
        {
                SCODataManager rteSCOData = null;
                rteSCOData = (SCODataManager) session.getAttribute("SCODataManager_" +
                        scormId + "_" + scoId);
                logger.info("rteSCOData=" + rteSCOData);

                if (rteSCOData == null)
                {
                        rteSCOData = getSCOData(userId, scormId, scoId);
                        session.setAttribute("SCODataManager_" + scormId + "_" + scoId,
                                rteSCOData);
                }

                return rteSCOData;
        }

        public static SCODataManager readSCODataFromFile(
                Integer courseContentNodeID, Integer userId, Integer scoId)
                throws ScormSysException
        {
                SCODataManager scoData = null;
                FileInputStream fileIn = null;
                ObjectInputStream objectIn = null;

                try
                {
                        String RTESCODataFile = ScormConstants.RTERoot +
                                courseContentNodeID + File.separator + userId + File.separator +
                                scoId;
                        File theRTESCODataFile = new File(RTESCODataFile);

                        if (theRTESCODataFile.exists())
                        {
                                fileIn = new FileInputStream(RTESCODataFile);

                                logger.info(
                                        "[LMSCMIServlet]--Created LMSSCODataFile FILE input stream successfully");

                                objectIn = new ObjectInputStream(fileIn);

                                logger.info(
                                        "[LMSCMIServlet]--Created OBJECT input stream successfully");

                                scoData = (SCODataManager) objectIn.readObject();
                                scoData.getCore().setSessionTime("00:00:00.0");
                        }
                }
                catch (Exception se)
                {
                        se.printStackTrace();
                        throw new ScormSysException(se);
                }
                finally
                {
                        try
                        {
                                if (objectIn != null)
                                {
                                        objectIn.close();
                                }

                                if (fileIn != null)
                                {
                                        fileIn.close();
                                }
                        }
                        catch (Exception e)
                        {
                                e.printStackTrace();
                        }
                }

                return scoData;
        }

        public static void writeSCODataFromFile(SCODataManager scoData,
                                                Integer courseContentNodeID, Integer userId, Integer scoId)
                throws ScormSysException
        {
                FileOutputStream fo = null;
                ObjectOutputStream oo = null;

                try
                {
                        String RTESCODataFile = ScormConstants.RTERoot +
                                courseContentNodeID + File.separator + userId + File.separator +
                                scoId;
                        File theRTESCODataFile = new File(RTESCODataFile);

                        if (scoData != null)
                        {
                                theRTESCODataFile.deleteOnExit();
                                // Write out the data to disk using serialization
                                fo = new FileOutputStream(RTESCODataFile);
                                oo = new ObjectOutputStream(fo);
                                oo.writeObject(scoData);
                        }

                        logger.info("写入成功！");
                }
                catch (Exception se)
                {
                        se.printStackTrace();
                        throw new ScormSysException(se);
                }
                finally
                {
                        try
                        {
                                if (oo != null)
                                {
                                        oo.close();
                                }

                                if (fo != null)
                                {
                                        fo.close();
                                }
                        }
                        catch (Exception e)
                        {
                                e.printStackTrace();
                        }
                }
        }

        /**
         * 查找Scorm
         *
         * @param id
         * @throws ScormSysException
         */
        public static NewScorm getNewScorm(Integer id) throws ScormSysException
        {
                return newScormDAO.get(id);
        }

        /**
         * 根据相关ID，返回对应Scorm
         *
         * @throws ScormSysException
         */
        public static NewScorm getNewScormByRelationID(Integer relationID,
                                                       String type) throws ScormSysException
        {
                return newScormDAO.getByRelationID(relationID, type);
        }

        /*
        * 插入Scorm.
        */
        public static void insertNewScorm(NewScorm newScorm)
                throws ScormSysException
        {
                newScormDAO.insert(newScorm);
        }

        /*
        * 更新Scorm.
        *
        */
        public static void updateNewScorm(NewScorm newScorm)
                throws ScormSysException
        {
                newScormDAO.update(newScorm);
        }

        /*
        * 删除 Scorm.
        *
        */
        public static void deleteNewScorm(NewScorm newScorm)
                throws ScormSysException
        {
                newScormDAO.delete(newScorm);
        }

        /**
         * 查找
         *
         * @param id
         * @throws ScormSysException
         */
        public static NewScormScoes getNewScormScoes(Integer id)
                throws ScormSysException
        {
                return newScormScoesDAO.get(id);
        }

        /**
         * 查找
         *
         * @param identifer
         * @throws ScormSysException
         */
        public static NewScormScoes getNewScormScoesByIdentifier(Integer scormId,
                                                                 String identifer)
        {
                return newScormScoesDAO.getByIdentifier(scormId, identifer);
        }

        /**
         * 返回Scorm的所有节点
         *
         * @param @return
         * @throws com.ulearning.ulms.content.exceptions.ContentManageSysException
         *
         */
        public static List getNewScormScoesByScorm(Integer scormId)
                throws ScormSysException
        {
                return newScormScoesDAO.getByScorm(scormId);
        }

        /**
         * 返回Scorm的所有节点
         *
         * @param @return
         * @throws com.ulearning.ulms.content.exceptions.ContentManageSysException
         *
         */
        public static List getNewScormScoesByScorm(Integer scormId, String scoType)
                throws ScormSysException
        {
                return newScormScoesDAO.getByScorm(scormId, scoType);
        }

        /**
         * 返回Scorm的第一个Sco节点
         *
         * @param @return
         * @throws com.ulearning.ulms.content.exceptions.ContentManageSysException
         *
         */
        public static NewScormScoes getNewScormScoesFirstScoByScorm(Integer scormId)
                throws ScormSysException
        {
                return newScormScoesDAO.getFirstScoByScorm(scormId);
        }

        /**
         * 返回Scorm的最后一个Sco节点
         *
         * @param @return
         * @throws com.ulearning.ulms.content.exceptions.ContentManageSysException
         *
         */
        public static NewScormScoes getNewScormScoesLastScoByScorm(Integer scormId)
                throws ScormSysException
        {
                return newScormScoesDAO.getLastScoByScorm(scormId);
        }

        /**
         * 返回Scorm的所有Sco节点
         *
         * @param @return
         * @throws com.ulearning.ulms.content.exceptions.ContentManageSysException
         *
         */
        public static List getNewScormScoesScoByScorm(Integer scormId)
        {
                return newScormScoesDAO.getScoByScorm(scormId);
        }

        /**
         * 返回某节点下面的内容。不包含子目录
         *
         * @param @return
         * @throws com.ulearning.ulms.content.exceptions.ContentManageSysException
         *
         */
        public static List getSubNewScormScoes(Integer scormId, String identifer)
        {
                return newScormScoesDAO.getSubContent(scormId, identifer);
        }

        /*
        * 插入 ScormScoes.
        */
        public static void insertNewScormScoes(NewScormScoes newScormScoes)
                throws ScormSysException
        {
                newScormScoesDAO.insert(newScormScoes);
        }

        /*
        * 更新 ScormScoes.
        */
        public static void updateNewScormScoes(NewScormScoes newScormScoes)
                throws ScormSysException
        {
                newScormScoesDAO.update(newScormScoes);
        }

        /*
        * 删除 ScormScoes.
        */
        public static void deleteNewScormScoes(NewScormScoes newScormScoes)
                throws ScormSysException
        {
                newScormScoesDAO.delete(newScormScoes);
        }

        /**
         * 查找 NewScormScoesTrack
         *
         * @param id
         * @throws ScormSysException
         */
        public static NewScormScoesTrack getNewScormScoesTrack(Integer id)
                throws ScormSysException
        {
                return newScormScoesTrackDAO.get(id);
        }

        /**
         * 返回学生在某sco的状态数据。
         *
         * @param userId
         * @param scoId
         * @return
         * @throws ScormSysException
         */
        public static List getNewScormScoesTracksByUserAndSco(Integer userId,
                                                              Integer scoId)
        {
                return newScormScoesTrackDAO.getNewScormScoesTracksByUserAndSco(userId,
                        scoId);
        }

        /**
         * 判断用户在SCO的跟踪记录(element对应的值)是否存在.
         *
         * @param userId  用户
         * @param scoId   SCO
         * @param element 元素
         * @return 存在与否
         * @throws ScormSysException 出错信息
         */
        public static NewScormScoesTrack isExistScormScoesTrack(Integer userId,
                                                                Integer scoId, String element) throws ScormSysException
        {
                return newScormScoesTrackDAO.isExistScormScoesTrack(userId, scoId,
                        element);
        }

        /*
        * 更新 ScormScoesTrack.
        */
        public static void updateScormScoesTrack(
                NewScormScoesTrack newScormScoesTrack) throws ScormSysException
        {
                logger.info("newScormScoesTrack.getUserId = " +
                        newScormScoesTrack.getUserId());
                logger.info("newScormScoesTrack.getScoId = " +
                        newScormScoesTrack.getScoId());
                logger.info("newScormScoesTrack.getElement = " +
                        newScormScoesTrack.getElement());
                logger.info("newScormScoesTrack.getValue = " +
                        newScormScoesTrack.getValue());

                NewScormScoesTrack track = newScormScoesTrackDAO.isExistScormScoesTrack(newScormScoesTrack.getUserId(),
                        newScormScoesTrack.getScoId(), newScormScoesTrack.getElement());

                if (track != null)
                {
                        logger.info("ScormScoesTrack 存在,更新！");
                        
                        if(track.getElement()!=null && track.getElement().equals("cmi.core.lesson_status"))
                        {
                                
                                if(newScormScoesTrack.getValue()!=null)
                                {  
                                        //若已完成，或通过，或失败，则不再更新状态
                                        //若要把状态由"未完成"转换为"已浏览"，则也不做此转换
                                        if((newScormScoesTrack.getValue().equals(CMIConstants.STATUS_PASSED)
                                             || newScormScoesTrack.getValue().equals(CMIConstants.STATUS_PASSED)
                                             || newScormScoesTrack.getValue().equals(CMIConstants.STATUS_FAILED))
                                             ||
                                             (newScormScoesTrack.getValue().equals(CMIConstants.STATUS_BROWSED)
                                             && track.getValue().equals(CMIConstants.STATUS_INCOMPLETE)))
                                        {
                                                return;
                                        } 
                                }
                        }
                        track.setValue(newScormScoesTrack.getValue());

                        Integer attempt = track.getAttempt();
                        int attempt_int = attempt.intValue();
                        track.setAttempt(new Integer(++attempt_int));
                        newScormScoesTrackDAO.update(track);
                }
                else
                {
                        logger.info("ScormScoesTrack 不存在,插入！");
                        newScormScoesTrackDAO.insert(newScormScoesTrack);
                }
        }

        /*
        * 删除 ScormScoesTrack.
        */
        public static void deleteScormScoesTrack(
                NewScormScoesTrack newScormScoesTrack) throws ScormSysException
        {
                newScormScoesTrackDAO.delete(newScormScoesTrack);
        }

        /**
         * 根据itemID，返回对应的SCO，如果itemID的类型就是Block or Accert，
         * 否则返回itemID下一个SCO.
         * 若没有，返回null.
         */
        public static NewScormScoes getSCO(Integer scormId, String identifer)
                throws ScormSysException
        {
                int itemID_tmp = -1;

                NewScormScoes sco = getNewScormScoesByIdentifier(scormId, identifer);

                String scoType = StringUtils.trimToEmpty(sco.getScoType());
                System.out.println("getSCO--scoType = " + scoType);

                if (scoType.equals(ScormConstants.SCOTYPE_SCO) ||
                        scoType.equals(ScormConstants.SCOTYPE_AU))
                {
                        System.out.println("getSCO--正常直接返回该itemID！");

                        return sco;
                }
                else
                {
                        System.out.println("getSCO--非SCO，需要往下遍历，直到发现一个sco！");
                }

                List allScoes = getNewScormScoesByScorm(scormId);

                for (int i = 0; i < allScoes.size(); i++)
                {
                        NewScormScoes newScormScoes = (NewScormScoes) allScoes.get(i);

                        if (newScormScoes.getSequence().compareTo(sco.getSequence()) > 0)
                        {
                                if (scoType.equals(ScormConstants.SCOTYPE_SCO) ||
                                        scoType.equals(ScormConstants.SCOTYPE_AU))
                                {
                                        System.out.println("getSCO--正常直接返回该itemID！");

                                        return newScormScoes;
                                }
                        }
                }

                return null;
        }

        /**
         * 根据itemID，返回下一个的SCO，如果itemID的类型就是Block or Accert，
         * 否则返回itemID下一个SCO.
         * 若没有，返回null.
         */
        public static NewScormScoes getNextSCO(Integer scormId, String identifer)
                throws ScormSysException
        {
                int itemID_tmp = -1;

                if ((identifer == null) || identifer.equals(""))
                {
                        return getNewScormScoesFirstScoByScorm(scormId);
                }

                NewScormScoes sco = getNewScormScoesByIdentifier(scormId, identifer);

                String scoType = StringUtils.trimToEmpty(sco.getScoType());
                System.out.println("getSCO--scoType = " + scoType);

                List allScoes = getNewScormScoesScoByScorm(scormId);

                for (int i = 0; i < allScoes.size(); i++)
                {
                        NewScormScoes newScormScoes = (NewScormScoes) allScoes.get(i);

                        if (newScormScoes.getSequence().compareTo(sco.getSequence()) > 0)
                        {
                                System.out.println("getSCO--正常直接返回该itemID！");

                                return newScormScoes;
                        }
                }

                return null;
        }

        /**
         * 根据itemID，返回前一个的SCO，如果itemID的类型就是Block or Asset，
         * 否则返回itemID前一个SCO.
         * 若没有，返回null.
         */
        public static NewScormScoes getPreviousSCO(Integer scormId, String identifer)
                throws ScormSysException
        {
                if ((identifer == null) || identifer.equals(""))
                {
                        return getNewScormScoesLastScoByScorm(scormId);
                }

                int itemID_tmp = -1;

                NewScormScoes sco = getNewScormScoesByIdentifier(scormId, identifer);

                String scoType = StringUtils.trimToEmpty(sco.getScoType());
                System.out.println("getSCO--scoType = " + scoType);

                List allScoes = getNewScormScoesScoByScorm(scormId);

                for (int i = allScoes.size() - 1; i >= 0; i--)
                {
                        NewScormScoes newScormScoes = (NewScormScoes) allScoes.get(i);

                        if (newScormScoes.getSequence().compareTo(sco.getSequence()) < 0)
                        {
                                System.out.println("getSCO--正常直接返回该itemID！");

                                return newScormScoes;
                        }
                }

                return null;
        }
}
