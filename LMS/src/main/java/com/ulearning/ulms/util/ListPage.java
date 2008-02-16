/*
 * Copyright (c) 2005 Your Corporation. All Rights Reserved.
 */
package com.ulearning.ulms.util;

import java.util.List;

/**
 * ����List�ķ�ҳ
 */
public class ListPage implements Page
{
        private List elements;
        private int pageSize;
        private int pageNumber;

        /**
         * ����ListPage�������List���ݵķ�ҳ����
         *
         * @param elements   List����Դ
         * @param pageNumber ��ǰҳ���룬��1��ʼ���������ֵΪInteger.MAX_VALUE��ʾ��ȡ���һҳ��
         *                   ����㲻֪�����һҳ���룬��Integer.MAX_VALUE���ɡ������ǰҳ������ҳ����Ҳ��ʾ���һҳ��
         *                   ��������������¸��ĵ�ǰҳ��ҳ�룬Ϊ���һҳ���롣
         * @param pageSize   ÿһҳ��ʾ����Ŀ��
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
         * ����List��������
         *
         * @return List����Դ
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

