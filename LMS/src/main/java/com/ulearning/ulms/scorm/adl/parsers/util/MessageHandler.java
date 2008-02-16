// Source File Name:   MessageHandler.java
package com.ulearning.ulms.scorm.adl.parsers.util;

import com.ulearning.ulms.scorm.adl.util.Message;
import com.ulearning.ulms.scorm.adl.util.Message;
import com.ulearning.ulms.scorm.adl.util.debug.DebugIndicator;

import java.io.FileWriter;
import java.io.PrintStream;

import java.util.Collection;
import java.util.Vector;

// Referenced classes of package com.ulearning.ulms.scorm.adl.parsers.util:

//            MessageClassification
public class MessageHandler
{
        private int numClassifications;
        private Vector[] messageClassification;

        public MessageHandler()
        {
                numClassifications = MessageClassification.NUM;
                messageClassification = new Vector[numClassifications];

                for (int i = 0; i < numClassifications; i++)
                {
                        messageClassification[i] = new Vector(0, 5);
                }
        }

        public void addMessage(int i, int j, String s, String s1, String s2)
        {
                Message message = new Message(j, s, s1, s2);
                messageClassification[i].add(message);

                if (DebugIndicator.ON)
                {
                        System.out.println(message.toString());
                }
        }

        public void addMessage(int i, int j, String s, String s1, String s2,
                               FileWriter filewriter)
        {
                logMessage(j, s, filewriter);
        }

        public void logMessage(int i, String s, FileWriter filewriter)
        {
                try
                {
                        String s1 = "";

                        if (i == 0)
                        {
                                s1 = s1 +
                                        "     <img src=\"../../../images/smallinfo.gif\">     <font style=\"font-size:15px;\" color=\"blue\">";
                        }
                        else if (i == 1)
                        {
                                s1 = s1 +
                                        "     <img src=\"../../../images/smallwarning.gif\">     <font style=\"font-size:15px;\" color=\"darkorange\"> WARNING:";
                        }
                        else if (i == 2)
                        {
                                s1 = s1 +
                                        "     <img src=\"../../../images/smallcheck.gif\">     <font style=\"font-size:15px;\" color=\"green\">";
                        }
                        else if (i == 3)
                        {
                                s1 = s1 +
                                        "     <img src=\"../../../images/smallxuser.gif\">     <font style=\"font-size:15px;\" color=\"red\"> ERROR:";
                        }
                        else if (i == 4)
                        {
                                s1 = s1 +
                                        "     <img src=\"../../../images/smallstop.gif\">     <font style=\"font-size:15px;\" color=\"red\"> ERROR:";
                        }
                        else if (i == 5)
                        {
                                s1 = s1 +
                                        "     <img src=\"../../../../images/adl_tm_24x16.jpg\">     <font style=\"font-size:15px;\" color=\"purple\">";
                        }
                        else
                        {
                                s1 = s1 +
                                        "     <font style=\"font-size:15px;\" color=\"black\">";
                        }

                        s1 = s1 + "&nbsp;&nbsp;&nbsp;" + s + "</font>";
                        s1 = s1 + "<br>\n";
                        filewriter.write(s1);
                }
                catch (Exception exception)
                {
                }
        }

        public void appendMessage(int i, Collection collection)
        {
                messageClassification[i].addAll(collection);
        }

        public Vector getMessage(int i)
        {
                return messageClassification[i];
        }

        public void clearMessage(int i)
        {
                messageClassification[i].clear();
        }

        public void clearAll()
        {
                messageClassification[MessageClassification.SYSTEM].clear();
                messageClassification[MessageClassification.WELLFORMED].clear();
                messageClassification[MessageClassification.VALID].clear();
                messageClassification[MessageClassification.MINIMUM].clear();
                messageClassification[MessageClassification.EXTENTION].clear();
                messageClassification[MessageClassification.METADATA].clear();
                messageClassification[MessageClassification.CONFORMANCE].clear();
        }
}
