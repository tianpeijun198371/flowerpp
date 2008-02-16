/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-7-21
 * Time: 13:46:01
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.util.treeControl;

import org.apache.webapp.admin.ApplicationServlet;
import org.apache.webapp.admin.TreeBuilder;
import org.apache.webapp.admin.TreeControl;

import javax.servlet.http.HttpServletRequest;

public interface UlmsTreeBuilder extends TreeBuilder
{
        void buildTree(TreeControl treeControl, ApplicationServlet applicationServlet, HttpServletRequest httpServletRequest);

        void buildTree(TreeControl treeControl);
}
