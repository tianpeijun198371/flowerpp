/**
 * Created by IntelliJ IDEA.
 * Book: dengj
 * Date: Apr 7, 2004
 * Time: 4:51:49 PM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.admin.cerdangan.form;

import com.ulearning.ulms.admin.book.model.BookModel;
import com.ulearning.ulms.admin.cerdangan.model.CerNewdanganTab;
import com.ulearning.ulms.core.util.StringUtil;

import org.apache.struts.action.ActionForm;

import java.util.Date;


public class CerNewDanganForm extends ActionForm
{
        /**
         * identifier field
         */
        private int danganid;

        /**
         * persistent field
         */
        private String certname;

        /**
         * nullable persistent field
         */
        private String time;

        /**
         * nullable persistent field
         */
        private String students;

        /**
         * nullable persistent field
         */
        private String maincontent;

        /**
         * nullable persistent field
         */
        private Integer number;

        /**
         * nullable persistent field
         */
        private String date;

        /**
         * nullable persistent field
         */
        private Float daycharge;

        /**
         * nullable persistent field
         */
        private String charge;

        /**
         * nullable persistent field
         */
        private String trainplace;

        /**
         * nullable persistent field
         */
        private String traintype;

        /**
         * nullable persistent field
         */
        private String projtype;

        /**
         * nullable persistent field
         */
        private String trainlever;

        /**
         * nullable persistent field
         */
        private String projhost;

        /**
         * nullable persistent field
         */
        private String remark1;

        /**
         * nullable persistent field
         */
        private String joiner;

        /**
         * nullable persistent field
         */
        private String manage;

        /**
         * nullable persistent field
         */
        private String remark2;

        /**
         * nullable persistent field
         */
        private String remark3;

        public int getDanganid()
        {
                return danganid;
        }

        public void setDanganid(int danganid)
        {
                this.danganid = danganid;
        }

        public String getCertname()
        {
                return certname;
        }

        public void setCertname(String certname)
        {
                this.certname = certname;
        }

        public String getTime()
        {
                return time;
        }

        public void setTime(String time)
        {
                this.time = time;
        }

        public String getStudents()
        {
                return students;
        }

        public void setStudents(String students)
        {
                this.students = students;
        }

        public String getMaincontent()
        {
                return maincontent;
        }

        public void setMaincontent(String maincontent)
        {
                this.maincontent = maincontent;
        }

        public Integer getNumber()
        {
                return number;
        }

        public void setNumber(Integer number)
        {
                this.number = number;
        }

        public String getDate()
        {
                return date;
        }

        public void setDate(String date)
        {
                this.date = date;
        }

        public Float getDaycharge()
        {
                return daycharge;
        }

        public void setDaycharge(Float daycharge)
        {
                this.daycharge = daycharge;
        }

        public String getCharge()
        {
                return charge;
        }

        public void setCharge(String charge)
        {
                this.charge = charge;
        }

        public String getTrainplace()
        {
                return trainplace;
        }

        public void setTrainplace(String trainplace)
        {
                this.trainplace = trainplace;
        }

        public String getTraintype()
        {
                return traintype;
        }

        public void setTraintype(String traintype)
        {
                this.traintype = traintype;
        }

        public String getProjtype()
        {
                return projtype;
        }

        public void setProjtype(String projtype)
        {
                this.projtype = projtype;
        }

        public String getTrainlever()
        {
                return trainlever;
        }

        public void setTrainlever(String trainlever)
        {
                this.trainlever = trainlever;
        }

        public String getProjhost()
        {
                return projhost;
        }

        public void setProjhost(String projhost)
        {
                this.projhost = projhost;
        }

        public String getJoiner()
        {
                return joiner;
        }

        public void setJoiner(String joiner)
        {
                this.joiner = joiner;
        }

        public String getManage()
        {
                return manage;
        }

        public void setManage(String manage)
        {
                this.manage = manage;
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

        public String getRemark1()
        {
                return remark1;
        }

        public void setRemark1(String remark1)
        {
                this.remark1 = remark1;
        }

        public CerNewDanganForm getCerDanganForm(CerNewdanganTab theModel)
        {
                CerNewDanganForm cerNewDanganForm = new CerNewDanganForm();
                cerNewDanganForm.setDanganid(theModel.getDanganid());
                cerNewDanganForm.setCertname(theModel.getCertname());
                cerNewDanganForm.setCharge(theModel.getCharge());
                cerNewDanganForm.setDate(theModel.getDate());
                cerNewDanganForm.setDaycharge(theModel.getDaycharge());
                cerNewDanganForm.setJoiner(theModel.getJoiner());
                cerNewDanganForm.setMaincontent(theModel.getMaincontent());
                cerNewDanganForm.setManage(theModel.getManage());
                cerNewDanganForm.setNumber(theModel.getNumber());
                cerNewDanganForm.setProjhost(theModel.getProjhost());
                cerNewDanganForm.setProjtype(theModel.getProjtype());
                cerNewDanganForm.setStudents(theModel.getStudents());
                cerNewDanganForm.setTime(theModel.getTime());
                cerNewDanganForm.setTrainlever(theModel.getTrainlever());
                cerNewDanganForm.setTraintype(theModel.getTraintype());
                cerNewDanganForm.setTrainplace(theModel.getTrainplace());
                cerNewDanganForm.setRemark1(theModel.getRemark1());
                cerNewDanganForm.setRemark2(theModel.getRemark2());
                cerNewDanganForm.setRemark3(theModel.getRemark3());

                return cerNewDanganForm;
        }

        public CerNewdanganTab getCerNewdanganTab()
        {
                CerNewdanganTab CerNewdanganTab = new CerNewdanganTab();
                CerNewdanganTab.setDanganid(this.danganid);
                CerNewdanganTab.setCertname(this.certname);
                CerNewdanganTab.setCharge(this.charge);
                CerNewdanganTab.setDate(this.date);
                CerNewdanganTab.setDaycharge(this.daycharge);
                CerNewdanganTab.setJoiner(this.joiner);
                CerNewdanganTab.setMaincontent(this.maincontent);
                CerNewdanganTab.setManage(this.manage);
                CerNewdanganTab.setNumber(this.number);
                CerNewdanganTab.setProjhost(this.projhost);
                CerNewdanganTab.setProjtype(this.projtype);
                CerNewdanganTab.setRemark1(this.remark1);
                CerNewdanganTab.setRemark2(this.remark2);
                CerNewdanganTab.setRemark3(this.remark3);
                CerNewdanganTab.setStudents(this.students);
                CerNewdanganTab.setTime(this.time);
                CerNewdanganTab.setTrainlever(this.trainlever);
                CerNewdanganTab.setTrainplace(this.trainplace);
                CerNewdanganTab.setTraintype(this.traintype);

                return CerNewdanganTab;
        }
}
