/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.test.Exambatch.action;

import com.ulearning.ulms.core.util.DateTimeUtil;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.course.test.Exambatch.dao.ExambatchDAO;
import com.ulearning.ulms.course.test.Exambatch.dao.ExambatchDAOFactory;
import com.ulearning.ulms.course.test.Exambatch.form.ExambatchForm;
import com.ulearning.ulms.course.test.paper.dao.PaperDAOImpl;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Class description goes here.
 * <p/>
 * 增加考场和修改考场的action
 * User: zhuyr
 * Date: 20051121
 * Time: 135243
 */
public class AddExambatchAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                System.out.println("ok");

                ExambatchForm tf = new ExambatchForm();
                tf = (ExambatchForm) form;

                if (!tf.getBeginTime().equals(""))
                {
                        String[] tmp = StringUtil.splitString(tf.getBeginTime(), "-");
                        tf.setExambegintime(DateTimeUtil.toDate(tmp[1], tmp[2], tmp[0],
                                tmp[3], tmp[4], "0"));

                        //System.out.println(tf.getExambegintime());
                }

                if (!tf.getLastTime().equals(""))
                {
                        String[] tmp = StringUtil.splitString(tf.getLastTime(), "-");
                        tf.setExamendtime(DateTimeUtil.toDate(tmp[1], tmp[2], tmp[0],
                                tmp[3], tmp[4], "0"));

                        //System.out.println(tf.getExamendtime());
                        //System.out.println(tf.getPaperID());
                }

                request.setAttribute("paperID", tf.getPaperID() + "");

                //System.out.println(tf.getExambegintime());
                ExambatchDAO dao = ExambatchDAOFactory.getDAO();

                PaperDAOImpl pstr = new PaperDAOImpl();

                //判断是否有考场ID有考场ID进行修改操作
                if (tf.getExambatchID() != 0)
                {
                        dao.updateExambatch(tf);
                }
                else
                {
                        dao.insertExambatch(tf);
                }

                //更改试卷时间范围
                pstr.updatePaper(tf.getPaperID(),
                        dao.getdatePaperTime(tf.getPaperID(), true),
                        dao.getdatePaperTime(tf.getPaperID(), false));

                //request.setAttribute("btime",dao.getdatePaperTime(tf.getPaperID(),true));
                //request.setAttribute("etime",dao.getdatePaperTime(tf.getPaperID(),false));
                // Forward to result page */
                return mapping.findForward(resultScreen);
        }
}
