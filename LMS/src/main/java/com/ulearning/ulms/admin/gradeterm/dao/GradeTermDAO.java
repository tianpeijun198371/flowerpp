/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.admin.gradeterm.dao;

import com.ulearning.ulms.admin.gradeterm.exceptions.GradeTermDAOSysException;
import com.ulearning.ulms.admin.gradeterm.form.GradeTermForm;

import java.io.Serializable;

import java.util.List;


/**
 * Class description goes here.
 * <p/>
 * Created by Auto Code Produce System
 * User: xiejh
 * Date: 20060321
 * Time: 182730
 */
public interface GradeTermDAO
{
        public Serializable insertGradeTerm(GradeTermForm tf)
                throws GradeTermDAOSysException;

        public void updateGradeTerm(GradeTermForm tf)
                throws GradeTermDAOSysException;

        public void deleteGradeTerm(int gradetID) throws GradeTermDAOSysException;

        public List getGradeTermList() throws GradeTermDAOSysException;

        public GradeTermForm getGradeTerm(int gradetID)
                throws GradeTermDAOSysException;

        public GradeTermForm getGradeTermSpe(String grade, String term, String speci)
                throws GradeTermDAOSysException;
}
