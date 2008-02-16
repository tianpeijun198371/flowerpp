package com.ulearning.ulms.util;


import java.sql.*;
import java.util.*;
import java.lang.StringBuffer;

//import com.properties.*;

public class PageObject
{

        public Connection conn = null;
        public ResultSet rst = null;

        /* ÿҳ�ļ�¼�� */
        public int intPageSize = 10;
        /* ��ҳ�� */
        public int intPageCount = 1;
        /* �ܼ�¼�� */
        public int intRowCount = 0;
        /* ��ǰ��ҳ�� */
        public int intPage = 1;


        /**
         * ˵����ÿҳ�ļ�¼������
         *
         * @param intPageSize ÿҳ�ļ�¼��
         */
        public void setIntPageSize(int intPageSize)
        {
                this.intPageSize = intPageSize;
        }

        /**
         * ˵����ÿҳ�ļ�¼������
         *
         * @return int ÿҳ�ļ�¼��
         */
        public int getIntPageSize()
        {
                return intPageSize;
        }

        /**
         * ˵������ҳ������
         *
         * @param intPageCount ��ҳ��
         */
        public void setIntPageCount(int intPageCount)
        {
                this.intPageCount = intPageCount;
        }

        /**
         * ˵������ҳ������
         *
         * @return int ��ҳ��
         */
        public int getIntPageCount()
        {
                return intPageCount;
        }

        /**
         * ˵�����ܼ�¼������
         *
         * @param intRowCount �ܼ�¼��
         */
        public void setIntRowCount(int intRowCount)
        {
                this.intRowCount = intRowCount;
        }

        /**
         * ˵�����ܼ�¼������
         *
         * @return int �ܼ�¼��
         */
        public int getIntRowCount()
        {
                return intRowCount;
        }

        /**
         * ˵������ǰ��ҳ��
         *
         * @param intPage ��ǰ��ҳ��
         */
        public void setIntPage(int intPage)
        {
                this.intPage = intPage;
        }

        /**
         * ˵������ǰ��ҳ��
         *
         * @return int ��ǰ��ҳ��
         */
        public int getIntPage()
        {
                return intPage;
        }

        /**
         * ˵�������캯��
         */
        public PageObject()
        {
        }

        /**
         * ˵����ȡ�ü�¼������������ͨ�ã�
         *
         * @param conn ���ݿ�����ʵ��
         * @param sql  SQL���
         * @return int ȡ�ü�¼����
         */
        public int getTotalRow(Connection conn, String sql)
        {
                int totalRow = 0;
                PreparedStatement pstmt = null;
                try
                {
                        pstmt = conn.prepareStatement(sql);
                        ResultSet rs = pstmt.executeQuery();
                        rs.next();
                        totalRow = rs.getInt(1);
                        rs.close();
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                        System.out.println("PageObject.getTotalRow() error!");
                }
                finally
                {
                        try
                        {
                                pstmt.close();
                        }
                        catch (Exception e)
                        {
                                e.printStackTrace();
                        }
                }
                return totalRow;
        }

        /**
         * ˵����ȡ�÷�ҳ��Ϣ��������ͨ�ã�
         *
         * @param totalRow �ܵļ�¼��
         * @param intPage  ��ǰҳ��
         * @return Vector ��ҳ��Ϣ
         */
        public Vector getPageInfo(int totalRow, int intPage)
        {
                Vector pageInfo = new Vector();
                //�ܼ�¼��
                intRowCount = totalRow;
                //��ҳ��
                intPageCount = (intRowCount + intPageSize - 1) / intPageSize;
                //������ǰҳ���Ϸ�
                if (intPage > intPageCount)
                {
                        intPage = intPageCount;
                }
                if (intPage < 1)
                {
                        intPage = 1;
                }
                //�����ϡ���ҳ��
                int prePage = 1;
                int nextPage = intPageCount;
                if (intPage > 1)
                {
                        prePage = intPage - 1;
                }
                if (intPage < intPageCount)
                {
                        nextPage = intPage + 1;
                }
                //��ҳ��Ϣ����������
                pageInfo.add(new Integer(intRowCount));
                pageInfo.add(new Integer(intPageCount));
                pageInfo.add(new Integer(intPage));
                pageInfo.add(new Integer(prePage));
                pageInfo.add(new Integer(nextPage));
                return pageInfo;
        }

        /**
         * ȡ�÷�ҳ�ַ���
         *
         * @param totalRow ��ҳ��
         * @param intPage  ��ǰҳ��
         */
        public String getPageInfoStr(int totalRow, int intPage)
        {
                this.intPage = intPage;
                Vector pageInfo = this.getPageInfo(totalRow, intPage);
                return this.getPageInfoStr(pageInfo);
        }

        /**
         * ȡ�÷�ҳ�ַ���
         * ************************************************************************
         * ��ҳ��Ϣ����������Ϊ��form1����form��
         * �����ʱִ��checkForm(command)����(command=turnPage),ִ�б�Ҫ��ҳ�洦��
         * ҳ�ű���Ϊ��intPage
         * ************************************************************************
         *
         * @param pageInfo ��ҳ����
         */
        public String getPageInfoStr(Vector pageInfo)
        {
                StringBuffer pageStr = new StringBuffer();
                if (pageInfo.size() == 5)
                {
                        pageStr.append("�� " + pageInfo.elementAt(0).toString() + " ����¼");
                        pageStr.append(" �� " + pageInfo.elementAt(1).toString() + " ҳ");
                        pageStr.append(" ��ǰ�� " + intPage + " ҳ");
                        int cur = Integer.parseInt(pageInfo.elementAt(2).toString());
                        int pre = Integer.parseInt(pageInfo.elementAt(3).toString());
                        if (pre == 0 || (pre >= cur))
                        {
                                pageStr.append(" ��һҳ");
                        }
                        else
                        {
                                pageStr.append(" <a href=\"javaScript:form1.intPage.value='" + pre + "';checkForm('turnPage')\">��һҳ</a>");
                        }
                        int nex = Integer.parseInt(pageInfo.elementAt(4).toString());
                        if (nex == 0 || (cur >= nex))
                        {
                                pageStr.append(" ��һҳ");
                        }
                        else
                        {
                                pageStr.append(" <a href=\"javaScript:form1.intPage.value='" + nex + "';checkForm('turnPage')\">��һҳ</a>");
                        }
                        pageStr.append(" ����<select name='intPage' onChange=\"checkForm('turnPage')\">");
                        for (int i = 1; i <= Integer.parseInt(pageInfo.elementAt(1).toString()); i++)
                        {
                                String se = (intPage == i) ? "selected" : "";
                                pageStr.append("<option value='" + i + "' " + se + " >" + i + "</option>");
                        }
                        if (Integer.parseInt(pageInfo.elementAt(1).toString()) == 0)
                        {
                                pageStr.append("<option value='1'>1</option>");
                        }
                        pageStr.append("</select>");
                }
                else
                {
                        pageStr.append("&nbsp;");
                }
                return pageStr.toString();
        }

        /**
         * ȡ��ָ�����������ݵ�sql��䣬���ڷ�ҳ
         * ������ORACLE 8.16 �Լ��Ժ�İ汾
         *
         * @param strSQL  ԭʼ��sql
         * @param intPage Ҫȡ�õڼ�ҳ������
         * @return ȡ����Ҫ���ݽ������SQL
         */
        public String getTargetSQL(String strSQL, int intPage)
        {
                String strTargetSQL = "";
                int iBegin = (intPage - 1) * this.intPageSize + 1;
                int iEnd = intPage * this.intPageSize;
                strTargetSQL = "select * from (select t.*,rownum as rowno from (" + strSQL + " ) t)where rowno>='" + iBegin + "' and rowno<='" + iEnd + "'";
                //return strTargetSQL;
                return strSQL;
        }

        /**
         * ͨ������ȡ��Sequence
         *
         * @param conn
         * @param tableName
         * @return
         * @throws SQLException
         */
        public String getNextVal(Connection conn, String tableName) throws SQLException
        {
                String id = "";
                Statement pstmt = null;
                String query = "update SEQ_" + tableName + "_IDS set pk=pk+1";
                String query1 = "select pk from SEQ_" + tableName + "_IDS";
                pstmt = conn.createStatement();
                System.out.println(query);
                System.out.println(query1);
                if (pstmt.executeUpdate(query) > 0)
                {
                        Statement pstmt1 = conn.createStatement();
                        ResultSet rst = pstmt1.executeQuery(query1);
                        if (rst.next())
                        {
                                id = rst.getString(1);
                        }
                        pstmt1.close();
                }
                pstmt.close();
                return id;
        }

        /**
         * ˵����������
         *
         * @param args
         */
        public static void main(String[] args)
        {
                PageObject pageObject1 = new PageObject();
        }
}