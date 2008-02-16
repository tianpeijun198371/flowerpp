/**
 * AddMatchClassAction.java.
 * User: zhangy Date: 2005-6-7 11:50:17
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.match.action;

import com.ulearning.ulms.match.dao.MatchDaoFactory;
import com.ulearning.ulms.match.dao.MatchModelDao;
import com.ulearning.ulms.match.dao.MatchModelDaoFactory;
import com.ulearning.ulms.match.form.MatchClassForm;
import com.ulearning.ulms.user.form.UserForm;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AddMatchClassAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                LogUtil.debug("course", "[addMatchAction]===========start");

                //System.out.println("radio[0]=" + request.getParameter("matchRadio"));
                //System.out.println("" + request.getParameter("methodRadio"));
                //System.out.println("" + request.getParameter("radioUse"));
                String radioUse = request.getParameter("radioUse");

                MatchClassForm mcf = new MatchClassForm();
                MatchModelDao mmd = MatchModelDaoFactory.getDAO();

                int matchid = Integer.parseInt(request.getParameter("matchRadio"));
                int catalogID = Integer.parseInt(request.getParameter("catalogID"));
                String type = request.getParameter("type");

                if (request.getParameter("methodRadio").equals("启用"))
                {
                        mmd.delMatchClassByModeleid(catalogID);
                        mcf.setMatchid(matchid);
                        mcf.setModeleid(catalogID);
                        mcf.setType(type);
                        mcf.setFrequenceid(Integer.parseInt(request.getParameter(
                                "frequenceID")));
                        mmd.addMatchClass(mcf);
                }
                else if (request.getParameter("methodRadio").equals("禁用"))
                {
                        mmd.delMatchClass(matchid, catalogID);
                }

                UserForm userForm = new UserForm();

                if ((radioUse != null) && radioUse.equals("立即执行"))
                {
                        List userFormList = MatchDaoFactory.getDAO()
                                .getMatchUsersFromDepart(Integer.parseInt(
                                        request.getParameter("orgID")), matchid);

                        for (int i = 0; i < userFormList.size(); i++)
                        {
                                userForm = (UserForm) userFormList.get(i);

                                //System.out.println("userForm==" + userForm);
                                //System.out.println("catalogID==" + catalogID);
                                //System.out.println(" Integer.parseInt(type)==" +  Integer.parseInt(type));
                                if ((userForm != null) &&
                                        mmd.isCoursUser(userForm, catalogID,
                                                Integer.parseInt(type)))
                                {
                                        // System.out.println("[AddMatchClassAction]addUser===========star");
                                        LogUtil.debug("system",
                                                "[AddMatchClassAction]addUser===========star");
                                        mmd.addCoursUser(userForm, catalogID, Integer.parseInt(type));
                                }
                        }
                }

                LogUtil.debug("course",
                        "[addMatchAction]===========resultScreen = " + resultScreen);

                return mapping.findForward(resultScreen);
        }
}
