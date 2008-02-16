// Source File Name:   ADLOrganization.java
package com.ulearning.ulms.scorm.adl.parsers.dom;

import com.ulearning.ulms.scorm.adl.parsers.util.MessageClassification;
import com.ulearning.ulms.scorm.adl.parsers.util.MessageHandler;
import com.ulearning.ulms.scorm.adl.parsers.util.adlrules.manifest.*;
import com.ulearning.ulms.scorm.adl.util.MessageType;
import com.ulearning.ulms.scorm.adl.util.MessageType;
import com.ulearning.ulms.scorm.adl.util.debug.DebugIndicator;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.PrintStream;
import java.io.Serializable;

import java.util.Vector;

// Referenced classes of package com.ulearning.ulms.scorm.adl.parsers.dom:

//            ADLElement, ADLItem, ADLMetadata, ADLSequence
public class ADLOrganization extends ADLElement implements Serializable
{
        private Vector itemList;
        private ADLMetadata adlMetadata;
        private ADLSequence adlSequence;
        private String titleElem;
        private String identifierAttr;
        private String structureAttr;
        private String isVisible;
        private int messageClass;
        private String messageLocation;

        public ADLOrganization()
        {
                super("organization");
                itemList = new Vector();
                titleElem = new String();
                identifierAttr = new String();
                structureAttr = new String();
                isVisible = new String();
                adlMetadata = null;
                adlSequence = null;
                messageClass = MessageClassification.MINIMUM;
                messageLocation = "ADLOrganization::";
        }

        public boolean fillOrg(Node node, Vector vector)
        {
                boolean flag = true;
                byte byte0 = 2;
                boolean flag1 = false;
                boolean flag2 = false;
                boolean flag3 = false;
                boolean flag4 = false;
                boolean flag5 = false;
                multiplicity = getMultiplicityUsed(node.getParentNode(), elemName);

                if (DebugIndicator.ON)
                {
                        System.out.println("******  ADLOrganization:fillOrg()  *********");
                }

                identifierAttr = getAttribute(node, "identifier");
                structureAttr = getAttribute(node, "structure");

                NodeList nodelist = node.getChildNodes();

                for (int j1 = 0; j1 < nodelist.getLength(); j1++)
                {
                        Node node1 = nodelist.item(j1);

                        if (node1.getNodeType() != 1)
                        {
                                continue;
                        }

                        if (node1.getLocalName().equalsIgnoreCase("item"))
                        {
                                ADLItem adlitem = new ADLItem();
                                String s = getAttribute(node1, "identifier");
                                String s1 = getAttribute(node1, "identifierref");
                                String s2 = getAttribute(node1, "isvisible");
                                String s3 = getAttribute(node1, "parameters");
                                String s4 = getSubElement(node1, "title");
                                String s5 = getSubElement(node1, "prerequisites");
                                String s6 = getSubElement(node1, "timelimitaction");
                                String s7 = getSubElement(node1, "maxtimeallowed");
                                String s8 = getSubElement(node1, "datafromlms");
                                String s9 = getSubElement(node1, "masteryscore");
                                int i = getMultiplicityUsed(node1, "prerequisites");
                                int j = getMultiplicityUsed(node1, "maxtimeallowed");
                                int k = getMultiplicityUsed(node1, "timelimitaction");
                                int l = getMultiplicityUsed(node1, "datafromlms");
                                int i1 = getMultiplicityUsed(node1, "masteryscore");
                                adlitem.setIdentifier(s);

                                if (!s1.equals(""))
                                {
                                        adlitem.setIdentifierref(s1);
                                }
                                else
                                {
                                        adlitem.setIdentifierref("");
                                }

                                adlitem.setIsVisible(s2);

                                if (!s3.equals(""))
                                {
                                        adlitem.setParameterString(s3);
                                }
                                else
                                {
                                        adlitem.setParameterString("");
                                }

                                adlitem.setTitle(s4);
                                adlitem.setPrerequisites(s5);
                                adlitem.setTimeLimitAction(s6);
                                adlitem.setMaxTimeAllowed(s7);
                                adlitem.setDataFromLMS(s8);
                                adlitem.setMasteryScore(s9);
                                adlitem.setLevel(0);
                                adlitem.setPrerequisitesMultiplicityUsed(i);
                                adlitem.setMaxTimeAllowedMultiplicityUsed(j);
                                adlitem.setTimeLimitActionMultiplicityUsed(k);
                                adlitem.setDataFromLMSMultiplicityUsed(l);
                                adlitem.setMasteryScoreMultiplicityUsed(i1);
                                flag = adlitem.fillItem(node1, vector);
                                itemList.addElement(adlitem);

                                continue;
                        }

                        if (node1.getLocalName().equalsIgnoreCase("title"))
                        {
                                titleElem = getText(node1);

                                continue;
                        }

                        if (node1.getLocalName().equalsIgnoreCase("metadata"))
                        {
                                adlMetadata = new ADLMetadata();
                                flag = adlMetadata.fillMetadata(node1) && flag;

                                continue;
                        }

                        if (node1.getLocalName().equalsIgnoreCase("sequencing"))
                        {
                                adlSequence = new ADLSequence();
                                flag = adlSequence.fillSequence(node1) && flag;
                        }
                }

                if (com.ulearning.ulms.scorm.adl.util.debug.DebugIndicator.ON)
                {
                        System.out.println("*** Exiting ADLOrganization::fillOrg() ***");
                        System.out.println("*** Returning: " + flag);
                }

                return flag;
        }

        public Vector getItemList()
        {
                if (com.ulearning.ulms.scorm.adl.util.debug.DebugIndicator.ON)
                {
                        System.out.println(
                                "*******    ADLOrganization::getItemList()  **************");
                }

                Vector vector = new Vector();
                Vector vector1 = new Vector();

                for (int i = 0; i < itemList.size(); i++)
                {
                        ADLItem adlitem = (ADLItem) itemList.elementAt(i);
                        vector1.addElement(adlitem);

                        Vector vector2 = adlitem.getItemList();
                        vector1.addAll(vector2);
                }

                vector.addAll(vector1);

                if (com.ulearning.ulms.scorm.adl.util.debug.DebugIndicator.ON)
                {
                        System.out.println(
                                "*******    EXITING  ADLOrganization::getItemList()  **************");
                        System.out.println("*******  Vector size is: " + vector.size() +
                                "  **************");
                }

                return vector;
        }

        public boolean checkConformance(String s, String s1)
        {
                boolean flag = true;

                if (s.equalsIgnoreCase("aggregation"))
                {
                        min = OrganizationRules.AMIN;
                        max = OrganizationRules.AMAX;
                }
                else
                {
                        min = OrganizationRules.RMIN;
                        max = OrganizationRules.RMAX;
                }

                spm = OrganizationRules.SPM;

                int j = messageClass;
                int k = MessageType.INFO;
                String s2 = "";
                String s3 = messageLocation + "checkConformance()";
                String s4 = "";
                s2 = "Testing element <" + elemName + "> for minimum conformance";
                messageHandler.addMessage(j, k, s2, s3, s4);
                flag = checkMultiplicity(j, s3);
                flag = checkIdentifier() && flag;
                flag = checkStructure(s) && flag;
                flag = checkIsVisible(s) && flag;
                flag = checkTitle(s) && flag;

                int i = itemList.size();

                for (int l = 0; l < i; l++)
                {
                        flag = ((ADLItem) itemList.elementAt(l)).checkConformance(s, s1) &&
                                flag;
                        messageHandler.appendMessage(j,
                                ((ADLItem) itemList.elementAt(l)).getMessage(j));
                }

                if (adlMetadata != null)
                {
                        flag = adlMetadata.checkConformance(s1) && flag;
                        messageHandler.appendMessage(j, adlMetadata.getMessage(j));
                }

                return flag;
        }

        public boolean checkIdentifier()
        {
                boolean flag = true;
                String s = new String("identifier");
                int i = messageClass;
                int j = MessageType.INFO;
                String s1 = "";
                String s4 = messageLocation + "checkIdentifier()";
                String s5 = "";
                int i1 = IdentifierRules.MIN;
                int j1 = IdentifierRules.MAX;
                int k1 = IdentifierRules.VALUESPM;
                s1 = "Testing attribute \"" + s + "\" for minimum " + "comformance";
                messageHandler.addMessage(i, j, s1, s4, s5);
                flag = checkMultiplicity(i, s4, s, i1, j1, identifierAttr, true) &&
                        flag;

                int l1 = identifierAttr.length();

                if (l1 > k1)
                {
                        int k = MessageType.WARNING;
                        String s2 = "The smallest permitted maximum for the value of attribute \"" +
                                s + "\" is " + k1 + " and a length of " + l1 + " was found.";
                        messageHandler.addMessage(i, k, s2, s4, s5);
                }
                else
                {
                        int l = MessageType.PASSED;
                        String s3 = "The value, \"" + identifierAttr +
                                "\", of attribute \"" + s + "\" passed the " +
                                "smallest permitted maximum test";
                        messageHandler.addMessage(i, l, s3, s4, s5);
                }

                return flag;
        }

        public boolean checkIsVisible(String s)
        {
                boolean flag = true;
                String s1 = new String("isvisible");
                int i = messageClass;
                int j = MessageType.INFO;
                String s2 = "";
                String s7 = messageLocation + "checkIsVisible()";
                String s8 = "";
                int j1 = -1;
                int k1 = -1;

                if (s.equalsIgnoreCase("aggregation"))
                {
                        j1 = IsvisibleRules.AMIN;
                        k1 = IsvisibleRules.AMAX;
                }
                else
                {
                        j1 = IsvisibleRules.RMIN;
                        k1 = IsvisibleRules.RMAX;
                }

                int l1 = IsvisibleRules.VOCABSIZE;
                Vector vector = new Vector(l1);

                for (int i2 = 0; i2 < l1; i2++)
                {
                        vector.add(IsvisibleRules.VOCAB[i2]);
                }

                s2 = "Testing attribute \"" + s1 + "\" for minimum " + "comformance";
                messageHandler.addMessage(i, j, s2, s7, s8);
                flag = checkMultiplicity(i, s7, s1, j1, k1, isVisible, true) && flag;

                if (isVisible.equalsIgnoreCase(""))
                {
                        int k = MessageType.PASSED;
                        String s3 = "Attribute \"" + s1 + "\" was not found or was " +
                                "left blank.  It is assumed that the default value of " +
                                "\"true\" will be used.";
                        messageHandler.addMessage(i, k, s3, s7, s8);
                }
                else
                {
                        boolean flag1 = false;
                        int j2 = vector.size();

                        for (int k2 = 0; (k2 < j2) && !flag1; k2++)
                        {
                                if (isVisible.equalsIgnoreCase((String) vector.elementAt(k2)))
                                {
                                        int l = MessageType.PASSED;
                                        String s4 = "Attribute \"" + s1 + "\" passed the " +
                                                "vocabulary test";
                                        messageHandler.addMessage(i, l, s4, s7, s8);
                                        flag1 = true;
                                }
                        }

                        if (!flag1)
                        {
                                int i1 = MessageType.FAILED;
                                String s5 = "Attribute \"" + s1 + "\" did not adhere to the " +
                                        "restricted vocabulary and failed the vocabulary test";
                                messageHandler.addMessage(i, i1, s5, s7, s8);
                                s5 = "Vocabulary list for attribute \"" + s1 +
                                        "\" is as follows:";
                                messageHandler.addMessage(i, i1, s5, s7, s8);

                                for (int l2 = 0; l2 < j2; l2++)
                                {
                                        String s6 = (String) vector.elementAt(l2);
                                        messageHandler.addMessage(i, i1, s6, s7, s8);
                                }

                                flag = false;
                        }
                }

                return flag;
        }

        public boolean checkStructure(String s)
        {
                boolean flag = true;
                String s1 = new String("structure");
                int i = messageClass;
                int j = MessageType.INFO;
                String s2 = "";
                String s5 = messageLocation + "checkStructure()";
                String s6 = "";
                int i1 = -1;
                int j1 = -1;

                if (s.equalsIgnoreCase("aggregation"))
                {
                        i1 = StructureRules.AMIN;
                        j1 = StructureRules.AMAX;
                }
                else
                {
                        i1 = StructureRules.RMIN;
                        j1 = StructureRules.RMAX;
                }

                int k1 = StructureRules.VALUESPM;
                s2 = "Testing attribute \"" + s1 + "\" for minimum " + "comformance";
                messageHandler.addMessage(i, j, s2, s5, s6);
                flag = checkMultiplicity(i, s5, s1, i1, j1, structureAttr, true) &&
                        flag;

                int l1 = structureAttr.length();

                if (l1 > k1)
                {
                        int k = MessageType.WARNING;
                        String s3 = "The smallest permitted maximum for the value of attribute \"" +
                                s1 + "\" is " + k1 + " and a length of " + l1 + " was found.";
                        messageHandler.addMessage(i, k, s3, s5, s6);
                }
                else
                {
                        int l = MessageType.PASSED;
                        String s4 = "The value, \"" + structureAttr +
                                "\", of attribute \"" + s1 + "\" passed the " +
                                "smallest permitted maximum test";
                        messageHandler.addMessage(i, l, s4, s5, s6);
                }

                return flag;
        }

        public boolean checkTitle(String s)
        {
                boolean flag = true;
                String s1 = new String("title");
                int i = messageClass;
                int j = MessageType.INFO;
                String s2 = "";
                String s5 = messageLocation + "checkTitle()";
                String s6 = "";
                int i1 = -1;
                int j1 = -1;

                if (s.equalsIgnoreCase("aggregation"))
                {
                        i1 = TitleRules.AMIN;
                        j1 = TitleRules.AMAX;
                }
                else
                {
                        i1 = TitleRules.RMIN;
                        j1 = TitleRules.RMAX;
                }

                int k1 = TitleRules.VALUESPM;
                s2 = "Testing element <" + s1 + "> for minimum " + "comformance";
                messageHandler.addMessage(i, j, s2, s5, s6);
                flag = checkMultiplicity(i, s5, s1, i1, j1, titleElem, false) && flag;

                int l1 = titleElem.length();

                if (l1 > k1)
                {
                        int k = MessageType.WARNING;
                        String s3 = "The smallest permitted maximum for the value of element <" +
                                s1 + "> is " + k1 + " and a length of " + l1 + " was found.";
                        messageHandler.addMessage(i, k, s3, s5, s6);
                }
                else
                {
                        int l = MessageType.PASSED;
                        String s4 = "The value, \"" + titleElem + "\", of element <" + s1 +
                                "> passed the " + "smallest permitted maximum test";
                        messageHandler.addMessage(i, l, s4, s5, s6);
                }

                return flag;
        }

        public ADLSequence getSequence()
        {
                ADLSequence adlsequence = new ADLSequence();
                ADLSequence adlsequence1 = new ADLSequence();

                if (adlSequence == null)
                {
                        adlsequence = adlsequence1;
                }
                else
                {
                        adlsequence = adlSequence;
                }

                return adlsequence;
        }

        public String getIdentifier()
        {
                return identifierAttr;
        }

        public Vector getMetadata()
        {
                Vector vector = new Vector();
                vector.add(adlMetadata);

                int i = itemList.size();

                for (int j = 0; j < i; j++)
                {
                        vector.addAll(((ADLItem) itemList.elementAt(j)).getMetadata());
                }

                return vector;
        }
}
