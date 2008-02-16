// Source File Name:   ADLMetadata.java
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

//            ADLElement
public class ADLMetadata extends ADLElement implements Serializable
{
        private Node metadataRoot;
        private String parentElementName;
        private String parentElementID;
        private String metadataType;
        private String schema;
        private String schemaversion;
        private String adlcpLocation;
        private int schemaMult;
        private int schemaversionMult;
        private int adlcpLocationMult;
        private int messageClass;
        private String messageLocation;

        public ADLMetadata()
        {
                super("metadata");
                metadataRoot = null;
                parentElementName = "";
                parentElementID = "";
                metadataType = "";
                schema = "";
                schemaversion = "";
                adlcpLocation = "";
                schemaMult = -1;
                schemaversionMult = -1;
                adlcpLocationMult = -1;
                messageClass = MessageClassification.MINIMUM;
                messageLocation = "ADLMetadata::";
        }

        public boolean fillMetadata(Node node)
        {
                boolean flag = true;
                URLDecoder urldecoder = new URLDecoder();
                multiplicity = getMultiplicityUsed(node.getParentNode(), elemName);

                Node node1 = node.getParentNode();
                parentElementName = node1.getLocalName();

                if (parentElementName.equalsIgnoreCase("manifest") ||
                        parentElementName.equalsIgnoreCase("organization") ||
                        parentElementName.equalsIgnoreCase("item") ||
                        parentElementName.equalsIgnoreCase("resource"))
                {
                        parentElementID = getAttribute(node1, "identifier");
                }
                else
                {
                        parentElementID = getAttribute(node1, "href");
                }

                if (parentElementName.equalsIgnoreCase("organization") ||
                        parentElementName.equalsIgnoreCase("item"))
                {
                        metadataType = "contentaggregation";
                }
                else if (parentElementName.equalsIgnoreCase("resource"))
                {
                        String s = getAttribute(node1, "scormtype");

                        if (s.equalsIgnoreCase("sco"))
                        {
                                metadataType = "sco";
                        }
                        else
                        {
                                metadataType = "asset";
                        }
                }
                else if (parentElementName.equalsIgnoreCase("manifest"))
                {
                        metadataType = "package";
                }
                else
                {
                        metadataType = "asset";
                }

                String s1 = messageLocation + "fillMetadata(Node)";

                if (com.ulearning.ulms.scorm.adl.util.debug.DebugIndicator.ON)
                {
                        System.out.println("******  " + s1 + "  *********");
                }

                NodeList nodelist = node.getChildNodes();
                int i = nodelist.getLength();

                for (int j = 0; j < i; j++)
                {
                        Node node2 = nodelist.item(j);

                        if (node2.getNodeType() != 1)
                        {
                                continue;
                        }

                        if (node2.getLocalName().equalsIgnoreCase("schema"))
                        {
                                schemaMult = getMultiplicityUsed(node2, "schema");
                                schema = getText(node2);

                                continue;
                        }

                        if (node2.getLocalName().equalsIgnoreCase("schemaversion"))
                        {
                                schemaversionMult = getMultiplicityUsed(node2, "schemaversion");
                                schemaversion = getText(node2);

                                continue;
                        }

                        if (node2.getLocalName().equalsIgnoreCase("location"))
                        {
                                adlcpLocationMult = getMultiplicityUsed(node, "location");
                                adlcpLocation = getText(node2);
                                adlcpLocation = urldecoder.decode(adlcpLocation);

                                continue;
                        }

                        if (node2.getLocalName().equalsIgnoreCase("lom"))
                        {
                                metadataRoot = node2;
                        }
                }

                if (com.ulearning.ulms.scorm.adl.util.debug.DebugIndicator.ON)
                {
                        System.out.println("*** Exiting " + s1 + " ***");
                        System.out.println("*** Returning: " + flag);
                }

                return flag;
        }

        public boolean checkConformance(String s)
        {
                boolean flag = true;
                int i = messageClass;
                int j = MessageType.INFO;
                String s1 = "";
                String s3 = messageLocation + "checkConformance()";
                String s4 = "";

                if (parentElementName.equalsIgnoreCase("manifest") ||
                        parentElementName.equalsIgnoreCase("resource") ||
                        parentElementName.equalsIgnoreCase("file"))
                {
                        min = MetadataRules.MIN;
                        max = MetadataRules.MAX;
                }
                else if (parentElementName.equalsIgnoreCase("organization") ||
                        parentElementName.equalsIgnoreCase("item"))
                {
                        min = MetadataRules.AMIN;
                        max = MetadataRules.AMAX;
                }

                s1 = "Testing element <" + elemName + "> for minimum conformance";
                messageHandler.addMessage(i, j, s1, s3, s4);
                flag = checkMultiplicity(i, s3);
                flag = checkSchema() && flag;
                flag = checkSchemaversion() && flag;

                if ((metadataRoot == null) && (adlcpLocationMult > 0))
                {
                        flag = checkADLCPLocation(s) && flag;
                }
                else if (adlcpLocationMult > 0)
                {
                        int k = MessageType.FAILED;
                        String s2 = "Element <location> can not be used in conjunction with in-line meta-data";
                        messageHandler.addMessage(i, k, s2, s3, s4);
                        flag = false;
                }

                return flag;
        }

        public boolean checkSchema()
        {
                boolean flag = true;
                String s = "schema";
                int i = messageClass;
                int j = MessageType.INFO;
                String s1 = "";
                String s7 = messageLocation + "checkSchema()";
                String s8 = "";
                int k1 = -1;
                int l1 = -1;

                if (parentElementName.equalsIgnoreCase("manifest") ||
                        parentElementName.equalsIgnoreCase("resource") ||
                        parentElementName.equalsIgnoreCase("file"))
                {
                        k1 = SchemaRules.MIN;
                        l1 = SchemaRules.MAX;
                }
                else if (parentElementName.equalsIgnoreCase("organization") ||
                        parentElementName.equalsIgnoreCase("item"))
                {
                        k1 = SchemaRules.AMIN;
                        l1 = SchemaRules.AMAX;
                }

                int i2 = SchemaRules.VALUESPM;
                int j2 = SchemaRules.VOCABSIZE;
                Vector vector = new Vector(j2);

                for (int k2 = 0; k2 < j2; k2++)
                {
                        vector.add(SchemaRules.VOCAB[k2]);
                }

                s1 = "Testing element <" + s + "> for minimum " + "conformance";
                messageHandler.addMessage(i, j, s1, s7, s8);

                if (schemaMult != -1)
                {
                        if ((schemaMult >= k1) && (schemaMult <= l1))
                        {
                                j = MessageType.PASSED;
                                s1 = "Element <" + s + "> passed the multiplicity " + "test";
                                flag = true;
                        }
                        else if (k1 == l1)
                        {
                                j = MessageType.FAILED;
                                s1 = "The multiplicity for element <" + s + "> is " + k1 +
                                        " and only " + l1 + " and " + multiplicity +
                                        " were found.";
                        }
                        else
                        {
                                j = MessageType.FAILED;
                                s1 = "The multiplicity for element <" + s + "> is " + k1 +
                                        " or " + l1 + " and " + multiplicity + " were found.";
                        }
                }

                messageHandler.addMessage(i, j, s1, s7, s8);

                int l2 = schema.length();

                if (l2 > i2)
                {
                        int k = MessageType.WARNING;
                        String s2 = "The smallest permitted maximum for the value of element <" +
                                elemName + "> is " + i2 + " and a length of " + l2 +
                                " was found.";
                        messageHandler.addMessage(i, k, s2, s7, s8);
                }
                else
                {
                        int l = MessageType.PASSED;
                        String s3 = "The value, \"" + schema + "\"element <" + elemName +
                                "> passed the smallest permitted maximum test";
                        messageHandler.addMessage(i, l, s3, s7, s8);
                }

                if (!schema.equalsIgnoreCase(""))
                {
                        boolean flag1 = false;
                        int i3 = vector.size();

                        for (int j3 = 0; (j3 < i3) && !flag1; j3++)
                        {
                                if (schema.equalsIgnoreCase((String) vector.elementAt(j3)))
                                {
                                        int i1 = MessageType.PASSED;
                                        String s4 = "Element <" + s +
                                                "> passed the vocabulary test";
                                        messageHandler.addMessage(i, i1, s4, s7, s8);
                                        flag1 = true;
                                }
                        }

                        if (!flag1)
                        {
                                int j1 = MessageType.FAILED;
                                String s5 = "Element <" + s + "> did not adhere to the " +
                                        "restricted vocabulary and failed the vocabulary test";
                                messageHandler.addMessage(i, j1, s5, s7, s8);
                                s5 = "Vocabulary list for element <" + s + "> is as follows:";
                                messageHandler.addMessage(i, j1, s5, s7, s8);

                                for (int k3 = 0; k3 < i3; k3++)
                                {
                                        String s6 = (String) vector.elementAt(k3);
                                        messageHandler.addMessage(i, j1, s6, s7, s8);
                                }

                                flag = true;
                        }
                }

                return flag;
        }

        public boolean checkSchemaversion()
        {
                boolean flag = true;
                String s = "schemaversion";
                int i = messageClass;
                int j = MessageType.INFO;
                String s1 = "";
                String s7 = messageLocation + "checkSchemaversion()";
                String s8 = "";
                int k1 = -1;
                int l1 = -1;

                if (parentElementName.equalsIgnoreCase("manifest") ||
                        parentElementName.equalsIgnoreCase("resource") ||
                        parentElementName.equalsIgnoreCase("file"))
                {
                        k1 = SchemaversionRules.MIN;
                        l1 = SchemaversionRules.MAX;
                }
                else if (parentElementName.equalsIgnoreCase("organization") ||
                        parentElementName.equalsIgnoreCase("item"))
                {
                        k1 = SchemaversionRules.AMIN;
                        l1 = SchemaversionRules.AMAX;
                }

                int i2 = SchemaversionRules.VALUESPM;
                int j2 = SchemaversionRules.VOCABSIZE;
                Vector vector = new Vector(j2);

                for (int k2 = 0; k2 < j2; k2++)
                {
                        vector.add(SchemaversionRules.VOCAB[k2]);
                }

                s1 = "Testing element <" + s + "> for minimum " + "conformance";
                messageHandler.addMessage(i, j, s1, s7, s8);

                if (schemaversionMult != -1)
                {
                        if ((schemaversionMult >= k1) && (schemaversionMult <= l1))
                        {
                                j = MessageType.PASSED;
                                s1 = "Element <" + s + "> passed the multiplicity " + "test";
                                flag = true;
                        }
                        else if (k1 == l1)
                        {
                                j = MessageType.FAILED;
                                s1 = "The multiplicity for element <" + s + "> is " + k1 +
                                        " and only " + l1 + " and " + multiplicity +
                                        " were found.";
                        }
                        else
                        {
                                j = MessageType.FAILED;
                                s1 = "The multiplicity for element <" + s + "> is " + k1 +
                                        " or " + l1 + " and " + multiplicity + " were found.";
                        }
                }

                messageHandler.addMessage(i, j, s1, s7, s8);

                int l2 = schemaversion.length();

                if (l2 > i2)
                {
                        int k = MessageType.WARNING;
                        String s2 = "The smallest permitted maximum for the value of element <" +
                                elemName + "> is " + i2 + " and a length of " + l2 +
                                " was found.";
                        messageHandler.addMessage(i, k, s2, s7, s8);
                }
                else
                {
                        int l = MessageType.PASSED;
                        String s3 = "The value, \"" + schemaversion + "\" element <" +
                                elemName + "> passed the smallest permitted maximum test";
                        messageHandler.addMessage(i, l, s3, s7, s8);
                }

                if (schema.equalsIgnoreCase("ADL SCORM"))
                {
                        boolean flag1 = false;
                        int i3 = vector.size();

                        for (int j3 = 0; (j3 < i3) && !flag1; j3++)
                        {
                                if (schemaversion.equalsIgnoreCase(
                                        (String) vector.elementAt(j3)))
                                {
                                        int i1 = MessageType.PASSED;
                                        String s4 = "Element <" + s + "> passed the " +
                                                "vocabulary test";
                                        messageHandler.addMessage(i, i1, s4, s7, s8);
                                        flag1 = true;
                                }
                        }

                        if (!flag1)
                        {
                                int j1 = MessageType.FAILED;
                                String s5 = "Element <" + s + "> did not adhere to the " +
                                        "restricted vocabulary and failed the vocabulary test";
                                messageHandler.addMessage(i, j1, s5, s7, s8);
                                s5 = "Vocabulary list for element <" + s + "> is as follows:";
                                messageHandler.addMessage(i, j1, s5, s7, s8);

                                for (int k3 = 0; k3 < i3; k3++)
                                {
                                        String s6 = (String) vector.elementAt(k3);
                                        messageHandler.addMessage(i, j1, s6, s7, s8);
                                }

                                flag = false;
                        }
                }

                return flag;
        }

        public boolean checkADLCPLocation(String s)
        {
                String s1 = messageLocation + "checkADLCPLocation()";

                if (com.ulearning.ulms.scorm.adl.util.debug.DebugIndicator.ON)
                {
                        System.out.println("******  " + s1 + "  *********");
                }

                boolean flag = true;
                String s2 = new String("location");
                int i = messageClass;
                int j = MessageType.INFO;
                String s3 = "";
                String s14 = "";
                int i3 = ADLCPLocationRules.MIN;
                int j3 = ADLCPLocationRules.MAX;
                int k3 = ADLCPLocationRules.VALUESPM;
                s3 = "Testing element <" + s2 + "> for minimum " + "comformance";
                messageHandler.addMessage(i, j, s3, s1, s14);

                if (adlcpLocationMult != -1)
                {
                        if ((adlcpLocationMult >= i3) && (adlcpLocationMult <= j3))
                        {
                                j = MessageType.PASSED;
                                s3 = "Element <" + s2 + "> passed the multiplicity " + "test";
                        }
                        else
                        {
                                if (i3 == j3)
                                {
                                        j = MessageType.FAILED;
                                        s3 = "The multiplicity for element <" + s2 + "> is " + i3 +
                                                " and only " + j3 + " and " + adlcpLocationMult +
                                                " were found.";
                                }
                                else
                                {
                                        j = MessageType.FAILED;
                                        s3 = "The multiplicity for element <" + s2 + "> is " + i3 +
                                                " or " + j3 + " and " + adlcpLocationMult +
                                                " were found.";
                                }

                                flag = false;
                        }
                }

                messageHandler.addMessage(i, j, s3, s1, s14);

                if (!adlcpLocation.equalsIgnoreCase(""))
                {
                        int l3 = adlcpLocation.length();

                        if (l3 > k3)
                        {
                                int k = MessageType.WARNING;
                                String s4 = "The smallest permitted maximum for the value of element <" +
                                        s2 + "> is " + k3 + " and a length of " + l3 +
                                        " was found.";
                                messageHandler.addMessage(i, k, s4, s1, s14);
                        }
                        else
                        {
                                int l = MessageType.PASSED;
                                String s5 = "The value, \"" + adlcpLocation + "\"element <" +
                                        s2 + "> passed the smallest permitted maximum test";
                                messageHandler.addMessage(i, l, s5, s1, s14);
                        }

                        try
                        {
                                if (com.ulearning.ulms.scorm.adl.util.debug.DebugIndicator.ON)
                                {
                                        System.out.println("%%%  baseDir = " + s);
                                        System.out.println("%%%  relative adlcpLocation = " +
                                                adlcpLocation);
                                }

                                String s15 = new String(s + adlcpLocation);

                                if (com.ulearning.ulms.scorm.adl.util.debug.DebugIndicator.ON)
                                {
                                        System.out.println("locationPath = " + s15);
                                }

                                File file = new File(s15);

                                if (file.isFile())
                                {
                                        int i1 = MessageType.PASSED;
                                        String s6 = "File \"" + adlcpLocation +
                                                "\" has been detected";
                                        messageHandler.addMessage(i, i1, s6, s1, s14);
                                }
                                else
                                {
                                        boolean flag1 = true;

                                        try
                                        {
                                                URL url = new URL(adlcpLocation);

                                                if (flag1)
                                                {
                                                        String s16 = url.getProtocol();

                                                        if (s16.equalsIgnoreCase("file"))
                                                        {
                                                                int j1 = MessageType.FAILED;
                                                                String s7 = "File or URL \"" + adlcpLocation +
                                                                        "\" could not be detected";
                                                                messageHandler.addMessage(i, j1, s7, s1, s14);
                                                                flag = false;
                                                        }
                                                        else
                                                        {
                                                                int k1 = MessageType.PASSED;
                                                                String s8 = "URL \"" + adlcpLocation +
                                                                        "\" has been detected";
                                                                messageHandler.addMessage(i, k1, s8, s1, s14);
                                                        }
                                                }
                                                else
                                                {
                                                        int l1 = MessageType.FAILED;
                                                        String s9 = "File or URL \"" + adlcpLocation +
                                                                "\" could not be detected";
                                                        messageHandler.addMessage(i, l1, s9, s1, s14);
                                                        flag = false;
                                                }
                                        }
                                        catch (MalformedURLException malformedurlexception)
                                        {
                                                int i2 = MessageType.FAILED;
                                                String s10 = "URL \"" + adlcpLocation +
                                                        "\" could not be detected";
                                                messageHandler.addMessage(i, i2, s10, s1, s14);
                                                flag = false;
                                        }
                                }
                        }
                        catch (NullPointerException nullpointerexception)
                        {
                                if (com.ulearning.ulms.scorm.adl.util.debug.DebugIndicator.ON)
                                {
                                        System.out.println(
                                                "NullPointerException thrown when accessing " +
                                                        adlcpLocation);
                                }

                                int j2 = MessageType.FAILED;
                                String s11 = "File \"" + adlcpLocation +
                                        "\" could not be detected";
                                messageHandler.addMessage(i, j2, s11, s1, s14);
                                flag = false;
                        }
                        catch (SecurityException securityexception)
                        {
                                if (com.ulearning.ulms.scorm.adl.util.debug.DebugIndicator.ON)
                                {
                                        System.out.println(
                                                "SecurityException thrown when accessing " +
                                                        adlcpLocation);
                                }

                                int k2 = MessageType.FAILED;
                                String s12 = "File \"" + adlcpLocation +
                                        "\" could not be detected";
                                messageHandler.addMessage(i, k2, s12, s1, s14);
                                flag = false;
                        }

                        if (metadataRoot != null)
                        {
                                int l2 = MessageType.FAILED;
                                String s13 = "Both the <adlcp:location> element and in-line methods  were found to express metadata.  Only one or the other is allowed, but not both";
                                messageHandler.addMessage(i, l2, s13, s1, s14);
                                flag = false;
                        }
                }

                if (com.ulearning.ulms.scorm.adl.util.debug.DebugIndicator.ON)
                {
                        System.out.println("*** Exiting " + s1 + " ***");
                        System.out.println("*** Returning: " + flag);
                }

                return flag;
        }

        public Node getMetadataRoot()
        {
                return metadataRoot;
        }

        public String getMetadataLocation()
        {
                return adlcpLocation;
        }

        public String getMetadataType()
        {
                return metadataType;
        }

        public String getParentName()
        {
                return parentElementName;
        }

        public String getParentID()
        {
                return parentElementID;
        }
}
