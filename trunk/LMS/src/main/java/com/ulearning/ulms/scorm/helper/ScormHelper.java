/**
 * ScormHelper.java.
 * User: fengch  Date: 2004-10-22
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.scorm.helper;

import com.ulearning.ulms.scorm.adl.parsers.dom.ADLOrganizations;
import com.ulearning.ulms.scorm.dao.ScormDAO;
import com.ulearning.ulms.scorm.dao.ScormDAOFactory;
import com.ulearning.ulms.scorm.exceptions.ScormSysException;
import com.ulearning.ulms.scorm.model.UserSCOModel;
import com.ulearning.ulms.scorm.util.ScormConstants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ulearning.ulms.scorm.util.CMIConstants;

import org.apache.commons.lang.StringUtils;
import java.io.*;


public class ScormHelper
{
        protected static Log logger = LogFactory.getLog(ScormHelper.class);
        private static ScormDAO scormDAO;

        static
        {
                try
                {
                        scormDAO = ScormDAOFactory.getDAO();
                }
                catch (Exception ex)
                {
                        ex.printStackTrace();
                }
        }

        /**
         * 判断用户是否已初始化scorm数据
         *
         * @param userID
         * @param courseContentNodeID
         * @return
         * @throws ScormSysException
         */
        public static boolean isInitUserSCO(int userID, int courseContentNodeID)
                throws ScormSysException
        {
                return scormDAO.isInitUserSCO(userID, courseContentNodeID);
        }

        /**
         * to insert scorm's all item to table C_UserSCO_Tab.
         *
         * @param userID
         * @param courseContentNodeID
         * @throws ScormSysException
         */
        public static void initUserSCO(int userID, int courseContentNodeID)
                throws ScormSysException
        {
                logger.info("[ScormHelper]isInitUserSCO--start！");
                logger.info("[ScormHelper]isInitUserSCO--userID: " + userID);
                logger.info("[ScormHelper]isInitUserSCO--courseContentNodeID: " +
                        courseContentNodeID);

                if (!isInitUserSCO(userID, courseContentNodeID))
                {
                        try
                        {
                                logger.info("[ScormHelper]insertUserSCO--需要 insertUserSCO!");
                                scormDAO.insertUserSCO(userID, courseContentNodeID);

                                //////////////////////////////////////////////////////////////////
                                // Create an ADLOrganizations object for the user and the course
                                /////////////////////////////////////////////////////////////////
                                //System.out.println("About to open the sequencing file");
                                String courseSeqFile = ScormConstants.ImportRoot +
                                        courseContentNodeID + "\\sequence.obj";
                                FileInputStream istream = new FileInputStream(courseSeqFile);
                                ObjectInputStream ois = new ObjectInputStream(istream);

                                ADLOrganizations sequenceObj = (ADLOrganizations) ois.readObject();

                                istream.close();

                                //////////////////////////////////////////////////////////////////
                                // Create a file for that user in this specific course
                                /////////////////////////////////////////////////////////////////
                                //  Write the Sequencing Object to a file
                                String sequencingFileName = ScormConstants.ImportRoot +
                                        courseContentNodeID + "\\sequence." + userID;
                                java.io.File userSequence = new java.io.File(sequencingFileName);
                                FileOutputStream ostream = new FileOutputStream(userSequence);
                                ObjectOutputStream oos = new ObjectOutputStream(ostream);
                                oos.writeObject(sequenceObj);
                                oos.flush();
                                oos.close();
                        }
                        catch (ClassNotFoundException ue)
                        {
                                logger.info(
                                        "[ScormHelper]insertUserSCO--ClassNotFoundException error");
                        }
                        catch (IOException ue)
                        {
                                logger.info("[ScormHelper]insertUserSCO--IOException error");
                        }
                }
                else
                {
                        logger.info("[ScormHelper]insertUserSCO--不需要 insertUserSCO!");
                }

                logger.info("[ScormHelper]isInitUserSCO--end！");
        }

        /**
         * update the user's lessonStatus
         *
         * @param userID
         * @param scoID
         * @param lessonStatus
         * @throws ScormSysException
         */
        public static void updateUserSCO(int userID, int scoID, String lessonStatus)
                throws ScormSysException
        {
                UserSCOModel userSco=scormDAO.getUserSCO(userID, scoID);
                if(userSco!=null)
                {
                        if(!StringUtils.isBlank(lessonStatus))
                        {
                                String oldStatus=StringUtils.trimToEmpty(userSco.getLessonStatus());
                                //若已完成，或通过，或失败，则不再更新状态
                                //若要把状态由"未完成"转换为"已浏览"，则也不做此转换
                                if((lessonStatus.equals(CMIConstants.STATUS_PASSED)
                                                     || lessonStatus.equals(CMIConstants.STATUS_PASSED)
                                                     || lessonStatus.equals(CMIConstants.STATUS_FAILED))
                                                     ||
                                                     (lessonStatus.equals(CMIConstants.STATUS_BROWSED)
                                                     && oldStatus.equals(CMIConstants.STATUS_INCOMPLETE)))
                                {
                                        return;
                                } 
                                scormDAO.updateUserSCO(userID, scoID, lessonStatus);   
                        }
                            
                }
                
        }

        /**
         * update the user's lessonStatus  and time
         *
         * @param userID
         * @param scoID
         * @param lessonStatus
         * @throws ScormSysException
         */
        public static void updateUserSCO(int userID, int scoID, String lessonStatus,
                                         String sessionTime) throws ScormSysException
        {
                UserSCOModel userSco=scormDAO.getUserSCO(userID, scoID);
                if(userSco!=null)
                {
                        if(!StringUtils.isBlank(lessonStatus))
                        {
                                String oldStatus=StringUtils.trimToEmpty(userSco.getLessonStatus());
                                //若已完成，或通过，或失败，则不再更新状态
                                //若要把状态由"未完成"转换为"已浏览"，则也不做此转换
                                if((lessonStatus.equals(CMIConstants.STATUS_PASSED)
                                                     || lessonStatus.equals(CMIConstants.STATUS_PASSED)
                                                     || lessonStatus.equals(CMIConstants.STATUS_FAILED))
                                                     ||
                                                     (lessonStatus.equals(CMIConstants.STATUS_BROWSED)
                                                     && oldStatus.equals(CMIConstants.STATUS_INCOMPLETE)))
                                {
                                        return;
                                } 
                                scormDAO.updateUserSCO(userID, scoID, lessonStatus, sessionTime);
                        }
                            
                }
               
        }

        public static UserSCOModel getUserSCO(int userID, int scoID) throws ScormSysException
        {
                
               //Gets the sessiontime from database according to uerId and scoID
               UserSCOModel usco = scormDAO.getUserSCO(userID,scoID);
               return usco;
        }

        /**
         * 根据itemID，返回对应的SCO，如果itemID的类型就是SCO，否则返回itemID下一个SCO
         *
         * @param itemID
         * @return
         * @throws ScormSysException
         */
        public static int getSCO(int itemID) throws ScormSysException
        {                                         
                int scoID = scormDAO.getSCO(itemID);

                if (scoID == -1)
                {
                        throw new ScormSysException("不存在itemID：" + itemID);
                }

                return scoID;
        }
}
