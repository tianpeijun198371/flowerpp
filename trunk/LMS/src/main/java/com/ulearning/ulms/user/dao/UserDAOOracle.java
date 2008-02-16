/**
 * Created by IntelliJ IDEA.
 * User: dengj
 * Date: Apr 8, 2004
 * Time: 9:36:03 AM
 * To change this template use Options | File Templates.
 */

package com.ulearning.ulms.user.dao;

import com.ulearning.ulms.core.crypto.MD5Encrypt;
import com.ulearning.ulms.user.exceptions.UserDAOSysException;
import com.ulearning.ulms.user.form.UserForm;
import com.ulearning.ulms.util.log.LogUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class UserDAOOracle extends UserDAOImpl
{

        /**
         * Insert a new user record to database
         *
         * @param userForm the value object to be added
         * @throws UserDAOSysException
         */
        public int addUser(UserForm userForm) throws UserDAOSysException
        {
                Statement stmt = null;
                ResultSet rs = null;
                int userID = 0;

                java.sql.Date dayToInsert = new java.sql.Date(System.currentTimeMillis());
                String lastloginDate = "to_date('" + dayToInsert + "','yyyy-mm-dd')";

                String passwd = userForm.getPassword().trim();
                String passwdMD5 = MD5Encrypt.encrypt(passwd);

                String sqlStr = "insert into U_User_Tab values(userID.nextval,'" +
                        userForm.getLoginName().trim() + "','" +
                        passwdMD5 + "','" +
                        userForm.getName() + "','" +
                        userForm.getAvailable() + "','" +
                        userForm.getMail() + "','" +
                        userForm.getCard() + "','" +
                        userForm.getSex() + "','" +
                        userForm.getPhone() + "','" +
                        userForm.getCellPhone() + "','" +
                        userForm.getAddress() + "','" +
                        userForm.getPostalcode() + "','" +
                        userForm.getEduLevel() + "','" +
                        userForm.getPwdQuestion() + "','" +
                        userForm.getPwdAnswer() + "'," +
                        lastloginDate + "," +
                        userForm.getCatalogID() + "," +
                        lastloginDate + ",'" +
                        userForm.getDesc0() + "','" +
                        userForm.getDesc1() + "','" +
                        userForm.getDescription() + "')";

                try
                {
                        dbConnection = getConnection();
                        stmt = dbConnection.createStatement();
                        LogUtil.debug("user", "[UserDAOOracle]====================the sql string is : " + sqlStr);
                        stmt.execute(sqlStr);

                        String isql = "SELECT userID.currval  as userID FROM DUAL";
                        stmt.execute(isql);
                        rs = stmt.getResultSet();

                        if (rs.next())
                        {
                                userID = rs.getInt("userID");
                        }


                }
                catch (SQLException se)
                {
                        throw new UserDAOSysException("SQLException while updating " + "account; Serial = " + userForm.getName() + " :\n" + se);
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection();
                }
                return userID;
        }

        /**
         * Update userInfo by the new Form
         *
         * @param userForm value object for changed
         * @throws UserDAOSysException
         */
        public void updateUser(UserForm userForm) throws UserDAOSysException
        {
                Statement stmt = null;
                String sqlStr = null;
                java.sql.Date dayToInsert = new java.sql.Date(System.currentTimeMillis());
                String lastloginDate = "to_date('" + dayToInsert + "','yyyy-mm-dd')";

                if (userForm.getPassword() == null)
                {
                        sqlStr = "update U_User_Tab set " +
                                "Name = '" + userForm.getName() + "', " +
                                "Available = '" + userForm.getAvailable() + "', " +
                                "Mail = '" + userForm.getMail() + "', " +
                                "Card = '" + userForm.getCard() + "', " +
                                "Sex = '" + userForm.getSex() + "', " +
                                "Phone = '" + userForm.getPhone() + "', " +
                                "CellPhone = '" + userForm.getCellPhone() + "', " +
                                "Address = '" + userForm.getAddress() + "', " +
                                "Postalcode = '" + userForm.getPostalcode() + "', " +
                                "EduLevel = '" + userForm.getEduLevel() + "', " +
                                "PwdQuestion = '" + userForm.getPwdQuestion() + "', " +
                                "PwdAnswer = '" + userForm.getPwdAnswer() + "', " +
                                "RegisterDate = " + lastloginDate + ", " +
                                "LastLoginDate = " + lastloginDate + ", " +
                                "Desc0 = '" + userForm.getDesc0() + "', " +
                                "Desc1 = '" + userForm.getDesc1() + "', " +
                                "Description = '" + userForm.getDescription() +
                                "'  where userID = " + userForm.getUserID();

                }
                else
                {
                        String passwdMD5 = MD5Encrypt.encrypt(userForm.getPassword());
                        sqlStr = "update U_User_Tab set " +
                                "Password = '" + passwdMD5 + "', " +
                                "Name = '" + userForm.getName() + "', " +
                                "Available = '" + userForm.getAvailable() + "', " +
                                "Mail = '" + userForm.getMail() + "', " +
                                "Card = '" + userForm.getCard() + "', " +
                                "Sex = '" + userForm.getSex() + "', " +
                                "Phone = '" + userForm.getPhone() + "', " +
                                "CellPhone = '" + userForm.getCellPhone() + "', " +
                                "Address = '" + userForm.getAddress() + "', " +
                                "Postalcode = '" + userForm.getPostalcode() + "', " +
                                "EduLevel = '" + userForm.getEduLevel() + "', " +
                                "PwdQuestion = '" + userForm.getPwdQuestion() + "', " +
                                "PwdAnswer = '" + userForm.getPwdAnswer() + "', " +
                                "RegisterDate = " + lastloginDate + ", " +
                                "LastLoginDate = " + lastloginDate + ", " +
                                "Desc0 = '" + userForm.getDesc0() + "', " +
                                "Desc1 = '" + userForm.getDesc1() + "', " +
                                "Description = '" + userForm.getDescription() +
                                "'  where userID = " + userForm.getUserID();
                }

                try
                {
                        dbConnection = getConnection();
                        stmt = dbConnection.createStatement();
                        LogUtil.debug("user", "[UserDAOOracle]====================the sql string is : " + sqlStr);
                        stmt.execute(sqlStr);

                }
                catch (SQLException se)
                {
                        throw new UserDAOSysException("SQLException while updating " + "account; Serial = " + userForm.getName() + " :\n" + se);
                }
                finally
                {
                        closeStatement(stmt);
                        closeConnection();
                }
        }


}
