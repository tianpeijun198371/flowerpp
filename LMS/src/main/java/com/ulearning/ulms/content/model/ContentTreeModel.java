/**
 * ContentTreeModel.java.
 * User: fengch Date: 2005-6-17 15:44:06
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.content.model;

import java.io.Serializable;

import java.util.List;


public class ContentTreeModel implements Serializable
{
        private int pageNo;
        private int pageSize;
        private int pageCount;
        private int totalCount;
        private List contents;
        private List contentCatalogs;

        public ContentTreeModel()
        {
        }

        public ContentTreeModel(int pageNo, int pageSize, int pageCount,
                                int totalCount, List contents, List contentCatalogs)
        {
                this.pageNo = pageNo;
                this.pageSize = pageSize;
                this.pageCount = pageCount;
                this.totalCount = totalCount;
                this.contents = contents;
                this.contentCatalogs = contentCatalogs;
        }

        public ContentTreeModel(int pageNo, int pageSize, int pageCount,
                                List contents, List contentCatalogs)
        {
                this.pageNo = pageNo;
                this.pageSize = pageSize;
                this.pageCount = pageCount;
                this.contents = contents;
                this.contentCatalogs = contentCatalogs;
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

        public List getContents()
        {
                return contents;
        }

        public void setContents(List contents)
        {
                this.contents = contents;
        }

        public List getContentCatalogs()
        {
                return contentCatalogs;
        }

        public void setContentCatalogs(List contentCatalogs)
        {
                this.contentCatalogs = contentCatalogs;
        }

        public int getTotalCount()
        {
                return totalCount;
        }

        public void setTotalCount(int totalCount)
        {
                this.totalCount = totalCount;
        }
}
