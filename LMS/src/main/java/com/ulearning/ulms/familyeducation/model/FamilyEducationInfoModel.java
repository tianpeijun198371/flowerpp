package com.ulearning.ulms.familyeducation.model;

import java.io.Serializable;

import java.util.Date;


public class FamilyEducationInfoModel implements Serializable
{
        private int id;
        int aspID;
        int createID;
        String createName;
        String province;
        String area;
        String district;

        /**
         * ��ϵ��
         */
        private String contactName;
        private String phone;
        private String mobile;

        /**
         * �Ա�
         */
        private int gender;
        private String address;

        /**
         * �꼶
         */
        private int gradeID;

        /**
         * �꼶
         */
        private String gradeCode;

        /**
         * ���ѧ��
         */
        private int subjectID;

        /**
         * �꼶
         */
        private String gradeName;

        /**
         * ���ڿ�ʱ��
         */
        private String availableAttendPeriod;

        /**
         * ���ѧ��
         */
        private String subjectName;

        /**
         * ����
         */
        private String description;

        /**
         * ��Ա�Ա�Ҫ��
         * 2Ҫ�н�Ա
         * 1ҪŮ��Ա
         * 0����ν
         */
        private int teacherGenderRequired;

        /**
         * �Խ�Ա����Ҫ��
         */
        private String otherRequireInfo;

        /**
         * ����
         */
        private String remuneration;
        private int status;
        private int isRecommend;
        private int isInTop;
        private Date createDate;
        private Date lastUpdateDate;

        public String getContactName()
        {
                return this.contactName;
        }

        public void setContactName(String contactName)
        {
                this.contactName = contactName;
        }

        public String getPhone()
        {
                return this.phone;
        }

        public void setPhone(String phone)
        {
                this.phone = phone;
        }

        public String getAddress()
        {
                return this.address;
        }

        public String getMobile()
        {
                return mobile;
        }

        public void setMobile(String mobile)
        {
                this.mobile = mobile;
        }

        public void setAddress(String address)
        {
                this.address = address;
        }

        public int getGradeID()
        {
                return this.gradeID;
        }

        public void setGradeID(int gradeID)
        {
                this.gradeID = gradeID;
        }

        public String getGradeCode()
        {
                return this.gradeCode;
        }

        public void setGradeCode(String gradeCode)
        {
                this.gradeCode = gradeCode;
        }

        public int getSubjectID()
        {
                return this.subjectID;
        }

        public void setSubjectID(int subjectID)
        {
                this.subjectID = subjectID;
        }

        public String getGradeName()
        {
                return this.gradeName;
        }

        public void setGradeName(String gradeName)
        {
                this.gradeName = gradeName;
        }

        public String getAvailableAttendPeriod()
        {
                return this.availableAttendPeriod;
        }

        public void setAvailableAttendPeriod(String availableAttendPeriod)
        {
                this.availableAttendPeriod = availableAttendPeriod;
        }

        public String getSubjectName()
        {
                return this.subjectName;
        }

        public void setSubjectName(String subjectName)
        {
                this.subjectName = subjectName;
        }

        public String getDescription()
        {
                return this.description;
        }

        public void setDescription(String description)
        {
                this.description = description;
        }

        public String getRemuneration()
        {
                return this.remuneration;
        }

        public void setRemuneration(String remuneration)
        {
                this.remuneration = remuneration;
        }

        public int getId()
        {
                return id;
        }

        public void setId(int id)
        {
                this.id = id;
        }

        public int getGender()
        {
                return gender;
        }

        public void setGender(int gender)
        {
                this.gender = gender;
        }

        public int getTeacherGenderRequired()
        {
                return teacherGenderRequired;
        }

        public void setTeacherGenderRequired(int teacherGenderRequired)
        {
                this.teacherGenderRequired = teacherGenderRequired;
        }

        public String getOtherRequireInfo()
        {
                return otherRequireInfo;
        }

        public void setOtherRequireInfo(String otherRequireInfo)
        {
                this.otherRequireInfo = otherRequireInfo;
        }

        public int getAspID()
        {
                return aspID;
        }

        public void setAspID(int aspID)
        {
                this.aspID = aspID;
        }

        public int getCreateID()
        {
                return createID;
        }

        public void setCreateID(int createID)
        {
                this.createID = createID;
        }

        public String getCreateName()
        {
                return createName;
        }

        public void setCreateName(String createName)
        {
                this.createName = createName;
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
