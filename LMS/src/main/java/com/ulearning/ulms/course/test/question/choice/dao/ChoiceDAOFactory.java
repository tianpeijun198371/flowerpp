/**
 * Created by IntelliJ IDEA.
 * Choice: dengj
 * Date: Apr 8, 2004
 * Time: 9:12:45 AM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.course.test.question.choice.dao;

import com.ulearning.ulms.course.test.question.choice.exceptions.ChoiceDAOSysException;
import com.ulearning.ulms.course.test.question.choiceitem.dao.ChoiceItemDAOImpl;
import com.ulearning.ulms.util.DBUtil;


public class ChoiceDAOFactory
{
        /**
         * This method instantiates a particular subclass implementing
         * the DAO methods based on the information obtained from the
         * deployment descriptor
         */
        public static ChoiceDAO getDAO() throws ChoiceDAOSysException
        {
                ChoiceDAO dao = null;

                try
                {
                        dao = new ChoiceDAOImpl();
                }
                catch (Exception se)
                {
                        throw new ChoiceDAOSysException(
                                "ChoiceDAOFactory.getDAO:  Exception while getting DAO type : \n" +
                                        se.getMessage());
                }

                return dao;
        }
}
