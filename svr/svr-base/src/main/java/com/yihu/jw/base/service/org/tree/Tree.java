package com.yihu.jw.base.service.org.tree;

import java.util.List;

public interface Tree {
    public List<SimpleTreeNode> getTree();
    public List<SimpleTreeNode> getRoot();
    public SimpleTreeNode getTreeNode(String nodeId);
}


