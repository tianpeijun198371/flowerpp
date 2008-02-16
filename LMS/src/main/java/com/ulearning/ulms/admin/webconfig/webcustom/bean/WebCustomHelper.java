/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-7-2
 * Time: 16:37:35
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.admin.webconfig.webcustom.bean;

import com.ulearning.ulms.admin.webconfig.webcustom.dao.WebCustomDAO;
import com.ulearning.ulms.admin.webconfig.webcustom.dao.WebCustomDAOFactory;
import com.ulearning.ulms.admin.webconfig.webcustom.exceptions.WebCustomDAOSysException;
import com.ulearning.ulms.admin.webconfig.webcustom.form.WebCustomForm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class WebCustomHelper
{
        /**
         * @param CustomID
         * @return
         */
        public static WebCustomForm getWebCustom(int CustomID)
        {
                WebCustomForm wcf = null;

                try
                {
                        WebCustomDAO webCustomDao = WebCustomDAOFactory.getDAO();
                        wcf = webCustomDao.getWebCustom(CustomID);
                }
                catch (WebCustomDAOSysException wcdse)
                {
                        wcdse.printStackTrace();
                }

                return wcf;
        }

        public void removeCustom(int CustomID)
        {
                try
                {
                        WebCustomDAO webCustomDao = WebCustomDAOFactory.getDAO();
                        webCustomDao.removeWebCustom(CustomID);
                }
                catch (WebCustomDAOSysException wcdse)
                {
                        wcdse.printStackTrace();
                }
        }

        public List getWebCustomList(int relationID, String relationTypeID)
        {
                List WebCustomFormList = new ArrayList();

                try
                {
                        WebCustomDAO webCustomDao = WebCustomDAOFactory.getDAO();
                        WebCustomFormList = webCustomDao.getWebCustomList(relationID,
                                relationTypeID);
                }
                catch (WebCustomDAOSysException wcdse)
                {
                        wcdse.printStackTrace();
                }

                return WebCustomFormList;
        }

        public List getWebCustomList(int relationID)
        {
                List WebCustomFormList = new ArrayList();

                try
                {
                        WebCustomDAO webCustomDao = WebCustomDAOFactory.getDAO();
                        WebCustomFormList = webCustomDao.getWebCustomList(relationID);
                }
                catch (WebCustomDAOSysException wcdse)
                {
                        wcdse.printStackTrace();
                }

                return WebCustomFormList;
        }

        public List getWebCustomList(String relationTypeID)
        {
                List WebCustomFormList = new ArrayList();

                try
                {
                        WebCustomDAO webCustomDao = WebCustomDAOFactory.getDAO();
                        WebCustomFormList = webCustomDao.getWebCustomList(relationTypeID);
                }
                catch (WebCustomDAOSysException wcdse)
                {
                        wcdse.printStackTrace();
                }

                return WebCustomFormList;
        }

        public WebCustomForm getLastWebCustom(int relationID, String relationTypeID)
        {
                WebCustomForm wcf = null;

                try
                {
                        WebCustomDAO webCustomDao = WebCustomDAOFactory.getDAO();
                        wcf = webCustomDao.getLastWebCustom(relationID, relationTypeID);
                }
                catch (WebCustomDAOSysException wcdse)
                {
                        wcdse.printStackTrace();
                }

                return wcf;
        }

        /**
         * @param webCustomForm
         * @return
         */
        public static int addWebCustom(WebCustomForm webCustomForm)
        {
                int CustomID = 0;

                try
                {
                        WebCustomDAO webCustomDao = WebCustomDAOFactory.getDAO();
                        CustomID = webCustomDao.addWebCustom(webCustomForm);
                }
                catch (WebCustomDAOSysException wcdse)
                {
                        wcdse.printStackTrace();
                }

                return CustomID;
        }

        /**
         * @param webCustomForm
         */
        public static void updateWebCustom(WebCustomForm webCustomForm)
        {
                try
                {
                        WebCustomDAO webCustomDao = WebCustomDAOFactory.getDAO();
                        webCustomDao.updateWebCustom(webCustomForm);
                }
                catch (WebCustomDAOSysException wcdse)
                {
                        wcdse.printStackTrace();
                }
        }

        public static HashMap getMyConfig(int userID, int orgID, int aspID)
        {
                HashMap properties = new HashMap();

                try
                {
                        WebCustomDAO webCustomDao = WebCustomDAOFactory.getDAO();
                        properties = webCustomDao.getMyConfig(userID, orgID, aspID);
                }
                catch (WebCustomDAOSysException wcdse)
                {
                        wcdse.printStackTrace();
                }

                return properties;
        }
}
