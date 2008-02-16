/**
 * MasterListModel.java.
 * User: fengch  Date: 2004-4-27
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.model;

import java.io.Serializable;

import java.util.ArrayList;


public class MasterListModel implements Serializable
{
        private ArrayList list;
        private int pageNo;
        private int pageSize;
        private int pageCount;
        private int totalNumber;

        public MasterListModel()
        {
        }

        public MasterListModel(ArrayList list)
        {
                this.list = list;
        }

        public int getPageNo()
        {
                return pageNo;
        }

        public void setPageNo(int pageNo)
        {
                this.pageNo = pageNo;
        }

        public int getPageSize()
        {
                return pageSize;
        }

        public void setPageSize(int pageSize)
        {
                this.pageSize = pageSize;
        }

        public int getPageCount()
        {
                return pageCount;
        }

        public void setPageCount(int pageCount)
        {
                this.pageCount = pageCount;
        }

        public int getTotalNumber()
        {
                return totalNumber;
        }

        public void setTotalNumber(int totalNumber)
        {
                this.totalNumber = totalNumber;
        }

        public ArrayList getList()
        {
                return list;
        }

        public void setList(ArrayList list)
        {
                this.list = list;
        }
}
