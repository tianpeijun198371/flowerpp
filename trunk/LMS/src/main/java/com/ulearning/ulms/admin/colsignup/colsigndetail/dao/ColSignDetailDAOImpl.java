/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-8-20
 * Time: 10:09:19
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.admin.colsignup.colsigndetail.dao;

import com.ulearning.ulms.admin.colsignup.colsigndetail.exceptions.ColSignDetailDAOSysException;
import com.ulearning.ulms.admin.colsignup.colsigndetail.form.ColSignDetailForm;
import com.ulearning.ulms.admin.colsignup.colsigndetail.model.ColSignDetailModel;
import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.core.security.bean.SecurityConstants;
import com.ulearning.ulms.util.HibernateDAO;
import com.ulearning.ulms.util.log.LogUtil;

import java.io.Serializable;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;


public class ColSignDetailDAOImpl implements ColSignDetailDAO
{
        public int addColSignDetail(ColSignDetailForm csdf)
                throws ColSignDetailDAOSysException
        {
                int colSignDetailID = 0;

                try
                {
                        Serializable s = HibernateDAO.add(csdf.getColSignDetailModel());
                        colSignDetailID = Integer.parseInt(s.toString());
                }
                catch (ULMSSysException se)
                {
                        se.printStackTrace();
                        throw new ColSignDetailDAOSysException(
                                "SQLException while insert colsignDetial" + se);
                }

                return colSignDetailID;
        }

        public void updateColSignDetail(ColSignDetailForm csdf)
                throws ColSignDetailDAOSysException
        {
                //
        }

        public void removeColSignDetail(int ColSignDetailID)
                throws ColSignDetailDAOSysException
        {
                String hql = " from ColSignDetailModel Where colSignDetailID = " +
                        ColSignDetailID;

                LogUtil.debug("colSignDetail", "[ColSignDetailDAOImpl] " + hql);

                try
                {
                        HibernateDAO.delete(hql);
                }
                catch (ULMSSysException se)
                {
                        se.printStackTrace();
                        throw new ColSignDetailDAOSysException(
                                "SQLException while remove C_ColSignDetail_Tab; ColSignDetailID = " +
                                        ColSignDetailID + " :\n" + se);
                }
        }

        public ColSignDetailForm getColSignDetail(int ColSignDetailID)
                throws ColSignDetailDAOSysException
        {
                ColSignDetailForm csdf = null;

                String hql = " from  ColSignDetailModel Where colSignDetailID = " +
                        ColSignDetailID;
                LogUtil.debug("colSignDetail", "[ColSignDetailDAOImpl] " + hql);

                try
                {
                        ColSignDetailModel csdm = null;
                        List tmList = HibernateDAO.find(hql);

                        if ((tmList != null) && (tmList.size() > 0))
                        {
                                csdm = (ColSignDetailModel) tmList.get(0);
                                csdf = new ColSignDetailForm(csdm);
                        }
                }
                catch (ULMSSysException se)
                {
                        se.printStackTrace();
                        throw new ColSignDetailDAOSysException(
                                "SQLException while Query C_ColSignDetail_Tab; ColSignDetailID = " +
                                        ColSignDetailID + " :\n" + se);
                }

                return csdf;
        }

        public List getColSignDetailList(int ColSignID)
                throws ColSignDetailDAOSysException
        {
                ArrayList list = new ArrayList();
                ColSignDetailForm csdf = null;
                String hql = " from  ColSignDetailModel Where colSignID = " +
                        ColSignID;

                LogUtil.debug("colSignDetail", "[ColSignDetailDAOImpl] " + hql);

                try
                {
                        ColSignDetailModel csdm = null;
                        List tmList = HibernateDAO.find(hql);

                        for (int i = 0; i < tmList.size(); i++)
                        {
                                csdm = (ColSignDetailModel) tmList.get(i);
                                csdf = new ColSignDetailForm(csdm);
                                list.add(csdf);
                        }
                }
                catch (ULMSSysException se)
                {
                        se.printStackTrace();
                        throw new ColSignDetailDAOSysException(
                                "SQLException while Query C_ColSignDetail_Tab; ColSignID = " +
                                        ColSignID + " :\n" + se);
                }

                return list;
        }

        public boolean isExist(int ColSignID, int RelationID, int Type)
                throws ColSignDetailDAOSysException
        {
                boolean isExist = false;
                String hql = "";
                hql = " from ColSignDetailModel Where ColSignID = " + ColSignID +
                        " And relationID = " + RelationID + " And typeID = " + Type;
                LogUtil.debug("colSignDetail", "[ColSignDetailDAOImpl] " + hql);

                try
                {
                        List tmList = HibernateDAO.find(hql);

                        if ((tmList != null) && (tmList.size() > 0))
                        {
                                isExist = true;
                        }
                }
                catch (ULMSSysException se)
                {
                        se.printStackTrace();
                        throw new ColSignDetailDAOSysException(
                                "SQLException while Query C_ColSignDetail_Tab; ColSignID = " +
                                        ColSignID + " :\n" + se);
                }

                return isExist;
        }

        public int ColSignStudentNum(int ColSignDetailID, int approved)
                throws ColSignDetailDAOSysException
        {
                int total = 0;
                String hql = "";
                String approvedStr = "";

                if (approved != -1)
                {
                        approvedStr = " And CCS.Approved = '" + approved + "'";
                }

                hql = "Select count(*) from ColSignDetailModel  CCD, ColStudentModel  CCS " +
                        " Where CCD.colSignDetailID = CCS.colSignDetailID " +
                        " And CCD.colSignDetailID = " + ColSignDetailID +
                        " And CCS.type = " + SecurityConstants.USER_DEFAULT_RELATION +
                        approvedStr;

                try
                {
                        total = HibernateDAO.count(hql);
                }
                catch (ULMSSysException se)
                {
                        se.printStackTrace();
                        throw new ColSignDetailDAOSysException(
                                "SQLException while Count Number Of Student; ColSignDetailID = " +
                                        ColSignDetailID + " :\n" + se);
                }

                return total;
        }

        private ColSignDetailForm convertRs2Form(ResultSet rs)
        {
                ColSignDetailForm csdf = new ColSignDetailForm();
                int rsIndex = 1;

                try
                {
                        csdf.setColSignDetailID(rs.getInt(rsIndex++));
                        csdf.setColSignID(rs.getInt(rsIndex++));
                        csdf.setRelationID(rs.getInt(rsIndex++));
                        csdf.setTypeID(rs.getInt(rsIndex++));
                        csdf.setDesc1(rs.getString(rsIndex++));
                }
                catch (SQLException sql)
                {
                        sql.printStackTrace();
                }

                return csdf;
        }

        //public void removeColSign(int ColSignID)throws ColSignDetailDAOSysException;
}
