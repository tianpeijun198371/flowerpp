/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-8-20
 * Time: 10:09:19
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.admin.colsignup.colsign.dao;

import com.ulearning.ulms.admin.colsignup.colsign.exceptions.ColSignDAOSysException;
import com.ulearning.ulms.admin.colsignup.colsign.form.ColSignForm;
import com.ulearning.ulms.admin.colsignup.colsign.model.ColSignModel;
import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.util.HibernateDAO;
import com.ulearning.ulms.util.log.LogUtil;

import java.io.Serializable;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ColSignDAOImpl implements ColSignDAO
{
        public int addColSign(ColSignForm details) throws ColSignDAOSysException
        {
                details.setCreateTime(new Date());

                int colSignID = 0;

                try
                {
                        Serializable s = HibernateDAO.add(details.getColSignModel());
                        colSignID = Integer.parseInt(s.toString());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new ColSignDAOSysException(
                                "SQLException while insert colsign");
                }

                return colSignID;
        }

        public void updateColSign(ColSignForm csf) throws ColSignDAOSysException
        {
                int colSignID = csf.getColSignID();
                ColSignForm newCsf = getColSign(colSignID);

                csf.setCreateTime(newCsf.getCreateTime());
                csf.setCreator(newCsf.getCreator());
                csf.setOrgID(newCsf.getOrgID());

                try
                {
                        HibernateDAO.update(csf.getColSignModel());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new ColSignDAOSysException(
                                "SQLException while update  colsign colSignID = " +
                                        csf.getColSignID());
                }
        }

        public void removeColSign(int ColSignID) throws ColSignDAOSysException
        {
                String hql = " from ColSignModel where colSignID = " + ColSignID;
                LogUtil.debug("colSign", "[ColSignDAOImpl] " + hql);

                try
                {
                        HibernateDAO.delete(hql);
                }
                catch (ULMSSysException se)
                {
                        se.printStackTrace();
                        throw new ColSignDAOSysException(
                                "SQLException while updating colsign; ColSignID = " +
                                        ColSignID + " :\n" + se);
                }
        }

        public ColSignForm getColSign(int ColSignID) throws ColSignDAOSysException
        {
                ColSignForm csf = null;
                String hql = " from ColSignModel where colSignID = " + ColSignID;
                LogUtil.debug("colSign", "[ColSignDAOImpl] " + hql);

                try
                {
                        ColSignModel csm = null;
                        List tmList = HibernateDAO.find(hql);

                        if ((tmList != null) && (tmList.size() > 0))
                        {
                                csm = (ColSignModel) tmList.get(0);
                                csf = new ColSignForm(csm);
                        }
                }
                catch (ULMSSysException se)
                {
                        se.printStackTrace();
                        throw new ColSignDAOSysException(
                                "SQLException while updating colsign; ColSignID = " +
                                        ColSignID + " :\n" + se);
                }

                return csf;
        }

        public List getColSignList(int orgID, int isSubmit)
                throws ColSignDAOSysException
        {
                ArrayList colSignList = new ArrayList();
                ColSignForm csf = null;
                String isSubmitStr = "";

                if (isSubmit != -1)
                {
                        isSubmitStr = " and isSubmit = '" + isSubmit + "'";
                }

                String hql = " from ColSignModel where " + " orgID = " + orgID +
                        isSubmitStr + " order by createTime desc,isSubmit ,approved";

                LogUtil.debug("colSign", "[ColSignDAOImpl] " + hql);

                try
                {
                        ColSignModel csm = null;
                        List tmList = HibernateDAO.find(hql);

                        for (int i = 0; i < tmList.size(); i++)
                        {
                                csm = (ColSignModel) tmList.get(i);
                                csf = new ColSignForm(csm);
                                colSignList.add(csf);
                        }
                }
                catch (ULMSSysException se)
                {
                        se.printStackTrace();
                        throw new ColSignDAOSysException(
                                "SQLException while updating colsign; orgID = " + orgID +
                                        " : isSubmit = " + isSubmit + "\n" + se);
                }

                return colSignList;
        }

        public void submitColSign(int ColSignID, String isSubmit)
                throws ColSignDAOSysException
        {
                ColSignForm newCsf = getColSign(ColSignID);
                newCsf.setIsSubmit(isSubmit);

                /*strSql = " ColSignModel Set isSubmit = '"
            + isSubmit + "' Where ColSignID = " + ColSignID;
            LogUtil.debug("colSign", "[ColSignDAOImpl] " + strSql);*/
                try
                {
                        HibernateDAO.update(newCsf.getColSignModel());
                }
                catch (ULMSSysException se)
                {
                        se.printStackTrace();
                        throw new ColSignDAOSysException(
                                "SQLException while updating colsign; ColSignID = " +
                                        ColSignID + " : isSubmit = " + isSubmit + "\n" + se);
                }
        }

        public void approvedColSign(int ColSignID, String Approved)
                throws ColSignDAOSysException
        {
                ColSignForm newCsf = getColSign(ColSignID);
                newCsf.setApproved(Approved);

                /*strSql = " ColSignModel Set isSubmit = '"
            + isSubmit + "' Where ColSignID = " + ColSignID;
            LogUtil.debug("colSign", "[ColSignDAOImpl] " + strSql);*/
                try
                {
                        HibernateDAO.update(newCsf.getColSignModel());
                }
                catch (ULMSSysException se)
                {
                        se.printStackTrace();
                        throw new ColSignDAOSysException(
                                "SQLException while updating colsign; ColSignID = " +
                                        ColSignID + " : Approved = " + Approved + "\n" + se);
                }
        }

        public int totalNumInOrg(int orgID, int approved)
                throws ColSignDAOSysException
        {
                int total = 0;
                String hql = "";
                String approveStr = "";

                if (approved != -1)
                {
                        approveStr = " And issubmit = '" + approved + "'";
                }

                hql = "Select count(*) From ColSignModel " + " Where orgID = " + orgID +
                        approveStr;
                LogUtil.debug("colSign", "[ColSignDAOImpl] " + hql);

                try
                {
                        total = HibernateDAO.count(hql);
                }
                catch (ULMSSysException se)
                {
                        se.printStackTrace();
                        throw new ColSignDAOSysException(
                                "SQLException while Query total  colsign; orgID = " + orgID +
                                        " : approved = " + approved + "\n" + se);
                }

                return total;
        }

        public int totalNumInColSign(int ColSignID, int approved)
                throws ColSignDAOSysException /*{
           int total = 0;
           String strSql = "";
           String approveStr = "";
           if (approved != -1)
           {
                   approveStr = " And ccs.Approved = '" + approved +"'";
           }
           strSql = "Select count(*) total From C_ColSign_Tab cc,C_ColSignDetail_Tab ccd,C_ColStudent_Tab ccs"
                   + " Where cc.ColSignID = ccd.ColSignID "
                   + " and ccd.ColSignDetailID = ccs.ColSignDetailID "
                   + " and cc.ColSignID = " + ColSignID
                   + approveStr;
           LogUtil.debug("colSign", "[ColSignDAOImpl] " + strSql);
           try
           {
                   if (rs != null && rs.next())
                   {
                           total = rs.getInt("total");
                   }
           }
           catch (ULMSSysException se)
           {
                   se.printStackTrace();
                   throw new ColSignDAOSysException("SQLException while Query total Student; ColSignID = " + ColSignID + " : approved = " + approved + "\n" + se);
           }
           catch (SQLException se)
           {
                   se.printStackTrace();
                   throw new ColSignDAOSysException("SQLException while Query total Student; ColSignID = " + ColSignID + " : approved = " + approved + "\n" + se);
           }
           finally
           {
                   try
                   {
                           {
                           }
                   }
                   catch (Exception e)
                   {
                   }
           }
           return total;
           }*/
        {
                int total = 0;
                String strSql = "";
                String approveStr = "";

                if (approved != -1)
                {
                        approveStr = " And ccs.Approved = '" + approved + "'";
                }

                strSql = "Select count(*) total From ColSignModel cc,ColSignDetailModel ccd,ColStudentModel ccs" +
                        " Where cc.ColSignID = ccd.ColSignID " +
                        " and ccd.ColSignDetailID = ccs.ColSignDetailID " +
                        " and cc.ColSignID = " + ColSignID + approveStr;

                LogUtil.debug("colSign", "[ColSignDAOImpl] " + strSql);

                try
                {
                        total = HibernateDAO.count(strSql);
                }
                catch (ULMSSysException se)
                {
                        se.printStackTrace();
                        throw new ColSignDAOSysException(
                                "SQLException while Query total Student; ColSignID = " +
                                        ColSignID + " : approved = " + approved + "\n" + se);
                }

                return total;
        }

        public int totalNumInColSignByFee(int colSignID, int feeState)
                throws ColSignDAOSysException
        {
                /*int total = 0;
               String hql = "";
               String approveStr = "";
               if(approved != -1)
               approveStr = " And ColStudentModel.Approved = " + approved;
               hql = " select count(*)  From C_ColSign_Tab as csm ,ColSignDetailModel ,ColStudentModel "
                       +" Where ColSignModel.colSignID = ColSignDetailModel.colSignID "
                       +" and ColSignDetailModel.colSignDetailID = ColStudentModel.colSignDetailID "
                       +" and ColSignModel.colSignID = " + ColSignID
                       + approveStr;
               LogUtil.debug("colSign", "[ColSignDAOImpl] " + hql);
               try
               {
                       if(rs != null && rs.next())
                               total = rs.getInt("total");
                       total = HibernateDAO.count(hql);
               }catch (ULMSSysException se)
               {
                   se.printStackTrace();
                   throw new ColSignDAOSysException("SQLException while Query total Student; ColSignID = " + ColSignID + " : approved = "+approved + "\n" + se);
               }
               return total;*/
                int total = 0;
                String strSql = "";
                String feeStateStr = "";

                if (feeState != -1)
                {
                        feeStateStr = " And ccs.feeState = '" + feeState + "'";
                }

                strSql = "Select count(*) total From C_ColSign_Tab cc,C_ColSignDetail_Tab ccd,C_ColStudent_Tab ccs" +
                        " Where cc.ColSignID = ccd.ColSignID " +
                        " and ccd.ColSignDetailID = ccs.ColSignDetailID " +
                        " and cc.ColSignID = " + colSignID + feeStateStr;

                LogUtil.debug("colSign",
                        "[ColSignDAOImpl]totalNumInColSignByFee----strSql=" + strSql);

                try
                {
                        total = HibernateDAO.count(strSql);
                }
                catch (ULMSSysException se)
                {
                        se.printStackTrace();
                        throw new ColSignDAOSysException(
                                "SQLException while Query total Student; ColSignID = " +
                                        colSignID + " : approved = " + feeStateStr + "\n" + se);
                }

                return total;
        }

        private ColSignForm convertRs2Form(ResultSet rs)
        {
                ColSignForm csf = new ColSignForm();
                int rsIndex = 1;

                try
                {
                        if (rs != null)
                        {
                                csf.setColSignID(rs.getInt(rsIndex++));
                                csf.setName(rs.getString(rsIndex++));
                                csf.setOrgID(rs.getInt(rsIndex++));
                                csf.setDescription(rs.getString(rsIndex++));
                                csf.setCreateTime(rs.getDate(rsIndex++));
                                csf.setCreateTimeStr(rs.getString((rsIndex - 1)));
                                csf.setCreator(rs.getInt(rsIndex++));
                                csf.setIsSubmit(rs.getString(rsIndex++));
                                csf.setApproved(rs.getString(rsIndex++));
                                csf.setDesc1(rs.getString(rsIndex++));
                        }
                }
                catch (SQLException sql)
                {
                        sql.printStackTrace();
                }

                return csf;
        }
}
