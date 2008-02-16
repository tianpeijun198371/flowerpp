/**
 * EmailServices.java.
 * User: fengch  Date: 2004-7-1
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.core.mail;

import com.ulearning.ulms.core.util.Config;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.core.util.ValidateUtil;
import com.ulearning.ulms.util.log.LogUtil;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


/**
 * Email Services
 */
public class EmailServices
{
        /**
         * Basic JavaMail Service
         */
        public static void sendMail(SmtpModel sm)
        {
                // first check to see if sending mail is enabled
                //todo
                boolean mailEnabled = true;

                boolean useSmtpAuth = false;

                if (!mailEnabled)
                {
                        // no error; just return as if we already processed
                        LogUtil.debug("core",
                                "[EmailService]sendMail-- Mail notifications disabled in ulms.xml");

                        return;
                }

                List sendTo = sm.getSendTo();
                List sendCc = sm.getSendCc();
                List sendBcc = sm.getSendBcc();
                String subject = sm.getSubject();

                String sendFrom = sm.getSendFrom();
                String body = sm.getBody();
                String sendType = sm.getSendType();
                String host = sm.getHost();
                String user = sm.getUser();
                String password = sm.getPassword();
                String contentType = sm.getContentType();
                String attachment = sm.getAttachment();

                // define some default
                if ((sendType == null) || sendType.equals("mail.smtp.host"))
                {
                        sendType = "mail.smtp.host";

                        if (ValidateUtil.isEmpty(host))
                        {
                                host = Config.getSmtpHost();
                        }

                        if (ValidateUtil.isEmpty(user))
                        {
                                user = Config.getSmtpUser();
                        }

                        if (ValidateUtil.isEmpty(password))
                        {
                                password = Config.getSmtpPassword();
                        }

                        if ((user != null) && (user.length() > 0))
                        {
                                useSmtpAuth = true;
                        }
                }

                if (contentType == null)
                {
                        contentType = "text/html;charset=GBK";
                }

                LogUtil.debug("core", "[EmailService]sendMail-- host=" + host);
                LogUtil.debug("core", "[EmailService]sendMail-- user=" + user);
                LogUtil.debug("core", "[EmailService]sendMail-- password=" + password);
                LogUtil.debug("core",
                        "[EmailService]sendMail-- ususeSmtpAuther=" + useSmtpAuth);

                LogUtil.debug("core", "[EmailService]sendMail-- sendFrom=" + sendFrom);

                try
                {
                        Properties props = System.getProperties();
                        props.put(sendType, host);
                        props.put("mail.mime.address.strict", "false");

                        if (useSmtpAuth)
                        {
                                props.put("mail.smtp.auth", "true");
                        }

                        Session session = Session.getInstance(props);

                        MimeMessage mail = new MimeMessage(session);
                        mail.setFrom(new InternetAddress(sendFrom));

                        //mail.addRecipients(Message.RecipientType.TO, (String) sendTo.get(0));
                        String temp = null;

                        if (sendTo != null)
                        {
                                for (int i = 0; i < sendTo.size(); i++)
                                {
                                        temp = (String) sendTo.get(i);
                                        LogUtil.debug("core",
                                                "[EmailService]sendMail-- $ sendTo:" + temp);

                                        //mail.addRecipient(Message.RecipientType.TO, new InternetAddress(temp));
                                        mail.addRecipients(Message.RecipientType.TO, temp); //为了在hotmail中正确显示地址
                                }
                        }
                        else
                        {
                                LogUtil.debug("core", "[EmailService]sendMail--  sendTo is null");
                        }

                        for (int i = 0; (sendCc != null) && (i < sendCc.size()); i++)
                        {
                                temp = (String) sendCc.get(i);
                                LogUtil.debug("core",
                                        "[EmailService]sendMail-- $ sendCc:" + temp);

                                mail.addRecipient(Message.RecipientType.CC,
                                        new InternetAddress(temp));
                        }

                        for (int i = 0; (sendBcc != null) && (i < sendBcc.size()); i++)
                        {
                                temp = (String) sendBcc.get(i);
                                LogUtil.debug("core",
                                        "[EmailService]sendMail-- $ sendBcc:" + temp);

                                try
                                {
                                        mail.addRecipient(Message.RecipientType.BCC,
                                                new InternetAddress(temp));
                                }
                                catch (Exception ex)
                                {
                                        LogUtil.debug("core",
                                                "[EmailService]sendMail--  sendBcc:" + temp +
                                                        " mail address error!");
                                        ex.printStackTrace();
                                }
                        }

                        mail.setSubject(subject);

                        // Handle the attachment, if there is one.
                        if (ValidateUtil.isNotEmpty(attachment))
                        {
                                // Set the ‘text’ as the first body part.
                                BodyPart mbp = new MimeBodyPart();
                                mbp.setContent(body, contentType);

                                Multipart mp = new MimeMultipart();

                                mp.addBodyPart(mbp);

                                mbp = new MimeBodyPart();

                                String imgName = attachment.substring(attachment.lastIndexOf(
                                        "\\") + 1);
                                DataSource src = new FileDataSource(attachment);
                                mbp.setDataHandler(new DataHandler(src));
                                imgName = new String(imgName.getBytes("GBK"), "ISO8859_1"); //add by xiejh
                                mbp.setFileName(imgName);
                                mp.addBodyPart(mbp);
                                mail.setContent(mp);
                        }
                        else
                        {
                                // Use the default method if no attachment.
                                mail.setContent(body, contentType);
                        }

                        // Set the date the mail would
                        mail.setSentDate(new Date());
                        mail.saveChanges();

                        Transport trans = session.getTransport("smtp");

                        if (useSmtpAuth)
                        {
                                trans.connect(host, user, password);
                        }
                        else
                        {
                                trans.connect();
                        }

                        trans.sendMessage(mail, mail.getAllRecipients());
                        trans.close();
                        LogUtil.debug("core", "[EmailService]sendMail-- sucess!");
                }
                catch (Exception e)
                {
                        LogUtil.debug("core",
                                "[EmailService]sendMail-- Cannot send mail message");
                        e.printStackTrace();
                }
        }
}
