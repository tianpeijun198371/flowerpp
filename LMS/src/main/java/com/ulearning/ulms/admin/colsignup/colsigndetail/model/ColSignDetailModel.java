/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-8-30
 * Time: 13:55:10
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.admin.colsignup.colsigndetail.model;

import org.apache.commons.lang.builder.ToStringBuilder;


public class ColSignDetailModel
{
        /**
         * identifier field
         */
        private int colSignDetailID;

        /**
         * persistent field
         */
        private int colSignID;

        /**
         * persistent field
         */
        private int relationID;

        /**
         * persistent field
         */
        private int typeID;

        /**
         * nullable persistent field
         */
        private String desc1;

        /**
         * full constructor
         */
        public ColSignDetailModel(int colSignID, int relationID, int typeID,
                                  String desc1)
        {
                this.colSignID = colSignID;
                this.relationID = relationID;
                this.typeID = typeID;
                this.desc1 = desc1;
        }

        /**
         * default constructor
         */
        public ColSignDetailModel()
        {
        }

        /**
         * minimal constructor
         */
        public ColSignDetailModel(int colSignID, int relationID, int typeID)
        {
                this.colSignID = colSignID;
                this.relationID = relationID;
                this.typeID = typeID;
        }

        public int getColSignDetailID()
        {
                return this.colSignDetailID;
        }

        public void setColSignDetailID(int colSignDetailID)
        {
                this.colSignDetailID = colSignDetailID;
        }

        public int getColSignID()
        {
                return this.colSignID;
        }

        public void setColSignID(int colSignID)
        {
                this.colSignID = colSignID;
        }

        public int getRelationID()
        {
                return this.relationID;
        }

        public void setRelationID(int relationID)
        {
                this.relationID = relationID;
        }

        public int getTypeID()
        {
                return this.typeID;
        }

        public void setTypeID(int typeID)
        {
                this.typeID = typeID;
        }

        public String getDesc1()
        {
                return this.desc1;
        }

        public void setDesc1(String desc1)
        {
                this.desc1 = desc1;
        }

        public String toString()
        {
                return new ToStringBuilder(this).append("colSignDetailID",
                        getColSignDetailID()).toString();
        }
}
