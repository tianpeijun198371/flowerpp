// Source File Name:   ADLFile.java
package com.ulearning.ulms.scorm.adl.parsers.dom;

import com.ulearning.ulms.scorm.adl.parsers.util.MessageClassification;
import com.ulearning.ulms.scorm.adl.parsers.util.MessageHandler;
import com.ulearning.ulms.scorm.adl.parsers.util.adlrules.manifest.FileRules;
import com.ulearning.ulms.scorm.adl.parsers.util.adlrules.manifest.HrefRules;
import com.ulearning.ulms.scorm.adl.util.MessageType;
import com.ulearning.ulms.scorm.adl.util.MessageType;
import com.ulearning.ulms.scorm.adl.util.debug.DebugIndicator;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.io.PrintStream;

import java.net.*;

// Referenced classes of package com.ulearning.ulms.scorm.adl.parsers.dom:

//            ADLElement, ADLMetadata
public class ADLFile extends ADLElement
{
        private String href;
        private ADLMetadata adlMetadata;
        private int messageClass;
        private String messageLocation;

        public ADLFile()
        {
                super("file");
                href = new String();
                adlMetadata = null;
                messageClass = MessageClassification.MINIMUM;
                messageLocation = "ADLFile::";
        }

        public boolean fillFile(Node node)
        {
                boolean flag = true;
                multiplicity = getMultiplicityUsed(node.getParentNode(), elemName);

                String s = messageLocation + "fillFile(Node)";

                if (DebugIndicator.ON)
                {
                        System.out.println("******  " + s + "  *********");
                }

                href = getAttribute(node, "href");

                NodeList nodelist = node.getChildNodes();
                int i = nodelist.getLength();

                for (int j = 0; j < i; j++)
                {
                        Node node1 = nodelist.item(j);

                        if ((node1.getNodeType() == 1) &&
                                node1.getLocalName().equalsIgnoreCase("metadata"))
                        {
                                adlMetadata = new ADLMetadata();
                                flag = adlMetadata.fillMetadata(node1) && flag;
                        }
                }

                if (DebugIndicator.ON)
                {
                        System.out.println("*** Exiting " + s + " ***");
                        System.out.println("*** Returning: " + flag);
                }

                return flag;
        }

        public boolean checkConformance(String s, String s1)
        {
                String s2 = messageLocation + "checkConformance(String)";

                if (com.ulearning.ulms.scorm.adl.util.debug.DebugIndicator.ON)
                {
                        System.out.println("******  " + s2 + "  *********");
                }

                boolean flag = true;
                min = FileRules.MIN;
                max = FileRules.MAX;

                int i = messageClass;
                int j = MessageType.INFO;
                String s3 = "";
                String s4 = "";
                s3 = "Testing element <" + elemName + "> for minimum conformance";
                messageHandler.addMessage(i, j, s3, s2, s4);
                flag = checkMultiplicity(i, s2);
                flag = checkHref(s, s1) && flag;

                if (adlMetadata != null)
                {
                        flag = adlMetadata.checkConformance(s) && flag;
                        messageHandler.appendMessage(i, adlMetadata.getMessage(i));
                }

                if (DebugIndicator.ON)
                {
                        System.out.println("*** Exiting " + s2 + " ***");
                        System.out.println("*** Returning: " + flag);
                }

                return flag;
        }

        public boolean checkHref(String s, String s1)
        {
                String s2 = messageLocation + "checkHref(String)";

                if (DebugIndicator.ON)
                {
                        System.out.println("******  " + s2 + "  *********");
                }

                boolean flag = true;
                String s3 = new String("href");
                int i = messageClass;
                int j = MessageType.INFO;
                String s4 = "";
                String s15 = "";
                int i3 = HrefRules.MIN;
                int j3 = HrefRules.MAX;
                int k3 = HrefRules.VALUESPM;
                s4 = "Testing attribute \"" + s3 + "\" for minimum " + "comformance";
                messageHandler.addMessage(i, j, s4, s2, s15);
                flag = checkMultiplicity(i, s2, s3, i3, j3, href, true) && flag;

                if (!href.equalsIgnoreCase(""))
                {
                        int l3 = href.length();

                        if (l3 > k3)
                        {
                                int k = MessageType.WARNING;
                                String s5 = "The smallest permitted maximum for the value of attribute \"" +
                                        s3 + "\" is " + k3 + " and a length of " + l3 +
                                        " was found.";
                                messageHandler.addMessage(i, k, s5, s2, s15);
                        }
                        else
                        {
                                int l = MessageType.PASSED;
                                String s6 = "The value, \"" + href + "\", of attribute \"" +
                                        s3 + "\" passed the " + "smallest permitted maximum test";
                                messageHandler.addMessage(i, l, s6, s2, s15);
                        }

                        if (href.charAt(0) == '/')
                        {
                                int i1 = MessageType.FAILED;
                                String s7 = "File  \"" + href + "\" is referenced to the " +
                                        "users home directory.  The \"href\" attribute must " +
                                        "reference a file that is local to the package or " +
                                        "reference an external file";
                                messageHandler.addMessage(i, i1, s7, s2, s15);

                                if (com.ulearning.ulms.scorm.adl.util.debug.DebugIndicator.ON)
                                {
                                        System.out.println(s7);
                                }

                                flag = false;
                        }

                        try
                        {
                                if (com.ulearning.ulms.scorm.adl.util.debug.DebugIndicator.ON)
                                {
                                        System.out.println("%%%  baseDir = " + s);
                                        System.out.println("%%%  relative href = " + href);
                                }

                                String s16 = href;
                                int i4 = s16.indexOf('?');

                                if (i4 > 0)
                                {
                                        s16 = s16.substring(0, i4);

                                        if (DebugIndicator.ON)
                                        {
                                                System.out.println("query temphref: " + s16);
                                        }
                                }

                                int j4 = s16.indexOf('#');

                                if (j4 > 0)
                                {
                                        s16 = s16.substring(0, j4);

                                        if (com.ulearning.ulms.scorm.adl.util.debug.DebugIndicator.ON)
                                        {
                                                System.out.println("fragment temphref: " + s16);
                                        }
                                }

                                URLDecoder urldecoder = new URLDecoder();
                                URLDecoder _tmp = urldecoder;
                                s16 = URLDecoder.decode(s16);

                                String s17 = new String(s + s1 + s16);

                                if (DebugIndicator.ON)
                                {
                                        System.out.println("hrefPath = " + s17);
                                }

                                File file = new File(s17);

                                if (file.isFile())
                                {
                                        int j1 = MessageType.PASSED;
                                        String s8 = "File \"" + href + "\" has been detected";
                                        messageHandler.addMessage(i, j1, s8, s2, s15);
                                }
                                else
                                {
                                        boolean flag1 = true;

                                        try
                                        {
                                                String s18 = href;

                                                if ((s1.length() > 6) &&
                                                        (s1.substring(0, 5).equals("http:") ||
                                                                s1.substring(0, 6).equals("https:")))
                                                {
                                                        s18 = s1 + s18;
                                                }

                                                URL url = new URL(s18);

                                                if (flag1)
                                                {
                                                        String s19 = url.getProtocol();

                                                        if (s19.equalsIgnoreCase("file"))
                                                        {
                                                                int k1 = MessageType.FAILED;
                                                                String s9 = "File or URL \"" + href +
                                                                        "\" could not " +
                                                                        "be detected or verified for format";
                                                                messageHandler.addMessage(i, k1, s9, s2, s15);
                                                                flag = false;
                                                        }
                                                        else
                                                        {
                                                                int l1 = MessageType.PASSED;
                                                                String s10 = "URL \"" + href +
                                                                        "\" has been verified for" +
                                                                        " correct format";
                                                                messageHandler.addMessage(i, l1, s10, s2, s15);
                                                        }
                                                }
                                                else
                                                {
                                                        int i2 = MessageType.FAILED;
                                                        String s11 = "File or URL \"" + href +
                                                                "\" could not be " +
                                                                "verified for correct format";
                                                        messageHandler.addMessage(i, i2, s11, s2, s15);
                                                        flag = false;
                                                }
                                        }
                                        catch (MalformedURLException malformedurlexception)
                                        {
                                                int j2 = MessageType.FAILED;
                                                String s12 = "URL \"" + href +
                                                        "\" could not be verified for " + "correct format";
                                                messageHandler.addMessage(i, j2, s12, s2, s15);
                                                flag = false;
                                        }
                                }
                        }
                        catch (NullPointerException nullpointerexception)
                        {
                                if (com.ulearning.ulms.scorm.adl.util.debug.DebugIndicator.ON)
                                {
                                        System.out.println(
                                                "NullPointerException thrown when accessing " + href);
                                }

                                int k2 = MessageType.FAILED;
                                String s13 = "File \"" + href + "\" could not be detected";
                                messageHandler.addMessage(i, k2, s13, s2, s15);
                                flag = false;
                        }
                        catch (SecurityException securityexception)
                        {
                                if (DebugIndicator.ON)
                                {
                                        System.out.println(
                                                "SecurityException thrown when accessing " + href);
                                }

                                int l2 = MessageType.FAILED;
                                String s14 = "File \"" + href + "\" could not be detected";
                                messageHandler.addMessage(i, l2, s14, s2, s15);
                                flag = false;
                        }
                }

                if (com.ulearning.ulms.scorm.adl.util.debug.DebugIndicator.ON)
                {
                        System.out.println("*** Exiting " + s2 + " ***");
                        System.out.println("*** Returning: " + flag);
                }

                return flag;
        }

        public ADLMetadata getMetadata()
        {
                return adlMetadata;
        }
}
