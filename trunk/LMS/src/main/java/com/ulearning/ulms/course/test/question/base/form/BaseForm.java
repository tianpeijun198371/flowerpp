/**
 * Created by IntelliJ IDEA.
 * Base: dengj
 * Date: Apr 7, 2004
 * Time: 4:51:49 PM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.course.test.question.base.form;

import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.course.test.question.base.model.QuestionModel;
import com.ulearning.ulms.tools.upload.model.UploadForm;

import org.apache.struts.action.ActionForm;

import java.util.Date;


public class BaseForm extends UploadForm
{
        private int questionID = 0;
        private String title = null;
        private String type = null;
        private String isContent = null;
        private int hardLevel = 0;
        private String key = null;
        private int parentID = 0;
        private String chapter = null;
        private String scope = null;
        private String point = null;
        private String object = null;
        private int courseID = 0;
        private float score = 0;
        private String correctReply = null;
        private String incorrectReply = null;
        private String correctAnswer = null;
        private String link = null;
        private Date createTime = null;
        private String createTimeStr = null;
        private Date updateTime = null;
        private String updateTimeStr = null;
        private String description = null;
        private String remark = null;
        private String desc1 = "";
        private String desc2 = "";
        private String desc3 = "";
        private String desc4 = "";
        private String desc5 = "";
        private String desc6 = "";
        private String desc7 = "";
        private QuestionModel questionModel = null;

        public QuestionModel getQuestionModel()
        {
                questionModel = new QuestionModel();
                questionModel.setQuestionID(this.questionID);
                questionModel.setTitle(this.title);
                questionModel.setType(this.type);
                questionModel.setIsContent(this.isContent);
                questionModel.setHardLevel(this.hardLevel);
                questionModel.setKey(this.key);
                questionModel.setParentID(this.parentID);
                questionModel.setChapter(this.chapter);
                questionModel.setScope(this.scope);
                questionModel.setPoint(this.point);
                questionModel.setObject(this.object);
                questionModel.setCourseID(this.courseID);
                questionModel.setScore(this.score);
                questionModel.setCorrectReply(this.correctReply);
                questionModel.setIncorrectReply(this.incorrectReply);
                questionModel.setCorrectAnswer(this.correctAnswer);
                questionModel.setLink(this.link);
                questionModel.setCreateTime(this.createTime);
                questionModel.setUpdateTime(this.updateTime);
                questionModel.setDescription(this.description);
                questionModel.setRemark(this.remark);
                questionModel.setDesc1(this.desc1);
                questionModel.setDesc2(this.desc2);
                questionModel.setDesc3(this.desc3);
                questionModel.setDesc4(this.desc4);
                questionModel.setDesc5(this.desc5);
                questionModel.setDesc6(this.desc6);
                questionModel.setDesc7(this.desc7);

                return questionModel;
        }

        public BaseForm getBaseForm(QuestionModel questionModel)
        {
                BaseForm baseForm = new BaseForm();
                baseForm.setQuestionID(questionModel.getQuestionID());
                baseForm.setTitle(questionModel.getTitle());
                baseForm.setType(questionModel.getType());
                baseForm.setIsContent(questionModel.getIsContent());
                baseForm.setHardLevel(questionModel.getHardLevel());
                baseForm.setKey(StringUtil.nullToStr(questionModel.getKey()));
                baseForm.setParentID(questionModel.getParentID());
                baseForm.setChapter(StringUtil.nullToStr(questionModel.getChapter()));
                baseForm.setScope(StringUtil.nullToStr(questionModel.getScope()));
                baseForm.setPoint(StringUtil.nullToStr(questionModel.getPoint()));
                baseForm.setObject(StringUtil.nullToStr(questionModel.getObject()));
                baseForm.setCourseID(questionModel.getCourseID());
                baseForm.setScore(questionModel.getScore());
                baseForm.setCorrectReply(StringUtil.nullToStr(
                        questionModel.getCorrectReply()));
                baseForm.setIncorrectReply(StringUtil.nullToStr(
                        questionModel.getIncorrectReply()));
                baseForm.setCorrectAnswer(questionModel.getCorrectAnswer());
                baseForm.setLink(StringUtil.nullToStr(questionModel.getLink()));
                baseForm.setCreateTime(questionModel.getCreateTime());
                baseForm.setUpdateTime(questionModel.getUpdateTime());
                baseForm.setDescription(StringUtil.nullToStr(
                        questionModel.getDescription()));
                baseForm.setRemark(StringUtil.nullToStr(questionModel.getRemark()));
                baseForm.setDesc1(StringUtil.nullToStr(questionModel.getDesc1()));
                baseForm.setDesc2(StringUtil.nullToStr(questionModel.getDesc2()));
                baseForm.setDesc3(StringUtil.nullToStr(questionModel.getDesc3()));
                baseForm.setDesc4(StringUtil.nullToStr(questionModel.getDesc4()));
                baseForm.setDesc5(StringUtil.nullToStr(questionModel.getDesc5()));
                baseForm.setDesc6(StringUtil.nullToStr(questionModel.getDesc6()));
                baseForm.setDesc7(StringUtil.nullToStr(questionModel.getDesc7()));

                return baseForm;
        }

        public int getQuestionID()
        {
                return questionID;
        }

        public void setQuestionID(int questionID)
        {
                this.questionID = questionID;
        }

        public String getTitle()
        {
                return title;
        }

        public void setTitle(String title)
        {
                this.title = title;
        }

        public String getType()
        {
                return type;
        }

        public void setType(String type)
        {
                this.type = type;
        }

        public String getIsContent()
        {
                return isContent;
        }

        public void setIsContent(String isContent)
        {
                this.isContent = isContent;
        }

        public int getHardLevel()
        {
                return hardLevel;
        }

        public void setHardLevel(int hardLevel)
        {
                this.hardLevel = hardLevel;
        }

        public String getKey()
        {
                return key;
        }

        public void setKey(String key)
        {
                this.key = key;
        }

        public int getParentID()
        {
                return parentID;
        }

        public void setParentID(int parentID)
        {
                this.parentID = parentID;
        }

        public String getChapter()
        {
                return chapter;
        }

        public void setChapter(String chapter)
        {
                this.chapter = chapter;
        }

        public String getScope()
        {
                return scope;
        }

        public void setScope(String scope)
        {
                this.scope = scope;
        }

        public String getPoint()
        {
                return point;
        }

        public void setPoint(String point)
        {
                this.point = point;
        }

        public String getObject()
        {
                return object;
        }

        public void setObject(String object)
        {
                this.object = object;
        }

        public int getCourseID()
        {
                return courseID;
        }

        public void setCourseID(int courseID)
        {
                this.courseID = courseID;
        }

        public float getScore()
        {
                return score;
        }

        public void setScore(float score)
        {
                this.score = score;
        }

        public String getCorrectReply()
        {
                return correctReply;
        }

        public void setCorrectReply(String correctReply)
        {
                this.correctReply = correctReply;
        }

        public String getIncorrectReply()
        {
                return incorrectReply;
        }

        public void setIncorrectReply(String incorrectReply)
        {
                this.incorrectReply = incorrectReply;
        }

        public String getCorrectAnswer()
        {
                return correctAnswer;
        }

        public void setCorrectAnswer(String correctAnswer)
        {
                this.correctAnswer = correctAnswer;
        }

        public String getLink()
        {
                return link;
        }

        public void setLink(String link)
        {
                this.link = link;
        }

        public String getDescription()
        {
                return description;
        }

        public Date getCreateTime()
        {
                return createTime;
        }

        public void setCreateTime(Date createTime)
        {
                this.createTime = createTime;
        }

        public String getCreateTimeStr()
        {
                return createTimeStr;
        }

        public void setCreateTimeStr(String createTimeStr)
        {
                this.createTimeStr = createTimeStr;
        }

        public Date getUpdateTime()
        {
                return updateTime;
        }

        public void setUpdateTime(Date updateTime)
        {
                this.updateTime = updateTime;
        }

        public String getUpdateTimeStr()
        {
                return updateTimeStr;
        }

        public void setUpdateTimeStr(String updateTimeStr)
        {
                this.updateTimeStr = updateTimeStr;
        }

        public void setDescription(String description)
        {
                this.description = description;
        }

        public String getRemark()
        {
                return remark;
        }

        public void setRemark(String remark)
        {
                this.remark = remark;
        }

        public String getDesc1()
        {
                return desc1;
        }

        public void setDesc1(String desc1)
        {
                this.desc1 = desc1;
        }

        public String getDesc2()
        {
                return desc2;
        }

        public void setDesc2(String desc2)
        {
                this.desc2 = desc2;
        }

        public String getDesc3()
        {
                return desc3;
        }

        public void setDesc3(String desc3)
        {
                this.desc3 = desc3;
        }

        public String getDesc4()
        {
                return desc4;
        }

        public void setDesc4(String desc4)
        {
                this.desc4 = desc4;
        }

        public String getDesc5()
        {
                return desc5;
        }

        public void setDesc5(String desc5)
        {
                this.desc5 = desc5;
        }

        public String getDesc6()
        {
                return desc6;
        }

        public void setDesc6(String desc6)
        {
                this.desc6 = desc6;
        }

        public String getDesc7()
        {
                return desc7;
        }

        public void setDesc7(String desc7)
        {
                this.desc7 = desc7;
        }
}
