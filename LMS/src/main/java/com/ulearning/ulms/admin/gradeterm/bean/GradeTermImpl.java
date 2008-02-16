/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.admin.gradeterm.bean;

import com.ulearning.ulms.admin.gradeterm.dao.GradeTermDAO;
import com.ulearning.ulms.admin.gradeterm.dao.GradeTermDAOFactory;
import com.ulearning.ulms.admin.gradeterm.exceptions.GradeTermDAOSysException;
import com.ulearning.ulms.admin.gradeterm.form.GradeTermForm;

import java.util.List;


/**
 * Class description goes here.
 * <p/>
 * Created by Auto Code Produce System
 * User: xiejh
 * Date: 20060321
 * Time: 182730
 */
public class GradeTermImpl
{
        public static List getGradeTermList() throws GradeTermDAOSysException
        {
                List GradeTermList = null;

                try
                {
                        GradeTermDAO dao = GradeTermDAOFactory.getDAO();
                        GradeTermList = dao.getGradeTermList();
                }
                catch (GradeTermDAOSysException tdse)
                {
                        tdse.printStackTrace();
                }

                return GradeTermList;
        }

        public static GradeTermForm getGradeTerm(int gradetID)
                throws GradeTermDAOSysException
        {
                GradeTermForm tf = null;

                try
                {
                        GradeTermDAO dao = GradeTermDAOFactory.getDAO();
                        tf = dao.getGradeTerm(gradetID);
                }
                catch (GradeTermDAOSysException tdse)
                {
                        tdse.printStackTrace();
                }

                return tf;
        }

        public static GradeTermForm getGradeTermSpe(String grade, String term,
                                                    String speci) throws GradeTermDAOSysException
        {
                GradeTermForm tf = null;

                try
                {
                        GradeTermDAO dao = GradeTermDAOFactory.getDAO();
                        tf = dao.getGradeTermSpe(grade, term, speci);
                }
                catch (GradeTermDAOSysException tdse)
                {
                        tdse.printStackTrace();
                }

                return tf;
        }

        public static void deleteGradeTerm(int gradetID)
                throws GradeTermDAOSysException
        {
                try
                {
                        GradeTermDAO dao = GradeTermDAOFactory.getDAO();
                        dao.deleteGradeTerm(gradetID);
                }
                catch (GradeTermDAOSysException tdse)
                {
                        tdse.printStackTrace();
                }
        }
}
