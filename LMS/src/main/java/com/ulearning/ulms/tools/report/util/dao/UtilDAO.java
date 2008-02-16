/**
 * Created by IntelliJ IDEA.
 * Report: xiejh
 * Date: Apr 7, 2004
 * Time: 5:06:46 PM
 * To change this template use Options | File Templates.
 */

package com.ulearning.ulms.tools.report.util.dao;

import com.ulearning.ulms.tools.report.util.exceptions.UtilDAOSysException;

import java.util.List;


public interface UtilDAO
{
        public List getList(String sql, int fieldNum, int pageNo, int pageSize) throws UtilDAOSysException;

        public String getSQLOneValue(String sql) throws UtilDAOSysException;
}
