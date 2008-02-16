/**
 * Created by IntelliJ IDEA.
 * Plan: dengj
 * Date: Apr 7, 2004
 * Time: 4:51:49 PM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.admin.plan.form;

import com.ulearning.ulms.admin.plan.model.PlanModel;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.tools.upload.model.UploadForm;

import org.apache.struts.action.ActionForm;

import java.util.Date;


public class PlanForm extends UploadForm
{
        private int planID = 0;
        private String title = null;
        private int orgID = 0;
        private int submitOrg = 0;
        private int isContent = 1;
        private String link = null;
        private int isHyperLink = 0;
        private int parentID = 0;
        private Date establishTime = new Date();
        private String startDateTimeValue = null;
        private String description = null;
        private String endDateTimeValue = null;
        private String beginTimehour = null;
        private String endTimehour = null;
        private String beginTimemin = null;
        private String endTimemin = null;
        private String teacher = null;
        private String radiobutton = null;
        private int classdate = 0;
        private String object = null;
        private String shape = null;
        private String thefile = null;
        private int number = 0;
        private String satisfied = null;
        private String result = null;
        private String reason = null;
        private String cwsatisfied = null;
        private Date nowTime = new Date();

        public PlanForm()
        {
        }

        public PlanForm(PlanModel pm)
        {
                if (pm != null)
                {
                        this.planID = pm.getPlanid();
                        this.title = StringUtil.nullToStr(pm.getTitle());
                        this.orgID = pm.getOrgid();
                        this.submitOrg = pm.getSubmitorg();
                        this.isContent = Integer.parseInt(pm.getIscontent());
                        this.link = StringUtil.nullToStr(pm.getLink());
                        this.isHyperLink = pm.getPlanid();
                        this.parentID = pm.getParentid();
                        this.establishTime = new Date(pm.getEstablishtime().getTime());
                        this.description = StringUtil.nullToStr(pm.getDescription());
                        this.beginTimehour = StringUtil.nullToStr(pm.getBeginTimehour());
                        this.endTimehour = StringUtil.nullToStr(pm.getEndTimehour());
                        this.beginTimemin = StringUtil.nullToStr(pm.getBeginTimemin());
                        this.endTimemin = StringUtil.nullToStr(pm.getEndTimemin());
                        this.teacher = StringUtil.nullToStr(pm.getTeacher());
                        this.radiobutton = StringUtil.nullToStr(pm.getRadiobutton());
                        this.classdate = pm.getClassdate();
                        this.object = StringUtil.nullToStr(pm.getObject());
                        this.shape = StringUtil.nullToStr(pm.getShape());
                        this.number = pm.getNumber();
                        this.satisfied = StringUtil.nullToStr(pm.getSatisfied());
                        this.result = StringUtil.nullToStr(pm.getResult());
                        this.reason = StringUtil.nullToStr(pm.getReason());
                        this.cwsatisfied = StringUtil.nullToStr(pm.getCwsatisfied());
                        this.nowTime = new Date(pm.getNowTime().getTime());
                }
        }

        public int getPlanID()
        {
                return planID;
        }

        public void setPlanID(int planID)
        {
                this.planID = planID;
        }

        public String getTitle()
        {
                return title;
        }

        public void setTitle(String title)
        {
                this.title = title;
        }

        public int getOrgID()
        {
                return orgID;
        }

        public void setOrgID(int orgID)
        {
                this.orgID = orgID;
        }

        public int getSubmitOrg()
        {
                return submitOrg;
        }

        public void setSubmitOrg(int submitOrg)
        {
                this.submitOrg = submitOrg;
        }

        public int getIsContent()
        {
                return isContent;
        }

        public void setIsContent(int isContent)
        {
                this.isContent = isContent;
        }

        public String getLink()
        {
                return link;
        }

        public void setLink(String link)
        {
                this.link = link;
        }

        public int getIsHyperLink()
        {
                return isHyperLink;
        }

        public void setIsHyperLink(int isHyperLink)
        {
                this.isHyperLink = isHyperLink;
        }

        public int getParentID()
        {
                return parentID;
        }

        public void setParentID(int parentID)
        {
                this.parentID = parentID;
        }

        public Date getEstablishTime()
        {
                return establishTime;
        }

        public void setEstablishTime(Date establishTime)
        {
                this.establishTime = establishTime;
        }

        public String getDescription()
        {
                return description;
        }

        public void setDescription(String description)
        {
                this.description = description;
        }

        public String getBeginTimehour()
        {
                return this.beginTimehour;
        }

        public void setBeginTimehour(String beginTimehour)
        {
                this.beginTimehour = beginTimehour;
        }

        public String getEndTimehour()
        {
                return this.endTimehour;
        }

        public void setEndTimehour(String endTimehour)
        {
                this.endTimehour = endTimehour;
        }

        public String getBeginTimemin()
        {
                return this.beginTimemin;
        }

        public void setBeginTimemin(String beginTimemin)
        {
                this.beginTimemin = beginTimemin;
        }

        public String getEndTimemin()
        {
                return this.endTimemin;
        }

        public void setEndTimemin(String endTimemin)
        {
                this.endTimemin = endTimemin;
        }

        public String getTeacher()
        {
                return this.teacher;
        }

        public void setTeacher(String teacher)
        {
                this.teacher = teacher;
        }

        public String getRadiobutton()
        {
                return this.radiobutton;
        }

        public void setRadiobutton(String radiobutton)
        {
                this.radiobutton = radiobutton;
        }

        public int getClassdate()
        {
                return this.classdate;
        }

        public void setClassdate(int classdate)
        {
                this.classdate = classdate;
        }

        public String getObject()
        {
                return this.object;
        }

        public void setObject(String object)
        {
                this.object = object;
        }

        public String getShape()
        {
                return this.shape;
        }

        public void setShape(String shape)
        {
                this.shape = shape;
        }

        public String getThefile()
        {
                return this.thefile;
        }

        public void setThefile(String thefile)
        {
                this.thefile = thefile;
        }

        public int getContent()
        {
                return isContent;
        }

        public void setContent(int content)
        {
                isContent = content;
        }

        public int getHyperLink()
        {
                return isHyperLink;
        }

        public void setHyperLink(int hyperLink)
        {
                isHyperLink = hyperLink;
        }

        public String getStartDateTimeValue()
        {
                return startDateTimeValue;
        }

        public void setStartDateTimeValue(String startDateTimeValue)
        {
                this.startDateTimeValue = startDateTimeValue;
        }

        public String getEndDateTimeValue()
        {
                return endDateTimeValue;
        }

        public void setEndDateTimeValue(String endDateTimeValue)
        {
                this.endDateTimeValue = endDateTimeValue;
        }

        public int getNumber()
        {
                return this.number;
        }

        public void setNumber(int number)
        {
                this.number = number;
        }

        public String getSatisfied()
        {
                return this.satisfied;
        }

        public void setSatisfied(String satisfied)
        {
                this.satisfied = satisfied;
        }

        public String getResult()
        {
                return this.result;
        }

        public void setResult(String result)
        {
                this.result = result;
        }

        public String getReason()
        {
                return this.reason;
        }

        public void setReason(String reason)
        {
                this.reason = reason;
        }

        public String getCwsatisfied()
        {
                return this.cwsatisfied;
        }

        public void setCwsatisfied(String cwsatisfied)
        {
                this.cwsatisfied = cwsatisfied;
        }

        public Date getNowTime()
        {
                return this.nowTime;
        }

        public void setNowTime(Date nowTime)
        {
                this.nowTime = nowTime;
        }

        public PlanModel getPlanModel()
        {
                PlanModel pm = new PlanModel();
                pm.setPlanid(this.planID);
                pm.setTitle(this.title);
                pm.setOrgid(this.orgID);
                pm.setSubmitorg(this.submitOrg);
                pm.setIscontent(new Integer(this.isContent).toString());
                pm.setLink(this.link);
                pm.setIshyperlink(new Integer(this.isHyperLink).toString());
                pm.setParentid(this.parentID);
                pm.setEstablishtime(this.establishTime);
                pm.setDescription(this.description);
                pm.setBeginTimehour(this.beginTimehour);
                pm.setEndTimehour(this.endTimehour);
                pm.setBeginTimemin(this.beginTimemin);
                pm.setEndTimemin(this.endTimemin);
                pm.setTeacher(this.teacher);
                pm.setRadiobutton(this.radiobutton);
                pm.setClassdate(this.classdate);
                pm.setObject(this.object);
                pm.setShape(this.shape);
                pm.setNumber(this.number);
                pm.setSatisfied(this.satisfied);
                pm.setResult(this.result);
                pm.setReason(this.reason);
                pm.setCwsatisfied(this.cwsatisfied);
                pm.setNowTime(this.nowTime);

                return pm;
        }
}
