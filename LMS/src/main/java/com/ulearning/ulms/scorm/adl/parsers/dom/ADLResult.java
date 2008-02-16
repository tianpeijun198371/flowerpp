// Source File Name:   ADLResult.java
package com.ulearning.ulms.scorm.adl.parsers.dom;

import com.ulearning.ulms.scorm.adl.util.debug.DebugIndicator;

import org.w3c.dom.Node;

import java.io.PrintStream;
import java.io.Serializable;

// Referenced classes of package com.ulearning.ulms.scorm.adl.parsers.dom:

//            ADLElement
public class ADLResult extends ADLElement implements Serializable
{
        private String result;
        private String action;
        private String identifier;

        public ADLResult()
        {
                super("result");
                result = new String();
                action = new String();
                identifier = new String();
        }

        public boolean fillResult(Node node)
        {
                boolean flag = true;

                if (DebugIndicator.ON)
                {
                        System.out.println("******  ADLResult:fillResult()  *********");
                }

                String s = getText(node);

                if (s != null)
                {
                        result = s;
                }

                String s1 = getAttribute(node, "action");

                if (s1 != null)
                {
                        action = s1;
                }

                String s2 = getAttribute(node, "identifier");

                if (s2 != null)
                {
                        identifier = s2;
                }

                return flag;
        }
}
