/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-7-2
 * Time: 10:54:54
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.admin.webconfig.webcustom.dao;

import com.ulearning.ulms.admin.webconfig.webcustom.bean.CustomTypeConstants;
import com.ulearning.ulms.admin.webconfig.webcustom.exceptions.WebCustomDAOSysException;
import com.ulearning.ulms.admin.webconfig.webcustom.form.WebCustomForm;
import com.ulearning.ulms.admin.webconfig.webcustom.model.WebCustomModel;
import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.util.HibernateDAO;
import com.ulearning.ulms.util.log.LogUtil;

import java.io.Serializable;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class WebCustomDAOImpl implements WebCustomDAO
{
        protected String strSql = "";

        public int addWebCustom(WebCustomForm details)
                throws WebCustomDAOSysException
        {
                Serializable s = null;

                try
                {
                        s = HibernateDAO.add(details.getWebCustomModel());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new WebCustomDAOSysException("" + e);
                }

                return s.hashCode();
        }

        public void updateWebCustom(WebCustomForm details)
                throws WebCustomDAOSysException
        {
                try
                {
                        HibernateDAO.update(details.getWebCustomModel());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new WebCustomDAOSysException("" + e);
                }
        }

        /**
         * @param relationType
         * @return
         * @throws WebCustomDAOSysException
         */
        public List getWebCustomList(String relationType)
                throws WebCustomDAOSysException
        {
                WebCustomForm bf = new WebCustomForm();
                WebCustomModel bm = null;
                ArrayList webCustomList = new ArrayList();
                List tmList = null;
                String hql = " from WebCustomModel where  relationType ='" +
                        relationType + "' ";

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new WebCustomDAOSysException("" + e);
                }

                for (int i = 0; i < tmList.size(); i++)
                {
                        bm = (WebCustomModel) tmList.get(i);
                        webCustomList.add(bf.getWebCustomForm(bm));
                }

                return webCustomList;
        }

        /**
         * @param relationID
         * @return
         * @throws WebCustomDAOSysException
         */
        public List getWebCustomList(int relationID)
                throws WebCustomDAOSysException
        {
                WebCustomForm bf = new WebCustomForm();
                WebCustomModel bm = null;
                ArrayList webCustomList = new ArrayList();
                List tmList = null;
                String hql = " from WebCustomModel where  relationID = " + relationID;

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new WebCustomDAOSysException("" + e);
                }

                for (int i = 0; i < tmList.size(); i++)
                {
                        bm = (WebCustomModel) tmList.get(i);
                        webCustomList.add(bf.getWebCustomForm(bm));
                }

                return webCustomList;
        }

        /**
         * @param relationID
         * @param relationType
         * @return
         * @throws WebCustomDAOSysException
         */
        public List getWebCustomList(int relationID, String relationType)
                throws WebCustomDAOSysException
        {
                WebCustomForm bf = new WebCustomForm();
                WebCustomModel bm = null;
                ArrayList webCustomList = new ArrayList();
                List tmList = null;
                String hql = " from WebCustomModel where  relationType ='" +
                        relationType + "' and relationID = " + relationID;

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new WebCustomDAOSysException("" + e);
                }

                for (int i = 0; i < tmList.size(); i++)
                {
                        bm = (WebCustomModel) tmList.get(i);
                        webCustomList.add(bf.getWebCustomForm(bm));
                }

                return webCustomList;
        }

        /**
         * @param relationID
         * @param relationType
         * @return
         * @throws WebCustomDAOSysException
         */
        public WebCustomForm getLastWebCustom(int relationID, String relationType)
                throws WebCustomDAOSysException
        {
                WebCustomForm bf = new WebCustomForm();
                WebCustomForm res = null;
                List tmList = null;
                String hql = " from WebCustomModel where relationType ='" +
                        relationType + "' and relationID = " + relationID +
                        " order by updateDate desc";

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new WebCustomDAOSysException("" + e);
                }

                if ((tmList != null) && (tmList.size() > 0))
                {
                        WebCustomModel bm = (WebCustomModel) tmList.get(0);
                        res = bf.getWebCustomForm(bm);
                }

                return res;
        }

        /**
         * @param CustomID
         * @throws WebCustomDAOSysException
         */
        public void removeWebCustom(int CustomID) throws WebCustomDAOSysException
        {
                String hql = " from WebCustomModel where CustomID = " + CustomID;

                try
                {
                        if (!hql.equals(""))
                        {
                                HibernateDAO.delete(hql);
                        }
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new WebCustomDAOSysException("" + e);
                }
        }

        /**
         * @param CustomID
         * @return
         * @throws WebCustomDAOSysException
         */
        public WebCustomForm getWebCustom(int CustomID)
                throws WebCustomDAOSysException
        {
                WebCustomForm bf = new WebCustomForm();
                WebCustomForm res = null;
                List tmList = null;
                String hql = " from WebCustomModel where CustomID = " + CustomID;

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new WebCustomDAOSysException("" + e);
                }

                if ((tmList != null) && (tmList.size() > 0))
                {
                        WebCustomModel bm = (WebCustomModel) tmList.get(0);
                        res = bf.getWebCustomForm(bm);
                }

                return res;
        }

        public HashMap getMyConfig(int userID, int orgID, int aspID)
                throws WebCustomDAOSysException
        {
                HashMap properties = new HashMap();
                WebCustomForm wcf = null;

                try
                {
                        wcf = getLastWebCustom(userID,
                                CustomTypeConstants.CUSTOM_USER_SELECT_TYPE);

                        if (wcf == null)
                        {
                                wcf = getLastWebCustom(aspID,
                                        CustomTypeConstants.CUSTOM_ASP_SELECT_TYPE);
                        }
                }
                catch (ULMSSysException se)
                {
                        throw new WebCustomDAOSysException(
                                "ULMSSysException while getMyConfig; userID = " + userID +
                                        " :\n" + se);
                }

                if (wcf != null)
                {
                        properties.put("cssFileNo", String.valueOf(wcf.getConfigTypeName()));
                }
                else
                {
                        //properties.put("cssFileNo", "1");
                }

                return properties;
        }

        /**
         * @param rs
         * @return
         * @throws WebCustomDAOSysException
         */

        /*
          private WebCustomForm convertRs2Form(ResultSet rs) throws WebCustomDAOSysException
          {
                  WebCustomForm wcf = new WebCustomForm();
                  int rsIndex = 1;
                  try
                  {
                          wcf.setCustomID(rs.getInt(rsIndex++));
                          wcf.setRelationID(rs.getInt(rsIndex++));
                          wcf.setRelationType(rs.getString(rsIndex++));
                          wcf.setConfigTypeName(rs.getString(rsIndex++));
                          wcf.setUpdateDate(rs.getDate(rsIndex++));
                          wcf.setDescription(rs.getString(rsIndex++));
                  }
                  catch (SQLException sql)
                  {
                          throw new WebCustomDAOSysException("SQLException while query :\n" + sql);
                  }
                  return wcf;
          }
        */
}
