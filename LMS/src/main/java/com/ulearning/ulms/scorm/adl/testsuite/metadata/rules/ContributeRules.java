// Source File Name:   ContributeRules.java
package com.ulearning.ulms.scorm.adl.testsuite.metadata.rules;

import com.ulearning.ulms.scorm.adl.testsuite.metadata.MetadataUtil;
import com.ulearning.ulms.scorm.adl.util.MessageType;
import com.ulearning.ulms.scorm.adl.util.MessageType;
import com.ulearning.ulms.scorm.adl.util.debug.DebugIndicator;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.PrintStream;

import java.util.Vector;

// Referenced classes of package com.ulearning.ulms.scorm.adl.testsuite.metadata.rules:

//            DatetypeRules
public class ContributeRules extends MetadataUtil
{
        private String elemNum;
        private Node contributeNode;
        private boolean optionalNotUsed;

        public ContributeRules(Node node, String s)
        {
                optionalNotUsed = true;

                if (DebugIndicator.ON)
                {
                        System.out.println("/////////////////////////////////");
                        System.out.println("ContributeRules() called");
                        System.out.println("/////////////////////////////////");
                }

                contributeNode = node;
                elemNum = s;
        }

        private ContributeRules()
        {
                optionalNotUsed = true;
        }

        public boolean verifyAssetMandatory()
        {
                if (DebugIndicator.ON)
                {
                        System.out.println("Element " + elemNum + " <contribute> has no " +
                                "mandatory sub-elements");
                }

                return false;
        }

        public boolean verifyScoMandatory()
        {
                return verifyAssetMandatory();
        }

        public boolean verifyCaMandatory()
        {
                return verifyScoMandatory();
        }

        public boolean verifyAssetOptionalLifecycle()
        {
                boolean flag = true;
                boolean flag1 = true;
                boolean flag2 = true;
                boolean flag3 = false;
                boolean flag4 = false;
                boolean flag5 = false;
                NodeList nodelist = contributeNode.getChildNodes();
                Node node = contributeNode;

                for (int i = 0; i < nodelist.getLength(); i++)
                {
                        Node node1 = nodelist.item(i);

                        if (node1.getNodeType() != 1)
                        {
                                continue;
                        }

                        String s = node1.getLocalName();

                        if (s.equalsIgnoreCase("role"))
                        {
                                if (flag3)
                                {
                                        continue;
                                }

                                setMessage(MessageType.OTHER, "", "");
                                setMessage(MessageType.INFO,
                                        "ContributeRules::verifyAssetOptional()",
                                        "Testing element " + elemNum + ".1 <role>...");

                                int j = getMultiplicityUsed(contributeNode, s);

                                if (j > 1)
                                {
                                        setMessage(MessageType.FAILED,
                                                "ContributeRules::verifyAssetOptional()",
                                                "More than 1 <role> element was found .. 1 and only 1 allowed");
                                        flag = false;
                                }
                                else
                                {
                                        setMessage(MessageType.PASSED,
                                                "ContributeRules::verifyAssetOptional()",
                                                "1 and only 1 <role> element was found");

                                        if (!verifyRoleLifecycle(node1))
                                        {
                                                flag = false;
                                        }
                                }

                                flag3 = true;

                                continue;
                        }

                        if (s.equalsIgnoreCase("centity"))
                        {
                                if (!flag4)
                                {
                                        setMessage(MessageType.OTHER, "", "");
                                        setMessage(MessageType.INFO,
                                                "ContributeRules::verifyAssetOptional()",
                                                "Testing element " + elemNum + ".2 <centity>...");
                                        setMessage(MessageType.INFO,
                                                "ContributeRules::verifyAssetOptional()",
                                                "Testing element " + elemNum +
                                                        ".2 <centity> for multiplicity...");

                                        int k = getMultiplicityUsed(contributeNode, s);

                                        if (k > 40)
                                        {
                                                setMessage(MessageType.WARNING,
                                                        "ContributeRules::verifyAssetOptional()",
                                                        "More than 40 <centity> elements were found");
                                        }
                                        else
                                        {
                                                setMessage(MessageType.PASSED,
                                                        "ContributeRules::verifyAssetOptional()",
                                                        "40 or less <centity> elements were found");
                                        }

                                        flag4 = true;
                                }

                                if (!verifyCentity(node1, elemNum + ".2"))
                                {
                                        flag1 = false;
                                }

                                continue;
                        }

                        if (!s.equalsIgnoreCase("date") || flag5)
                        {
                                continue;
                        }

                        setMessage(MessageType.OTHER, "", "");
                        setMessage(MessageType.INFO,
                                "ContributeRules::verifyAssetOptional()",
                                "Testing element " + elemNum + ".3 <date>...");

                        int l = getMultiplicityUsed(contributeNode, s);

                        if (l > 1)
                        {
                                setMessage(MessageType.FAILED,
                                        "ContributeRules::verifyAssetOptional()",
                                        "More than 1 <date> element was found .. 0 or 1 allowed");
                                flag2 = false;
                        }
                        else
                        {
                                setMessage(MessageType.PASSED,
                                        "ContributeRules::verifyAssetOptional()",
                                        "0 or 1 <date> element was found");

                                if (!verifyDate(node1, elemNum + ".2"))
                                {
                                        flag2 = false;
                                }
                        }

                        flag5 = true;
                }

                if (DebugIndicator.ON)
                {
                        boolean flag6 = flag && flag1 && flag2;
                        System.out.println("returning ->" + flag6);
                }

                return flag && flag1 && flag2;
        }

        public boolean verifyAssetOptionalMetametadata()
        {
                boolean flag = true;
                boolean flag1 = true;
                boolean flag2 = true;
                boolean flag3 = false;
                boolean flag4 = false;
                boolean flag5 = false;
                NodeList nodelist = contributeNode.getChildNodes();
                Node node = contributeNode;

                for (int i = 0; i < nodelist.getLength(); i++)
                {
                        Node node1 = nodelist.item(i);

                        if (node1.getNodeType() != 1)
                        {
                                continue;
                        }

                        String s = node1.getLocalName();

                        if (s.equalsIgnoreCase("role"))
                        {
                                if (flag3)
                                {
                                        continue;
                                }

                                setMessage(MessageType.OTHER, "", "");
                                setMessage(MessageType.INFO,
                                        "ContributeRules::verifyAssetOptionalMetametadata()",
                                        "Testing element " + elemNum + ".1 <role>...");

                                int j = getMultiplicityUsed(contributeNode, s);

                                if (j > 1)
                                {
                                        setMessage(MessageType.FAILED,
                                                "ContributeRules::verifyAssetOptionalMetametadata()",
                                                "More than 1 <role> element was found .. 1 and only 1 allowed");
                                        flag = false;
                                }
                                else
                                {
                                        setMessage(MessageType.PASSED,
                                                "ContributeRules::verifyAssetOptionalMetametadata()",
                                                "1 and only 1 <role> element was found");

                                        if (!verifyRoleMetametadata(node1))
                                        {
                                                flag = false;
                                        }
                                }

                                flag3 = true;

                                continue;
                        }

                        if (s.equalsIgnoreCase("centity"))
                        {
                                if (!flag4)
                                {
                                        setMessage(MessageType.OTHER, "", "");
                                        setMessage(MessageType.INFO,
                                                "ContributeRules::verifyAssetOptionalMetametadata()",
                                                "Testing element " + elemNum + ".2 <centity>...");
                                        setMessage(MessageType.INFO,
                                                "ContributeRules::verifyAssetOptionalMetametadata()",
                                                "Testing element " + elemNum +
                                                        ".2 <centity> for multiplicity...");

                                        int k = getMultiplicityUsed(contributeNode, s);

                                        if (k > 10)
                                        {
                                                setMessage(MessageType.WARNING,
                                                        "ContributeRules::verifyAssetOptionalMetametadata()",
                                                        "More than 10 <centity> elements were found");
                                        }
                                        else
                                        {
                                                setMessage(MessageType.PASSED,
                                                        "ContributeRules::verifyAssetOptionalMetametadata()",
                                                        "10 or less <centity> elements were found");
                                        }

                                        flag4 = true;
                                }

                                if (!verifyCentity(node1, elemNum + ".2"))
                                {
                                        flag1 = false;
                                }

                                continue;
                        }

                        if (!s.equalsIgnoreCase("date") || flag5)
                        {
                                continue;
                        }

                        setMessage(MessageType.OTHER, "", "");
                        setMessage(MessageType.INFO,
                                "ContributeRules::verifyAssetOptional()",
                                "Testing element " + elemNum + ".3 <date>...");

                        int l = getMultiplicityUsed(contributeNode, s);

                        if (l > 1)
                        {
                                setMessage(MessageType.FAILED,
                                        "ContributeRules::verifyAssetOptional()",
                                        "More than 1 <date> element was found .. 0 or 1 allowed");
                                flag = false;
                        }
                        else
                        {
                                setMessage(MessageType.PASSED,
                                        "ContributeRules::verifyAssetOptional()",
                                        "0 or 1 <date> element was found");

                                if (!verifyDate(node1, elemNum + ".2"))
                                {
                                        flag2 = false;
                                }
                        }

                        flag5 = true;
                }

                if (com.ulearning.ulms.scorm.adl.util.debug.DebugIndicator.ON)
                {
                        boolean flag6 = flag && flag1 && flag2;
                        System.out.println("returning ->" + flag6);
                }

                return flag && flag1 && flag2;
        }

        public boolean verifyScoOptionalLifecycle()
        {
                return verifyAssetOptionalLifecycle();
        }

        public boolean verifyCaOptionalLifecycle()
        {
                return verifyScoOptionalLifecycle();
        }

        public boolean verifyScoOptionalMetametadata()
        {
                return verifyAssetOptionalMetametadata();
        }

        public boolean verifyCaOptionalMetametadata()
        {
                return verifyScoOptionalMetametadata();
        }

        private boolean verifyRoleLifecycle(Node node)
        {
                boolean flag = true;
                boolean flag1 = false;
                Vector vector = buildVocabLifecycleRole();
                flag = verifyVocabulary(node, vector, "role", flag1);

                return flag;
        }

        private boolean verifyRoleMetametadata(Node node)
        {
                boolean flag = true;
                boolean flag1 = false;
                Vector vector = buildVocabMetametadataRole();
                flag = verifyVocabulary(node, vector, "role", flag1);

                return flag;
        }

        private boolean verifyCentity(Node node, String s)
        {
                boolean flag = true;
                String s1 = new String();
                boolean flag1 = false;
                String s3 = new String();
                char c = '\u03E8';
                Node node1 = node;
                NodeList nodelist = node.getChildNodes();

                if (nodelist != null)
                {
                        for (int i = 0; i < nodelist.getLength(); i++)
                        {
                                Node node2 = nodelist.item(i);

                                if (node2.getNodeType() != 1)
                                {
                                        continue;
                                }

                                String s2 = node2.getLocalName();

                                if (!s2.equalsIgnoreCase("vcard"))
                                {
                                        continue;
                                }

                                if (!flag1)
                                {
                                        setMessage(MessageType.INFO,
                                                "ContributeRules::verifyCentity()",
                                                "Testing element <vcard>");
                                        flag1 = true;
                                }

                                Node node3 = nodelist.item(i);
                                NodeList nodelist1 = node3.getChildNodes();

                                if (nodelist1 == null)
                                {
                                        continue;
                                }

                                for (int j = 0; j < nodelist1.getLength(); j++)
                                {
                                        Node node4 = nodelist1.item(j);

                                        if ((node4.getNodeType() == 3) ||
                                                (node4.getNodeType() == 4))
                                        {
                                                setMessage(MessageType.INFO,
                                                        "ContributeRules()::verifyCentity()",
                                                        "Testing element <vcard> for character length..");
                                                s3 = s3 + getTaggedData(node2);
                                        }
                                }

                                if (DebugIndicator.ON)
                                {
                                        System.out.println("1<= " + s3.length() + " <= " + (int) c);
                                }

                                if (s3.length() > c)
                                {
                                        setMessage(MessageType.WARNING,
                                                "ContributeRules::verifyCentity()",
                                                "The text contained in element " + s +
                                                        "<centity> is greater than " + (int) c);

                                        continue;
                                }

                                if (s3.length() < 1)
                                {
                                        flag = false;
                                        setMessage(MessageType.FAILED,
                                                "ContributeRules::verifyCentity()",
                                                "No text was found in element " + s +
                                                        " <centity> and fails the minimum " +
                                                        "character length test");
                                }
                                else
                                {
                                        setMessage(MessageType.PASSED,
                                                "ContributeRules::verifyCentity()",
                                                "Character length for element " + s +
                                                        " <centity> has been verified");
                                }
                        }

                        if (!flag1)
                        {
                                flag = false;
                                setMessage(MessageType.FAILED,
                                        "ContributeRules::verifyCentity()",
                                        "No vcard element was found in element " + s +
                                                " <centity>");
                        }
                }

                return flag;
        }

        private boolean verifyDate(Node node, String s)
        {
                boolean flag = true;
                boolean flag2 = true;
                boolean flag4 = true;
                boolean flag5 = true;
                boolean flag6 = false;
                boolean flag7 = false;
                setMessage(MessageType.INFO, "ContributeRules::verifyDate()",
                        "Testing element " + s + " <date> for " +
                                "Element <datetime> and <description>...");

                NodeList nodelist = node.getChildNodes();
                Node node1 = node;

                for (int i = 0; i < nodelist.getLength(); i++)
                {
                        Node node2 = nodelist.item(i);

                        if (node2.getNodeType() != 1)
                        {
                                continue;
                        }

                        String s1 = node2.getLocalName();

                        if (s1.equalsIgnoreCase("datetime"))
                        {
                                if (!flag6)
                                {
                                        setMessage(MessageType.INFO,
                                                "ContributeRules::verifyDate()",
                                                "Testing element <datetime> for multiplicity...");
                                        setMessage(MessageType.PASSED,
                                                "ContributeRules::verifyDate()",
                                                "Element <datetime> existed 1 and only 1 time");
                                        flag6 = true;
                                }

                                DatetypeRules datetyperules = new DatetypeRules(node2);
                                boolean flag1 = datetyperules.verifyDatetime();

                                if (!flag1)
                                {
                                        flag4 = false;
                                }

                                appendMessages(datetyperules.getMessages());

                                continue;
                        }

                        if (!s1.equalsIgnoreCase("description"))
                        {
                                continue;
                        }

                        if (!flag7)
                        {
                                setMessage(MessageType.INFO, "ContributeRules::verifyDate()",
                                        "Testing element <description>...");
                                setMessage(MessageType.INFO, "ContributeRules::verifyDate()",
                                        "Testing element <description> for multiplicity...");
                                setMessage(MessageType.PASSED, "ContributeRules::verifyDate()",
                                        "Element <description> existed 0 or 1 time");
                                flag7 = true;
                        }

                        DatetypeRules datetyperules1 = new DatetypeRules(node2);
                        boolean flag3 = datetyperules1.verifyDescription();

                        if (!flag3)
                        {
                                flag5 = false;
                        }

                        appendMessages(datetyperules1.getMessages());
                }

                if (!flag6)
                {
                        setMessage(MessageType.INFO, "ContributeRules::verifyRole()",
                                "Testing element <datetime> for multiplicity...");
                        setMessage(MessageType.FAILED, "ContributeRules::verifyRole()",
                                "Element <datetime> was not found and must appear 1 and only 1 time");
                }

                return flag5 && flag4;
        }

        private Vector buildVocabLifecycleRole()
        {
                Vector vector = new Vector(14);
                vector.add("Author");
                vector.add("Publisher");
                vector.add("Unknown");
                vector.add("Initiator");
                vector.add("Terminator");
                vector.add("Validator");
                vector.add("Editor");
                vector.add("Graphical Designer");
                vector.add("Technical Implementer");
                vector.add("Content Provider");
                vector.add("Technical Validator");
                vector.add("Educational Validator");
                vector.add("Script Writer");
                vector.add("Instructional Designer");

                return vector;
        }

        private Vector buildVocabMetametadataRole()
        {
                Vector vector = new Vector(2);
                vector.add("Creator");
                vector.add("Validator");

                return vector;
        }
}
