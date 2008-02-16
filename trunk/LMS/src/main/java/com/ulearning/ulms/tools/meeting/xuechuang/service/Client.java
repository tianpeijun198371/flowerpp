package com.ulearning.ulms.tools.meeting.xuechuang.service;

public class Client
{
        public static void main(String[] args)
                throws org.apache.axis2.AxisFault, java.rmi.RemoteException,
                ExceptionException0
        {
                VersionStub vs = new VersionStub();
                com.ulearning.ulms.tools.meeting.xuechuang.service.VersionStub.GetVersionResponse result =
                        vs.getVersion();
                System.out.println("result=" + result);
                System.out.println("result.get_return=" + result.get_return());
        }

        public static String getVersion()
                throws org.apache.axis2.AxisFault, java.rmi.RemoteException,
                ExceptionException0
        {
                VersionStub vs = new VersionStub();
                com.ulearning.ulms.tools.meeting.xuechuang.service.VersionStub.GetVersionResponse result =
                        vs.getVersion();

                return result.get_return();
        }
}
