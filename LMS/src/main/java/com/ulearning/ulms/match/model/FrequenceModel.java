/**
 * FrequenceModel.java.
 * User: zhangy Date: 2005-6-2 14:32:34
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.match.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

import java.util.Date;


public class FrequenceModel implements Serializable
{
        /**
         * identifier field
         */
        private Integer frequenceid;

        /**
         * persistent field
         */
        private String frequencemode;

        /**
         * nullable persistent field
         */
        private Date frequencedate;

        /**
         * persistent field
         */
        private String frequencescope;

        /**
         * nullable persistent field
         */
        private Date startdate;

        /**
         * nullable persistent field
         */
        private Date repeattimes;

        /**
         * nullable persistent field
         */
        private Date finishdate;

        /**
         * full constructor
         */
        public FrequenceModel(String frequencemode, Date frequencedate,
                              String frequencescope, Date startdate, Date repeattimes, Date finishdate)
        {
                this.frequencemode = frequencemode;
                this.frequencedate = frequencedate;
                this.frequencescope = frequencescope;
                this.startdate = startdate;
                this.repeattimes = repeattimes;
                this.finishdate = finishdate;
        }

        /**
         * default constructor
         */
        public FrequenceModel()
        {
        }

        /**
         * minimal constructor
         */
        public FrequenceModel(String frequencemode, String frequencescope)
        {
                this.frequencemode = frequencemode;
                this.frequencescope = frequencescope;
        }

        public Integer getFrequenceid()
        {
                return this.frequenceid;
        }

        public void setFrequenceid(Integer frequenceid)
        {
                this.frequenceid = frequenceid;
        }

        public String getFrequencemode()
        {
                return this.frequencemode;
        }

        public void setFrequencemode(String frequencemode)
        {
                this.frequencemode = frequencemode;
        }

        public Date getFrequencedate()
        {
                return this.frequencedate;
        }

        public void setFrequencedate(Date frequencedate)
        {
                this.frequencedate = frequencedate;
        }

        public String getFrequencescope()
        {
                return this.frequencescope;
        }

        public void setFrequencescope(String frequencescope)
        {
                this.frequencescope = frequencescope;
        }

        public Date getStartdate()
        {
                return this.startdate;
        }

        public void setStartdate(Date startdate)
        {
                this.startdate = startdate;
        }

        public Date getRepeattimes()
        {
                return this.repeattimes;
        }

        public void setRepeattimes(Date repeattimes)
        {
                this.repeattimes = repeattimes;
        }

        public Date getFinishdate()
        {
                return this.finishdate;
        }

        public void setFinishdate(Date finishdate)
        {
                this.finishdate = finishdate;
        }

        public String toString()
        {
                return new ToStringBuilder(this).append("frequenceid", getFrequenceid())
                        .toString();
        }
}
