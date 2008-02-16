/**
 * FinanceReporterHelper.java.
 * User: liz  Date: 2006-1-5
 * ����ͳ�Ʊ���ģ��
 * All rights reserved.
 */
package com.ulearning.ulms.finance.helper;

import com.ulearning.ulms.admin.certificate.bean.CertHelper;
import com.ulearning.ulms.admin.certificate.model.CertModel;
import com.ulearning.ulms.core.util.DateTimeUtil;
import com.ulearning.ulms.course.model.Course;
import com.ulearning.ulms.course.model.CourseModel;
import com.ulearning.ulms.course.util.CourseKeys;
import com.ulearning.ulms.finance.form.CertChangeStatForm;
import com.ulearning.ulms.finance.form.FinanceAlwaysReport;
import com.ulearning.ulms.finance.form.FinanceCerReportForm;
import com.ulearning.ulms.finance.model.FuserAccountDetailModel;
import com.ulearning.ulms.finance.model.FuserAccountModel;
import com.ulearning.ulms.organ.bean.OrganHelper;
import com.ulearning.ulms.organ.dao.OrganDAO;
import com.ulearning.ulms.organ.dao.OrganDAOFactory;
import com.ulearning.ulms.organ.form.OrganForm;
import com.ulearning.ulms.organ.model.OrganModel;
import com.ulearning.ulms.user.model.UserModel;
import com.ulearning.ulms.util.HibernateDAO;
import com.ulearning.ulms.util.ReportMonth;
import com.ulearning.ulms.util.monthNature;

import java.util.*;


public class FinanceReporterHelper
{
        /**
         * �������ڶκͰ༶ͳ�Ƹð༶�Ĳ������
         *
         * @param beginDate
         * @param endDate
         * @param cerid
         * @return List is FinanceCerReportForm
         */
        public List getCerreport(String beginDate, String endDate, int cerid)
        {
                List list = null;
                List resultlist = null;

                if (cerid < 1) //û�а༶������NULL
                {
                        return resultlist;
                }

                //û��ͳ������������NULL
                if (((null == beginDate) || beginDate.equals("")) &&
                        ((null == endDate) || endDate.equals("")) && (cerid < 1))
                {
                        return resultlist;
                }

                StringBuffer hql = new StringBuffer();
                hql.append(
                        " from CourseUser cu,UserModel um,CertModel cm,FuserAccountDetailModel udm");
                hql.append(",OrganUserModel oum,OrganModel om,FuserAccountModel fm");
                hql.append(" where cu.comp_id.type=4 and cu.comp_id.userID=um.userid");
                hql.append(
                        " and oum.comp_id.userid=cu.comp_id.userID and oum.comp_id.orgid=om.orgid"); //�û����ڲ���
                hql.append(" and udm.uadetailInOutType=2 and udm.uadetailTypeId=10"); //֧������ѵ��
                hql.append(" and cu.comp_id.relationID=cm.certificateID");
                hql.append(
                        " and cu.comp_id.userID=udm.userId and cu.comp_id.userID=fm.userId");
                hql.append(" and cu.comp_id.relationID=udm.uadetailEntityValue");
                hql.append(" and cu.comp_id.relationID=");
                hql.append(cerid);

                if (((null != beginDate) && !beginDate.equals("")) &&
                        ((null != endDate) && !endDate.equals("")))
                {
                        hql.append(" and cu.joinTime>=:startDateTime");
                        hql.append(" and cu.joinTime<=:endDateTime");
                }
                else if ((null != endDate) && !endDate.equals(""))
                {
                        hql.append(" and cu.joinTime<=:endDateTime");
                }
                else if ((null != beginDate) && !beginDate.equals(""))
                {
                        hql.append(" and cu.joinTime>=:startDateTime");
                }

                list = HibernateDAO.find(hql.toString(), beginDate, endDate, -1, -1); //Hibernate��ѯ

                FinanceCerReportForm frm = null;
                int totalUser = 0; //ͳ������
                double totalsum = 0.0; //ͳ�ƽ��
                Object[] obj = null;

                if (null == list)
                {
                        return resultlist;
                }

                resultlist = new ArrayList();

                for (Iterator iter = list.iterator(); iter.hasNext();)
                {
                        obj = (Object[]) iter.next();
                        frm = new FinanceCerReportForm();
                        makeUserInfo(obj, frm); //����,�û���Ϣ
                        makeCerInfo(obj, frm); //����,�༶��Ϣ
                        makeAccInfo(obj, frm); //����,������Ϣ
                        totalsum = makeAmountInfo(obj, frm, totalsum); //����,������Ϣ,�ϼƽ��
                        makeDeptInfo(obj, frm); //����,������Ϣ
                        ++totalUser; //�������
                        frm.setSerialNo(Integer.toString(totalUser));
                        resultlist.add(frm); //���������
                }

                resultlist.add(makeSumData(totalsum, totalUser)); //���ã������ϼ���Ϣ

                return resultlist;
        }

        /**
         * �������ڶκͰ༶ͳ�Ƹð༶�Ĳ������
         *
         * @param beginDate
         * @param endDate
         * @param cerid
         * @return List is FinanceCerReportForm
         */
        public List getCerreportOrg(String beginDate, String endDate, int cerid,
                                    String orgid)
        {
                List list = null;
                List resultlist = null;

                StringBuffer hql = new StringBuffer();
                hql.append(
                        " from CourseUser cu,UserModel um,CertModel cm,FuserAccountDetailModel udm");
                hql.append(",OrganUserModel oum,OrganModel om,FuserAccountModel fm");
                hql.append(" where cu.comp_id.type=4 and cu.comp_id.userID=um.userid");
                hql.append(
                        " and oum.comp_id.userid=cu.comp_id.userID and oum.comp_id.orgid=om.orgid"); //�û����ڲ���
                hql.append(" and udm.uadetailInOutType=2 and udm.uadetailTypeId=10"); //֧������ѵ��
                hql.append(" and cu.comp_id.relationID=cm.certificateID");
                hql.append(
                        " and cu.comp_id.userID=udm.userId and cu.comp_id.userID=fm.userId");
                hql.append(" and cu.comp_id.relationID=udm.uadetailEntityValue");

                if (cerid > 1)
                {
                        hql.append(" and cu.comp_id.relationID=");
                        hql.append(cerid);
                }

                if (((null != beginDate) && !beginDate.equals("")) &&
                        ((null != endDate) && !endDate.equals("")))
                {
                        hql.append(" and cu.joinTime>=:startDateTime");
                        hql.append(" and cu.joinTime<=:endDateTime");
                }
                else if ((null != endDate) && !endDate.equals(""))
                {
                        hql.append(" and cu.joinTime<=:endDateTime");
                }
                else if ((null != beginDate) && !beginDate.equals(""))
                {
                        hql.append(" and cu.joinTime>=:startDateTime");
                }

                if (!((null == orgid) || "".equals(orgid) || "null".equals(orgid)))
                {
                        StringBuffer orgwhere = new StringBuffer();
                        OrganDAO organDao = OrganDAOFactory.getDAO();
                        List orgchild = organDao.getOrganChildbyOrgID(Integer.parseInt(
                                orgid));

                        for (int i = 0; i < orgchild.size(); i++)
                        {
                                OrganForm of = (OrganForm) orgchild.get(i);
                                orgwhere.append(of.getOrgID());

                                if (i < (orgchild.size() - 1))
                                {
                                        orgwhere.append(",");
                                }
                        }

                        if ((null == orgwhere.toString()) ||
                                "".equals(orgwhere.toString()))
                        {
                                orgwhere.append(orgid);
                        }
                        else
                        {
                                orgwhere.append(",");
                                orgwhere.append(orgid);
                        }

                        hql.append(" and um.catalogid in (");
                        hql.append(orgwhere.toString());
                        hql.append(")");
                }

                System.out.println("sql======" + hql.toString());
                list = HibernateDAO.find(hql.toString(), beginDate, endDate, -1, -1); //Hibernate��ѯ

                FinanceCerReportForm frm = null;
                int totalUser = 0; //ͳ������
                double totalsum = 0.0; //ͳ�ƽ��
                Object[] obj = null;

                if (null == list)
                {
                        return resultlist;
                }

                resultlist = new ArrayList();

                for (Iterator iter = list.iterator(); iter.hasNext();)
                {
                        obj = (Object[]) iter.next();
                        frm = new FinanceCerReportForm();
                        makeUserInfo(obj, frm); //����,�û���Ϣ
                        makeCerInfo(obj, frm); //����,�༶��Ϣ
                        makeAccInfo(obj, frm); //����,������Ϣ
                        totalsum = makeAmountInfo(obj, frm, totalsum); //����,������Ϣ,�ϼƽ��
                        makeDeptInfo(obj, frm); //����,������Ϣ
                        ++totalUser; //�������
                        frm.setSerialNo(Integer.toString(totalUser));
                        resultlist.add(frm); //���������
                }

                resultlist.add(makeSumData(totalsum, totalUser)); //���ã������ϼ���Ϣ

                return resultlist;
        }

        /**
         * ����һ���༶�ĺϼ���Ϣ
         *
         * @param totalsum
         * @param totalUser
         * @return FinanceCerReportForm
         */
        private FinanceCerReportForm makeSumData(double totalsum, double totalUser)
        {
                FinanceCerReportForm frm = new FinanceCerReportForm();
                frm.setSerialNo("�ϼ�");
                frm.setCername("��");
                frm.setDeptName("��");
                frm.setPostalcode("��");
                frm.setSumout(Double.toString(totalsum));
                frm.setUserName("");

                return frm;
        }

        /**
         * �����û���Ϣ
         *
         * @param obj
         * @param frm
         */
        private void makeUserInfo(Object[] obj, FinanceCerReportForm frm)
        {
                UserModel umod = (UserModel) obj[1];

                if ((null == Integer.toString(umod.getUserid())) ||
                        Integer.toString(umod.getUserid()).equals(""))
                {
                        frm.setUseId("��"); //Ϊ�����ȫ�ǿո�
                        frm.setPay("��");
                        frm.setHowToPay("��");
                }
                else
                {
                        frm.setUseId(Integer.toString(umod.getUserid()));
                }

                if ((null == umod.getMail()) || umod.getMail().equals(""))
                {
                        frm.setMail("��"); //Ϊ�����ȫ�ǿո�
                }
                else
                {
                        frm.setMail(umod.getMail());
                }

                if ((null == umod.getPhone()) || umod.getPhone().equals(""))
                {
                        frm.setPhone("��"); //Ϊ�����ȫ�ǿո�
                }
                else
                {
                        frm.setPhone(umod.getPhone());
                }

                if ((null == umod.getName()) || umod.getName().equals(""))
                {
                        frm.setUserName("��"); //Ϊ�����ȫ�ǿո�
                }
                else
                {
                        frm.setUserName(umod.getName());
                }

                if ((null == umod.getPostalcode()) || umod.getPostalcode().equals(""))
                {
                        frm.setPostalcode("��"); //Ϊ�����ȫ�ǿո�
                }
                else
                {
                        frm.setPostalcode(umod.getPostalcode());
                }
        }

        /**
         * �����༶��Ϣ
         *
         * @param obj
         * @param frm
         */
        private void makeCerInfo(Object[] obj, FinanceCerReportForm frm)
        {
                CertModel umod = (CertModel) obj[2];

                if ((null == umod.getName()) || umod.getName().equals(""))
                {
                        frm.setCername("��"); //Ϊ�����ȫ�ǿո�
                }
                else
                {
                        frm.setCername(umod.getName());
                }
        }

        private void makeAccInfo(Object[] obj, FinanceCerReportForm frm)
        {
                FuserAccountModel accmod = (FuserAccountModel) obj[6];

                if ((null == Double.toString(accmod.getUacotTotal())) ||
                        Double.toString(accmod.getUacotTotal()).equals(""))
                {
                        frm.setAccount("��"); //Ϊ�����ȫ�ǿո�
                }
                else
                {
                        frm.setAccount(Double.toString(accmod.getUacotTotal()));
                }
        }

        /**
         * ����������Ϣ
         *
         * @param obj
         * @param frm
         * @return double
         */
        private double makeAmountInfo(Object[] obj, FinanceCerReportForm frm,
                                      double totalsum)
        {
                FuserAccountDetailModel umod = (FuserAccountDetailModel) obj[3];

                if (null == umod.getUadetailDate())
                {
                        //frm.setDate(null);//Ϊ�����ȫ�ǿո�
                }
                else
                {
                        frm.setDate(umod.getUadetailDate());
                }

                frm.setSumout(Double.toString(umod.getUadetailAmount()));

                if (umod.getUadetailAmount() > 0.0)
                {
                        frm.setPay("��");
                        frm.setHowToPay("�ʻ�֧��");
                }
                else
                {
                        frm.setPay("��");
                        frm.setHowToPay("���");
                }

                double tsum = totalsum;
                tsum = totalsum + umod.getUadetailAmount();

                return tsum;
        }

        /**
         * ����������Ϣ
         *
         * @param obj
         * @param frm
         */
        private void makeDeptInfo(Object[] obj, FinanceCerReportForm frm)
        {
                OrganModel umod = (OrganModel) obj[5];

                if ((null == umod.getOrgname()) || umod.getOrgname().equals(""))
                {
                        frm.setDeptName("��"); //Ϊ�����ȫ�ǿո�
                }
                else
                {
                        frm.setDeptName(umod.getOrgname());
                }
        }

        /**
         * �������ڶκͿγ�ͳ�Ƹÿγ̵Ĳ������
         *
         * @param beginDate
         * @param endDate
         * @param courseid
         * @return List is FinanceCerReportForm
         */
        public List getCoursereport(String beginDate, String endDate, int courseid)
        {
                List list = null;
                List resultlist = null;

                if (courseid < 1) //û�а༶������NULL
                {
                        return resultlist;
                }

                //û��ͳ������������NULL
                if (((null == beginDate) || beginDate.equals("")) &&
                        ((null == endDate) || endDate.equals("")) && (courseid < 1))
                {
                        return resultlist;
                }

                StringBuffer hql = new StringBuffer();
                hql.append(
                        " from CourseUser cu,UserModel um,Course cos,FuserAccountDetailModel udm");
                hql.append(",OrganUserModel oum,OrganModel om");
                hql.append(" where cu.comp_id.type=3 and cu.comp_id.userID=um.userid");
                hql.append(
                        " and oum.comp_id.userid=cu.comp_id.userID and oum.comp_id.orgid=om.orgid"); //�û����ڲ���
                hql.append(" and udm.uadetailInOutType=2 and udm.uadetailTypeId=9"); //֧�����γ�
                hql.append(" and cu.comp_id.relationID=cos.courseid");
                hql.append(" and cu.comp_id.userID=udm.userId");
                hql.append(" and cu.comp_id.relationID=udm.uadetailEntityValue");
                hql.append(" and cu.comp_id.relationID=");
                hql.append(courseid);

                if (((null != beginDate) && !beginDate.equals("")) &&
                        ((null != endDate) && !endDate.equals("")))
                {
                        hql.append(" and cu.joinTime>=:startDateTime");
                        hql.append(" and cu.joinTime<=:endDateTime");
                }
                else if ((null != endDate) && !endDate.equals(""))
                {
                        hql.append(" and cu.joinTime<=:endDateTime");
                }
                else if ((null != beginDate) && !beginDate.equals(""))
                {
                        hql.append(" and cu.joinTime>=:startDateTime");
                }

                list = HibernateDAO.find(hql.toString(), beginDate, endDate, -1, -1); //Hibernate��ѯ

                FinanceCerReportForm frm = null;
                int totalUser = 0; //ͳ������
                double totalsum = 0.0; //ͳ�ƽ��
                Object[] obj = null;
                resultlist = new ArrayList();

                for (Iterator iter = list.iterator(); iter.hasNext();)
                {
                        obj = (Object[]) iter.next();
                        frm = new FinanceCerReportForm();
                        makeUserInfo(obj, frm); //����,�û���Ϣ
                        makeCourseInfo(obj, frm); //����,�γ���Ϣ
                        totalsum = makeAmountInfo(obj, frm, totalsum); //����,������Ϣ,�ϼƽ��
                        makeDeptInfo(obj, frm); //����,������Ϣ
                        ++totalUser; //�������
                        frm.setSerialNo(Integer.toString(totalUser));
                        resultlist.add(frm); //���������
                }

                resultlist.add(makeSumData(totalsum, totalUser)); //���ã������ϼ���Ϣ

                return resultlist;
        }

        /**
         * �����γ���Ϣ
         *
         * @param obj
         * @param frm
         */
        private void makeCourseInfo(Object[] obj, FinanceCerReportForm frm)
        {
                Course umod = (Course) obj[2];

                if ((null == umod.getName()) || umod.getName().equals(""))
                {
                        frm.setCername("��"); //Ϊ�����ȫ�ǿո�
                }
                else
                {
                        frm.setCername(umod.getName());
                }
        }

        /**
         * produces always report form of finance
         *
         * @param aspID
         * @param orgID
         * @param month     ���²�ѯ��beginDate endDate��ʧЧ
         * @param beginDate
         * @param endDate
         * @return List
         */
        public List getStatReport(int aspID, int orgID, int month,
                                  String beginDate, String endDate)
        {
                List listResult = null;
                List list = null;
                StringBuffer hql = new StringBuffer();
                hql.append(" from FuserAccountDetailModel udm");
                hql.append(" where udm.uadetailInOutType=2"); //֧����ϸ
                hql.append(" and udm.aspId=");
                hql.append(aspID);
                hql.append(" and udm.orgId=");
                hql.append(orgID);

                if ((1 <= month) && (month <= 12)) //���²�ѯ
                {
                        monthNature date = new monthNature();
                        ReportMonth rm = new ReportMonth();
                        Map mapmn = new HashMap();
                        rm.setState(date);
                        mapmn = rm.makeDateRange(month);
                        beginDate = mapmn.get("beginDate").toString();
                        endDate = mapmn.get("endDate").toString();
                }

                if (((null != beginDate) && !beginDate.equals("")) &&
                        ((null != endDate) && !endDate.equals("")))
                {
                        hql.append(
                                " and udm.uadetailDate>=:startDateTime and udm.uadetailDate<=:endDateTime");
                }
                else if ((null != beginDate) && !beginDate.equals(""))
                {
                        hql.append(" and udm.uadetailDate>=:startDateTime");
                }
                else if ((null != endDate) && !endDate.equals(""))
                {
                        hql.append(" and udm.uadetailDate<=:endDateTime");
                }

                list = HibernateDAO.find(hql.toString(), beginDate, endDate, -1, -1); //Hibernate��ѯ

                if (null == list)
                {
                        return listResult;
                }

                FuserAccountDetailModel model = new FuserAccountDetailModel();
                listResult = new ArrayList();

                double courseSum = 0.0; //�γ̷�
                double certSum = 0.0; //�༶��
                double examSum = 0.0; //���Է�
                double answerSum = 0.0; //���ɷ�
                double materialSum = 0.0; //�̲ķ�
                double trainSum = 0.0; //��ѵ��

                for (Iterator iter = list.iterator(); iter.hasNext();)
                { //��������
                        model = (FuserAccountDetailModel) iter.next();

                        switch (model.getUadetailTypeId())
                        {
                                case 7: //����
                                        examSum = examSum + model.getUadetailAmount();

                                        continue;

                                case 8: //����
                                        answerSum = answerSum + model.getUadetailAmount();

                                        continue;

                                case 9: //�γ�
                                        courseSum = courseSum + model.getUadetailAmount();

                                        continue;

                                case 10: //��ѵ��
                                        certSum = certSum + model.getUadetailAmount();

                                        continue;

                                case 11: //�̲�
                                        materialSum = materialSum + model.getUadetailAmount();

                                        continue;

                                case 12: //��ѵ�
                                        trainSum = trainSum + model.getUadetailAmount();

                                        continue;

                                default:
                                        break;
                        }
                }

                Map map = new HashMap();
                map.put("courseSum", Double.toString(courseSum)); //�γ̷�
                map.put("certSum", Double.toString(certSum)); //�༶��
                map.put("examSum", Double.toString(examSum)); //���Է�
                map.put("answerSum", Double.toString(answerSum)); //���ɷ�
                map.put("materialSum", Double.toString(materialSum)); //�̲ķ�
                map.put("trainSum", Double.toString(trainSum)); //��ѵ��
                listResult = makeCourseData(map); //����

                return listResult;
        }

        /**
         * compose always report form of finance data
         *
         * @param mapsum
         * @return
         */
        private List makeCourseData(Map mapsum)
        {
                FinanceAlwaysReport frm = null;
                List list = null;

                if ((null == mapsum) || (0 == mapsum.size()))
                {
                        return list;
                }

                list = new ArrayList();
                frm = new FinanceAlwaysReport();
                frm.setEntityName("�γ̷�");
                frm.setEntitysum(mapsum.get("courseSum").toString());
                list.add(frm);
                frm = new FinanceAlwaysReport();
                frm.setEntityName("�༶��");
                frm.setEntitysum(mapsum.get("certSum").toString());
                list.add(frm);
                frm = new FinanceAlwaysReport();
                frm.setEntityName("���Է�");
                frm.setEntitysum(mapsum.get("examSum").toString());
                list.add(frm);
                frm = new FinanceAlwaysReport();
                frm.setEntityName("���ɷ�");
                frm.setEntitysum(mapsum.get("answerSum").toString());
                list.add(frm);
                frm = new FinanceAlwaysReport();
                frm.setEntityName("�̲ķ�");
                frm.setEntitysum(mapsum.get("materialSum").toString());
                list.add(frm);
                frm = new FinanceAlwaysReport();
                frm.setEntityName("��ѵ��");
                frm.setEntitysum(mapsum.get("trainSum").toString());
                list.add(frm);

                return list;
        }

        /**
         * Stat course of certificate charge
         *
         * @param certid �༶ID
         * @param type   1 ����֤���Ӧ�ķ����γ�    2 ����֤��ĩʹ�÷����Ŀγ�
         * @return
         */
        public List getCertCourseCharge(String certid, int type)
        {
                List list = null;
                List listResult = null;
                list = CertHelper.getCourseListFromCert(certid, type);

                if (null == list)
                {
                        return listResult;
                }

                CourseModel model = new CourseModel();
                CertChangeStatForm frm = null;
                double charge = 0.0; //�ϼ��շ�
                String courseTypeStr; //
                listResult = new ArrayList();

                for (Iterator iter = list.iterator(); iter.hasNext();)
                {
                        frm = new CertChangeStatForm();
                        model = (CourseModel) iter.next();
                        frm.setCoursename(model.getName());
                        frm.setCoursePeriod(Double.toString(model.getPeriod())); //��ʱ
                        courseTypeStr = "��ͨ";

                        int cType = Integer.parseInt(model.getType());

                        if (cType == CourseKeys.COMPULSORY_COURSE_TYPE)
                        {
                                courseTypeStr = "����";
                        }
                        else if (cType == CourseKeys.ELECTIVE_COURSE_TYPE)
                        {
                                courseTypeStr = "ѡ��";
                        }

                        frm.setCoursetype(courseTypeStr);
                        frm.setCoursecharge(model.getMemberCharge());
                        charge = charge + model.getMemberCharge();
                        listResult.add(frm);
                }

                frm = new CertChangeStatForm();
                frm.setCoursename("�ϼ�");
                frm.setCoursecharge(charge);
                frm.setCoursetype("��");
                frm.setCoursePeriod("��");
                listResult.add(frm);

                return listResult;
        }

        public static void main(String[] args)
        {
                FinanceReporterHelper fh = new FinanceReporterHelper();
                List list = fh.getCerreportOrg("", "", 0, "307");

                for (int i = 0; i < list.size(); i++)
                {
                        FinanceCerReportForm ff = (FinanceCerReportForm) list.get(i);
                        System.out.println(ff.getSerialNo() + "===" + ff.getUserName());
                }
        }
}
