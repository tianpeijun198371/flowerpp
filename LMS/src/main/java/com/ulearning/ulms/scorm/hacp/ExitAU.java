/**
 * Copyright (c)2006 Beijing WenhuaOnline Sci-tech DevelopmentCo.,Ltd.
 * All rights reserved.
 *
 * User: fengch
 * Date: 2007-9-28 11:46:34
 */
package com.ulearning.ulms.scorm.hacp;

/**
 * Class for the process the command Exit AU in aicc server side.
 */

import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.scorm.datamodels.SCODataManager;
import com.ulearning.ulms.scorm.datamodels.cmi.CMICore;
import com.ulearning.ulms.scorm.datamodels.cmi.CMITime;
import com.ulearning.ulms.scorm.helper.NewScormHelper;
import com.ulearning.ulms.scorm.model.NewScorm;
import com.ulearning.ulms.scorm.model.NewScormScoes;
import com.ulearning.ulms.scorm.model.NewScormScoesTrack;
import com.ulearning.ulms.scorm.util.CMIConstants;
import com.ulearning.ulms.util.LMSConstants;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ExitAU implements IHacpCommand
{
        protected static Log logger = LogFactory.getLog(ExitAU.class);

        //properties
        HttpServletRequest req = null;
        HttpServletResponse resp = null;

        public ExitAU(HttpServletRequest request, HttpServletResponse response)
        {
                req = request;
                resp = response;
        }

        public void execute() throws IOException, ServletException
        {
                String aicc_data = req.getParameter("aicc_data");

                if (aicc_data != null)
                {
                        logger.info("courseware input:" + aicc_data);
                }

                logger.info("ExitAU start!");

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

                NewScormScoesTrack track = null;
                String value = null;

                value = cmiCore.getLessonLocation().getValue();

                logger.info("lesson_location value = " + value);

                if ((value != null) && !value.equals(""))
                {
                        track = new NewScormScoesTrack();
                        track.setScoId(current_sco.getId());
                        track.setUserId(new Integer(userID));
                        track.setElement("cmi.core.lesson_location");
                        track.setValue(value);
                        NewScormHelper.updateScormScoesTrack(track);
                        cmiCore.setLessonLocation(value);
                }

                value = cmiCore.getCredit().getValue();
                logger.info("credit value = " + value);

                if ((value != null) && !value.equals(""))
                {
                        if (String.valueOf(value.charAt(0))
                                .equalsIgnoreCase(String.valueOf(
                                        CMIConstants.CREDIT_CREDIT.charAt(0))))
                        {
                                value = CMIConstants.CREDIT_CREDIT;
                        }
                        else if (String.valueOf(value.charAt(0))
                                .equalsIgnoreCase(String.valueOf(
                                        CMIConstants.CREDIT_NOCREDIT.charAt(0))))
                        {
                                value = CMIConstants.CREDIT_NOCREDIT;
                        }

                        logger.info("credit value1 = " + value);
                        track = new NewScormScoesTrack();
                        track.setScoId(current_sco.getId());
                        track.setUserId(new Integer(userID));
                        track.setElement("cmi.core.credit");
                        track.setValue(value);
                        NewScormHelper.updateScormScoesTrack(track);

                        cmiCore.setCredit(value);
                }

                value = cmiCore.getTotalTime().getValue();
                logger.info("lesson_location value = " + value);

                if ((value != null) && !value.equals(""))
                {
                        track = new NewScormScoesTrack();
                        track.setScoId(current_sco.getId());
                        track.setUserId(new Integer(userID));
                        track.setElement("cmi.core.total_time");
                        track.setValue(value);
                        NewScormHelper.updateScormScoesTrack(track);

                        cmiCore.setTotalTime(value);
                }

                value = cmiCore.getLessonStatus().getValue();
                logger.info("lesson_status value = " + value);

                if ((value != null) && !value.equals(""))
                {
                        logger.info(" 这次状态 value = " + value);

                        NewScormScoesTrack track_tmp = NewScormHelper.isExistScormScoesTrack(new Integer(
                                userID), current_sco.getId(), "cmi.core.lesson_status");
                        String value_tmp = "";

                        if (track_tmp != null)
                        {
                                value_tmp = track_tmp.getValue();
                                logger.info(" 原来状态 value_tmp = " + value_tmp);
                        }

                        if ((track_tmp == null) ||
                                (((track_tmp != null) &&
                                        value_tmp.equals(CMIConstants.STATUS_INCOMPLETE)) ||
                                        value_tmp.equals(CMIConstants.STATUS_NOTATTEMPTED) ||
                                        value_tmp.equals(CMIConstants.STATUS_BROWSED)))
                        {
                                if (String.valueOf(value.charAt(0))
                                        .equalsIgnoreCase(String.valueOf(
                                                CMIConstants.STATUS_BROWSED.charAt(0))))
                                {
                                        value = CMIConstants.STATUS_BROWSED;
                                }
                                else if (String.valueOf(value.charAt(0))
                                        .equalsIgnoreCase(String.valueOf(
                                                CMIConstants.STATUS_COMPLETED.charAt(0))))
                                {
                                        value = CMIConstants.STATUS_COMPLETED;
                                }
                                else if (String.valueOf(value.charAt(0))
                                        .equalsIgnoreCase(String.valueOf(
                                                CMIConstants.STATUS_COMPLETED.charAt(0))))
                                {
                                        value = CMIConstants.STATUS_COMPLETED;
                                }
                                else if (String.valueOf(value.charAt(0))
                                        .equalsIgnoreCase(String.valueOf(
                                                CMIConstants.STATUS_FAILED.charAt(0))))
                                {
                                        value = CMIConstants.STATUS_FAILED;
                                }
                                else if (String.valueOf(value.charAt(0))
                                        .equalsIgnoreCase(String.valueOf(
                                                CMIConstants.STATUS_INCOMPLETE.charAt(0))))
                                {
                                        value = CMIConstants.STATUS_INCOMPLETE;
                                }
                                else if (String.valueOf(value.charAt(0))
                                        .equalsIgnoreCase(String.valueOf(
                                                CMIConstants.STATUS_NOTATTEMPTED.charAt(0))))
                                {
                                        value = CMIConstants.STATUS_NOTATTEMPTED;
                                }
                                else if (String.valueOf(value.charAt(0))
                                        .equalsIgnoreCase(String.valueOf(
                                                CMIConstants.STATUS_PASSED.charAt(0))))
                                {
                                        value = CMIConstants.STATUS_PASSED;
                                }

                                logger.info("lesson_status value1 = " + value);
                                track = new NewScormScoesTrack();
                                track.setScoId(current_sco.getId());
                                track.setUserId(new Integer(userID));
                                track.setElement("cmi.core.lesson_status");
                                track.setValue(value);
                                NewScormHelper.updateScormScoesTrack(track);
                                cmiCore.setLessonStatus(value);
                        }
                }

                value = cmiCore.getEntry().getValue();
                logger.info("entry value = " + value);

                if ((value != null) && !value.equals(""))
                {
                        if (String.valueOf(value.charAt(0))
                                .equalsIgnoreCase(String.valueOf(
                                        CMIConstants.ENTRY_ABINITIO.charAt(0))))
                        {
                                value = CMIConstants.ENTRY_ABINITIO;
                        }
                        else if (String.valueOf(value.charAt(0))
                                .equalsIgnoreCase(String.valueOf(
                                        CMIConstants.ENTRY_EMPTY.charAt(0))))
                        {
                                value = CMIConstants.ENTRY_EMPTY;
                        }
                        else if (String.valueOf(value.charAt(0))
                                .equalsIgnoreCase(String.valueOf(
                                        CMIConstants.ENTRY_RESUME.charAt(0))))
                        {
                                value = CMIConstants.ENTRY_RESUME;
                        }

                        logger.info("entry value1 = " + value);
                        track = new NewScormScoesTrack();
                        track.setScoId(current_sco.getId());
                        track.setUserId(new Integer(userID));
                        track.setElement("cmi.core.entry");
                        track.setValue(value);
                        NewScormHelper.updateScormScoesTrack(track);

                        cmiCore.setEntry(value);
                }

                value = cmiCore.getLessonMode().getValue();
                logger.info("lesson_mode value = " + value);

                if ((value != null) && !value.equals(""))
                {
                        if (String.valueOf(value.charAt(0))
                                .equalsIgnoreCase(String.valueOf(
                                        CMIConstants.MODE_BROWSE.charAt(0))))
                        {
                                value = CMIConstants.MODE_BROWSE;
                        }
                        else if (String.valueOf(value.charAt(0))
                                .equalsIgnoreCase(String.valueOf(
                                        CMIConstants.MODE_NORMAL.charAt(0))))
                        {
                                value = CMIConstants.MODE_NORMAL;
                        }
                        else if (String.valueOf(value.charAt(0))
                                .equalsIgnoreCase(String.valueOf(
                                        CMIConstants.MODE_REVIEW.charAt(0))))
                        {
                                value = CMIConstants.MODE_REVIEW;
                        }

                        logger.info("lesson_mode value1 = " + value);
                        track = new NewScormScoesTrack();
                        track.setScoId(current_sco.getId());
                        track.setUserId(new Integer(userID));
                        track.setElement("cmi.core.lesson_mode");
                        track.setValue(value);
                        NewScormHelper.updateScormScoesTrack(track);

                        cmiCore.setLessonMode(value);
                }

                value = cmiCore.getExit().getValue();
                logger.info("exit value = " + value);

                if ((value != null) && !value.equals(""))
                {
                        if (String.valueOf(value.charAt(0))
                                .equalsIgnoreCase(String.valueOf(
                                        CMIConstants.EXIT_LOGOUT.charAt(0))))
                        {
                                value = CMIConstants.EXIT_LOGOUT;
                        }
                        else if (String.valueOf(value.charAt(0))
                                .equalsIgnoreCase(String.valueOf(
                                        CMIConstants.EXIT_SUSPEND.charAt(0))))
                        {
                                value = CMIConstants.EXIT_SUSPEND;
                        }
                        else if (String.valueOf(value.charAt(0))
                                .equalsIgnoreCase(String.valueOf(
                                        CMIConstants.EXIT_TIMEOUT.charAt(0))))
                        {
                                value = CMIConstants.EXIT_TIMEOUT;
                        }

                        logger.info("exit value1 = " + value);
                        track = new NewScormScoesTrack();
                        track.setScoId(current_sco.getId());
                        track.setUserId(new Integer(userID));
                        track.setElement("cmi.core.exit");
                        track.setValue(value);
                        NewScormHelper.updateScormScoesTrack(track);

                        cmiCore.setExit(value);
                }

                value = cmiCore.getSessionTime().getValue();
                logger.info("session_time value = " + value);

                if ((value != null) && !value.equals(""))
                {
                        NewScormScoesTrack track_tmp = NewScormHelper.isExistScormScoesTrack(new Integer(
                                userID), current_sco.getId(), "cmi.core.session_time");

                        logger.info(" 这次停留时间 value = " + value);

                        CMITime totalSessionTime = new CMITime(value);

                        if (track_tmp != null)
                        {
                                String value_tmp = track_tmp.getValue();
                                logger.info(" 以前停留时间 value = " + value_tmp);

                                if ((value_tmp != null) && !value_tmp.equals(""))
                                {
                                        CMITime cmiSessionTime = new CMITime(value_tmp);
                                        totalSessionTime.add(cmiSessionTime);
                                }
                        }

                        logger.info(" 总停留时间 value = " + totalSessionTime.toString());
                        track = new NewScormScoesTrack();
                        track.setScoId(current_sco.getId());
                        track.setUserId(new Integer(userID));
                        track.setElement("cmi.core.session_time");
                        track.setValue(totalSessionTime.toString());
                        NewScormHelper.updateScormScoesTrack(track);

                        cmiCore.setSessionTime(totalSessionTime.toString());
                }

                //NewScormHelper.writeSCODataFromFile(scoDate,new Integer(nodeID),new Integer(userID),scorm.getId());
                req.getSession()
                        .removeAttribute("SCODataManager_" + scorm.getId() + "_" +
                                current_sco.getId());
                HacpResponse.sendResult(req, resp, 0, null);
        }
}
