/**
 * PagerUtil.java.
 * User: fengch Date: 2005-6-20 9:14:56
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.core.util;


/**
 * ��װ��һЩ��ҳ������ط�����ʹ���ࡣ
 */
public class PagerUtil
{
        /**
         * ���ط�ҳ�����ҳ��.
         *
         * @param totalCount �ܼ�¼��
         * @param pageSize   ÿҳ������
         * @return ��ҳ�����ҳ��
         */
        public static int getPageCount(int totalCount, int pageSize)
        {
                int pageCount = 0;

                if (pageSize == 0)
                {
                        return 0;
                }

                if ((totalCount % pageSize) == 0)
                {
                        pageCount = totalCount / pageSize;
                }
                else
                {
                        pageCount = (totalCount / pageSize) + 1;
                }

                return pageCount;
        }
}
