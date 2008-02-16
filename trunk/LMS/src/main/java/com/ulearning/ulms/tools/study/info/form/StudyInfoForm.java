/**
 * Copyright (c)2006 Beijing WenhuaOnline Sci-tech DevelopmentCo.,Ltd. 
 * All rights reserved. 
 *
 * User: zhangly
 * Date: 2007-10-23 15:24:02 
 */

package com.ulearning.ulms.tools.study.info.form;

import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.tools.study.info.model.StudyInfoModel;
import com.ulearning.ulms.tools.upload.model.UploadForm;
import org.apache.struts.action.ActionForm;

import java.util.Date;

public class StudyInfoForm extends UploadForm
{
        private int studyInfoID;
        private int orgID;
        private int courseID;
        private int userID;
        private int type;
        private int examType;
        private int testType;          
        private String year;
        private int grade;
        private String subject;
        private String name;
        private String school;
        private String book;
        private String mail;
        private String card;
        private String sex;
        private String phone;
        private String cellphone;
        private String address;
        private String postalcode;
        private float tingLi;
        private float tingLiScore;
        private String tingLiPingYu;
        private float yuYin;
        private float yuYinScore;
        private String yuYinPingYu;
        private float ciHui;
        private float ciHuiScore;
        private String ciHuiPingYu;
        private float huiHua;
        private float huiHuaScore;
        private String huiHuaPingYu;
        private float yuFa;
        private float yuFaScore;
        private String yuFaPingYu;
        private float yueDu;
        private float yueDuScore;
        private String yueDuPingYu;
        private float xieZuo;
        private float xieZuoScore;
        private String xieZuoPingYu;
        private float zongHe;
        private float zongHeScore;
        private String zongHePingYu;
        private float zongChengJi;
        private float zongChengJiScore;
        private String zongPingYu;
        private String luRuRen;
        private String luRuDate;
        private Date createDate;
        private int zhengShu;
        private String zhengShuNeiRong;
        private String description;
        private String remark1;
        private String remark2;
        private String remark3;
        private String remark4;
        private String remark5;
        private String remark6;
        private String remark7;
        private String remark8;



        public StudyInfoForm()
        {
        }

        public StudyInfoForm(StudyInfoModel pm)
        {
                if (pm != null)
                {
                        this.studyInfoID = pm.getStudyInfoID();
                        this.orgID = pm.getOrgID();
                        this.courseID = pm.getCourseID();
                        this.userID = pm.getUserID();
                        this.type = pm.getType();
                        this.examType = pm.getExamType();
                        this.testType = pm.getTestType();
                        this.year = StringUtil.nullToStr(pm.getYear());
                        this.grade = pm.getGrade();
                        this.subject = StringUtil.nullToStr(pm.getSubject());
                        this.name = StringUtil.nullToStr(pm.getName());
                        this.school = StringUtil.nullToStr(pm.getSchool());
                        this.book = StringUtil.nullToStr(pm.getBook());
                        this.mail = StringUtil.nullToStr(pm.getMail());
                        this.card = StringUtil.nullToStr(pm.getCard());
                        this.sex = StringUtil.nullToStr(pm.getSex());
                        this.phone = StringUtil.nullToStr(pm.getPhone());
                        this.cellphone = StringUtil.nullToStr(pm.getCellphone());
                        this.address = StringUtil.nullToStr(pm.getAddress());
                        this.postalcode = StringUtil.nullToStr(pm.getPostalcode());

                        this.tingLi = pm.getTingLi();
                        this.tingLiScore = pm.getTingLiScore();
                        this.tingLiPingYu = StringUtil.nullToStr(pm.getTingLiPingYu());

                        this.yuYin = pm.getYuYin();
                        this.yuYinScore = pm.getYuYinScore();
                        this.yuYinPingYu = StringUtil.nullToStr(pm.getYuYinPingYu());

                        this.ciHui = pm.getCiHui();
                        this.ciHuiScore = pm.getCiHuiScore();
                        this.ciHuiPingYu = StringUtil.nullToStr(pm.getCiHuiPingYu());

                        this.huiHua = pm.getHuiHua();
                        this.huiHuaScore = pm.getHuiHuaScore();
                        this.huiHuaPingYu = StringUtil.nullToStr(pm.getHuiHuaPingYu());


                        this.zongHe = pm.getZongHe();
                        this.zongHeScore = pm.getZongHeScore();
                        this.zongHePingYu = StringUtil.nullToStr(pm.getZongHePingYu());

                        this.yuFa = pm.getYuFa();
                        this.yuFaScore = pm.getYuFaScore();
                        this.yuFaPingYu = StringUtil.nullToStr(pm.getYuFaPingYu());

                        this.yueDu = pm.getYueDu();
                        this.yueDuScore = pm.getYueDuScore();
                        this.yueDuPingYu = StringUtil.nullToStr(pm.getYueDuPingYu());


                        this.xieZuo = pm.getXieZuo();
                        this.xieZuoScore = pm.getXieZuoScore();
                        this.xieZuoPingYu = StringUtil.nullToStr(pm.getXieZuoPingYu());

                        this.zongChengJi = pm.getZongChengJi();
                        this.zongChengJiScore = pm.getZongChengJiScore();
                        this.zongPingYu = StringUtil.nullToStr(pm.getZongPingYu());
                        this.luRuRen = StringUtil.nullToStr(pm.getLuRuRen());
                        this.luRuDate = StringUtil.nullToStr(pm.getLuRuDate());
                        this.createDate = pm.getCreateDate();

                        this.zhengShu = pm.getZhengShu();
                        this.zhengShuNeiRong  =  StringUtil.nullToStr(pm.getZhengShuNeiRong());

                        this.description = StringUtil.nullToStr(pm.getDescription());
                        this.remark1 = StringUtil.nullToStr(pm.getRemark1());
                        this.remark2 = StringUtil.nullToStr(pm.getRemark2());
                        this.remark3 = StringUtil.nullToStr(pm.getRemark3());
                        this.remark4 = StringUtil.nullToStr(pm.getRemark4());
                        this.remark5 = StringUtil.nullToStr(pm.getRemark5());
                        this.remark6 = StringUtil.nullToStr(pm.getRemark6());
                        this.remark7 = StringUtil.nullToStr(pm.getRemark7());
                        this.remark8 = StringUtil.nullToStr(pm.getRemark8());

                }
        }


        public int getStudyInfoID()
        {
                return studyInfoID;
        }

        public void setStudyInfoID(int studyInfoID)
        {
                this.studyInfoID = studyInfoID;
        }

        public int getOrgID()
        {
                return orgID;
        }

        public void setOrgID(int orgID)
        {
                this.orgID = orgID;
        }

        public int getCourseID()
        {
                return courseID;
        }

        public void setCourseID(int courseID)
        {
                this.courseID = courseID;
        }

        public int getUserID()
        {
                return userID;
        }

        public void setUserID(int userID)
        {
                this.userID = userID;
        }

        public int getType()
        {
                return type;
        }

        public void setType(int type)
        {
                this.type = type;
        }

        public int getExamType()
        {
                return examType;
        }

        public void setExamType(int examType)
        {
                this.examType = examType;
        }

        public int getTestType()
        {
                return testType;
        }

        public void setTestType(int testType)
        {
                this.testType = testType;
        }

        public String getYear()
        {
                return year;
        }

        public void setYear(String year)
        {
                this.year = year;
        }

        public int getGrade()
        {
                return grade;
        }

        public void setGrade(int grade)
        {
                this.grade = grade;
        }

        public String getSubject()
        {
                return subject;
        }

        public void setSubject(String subject)
        {
                this.subject = subject;
        }

        public String getName()
        {
                return name;
        }

        public void setName(String name)
        {
                this.name = name;
        }

        public String getSchool()
        {
                return school;
        }

        public void setSchool(String school)
        {
                this.school = school;
        }

        public String getBook()
        {
                return book;
        }

        public void setBook(String book)
        {
                this.book = book;
        }

        public String getMail()
        {
                return mail;
        }

        public void setMail(String mail)
        {
                this.mail = mail;
        }

        public String getCard()
        {
                return card;
        }

        public void setCard(String card)
        {
                this.card = card;
        }

        public String getSex()
        {
                return sex;
        }

        public void setSex(String sex)
        {
                this.sex = sex;
        }

        public String getPhone()
        {
                return phone;
        }

        public void setPhone(String phone)
        {
                this.phone = phone;
        }

        public String getCellphone()
        {
                return cellphone;
        }

        public void setCellphone(String cellphone)
        {
                this.cellphone = cellphone;
        }

        public String getAddress()
        {
                return address;
        }

        public void setAddress(String address)
        {
                this.address = address;
        }

        public String getPostalcode()
        {
                return postalcode;
        }

        public void setPostalcode(String postalcode)
        {
                this.postalcode = postalcode;
        }

        public float getTingLi()
        {
                return tingLi;
        }

        public void setTingLi(float tingLi)
        {
                this.tingLi = tingLi;
        }

        public float getTingLiScore()
        {
                return tingLiScore;
        }

        public void setTingLiScore(float tingLiScore)
        {
                this.tingLiScore = tingLiScore;
        }

        public String getTingLiPingYu()
        {
                return tingLiPingYu;
        }

        public void setTingLiPingYu(String tingLiPingYu)
        {
                this.tingLiPingYu = tingLiPingYu;
        }

        public float getYuYin()
        {
                return yuYin;
        }

        public void setYuYin(float yuYin)
        {
                this.yuYin = yuYin;
        }

        public float getYuYinScore()
        {
                return yuYinScore;
        }

        public void setYuYinScore(float yuYinScore)
        {
                this.yuYinScore = yuYinScore;
        }

        public String getYuYinPingYu()
        {
                return yuYinPingYu;
        }

        public void setYuYinPingYu(String yuYinPingYu)
        {
                this.yuYinPingYu = yuYinPingYu;
        }

        public float getCiHui()
        {
                return ciHui;
        }

        public void setCiHui(float ciHui)
        {
                this.ciHui = ciHui;
        }

        public float getCiHuiScore()
        {
                return ciHuiScore;
        }

        public void setCiHuiScore(float ciHuiScore)
        {
                this.ciHuiScore = ciHuiScore;
        }

        public String getCiHuiPingYu()
        {
                return ciHuiPingYu;
        }

        public void setCiHuiPingYu(String ciHuiPingYu)
        {
                this.ciHuiPingYu = ciHuiPingYu;
        }

        public float getHuiHua()
        {
                return huiHua;
        }

        public void setHuiHua(float huiHua)
        {
                this.huiHua = huiHua;
        }

        public float getHuiHuaScore()
        {
                return huiHuaScore;
        }

        public void setHuiHuaScore(float huiHuaScore)
        {
                this.huiHuaScore = huiHuaScore;
        }

        public String getHuiHuaPingYu()
        {
                return huiHuaPingYu;
        }

        public void setHuiHuaPingYu(String huiHuaPingYu)
        {
                this.huiHuaPingYu = huiHuaPingYu;
        }

        public float getYuFa()
        {
                return yuFa;
        }

        public void setYuFa(float yuFa)
        {
                this.yuFa = yuFa;
        }

        public float getYuFaScore()
        {
                return yuFaScore;
        }

        public void setYuFaScore(float yuFaScore)
        {
                this.yuFaScore = yuFaScore;
        }

        public String getYuFaPingYu()
        {
                return yuFaPingYu;
        }

        public void setYuFaPingYu(String yuFaPingYu)
        {
                this.yuFaPingYu = yuFaPingYu;
        }

        public float getYueDu()
        {
                return yueDu;
        }

        public void setYueDu(float yueDu)
        {
                this.yueDu = yueDu;
        }

        public float getYueDuScore()
        {
                return yueDuScore;
        }

        public void setYueDuScore(float yueDuScore)
        {
                this.yueDuScore = yueDuScore;
        }

        public String getYueDuPingYu()
        {
                return yueDuPingYu;
        }

        public void setYueDuPingYu(String yueDuPingYu)
        {
                this.yueDuPingYu = yueDuPingYu;
        }

        public float getXieZuo()
        {
                return xieZuo;
        }

        public void setXieZuo(float xieZuo)
        {
                this.xieZuo = xieZuo;
        }

        public float getXieZuoScore()
        {
                return xieZuoScore;
        }

        public void setXieZuoScore(float xieZuoScore)
        {
                this.xieZuoScore = xieZuoScore;
        }

        public String getXieZuoPingYu()
        {
                return xieZuoPingYu;
        }

        public void setXieZuoPingYu(String xieZuoPingYu)
        {
                this.xieZuoPingYu = xieZuoPingYu;
        }

        public float getZongHe()
        {
                return zongHe;
        }

        public void setZongHe(float zongHe)
        {
                this.zongHe = zongHe;
        }

        public float getZongHeScore()
        {
                return zongHeScore;
        }

        public void setZongHeScore(float zongHeScore)
        {
                this.zongHeScore = zongHeScore;
        }

        public String getZongHePingYu()
        {
                return zongHePingYu;
        }

        public void setZongHePingYu(String zongHePingYu)
        {
                this.zongHePingYu = zongHePingYu;
        }

        public float getZongChengJi()
        {
                return zongChengJi;
        }

        public void setZongChengJi(float zongChengJi)
        {
                this.zongChengJi = zongChengJi;
        }

        public float getZongChengJiScore()
        {
                return zongChengJiScore;
        }

        public void setZongChengJiScore(float zongChengJiScore)
        {
                this.zongChengJiScore = zongChengJiScore;
        }

        public String getZongPingYu()
        {
                return zongPingYu;
        }

        public void setZongPingYu(String zongPingYu)
        {
                this.zongPingYu = zongPingYu;
        }

        public String getLuRuRen()
        {
                return luRuRen;
        }

        public void setLuRuRen(String luRuRen)
        {
                this.luRuRen = luRuRen;
        }

        public String getLuRuDate()
        {
                return luRuDate;
        }

        public void setLuRuDate(String luRuDate)
        {
                this.luRuDate = luRuDate;
        }

        public Date getCreateDate()
        {
                return createDate;
        }

        public void setCreateDate(Date createDate)
        {
                this.createDate = createDate;
        }

        public int getZhengShu()
        {
                return zhengShu;
        }

        public void setZhengShu(int zhengShu)
        {
                this.zhengShu = zhengShu;
        }

        public String getZhengShuNeiRong()
        {
                return zhengShuNeiRong;
        }

        public void setZhengShuNeiRong(String zhengShuNeiRong)
        {
                this.zhengShuNeiRong = zhengShuNeiRong;
        }

        public String getDescription()
        {
                return description;
        }

        public void setDescription(String description)
        {
                this.description = description;
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

        public String getRemark7()
        {
                return remark7;
        }

        public void setRemark7(String remark7)
        {
                this.remark7 = remark7;
        }

        public String getRemark8()
        {
                return remark8;
        }

        public void setRemark8(String remark8)
        {
                this.remark8 = remark8;
        }

        public StudyInfoModel getStudyInfoModel()
        {
                StudyInfoModel pm = new StudyInfoModel();
                pm.setStudyInfoID(this.studyInfoID);
                pm.setOrgID(this.orgID);
                pm.setCourseID(this.courseID);
                pm.setUserID(this.userID);
                pm.setType(this.type);
                pm.setExamType(this.examType);
                pm.setTestType(this.testType);
                pm.setYear(this.year);
                pm.setGrade(this.grade);
                pm.setSubject(this.subject);
                pm.setName(this.name);
                pm.setSchool(this.school);
                pm.setBook(this.book);
                pm.setMail(this.mail);
                pm.setCard(this.card);
                pm.setSex(this.sex);
                pm.setPhone(this.phone);
                pm.setCellphone(this.cellphone);
                pm.setAddress(this.address);
                pm.setPostalcode(this.postalcode);

                pm.setTingLi(this.tingLi);
                pm.setTingLiScore(this.tingLiScore);
                pm.setTingLiPingYu(this.tingLiPingYu);

                pm.setYuYin(this.yuYin);
                pm.setYuYinScore(this.yuYinScore);
                pm.setYuYinPingYu(this.yuYinPingYu);

                pm.setCiHui(this.ciHui);
                pm.setCiHuiScore(this.ciHuiScore);
                pm.setCiHuiPingYu(this.ciHuiPingYu);

                pm.setHuiHua(this.huiHua);
                pm.setHuiHuaScore(this.huiHuaScore);
                pm.setHuiHuaPingYu(this.huiHuaPingYu);

                pm.setZongHe(this.zongHe);
                pm.setZongHeScore(this.zongHeScore);
                pm.setZongHePingYu(this.zongHePingYu);

                pm.setYuFa(this.yuFa);
                pm.setYuFaScore(this.yuFaScore);
                pm.setYuFaPingYu(this.yuFaPingYu);

                pm.setYueDu(this.yueDu);
                pm.setYueDuScore(this.yueDuScore);
                pm.setYueDuPingYu(this.yueDuPingYu);

                pm.setXieZuo(this.xieZuo);
                pm.setXieZuoScore(this.xieZuoScore);
                pm.setXieZuoPingYu(this.xieZuoPingYu);

                pm.setZongChengJi(this.zongChengJi);
                pm.setZongChengJiScore(this.zongChengJiScore);
                pm.setZongPingYu(this.zongPingYu);
                pm.setLuRuRen(this.luRuRen);
                pm.setLuRuDate(this.luRuDate);
                pm.setCreateDate(this.createDate);
                pm.setZhengShu(this.zhengShu);
                pm.setZhengShuNeiRong(this.zhengShuNeiRong);
                pm.setDescription(this.description);
                pm.setRemark1(this.remark1);
                pm.setRemark2(this.remark2);
                pm.setRemark3(this.remark3);
                pm.setRemark4(this.remark4);
                pm.setRemark5(this.remark5);
                pm.setRemark6(this.remark6);
                pm.setRemark7(this.remark7);
                pm.setRemark8(this.remark8);

                return pm;
        }


        
}
