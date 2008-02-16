package com.ulearning.ulms.tools.visit.model;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Jacky
 * Date: 2007-12-28
 * Time: 10:57:56
 * To change this template use File | Settings | File Templates.
 */
public class VisitModel
{
        private int visitID = 0;
        private int type = 0;
        private int relationID = 0;
        private int visitNum = 0;
        private Date createTime = null;
        private Date updateTime = null;
        private String title = "";
        private String description = "";
        private String remark = "";

        public int getVisitID()
        {
                return visitID;
        }

        public void setVisitID(int visitID)
        {
                this.visitID = visitID;
        }

        public int getType()
        {
                return type;
        }

        public void setType(int type)
        {
                this.type = type;
        }

        public int getRelationID()
        {
                return relationID;
        }

        public void setRelationID(int relationID)
        {
                this.relationID = relationID;
        }

        public int getVisitNum()
        {
                return visitNum;
        }

        public void setVisitNum(int visitNum)
        {
                this.visitNum = visitNum;
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

        public String getTitle()
        {
                return title;
        }

        public void setTitle(String title)
        {
                this.title = title;
        }

        public String getDescription()
        {
                return description;
        }

        public void setDescription(String description)
        {
                this.description = description;
        }

        public String getRemark()
        {
                return remark;
        }

        public void setRemark(String remark)
        {
                this.remark = remark;
        }
}
