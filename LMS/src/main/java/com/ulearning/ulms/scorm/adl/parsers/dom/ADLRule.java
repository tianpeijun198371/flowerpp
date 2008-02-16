// Source File Name:   ADLRule.java
package com.ulearning.ulms.scorm.adl.parsers.dom;

import com.ulearning.ulms.scorm.adl.util.debug.DebugIndicator;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.PrintStream;
import java.io.Serializable;

import java.util.Vector;

// Referenced classes of package com.ulearning.ulms.scorm.adl.parsers.dom:

//            ADLElement, ADLResult
public class ADLRule extends ADLElement implements Serializable
{
        private String condition;
        private String identifier;
        private Vector resultList;

        public ADLRule()
        {
                super("rule");
                condition = new String("");
                identifier = new String("");
                resultList = new Vector();
        }

        public boolean fillRule(Node node)
        {
                boolean flag = true;

                if (com.ulearning.ulms.scorm.adl.util.debug.DebugIndicator.ON)
                {
                        System.out.println("******  ADLRule:fillRule()  *********");
                }

                String s = getAttribute(node, "condition");

                if (s != null)
                {
                        condition = s;
                }

                String s1 = getAttribute(node, "identifier");

                if (s1 != null)
                {
                        identifier = s1;
                }

                NodeList nodelist = node.getChildNodes();

                if (com.ulearning.ulms.scorm.adl.util.debug.DebugIndicator.ON)
                {
                        System.out.println("*** NODE: " + node.getNodeName());
                        System.out.println("*** Children - " + nodelist.getLength() +
                                " ***");
                }

                for (int i = 0; i < nodelist.getLength(); i++)
                {
                        Node node1 = nodelist.item(i);

                        if ((node1.getNodeType() != 1) ||
                                !node1.getLocalName().equalsIgnoreCase("results"))
                        {
                                continue;
                        }

                        if (com.ulearning.ulms.scorm.adl.util.debug.DebugIndicator.ON)
                        {
                                System.out.println("*** Found Current Node: " +
                                        node1.getNodeName());
                        }

                        fillResults(node1);
                }

                return flag;
        }

        public boolean fillResults(Node node)
        {
                boolean flag = true;

                if (DebugIndicator.ON)
                {
                        System.out.println("******  ADLRule:fillResults()  *********");
                }

                NodeList nodelist = node.getChildNodes();

                if (DebugIndicator.ON)
                {
                        System.out.println("*** NODE: " + node.getNodeName());
                        System.out.println("*** Children - " + nodelist.getLength() +
                                " ***");
                }

                for (int i = 0; i < nodelist.getLength(); i++)
                {
                        Node node1 = nodelist.item(i);

                        if ((node1.getNodeType() != 1) ||
                                !node1.getLocalName().equalsIgnoreCase("result"))
                        {
                                continue;
                        }

                        if (com.ulearning.ulms.scorm.adl.util.debug.DebugIndicator.ON)
                        {
                                System.out.println("*** Found Current Node: " +
                                        node1.getNodeName());
                        }

                        ADLResult adlresult = new ADLResult();
                        adlresult.fillResult(node1);
                        resultList.addElement(adlresult);
                }

                return flag;
        }
}
