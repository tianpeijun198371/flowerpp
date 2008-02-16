package com.ulearning.ulms.tools.answerquestion.model;

import com.ulearning.ulms.tools.answerquestion.form.AnswerQuestionForm;
import com.ulearning.ulms.tools.upload.model.UploadForm;

import java.util.Date;


/**
 * @author Hibernate CodeGenerator
 */
public class AnswerQuestionModel extends UploadForm
{
        private int aqID;
        private int submmiterID;
        private int replyerID;
        private String submmiterName;
        private String replyerName;
        private String questionTitle;
        private String questionKey;
        private String questionContent;
        private String questionLink;
        private String questionLinkTitle;
        private String answerContent;
        private String answerLinkTitle;
        private String answerLink;
        private String type;
        private int parentID;
        private int relationID;
        private String relationName;
        private String isReply;
        private String isMessage;
        private String isMail;
        private String isUserful;
        private String isOpenGuest;
        private String isView;
        private int depth;
        private Date displayBeginDate;
        private String Status;
        private Date displayEndDate;
        private Date createDate;
        private int orderIndex;
        private Date modifyDate;
        private String remark;
        private String remark1;
        private String remark2;
        private String remark3;
        private String remark4;
        private String remark5;
        private String remark6;

        public AnswerQuestionModel()
        {
        }

        public AnswerQuestionModel(String questionTitle, String questionKey,
                                   String questionContent, String questionLink, String questionLinkTitle,
                                   String type, int parentID, Date createDate)
        {
                this.questionTitle = questionTitle;
                this.questionKey = questionKey;
                this.questionContent = questionContent;
                this.questionLink = questionLink;
                this.questionLinkTitle = questionLinkTitle;
                this.type = type;
                this.parentID = parentID;
                this.createDate = createDate;
        }

        public AnswerQuestionModel(int aqID, String questionTitle,
                                   String questionKey, String questionContent, String questionLink,
                                   String questionLinkTitle, String type, int parentID, Date createDate,
                                   String isReply, String submmiterName)
        {
                this.aqID = aqID;
                this.questionTitle = questionTitle;
                this.questionKey = questionKey;
                this.questionContent = questionContent;
                this.questionLink = questionLink;
                this.questionLinkTitle = questionLinkTitle;
                this.type = type;
                this.parentID = parentID;
                this.createDate = createDate;
                this.isReply = isReply;
                this.submmiterName = submmiterName;
        }

        public AnswerQuestionForm getAnswerQuestionForm()
        {
                AnswerQuestionForm qForm = new AnswerQuestionForm();
                qForm.setAqID(this.aqID);
                qForm.setType(this.type);
                qForm.setSubmmiterID(this.submmiterID);
                qForm.setSubmmiterName(this.submmiterName);
                qForm.setReplyerID(this.replyerID);
                qForm.setReplyerName(this.replyerName);
                qForm.setRelationID(this.relationID);
                qForm.setRelationName(this.relationName);
                qForm.setStatus(this.Status);
                qForm.setAnswerContent(this.answerContent);
                qForm.setAnswerLink(this.answerLink);
                qForm.setAnswerLinkTitle(this.answerLinkTitle);
                qForm.setDepth(this.depth);
                qForm.setIsMail(this.isMail);
                qForm.setIsMessage(this.isMessage);
                qForm.setIsOpenGuest(this.isOpenGuest);
                qForm.setIsReply(this.isReply);
                qForm.setIsUserful(this.isUserful);
                qForm.setIsView(this.isView);
                qForm.setOrderIndex(this.orderIndex);
                qForm.setParentID(this.parentID);
                qForm.setQuestionContent(this.questionContent);
                qForm.setQuestionKey(this.questionKey);
                qForm.setQuestionLink(this.questionLink);
                qForm.setQuestionLinkTitle(this.questionLinkTitle);
                qForm.setQuestionTitle(this.questionTitle);
                qForm.setRemark(this.remark);
                qForm.setRemark1(this.remark1);
                qForm.setRemark2(this.remark2);
                qForm.setRemark3(this.remark3);
                qForm.setRemark4(this.remark4);
                qForm.setRemark5(this.remark5);
                qForm.setRemark6(this.remark6);

                return qForm;
        }

        public int getAqID()
        {
                return aqID;
        }

        public void setAqID(int aqID)
        {
                this.aqID = aqID;
        }

        public int getSubmmiterID()
        {
                return submmiterID;
        }

        public void setSubmmiterID(int submmiterID)
        {
                this.submmiterID = submmiterID;
        }

        public int getReplyerID()
        {
                return replyerID;
        }

        public void setReplyerID(int replyerID)
        {
                this.replyerID = replyerID;
        }

        public String getSubmmiterName()
        {
                return submmiterName;
        }

        public void setSubmmiterName(String submmiterName)
        {
                this.submmiterName = submmiterName;
        }

        public String getReplyerName()
        {
                return replyerName;
        }

        public void setReplyerName(String replyerName)
        {
                this.replyerName = replyerName;
        }

        public String getQuestionTitle()
        {
                return questionTitle;
        }

        public void setQuestionTitle(String questionTitle)
        {
                this.questionTitle = questionTitle;
        }

        public String getQuestionKey()
        {
                return questionKey;
        }

        public void setQuestionKey(String questionKey)
        {
                this.questionKey = questionKey;
        }

        public String getQuestionContent()
        {
                return questionContent;
        }

        public void setQuestionContent(String questionContent)
        {
                this.questionContent = questionContent;
        }

        public String AnswerLinkgetQuestionLink()
        {
                return questionLink;
        }

        public String getQuestionLink()
        {
                return questionLink;
        }

        public void setQuestionLink(String questionLink)
        {
                this.questionLink = questionLink;
        }

        public String getQuestionLinkTitle()
        {
                return questionLinkTitle;
        }

        public void setQuestionLinkTitle(String questionLinkTitle)
        {
                this.questionLinkTitle = questionLinkTitle;
        }

        public String getAnswerContent()
        {
                return answerContent;
        }

        public void setAnswerContent(String answerContent)
        {
                this.answerContent = answerContent;
        }

        public String getAnswerLinkTitle()
        {
                return answerLinkTitle;
        }

        public void setAnswerLinkTitle(String answerLinkTitle)
        {
                this.answerLinkTitle = answerLinkTitle;
        }

        public String getAnswerLink()
        {
                return answerLink;
        }

        public void setAnswerLink(String answerLink)
        {
                this.answerLink = answerLink;
        }

        public String getType()
        {
                return type;
        }

        public void setType(String type)
        {
                this.type = type;
        }

        public int getParentID()
        {
                return parentID;
        }

        public void setParentID(int parentID)
        {
                this.parentID = parentID;
        }

        public int getRelationID()
        {
                return relationID;
        }

        public void setRelationID(int relationID)
        {
                this.relationID = relationID;
        }

        public String getRelationName()
        {
                return relationName;
        }

        public void setRelationName(String relationName)
        {
                this.relationName = relationName;
        }

        public String getIsReply()
        {
                return isReply;
        }

        public void setIsReply(String isReply)
        {
                this.isReply = isReply;
        }

        public String getIsMail()
        {
                return isMail;
        }

        public void setIsMail(String isMail)
        {
                this.isMail = isMail;
        }

        public String getIsUserful()
        {
                return isUserful;
        }

        public void setIsUserful(String isUserful)
        {
                this.isUserful = isUserful;
        }

        public String getIsOpenGuest()
        {
                return isOpenGuest;
        }

        public void setIsOpenGuest(String isOpenGuest)
        {
                this.isOpenGuest = isOpenGuest;
        }

        public String getIsView()
        {
                return isView;
        }

        public void setIsView(String isView)
        {
                this.isView = isView;
        }

        public int getDepth()
        {
                return depth;
        }

        public void setDepth(int depth)
        {
                this.depth = depth;
        }

        public int getOrderIndex()
        {
                return orderIndex;
        }

        public void setOrderIndex(int orderIndex)
        {
                this.orderIndex = orderIndex;
        }

        public Date getDisplayBeginDate()
        {
                return displayBeginDate;
        }

        public void setDisplayBeginDate(Date displayBeginDate)
        {
                this.displayBeginDate = displayBeginDate;
        }

        public String getStatus()
        {
                return Status;
        }

        public void setStatus(String status)
        {
                Status = status;
        }

        public Date getDisplayEndDate()
        {
                return displayEndDate;
        }

        public void setDisplayEndDate(Date displayEndDate)
        {
                this.displayEndDate = displayEndDate;
        }

        public Date getCreateDate()
        {
                return createDate;
        }

        public void setCreateDate(Date createDate)
        {
                this.createDate = createDate;
        }

        public String getIsMessage()
        {
                return isMessage;
        }

        public void setIsMessage(String isMessage)
        {
                this.isMessage = isMessage;
        }

        public Date getModifyDate()
        {
                return modifyDate;
        }

        public void setModifyDate(Date modifyDate)
        {
                this.modifyDate = modifyDate;
        }

        public String getRemark()
        {
                return remark;
        }

        public void setRemark(String remark)
        {
                this.remark = remark;
        }

        public String getRemark1()
        {
                return remark1;
        }

        public void setRemark1(String remark1)
        {
                this.remark1 = remark1;
        }

        public String getRemark2()
        {
                return remark2;
        }

        public void setRemark2(String remark2)
        {
                this.remark2 = remark2;
        }

        public String getRemark3()
        {
                return remark3;
        }

        public void setRemark3(String remark3)
        {
                this.remark3 = remark3;
        }

        public String getRemark4()
        {
                return remark4;
        }

        public void setRemark4(String remark4)
        {
                this.remark4 = remark4;
        }

        public String getRemark5()
        {
                return remark5;
        }

        public void setRemark5(String remark5)
        {
                this.remark5 = remark5;
        }

        public String getRemark6()
        {
                return remark6;
        }

        public void setRemark6(String remark6)
        {
                this.remark6 = remark6;
        }
}
