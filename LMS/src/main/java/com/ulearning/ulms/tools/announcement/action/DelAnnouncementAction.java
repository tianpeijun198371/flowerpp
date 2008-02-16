/**
 * Created by IntelliJ IDEA.
 * Announcement: dengj
 * Date: Apr 14, 2004
 * Time: 12:05:08 PM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.tools.announcement.action;

import com.ulearning.ulms.tools.announcement.dao.AnnouncementDAO;
import com.ulearning.ulms.tools.announcement.dao.AnnouncementDAOFactory;

import org.apache.struts.action.*;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DelAnnouncementAction extends Action
{
        public DelAnnouncementAction()
        {
        }

        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                ArrayList al = new ArrayList();
                String[] announcementIDs = request.getParameterValues("announcementIDs");

                if (announcementIDs != null)
                {
                        for (int i = 0; i < announcementIDs.length; i++)
                        {
                                al.add(new Integer(announcementIDs[i]));
                        }
                }

                AnnouncementDAO dao = AnnouncementDAOFactory.getDAO();
                dao.delete(al);

                return mapping.findForward(resultScreen);
        }
}
