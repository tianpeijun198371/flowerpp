/**
 * FluxUtil.java.
 * User: fengch  Date: 2004-6-23
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.admin.fluxManage.util;

import com.ulearning.ulms.core.util.StringUtil;

import java.text.DecimalFormat;

import java.util.List;


public class FluxUtil
{
        /**
         * 返回字节属性,<br>形如4234,返回"4,2k";
         *
         * @param b
         * @return
         */
        public static String format(long b)
        {
                long SIZE_K = 1024;
                long SIZE_M = SIZE_K * SIZE_K;
                long SIZE_G = SIZE_K * SIZE_K * SIZE_K;
                DecimalFormat nf = new DecimalFormat("##########.#");
                String result = null;

                if (b >= SIZE_G)
                {
                        result = nf.format((double) b / SIZE_G) + " GB";
                }
                else if (b >= SIZE_M)
                {
                        result = nf.format((double) b / SIZE_M) + " MB";
                }
                else if (b >= SIZE_K)
                {
                        result = nf.format((double) b / SIZE_K) + " KB";
                }
                else
                {
                        result = String.valueOf(b);
                }

                return result;
        }

        /**
         * 返回页面图片bar的序号,如bar1.gif.
         *
         * @return
         */
        public static int random()
        {
                int total_bar = 10;

                return ((new Double(Math.random() * total_bar)).intValue() + 1);
        }

        /**
         * 返回链接地址指向的文件名称.
         *
         * @param url
         * @return
         */
        public static String getURLResourceName(String url)
        {
                String str = null;

                try
                {
                        List l = StringUtil.split(url, "/");

                        if (l != null)
                        {
                                str = (String) l.get(l.size() - 1);
                        }
                }
                catch (Exception ex)
                {
                }

                return str;
        }
}
