/**
 * Copyright (c)2006 Beijing WenhuaOnline Sci-tech DevelopmentCo.,Ltd.
 * All rights reserved.
 *
 * User: fengch
 * Date: 2007-9-28 11:46:34
 */
package com.ulearning.ulms.scorm.hacp;

/**
 * Class for process the get parameters command in aicc server side.
 *
 *
 */

import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.scorm.datamodels.SCODataManager;
import com.ulearning.ulms.scorm.datamodels.cmi.CMICore;
import com.ulearning.ulms.scorm.helper.NewScormHelper;
import com.ulearning.ulms.scorm.model.NewScorm;
import com.ulearning.ulms.scorm.model.NewScormScoes;
import com.ulearning.ulms.util.LMSConstants;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class GetParam implements IHacpCommand
{
        protected static Log logger = LogFactory.getLog(GetParam.class);

        //properties
        HttpServletRequest req = null;
        HttpServletResponse resp = null;

        public GetParam(HttpServletRequest request, HttpServletResponse response)
        {
                req = request;
                resp = response;
        }

        public void execute() throws IOException, ServletException
        {
                String aicc_data = req.getParameter("aicc_data");

                if (aicc_data != null)
                {
                        logger.info("get Param :" + aicc_data);
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

                StringBuffer responseResult = new StringBuffer();
                responseResult.append("\n[Core]\n");
                responseResult.append("Student_ID=" +
                        cmiCore.getStudentId().getValue() + "\n");
                responseResult.append("Student_Name=" +
                        cmiCore.getStudentName().getValue() + "\n");
                responseResult.append("Lesson_Location=" +
                        cmiCore.getLessonLocation().getValue() + "\n");
                responseResult.append("Credit=" + cmiCore.getCredit().getValue() +
                        "\n");
                responseResult.append("Lesson_Status=" +
                        cmiCore.getLessonStatus().getValue() + "\n");
                responseResult.append("Score=" +
                        cmiCore.getScore().getRaw().getValue() + "\n");
                responseResult.append("Time=" + cmiCore.getTotalTime().getValue() +
                        "\n");
                responseResult.append("Lesson_Mode=" +
                        cmiCore.getLessonMode().getValue() + "\n");

                //todo:Suspend Data
                responseResult.append("[Core_Lesson]\n");

                //todo:Launch Data
                responseResult.append("[[Core Vendor]]\n");

                logger.info("responseResult = " + responseResult);
                HacpResponse.sendResult(req, resp, 0, responseResult.toString());
        }
}
