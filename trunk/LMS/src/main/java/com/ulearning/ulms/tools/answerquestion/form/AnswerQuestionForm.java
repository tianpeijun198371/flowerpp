package com.ulearning.ulms.tools.answerquestion.form;

import com.ulearning.ulms.tools.answerquestion.model.AnswerQuestionModel;
import com.ulearning.ulms.tools.upload.model.UploadForm;

import java.util.Date;


/**
 * @author Hibernate CodeGenerator
 */
public class AnswerQuestionForm extends UploadForm
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
        private int orderIndex;
        private String Status;
        private Date displayBeginDate;
        private Date displayEndDate;
        private Date createDate;
        private Date modifyDate;
        private String remark;
        private String remark1;
        private String remark2;
        private String remark3;
        private String remark4;
        private String remark5;
        private String remark6;

        public AnswerQuestionModel setAnswerQuestionModel()
        {
                AnswerQuestionModel qModel = new AnswerQuestionModel();
                qModel.setType(this.type);
                qModel.setSubmmiterID(this.submmiterID);
                qModel.setSubmmiterName(this.submmiterName);
                qModel.setReplyerID(this.replyerID);
                qModel.setReplyerName(this.replyerName);
                qModel.setRelationID(this.relationID);
                qModel.setRelationName(this.relationName);
                qModel.setStatus(this.Status);
                qModel.setAnswerContent(this.answerContent);
                qModel.setAnswerLink(this.answerLink);
                qModel.setAnswerLinkTitle(this.answerLinkTitle);
                qModel.setDepth(this.depth);
                qModel.setIsMail(this.isMail);
                qModel.setIsMessage(this.isMessage);
                qModel.setIsOpenGuest(this.isOpenGuest);
                qModel.setIsReply(this.isReply);
                qModel.setIsUserful(this.isUserful);
                qModel.setIsView(this.isView);
                qModel.setOrderIndex(this.orderIndex);
                qModel.setParentID(this.parentID);
                qModel.setQuestionContent(this.questionContent);
                qModel.setQuestionKey(this.questionKey);
                qModel.setQuestionLink(this.questionLink);
                qModel.setQuestionLinkTitle(this.questionLinkTitle);
                qModel.setQuestionTitle(this.questionTitle);
                qModel.setRemark(this.remark);
                qModel.setRemark1(this.remark1);
                qModel.setRemark2(this.remark2);
                qModel.setRemark3(this.remark3);
                qModel.setRemark4(this.remark4);
                qModel.setRemark5(this.remark5);
                qModel.setRemark6(this.remark6);

                return qModel;
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

        public String getIsMessage()
        {
                return isMessage;
        }

        public void setIsMessage(String isMessage)
        {
                this.isMessage = isMessage;
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

        public String getStatus()
        {
                return Status;
        }

        public void setStatus(String status)
        {
                Status = status;
        }

        public Date getDisplayBeginDate()
        {
                return displayBeginDate;
        }

        public void setDisplayBeginDate(Date displayBeginDate)
        {
                this.displayBeginDate = displayBeginDate;
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
