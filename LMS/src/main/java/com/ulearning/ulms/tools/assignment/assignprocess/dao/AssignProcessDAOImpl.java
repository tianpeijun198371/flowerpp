/** * AssignProcessDAOImpl.java.
 * User: xiejh  Date: 2004-4-22 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.assignment.assignprocess.dao;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.tools.assignment.assignprocess.exceptions.AssignProcessDAOSysException;
import com.ulearning.ulms.tools.assignment.assignprocess.form.AssignProcessForm;
import com.ulearning.ulms.tools.assignment.assignprocess.form.UserAssignProForm;
import com.ulearning.ulms.tools.assignment.assignprocess.model.AssignProcessModel;
import com.ulearning.ulms.tools.assignment.dao.AssignmentDAOImpl;
import com.ulearning.ulms.user.model.UserModel;
import com.ulearning.ulms.util.HibernateDAO;
import com.ulearning.ulms.util.HibernateUtil;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


public class AssignProcessDAOImpl implements AssignProcessDAO
{
        public int addAssignProcess(AssignProcessForm details)
                throws AssignProcessDAOSysException
        {
                int res = -1;

                try
                {
                        res = HibernateDAO.add(details.getAssignProcessModel()).hashCode();
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new AssignProcessDAOSysException("" + e);
                }

                return res;
        }

        public void updateAssignProcess(AssignProcessForm details)
                throws AssignProcessDAOSysException
        {
                try
                {
                        AssignProcessForm assignProcessForm = getAssignProcess(details.getAssignProcessID());

                        if (assignProcessForm.getState().equals("1")) //stu update
                        {
                                assignProcessForm.setAttach_path_stu(details.getAttach_path_stu());
                                assignProcessForm.setStuRemark(details.getStuRemark());
                                assignProcessForm.setState("2");
                                assignProcessForm.setSubmit_datetime(new Date());
                        }
                        else if ((assignProcessForm.getState().equals("2") ||
                                assignProcessForm.getState().equals("3")) &&
                                (details.getScore() != null)) //teacher update
                        {
                                assignProcessForm.setAttach_path_tea(details.getAttach_path_tea());
                                assignProcessForm.setTeaRemark(details.getTeaRemark());
                                assignProcessForm.setScore(details.getScore());
                                assignProcessForm.setState("3");
                                assignProcessForm.setUpdate_datetime(new Date());
                        }

                        HibernateDAO.update(assignProcessForm.getAssignProcessModel());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new AssignProcessDAOSysException("" + e);
                }
        }

        /**
         * Remove the assignment from database by the assignProcessID
         *
         * @param assignProcessID
         * @throws AssignProcessDAOSysException
         */
        public void removeAssignProcess(String assignProcessID)
                throws AssignProcessDAOSysException
        {
                String hql = " from AssignProcessModel where AssignProcessID = " +
                        assignProcessID;

                try
                {
                        HibernateDAO.delete(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new AssignProcessDAOSysException("" + e);
                }
        }

        /**
         * Get the assignment info via the unique assignProcessID
         *
         * @param assignProcessID
         * @return the prepared assignProcessForm, default is null
         * @throws AssignProcessDAOSysException
         */
        public AssignProcessForm getAssignProcess(int assignProcessID)
                throws AssignProcessDAOSysException
        {
                AssignProcessForm bf = new AssignProcessForm();
                AssignProcessForm res = null;
                List tmList = null;
                String hql = " from AssignProcessModel where AssignProcessID = " +
                        assignProcessID;

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new AssignProcessDAOSysException("" + e);
                }

                if ((tmList != null) && (tmList.size() > 0))
                {
                        AssignProcessModel bm = (AssignProcessModel) tmList.get(0);
                        res = bf.getAssignProcessForm(bm);
                }

                return res;
        }

        /**
         * Get the assignment list by the catalogID
         *
         * @param relationID ,String relationType
         * @return The prepared arraylist object,default size is 0
         * @throws AssignProcessDAOSysException
         */

        /*
          public List getAssignProcessList(int relationID ,String relationType) throws AssignProcessDAOSysException
          {
                  ArrayList AssignProcessList = new ArrayList();
                  AssignProcessForm bf = new AssignProcessForm();
                  AssignProcessForm res = null;
                  List tmList = null;
                  UserModel userModel=null;
                  String hql = "from AssignProcessModel a"
                          +" where relationID = " + relationID
                          +" and relationType='"+relationType+"'";
               try
               {
                       tmList=HibernateDAO.find(hql);
               }
               catch (ULMSSysException e)
               {
                       e.printStackTrace();
                       throw new AssignProcessDAOSysException(""+e);
               }
               if ((tmList != null) && (tmList.size() > 0))
               {
                       //AssignProcessModel bm = (AssignProcessModel) tmList.get(0);
                       //res = bf.getAssignProcessForm(bm);
                       //AssignProcessList.add(res);
               }
               return AssignProcessList;
          }
           public List getAssignProcessUserList(int relationID ,String relationType) throws AssignProcessDAOSysException
                  {
                          ArrayList UserFormList = new ArrayList();
                          List tmList=null;
                          UserModel userModel=null;
                          String hql = "select distinct u  from AssignProcessModel as  a,UserModel as u"
                                  +" where a.userID=u.userid and a.relationID = " + relationID
                                  +" and a.relationType='"+relationType+"'";  //u.userid,u.loginname,u.name
                       try
                       {
                               Session session = HibernateUtil.getSession();
                               tmList= session.find(hql);
                       }
                       catch (HibernateException e)
                       {
                               e.printStackTrace();
                               throw new AssignProcessDAOSysException(""+e);
                       }
                       if ((tmList != null) && (tmList.size() > 0))
                       {
                                   for (Iterator iter = tmList.iterator(); iter.hasNext();)
                                   {
                                           userModel=  (UserModel)iter.next();
                                           UserFormList.add(userModel);
                                   }
                       }
                       return UserFormList;
                  }
        */
        public List getAssignProcessList(int relationID, String relationType)
                throws AssignProcessDAOSysException
        {
                ArrayList userAssignProList = new ArrayList();
                AssignProcessForm bf = new AssignProcessForm();
                List tmList = null;

                AssignProcessForm assignProcessForm = null;

                String hql = "from AssignProcessModel as a,UserModel as u" +
                        " where a.userID=u.userid and a.relationID = " + relationID +
                        " and a.relationType='" + relationType + "'";

                try
                {
                        // Session session = HibernateUtil.getSession();
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new AssignProcessDAOSysException("" + e);
                }

                if ((tmList != null) && (tmList.size() > 0))
                {
                        Object[] row = null;

                        for (Iterator iter = tmList.iterator(); iter.hasNext();)
                        {
                                row = (Object[]) iter.next();

                                UserModel userModel = null;
                                AssignProcessModel assignProcessModel = null;
                                assignProcessModel = (AssignProcessModel) row[0];
                                userModel = (UserModel) row[1];

                                UserAssignProForm userAssignProForm = new UserAssignProForm();

                                if ((assignProcessModel != null) && (userModel != null))
                                {
                                        assignProcessForm = bf.getAssignProcessForm(assignProcessModel);
                                        userAssignProForm.setAp(assignProcessForm);
                                        userAssignProForm.setLoginName(userModel.getLoginname());
                                        userAssignProForm.setName(userModel.getName());
                                        userAssignProList.add(userAssignProForm);
                                }
                        }
                }

                return userAssignProList;
        }

        public AssignProcessForm getAssignProcess(int relationID,
                                                  String relationType, int UserID) throws AssignProcessDAOSysException
        {
                AssignProcessForm bf = new AssignProcessForm();
                AssignProcessForm res = null;
                List tmList = null;
                String hql = " from AssignProcessModel where relationID = " +
                        relationID + " and relationType='" + relationType + "'" +
                        " and UserID=" + UserID;

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new AssignProcessDAOSysException("" + e);
                }

                if ((tmList != null) && (tmList.size() > 0))
                {
                        AssignProcessModel bm = (AssignProcessModel) tmList.get(0);
                        res = bf.getAssignProcessForm(bm);
                }

                return res;
        }

        public List getAssignProcess(String relationType, int UserID)
                throws AssignProcessDAOSysException
        {
                List tmList = null;
                String hql = " from AssignProcessModel where relationType='" +
                        relationType + "'" + " and userID=" + UserID;

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new AssignProcessDAOSysException("" + e);
                }

                //if ((tmList != null) && (tmList.size() > 0))
                //{
                //AssignProcessModel bm = (AssignProcessModel) tmList.get(0);
                //res = bf.getAssignProcessForm(bm);
                //}
                return tmList;
        }

        /*
          private AssignProcessForm convertRs2Form(ResultSet rs)
          {
              AssignProcessForm bf = new AssignProcessForm();
              int rsIndex = 1;
              try
              {
                      bf.setAssignProcessID(rs.getInt(rsIndex++));
                      bf.setRelationID(rs.getInt(rsIndex++));
                      bf.setRelationType(StringUtil.nullToStr(rs.getString(rsIndex++)));
                      bf.setState(StringUtil.nullToStr(rs.getString(rsIndex++)));
                      bf.setUserID(rs.getInt(rsIndex++));
                      bf.setAccess_datetime(rs.getDate(rsIndex++));
                      bf.setSubmit_datetime(rs.getDate(rsIndex++));
                      bf.setStuRemark(StringUtil.nullToStr(rs.getString(rsIndex++)));
                      bf.setAttach_path_stu(StringUtil.nullToStr(rs.getString(rsIndex++)));
                      bf.setUpdate_datetime(rs.getDate(rsIndex++));
                      bf.setScore(StringUtil.nullToStr(rs.getString(rsIndex++)));
                      bf.setTeaRemark(StringUtil.nullToStr(rs.getString(rsIndex++)));
                      bf.setAttach_path_tea(StringUtil.nullToStr(rs.getString(rsIndex++)));
              }
              catch (SQLException sql)
              {
                      sql.printStackTrace();
              }
              return bf;
          }
        */
        public static void main(String[] args)
        {
        }
}
