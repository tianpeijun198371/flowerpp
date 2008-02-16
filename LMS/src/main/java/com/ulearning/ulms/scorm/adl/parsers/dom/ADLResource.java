// Source File Name:   ADLResource.java
package com.ulearning.ulms.scorm.adl.parsers.dom;

import com.ulearning.ulms.scorm.adl.parsers.util.MessageClassification;
import com.ulearning.ulms.scorm.adl.parsers.util.MessageHandler;
import com.ulearning.ulms.scorm.adl.parsers.util.adlrules.manifest.*;
import com.ulearning.ulms.scorm.adl.util.MessageType;
import com.ulearning.ulms.scorm.adl.util.MessageType;
import com.ulearning.ulms.scorm.adl.util.debug.DebugIndicator;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.*;

import java.net.*;

import java.util.Vector;

// Referenced classes of package com.ulearning.ulms.scorm.adl.parsers.dom:

//            ADLElement, ADLFile, ADLMetadata
public class ADLResource extends ADLElement implements Serializable
{
        private ADLMetadata adlMetadata;
        private Vector fileList;
        private Vector dependencyList;
        private Vector dependencyTextEmpty;
        private Vector idExists;
        private String identifier;
        private String type;
        private String adlcpScormtype;
        private String href;
        private String xmlBase;
        private String fullLaunchLocation;
        private int messageClass;
        private String messageLocation;

        public ADLResource()
        {
                super("resource");
                adlMetadata = null;
                fileList = new Vector();
                dependencyList = new Vector();
                dependencyTextEmpty = new Vector();
                identifier = new String();
                type = new String();
                adlcpScormtype = new String();
                href = new String();
                idExists = new Vector();
                xmlBase = new String();
                fullLaunchLocation = new String();
                messageClass = MessageClassification.MINIMUM;
                messageLocation = "ADLResource::";
        }

        public boolean fillResource(Node node)
        {
                String s = messageLocation + "fillResource(Node)";

                if (com.ulearning.ulms.scorm.adl.util.debug.DebugIndicator.ON)
                {
                        System.out.println("******  " + s + "  *********");
                }

                boolean flag = true;
                byte byte0 = 2;
                multiplicity = getMultiplicityUsed(node.getParentNode(), elemName);
                identifier = getAttribute(node, "identifier");
                type = getAttribute(node, "type");
                adlcpScormtype = getAttribute(node, "scormtype");
                href = getAttribute(node, "href");
                xmlBase = getAttribute(node, "base");

                if (xmlBase.length() > 0)
                {
                        xmlBase = xmlBase.replace('\\', '/');

                        if (xmlBase.charAt(xmlBase.length() - 1) != '/')
                        {
                                xmlBase = xmlBase + '/';
                        }
                }

                NodeList nodelist = node.getChildNodes();

                for (int i = 0; i < nodelist.getLength(); i++)
                {
                        Node node1 = nodelist.item(i);

                        if (node1.getNodeType() != 1)
                        {
                                continue;
                        }

                        if (node1.getLocalName().equalsIgnoreCase("file"))
                        {
                                ADLFile adlfile = new ADLFile();
                                flag = adlfile.fillFile(node1) && flag;
                                fileList.add(adlfile);

                                continue;
                        }

                        if (node1.getLocalName().equalsIgnoreCase("dependency"))
                        {
                                String s1 = getText(node1);

                                if (s1.equalsIgnoreCase(""))
                                {
                                        dependencyTextEmpty.add(new Boolean("true"));
                                }
                                else
                                {
                                        dependencyTextEmpty.add(new Boolean("false"));
                                }

                                String s2 = getIdentifierrefAttribute(node, "dependency");
                                dependencyList.add(s2);

                                continue;
                        }

                        if (node1.getLocalName().equalsIgnoreCase("metadata"))
                        {
                                adlMetadata = new ADLMetadata();
                                flag = adlMetadata.fillMetadata(node1) && flag;
                        }
                }

                if (dependencyTextEmpty.size() != dependencyList.size())
                {
                        flag = false;
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
                min = ResourceRules.MIN;
                max = ResourceRules.MAX;

                int i = messageClass;
                int j = MessageType.INFO;
                String s3 = "";
                String s4 = "";
                s3 = "Testing element <" + elemName + "> for minimum conformance";
                messageHandler.addMessage(i, j, s3, s2, s4);
                flag = checkMultiplicity(i, s2);

                String s5 = new String();
                s5 = s1 + xmlBase;
                flag = checkIdentifier() && flag;
                flag = checkType() && flag;
                flag = checkAdlcpScormtype() && flag;
                flag = checkHref(s, s5) && flag;

                int k = fileList.size();

                for (int l = 0; l < k; l++)
                {
                        flag = ((ADLFile) fileList.elementAt(l)).checkConformance(s, s5) &&
                                flag;
                        messageHandler.appendMessage(i,
                                ((ADLFile) fileList.elementAt(l)).getMessage(i));
                }

                flag = checkDependency() && flag;

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

        public boolean checkIdentifier()
        {
                String s = messageLocation + "checkIdentifier()";

                if (DebugIndicator.ON)
                {
                        System.out.println("******  " + s + "  *********");
                }

                boolean flag = true;
                String s1 = new String("identifier");
                int i = messageClass;
                int j = MessageType.INFO;
                String s2 = "";
                String s5 = "";
                int i1 = IdentifierRules.MIN;
                int j1 = IdentifierRules.MAX;
                int k1 = IdentifierRules.VALUESPM;
                s2 = "Testing attribute \"" + s1 + "\" for minimum " + "comformance";
                messageHandler.addMessage(i, j, s2, s, s5);
                flag = checkMultiplicity(i, s, s1, i1, j1, identifier, true) && flag;

                int l1 = identifier.length();

                if (l1 > k1)
                {
                        int k = MessageType.WARNING;
                        String s3 = "The smallest permitted maximum for the value of attribute \"" +
                                s1 + "\" is " + k1 + " and a length of " + l1 + " was found.";
                        messageHandler.addMessage(i, k, s3, s, s5);
                }
                else
                {
                        int l = MessageType.PASSED;
                        String s4 = "The value, \"" + identifier + "\", of attribute \"" +
                                s1 + "\" passed the " + "smallest permitted maximum test";
                        messageHandler.addMessage(i, l, s4, s, s5);
                }

                if (com.ulearning.ulms.scorm.adl.util.debug.DebugIndicator.ON)
                {
                        System.out.println("*** Exiting " + s + " ***");
                        System.out.println("*** Returning: " + flag);
                }

                return flag;
        }

        public boolean checkType()
        {
                String s = messageLocation + "checkType()";

                if (DebugIndicator.ON)
                {
                        System.out.println("******  " + s + "  *********");
                }

                boolean flag = true;
                String s1 = new String("type");
                int i = messageClass;
                int j = MessageType.INFO;
                String s2 = "";
                String s6 = "";
                int i1 = TypeRules.MIN;
                int j1 = TypeRules.MAX;
                int k1 = TypeRules.VALUESPM;
                int l1 = TypeRules.VOCABSIZE;
                Vector vector = new Vector(l1);

                for (int i2 = 0; i2 < l1; i2++)
                {
                        vector.add(TypeRules.VOCAB[i2]);
                }

                s2 = "Testing attribute \"" + s1 + "\" for minimum " + "comformance";
                messageHandler.addMessage(i, j, s2, s, s6);
                flag = checkMultiplicity(i, s, s1, i1, j1, type, true) && flag;

                boolean flag1 = false;
                int j2 = vector.size();

                for (int k2 = 0; (k2 < j2) && !flag1; k2++)
                {
                        if (type.equalsIgnoreCase((String) vector.elementAt(k2)))
                        {
                                int k = MessageType.PASSED;
                                String s3 = "Attribute \"" + s1 + "\" passed the " +
                                        "vocabulary test";
                                messageHandler.addMessage(i, k, s3, s, s6);
                                flag1 = true;
                        }
                }

                if (!flag1)
                {
                        int l = MessageType.FAILED;
                        String s4 = "Attribute \"" + s1 + "\" did not adhere to the " +
                                "restricted vocabulary and failed the vocabulary test";
                        messageHandler.addMessage(i, l, s4, s, s6);
                        s4 = "Vocabulary list for attribute \"" + s1 + "\" is as follows:";
                        messageHandler.addMessage(i, l, s4, s, s6);

                        for (int l2 = 0; l2 < j2; l2++)
                        {
                                String s5 = (String) vector.elementAt(l2);
                                messageHandler.addMessage(i, l, s5, s, s6);
                        }

                        flag = false;
                }

                if (DebugIndicator.ON)
                {
                        System.out.println("*** Exiting " + s + " ***");
                        System.out.println("*** Returning: " + flag);
                }

                return flag;
        }

        public boolean checkAdlcpScormtype()
        {
                String s = messageLocation + "checkAdlcpScormtype()";

                if (DebugIndicator.ON)
                {
                        System.out.println("******  " + s + "  *********");
                }

                boolean flag = true;
                String s1 = new String("scormtype");
                int i = messageClass;
                int j = MessageType.INFO;
                String s2 = "";
                String s7 = "";
                int j1 = ADLCPScormtypeRules.MIN;
                int k1 = ADLCPScormtypeRules.MAX;
                int l1 = ADLCPScormtypeRules.VOCABSIZE;
                Vector vector = new Vector(l1);

                for (int i2 = 0; i2 < l1; i2++)
                {
                        vector.add(ADLCPScormtypeRules.VOCAB[i2]);
                }

                s2 = "Testing attribute \"" + s1 + "\" for minimum " + "comformance";
                messageHandler.addMessage(i, j, s2, s, s7);
                flag = checkMultiplicity(i, s, s1, j1, k1, adlcpScormtype, true) &&
                        flag;

                boolean flag1 = false;
                int j2 = vector.size();

                if (adlcpScormtype.equalsIgnoreCase(""))
                {
                        int k = MessageType.PASSED;
                        String s3 = "Element <" + elemName +
                                "> passed the vocabulary test";
                        messageHandler.addMessage(i, k, s3, s, s7);
                        flag1 = true;
                }
                else
                {
                        for (int k2 = 0; (k2 < j2) && !flag1; k2++)
                        {
                                if (adlcpScormtype.equalsIgnoreCase(
                                        (String) vector.elementAt(k2)))
                                {
                                        int l = MessageType.PASSED;
                                        String s4 = "Attribute \"" + s1 + "\" passed the " +
                                                "vocabulary test";
                                        messageHandler.addMessage(i, l, s4, s, s7);
                                        flag1 = true;
                                }
                        }
                }

                if (!flag1)
                {
                        int i1 = MessageType.FAILED;
                        String s5 = "Attribute \"" + s1 + "\" did not adhere to the " +
                                "restricted vocabulary and failed the vocabulary test";
                        messageHandler.addMessage(i, i1, s5, s, s7);
                        s5 = "Vocabulary list for attribute \"" + s1 + "\" is as follows:";
                        messageHandler.addMessage(i, i1, s5, s, s7);

                        for (int l2 = 0; l2 < j2; l2++)
                        {
                                String s6 = (String) vector.elementAt(l2);
                                messageHandler.addMessage(i, i1, s6, s, s7);
                        }

                        flag = false;
                }

                if (com.ulearning.ulms.scorm.adl.util.debug.DebugIndicator.ON)
                {
                        System.out.println("*** Exiting " + s + " ***");
                        System.out.println("*** Returning: " + flag);
                }

                return flag;
        }

        public boolean checkHref(String s, String s1)
        {
                String s2 = messageLocation + "checkHref(String)";

                if (com.ulearning.ulms.scorm.adl.util.debug.DebugIndicator.ON)
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

                if (adlcpScormtype.equalsIgnoreCase("asset"))
                {
                        i3 = 0;
                }

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
                                href = urldecoder.decode(href);

                                String s17 = new String(s + s1 + s16);
                                String s18 = new String(s + s1 + href);

                                if (DebugIndicator.ON)
                                {
                                        System.out.println("temphrefPath = " + s17);
                                }

                                File file = new File(s17);

                                if (file.isFile())
                                {
                                        int j1 = MessageType.PASSED;
                                        String s8 = "File \"" + href + "\" has been detected";
                                        messageHandler.addMessage(i, j1, s8, s2, s15);
                                        fullLaunchLocation = s18;
                                }
                                else
                                {
                                        boolean flag1 = true;

                                        try
                                        {
                                                String s19 = href;
                                                fullLaunchLocation = href;

                                                if ((s1.length() > 6) &&
                                                        (s1.substring(0, 5).equals("http:") ||
                                                                s1.substring(0, 6).equals("https:")))
                                                {
                                                        s19 = s1 + s19;
                                                        fullLaunchLocation = s19;
                                                }

                                                URL url = new URL(s19);

                                                if (flag1)
                                                {
                                                        String s20 = url.getProtocol();
                                                        String s21 = url.getFile();
                                                        String s22 = url.getQuery();
                                                        String s23 = url.getUserInfo();
                                                        String s24 = url.getRef();

                                                        if (s20.equalsIgnoreCase("file"))
                                                        {
                                                                int k1 = MessageType.FAILED;
                                                                String s9 = "URL \"" + href +
                                                                        "\" could not be " + "verified for format";
                                                                messageHandler.addMessage(i, k1, s9, s2, s15);
                                                                flag = false;
                                                        }
                                                        else
                                                        {
                                                                int l1 = MessageType.PASSED;
                                                                String s10 = "URL \"" + href +
                                                                        "\" has been verified " + "for format";
                                                                messageHandler.addMessage(i, l1, s10, s2, s15);
                                                        }
                                                }
                                                else
                                                {
                                                        int i2 = MessageType.FAILED;
                                                        String s11 = "File or URL \"" + href +
                                                                "\" could not be " +
                                                                "detected or verified for format";
                                                        messageHandler.addMessage(i, i2, s11, s2, s15);
                                                        flag = false;
                                                }
                                        }
                                        catch (MalformedURLException malformedurlexception)
                                        {
                                                int j2 = MessageType.FAILED;
                                                String s12 = "URL \"" + href +
                                                        "\" could not be verified for " + "format";
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

        public boolean checkDependency()
        {
                String s = messageLocation + "checkDependency()";

                if (com.ulearning.ulms.scorm.adl.util.debug.DebugIndicator.ON)
                {
                        System.out.println("******  " + s + "  *********");
                }

                boolean flag = true;
                String s1 = new String("dependency");
                String s2 = new String("identifierref");
                int i = messageClass;
                int j = MessageType.INFO;
                String s3 = "";
                String s6 = "";
                s3 = "Testing element <" + s1 + "> for minimum conformance";
                messageHandler.addMessage(i, j, s3, s, s6);

                int i1 = dependencyList.size();

                for (int j1 = 0; j1 < i1; j1++)
                {
                        if (((Boolean) dependencyTextEmpty.elementAt(j1)).booleanValue())
                        {
                                int k = MessageType.PASSED;
                                String s4 = "Element <" + s1 + "> is represented as an " +
                                        " empty element";
                                messageHandler.addMessage(i, k, s4, s, s6);
                        }
                        else
                        {
                                int l = MessageType.FAILED;
                                String s5 = "Element <" + s1 + "> is not represented as an " +
                                        " empty element because it contains text";
                                messageHandler.addMessage(i, l, s5, s, s6);
                                flag = false;
                        }

                        flag = checkIdentifierref((String) dependencyList.elementAt(j1),
                                ((Boolean) idExists.elementAt(j1)).booleanValue()) && flag;
                }

                if (com.ulearning.ulms.scorm.adl.util.debug.DebugIndicator.ON)
                {
                        System.out.println("*** Exiting " + s + " ***");
                        System.out.println("*** Returning: " + flag);
                }

                return flag;
        }

        public boolean checkIdentifierref(String s, boolean flag)
        {
                String s1 = messageLocation + "checkIdentitierref()";

                if (com.ulearning.ulms.scorm.adl.util.debug.DebugIndicator.ON)
                {
                        System.out.println("******  " + s1 + "  *********");
                }

                boolean flag1 = true;
                String s2 = new String("identitierref");
                int i = messageClass;
                int j = MessageType.INFO;
                String s3 = "";
                String s9 = "";
                int l1 = -1;
                int i2 = -1;
                l1 = IdentifierrefRules.AMIN;
                i2 = IdentifierrefRules.AMAX;

                int j2 = IdentifierrefRules.VALUESPM;
                s3 = "Testing attribute \"" + s2 + "\" for minimum " + "comformance";
                messageHandler.addMessage(i, j, s3, s1, s9);
                flag1 = checkMultiplicity(i, s1, s2, l1, i2, s, true) && flag1;

                int k2 = s.length();

                if (k2 > j2)
                {
                        int k = MessageType.WARNING;
                        String s4 = "The smallest permitted maximum for the value of attribute \"" +
                                s2 + "\" is " + j2 + " and a length of " + k2 + " was found.";
                        messageHandler.addMessage(i, k, s4, s1, s9);
                }
                else
                {
                        int l = MessageType.PASSED;
                        String s5 = "The value, \"" + s + "\", of attribute \"" + s2 +
                                "\" passed the " + "smallest permitted maximum test";
                        messageHandler.addMessage(i, l, s5, s1, s9);
                }

                if (s.equalsIgnoreCase(""))
                {
                        int i1 = MessageType.PASSED;
                        String s6 = "Attribute \"" + s2 + "\" was not found or was " +
                                "left blank.  It is assumed that there is no content " +
                                "associated with this entry in the organization";
                        messageHandler.addMessage(i, i1, s6, s1, s9);
                }
                else if (flag)
                {
                        int j1 = MessageType.PASSED;
                        String s7 = "Resource identifier \"" + s + "\" has " +
                                "been found";
                        messageHandler.addMessage(i, j1, s7, s1, s9);
                }
                else
                {
                        int k1 = MessageType.FAILED;
                        String s8 = "Resource identifier \"" + s + "\" could " +
                                "not be found and failed the referenced identifier test";
                        messageHandler.addMessage(i, k1, s8, s1, s9);
                        flag1 = false;
                }

                if (DebugIndicator.ON)
                {
                        System.out.println("*** Exiting " + s1 + " ***");
                        System.out.println("*** Returning: " + flag1);
                }

                return flag1;
        }

        public boolean setIdExists(Vector vector)
        {
                boolean flag = false;
                int i = dependencyList.size();

                for (int j = 0; j < i; j++)
                {
                        String s = (String) dependencyList.elementAt(j);
                        boolean flag1 = false;
                        int k = vector.size();
                        int l = 0;

                        do
                        {
                                if (l >= k)
                                {
                                        break;
                                }

                                if (s.equalsIgnoreCase((String) vector.elementAt(l)))
                                {
                                        idExists.add(new Boolean("true"));
                                        flag1 = true;

                                        break;
                                }

                                l++;
                        } while (true);

                        if (!flag1)
                        {
                                idExists.add(new Boolean("false"));
                        }
                }

                if (idExists.size() == i)
                {
                        flag = true;
                }

                return flag;
        }

        public String getIdentifier()
        {
                return identifier;
        }

        public Vector getMetadata()
        {
                Vector vector = new Vector();
                vector.add(adlMetadata);

                int i = fileList.size();

                for (int j = 0; j < i; j++)
                {
                        vector.add(((ADLFile) fileList.elementAt(j)).getMetadata());
                }

                return vector;
        }

        public String getADLCPScormtype()
        {
                return adlcpScormtype;
        }

        public String getHref()
        {
                return href;
        }

        public String getFullLaunchLocation()
        {
                return fullLaunchLocation;
        }

        public String getXMLBase()
        {
                return xmlBase;
        }
}
