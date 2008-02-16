/*
 * Copyright (c) 2004 Your Corporation. All Rights Reserved.
 */
package com.ulearning.ulms.course.action;

import com.ulearning.ulms.course.dao.MasterDAO;
import com.ulearning.ulms.course.dao.MasterDAOFactory;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Created by IntelliJ IDEA.
 * DelMasterNormal.java .
 * <p/>
 * User: keyh * Date: 2004-9-19
 * Time: 17:00:22
 * <p/>
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
public class DelMasterNormalAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                String type = request.getParameter("type");
                String[] masterIDs;

                masterIDs = request.getParameterValues("masterIDs");

                MasterDAO dao = MasterDAOFactory.getDAO();
                List IDList = new ArrayList();

                for (int i = 0; i < masterIDs.length; i++)
                {
                        IDList.add(new Integer(Integer.parseInt(masterIDs[i])));
                }

                dao.deleteMaster(IDList);

                return mapping.findForward(resultScreen);
        }
}
