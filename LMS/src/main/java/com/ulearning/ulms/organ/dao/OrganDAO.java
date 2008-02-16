/**
 * OrganDAO
 * User: dengj
 * Date: Apr 14, 2006
 * Time: 5:16:18 PM
 * Copyright (c) 2006-2007.Beijing WenhuaOnline Sci-tech DevelopmentCo.,Ltd.
 */
package com.ulearning.ulms.organ.dao;

import com.ulearning.ulms.organ.exceptions.OrganDAOSysException;
import com.ulearning.ulms.organ.form.OrgUserForm;
import com.ulearning.ulms.organ.form.OrganForm;
import com.ulearning.ulms.organ.form.OrganJieFo;
import com.ulearning.ulms.organ.model.OrganModel;

import java.util.List;


public interface OrganDAO
{
        /**
         * Adds a Organ object to database
         *
         * @param details
         * @return the added object ID ,default is 0
         * @throws OrganDAOSysException
         */
        public int addOrgan(OrganForm details) throws OrganDAOSysException;

        public void addJieFoOrgan(OrganJieFo details) throws OrganDAOSysException;

        /**
         * Adds organ user to database,according to given orgID and userID
         *
         * @param orgID
         * @param userID
         * @throws OrganDAOSysException
         */
        public void addOrganUser(int orgID, int userID) throws OrganDAOSysException;

        /**
         * Adds organ user to database,according to given OrgUserForm
         *
         * @param ouf
         * @throws OrganDAOSysException
         */
        public void addOrganUser(OrgUserForm ouf) throws OrganDAOSysException;

        /**
         * Judges whether the userID is already exsist in the organID
         *
         * @param orgID
         * @param userID
         * @return true meaning the user is already exsist, otherwise is false
         * @throws OrganDAOSysException
         */
        public boolean isExsistOrganUser(int orgID, int userID)
                throws OrganDAOSysException;

        public void updateJieFoOrgan(OrganJieFo details)
                throws OrganDAOSysException;

        /**
         * Updates organ object according to the organ form
         *
         * @param organForm
         * @throws OrganDAOSysException
         */
        public void updateOrgan(OrganForm organForm) throws OrganDAOSysException;

        /**
         * Update user organ information according to he orgUserForm
         *
         * @param orgUserForm
         * @throws OrganDAOSysException
         */
        public void updateOrganUser(OrgUserForm orgUserForm)
                throws OrganDAOSysException;

        /**
         * Deletes organ from database
         *
         * @param userID
         * @throws OrganDAOSysException
         */
        public void removeOrgan(String userID) throws OrganDAOSysException;

        public void removeJieFoOrgan(String orgID) throws OrganDAOSysException;

        public void removeOrganUser(String userID) throws OrganDAOSysException;

        /**
         * Gets Organ object from database according to given orgID
         *
         * @param orgID
         * @return prepared objest ,default is null
         * @throws OrganDAOSysException
         */
        public OrganForm getOrgan(int orgID) throws OrganDAOSysException;

        /**
         * Get the user organ information from databse accoeding to userID
         *
         * @param userID
         * @return prepared organForm objest ,default is null
         * @throws OrganDAOSysException
         */
        public OrganForm getOrgan(String userID) throws OrganDAOSysException;

        public OrganForm getOrgan(int orgID, int userID)
                throws OrganDAOSysException;

        /**
         * Get organ IDentifier from database according to organ name
         *
         * @param name
         * @return the correct organ ID,default is -1
         * @throws OrganDAOSysException
         */
        public int getOrgID(String name) throws OrganDAOSysException;

        /**
         * Get organ IDentifier from database according to given organ name and parentID
         *
         * @param parentID
         * @param orgName
         * @return the correct organ ID,default is -1
         * @throws OrganDAOSysException
         */
        public int getOrgID(int parentID, String orgName)
                throws OrganDAOSysException;

        /**
         * Get organ IDentifier from database according to given organ code
         *
         * @param code
         * @return the correct organ ID,default is -1
         * @throws OrganDAOSysException
         */
        public int getOrgIDByCode(String code) throws OrganDAOSysException;

        public OrganForm getOrganForm(String orgNo) throws OrganDAOSysException;
        /**
         * Gets all the organ children from database according to given parentID
         *
         * @param parentID
         * @return prepared organ list<OrganForm> ,default is an empty Arraylist
         * @throws OrganDAOSysException
         */
        public List getOrganList(int parentID) throws OrganDAOSysException;

        public List getOrganListBy(int parentID) throws OrganDAOSysException;

        public List getOrganListByCommend(int parentID) throws OrganDAOSysException;

        /**
         * Gets all organ users according to given orgID
         *
         * @param orgID
         * @return prepared user list<UserForm> ,default is an empty Arraylist
         * @throws OrganDAOSysException
         */
        public List getOrganUserList(int orgID) throws OrganDAOSysException;

        /**
         * Gets the organ path according to orgID,for Example:an organ's parentID is 1, organ 1's parent is 7, and organ 7's
         * parentID is 3, So the organPath list should include "1,7,3" three organs
         *
         * @param orgID
         * @return prepared organ list<OrganForm> ,default is an empty Arraylist
         * @throws OrganDAOSysException
         */
        public List getOrganPath(int orgID) throws OrganDAOSysException;



        /**
         * Judges whether the organ has child organ
         *
         * @param orgID
         * @return true is meaning has child organ,otherwise is false
         * @throws OrganDAOSysException
         */
        public boolean isHasSubOrgan(int orgID) throws OrganDAOSysException;

        /**
         * Gets the organ tree(include all the children) from database according to orgaID
         *
         * @param orgID
         * @return prepared organ tree list<OrganForm> arranged by the organ layer,default is an empty Arraylist
         * @throws OrganDAOSysException
         */
        public List getOrganTree(int orgID) throws OrganDAOSysException;

        public List getOrganParentList(int orgID) throws OrganDAOSysException;

        /**
         * Gets all the organ children from database according to given organID
         *
         * @param orgID
         * @return prepared organ child list<OrganForm>order by orgID,default is an empty Arraylist
         * @throws OrganDAOSysException
         */
        public List getOrganChildList(int orgID) throws OrganDAOSysException;

        public List getUserAsp(int orgID) throws OrganDAOSysException;

        public List getOrganChild(int orgID) throws OrganDAOSysException;

        /**
         * Gets ASP(Application Services Provider) organ tree list from database according to aspID
         *
         * @param aspID
         * @return prepared asp's child organ tree list<OrganForm> arranged by the organ layer,default is an empty Arraylist
         * @throws OrganDAOSysException
         */
        public List getAspTreeByAsp(int aspID) throws OrganDAOSysException;

        public List getOrganChildbyOrgID(int orgID) throws OrganDAOSysException;

        public OrganModel getOrganByDomain(String domainName) throws OrganDAOSysException;

        /**
         *  Gets ASPs from database according to area,include province,city and county
         * @param province the provice where the school is lie in,null means get all province
         * @param city  the city where the school is lie in,null means get all city
         * @param county  the county where the school is lie in,null means get all county
         * @return   prepared organ model list,otherwise is an empty List
         * @throws OrganDAOSysException
         */
        public List getOrganByArea(String province,String city,String county) throws OrganDAOSysException;

        /**
         * search organ by organForm
         * @param of     the of  where the organForm  is lie in ,null means get all organ
         * @return
         */
        public List searchOrganForm(OrganForm of) throws OrganDAOSysException;
}
