/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-8-20
 * Time: 10:09:19
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.admin.colsignup.colstudent.dao;

import com.ulearning.ulms.admin.colsignup.colstudent.exceptions.ColStudentDAOSysException;
import com.ulearning.ulms.admin.colsignup.colstudent.form.ColStudentForm;
import com.ulearning.ulms.admin.colsignup.colstudent.model.ColStudentModel;
import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.core.security.bean.SecurityConstants;
import com.ulearning.ulms.user.form.UserForm;
import com.ulearning.ulms.util.HibernateDAO;
import com.ulearning.ulms.util.OperateDB;
import com.ulearning.ulms.util.log.LogUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;


public class ColStudentDAOImpl implements ColStudentDAO
{
        public void addColStudent(ColStudentForm csf)
                throws ColStudentDAOSysException
        {
                try
                {
                        HibernateDAO.add(csf.getColStudentModel());
                        LogUtil.debug("course",
                                "[ColStudentDAOImpl]addColStudent-- " + "一共插入 1 行");
                }
                catch (ULMSSysException se)
                {
                        se.printStackTrace();
                        throw new ColStudentDAOSysException(
                                "SQLException while Insert Into C_ColStudent_Tab;  :\n" + se);
                }
        }

        public void updateColStudent(ColStudentForm csf)
                throws ColStudentDAOSysException
        {
                try
                {
                        HibernateDAO.update(csf.getColStudentModel());
                        LogUtil.debug("course",
                                "[ColStudentDAOImpl]updateColStudent-- " + "一共修改 1 行");
                }
                catch (ULMSSysException se)
                {
                        se.printStackTrace();
                        throw new ColStudentDAOSysException(
                                "SQLException while Update C_ColStudent_Tab;  :\n" + se);
                }
        }

        public void removeColStudent(int ColSignDetailID, int RelationID, int Type)
                throws ColStudentDAOSysException
        {
                String hql = " from ColStudentModel where " + " ColSignDetailID = " +
                        ColSignDetailID + " and RelationID = " + RelationID +
                        " and Type = " + Type;
                LogUtil.debug("course", "[ColStudentDAOImpl] " + hql);

                try
                {
                        HibernateDAO.delete(hql);
                        LogUtil.debug("course",
                                "[ColStudentDAOImpl]removeColStudent--- " + "一共删除 1 行");
                }
                catch (ULMSSysException se)
                {
                        se.printStackTrace();
                        throw new ColStudentDAOSysException(
                                "SQLException while Insert Into C_ColStudent_Tab;  :\n" + se);
                }
        }

        public List getColStudentList(int userID, int relationID, int type)
                throws ColStudentDAOSysException
        {
                String strSql = "";
                ArrayList list = new ArrayList();
                ColStudentForm csf = null;
                ResultSet rs = null;
                OperateDB operateDB = new OperateDB();

                strSql = "select CCS.ColSignDetailID,CCSD.RelationID,CCSD.TypeID as Type," +
                        "CCS.RelationID as UserID " +
                        "from C_ColStudent_Tab CCS,C_ColSignDetail_Tab CCSD " +
                        "where CCS.ColSignDetailID=CCSD.ColSignDetailID " +
                        "and CCSD.RelationID=" + relationID + " and CCSD.TypeID=" + +type +
                        " and CCS.RelationID=" + userID;

                LogUtil.debug("course",
                        "[ColStudentDAOImpl]getColStudentList--- " + strSql);

                try
                {
                        rs = operateDB.exequery(strSql);

                        while ((rs != null) && rs.next())
                        {
                                csf = new ColStudentForm();
                                csf.setColSignDetailID(rs.getInt("ColSignDetailID"));
                                list.add(csf);
                        }
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new ColStudentDAOSysException(
                                "SQLException while Insert Into C_ColStudent_Tab;  :\n" + se);
                }
                catch (ULMSSysException se)
                {
                        se.printStackTrace();
                        throw new ColStudentDAOSysException(
                                "SQLException while Insert Into C_ColStudent_Tab;  :\n" + se);
                }
                finally
                {
                        try
                        {
                                if (rs != null)
                                {
                                        rs.close();
                                }

                                if (operateDB != null)
                                {
                                        operateDB.closeDB();
                                }
                        }
                        catch (Exception e)
                        {
                        }
                }

                return list;
        }

        public List getColStudentList(int ColSignDetailID, int Type)
                throws ColStudentDAOSysException
        {
                String strSql = "";
                ArrayList list = new ArrayList();
                ColStudentForm csf = null;
                ResultSet rs = null;
                OperateDB operateDB = new OperateDB();

                if (Type == SecurityConstants.USER_DEFAULT_RELATION)
                {
                        strSql = "Select CC.* ,UU.Name,UU.LoginName,UU.Mail,UU.Sex,UU.Phone,UU.Cellphone,TT.OrgName,CC.FeeState From " +
                                " C_ColStudent_Tab CC,U_User_Tab UU,TM_Org_Tab TT Where " +
                                " CC.RelationID = UU.UserID " +
                                " AND UU.CatalogID = TT.OrgID " + " AND CC.Type = " + Type +
                                " AND CC.ColSignDetailID = " + ColSignDetailID;

                        LogUtil.debug("course", "[ColStudentDAOImpl] " + strSql);

                        try
                        {
                                rs = operateDB.exequery(strSql);

                                while ((rs != null) && rs.next())
                                {
                                        csf = new ColStudentForm();
                                        csf.setColSignDetailID(ColSignDetailID);
                                        csf.setType(Type);
                                        csf.setApproved(rs.getString("Approved"));
                                        csf.setRelationID(rs.getInt("RelationID"));
                                        csf.setOrgName(rs.getString("OrgName"));
                                        csf.setEmail(rs.getString("Mail"));
                                        csf.setLogInName(rs.getString("LoginName"));
                                        csf.setName(rs.getString("Name"));
                                        csf.setSex(rs.getString("Sex"));
                                        csf.setPhone(rs.getString("Phone"));
                                        csf.setFeeState(rs.getString("FeeState"));
                                        list.add(csf);
                                }
                        }
                        catch (SQLException se)
                        {
                                se.printStackTrace();
                                throw new ColStudentDAOSysException(
                                        "SQLException while Insert Into C_ColStudent_Tab;  :\n" +
                                                se);
                        }
                        catch (ULMSSysException se)
                        {
                                se.printStackTrace();
                                throw new ColStudentDAOSysException(
                                        "SQLException while Insert Into C_ColStudent_Tab;  :\n" +
                                                se);
                        }
                        finally
                        {
                                try
                                {
                                        if (rs != null)
                                        {
                                                rs.close();
                                        }

                                        if (operateDB != null)
                                        {
                                                operateDB.closeDB();
                                        }
                                }
                                catch (Exception e)
                                {
                                }
                        }
                }

                return list;
        }

        public int getStudentNumber(int orgID, int ColSignDetailID, int Type)
                throws ColStudentDAOSysException
        {
                int total = 0;
                String strSql = "";
                ResultSet rs = null;
                OperateDB operateDB = new OperateDB();

                strSql = "select count(*) From C_ColStudent_Tab CC, U_User_Tab UU ,TM_ORGUSER_TAB ORG,TM_Org_Tab TT" +
                        " Where CC.RelationID = UU.UserID " +
                        " And ORG.userID = UU.userID" + " And (ORG.orgID = " + orgID +
                        " or ORG.orgID in(select orgID from tm_org_tab where ParentID=" +
                        orgID + "))" + " AND UU.CatalogID = TT.OrgID And UU.userID >= 100" +
                        " And CC.ColSignDetailID = " + ColSignDetailID + " And CC.Type = " +
                        Type;

                LogUtil.debug("course", "[ColStudentDAOImpl] " + strSql);

                try
                {
                        rs = operateDB.exequery(strSql);

                        if ((rs != null) && rs.next())
                        {
                                total = rs.getInt(1);
                        }
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new ColStudentDAOSysException(
                                "SQLException while Insert Into C_ColStudent_Tab;  :\n" + se);
                }
                catch (ULMSSysException se)
                {
                        se.printStackTrace();
                        throw new ColStudentDAOSysException(
                                "SQLException while Insert Into C_ColStudent_Tab;  :\n" + se);
                }
                finally
                {
                        try
                        {
                                if (rs != null)
                                {
                                        rs.close();
                                }

                                if (operateDB != null)
                                {
                                        operateDB.closeDB();
                                }
                        }
                        catch (Exception e)
                        {
                        }
                }

                return total;
        }

        /**
         * @param colSignDetailID
         * @param relationID
         * @param type
         * @return
         * @throws ColStudentDAOSysException
         */
        public ColStudentForm getColStudent(int colSignDetailID, int relationID,
                                            int type) throws ColStudentDAOSysException
        {
                ColStudentModel csm = null;
                ColStudentForm csf1 = null;
                String hql = " from ColStudentModel Where " + " ColSignDetailID = " +
                        colSignDetailID + " And RelationID = " + relationID +
                        " And Type = " + type;

                LogUtil.debug("course", "[ColStudentDAOImpl] " + hql);

                try
                {
                        /*ResultSet rs = operateDB.exequery(strSql);
              if(rs != null && rs.next())
                      isExist = true;*/
                        List tmList = HibernateDAO.find(hql);

                        if ((tmList != null) && (tmList.size() > 0))
                        {
                                csm = (ColStudentModel) tmList.get(0);
                                csf1 = new ColStudentForm(csm);
                        }
                }
                catch (ULMSSysException se)
                {
                        se.printStackTrace();
                        throw new ColStudentDAOSysException(
                                "SQLException while Insert Into C_ColStudent_Tab;  :\n" + se);
                }

                return csf1;
        }

        public List getStuOfOrg(int orgID, int ColSignDetailID, int type)
                throws ColStudentDAOSysException
        {
                List list = new ArrayList();
                String strSql = "";
                String typeStr = "";

                if (type != -1)
                {
                        typeStr = " And CC.Type = " + type;
                }

                ResultSet rs = null;
                OperateDB operateDB = new OperateDB();

                int userID = 0;
                strSql = "select CC.RelationID From C_ColStudent_Tab CC, U_User_Tab UU ,TM_ORGUSER_TAB ORG" +
                        " Where CC.RelationID = UU.UserID " +
                        " And ORG.userID = UU.userID" + " And ORG.orgID = " + orgID +
                        " And UU.userID >= 100" + " And CC.ColSignDetailID = " +
                        ColSignDetailID + typeStr;

                LogUtil.debug("course", "[ColStudentDAOImpl] " + strSql);

                try
                {
                        rs = operateDB.exequery(strSql);

                        while ((rs != null) && rs.next())
                        {
                                UserForm uf = new UserForm();
                                userID = rs.getInt("RelationID");
                                uf.setUserID(userID);
                                list.add(uf);
                        }
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new ColStudentDAOSysException(
                                "SQLException while Insert Into C_ColStudent_Tab;  :\n" + se);
                }
                catch (ULMSSysException se)
                {
                        se.printStackTrace();
                        throw new ColStudentDAOSysException(
                                "SQLException while Insert Into C_ColStudent_Tab;  :\n" + se);
                }
                finally
                {
                        try
                        {
                                if (rs != null)
                                {
                                        rs.close();
                                }

                                if (operateDB != null)
                                {
                                        operateDB.closeDB();
                                }
                        }
                        catch (Exception e)
                        {
                        }
                }

                return list;
        }
}
