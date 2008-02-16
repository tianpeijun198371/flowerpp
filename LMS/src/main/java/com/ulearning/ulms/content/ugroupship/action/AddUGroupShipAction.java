/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.content.ugroupship.action;

import com.ulearning.ulms.content.ugroupship.dao.UGroupShipDAO;
import com.ulearning.ulms.content.ugroupship.dao.UGroupShipDAOFactory;
import com.ulearning.ulms.content.ugroupship.form.UGroupShipForm;

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
public class AddUGroupShipAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                /*
             String userId = request.getParameter("userID");
             String groupName = request.getParameter("groupName");
             String perdUrl = request.getParameter("perdUrl");*/
                String resultScreen = "success";
                UGroupShipForm tf = (UGroupShipForm) form;

                //System.out.println(tf.getUserID());
                //System.out.println(tf.getGroupName());
                //System.out.println(tf.getPerdUrl());
                UGroupShipDAO dao = UGroupShipDAOFactory.getDAO();
                dao.insertUGroupShip(tf);

                /*
              System.out.println(userId);
              System.out.println(groupName);
              System.out.println(perdUrl);  */

                //UGroupShipForm tf = (UGroupShipForm) form;
                //UGroupShipDAO dao = UGroupShipDAOFactory.getDAO();
                //dao.insertUGroupShip(tf);
                //String path = request.getContextPath()+"content";

                //System.out.println(path);
                //return null;
                return mapping.findForward(resultScreen);

                // Forward to result page
                //return mapping.findForward(resultScreen);
        }
}
