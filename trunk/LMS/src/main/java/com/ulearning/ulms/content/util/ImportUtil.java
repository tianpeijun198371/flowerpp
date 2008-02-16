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

                //ȡ��courseContentType������
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

                //ȡ��courseCode
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

                //����ļ�
                for (int i = 0; i < lt.size(); i++)
                {
                        String[] args = (String[]) lt.get(i);
                        String err = checkRow(args, i + 1, courseContentTypenamelt,
                                courseCodelt);

                        if (err != null)
                        {
                                str.add("��" + (i + 1) + "���д���" + err);

                                //str += "��" + (i+1) + "���д���" + err + "<br>";
                        }
                }

                return str;
        }

        /**
         * args��Ҫ�������ݣ�row�ǵڼ��У�courseContentTypenamelt�ǿγ���Դ������Ƶ�list,courseCodelt�����пγ̱����list
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
                        message += ("��1�У�" + a + "  ");
                }

                String b = checkContentName(args[1]);

                if (b != null)
                {
                        message = "";
                        message += ("��2�У�" + b + "  ");
                }

                String c = checkDescribe(args[2]);

                if (c != null)
                {
                        message = "";
                        message += ("��3�У�" + c + "  ");
                }

                String d = checkCourseContentType(args[3], courseContentTypenamelt);

                if (d != null)
                {
                        message = "";
                        message = "��4�У�" + d + "  ";
                }

                String f = checkBand(args[4]);

                if (f != null)
                {
                        message = "";
                        message = "��5�У�" + f + "  ";
                }

                String g = checkUrl(args[5]);

                if (g != null)
                {
                        message = "";
                        message = "��6�У�" + g + "  ";
                }

                //System.out.println("row = " + row);
                String h = checkState(args[6]);

                if (h != null)
                {
                        message = "";
                        message = "��7�У�" + h;
                }

                return message;
        }

        public String checkCourseCode(String str, List lt)
        {
                if ((str != null) && (str.length() != 0))
                {
                        if (!lt.contains(str))
                        {
                                return "�γ̱��벻���ڣ�";
                        }

                        if (str.length() > 50)
                        {
                                return "�γ̱��볤�Ȳ��ܳ���50���ַ���"; //��c_course_tab��courseCode����Ϊ��׼��
                        }
                }
                else
                {
                        return "�γ̱��벻��Ϊ�գ�";
                }

                return null;
        }

        public String checkContentName(String str)
        {
                if ((str != null) && (str.length() != 0))
                {
                        if (str.length() > 250)
                        {
                                return "�γ���Դ���Ƴ��Ȳ��ܳ���250�������ַ���";
                        }
                }
                else
                {
                        return "�γ���Դ���Ʋ���Ϊ�գ�";
                }

                return null;
        }

        public String checkDescribe(String str)
        {
                if ((str != null) && (str.length() != 0))
                {
                        if (str.length() > 250)
                        {
                                return "�γ���Դ�������Ȳ��ܳ���250�������ַ���";
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
                                return "�γ���Դ��������\"�γ���Դ������\"���Ѿ�����ģ�";
                        }
                }
                else
                {
                        return "�γ���Դ�����Ϊ�գ�";
                }

                return null;
        }

        public String checkBand(String str)
        {
                if ((str != null) && (str.length() != 0))
                {
                        if (!str.equals("���") && !str.equals("խ��"))
                        {
                                return "״ֻ̬����\"���\",\"խ��\"����ʾ";
                        }
                }
                else
                {
                        return "���/խ�������Ϊ�գ�";
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
                                return "URL��ַ���Ȳ��ܳ���255���ַ���";
                        }
                }
                else
                {
                        return "URL��ַ����Ϊ�գ�";
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
                                return "״̬���ܳ���1���ַ���";
                        }

                        if (!str.equals("0") && !str.equals("1"))
                        {
                                return "״ֻ̬����\"0\"����������,\"1\"������������ʾ";
                        }
                }
                else
                {
                        return "״̬����Ϊ�գ�";
                }

                return null;
        }
}
