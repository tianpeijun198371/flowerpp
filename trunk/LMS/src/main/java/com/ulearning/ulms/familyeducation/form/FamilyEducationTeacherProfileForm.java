/**
 * Copyright (c)2006 Beijing WenhuaOnline Sci-tech DevelopmentCo.,Ltd.
 * All rights reserved.
 *
 * User: Fengch
 * Date: 2007-12-7 15:42:34
 */
package com.ulearning.ulms.familyeducation.form;

import org.apache.struts.action.ActionForm;

import java.util.Date;


public class FamilyEducationTeacherProfileForm extends ActionForm
{
        private int teacherID;
        private int aspID;
        private String teacherName;
        private String teacherCode;
        private int gender;
        private String province;
        private String area;
        private String district;

        //身份
        private int bodyIdentity;

        //可教授科目。多个科目,隔开
        private String availableTeachSubject;

        /**
         * 其它科目
         */
        private String otherSubjects;

        /**
         * 所获证书
         */
        private String certification;
        private String keyword;

        /**
         * 自我描述自我展示
         */
        private String description;

        /**
         * 可授课区域
         */
        private String availableTeachArea;

        /**
         * 可授课区域详细描术
         */
        private String availableTeachAreaDetail;

        /**
         * 家教简历 学员评价 教学成功
         */
        private String resume;

        /**
         * 可辅导方式
         * <p/>
         * 0 本人上门
         * 1 学生上门
         * 2 网上辅导
         */
        private int tutorMode;

        /**
         * 薪资要求
         */
        private String remunerationRequired;

        /**
         * 来路调查
         */
        private int source;
        private int status;
        private int isRecommend;
        private int isInTop;
        private int rank;
        private Date createDate;
        private Date lastUpdateDate;

        public int getTeacherID()
        {
                return teacherID;
        }

        public void setTeacherID(int teacherID)
        {
                this.teacherID = teacherID;
        }

        public String getKeyword()
        {
                return keyword;
        }

        public void setKeyword(String keyword)
        {
                this.keyword = keyword;
        }

        public String getTeacherCode()
        {
                return teacherCode;
        }

        public int getGender()
        {
                return gender;
        }

        public void setGender(int gender)
        {
                this.gender = gender;
        }

        public void setTeacherCode(String teacherCode)
        {
                this.teacherCode = teacherCode;
        }

        public int getBodyIdentity()
        {
                return bodyIdentity;
        }

        public void setBodyIdentity(int bodyIdentity)
        {
                this.bodyIdentity = bodyIdentity;
        }

        public String getOtherSubjects()
        {
                return otherSubjects;
        }

        public String getAvailableTeachSubject()
        {
                return availableTeachSubject;
        }

        public void setAvailableTeachSubject(String availableTeachSubject)
        {
                this.availableTeachSubject = availableTeachSubject;
        }

        public void setOtherSubjects(String otherSubjects)
        {
                this.otherSubjects = otherSubjects;
        }

        public String getCertification()
        {
                return certification;
        }

        public void setCertification(String certification)
        {
                this.certification = certification;
        }

        public String getDescription()
        {
                return description;
        }

        public void setDescription(String description)
        {
                this.description = description;
        }

        public String getAvailableTeachArea()
        {
                return availableTeachArea;
        }

        public void setAvailableTeachArea(String availableTeachArea)
        {
                this.availableTeachArea = availableTeachArea;
        }

        public String getAvailableTeachAreaDetail()
        {
                return availableTeachAreaDetail;
        }

        public void setAvailableTeachAreaDetail(String availableTeachAreaDetail)
        {
                this.availableTeachAreaDetail = availableTeachAreaDetail;
        }

        public String getResume()
        {
                return resume;
        }

        public void setResume(String resume)
        {
                this.resume = resume;
        }

        public int getTutorMode()
        {
                return tutorMode;
        }

        public void setTutorMode(int tutorMode)
        {
                this.tutorMode = tutorMode;
        }

        public String getRemunerationRequired()
        {
                return remunerationRequired;
        }

        public void setRemunerationRequired(String remunerationRequired)
        {
                this.remunerationRequired = remunerationRequired;
        }

        public int getSource()
        {
                return source;
        }

        public void setSource(int source)
        {
                this.source = source;
        }

        public int getAspID()
        {
                return aspID;
        }

        public void setAspID(int aspID)
        {
                this.aspID = aspID;
        }

        public String getTeacherName()
        {
                return teacherName;
        }

        public void setTeacherName(String teacherName)
        {
                this.teacherName = teacherName;
        }

        public String getProvince()
        {
                return province;
        }

        public void setProvince(String province)
        {
                this.province = province;
        }

        public String getArea()
        {
                return area;
        }

        public void setArea(String area)
        {
                this.area = area;
        }

        public String getDistrict()
        {
                return district;
        }

        public void setDistrict(String district)
        {
                this.district = district;
        }

        public int getStatus()
        {
                return status;
        }

        public void setStatus(int status)
        {
                this.status = status;
        }

        public int getIsRecommend()
        {
                return isRecommend;
        }

        public void setIsRecommend(int recommend)
        {
                isRecommend = recommend;
        }

        public int getIsInTop()
        {
                return isInTop;
        }

        public void setIsInTop(int inTop)
        {
                isInTop = inTop;
        }

        public int getRank()
        {
                return rank;
        }

        public void setRank(int rank)
        {
                this.rank = rank;
        }

        public Date getCreateDate()
        {
                return createDate;
        }

        public void setCreateDate(Date createDate)
        {
                this.createDate = createDate;
        }

        public Date getLastUpdateDate()
        {
                return lastUpdateDate;
        }

        public void setLastUpdateDate(Date lastUpdateDate)
        {
                this.lastUpdateDate = lastUpdateDate;
        }
}
