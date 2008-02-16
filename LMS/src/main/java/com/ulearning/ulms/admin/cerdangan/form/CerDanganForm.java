/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.admin.cerdangan.form;

import com.ulearning.ulms.admin.cerdangan.model.CerDanganModel;
import com.ulearning.ulms.tools.upload.model.UploadForm;

import org.apache.struts.action.ActionForm;

import java.util.Date;


/**
 * Class description goes here.
 * <p/>
 * Created by Auto Code Produce System
 * User: xiejh
 * Date: 20060317
 * Time: 103906
 */
public class CerDanganForm extends UploadForm
{
        private int danganID = 0;
        private int adminID = 0;
        private String certName = "";
        private Date dateTime = new java.sql.Date(System.currentTimeMillis());
        private int page = 0;
        private String remark0 = "";
        private String remark1 = "";
        private String remark2 = "";
        private String remark3 = "";
        private String remark4 = "";
        private String assdateTime = "";

        public String getAssdateTime()
        {
                return assdateTime;
        }

        public void setAssdateTime(String assdateTime)
        {
                this.assdateTime = assdateTime;
        }

        public int getDanganID()
        {
                return danganID;
        }

        public void setDanganID(int danganID)
        {
                this.danganID = danganID;
        }

        public int getAdminID()
        {
                return adminID;
        }

        public void setAdminID(int adminID)
        {
                this.adminID = adminID;
        }

        public String getCertName()
        {
                return certName;
        }

        public void setCertName(String certName)
        {
                this.certName = certName;
        }

        public Date getDateTime()
        {
                return dateTime;
        }

        public void setDateTime(Date dateTime)
        {
                this.dateTime = dateTime;
        }

        public int getPage()
        {
                return page;
        }

        public void setPage(int page)
        {
                this.page = page;
        }

        public String getRemark0()
        {
                return remark0;
        }

        public void setRemark0(String remark0)
        {
                this.remark0 = remark0;
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

        public CerDanganModel getCerDanganModel()
        {
                CerDanganModel cerDanganModel = new CerDanganModel();
                cerDanganModel.setDanganID(this.danganID);
                cerDanganModel.setAdminID(this.adminID);
                cerDanganModel.setCertName(this.certName);
                cerDanganModel.setDateTime(this.dateTime);
                cerDanganModel.setPage(this.page);
                cerDanganModel.setRemark0(this.remark0);
                cerDanganModel.setRemark1(this.remark1);
                cerDanganModel.setRemark2(this.remark2);
                cerDanganModel.setRemark3(this.remark3);
                cerDanganModel.setRemark4(this.remark4);

                return cerDanganModel;
        }

        public CerDanganForm getCerDanganForm(CerDanganModel theModel)
        {
                CerDanganForm cerDanganForm = new CerDanganForm();
                cerDanganForm.setDanganID(theModel.getDanganID());
                cerDanganForm.setAdminID(theModel.getAdminID());
                cerDanganForm.setCertName(theModel.getCertName());
                cerDanganForm.setDateTime(theModel.getDateTime());
                cerDanganForm.setPage(theModel.getPage());
                cerDanganForm.setRemark0(theModel.getRemark0());
                cerDanganForm.setRemark1(theModel.getRemark1());
                cerDanganForm.setRemark2(theModel.getRemark2());
                cerDanganForm.setRemark3(theModel.getRemark3());
                cerDanganForm.setRemark4(theModel.getRemark4());

                return cerDanganForm;
        }
}
