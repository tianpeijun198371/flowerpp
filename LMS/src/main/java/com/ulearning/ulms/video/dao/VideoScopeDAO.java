/**
 * Created by IntelliJ IDEA.
 * User: liaoxx
 * Date: 2007-1-17
 * Time: 9:58:44
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.video.dao;

import com.ulearning.ulms.video.exceptions.VideoScopeDAOSysException;
import com.ulearning.ulms.video.model.VideouserModel;
import com.ulearning.ulms.video.model.VideoOrganModel;
import com.ulearning.ulms.video.model.VideoModel;

import java.util.List;

public interface VideoScopeDAO
{
        public int addVideoUser(VideouserModel videoUserModel) throws VideoScopeDAOSysException;

        public int addVideoOrg(VideoOrganModel videoOrganModel) throws VideoScopeDAOSysException;

        //public int addVideoCondition(VideoConditionModel videoConditionModel) throws VideoScopeDAOSysException;
        public int addVideo(VideoModel videomodel) throws VideoScopeDAOSysException;

        public void updateVideoUser(VideouserModel videoUserModel) throws VideoScopeDAOSysException;

        public void updateVideoOrg(VideoOrganModel videoOrganModel) throws VideoScopeDAOSysException;

        //public void updateVideoCondition(VideoConditionModel videoConditionModel) throws VideoScopeDAOSysException;
        public void updateVideo(VideoModel conditionModel) throws VideoScopeDAOSysException;

        public List getVideoUser(int vclassid, int type) throws VideoScopeDAOSysException;

        public List getVideoUser(int vclassid, int userID, int type) throws VideoScopeDAOSysException;

        public List getVideoList(int type) throws VideoScopeDAOSysException;

        public List getVideoOrg(int vclassid, int type) throws VideoScopeDAOSysException;

        public List getVideoOrg(int vclassid, int orgID, int type) throws VideoScopeDAOSysException;

        public VideoModel getVideo(int vclassid, int type) throws VideoScopeDAOSysException;

        public void delVideoUser(int vclassid, int type) throws VideoScopeDAOSysException;

        public void delVideoUserbyuserid(int vclassid, int userID, int type) throws VideoScopeDAOSysException;

        public void delVideoOrg(int vclassid, int type) throws VideoScopeDAOSysException;

        public void delVideoOrgbyorgid(int vclassid, int orgID, int type) throws VideoScopeDAOSysException;

        public void delVideo(int vclassid, int type) throws VideoScopeDAOSysException;
}
