/**
 * PagerUtil.java.
 * User: fengch Date: 2005-6-20 9:14:56
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.core.util;


/**
 * 封装了一些分页处理相关方法的使用类。
 */
public class PagerUtil
{
        /**
         * 返回分页后的总页数.
         *
         * @param totalCount 总记录数
         * @param pageSize   每页的容量
         * @return 分页后的总页数
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
