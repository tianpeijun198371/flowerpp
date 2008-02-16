/**
 * TreeTag.java.
 * User: dengj  Date: 2004-7-15
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.admin;

import com.ulearning.ulms.admin.certificate.bean.CertHelper;
import com.ulearning.ulms.admin.certificate.exceptions.CertDAOSysException;
import com.ulearning.ulms.admin.certificate.form.CertForm;
import com.ulearning.ulms.admin.certificate.webimpls.CertWebImpl;
import com.ulearning.ulms.admin.colsignup.colstudent.bean.ColStudentHelper;
import com.ulearning.ulms.core.context.DefaultContext;
import com.ulearning.ulms.core.security.bean.SecurityConstants;
import com.ulearning.ulms.core.util.Config;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.core.util.StyleConfig;
import com.ulearning.ulms.core.util.StyleConstants;
import com.ulearning.ulms.course.exceptions.CourseSysException;
import com.ulearning.ulms.course.helper.CourseHelper;
import com.ulearning.ulms.course.helper.CourseUserHelper;
import com.ulearning.ulms.course.model.CourseModel;
import com.ulearning.ulms.course.model.CourseUserModel;
import com.ulearning.ulms.course.model.MasterModel;
import com.ulearning.ulms.course.util.CourseKeys;
import com.ulearning.ulms.util.LMSConstants;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.struts.util.RequestUtils;

import org.apache.webapp.admin.TreeControl;
import org.apache.webapp.admin.TreeControlNode;
import org.apache.webapp.admin.TreeControlTag;

import java.io.IOException;

import java.net.URLEncoder;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

import javax.swing.text.Style;


public class TreeTag extends TreeControlTag
{
        /**
         * The default directory name for icon images.
         */
        static final String DEFAULT_IMAGES = "images";

        //private String intervalTR = getIntervalTR();
        /**
         * The names of tree state images that we need.
         */

        //HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
        static final String IMAGE_HANDLE_EXPANDED = Config.getContextRoot() +
                "/images/fopen.gif";
        static final String IMAGE_HANDLE_UNEXPANDED =
                Config.getContextRoot() + "/images/fclosed.gif";
        static final String style = StyleConstants.LIST_FORM_TABLE_STYLE;
        private int iii = 0;
        private String trStyle = StyleConstants.TR_STYLE2;
        private boolean blnDouble = false;
        private int ii = 0;
        protected String para1 = null;
        protected String para2 = null;
        protected String images2;
        protected String hasOperate = null;
        protected String intervalTR = null;

        public String getHasOperate()
        {
                return hasOperate;
        }

        public void setHasOperate(String hasOperate)
        {
                this.hasOperate = hasOperate;
        }

        public String getPara1()
        {
                return para1;
        }

        public void setPara1(String para1)
        {
                this.para1 = para1;
        }

        public String getPara2()
        {
                return para2;
        }

        public void setPara2(String para2)
        {
                this.para2 = para2;
        }

        public String getImages2()
        {
                return images2;
        }

        public void setImages2(String images2)
        {
                this.images2 = images2;
        }

        public int getIii()
        {
                return iii;
        }

        public void setIii(int iii)
        {
                this.iii = iii;
        }

        public String getTrStyle()
        {
                return trStyle;
        }

        public void setTrStyle(String trStyle)
        {
                this.trStyle = trStyle;
        }

        public String getIntervalTR()
        {
                return intervalTR;
        }

        public void setIntervalTR(String intervalTR)
        {
                this.intervalTR = intervalTR;
        }

        /**
         * Return the <code>TreeControl</code> instance for the tree control that
         * we are rendering.
         *
         * @throws javax.servlet.jsp.JspException if no TreeControl instance can be found
         */
        protected TreeControl getTreeControl() throws JspException
        {
                Object treeControl = null;

                if (scope == null)
                {
                        HttpSession session = pageContext.getSession();

                        //
                        if ((session.getAttribute(tree + "type") != null) &&
                                ((String) session.getAttribute(tree + "type")).equals("0"))
                        {
                                treeControl = null;
                        }
                        else
                        {
                                treeControl = pageContext.getSession().getAttribute(tree);
                        }

                        if (treeControl == null)
                        {
                                LogUtil.debug("TreeTag==================",
                                        "I am createing a tree and  put in session");

                                DefaultContext df = new DefaultContext(pageContext);
                                HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
                                ServletContext sevContext = null;
                                treeControl = df.getTree(tree, request);
                        }
                        else
                        {
                                LogUtil.debug("TreeTag==============================",
                                        "I am get a tree from session");
                        }

                        return (TreeControl) treeControl;
                }
                else if ("page".equals(scope))
                {
                        treeControl = pageContext.getAttribute(tree, PageContext.PAGE_SCOPE);
                }
                else if ("request".equals(scope))
                {
                        treeControl = pageContext.getAttribute(tree,
                                PageContext.REQUEST_SCOPE);
                }
                else if ("session".equals(scope))
                {
                        treeControl = pageContext.getAttribute(tree,
                                PageContext.SESSION_SCOPE);
                }
                else if ("application".equals(scope))
                {
                        treeControl = pageContext.getAttribute(tree,
                                PageContext.APPLICATION_SCOPE);
                }

                System.out.println("TreeControl = " + treeControl);

                if (treeControl == null)
                {
                        throw new JspException("Cannot find tree control attribute '" +
                                tree + "'");
                }
                else if (!(treeControl instanceof TreeControl))
                {
                        throw new JspException("Invalid tree control attribute '" + tree +
                                "'");
                }
                else
                {
                        return ((TreeControl) treeControl);
                }
        }

        /**
         * Render this tree control.
         *
         * @throws JspException if a processing error occurs
         */
        public int doEndTag() throws JspException
        {
                if (tree.equals(LMSConstants.TREE_PUBLISH) ||
                        (tree.equals(LMSConstants.TREE_PUBLISHED_COURSE)) ||
                        (tree.equals(LMSConstants.TREE_PUBLISHED_CERT)))
                {
                        doEndPublishTag();
                }
                else if (tree.equals(LMSConstants.TREE_SELECT))
                {
                        doEndSelectTag();
                }
                else if (tree.equals(LMSConstants.TREE_SELECT_NO_SELECT_COURSE))
                {
                        doEndSelectNoSelectCourseTag();
                }
                else if (tree.equals(LMSConstants.TREE_PUBLISH_COURSE) ||
                        tree.equals(LMSConstants.TREE_PUBLISH_CERT) ||
                        tree.equals(LMSConstants.TREE_PUBLISH_CERT_OFFLINE))
                {
                        doEndPublishInTag();
                }
                else if (tree.equals(LMSConstants.TREE_STUDENT_SIGNUP))
                {
                        doEndStudentSignUp();
                }
                else
                {
                        TreeControl treeControl = getTreeControl();
                        JspWriter out = pageContext.getOut();

                        try
                        {
                                out.print("<table " + style + ">");
                                out.println("<tr >");
                                out.println("<th>名&nbsp;&nbsp;称</th>");
                                out.println("<th >编&nbsp;&nbsp;号</th>");
                                out.println("<th >类&nbsp;&nbsp;型</th>");
                                out.println("</tr>");
                                iii = 0;

                                int level = 0;
                                TreeControlNode node = treeControl.getRoot();
                                render(out, node, level, treeControl.getWidth(), true);
                                out.println("</table>");
                        }
                        catch (IOException e)
                        {
                                throw new JspException(e);
                        }
                }

                return (EVAL_PAGE);
        }

        private void doEndPublishTag() throws JspException
        {
                TreeControl treeControl = getTreeControl();
                JspWriter out = pageContext.getOut();

                try
                {
                        out.print("<table " + style + ">");
                        out.println("<tr>");
                        out.println("<th >名称</th>");
                        out.println("<th>编号</th>");
                        out.println("<th>操作人</th>");
                        /*                        out.println("<th>课程（" +
                        RequestUtils.message(pageContext, null, null, "message.certificate") +
                        "）名称</th>");*/
                        out.println("</tr>");

                        if (para1 != null)
                        {
                                out.println("  <tr>");
                                out.println("<td align='left'>");
                                out.print("<input type=\"radio\"");
                                out.print(" name=\"catalogIDs\" value=\"0\">");
                                out.print("<input type=\"hidden\" name=\"selectedType" +
                                        (iii++) + "\" value=\"0\">");
                                out.print("根目录</td>");
                                out.print("<td>");
                                out.print("&nbsp;");
                                out.println("</td>");
                                out.println("<td>&nbsp;</td>");
                                out.println("<td>&nbsp;</td>");
                        }

                        iii = 0;

                        int level = 0;
                        TreeControlNode node = treeControl.getRoot();
                        renderPublishTree(out, node, level, treeControl.getWidth(), true);
                        out.println("</table>");
                }
                catch (IOException e)
                {
                        throw new JspException(e);
                }
        }

        private void doEndSelectTag() throws JspException
        {
                TreeControl treeControl = getTreeControl();
                JspWriter out = pageContext.getOut();

                try
                {
                        out.print("<table " + style + ">");
                        out.println("<tr>");
                        out.println("<th >课程（" +
                                RequestUtils.message(pageContext, null, null,
                                        "message.certificate") + "）名称</th>");
                        out.println("<th>描述</th>");
                        out.println("</tr>");
                        iii = 0;

                        int level = 0;
                        TreeControlNode node = treeControl.getRoot();
                        renderSelectTree(out, node, level, treeControl.getWidth(), true);
                        out.println("</table>");
                }
                catch (IOException e)
                {
                        throw new JspException(e);
                }
        }

        private void doEndSelectNoSelectCourseTag() throws JspException
        {
                TreeControl treeControl = getTreeControl();
                JspWriter out = pageContext.getOut();

                try
                {
                        out.print("<table " + style + ">");
                        out.println("<tr >");
                        out.println("<th align=\"center\">课程（" +
                                RequestUtils.message(pageContext, null, null,
                                        "message.certificate") + "）名称</th>");
                        out.println("<th align=\"center\">发布编号</th>");
                        out.println("</tr>");
                        iii = 0;

                        int level = 0;
                        TreeControlNode node = treeControl.getRoot();
                        renderSelectNoSelectCourseTree(out, node, level,
                                treeControl.getWidth(), true);
                        out.println("</table>");
                }
                catch (IOException e)
                {
                        throw new JspException(e);
                }
        }

        private void doEndPublishInTag() throws JspException
        {
                String tdName = "课程名称";

                if (tree.equals(LMSConstants.TREE_PUBLISH_COURSE))
                {
                        setTree(LMSConstants.TREE_COURSE);
                        tdName = "课程名称";
                }
                else if (tree.equals(LMSConstants.TREE_PUBLISH_CERT))
                {
                        setTree(LMSConstants.TREE_CERT);
                        //tdName = RequestUtils.message(pageContext, null, null, "message.certificate") + "名称";
                        tdName = "远程项目";
                }
                else if (tree.equals(LMSConstants.TREE_PUBLISH_CERT_OFFLINE))
                {
                        setTree(LMSConstants.TREE_CERT_OFFLINE);
                        //tdName = "脱产"+RequestUtils.message(pageContext, null, null, "message.certificate") + "名称";
                        tdName = "脱产项目";
                }

                TreeControl treeControl = getTreeControl();
                JspWriter out = pageContext.getOut();

                try
                {
                        out.print("<table " + style + ">");
                        out.println("<tr >");
                        out.println("<th>" + tdName + "</th>");
                        out.println("<th>描述</th>");
                        out.println("</tr>");

                        iii = 0;

                        int level = 0;
                        TreeControlNode node = treeControl.getRoot();
                        renderPuglishInTree(out, node, level, treeControl.getWidth(), true);
                        out.println("</table>");
                }
                catch (IOException e)
                {
                        throw new JspException(e);
                }
        }

        private void doEndStudentSignUp() throws JspException
        {
                TreeControl treeControl = getTreeControl();
                JspWriter out = pageContext.getOut();

                try
                {
                        out.print("<table " + style + ">");
                        out.println("<tr >");
                        out.println("<th align=\"center\">课程（" +
                                RequestUtils.message(pageContext, null, null,
                                        "message.certificate") + "）名称</th>");
                        out.println("<th align=\"center\">状&nbsp;&nbsp;态</th>");
                        out.println("</tr>");

                        iii = 0;

                        int level = 0;
                        TreeControlNode node = treeControl.getRoot();
                        renderEndStudentSignUpTree(out, node, level,
                                treeControl.getWidth(), true);
                        out.println("</table>");
                }
                catch (IOException e)
                {
                        throw new JspException(e);
                }
        }

        private void renderPublishTree(JspWriter out, TreeControlNode node,
                                       int level, int width, boolean last) throws IOException
        {
                StyleConfig scf02 = new StyleConfig((HttpServletRequest) pageContext.getRequest());
                HttpServletResponse response = (HttpServletResponse) pageContext.getResponse();

                if ("ROOT-NODE".equalsIgnoreCase(node.getName()) &&
                        (node.getLabel() == null))
                {
                        // Render the children of this node
                        TreeControlNode[] children = node.findChildren();
                        int lastIndex = children.length - 1;
                        int newLevel = level + 1;

                        for (int i = 0; i < children.length; i++)
                        {
                                renderPublishTree(out, children[i], newLevel, width,
                                        i == lastIndex);
                        }

                        return;
                }

                // trStyle = (trStyle.equals(StyleConstants.TR_STYLE2) ? StyleConstants.TR_STYLE1 : StyleConstants.TR_STYLE2);

                // Render the beginning of this node
                ii++;
                trStyle = (trStyle.equals(StyleConstants.TR_STYLE2)
                        ? StyleConstants.TR_STYLE1 : StyleConstants.TR_STYLE2);
                out.println("<tr class=" + trStyle + ">");
                out.println("<td align='left'>");

                // Create the appropriate number of indents
                for (int i = 0; i < (level - 1); i++)
                {
                        out.print("&nbsp;&nbsp;&nbsp;");
                }

                // Render the tree state image for this node
                // HACK to take into account special characters like = and &
                // in the node name, could remove this code if encode URL
                // and later request.getParameter() could deal with = and &
                // character in parameter values.
                String encodedNodeName = URLEncoder.encode(node.getName());

                String action = replace(getAction(), "${name}", encodedNodeName);
                String updateTreeAction = replace(getAction(), "tree=${name}",
                        "select=" + encodedNodeName);

                updateTreeAction = ((HttpServletResponse) pageContext.getResponse()).encodeURL(updateTreeAction);

                String[] tempArray = null;
                String nameTempString = node.getName();
                boolean isCatalog = false;
                String disabled = "";
                String hyperlink = null;

                if (nameTempString.indexOf("/") > 0)
                {
                        tempArray = StringUtil.splitString(nameTempString, "/");

                        if (tempArray[0].equals("catalogIDs"))
                        {
                                isCatalog = true;
                        }

                        if (para1 == null)
                        {
                                out.print("<input type=\"checkbox\""); //复选框
                                out.print(" name=\"" + tempArray[0] + "\" value=\"" +
                                        tempArray[1] + "\">");

                                if (isCatalog)
                                {
                                        out.print("<input type=\"hidden\" name=\"selectedType" +
                                                (iii++) + "\" value=\"0\">");
                                }
                                else if (tempArray[0].equals("courseIDs"))
                                {
                                        out.print("<input type=\"hidden\" name=\"selectedType" +
                                                (iii++) + "\" value=\"1\">");
                                }
                                else
                                {
                                        out.print("<input type=\"hidden\" name=\"selectedType" +
                                                (iii++) + "\" value=\"2\">");
                                }
                        }
                        else
                        {
                                if (isCatalog)
                                {
                                        if ((intervalTR != null) &&
                                                (intervalTR.indexOf("/" + tempArray[1] + "/") >= 0))
                                        {
                                                disabled = "disabled";
                                        }

                                        out.print("<input type=\"radio\""); //复选框
                                        out.print(" name=\"" + tempArray[0] + "\" value=\"" +
                                                tempArray[1] + "\"" + disabled + ">");
                                        out.print("<input type=\"hidden\" name=\"selectedType" +
                                                (iii++) + "\" value=\"0\">");
                                }
                                else
                                {
                                        if (((para1 != null) && (para2 != null)) &&
                                                ((para1.indexOf("/" + tempArray[1] + "/") >= 0) ||
                                                        (para2.indexOf("/" + tempArray[1] + "/") >= 0)))
                                        {
                                                disabled = "disabled";
                                                out.print("<input type=\"radio\""); //复选框
                                                out.print(" name=\"" + tempArray[0] + "\" value=\"" +
                                                        tempArray[1] + "\"" + disabled + ">");
                                        }
                                }
                        }
                }
                else
                {
                        out.println("<input type=\"checkbox\"");
                        out.println(" name=\"id\" value=\"" + nameTempString + "\">");
                }

                printNameString(out, node, isCatalog, updateTreeAction, response,
                        hyperlink, action);
                out.println("</td>");

                if (!isCatalog && node.isLeaf())
                {
                        String publicCode = "";
                        String objectName = "";
                        String id = "";

                        if (tempArray != null)
                        {
                                id = tempArray[1];

                                try
                                {
                                        if (tempArray[0].equals("courseIDs"))
                                        {
                                                CourseModel cm = CourseHelper.getCourse(Integer.parseInt(
                                                        id));

                                                if (cm != null)
                                                {
                                                        publicCode = cm.getCourseCode();
                                                        objectName = cm.getName();
                                                }
                                        }
                                        else if (tempArray[0].equals("certificateIDs"))
                                        {
                                                CertForm cf = CertHelper.getCert(Integer.parseInt(id));

                                                if (cf != null)
                                                {
                                                        /*    String certName = cf.getName();
                                                        if(Integer.parseInt(cf.getCertType())==SecurityConstants.USER_OFFLINE_CERTIFICATE_RELATION)
                                                        {
                                                                certName += "  [脱产x]";
                                                        }
                                                        else if(Integer.parseInt(cf.getCertType())==SecurityConstants.USER_CERTIFICATE_RELATION)
                                                        {
                                                                certName += "  [远程x]";
                                                        }*/
                                                        publicCode = cf.getCode();
                                                        objectName = cf.getOperator();
                                                }
                                        }
                                }
                                catch (CourseSysException cse)
                                {
                                        cse.printStackTrace();
                                }
                        }

                        out.println("<td >");
                        out.println("&nbsp;" + publicCode);
                        out.println("</td>");

                        out.println("<td ");
                        out.println("&nbsp;" + objectName);
                        out.println("</td>");
                }
                else
                {
                        out.println("<td>&nbsp;</td>");
                        out.println("<td>&nbsp;</td>");
                }

                // Render the end of this node
                out.println("</tr>");

                // Render the children of this node
                if (node.isExpanded())
                {
                        TreeControlNode[] children = node.findChildren();
                        int lastIndex = children.length - 1;
                        int newLevel = level + 1;

                        for (int i = 0; i < children.length; i++)
                        {
                                renderPublishTree(out, children[i], newLevel, width,
                                        i == lastIndex);
                        }
                }
        }

        private void renderSelectNoSelectCourseTree(JspWriter out,
                                                    TreeControlNode node, int level, int width, boolean last)
                throws IOException
        {
                StyleConfig scf02 = new StyleConfig((HttpServletRequest) pageContext.getRequest());
                HttpServletResponse response = (HttpServletResponse) pageContext.getResponse();

                if ("ROOT-NODE".equalsIgnoreCase(node.getName()) &&
                        (node.getLabel() == null))
                {
                        // Render the children of this node
                        TreeControlNode[] children = node.findChildren();
                        int lastIndex = children.length - 1;
                        int newLevel = level + 1;

                        for (int i = 0; i < children.length; i++)
                        {
                                renderSelectNoSelectCourseTree(out, children[i], newLevel,
                                        width, i == lastIndex);
                        }

                        return;
                }

                // trStyle = (trStyle.equals(StyleConstants.TR_STYLE2) ? StyleConstants.TR_STYLE1 : StyleConstants.TR_STYLE2);

                // Render the beginning of this node
                ii++;
                trStyle = (trStyle.equals(StyleConstants.TR_STYLE2)
                        ? StyleConstants.TR_STYLE1 : StyleConstants.TR_STYLE2);
                out.println("<tr class=" + trStyle + ">");
                out.println("<td align='left'>");

                // Create the appropriate number of indents
                for (int i = 0; i < (level - 1); i++)
                {
                        out.print("&nbsp;&nbsp;&nbsp;");
                }

                String encodedNodeName = URLEncoder.encode(node.getName());

                String action = replace(getAction(), "${name}", encodedNodeName);
                String updateTreeAction = replace(getAction(), "tree=${name}",
                        "select=" + encodedNodeName);

                updateTreeAction = ((HttpServletResponse) pageContext.getResponse()).encodeURL(updateTreeAction);

                String[] tempArray = null;
                String nameTempString = node.getName();
                boolean isCatalog = false;

                String hyperlink = null;

                if (nameTempString.indexOf("/") > 0)
                {
                        tempArray = StringUtil.splitString(nameTempString, "/");

                        if (tempArray[0].equals("catalogIDs"))
                        {
                                isCatalog = true;
                        }

                        if (!isCatalog)
                        {
                                out.print("<input type=\"radio\"");
                                out.print(" name=\"relationID\" value=\"" + tempArray[1] +
                                        "\">");
                                iii++;

                                if (tempArray[0].equals("courseID"))
                                {
                                        out.print("<input type=\"hidden\" name=\"selectedType" +
                                                iii + "\" value=\"" +
                                                SecurityConstants.USER_COURSE_RELATION + "\">");
                                        out.print("<input type=\"hidden\" name=\"courseN" + iii +
                                                "\" value=\"" + node.getLabel() + "\">");
                                }
                                else if (tempArray[0].equals("certificateIDs"))
                                {
                                        out.print("<input type=\"hidden\" name=\"selectedType" +
                                                iii + "\" value=\"" +
                                                SecurityConstants.USER_CERTIFICATE_RELATION + "\">");
                                        out.print("<input type=\"hidden\" name=\"courseN" + iii +
                                                "\" value=\"" + node.getLabel() + "\">");
                                }
                        }
                }

                printNameString(out, node, isCatalog, updateTreeAction, response,
                        hyperlink, action);
                out.println("</td>");

                if (!isCatalog && node.isLeaf())
                {
                        String desc = "";
                        String id = "";

                        if (tempArray != null)
                        {
                                id = tempArray[1];

                                try
                                {
                                        if (tempArray[0].equals("courseID"))
                                        {
                                                CourseModel cm = CourseHelper.getCourse(Integer.parseInt(
                                                        id));

                                                if (cm != null)
                                                {
                                                        desc = cm.getCourseCode();
                                                }
                                        }
                                        else if (tempArray[0].equals("certificateIDs"))
                                        {
                                                CertForm cf = CertHelper.getCert(Integer.parseInt(id));

                                                if ((cf != null) && (cf.getCode() != null))
                                                {
                                                        desc = cf.getCode();
                                                }
                                        }
                                }
                                catch (CourseSysException cse)
                                {
                                        cse.printStackTrace();
                                }
                        }

                        out.println("<td>");
                        out.println("&nbsp;" + desc);
                        out.println("</td>");
                }
                else
                {
                        out.println("<td >&nbsp;</td>");
                }

                out.println("</tr>");

                // Render the children of this node
                if (node.isExpanded())
                {
                        TreeControlNode[] children = node.findChildren();
                        int lastIndex = children.length - 1;
                        int newLevel = level + 1;

                        for (int i = 0; i < children.length; i++)
                        {
                                renderSelectTree(out, children[i], newLevel, width,
                                        i == lastIndex);
                        }
                }
        }

        private void renderEndStudentSignUpTree(JspWriter out,
                                                TreeControlNode node, int level, int width, boolean last)
                throws IOException
        {
                StyleConfig scf02 = new StyleConfig((HttpServletRequest) pageContext.getRequest());
                HttpServletResponse response = (HttpServletResponse) pageContext.getResponse();

                if ("ROOT-NODE".equalsIgnoreCase(node.getName()) &&
                        (node.getLabel() == null))
                {
                        // Render the children of this node
                        TreeControlNode[] children = node.findChildren();
                        int lastIndex = children.length - 1;
                        int newLevel = level + 1;

                        for (int i = 0; i < children.length; i++)
                        {
                                renderEndStudentSignUpTree(out, children[i], newLevel, width,
                                        i == lastIndex);
                        }

                        return;
                }

                // trStyle = (trStyle.equals(StyleConstants.TR_STYLE2) ? StyleConstants.TR_STYLE1 : StyleConstants.TR_STYLE2);

                // Render the beginning of this node
                ii++;
                trStyle = (trStyle.equals(StyleConstants.TR_STYLE2)
                        ? StyleConstants.TR_STYLE1 : StyleConstants.TR_STYLE2);
                out.println("<tr class=" + trStyle + ">");
                out.println("<td align='left'>");

                // Create the appropriate number of indents
                for (int i = 0; i < (level - 1); i++)
                {
                        out.print("&nbsp;&nbsp;&nbsp;");
                }

                String encodedNodeName = URLEncoder.encode(node.getName());

                String action = replace(getAction(), "${name}", encodedNodeName);
                String updateTreeAction = replace(getAction(), "tree=${name}",
                        "select=" + encodedNodeName);

                updateTreeAction = ((HttpServletResponse) pageContext.getResponse()).encodeURL(updateTreeAction);

                String[] tempArray = null;
                String nameTempString = node.getName();
                boolean isCatalog = false;

                String hyperlink = null;

                if (nameTempString.indexOf("/") > 0)
                {
                        tempArray = StringUtil.splitString(nameTempString, "/");

                        if (tempArray[0].equals("catalogIDs"))
                        {
                                isCatalog = true;
                        }

                        if (!isCatalog)
                        {
                                out.print("<input type=\"radio\"");
                                out.print(" name=\"relationID\" value=\"" + tempArray[1] +
                                        "\">");
                                iii++;

                                if (tempArray[0].equals("courseID"))
                                {
                                        out.print("<input type=\"hidden\" name=\"selectedType" +
                                                iii + "\" value=\"" +
                                                SecurityConstants.USER_COURSE_RELATION + "\">");
                                        out.print("<input type=\"hidden\" name=\"courseN" + iii +
                                                "\" value=\"" + node.getLabel() + "\">");
                                }
                                else if (tempArray[0].equals("certificateIDs"))
                                {
                                        out.print("<input type=\"hidden\" name=\"selectedType" +
                                                iii + "\" value=\"" +
                                                SecurityConstants.USER_CERTIFICATE_RELATION + "\">");
                                        out.print("<input type=\"hidden\" name=\"courseN" + iii +
                                                "\" value=\"" + node.getLabel() + "\">");
                                }
                        }
                }

                printNameString(out, node, isCatalog, updateTreeAction, response,
                        hyperlink, action);

                out.println("</td>");

                if (!isCatalog && node.isLeaf())
                {
                        String desc = "";
                        String id = "";
                        int userID = Integer.parseInt((String) pageContext.getSession()
                                .getAttribute(LMSConstants.USERID_KEY));

                        // boolean hasApply = false;
                        boolean hasStudy = false;
                        int type = 0;

                        if (tempArray != null)
                        {
                                id = tempArray[1];

                                try
                                {
                                        if (tempArray[0].equals("courseID"))
                                        {
                                                type = SecurityConstants.USER_COURSE_RELATION;
                                        }
                                        else if (tempArray[0].equals("certificateIDs"))
                                        {
                                                type = SecurityConstants.USER_CERTIFICATE_RELATION;
                                        }

                                        //判断是否已经申请了这门课
                                        /*List colStudentList = ColStudentHelper.getColStudentList(userID , Integer.parseInt(id) , type);
                                      if(colStudentList != null && !colStudentList.isEmpty())
                                      hasApply = true;*/

                                        //判断本学员是否已经在学习本课程
                                        hasStudy = CourseUserHelper.isExisitCourseUserRole(userID,
                                                SecurityConstants.ROLE_COURSR_STUDENT,
                                                Integer.parseInt(id), type);

                                        if (hasStudy)
                                        {
                                                CourseUserModel cum = CourseUserHelper.getCourseUser(Integer.parseInt(
                                                        id), type, userID);
                                                int state = cum.getState();
                                                desc = RequestUtils.message(pageContext, null, null,
                                                        CourseUserHelper.getCourseUserStateKey(state));
                                        }
                                        else
                                        {
                                                desc = "";
                                        }
                                }
                                catch (Exception e)
                                {
                                        e.printStackTrace();
                                }
                        }

                        out.println("<td>");
                        out.println("&nbsp;" + desc);
                        out.println("</td>");
                }
                else
                {
                        out.println("<td >&nbsp;</td>");
                }

                out.println("</tr>");

                // Render the children of this node
                if (node.isExpanded())
                {
                        TreeControlNode[] children = node.findChildren();
                        int lastIndex = children.length - 1;
                        int newLevel = level + 1;

                        for (int i = 0; i < children.length; i++)
                        {
                                renderEndStudentSignUpTree(out, children[i], newLevel, width,
                                        i == lastIndex);
                        }
                }
        }

        private void renderSelectTree(JspWriter out, TreeControlNode node,
                                      int level, int width, boolean last) throws IOException
        {
                StyleConfig scf02 = new StyleConfig((HttpServletRequest) pageContext.getRequest());
                HttpServletResponse response = (HttpServletResponse) pageContext.getResponse();

                if ("ROOT-NODE".equalsIgnoreCase(node.getName()) &&
                        (node.getLabel() == null))
                {
                        // Render the children of this node
                        TreeControlNode[] children = node.findChildren();
                        int lastIndex = children.length - 1;
                        int newLevel = level + 1;

                        for (int i = 0; i < children.length; i++)
                        {
                                renderSelectTree(out, children[i], newLevel, width,
                                        i == lastIndex);
                        }

                        return;
                }

                // Render the beginning of this node
                ii++;
                trStyle = (trStyle.equals(StyleConstants.TR_STYLE2)
                        ? StyleConstants.TR_STYLE1 : StyleConstants.TR_STYLE2);
                out.println("<tr class=" + trStyle + ">");
                out.println("<td align='left'>");

                // Create the appropriate number of indents
                for (int i = 0; i < (level - 1); i++)
                {
                        out.print("&nbsp;&nbsp;&nbsp;");
                }

                String encodedNodeName = URLEncoder.encode(node.getName());

                String action = replace(getAction(), "${name}", encodedNodeName);
                String updateTreeAction = replace(getAction(), "tree=${name}",
                        "select=" + encodedNodeName);

                updateTreeAction = ((HttpServletResponse) pageContext.getResponse()).encodeURL(updateTreeAction);

                String[] tempArray = null;
                String nameTempString = node.getName();
                boolean isCatalog = false;

                String hyperlink = null;

                if (nameTempString.indexOf("/") > 0)
                {
                        tempArray = StringUtil.splitString(nameTempString, "/");

                        if (tempArray[0].equals("catalogIDs"))
                        {
                                isCatalog = true;
                        }

                        if (!isCatalog)
                        {
                                out.print("<input type=\"radio\"");
                                out.print(" name=\"relationID\" value=\"" + tempArray[1] +
                                        "\">");
                                iii++;

                                if (tempArray[0].equals("courseID"))
                                {
                                        out.print("<input type=\"hidden\" name=\"selectedType" +
                                                iii + "\" value=\"" +
                                                SecurityConstants.USER_COURSE_RELATION + "\">");
                                        out.print("<input type=\"hidden\" name=\"courseN" + iii +
                                                "\" value=\"" + node.getLabel() + "\">");
                                }
                                else if (tempArray[0].equals("certificateIDs"))
                                {
                                        out.print("<input type=\"hidden\" name=\"selectedType" +
                                                iii + "\" value=\"" +
                                                SecurityConstants.USER_CERTIFICATE_RELATION + "\">");
                                        out.print("<input type=\"hidden\" name=\"courseN" + iii +
                                                "\" value=\"" + node.getLabel() + "\">");
                                }
                        }
                }

                printNameString(out, node, isCatalog, updateTreeAction, response,
                        hyperlink, action);

                out.println("</td>");

                if (!isCatalog && node.isLeaf())
                {
                        String desc = "";
                        String id = "";

                        if (tempArray != null)
                        {
                                id = tempArray[1];

                                try
                                {
                                        if (tempArray[0].equals("courseID"))
                                        {
                                                CourseModel cm = CourseHelper.getCourse(Integer.parseInt(
                                                        id));

                                                if (cm != null)
                                                {
                                                        desc = cm.getDescription();
                                                }
                                        }
                                        else if (tempArray[0].equals("certificateIDs"))
                                        {
                                                CertForm cf = CertHelper.getCert(Integer.parseInt(id));

                                                if ((cf != null) && (cf.getDescription() != null))
                                                {
                                                        desc = cf.getDescription();
                                                }
                                        }
                                }
                                catch (CourseSysException cse)
                                {
                                        cse.printStackTrace();
                                }
                        }

                        out.println("<td>");
                        out.println("&nbsp;" + desc);
                        out.println("</td>");
                }
                else
                {
                        out.println("<td>&nbsp;</td>");
                }

                out.println("</tr>");

                // Render the children of this node
                if (node.isExpanded())
                {
                        TreeControlNode[] children = node.findChildren();
                        int lastIndex = children.length - 1;
                        int newLevel = level + 1;

                        for (int i = 0; i < children.length; i++)
                        {
                                renderSelectTree(out, children[i], newLevel, width,
                                        i == lastIndex);
                        }
                }
        }

        protected void render(JspWriter out, TreeControlNode node, int level,
                              int width, boolean last) throws IOException
        {
                StyleConfig scf02 = new StyleConfig((HttpServletRequest) pageContext.getRequest());
                HttpServletResponse response = (HttpServletResponse) pageContext.getResponse();

                // if the node is root node and the label value is
                // null, then do not render root node in the tree.
                if ("ROOT-NODE".equalsIgnoreCase(node.getName()) &&
                        (node.getLabel() == null))
                {
                        // Render the children of this node
                        TreeControlNode[] children = node.findChildren();
                        int lastIndex = children.length - 1;
                        int newLevel = level + 1;

                        for (int i = 0; i < children.length; i++)
                        {
                                render(out, children[i], newLevel, width, i == lastIndex);
                        }

                        return;
                }

                trStyle = (trStyle.equals(StyleConstants.TR_STYLE2)
                        ? StyleConstants.TR_STYLE1 : StyleConstants.TR_STYLE2);

                // Render the beginning of this node
                //out.println(intervalTR);
                ii++;
                out.println("  <tr class=" + trStyle + ">");
                out.println("<td align='left'>");

                // Create the appropriate number of indents
                for (int i = 0; i < (level - 1); i++)
                {
                        out.print("&nbsp;&nbsp;&nbsp;");
                }

                // Render the tree state image for this node
                // HACK to take into account special characters like = and &
                // in the node name, could remove this code if encode URL
                // and later request.getParameter() could deal with = and &
                // character in parameter values.
                String encodedNodeName = URLEncoder.encode(node.getName());
                String action = replace(getAction(), "${name}", encodedNodeName);
                String updateTreeAction = replace(getAction(), "tree=${name}",
                        "select=" + encodedNodeName);

                updateTreeAction = ((HttpServletResponse) pageContext.getResponse()).encodeURL(updateTreeAction);

                String actionString = getAction();
                String[] tempArray = null;
                String nameTempString = node.getName();
                boolean isCatalog = false;
                boolean isCertMaster = false;
                String allMasterInCertString = "/";

                if (actionString.indexOf(LMSConstants.TREE_CERT_MASTER) > 0)
                {
                        try
                        {
                                isCertMaster = true;

                                String masterID = "0";

                                if (para1 != null)
                                {
                                        masterID = para1;
                                }

                                CertWebImpl certWebImpl = new CertWebImpl();
                                List allMasterInCertList = certWebImpl.getCourseListFromCert(masterID,
                                        Integer.parseInt(para2));

                                for (int k = 0; k < allMasterInCertList.size(); k++)
                                {
                                        allMasterInCertString += (((MasterModel) allMasterInCertList.get(k)).getMasterID() +
                                                "/");
                                }
                        }
                        catch (CertDAOSysException e)
                        {
                                e.printStackTrace();
                        }
                }

                //create node's image
                String hyperlink = null;

                if (nameTempString.indexOf("/") > 0)
                {
                        tempArray = StringUtil.splitString(nameTempString, "/");

                        if (tempArray[0].equals("catalogIDs"))
                        {
                                isCatalog = true;
                        }

                        if (!isCertMaster || !isCatalog)
                        {
                                String disabled = "";

                                if (isCertMaster)
                                {
                                        if (allMasterInCertString.indexOf("/" + tempArray[1] + "/") >= 0)
                                        {
                                                disabled = "disabled";
                                        }
                                }

                                out.print("<input type=\"checkbox\"");
                                out.print(" name=\"" + tempArray[0] + "\" value=\"" +
                                        tempArray[1] + "\" " + disabled + ">");
                        }

                        if (isCatalog)
                        {
                                out.print("<input type=\"hidden\" name=\"selectedType" + (iii) +
                                        "\" value=\"0\">");
                        }
                        else
                        {
                                out.print("<input type=\"hidden\" name=\"selectedType" + (iii) +
                                        "\" value=\"1\">");
                                out.print("<input type=\"hidden\" name=\"courseN" + iii +
                                        "\" value=\"" + node.getLabel() + "\">");
                        }

                        iii++;
                }
                else
                {
                        out.print("<input type=\"checkbox\"");
                        out.print(" name=\"id\" value=\"" + nameTempString + "\">");
                }

                printNameString(out, node, isCatalog, updateTreeAction, response,
                        hyperlink, action);

                out.println("</td>");

                if (!isCatalog && node.isLeaf())
                {
                        MasterModel mm = null;
                        String code = "";
                        int type = 4;

                        try
                        {
                                if (tempArray != null)
                                {
                                        String masterID = tempArray[1];
                                        mm = CourseHelper.getMaster(Integer.parseInt(masterID));

                                        if (mm != null)
                                        {
                                                code = mm.getMasterCode();
                                                type = mm.getType();
                                        }
                                }
                        }
                        catch (CourseSysException cse)
                        {
                                cse.printStackTrace();
                        }

                        String typeStr = "";

                        if (type == SecurityConstants.USER_CERTIFICATE_RELATION)
                        {
                                typeStr = "远程培训项目";
                        }
                        else if (type == SecurityConstants.USER_OFFLINE_CERTIFICATE_RELATION)
                        {
                                typeStr = "脱产培训项目";
                        }

                        out.println("<td>");
                        out.println("&nbsp;" + code);
                        out.println("</td>");
                        out.println("<td>");
                        out.println("&nbsp;" + typeStr);
                        out.println("</td>");
                }
                else
                {
                        out.println("<td>&nbsp;</td>");
                        out.println("<td>&nbsp;</td>");
                }

                // Render the end of this node
                out.println("</tr>");

                // Render the children of this node
                if (node.isExpanded())
                {
                        TreeControlNode[] children = node.findChildren();
                        int lastIndex = children.length - 1;
                        int newLevel = level + 1;

                        for (int i = 0; i < children.length; i++)
                        {
                                render(out, children[i], newLevel, width, i == lastIndex);
                        }
                }
        }

        private void renderPuglishInTree(JspWriter out, TreeControlNode node,
                                         int level, int width, boolean last) throws IOException
        {
                StyleConfig scf02 = new StyleConfig((HttpServletRequest) pageContext.getRequest());
                HttpServletResponse response = (HttpServletResponse) pageContext.getResponse();

                // if the node is root node and the label value is
                // null, then do not render root node in the tree.
                if ("ROOT-NODE".equalsIgnoreCase(node.getName()) &&
                        (node.getLabel() == null))
                {
                        // Render the children of this node
                        TreeControlNode[] children = node.findChildren();
                        int lastIndex = children.length - 1;
                        int newLevel = level + 1;

                        for (int i = 0; i < children.length; i++)
                        {
                                renderPuglishInTree(out, children[i], newLevel, width,
                                        i == lastIndex);
                        }

                        return;
                }

                trStyle = (trStyle.equals(StyleConstants.TR_STYLE2)
                        ? StyleConstants.TR_STYLE1 : StyleConstants.TR_STYLE2);

                // Render the beginning of this node
                //out.println(intervalTR);
                ii++;
                trStyle = (trStyle.equals(StyleConstants.TR_STYLE2)
                        ? StyleConstants.TR_STYLE1 : StyleConstants.TR_STYLE2);
                out.println("<tr class=" + trStyle + ">");
                out.println("<td align='left'>");

                // Create the appropriate number of indents
                for (int i = 0; i < (level - 1); i++)
                {
                        out.print("&nbsp;&nbsp;&nbsp;");
                }

                // Render the tree state image for this node
                // HACK to take into account special characters like = and &
                // in the node name, could remove this code if encode URL
                // and later request.getParameter() could deal with = and &
                // character in parameter values.
                String encodedNodeName = URLEncoder.encode(node.getName());
                String action = replace(getAction(), "${name}", encodedNodeName);
                String updateTreeAction = replace(getAction(), "tree=${name}",
                        "select=" + encodedNodeName);

                updateTreeAction = ((HttpServletResponse) pageContext.getResponse()).encodeURL(updateTreeAction);

                String actionString = getAction();
                String[] tempArray = null;
                String nameTempString = node.getName();

                boolean isCatalog = false;

                //create node's image
                String hyperlink = null;

                if (nameTempString.indexOf("/") > 0)
                {
                        tempArray = StringUtil.splitString(nameTempString, "/");

                        if (tempArray[0].equals("catalogIDs"))
                        {
                                isCatalog = true;
                        }

                        if (!isCatalog)
                        {
                                out.print("<input type=\"radio\"");
                                out.print(" name=\"" + tempArray[0] + "\" value=\"" +
                                        tempArray[1] + "\">");
                        }
                }

                printNameString(out, node, isCatalog, updateTreeAction, response,
                        hyperlink, action);

                out.println("</td>");

                if (!isCatalog && node.isLeaf())
                {
                        MasterModel mm = null;
                        String description = "";

                        try
                        {
                                if (tempArray != null)
                                {
                                        String masterID = tempArray[1];
                                        mm = CourseHelper.getMaster(Integer.parseInt(masterID));

                                        if ((mm != null) && (mm.getDescription() != null))
                                        {
                                                description = mm.getDescription();
                                        }
                                }
                        }
                        catch (CourseSysException cse)
                        {
                                cse.printStackTrace();
                        }

                        out.println("<td>");
                        out.println("&nbsp;" + description);
                        out.println("</td>");
                }
                else
                {
                        out.println("<td>&nbsp;</td>");
                }

                // Render the end of this node
                out.println("</tr>");

                // Render the children of this node
                if (node.isExpanded())
                {
                        TreeControlNode[] children = node.findChildren();
                        int lastIndex = children.length - 1;
                        int newLevel = level + 1;

                        for (int i = 0; i < children.length; i++)
                        {
                                renderPuglishInTree(out, children[i], newLevel, width,
                                        i == lastIndex);
                        }
                }
        }

        private void printNameString(JspWriter out, TreeControlNode node,
                                     boolean isCatalog, String updateTreeAction,
                                     HttpServletResponse response, String hyperlink, String action)
                throws IOException
        {
                if ((action != null) && (isCatalog || !node.isLeaf()))
                {
                        out.print("<a href=\"");
                        out.print(response.encodeURL(action));
                        out.print("\"");
                        out.print(">");

                        out.print("<img src=\"");

                        if (node.isExpanded())
                        {
                                out.print(IMAGE_HANDLE_EXPANDED);
                        }
                        else
                        {
                                out.print(IMAGE_HANDLE_UNEXPANDED);
                        }

                        out.print("\" border=\"0\">");
                        out.print("</a>");
                }
                // Calculate the hyperlink for this node (if any)
                else if (!isCatalog && node.isLeaf())
                {
                        if (node.getAction() != null)
                        {
                                hyperlink = ((HttpServletResponse) pageContext.getResponse()).encodeURL(node.getAction());
                        }

                        if (node.getIcon() != null)
                        {
                                if (hyperlink != null)
                                {
                                        out.print("<a href=\"");
                                        out.print(hyperlink);
                                        out.print("\"");

                                        String target = node.getTarget();

                                        if (target != null)
                                        {
                                                out.print(" target=\"");
                                                out.print(target);
                                                out.print("\"");
                                        }

                                        out.print(">");
                                        out.print("<img src=\"");
                                        out.print(node.getIcon());
                                        out.print("\" border=\"0\">");
                                        out.print("</a>");
                                }
                        }
                }

                // Render the label for this node (if any)
                if (node.getLabel() != null)
                {
                        System.out.println(" node.getLabel()-=================== = " +
                                node.getLabel());

                        if (hyperlink != null)
                        {
                                // Note the leading space so that the text has some space
                                // between it and any preceding images
                                out.print("<a href=\"");
                                out.print(hyperlink);
                                out.print("\"");

                                String target = node.getTarget();

                                if (target != null)
                                {
                                        out.print(" target=\"");
                                        out.print(target);
                                        out.print("\"");
                                }

                                // to refresh the tree in the same 'self' frame
                                //out.print(" onclick=\"");
                                //out.print("self.location.href='" + updateTreeAction + "'");
                                //out.print("\"");
                                out.print(">");
                        }

                        if (node.isSelected() && node.isExpanded())
                        {
                                out.println("<B>");
                        }

                        out.print(node.getLabel());

                        if (node.isSelected() && node.isExpanded())
                        {
                                out.println("</B>");
                        }

                        if (hyperlink != null)
                        {
                                out.print("</a>");
                        }
                }
        }
}
