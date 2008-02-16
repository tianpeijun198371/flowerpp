package com.ulearning.ulms.video.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * table="T_CONDITION_TAB"
 */
public class VideoModel implements Serializable
{

        /**
         * identifier field
         */
        private int vclassid;

        /**
         * nullable persistent field
         */
        private String name;

        /**
         * nullable persistent field
         */
        private String type;

        /**
         * nullable persistent field
         */
        private String relationidstr;

        /**
         * nullable persistent field
         */
        private Date begindate;

        /**
         * nullable persistent field
         */
        private Date enddate;

        /**
         * nullable persistent field
         */
        private String remark0;

        /**
         * nullable persistent field
         */
        private String remark1;

        /**
         * nullable persistent field
         */
        private String remark2;

        /**
         * nullable persistent field
         */
        private String remark3;

        /**
         * persistent field
         */
        private Set texamconditionTabs;

        /**
         * full constructor
         */
        public VideoModel(int vclassid, String name, String type, String relationidstr, Date begindate, Date enddate, String remark0, String remark1, String remark2, String remark3, Set texamconditionTabs)
        {
                this.vclassid = vclassid;
                this.name = name;
                this.type = type;
                this.relationidstr = relationidstr;
                this.begindate = begindate;
                this.enddate = enddate;
                this.remark0 = remark0;
                this.remark1 = remark1;
                this.remark2 = remark2;
                this.remark3 = remark3;
                this.texamconditionTabs = texamconditionTabs;
        }

        /**
         * default constructor
         */
        public VideoModel()
        {
        }

        /**
         * minimal constructor
         */
        public VideoModel(int vclassid, Set texamconditionTabs)
        {
                this.vclassid = vclassid;
                this.texamconditionTabs = texamconditionTabs;
        }

        /**
         * generator-class="assigned"
         * type="int"
         * column="vclassID"
         */
        public int getVclassid()
        {
                return this.vclassid;
        }

        public void setVclassid(int vclassid)
        {
                this.vclassid = vclassid;
        }

        /**
         * column="NAME"
         * length="100"
         */
        public String getName()
        {
                return this.name;
        }

        public void setName(String name)
        {
                this.name = name;
        }

        /**
         * column="TYPE"
         * length="1"
         */
        public String getType()
        {
                return this.type;
        }

        public void setType(String type)
        {
                this.type = type;
        }

        /**
         * column="RELATIONIDSTR"
         * length="1000"
         */
        public String getRelationidstr()
        {
                return this.relationidstr;
        }

        public void setRelationidstr(String relationidstr)
        {
                this.relationidstr = relationidstr;
        }

        /**
         * column="BEGINDATE"
         * length="7"
         */
        public Date getBegindate()
        {
                return this.begindate;
        }

        public void setBegindate(Date begindate)
        {
                this.begindate = begindate;
        }

        /**
         * column="ENDDATE"
         * length="7"
         */
        public Date getEnddate()
        {
                return this.enddate;
        }

        public void setEnddate(Date enddate)
        {
                this.enddate = enddate;
        }

        /**
         * column="REMARK0"
         * length="100"
         */
        public String getRemark0()
        {
                return this.remark0;
        }

        public void setRemark0(String remark0)
        {
                this.remark0 = remark0;
        }

        /**
         * column="REMARK1"
         * length="100"
         */
        public String getRemark1()
        {
                return this.remark1;
        }

        public void setRemark1(String remark1)
        {
                this.remark1 = remark1;
        }

        /**
         * column="REMARK2"
         * length="100"
         */
        public String getRemark2()
        {
                return this.remark2;
        }

        public void setRemark2(String remark2)
        {
                this.remark2 = remark2;
        }

        /**
         * column="REMARK3"
         * length="100"
         */
        public String getRemark3()
        {
                return this.remark3;
        }

        public void setRemark3(String remark3)
        {
                this.remark3 = remark3;
        }

        /**
         * lazy="true"
         * inverse="true"
         * cascade="none"
         * column="vclassID"
         * class="ulms_oracle.com.ulearning.ulms.course.test.examscope.TexamconditionTab"
         */
        public Set getTexamconditionTabs()
        {
                return this.texamconditionTabs;
        }

        public void setTexamconditionTabs(Set texamconditionTabs)
        {
                this.texamconditionTabs = texamconditionTabs;
        }

        public String toString()
        {
                return new ToStringBuilder(this)
                        .append("vclassid", getVclassid())
                        .toString();
        }
}
