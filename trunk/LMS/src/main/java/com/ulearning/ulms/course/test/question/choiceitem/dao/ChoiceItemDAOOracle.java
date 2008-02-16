/**
 * Created by IntelliJ IDEA.
 * ChoiceItem: dengj
 * Date: Apr 8, 2004
 * Time: 9:36:03 AM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.course.test.question.choiceitem.dao;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.course.test.question.choiceitem.exceptions.ChoiceItemDAOSysException;
import com.ulearning.ulms.course.test.question.choiceitem.form.ChoiceItemForm;
import com.ulearning.ulms.util.log.LogUtil;

import java.sql.Statement;


public class ChoiceItemDAOOracle extends ChoiceItemDAOImpl
{
        /**
         * Insert a new choiceitem record to database
         * @param choiceitemForm   the value object to be added
         * @throws ChoiceItemDAOSysException
         */

        /*
          public void addChoiceItem(ChoiceItemForm choiceitemForm) throws ChoiceItemDAOSysException
          {
                  String sqlStr = "insert into T_ChoiceItem_Tab(QuestionID,ChoiceItemID,Title,Link) values("+
                          choiceitemForm.getQuestionID() + "," +
                          "ChoiceItemID.nextval" +  ",'"+
                          choiceitemForm.getItemTitle() + "','" +
                          choiceitemForm.getLink() + "')";
                  try
                  {
                      LogUtil.debug("system", "[ChoiceItemDAOOracle]====================the sql string is : " + sqlStr);
                  } catch (ULMSSysException se)
                  {
                      throw new ChoiceItemDAOSysException("SQLException while updating " + "choiceitem; Serial = " + choiceitemForm.getItemTitle() + " :\n" + se);
                  }
          }
        */

        /**
         * Update choiceitemInfo by the new Form
         * @param choiceitemForm   value object for changed
         * @throws ChoiceItemDAOSysException
         */

        /*
          public void updateChoiceItem(ChoiceItemForm choiceitemForm) throws ChoiceItemDAOSysException
          {
              String sqlStr = "update T_ChoiceItem_Tab set " +
                      "Title = '" + choiceitemForm.getItemTitle() + "', " +
                      "Link = '" + choiceitemForm.getLink() + "' " +
                      " where QuestionID = " + choiceitemForm.getQuestionID()+
                      " and ChoiceItemID=" + choiceitemForm.getChoiceItemID();
              try
              {
                      LogUtil.debug("system", "[ChoiceItemDAOOracle]====================the sql string is : " + sqlStr);
              } catch (ULMSSysException se)
              {
                  throw new ChoiceItemDAOSysException("SQLException while updating " + "account; Serial = " + choiceitemForm.getItemTitle() + " :\n" + se);
              }
          }
        */
}
