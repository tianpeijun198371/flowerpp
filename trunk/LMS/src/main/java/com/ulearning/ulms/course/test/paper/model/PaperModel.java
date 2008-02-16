package com.ulearning.ulms.course.test.paper.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

import java.util.Date;
import java.util.Set;


/**
 * @author Hibernate CodeGenerator
 */
public class PaperModel implements Serializable
{
        /**
         * identifier field
         */
        private int paperid;

        /**
         * persistent field
         */
        private int courseid;

        /**
         * persistent field
         */
        private String title;

        /**
         * nullable persistent field
         */
        private String description;

        /**
         * nullable persistent field
         */
        private String instruction;

        /**
         * nullable persistent field
         */
        private String type;

        /**
         * nullable persistent field
         */
        private String isuploadpaper;

        /**
         * nullable persistent field
         */
        private String isavailable;

        /**
         * nullable persistent field
         */
        private String isannounce;

        /**
         * nullable persistent field
         */
        private String isfeedbackgrade;

        /**
         * nullable persistent field
         */
        private String isfeedbackanswer;

        /**
         * nullable persistent field
         */
        private String isfeedbackreply;

        /**
         * nullable persistent field
         */
        private Date starttime;

        /**
         * nullable persistent field
         */
        private Date endtime;

        /**
         * nullable persistent field
         */
        private Date createtime;

        /**
         * nullable persistent field
         */
        private String desc1;

        /**
         * nullable persistent field
         */
        private String desc2;

        /**
         * nullable persistent field
         */
        private String desc3;

        /**
         * nullable persistent field
         */
        private String desc4;

        /**
         * nullable persistent field
         */
        private String desc5;

        /**
         * nullable persistent field
         */
        private String desc6;

        /**
         * nullable persistent field
         */
        private String desc7;

        /**
         * persistent field
         */
        private Set tpaperuserTabs;

        /**
         * full constructor
         */
        public PaperModel(int courseid, String title, String description,
                          String instruction, String type, String isuploadpaper,
                          String isavailable, String isannounce, String isfeedbackgrade,
                          String isfeedbackanswer, String isfeedbackreply, Date starttime,
                          Date endtime, Date createtime, String desc1, String desc2,
                          String desc3, String desc4, String desc5, String desc6, String desc7,
                          Set tpaperuserTabs)
        {
                this.courseid = courseid;
                this.title = title;
                this.description = description;
                this.instruction = instruction;
                this.type = type;
                this.isuploadpaper = isuploadpaper;
                this.isavailable = isavailable;
                this.isannounce = isannounce;
                this.isfeedbackgrade = isfeedbackgrade;
                this.isfeedbackanswer = isfeedbackanswer;
                this.isfeedbackreply = isfeedbackreply;
                this.starttime = starttime;
                this.endtime = endtime;
                this.createtime = createtime;
                this.desc1 = desc1;
                this.desc2 = desc2;
                this.desc3 = desc3;
                this.desc4 = desc4;
                this.desc5 = desc5;
                this.desc6 = desc6;
                this.desc7 = desc7;
                this.tpaperuserTabs = tpaperuserTabs;
        }

        /**
         * default constructor
         */
        public PaperModel()
        {
        }

        /**
         * minimal constructor
         */
        public PaperModel(int courseid, String title, Set tpaperuserTabs)
        {
                this.courseid = courseid;
                this.title = title;
                this.tpaperuserTabs = tpaperuserTabs;
        }

        public int getPaperid()
        {
                return this.paperid;
        }

        public void setPaperid(int paperid)
        {
                this.paperid = paperid;
        }

        public int getCourseid()
        {
                return this.courseid;
        }

        public void setCourseid(int courseid)
        {
                this.courseid = courseid;
        }

        public String getTitle()
        {
                return this.title;
        }

        public void setTitle(String title)
        {
                this.title = title;
        }

        public String getDescription()
        {
                return this.description;
        }

        public void setDescription(String description)
        {
                this.description = description;
        }

        public String getInstruction()
        {
                return this.instruction;
        }

        public void setInstruction(String instruction)
        {
                this.instruction = instruction;
        }

        public String getType()
        {
                return this.type;
        }

        public void setType(String type)
        {
                this.type = type;
        }

        public String getIsuploadpaper()
        {
                return this.isuploadpaper;
        }

        public void setIsuploadpaper(String isuploadpaper)
        {
                this.isuploadpaper = isuploadpaper;
        }

        public String getIsavailable()
        {
                return this.isavailable;
        }

        public void setIsavailable(String isavailable)
        {
                this.isavailable = isavailable;
        }

        public String getIsannounce()
        {
                return this.isannounce;
        }

        public void setIsannounce(String isannounce)
        {
                this.isannounce = isannounce;
        }

        public String getIsfeedbackgrade()
        {
                return this.isfeedbackgrade;
        }

        public void setIsfeedbackgrade(String isfeedbackgrade)
        {
                this.isfeedbackgrade = isfeedbackgrade;
        }

        public String getIsfeedbackanswer()
        {
                return this.isfeedbackanswer;
        }

        public void setIsfeedbackanswer(String isfeedbackanswer)
        {
                this.isfeedbackanswer = isfeedbackanswer;
        }

        public String getIsfeedbackreply()
        {
                return this.isfeedbackreply;
        }

        public void setIsfeedbackreply(String isfeedbackreply)
        {
                this.isfeedbackreply = isfeedbackreply;
        }

        public Date getStarttime()
        {
                return this.starttime;
        }

        public void setStarttime(Date starttime)
        {
                this.starttime = starttime;
        }

        public Date getEndtime()
        {
                return this.endtime;
        }

        public void setEndtime(Date endtime)
        {
                this.endtime = endtime;
        }

        public Date getCreatetime()
        {
                return this.createtime;
        }

        public void setCreatetime(Date createtime)
        {
                this.createtime = createtime;
        }

        public String getDesc1()
        {
                return this.desc1;
        }

        public void setDesc1(String desc1)
        {
                this.desc1 = desc1;
        }

        public String getDesc2()
        {
                return this.desc2;
        }

        public void setDesc2(String desc2)
        {
                this.desc2 = desc2;
        }

        public String getDesc3()
        {
                return this.desc3;
        }

        public void setDesc3(String desc3)
        {
                this.desc3 = desc3;
        }

        public String getDesc4()
        {
                return this.desc4;
        }

        public void setDesc4(String desc4)
        {
                this.desc4 = desc4;
        }

        public String getDesc5()
        {
                return this.desc5;
        }

        public void setDesc5(String desc5)
        {
                this.desc5 = desc5;
        }

        public String getDesc6()
        {
                return this.desc6;
        }

        public void setDesc6(String desc6)
        {
                this.desc6 = desc6;
        }

        public String getDesc7()
        {
                return this.desc7;
        }

        public void setDesc7(String desc7)
        {
                this.desc7 = desc7;
        }

        public Set getTpaperuserTabs()
        {
                return this.tpaperuserTabs;
        }

        public void setTpaperuserTabs(Set tpaperuserTabs)
        {
                this.tpaperuserTabs = tpaperuserTabs;
        }

        public String toString()
        {
                return new ToStringBuilder(this).append("paperid", getPaperid())
                        .toString();
        }
}
