/**
 * Created by IntelliJ IDEA.
 * User: dengj
 * Date: Apr 7, 2004
 * Time: 4:51:49 PM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.course.test.grade.form;

import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.course.test.grade.model.GradeModel;

import org.apache.struts.action.ActionForm;

import java.util.Date;


public class GradeForm extends ActionForm
{
        private int gradeID = 0;
        private int courseID = 0;
        private String title = null;
        private String[] paperID = null;
        private int type = 0;
        private int relationID = 0;
        private String description = "";
        private Date createTime = null;
        private Date updateTime = null;

        public GradeForm()
        {
        }

        public GradeForm(GradeModel gm)
        {
                if (gm != null)
                {
                        this.gradeID = gm.getGradeID();
                        this.courseID = gm.getCourseID();
                        this.title = gm.getTitle();
                        this.type = gm.getType();
                        this.description = StringUtil.nullToStr(gm.getDescription());
                        this.createTime = gm.getCreateTime();
                        this.updateTime = gm.getUpdateTime();
                }
        }

        public int getRelationID()
        {
                return relationID;
        }

        public void setRelationID(int relationID)
        {
                this.relationID = relationID;
        }

        public Date getCreateTime()
        {
                return createTime;
        }

        public void setCreateTime(Date createTime)
        {
                this.createTime = createTime;
        }

        public Date getUpdateTime()
        {
                return updateTime;
        }

        public void setUpdateTime(Date updateTime)
        {
                this.updateTime = updateTime;
        }

        public String getDescription()
        {
                return description;
        }

        public void setDescription(String description)
        {
                //String temp = com.ulearning.ulms.core.util.StringUtil.htmlFilter(description.trim());
                this.description = description;
        }

        public int getType()
        {
                return type;
        }

        public void setType(int type)
        {
                this.type = type;
        }

        public String[] getPaperID()
        {
                return paperID;
        }

        public void setPaperID(String[] paperID)
        {
                this.paperID = paperID;
        }

        public int getGradeID()
        {
                return gradeID;
        }

        public void setGradeID(int gradeID)
        {
                this.gradeID = gradeID;
        }

        public int getCourseID()
        {
                return courseID;
        }

        public void setCourseID(int courseID)
        {
                this.courseID = courseID;
        }

        public String getTitle()
        {
                return title;
        }

        public void setTitle(String title)
        {
                //String temp = com.ulearning.ulms.core.util.StringUtil.htmlFilter(title.trim());
                this.title = title;
        }

        public GradeModel getGradeModel()
        {
                GradeModel gm = new GradeModel();
                gm.setCourseID(this.courseID);
                gm.setCreateTime(this.getCreateTime());
                gm.setDescription(this.description);
                gm.setGradeID(this.gradeID);
                gm.setTitle(this.title);
                gm.setType(this.type);
                gm.setUpdateTime(this.updateTime);

                return gm;
        }
}
