package com.ulearning.ulms.course.test.grade.action;

import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.course.test.grade.bean.PaperUserHelper;

import org.apache.struts.action.*;

import javax.servlet.http.*;


public class UpdateScoreAction extends Action
{
        public ActionForward execute(ActionMapping actionMapping,
                                     ActionForm actionForm, HttpServletRequest request,
                                     HttpServletResponse response) throws Exception
        {
                String resultScreen = "success";

                String paperID = request.getParameter("paperID");
                String[] userIDs = request.getParameterValues("userID");
                String[] updscore = request.getParameterValues("updscore");
                String[] Grade = request.getParameterValues("Grade");

                if (paperID == null)
                {
                        paperID = "0";
                }

                PaperUserHelper.updateScores(StringUtil.parseInt(paperID), userIDs,
                        Grade, updscore);

                return actionMapping.findForward(resultScreen);
        }
}
