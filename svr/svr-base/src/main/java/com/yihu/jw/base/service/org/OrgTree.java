package com.yihu.jw.base.service.org;

import com.yihu.jw.base.service.org.tree.TreeNode;
import com.yihu.jw.entity.IntegerIdentityEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "org_tree")
public class OrgTree extends IntegerIdentityEntity implements TreeNode {
    private String code;
    private String parentCode;
    private String name;
    //当前树节点所在层级，例如省在第一级,用0表示
    private Integer level;

    public OrgTree(){}
    public OrgTree(String code, String parentCode, String name){
        this.code = code;
        this.parentCode = parentCode;
        this.name = name;
    }

    @Override
    public String extractNodeId() {
        return this.code;
    }

    @Override
    public String extractNodeName() {
        return this.name;
    }

    @Override
    public String extractNodeParentId() {
        return this.parentCode;
    }

    @Override
    public Integer extractOrderNum() {
        return null;
    }

    @Column(name = "code", nullable = false)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "parent_code", nullable = false)
    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "name", nullable = false)
    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
