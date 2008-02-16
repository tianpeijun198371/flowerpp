/**
 * PagerList.java.
 * User: fengch Date: 2005-11-2 16:35:34
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.core.util;

import java.util.ArrayList;
import java.util.List;


/**
 * 封装了一些分页Model。
 */
public class PagerList
{
        //页List
        private List pagerList;

        //当前页码
        private int pageNo;

        //每页的容量
        private int pageSize;

        //分页后的总页数
        private int pageCount;

        //总记录数
        private int totalCount;

        public PagerList()
        {
                this.pagerList=new ArrayList();
        }

        public PagerList(List pagerList)
        {
                this.pagerList = pagerList;
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

        public int getTotalCount()
        {
                return totalCount;
        }

        public void setTotalCount(int totalCount)
        {
                this.totalCount = totalCount;
        }

        public List getPagerList()
        {
                return pagerList;
        }

        public void setPagerList(List pagerList)
        {
                this.pagerList = pagerList;
        }
}
