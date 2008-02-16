//Source file: d:\\ulms\\source\\com\\eduedu\\ulms\\tools\\calendar\\model\\PreferenceModel.java
package com.ulearning.ulms.tools.calendar.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;


/**
 * @author Hibernate CodeGenerator
 */
public class PreferenceModel implements Serializable
{
        /**
         * identifier field
         */
        private int userid;

        /**
         * nullable persistent field
         */
        private int loginview;

        /**
         * nullable persistent field
         */
        private String timezone;

        /**
         * persistent field
         */
        private int firstdayofweek;

        /**
         * nullable persistent field
         */
        private String isviewlunarcalendar;

        /**
         * nullable persistent field
         */
        private String hourstyle;

        public int getUserid()
        {
                return userid;
        }

        public void setUserid(int userid)
        {
                this.userid = userid;
        }

        public int getLoginview()
        {
                return loginview;
        }

        public void setLoginview(int loginview)
        {
                this.loginview = loginview;
        }

        public String getTimezone()
        {
                return timezone;
        }

        public void setTimezone(String timezone)
        {
                this.timezone = timezone;
        }

        public int getFirstdayofweek()
        {
                return firstdayofweek;
        }

        public void setFirstdayofweek(int firstdayofweek)
        {
                this.firstdayofweek = firstdayofweek;
        }

        public String getIsviewlunarcalendar()
        {
                return isviewlunarcalendar;
        }

        public void setIsviewlunarcalendar(String isviewlunarcalendar)
        {
                this.isviewlunarcalendar = isviewlunarcalendar;
        }

        public String getHourstyle()
        {
                return hourstyle;
        }

        public void setHourstyle(String hourstyle)
        {
                this.hourstyle = hourstyle;
        }

        public String toString()
        {
                return new ToStringBuilder(this).append("userid", getUserid()).toString();
        }
}
