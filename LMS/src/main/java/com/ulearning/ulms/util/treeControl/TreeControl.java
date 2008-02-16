/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-7-28
 * Time: 16:29:15
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.util.treeControl;

import org.apache.webapp.admin.TreeControlNode;

public class TreeControl extends org.apache.webapp.admin.TreeControl
{
        public Object clone()
        {
                TreeControlNode tcn = new TreeControlNode();
                tcn.copy(root);
                org.apache.webapp.admin.TreeControl tc = new org.apache.webapp.admin.TreeControl(tcn);
                return tc;
        }
}
