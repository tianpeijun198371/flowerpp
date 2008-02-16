/** * TableConfig.java.
 * User: xiejh  Date: 2004-6-29 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.core.util;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;


public class StyleConfig
{
        int tableRow = 0;
        int tableCol = 0;
        String trStyle = null;
        String tdStyle = null;
        boolean blnDoubleTD = false;
        String tableStyle = " width=90% border=\"1\" align=\"center\" cellpadding=\"2\" cellspacing=\"0\"  " +
                " bordercolorlight=\"#FFFFFF\" bordercolordark=\"#000000\" class=\"table_global\"";

        //String formTableStyle=" width=90% border=\"1\" align=\"center\" cellpadding=\"2\" cellspacing=\"0\"  "
        //                         +" bordercolorlight=\"#FFFFFF\" bordercolordark=\"#000000\" class=\"table_global\""; //

        /**
         * 增加表单的样式里面table的样式
         */
        String addformTableStyle = " border=\"0\" cellpadding=\"4\" cellspacing=\"1\" class=\"tableadd\"";

        /**
         * View Form table 样式
         */
        String viewFormTableStyle = " border=\"0\" cellpadding=\"4\" cellspacing=\"1\" class=\"tableview\"";

        /**
         * 导般条的table 样式
         */
        String navigatorTableStyle = " width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"link01\"";

        /**
         * List table 样式
         */
        String listFormTbleStyle = " border=\"0\" cellpadding=\"4\" cellspacing=\"1\"    class=\"tablemain\"";

        /**
         * 标题和提交按钮的table 样式
         */
        String subButtonTable = " width=\"100%\" border=\"0\" cellpadding=\"4\" cellspacing=\"0\"";

        /**
         * 功能按钮所在的table 的样式
         */
        String funButtonTable = "width=\"100%\" border=\"0\" cellpadding=\"4\" cellspacing=\"0\" class=\"button02\"";
        String HR_STYLE = "<hr width=\"100%\" size=\"1\" noshade color=\"#9C9A9C\">";
        HttpServletRequest request = null;

        public StyleConfig(HttpServletRequest rq)
        {
                request = rq;
        }

        public String configTitle()
        {
                return "";
        }

        public String configCssFile()
        {
                //todo this config file will retrivel from database
                String cssFile = "norm0.css";

                /*HashMap myConfig = (HashMap)request.getSession(true).getAttribute("myConfig");
                  if  (myConfig!=null)
                  {
                          String cssFileNo= (String)myConfig.get("cssFileNo");
                          if (cssFileNo==null||cssFileNo.equals("null")||cssFileNo.equals(""))
                          {
                                  //cssFile="norm.css" ;
                          }
                          else
                          {
                                  cssFile="norm"+cssFileNo+".css";
                          }
                  }
                */
                return cssFile;
        }

        public String configFrameCssFile()
        {
                String cssFile = "top0.css";

                /*
                  HashMap myConfig = (HashMap)request.getSession(true).getAttribute("myConfig");
                  if  (myConfig!=null)
                  {
                          String cssFileNo= (String)myConfig.get("cssFileNo");
                          if (cssFileNo==null||cssFileNo.equals("null")||cssFileNo.equals(""))
                          {
                                  //cssFile="index.css" ;
                          }
                          else
                          {
                                  cssFile="top"+cssFileNo+".css";
                          }
                  }
                */
                return cssFile;
        }

        public String configMyStyle()
        {
                return "";
        }

        public String printTR(int n)
        {
                /* String trStr="<tr><td height=\"0\" class=\"intervalTDStyle1\" colspan=\""+n+"\">"
             +"<img src=\"../../images/spacer.gif\"  height=\"0\"></td></tr>";*/
                String trStr = "";

                return trStr;
        }

        public String printFormTR(int n)
        {
                String trStr = "<tr><td height=\"1\"><img src=\"../../images/spacer.gif\"  height=\"0\"></td>" +
                        "<td height=\"0\" class=\"intervalTDStyle1\" colspan=\"" + (n - 1) +
                        "\">" +
                        "<img src=\"../../images/spacer.gif\"  height=\"0\"></td></tr>";
                trStr = "";

                return trStr;
        }

        public String printTRStyle()
        {
                tableRow++;
                tableCol = 0;

                if (tableRow == 1)
                {
                        trStyle = " class=tableTitleStyle ";
                }
                else if ((tableRow % 2) == 0)
                {
                        trStyle = " class=tableTRStyle0 ";
                }
                else
                {
                        trStyle = " class=tableTRStyle1 ";
                }

                trStyle = "";

                return trStyle;
        }

        //增加树的样式控制
        public String printTreeTRStyle(boolean blnDouble)
        {
                if (blnDouble)
                {
                        tdStyle = "td1";
                }
                else
                {
                        tdStyle = "td2";
                }

                return tdStyle;
        }

        public String printTDStyle()
        {
                tableCol++;

                //if ((table_col % 2)==0)
                if (tableCol == 1)
                {
                        tdStyle = " class=tableTDStyle0 ";
                }
                else
                {
                        tdStyle = " class=tableTDStyle1 ";
                }

                trStyle = "";

                return tdStyle;
        }

        public String printTDStyle1()
        {
                tableCol++;

                //if ((table_col % 2)==0)
                if (blnDoubleTD)
                {
                        blnDoubleTD = false;
                        tdStyle = " class=tableTDStyle1 ";
                }
                else
                {
                        blnDoubleTD = true;
                        tdStyle = " class=tableTDStyle0 ";
                }

                tdStyle = "";

                return tdStyle;
        }

        public int getTableRow()
        {
                return tableRow;
        }

        public void setTableRow(int tableRow)
        {
                this.tableRow = tableRow;
        }

        public int getTableCol()
        {
                return tableCol;
        }

        public void setTableCol(int tableCol)
        {
                this.tableCol = tableCol;
        }

        public String getTableStyle()
        {
                return tableStyle;
        }

        public String getFunTableStyle()
        {
                return subButtonTable;
        }

        public void setTableStyle(String tableStyle)
        {
                this.tableStyle = tableStyle;
        }

        public String getFormTableStyle()
        {
                return addformTableStyle;
        }

        public String getAddformTableStyle()
        {
                return addformTableStyle;
        }

        public void setAddformTableStyle(String addformTableStyle)
        {
                this.addformTableStyle = addformTableStyle;
        }

        public String getViewFormTableStyle()
        {
                return viewFormTableStyle;
        }

        public void setViewFormTableStyle(String viewFormTableStyle)
        {
                this.viewFormTableStyle = viewFormTableStyle;
        }

        public String getNavigatorTableStyle()
        {
                return navigatorTableStyle;
        }

        public void setNavigatorTableStyle(String navigatorTableStyle)
        {
                this.navigatorTableStyle = navigatorTableStyle;
        }

        public String getListFormTbleStyle()
        {
                return listFormTbleStyle;
        }

        public void setListFormTbleStyle(String listFormTbleStyle)
        {
                this.listFormTbleStyle = listFormTbleStyle;
        }

        public String getSubButtonTable()
        {
                return subButtonTable;
        }

        public void setSubButtonTable(String subButtonTable)
        {
                this.subButtonTable = subButtonTable;
        }

        public String getFunButtonTable()
        {
                return funButtonTable;
        }

        public void setFunButtonTable(String funButtonTable)
        {
                this.funButtonTable = funButtonTable;
        }
}
