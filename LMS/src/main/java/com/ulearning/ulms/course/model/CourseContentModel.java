package com.ulearning.ulms.course.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

import java.util.Date;
import java.util.Set;


/**
 * @author Hibernate CodeGenerator
 */
public class CourseContentModel implements Serializable
{
        /**
         * identifier field
         */
        private int nodeid;

        /**
         * persistent field
         */
        private String nodetitle;

        /**
         * persistent field
         */
        private int relationid;

        /**
         * nullable persistent field
         */
        private String type;

        /**
         * nullable persistent field
         */
        private String nodetype;

        /**
         * nullable persistent field
         */
        private String isuserful;

        /**
         * nullable persistent field
         */
        private String isopenguest;

        /**
         * nullable persistent field
         */
        private String iscontent;

        /**
         * nullable persistent field
         */
        private String link;

        /**
         * nullable persistent field
         */
        private String ishyperlink;

        /**
         * nullable persistent field
         */
        private String isview;

        /**
         * nullable persistent field
         */
        private int depth;

        /**
         * nullable persistent field
         */
        private int parentid;

        /**
         * nullable persistent field
         */
        private Date createdate;

        /**
         * nullable persistent field
         */
        private Date lastmoddate;

        /**
         * nullable persistent field
         */
        private int orderindex;

        /**
         * nullable persistent field
         */
        private String catalog;

        /**
         * persistent field
         */
        private Set cuserscoTabs;

        /**
         * persistent field
         */
        private Set citemTabs;

        /**
         * full constructor
         */
        public CourseContentModel(String nodetitle, int relationid, String type,
                                  String nodetype, String isuserful, String isopenguest,
                                  String iscontent, String link, String ishyperlink, String isview,
                                  int depth, int parentid, Date createdate, Date lastmoddate,
                                  int orderindex, String catalog, Set cuserscoTabs, Set citemTabs)
        {
                this.nodetitle = nodetitle;
                this.relationid = relationid;
                this.type = type;
                this.nodetype = nodetype;
                this.isuserful = isuserful;
                this.isopenguest = isopenguest;
                this.iscontent = iscontent;
                this.link = link;
                this.ishyperlink = ishyperlink;
                this.isview = isview;
                this.depth = depth;
                this.parentid = parentid;
                this.createdate = createdate;
                this.lastmoddate = lastmoddate;
                this.orderindex = orderindex;
                this.catalog = catalog;
                this.cuserscoTabs = cuserscoTabs;
                this.citemTabs = citemTabs;
        }

        /**
         * default constructor
         */
        public CourseContentModel()
        {
        }

        /**
         * minimal constructor
         */
        public CourseContentModel(String nodetitle, int relationid,
                                  Set cuserscoTabs, Set citemTabs)
        {
                this.nodetitle = nodetitle;
                this.relationid = relationid;
                this.cuserscoTabs = cuserscoTabs;
                this.citemTabs = citemTabs;
        }

        public int getNodeid()
        {
                return this.nodeid;
        }

        public void setNodeid(int nodeid)
        {
                this.nodeid = nodeid;
        }

        public String getNodetitle()
        {
                return this.nodetitle;
        }

        public void setNodetitle(String nodetitle)
        {
                this.nodetitle = nodetitle;
        }

        public int getRelationid()
        {
                return this.relationid;
        }

        public void setRelationid(int relationid)
        {
                this.relationid = relationid;
        }

        public String getType()
        {
                return this.type;
        }

        public void setType(String type)
        {
                this.type = type;
        }

        public String getNodetype()
        {
                return this.nodetype;
        }

        public void setNodetype(String nodetype)
        {
                this.nodetype = nodetype;
        }

        public String getIsuserful()
        {
                return this.isuserful;
        }

        public void setIsuserful(String isuserful)
        {
                this.isuserful = isuserful;
        }

        public String getIsopenguest()
        {
                return this.isopenguest;
        }

        public void setIsopenguest(String isopenguest)
        {
                this.isopenguest = isopenguest;
        }

        public String getIscontent()
        {
                return this.iscontent;
        }

        public void setIscontent(String iscontent)
        {
                this.iscontent = iscontent;
        }

        public String getLink()
        {
                return this.link;
        }

        public void setLink(String link)
        {
                this.link = link;
        }

        public String getIshyperlink()
        {
                return this.ishyperlink;
        }

        public void setIshyperlink(String ishyperlink)
        {
                this.ishyperlink = ishyperlink;
        }

        public String getIsview()
        {
                return this.isview;
        }

        public void setIsview(String isview)
        {
                this.isview = isview;
        }

        public int getDepth()
        {
                return this.depth;
        }

        public void setDepth(int depth)
        {
                this.depth = depth;
        }

        public int getParentid()
        {
                return this.parentid;
        }

        public void setParentid(int parentid)
        {
                this.parentid = parentid;
        }

        public Date getCreatedate()
        {
                return this.createdate;
        }

        public void setCreatedate(Date createdate)
        {
                this.createdate = createdate;
        }

        public Date getLastmoddate()
        {
                return this.lastmoddate;
        }

        public void setLastmoddate(Date lastmoddate)
        {
                this.lastmoddate = lastmoddate;
        }

        public int getOrderindex()
        {
                return this.orderindex;
        }

        public void setOrderindex(int orderindex)
        {
                this.orderindex = orderindex;
        }

        public String getCatalog()
        {
                return this.catalog;
        }

        public void setCatalog(String catalog)
        {
                this.catalog = catalog;
        }

        public Set getCuserscoTabs()
        {
                return this.cuserscoTabs;
        }

        public void setCuserscoTabs(Set cuserscoTabs)
        {
                this.cuserscoTabs = cuserscoTabs;
        }

        public Set getCitemTabs()
        {
                return this.citemTabs;
        }

        public void setCitemTabs(Set citemTabs)
        {
                this.citemTabs = citemTabs;
        }

        public String toString()
        {
                return new ToStringBuilder(this).append("nodeid", getNodeid()).toString();
        }
}
