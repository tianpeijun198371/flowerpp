/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.content.resuserecord.action;

import com.ulearning.ulms.content.resuserecord.dao.ResuserecordDAO;
import com.ulearning.ulms.content.resuserecord.dao.ResuserecordDAOFactory;
import com.ulearning.ulms.content.resuserecord.form.ResuserecordForm;
import com.ulearning.ulms.core.util.DateTimeUtil;
import com.ulearning.ulms.core.util.StringUtil;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Class description goes here.
 * <p/>
 * Created by Auto Code Produce System
 * User: xiejh
 * Date: 20060317
 * Time: 103906
 */
public class UpdateResuserecordAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                ResuserecordForm tf = (ResuserecordForm) form;
                String[] tmp = StringUtil.splitString(request.getParameter(
                        "userbegindate1"), "-");
                tf.setUserbegindate(DateTimeUtil.toDate(tmp[1], tmp[2].substring(0, 2),
                        tmp[0], "0", "0", "0"));

                //System.out.println(tf.getUserbegindate());
                String[] tmp2 = StringUtil.splitString(request.getParameter(
                        "userenddate1"), "-");
                tf.setUserenddate(DateTimeUtil.toDate(tmp2[1], tmp2[2].substring(0, 2),
                        tmp2[0], "0", "0", "0"));

                //System.out.println(tf.getUserenddate());
                ResuserecordDAO dao = ResuserecordDAOFactory.getDAO();
                dao.updateResuserecord(tf);

                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
