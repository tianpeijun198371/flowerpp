/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.cert.Certificate.form;

import com.ulearning.ulms.cert.Certificate.model.CertificateModel;

import org.apache.struts.action.ActionForm;

import java.util.Date;


/**
 * Class description goes here.
 * <p/>
 * Created by Auto Code Produce System
 * User: xiejh
 * Date: 20060109
 * Time: 101040
 */
public class CertificateForm extends ActionForm
{
        private int ctifiID = 0;
        private int courseID = 0;
        private String cZuyeci = "";
        private String cZuoyeGrade = "";
        private String cZuoyeAllGrade = "";
        private String cZuoyeMoren = "";
        private String cKaoshiID = "";
        private String cKaoshiTitle = "";
        private String cKaoshiBili = "";
        private String cKaoshiMoren = "";
        private String cbak1 = "";
        private String cbak2 = "";
        private String cbak3 = "";

        public int getCtifiID()
        {
                return ctifiID;
        }

        public void setCtifiID(int ctifiID)
        {
                this.ctifiID = ctifiID;
        }

        public int getCourseID()
        {
                return courseID;
        }

        public void setCourseID(int courseID)
        {
                this.courseID = courseID;
        }

        public String getCZuyeci()
        {
                return cZuyeci;
        }

        public void setCZuyeci(String cZuyeci)
        {
                this.cZuyeci = cZuyeci;
        }

        public String getCZuoyeGrade()
        {
                return cZuoyeGrade;
        }

        public void setCZuoyeGrade(String cZuoyeGrade)
        {
                this.cZuoyeGrade = cZuoyeGrade;
        }

        public String getCZuoyeAllGrade()
        {
                return cZuoyeAllGrade;
        }

        public void setCZuoyeAllGrade(String cZuoyeAllGrade)
        {
                this.cZuoyeAllGrade = cZuoyeAllGrade;
        }

        public String getCZuoyeMoren()
        {
                return cZuoyeMoren;
        }

        public void setCZuoyeMoren(String cZuoyeMoren)
        {
                this.cZuoyeMoren = cZuoyeMoren;
        }

        public String getCKaoshiID()
        {
                return cKaoshiID;
        }

        public void setCKaoshiID(String cKaoshiID)
        {
                this.cKaoshiID = cKaoshiID;
        }

        public String getCKaoshiTitle()
        {
                return cKaoshiTitle;
        }

        public void setCKaoshiTitle(String cKaoshiTitle)
        {
                this.cKaoshiTitle = cKaoshiTitle;
        }

        public String getCKaoshiBili()
        {
                return cKaoshiBili;
        }

        public void setCKaoshiBili(String cKaoshiBili)
        {
                this.cKaoshiBili = cKaoshiBili;
        }

        public String getCKaoshiMoren()
        {
                return cKaoshiMoren;
        }

        public void setCKaoshiMoren(String cKaoshiMoren)
        {
                this.cKaoshiMoren = cKaoshiMoren;
        }

        public String getCbak1()
        {
                return cbak1;
        }

        public void setCbak1(String cbak1)
        {
                this.cbak1 = cbak1;
        }

        public String getCbak2()
        {
                return cbak2;
        }

        public void setCbak2(String cbak2)
        {
                this.cbak2 = cbak2;
        }

        public String getCbak3()
        {
                return cbak3;
        }

        public void setCbak3(String cbak3)
        {
                this.cbak3 = cbak3;
        }

        public CertificateModel getCertificateModel()
        {
                CertificateModel certificateModel = new CertificateModel();
                certificateModel.setCtifiID(this.ctifiID);
                certificateModel.setCourseID(this.courseID);
                certificateModel.setCZuyeci(this.cZuyeci);
                certificateModel.setCZuoyeGrade(this.cZuoyeGrade);
                certificateModel.setCZuoyeAllGrade(this.cZuoyeAllGrade);
                certificateModel.setCZuoyeMoren(this.cZuoyeMoren);
                certificateModel.setCKaoshiID(this.cKaoshiID);
                certificateModel.setCKaoshiTitle(this.cKaoshiTitle);
                certificateModel.setCKaoshiBili(this.cKaoshiBili);
                certificateModel.setCKaoshiMoren(this.cKaoshiMoren);
                certificateModel.setCbak1(this.cbak1);
                certificateModel.setCbak2(this.cbak2);
                certificateModel.setCbak3(this.cbak3);

                return certificateModel;
        }

        public CertificateForm getCertificateForm(CertificateModel theModel)
        {
                CertificateForm certificateForm = new CertificateForm();
                certificateForm.setCtifiID(theModel.getCtifiID());
                certificateForm.setCourseID(theModel.getCourseID());
                certificateForm.setCZuyeci(theModel.getCZuyeci());
                certificateForm.setCZuoyeGrade(theModel.getCZuoyeGrade());
                certificateForm.setCZuoyeAllGrade(theModel.getCZuoyeAllGrade());
                certificateForm.setCZuoyeMoren(theModel.getCZuoyeMoren());
                certificateForm.setCKaoshiID(theModel.getCKaoshiID());
                certificateForm.setCKaoshiTitle(theModel.getCKaoshiTitle());
                certificateForm.setCKaoshiBili(theModel.getCKaoshiBili());
                certificateForm.setCKaoshiMoren(theModel.getCKaoshiMoren());
                certificateForm.setCbak1(theModel.getCbak1());
                certificateForm.setCbak2(theModel.getCbak2());
                certificateForm.setCbak3(theModel.getCbak3());

                return certificateForm;
        }
}
