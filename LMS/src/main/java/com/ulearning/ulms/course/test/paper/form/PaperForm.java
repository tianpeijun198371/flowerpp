/**
 * PaperForm.java.
 * User: huangsb  Date: 2004-6-15
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.course.test.paper.form;

import com.ulearning.ulms.core.util.DateTimeUtil;
import com.ulearning.ulms.course.test.paper.model.PaperModel;

import org.apache.struts.action.ActionForm;

import java.util.Date;


public class PaperForm extends ActionForm
{
        private int paperID = 0;
        private int courseID = 0;
        private String title = null;
        private String Description = null;
        private String Instruction = null;
        private int type = 0;
        private int isUploadPaper = 0;
        private int isAvailable = 0;
        private int isAnnounce = 0;
        private int isFeedbackGrade = 0;
        private int isFeedbackAnswer = 0;
        private int isFeedbackReply = 0;
        private Date startTime = null;
        private Date endTime = null;
        private Date createTime = null; //new Date();
        private String beginTime;
        private String lastTime;
        private String desc1 = null;
        private String desc2 = null;
        private String desc3 = null;
        private String desc4 = null;
        private String desc5 = null;
        private String desc6 = null;
        private String desc7 = null;

        public PaperForm()
        {
        }

        public PaperForm(PaperModel pm)
        {
                if (pm != null)
                {
                        this.paperID = pm.getPaperid();
                        this.courseID = pm.getCourseid();
                        this.title = pm.getTitle();
                        this.Description = pm.getDescription();
                        this.Instruction = pm.getInstruction();
                        this.type = Integer.parseInt(pm.getType());

                        this.isUploadPaper = Integer.parseInt(pm.getIsuploadpaper());
                        this.isAvailable = Integer.parseInt(pm.getIsavailable());
                        this.isAnnounce = Integer.parseInt(pm.getIsannounce());
                        this.isFeedbackGrade = Integer.parseInt(pm.getIsfeedbackgrade());
                        this.isFeedbackAnswer = Integer.parseInt(pm.getIsfeedbackanswer());
                        this.isFeedbackReply = Integer.parseInt(pm.getIsfeedbackreply());
                        this.startTime = pm.getStarttime();
                        this.endTime = pm.getEndtime();
                        this.createTime = pm.getCreatetime();
                        this.desc1 = pm.getDesc1();
                        this.desc2 = pm.getDesc2();
                        this.desc3 = pm.getDesc3();
                        this.desc4 = pm.getDesc4();
                        this.desc5 = pm.getDesc5();
                        this.desc6 = pm.getDesc6();
                        this.desc7 = pm.getDesc7();
                }
        }

        public int hashCode()
        {
                return this.getStartTime().hashCode() + this.getEndTime().hashCode();
        }

        public boolean equals(Object o)
        {
                if (!(o instanceof PaperForm))
                {
                        return false;
                }

                PaperForm a = (PaperForm) o;

                return DateTimeUtil.format((this.getStartTime()), "yyyy-MM-dd HH:mm")
                        .equals(DateTimeUtil.format((a.getStartTime()),
                                "yyyy-MM-dd HH:mm")) &&
                        DateTimeUtil.format(this.getEndTime(), "yyyy-MM-dd HH:mm")
                                .equals(DateTimeUtil.format(a.getEndTime(),
                                        "yyyy-MM-dd HH:mm"));
        }

        public PaperModel getPaperModel()
        {
                PaperModel pm = new PaperModel();
                pm.setPaperid(this.paperID);
                pm.setCourseid(this.courseID);
                pm.setTitle(this.title);
                pm.setDescription(this.Description);
                pm.setInstruction(this.Instruction);
                pm.setType(new Integer(this.type).toString());
                pm.setIsuploadpaper(new Integer(this.isUploadPaper).toString());
                pm.setIsavailable(new Integer(this.isAvailable).toString());
                pm.setIsannounce(new Integer(this.isAnnounce).toString());
                pm.setIsfeedbackgrade(new Integer(this.isFeedbackGrade).toString());
                pm.setIsfeedbackanswer(new Integer(this.isFeedbackAnswer).toString());
                pm.setIsfeedbackreply(new Integer(this.isFeedbackReply).toString());
                pm.setStarttime(this.startTime);
                pm.setEndtime(this.endTime);
                pm.setCreatetime(this.createTime);
                pm.setDesc1(this.desc1);
                pm.setDesc2(this.desc2);
                pm.setDesc3(this.desc3);
                pm.setDesc4(this.desc4);
                pm.setDesc5(this.desc5);
                pm.setDesc6(this.desc6);
                pm.setDesc7(this.desc7);

                return pm;
        }

        public int getPaperID()
        {
                return paperID;
        }

        public void setPaperID(int paperID)
        {
                this.paperID = paperID;
        }

        public int getCourseID()
        {
                return courseID;
        }

        public void setCourseID(int courseID)
        {
                this.courseID = courseID;
        }

        public String getTitle()
        {
                return title;
        }

        public void setTitle(String title)
        {
                this.title = title;
        }

        public String getDescription()
        {
                return Description;
        }

        public void setDescription(String description)
        {
                Description = description;
        }

        public String getInstruction()
        {
                return Instruction;
        }

        public void setInstruction(String instruction)
        {
                Instruction = instruction;
        }

        public int getType()
        {
                return type;
        }

        public void setType(int type)
        {
                this.type = type;
        }

        public int getIsUploadPaper()
        {
                return isUploadPaper;
        }

        public void setIsUploadPaper(int uploadPaper)
        {
                isUploadPaper = uploadPaper;
        }

        public int getIsAvailable()
        {
                return isAvailable;
        }

        public void setIsAvailable(int available)
        {
                isAvailable = available;
        }

        public int getIsAnnounce()
        {
                return isAnnounce;
        }

        public void setIsAnnounce(int announce)
        {
                isAnnounce = announce;
        }

        public int getIsFeedbackGrade()
        {
                return isFeedbackGrade;
        }

        public void setIsFeedbackGrade(int feedbackGrade)
        {
                isFeedbackGrade = feedbackGrade;
        }

        public int getIsFeedbackAnswer()
        {
                return isFeedbackAnswer;
        }

        public void setIsFeedbackAnswer(int feedbackAnswer)
        {
                isFeedbackAnswer = feedbackAnswer;
        }

        public int getIsFeedbackReply()
        {
                return isFeedbackReply;
        }

        public void setIsFeedbackReply(int feedbackReply)
        {
                isFeedbackReply = feedbackReply;
        }

        public Date getStartTime()
        {
                return startTime;
        }

        public void setStartTime(Date startTime)
        {
                this.startTime = startTime;
        }

        public Date getEndTime()
        {
                return endTime;
        }

        public void setEndTime(Date endTime)
        {
                this.endTime = endTime;
        }

        public Date getCreateTime()
        {
                return createTime;
        }

        public void setCreateTime(Date createTime)
        {
                this.createTime = createTime;
        }

        public String getBeginTime()
        {
                return beginTime;
        }

        public void setBeginTime(String beginTime)
        {
                this.beginTime = beginTime;
        }

        public String getLastTime()
        {
                return lastTime;
        }

        public void setLastTime(String lastTime)
        {
                this.lastTime = lastTime;
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
