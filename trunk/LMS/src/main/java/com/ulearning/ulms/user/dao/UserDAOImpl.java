/**
 * Created by IntelliJ IDEA.
 * User: dengj
 * Date: Apr 8, 2004
 * Time: 9:36:22 AM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.user.dao;

import com.ulearning.ulms.core.crypto.MD5Encrypt;
import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.user.exceptions.UserDAOSysException;
import com.ulearning.ulms.user.form.JieFoClerkForm;
import com.ulearning.ulms.user.form.UserForm;
import com.ulearning.ulms.user.model.UserModel;
import com.ulearning.ulms.util.DBUtil;
import com.ulearning.ulms.util.HibernateDAO;
import com.ulearning.ulms.util.HibernateUtil;
import com.ulearning.ulms.util.log.LogUtil;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;
import net.sf.hibernate.SessionFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Date;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.StringEscapeUtils;

public class UserDAOImpl implements UserDAO
{

        protected transient Connection dbConnection = null;
        protected transient DataSource datasource = null;
        protected static SessionFactory sessionFactory = null;
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        //pass
        public int addUser(UserForm details) throws UserDAOSysException
        {
                int userID = 0;
                try
                {
                        String passwd = details.getPassword().trim();
                        details.setPlainPassword(passwd);
                        String passwdMD5 = MD5Encrypt.encrypt(passwd);
                        details.setRegisterDate(new Date(System.currentTimeMillis()));
                        details.setPassword(passwdMD5);
                        if (details.getPwdAnswer() != null && details.getPwdAnswer().length() >= 1)
                        {
                                details.setPwdAnswer(MD5Encrypt.encrypt(details.getPwdAnswer()));
                        }
                        String userIDs = HibernateDAO.add(details.getUserModel()).toString();
                        userID = Integer.parseInt(userIDs);

                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new UserDAOSysException("ULMSSysException while addUser(UserForm details) method" + e);
                }

                return userID;
        }

        public void addJieFoUser(JieFoClerkForm form) throws UserDAOSysException
        {
                Statement stmt = null;
                String passwd = form.getClerk_pwd().trim();
                //String passwdMD5 = MD5Encrypt.encrypt(passwd);
                String sqlStr = "insert into clerk(Clerk_ID,OrganId,Clerk_name,Name,Clerk_pwd,Clerk_sex," +
                        "Clerk_Job,Clerk_address,Clerk_telephone,Clerk_email,Clerk_BM,Clerk_xl," +
                        "Clerk_WorkType,Clerk_SFZNumber,Clerk_post,Leibie)" +
                        " values(" + form.getUserID() + "," +
                        form.getOrganID() + ",'" +
                        form.getClerk_name() + "','" +
                        form.getName() + "','" +
                        form.getClerk_pwd() + "','" +
                        form.getClerk_sex() + "','" +
                        form.getClerk_job() + "','" +
                        form.getClerk_address() + "','" +
                        form.getClerk_telephone() + "','" +
                        form.getClerk_email() + "','" +
                        form.getClerk_BM() + "','" +
                        form.getClerk_xl() + "','" +
                        form.getClerk_WorkType() + "','" +
                        form.getClerk_SFZNumber() + "','" +
                        form.getClerk_post() + "'," +
                        form.getLeibie() + ")";

                LogUtil.debug("user", "[UserDAOImpl]====================the sql string is : " + sqlStr);
                try
                {
                        dbConnection = getJieFoConnection();
                        stmt = dbConnection.createStatement();
                        stmt.execute(sqlStr);
                }
                catch (SQLException se)
                {
                        throw new UserDAOSysException("SQLException while addJieFoUser(JieFoClerkForm form) " + "user; Serial = " + form.getClerk_name() + " :\n" + se);
                }
                catch (ULMSSysException se)
                {
                        throw new UserDAOSysException("SQLException while addJieFoUser(JieFoClerkForm form) " + "User; Serial = " + form.getClerk_name() + " :\n" + se);
                }
                finally
                {
                        closeStatement(stmt);
                        closeConnection();
                }

        }

        public void updateJieFoUser(JieFoClerkForm form) throws UserDAOSysException
        {
                Statement stmt = null;
                String sqlStr = null;
                if (form.getClerk_pwd() == null)
                {
                        sqlStr = "update clerk set " +
                                "Clerk_name = '" + form.getClerk_name() + "', " +
                                "Name = '" + form.getName() + "', " +
                                "Clerk_sex = '" + form.getClerk_sex() + "', " +
                                "Clerk_Job = '" + form.getClerk_job() + "', " +
                                "Clerk_address = '" + form.getClerk_address() + "', " +
                                "Clerk_telephone = '" + form.getClerk_telephone() + "', " +
                                "Clerk_email = '" + form.getClerk_email() + "', " +
                                "Clerk_BM = '" + form.getClerk_BM() + "', " +
                                "Clerk_xl = '" + form.getClerk_xl() + "', " +
                                "Clerk_WorkType = '" + form.getClerk_WorkType() + "', " +
                                "Clerk_SFZNumber = '" + form.getClerk_SFZNumber() + "', " +
                                "Clerk_post = '" + form.getClerk_post() + "', " +
                                "Leibie = " + form.getLeibie() +
                                "  where Clerk_ID = " + form.getUserID();

                }
                else
                {
                        //String passwdMD5 = MD5Encrypt.encrypt(form.getClerk_pwd());
                        sqlStr = "update clerk set " +
                                "Clerk_name = '" + form.getClerk_name() + "', " +
                                "Name = '" + form.getName() + "', " +
                                "Clerk_pwd = '" + form.getClerk_pwd() + "', " +
                                "Clerk_sex = '" + form.getClerk_sex() + "', " +
                                "Clerk_Job = '" + form.getClerk_job() + "', " +
                                "Clerk_address = '" + form.getClerk_address() + "', " +
                                "Clerk_telephone = '" + form.getClerk_telephone() + "', " +
                                "Clerk_email = '" + form.getClerk_email() + "', " +
                                "Clerk_xl = '" + form.getClerk_xl() + "', " +
                                "Clerk_WorkType = '" + form.getClerk_WorkType() + "', " +
                                "Clerk_SFZNumber = '" + form.getClerk_SFZNumber() + "', " +
                                "Clerk_post = '" + form.getClerk_post() + "', " +
                                "Leibie = " + form.getLeibie() +
                                "  where Clerk_ID = " + form.getUserID();
                }
                try
                {
                        dbConnection = getJieFoConnection();
                        stmt = dbConnection.createStatement();
                        LogUtil.debug("user", "[UserDAOImpl]====================the sql string is : " + sqlStr);
                        stmt.execute(sqlStr);
                }
                catch (SQLException se)
                {
                        throw new UserDAOSysException("SQLException while updateJieFoUser(JieFoClerkForm form) " + "user; Serial = " + form.getClerk_name() + " :\n" + se);
                }
                catch (ULMSSysException se)
                {
                        throw new UserDAOSysException("SQLException while updateJieFoUser(JieFoClerkForm form) " + "User; Serial = " + form.getClerk_name() + " :\n" + se);
                }
                finally
                {
                        closeStatement(stmt);
                        closeConnection();
                }
        }

        public void updateLeiBie(int userID, int leibie) throws UserDAOSysException
        {
                Connection conn = null;
                Statement stmt = null;
                String sqlStr = "update clerk set Leibie =" + leibie + "" +
                        "where Clerk_ID=" + userID;
                try
                {
                        conn = getJieFoConnection();
                        stmt = conn.createStatement();
                        LogUtil.debug("user", "[UserDAOImpl]====================the sql string is : " + sqlStr);
                        stmt.execute(sqlStr);

                }
                catch (SQLException se)
                {
                        throw new UserDAOSysException("SQLException while updateJieFoUser(JieFoClerkForm form) " + "user; Serial = " + userID + " :\n" + se);
                }
                catch (ULMSSysException se)
                {
                        throw new UserDAOSysException("SQLException while updateJieFoUser(JieFoClerkForm form) " + "User; Serial = " + userID + " :\n" + se);
                }
                finally
                {
                        closeStatement(stmt);
                        closeConnection();
                }
        }

        //pass
        public void updateUser(UserForm details) throws UserDAOSysException
        {

                try
                {
                        if (details.getPassword() == null || details.getPassword().length() < 1)
                        {
                                UserForm uf = this.getUser(new Integer(details.getUserID()).toString());
                                details.setPassword(uf.getPassword());
                                details.setPlainPassword(uf.getPlainPassword());
                        }
                        else
                        {
                                //Use MD5 to encrypt the password
                                String passwd = details.getPassword().trim();
                                details.setPlainPassword(passwd);
                                String passwdMD5 = MD5Encrypt.encrypt(passwd);
                                details.setPassword(passwdMD5);
                        }
                        if (details.getPwdAnswer() == null || details.getPwdAnswer().length() < 1)
                        {
                                UserForm uf = this.getUser(new Integer(details.getUserID()).toString());
                                details.setPwdQuestion(uf.getPwdQuestion());
                                details.setPwdAnswer(uf.getPwdAnswer());
                        }
                        else
                        {

                                details.setPwdAnswer(MD5Encrypt.encrypt(details.getPwdAnswer()));
                        }
                        HibernateDAO.update(details.getUserModel());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new UserDAOSysException("ULMSSysException while updateUser(UserForm details) method" + e);
                }

        }

        public void updateStates(int userID, int available) throws UserDAOSysException
        {
                try
                {
                        UserModel um = (UserModel) HibernateDAO.load(UserModel.class, new Integer(userID));
                        um.setAvailable(new Integer(available).toString());
                        HibernateDAO.update(um);
                }
                catch (ULMSSysException se)
                {
                        throw new UserDAOSysException("SQLException while update updateStates(int userID,int available) " + "account; Serial = " + userID + " :\n" + se);
                }
        }

        //pass
        /**
         * Remove the user from database by the userID
         *
         * @param userID
         * @throws UserDAOSysException
         */
        public void removeUser(String userID) throws UserDAOSysException
        {
                String hql = "from UserModel where userID =" + userID;
                try
                {
                        HibernateDAO.delete(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new UserDAOSysException("ULMSSysException while removeUser(String userID) method" + e);
                }
        }

        public void removeJieFoUser(String userID) throws UserDAOSysException
        {
                Statement stmt = null;
                String sqlStr = "delete from clerk where Clerk_Id = " + userID;
                try
                {
                        dbConnection = getJieFoConnection();
                        stmt = dbConnection.createStatement();
                        LogUtil.info("system", "[UserDAOOracle]====================the sql string is : " + sqlStr);
                        stmt.execute(sqlStr);
                }
                catch (SQLException se)
                {
                        throw new UserDAOSysException("SQLException while deleting removeJieFoUser " + "account; Serial = " + userID + " :\n" + se);
                }
                finally
                {
                        closeStatement(stmt);
                        closeConnection();
                }
        }


        public int checkUser(int userID) throws UserDAOSysException
        {
                // boolean isTure=false;
                ResultSet rs = null;
                // ArrayList orgIDList = new ArrayList();
                int useID = 0;
                String sqlStr = "select userID from u_user_tab where catalogID in " +
                        "(select orgID from tm_org_tab where orgName in " +
                        "('人事局','营运中心','机关服务局','上海市分行','教育培训局','信用管理局','平台')) " +
                        "and userID =" + userID;
                try
                {
                        LogUtil.debug("system", "[checkOrgSQLStr]====================the sql string is : " + sqlStr);
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(sqlStr);
                        while (rs.next())
                        {
                                useID = rs.getInt("userID");
                        }

                }
                catch (SQLException se)
                {
                        throw new UserDAOSysException("SQLException while checkOrgSQLStr " + "user; catalogID=" + "xxxx" + ":\n" + se);
                }
                catch (ULMSSysException se)
                {
                        throw new UserDAOSysException("SQLException while checkOrgSQLStr " + "user; catalogID = " + "xxx" + " :\n" + se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }
                return useID;  //To change body of implemented methods use File | Settings | File Templates.
        }

        public UserForm getUser(String userID) throws UserDAOSysException
        {
                UserForm uf = null;
                try
                {
                        UserModel um = null;
                        Object user = HibernateDAO.load(UserModel.class, new Integer(userID));
                        if (user != null)
                        {
                                um = (UserModel) user;
                        }
                        uf = new UserForm(um);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new UserDAOSysException("ULMSSysException while getUser(String userID) method" + e);
                }
                return uf;
        }

        public UserForm getUserByLoginName(String loginname) throws UserDAOSysException
         {
                List list = new ArrayList();
                UserForm uf =null;
                String hql = "from UserModel where loginname='"+loginname+"'  order by loginname desc";
                try
                {
                        list = HibernateDAO.find(hql);
                        UserModel um = null;
                        if (list != null)
                        {
                                for (int i = 0; i < list.size(); i++)
                                {
                                        um = (UserModel) list.get(i);
                                        uf = new UserForm(um);
                                }
                        }
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new UserDAOSysException("HibernateLException while getUserList(int ogID)" + e);
                }
                return uf;
         }

        public UserModel getUserByExternalSystemUserID(int externalSystemUserID) throws UserDAOSysException
        {
                UserModel userModel = null;
                List tmList = null;
                String hql = " from UserModel  where  externalSystemUserID = "+externalSystemUserID
                        +" order by userid desc";
                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new UserDAOSysException("" + e);
                }

                for (int i = 0; i < tmList.size(); i++)
                {
                        userModel = (UserModel) tmList.get(i);
                        break;
                }
                return userModel;
        }

        /**
         * 为开发银行写
         *
         * @param userID
         * @return
         * @throws UserDAOSysException
         */
        public int checkOrgUser(int userID) throws UserDAOSysException
        {
                // boolean isTure=false;
                ResultSet rs = null;
                // ArrayList orgIDList = new ArrayList();
                int useID = 0;
                String sqlStr = "select userID from u_user_tab where catalogID in " +
                        "(select orgID from tm_org_tab where orgName in " +
                        "('人事局','营运中心','机关服务局','上海市分行','教育培训局','评审一局','评审二局','机关服务局','信用管理局','信用管理局','平台')) " +
                        "and userID =" + userID;
                try
                {
                        LogUtil.debug("system", "[checkOrgSQLStr]====================the sql string is : " + sqlStr);
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(sqlStr);
                        while (rs.next())
                        {
                                useID = rs.getInt("userID");
                        }

                }
                catch (SQLException se)
                {
                        throw new UserDAOSysException("SQLException while checkOrgSQLStr " + "user; catalogID=" + "xxxx" + ":\n" + se);
                }
                catch (ULMSSysException se)
                {
                        throw new UserDAOSysException("SQLException while checkOrgSQLStr " + "user; catalogID = " + "xxx" + " :\n" + se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }
                return useID;  //To change body of implemented methods use File | Settings | File Templates.
        }

        public List getApplyUserList() throws UserDAOSysException
        {
                List list = new ArrayList();
                List userList = new ArrayList();
                String hql = "from UserModel where userID >= 100 and available = '0' order by UserModel.userid desc";
                try
                {
                        list = HibernateDAO.find(hql);
                        UserModel um = null;
                        if (list != null)
                        {
                                for (int i = 0; i < list.size(); i++)
                                {
                                        um = (UserModel) list.get(i);
                                        UserForm pf = new UserForm(um);
                                        userList.add(pf);
                                }
                        }
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new UserDAOSysException("HibernateLException while getUserList(int ogID)" + e);
                }

                return userList;
        }

        public List getApplyUserList(int aspID) throws UserDAOSysException
        {
                List list = new ArrayList();
                List userList = new ArrayList();
                String hql = "from UserModel where userID >= 100 and  catalogid="+aspID+" and available = '0'  order by registerdate desc";
                try
                {
                        list = HibernateDAO.find(hql);
                        UserModel um = null;
                        if (list != null)
                        {
                                for (int i = 0; i < list.size(); i++)
                                {
                                        um = (UserModel) list.get(i);
                                        UserForm pf = new UserForm(um);
                                        userList.add(pf);
                                }
                        }
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new UserDAOSysException("HibernateLException while getUserList(int ogID)" + e);
                }

                return userList;
        }

        public List getSearchUserListByRole(UserForm search) throws UserDAOSysException
        {
                ResultSet rs = null;
                UserForm uf = null;
                ArrayList searchList = new ArrayList();
                String whereStr = " where U_User_Tab.CatalogID = TM_Org_Tab.OrgID  and U_User_Tab.UserID = Sec_UserRole_Tab.UserID and Sec_UserRole_Tab.type=1" +
                        "and U_User_Tab.userID >= 100 and U_User_Tab.available = '1' ";
                if (search.getCatalogID() >= 0)
                {
                        whereStr += " and U_User_Tab.CatalogID = " + search.getCatalogID();
                }
                if ((search.getLoginName() != null) && (!search.getLoginName().trim().equals("")))
                {
                        String loginName = StringEscapeUtils.escapeSql(search.getLoginName());
                        if ((search.getName() != null) && (!search.getName().trim().equals("")))
                        {

                                whereStr += " and (loginName like '%" + loginName + "%'";
                        }
                        else
                        {
                                whereStr += " and loginName like '%" +loginName + "%'";
                        }
                }
                if ((search.getName() != null) && (!search.getName().trim().equals("")))
                {
                        String name = StringEscapeUtils.escapeSql(search.getName());
                        if ((search.getLoginName() != null) && (!search.getLoginName().trim().equals("")))
                        {

                                whereStr += " or name like '%" + name + "%')";
                        }
                        else
                        {
                                whereStr += " and name like '%" + name + "%'";
                        }
                }
                if ((search.getMail() != null) && (!search.getMail().trim().equals("")))
                {
                        whereStr += " and mail like '%" + search.getMail().trim() + "%'";
                }
                if ((search.getSex() != null) && (!search.getSex().trim().equals("")))
                {
                        whereStr += " and sex=" + search.getSex().trim();
                }
                if ((search.getAddress() != null) && (!search.getAddress().trim().equals("")))
                {
                        whereStr += " and address like '%" + search.getAddress().trim() + "%'";
                }
                String remark  = StringEscapeUtils.escapeSql(search.getRemark12());
                if ((search.getRemark12() != null) && (!search.getRemark12().trim().equals("")))
                {
                        whereStr += " and Sec_UserRole_Tab.roleID =" + remark + "";
                }

                String sqlStr = "Select * from U_User_Tab , TM_Org_Tab, Sec_UserRole_Tab " + whereStr;
                System.out.println("sqlStr ====11===== " + sqlStr);
                LogUtil.debug("user", "[UserDAOOracle]====================the sql string is : " + sqlStr);
                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(sqlStr);
                        while (rs.next())
                        {
                                uf = convertRs2Form(rs);
                                searchList.add(uf);
                        }

                }
                catch (SQLException se)
                {
                        throw new UserDAOSysException("SQLException while updating " + "user; Serial = " + search + " :\n" + se);
                }
                catch (ULMSSysException se)
                {
                        throw new UserDAOSysException("SQLException while updating " + "User; Serial = " + search + " :\n" + se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }

                return searchList;


        }

        /**
         * @param search
         * @return
         * @throws UserDAOSysException
         */
        public List getSearchUserList(UserForm search) throws UserDAOSysException
        {
                ResultSet rs = null;
                UserForm uf = null;
                ArrayList searchList = new ArrayList();
                String whereStr = " where U_User_Tab.CatalogID = TM_Org_Tab.OrgID  and userID >= 100 and available = '1' ";
                if (search.getCatalogID() >= 0)
                {
                        whereStr += " and U_User_Tab.CatalogID = " + search.getCatalogID();
                }
                if ((search.getLoginName() != null) && (!search.getLoginName().trim().equals("")))
                {
                        String loginName = StringEscapeUtils.escapeSql(search.getLoginName());
                        if ((search.getName() != null) && (!search.getName().trim().equals("")))
                        {

                                whereStr += " and (loginName like '%" + loginName + "%'";
                        }
                        else
                        {
                                whereStr += " and loginName like '%" +loginName + "%'";
                        }
                }
                if ((search.getName() != null) && (!search.getName().trim().equals("")))
                {
                        String name = StringEscapeUtils.escapeSql(search.getName());
                        if ((search.getLoginName() != null) && (!search.getLoginName().trim().equals("")))
                        {

                                whereStr += " or name like '%" + name + "%')";
                        }
                        else
                        {
                                whereStr += " and name like '%" + name + "%'";
                        }
                }
                if ((search.getMail() != null) && (!search.getMail().trim().equals("")))
                {
                        whereStr += " and mail like '%" + search.getMail().trim() + "%'";
                }
                if ((search.getSex() != null) && (!search.getSex().trim().equals("")))
                {
                        whereStr += " and sex=" + search.getSex().trim();
                }
                if ((search.getAddress() != null) && (!search.getAddress().trim().equals("")))
                {
                        whereStr += " and address like '%" + search.getAddress().trim() + "%'";
                }
                if ((search.getRemark1() != null) && (!search.getRemark1().trim().equals("")))
                {
                        whereStr += " and Remark1 like '%" + search.getRemark1().trim() + "%'";
                }
                if ((search.getRemark2() != null) && (!search.getRemark2().trim().equals("")))
                {
                        whereStr += " and Remark2 like '%" + search.getRemark2().trim() + "%'";
                }
                if ((search.getRemark3() != null) && (!search.getRemark3().trim().equals("")))
                {
                        whereStr += " and Remark3 like '%" + search.getRemark3().trim() + "%'";
                }
                if ((search.getRemark4() != null) && (!search.getRemark4().trim().equals("")))
                {
                        whereStr += " and Remark4 like '%" + search.getRemark4().trim() + "%'";
                }
                if ((search.getRemark5() != null) && (!search.getRemark5().trim().equals("")))
                {
                        whereStr += " and Remark5 like '%" + search.getRemark5().trim() + "%'";
                }
                if ((search.getRemark6() != null) && (!search.getRemark6().trim().equals("")))
                {
                        whereStr += " and Remark6 like '%" + search.getRemark6().trim() + "%'";
                }
                if ((search.getRemark7() != null) && (!search.getRemark7().trim().equals("")))
                {
                        whereStr += " and Remark7 like '%" + search.getRemark7().trim() + "%'";
                }
                if ((search.getRemark8() != null) && (!search.getRemark8().trim().equals("")))
                {
                        whereStr += " and Remark8 like '%" + search.getRemark8().trim() + "%'";
                }
                if ((search.getRemark9() != null) && (!search.getRemark9().trim().equals("")))
                {
                        whereStr += " and Remark9 like '%" + search.getRemark9().trim() + "%'";
                }

                String sqlStr = "Select * from U_User_Tab , TM_Org_Tab " + whereStr;
                LogUtil.debug("user", "[UserDAOOracle]====================the sql string is : " + sqlStr);
                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(sqlStr);
                        while (rs.next())
                        {
                                uf = convertRs2Form(rs);
                                searchList.add(uf);
                        }

                }
                catch (SQLException se)
                {
                        throw new UserDAOSysException("SQLException while updating " + "user; Serial = " + search + " :\n" + se);
                }
                catch (ULMSSysException se)
                {
                        throw new UserDAOSysException("SQLException while updating " + "User; Serial = " + search + " :\n" + se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }

                return searchList;

        }


        /**
         * @param search
         * @return
         * @throws UserDAOSysException
         */
        public List getSearchByUserList(UserForm search, int orgID, boolean isAsp) throws UserDAOSysException
        {
                ResultSet rs = null;
                UserForm uf = null;
                ArrayList searchList = new ArrayList();
                String whereStr = " where userID >= 100 ";
                if ((search.getLoginName() != null) && (!search.getLoginName().trim().equals("")))
                {
                        whereStr += " and loginName like '%" + search.getLoginName().trim() + "%'";
                }
                if ((search.getName() != null) && (!search.getName().trim().equals("")))
                {
                        whereStr += " and name like '%" + search.getName().trim() + "%'";
                }
                if ((search.getMail() != null) && (!search.getMail().trim().equals("")))
                {
                        whereStr += " and mail like '%" + search.getMail().trim() + "%'";
                }
                if ((search.getSex() != null) && (!search.getSex().trim().equals("")))
                {
                        whereStr += " and sex=" + search.getSex().trim();
                }
                if ((search.getAddress() != null) && (!search.getAddress().trim().equals("")))
                {
                        whereStr += " and address like '%" + search.getAddress().trim() + "%'";
                }
                if ((search.getRemark1() != null) && (!search.getRemark1().trim().equals("")))
                {
                        whereStr += " and Remark1 like '%" + search.getRemark1().trim() + "%'";
                }
                if ((search.getRemark2() != null) && (!search.getRemark2().trim().equals("")))
                {
                        whereStr += " and Remark2 like '%" + search.getRemark2().trim() + "%'";
                }
                if ((search.getRemark3() != null) && (!search.getRemark3().trim().equals("")))
                {
                        whereStr += " and Remark3 like '%" + search.getRemark3().trim() + "%'";
                }
                if ((search.getRemark4() != null) && (!search.getRemark4().trim().equals("")))
                {
                        whereStr += " and Remark4 like '%" + search.getRemark4().trim() + "%'";
                }
                if ((search.getRemark5() != null) && (!search.getRemark5().trim().equals("")))
                {
                        whereStr += " and Remark5 like '%" + search.getRemark5().trim() + "%'";
                }
                if ((search.getRemark6() != null) && (!search.getRemark6().trim().equals("")))
                {
                        whereStr += " and Remark6 like '%" + search.getRemark6().trim() + "%'";
                }
                if ((search.getRemark7() != null) && (!search.getRemark7().trim().equals("")))
                {
                        whereStr += " and Remark7 like '%" + search.getRemark7().trim() + "%'";
                }
                if ((search.getRemark8() != null) && (!search.getRemark8().trim().equals("")))
                {
                        whereStr += " and Remark8 like '%" + search.getRemark8().trim() + "%'";
                }
                if ((search.getRemark9() != null) && (!search.getRemark9().trim().equals("")))
                {
                        whereStr += " and Remark9 like '%" + search.getRemark9().trim() + "%'";
                }

                String sqlStr = "Select * from U_User_Tab " + whereStr;
                LogUtil.debug("user", "[UserDAOOracle]====================the sql string is : " + sqlStr);
                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(sqlStr);
                        while (rs.next())
                        {
                                uf = convertRs2Form(rs);
                                searchList.add(uf);
                        }

                }
                catch (SQLException se)
                {
                        throw new UserDAOSysException("SQLException while updating " + "user; Serial = " + search + " :\n" + se);
                }
                catch (ULMSSysException se)
                {
                        throw new UserDAOSysException("SQLException while updating " + "User; Serial = " + search + " :\n" + se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }

                return searchList;

        }

        public int getAspUserCount(int aspID) throws UserDAOSysException
        {
                int totalUser = 0;
                String hql = " select count(um.userid)  from UserModel as um , OrganModel as om  where um.catalogid = om.orgid " +
                        " and  om.aspid=" + aspID;
                try
                {
                        totalUser = HibernateDAO.count(hql);
                }
                catch (ULMSSysException se)
                {
                        se.printStackTrace();
                        throw new UserDAOSysException("SQLException while getUserCount(int orgID) " + "user; Serial = " + aspID + " :\n" + se);
                }
                return totalUser;
        }

        public int getUserCount(int orgID) throws UserDAOSysException
        {
                int totalUser = 0;
                String hql = "select count(*)  from UserModel where catalogID=" + orgID;
                try
                {
                        totalUser = HibernateDAO.count(hql);
                }
                catch (ULMSSysException se)
                {
                        se.printStackTrace();
                        throw new UserDAOSysException("SQLException while getUserCount(int orgID) " + "user; Serial = " + orgID + " :\n" + se);
                }
                return totalUser;


        }

        /**
         * Get the user list by the aspID
         *
         * @param aspID
         * @return The prepared arraylist object,default size is 0
         * @throws UserDAOSysException
         */
        public List getAspUserList(int aspID) throws UserDAOSysException
        {
//        UserForm uf = null;
//        ResultSet rs = null;
//        ArrayList userList = new ArrayList();
//        String sqlStr = "select a.UserID,a.LoginName,a.Password,a.Name,a.Available,a.Mail," +
//                " a.Card,a.Sex,a.Phone,a.Cellphone,a.Address,a.Postalcode,a.Edulevel,a.Pwdquestion," +
//                " a.Pwdanswer,a.RegisterDate,a.CatalogID,a.LastloginDate,a.Desc0,a.Desc1,a.Description," +
//                " a.Remark1,a.Remark2,a.Remark3,a.Remark4,a.Remark5,a.Remark6,a.Remark7,a.Remark8,a.Remark9" +
//                " from  U_USER_TAB a , TM_ORGUSER_TAB b, TM_ORG_TAB c " +
//                " where a.catalogID=b.orgID and a.userID = b.userID" +
//                " and b.orgID=c.orgID" +
//                " and c.aspID=" + aspID;

//        try
//        {
//            LogUtil.debug("system", "[DiscussDAOImpl]====================the sql string is : " + sqlStr);
//            while (rs.next())
//            {
//                uf = convertRs2Form(rs);
//                userList.add(uf);
//            }
//
//        } catch (SQLException se)
//        {
//            throw new UserDAOSysException("SQLException while select " + "user; aspID=" + aspID + ":\n" + se);
//        } catch (ULMSSysException se)
//        {
//            throw new UserDAOSysException("SQLException while select " + "user; aspID = " + aspID + " :\n" + se);
//        } finally
//        {
//            try
//            {
//            } catch (SQLException se)
//            {
//            }
//        }
                List userList = new ArrayList();
                List tmList = new ArrayList();
                String hql = " from UserModel  as um , OrganUserModel as oum , OrganModel om  " +
                        " where um.catalogid = oum.comp_id.orgid and um.userid = oum.comp_id.userid " +
                        "  and  oum.comp_id.orgid = om.orgid and  om.aspid =" + aspID;
                try
                {
                        tmList = HibernateDAO.find(hql);

                }
                catch (ULMSSysException se)
                {
                        throw new UserDAOSysException("SQLException while getAspUserList(int aspID) " + "user; =" + se);
                }
                if ((tmList != null) && (tmList.size() > 0))
                {
                        Object[] record = null;
                        UserModel um = new UserModel();
                        for (Iterator iter = tmList.iterator(); iter.hasNext();)
                        {
                                record = (Object[]) iter.next();
                                um = (UserModel) record[0];
                                UserForm uf = new UserForm(um);
                                userList.add(uf);
                        }
                }
                return userList;

        }

        public void updatePwdQA(int userID, String pwdQuestion, String pwdAnswer) throws UserDAOSysException
        {
                try
                {
                        UserModel um = (UserModel) HibernateDAO.load(UserModel.class, new Integer(userID));
                        um.setPwdquestion(pwdQuestion);
                        um.setPwdanswer(MD5Encrypt.encrypt(pwdAnswer));
                        HibernateDAO.update(um);
                }
                catch (ULMSSysException se)
                {
                        throw new UserDAOSysException("SQLException while updatePwdQA(int userID, String pwdQuestion, String pwdAnswer)" + "account; Serial = " + userID + " :\n" + se);
                }
        }

        public List getUserList(int orgID, int userType) throws UserDAOSysException
        {
                return null;
        }

        /**
         * Get the user list by the catalogID
         *
         * @param ogID
         * @return The prepared arraylist object,default size is 0
         * @throws UserDAOSysException
         */
        public List getUserList(int ogID) throws UserDAOSysException
        {
                List list = new ArrayList();
                List userList = new ArrayList();
                String hql = "from UserModel where userID >= 100 ";
                try
                {
                        list = HibernateDAO.find(hql);
                        UserModel um = null;
                        if (list != null)
                        {
                                for (int i = 0; i < list.size(); i++)
                                {
                                        um = (UserModel) list.get(i);
                                        UserForm pf = new UserForm(um);
                                        userList.add(pf);
                                }
                        }
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new UserDAOSysException("HibernateLException while getUserList(int ogID)" + e);
                }

                return userList;
        }

        public int getCountUserList(int catalogID) throws UserDAOSysException
        {
                // List list = new ArrayList();
                String hql = " select count(*)  from UserModel  where  userid >= 100 ";
                int num = 0;
                try
                {
                        //  list = HibernateDAO.find(hql);
                        num = HibernateDAO.count(hql);
//            if (list != null)
//            {
//                return list.size();
//            }
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new UserDAOSysException("HibernateLException while getUserList(int ogID)" + e);
                }

                return num;
        }

        public List getUserList(int catalogID, int first, int max) throws UserDAOSysException, HibernateException
        {
                List list = new ArrayList();
                List userList = new ArrayList();
                String hql = "from UserModel where userID >= 100 order by LoginName";
                Session session = HibernateUtil.getSession();

                Query query = session.createQuery(hql);
                query.setFirstResult(first);
                query.setMaxResults(max);
                list = query.list();
                UserModel um = null;
                if (list != null)
                {
                        for (int i = 0; i < list.size(); i++)
                        {
                                um = (UserModel) list.get(i);
                                UserForm pf = new UserForm(um);
                                userList.add(pf);
                        }
                }
                try
                {
                        if (session != null)
                        {
                                HibernateUtil.releaseSession(session);
                        }
                }
                catch (HibernateException he)
                {
                        he.printStackTrace();
                }

                return userList;
        }


        /**
         * Get the user list by the orgID
         *
         * @param catalogID
         * @return The prepared arraylist object,default size is 0
         * @throws UserDAOSysException
         */
        public List getUserList(String catalogID) throws UserDAOSysException
        {
                List userList = new ArrayList();
                List list = new ArrayList();
                try
                {
                        Session session = HibernateUtil.getSession();
                        String hql = "";
                        if (Integer.parseInt(catalogID) == 0)
                        {

                                hql = "from UserModel as um where um.userid >= 100  and um.available=1 and um.catalogid = " + catalogID;

                        }
                        else
                        {
                                hql = "from UserModel as um , OrganUserModel as oum where um.userid >= 100  and um.available=1  and um.userid = oum.comp_id.userid and oum.comp_id.orgid=" + catalogID;

                        }
                        Query query = session.createQuery(hql);
                        list = query.list();
                        if (Integer.parseInt(catalogID) == 0)
                        {
                                UserModel um = new UserModel();
                                if (list != null)
                                {
                                        for (int i = 0; i < list.size(); i++)
                                        {
                                                um = (UserModel) list.get(i);
                                                UserForm pf = new UserForm(um);
                                                userList.add(pf);
                                        }
                                }
                        }
                        else
                        {
                                Object[] record = null;
                                UserModel um = null;
                                for (Iterator iter = list.iterator(); iter.hasNext();)
                                {
                                        record = (Object[]) iter.next();
                                        um = (UserModel) record[0];
                                        UserForm pf = new UserForm(um);
                                        userList.add(pf);
                                }
                        }
                        if (session != null)
                        {
                                HibernateUtil.releaseSession(session);
                        }
                }
                catch (HibernateException ht)
                {
                        ht.printStackTrace();
                }
                return userList;

        }

        public int getCountUserList(String orgID) throws UserDAOSysException
        {
                String hql = "";
                int num = 0;
                if (Integer.parseInt(orgID) == 0)
                {
                        hql = " select count(um.userid)  from UserModel as um where userID >= 100 and  catalogID= " + orgID;
                        //sqlStr = "select count(u.userID) as num from U_User_Tab as u where userID>=100 and catalogID=" + orgID;

                }
                else
                {
                        hql = " select count(um.userid)  from UserModel as um , OrganUserModel as oum where um.userid >= 100 " +
                                " and um.userid = oum.comp_id.userid and oum.comp_id.orgid=" + orgID;
                        //  sqlStr = "select count(a.userID)  as num from U_User_Tab a ,TM_ORGUSER_TAB b where  a.userID>=100 and " +
                        //         " a.userID=b.userID and b.orgID=" + orgID;
                }
                try
                {
                        num = HibernateDAO.count(hql);
                }
                catch (ULMSSysException se)
                {
                        se.printStackTrace();
                        throw new UserDAOSysException("SQLException while getUserCount(int orgID) " + "user; Serial = " + orgID + " :\n" + se);
                }
                return num;

        }

        public List getUserList(String catalogID, int first, int max) throws UserDAOSysException, HibernateException
        {

                List list = new ArrayList();
                List userList = new ArrayList();
                Session session = HibernateUtil.getSession();
                String hql = "from UserModel as um where um.userid >= 100 ";

                if (Integer.parseInt(catalogID) == 0)
                {

                        hql = "from UserModel as um where um.userid >= 100  and um.catalogid = :catalogID";

                }
                else
                {
                        hql = "from UserModel as um , OrganUserModel as oum where um.userid >= 100  and um.userid = oum.comp_id.userid and oum.comp_id.orgid=:catalogID";

                }
                Query query = session.createQuery(hql);
                query.setInteger("catalogID", Integer.parseInt(catalogID));
                query.setFirstResult(first);
                query.setMaxResults(max);

                list = query.list();
                if (Integer.parseInt(catalogID) == 0)
                {
                        UserModel um = null;
                        if (list != null)
                        {
                                for (int i = 0; i < list.size(); i++)
                                {
                                        um = (UserModel) list.get(i);
                                        UserForm pf = new UserForm(um);
                                        userList.add(pf);
                                }
                        }
                }
                else
                {
                        Object[] record = null;
                        UserModel um = null;
                        for (Iterator iter = list.iterator(); iter.hasNext();)
                        {
                                record = (Object[]) iter.next();
                                um = (UserModel) record[0];
                                UserForm pf = new UserForm(um);
                                userList.add(pf);
                        }
                }
                try
                {
                        if (session != null)
                        {
                                HibernateUtil.releaseSession(session);
                        }
                }
                catch (HibernateException he)
                {
                        he.printStackTrace();
                }

                return userList;

        }

        /**
         * Get the user list by the catalogID,the difference to getUserList(String catalogID) is
         * this method only get the users under the catalog attribute in U_User_Tab
         *
         * @param catalogID
         * @return The prepared arraylist object,default size is 0
         * @throws UserDAOSysException
         */
        public List getCatalogUserList(int catalogID) throws UserDAOSysException
        {
                List list = new ArrayList();
                List userList = new ArrayList();
                String hql = "from UserModel as um where um.userid >= 100  and um.catalogid = " + catalogID;
                try
                {
                        list = HibernateDAO.find(hql);
                        UserModel um = null;
                        if (list != null)
                        {
                                for (int i = 0; i < list.size(); i++)
                                {
                                        um = (UserModel) list.get(i);
                                        UserForm pf = new UserForm(um);
                                        userList.add(pf);
                                }
                        }
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new UserDAOSysException("HibernateLException while getUserList(int ogID)" + e);
                }

                return userList;
        }

        /**
         * Get the user list by the orgID
         *
         * @param orgID
         * @param isAsp org : 0  , asp: 1
         * @param desc1
         * @return The prepared arraylist UserForm,default size is 0
         * @throws UserDAOSysException
         */
        public List getUserList(int orgID, int isAsp, String desc1) throws UserDAOSysException
        {
//        String sqlStr = "Select b.UserID,b.LoginName,b.Password,b.Name,b.Available,b.Mail," +
//                " b.Card,b.Sex,b.Phone,b.Cellphone,b.Address,b.Postalcode,b.Edulevel,b.Pwdquestion," +
//                " b.Pwdanswer,b.RegisterDate,b.CatalogID,b.LastloginDate,b.Desc0,b.Desc1,b.Description," +
//                " b.Remark1,b.Remark2,b.Remark3,b.Remark4,b.Remark5,b.Remark6,b.Remark7,b.Remark8,b.Remark9" +
//                " from TM_Org_TAB a,U_USER_TAB b " +
//                " where  a.orgID=b.catalogID " +
//                " and a.isAsp=" + isAsp + "" +
//                " and b.catalogID=" + orgID + "" +
//                " and b.desc1='" + desc1 + "'";
                List userList = new ArrayList();
                List tmList = new ArrayList();
                String hql = " from OrganModel a, UserModel  b   " +
                        " where a.orgid = b.catalogid  " +
                        "  and a.isasp =  " + isAsp +
                        "  and  b.catalogid = " + orgID + " " +
                        " and  b.desc1 =" + desc1;
                try
                {
                        tmList = HibernateDAO.find(hql);

                }
                catch (ULMSSysException se)
                {
                        throw new UserDAOSysException("SQLException while getAspUserList(int aspID) " + "user; =" + se);
                }
                if ((tmList != null) && (tmList.size() > 0))
                {
                        Object[] record = null;
                        UserModel um = new UserModel();
                        for (Iterator iter = tmList.iterator(); iter.hasNext();)
                        {
                                record = (Object[]) iter.next();
                                um = (UserModel) record[1];
                                UserForm uf = new UserForm(um);
                                userList.add(uf);
                        }
                }
                return userList;
        }

        public String getUserEmail(String userID) throws UserDAOSysException
        {
                try
                {
                        Object user = HibernateDAO.load(UserModel.class, new Integer(userID));
                        if (user != null)
                        {
                                return ((UserModel) user).getMail();
                        }

                }
                catch (ULMSSysException se)
                {
                        throw new UserDAOSysException("SQLException while getUserEmail(String userID) " + "account; Serial = " + userID + " :\n" + se);
                }
                return null;
        }

        public boolean isExistUser(String userID) throws UserDAOSysException
        {
                try
                {
                        Object user = HibernateDAO.load(UserModel.class, new Integer(userID));
                        if (user != null)
                        {
                                return true;
                        }

                }
                catch (ULMSSysException se)
                {
                        throw new UserDAOSysException("SQLException while updating " + "account; Serial = " + userID + " :\n" + se);
                }
                return false;
        }

        public boolean isExistUser(String loginName, int aspID) throws UserDAOSysException
        {
                boolean isExist = false;
                int userID = 0;
                List tmList = null;
                String hql = " from UserModel  where  loginname = '" + loginName + "'";
                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new UserDAOSysException("" + e);
                }
                if (tmList != null && tmList.size() > 0)
                {
                        isExist = true;

                }
                return isExist;  //To change body of implemented methods use File | Settings | File Templates.
        }

        public int getUserID(String loginName) throws UserDAOSysException
        {
                int userID = 0;
                List tmList = null;
                String hql = " from UserModel  where  loginname = '" + loginName + "'";
                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new UserDAOSysException("" + e);
                }
                if (tmList != null && tmList.size() > 0)
                {
                        for (int i = 0; i < tmList.size(); i++)
                        {
                                userID = ((UserModel) tmList.get(i)).getUserid();
                        }
                }
                return userID;


        }

        public int getIsExistEmail(String email) throws UserDAOSysException
        {
                int userID = 0;
                List tmList = null;
                String hql = " from UserModel  where  mail = '" + email + "'";
                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new UserDAOSysException("" + e);
                }
                if (tmList != null && tmList.size() > 0)
                {
                        for (int i = 0; i < tmList.size(); i++)
                        {
                                userID = ((UserModel) tmList.get(i)).getUserid();
                        }
                }
                return userID;
        }

        public int getOrgID(int userID) throws UserDAOSysException
        {
                int orgID = 0;
                try
                {
                        Object user = HibernateDAO.load(UserModel.class, new Integer(userID));
                        if (user != null)
                        {
                                orgID = ((UserModel) user).getCatalogid();
                        }

                }
                catch (ULMSSysException se)
                {
                        throw new UserDAOSysException("SQLException while getUserEmail(String userID) " + "account; Serial = " + userID + " :\n" + se);
                }
                return orgID;
        }

        protected void closeConnection() throws UserDAOSysException
        {
                try
                {
                        if (dbConnection != null && !dbConnection.isClosed())
                        {
                                dbConnection.close();
                        }
                }
                catch (SQLException se)
                {
                        throw new UserDAOSysException("SQL Exception while closing " +
                                "DB connection : \n" + se);
                }
        }

        protected void closeResultSet(ResultSet result) throws UserDAOSysException
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
                        throw new UserDAOSysException("SQL Exception while closing " +
                                "Result Set : \n" + se);
                }
        }

        protected void closeStatement(Statement stmt) throws UserDAOSysException
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
                        throw new UserDAOSysException("SQL Exception while closing " +
                                "Statement : \n" + se);
                }
        }

        protected Connection getConnection() throws UserDAOSysException
        {
                try
                {
                        dbConnection = DBUtil.getConnection();
                }
                catch (Exception se)
                {
                        throw new UserDAOSysException("SQL Exception while getting " +
                                "DB connection : \n" + se);
                }
                return dbConnection;
        }

        protected Connection getJieFoConnection() throws UserDAOSysException
        {
                try
                {
                        dbConnection = DBUtil.getJieFoJDBCConnection();
                }
                catch (Exception se)
                {
                        throw new UserDAOSysException("SQL Exception while getting " +
                                "DB connection : \n" + se);
                }
                return dbConnection;
        }

        /**
         * Convert the resultSet object to userForm
         *
         * @param rs the resultSet which needs to convert
         * @return the wanted userForm
         */
        private UserForm convertRs2Form(ResultSet rs)
        {
                UserForm uf = new UserForm();
                int rsIndex = 1;
                try
                {
                        uf.setUserID(rs.getInt(rsIndex++));
                        uf.setLoginName(StringUtil.nullToStr(rs.getString(rsIndex++)));
                        uf.setPassword(StringUtil.nullToStr(rs.getString(rsIndex++)));
                        uf.setName(rs.getString(rsIndex++));
                        uf.setAvailable(rs.getString(rsIndex++));
                        uf.setMail(StringUtil.nullToStr(rs.getString(rsIndex++)));
                        uf.setCard(StringUtil.nullToStr(rs.getString(rsIndex++)));
                        uf.setSex(rs.getString(rsIndex++));
                        uf.setPhone(StringUtil.nullToStr(rs.getString(rsIndex++)));
                        uf.setCellPhone(StringUtil.nullToStr(rs.getString(rsIndex++)));
                        uf.setAddress(StringUtil.nullToStr(rs.getString(rsIndex++)));
                        uf.setPostalcode(StringUtil.nullToStr(rs.getString(rsIndex++)));
                        uf.setEduLevel(StringUtil.nullToStr(rs.getString(rsIndex++)));
                        uf.setPwdQuestion(StringUtil.nullToStr(rs.getString(rsIndex++)));
                        uf.setPwdAnswer(StringUtil.nullToStr(rs.getString(rsIndex++)));
                        uf.setRegisterDate(rs.getDate(rsIndex++));
                        uf.setCatalogID(rs.getInt(rsIndex++));
                        uf.setLastloginDate(rs.getDate(rsIndex++));
                        uf.setDesc0(StringUtil.nullToStr(rs.getString(rsIndex++)));
                        uf.setDesc1(StringUtil.nullToStr(rs.getString(rsIndex++)));
                        uf.setDescription(StringUtil.nullToStr(rs.getString(rsIndex++)));
                        uf.setRemark1(StringUtil.nullToStr(rs.getString(rsIndex++)));
                        uf.setRemark2(StringUtil.nullToStr(rs.getString(rsIndex++)));
                        uf.setRemark3(StringUtil.nullToStr(rs.getString(rsIndex++)));
                        uf.setRemark4(StringUtil.nullToStr(rs.getString(rsIndex++)));
                        uf.setRemark5(StringUtil.nullToStr(rs.getString(rsIndex++)));
                        uf.setRemark6(StringUtil.nullToStr(rs.getString(rsIndex++)));
                        uf.setRemark7(StringUtil.nullToStr(rs.getString(rsIndex++)));
                        uf.setRemark8(StringUtil.nullToStr(rs.getString(rsIndex++)));
                        uf.setRemark9(StringUtil.nullToStr(rs.getString(rsIndex++)));
                        uf.setPlainPassword(StringUtil.nullToStr(rs.getString(rsIndex++)));
                        uf.setExternalSystemUserID(new Integer(NumberUtils.toInt(rs.getString(rsIndex++))));
                        uf.setFax(StringUtil.nullToStr(rs.getString(rsIndex++)));
                        uf.setRemark10(StringUtil.nullToStr(rs.getString(rsIndex++)));
                        uf.setRemark11(StringUtil.nullToStr(rs.getString(rsIndex++)));
                        uf.setRemark12(StringUtil.nullToStr(rs.getString(rsIndex++)));
                }
                catch (SQLException sql)
                {
                        sql.printStackTrace();
                }
                return uf;
        }

        public int isExistIdentity(String identity) throws UserDAOSysException
        {
                int userID = 0;
                List tmList = null;
                String hql = " from UserModel  where  card = '" + identity + "'";
                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new UserDAOSysException("" + e);
                }
                if (tmList != null && tmList.size() > 0)
                {
                        for (int i = 0; i < tmList.size(); i++)
                        {
                                userID = ((UserModel) tmList.get(i)).getUserid();
                        }
                }
                return userID;  //To change body of implemented methods use File | Settings | File Templates.
        }

        public List getUserListByRole(int relationID,int roleID,int type) throws UserDAOSysException
        {
                List userList = new ArrayList();
                List tmList = new ArrayList();
                String realtionStr="";
                if(relationID!=0)
                {
                   realtionStr=" and um.catalogid ="+relationID;
                }
                String hql = " from UserModel  as um , SecUserRoleModel as srm" +
                        " where um.available=1 and  um.catalogid = srm.comp_id.relationID  and um.userid = srm.comp_id.userID " + realtionStr+
                        //"  and  um.catalogid = "+relationID+"" +
                        "  and  srm.comp_id.roleID =" + roleID+"" +
                        "  and srm.comp_id.type="+type+" order by um.remark6 asc,um.registerdate desc,um.userid desc";
                try
                {
                        tmList = HibernateDAO.find(hql);

                }
                catch (ULMSSysException se)
                {
                        throw new UserDAOSysException("SQLException while getAspUserList(int aspID) " + "user; =" + se);
                }
                if ((tmList != null) && (tmList.size() > 0))
                {
                        Object[] record = null;
                        UserModel um = new UserModel();
                        for (Iterator iter = tmList.iterator(); iter.hasNext();)
                        {
                                record = (Object[]) iter.next();
                                um = (UserModel) record[0];
                                UserForm uf = new UserForm(um);
                                userList.add(uf);
                        }
                }
                return userList;

        }

        public List getMaster(int relationID,int roleID,int type) throws UserDAOSysException
        {
                List userList = new ArrayList();
                List tmList = new ArrayList();
                String realtionStr="";
                if(relationID!=0)
                {
                   realtionStr=" and um.catalogid ="+relationID;
                }
                String hql = " from UserModel  as um , SecUserRoleModel as srm" +
                        " where um.available=1 and  um.catalogid = srm.comp_id.relationID  and um.userid = srm.comp_id.userID " + realtionStr+
                        //"  and  um.catalogid = "+relationID+"" +
                        "  and  srm.comp_id.roleID =" + roleID+"" +
                        "  and srm.comp_id.type="+type+" order by um.userid desc ";
                try
                {
                        tmList = HibernateDAO.find(hql);

                }
                catch (ULMSSysException se)
                {
                        throw new UserDAOSysException("SQLException while getAspUserList(int aspID) " + "user; =" + se);
                }
                if ((tmList != null) && (tmList.size() > 0))
                {
                        Object[] record = null;
                        UserModel um = new UserModel();
                        for (Iterator iter = tmList.iterator(); iter.hasNext();)
                        {
                                record = (Object[]) iter.next();
                                um = (UserModel) record[0];
                                UserForm uf = new UserForm(um);
                                userList.add(uf);
                        }
                }
                return userList;

        }

    public List getUserByRegisterDate(int relationID,int roleID,int type) throws UserDAOSysException
    {
            List userList = new ArrayList();
            List tmList = new ArrayList();
            String realtionStr="";
            if(relationID!=0)
            {
               realtionStr=" and um.catalogid ="+relationID;
            }
            String hql = " from UserModel  as um , SecUserRoleModel as srm" +
                    " where um.available=1 and  um.catalogid = srm.comp_id.relationID  and um.userid = srm.comp_id.userID " + realtionStr+
                    //"  and  um.catalogid = "+relationID+"" +
                    "  and  srm.comp_id.roleID =" + roleID+"" +
                    "  and srm.comp_id.type="+type+" order by um.registerdate desc ";
            try
            {
                    tmList = HibernateDAO.find(hql);

            }
            catch (ULMSSysException se)
            {
                    throw new UserDAOSysException("SQLException while getAspUserList(int aspID) " + "user; =" + se);
            }
            if ((tmList != null) && (tmList.size() > 0))
            {
                    Object[] record = null;
                    UserModel um = new UserModel();
                    for (Iterator iter = tmList.iterator(); iter.hasNext();)
                    {
                            record = (Object[]) iter.next();
                            um = (UserModel) record[0];
                            UserForm uf = new UserForm(um);
                            userList.add(uf);
                    }
            }
            return userList;

    }


}


