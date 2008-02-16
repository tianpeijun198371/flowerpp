/**
 * CloseNode.java.
 * User: keyh  Date: 2004-9-7
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.util.treeControl;

import java.util.List;
import java.io.Serializable;

public class TreeUtil implements Serializable
{
        public static int getNodeState(List list, int id)
        {
                for (int i = 0; i < list.size(); i++)
                {
                        TreeNode node = new TreeNode();
                        node = (TreeNode) list.get(i);
                        if (id == node.getID())
                        {
                                if (node.isExpanded())
                                {
                                        System.out.println("getNodeState: found node expended,id=" + id);
                                        return 1;
                                }
                                else
                                {
                                        System.out.println("getNodeState: found node closed,id=" + id);
                                        return 0;
                                }
                        }
                }
                System.out.println("getNodeState:Could not found node,id=" + id);
                return -1;
        }

        public static int getNodePosition(List list, int id)
        {
                for (int i = 0; i < list.size(); i++)
                {
                        TreeNode node = new TreeNode();
                        node = (TreeNode) list.get(i);
                        if (id == node.getID())
                        {
                                return i;
                        }
                }
                return -1;
        }

        public static List clickNode(List list, List childList, int id)
        {
                int pos = getNodePosition(list, id);
                TreeNode node = new TreeNode();
                node = (TreeNode) list.get(pos);
                if (!node.isLeaf())
                {

                        int nodeState = getNodeState(list, id);
                        if (nodeState == 1)
                        {
                                System.out.println("clickNode:Close Node.");
                                closeNode(list, id);

                        }
                        else if (nodeState == 0)
                        {
                                System.out.println("clickNode:Expand Node.");
                                expandNode(list, childList, id);

                        }
                        else
                        {
                                System.out.println("clickNode:Wrong State.");
                                return null;
                        }
                }
                return list;


        }

        public static List closeNode(List list, int id)
        {

                int pos = getNodePosition(list, id);
                TreeNode node = (TreeNode) list.get(pos);
                String description = node.getDescription();
                deleteChildNode(list, description);
                setNodeExpanded(list, id, false);
                return list;
        }

        public static List expandNode(List list, List childList, int id)
        {
                setNodeExpanded(list, id, true);
                getChildNode(list, childList, id);
                return list;
        }

        public static List deleteChildNode(List list, String description)
        {
                int length = description.length();

                System.out.println("deleteChildNode,parentDescription=" + description);
                for (int i = 0; i < list.size(); i++)
                {
                        TreeNode node = new TreeNode();
                        node = (TreeNode) list.get(i);
                        String nodeDescription = node.getDescription();
                        if (nodeDescription.startsWith(description) && nodeDescription.length() > length)
                        {
                                System.out.println("deleteChildNode,parentDescription=" + description +
                                        "found child,index=" + i);
                                list.remove(i);
                                i--;
                        }

                }

                return list;
        }

        public static List getChildNode(List list, List childList, int id)
        {
                int pos = getNodePosition(list, id);
                TreeNode pnode = new TreeNode();
                pnode = (TreeNode) list.get(pos);
                int layer = pnode.getLayer();
                String description = pnode.getDescription();
                System.out.println("getChildNode,parentid=" + id + ",childsize=" + childList.size());
                for (int i = 0; i < childList.size(); i++)
                {
                        TreeNode cnode = new TreeNode();
                        cnode = (TreeNode) childList.get(i);
                        cnode.setLayer(layer + 1);
                        cnode.setDescription(description + cnode.getID());
                        list.add(++pos, cnode);

                }
                return list;
        }

        public static List setNodeExpanded(List list, int id, boolean expanded)
        {
                int pos = getNodePosition(list, id);
                TreeNode node = new TreeNode();
                node = (TreeNode) list.get(pos);
                node.setExpanded(expanded);
                list.set(pos, node);
                System.out.println("setNodeExpanded,id=" + id + ",expanded=" + expanded);
                return list;
        }


}

