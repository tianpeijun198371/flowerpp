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
                //批量读xml文件存入库中
                PaperAnswerHelper.bacthPaperAnswerXML(StringUtil.parseInt(paperID), true);

                //批改过的xml文件把试卷文件夹的名字更新
                String reName = Config.getUploadPhysicalPath() + "PaperXML" +
                        File.separator + paperID;
                File file = new File(reName);
                file.renameTo(new File(reName + "_old"));

                //清除存入缓存的试题对象paper数组
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
