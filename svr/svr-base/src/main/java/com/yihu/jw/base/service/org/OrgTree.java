package com.yihu.jw.base.service.org;

import com.yihu.jw.base.service.org.tree.TreeNode;

public class OrgTree implements TreeNode {
    private Integer id;
    private String code;
    private String parentCode;
    private String name;
    private Integer orderNum;
    public OrgTree(){}
    public OrgTree(String code, String parentCode, String name, Integer orderNum){
        this.code = code;
        this.parentCode = parentCode;
        this.name = name;
        this.orderNum = orderNum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getNodeId() {
        return this.code;
    }

    @Override
    public String getNodeName() {
        return this.name;
    }

    @Override
    public String getNodeParentId() {
        return this.parentCode;
    }

    @Override
    public Integer getOrderNum() {
        return this.orderNum;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

}
