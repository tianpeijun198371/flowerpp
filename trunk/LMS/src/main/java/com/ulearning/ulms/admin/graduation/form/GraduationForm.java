/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-9-7
 * Time: 17:46:26
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.admin.graduation.form;

import com.ulearning.ulms.admin.graduation.model.GraduationModel;
import com.ulearning.ulms.admin.graduation.model.GraduationModelPK;
import com.ulearning.ulms.core.util.DateTimeUtil;

import java.sql.Date;


public class GraduationForm
{
        private int relationID = 0;
        private int type = 0;
        private int userID = 0;
        private String loginName = "";
        private String stuName = "";
        private int orgID = 0;
        private String orgName = "";
        private double compulsoryScore = 0;
        private double electiveScore = 0;
        private double score = 0;
        private String remark = "";
        private int status = 0;
        private String certNo = "";
        private String startDate = "";
        private String graduateDate = DateTimeUtil.FormatDateToWeb1(new Date(
                System.currentTimeMillis()));

        public GraduationForm()
        {
        }

        public GraduationForm(GraduationModel gm)
        {
                this.relationID = ((GraduationModelPK) gm.getComp_id()).getRelationID();
                this.type = ((GraduationModelPK) gm.getComp_id()).getType();
                this.userID = ((GraduationModelPK) gm.getComp_id()).getUserID();
                this.compulsoryScore = gm.getCompulsoryScore();
                this.electiveScore = gm.getElectiveScore();
                this.remark = gm.getRemark();
                this.status = Integer.parseInt(gm.getStatus());
                this.certNo = gm.getCertNo();
                this.startDate = gm.getStartDate();
                this.graduateDate = gm.getGraduateDate();
                this.setScore(this.compulsoryScore + this.electiveScore);
        }

        public int getRelationID()
        {
                return relationID;
        }

        public void setRelationID(int relationID)
        {
                this.relationID = relationID;
        }

        public int getType()
        {
                return type;
        }

        public void setType(int type)
        {
                this.type = type;
        }

        public int getUserID()
        {
                return userID;
        }

        public void setUserID(int userID)
        {
                this.userID = userID;
        }

        public double getCompulsoryScore()
        {
                return compulsoryScore;
        }

        public void setCompulsoryScore(double compulsoryScore)
        {
                this.compulsoryScore = compulsoryScore;
        }

        public double getElectiveScore()
        {
                return electiveScore;
        }

        public void setElectiveScore(double electiveScore)
        {
                this.electiveScore = electiveScore;
        }

        public String getRemark()
        {
                return remark;
        }

        public void setRemark(String remark)
        {
                this.remark = remark;
        }

        public int getStatus()
        {
                return status;
        }

        public void setStatus(int status)
        {
                this.status = status;
        }

        public String getCertNo()
        {
                return certNo;
        }

        public void setCertNo(String certNo)
        {
                this.certNo = certNo;
        }

        public String getStartDate()
        {
                return startDate;
        }

        public void setStartDate(String startDate)
        {
                this.startDate = startDate;
        }

        public String getGraduateDate()
        {
                return graduateDate;
        }

        public void setGraduateDate(String graduateDate)
        {
                this.graduateDate = graduateDate;
        }

        public String getStuName()
        {
                return stuName;
        }

        public void setStuName(String stuName)
        {
                this.stuName = stuName;
        }

        public String getOrgName()
        {
                return orgName;
        }

        public void setOrgName(String orgName)
        {
                this.orgName = orgName;
        }

        public double getScore()
        {
                return score;
        }

        public void setScore(double score)
        {
                this.score = score;
        }

        public String getLoginName()
        {
                return loginName;
        }

        public void setLoginName(String loginName)
        {
                this.loginName = loginName;
        }

        public int getOrgID()
        {
                return orgID;
        }

        public void setOrgID(int orgID)
        {
                this.orgID = orgID;
        }

        public GraduationModel getModel()
        {
                GraduationModel gm = new GraduationModel();
                GraduationModelPK gmPK = new GraduationModelPK();
                gmPK.setRelationID(this.relationID);
                gmPK.setType(this.type);
                gmPK.setUserID(this.userID);
                gm.setComp_id(gmPK);
                gm.setCompulsoryScore(this.compulsoryScore);
                gm.setElectiveScore(this.electiveScore);
                gm.setRemark(this.remark);
                gm.setStatus(String.valueOf(this.status));
                gm.setStartDate(this.startDate);
                gm.setGraduateDate(this.graduateDate);
                gm.setCertNo(this.certNo);

                return gm;
        }
}
