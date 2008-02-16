/**
 * Created by IntelliJ IDEA.
 * Report: xiejh
 * Date: Apr 8, 2004
 * Time: 9:36:22 AM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.tools.report.util.dao;

import com.ulearning.ulms.tools.report.util.exceptions.UtilDAOSysException;
import com.ulearning.ulms.util.log.LogUtil;
import com.ulearning.ulms.util.DBUtil;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.core.exceptions.ULMSSysException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UtilDAOImpl implements UtilDAO
{
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        public List getList(String sql, int fieldNum, int pageNo, int pageSize) throws UtilDAOSysException
        {
                String[] strArray = new String[fieldNum];
                ArrayList resultList = new ArrayList();
                String countSqlStr = "";
                int tmp = sql.indexOf("from");
                if (tmp < 0)
                {
                        LogUtil.debug("system", "[UtilDAOImpl]============SQLException  sql is " + sql);
                }
                else
                {
                        countSqlStr = "select count(*) " + sql.substring(tmp - 1);
                }
                ResultSet rs = null;
                int count = 0;
                int totalPageNumbers = 0;
                try
                {
                        //get the total count of result
                        LogUtil.debug("system", "[UtilDAOImpl] sql_count=" + countSqlStr);
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(countSqlStr);
                        if (rs.next())
                        {
                                count = rs.getInt(1);
                        }

                        LogUtil.debug("system", "[UtilDAOImpl]============the sql string is : " + sql);
                        rs = stmt.executeQuery(sql);
                        if (count % pageSize == 0)
                        {
                                totalPageNumbers = count / pageSize;
                        }
                        else
                        {
                                totalPageNumbers = count / pageSize + 1;
                        }

                        if (pageNo > totalPageNumbers)
                        {
                                pageNo = totalPageNumbers;
                        }

                        for (int k = 0; k < pageSize * (pageNo - 1) && rs.next(); k++)
                        {
                                ;
                        }
                        for (int j = 0; rs.next() && j < pageSize; j++)
                        {
                                strArray = convertRs2Array(rs, fieldNum);
                                resultList.add(strArray);
                        }
                }

                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new UtilDAOSysException("SQLException while getList sql=" + sql + " :\n" + se);
                }
                catch (ULMSSysException se)
                {
                        throw new UtilDAOSysException("SQLException while getList sql=" + sql + " :\n" + se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }
                return resultList;
        }


        public String getSQLOneValue(String sql) throws UtilDAOSysException
        {
                String re = "";
                try
                {
                        LogUtil.debug("system", "[ReportDAOImpl]====================the sql string is : " + sql);
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(sql);
                        if (rs.next())
                        {
                                re = rs.getString(1);
                        }
                }
                catch (SQLException se)
                {
                        throw new UtilDAOSysException("SQLException while getSQLOneValue sql = " + sql + " :\n" + se);
                }
                catch (ULMSSysException se)
                {
                        throw new UtilDAOSysException("SQLException while getSQLOneValue sql = " + sql + " :\n" + se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }
                return re;
        }

        /**
         * Convert the resultSet object to String[]
         *
         * @param rs the resultSet which needs to convert
         * @return the wanted reportForm
         */
        private String[] convertRs2Array(ResultSet rs, int arraySize)
        {
                String[] str = new String[arraySize];
                try
                {
                        for (int rsIndex = 1; rsIndex <= arraySize; rsIndex++)
                        {
                                str[rsIndex - 1] = StringUtil.nullToStr(rs.getString(rsIndex));
                        }

                }
                catch (SQLException sql)
                {
                        sql.printStackTrace();
                }
                return str;
        }

}
