/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-8-20
 * Time: 10:10:23
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.admin.colsignup.colsign.bean;

import com.ulearning.ulms.admin.colsignup.colsign.dao.ColSignDAO;
import com.ulearning.ulms.admin.colsignup.colsign.dao.ColSignDAOFactory;
import com.ulearning.ulms.admin.colsignup.colsign.exceptions.ColSignDAOSysException;
import com.ulearning.ulms.admin.colsignup.colsign.form.ColSignForm;
import com.ulearning.ulms.organ.bean.OrganHelper;
import com.ulearning.ulms.organ.form.OrganForm;

import java.util.ArrayList;
import java.util.List;


public class ColSignHelper
{
        /**
         * Wrapping the get book method for JSP and  the other modules
         *
         * @param ColSignID
         * @return the book modle according to the bookID
         */
        public static ColSignForm getSignForm(int ColSignID)
        {
                ColSignForm bf = null;

                try
                {
                        ColSignDAO Dao = ColSignDAOFactory.getDAO();
                        bf = Dao.getColSign(ColSignID);
                }
                catch (ColSignDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return bf;
        }

        /**
         * Wrapping the get bookList method for JSP and  the other modules
         *
         * @param orgID Published
         * @return the book list according to the catalog
         */
        public static List getColSignList(int orgID, int Published)
        {
                List list = null;

                try
                {
                        ColSignDAO Dao = ColSignDAOFactory.getDAO();
                        list = Dao.getColSignList(orgID, Published);
                }
                catch (ColSignDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return list;
        }

        /**
         * Wrapping the get bookList method for JSP and  the other modules
         *
         * @param orgID
         * @param Published
         * @return all the signup list from the organID
         */
        public static List getAllColSignList(int orgID, int Published)
        {
                List list = new ArrayList();

                try
                {
                        ColSignDAO Dao = ColSignDAOFactory.getDAO();

                        //Get the signup list from the sub organ
                        List orgList = OrganHelper.getOrganTree(orgID);

                        for (int i = 0; i < orgList.size(); i++)
                        {
                                int subOrgID = ((OrganForm) orgList.get(i)).getOrgID();
                                System.out.println("[ColSignHelper]=========== subOrgID= " +
                                        subOrgID);

                                if (orgID != subOrgID)
                                {
                                        List signupList = Dao.getColSignList(subOrgID, Published);

                                        for (int j = 0; j < signupList.size(); j++)
                                        {
                                                list.add(signupList.get(j));
                                        }
                                }
                        }
                }
                catch (ColSignDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return list;
        }

        /**
         * @param ColSignID
         * @param approved
         * @return
         */
        public static int totalNumInColSign(int ColSignID, int approved)
        {
                int total = 0;

                try
                {
                        ColSignDAO Dao = ColSignDAOFactory.getDAO();
                        total = Dao.totalNumInColSign(ColSignID, approved);
                }
                catch (ColSignDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return total;
        }

        /**
         * 返回报名单的交费人数。
         *
         * @param colSignID
         * @param feeState
         * @return
         */
        public static int totalNumInColSignByFee(int colSignID, int feeState)
        {
                int total = 0;

                try
                {
                        ColSignDAO Dao = ColSignDAOFactory.getDAO();
                        total = Dao.totalNumInColSignByFee(colSignID, feeState);
                }
                catch (ColSignDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return total;
        }

        public static void submitColSign(int ColSignID, String isSubmit)
        {
                try
                {
                        ColSignDAO Dao = ColSignDAOFactory.getDAO();
                        Dao.submitColSign(ColSignID, isSubmit);
                }
                catch (ColSignDAOSysException udse)
                {
                        udse.printStackTrace();
                }
        }

        public static void approvedColSign(int ColSignID, String Approved)
        {
                try
                {
                        ColSignDAO Dao = ColSignDAOFactory.getDAO();
                        Dao.approvedColSign(ColSignID, Approved);
                }
                catch (ColSignDAOSysException udse)
                {
                        udse.printStackTrace();
                }
        }

        public static int totalNumInOrg(int orgID, int approved)
        {
                int total = 0;

                try
                {
                        ColSignDAO Dao = ColSignDAOFactory.getDAO();
                        total = Dao.totalNumInOrg(orgID, approved);
                }
                catch (ColSignDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return total;
        }
}
