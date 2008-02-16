package com.ulearning.ulms.tools.doc.doccontent.model;

import com.ulearning.ulms.tools.doc.document.model.DocumentModel;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;


/**
 * @author Hibernate CodeGenerator
 */
public class DocContentModel implements Serializable
{
        /**
         * identifier field
         */
        private int docID;

        /**
         * nullable persistent field
         */
        private String docContent;

        /**
         * nullable persistent field
         */
        private String docLinkTitle;

        /**
         * nullable persistent field
         */
        private String docLink;

        /**
         * full constructor
         */
        public DocContentModel(String docContent, String docLinkTitle,
                               String docLink)
        {
                this.docContent = docContent;
                this.docLinkTitle = docLinkTitle;
                this.docLink = docLink;
        }

        /**
         * default constructor
         */
        public DocContentModel()
        {
        }

        public int getDocID()
        {
                return this.docID;
        }

        public void setDocID(int docID)
        {
                this.docID = docID;
        }

        public String getDocContent()
        {
                return this.docContent;
        }

        public void setDocContent(String docContent)
        {
                this.docContent = docContent;
        }

        public String getDocLinkTitle()
        {
                return this.docLinkTitle;
        }

        public void setDocLinkTitle(String docLinkTitle)
        {
                this.docLinkTitle = docLinkTitle;
        }

        public String getDocLink()
        {
                return this.docLink;
        }

        public void setDocLink(String docLink)
        {
                this.docLink = docLink;
        }

        public String toString()
        {
                return new ToStringBuilder(this).append("docID", getDocID()).toString();
        }
}
