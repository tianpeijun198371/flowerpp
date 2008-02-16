package com.ulearning.ulms.content.schoolbook.util;

import org.apache.log4j.Logger;

import java.util.List;


public class PageWrapper
{
        private static final Logger log = Logger.getLogger(PageWrapper.class);
        private List list = null; // 保存查询数据的信息
        private long currentPage = 0; // 当前页数
        private long countNum = 0; // 每页记录信息的条数
        private long totalPage = 0; // 总页数
        private long totalRecord = 0; // 符合条件信息总条数
        private long prevPage = -1; // 上一页
        private long nextPage = -1; // 下一页
        private String prevPageUrl;
        private String nextPageUrl;
        private UrlProperty baseUrl;

        /**
         * 构造函数
         *
         * @param start       当前页数
         * @param count       每页记录信息的条数
         * @param totalRecord 符合条件信息总条数
         */
        public PageWrapper(long start, long count, long totalRecord)
        {
                this.currentPage = start;
                this.countNum = count;
                this.totalRecord = totalRecord;

                try
                {
                        caculate(); // 设置Page内部信息
                }
                catch (Exception e)
                {
                        log.error("PageWrapper Caculation Error !!" + e.getMessage());
                }
        }

        public UrlProperty getBaseUrl()
        {
                return baseUrl;
        }

        public void setBaseUrl(UrlProperty baseUrl)
        {
                this.baseUrl = baseUrl;
        }

        public String getNextPageUrl()
        {
                if (hasNextPage())
                {
                        this.baseUrl.put("p", String.valueOf(this.getNextPage()));
                        this.nextPageUrl = this.baseUrl.getUrl();
                }

                return (this.nextPageUrl == null) ? "" : this.nextPageUrl;
        }

        public String getPrevPageUrl()
        {
                if (hasPrevPage())
                {
                        this.baseUrl.put("p", String.valueOf(this.getPrevPage()));
                        this.prevPageUrl = this.baseUrl.getUrl();
                }

                return (this.prevPageUrl == null) ? "" : this.prevPageUrl;
        }

        public static PageWrapper getPage(String index, int countPerPage, int total)
        {
                int currentPage = ((index == null) || index.equals("null")) ? 1
                        : Integer.parseInt(index);

                return new PageWrapper(currentPage, countPerPage, total);
        }

        public static PageWrapper getPage(int index, int countPerPage, int total)
        {
                return new PageWrapper(index, countPerPage, total);
        }

        private void caculate() throws Exception
        {
                // currentPage,countNum,totalRecord都不能小于"1"!
                if ((this.currentPage < 1) || (this.countNum < 1) ||
                        (this.totalRecord < 1))
                {
                        this.currentPage = 0;
                        this.countNum = 0;
                        this.totalRecord = 0;
                        throw new Exception(
                                "currentPage||countNum||totalRecord < 1 Error !!");
                }

                // 计算总页数
                this.totalPage = ((this.totalRecord + this.countNum) - 1) / this.countNum;

                // currentPage不能大于totalPage!
                long tempCurrentPage = -1;

                if (this.currentPage > this.totalPage)
                {
                        tempCurrentPage = this.currentPage;
                        this.currentPage = this.totalPage;
                }

                // 计算上一页
                if (tempCurrentPage != -1)
                {
                        this.prevPage = ((tempCurrentPage - 1) > 0) ? (tempCurrentPage - 1)
                                : (-1);
                }
                else
                {
                        this.prevPage = ((this.currentPage - 1) > 0) ? (this.currentPage -
                                1) : (-1);
                }

                // 计算下一页
                this.nextPage = ((this.currentPage + 1) <= this.totalPage)
                        ? (this.currentPage + 1) : (-1);
        }

        /**
         * test method
         *
         * @param page
         */
        public String toString()
        {
                StringBuffer sb = new StringBuffer();
                sb.append(
                        "\r\n------------------------------------------------------------");
                sb.append("\r\n  currentPage=" + getCurrentPage());
                sb.append("\r\n   countNum=" + getCountNum());
                sb.append("\r\n  totalPage=" + getTotalPage());
                sb.append("\r\ntotalRecord=" + getTotalRecord());
                sb.append("\r\n   prevPage=" + getPrevPage());
                sb.append("\r\nprevPageUrl=" + getPrevPageUrl());
                sb.append("\r\n   nextPage=" + getNextPage());
                sb.append("\r\nnextPageUrl=" + getNextPageUrl());
                sb.append("\r\n    baseUrl=" + getBaseUrl().getBaseUrl());
                sb.append(
                        "\r\n------------------------------------------------------------");

                return sb.toString();
        }

        /**
         * 判断是否有上一页
         *
         * @return 有上一页就返回true,否则返回false
         */
        public boolean hasPrevPage()
        {
                return (this.prevPage == -1) ? false : true;
        }

        /**
         * 判断是否有下一页
         *
         * @return 有下一页就返回true,否则返回false
         */
        public boolean hasNextPage()
        {
                return (this.nextPage == -1) ? false : true;
        }

        /**
         * 返回Page中储存的数据List
         *
         * @return Returns 一个储存数据的List.
         */
        public List getList()
        {
                return list;
        }

        /**
         * 设置Page中储存的数据List
         *
         * @param list The list to set.
         */
        public void setList(List list)
        {
                this.list = list;
        }

        /**
         * 返回每页显示记录条数.
         *
         * @return Returns 每页记录条数.
         */
        public long getCountNum()
        {
                return countNum;
        }

        /**
         * 获取下一页
         *
         * @return Returns 下一页数.
         */
        public long getNextPage()
        {
                return nextPage;
        }

        /**
         * 获取上一页
         *
         * @return Returns 上一页数.
         */
        public long getPrevPage()
        {
                return prevPage;
        }

        /**
         * 获取当前页数
         *
         * @return Returns 当前页数.
         */
        public long getCurrentPage()
        {
                return currentPage;
        }

        /**
         * 获取总页数
         *
         * @return Returns 总页数.
         */
        public long getTotalPage()
        {
                return totalPage;
        }

        /**
         * 获取总记录条数
         *
         * @return Returns 总记录条数.
         */
        public long getTotalRecord()
        {
                return totalRecord;
        }
}
