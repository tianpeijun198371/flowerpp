/** * ChoiceItemHelper.java.
 * ChoiceItem: xiejh  Date: 2004-4-22 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.test.question.choiceitem.bean;

import com.ulearning.ulms.course.test.question.choiceitem.dao.ChoiceItemDAO;
import com.ulearning.ulms.course.test.question.choiceitem.dao.ChoiceItemDAOFactory;
import com.ulearning.ulms.course.test.question.choiceitem.exceptions.ChoiceItemDAOSysException;
import com.ulearning.ulms.course.test.question.choiceitem.form.ChoiceItemForm;

import java.util.List;


public class ChoiceItemHelper
{
        /**
         * Wrapping the get choiceitem method for JSP and  the other modules
         *
         * @param choiceitemID
         * @return the choiceitem modle according to the choiceitemID
         */
        public ChoiceItemForm getChoiceItem(int choiceitemID)
        {
                ChoiceItemForm bf = null;

                try
                {
                        ChoiceItemDAO choiceitemDao = ChoiceItemDAOFactory.getDAO();
                        bf = choiceitemDao.getChoiceItem(choiceitemID);
                }
                catch (ChoiceItemDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return bf;
        }

        /**
         * Wrapping the get choiceitemList method for JSP and  the other modules
         *
         * @param questionID
         * @return the choiceitem list according to the catalog
         */
        public List getChoiceItemList(int questionID)
        {
                List choiceitemList = null;

                try
                {
                        ChoiceItemDAO choiceitemDao = ChoiceItemDAOFactory.getDAO();
                        choiceitemList = choiceitemDao.getChoiceItemList(questionID);
                }
                catch (ChoiceItemDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return choiceitemList;
        }
}
