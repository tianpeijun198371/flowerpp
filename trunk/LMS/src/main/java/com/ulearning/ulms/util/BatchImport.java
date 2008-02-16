/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-6-29
 * Time: 12:20:51
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.util;


import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.List;

public class BatchImport
{
        protected HSSFWorkbook wb = null;
        protected HSSFSheet sheet = null;
        protected HSSFRow row = null;
        protected HSSFCell cell = null;
        protected String res = "";   //return check info
        protected String insert_str = ""; //return insert info
        protected String tempStr = "";
        protected int rowCount = 0; //excel total row

        public List insertIntoDb(String file_path) throws Exception
        {
                return null;
        }

        public String checkExcel(String file_path) throws Exception
        {
                return null;
        }
}
