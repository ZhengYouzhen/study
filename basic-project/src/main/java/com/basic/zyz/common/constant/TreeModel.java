package com.basic.zyz.common.constant;

import java.io.Serializable;
import java.util.List;

/**
 * 树形结构模型
 *
 * @author zyz
 */
public class TreeModel<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private T node; // 树节点
    private List<TreeModel<T>> children; // 子节点集合

    public T getNode() {
        return node;
    }

    public void setNode(T node) {
        this.node = node;
    }

    public List<TreeModel<T>> getChildren() {
        return children;
    }

    public void setChildren(List<TreeModel<T>> children) {
        this.children = children;
    }

}
