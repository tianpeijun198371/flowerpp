/** * AssignmentDAOImpl.java.
 * User: xiejh  Date: 2004-4-22 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.assignment.dao;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.core.util.DateTimeUtil;
import com.ulearning.ulms.core.util.PagerList;
import com.ulearning.ulms.course.dao.CourseMyCourseDAO;
import com.ulearning.ulms.course.dao.CourseMyCourseDAOFactory;
import com.ulearning.ulms.course.exceptions.CourseDAOSysException;
import com.ulearning.ulms.course.model.CourseListModel;
import com.ulearning.ulms.course.model.CourseModel;
import com.ulearning.ulms.tools.assignment.exceptions.AssignmentDAOSysException;
import com.ulearning.ulms.tools.assignment.form.AssignmentForm;
import com.ulearning.ulms.tools.assignment.model.AssignmentModel;
import com.ulearning.ulms.util.HibernateDAO;
import com.ulearning.ulms.util.HibernateUtil;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


public class AssignmentDAOImpl implements AssignmentDAO
{
        public int addAssignment(AssignmentForm details)
                throws AssignmentDAOSysException
        {
                Serializable s = null;

                try
                {
                        //System.out.println("details getCreateTime="+details.getCreateTime()) ;
                        AssignmentModel a = details.getAssignmentModel();
                        //System.out.println("getCreateTime="+a.getCreateTime()) ;
                        s = HibernateDAO.add(a);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new AssignmentDAOSysException("" + e);
                }

                return s.hashCode();
        }

        public void updateAssignment(AssignmentForm details)
                throws AssignmentDAOSysException
        {
                try
                {
                        HibernateDAO.update(details.getAssignmentModel());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new AssignmentDAOSysException("" + e);
                }
        }

        /**
         * Remove the assignment from database by the assignmentID
         *
         * @param assignmentID
         * @throws AssignmentDAOSysException
         */
        public void removeAssignment(String assignmentID)
                throws AssignmentDAOSysException
        {
                String hql = " from AssignmentModel where AssignmentID = " +
                        assignmentID;

                try
                {
                        HibernateDAO.delete(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new AssignmentDAOSysException("" + e);
                }
        }

        /**
         * Remove the assignment from database by the assignmentID
         *
         * @param rootID
         * @throws AssignmentDAOSysException
         */
        public void removeAssignmentRoot(String rootID)
                throws AssignmentDAOSysException
        {
                String hql = " from AssignmentModel where rootID = " + rootID;

                try
                {
                        HibernateDAO.delete(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new AssignmentDAOSysException("" + e);
                }
        }

        /**
         * Get the assignment info via the unique assignmentID
         *
         * @param assignmentID
         * @return the prepared assignmentForm, default is null
         * @throws AssignmentDAOSysException
         */
        public AssignmentForm getAssignment(int assignmentID)
                throws AssignmentDAOSysException
        {
                AssignmentForm bf = new AssignmentForm();
                AssignmentForm res = null;
                List tmList = null;
                String hql = " from AssignmentModel where AssignmentID = " +
                        assignmentID;

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new AssignmentDAOSysException("" + e);
                }

                if ((tmList != null) && (tmList.size() > 0))
                {
                        AssignmentModel bm = (AssignmentModel) tmList.get(0);
                        res = bf.getAssignmentForm(bm);
                }

                return res;
        }

        /**
         * Get the assignment list by the catalogID
         *
         * @param rootID
         * @return The prepared arraylist object,default size is 0
         * @throws AssignmentDAOSysException
         */
        public List getAssignmentList(int rootID) throws AssignmentDAOSysException
        {
                AssignmentForm bf = new AssignmentForm();
                AssignmentModel bm = null;
                ArrayList doccontentList = new ArrayList();
                List tmList = null;
                String hql = " from AssignmentModel where rootID = " + rootID +" order by AssignmentID desc";

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new AssignmentDAOSysException("" + e);
                }

                for (int i = 0; i < tmList.size(); i++)
                {
                        bm = (AssignmentModel) tmList.get(i);
                        doccontentList.add(bf.getAssignmentForm(bm));
                }

                return doccontentList;
        }

        /**
         * Get the assignment list by the catalogID
         *
         * @param courseID
         * @return The prepared arraylist object,default size is 0
         * @throws AssignmentDAOSysException
         */
        public List getAssignmentList(int courseID, boolean isAdmin)
                throws AssignmentDAOSysException
        {
                AssignmentForm bf = null;
                String visitSQL = null;

                if (isAdmin)
                {
                        visitSQL = "";
                }
                else
                {
                        visitSQL = " and (" +
                                "(DisAfterTime IS NULL and DisUntilTime IS NULL) " +
                                " or (DisAfterTime IS NULL and DisUntilTime IS NOT NULL and " +
                                " DisUntilTime>=:now_day)" +
                                " or (DisAfterTime IS NOT NULL and DisAfterTime<=:now_day and DisUntilTime IS NULL)" +
                                " or (DisAfterTime IS NOT NULL and DisAfterTime<=:now_day and DisUntilTime IS NOT NULL and " +
                                " DisUntilTime>=:now_day)" +
                                " ) and IsViewable='1' and IsAvailable='1'";
                }

                String hql = " from AssignmentModel where courseID=" + courseID +
                        visitSQL + " order by type,AssignmentID desc";

                List tmList = null;
                List assignmentList = new ArrayList();
                Session session = null;

                try
                {
                        Date nowDate = new Date();
                        //nowDate=DateTimeUtil.toDate(DateTimeUtil.FormatDateToWeb1(nowDate)+" 00:00:00");
                        session = HibernateUtil.getSession();

                        Query q = session.createQuery(hql);

                        if (!isAdmin)
                        {
                                q.setDate("now_day", nowDate);
                        }

                        //q.setFirstResult(firstResult);
                        //q.setMaxResults(maxResults);
                        tmList = q.list();
                }
                catch (HibernateException he)
                {
                        throw new AssignmentDAOSysException(
                                "HibernateException while getAssignmentFormList: \n" + he);
                }

                try
                {
                        HibernateUtil.releaseSession(session);
                }
                catch (HibernateException he)
                {
                        throw new AssignmentDAOSysException(
                                "HibernateException while getAssignmentFormList: \n" + he);
                }

                AssignmentModel am = null;
                AssignmentForm at = new AssignmentForm();

                for (int i = 0; i < tmList.size(); i++)
                {
                        am = (AssignmentModel) tmList.get(i);
                        assignmentList.add(at.getAssignmentForm(am));
                }

                return assignmentList;
        }

        /**
         * 返回用户所能看到的的所有作业列表
         *
         * @param userID
         * @param pageNo
         * @param pageSize
         * @return
         * @throws CourseDAOSysException
         */
        public PagerList getAllAssignmentsByUser(int userID, int pageNo,
                                                 int pageSize) throws CourseDAOSysException
        {
                List pagerList = new ArrayList();
                Session session = null;
                PagerList pl = null;
                int totalCount = 0;
                String visitHql = " (" +
                        "(DisAfterTime IS NULL and DisUntilTime IS NULL) " +
                        " or (DisAfterTime IS NULL and DisUntilTime IS NOT NULL and " +
                        " DisUntilTime>=:now_day)" +
                        " or (DisAfterTime IS NOT NULL and DisAfterTime<=:now_day and DisUntilTime IS NULL)" +
                        " or (DisAfterTime IS NOT NULL and DisAfterTime<=:now_day and DisUntilTime IS NOT NULL and " +
                        " DisUntilTime>=:now_day)" +
                        " ) and IsViewable='1' and IsAvailable='1'";
                String hql = " from AssignmentModel where " + visitHql;

                //返回我的所有课程
                CourseMyCourseDAO courseMyCourseDAO = CourseMyCourseDAOFactory.getDAO();
                CourseListModel clm = courseMyCourseDAO.getMyAllCourses(userID);

                if (clm != null)
                {
                        List courseList = clm.getList();

                        if ((courseList != null) && !courseList.isEmpty())
                        {
                                hql += " and (";

                                for (int i = 0; i < courseList.size(); i++)
                                {
                                        CourseModel cm = (CourseModel) courseList.get(i);

                                        if (i == 0)
                                        {
                                                hql += ("CourseID = " + cm.getCourseID());
                                        }
                                        else
                                        {
                                                hql += (" or CourseID = " + cm.getCourseID());
                                        }
                                }

                                hql += ")";

                                try
                                {
                                        //得到数据
                                        Date nowDate = new Date();
                                        session = HibernateUtil.getSession();

                                        Query query = session.createQuery(hql +
                                                " order by CourseID desc,AssignmentID desc");
                                        query.setDate("now_day", nowDate);
                                        query.setMaxResults(pageSize);
                                        query.setFirstResult(pageSize * pageNo);
                                        pagerList = query.list();
                                        System.out.println("hql=======" + hql);
                                        System.out.println("pagerList = " + pagerList.size());

                                        //计算总页数
                                        String hql_count = "select count(*) " + hql;
                                        query = session.createQuery(hql_count);
                                        query.setDate("now_day", nowDate);
                                        totalCount = ((Integer) query.uniqueResult()).intValue();
                                        System.out.println("hql_count = " + hql_count);

                                        AssignmentModel am = null;
                                        AssignmentForm at = new AssignmentForm();
                                        List formList = new ArrayList();

                                        for (int i = 0; i < pagerList.size(); i++)
                                        {
                                                am = (AssignmentModel) pagerList.get(i);
                                                formList.add(at.getAssignmentForm(am));
                                        }

                                        System.out.println("formList = " + formList.size());
                                        //初始化PagerList其它项
                                        pl = new PagerList();
                                        pl.setPageSize(pageSize);
                                        pl.setPageNo(pageNo);
                                        pl.setTotalCount(totalCount);
                                        pl.setPagerList(formList);
                                }
                                catch (HibernateException he)
                                {
                                        throw new AssignmentDAOSysException(
                                                "HibernateException while getAllAssignmentsByUser: \n" +
                                                        he);
                                }
                        }
                }

                return pl;
        }

        /**
         * Get the assignment list by the catalogID
         *
         * @param CourseID
         * @return The prepared arraylist object,default size is 0
         * @throws AssignmentDAOSysException
         */
        public List getRootAssignmentList(int CourseID)
                throws AssignmentDAOSysException
        {
                AssignmentForm bf = new AssignmentForm();
                AssignmentModel bm = null;
                ArrayList doccontentList = new ArrayList();
                List tmList = null;
                String hql = " from AssignmentModel where CourseID = " + CourseID +
                        " order by type,AssignmentID desc";

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new AssignmentDAOSysException("" + e);
                }

                for (int i = 0; i < tmList.size(); i++)
                {
                        bm = (AssignmentModel) tmList.get(i);
                        doccontentList.add(bf.getAssignmentForm(bm));
                }

                return doccontentList;
        }

        public AssignmentForm getLastAssignment(AssignmentForm af)
                throws AssignmentDAOSysException
        {
                AssignmentForm bf = new AssignmentForm();
                AssignmentForm res = null;
                List tmList = null;
                String hql = " from AssignmentModel where CourseID = " +
                        af.getCourseID() + "and   ParentID = " + af.getParentID() +
                        "and   Name = '" + af.getName() + "'" +
                        "order by assignmentID desc";

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new AssignmentDAOSysException("" + e);
                }

                if ((tmList != null) && (tmList.size() > 0))
                {
                        AssignmentModel bm = (AssignmentModel) tmList.get(0);
                        res = bf.getAssignmentForm(bm);
                }

                return res;
        }

        public void updateAssignmentViewable(String assignmentID, String viewable)
                throws AssignmentDAOSysException
        {
                AssignmentForm af = getAssignment(Integer.parseInt(assignmentID));
                af.setViewable(viewable);
                updateAssignment(af);
        }

        public void updateAssignmentAvailable(String assignmentID, String available)
                throws AssignmentDAOSysException
        {
                AssignmentForm af = getAssignment(Integer.parseInt(assignmentID));
                af.setAvailable(available);
                updateAssignment(af);
        }

        public List getNewTrainInfo(int courseID, int rootID, int type,
                                    boolean isAdmin) throws AssignmentDAOSysException
        {
                AssignmentForm bf = null;
                String visitSQL = null;

                if (isAdmin)
                {
                        visitSQL = "";
                }
                else
                {
                        visitSQL = " and (" +
                                "(disAfterTime IS NULL and disUntilTime IS NULL) " +
                                " or (disAfterTime IS NULL and disUntilTime IS NOT NULL and " +
                                " disUntilTime>=:now_day)" +
                                " or (disAfterTime IS NOT NULL and disAfterTime<=:now_day and disUntilTime IS NULL)" +
                                " or (disAfterTime IS NOT NULL and disAfterTime<=:now_day and disUntilTime IS NOT NULL and " +
                                " disUntilTime>=:now_day)" +
                                " ) and IsViewable='1' and IsAvailable='1'";
                }

                String root = "";

                if (rootID >= 0)
                {
                        root = " and rootID=" + rootID;
                }

                String hql = " from AssignmentModel where courseID=" + courseID +
                        "and type='" + type + root + visitSQL +
                        "' order by AssignmentID DESC";
                                           
                List tmList = null;
                List assignmentList = new ArrayList();
                Session session = null;

                try
                {
                        Date nowDate = new Date();
                        //nowDate=DateTimeUtil.toDate(DateTimeUtil.FormatDateToWeb1(nowDate)+" 00:00:00");
                        session = HibernateUtil.getSession();

                        Query q = session.createQuery(hql);

                        if (!isAdmin)
                        {
                                q.setDate("now_day", nowDate);
                        }

                        //q.setFirstResult(firstResult);
                        //q.setMaxResults(maxResults);
                        tmList = q.list();
                }
                catch (HibernateException he)
                {
                        throw new AssignmentDAOSysException(
                                "HibernateException while getAssignmentFormList: \n" + he);
                }

                try
                {
                        HibernateUtil.releaseSession(session);
                }
                catch (HibernateException he)
                {
                        throw new AssignmentDAOSysException(
                                "HibernateException while getAssignmentFormList: \n" + he);
                }

                AssignmentModel am = null;
                AssignmentForm at = new AssignmentForm();

                for (int i = 0; i < tmList.size(); i++)
                {
                        am = (AssignmentModel) tmList.get(i);
                        assignmentList.add(at.getAssignmentForm(am));
                }

                return assignmentList;
        }

        /**
         * Convert the resultSet object to assignmentForm
         * @param rs  the resultSet which needs to convert
         * @return the wanted assignmentForm
         */

        /*
          private AssignmentForm convertRs2Form(ResultSet rs)
          {
              AssignmentForm bf = new AssignmentForm();
              int rsIndex = 1;
              try
              {
                      bf.setAssignmentID(rs.getInt(rsIndex++));
                      bf.setCourseID(rs.getInt(rsIndex++));
                      bf.setParentID(rs.getInt(rsIndex++));
                      bf.setRootID(rs.getInt(rsIndex++));
                      bf.setOrderIndex(rs.getInt(rsIndex++));
                      bf.setName(StringUtil.nullToStr(rs.getString(rsIndex++)));
                      bf.setDescription(StringUtil.nullToStr(rs.getString(rsIndex++)));
                      bf.setViewable(StringUtil.nullToStr(rs.getString(rsIndex++)));
                      bf.setLinkFileUrl(StringUtil.nullToStr(rs.getString(rsIndex++)));
                      bf.setLinkFileName(StringUtil.nullToStr(rs.getString(rsIndex++)));
                      bf.setType(StringUtil.nullToStr(rs.getString(rsIndex++)));
                      bf.setFileType(StringUtil.nullToStr(rs.getString(rsIndex++)));
                      bf.setTrackNumber(StringUtil.nullToStr(rs.getString(rsIndex++)));
                      bf.setViewNumber(rs.getInt(rsIndex++));
                      bf.setAvailable(StringUtil.nullToStr(rs.getString(rsIndex++)));
                      bf.setCreateTime(String.valueOf(rs.getDate(rsIndex++)));
                      bf.setDisAfterTime(String.valueOf(rs.getDate(rsIndex++)));
                      bf.setDisUntilTime(String.valueOf(rs.getDate(rsIndex++)));
              } catch (SQLException sql)
              {
                      sql.printStackTrace();
              }
              return bf;
          }
        */
}
