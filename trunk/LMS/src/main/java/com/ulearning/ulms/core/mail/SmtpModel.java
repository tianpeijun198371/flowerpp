/**
 * SmtpModel.java.
 * User: fengch  Date: 2004-7-1
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.core.mail;

import com.ulearning.ulms.tools.upload.model.UploadForm;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;


//   加上extends UploadForm 是为了使用actionForm和文件上传  －－谢建华
public class SmtpModel extends UploadForm //implements Serializable
{
        private String sendFrom;
        private List sendTo;
        private List sendCc;
        private List sendBcc;
        private String subject;
        private String body;
        private String attachment = "";
        private String sendType;
        private String contentType;
        private String host;
        private int port = 25;
        private String user;
        private String password;

        public SmtpModel() //add by xiejh
        {
        }

        public SmtpModel(String sendFrom, List sendTo, List sendCc, List sendBcc,
                         String subject, String body, String attachment, String sendType,
                         String contentType, String host, int port, String user, String password)
        {
                this.sendFrom = sendFrom;
                this.sendTo = sendTo;
                this.sendCc = sendCc;
                this.sendBcc = sendBcc;
                this.subject = subject;
                this.body = body;
                this.attachment = attachment;
                this.sendType = sendType;
                this.contentType = contentType;
                this.host = host;
                this.port = port;
                this.user = user;
                this.password = password;
        }

        public SmtpModel(String sendFrom, List sendTo, List sendCc, List sendBcc,
                         String subject, String body, String attachment)
        {
                this.sendFrom = sendFrom;
                this.sendTo = sendTo;
                this.sendCc = sendCc;
                this.sendBcc = sendBcc;
                this.subject = subject;
                this.body = body;
                this.attachment = attachment;
        }

        public SmtpModel(String sendFrom, List sendTo, String subject, String body,
                         String attachment)
        {
                this.sendFrom = sendFrom;
                this.sendTo = sendTo;
                this.sendCc = new ArrayList();
                this.sendBcc = new ArrayList();
                this.subject = subject;
                this.body = body;
                this.attachment = attachment;
        }

        public SmtpModel(List sendTo, String subject, String body, String attachment)
        {
                this.sendTo = sendTo;
                this.sendCc = new ArrayList();
                this.sendBcc = new ArrayList();
                this.subject = subject;
                this.body = body;
                this.attachment = attachment;
        }

        public SmtpModel(List sendTo, String subject, String body)
        {
                this.sendTo = sendTo;
                this.sendCc = new ArrayList();
                this.sendBcc = new ArrayList();
                this.subject = subject;
                this.body = body;
        }

        public SmtpModel(String sendFrom, String sendTo, String subject,
                         String body, String attachment)
        {
                List sendTos = new ArrayList();
                sendTos.add(sendTo);
                this.sendFrom = sendFrom;
                this.sendCc = new ArrayList();
                this.sendBcc = new ArrayList();
                this.sendTo = sendTos;
                this.subject = subject;
                this.body = body;
                this.attachment = attachment;
        }

        public SmtpModel(String sendFrom, String sendTo, String subject, String body)
        {
                List sendTos = new ArrayList();
                sendTos.add(sendTo);
                this.sendFrom = sendFrom;
                this.sendTo = sendTos;
                this.sendCc = new ArrayList();
                this.sendBcc = new ArrayList();
                this.subject = subject;
                this.body = body;
        }

        public SmtpModel(String sendTo, String subject, String body)
        {
                List sendTos = new ArrayList();
                sendTos.add(sendTo);
                this.sendTo = sendTos;
                this.sendCc = new ArrayList();
                this.sendBcc = new ArrayList();
                this.subject = subject;
                this.body = body;
        }

        public String getSendFrom()
        {
                return sendFrom;
        }

        public void setSendFrom(String sendFrom)
        {
                this.sendFrom = sendFrom;
        }

        public List getSendTo()
        {
                return sendTo;
        }

        public void setSendTo(List sendTo)
        {
                this.sendTo = sendTo;
        }

        public List getSendCc()
        {
                return sendCc;
        }

        public void setSendCc(List sendCc)
        {
                this.sendCc = sendCc;
        }

        public List getSendBcc()
        {
                return sendBcc;
        }

        public void setSendBcc(List sendBcc)
        {
                this.sendBcc = sendBcc;
        }

        public String getSubject()
        {
                return subject;
        }

        public void setSubject(String subject)
        {
                this.subject = subject;
        }

        public String getBody()
        {
                return body;
        }

        public void setBody(String body)
        {
                this.body = body;
        }

        public String getAttachment()
        {
                return attachment;
        }

        public void setAttachment(String attachment)
        {
                this.attachment = attachment;
        }

        public String getSendType()
        {
                return sendType;
        }

        public void setSendType(String sendType)
        {
                this.sendType = sendType;
        }

        public String getContentType()
        {
                return contentType;
        }

        public void setContentType(String contentType)
        {
                this.contentType = contentType;
        }

        public String getHost()
        {
                return host;
        }

        public void setHost(String host)
        {
                this.host = host;
        }

        public int getPort()
        {
                return port;
        }

        public void setPort(int port)
        {
                this.port = port;
        }

        public String getUser()
        {
                return user;
        }

        public void setUser(String user)
        {
                this.user = user;
        }

        public String getPassword()
        {
                return password;
        }

        public void setPassword(String password)
        {
                this.password = password;
        }
}
