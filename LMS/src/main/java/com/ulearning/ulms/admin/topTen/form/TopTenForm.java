/**
 * Created by IntelliJ IDEA.
 * author: houct
 * Date: 2005/04/08
 * Time: 10:58:23 AM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.admin.topTen.form;

import com.ulearning.ulms.admin.topTen.model.TopTenModel;

import org.apache.struts.action.ActionForm;

import java.util.Date;


public class TopTenForm extends ActionForm
{
        private int ID = 0;
        private String name = null;
        private String type = null;
        private String c_t = null;
        private String duty = null;
        private int grade = 0;
        private String link = null;
        private String description = null;
        private String is_display = null;
        private Date creattime = null;
        private String desc0 = null;
        private String desc1 = null;

        public TopTenForm()
        {
        }

        public TopTenForm(TopTenModel ttm)
        {
                if (ttm != null)
                {
                        this.ID = ttm.getId();
                        this.name = ttm.getName();
                        this.type = ttm.getType();
                        this.c_t = ttm.getCt();
                        this.duty = ttm.getDuty();
                        this.grade = ttm.getGrade();
                        this.link = ttm.getLink();
                        this.description = ttm.getDescription();
                        this.is_display = ttm.getIsDisplay();
                        this.creattime = ttm.getCreattime();
                        this.desc0 = ttm.getDesc0();
                        this.desc1 = ttm.getDesc1();
                }
        }

        public int getID()
        {
                return ID;
        }

        public void setID(int ID)
        {
                this.ID = ID;
        }

        public String getName()
        {
                return name;
        }

        public void setName(String name)
        {
                this.name = name;
        }

        public String getType()
        {
                return type;
        }

        public void setType(String type)
        {
                this.type = type;
        }

        public String getC_t()
        {
                return c_t;
        }

        public void setC_t(String c_t)
        {
                this.c_t = c_t;
        }

        public String getDuty()
        {
                return duty;
        }

        public void setDuty(String duty)
        {
                this.duty = duty;
        }

        public int getGrade()
        {
                return grade;
        }

        public void setGrade(int grade)
        {
                this.grade = grade;
        }

        public String getLink()
        {
                return link;
        }

        public void setLink(String link)
        {
                this.link = link;
        }

        public String getDescription()
        {
                return description;
        }

        public void setDescription(String description)
        {
                this.description = description;
        }

        public String getIs_display()
        {
                return is_display;
        }

        public void setIs_display(String is_display)
        {
                this.is_display = is_display;
        }

        public Date getCreattime()
        {
                return creattime;
        }

        public void setCreattime(Date creattime)
        {
                this.creattime = creattime;
        }

        public TopTenModel getTopTenModel()
        {
                TopTenModel ttm = new TopTenModel();
                ttm.setId(this.ID);
                ttm.setName(this.name);
                ttm.setType(this.type);
                ttm.setCt(this.c_t);
                ttm.setDuty(this.duty);
                ttm.setGrade(this.grade);
                ttm.setLink(this.link);
                ttm.setDescription(this.description);
                ttm.setIsDisplay(this.is_display);
                ttm.setCreattime(this.creattime);
                ttm.setDesc0(this.desc0);
                ttm.setDesc1(this.desc1);

                return ttm;
        }

        public String getDesc0()
        {
                return desc0;
        }

        public void setDesc0(String desc0)
        {
                this.desc0 = desc0;
        }

        public String getDesc1()
        {
                return desc1;
        }

        public void setDesc1(String desc1)
        {
                this.desc1 = desc1;
        }
}
