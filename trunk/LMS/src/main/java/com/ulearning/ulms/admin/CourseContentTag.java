/*
 * Copyright (c) 2004 Your Corporation. All Rights Reserved.
 */
package com.ulearning.ulms.admin;

import com.ulearning.ulms.core.security.bean.SecurityConstants;
import com.ulearning.ulms.core.security.bean.SecurityHelper;
import com.ulearning.ulms.core.util.*;
import com.ulearning.ulms.course.helper.CourseContentHelper;
import com.ulearning.ulms.course.model.CourseContentForm;
import com.ulearning.ulms.course.util.DefaultContextCont;
import com.ulearning.ulms.tools.upload.action.MultipartParamUtils;
import com.ulearning.ulms.util.LMSConstants;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.webapp.admin.TreeControl;
import org.apache.webapp.admin.TreeControlNode;
import org.apache.webapp.admin.TreeControlTag;

import java.io.IOException;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;


public class CourseContentTag extends TreeControlTag
{
        /**
         * The default directory name for icon images.
         */
        static final String DEFAULT_IMAGES = "images";
        static final String IMAGE_HANDLE_EXPANDED = Config.getContextRoot() +
                "/images/fopen.gif";
        static final String IMAGE_HANDLE_UNEXPANDED =
                Config.getContextRoot() + "/images/fclosed.gif";
        private String trStyle = StyleConstants.TR_STYLE2;
        private String nodeID;
        private String path;
        private int ii;
        private boolean blnDouble;
        protected String para1 = null;
        protected String para2 = null;
        protected String images2;
        protected String hasOperate = null;
        protected String intervalTR = null;
        private String style = StyleConstants.LIST_FORM_TABLE_STYLE;

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

        public int doEndTag() throws JspException
        {
                TreeControl treeControl = getTreeControl();
                JspWriter out = pageContext.getOut();
                HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
                String downLoad = (String) request.getAttribute("downLoad");

                try
                {
                        //判断权限
                        MultipartParamUtils mp = (MultipartParamUtils) request.getAttribute(
                                "mp");

                        if (mp == null)
                        {
                                mp = new MultipartParamUtils(request, 1024 * 1014 * 10);
                        }

                        int relationID = 0;

                        if ((mp.getParameter("courseID") != null) &&
                                !mp.getParameter("courseID").equals("null"))
                        {
                                relationID = Integer.parseInt(mp.getParameter("courseID"));
                        }

                        if ((relationID == 0) &&
                                (request.getAttribute("relationID") != null) &&
                                !request.getAttribute("relationID").equals("null"))
                        {
                                relationID = Integer.parseInt(request.getAttribute("relationID")
                                        .toString());
                        }

                        System.out.println("###mp.getParameter(\"courseID\") = " +
                                mp.getParameter("courseID"));
                        System.out.println("###mp.getParameter(\"relationID\") = " +
                                mp.getParameter("relationID"));

                        System.out.println("###request.getParameter(\"courseID\") = " +
                                request.getParameter("courseID"));
                        System.out.println("###request.getParameter(\"relationID\") = " +
                                request.getParameter("relationID"));

                        //out.print(relationID);
                        int userID = StringUtil.parseInt((String) request.getSession()
                                .getAttribute(LMSConstants.USERID_KEY));
                        boolean isAdmin = SecurityHelper.isHasPermission(userID,
                                relationID, SecurityConstants.USER_COURSE_RELATION,
                                SecurityConstants.COURSE_CONTENT_MANAGE);

                        out.print("<table " + style + ">");
                        out.println("<tr>");
                        out.println("<th align='center'>名称</th>");

                        if (isAdmin)
                        {
                                out.println("<th align='center'>修改时间</th>");
                                out.println("<th align='center'>学生能否访问</th>");
                        }
                        else
                        {
                                out.println("<th align='center'>创建时间</th>");
                        }

                        if (downLoad.equals("1"))
                        {
                                out.println("<th align='center'>在线下载</th>");
                        }

                        out.println("</tr>");

                        int level = 0;
                        TreeControlNode node = treeControl.getRoot();
                        render(out, node, level, treeControl.getWidth(), true, isAdmin);
                        out.println("</table>");
                }
                catch (IOException e)
                {
                        throw new JspException(e);
                }

                return (EVAL_PAGE);
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
                                DefaultContextCont df = new DefaultContextCont(pageContext);
                                HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
                                treeControl = df.getTree(tree, request);
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
                        //   System.out.println("houhx===============treeControl=" + treeControl);
                        return (TreeControl) treeControl;
                }
        }

        protected void render(JspWriter out, TreeControlNode node, int level,
                              int width, boolean last, boolean isAdmin) throws IOException
        {
                StyleConfig scf = new StyleConfig((HttpServletRequest) pageContext.getRequest());
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
                                render(out, children[i], newLevel, width, i == lastIndex,
                                        isAdmin);
                        }

                        return;
                }

                String encodedNodeName = URLEncoder.encode(node.getName());
                String action = replace(getAction(), "${name}", encodedNodeName);
                String updateTreeAction = replace(getAction(), "tree=${name}",
                        "select=" + encodedNodeName);

                updateTreeAction = ((HttpServletResponse) pageContext.getResponse()).encodeURL(updateTreeAction);

                String[] tempArray = null;
                String nameTempString = node.getName();
                boolean isCatalog = false;

                //create node's image
                String hyperlink = null;
                CourseContentForm of = null;
                ii++;
                trStyle = (trStyle.equals(StyleConstants.TR_STYLE2)
                        ? StyleConstants.TR_STYLE1 : StyleConstants.TR_STYLE2);

                if (nameTempString.indexOf("/") > 0)
                {
                        tempArray = StringUtil.splitString(nameTempString, "/");

                        if (tempArray[0].equals("nodeID"))
                        {
                                isCatalog = true;
                        }

                        if (tempArray != null)
                        {
                                nodeID = tempArray[1];
                                of = CourseContentHelper.getCourseContent(nodeID);
                        }

                        if (styleSelected.equals("0") && (of.getIsUserful() == 0))
                        { //对没有管理权限的用户来说,不能访问设置为学生不能访问的文档

                                return;
                        }

                        out.println("<tr class=" + trStyle + ">");
                        out.println("<td  align='left'>");

                        // Create the appropriate number of indents
                        for (int i = 0; i < (level - 1); i++)
                        {
                                out.print("&nbsp;&nbsp;&nbsp;");
                        }

                        if (isAdmin)
                        {
                                out.print("<input type=\"checkbox\"");
                                out.print(" name=\"nodeID\" value=\"" + tempArray[1] + "\">");
                        }

                        int nodeType = of.getNodetype();

                        if (of.getIsHyperLink() == 1)
                        {
                                path = of.getLink();
                        }
                        else
                        {
                                path = Config.getUploadVirtualPath() + of.getLink();
                        }

                        if (isCatalog)
                        {
                                out.print("<input type=\"hidden\" name=\"selectedType" +
                                        "\" value=\"0\">");
                        }
                        else
                        {
                                out.print("<input type=\"hidden\" name=\"selectedType" +
                                        "\" value=\"1\">");
                        }

                        out.print("<input type=\"hidden\" name=\"nodeType" + "\" value=\"" +
                                nodeType + "\">");
                }

                printNameString(out, node, isCatalog, updateTreeAction, response,
                        hyperlink, action);

                String vpath = CourseContentHelper.getCourseContentURL(of.getNodeID());

                out.println("</td>");
                out.println("<td align=\"center\">");

                if (isAdmin)
                {
                        out.println("&nbsp;" +
                                I18Util.FormatDateTime(of.getLastModDate(), pageContext));
                }
                else
                {
                        out.println("&nbsp;" +
                                I18Util.FormatDateTime(of.getCreateDate(), pageContext));
                }

                out.println("</td>");

                if (isAdmin)
                {
                        out.println("<td align=\"center\">");

                        String temp = "";

                        if (of.getIsUserful() == 1)
                        {
                                temp = "可以";
                        }
                        else
                        {
                                temp = "不可";
                        }

                        out.println("&nbsp;" + temp);
                        out.println("</td>");
                }

                HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
                String downLoad = (String) request.getAttribute("downLoad");

                if (downLoad.equals("1") && (vpath != null))
                {
                        String url = "<script>document.write(\"<a href='<%=request.getContextPath()%>/fluxControl?nodeID=" +
                                of.getNodeID() +
                                "\"+download_url+\"'>下载</a>\");</script></td>";
                        out.println("<td " + StyleConstants.TD_STYLE2 +
                                " align=\"center\">");
                        out.print(url);

                        out.println("</td>");
                }
                else if (downLoad.equals("1"))
                {
                        out.println("<td " + StyleConstants.TD_STYLE2 +
                                " align=\"center\">");
                        out.println("&nbsp;");
                        out.println("</td>");
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
                                render(out, children[i], newLevel, width, i == lastIndex,
                                        isAdmin);
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
                        out.print(" onclick=\"");
                        out.print("self.location.href='" + updateTreeAction + "'");
                        out.print("\">");

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
                        out.print("</a> ");
                }
                // Calculate the hyperlink for this node (if any)
                else if (!isCatalog && node.isLeaf())
                {
                        if (node.getAction() != null)
                        {
                                hyperlink = ((HttpServletResponse) pageContext.getResponse()).encodeURL(node.getAction());
                        }

                        //     LogUtil.info("CouseContent","node.getIcon()="+node.getIcon());
                        if (node.getIcon() != null)
                        {
                                // LogUtil.info("CouseContent","hyperlink==========="+hyperlink);
                                if (hyperlink != null)
                                {
                                        out.print("<a href=\"");
                                        out.print(hyperlink);
                                        out.print("\"");

                                        String target = node.getTarget();

                                        //   LogUtil.info("CouseContent","target==========="+target);
                                        if (target != null)
                                        {
                                                out.print(" target=\"");
                                                out.print(target);
                                                out.print("\"");
                                        }

                                        // to refresh the tree in the same 'self' frame
                                        out.print(" onclick=\"");
                                        out.print("self.location.href='" + updateTreeAction + "'");
                                        out.print("\">");
                                        out.print("<img src=\"");
                                        out.print(node.getIcon());
                                        out.print("\" border=\"0\">");
                                        out.print("</a> ");
                                }
                        }
                }

                // Render the label for this node (if any)
                if (node.getLabel() != null)
                {
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
                                out.print(" onclick=\"");
                                out.print("self.location.href='" + updateTreeAction + "'");
                                out.print("\"");
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
