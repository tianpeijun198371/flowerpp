package com.ulearning.ulms.util;


import java.sql.*;
import java.util.*;
import java.lang.StringBuffer;

//import com.properties.*;

public class PageObject
{

        public Connection conn = null;
        public ResultSet rst = null;

        /* 每页的记录数 */
        public int intPageSize = 10;
        /* 总页数 */
        public int intPageCount = 1;
        /* 总记录数 */
        public int intRowCount = 0;
        /* 当前的页数 */
        public int intPage = 1;


        /**
         * 说明：每页的记录数属性
         *
         * @param intPageSize 每页的记录数
         */
        public void setIntPageSize(int intPageSize)
        {
                this.intPageSize = intPageSize;
        }

        /**
         * 说明：每页的记录数属性
         *
         * @return int 每页的记录数
         */
        public int getIntPageSize()
        {
                return intPageSize;
        }

        /**
         * 说明：总页数属性
         *
         * @param intPageCount 总页数
         */
        public void setIntPageCount(int intPageCount)
        {
                this.intPageCount = intPageCount;
        }

        /**
         * 说明：总页数属性
         *
         * @return int 总页数
         */
        public int getIntPageCount()
        {
                return intPageCount;
        }

        /**
         * 说明：总记录数属性
         *
         * @param intRowCount 总记录数
         */
        public void setIntRowCount(int intRowCount)
        {
                this.intRowCount = intRowCount;
        }

        /**
         * 说明：总记录数属性
         *
         * @return int 总记录数
         */
        public int getIntRowCount()
        {
                return intRowCount;
        }

        /**
         * 说明：当前的页数
         *
         * @param intPage 当前的页数
         */
        public void setIntPage(int intPage)
        {
                this.intPage = intPage;
        }

        /**
         * 说明：当前的页数
         *
         * @return int 当前的页数
         */
        public int getIntPage()
        {
                return intPage;
        }

        /**
         * 说明：构造函数
         */
        public PageObject()
        {
        }

        /**
         * 说明：取得记录总数（个部分通用）
         *
         * @param conn 数据库链接实例
         * @param sql  SQL语句
         * @return int 取得记录总数
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
         * 说明：取得分页信息（个部分通用）
         *
         * @param totalRow 总的记录数
         * @param intPage  当前页数
         * @return Vector 分页信息
         */
        public Vector getPageInfo(int totalRow, int intPage)
        {
                Vector pageInfo = new Vector();
                //总记录数
                intRowCount = totalRow;
                //总页数
                intPageCount = (intRowCount + intPageSize - 1) / intPageSize;
                //调整当前页数合法
                if (intPage > intPageCount)
                {
                        intPage = intPageCount;
                }
                if (intPage < 1)
                {
                        intPage = 1;
                }
                //调整上、下页数
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
                //分页信息存于向量中
                pageInfo.add(new Integer(intRowCount));
                pageInfo.add(new Integer(intPageCount));
                pageInfo.add(new Integer(intPage));
                pageInfo.add(new Integer(prePage));
                pageInfo.add(new Integer(nextPage));
                return pageInfo;
        }

        /**
         * 取得分页字符串
         *
         * @param totalRow 总页数
         * @param intPage  当前页数
         */
        public String getPageInfoStr(int totalRow, int intPage)
        {
                this.intPage = intPage;
                Vector pageInfo = this.getPageInfo(totalRow, intPage);
                return this.getPageInfoStr(pageInfo);
        }

        /**
         * 取得分页字符串
         * ************************************************************************
         * 分页信息必须在名字为“form1”的form中
         * 当点击时执行checkForm(command)函数(command=turnPage),执行必要的页面处理
         * 页号变量为＝intPage
         * ************************************************************************
         *
         * @param pageInfo 分页向量
         */
        public String getPageInfoStr(Vector pageInfo)
        {
                StringBuffer pageStr = new StringBuffer();
                if (pageInfo.size() == 5)
                {
                        pageStr.append("共 " + pageInfo.elementAt(0).toString() + " 条记录");
                        pageStr.append(" 共 " + pageInfo.elementAt(1).toString() + " 页");
                        pageStr.append(" 当前第 " + intPage + " 页");
                        int cur = Integer.parseInt(pageInfo.elementAt(2).toString());
                        int pre = Integer.parseInt(pageInfo.elementAt(3).toString());
                        if (pre == 0 || (pre >= cur))
                        {
                                pageStr.append(" 上一页");
                        }
                        else
                        {
                                pageStr.append(" <a href=\"javaScript:form1.intPage.value='" + pre + "';checkForm('turnPage')\">上一页</a>");
                        }
                        int nex = Integer.parseInt(pageInfo.elementAt(4).toString());
                        if (nex == 0 || (cur >= nex))
                        {
                                pageStr.append(" 下一页");
                        }
                        else
                        {
                                pageStr.append(" <a href=\"javaScript:form1.intPage.value='" + nex + "';checkForm('turnPage')\">下一页</a>");
                        }
                        pageStr.append(" 跳到<select name='intPage' onChange=\"checkForm('turnPage')\">");
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
         * 取得指定数量的数据的sql语句，用于分页
         * 仅限于ORACLE 8.16 以及以后的版本
         *
         * @param strSQL  原始的sql
         * @param intPage 要取得第几页的数据
         * @return 取得所要数据结果集的SQL
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
         * 通过表名取得Sequence
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
         * 说明：主函数
         *
         * @param args
         */
        public static void main(String[] args)
        {
                PageObject pageObject1 = new PageObject();
        }
}