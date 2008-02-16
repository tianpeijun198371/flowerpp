/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-7-27
 * Time: 11:50:25
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.admin;

import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.core.util.StyleConfig;
import com.ulearning.ulms.core.util.StyleConstants;
import com.ulearning.ulms.organ.bean.OrganHelper;
import com.ulearning.ulms.organ.form.OrganForm;
import com.ulearning.ulms.util.treeControl.TreeBuilderFactory;

import org.apache.webapp.admin.TreeBuilder;
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


public class OrganTreeTag extends TreeControlTag
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
        static final String IMAGE_HANDLE_EXPANDED = "../../images/organ_open.gif";
        static final String IMAGE_HANDLE_UNEXPANDED = "../../images/organ_close.gif";
        static final String style = StyleConstants.LIST_FORM_TABLE_STYLE;
        private String trStyle = StyleConstants.TR_STYLE2;
        protected String images2;
        protected String intervalTR;
        private boolean blnDouble = false;
        private int ii = 0;

        public String getTrStyle()
        {
                return trStyle;
        }

        public void setTrStyle(String trStyle)
        {
                this.trStyle = trStyle;
        }

        public String getImages2()
        {
                return images2;
        }

        public void setImages2(String images2)
        {
                this.images2 = images2;
        }

        public String getIntervalTR()
        {
                return intervalTR;
        }

        public void setIntervalTR(String intervalTR)
        {
                this.intervalTR = intervalTR;
        }

        /* Render this tree control.
        *
        * @exception JspException if a processing error occurs
        */
        public int doEndTag() throws JspException
        {
                TreeControl treeControl = getTreeControl();
                JspWriter out = pageContext.getOut();

                try
                {
                        out.print("<table " + style + ">");

                        out.println("<tr>");
                        out.println("<th align='center'>单位名称</th>");
                        out.println("<th align='center'>单位编号</th>");
                        out.println("</tr>");

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

        protected TreeControl getTreeControl() throws JspException
        {
                Object treeControl = null;

                if (scope == null)
                {
                        HttpSession session = pageContext.getSession();

                        if ((session.getAttribute(tree + "type") != null) &&
                                ((String) session.getAttribute(tree + "type")).equals("0"))
                        {
                                treeControl = null;

                                //LogUtil.info("System","I am here 1");
                        }
                        else
                        {
                                treeControl = pageContext.getSession().getAttribute(tree);

                                //LogUtil.info("System","I am here 2");
                        }

                        if (treeControl == null)
                        {
                                TreeControl control = null;
                                TreeControlNode root = new TreeControlNode("ROOT-NODE", null,
                                        null, "setupTree.do?select=ROOT-NODE", "content", true,
                                        "Root");
                                control = new TreeControl(root);

                                TreeBuilder builder = TreeBuilderFactory.getTreeBuilder(tree, id);

                                if (builder != null)
                                {
                                        builder.buildTree(control, null,
                                                (HttpServletRequest) pageContext.getRequest());
                                }

                                treeControl = control;
                                session.setAttribute(tree, control);
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

        protected void render(JspWriter out, TreeControlNode node, int level,
                              int width, boolean last) throws IOException
        {
                StyleConfig scf = new StyleConfig((HttpServletRequest) pageContext.getRequest());
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

                // trStyle = (trStyle.equals(StyleConstants.TR_STYLE2) ? StyleConstants.TR_STYLE1 : StyleConstants.TR_STYLE2);
                // Render the beginning of this node
                //out.println(intervalTR);
                ii++;
                trStyle = (trStyle.equals(StyleConstants.TR_STYLE2)
                        ? StyleConstants.TR_STYLE1 : StyleConstants.TR_STYLE2);
                out.println(" <tr class=" + trStyle + ">");
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

                //create node's image
                String hyperlink = null;
                OrganForm of = null;

                if (nameTempString.indexOf("/") > 0)
                {
                        tempArray = StringUtil.splitString(nameTempString, "/");

                        if (tempArray != null)
                        {
                                String organ0ID = tempArray[1];
                                of = OrganHelper.getOrgan(Integer.parseInt(organ0ID));
                        }

                        if (!styleSelected.equals("0"))
                        {
                                //out.println("<input type=\"checkbox\"");
                                //out.println(" name=\"orgID\" value=\"" + tempArray[1] + "\">");
                                out.print("<input type=\"checkbox\" name =\"orgID\" value=\"" +
                                        tempArray[1] + "\">");
                                out.print("<input type= \"hidden\"  name=\"isAsp\"  value=\"" +
                                        of.getIsAsp() + "\">");
                        }
                }

                printNameString(out, node, isCatalog, updateTreeAction, response,
                        hyperlink, action);
                out.println("</td>");

                String orgNo = "";

                if (tempArray != null)
                {
                        String organ0ID = tempArray[1];
                        of = OrganHelper.getOrgan(Integer.parseInt(organ0ID));

                        if (of != null)
                        {
                                orgNo = of.getOrgNO();
                        }
                }

                out.println("<td align=\"center\" >");
                out.println("&nbsp;" + orgNo);
                out.println("</td>");
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

        /*原有方法
          protected void render(JspWriter out, TreeControlNode node,
                                int level, int width, boolean last)
                  throws IOException
          {
                  HttpServletResponse response =
                          (HttpServletResponse) pageContext.getResponse();
                  // if the node is root node and the label value is
                  // null, then do not render root node in the tree.
                  if ("ROOT-NODE".equalsIgnoreCase(node.getName()) &&
                          (node.getLabel() == null))
                  {
                          // Render the children of this node
                          TreeControlNode children[] = node.findChildren();
                          int lastIndex = children.length - 1;
                          int newLevel = level + 1;
                          for (int i = 0; i < children.length; i++)
                          {
                                  render(out, children[i], newLevel, width, i == lastIndex);
                          }
                          return;
                  }
                 // trStyle = (trStyle.equals(StyleConstants.TR_STYLE2) ? StyleConstants.TR_STYLE1 : StyleConstants.TR_STYLE2);
                  // Render the beginning of this node
                  //out.println(intervalTR);
                  out.println("  <tr>");
                  out.println("<td align='left'>");
                  // Create the appropriate number of indents
                  for (int i = 0; i < level; i++)
                          out.print("&nbsp;&nbsp;&nbsp;");
                  // Render the tree state image for this node
                  // HACK to take into account special characters like = and &
                  // in the node name, could remove this code if encode URL
                  // and later request.getParameter() could deal with = and &
                  // character in parameter values.
                  String encodedNodeName = URLEncoder.encode(node.getName());
                  String action = replace(getAction(), "${name}", encodedNodeName);
                  String updateTreeAction =
                          replace(getAction(), "tree=${name}", "select=" + encodedNodeName);
                  updateTreeAction =
                          ((HttpServletResponse) pageContext.getResponse()).
                          encodeURL(updateTreeAction);
                  String[] tempArray = null;
                  String nameTempString = node.getName();
                  boolean isCatalog = false;
                  //create node's image
                  String hyperlink = null;
                  OrganForm of = null;
                  if (nameTempString.indexOf("/") > 0)
                  {
                          tempArray = StringUtil.splitString(nameTempString, "/");
                          if (tempArray != null)
                          {
                                  String organ0ID = tempArray[1];
                                  of = OrganHelper.getOrgan(Integer.parseInt(organ0ID));
                          }
                          if(!styleSelected.equals("0"))
                          {
                                              //out.println("<input type=\"checkbox\"");
                                                  //out.println(" name=\"orgID\" value=\"" + tempArray[1] + "\">");
                                                  out.println("<input type=\"checkbox\" name =\"orgID\" value=\""+tempArray[1]+"\">");
                                                  out.println("<input type= \"hidden\"  name=\"isAsp\"  value=\""+of.getIsAsp()+"\">");
                          }
                  }
                  printNameString(out, node, isCatalog, updateTreeAction, response, hyperlink, action);
                  out.println("</td>");
                  String orgNo = "";
                  if (tempArray != null)
                  {
                          String organ0ID = tempArray[1];
                          of = OrganHelper.getOrgan(Integer.parseInt(organ0ID));
                          if (of != null)
                                  orgNo = of.getOrgNO();
                  }
                  out.println("<td " + StyleConstants.TD_STYLE2 + " align=\"center\">");
                  out.println("&nbsp;" + orgNo);
                  out.println("</td>");
                  // Render the end of this node
                  out.println("</tr>");
                  // Render the children of this node
                  if (node.isExpanded())
                  {
                          TreeControlNode children[] = node.findChildren();
                          int lastIndex = children.length - 1;
                          int newLevel = level + 1;
                          for (int i = 0; i < children.length; i++)
                          {
                                  render(out, children[i], newLevel, width, i == lastIndex);
                          }
                  }
          }
        */
        private void printNameString(JspWriter out, TreeControlNode node,
                                     boolean isCatalog, String updateTreeAction,
                                     HttpServletResponse response, String hyperlink, String action)
                throws IOException
        {
                if (!styleSelected.equals("0"))
                {
                        if (action != null)
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
                else
                {
                        if (action != null)
                        {
                                out.print("<a href=\"");
                                out.print(response.encodeURL(action));
                                out.print("\"");
                                out.print(" onclick=\"");
                                out.print("self.location.href='" + updateTreeAction + "'");
                                out.print("\">");

                                if (node.isSelected())
                                {
                                        out.println("<B>");
                                }

                                out.print(node.getLabel());

                                if (node.isSelected())
                                {
                                        out.println("</B>");
                                }

                                out.print("</a>");
                        }
                }
        }
}
