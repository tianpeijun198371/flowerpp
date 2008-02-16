/**
 * ScoreListModel.java.
 * User: fengch  Date: 2004-4-27
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.common.model;

import java.io.Serializable;

import java.util.List;


public class ScoreListModel implements Serializable
{
        private List lists;
        private int pageNo;
        private int pageSize;

        public ScoreListModel()
        {
        }

        public List getLists()
        {
                return lists;
        }

        public void setLists(List lists)
        {
                this.lists = lists;
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
}
