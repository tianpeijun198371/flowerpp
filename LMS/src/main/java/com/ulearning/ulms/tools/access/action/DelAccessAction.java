/**
 * Created by IntelliJ IDEA.
 * Access: dengj
 * Date: Apr 14, 2004
 * Time: 12:05:08 PM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.tools.access.action;

import com.ulearning.ulms.tools.access.dao.AccessDAO;
import com.ulearning.ulms.tools.access.dao.AccessDAOFactory;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DelAccessAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";

                String[] accessIDs;

                ArrayList al = new ArrayList();
                accessIDs = request.getParameterValues("accessIDs");

                if (accessIDs != null)
                {
                        for (int i = 0; i < accessIDs.length; i++)
                        {
                                al.add(new Integer(accessIDs[i]));
                        }
                }

                AccessDAO dao = AccessDAOFactory.getDAO();
                dao.delete(al);

                return mapping.findForward(resultScreen);
        }
}
