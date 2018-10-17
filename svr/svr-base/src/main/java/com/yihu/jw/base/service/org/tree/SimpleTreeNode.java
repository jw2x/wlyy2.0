package com.yihu.jw.base.service.org.tree;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.ArrayList;
import java.util.List;

public class SimpleTreeNode {
    //树节点ID
    @JSONField(ordinal=1)
    private String nodeId;
    //树节点名称
    @JSONField(ordinal=2)
    private String nodeName;
    //父节点ID
    @JSONField(ordinal=3)
    private String parentNodeId;
    //节点在树中的排序号
    @JSONField(ordinal=4)
    private int orderNum;
    //节点所在的层级
    @JSONField(ordinal=5)
    private int level;
    private SimpleTreeNode parent;
    //当前节点的二子节点
    @JSONField(ordinal=6)
    private List<SimpleTreeNode> children = new ArrayList<>();
    //当前节点的子孙节点
    private List<SimpleTreeNode> allChildren = new ArrayList<>();

    public SimpleTreeNode(TreeNode obj){
        this.nodeId = obj.extractNodeId();
        this.nodeName = obj.extractNodeName();
        this.parentNodeId = obj.extractNodeParentId();
//        this.level = obj.extractOrderNum();
    }
    public void addChild(SimpleTreeNode treeNode){
        this.children.add(treeNode);
    }
    public void removeChild(TreeNode treeNode){
        this.children.remove(treeNode);
    }
    public String getNodeId() {
        return nodeId;
    }
    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }
    public String getNodeName() {
        return nodeName;
    }
    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }
    public String getParentNodeId() {
        return parentNodeId;
    }
    public void setParentNodeId(String parentNodeId) {
        this.parentNodeId = parentNodeId;
    }
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public SimpleTreeNode getParent() {
        return parent;
    }
    public void setParent(SimpleTreeNode parent) {
        this.parent = parent;
    }
    public List<SimpleTreeNode> getChildren() {
        return children;
    }
    public void setChildren(List<SimpleTreeNode> children) {
        this.children = children;
    }
    public int getOrderNum() {
        return orderNum;
    }
    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public List<SimpleTreeNode> getAllChildren() {
        if(this.allChildren.isEmpty()){
            for(SimpleTreeNode treeNode : this.children){
                this.allChildren.add(treeNode);
                this.allChildren.addAll(treeNode.getAllChildren());
            }
        }
        return this.allChildren;
    }

}
