package com.semenovegor.grapheditor;

import java.io.Serial;
import java.io.Serializable;

enum EdgeType {
    BASIC_EDGE("Undirected edge"),
    SPECIAL_EDGE("Directed edge");

    String edgeType;

    EdgeType(String edgeType){
        this.edgeType = edgeType;
    }

    @Override
    public String toString() {
        return edgeType;
    }
}

public class Edge implements Serializable {
    @Serial
    private static final long serialVersionUID = -6972652167790425200L;

    protected Node nodeA;

    protected Node nodeB;

    protected String label;

    protected EdgeType edgeType;

    public Edge(Node nodeA, Node nodeB, String label, EdgeType edgeType) {
        this.nodeA = nodeA;
        this.nodeB = nodeB;
        this.label = label;
        this.edgeType = edgeType;
    }

    public Node getNodeA(){
        return nodeA;
    }

    public void setNodeA(Node nodeA){
        this.nodeA = nodeA;
    }

    public Node getNodeB(){
        return nodeB;
    }

    public void setNodeB(Node nodeB){
        this.nodeB=nodeB;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
