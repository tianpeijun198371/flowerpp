/**
 * Created by IntelliJ IDEA.
 * Grade: dengj
 * Date: Apr 13, 2004
 * Time: 3:18:40 PM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.course.test.grade.bean;

import com.ulearning.ulms.course.test.grade.dao.GradeDAO;
import com.ulearning.ulms.course.test.grade.dao.GradeDAOFactory;
import com.ulearning.ulms.course.test.grade.exceptions.GradeDAOSysException;
import com.ulearning.ulms.course.test.grade.form.GradeForm;

import java.util.List;


public class GradeHelper
{
        /**
         * Wrapping the get Grade method for JSP and  the other modules
         *
         * @param GradeID
         * @return the Grade modle according to the GradeID
         */
        public static GradeForm getGrade(int GradeID)
        {
                GradeForm uf = null;

                try
                {
                        GradeDAO GradeDao = GradeDAOFactory.getDAO();
                        uf = GradeDao.getGrade(GradeID);
                }
                catch (GradeDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return uf;
        }

        /**
         * Wrapping the get GradeList method for JSP and  the other modules
         *
         * @param courseID
         * @return
         */
        public static List getGradeList(int courseID)
        {
                List GradeList = null;

                try
                {
                        GradeDAO GradeDao = GradeDAOFactory.getDAO();
                        GradeList = GradeDao.getGradeList(courseID);
                }
                catch (GradeDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return GradeList;
        }

        /**
         * Wrapping the get GradeList method for JSP and  the other modules
         *
         * @param GradeID
         * @param type
         * @param courseID
         * @return
         */
        public static List getGradeSelectePaperID(int GradeID, int type,
                                                  int courseID)
        {
                List paperList = null;

                try
                {
                        GradeDAO GradeDao = GradeDAOFactory.getDAO();
                        paperList = GradeDao.getGradeSelectePaperID(GradeID, type, courseID);
                }
                catch (GradeDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return paperList;
        }

        /**
         * Wrapping the get GradeList method for JSP and  the other modules
         *
         * @param GradeID
         * @param type
         * @param courseID
         * @return
         */
        public static List getAllNoSelectedPaperID(int GradeID, int type,
                                                   int courseID)
        {
                List paperList = null;

                try
                {
                        GradeDAO GradeDao = GradeDAOFactory.getDAO();
                        paperList = GradeDao.getGradeNoSelectedPaperID(GradeID, type,
                                courseID);
                }
                catch (GradeDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return paperList;
        }
}
