/**
 * Copyright (c)2006 Beijing WenhuaOnline Sci-tech DevelopmentCo.,Ltd.
 * All rights reserved.
 *
 * User: fengch
 * Date: 2007-10-17 11:24:26
 */
package com.ulearning.ulms.tools.meeting.xuechuang.client;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.activation.DataHandler;
import java.util.Calendar;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.user.form.UserForm;
import com.ulearning.ulms.user.bean.UserHelper;


public class SetMeetingClient
{
        protected static Log logger = LogFactory.getLog(SetMeetingClient.class);
        private static SetMeetingStub stub;

        static
        {
                try
                {
                        stub = new SetMeetingStub();
                }
                catch (Exception ex)
                {
                        logger.info(ex);
                }
        }

        //添加一个会议 
        public static int addMeeting(SetMeetingStub.MeetingInfo meetingInfo)
        {
                int result = -1;

                try
                {
                        SetMeetingStub.AddMeeting addMeeting = new SetMeetingStub.AddMeeting();
                        addMeeting.setMeetingModel(meetingInfo);

                        SetMeetingStub.AddMeetingResponse addMeetingResponse = stub.AddMeeting(addMeeting);
                        result = addMeetingResponse.getAddMeetingResult();
                }
                catch (Exception ex)
                {
                        logger.info(ex);
                        throw new ULMSSysException("益学视频教学系统出错或者在维护中, 请稍候再试！",null,ex);
                }

                return result;
        }

        //修改会议
        public static String editMeeting(SetMeetingStub.MeetingInfo meetingInfo)
        {
                String result = "-1";

                try
                {
                        SetMeetingStub.EditMeeting editMeeting = new SetMeetingStub.EditMeeting();
                        editMeeting.setMeetingModel(meetingInfo);

                        SetMeetingStub.EditMeetingResponse editMeetingResponse = stub.EditMeeting(editMeeting);
                        result = editMeetingResponse.getEditMeetingResult();
                }
                catch (Exception ex)
                {
                        logger.info(ex);
                        throw new ULMSSysException("益学视频教学系统出错或者在维护中, 请稍候再试！",null,ex);
                }

                return result;
        }

        //停止一个未结束的会议
        public static boolean stopMeeting(int meetingId)
        {
                boolean result = false;

                try
                {
                        SetMeetingStub.StopMeeting stopMeeting = new SetMeetingStub.StopMeeting();
                        stopMeeting.setMeetingId(meetingId);

                        SetMeetingStub.StopMeetingResponse editMeetingResponse = stub.StopMeeting(stopMeeting);
                        result = editMeetingResponse.getStopMeetingResult();
                }
                catch (Exception ex)
                {
                        logger.info(ex);
                        throw new ULMSSysException("益学视频教学系统出错或者在维护中, 请稍候再试！",null,ex);
                }

                return result;
        }

        //生成进入会议字符串
        public static String getEnterCodeByUserId(int userId,int meetingId,String nickName,String skey)
        {
                String result = "-1";
                UserForm user= UserHelper.getUser(String.valueOf(userId));
                int externalSystemUserID=0;
                if (user.getExternalSystemUserID() != null && user.getExternalSystemUserID().intValue() > 0)
                {
                        externalSystemUserID=user.getExternalSystemUserID().intValue();
                }
                if(externalSystemUserID<=0)
                {
                        result=getEnterCode(meetingId,nickName,skey);
                }
                else
                {
                        try
                        {
                                SetMeetingStub.GetEnterCodeByUserId getEnterCodeByUserId = new SetMeetingStub.GetEnterCodeByUserId();
                                getEnterCodeByUserId.setMeetingId(meetingId);
                                getEnterCodeByUserId.setNickName(nickName);
                                getEnterCodeByUserId.setUserId(externalSystemUserID);
                                getEnterCodeByUserId.setSKey(skey);
                                SetMeetingStub.GetEnterCodeByUserIdResponse getEnterCodeByUserIdResponse = stub.GetEnterCodeByUserId(getEnterCodeByUserId);
                                result = getEnterCodeByUserIdResponse.getGetEnterCodeByUserIdResult();
                        }
                        catch (Exception ex)
                        {
                                logger.info(ex);
                                throw new ULMSSysException("益学视频教学系统出错或者在维护中, 请稍候再试！",null,ex);
                        }
                }

                return result;
        }

        //生成进入会议字符串 
        public static String getEnterCode(int meetingId,String nickName,String skey)
        {
                String result = "-1";

                try
                {
                        SetMeetingStub.GetEnterCode getEnterCode = new SetMeetingStub.GetEnterCode();
                        getEnterCode.setMeetingId(meetingId);
                        getEnterCode.setNickName(nickName);
                        getEnterCode.setSKey(skey);
                        SetMeetingStub.GetEnterCodeResponse getEnterCodeResponse = stub.GetEnterCode(getEnterCode);
                        result = getEnterCodeResponse.getGetEnterCodeResult();
                }
                catch (Exception ex)
                {
                        logger.info(ex);
                        throw new ULMSSysException("益学视频教学系统出错或者在维护中, 请稍候再试！",null,ex);
                }

                return result;
        }

        //获取一个会议 
        public static SetMeetingStub.MeetingInfo getMeeting(int meetingId)
        {
                SetMeetingStub.MeetingInfo result = null;
               
                try
                {
                        SetMeetingStub.GetMeeting getMeeting = new SetMeetingStub.GetMeeting();
                        getMeeting.setMeetingId(meetingId);

                        SetMeetingStub.GetMeetingResponse getMeetingResponse = stub.GetMeeting(getMeeting);
                        result = getMeetingResponse.getGetMeetingResult();
                }
                catch (Exception ex)
                {
                        logger.info(ex);
                        throw new ULMSSysException("益学视频教学系统出错或者在维护中, 请稍候再试！",null,ex);
                }

                return result;
        }

        /**
         * 获取符合条件的会议列表集合
         * @param strWhere   参数为:status=2 则返回当前在线会议列表
         * @return
         */
        public static SetMeetingStub.MeetingInfo[] getMeetings(String strWhere)
        {
                SetMeetingStub.MeetingInfo[] result = null;

                try
                {
                        SetMeetingStub.GetMeetings getMeetings = new SetMeetingStub.GetMeetings();
                        getMeetings.setStrWhere(strWhere);
                        result = stub.GetMeetings(getMeetings).getGetMeetingsResult().getMeetingInfo();
                }
                catch (Exception ex)
                {
                        logger.info("error:",ex);
                        throw new ULMSSysException("益学视频教学系统出错或者在维护中, 请稍候再试！",null,ex);
                }

                return result;
        }

        //获取所有可用主从服务器 
        public static SetMeetingStub.Sys_Configs[] getAllServers()
        {
                SetMeetingStub.Sys_Configs[] result = null;

                try
                {
                        SetMeetingStub.GetAllServers getAllServers = new SetMeetingStub.GetAllServers();
                        result = stub.GetAllServers(getAllServers).getGetAllServersResult().getSys_Configs();
                }
                catch (Exception ex)
                {
                        logger.info(ex);
                        throw new ULMSSysException("益学视频教学系统出错或者在维护中, 请稍候再试！",null,ex);
                }

                return result;
        }

        //通知消息(公告)
        public static boolean notifyMessage(int userId,int sendType,String title,String content
                )
        {
                boolean result = false;
                SetMeetingStub.NotifyMessage notifyMessage=new SetMeetingStub.NotifyMessage();
                notifyMessage.setContent(content);
                notifyMessage.setSendType(sendType);
                notifyMessage.setTitle(title);
                notifyMessage.setUserId(userId);
                try
                {
                        result = stub.NotifyMessage(notifyMessage).getNotifyMessageResult();
                }
                catch (Exception ex)
                {
                        logger.info(ex);
                        throw new ULMSSysException("益学视频教学系统出错或者在维护中, 请稍候再试！",null,ex);
                }

                return result;
        }

        //向用户发送会议邀请通知
        public static boolean sendJoinMeetingMsg(int userId, int meetingId,
                                                 String key)
        {
                boolean result = false;

                try
                {
                        SetMeetingStub.SendJoinMeetingMsg sendJoinMeetingMsg = new SetMeetingStub.SendJoinMeetingMsg();
                        sendJoinMeetingMsg.setKey(key);
                        sendJoinMeetingMsg.setMeetingId(meetingId);
                        sendJoinMeetingMsg.setUserId(userId);
                        result = stub.SendJoinMeetingMsg(sendJoinMeetingMsg)
                                .getSendJoinMeetingMsgResult();
                }
                catch (Exception ex)
                {
                        logger.info(ex);
                        throw new ULMSSysException("益学视频教学系统出错或者在维护中, 请稍候再试！",null,ex);
                }

                return result;
        }

        //获取某个会议中当前用户总数
        public static int getMeetingUserCount(int serverId, int meetingId)
        {
                int result = 0;

                try
                {
                        SetMeetingStub.GetMeetingUserCount getMeetingUserCount = new SetMeetingStub.GetMeetingUserCount();
                        getMeetingUserCount.setMeetingId(meetingId);
                        getMeetingUserCount.setServerId(serverId);
                        result = stub.GetMeetingUserCount(getMeetingUserCount).getGetMeetingUserCountResult();
                }
                catch (Exception ex)
                {
                        logger.info(ex);
                        throw new ULMSSysException("益学视频教学系统出错或者在维护中, 请稍候再试！",null,ex);
                }

                return result;
        }

        //用户注册接口
        public static int userRegister(String account, String nickName,String pwd)
        {
                int result = 0;

                try
                {
                        SetMeetingStub.UserRegister userRegister = new SetMeetingStub.UserRegister();
                        userRegister.setAccount(account);
                        userRegister.setNickName(nickName);
                        userRegister.setPwd(pwd);
                        result = stub.UserRegister(userRegister).getUserRegisterResult();
                }
                catch (Exception ex)
                {
                        ex.printStackTrace();
                        logger.info(ex);
                        throw new ULMSSysException("益学视频教学系统出错或者在维护中, 请稍候再试！",null,ex);
                }

                return result;
        }

        //修改密码接口
        public static boolean changePwd(String account, String newPwd,String oldPwd)
        {
                boolean result = false;

                try
                {
                        SetMeetingStub.ChangePwd changePwd = new SetMeetingStub.ChangePwd();
                        changePwd.setAccount(account);
                        changePwd.setNewPwd(newPwd);
                        //changePwd.setOldPwd(oldPwd);
                        result = stub.ChangePwd(changePwd).getChangePwdResult();
                }
                catch (Exception ex)
                {
                        logger.info(ex);
                        throw new ULMSSysException("益学视频教学系统出错或者在维护中, 请稍候再试！",null,ex);
                }

                return result;
        }

        //修改用户信息 
        public static boolean editUserInfo(SetMeetingStub.UserInformation user)
        {
                boolean result = false;

                try
                {
                        SetMeetingStub.UserInformation user_tmp= new  SetMeetingStub.UserInformation();
                        SetMeetingStub.EditUserInfo editUserInfo = new SetMeetingStub.EditUserInfo();
                        editUserInfo.setUserMsg(user);
                        result = stub.EditUserInfo(editUserInfo).getEditUserInfoResult();
                }
                catch (Exception ex)
                {
                        logger.info(ex);
                        throw new ULMSSysException("益学视频教学系统出错或者在维护中, 请稍候再试！",null,ex);
                }

                return result;
        }

        //获取用户信息 
        public static SetMeetingStub.UserInformation getUserInfo(int userId)
        {
                SetMeetingStub.UserInformation result = null;

                try
                {
                        SetMeetingStub.GetUserInfo getUserInfo = new SetMeetingStub.GetUserInfo();
                        getUserInfo.setUserId(userId);
                        result = stub.GetUserInfo(getUserInfo).getGetUserInfoResult();
                }
                catch (Exception ex)
                {
                        logger.info(ex);
                        throw new ULMSSysException("益学视频教学系统出错或者在维护中, 请稍候再试！",null,ex);
                }

                return result;
        }

        //判断用户在线状态
        public static boolean getUserIsOnLine(int userId)
        {
                boolean result = false;

                try
                {
                        SetMeetingStub.GetUserIsOnLine getUserIsOnLine = new SetMeetingStub.GetUserIsOnLine();
                        getUserIsOnLine.setUserId(userId);
                        result = stub.GetUserIsOnLine(getUserIsOnLine).getGetUserIsOnLineResult();
                }
                catch (Exception ex)
                {
                        logger.info(ex);
                        throw new ULMSSysException("益学视频教学系统出错或者在维护中, 请稍候再试！",null,ex);
                }

                return result;
        }

        //获取用户在线总数
        public static int getUserOnLineCount()
        {
                int result = 0;

                try
                {
                        SetMeetingStub.GetUserOnLineCount getUserOnLineCount = new SetMeetingStub.GetUserOnLineCount();
                        result = stub.GetUserOnLineCount(getUserOnLineCount).getGetUserOnLineCountResult();
                }
                catch (Exception ex)
                {
                        logger.info(ex);
                        throw new ULMSSysException("益学视频教学系统出错或者在维护中, 请稍候再试！",null,ex);
                }

                return result;
        }

        //获取用户留言 
        public static SetMeetingStub.UserMessage[] getUserMessage(int userId)
        {
                SetMeetingStub.UserMessage[] result = null;

                try
                {
                        SetMeetingStub.GetUserMessage getUserMessage = new SetMeetingStub.GetUserMessage();
                        getUserMessage.setUserId(userId);
                        result = stub.GetUserMessage(getUserMessage).getGetUserMessageResult().getUserMessage();
                }
                catch (Exception ex)
                {
                        logger.info(ex);
                        throw new ULMSSysException("益学视频教学系统出错或者在维护中, 请稍候再试！",null,ex);
                }

                return result;
        }

        //上传用户头像
        public static String uploadHeadPhoto(int userId, DataHandler dataHandler)
        {
                String result = "-1";

                try
                {
                        SetMeetingStub.UploadHeadPhoto uploadHeadPhoto = new SetMeetingStub.UploadHeadPhoto();
                        uploadHeadPhoto.setByteFileStream(dataHandler);
                        uploadHeadPhoto.setUserId(userId);
                        result = stub.UploadHeadPhoto(uploadHeadPhoto).getUploadHeadPhotoResult();
                }
                catch (Exception ex)
                {
                        logger.info(ex);
                        throw new ULMSSysException("益学视频教学系统出错或者在维护中, 请稍候再试！",null,ex);
                }

                return result;
        }

        //获取用户头像URL
        public static String getHeadPhoto(int userId)
        {
                String result = "-1";

                try
                {
                        SetMeetingStub.GetHeadPhoto getHeadPhoto = new SetMeetingStub.GetHeadPhoto();
                        getHeadPhoto.setUserId(userId);
                        result = stub.GetHeadPhoto(getHeadPhoto).getGetHeadPhotoResult();
                }
                catch (Exception ex)
                {
                        logger.info(ex);
                        throw new ULMSSysException("益学视频教学系统出错或者在维护中, 请稍候再试！",null,ex);
                }

                return result;
        }


        //通过账号获取用户信息
        public static SetMeetingStub.UserInformation getUserInfoByAccount (String account)
        {
                SetMeetingStub.UserInformation result = null;

                try
                {
                        SetMeetingStub.GetUserInfoByAccount  getUserInfoByAccount  = new SetMeetingStub.GetUserInfoByAccount();
                        getUserInfoByAccount.setAccount(account);;
                        result = stub.GetUserInfoByAccount(getUserInfoByAccount).getGetUserInfoByAccountResult();
                }
                catch (Exception ex)
                {
                        logger.info(ex);
                        throw new ULMSSysException("益学视频教学系统出错！",ex);
                }

                return result;
        }

         //获取用户参加会议的记录
        public static SetMeetingStub.Sys_Logs[] getSysLogsByMeetingId(int meetingId)
        {
                SetMeetingStub.Sys_Logs[] result = null;

                try
                {
                        SetMeetingStub.GetSysLogsByMeetingId getSysLogsByMeetingId = new SetMeetingStub.GetSysLogsByMeetingId();
                        getSysLogsByMeetingId.setMeetingId(meetingId);
                        result = stub.GetSysLogsByMeetingId(getSysLogsByMeetingId).getGetSysLogsByMeetingIdResult().getSys_Logs();
                }
                catch (Exception ex)
                {
                        logger.info(ex);
                        throw new ULMSSysException("益学视频教学系统出错！",ex);
                }

                return result;
        }

        //获取单个用户参加会议的记录 
        public static SetMeetingStub.Sys_Logs[] getSysLogsByMIdUId (int meetingId,int userId)
        {
                SetMeetingStub.Sys_Logs[] result = null;

                try
                {
                        SetMeetingStub.GetSysLogsByMIdUId  getSysLogsByMIdUId  = new SetMeetingStub.GetSysLogsByMIdUId();
                        getSysLogsByMIdUId.setMeetingId(meetingId);
                        getSysLogsByMIdUId.setUserId(userId);
                        result = stub.GetSysLogsByMIdUId (getSysLogsByMIdUId).getGetSysLogsByMIdUIdResult().getSys_Logs();
                }
                catch (Exception ex)
                {
                        logger.info(ex);
                        throw new ULMSSysException("益学视频教学系统出错！",ex);
                }

                return result;
        }

        //获取单个用户参加会议的记录
        public static SetMeetingStub.Sys_Logs[] getSysLogsByDate  (Calendar start,Calendar end)
        {
                SetMeetingStub.Sys_Logs[] result = null;

                try
                {
                        SetMeetingStub.GetSysLogsByDate   getSysLogsByDate   = new SetMeetingStub.GetSysLogsByDate ();
                        getSysLogsByDate.setStart(start);
                        getSysLogsByDate.setEnd(end);
                        result = stub.GetSysLogsByDate  (getSysLogsByDate).getGetSysLogsByDateResult().getSys_Logs();
                }
                catch (Exception ex)
                {
                        logger.info(ex);
                        throw new ULMSSysException("益学视频教学系统出错！",ex);
                }

                return result;
        }
}
