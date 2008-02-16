/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-6-24
 * Time: 10:02:43
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.user.util;

import com.ulearning.ulms.admin.sysconfig.bean.SysConfigHelper;
import com.ulearning.ulms.core.security.bean.SecurityHelper;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.finance.form.UserAccountDetailForm;
import com.ulearning.ulms.organ.bean.OrganHelper;
import com.ulearning.ulms.user.bean.UserHelper;
import com.ulearning.ulms.user.exceptions.UserSystemException;
import com.ulearning.ulms.user.form.UserForm;
import com.ulearning.ulms.util.DbInputUtil;
import com.ulearning.ulms.util.log.LogUtil;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class UserDbInput
{
        private HSSFWorkbook wb = null;
        private HSSFSheet sheet = null;
        private HSSFRow row = null;
        private HSSFCell cell = null;
        private String res = "";   //return check info
        private String res1 = "";   //return check info
        private String insert_str = ""; //return insert info

        private int rowCount = 0; //excel total row

        String tempStr = "";
        String loginName = "";
        String password = "";
        String pwdQuestion = "";
        String pwdAnswer = "";
        String name = "";
        String sexTemp = "";
        String sex = "1";
        String desc0 = "";
        String desc1 = "";
        String available = "1";
        String mail = "";
        String card = "";

        String phone = "";
        String cellPhone = "";
        String address = "";
        String postalcode = "";
        String eduLevel = "";

        String catalogName = "";

        int catalogID = 1;
        List roleName = null;
        List orgRoleName = null;
        String charge = "";
        double uaDetailAmount = 0.00;
        String[] roleID = null;
        String[] orgRoleID = null;
        private String remark1 = null;
        private String remark2 = null;
        private String remark3 = null;
        private String remark4 = null;
        private String remark5 = null;
        private String remark6 = null;
        private String remark7 = null;
        private String remark8 = null;
        private String remark9 = null;

        /**
         * batch insert user info
         *
         * @param file_path
         * @return List userList
         * @throws UserSystemException
         */
        public List insertIntoDb(String file_path) throws UserSystemException
        {
                ArrayList userFormList = new ArrayList();
                UserForm uf = null;
                int cellNum = 0;
                UserHelper userHelper = new UserHelper();
                OrganHelper organHelper = new OrganHelper();
                try
                {
                        FileInputStream in = new FileInputStream(file_path);
                        wb = new HSSFWorkbook(in);
                        sheet = wb.getSheetAt(0);
                        LogUtil.debug("User", "[UserDbInput]====================Start insert user info!");

                        //get user info total number
                        rowCount = sheet.getLastRowNum();

                        //insert user info
                        for (int i = 1; i <= rowCount; i++)
                        {

                                row = sheet.getRow((short) i);
                                //deal with empty row
                                tempStr = "";
                                try
                                {
                                        cellNum = row.getLastCellNum();
                                }
                                catch (Exception e)
                                {
                                }
                                for (int j = 0; j < cellNum; j++)
                                {
                                        tempStr += DbInputUtil.getThisCellValue(row, j);
                                }
                                if (tempStr.trim().equals(""))
                                {
                                        continue;
                                }
                                uf = new UserForm();
                                loginName = DbInputUtil.getThisCellValue(row, 0);
                                password = DbInputUtil.getThisCellValue(row, 1);
                                pwdQuestion = DbInputUtil.getThisCellValue(row, 2);
                                pwdAnswer = DbInputUtil.getThisCellValue(row, 3);
                                name = DbInputUtil.getThisCellValue(row, 4);
                                sexTemp = DbInputUtil.getThisCellValue(row, 5);
                                if (sexTemp.equals("男") || sexTemp.equals("male"))
                                {
                                        sex = "1";
                                }
                                else
                                {
                                        sex = "0";
                                }
                                desc0 = DbInputUtil.getThisCellValue(row, 6);   //生日
                                desc1 = DbInputUtil.getThisCellValue(row, 7); //民族
                                card = DbInputUtil.getThisCellValue(row, 8); //身份证
                                mail = DbInputUtil.getThisCellValue(row, 9);
                                phone = DbInputUtil.getThisCellValue(row, 10);
                                cellPhone = DbInputUtil.getThisCellValue(row, 11);
                                remark5 = DbInputUtil.getThisCellValue(row, 12);  //QQ
                                remark6 = DbInputUtil.getThisCellValue(row, 13);  //MSN
                                remark3 = DbInputUtil.getThisCellValue(row, 14);  //省份
                                remark4 = DbInputUtil.getThisCellValue(row, 15);  //城市
                                catalogID = OrganHelper.getOrgIDByCode(DbInputUtil.getThisCellValue(row, 16));//单位编号
                                address = DbInputUtil.getThisCellValue(row, 17);
                                postalcode = DbInputUtil.getThisCellValue(row, 18);
                                remark8 = DbInputUtil.getThisCellValue(row, 19);  //所属单位

//                                tempStr = "";
//                                tempStr = DbInputUtil.getThisCellValue(row, 14);
//                                if (tempStr.equals(""))
//                                {
//                                        roleID = new String[1];
//                                        roleID[0] = "9";
//                                }
//                                else
//                                {
//                                        roleName = StringUtil.split(tempStr, "/");
//                                        roleID = new String[roleName.size()];
//                                        for (int k = 0; k < roleName.size(); k++)
//                                        {
//                                                String roleNameTemp = (String) roleName.get(k);
//                                                int roleTempID = SecurityHelper.getRoleIDByName(roleNameTemp);
//                                                roleID[k] = "" + roleTempID;
//                                        }
//                                }
                                tempStr = "";
                                tempStr = DbInputUtil.getThisCellValue(row, 21);
                                if (tempStr.equals(""))
                                {
                                        orgRoleID = new String[1];
                                        orgRoleID[0] = "9";
                                }
                                else
                                {
                                        orgRoleName = StringUtil.split(tempStr, "/");
                                        orgRoleID = new String[orgRoleName.size()];
                                        for (int k = 0; k < orgRoleName.size(); k++)
                                        {
                                                String roleNameTemp = (String) orgRoleName.get(k);
                                                int orgRoleTempID = SecurityHelper.getRoleIDByName(roleNameTemp);
                                                orgRoleID[k] = "" + orgRoleTempID;
                                        }
                                }
                                uf.setLoginName(loginName);
                                uf.setPassword(password);
                                uf.setPwdQuestion(pwdQuestion);
                                uf.setPwdAnswer(pwdAnswer);
                                uf.setName(name);
                                uf.setSex(sex);
                                uf.setDesc0(desc0);
                                uf.setDesc1(desc1);
                                uf.setCard(card);
                                uf.setMail(mail);
                                uf.setPhone(phone);
                                uf.setCellPhone(cellPhone);
                                uf.setRemark5(remark5);
                                uf.setRemark6(remark6);
                                uf.setRemark4(remark4);
                                uf.setRemark3(remark3);
                                uf.setCatalogID(catalogID);
                                uf.setAddress(address);
                                uf.setPostalcode(postalcode);
                                uf.setRemark8(remark8);
                                uf.setOrgRoleID(orgRoleID);
                                uf.setUser_type(1);
                                uf.setAvailable("1");
                                userFormList.add(uf);
                        }
                }
                catch (Exception e)
                {
                        throw new UserSystemException("userDbInput is error " + e);
                }
                return userFormList;

        }

        public List chargeInsert(String file_path) throws UserSystemException
        {
                ArrayList userFormList1 = new ArrayList();
                UserAccountDetailForm uadf = null;
                int cellNum = 0;
                try
                {
                        FileInputStream in = new FileInputStream(file_path);
                        wb = new HSSFWorkbook(in);
                        sheet = wb.getSheetAt(0);
                        LogUtil.debug("User", "[UserDbInput]====================Start insert user info!");
                        //get user info total number
                        rowCount = sheet.getLastRowNum();
                        //insert user info
                        for (int i = 1; i <= rowCount; i++)
                        {
                                row = sheet.getRow((short) i);
                                try
                                {
                                        cellNum = row.getLastCellNum();
                                }
                                catch (Exception e)
                                {
                                }
                                charge = DbInputUtil.getThisCellValue(row, 20);
                                uaDetailAmount = StringUtil.parseFloat(DbInputUtil.getThisCellValue(row, 20));
                                uadf = new UserAccountDetailForm();
                                userFormList1.add(charge);
                        }
                }
                catch (Exception e)
                {
                        throw new UserSystemException("userDbInput is error " + e);
                }
                return userFormList1;

        }


        /**
         * check uerinfo in file
         *
         * @param file_path
         * @return chechinfo
         * @throws UserSystemException
         */
        public String checkExcel(String file_path, HttpServletRequest request) throws UserSystemException
        {
                System.out.println("file_path1 = " + file_path);
                int cellNum = 0;  // row number
                UserHelper userHelper = new UserHelper();
                OrganHelper organHelper = new OrganHelper();
                try
                {
                        FileInputStream in = new FileInputStream(file_path);
                        wb = new HSSFWorkbook(in);
                        sheet = wb.getSheetAt(0);
                        LogUtil.debug("User", "[UserDbInput]====================Start parse user info!");

                        // get user info total number
                        rowCount = sheet.getLastRowNum();
                        //check user info ever row
                        List loginNameList = new ArrayList();
                        List cardList = new ArrayList();
                        for (int i = 1; i <= rowCount; i++)
                        {
                                row = sheet.getRow(i);

                                //deal with empty row
                                tempStr = "";
                                try
                                {
                                        cellNum = row.getLastCellNum();
                                }
                                catch (Exception e)
                                {
                                }
                                for (int j = 0; j < cellNum; j++)
                                {
                                        tempStr += DbInputUtil.getThisCellValue(row, j);
                                }
                                loginName = DbInputUtil.getThisCellValue(row, 0);
                                card = DbInputUtil.getThisCellValue(row, 8);
                                loginNameList.add(loginName);
                                cardList.add(card);
                                if (tempStr.trim().equals(""))
                                {
                                        continue;
                                }
                                // check loginName
                                loginName = DbInputUtil.getThisCellValue(row, 0);
                                if (loginName.equals("") || loginName.length() > 38)
                                {
                                        res += "第" + (i + 1) + "行 用户名不能为空或长度过长<BR>";
                                }
                                if (UserHelper.getUserID(loginName) != 0)
                                {
                                        res += "第" + (i + 1) + "行 " + loginName + "，用户名已经存在<BR>";
                                }
                                //check  password
                                password = DbInputUtil.getThisCellValue(row, 1);
                                SysConfigHelper sysConfigHelper = new SysConfigHelper();
                                String orgID = "0";    //现在只有一个机构，先这样做吧。
                                int pwdLength = sysConfigHelper.getSysConfig(orgID).getPWDLength();
                                if (password.length() < pwdLength || password.length() > 48)
                                {
                                        res += "第" + (i + 1) + "行 密码长度不合法<BR>";
                                }

                                pwdQuestion = DbInputUtil.getThisCellValue(row, 2);
                                if (pwdQuestion.equals(""))
                                {
                                        res += "第" + (i + 1) + "行 查询密码问题不能为空<BR>";
                                }
                                pwdAnswer = DbInputUtil.getThisCellValue(row, 3);
                                if (pwdAnswer.equals(""))
                                {
                                        res += "第" + (i + 1) + "行 查询密码结果不能为空<BR>";
                                }

                                name = DbInputUtil.getThisCellValue(row, 4);
                                if (name.length() < 0 || name.length() > 30)
                                {
                                        res += "第" + (i + 1) + "行 姓名不能为空或过长<BR>";
                                }
                                // check sex
                                sex = DbInputUtil.getThisCellValue(row, 5);
                                if (!sex.equals(""))
                                {
                                        if (sex.length() > 1)
                                        {
                                                res += "第" + (i + 1) + "行 性别输入错误<BR>";
                                        }
                                        if (!sex.equals("男") && !sex.equals("女") &&
                                                !sex.equals("male") && !sex.equals("female"))
                                        {
                                                res += "第" + (i + 1) + "行 性别输入错误<BR>";
                                        }
                                }

                                //check  card
                                card = DbInputUtil.getThisCellValue(row, 8);
                                if (!card.equals(""))
                                {
                                        if (card.length() != 15 && card.length() != 18 && !isDigital(card))
                                        {
                                                res += "第" + (i + 1) + "行 请输入正确的身份证号<BR>";
                                        }


                                }
                                else
                                {
                                        res += "第" + (i + 1) + "行 请输入身份证号<BR>";
                                }

                                //check mail
                                mail = DbInputUtil.getThisCellValue(row, 9);

                                if (!mail.equals(""))
                                {
                                        if (!DbInputUtil.isEmail(mail))
                                        {
                                                res += "第" + (i + 1) + "行 请输入正确的邮箱地址<BR>";
                                        }
                                }

                                phone = DbInputUtil.getThisCellValue(row, 10);
                                //phone.
                                if (phone.length() > 30)
                                {
                                        res += "第" + (i + 1) + "行 电话号码长度输入过长<BR>";
                                }
                                for (int c = 0; c < phone.length(); c++)
                                {
                                        int ascii = (int) phone.charAt(c);
                                        if (ascii > 255)
                                        {
                                                res += "第" + (i + 1) + "行 电话号码含有非法字符<BR>";
                                                // break;
                                        }

                                }
                                cellPhone = DbInputUtil.getThisCellValue(row, 11);
                                if (cellPhone.length() > 15)
                                {
                                        res += "第" + (i + 1) + "行 手机号码输入过长<BR>";
                                }
                                for (int c = 0; c < cellPhone.length(); c++)
                                {
                                        int ascii = (int) cellPhone.charAt(c);
                                        if (ascii > 255)
                                        {
                                                res += "第" + (i + 1) + "行 手机号码含有非法字符<BR>";
                                                //break;
                                        }

                                }
                                //check catalogName
                                catalogName = DbInputUtil.getThisCellValue(row, 16);
                                if (!catalogName.equals(""))
                                {
                                        catalogID = OrganHelper.getOrgIDByCode(catalogName);
                                        if (catalogID == -1)
                                        {
                                                res += "第" + (i + 1) + "行 单位编号有误<BR>";
                                        }
                                }
                                else
                                {
                                        res += "第" + (i + 1) + "行 单位编号不能为空<BR>";
                                }
                                address = DbInputUtil.getThisCellValue(row, 17);
                                if (address.length() > 100)
                                {
                                        res += "第" + (i + 1) + "行 地址不能超过100个字符<BR>";
                                }
                                postalcode = DbInputUtil.getThisCellValue(row, 18);
                                if (!postalcode.equals(""))
                                {
                                        if (postalcode.length() != 6)
                                        {
                                                res += "第" + (i + 1) + "行 邮政编码为6位数字<BR>";
                                        }
                                        for (int p = 0; p < postalcode.length(); p++)
                                        {
                                                int ss = (int) postalcode.charAt(p);
                                                if (ss <= 9)
                                                {
                                                        res += "第" + (i + 1) + "行 邮政编码为6位数字<BR>";
                                                        // break;
                                                }
                                        }
                                }
                                //check orgRole
//                                tempStr = "";
//                                tempStr = DbInputUtil.getThisCellValue(row, 15);
//                                if (!tempStr.equals(""))
//                                {
//                                        orgRoleName = StringUtil.split(tempStr, "/");
//                                        for (int k = 0; k < orgRoleName.size(); k++)
//                                        {
//                                                String orgRoleNameTemp = (String) orgRoleName.get(k);
//                                                int orgRoleTempID = SecurityHelper.getRoleIDByName(orgRoleNameTemp);
//                                                if (orgRoleTempID == 0)
//                                                {
//                                                        res += "第" + (i + 1) + "行 用户机构权限输入有错误<BR>";
//                                                }
//                                        }
//                                }
                                remark3 = DbInputUtil.getThisCellValue(row, 14);
                                remark4 = DbInputUtil.getThisCellValue(row, 15);
                                remark8 = DbInputUtil.getThisCellValue(row, 19);
                                if (remark3 == null && remark3.length() > 50)
                                {
                                        res += "第" + (i + 1) + "行 省份输入太长<BR>";
                                }
                                if (remark4 == null && remark4.length() > 50)
                                {
                                        res += "第" + (i + 1) + "行 城市输入太长<BR>";
                                }
                                if (remark8 == null && remark8.length() > 200)
                                {
                                        res += "第" + (i + 1) + "行 所属单位输入太长<BR>";
                                }
                                charge = DbInputUtil.getThisCellValue(row, 20);
                                if (!DbInputUtil.isNumeric(charge))
                                {
                                        res1 += "第" + (i + 1) + "行 帐户金额只能为数字!<BR>";
                                }
                                tempStr = "";
                                tempStr = DbInputUtil.getThisCellValue(row, 21);
                                if (!tempStr.equals(""))
                                {
                                        roleName = StringUtil.split(tempStr, "/");
                                        for (int k = 0; k < roleName.size(); k++)
                                        {
                                                String roleNameTemp = (String) roleName.get(k);
                                                int roleTempID = SecurityHelper.getRoleIDByName(roleNameTemp);
                                                if (roleTempID == 0)
                                                {
                                                        res += "第" + (i + 1) + "行 用户角色输入有错误<BR>";
                                                }
                                        }
                                }
                        }

                        int count = 3;
                        int displayCount = 0;
                        int n = 0;
                        String temp1 = "";
                        String temp2 = "";

                        outWhile:
                        while (n < loginNameList.size())
                        {

                                temp1 = (String) loginNameList.get(n);

                                for (int m = n + 1; m < loginNameList.size(); m++)
                                {
                                        temp2 = (String) loginNameList.get(m);


                                        if (!temp1.equals("") && !temp2.equals("") && temp1.equals(temp2))
                                        {
                                                res += "第" + (n + 2) + "行和第" + (m + 2) + "行的登陆重复<br>";
                                                displayCount++;
                                                if (displayCount >= count)
                                                {
                                                        res += "………………";
                                                        break outWhile;
                                                }
                                        }

                                }
                                n++;
                        }

                        count = 3;
                        displayCount = 0;
                        n = 0;
                        temp1 = "";
                        temp2 = "";
/*                        outWhile: while (n < cardList.size())
                        {

                                temp1 = (String) cardList.get(n);

                                for (int m = n + 1; m < cardList.size(); m++)
                                {
                                        temp2 = (String) cardList.get(m);


                                        if (!temp1.equals("") && !temp2.equals("") && temp1.equals(temp2))
                                        {
                                                res += "第" + (n + 2) + "行和第" + (m + 2) + "行的身份证号重复<br>";
                                                displayCount++;
                                                if (displayCount >= count)
                                                {
                                                        res += "………………";
                                                        break outWhile;
                                                }
                                        }

                                }
                                n++;
                        }*/


                }
                catch (Exception e)
                {
                        throw new UserSystemException("Exception in checkExcel " + e);
                }
                return res;
        }

        /**
         * check charge info
         *
         * @param file_path
         */
        public String checkCharge(String file_path, HttpServletRequest request) throws UserSystemException
        {
                {
                        int cellNum = 0;  // row number
                        try
                        {
                                FileInputStream in = new FileInputStream(file_path);
                                wb = new HSSFWorkbook(in);
                                sheet = wb.getSheetAt(0);

                                // get user info total number
                                rowCount = sheet.getLastRowNum();
                                //check user info ever row
                                for (int i = 1; i <= rowCount; i++)
                                {
                                        row = sheet.getRow(i);
                                        //deal with empty row
                                        try
                                        {
                                                cellNum = row.getLastCellNum();
                                        }
                                        catch (Exception e)
                                        {
                                        }
                                        charge = DbInputUtil.getThisCellValue(row, 20);
                                        if (!DbInputUtil.isNumeric(charge))
                                        {
                                                res1 += "第" + (i + 1) + "行 帐户金额只能为数字!<BR>";
                                                break;
                                        }
                                }
                        }
                        catch (Exception e)
                        {
                                throw new UserSystemException("Exception in checkExcel " + e);
                        }
                        return res1;
                }
        }

        private boolean isDigital(String str)
        {
                boolean returnValue = false;
                out:
                if (str != null && !str.equals(""))
                {
                        for (int i = 0; i < str.length(); i++)
                        {
                                char temp = str.charAt(i);
                                if (temp < '0' || temp > '9')
                                {
                                        break out;
                                }
                        }
                        returnValue = true;
                }
                return returnValue;
        }

        public UserDbInput()
        {

        }

}
