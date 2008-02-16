package com.ulearning.ulms.core.excel;

import com.ulearning.ulms.core.util.DateTimeUtil;
import com.ulearning.ulms.core.util.StringUtil;

import org.apache.poi.hssf.usermodel.*;

/**
 * <p>Title: education</p>
 * <p>Description: ulms</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: edu-edu</p>
 * @author Xiejh
 * @version 1.0
 */
import java.io.*;

import java.util.Date;


public class DoExcel
{
        public DoExcel()
        {
        }

        //将html格式的字符串转为指定格式的字符串
        public static String formatStr(String str, String flag1, String flag2)
        {
                int index = 0;
                //String flag1="///";
                //String flag2="***";
                //str=str.toLowerCase();
                str = StringUtil.replaceString(str, "</tr>", "");
                str = StringUtil.replaceString(str, "</td>", "");
                str = StringUtil.replaceString(str, "</TR>", "");
                str = StringUtil.replaceString(str, "</TD>", "");
                str = StringUtil.replaceString(str, "</Tr>", "");
                str = StringUtil.replaceString(str, "</Td>", "");
                str = StringUtil.replaceString(str, "</tR>", "");
                str = StringUtil.replaceString(str, "</tD>", "");

                str = StringUtil.replaceString(str, "</th>", "");
                str = StringUtil.replaceString(str, "</Th>", "");
                str = StringUtil.replaceString(str, "</Th>", "");
                str = StringUtil.replaceString(str, "</TH>", "");

                str = StringUtil.replaceString(str, "</table>", "");
                str = StringUtil.replaceString(str, "</TABLE>", "");
                str = StringUtil.replaceString(str, "<TBODY>", "");
                str = StringUtil.replaceString(str, "</TBODY>", "");
                str = StringUtil.replaceString(str, "&nbsp;", "");
                str = StringUtil.replaceString(str, "&gt;", "");
                str = StringUtil.replaceString(str, "　", "");

                str = StringUtil.replaceString(str, "<TR", "<tr");
                str = StringUtil.replaceString(str, "<TD", "<td");
                str = StringUtil.replaceString(str, "<tR", "<tr");
                str = StringUtil.replaceString(str, "<tD", "<td");
                str = StringUtil.replaceString(str, "<Tr", "<tr");
                str = StringUtil.replaceString(str, "<Td", "<td");

                str = StringUtil.replaceString(str, "<TH", "<th");
                str = StringUtil.replaceString(str, "<Th", "<th");
                str = StringUtil.replaceString(str, "<tH", "<th");

                //str=StringUtil.replaceString(str,"<tr",flag1);
                //str=StringUtil.replaceString(str,"<td",flag2);
                if (str.indexOf("<table") != -1)
                {
                        int tmp1 = str.indexOf("<table", index);
                        int tmp2 = str.indexOf(">", tmp1);
                        str = str.substring(0, tmp1) + str.substring(tmp2 + 1);
                }

                if (str.indexOf("<TABLE") != -1)
                {
                        int tmp1 = str.indexOf("<TABLE", index);
                        int tmp2 = str.indexOf(">", tmp1);
                        str = str.substring(0, tmp1) + str.substring(tmp2 + 1);
                }

                //System.out.println(str);
                while (str.indexOf("<tr") != -1)
                {
                        int tmp1 = str.indexOf("<tr", index);
                        int tmp2 = str.indexOf(">", tmp1);
                        str = str.substring(0, tmp1) + flag1 + str.substring(tmp2 + 1);
                }

                while (str.indexOf("<th") != -1)
                {
                        int tmp1 = str.indexOf("<th", index);
                        int tmp2 = str.indexOf(">", tmp1);
                        str = str.substring(0, tmp1) + flag2 + str.substring(tmp2 + 1);
                }

                //System.out.println(str);
                while (str.indexOf("<td") != -1)
                {
                        int tmp1 = str.indexOf("<td", index);
                        int tmp2 = str.indexOf(">", tmp1);
                        str = str.substring(0, tmp1) + flag2 + str.substring(tmp2 + 1);
                }

                //System.out.println(str);
                //删除表格内容中的html标记
                while (str.indexOf("<") != -1)
                {
                        int tmp1 = str.indexOf("<", index);
                        int tmp2 = str.indexOf(">", tmp1);
                        str = str.substring(0, tmp1) + str.substring(tmp2 + 1);
                }

                return str;
        }

        public static String produceExcel(String title, String source_str,
                                          String sp1, String sp2, String dis_file_path)
        {
                String strFlag = "";

                try
                {
                        HSSFWorkbook wb = new HSSFWorkbook();
                        HSSFSheet sheet = wb.createSheet();

                        HSSFRow row = null;
                        HSSFRow row1 = null;
                        HSSFCell cell = null;
                        HSSFCell cell1 = null;
                        HSSFCellStyle cellStyle = wb.createCellStyle();

                        cellStyle.setBorderBottom((short) 1);
                        cellStyle.setBorderLeft((short) 1);
                        cellStyle.setBorderRight((short) 1);
                        cellStyle.setBorderTop((short) 1);

                        //row1 = sheet.getRow(1);
                        //cell1 = row1.getCell((short)0);
                        String columns = "";
                        int number = 1;
                        int j = 1; //开始行号
                        int k = 0; //开始列号
                        //添加标题

                        number++;
                        row = sheet.createRow(j);
                        cell = row.createCell((short) k);
                        cell.setCellStyle(cellStyle);
                        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                        cell.setEncoding(HSSFCell.ENCODING_UTF_16);
                        cell.setCellValue(title);
                        k++;

                        //添加表内容
                        String[] row_str = StringUtil.splitString(source_str, sp1);

                        for (int r = 1; r < row_str.length; r++)
                        {
                                number++;
                                row = sheet.createRow(number);

                                String[] col_str = StringUtil.splitString(row_str[r], sp2);

                                for (int c = 1; c < col_str.length; c++)
                                {
                                        cell = row.createCell((short) (c - 1));
                                        cell.setCellStyle(cellStyle);
                                        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                                        cell.setEncoding(HSSFCell.ENCODING_UTF_16);
                                        cell.setCellValue(col_str[c].trim());
                                }
                        }

                        HSSFCellStyle cellStyle1 = wb.createCellStyle();

                        // Write the output to a file
                        FileOutputStream fileOut = new FileOutputStream(dis_file_path);
                        wb.write(fileOut);
                        fileOut.close();
                        strFlag = "1";
                }
                catch (Exception e)
                {
                        System.err.println("" + e);
                        strFlag = "-1";
                }

                return strFlag;
        }

        public static String getExcel(String title, String table_str,
                                      String filePath)
        {
                String res = "";
                String flag1 = "///";
                String flag2 = "***";
                Date d = new Date();
                String fileName = DateTimeUtil.FormatDateToWeb4(d) +
                        StringUtil.randomStr(6) + ".xls";
                String str1 = formatStr(table_str, flag1, flag2);
                String t1 = produceExcel(title, str1, flag1, flag2, filePath +
                        fileName);

                if (t1.equals("1"))
                {
                        res = fileName;
                }

                return res;
        }

        public static void main(String[] args)
        {
                //String tb="<TABLE><TR><TD>1</TD><TD>2</TD></TR><TR><TD>3</TD><TD>4</TD></TR></TABLE>";
                String tb = "<table border=1 cellspacing=0 cellpadding=0  bordercolorlight=#000000 bordercolordark=#FFFFFF>" +
                        "" + "<tr class=table-td>" +
                        "<th rowspan=2 class=table-td> 分类</td> " +
                        "<th rowspan=2 class=table-td> 主业关联职工人数</td> " +
                        "<th colspan=2 class=table-td> 资格培训（人次）</td> " +
                        "<th colspan=2 class=table-td> 适应性培训（人次）</td>" +
                        "<th colspan=2 class=table-td> 技术等级培训（人次）</td>" +
                        "<th colspan=2 class=table-td> 其他培训（人次）</td>" +
                        " <th rowspan=2 class=table-td> 出国培训（人次）</td>" +
                        "<th rowspan=2 class=table-td> 培训人次小计</td>" +
                        "<th rowspan=2 class=table-td> 内培人次</td> " +
                        "<th rowspan=2 class=table-td> 培训人数</td> " +
                        "<td rowspan=2 class=table-td> 网络培训折合课时</th> " +
                        "<td rowspan=2 class=table-td> 生产培训折合课时</th> " +
                        "<td rowspan=2 class=table-td> 累计培训1课时以上人数</th> " +
                        "<td rowspan=2 class=table-td> 1课时以上培训率（%）</th>" + "</tr>" +
                        "<tr>" + "<th class=table-td>外培</th> " +
                        "<th class=table-td>内培</th> " + "<th class=table-td>外培</th> " +
                        "<th class=table-td>内培</th>" + "<th class=table-td>外培</th>  " +
                        "<th class=table-td>内培</th> " + "<th class=table-td>外培</th> " +
                        "<td class=table-td>内培</th> " + " </tr> " + " <tr class=tdcolor> " +
                        " <td valign=top class=table-td>DC1</td> " +
                        "  <td valign=top>5</td> " + " <td valign=top>0</td> " +
                        " <td valign=top>8</td> " + " <td valign=top>5</td> " +
                        " <td valign=top>3</td> " + " <td valign=top>1</td>  " +
                        " <td valign=top>2</td> " + " <td valign=top>2</td> " +
                        " <td valign=top>0</td>  " + " <td valign=top>1</td> " +
                        " <td valign=top>22</td>" + " <td valign=top>13</td> " +
                        " <td valign=top>2</td> " + " <td valign=top>152.0</td> " +
                        " <td valign=top>32.0</td>" + " <td valign=top>5</td>  " +
                        " <td valign=top>100.00%</td> " + " </tr>  " +
                        "<tr class=tdcolor>" + "<td valign=top class=table-td>DC2</td>" +
                        "<td valign=top>2</td> " + "<td valign=top>1</td> " +
                        "<td valign=top>0</td> " + "<td valign=top>1</td>  " +
                        "<td valign=top>1</td>  " + "<td valign=top>1</td>  " +
                        "<td valign=top>1</td>  " + "<td valign=top>1</td>  " +
                        "<td valign=top>2</td>  " + "<td valign=top>1</td>  " +
                        "<td valign=top>9</td>  " + "<td valign=top>4</td>  " +
                        "<td valign=top>1</td>  " + "<td valign=top>0</td>  " +
                        "<td valign=top>0</td>  " + " <td valign=top>0</td>  " +
                        " <td valign=top>0.00%</td>" + "</tr> " + "<tr class=tdcolor> " +
                        " <td valign=top class=table-td>DC</td>   " +
                        " <td valign=top>0</td>" + " <td valign=top>0</td> " +
                        " <td valign=top>0</td> " + " <td valign=top>0</td>" +
                        " <td valign=top>0</td>" + "<td valign=top>0</td>" +
                        " <td valign=top>0</td> " + " <td valign=top>0</td> " +
                        " <td valign=top>0</td> " + " <td valign=top>0</td> " +
                        " <td valign=top>0</td> " + " <td valign=top>0</td> " +
                        " <td valign=top>0</td> " + "<td valign=top>0</td>" +
                        "<td valign=top>0</td> " + "<td valign=top>0</td> " +
                        "<td valign=top>0.00%</td> " + "</tr>  " + "<tr class=tdcolor>" +
                        " <td valign=top class=table-td> 总计</td>  " +
                        " <td valign=top>7</td>  " + " <td valign=top>1</td>  " +
                        " <td valign=top>8</td>  " + " <td valign=top>6</td>  " +
                        " <td valign=top>4</td>  " + " <td valign=top>2</td>  " +
                        " <td valign=top>3</td>  " + " <td valign=top>3</td>  " +
                        " <td valign=top>2</td>  " + " <td valign=top>2</td>  " +
                        " <td valign=top>31</td> " + " <td valign=top>17</td> " +
                        " <td valign=top>3</td>  " + " <td valign=top>152.0</td>" +
                        " <td valign=top>32.0</td> " + " <td valign=top>5</td>" +
                        " <td valign=top>71.43%</td> " + "</tr>" + "</table>";
                System.out.println(DoExcel.getExcel("aa", tb, "D:\\TEMP\\"));
        }
}
