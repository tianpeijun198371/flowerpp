/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.content.ugroupship.action;

import com.ulearning.ulms.content.ugroupship.dao.UGroupShipDAO;
import com.ulearning.ulms.content.ugroupship.dao.UGroupShipDAOFactory;

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
public class DelUGroupShipAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";

                String G_Ship_ID = request.getParameter("g_ship_id");
                String[] id = new String[]{G_Ship_ID};
                UGroupShipDAO dao = UGroupShipDAOFactory.getDAO();

                for (int i = 0; i < id.length; i++)
                {
                        dao.deleteUGroupShip(Integer.parseInt(id[i]));
                }

                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
