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

import java.util.Date;

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
public class AddResuserecordAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                ResuserecordForm tf = (ResuserecordForm) form;

                String[] tmp = StringUtil.splitString(request.getParameter(
                        "userbegindate1") + "-" + request.getParameter("selectb"),
                        "-");
                //tf.setUserbegindate(DateTimeUtil.toDate(tmp[1], tmp[2], tmp[0],"0", "0", "0"));
                System.out.println(tf.getUserbegindate());

                String[] tmp2 = StringUtil.splitString(request.getParameter(
                        "userenddate1") + "-" + request.getParameter("selecte"), "-");
                //tf.setUserenddate(DateTimeUtil.toDate(tmp2[1], tmp2[2], tmp2[0],"0", "0", "0"));
                System.out.println(tf.getUserenddate());

                ResuserecordDAO dao = ResuserecordDAOFactory.getDAO();
                String[] shijianz = request.getParameterValues("shijianz");
                String bdate = "";
                String edate = "";
                long todate = DateTimeUtil.toDate(tmp2[1], tmp2[2], tmp2[0], "00", "0",
                        "0").getTime() -
                        DateTimeUtil.toDate(tmp[1], tmp[2], tmp[0], "00", "00", "0")
                                .getTime();

                int day = (int) (todate / 86400000);
                day = day + 1;
                System.out.println("==============================================" +
                        day);
                System.out.println("==============================================" +
                        shijianz.length);

                for (int k = 0; k < day; k++)
                {
                        for (int i = 0; i < shijianz.length; i++)
                        {
                                Date now = DateTimeUtil.toDate(tmp[1], tmp[2], tmp[0], "00",
                                        "00", "0");
                                String[] tmp3 = StringUtil.splitString(DateTimeUtil.addDateTime(
                                        now, "D", k).substring(0, 10), "-");

                                if (shijianz[i].equals("1"))
                                {
                                        //for (int j = 0; j < day; j++)
                                        //{
                                        tf.setUserbegindate(DateTimeUtil.toDate(tmp3[1], tmp3[2],
                                                tmp3[0], "00", "00", "0"));
                                        tf.setUserenddate(DateTimeUtil.toDate(tmp3[1], tmp3[2],
                                                tmp3[0], "11", "59", "0"));
                                        dao.insertResuserecord(tf);

                                        //}
                                }
                                else if (shijianz[i].equals("2"))
                                {
                                        //for (int j = 0; j < day; j++)
                                        //{
                                        tf.setUserbegindate(DateTimeUtil.toDate(tmp3[1], tmp3[2],
                                                tmp3[0], "12", "00", "0"));
                                        tf.setUserenddate(DateTimeUtil.toDate(tmp3[1], tmp3[2],
                                                tmp3[0], "14", "59", "0"));
                                        dao.insertResuserecord(tf);

                                        //}
                                }
                                else if (shijianz[i].equals("3"))
                                {
                                        //for (int j = 0; j < day; j++)
                                        //{
                                        tf.setUserbegindate(DateTimeUtil.toDate(tmp3[1], tmp3[2],
                                                tmp3[0], "15", "00", "0"));
                                        tf.setUserenddate(DateTimeUtil.toDate(tmp3[1], tmp3[2],
                                                tmp3[0], "23", "59", "0"));
                                        dao.insertResuserecord(tf);

                                        //}
                                }
                        }
                }

                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
