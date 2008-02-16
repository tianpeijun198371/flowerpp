/**
 * Created by IntelliJ IDEA.
 * Delete: dengj
 * Date: Apr 8, 2004
 * Time: 9:36:22 AM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.tools.delete.dao;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.core.util.DateTimeUtil;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.tools.announcement.exceptions.AnnouncementSysException;
import com.ulearning.ulms.tools.announcement.model.AnnouncementModel;
import com.ulearning.ulms.tools.delete.exceptions.DeleteDAOSysException;
import com.ulearning.ulms.tools.delete.form.DeleteForm;
import com.ulearning.ulms.tools.delete.model.DeleteModel;
import com.ulearning.ulms.util.HibernateDAO;
import com.ulearning.ulms.util.HibernateUtil;
import com.ulearning.ulms.util.log.LogUtil;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;
import net.sf.hibernate.type.TimestampType;

import java.io.Serializable;

import java.sql.SQLException;

import java.util.*;


public class DeleteDAOImpl implements DeleteDAO
{
        public Serializable addDelete(DeleteForm details)
                throws DeleteDAOSysException
        {
                Serializable s = null;

                try
                {
                        s = HibernateDAO.add(details.getDeleteModel());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new DeleteDAOSysException("" + e);
                }

                return s;
        }

        public void updateDelete(DeleteForm details) throws DeleteDAOSysException
        {
                try
                {
                        HibernateDAO.update(details.getDeleteModel());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new DeleteDAOSysException("" + e);
                }
        }

        /**
         * Remove the delete from database by the deleteID
         *
         * @param deleteID
         * @throws DeleteDAOSysException
         */
        public void removeDelete(String deleteID) throws DeleteDAOSysException
        {
                String hql = " from DeleteModel where DeleteID = " + deleteID;

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
                        throw new DeleteDAOSysException("" + e);
                }
        }

        /**
         * Get the delete info via the unique deleteID
         *
         * @param deleteID
         * @return the prepared deleteForm, default is null
         * @throws DeleteDAOSysException
         */
        public DeleteForm getDelete(int deleteID) throws DeleteDAOSysException
        {
                DeleteForm bf = new DeleteForm();
                DeleteForm res = null;
                List tmList = null;
                String hql = " from DeleteModel where DeleteID=" + deleteID;

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new DeleteDAOSysException("" + e);
                }

                if ((tmList != null) && (tmList.size() > 0))
                {
                        DeleteModel bm = (DeleteModel) tmList.get(0);
                        res = bf.getDeleteForm(bm);
                }

                return res;
        }

        public List getDeleteList(int relationID, String relationType)
                throws DeleteDAOSysException
        {
                DeleteForm bf = new DeleteForm();
                DeleteModel bm = null;
                ArrayList deleteList = new ArrayList();
                List tmList = null;
                String hql = " from DeleteModel where relationID = " + relationID +
                        " and relationType='" + relationType + "'";

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new DeleteDAOSysException("" + e);
                }

                for (int i = 0; (tmList != null) && (i < tmList.size()); i++)
                {
                        bm = (DeleteModel) tmList.get(i);
                        deleteList.add(bf.getDeleteForm(bm));
                }

                return deleteList;
        }

        /**
         * get a piece of information
         *
         * @param modelName     model名称 ，如 AnnouncementModel
         * @param timeFieldName 参考的时间字段名称  如 modifydate
         * @param saveMaxRows   最大保留记录数
         * @param saveAfterDate 在此时间后的记录得以保留
         * @throws DeleteDAOSysException 当  saveMaxRows和saveAfterDate 有冲突时，下面例子将按删除多的方案进行，即按max(saveAfterDate,modifydate)
         *                               如果想相反，则按 min(saveAfterDate,modifydate) 进行删除
         * @ return void
         */
        public void deleteNoSave(String modelName, String timeFieldName,
                                 int saveMaxRows, Date saveAfterDate, String otherConditionItem)
                throws DeleteDAOSysException
        {
                Session session = null;
                List tmList = null;

                try
                {
                        session = HibernateUtil.getSession();

                        String hql = "";
                        String hql_count = "select count(*) from " + modelName;

                        if (!otherConditionItem.equals(""))
                        {
                                hql_count = hql_count + " where  " + otherConditionItem;
                        }

                        int countNum = HibernateDAO.count(hql_count);

                        if (countNum > saveMaxRows) //根据保留记录数获取临界时间
                        {
                                hql = "select a." + timeFieldName + " from " + modelName +
                                        " a order by a." + timeFieldName;

                                if (!otherConditionItem.equals(""))
                                {
                                        hql = hql + " where  " + otherConditionItem;
                                }

                                Query q = session.createQuery(hql);
                                q.setFirstResult(countNum - saveMaxRows);
                                q.setMaxResults(countNum - saveMaxRows);
                                tmList = q.list();

                                Iterator it1 = tmList.iterator();
                                Date modifydate = null;

                                while (it1.hasNext())
                                {
                                        Object row = (Object) it1.next();
                                        modifydate = (Date) row;
                                        System.out.println(DateTimeUtil.FormatDateToWeb4(modifydate));
                                }

                                if (modifydate.getTime() > saveAfterDate.getTime())
                                {
                                        saveAfterDate = modifydate;
                                }
                        }

                        //按时间删除多余记录
                        hql = " from " + modelName + " where " + timeFieldName + "<?";

                        if (!otherConditionItem.equals(""))
                        {
                                hql = hql + " and  " + otherConditionItem;
                        }

                        session.delete(hql, saveAfterDate, new TimestampType());
                        session.flush();
                        session.connection().commit();
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new DeleteDAOSysException("" + e);
                }
                catch (HibernateException e)
                {
                        e.printStackTrace();
                        throw new DeleteDAOSysException("" + e);
                }
                catch (SQLException e)
                {
                        throw new DeleteDAOSysException("" + e);
                }

                try
                {
                        HibernateUtil.releaseSession(session);
                }
                catch (HibernateException he)
                {
                        throw new DeleteDAOSysException(
                                "HibernateException while getAnnouncementFormList: \n" + he);
                }
        }

        /*
          private DeleteForm convertRs2Form(ResultSet rs)
          {
              DeleteForm bf = new DeleteForm();
              int rsIndex = 1;
              try
              {
                      bf.setDeleteID(rs.getInt(rsIndex++));
                      bf.setRelationID(rs.getInt(rsIndex++));
                      bf.setRelationType(StringUtil.nullToStr(rs.getString(rsIndex++)));
                      bf.setState(StringUtil.nullToStr(rs.getString(rsIndex++)));
                      bf.setObjectType(StringUtil.nullToStr(rs.getString(rsIndex++)));
                      bf.setSaveTimeNum(rs.getInt(rsIndex++));
                      bf.setTimeType(StringUtil.nullToStr(rs.getString(rsIndex++)));
                      bf.setSaveRows(rs.getInt(rsIndex++));
                      bf.setUserID(rs.getInt(rsIndex++));
                      bf.setUpdateDate(rs.getDate(rsIndex++));
              } catch (SQLException sql)
              {
                      sql.printStackTrace();
              }
              return bf;
          }
        */
        public static void main(String[] args)
        {
                DeleteDAOImpl deleteDAOImpl = new DeleteDAOImpl();

                try
                {
                        deleteDAOImpl.deleteNoSave("AnnouncementModel", "modifydate", 31,
                                DateTimeUtil.toDate("2004-09-28 03:09:17"), "");
                }
                catch (DeleteDAOSysException he)
                {
                        System.out.println(
                                "HibernateException while getAnnouncementFormList: \n" + he);
                }
        }
}
