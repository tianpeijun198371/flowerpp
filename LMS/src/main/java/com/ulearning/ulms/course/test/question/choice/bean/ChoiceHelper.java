/** * ChoiceHelper.java.
 * Choice: xiejh  Date: 2004-4-22 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.test.question.choice.bean;

import com.ulearning.ulms.course.test.question.choice.dao.ChoiceDAO;
import com.ulearning.ulms.course.test.question.choice.dao.ChoiceDAOFactory;
import com.ulearning.ulms.course.test.question.choice.exceptions.ChoiceDAOSysException;
import com.ulearning.ulms.course.test.question.choice.form.ChoiceForm;

import java.util.List;


public class ChoiceHelper
{
        /**
         * Wrapping the get choice method for JSP and  the other modules
         *
         * @param questionID
         * @return the choice modle according to the questionID
         */
        public ChoiceForm getChoice(int questionID)
        {
                ChoiceForm bf = null;

                try
                {
                        ChoiceDAO choiceDao = ChoiceDAOFactory.getDAO();
                        bf = choiceDao.getChoice(questionID);
                }
                catch (ChoiceDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return bf;
        }

        /**
         * Wrapping the get choiceList method for JSP and  the other modules
         *
         * @param questionID
         * @return the choice list according to the catalog
         */
        public List getChoiceList(List questionID)
        {
                List choiceList = null;

                try
                {
                        ChoiceDAO choiceDao = ChoiceDAOFactory.getDAO();
                        choiceList = choiceDao.getChoiceList(questionID);
                }
                catch (ChoiceDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return choiceList;
        }
}
