package com.ulearning.ulms.user.group.model;

import java.io.Serializable;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * @author Hibernate CodeGenerator
 */
public class GroupModel implements Serializable
{

        /**
         * identifier field
         */
        private int groupId;

        /**
         * persistent field
         */
        private String groupName;

        /**
         * persistent field
         */
        private int relationID;

        /**
         * persistent field
         */
        private int type;

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
         * nullable persistent field
         */
        private String remark4;

        /**
         * nullable persistent field
         */
        private String remark5;

        /**
         * persistent field
         */
        private Set uuserGroupTabs;

        /**
         * full constructor
         */
        public GroupModel(String groupName, int relationId, int type, String remark1, String remark2, String remark3, String remark4, String remark5)
        {
                this.groupName = groupName;
                this.relationID = relationId;
                this.type = type;
                this.remark1 = remark1;
                this.remark2 = remark2;
                this.remark3 = remark3;
                this.remark4 = remark4;
                this.remark5 = remark5;
        }

        /**
         * default constructor
         */
        public GroupModel()
        {
        }

        /**
         * minimal constructor
         */
        public GroupModel(String groupName, int relationId, int type)
        {
                this.groupName = groupName;
                this.relationID = relationId;
                this.type = type;
        }

        public int getGroupId()
        {
                return this.groupId;
        }

        public void setGroupId(int groupId)
        {
                this.groupId = groupId;
        }

        public String getGroupName()
        {
                return this.groupName;
        }

        public void setGroupName(String groupName)
        {
                this.groupName = groupName;
        }

        public int getRelationID()
        {
                return this.relationID;
        }

        public void setRelationID(int relationID)
        {
                this.relationID = relationID;
        }

        public int getType()
        {
                return this.type;
        }

        public void setType(int type)
        {
                this.type = type;
        }

        public String getRemark1()
        {
                return this.remark1;
        }

        public void setRemark1(String remark1)
        {
                this.remark1 = remark1;
        }

        public String getRemark2()
        {
                return this.remark2;
        }

        public void setRemark2(String remark2)
        {
                this.remark2 = remark2;
        }

        public String getRemark3()
        {
                return this.remark3;
        }

        public void setRemark3(String remark3)
        {
                this.remark3 = remark3;
        }

        public String getRemark4()
        {
                return this.remark4;
        }

        public void setRemark4(String remark4)
        {
                this.remark4 = remark4;
        }

        public String getRemark5()
        {
                return this.remark5;
        }

        public void setRemark5(String remark5)
        {
                this.remark5 = remark5;
        }

        public Set getUuserGroupTabs()
        {
                return this.uuserGroupTabs;
        }

        public void setUuserGroupTabs(Set uuserGroupTabs)
        {
                this.uuserGroupTabs = uuserGroupTabs;
        }

        public String toString()
        {
                return new ToStringBuilder(this)
                        .append("groupId", getGroupId())
                        .toString();
        }

}
