/*
 * Copyright (c) 2005 Your Corporation. All Rights Reserved.
 */
package com.ulearning.ulms.util;

import java.util.List;

/**
 * 处理List的分页
 */
public class ListPage implements Page
{
        private List elements;
        private int pageSize;
        private int pageNumber;

        /**
         * 构建ListPage对象，完成List数据的分页处理
         *
         * @param elements   List数据源
         * @param pageNumber 当前页编码，从1开始，如果传的值为Integer.MAX_VALUE表示获取最后一页。
         *                   如果你不知道最后一页编码，传Integer.MAX_VALUE即可。如果当前页超过总页数，也表示最后一页。
         *                   这两种情况将重新更改当前页的页码，为最后一页编码。
         * @param pageSize   每一页显示的条目数
         */
        public ListPage(List elements, int pageNumber, int pageSize)
        {
                this.elements = elements;
                this.pageSize = pageSize;
                this.pageNumber = pageNumber;
                if (Integer.MAX_VALUE == this.pageNumber || pageNumber > getLastPageNumber())
                {
                        this.pageNumber = getLastPageNumber();
                }
        }

        public boolean isFirstPage()
        {
                return getThisPageNumber() == 1;
        }

        public boolean isLastPage()
        {
                return getThisPageNumber() >= getLastPageNumber();
        }

        public boolean hasNextPage()
        {
                return getLastPageNumber() > getThisPageNumber();
        }

        public boolean hasPreviousPage()
        {
                return getThisPageNumber() > 1;
        }

        public int getLastPageNumber()
        {
                int totalElements = elements.size();
                return totalElements % this.pageSize == 0 ? totalElements / this.pageSize : totalElements / this.pageSize + 1;
        }

        /**
         * 返回List类型数据
         *
         * @return List数据源
         */
        public Object getThisPageElements()
        {
                return elements.subList(getThisPageFirstElementNumber() - 1, getThisPageLastElementNumber());
        }

        public int getTotalNumberOfElements()
        {
                return elements.size();
        }

        public int getThisPageFirstElementNumber()
        {
                return (getThisPageNumber() - 1) * getPageSize() + 1;
        }

        public int getThisPageLastElementNumber()
        {
                int fullPage = getThisPageFirstElementNumber() + getPageSize() - 1;
                return getTotalNumberOfElements() < fullPage ? getTotalNumberOfElements() : fullPage;
        }

        public int getNextPageNumber()
        {
                return getThisPageNumber() + 1;
        }

        public int getPreviousPageNumber()
        {
                return getThisPageNumber() - 1;
        }

        public int getPageSize()
        {
                return pageSize;
        }

        public int getThisPageNumber()
        {
                return pageNumber;
        }
}

