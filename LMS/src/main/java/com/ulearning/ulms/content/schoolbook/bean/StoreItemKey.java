package com.ulearning.ulms.content.schoolbook.bean;

import org.apache.commons.beanutils.BeanUtils;

/**
 * ����������������Ӧ��
 */
import java.io.Serializable;

import java.lang.reflect.InvocationTargetException;

import java.util.ArrayList;
import java.util.List;


public class StoreItemKey implements Serializable
{
        // �̲Ļ�����ϢID��
        private String[] infoid;

        // �������
        private Integer tcitemquantity;

        // ��Ӧ��id
        private Integer tcitemsupplierid;

        // ��Ӧ����������ID
        private Integer mainstoreId;

        public StoreItemKey()
        {
        }

        public StoreItemKey(String[] infoid, Integer tcitemquantity,
                            Integer tcitemsupplierid, Integer mainstoreId)
        {
                this.infoid = infoid;
                this.tcitemquantity = tcitemquantity;
                this.tcitemsupplierid = tcitemsupplierid;
                this.mainstoreId = mainstoreId;
        }

        // ����infoid���ɶ����������λ��
        public static List buildStoreItemUnit(StoreItemKey parent)
        {
                List list = null;

                if (parent.infoid.length > 0)
                {
                        list = new ArrayList();

                        try
                        {
                                for (int i = 0; i < parent.infoid.length; i++)
                                {
                                        StoreItemUnit siu = new StoreItemUnit();
                                        siu.setUnitInfoId(new Integer(parent.infoid[i]));
                                        BeanUtils.copyProperties(siu, parent);
                                        list.add(siu);
                                }
                        }
                        catch (IllegalAccessException e)
                        {
                                e.printStackTrace();
                        }
                        catch (InvocationTargetException e)
                        {
                                e.printStackTrace();
                        }
                        catch (Exception e)
                        {
                                e.printStackTrace();
                        }

                        return list;
                }

                return null;
        }

        public static void main(String[] args)
        {
                StoreItemKey key = new StoreItemKey(new String[]{"1", "2", "3"},
                        new Integer(1), new Integer(11), new Integer(22));
                List list = StoreItemKey.buildStoreItemUnit(key);

                for (int i = 0; i < list.size(); i++)
                {
                        System.out.println(list.get(i));
                }
        }

        public String[] getInfoid()
        {
                return infoid;
        }

        public void setInfoid(String[] infoid)
        {
                this.infoid = infoid;
        }

        public Integer getItemId()
        {
                return mainstoreId;
        }

        public void setItemId(Integer itemId)
        {
                this.mainstoreId = itemId;
        }

        public Integer getTcitemquantity()
        {
                return tcitemquantity;
        }

        public void setTcitemquantity(Integer tcitemquantity)
        {
                this.tcitemquantity = tcitemquantity;
        }

        public Integer getTcitemsupplierid()
        {
                return tcitemsupplierid;
        }

        public void setTcitemsupplierid(Integer tcitemsupplierid)
        {
                this.tcitemsupplierid = tcitemsupplierid;
        }

        public Integer getMainstoreId()
        {
                return mainstoreId;
        }

        public void setMainstoreId(Integer mainstoreId)
        {
                this.mainstoreId = mainstoreId;
        }
}
