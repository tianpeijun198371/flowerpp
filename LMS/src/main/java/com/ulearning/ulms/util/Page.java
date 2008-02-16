/*
 * Copyright (c) 2005 Your Corporation. All Rights Reserved.
 */
package com.ulearning.ulms.util;


/**
 * ��ҳ��Ϣ�ӿ�
 */
public interface Page
{
        /**
         * �Ƿ�����ҳ����һҳ������һҳҳ��Ϊ1
         *
         * @return ��ҳ��ʶ
         */
        public boolean isFirstPage();

        /**
         * �Ƿ������һҳ
         *
         * @return ĩҳ��ʶ
         */
        public boolean isLastPage();

        /**
         * �Ƿ�����һҳ
         *
         * @return ��һҳ��ʶ
         */
        public boolean hasNextPage();

        /**
         * �Ƿ�����һҳ
         *
         * @return ��һҳ��ʶ
         */
        public boolean hasPreviousPage();

        /**
         * ��ȡ���һҳҳ�룬Ҳ������ҳ��
         *
         * @return ���һҳҳ��
         */
        public int getLastPageNumber();

        /**
         * ��ǰҳ���������ݣ���ͬ��������ܷ��ص��������Ͳ�һ������List��RowSet�ȣ���ο������ʵ��
         *
         * @return ��ǰҳ����Դ
         */
        public Object getThisPageElements();

        /**
         * �ܵ�������Ŀ������0��ʾû������
         *
         * @return ������
         */
        public int getTotalNumberOfElements();

        /**
         * ��ȡ��ǰҳ���������ݵ��б���
         *
         * @return ��ǰҳ���������ݵ��б���
         */
        public int getThisPageFirstElementNumber();

        /**
         * ��ȡ��ǰҳ��ĩ�����ݵ��б���
         *
         * @return ��ǰҳ��ĩ�����ݵ��б���
         */
        public int getThisPageLastElementNumber();

        /**
         * ��ȡ��һҳ����
         *
         * @return ��һҳ����
         */
        public int getNextPageNumber();

        /**
         * ��ȡ��һҳ����
         *
         * @return ��һҳ����
         */
        public int getPreviousPageNumber();

        /**
         * ÿһҳ��ʾ����Ŀ��
         *
         * @return ÿһҳ��ʾ����Ŀ��
         */
        public int getPageSize();

        /**
         * ��ǰҳ��ҳ��
         *
         * @return ��ǰҳ��ҳ��
         */
        public int getThisPageNumber();

}
