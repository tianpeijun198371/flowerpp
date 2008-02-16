/**
 * Created by IntelliJ IDEA.
 * Base: dengj
 * Date: Apr 8, 2004
 * Time: 9:36:03 AM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.course.test.question.base.dao;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.course.test.question.base.exceptions.BaseDAOSysException;
import com.ulearning.ulms.course.test.question.base.form.BaseForm;
import com.ulearning.ulms.util.log.LogUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class BaseDAOOracle extends BaseDAOImpl
{
        /**
         * Insert a new base record to database
         * @param baseForm   the value object to be added
         * @throws BaseDAOSysException
         */

        /*
          public int addBase(BaseForm baseForm) throws BaseDAOSysException
          {
                  java.sql.Date dayToInsert = new java.sql.Date(System.currentTimeMillis());
                  String createDate  = "to_date('" + dayToInsert + "','yyyy-mm-dd')";
                  String updateDate=createDate;
                  int questionID=0;
                  String sqlStr = "insert into T_Question_Tab values(QuestionID.nextval,'" +
                          baseForm.getTitle() + "','" +
                          baseForm.getType() + "','" +
                          baseForm.getIsContent() + "'," +
                          baseForm.getHardLevel() + ",'" +
                          baseForm.getKey() + "'," +
                          baseForm.getParentID() + ",'" +
                          baseForm.getChapter() + "','" +
                          baseForm.getScope() + "','" +
                          baseForm.getPoint() + "','" +
                          baseForm.getObject() + "'," +
                          baseForm.getCourseID() + "," +
                          baseForm.getScore() + ",'" +
                          baseForm.getCorrectReply() + "','" +
                          baseForm.getIncorrectReply() + "','" +
                          baseForm.getCorrectAnswer() + "','" +
                          baseForm.getLink() + "'," +
                          createDate + "," +
                          updateDate + ",'" +
                          baseForm.getDescription() + "','" +
                          baseForm.getRemark() + "','" +
                          baseForm.getDesc1() + "','" +
                          baseForm.getDesc2() + "','" +
                          baseForm.getDesc3() + "','" +
                          baseForm.getDesc4() + "','" +
                          baseForm.getDesc5() + "','" +
                          baseForm.getDesc6() + "','" +
                          baseForm.getDesc7() + "')";
                  try
                  {
                          LogUtil.debug("system", "[question BaseDAOOracle]====================the sql string is : " + sqlStr);
                               String isql = "SELECT QuestionID.currval  as QuestionID FROM DUAL";
                              if (rs.next())
                              {
                                      questionID = rs.getInt("QuestionID");
                              }
                  }
                  catch(SQLException e)
                  {
                        //throw new SQLException("SQLException while updating " + "base; Serial = " + baseForm.getTitle() + " :\n" );
                  }
                  catch (ULMSSysException se)
                  {
                          throw new BaseDAOSysException("SQLException while updating " + "base; Serial = " + baseForm.getTitle() + " :\n" + se);
                  }
                  finally
                  {
                          try
                          {
                          }
                          catch (SQLException e)
                          {
                          }
                  }
                  return questionID;
          }
        */

        /**
         * Update baseInfo by the new Form
         * @param baseForm   value object for changed
         * @throws BaseDAOSysException
         */

        /*
          public void updateBase(BaseForm baseForm) throws BaseDAOSysException
          {
                  java.sql.Date dayToInsert = new java.sql.Date(System.currentTimeMillis());
                  String updateDate  = "to_date('" + dayToInsert + "','yyyy-mm-dd')";
                  String sqlStr = "update T_Question_Tab set " +
                          "Title = '" + baseForm.getTitle() + "'," +
                          "Type = '" + baseForm.getType() + "'," +
                          "IsContent = '" + baseForm.getIsContent() + "'," +
                          "HardLevel = " + baseForm.getHardLevel() + "," +
                          "Key = '" + baseForm.getKey() + "'," +
                          "ParentID = " + baseForm.getParentID() + "," +
                          "Chapter = '" + baseForm.getChapter() + "'," +
                          "Scope = '" + baseForm.getScope() + "'," +
                          "Point = '" + baseForm.getPoint() + "'," +
                          "Object = '" + baseForm.getObject() + "'," +
                          "CourseID = " + baseForm.getCourseID() + "," +
                          "Score = " + baseForm.getScore() + "," +
                          "CorrectReply = '" + baseForm.getCorrectReply() + "'," +
                          "IncorrectReply = '" + baseForm.getIncorrectReply() + "'," +
                          "CorrectAnswer = '" + baseForm.getCorrectAnswer() + "'," +
                          "Link = '" + baseForm.getLink() + "'," +
                          "updateTime = " + updateDate + "," +
                          "Description = '" + baseForm.getDescription() + "'," +
                          "Remark = '" + baseForm.getRemark() + "'," +
                          "Desc1 = '" + baseForm.getDesc1() + "'," +
                          "Desc2 = '" + baseForm.getDesc2() + "'," +
                          "Desc3 = '" + baseForm.getDesc3() + "'," +
                          "Desc4 = '" + baseForm.getDesc4() + "'," +
                          "Desc5 = '" + baseForm.getDesc5() + "'," +
                          "Desc6 = '" + baseForm.getDesc6() + "'," +
                          "Desc7 = '" + baseForm.getDesc7() + "' " +
                          "where QuestionID = " + baseForm.getQuestionID();
              try
              {
                      LogUtil.debug("system", "[BaseDAOOracle]====================the sql string is : " + sqlStr);
              } catch (ULMSSysException se)
              {
                  throw new BaseDAOSysException("SQLException while updating " + "account; Serial = " + baseForm.getTitle() + " :\n" + se);
              }
          }
        */
}
