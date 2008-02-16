/**
 * Access.java.
 * User: fengch  Date: 2004-4-26
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.access.model;

import java.io.Serializable;

import java.util.ArrayList;


public class AccessList implements Serializable
{
        private ArrayList accesss;
        private int pageNo;
        private int pageSize;
        private int pageCount;

        public AccessList()
        {
        }

        public AccessList(ArrayList accesss, int pageNo, int pageSize, int pageCount)
        {
                this.accesss = accesss;
                this.pageNo = pageNo;
                this.pageSize = pageSize;
                this.pageCount = pageCount;
        }

        //ArrayList is constructed by the DAO,and the value object is just designed to "TRANSLATE" the values.
        public ArrayList getAccesss()
        {
                return this.accesss;
        }

        public int getPageNo()
        {
                return this.pageNo;
        }

        public int getPageSize()
        {
                return this.pageSize;
        }

        public int getPageCount()
        {
                return this.pageCount;
        }

        public void setAccesss(ArrayList accesss)
        {
                this.accesss = accesss;
        }

        public void setPageNo(int pageNo)
        {
                this.pageNo = pageNo;
        }

        public void setPageSize(int pageSize)
        {
                this.pageSize = pageSize;
        }

        public void setPageCount(int pageCount)
        {
                this.pageCount = pageCount;
        }
}
