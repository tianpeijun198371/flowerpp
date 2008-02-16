package com.ulearning.ulms.course.test.grade.action;

import com.ulearning.ulms.core.exceptions.ULMSAppException;
import com.ulearning.ulms.core.util.Config;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.course.test.paper.bean.PaperAnswerHelper;
import com.ulearning.ulms.course.test.testbase.PaperXML;
import com.ulearning.ulms.util.LMSConstants;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.struts.action.*;

import java.io.File;

import java.util.*;

import javax.servlet.ServletContext;
import javax.servlet.http.*;


public class AddCorrectAction extends Action
{
        public ActionForward execute(ActionMapping actionMapping,
                                     ActionForm actionForm, HttpServletRequest request,
                                     HttpServletResponse response) throws Exception
        {
                String resultScreen = "success";

                //PaperAnswerDAO dao = PaperAnswerDAOFactory.getDAO();
                //PaperAnswerForm paf = null;
                //String courseID = request.getParameter("courseID");
                String paperID = request.getParameter("paperID");

                if (paperID == null)
                {
                        paperID = "0";
                }

                LogUtil.info("yangds", "courseID");
                //������xml�ļ��������
                PaperAnswerHelper.bacthPaperAnswerXML(StringUtil.parseInt(paperID), true);

                //���Ĺ���xml�ļ����Ծ��ļ��е����ָ���
                String reName = Config.getUploadPhysicalPath() + "PaperXML" +
                        File.separator + paperID;
                File file = new File(reName);
                file.renameTo(new File(reName + "_old"));

                //������뻺����������paper����
                ServletContext sc = request.getSession().getServletContext();

                if (sc.getAttribute(paperID + "_exam") != null)
                {
                        sc.removeAttribute(paperID + "_exam");
                }

                if (sc.getAttribute(paperID) != null)
                {
                        sc.removeAttribute(paperID);
                }

                return actionMapping.findForward(resultScreen);
        }
}
