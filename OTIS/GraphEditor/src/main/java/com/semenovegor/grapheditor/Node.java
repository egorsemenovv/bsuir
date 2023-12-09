package com.semenovegor.grapheditor;

import java.io.Serial;
import java.io.Serializable;

enum NodeType{
    BASIC_NODE("Basic node");
    final String nodeType;
    NodeType(String nodeType){
        this.nodeType = nodeType;
    }
}

public class Node implements Serializable {
    protected String label;
    @Serial
    private static final long serialVersionUID = -7357466511459361679L;

    private final NodeType nodeType;

    private double x;
    private double y;

    public Node(String label){
        nodeType = NodeType.BASIC_NODE;
        this.label=label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public NodeType getNodeType() {
        return nodeType;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return "Node{" +
                "label='" + label + '\'' +
                ", nodeType=" + nodeType +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
