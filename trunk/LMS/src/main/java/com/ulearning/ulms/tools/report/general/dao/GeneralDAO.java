/**
 * Created by IntelliJ IDEA.
 * Report: dengj
 * Date: Apr 7, 2004
 * Time: 5:06:46 PM
 * To change this template use Options | File Templates.
 */

package com.ulearning.ulms.tools.report.general.dao;

import com.ulearning.ulms.tools.report.general.exceptions.GeneralDAOSysException;

import java.util.List;


public interface GeneralDAO
{

        public List getCategoryFormList(int ModulID) throws GeneralDAOSysException;

        public List getReportTypeFormList(int CategoryID) throws GeneralDAOSysException;

        public List getMReportFormList(String ReportType) throws GeneralDAOSysException;

        public List getFieldItemFormList(int MReportID) throws GeneralDAOSysException;

        public List getConditionItemFormList(String ReportType) throws GeneralDAOSysException;


}
