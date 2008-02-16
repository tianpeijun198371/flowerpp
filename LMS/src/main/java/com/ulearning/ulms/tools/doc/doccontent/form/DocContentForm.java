/**
 * Created by IntelliJ IDEA.
 * DocContent: dengj
 * Date: Apr 7, 2004
 * Time: 4:51:49 PM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.tools.doc.doccontent.form;

import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.tools.doc.doccontent.model.DocContentModel;

import org.apache.struts.action.ActionForm;

import java.sql.Date;


public class DocContentForm extends ActionForm
{
        private int docID = 0;
        private String docContent = null;
        private String docLinkTitle = null;
        private String docLink = null;
        private DocContentModel docContentModel = null;

        public DocContentModel getDocContentModel()
        {
                docContentModel = new DocContentModel();
                docContentModel.setDocID(this.docID);
                docContentModel.setDocContent(this.docContent);
                docContentModel.setDocLinkTitle(this.docLinkTitle);
                docContentModel.setDocLink(this.docLink);

                return docContentModel;
        }

        public DocContentForm getDocContentForm(DocContentModel docContentModel)
        {
                DocContentForm docContentForm = new DocContentForm();
                docContentForm.setDocID(docContentModel.getDocID());
                docContentForm.setDocContent(StringUtil.nullToStr(
                        docContentModel.getDocContent()));
                docContentForm.setDocLinkTitle(StringUtil.nullToStr(
                        docContentModel.getDocLinkTitle()));
                docContentForm.setDocLink(StringUtil.nullToStr(
                        docContentModel.getDocLink()));

                return docContentForm;
        }

        public int getDocID()
        {
                return docID;
        }

        public void setDocID(int docID)
        {
                this.docID = docID;
        }

        public String getDocContent()
        {
                return docContent;
        }

        public void setDocContent(String docContent)
        {
                this.docContent = docContent;
        }

        public String getDocLinkTitle()
        {
                return docLinkTitle;
        }

        public void setDocLinkTitle(String docLinkTitle)
        {
                this.docLinkTitle = docLinkTitle;
        }

        public String getDocLink()
        {
                return docLink;
        }

        public void setDocLink(String docLink)
        {
                this.docLink = docLink;
        }
}
