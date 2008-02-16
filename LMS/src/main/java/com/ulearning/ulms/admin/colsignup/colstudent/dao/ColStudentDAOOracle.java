/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-8-20
 * Time: 10:09:55
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.admin.colsignup.colstudent.dao;

import com.ulearning.ulms.admin.colsignup.colstudent.exceptions.ColStudentDAOSysException;
import com.ulearning.ulms.admin.colsignup.colstudent.form.ColStudentForm;
import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.util.OperateDB;
import com.ulearning.ulms.util.log.LogUtil;


public class ColStudentDAOOracle extends ColStudentDAOImpl
{
        public void addColStudent(ColStudentForm csf)
                throws ColStudentDAOSysException
        {
                String strSql = "";
                OperateDB operateDB = new OperateDB();
                strSql = "Insert into C_ColStudent_Tab Values( " +
                        csf.getColSignDetailID() + "," + csf.getRelationID() + "," +
                        csf.getType() + ",0)";
                LogUtil.debug("colstudent", "[ColStudentDAOOracle] " + strSql);

                try
                {
                        int total = operateDB.exeupdate(strSql);
                        LogUtil.debug("colstudent",
                                "[ColStudentDAOOracle] " + "一共插入 " + total + "行");
                }
                catch (ULMSSysException se)
                {
                        se.printStackTrace();
                        throw new ColStudentDAOSysException(
                                "SQLException while Insert Into C_ColStudent_Tab;  :\n" + se);
                }
        }

        public void updateColStudent(ColStudentForm csf)
                throws ColStudentDAOSysException
        {
                String strSql = "";
                OperateDB operateDB = new OperateDB();
                strSql = "Update C_ColStudent_Tab set Approved = '" +
                        csf.getApproved() + "' Where ColSignDetailID = " +
                        csf.getColSignDetailID() + " and RelationID = " +
                        csf.getRelationID() + " and Type = " + csf.getType();
                LogUtil.debug("colstudent", "[ColStudentDAOOracle] " + strSql);

                try
                {
                        int total = operateDB.exeupdate(strSql);
                        LogUtil.debug("colstudent",
                                "[ColStudentDAOOracle] " + "一共修改 " + total + "行");
                }
                catch (ULMSSysException se)
                {
                        se.printStackTrace();
                        throw new ColStudentDAOSysException(
                                "SQLException while Update C_ColStudent_Tab;  :\n" + se);
                }
        }
}
