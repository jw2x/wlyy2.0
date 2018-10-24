package com.yihu.jw.base.service.org;

import com.yihu.jw.base.service.org.tree.TreeNode;
import com.yihu.jw.entity.IntegerIdentityEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "org_tree")
public class OrgTree extends IntegerIdentityEntity implements TreeNode {
    private String code;
    private String parentCode;
    private String name;
    //当前树节点所在层级，例如省在第一级,用0表示
    private Integer level;

    private boolean checked;

    public enum Level{
        province(0),
        city(1),
        town(2),
        org(3),
        dept(4),
        role(4);

        private int levelValue;

         Level(int levelValue){
            this.levelValue = levelValue;
        }

        public int getLevelValue() {
            return levelValue;
        }
    }
    public OrgTree(){}
    public OrgTree(String code, String parentCode, String name,Integer level){
        this.code = code;
        this.parentCode = parentCode;
        this.name = name;
        this.level = level;
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

    @Override
    public boolean extractChecked() {
        return this.checked;
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

    @Column(name = "level",nullable = false)
    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Transient
    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
