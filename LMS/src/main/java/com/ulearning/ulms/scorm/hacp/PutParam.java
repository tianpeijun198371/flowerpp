/**
 * Copyright (c)2006 Beijing WenhuaOnline Sci-tech DevelopmentCo.,Ltd.
 * All rights reserved.
 *
 * User: fengch
 * Date: 2007-9-28 11:46:34
 */
package com.ulearning.ulms.scorm.hacp;

/**
 * Class for process the put parameters command in the aicc server.
 *
 */

import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.scorm.datamodels.SCODataManager;
import com.ulearning.ulms.scorm.datamodels.cmi.CMICore;
import com.ulearning.ulms.scorm.datamodels.cmi.CMIScore;
import com.ulearning.ulms.scorm.helper.NewScormHelper;
import com.ulearning.ulms.scorm.model.NewScorm;
import com.ulearning.ulms.scorm.model.NewScormScoes;
import com.ulearning.ulms.util.LMSConstants;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;

import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class PutParam implements IHacpCommand
{
        protected static Log logger = LogFactory.getLog(GetParam.class);

        //properties
        HttpServletRequest req = null;
        HttpServletResponse resp = null;

        public PutParam(HttpServletRequest request, HttpServletResponse response)
        {
                req = request;
                resp = response;
        }

        public void execute() throws IOException, ServletException
        {
                logger.info("Put Param start!");

                //[core]
                String Lesson_Location = ""; //Lesson_Location
                String Lesson_Status = ""; //Lesson_Status
                String Score = ""; //Score
                String time = ""; //time « ±º‰∂Œ,SCE_total_time,session_time

                //[Core_Lesson]
                //[Comments]
                //[Objectives_Status]
                String[] J_ID; //J_ID.1=1
                String[] J_Score; //J_Score.1=Failed;
                String[] J_Status; //J_Status.1=0
                //[Student_Data]
                //Tries_During_Lesson=
                //Try_Score.1=
                //Try_Status.1=
                //Try_Time.1=
                //[Student_Preferences]

                String Audio = "";
                String Language = "";

                //Lesson_Type=
                String Text = "";

                //Text_Color=
                //Text_Location=
                //Text_Size=
                String Video = "";

                //Window.1=
                String aicc_data = req.getParameter("aicc_data");

                if (aicc_data == null)
                {
                        aicc_data = StringUtils.trimToEmpty(req.getParameter("AICC_Data"));
                }

                logger.info("Put Param:" + aicc_data);
                aicc_data = URLDecoder.decode(aicc_data, "UTF-8");
                logger.info("Decodered Put Param:" + aicc_data);

                IParameter p = null;

                try
                {
                        p = ParameterFactory.getInstanceOf(aicc_data);
                        p.print();

                        Lesson_Location = p.getParameter("Lesson_Location");
                        Lesson_Status = p.getParameter("Lesson_Status");
                        Score = p.getParameter("Score");
                        time = p.getParameter("Time");
                        Audio = p.getParameter("Audio");
                        Language = p.getParameter("Language");
                        Text = p.getParameter("Text");
                        Video = p.getParameter("Video");
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                }

                String paramName = "";
                int max = 0;
                int Jmax = 0;
                int i = 0;

                for (i = 0; i < p.getCount(); i++)
                {
                        paramName = p.getName(i).trim();

                        if (paramName.length() > 5)
                        {
                                if (paramName.substring(0, 5).equals("J_ID."))
                                {
                                        Jmax = Jmax + 1;
                                }
                        }

                        max = max + 1;
                }

                logger.info("max = " + max);
                logger.info("Jmax = " + Jmax);

                J_ID = new String[Jmax]; //J_ID.1=1
                J_Score = new String[Jmax]; //J_Score.1=Failed;
                J_Status = new String[Jmax]; //J_Status.1=0

                for (i = 0; i < Jmax; i++)
                {
                        J_ID[i] = p.getParameter("J_ID." + String.valueOf(i + 1));
                        J_Score[i] = p.getParameter("J_Score." + String.valueOf(i + 1));
                        J_Status[i] = p.getParameter("J_Status." + String.valueOf(i + 1));
                }

                for (i = 0; i < Jmax; i++)
                {
                        logger.info("J_ID[" + i + "]=" + J_ID[i]);
                        logger.info("J_Score[" + i + "]=" + J_Score[i]);
                        logger.info("J_Status[" + i + "]=" + J_Status[i]);
                }

                NewScorm scorm = (NewScorm) req.getSession().getAttribute("SCORM");
                int nodeID = StringUtil.parseInt((String) req.getSession()
                        .getAttribute("NODEID"));
                int userID = StringUtil.parseInt((String) req.getSession()
                        .getAttribute(LMSConstants.USERID_KEY));
                String identifier = StringUtils.trimToEmpty((String) req.getSession()
                        .getAttribute("IDENTIFER"));

                NewScormScoes current_sco = (NewScormScoes) req.getSession()
                        .getAttribute("CURRENT_SCO");
                SCODataManager scoDate = NewScormHelper.getSCOData(new Integer(userID),
                        scorm.getId(), current_sco.getId(), req.getSession());

                CMICore cmiCore = scoDate.getCore();

                cmiCore.setLessonLocation(Lesson_Location);
                cmiCore.setLessonStatus(Lesson_Status);

                CMIScore cmiScore = new CMIScore();
                cmiScore.setRaw(Score);
                cmiCore.setScore(cmiScore);
                cmiCore.setSessionTime(time);

                HacpResponse.sendResult(req, resp, 0, null);
        }

        public static String computTime(String time1, String time2)
        {
                if (time1 == null)
                {
                        return null;
                }
                else
                {
                        if (time1.trim().length() < 8)
                        {
                                return null;
                        }
                }

                if (time2 == null)
                {
                        return null;
                }
                else
                {
                        if (time2.trim().length() < 8)
                        {
                                return null;
                        }
                }

                int hh1 = Integer.parseInt(time1.substring(0, 2));
                int mm1 = Integer.parseInt(time1.substring(3, 5));
                int ss1 = Integer.parseInt(time1.substring(6, 8));

                int hh2 = Integer.parseInt(time2.substring(0, 2));
                int mm2 = Integer.parseInt(time2.substring(3, 5));
                int ss2 = Integer.parseInt(time2.substring(6, 8));

                String hh3 = "";
                String mm3 = "";
                String ss3 = "";

                ss1 = ss1 + ss2;
                mm1 = mm1 + mm2;
                hh1 = hh1 + hh2;

                if (ss1 > 59)
                {
                        mm1 = mm1 + (ss1 / 60);
                        ss1 = ss1 % 60;
                }

                if (mm1 > 59)
                {
                        hh1 = hh1 + (mm1 / 60);
                        mm1 = mm1 % 60;
                }

                if (String.valueOf(hh1).length() <= 1)
                {
                        hh3 = "0" + String.valueOf(hh1);
                }
                else
                {
                        hh3 = String.valueOf(hh1);
                }

                if (String.valueOf(mm1).length() <= 1)
                {
                        mm3 = "0" + String.valueOf(mm1);
                }
                else
                {
                        mm3 = String.valueOf(mm1);
                }

                if (String.valueOf(ss1).length() <= 1)
                {
                        ss3 = "0" + String.valueOf(ss1);
                }
                else
                {
                        ss3 = String.valueOf(ss1);
                }

                return hh3 + ":" + mm3 + ":" + ss3;
        }
}
