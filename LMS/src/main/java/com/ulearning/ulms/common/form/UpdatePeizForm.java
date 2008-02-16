/**
 * Created by IntelliJ IDEA.
 * Book: dengj
 * Date: Apr 7, 2004
 * Time: 4:51:49 PM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.common.form;

import com.ulearning.ulms.common.model.CodeItem;
import com.ulearning.ulms.core.util.StringUtil;

import org.apache.struts.action.ActionForm;

import java.util.Date;


public class UpdatePeizForm extends ActionForm
{
        private int codeitemid;
        private int CodeID = 0;
        private int Type = 0;
        private String Name = null;
        private String Value = null;
        private int Orderindex = 0;
        private String Comments = null;
        private String Description = null;

        public CodeItem getCodeItem() //CodeItem is model
        {
                CodeItem codeItem = new CodeItem();
                codeItem.setCodeitemid(this.codeitemid);
                codeItem.setCodeid(this.CodeID);
                codeItem.setType1(this.Type);
                codeItem.setName1(this.Name);
                codeItem.setValue1(this.Value);
                codeItem.setOrderindex(this.Orderindex);
                codeItem.setComments(this.Comments);
                codeItem.setDescription(this.Description);

                return codeItem;
        }

        public UpdatePeizForm getUpdatePeizForm(CodeItem codeItem)
        {
                UpdatePeizForm updatepeizForm = new UpdatePeizForm();
                updatepeizForm.setCodeitemid(codeItem.getCodeitemid());
                updatepeizForm.setCodeid(codeItem.getCodeid());
                updatepeizForm.setType1(codeItem.getType1());
                updatepeizForm.setName1(codeItem.getName1());
                updatepeizForm.setValue1(codeItem.getValue1());
                updatepeizForm.setOrderindex(codeItem.getOrderindex());
                updatepeizForm.setComments(codeItem.getComments());
                updatepeizForm.setDescription(codeItem.getDescription());

                return updatepeizForm;
        }

        public int getCodeitemid()
        {
                return this.codeitemid;
        }

        public void setCodeitemid(int codeitemid)
        {
                this.codeitemid = codeitemid;
        }

        public int getCodeid()
        {
                return CodeID;
        }

        public void setCodeid(int Codeid)
        {
                this.CodeID = Codeid;
        }

        public int getType1()
        {
                return Type;
        }

        public void setType1(int Type)
        {
                this.Type = Type;
        }

        public String getName1()
        {
                return Name;
        }

        public void setName1(String Name)
        {
                this.Name = Name;
        }

        public String getValue1()
        {
                return Value;
        }

        public void setValue1(String Value)
        {
                this.Value = Value;
        }

        public int getOrderindex()
        {
                return Orderindex;
        }

        public void setOrderindex(int Orderindex)
        {
                this.Orderindex = Orderindex;
        }

        public String getComments()
        {
                return Comments;
        }

        public void setComments(String Comments)
        {
                this.Comments = Comments;
        }

        public String getDescription()
        {
                return Description;
        }

        public void setDescription(String Description)
        {
                this.Description = Description;
        }
}
