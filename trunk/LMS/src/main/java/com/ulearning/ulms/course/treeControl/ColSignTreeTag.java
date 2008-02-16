/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-8-18
 * Time: 11:45:14
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.course.treeControl;

import com.ulearning.ulms.admin.certificate.bean.CertHelper;
import com.ulearning.ulms.admin.certificate.exceptions.CertDAOSysException;
import com.ulearning.ulms.admin.certificate.form.CertForm;
import com.ulearning.ulms.admin.certificate.webimpls.CertWebImpl;
import com.ulearning.ulms.core.context.DefaultContext;
import com.ulearning.ulms.core.security.bean.SecurityConstants;
import com.ulearning.ulms.core.util.Config;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.core.util.StyleConstants;
import com.ulearning.ulms.course.exceptions.CourseSysException;
import com.ulearning.ulms.course.helper.CourseHelper;
import com.ulearning.ulms.course.model.CourseModel;
import com.ulearning.ulms.course.model.MasterModel;
import com.ulearning.ulms.course.util.CourseKeys;
import com.ulearning.ulms.util.LMSConstants;

import org.apache.webapp.admin.TreeControl;
import org.apache.webapp.admin.TreeControlNode;
import org.apache.webapp.admin.TreeControlTag;

import java.io.IOException;

import java.net.URLEncoder;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;


public class ColSignTreeTag extends TreeControlTag
{
        static final String DEFAULT_IMAGES = "images";

        //private String intervalTR = getIntervalTR();
        /**
         * The names of tree state images that we need.
         */

        //HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
        static final String IMAGE_HANDLE_EXPANDED =  Config.getContextRoot() +
                "/images/fopen.gif";
        static final String IMAGE_HANDLE_UNEXPANDED = 
                Config.getContextRoot() + "/images/fclosed.gif";
        private String trStyle = StyleConstants.TR_STYLE2;
        private int iii = 0;
        protected String para1 = null;
        protected String hasOperate = null;

        public String getPara1()
        {
                return para1;
        }

        public void setPara1(String para1)
        {
                this.para1 = para1;
        }

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
                                DefaultContext df = new DefaultContext(pageContext);
                                treeControl = df.getTree(tree,
                                        (HttpServletRequest) pageContext.getRequest());
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

        public int doEndTag() throws JspException
        {
                TreeControl treeControl = getTreeControl();
                JspWriter out = pageContext.getOut();

                try
                {
                        out.print("<table " + style + ">");
                        out.println("<tr " + StyleConstants.TITLE_STYLE + ">");
                        out.println("<td align='center'>名&nbsp;&nbsp;称</td>");
                        out.println("<td align='center'>编&nbsp;&nbsp;号</td>");
                        out.println("<td align='center'>开始注册时间</td>");
                        out.println("<td align='center'>结束注册时间</td>");
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

                return (EVAL_PAGE);
        }

        protected void render(JspWriter out, TreeControlNode node, int level,
                              int width, boolean last) throws IOException
        {
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
                out.println("<tr class=" + trStyle + ">");
                out.println("<td>");

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
                String allSelInColString = "/";

                /*try
                {
                        String masterID = "0";
                        if(para1 != null)
                                masterID = para1;
                        CertWebImpl certWebImpl = new  CertWebImpl();
                        List allMasterInCertList = certWebImpl.getCourseListFromCert(masterID,Integer.parseInt(para1));
                        for(int k = 0 ;k < allMasterInCertList.size();k++)
                        {
                                allSelInColString += ((MasterModel)allMasterInCertList.get(k)).getMasterID()+"/";
                        }
                }catch(CertDAOSysException e)
                {
                        e.printStackTrace();
                }*/

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
                                String checked = "";

                                if (allSelInColString.indexOf("/" + tempArray[1] + "/") >= 0)
                                {
                                        checked = "checked";
                                }

                                out.print("<input type=\"checkbox\"");
                                out.print(" name=\"" + tempArray[0] + "\" value=\"" +
                                        tempArray[1] + "\" " + checked + ">");
                        }

                        if (tempArray[0].equals("courseID"))
                        {
                                out.print("<input type=\"hidden\" name=\"selectedType" + iii +
                                        "\" value=\"" + SecurityConstants.USER_COURSE_RELATION +
                                        "\">");
                        }
                        else if (tempArray[0].equals("certificateIDs"))
                        {
                                out.print("<input type=\"hidden\" name=\"selectedType" + iii +
                                        "\" value=\"" +
                                        SecurityConstants.USER_CERTIFICATE_RELATION + "\">");
                        }
                }

                printNameString(out, node, isCatalog, updateTreeAction, response,
                        hyperlink, action);

                out.println("</td>");

                if (!isCatalog && node.isLeaf())
                {
                        String desc = "";
                        String regStartTime = "";
                        String regEndTime = "";
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

                                                if (cf != null)
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

                        out.println("<td " + StyleConstants.TD_STYLE2 +
                                " align=\"center\">");
                        out.println("&nbsp;" + desc);
                        out.println("</td>");
                }
                else
                {
                        out.println("<td " + StyleConstants.TD_STYLE2 +
                                " align=\"center\">&nbsp;</td>");
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

                                        // to refresh the tree in the same 'self' frame
                                        out.print(" onclick=\"");
                                        out.print("self.location.href='" + updateTreeAction + "'");
                                        out.print("\">");
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
                        if (hyperlink != null)
                        {
                                // Note the leading space so that the text has some space
                                // between it and any preceding images
                                out.print(" <a href=\"");
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

                        if (node.isSelected())
                        {
                                out.println("<B>");
                        }

                        out.print(node.getLabel());

                        if (node.isSelected())
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
