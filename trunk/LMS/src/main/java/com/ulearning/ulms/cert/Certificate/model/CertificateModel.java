/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.cert.Certificate.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

import java.util.Date;


/**
 * Class description goes here.
 * <p/>
 * Created by Auto Code Produce System
 * User: xiejh
 * Date: 20060109
 * Time: 101040
 */
public class CertificateModel implements Serializable
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

        /**
         * default constructor
         */
        public CertificateModel()
        {
        }

        /**
         * full constructor
         */
        public CertificateModel(int ctifiID, int courseID, String cZuyeci,
                                String cZuoyeGrade, String cZuoyeAllGrade, String cZuoyeMoren,
                                String cKaoshiID, String cKaoshiTitle, String cKaoshiBili,
                                String cKaoshiMoren, String cbak1, String cbak2, String cbak3)
        {
                this.ctifiID = ctifiID;
                this.courseID = courseID;
                this.cZuyeci = cZuyeci;
                this.cZuoyeGrade = cZuoyeGrade;
                this.cZuoyeAllGrade = cZuoyeAllGrade;
                this.cZuoyeMoren = cZuoyeMoren;
                this.cKaoshiID = cKaoshiID;
                this.cKaoshiTitle = cKaoshiTitle;
                this.cKaoshiBili = cKaoshiBili;
                this.cKaoshiMoren = cKaoshiMoren;
                this.cbak1 = cbak1;
                this.cbak2 = cbak2;
                this.cbak3 = cbak3;
        }

        public String getcZuyeci()
        {
                return cZuyeci;
        }

        public void setcZuyeci(String cZuyeci)
        {
                this.cZuyeci = cZuyeci;
        }

        public String getcZuoyeGrade()
        {
                return cZuoyeGrade;
        }

        public void setcZuoyeGrade(String cZuoyeGrade)
        {
                this.cZuoyeGrade = cZuoyeGrade;
        }

        public String getcZuoyeAllGrade()
        {
                return cZuoyeAllGrade;
        }

        public void setcZuoyeAllGrade(String cZuoyeAllGrade)
        {
                this.cZuoyeAllGrade = cZuoyeAllGrade;
        }

        public String getcZuoyeMoren()
        {
                return cZuoyeMoren;
        }

        public void setcZuoyeMoren(String cZuoyeMoren)
        {
                this.cZuoyeMoren = cZuoyeMoren;
        }

        public String getcKaoshiID()
        {
                return cKaoshiID;
        }

        public void setcKaoshiID(String cKaoshiID)
        {
                this.cKaoshiID = cKaoshiID;
        }

        public String getcKaoshiTitle()
        {
                return cKaoshiTitle;
        }

        public void setcKaoshiTitle(String cKaoshiTitle)
        {
                this.cKaoshiTitle = cKaoshiTitle;
        }

        public String getcKaoshiBili()
        {
                return cKaoshiBili;
        }

        public void setcKaoshiBili(String cKaoshiBili)
        {
                this.cKaoshiBili = cKaoshiBili;
        }

        public String getcKaoshiMoren()
        {
                return cKaoshiMoren;
        }

        public void setcKaoshiMoren(String cKaoshiMoren)
        {
                this.cKaoshiMoren = cKaoshiMoren;
        }

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

        public String toString()
        {
                return new ToStringBuilder(this).append("ctifiID", getCtifiID())
                        .toString();
        }
}
