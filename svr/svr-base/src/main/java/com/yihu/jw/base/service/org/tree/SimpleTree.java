package com.yihu.jw.base.service.org.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class SimpleTree implements Tree{
    private HashMap<String, SimpleTreeNode> treeNodesMap = new HashMap<String, SimpleTreeNode>();
    private List<SimpleTreeNode> treeNodesList = new ArrayList<SimpleTreeNode>();

    public SimpleTree(List<TreeNode> list){
        initTreeNodeMap(list);
        initTreeNodeList();
    }

    private void initTreeNodeMap(List<TreeNode> list){
        SimpleTreeNode treeNode = null;
        for(TreeNode item : list){
            treeNode = new SimpleTreeNode(item);
            treeNodesMap.put(treeNode.getNodeId(), treeNode);
        }

        Iterator<SimpleTreeNode> iter = treeNodesMap.values().iterator();
        SimpleTreeNode parentTreeNode = null;
        while(iter.hasNext()){
            treeNode = iter.next();
            if(treeNode.getParentNodeId() == null || treeNode.getParentNodeId() == ""){
                continue;
            }

            parentTreeNode = treeNodesMap.get(treeNode.getParentNodeId());
            if(parentTreeNode != null){
                treeNode.setParent(parentTreeNode);
                parentTreeNode.addChild(treeNode);
            }
        }
    }

    private void initTreeNodeList(){
        if(treeNodesList.size() > 0){
            return;
        }
        if(treeNodesMap.size() == 0){
            return;
        }
        Iterator<SimpleTreeNode> iter = treeNodesMap.values().iterator();
        SimpleTreeNode treeNode = null;
        while(iter.hasNext()){
            treeNode = iter.next();
            if(treeNode.getParent() == null){
                this.treeNodesList.add(treeNode);
                this.treeNodesList.addAll(treeNode.getAllChildren());
            }
        }
    }

    @Override
    public List<SimpleTreeNode> getTree() {
        return this.treeNodesList;
    }

    @Override
    public List<SimpleTreeNode> getRoot() {
        List<SimpleTreeNode> rootList = new ArrayList<>();
        if (this.treeNodesList.size() > 0) {
            for (SimpleTreeNode node : treeNodesList) {
                if (node.getParent() == null)
                    rootList.add(node);
            }
        }
        return rootList;
    }

    @Override
    public SimpleTreeNode getTreeNode(String nodeId) {
        return this.treeNodesMap.get(nodeId);
    }

}
