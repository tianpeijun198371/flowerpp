package com.ulearning.ulms.tools.message.form;

import com.ulearning.ulms.tools.message.model.MessageUserModel;
import com.ulearning.ulms.tools.message.model.MessageUserModelPK;

import org.apache.struts.action.ActionForm;


/**
 * Created by IntelliJ IDEA.
 * User: keyh
 * Date: 2004-7-26
 * Time: 9:40:19
 * To change this template use File | Settings | File Templates.
 */
public class MessageUserForm extends ActionForm
{
        private int messageID;
        private int userID;
        private int type;

        public MessageUserForm(int messageID, int userID, int type)
        {
                this.messageID = messageID;
                this.userID = userID;
                this.type = type;
        }

        public MessageUserForm()
        {
        }

        public MessageUserModel getMessageUserModel()
        {
                MessageUserModel mum = new MessageUserModel();
                MessageUserModelPK mumPK = new MessageUserModelPK();
                mumPK.setUserid(this.userID);
                mumPK.setMessageid(this.messageID);
                mum.setComp_id(mumPK);
                mum.setType(this.type);

                return mum;
        }

        public MessageUserForm getMessageUserForm(MessageUserModel mum)
        {
                MessageUserForm muf = new MessageUserForm();
                muf.setUserID(mum.getComp_id().getUserid());
                muf.setMessageID(mum.getComp_id().getMessageid());
                muf.setType(mum.getType());

                return muf;
        }

        public int getUserID()
        {
                return userID;
        }

        public void setUserID(int userID)
        {
                this.userID = userID;
        }

        public int getMessageID()
        {
                return messageID;
        }

        public void setMessageID(int messageID)
        {
                this.messageID = messageID;
        }

        public int getType()
        {
                return type;
        }

        public void setType(int type)
        {
                this.type = type;
        }
}
