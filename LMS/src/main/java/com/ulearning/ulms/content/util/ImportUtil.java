/**
 * ImportUtil.java.
 * User: Fengch  Date: 2005-9-7 19:54:03
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.content.util;

import com.ulearning.ulms.content.dao.CourseContentTypeDAO;
import com.ulearning.ulms.content.dao.CourseContentTypeDAOFactory;
import com.ulearning.ulms.content.model.CourseContentTypeModel;
import com.ulearning.ulms.course.exceptions.CourseSysException;
import com.ulearning.ulms.util.DBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;


public class ImportUtil
{
        public List checkFile(List lt, int aspID)
        {
                List str = new ArrayList();

                //取得courseContentType的名称
                CourseContentTypeDAO cctd = CourseContentTypeDAOFactory.getDAO();
                List cctdlt = cctd.getAllCourseContentType(String.valueOf(aspID));

                List courseContentTypenamelt = new ArrayList();

                for (int i = 0; i < cctdlt.size(); i++)
                {
                        CourseContentTypeModel cctm = (CourseContentTypeModel) cctdlt.get(i);
                        courseContentTypenamelt.add(cctm.getCourseContentType());
                }

                Connection conn = null;
                Statement stmt = null;
                ResultSet rs = null;

                List courseCodelt = new ArrayList();

                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();

                        String queryStr1 = "select courseCode from C_Course_Tab" +
                                " WHERE orgID=" + aspID;

                        rs = stmt.executeQuery(queryStr1);

                        while (rs.next())
                        {
                                courseCodelt.add(rs.getString(1));
                        }
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new CourseSysException(se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }

                //取得courseCode
                /*               List courselt = new ArrayList();
                List courseCodelt = new ArrayList();
                Session session = null;
                try
                {
                        String hql = "from Course where orgID=" + aspID;
                        session = HibernateUtil.getSession();
                        Query q = session.createQuery(hql);
                        courselt = q.list();
                }
                catch (HibernateException e)
                {
                        throw new ContentManageSysException(e);
                }
                finally
                {
                        try
                        {
                                HibernateUtil.releaseSession(session);
                        }
                        catch (HibernateException e)
                        {
                                throw new ContentManageSysException(e);
                        }
                }
                for (int i = 0; i < courselt.size(); i++)
                {
                        Course course = (Course) courselt.get(i);
                        courseCodelt.add(course.getCoursecode());
                }*/

                //检查文件
                for (int i = 0; i < lt.size(); i++)
                {
                        String[] args = (String[]) lt.get(i);
                        String err = checkRow(args, i + 1, courseContentTypenamelt,
                                courseCodelt);

                        if (err != null)
                        {
                                str.add("第" + (i + 1) + "行有错误：" + err);

                                //str += "第" + (i+1) + "行有错误：" + err + "<br>";
                        }
                }

                return str;
        }

        /**
         * args是要检查的内容，row是第几行，courseContentTypenamelt是课程资源类别名称的list,courseCodelt是所有课程编码的list
         *
         * @param args
         * @param row
         * @param courseContentTypenamelt
         * @param courseCodelt
         * @return
         */
        public String checkRow(String[] args, int row,
                               List courseContentTypenamelt, List courseCodelt)
        {
                String message = null;

                String a = checkCourseCode(args[0], courseCodelt);

                if (a != null)
                {
                        message = "";
                        message += ("第1列：" + a + "  ");
                }

                String b = checkContentName(args[1]);

                if (b != null)
                {
                        message = "";
                        message += ("第2列：" + b + "  ");
                }

                String c = checkDescribe(args[2]);

                if (c != null)
                {
                        message = "";
                        message += ("第3列：" + c + "  ");
                }

                String d = checkCourseContentType(args[3], courseContentTypenamelt);

                if (d != null)
                {
                        message = "";
                        message = "第4列：" + d + "  ";
                }

                String f = checkBand(args[4]);

                if (f != null)
                {
                        message = "";
                        message = "第5列：" + f + "  ";
                }

                String g = checkUrl(args[5]);

                if (g != null)
                {
                        message = "";
                        message = "第6列：" + g + "  ";
                }

                //System.out.println("row = " + row);
                String h = checkState(args[6]);

                if (h != null)
                {
                        message = "";
                        message = "第7列：" + h;
                }

                return message;
        }

        public String checkCourseCode(String str, List lt)
        {
                if ((str != null) && (str.length() != 0))
                {
                        if (!lt.contains(str))
                        {
                                return "课程编码不存在！";
                        }

                        if (str.length() > 50)
                        {
                                return "课程编码长度不能超过50个字符！"; //以c_course_tab的courseCode长度为标准。
                        }
                }
                else
                {
                        return "课程编码不能为空！";
                }

                return null;
        }

        public String checkContentName(String str)
        {
                if ((str != null) && (str.length() != 0))
                {
                        if (str.length() > 250)
                        {
                                return "课程资源名称长度不能超过250个中文字符！";
                        }
                }
                else
                {
                        return "课程资源名称不能为空！";
                }

                return null;
        }

        public String checkDescribe(String str)
        {
                if ((str != null) && (str.length() != 0))
                {
                        if (str.length() > 250)
                        {
                                return "课程资源描述长度不能超过250个中文字符！";
                        }
                }

                return null;
        }

        public String checkCourseContentType(String str, List lt)
        {
                if ((str != null) && (str.length() != 0))
                {
                        if (!lt.contains(str))
                        {
                                return "课程资源类别必须是\"课程资源类别管理\"中已经定义的！";
                        }
                }
                else
                {
                        return "课程资源类别不能为空！";
                }

                return null;
        }

        public String checkBand(String str)
        {
                if ((str != null) && (str.length() != 0))
                {
                        if (!str.equals("宽带") && !str.equals("窄带"))
                        {
                                return "状态只能用\"宽带\",\"窄带\"来表示";
                        }
                }
                else
                {
                        return "宽带/窄带类别不能为空！";
                }

                return null;
        }

        public String checkUrl(String str)
        {
                //System.out.println("str = " + str);
                if ((str != null) && (str.length() != 0))
                {
                        if (str.length() > 255)
                        {
                                return "URL地址长度不能超过255个字符！";
                        }
                }
                else
                {
                        return "URL地址不能为空！";
                }

                return null;
        }

        public String checkState(String str)
        {
                //System.out.println("str = " + str);
                if ((str != null) && (str.length() != 0))
                {
                        if (str.length() > 1)
                        {
                                return "状态不能超过1个字符！";
                        }

                        if (!str.equals("0") && !str.equals("1"))
                        {
                                return "状态只能用\"0\"（不发布）,\"1\"（发布）来表示";
                        }
                }
                else
                {
                        return "状态不能为空！";
                }

                return null;
        }
}
