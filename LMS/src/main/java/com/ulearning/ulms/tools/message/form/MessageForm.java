package com.ulearning.ulms.tools.message.form;

import com.ulearning.ulms.tools.message.model.MessageModel;

import org.apache.struts.action.ActionForm;

import java.util.Date;


/**
 * Created by IntelliJ IDEA.
 * User: keyh
 * Date: 2004-7-23
 * Time: 16:18:26
 * To change this template use File | Settings | File Templates.
 */
public class MessageForm extends ActionForm
{
        private int messageID;
        private int senderID;
        private String senderName;
        private String recieverName;
        private Date sendTime;
        private String title;
        private String content;
        private int isPopUpMessage;
        private int isSave;
        private int type;
        private String description;
        private boolean read;

        public MessageForm(int messageID, int senderID, String senderName,
                           String recieverName, Date sendTime, String title, String content,
                           int popUpMessage, int save, int type, String description, boolean read)
        {
                this.messageID = messageID;
                this.senderID = senderID;
                this.senderName = senderName;
                this.recieverName = recieverName;
                this.sendTime = sendTime;
                this.title = title;
                this.content = content;
                isPopUpMessage = popUpMessage;
                isSave = save;
                this.type = type;
                this.description = description;
                this.read = read;
        }

        public MessageForm()
        {
        }

        public MessageModel getMessageModel()
        {
                MessageModel mm = new MessageModel();
                mm.setMessageid(this.messageID);
                mm.setSenduserid(this.senderID);
                mm.setReciever(this.recieverName);
                mm.setSendtime(this.sendTime);
                mm.setTitle(this.title);
                mm.setContent(this.content);
                mm.setIspopupmessage(this.isPopUpMessage);
                mm.setIssave(this.isSave);
                mm.setType(this.type);
                mm.setDescription(this.description);

                return mm;
        }

        public MessageForm getMessageForm(MessageModel mm)
        {
                MessageForm mf = new MessageForm();
                mf.setMessageID(mm.getMessageid());
                mf.setSenderID(mm.getSenduserid());
                mf.setRecieverName(mm.getReciever());
                mf.setSendTime(mm.getSendtime());
                mf.setTitle(mm.getTitle());
                mf.setContent(mm.getContent());
                mf.setIsPopUpMessage(mm.getIspopupmessage());
                mf.setIsSave(mm.getIssave());
                mf.setType(mm.getType());
                mf.setDescription(mm.getDescription());

                return mf;
        }

        public String getRecieverName()
        {
                return recieverName;
        }

        public void setRecieverName(String recieverName)
        {
                this.recieverName = recieverName;
        }

        public String getSenderName()
        {
                return senderName;
        }

        public void setSenderName(String senderName)
        {
                this.senderName = senderName;
        }

        public int getMessageID()
        {
                return messageID;
        }

        public void setMessageID(int messageID)
        {
                this.messageID = messageID;
        }

        public int getSenderID()
        {
                return senderID;
        }

        public void setSenderID(int sendUserID)
        {
                this.senderID = sendUserID;
        }

        public String getTitle()
        {
                return title;
        }

        public void setTitle(String title)
        {
                this.title = title;
        }

        public String getContent()
        {
                return content;
        }

        public void setContent(String content)
        {
                this.content = content;
        }

        public int getIsPopUpMessage()
        {
                return isPopUpMessage;
        }

        public void setIsPopUpMessage(int popUpMessage)
        {
                isPopUpMessage = popUpMessage;
        }

        public int getIsSave()
        {
                return isSave;
        }

        public void setIsSave(int issave)
        {
                isSave = issave;
        }

        public int getType()
        {
                return type;
        }

        public void setType(int type)
        {
                this.type = type;
        }

        public String getDescription()
        {
                return description;
        }

        public void setDescription(String description)
        {
                this.description = description;
        }

        public Date getSendTime()
        {
                return sendTime;
        }

        public void setSendTime(Date sendTime)
        {
                this.sendTime = sendTime;
        }

        public boolean isRead()
        {
                return read;
        }

        public void setRead(boolean read)
        {
                this.read = read;
        }
}
