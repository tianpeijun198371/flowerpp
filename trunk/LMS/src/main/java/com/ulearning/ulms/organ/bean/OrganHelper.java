/**
 * OrganHelper
 * User: huangsb
 * Date: Apr 23, 2006
 * Time: 9:26:30 PM
 * Copyright (c) 2006-2007.Beijing WenhuaOnline Sci-tech DevelopmentCo.,Ltd.
 */
package com.ulearning.ulms.organ.bean;

import com.ulearning.ulms.organ.dao.OrganDAO;
import com.ulearning.ulms.organ.dao.OrganDAOFactory;
import com.ulearning.ulms.organ.exceptions.OrganDAOSysException;
import com.ulearning.ulms.organ.form.OrganForm;
import com.ulearning.ulms.organ.model.OrganModel;
import com.ulearning.ulms.util.log.DebugUtil;

import java.util.List;


public class OrganHelper
{
        /**
         * Wrapping the get plan method for JSP and  the other modules
         *
         * @param orgID
         * @return the admin modle according to the planID
         */
        public static OrganForm getOrgan(int orgID)
        {
                OrganForm of = null;

                try
                {
                        OrganDAO organDao = OrganDAOFactory.getDAO();
                        of = organDao.getOrgan(orgID);
                }
                catch (OrganDAOSysException odse)
                {
                        odse.printStackTrace();
                }

                return of;
        }

        public static boolean isExsistOrganUser(int orgID, int userID)
        {
                boolean ff = false;

                try
                {
                        OrganDAO organDao = OrganDAOFactory.getDAO();
                        ff = organDao.isExsistOrganUser(orgID, userID);
                }
                catch (OrganDAOSysException odse)
                {
                        odse.printStackTrace();
                }

                return ff;
        }

        /**
         * Wrapping the get plan method for JSP and  the other modules
         *
         * @param userID
         * @return the admin modle according to the planID
         */
        public static OrganForm getOrgan(String userID)
        {
                OrganForm of = null;

                try
                {
                        OrganDAO organDao = OrganDAOFactory.getDAO();
                        of = organDao.getOrgan(userID);
                }
                catch (OrganDAOSysException odse)
                {
                        odse.printStackTrace();
                }

                return of;
        }

        public static OrganModel getOrganByDomain(String domainName)
        {
                OrganModel om = null;

                try
                {
                        OrganDAO organDao = OrganDAOFactory.getDAO();
                        om = organDao.getOrganByDomain(domainName);
                }
                catch (OrganDAOSysException odse)
                {
                        odse.printStackTrace();
                }

                return om;
        }

        /**
         * Wrapping the get plan method for JSP and  the other modules
         *
         * @param orgID
         * @param userID
         * @return the admin modle according to the planID
         */
        public static OrganForm getOrgan(int orgID, int userID)
        {
                OrganForm of = null;

                try
                {
                        OrganDAO organDao = OrganDAOFactory.getDAO();
                        of = organDao.getOrgan(orgID, userID);
                }
                catch (OrganDAOSysException odse)
                {
                        odse.printStackTrace();
                }

                return of;
        }

        /**
         * Get orgID according to the orgNO
         *
         * @param orgNO
         * @return the prepared orgID ,default -1
         */
        public static int getOrgIDByCode(String orgNO)
        {
                int orgID = -1;

                try
                {
                        OrganDAO organDao = OrganDAOFactory.getDAO();
                        orgID = organDao.getOrgIDByCode(orgNO);
                }
                catch (OrganDAOSysException odse)
                {
                        odse.printStackTrace();
                }

                return orgID;
        }

        /**
         * Get orgID according to the orgNO
         *
         * @param orgNO
         * @return the prepared orgID ,default -1
         */
        public static OrganForm getOrganFormByOrgNo(String orgNO)
        {
                OrganForm of = null;

                try
                {
                        OrganDAO organDao = OrganDAOFactory.getDAO();
                        of = organDao.getOrganForm(orgNO);
                }
                catch (OrganDAOSysException odse)
                {
                        odse.printStackTrace();
                }

                return of;
        }

        /**
         * Get orgID according to the orgName
         *
         * @param orgName
         * @return the prepared orgID ,default -1
         */
        public static int getOrgID(String orgName)
        {
                int orgID = -1;

                try
                {
                        OrganDAO organDao = OrganDAOFactory.getDAO();
                        orgID = organDao.getOrgID(orgName);
                }
                catch (OrganDAOSysException odse)
                {
                        odse.printStackTrace();
                }

                return orgID;
        }

        public static void delJieFoOrgan(String orgID)
        {
                try
                {
                        OrganDAO dao = OrganDAOFactory.getDAO();
                        OrganForm oof = dao.getOrgan(orgID);

                        if (oof.getIsAsp() == 1)
                        {
                                dao.removeJieFoOrgan(orgID);
                        }
                }
                catch (OrganDAOSysException odse)
                {
                        odse.printStackTrace();
                }
        }

        /**
         * Get orgID according to the orgName
         *
         * @param parentID
         * @param orgName
         * @return the prepared orgID ,default -1
         */
        public static int getOrgID(int parentID, String orgName)
        {
                int orgID = -1;

                try
                {
                        OrganDAO organDao = OrganDAOFactory.getDAO();
                        orgID = organDao.getOrgID(parentID, orgName);
                }
                catch (OrganDAOSysException odse)
                {
                        odse.printStackTrace();
                }

                return orgID;
        }

        /**
         * Wrapping the get planList method for JSP and  the other modules
         *
         * @param parentID
         * @return the plan list according to the orgID
         */
        public static List getOrganList(int parentID)
        {
                List organList = null;

                try
                {
                        OrganDAO organDao = OrganDAOFactory.getDAO();
                        organList = organDao.getOrganList(parentID);
                }
                catch (OrganDAOSysException odse)
                {
                        odse.printStackTrace();
                }

                return organList;
        }

        /**
         * Wrapping the get planList method for JSP and  the other modules
         *
         * @param parentID
         * @return the plan list according to the orgID
         */
        public static List getOrganListBy(int parentID)
        {
                List organList = null;

                try
                {
                        OrganDAO organDao = OrganDAOFactory.getDAO();
                        organList = organDao.getOrganListBy(parentID);
                }
                catch (OrganDAOSysException odse)
                {
                        odse.printStackTrace();
                }

                return organList;
        }
        /**
         * Wrapping the get planList method for JSP and  the other modules
         *
         * @param parentID
         * @return the plan list according to the orgID
         */
        public static List getOrganListByCommend(int parentID)
        {
                List organList = null;

                try
                {
                        OrganDAO organDao = OrganDAOFactory.getDAO();
                        organList = organDao.getOrganListByCommend(parentID);
                }
                catch (OrganDAOSysException odse)
                {
                        odse.printStackTrace();
                }

                return organList;
        }

        /**
         * IsduplicateID
         *
         * @param list
         * @param orgID
         * @return
         */
        public static boolean isDuplicateID(List list, int orgID)
        {
                for (int i = 0; i < list.size(); i++)
                {
                        int id = ((Integer) list.get(i)).intValue();

                        if (id == orgID)
                        {
                                list.remove(list.get(i));
                        }
                }

                return true;
        }

        /**
         * Judge whether the id is duplicate
         *
         * @param list
         * @param id
         * @return true if the id is duplicated, false otherwise
         */
        public static boolean isDuplicate(List list, int id)
        {
                return list.contains(new Integer(id));
        }

        /**
         * isHasSubOrgan
         *
         * @param orgID
         * @return
         */
        public static boolean isHasSubOrgan(int orgID)
        {
                boolean isHasSubOrgan = false;

                try
                {
                        OrganDAO organDao = OrganDAOFactory.getDAO();
                        isHasSubOrgan = organDao.isHasSubOrgan(orgID);
                }
                catch (OrganDAOSysException odse)
                {
                        odse.printStackTrace();
                }

                return isHasSubOrgan;
        }

        public static List getOrganUserList(int orgID)
        {
                List userList = null;

                try
                {
                        OrganDAO organDao = OrganDAOFactory.getDAO();
                        userList = organDao.getOrganUserList(orgID);
                }
                catch (OrganDAOSysException odse)
                {
                        odse.printStackTrace();
                }

                return userList;
        }

        /**
         * @param orgID
         */
        public List getOrganPath(int orgID) throws OrganDAOSysException
        {
                List l;

                OrganDAO organDao = OrganDAOFactory.getDAO();
                l = organDao.getOrganPath(orgID);

                return l;
        }

        /**
         * Get the organ tree according to the specified orgID
         *
         * @param orgID
         * @return prepared tree,null default
         */
        public static List getOrganTree(int orgID)
        {
                List tree = null;

                try
                {
                        OrganDAO organDao = OrganDAOFactory.getDAO();
                        tree = organDao.getOrganTree(orgID);
                }
                catch (OrganDAOSysException odse)
                {
                        odse.printStackTrace();
                }

                return tree;
        }

        /**
         * 根据aspID得到下面所有的aspID的树形结构
         *
         * @param aspID
         * @return
         */
        public static List getAspTree(int aspID)
        {
                List tree = null;

                try
                {
                        OrganDAO organDao = OrganDAOFactory.getDAO();
                        tree = organDao.getAspTreeByAsp(aspID);
                }
                catch (OrganDAOSysException odse)
                {
                        odse.printStackTrace();
                }

                return tree;
        }

        /**
         * Get the organ tree according to the specified orgID
         *
         * @param orgID
         * @return prepared parentlist,null default
         */
        public static List getOrganParentList(int orgID)
        {
                List tree = null;

                try
                {
                        OrganDAO organDao = OrganDAOFactory.getDAO();
                        tree = organDao.getOrganParentList(orgID);
                }
                catch (OrganDAOSysException odse)
                {
                        odse.printStackTrace();
                }

                return tree;
        }

        /**
         * Get all the children from the current organID
         * //todo this method need refine
         *
         * @param orgID
         * @return prepared children
         */
        public static List getOrganChildList(int orgID)
        {
                List children = null;

                try
                {
                        OrganDAO organDao = OrganDAOFactory.getDAO();
                        children = organDao.getOrganChildList(orgID);
                }
                catch (OrganDAOSysException odse)
                {
                        odse.printStackTrace();
                }

                return children;
        }

        public static int getOrganID(String orgName, int parentID)
        {
                int orgID = 0;

                try
                {
                        OrganDAO organDao = OrganDAOFactory.getDAO();
                        orgID = organDao.getOrgID(parentID, orgName);
                }
                catch (OrganDAOSysException odse)
                {
                        odse.printStackTrace();
                }

                return orgID;
        }

        /**
         * Get organ ID from org name and parentID
         *
         * @param str
         * @param parentID
         * @return prepared orgID
         */
        public static int parseOrgan(String str, int parentID)
        {
                if ((str == null) || (str.trim().length() == 0))
                {
                        return parentID;
                }

                try
                {
                        str = str.trim();

                        int indexOrg = str.indexOf("/");

                        if ((indexOrg == -1) && (str.length() > 0))
                        {
                                return OrganHelper.getOrganID(str, parentID);
                        }
                        else
                        {
                                String parentOrgStr = str.substring(0, indexOrg);
                                int parID = OrganHelper.getOrgID(parentOrgStr);
                                String leftStr = str.substring(indexOrg + 1, str.length());

                                return parseOrgan(leftStr, parID);
                        }
                }
                catch (Exception ex)
                {
                        ex.printStackTrace();
                }

                return 0;
        }

        /**
         * Get organ ID from org string
         *
         * @param str
         * @return prepared orgID, default is 0
         */
        public static int getOrganID(String str)
        {
                if ((str == null) || (str.trim().length() == 0))
                {
                        return 0;
                }

                DebugUtil.print("[OrganHelper]getOrganID===========get str " + str);

                try
                {
                        str = str.trim();

                        int indexOrg = str.indexOf("/");

                        if ((indexOrg == -1) && (str.length() > 0))
                        {
                                return OrganHelper.getOrgID(str);
                        }
                        else
                        {
                                String parentOrgStr = str.substring(0, indexOrg);
                                int parentID = OrganHelper.getOrgID(parentOrgStr);
                                String leftStr = str.substring(indexOrg + 1, str.length());

                                return parseOrgan(leftStr, parentID);
                        }
                }
                catch (Exception ex)
                {
                        ex.printStackTrace();
                }

                return 0;
        }

        /**
         *  Gets ASPs from database according to area,include province,city and county
         * @param province the provice where the school is lie in,null means get all province
         * @param city  the city where the school is lie in,null means get all city
         * @param county  the county where the school is lie in,null means get all county
         * @return   prepared organ model list,otherwise is an empty List
         * @throws OrganDAOSysException
         */
        public static List getOrganByArea(String province,String city,String county){
                 List organList = null;
                try
                {
                        OrganDAO organDao = OrganDAOFactory.getDAO();
                        organList = organDao.getOrganByArea(province,city,county);
                }
                catch (OrganDAOSysException odse)
                {
                        odse.printStackTrace();
                }
                return organList;
        }

        /**
         * search organ by organForm
         * @param of     the of  where the organForm  is lie in ,null means get all organ
         * @return
         */
        public static List searchOrganForm(OrganForm of){
                 List organList = null;
                try
                {
                        OrganDAO organDao = OrganDAOFactory.getDAO();
                        organList = organDao.searchOrganForm(of);
                }
                catch (OrganDAOSysException odse)
                {
                        odse.printStackTrace();
                }
                return organList;
        }
}
