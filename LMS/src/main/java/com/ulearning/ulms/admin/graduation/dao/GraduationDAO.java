/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-9-7
 * Time: 13:49:01
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.admin.graduation.dao;

import com.ulearning.ulms.admin.graduation.exceptions.GraduationAppException;
import com.ulearning.ulms.admin.graduation.form.GraduationForm;

import java.util.List;


public interface GraduationDAO
{
        public void addGraduation(GraduationForm gm) throws GraduationAppException;

        public void modifyGraduation(GraduationForm gm)
                throws GraduationAppException;

        public void deleteGraduation(GraduationForm gm)
                throws GraduationAppException;

        public List getGraduationList(int RelationID, int type, int status)
                throws GraduationAppException;

        public GraduationForm getGraduation(int RelationID, int type, int userID)
                throws GraduationAppException;

        public double getTotalGreditOrScore(int relationID, int type, int userID,
                                            int courseType) throws GraduationAppException;

        public boolean isDuplicate(String certNo, int relationID, int type,
                                   int userID) throws GraduationAppException;

        public List searchCert(String loginName, String name)
                throws GraduationAppException;
}
