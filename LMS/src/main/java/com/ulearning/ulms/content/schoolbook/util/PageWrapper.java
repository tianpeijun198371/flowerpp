package com.ulearning.ulms.content.schoolbook.util;

import org.apache.log4j.Logger;

import java.util.List;


public class PageWrapper
{
        private static final Logger log = Logger.getLogger(PageWrapper.class);
        private List list = null; // �����ѯ���ݵ���Ϣ
        private long currentPage = 0; // ��ǰҳ��
        private long countNum = 0; // ÿҳ��¼��Ϣ������
        private long totalPage = 0; // ��ҳ��
        private long totalRecord = 0; // ����������Ϣ������
        private long prevPage = -1; // ��һҳ
        private long nextPage = -1; // ��һҳ
        private String prevPageUrl;
        private String nextPageUrl;
        private UrlProperty baseUrl;

        /**
         * ���캯��
         *
         * @param start       ��ǰҳ��
         * @param count       ÿҳ��¼��Ϣ������
         * @param totalRecord ����������Ϣ������
         */
        public PageWrapper(long start, long count, long totalRecord)
        {
                this.currentPage = start;
                this.countNum = count;
                this.totalRecord = totalRecord;

                try
                {
                        caculate(); // ����Page�ڲ���Ϣ
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
                // currentPage,countNum,totalRecord������С��"1"!
                if ((this.currentPage < 1) || (this.countNum < 1) ||
                        (this.totalRecord < 1))
                {
                        this.currentPage = 0;
                        this.countNum = 0;
                        this.totalRecord = 0;
                        throw new Exception(
                                "currentPage||countNum||totalRecord < 1 Error !!");
                }

                // ������ҳ��
                this.totalPage = ((this.totalRecord + this.countNum) - 1) / this.countNum;

                // currentPage���ܴ���totalPage!
                long tempCurrentPage = -1;

                if (this.currentPage > this.totalPage)
                {
                        tempCurrentPage = this.currentPage;
                        this.currentPage = this.totalPage;
                }

                // ������һҳ
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

                // ������һҳ
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
         * �ж��Ƿ�����һҳ
         *
         * @return ����һҳ�ͷ���true,���򷵻�false
         */
        public boolean hasPrevPage()
        {
                return (this.prevPage == -1) ? false : true;
        }

        /**
         * �ж��Ƿ�����һҳ
         *
         * @return ����һҳ�ͷ���true,���򷵻�false
         */
        public boolean hasNextPage()
        {
                return (this.nextPage == -1) ? false : true;
        }

        /**
         * ����Page�д��������List
         *
         * @return Returns һ���������ݵ�List.
         */
        public List getList()
        {
                return list;
        }

        /**
         * ����Page�д��������List
         *
         * @param list The list to set.
         */
        public void setList(List list)
        {
                this.list = list;
        }

        /**
         * ����ÿҳ��ʾ��¼����.
         *
         * @return Returns ÿҳ��¼����.
         */
        public long getCountNum()
        {
                return countNum;
        }

        /**
         * ��ȡ��һҳ
         *
         * @return Returns ��һҳ��.
         */
        public long getNextPage()
        {
                return nextPage;
        }

        /**
         * ��ȡ��һҳ
         *
         * @return Returns ��һҳ��.
         */
        public long getPrevPage()
        {
                return prevPage;
        }

        /**
         * ��ȡ��ǰҳ��
         *
         * @return Returns ��ǰҳ��.
         */
        public long getCurrentPage()
        {
                return currentPage;
        }

        /**
         * ��ȡ��ҳ��
         *
         * @return Returns ��ҳ��.
         */
        public long getTotalPage()
        {
                return totalPage;
        }

        /**
         * ��ȡ�ܼ�¼����
         *
         * @return Returns �ܼ�¼����.
         */
        public long getTotalRecord()
        {
                return totalRecord;
        }
}
