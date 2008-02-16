/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-7-2
 * Time: 10:26:01
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.admin.webconfig.webcustom.dao;

import com.ulearning.ulms.admin.webconfig.webcustom.exceptions.WebCustomDAOSysException;
import com.ulearning.ulms.admin.webconfig.webcustom.form.WebCustomForm;

import java.util.HashMap;
import java.util.List;


public interface WebCustomDAO
{
        /**
         * @param details
         * @return
         * @throws WebCustomDAOSysException
         */
        public int addWebCustom(WebCustomForm details)
                throws WebCustomDAOSysException;

        /**
         * @param details
         * @throws WebCustomDAOSysException
         */
        public void updateWebCustom(WebCustomForm details)
                throws WebCustomDAOSysException;

        /**
         * @param relationID
         * @param relationTypeID
         * @return
         * @throws WebCustomDAOSysException
         */
        public List getWebCustomList(int relationID, String relationTypeID)
                throws WebCustomDAOSysException;

        /**
         * @param relationID
         * @param relationTypeID
         * @return
         * @throws WebCustomDAOSysException
         */
        public WebCustomForm getLastWebCustom(int relationID, String relationTypeID)
                throws WebCustomDAOSysException;

        /**
         * @param customID
         * @throws WebCustomDAOSysException
         */
        public void removeWebCustom(int customID) throws WebCustomDAOSysException;

        /**
         * @param CustomID
         * @return
         * @throws WebCustomDAOSysException
         */
        public WebCustomForm getWebCustom(int CustomID)
                throws WebCustomDAOSysException;

        public List getWebCustomList(String relationTypeID)
                throws WebCustomDAOSysException;

        public List getWebCustomList(int relationID)
                throws WebCustomDAOSysException;

        public HashMap getMyConfig(int userID, int orgID, int aspID)
                throws WebCustomDAOSysException;
}
