/**
 * OrganDAOImpl
 * User: dengj
 * Date: Apr 14, 2006
 * Time: 5:17:00 PM
 * Copyright (c) 2006-2007.Beijing WenhuaOnline Sci-tech DevelopmentCo.,Ltd.
 */
package com.ulearning.ulms.organ.dao;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.core.security.bean.SecurityConstants;
import com.ulearning.ulms.core.security.form.RoleForm;
import com.ulearning.ulms.core.security.form.UserRoleForm;
import com.ulearning.ulms.organ.bean.OrganHelper;
import com.ulearning.ulms.organ.exceptions.OrganDAOSysException;
import com.ulearning.ulms.organ.form.OrgUserForm;
import com.ulearning.ulms.organ.form.OrganForm;
import com.ulearning.ulms.organ.form.OrganJieFo;
import com.ulearning.ulms.organ.model.OrganModel;
import com.ulearning.ulms.user.form.UserForm;
import com.ulearning.ulms.util.DBUtil;
import com.ulearning.ulms.util.HibernateDAO;
import com.ulearning.ulms.util.log.DebugUtil;
import com.ulearning.ulms.util.log.LogUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Date;

import javax.sql.DataSource;


public class OrganDAOImpl implements OrganDAO
{
        protected transient Connection dbConnection = null;
        protected transient DataSource datasource = null;
        private List orgTree = new ArrayList();
        private List organParentList = new ArrayList();
        private List organChildList = new ArrayList();
        private List tmpList = new ArrayList();
        private List parenttmpList = new ArrayList();
        private List userAsp = new ArrayList();
        private List childList = new ArrayList();
        private List aspTree = new ArrayList();
        private int orgDepth = 0;

        public int addOrgan(OrganForm details) throws OrganDAOSysException
        {
                int orgID = 0;

                try
                {       details.setRegDate(new Date(System.currentTimeMillis()));
                        String orgIDs = HibernateDAO.add(details.getOrganModel()).toString();
                        if (Integer.parseInt(details.getOrganModel().getIsasp()) == 1)
                        {
                                OrganForm of = new OrganForm();
                                of.setOrgID(Integer.parseInt(orgIDs));
                                of.setParentID(details.getParentID());
                                of.setAspID(Integer.parseInt(orgIDs));
                                of.setLayer(details.getLayer());
                                of.setOrgType(details.getOrgType());
                                of.setOrgName(details.getOrgName());
                                of.setOrgStatus(details.getOrgStatus());
                                of.setForGuest(details.getForGuest());
                                of.setOrgNO(details.getOrgNO());
                                of.setIsAsp(details.getIsAsp());
                                of.setDescription(details.getDescription());
                                of.setProvince(details.getProvince());
                                of.setCity(details.getCity());
                                of.setCounty(details.getCounty());
                                of.setMember(details.getMember());
                                of.setDnsName(details.getDnsName());
                                of.setRemark1(details.getRemark1());
                                of.setRemark2(details.getRemark2());
                                of.setRemark3(details.getRemark3());
                                of.setRemark4(details.getRemark4());
                                of.setRemark5(details.getRemark5());
                                of.setStartDate(details.getStartDate());
                                of.setEndDate(details.getEndDate());
                                of.setSchoolID(details.getSchoolID());
                                of.setLogo(details.getLogo());
                                of.setRegDate(details.getRegDate());
                                of.setIsCheck(details.getIsCheck());
                                HibernateDAO.update(of.getOrganModel());
                        }
                        else
                        {
                                OrganForm off = OrganHelper.getOrgan(details.getParentID());
                                //LogUtil.info("system", "[OrganDAOOracle]====================the sql is Delete Organ : " + off.getOrgName());
                                //LogUtil.debug("system", "[OrganDAOOracle]====================the sql is Delete Organ : " + off.getOrgName());
                                OrganForm of = new OrganForm();
                                of.setOrgID(Integer.parseInt(orgIDs));
                                of.setParentID(details.getParentID());
                                of.setAspID(off.getAspID());
                                of.setLayer(details.getLayer());
                                of.setOrgType(details.getOrgType());
                                of.setOrgName(details.getOrgName());
                                of.setOrgStatus(details.getOrgStatus());
                                of.setForGuest(details.getForGuest());
                                of.setOrgNO(details.getOrgNO());
                                of.setIsAsp(details.getIsAsp());
                                of.setDescription(details.getDescription());
                                of.setProvince(details.getProvince());
                                of.setCity(details.getCity());
                                of.setCounty(details.getCounty());
                                of.setMember(details.getMember());
                                of.setDnsName(details.getDnsName());
                                of.setRemark1(details.getRemark1());
                                of.setRemark2(details.getRemark2());
                                of.setRemark3(details.getRemark3());
                                of.setRemark4(details.getRemark4());
                                of.setRemark5(details.getRemark5());
                                of.setStartDate(details.getStartDate());
                                of.setEndDate(details.getEndDate());
                                of.setSchoolID(details.getSchoolID());
                                of.setLogo(details.getLogo());
                                of.setRegDate(details.getRegDate());
                                of.setIsCheck(details.getIsCheck());                                
                                HibernateDAO.update(of.getOrganModel());
                        }
                        orgID = Integer.parseInt(orgIDs);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new OrganDAOSysException(
                                "updateOrgan(OrganForm details) method" + e);
                }

                return orgID;
        }

        public void addJieFoOrgan(OrganJieFo details) throws OrganDAOSysException
        {
                Statement stmt = null;
                String sqlStr = "insert into organ(OrganID,OrganName,MasterID,OrganBrief) " +
                        "values (" + details.getOrganID() + ",'" + details.getOrganName() +
                        "'," + details.getMasterID() + ",'" + details.getOrganBrief() +
                        "')";
                LogUtil.debug("user",
                        "[OrganDAOImpl]====================the sql string is : " + sqlStr);

                try
                {
                        dbConnection = getJieFoConnection();
                        stmt = dbConnection.createStatement();
                        stmt.execute(sqlStr);
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new OrganDAOSysException(
                                "SQLException while addJieFoOrgan(OrganJieFo details) " +
                                        "Organ; Serial = " + details.getOrganName() + " :\n" + se);
                }
                catch (ULMSSysException se)
                {
                        se.printStackTrace();
                        throw new OrganDAOSysException(
                                "SQLException while addJieFoOrgan(OrganJieFo details) " +
                                        "Organ; Serial = " + details.getOrganName() + " :\n" + se);
                }
                finally
                {
                        closeStatement(stmt);
                        closeConnection();
                }
        }

        public void updateJieFoOrgan(OrganJieFo details)
                throws OrganDAOSysException
        {
                Statement stmt = null;
                String sqlStr = "update  organ set " + "OrganName= '" +
                        details.getOrganName() + "'," + "OrganBrief= '" +
                        details.getOrganBrief() + "'," + "MasterID = " +
                        details.getMasterID() + " where OrganID =" + details.getOrganID();
                LogUtil.debug("user",
                        "[OrganDAOImpl]====================the sql string is : " + sqlStr);

                try
                {
                        dbConnection = getJieFoConnection();
                        stmt = dbConnection.createStatement();
                        stmt.execute(sqlStr);
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new OrganDAOSysException(
                                "SQLException while updateJieFoOrgan(OrganJieFo details) " +
                                        "Organ; Serial = " + details.getOrganName() + " :\n" + se);
                }
                catch (ULMSSysException se)
                {
                        se.printStackTrace();
                        throw new OrganDAOSysException(
                                "SQLException while updateJieFoOrgan(OrganJieFo details) " +
                                        "Organ; Serial = " + details.getOrganName() + " :\n" + se);
                }
                finally
                {
                        closeStatement(stmt);
                        closeConnection();
                }
        }

        public void updateOrgan(OrganForm details) throws OrganDAOSysException
        {
                try
                {
                        HibernateDAO.update(details.getOrganModel());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new OrganDAOSysException(
                                "updateOrgan(OrganForm details) method" + e);
                }
        }

        public void updateOrganUser(OrgUserForm orgUserForm)
                throws OrganDAOSysException
        {
                Statement stmt = null;
                String sqlStr = "update  TM_OrgUser_Tab set " + "OrgID= " +
                        orgUserForm.getOrgID() + "  where UserID =" +
                        orgUserForm.getUserID();
                LogUtil.debug("user",
                        "[OrganDAOImpl]====================the sql string is : " + sqlStr);

                try
                {
                        dbConnection = getConnection();
                        stmt = dbConnection.createStatement();
                        stmt.execute(sqlStr);
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new OrganDAOSysException(
                                "SQLException while updateOrganUser(OrgUserForm ouf) " +
                                        "Organ; Serial = " + orgUserForm.getOrgID() + " :\n" + se);
                }
                catch (ULMSSysException se)
                {
                        se.printStackTrace();
                        throw new OrganDAOSysException(
                                "SQLException while updateOrganUser(OrgUserForm ouf) " +
                                        "Organ; Serial = " + orgUserForm.getOrgID() + " :\n" + se);
                }
                finally
                {
                        closeStatement(stmt);
                        closeConnection();
                }
        }

        public void addOrganUser(int orgID, int userID) throws OrganDAOSysException
        {
        }

        public void addOrganUser(OrgUserForm ouf) throws OrganDAOSysException
        {
                try
                {
                        HibernateDAO.add(ouf.getOrganUserModel());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new OrganDAOSysException(
                                "addOrganUser(OrgUserForm ouf) method" + e);
                }
        }

        /**
         * Judges whether the userID is already exsist in the organID
         *
         * @param orgID
         * @param userID
         * @return true meaning the user is already exsist, otherwise is false
         * @throws OrganDAOSysException
         */
        public boolean isExsistOrganUser(int orgID, int userID)
                throws OrganDAOSysException
        {
                boolean isExsist = false;
                String hql = "select count (*)  from  OrganUserModel  oum where  oum.comp_id.orgid = " +
                        orgID + " and oum.comp_id.userid= " + userID;

                try
                {
                        int user = HibernateDAO.count(hql);

                        if (user > 0)
                        {
                                isExsist = true;
                        }
                }
                catch (ULMSSysException se)
                {
                        throw new OrganDAOSysException(
                                "SQLException while getUserEmail(String userID) " +
                                        "account; Serial = " + userID + " :\n" + se);
                }

                return isExsist;
        }

        //delete Jiefo organ
        public void removeJieFoOrgan(String orgID) throws OrganDAOSysException
        {
                Statement stmt = null;
                String sqlStr = "delete from organ where OrganID = " + orgID;

                try
                {
                        dbConnection = getJieFoConnection();
                        stmt = dbConnection.createStatement();
                        LogUtil.info("system",
                                "[OrganDAOOracle]====================the sql string is : " +
                                        sqlStr);
                        stmt.execute(sqlStr);
                }
                catch (SQLException se)
                {
                        throw new OrganDAOSysException(
                                "SQLException while deleting removeJieFoOrgan " +
                                        "account; Serial = " + orgID + " :\n" + se);
                }
                finally
                {
                        closeStatement(stmt);
                        closeConnection();
                }
        }

        public void removeOrgan(String orgID) throws OrganDAOSysException
        {
                Statement stmt = null;
                ResultSet rs = null;

                try
                {
                        dbConnection = getConnection();
                        stmt = dbConnection.createStatement();

                        // String sqlStr = "delete TM_Org_Tab where orgID = " + orgID;
                        String hql = " from OrganModel where orgid =" + orgID;

                        HibernateDAO.delete(hql);

                        //Delete all the user in this organ
                        String sqlStr = "update U_User_Tab set CatalogID = 0 where CatalogID = " +
                                orgID;
                        LogUtil.debug("system",
                                "[OrganDAOImpl]====================the sql is Delete Organ : " +
                                        sqlStr);
                        stmt.execute(sqlStr);
                }
                catch (ULMSSysException se)
                {
                        throw new OrganDAOSysException(
                                "SQLException while removeOrgan(String orgID) " +
                                        "organ; Serial = " + orgID + " :\n" + se);
                }
                catch (SQLException se)
                {
                        throw new OrganDAOSysException(
                                "SQLException while removeOrgan(String orgID) " +
                                        "organ; Serial = " + orgID + " :\n" + se);
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection();
                }
        }

        /**
         * Remove the plan from database by the userID
         *
         * @param userID
         * @throws OrganDAOSysException
         */
        public void removeOrganUser(String userID) throws OrganDAOSysException
        {
                String hql = "from OrganUserModel oum  where  oum.comp_id.userid = " +
                        userID;

                try
                {
                        HibernateDAO.delete(hql);
                        LogUtil.info("system",
                                "[OrganDAOOracle==============the SQL String]" + hql);
                }
                catch (ULMSSysException se)
                {
                        throw new OrganDAOSysException(
                                "SQLException while removeOrganUser(String userID)  " +
                                        "OrganUser;" + " Serial=" + userID + ":\n" + se);
                }
        }

        public OrganForm getOrgan(String userID) throws OrganDAOSysException
        {
                List tmList = null;
                OrganForm at = null;
                String hql = " from OrganModel  om ,OrganUserModel  oum  " +
                        " where om.orgid = oum.comp_id.orgid and oum.comp_id.userid=" +
                        userID;

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new OrganDAOSysException("" + e);
                }

                if ((tmList != null) && (tmList.size() > 0))
                {
                        Object[] record = null;
                        OrganModel am = new OrganModel();

                        for (Iterator iter = tmList.iterator(); iter.hasNext();)
                        {
                                record = (Object[]) iter.next();
                                am = (OrganModel) record[0];
                                at = new OrganForm(am);
                        }
                }

                return at;
        }

        public OrganForm getOrgan(int orgID, int userID)
                throws OrganDAOSysException
        {
                List tmList = null;
                OrganForm at = null;
                String hql = "";

                if (orgID == 0)
                {
                        // sqlStr = "select * from TM_ORG_TAB a,U_user_tab b where a.orgID =b.catalogID and b.UserID=" + userID;
                        hql = " from OrganModel  as om , UserModel  as um  " +
                                "where om.orgid = um.catalogid and um.userid =" + userID;
                }
                else
                {
                        //sqlStr = "select  * from TM_ORG_TAB a,TM_ORGUSER_TAB b where a.orgID =b.orgID" +
                        //       " and b.UserID= " + userID + " and b.orgID=" + orgID;
                        hql = " from OrganModel as om , OrganUserModel as oum " +
                                " where om.orgid = oum.comp_id.orgid and oum.comp_id.userid = " +
                                userID + " and oum.comp_id.orgid = " + orgID;
                }

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new OrganDAOSysException("" + e);
                }

                if ((tmList != null) && (tmList.size() > 0))
                {
                        Object[] record = null;
                        OrganModel am = new OrganModel();

                        for (Iterator iter = tmList.iterator(); iter.hasNext();)
                        {
                                record = (Object[]) iter.next();
                                am = (OrganModel) record[0];
                                at = new OrganForm(am);
                        }
                }

                return at;
        }

        public int getOrgID(String orgName) throws OrganDAOSysException
        {
                int orgID = -1;
                String hsql = " from OrganModel om where om.orgname = '" + orgName +
                        "'";

                try
                {
                        List organs = HibernateDAO.find(hsql);

                        if ((organs != null) && (organs.size() > 0))
                        {
                                orgID = ((OrganModel) organs.get(0)).getOrgid();

                                if (organs.size() > 1)
                                {
                                        DebugUtil.print(
                                                "[OrganDAOImpl]getOrgID Warning ===== the orgName is duplicat!");
                                }
                        }
                }
                catch (ULMSSysException se)
                {
                        throw new OrganDAOSysException(
                                "SQLException while getUserEmail(String userID) " +
                                        "account; Serial = " + orgName + " :\n" + se);
                }

                return orgID;
        }

        public int getOrgID(int parentID, String orgName)
                throws OrganDAOSysException
        {
                int orgID = -1;
                List tmList = null;
                String hql = " from OrganModel  where parentid = " + parentID + " " +
                        " and  orgname = '" + orgName + "'";

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new OrganDAOSysException("" + e);
                }

                if ((tmList != null) && (tmList.size() > 0))
                {
                        for (int i = 0; i < tmList.size(); i++)
                        {
                                orgID = ((OrganModel) tmList.get(i)).getOrgid();
                        }
                }

                return orgID;
        }

        public int getOrgIDByCode(String code) throws OrganDAOSysException
        {
                int orgID = -1;
                List tmList = null;
                String hql = " from OrganModel  where orgno = '" + code + "'";

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new OrganDAOSysException("" + e);
                }

                if ((tmList != null) && (tmList.size() > 0))
                {
                        for (int i = 0; i < tmList.size(); i++)
                        {
                                orgID = ((OrganModel) tmList.get(i)).getOrgid();
                        }
                }
                else
                {
                        orgID = getOrgID(code);
                }

                return orgID;
        }

        public OrganForm getOrganForm(String orgNo) throws OrganDAOSysException
        {
                List tmList = null;
                OrganModel om=null;
                OrganForm of=null;
                String hql = " from OrganModel  where orgno = '" + orgNo + "'";

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new OrganDAOSysException("" + e);
                }

                if ((tmList != null) && (tmList.size() > 0))
                {
                        for (int i = 0; i < tmList.size(); i++)
                        {
                                om = (OrganModel) tmList.get(i);
                                of = new OrganForm(om);
                        }
                }
                return of;
        }


        public OrganForm getOrgan(int orgID) throws OrganDAOSysException
        {
                OrganForm of = null;

                try
                {
                        OrganModel om = null;
                        Object oj = HibernateDAO.load(OrganModel.class, new Integer(orgID));

                        if (oj != null)
                        {
                                om = (OrganModel) oj;
                        }

                        of = new OrganForm(om);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new OrganDAOSysException(
                                "ULMSSysException whilegetOrgan(int orgID)  method" + e);
                }

                return of;
        }

        /**
         * @param parentID
         * @return
         * @throws OrganDAOSysException
         */
        public List getOrganList(int parentID) throws OrganDAOSysException
        {
                List orgList = getOrganListJDBC(parentID);

                //用JDBC的方式替换Hibernate
                /*List orgList = new ArrayList();
                String hql = "from OrganModel where  orgid > = 1 and parentid = " + parentID;
                try {
                        list = HibernateDAO.find(hql);
                        OrganModel um = null;
                        if (list != null) {
                                for (int i = 0; i < list.size(); i++) {
                                        um = (OrganModel) list.get(i);
                                        OrganForm of = new OrganForm(um);
                                        orgList.add(of);
                                }
                        }
                } catch (ULMSSysException e) {
                        e.printStackTrace();
                        throw new OrganDAOSysException("HibernateLException while getOrganList(int ogID)" + e);
                }*/
                return orgList;
        }

        public List getOrganListJDBC(int parentID) throws OrganDAOSysException
        {
                Statement stmt = null;
                ResultSet rs = null;
                List orgList = new ArrayList();
                OrganForm organForm = new OrganForm();
                String hql = "select * from  TM_Org_Tab where  orgid >= 1 and parentid = " +
                        parentID+" order by regDate desc";

                try
                {
                        dbConnection = getConnection();
                        stmt = dbConnection.createStatement();
                        rs = stmt.executeQuery(hql);

                        while (rs.next())
                        {
                                organForm = convertRs2Form(rs);
                                orgList.add(organForm);
                        }
                }
                catch (SQLException e)
                {
                        e.printStackTrace();
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection();
                }

                return orgList;
        }

        public List getOrganChildbyOrgID(int orgID) throws OrganDAOSysException
        {
                List child = getOrganList(orgID);

                for (int i = 0; i < child.size(); i++)
                {
                        OrganForm of = (OrganForm) child.get(i);
                        int subOrgID = of.getOrgID();
                        childList.add(of);

                        if (isHasSubOrgan(subOrgID))
                        {
                                getOrganChildbyOrgID(subOrgID);
                        }
                }

                return childList;
        }

        public OrganModel getOrganByDomain(String domainName) throws OrganDAOSysException
        {
                OrganModel organ = null;
                String hsql = " from OrganModel om where om.dnsname = '" + domainName + "'";

                try
                {
                        List organs = HibernateDAO.find(hsql);

                        if ((organs != null) && (organs.size() > 0))
                        {
                                organ = (OrganModel) organs.get(0);

                                if (organs.size() > 1)
                                {
                                        DebugUtil.print(
                                                "[OrganDAOImpl]getOrgID Warning ===== the orgName is duplicat!");
                                }
                        }
                }
                catch (ULMSSysException se)
                {
                        throw new OrganDAOSysException(
                                "SQLException while getOrganByDomain(String userID) " +
                                        "account; Serial = " + organ.getDnsname() + " :\n" + se);
                }
                return organ;
        }

        public List getOrganByArea(String province,String city,String county) throws OrganDAOSysException
        {
                List list = new ArrayList();
                String hql = "from OrganModel where 1=1";
                if ((province != null)&&(province.trim().length() > 0)){
                    hql = hql + " and province='" + province + "'";
                }
                if ((city != null)&&(city.trim().length() > 0)){
                    hql = hql + " and city='" + city + "'";
                }
                if ((county != null)&&(county.trim().length() > 0)){
                    hql = hql + " and county='" + county + "'";
                }
                hql+=" order by orgid desc";
                try
                {
                        list = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new OrganDAOSysException(
                                "HibernateLException while getOrganList(int ogID)" + e);
                }
                return list;
        }

        /**
         * search organ by organForm
         * @param of     the of  where the organForm  is lie in ,null means get all organ
         * @return
         */
        public List searchOrganForm(OrganForm of) throws OrganDAOSysException
        {
                List list = new ArrayList();
                List organList = new ArrayList();
                String hql = "from OrganModel where 1=1";
                if ((of.getOrgName() != null)&&(of.getOrgName().trim().length() > 0)){
                    hql = hql + " and orgname like '%" + of.getOrgName() + "%'";
                }
                if ((of.getOrgNO() != null)&&(of.getOrgNO().trim().length() > 0)){
                    hql = hql + " and orgno='" + of.getOrgNO() + "'";
                }
                hql+=" order by orgid desc";
                System.out.println("=============hql =================== " + hql);
                try
                {
                        list = HibernateDAO.find(hql);
                        OrganModel um = null;
                        if (list != null)
                        {
                                for (int i = 0; i < list.size(); i++)
                                {
                                        um = (OrganModel) list.get(i);
                                        OrganForm oof = new OrganForm(um);
                                        organList.add(oof);
                                }
                        }
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new OrganDAOSysException(
                                "HibernateLException while getOrganList(int ogID)" + e);
                }
                return organList;
        }    


        /**
         * @param parentID
         * @return
         * @throws OrganDAOSysException
         */
        public List getOrganListBy(int parentID) throws OrganDAOSysException
        {
                List list = new ArrayList();
                List orgList = new ArrayList();
                String hql = "from OrganModel where  isasp = '1' and orgid > = 1  and parentid = " +
                        parentID+" order by regDate desc";

                try
                {
                        list = HibernateDAO.find(hql);

                        OrganModel um = null;

                        if (list != null)
                        {
                                for (int i = 0; i < list.size(); i++)
                                {
                                        um = (OrganModel) list.get(i);

                                        OrganForm of = new OrganForm(um);
                                        orgList.add(of);
                                }
                        }
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new OrganDAOSysException(
                                "HibernateLException while getOrganList(int ogID)" + e);
                }

                return orgList;
        }

        public List getOrganListByCommend(int parentID) throws OrganDAOSysException
        {
                List list = new ArrayList();
                List orgList = new ArrayList();
                String hql = "from OrganModel where  isasp = '1' and orgid > = 1 and forguest=1 and parentid = " +
                        parentID+" order by regDate desc";
                try
                {
                        list = HibernateDAO.find(hql);

                        OrganModel um = null;

                        if (list != null)
                        {
                                for (int i = 0; i < list.size(); i++)
                                {
                                        um = (OrganModel) list.get(i);

                                        OrganForm of = new OrganForm(um);
                                        orgList.add(of);
                                }
                        }
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new OrganDAOSysException(
                                "HibernateLException while getOrganList(int ogID)" + e);
                }

                return orgList;
        }

        /**
         * @param orgID
         * @return
         * @throws OrganDAOSysException
         */
        public List getOrganUserList(int orgID) throws OrganDAOSysException
        {
                Statement stmt = null;
                Statement stmtRole = null;
                ResultSet rs = null;
                ResultSet rsRole = null;
                List userRoleList = new ArrayList();
                String sqlStr = "SELECT s.UserID,u.LoginName,u.Mail,u.CatalogID,u.Available,u.name,u.sex,u.postalcode,u.address,u.phone" +
                        " FROM Sec_UserRole_Tab s,U_User_Tab u WHERE relationID = " +
                        orgID + " AND type = " + SecurityConstants.USER_ORGAN_RELATION +
                        " AND s.UserID = u.UserID ORDER by s.roleID, s.userID";

                String sqlStrRole = "";

                try
                {
                        dbConnection = getConnection();
                        stmt = dbConnection.createStatement();
                        LogUtil.debug("user",
                                "[OrganDAOImpl]====================the sql string is : " +
                                        sqlStr);
                        rs = stmt.executeQuery(sqlStr);

                        int userID = 0;

                        //Judge whether the userID is dupliacted
                        List tempUserList = new ArrayList();

                        while (rs.next())
                        {
                                userID = rs.getInt("userID");

                                if (!OrganHelper.isDuplicate(tempUserList, userID))
                                {
                                        tempUserList.add(new Integer(userID));

                                        UserRoleForm urf = new UserRoleForm();
                                        UserForm uf = new UserForm();
                                        uf.setUserID(userID);
                                        uf.setLoginName(rs.getString("LoginName"));
                                        uf.setMail(rs.getString("Mail"));
                                        uf.setAvailable(rs.getString("Available"));
                                        uf.setPhone(rs.getString("phone"));
                                        uf.setAddress(rs.getString("address"));
                                        uf.setName(rs.getString("name"));
                                        uf.setSex(rs.getString("sex"));
                                        uf.setPostalcode(rs.getString("postalcode"));
                                        urf.setUserID(userID);
                                        urf.setUserForm(uf);

                                        sqlStrRole = "SELECT * FROM Sec_Role_Tab s1,Sec_UserRole_Tab s2 WHERE s2.relationID = " +
                                                orgID + " AND type = " +
                                                SecurityConstants.USER_ORGAN_RELATION +
                                                " AND s2.userID = " + userID +
                                                " AND s1.RoleID = s2.RoleID ORDER BY s2.roleID";

                                        stmtRole = dbConnection.createStatement();
                                        rsRole = stmtRole.executeQuery(sqlStrRole);

                                        RoleForm rf = null;
                                        int rsIndex = 0;
                                        List roleList = new ArrayList();

                                        while (rsRole.next())
                                        {
                                                rsIndex = 1;
                                                rf = new RoleForm();
                                                rf.setSysID(rsRole.getInt(rsIndex++));
                                                rf.setRoleID(rsRole.getInt(rsIndex++));
                                                rf.setName(rsRole.getString(rsIndex++));
                                                rf.setDescription(rsRole.getString(rsIndex++));
                                                roleList.add(rf);
                                        }

                                        urf.setRoleList(roleList);
                                        userRoleList.add(urf);
                                }
                        }
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new OrganDAOSysException(
                                "SQLException while getting org userlist " +
                                        "account; Serial = " + orgID + " :\n" + se);
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeResultSet(rsRole);
                        closeStatement(stmtRole);
                        closeConnection();
                }

                return userRoleList;
        }

        public List getOrganPath(int orgID) throws OrganDAOSysException
        {
                //List organParentList=new ArrayList();
                OrganForm of = getOrgan(orgID);
                int parentID = of.getParentID();

                if (parenttmpList.size() == 0)
                {
                        parenttmpList.add(new Integer(orgID));
                        organParentList.add(getOrgan(orgID));
                }

                if ((parentID > 0) && (parentID != orgID) &&
                        (!OrganHelper.isDuplicate(parenttmpList, parentID)))
                {
                        parenttmpList.add(new Integer(orgID));
                        organParentList.add(getOrgan(parentID));
                        getOrganPath(parentID);
                }

                return organParentList;
        }

        public boolean isHasSubOrgan(int orgID) throws OrganDAOSysException
        {
                String hql = " select count(*)  from OrganModel where parentid = " +
                        orgID;

                try
                {
                        int num = HibernateDAO.count(hql);

                        if (num > 0)
                        {
                                return true;
                        }
                }
                catch (ULMSSysException se)
                {
                        throw new OrganDAOSysException(
                                "SQLException while isHasSubOrgan(int orgID) " +
                                        "account; Serial = " + orgID + " :\n" + se);
                }

                return false;
        }

        /**
         * Get the tree structure from the current organ ID
         *
         * @param orgID
         * @return
         * @throws OrganDAOSysException
         */
        public List getOrganTree(int orgID) throws OrganDAOSysException
        {
                List root = getOrganList(orgID);
                int level = 0;

                for (Iterator it = root.iterator(); it.hasNext();)
                {
                        OrganForm of = (OrganForm) it.next();
                        of.setLayer(level);

                        int rootID = of.getOrgID();
                        orgTree.add(of);

                        if (isHasSubOrgan(rootID))
                        {
                                getOrganTree(rootID, level);
                        }
                }

                return orgTree;
        }

        /**
         * Get all the parent organs according to the sub organ ID
         *
         * @param orgID
         * @return
         * @throws OrganDAOSysException
         */
        public List getOrganParentList(int orgID) throws OrganDAOSysException
        {
                //List organParentList=new ArrayList();
                OrganForm of = getOrgan(orgID);
                int parentID = of.getParentID();

                if (parenttmpList.size() == 0)
                {
                        parenttmpList.add(new Integer(orgID));
                }

                if ((parentID > 0) && (parentID != orgID) &&
                        (!OrganHelper.isDuplicate(parenttmpList, orgID)))
                {
                        parenttmpList.add(new Integer(parentID));
                        organParentList.add(new Integer(parentID));
                        getOrganParentList(parentID);
                }

                return organParentList;
        }

        public List getOrganChild(int orgID) throws OrganDAOSysException
        {
                List child = getOrganList(orgID);

                for (int i = 0; i < child.size(); i++)
                {
                        OrganForm of = (OrganForm) child.get(i);
                        int subOrgID = of.getOrgID();
                        childList.add(new Integer(subOrgID));

                        if (isHasSubOrgan(subOrgID))
                        {
                                getOrganChild(subOrgID);
                        }
                }

                return childList;
        }

        public List getAspTreeByAsp(int aspID) throws OrganDAOSysException
        {
                List root = getOrganListBy(aspID);
                int level = 0;

                for (Iterator it = root.iterator(); it.hasNext();)
                {
                        OrganForm of = (OrganForm) it.next();
                        of.setLayer(level);

                        int rootID = of.getOrgID();
                        aspTree.add(of);

                        if (isHasSubOrgan(rootID))
                        {
                                getAspTree(rootID, level);
                        }
                }

                return aspTree; //To change body of implemented methods use File | Settings | File Templates.
        }

        /**
         * Get all the children according to the current organ ID
         *
         * @param orgID
         * @return
         * @throws OrganDAOSysException
         */
        public List getOrganChildList(int orgID) throws OrganDAOSysException
        {
                List children = getOrganList(orgID);
                LogUtil.debug("admin",
                        "[OrganDAOimpl]====================the children's Size() is : " +
                                children.size());

                if (tmpList.size() == 0)
                {
                        tmpList.add(new Integer(orgID));
                }

                for (Iterator it = children.iterator(); it.hasNext();)
                {
                        OrganForm of = (OrganForm) it.next();
                        int subOrgID = of.getOrgID();

                        if ((orgID != subOrgID) &&
                                (!OrganHelper.isDuplicate(tmpList, orgID)))
                        {
                                tmpList.add(new Integer(subOrgID));
                                organChildList.add(new Integer(subOrgID));
                        }
                }

                return organChildList;
        }

        /**
         * 得到用户所在ASP
         *
         * @param orgID
         * @return
         * @throws OrganDAOSysException
         */
        public List getUserAsp(int orgID) throws OrganDAOSysException
        {
                OrganForm of = getOrgan(orgID);
                int parentID = of.getParentID();
                userAsp.add(of);

                if (of.getParentID() > 0)
                {
                        getUserAsp(parentID);
                }

                return userAsp;
        }

        /**
         * Get organ Tree from the current organID
         *
         * @param orgID
         * @param currentLevel
         * @return
         * @throws OrganDAOSysException
         */
        public List getOrganTree(int orgID, int currentLevel)
                throws OrganDAOSysException
        {
                List root = getOrganList(orgID);
                int level = currentLevel + 1;

                for (Iterator it = root.iterator(); it.hasNext();)
                {
                        OrganForm of = (OrganForm) it.next();
                        of.setLayer(level);

                        int rootID = of.getOrgID();
                        orgTree.add(of);

                        if (isHasSubOrgan(rootID))
                        {
                                getOrganTree(rootID, level);
                        }
                }

                return orgTree;
        }

        public List getAspTree(int orgID, int currentLevel)
                throws OrganDAOSysException
        {
                List root = getOrganListBy(orgID);
                int level = currentLevel + 1;

                for (Iterator it = root.iterator(); it.hasNext();)
                {
                        OrganForm of = (OrganForm) it.next();
                        of.setLayer(level);

                        int rootID = of.getOrgID();
                        aspTree.add(of);

                        if (isHasSubOrgan(rootID))
                        {
                                getAspTree(rootID, level);
                        }
                }

                return aspTree;
        }

        protected void closeConnection() throws OrganDAOSysException
        {
                try
                {
                        if ((dbConnection != null) && !dbConnection.isClosed())
                        {
                                dbConnection.close();
                        }
                }
                catch (SQLException se)
                {
                        throw new OrganDAOSysException("SQL Exception while closing " +
                                "DB connection : \n" + se);
                }
        }

        protected void closeResultSet(ResultSet result) throws OrganDAOSysException
        {
                try
                {
                        if (result != null)
                        {
                                result.close();
                        }
                }
                catch (SQLException se)
                {
                        throw new OrganDAOSysException("SQL Exception while closing " +
                                "Result Set : \n" + se);
                }
        }

        protected void closeStatement(Statement stmt) throws OrganDAOSysException
        {
                try
                {
                        if (stmt != null)
                        {
                                stmt.close();
                        }
                }
                catch (SQLException se)
                {
                        throw new OrganDAOSysException("SQL Exception while closing " +
                                "Statement : \n" + se);
                }
        }

        protected Connection getConnection() throws OrganDAOSysException
        {
                try
                {
                        dbConnection = DBUtil.getConnection();
                }
                catch (Exception se)
                {
                        throw new OrganDAOSysException("SQL Exception while getting " +
                                "DB connection : \n" + se);
                }

                return dbConnection;
        }

        protected Connection getJieFoConnection() throws OrganDAOSysException
        {
                try
                {
                        dbConnection = DBUtil.getJieFoJDBCConnection();
                }
                catch (Exception se)
                {
                        throw new OrganDAOSysException("SQL Exception while getting " +
                                "DB connection : \n" + se);
                }

                return dbConnection;
        }

        /**
         * Convert the resultSet object to organForm
         *
         * @param rs the resultSet which needs to convert
         * @return the wanted organForm
         */
        private OrganForm convertRs2Form(ResultSet rs)
        {
                OrganForm of = new OrganForm();
                int rsIndex = 1;

                try
                {
                        of.setOrgID(rs.getInt(rsIndex++));
                        of.setParentID(rs.getInt(rsIndex++));
                        of.setAspID(rs.getInt(rsIndex++));
                        of.setLayer(rs.getInt(rsIndex++));
                        of.setOrgType(rs.getInt(rsIndex++));
                        of.setOrgName(rs.getString(rsIndex++));
                        of.setOrgStatus(rs.getInt(rsIndex++));
                        of.setForGuest(rs.getInt(rsIndex++));
                        of.setOrgNO(rs.getString(rsIndex++));
                        of.setIsAsp(rs.getInt(rsIndex++));
                        of.setDescription(rs.getString(rsIndex++));
                        of.setProvince(rs.getString(rsIndex++));
                        of.setCity(rs.getString(rsIndex++));
                        of.setCounty(rs.getString(rsIndex++));
                        of.setMember(rs.getString(rsIndex++));
                        of.setDnsName(rs.getString(rsIndex++));
                        of.setRemark1(rs.getString(rsIndex++));
                        of.setRemark2(rs.getString(rsIndex++));
                        of.setRemark3(rs.getString(rsIndex++));
                        of.setRemark4(rs.getString(rsIndex++));
                        of.setRemark5(rs.getString(rsIndex++));
                        of.setStartDate(rs.getDate(rsIndex++));
                        of.setEndDate(rs.getDate(rsIndex++));
                        of.setSchoolID(rs.getString(rsIndex++));
                        of.setLogo(rs.getString(rsIndex++));
                        of.setRegDate(rs.getDate(rsIndex++));
                        of.setIsCheck(rs.getString(rsIndex++));

                }
                catch (SQLException sql)
                {
                        sql.printStackTrace();
                }

                return of;
        }
}
