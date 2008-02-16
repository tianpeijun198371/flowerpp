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

        //���
        private int bodyIdentity;

        //�ɽ��ڿ�Ŀ�������Ŀ,����
        private String availableTeachSubject;

        /**
         * ������Ŀ
         */
        private String otherSubjects;

        /**
         * ����֤��
         */
        private String certification;
        private String keyword;

        /**
         * ������������չʾ
         */
        private String description;

        /**
         * ���ڿ�����
         */
        private String availableTeachArea;

        /**
         * ���ڿ�������ϸ����
         */
        private String availableTeachAreaDetail;

        /**
         * �ҽ̼��� ѧԱ���� ��ѧ�ɹ�
         */
        private String resume;

        /**
         * �ɸ�����ʽ
         * <p/>
         * 0 ��������
         * 1 ѧ������
         * 2 ���ϸ���
         */
        private int tutorMode;

        /**
         * н��Ҫ��
         */
        private String remunerationRequired;

        /**
         * ��·����
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
