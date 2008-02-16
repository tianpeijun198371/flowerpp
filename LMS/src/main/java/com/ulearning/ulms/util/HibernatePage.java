/*
 * Copyright (c) 2005 Your Corporation. All Rights Reserved.
 */
package com.ulearning.ulms.util;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.ScrollableResults;


import java.util.List;

/**
 * Hibernate��ҳ��Ϣ
 */
public class HibernatePage implements Page
{
        private List elements;
        private int pageSize;
        private int pageNumber;
        private int totalElements = 0;

        /**
         * ����HibernatePage�������Hibernate��Query���ݵķ�ҳ����
         *
         * @param query      Hibernate��Query����
         * @param pageNumber ��ǰҳ���룬��1��ʼ���������ֵΪInteger.MAX_VALUE��ʾ��ȡ���һҳ��
         *                   ����㲻֪�����һҳ���룬��Integer.MAX_VALUE���ɡ������ǰҳ������ҳ����Ҳ��ʾ���һҳ��
         *                   ��������������¸��ĵ�ǰҳ��ҳ�룬Ϊ���һҳ���롣
         * @param pageSize   ÿһҳ��ʾ����Ŀ��
         */
        public HibernatePage(Query query, int pageNumber, int pageSize)
        {
                this.pageNumber = pageNumber;
                this.pageSize = pageSize;
                try
                {
                        ScrollableResults scrollableResults = query.scroll();
                        //get the total elements number
                        scrollableResults.last();
                        this.totalElements = scrollableResults.getRowNumber();
                        if (Integer.MAX_VALUE == this.pageNumber || this.pageNumber > getLastPageNumber())   //last page
                        {
                                this.pageNumber = getLastPageNumber();
                        }
                        elements = query.setFirstResult((this.pageNumber - 1) * this.pageSize).setMaxResults(this.pageSize + 1).list();
                }
                catch (HibernateException e)
                {
                        throw new RuntimeException(e);
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
                return totalElements % this.pageSize == 0 ? totalElements / this.pageSize : totalElements / this.pageSize + 1;
        }

        /**
         * ����List��������
         *
         * @return List����Դ
         */
        public Object getThisPageElements()
        {
                return elements;
        }

        public int getTotalNumberOfElements()
        {
                return totalElements;
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
