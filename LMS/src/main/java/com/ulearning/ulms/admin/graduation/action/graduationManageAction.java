/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-9-9
 * Time: 9:50:58
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.admin.graduation.action;

import com.ulearning.ulms.admin.certificate.bean.CertHelper;
import com.ulearning.ulms.admin.certificate.form.CertForm;
import com.ulearning.ulms.admin.graduation.dao.GraduationDAO;
import com.ulearning.ulms.admin.graduation.dao.GraduationDAOFactory;
import com.ulearning.ulms.admin.graduation.exceptions.GraduationCertNoDuplicateException;
import com.ulearning.ulms.admin.graduation.form.GraduationForm;
import com.ulearning.ulms.common.helper.HistoryHelper;
import com.ulearning.ulms.common.helper.ScoreHelper;
import com.ulearning.ulms.common.model.ScoreModel;
import com.ulearning.ulms.common.util.ScoreConstants;
import com.ulearning.ulms.core.security.bean.SecurityConstants;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.course.helper.CourseHelper;
import com.ulearning.ulms.course.helper.CourseUserHelper;
import com.ulearning.ulms.course.model.CourseRoleListModel;
import com.ulearning.ulms.course.model.CourseRoleModel;
import com.ulearning.ulms.course.model.CourseUserModel;
import com.ulearning.ulms.course.util.CourseKeys;
import com.ulearning.ulms.course.webimpls.CourseUserWebImpl;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class graduationManageAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                String[] userIDs = request.getParameterValues("userID");
                int relationID = StringUtil.parseInt(request.getParameter("relationID"));

                //�������֤��
                int type = SecurityConstants.USER_CERTIFICATE_RELATION;

                String compulsoryNum = "";
                String electiveNum = "";
                int isPass = ScoreConstants.UNPASS;
                String certNo = "";
                int userID = 0;
                String scoreID_str = null;
                int scoreID;
                int operateType = 0;
                double totalCert = 0.0;
                String startDate = "";
                GraduationForm gf = new GraduationForm();
                gf.setRelationID(relationID);
                gf.setType(type);

                GraduationDAO dao = GraduationDAOFactory.getDAO();
                boolean isDuplicate = false;
                System.out.println("length =============================" +
                        userIDs.length);

                for (int i = 0; i < userIDs.length; i++)
                {
                        scoreID_str = (String) request.getParameter("scoreID" + userIDs[i]);
                        scoreID = Integer.parseInt(scoreID_str);

                        userID = Integer.parseInt(userIDs[i]);
                        compulsoryNum = request.getParameter("compulsoryNum" + userID);
                        electiveNum = request.getParameter("electiveNum" + userID);
                        isPass = StringUtil.parseInt(request.getParameter("status" +
                                userID));
                        certNo = request.getParameter("certNo" + userID);
                        operateType = StringUtil.parseInt(request.getParameter("operate" +
                                userID));
                        startDate = request.getParameter("startDate" + userID);
                        totalCert = Double.parseDouble(compulsoryNum) +
                                Double.parseDouble(electiveNum);
                        //��û��ѡ���ҵ״̬ʱ����ѧ���ĳɼ���������ҵ״̬

                        //ѧԱ�Ƿ��ҵ��ҳ���������� ,�ʲ���Ҫ�ڴ����ж�

                        /*                    if (isPass == -1)
                        {
                                LogUtil.debug("Graduation", "[graduationManageAction] ===================  ����ѧ���ж�ѧ���ı�ҵ״̬!!!");
                                int limit1 = 0;
                                int limit2 = 0;
                                CertForm cf = null;
                                cf = CertHelper.getCert(relationID);
                                if (cf.getCompulsoryType().equals("1"))
                                {
                                        limit1 = (int) cf.getCompulsoryCredit();
                                }
                                if (cf.getTotalType().equals("1"))
                                {
                                        limit2 = (int) cf.getTotalCredit();
                                }
                                totalCert = Double.parseDouble(compulsoryNum) + Double.parseDouble(electiveNum);
                                if (Double.parseDouble(compulsoryNum) >= limit1 && totalCert >= limit2)
                                {
                                        isPass = ScoreConstants.PASS;
                                }
                        }*/
                        LogUtil.debug("Graduation",
                                "[graduationManageAction] ===================  ����ѧ���ı�ҵ״̬!!!");
                        //�ж�֤���Ƿ��ظ�
                        isDuplicate = dao.isDuplicate(certNo, relationID, type, userID);

                        if ((certNo != null) &&
                                dao.isDuplicate(certNo, relationID, type, userID))
                        {
                                LogUtil.debug("course", "[selegraduationManageAction]  �����ظ�!");
                                throw new GraduationCertNoDuplicateException(certNo + " �����ظ�!");
                        }

                        gf.setCertNo(certNo);
                        gf.setCompulsoryScore(Double.parseDouble(compulsoryNum));
                        gf.setElectiveScore(Double.parseDouble(electiveNum));
                        gf.setUserID(userID);
                        gf.setStartDate(startDate);
                        gf.setStatus(isPass);
                        System.out.println("isPass ====================================== " +
                                isPass);

                        if (operateType == 1) //�޸�
                        {
                                dao.modifyGraduation(gf);
                                System.out.println(
                                        "gf.getStatus() ====================================== " +
                                                gf.getStatus());
                                System.out.println("�޸� ====================================== ");
                        }
                        else if (operateType == -1) //���
                        {
                                dao.addGraduation(gf);
                                System.out.println("��� ====================================== ");
                        }

                        LogUtil.debug("Graduation",
                                "[graduationManageAction] ===================  �޸�ѧ����ѧϰ״̬!!!");

                        int state = CourseKeys.COURSE_USER_FINISH_STATE;

                        Date now = new Date();
                        String score = "";
                        int scoreType = 1;
                        float credit = (float) totalCert;
                        int examType = 0;
                        int period = 0;
                        ScoreModel sm = new ScoreModel(scoreID, userID, relationID, type,
                                score, scoreType, credit, isPass, examType, now, now);

                        if (scoreID == -1) //�ɼ���û�У���Ҫ����
                        {
                                LogUtil.info("common",
                                        "[ScoreManageAction]===========�ɼ���û�У���Ҫ����");
                                ScoreHelper.insert(sm);
                        }
                        else //�ɼ��Ѵ��ڣ���Ҫ����
                        {
                                LogUtil.info("common",
                                        "[ScoreManageAction]===========//�ɼ��Ѵ��ڣ���Ҫ����");
                                ScoreHelper.update(sm);
                        }

                        //���ɼ���û�У��������ǵ�һ����˸�ѧ���ɼ�����ֹͣ��ѧ��ѧϰ�ÿγ̣��������������
                        if (scoreID == -1)
                        {
                                LogUtil.info("common",
                                        "[ScoreManageAction]===========�ɼ���û�У��������ǵ�һ����˸�ѧ���ɼ�����ֹͣ��ѧ��ѧϰ�ÿγ̣��������������");
                                CourseUserHelper.updateCourseUser(userID, relationID,
                                        SecurityConstants.USER_CERTIFICATE_RELATION, new Date(),
                                        CourseKeys.COURSE_USER_FINISH_STATE);
                        }

                        HistoryHelper.update(userID, relationID, type, score, state,
                                scoreType, period, credit, isPass);
                }

                return mapping.findForward(resultScreen);
        }
}
