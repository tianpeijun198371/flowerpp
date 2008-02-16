/**
 * FinanceReporterHelper.java.
 * User: liz  Date: 2006-1-5
 * 财务统计报表模块
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
         * 根据日期段和班级统计该班级的财务情况
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

                if (cerid < 1) //没有班级，返回NULL
                {
                        return resultlist;
                }

                //没有统计条件，返回NULL
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
                        " and oum.comp_id.userid=cu.comp_id.userID and oum.comp_id.orgid=om.orgid"); //用户所在部门
                hql.append(" and udm.uadetailInOutType=2 and udm.uadetailTypeId=10"); //支出、培训班
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

                list = HibernateDAO.find(hql.toString(), beginDate, endDate, -1, -1); //Hibernate查询

                FinanceCerReportForm frm = null;
                int totalUser = 0; //统计人数
                double totalsum = 0.0; //统计金额
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
                        makeUserInfo(obj, frm); //调用,用户信息
                        makeCerInfo(obj, frm); //调用,班级信息
                        makeAccInfo(obj, frm); //调用,费用信息
                        totalsum = makeAmountInfo(obj, frm, totalsum); //调用,财务信息,合计金额
                        makeDeptInfo(obj, frm); //调用,部门信息
                        ++totalUser; //产生序号
                        frm.setSerialNo(Integer.toString(totalUser));
                        resultlist.add(frm); //建立结果集
                }

                resultlist.add(makeSumData(totalsum, totalUser)); //调用，建立合计信息

                return resultlist;
        }

        /**
         * 根据日期段和班级统计该班级的财务情况
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
                        " and oum.comp_id.userid=cu.comp_id.userID and oum.comp_id.orgid=om.orgid"); //用户所在部门
                hql.append(" and udm.uadetailInOutType=2 and udm.uadetailTypeId=10"); //支出、培训班
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
                list = HibernateDAO.find(hql.toString(), beginDate, endDate, -1, -1); //Hibernate查询

                FinanceCerReportForm frm = null;
                int totalUser = 0; //统计人数
                double totalsum = 0.0; //统计金额
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
                        makeUserInfo(obj, frm); //调用,用户信息
                        makeCerInfo(obj, frm); //调用,班级信息
                        makeAccInfo(obj, frm); //调用,费用信息
                        totalsum = makeAmountInfo(obj, frm, totalsum); //调用,财务信息,合计金额
                        makeDeptInfo(obj, frm); //调用,部门信息
                        ++totalUser; //产生序号
                        frm.setSerialNo(Integer.toString(totalUser));
                        resultlist.add(frm); //建立结果集
                }

                resultlist.add(makeSumData(totalsum, totalUser)); //调用，建立合计信息

                return resultlist;
        }

        /**
         * 建立一个班级的合计信息
         *
         * @param totalsum
         * @param totalUser
         * @return FinanceCerReportForm
         */
        private FinanceCerReportForm makeSumData(double totalsum, double totalUser)
        {
                FinanceCerReportForm frm = new FinanceCerReportForm();
                frm.setSerialNo("合计");
                frm.setCername("　");
                frm.setDeptName("金额：");
                frm.setPostalcode("　");
                frm.setSumout(Double.toString(totalsum));
                frm.setUserName("");

                return frm;
        }

        /**
         * 建立用户信息
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
                        frm.setUseId("　"); //为空添加全角空格
                        frm.setPay("　");
                        frm.setHowToPay("　");
                }
                else
                {
                        frm.setUseId(Integer.toString(umod.getUserid()));
                }

                if ((null == umod.getMail()) || umod.getMail().equals(""))
                {
                        frm.setMail("　"); //为空添加全角空格
                }
                else
                {
                        frm.setMail(umod.getMail());
                }

                if ((null == umod.getPhone()) || umod.getPhone().equals(""))
                {
                        frm.setPhone("　"); //为空添加全角空格
                }
                else
                {
                        frm.setPhone(umod.getPhone());
                }

                if ((null == umod.getName()) || umod.getName().equals(""))
                {
                        frm.setUserName("　"); //为空添加全角空格
                }
                else
                {
                        frm.setUserName(umod.getName());
                }

                if ((null == umod.getPostalcode()) || umod.getPostalcode().equals(""))
                {
                        frm.setPostalcode("　"); //为空添加全角空格
                }
                else
                {
                        frm.setPostalcode(umod.getPostalcode());
                }
        }

        /**
         * 建立班级信息
         *
         * @param obj
         * @param frm
         */
        private void makeCerInfo(Object[] obj, FinanceCerReportForm frm)
        {
                CertModel umod = (CertModel) obj[2];

                if ((null == umod.getName()) || umod.getName().equals(""))
                {
                        frm.setCername("　"); //为空添加全角空格
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
                        frm.setAccount("　"); //为空添加全角空格
                }
                else
                {
                        frm.setAccount(Double.toString(accmod.getUacotTotal()));
                }
        }

        /**
         * 建立财务信息
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
                        //frm.setDate(null);//为空添加全角空格
                }
                else
                {
                        frm.setDate(umod.getUadetailDate());
                }

                frm.setSumout(Double.toString(umod.getUadetailAmount()));

                if (umod.getUadetailAmount() > 0.0)
                {
                        frm.setPay("是");
                        frm.setHowToPay("帐户支付");
                }
                else
                {
                        frm.setPay("否");
                        frm.setHowToPay("免费");
                }

                double tsum = totalsum;
                tsum = totalsum + umod.getUadetailAmount();

                return tsum;
        }

        /**
         * 建立部门信息
         *
         * @param obj
         * @param frm
         */
        private void makeDeptInfo(Object[] obj, FinanceCerReportForm frm)
        {
                OrganModel umod = (OrganModel) obj[5];

                if ((null == umod.getOrgname()) || umod.getOrgname().equals(""))
                {
                        frm.setDeptName("　"); //为空添加全角空格
                }
                else
                {
                        frm.setDeptName(umod.getOrgname());
                }
        }

        /**
         * 根据日期段和课程统计该课程的财务情况
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

                if (courseid < 1) //没有班级，返回NULL
                {
                        return resultlist;
                }

                //没有统计条件，返回NULL
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
                        " and oum.comp_id.userid=cu.comp_id.userID and oum.comp_id.orgid=om.orgid"); //用户所在部门
                hql.append(" and udm.uadetailInOutType=2 and udm.uadetailTypeId=9"); //支出、课程
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

                list = HibernateDAO.find(hql.toString(), beginDate, endDate, -1, -1); //Hibernate查询

                FinanceCerReportForm frm = null;
                int totalUser = 0; //统计人数
                double totalsum = 0.0; //统计金额
                Object[] obj = null;
                resultlist = new ArrayList();

                for (Iterator iter = list.iterator(); iter.hasNext();)
                {
                        obj = (Object[]) iter.next();
                        frm = new FinanceCerReportForm();
                        makeUserInfo(obj, frm); //调用,用户信息
                        makeCourseInfo(obj, frm); //调用,课程信息
                        totalsum = makeAmountInfo(obj, frm, totalsum); //调用,财务信息,合计金额
                        makeDeptInfo(obj, frm); //调用,部门信息
                        ++totalUser; //产生序号
                        frm.setSerialNo(Integer.toString(totalUser));
                        resultlist.add(frm); //建立结果集
                }

                resultlist.add(makeSumData(totalsum, totalUser)); //调用，建立合计信息

                return resultlist;
        }

        /**
         * 建立课程信息
         *
         * @param obj
         * @param frm
         */
        private void makeCourseInfo(Object[] obj, FinanceCerReportForm frm)
        {
                Course umod = (Course) obj[2];

                if ((null == umod.getName()) || umod.getName().equals(""))
                {
                        frm.setCername("　"); //为空添加全角空格
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
         * @param month     按月查询，beginDate endDate将失效
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
                hql.append(" where udm.uadetailInOutType=2"); //支出明细
                hql.append(" and udm.aspId=");
                hql.append(aspID);
                hql.append(" and udm.orgId=");
                hql.append(orgID);

                if ((1 <= month) && (month <= 12)) //按月查询
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

                list = HibernateDAO.find(hql.toString(), beginDate, endDate, -1, -1); //Hibernate查询

                if (null == list)
                {
                        return listResult;
                }

                FuserAccountDetailModel model = new FuserAccountDetailModel();
                listResult = new ArrayList();

                double courseSum = 0.0; //课程费
                double certSum = 0.0; //班级费
                double examSum = 0.0; //考试费
                double answerSum = 0.0; //答疑费
                double materialSum = 0.0; //教材费
                double trainSum = 0.0; //培训费

                for (Iterator iter = list.iterator(); iter.hasNext();)
                { //建立报表
                        model = (FuserAccountDetailModel) iter.next();

                        switch (model.getUadetailTypeId())
                        {
                                case 7: //考试
                                        examSum = examSum + model.getUadetailAmount();

                                        continue;

                                case 8: //答疑
                                        answerSum = answerSum + model.getUadetailAmount();

                                        continue;

                                case 9: //课程
                                        courseSum = courseSum + model.getUadetailAmount();

                                        continue;

                                case 10: //培训班
                                        certSum = certSum + model.getUadetailAmount();

                                        continue;

                                case 11: //教材
                                        materialSum = materialSum + model.getUadetailAmount();

                                        continue;

                                case 12: //培训活动
                                        trainSum = trainSum + model.getUadetailAmount();

                                        continue;

                                default:
                                        break;
                        }
                }

                Map map = new HashMap();
                map.put("courseSum", Double.toString(courseSum)); //课程费
                map.put("certSum", Double.toString(certSum)); //班级费
                map.put("examSum", Double.toString(examSum)); //考试费
                map.put("answerSum", Double.toString(answerSum)); //答疑费
                map.put("materialSum", Double.toString(materialSum)); //教材费
                map.put("trainSum", Double.toString(trainSum)); //培训费
                listResult = makeCourseData(map); //调用

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
                frm.setEntityName("课程费");
                frm.setEntitysum(mapsum.get("courseSum").toString());
                list.add(frm);
                frm = new FinanceAlwaysReport();
                frm.setEntityName("班级费");
                frm.setEntitysum(mapsum.get("certSum").toString());
                list.add(frm);
                frm = new FinanceAlwaysReport();
                frm.setEntityName("考试费");
                frm.setEntitysum(mapsum.get("examSum").toString());
                list.add(frm);
                frm = new FinanceAlwaysReport();
                frm.setEntityName("答疑费");
                frm.setEntitysum(mapsum.get("answerSum").toString());
                list.add(frm);
                frm = new FinanceAlwaysReport();
                frm.setEntityName("教材费");
                frm.setEntitysum(mapsum.get("materialSum").toString());
                list.add(frm);
                frm = new FinanceAlwaysReport();
                frm.setEntityName("培训费");
                frm.setEntitysum(mapsum.get("trainSum").toString());
                list.add(frm);

                return list;
        }

        /**
         * Stat course of certificate charge
         *
         * @param certid 班级ID
         * @param type   1 返回证书对应的发布课程    2 返回证书末使用范本的课程
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
                double charge = 0.0; //合计收费
                String courseTypeStr; //
                listResult = new ArrayList();

                for (Iterator iter = list.iterator(); iter.hasNext();)
                {
                        frm = new CertChangeStatForm();
                        model = (CourseModel) iter.next();
                        frm.setCoursename(model.getName());
                        frm.setCoursePeriod(Double.toString(model.getPeriod())); //课时
                        courseTypeStr = "普通";

                        int cType = Integer.parseInt(model.getType());

                        if (cType == CourseKeys.COMPULSORY_COURSE_TYPE)
                        {
                                courseTypeStr = "必修";
                        }
                        else if (cType == CourseKeys.ELECTIVE_COURSE_TYPE)
                        {
                                courseTypeStr = "选修";
                        }

                        frm.setCoursetype(courseTypeStr);
                        frm.setCoursecharge(model.getMemberCharge());
                        charge = charge + model.getMemberCharge();
                        listResult.add(frm);
                }

                frm = new CertChangeStatForm();
                frm.setCoursename("合计");
                frm.setCoursecharge(charge);
                frm.setCoursetype("　");
                frm.setCoursePeriod("　");
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
