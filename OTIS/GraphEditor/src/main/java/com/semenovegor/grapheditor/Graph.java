package com.semenovegor.grapheditor;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;

public class Graph implements Serializable {

    @Serial
    private static final long serialVersionUID = 5673009196816218789L;

    private String graphTitle;
    private List<Node> nodes;
    private List<Edge> edges;


    public Graph(String title) {
        setGraphTitle(title);
        setNodes(new ArrayList<Node>());
        setEdges(new ArrayList<Edge>());

    }

    public String getGraphTitle() {
        return graphTitle;
    }

    public void setGraphTitle(String graphTitle) {
        if (graphTitle == null)
            graphTitle = "";
        else
            this.graphTitle = graphTitle;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    public void addNode(Node n) {
        nodes.add(n);
    }

    public void addEdge(Edge e) {
        for (Edge edge : edges) {
            if (e.equals(edge))
                return;
        }
        edges.add(e);
    }

    public void removeNode(String nodeLabel) {
        for (Node node : nodes) {
            if (node.getLabel().equals(nodeLabel)) {
                nodes.remove(node);
                edges.removeIf(edge -> (edge.nodeA == node || edge.nodeB == node));
                break;
            }
        }
    }

    public void removeEdge(String edgeLabel) {
        for (Edge edge : edges) {
            if (edge.getLabel().equals(edgeLabel)) {
                edges.remove(edge);
                break;
            }
        }
    }

    public Node getNodeByLabel(String label) {
        for (Node node : nodes) {
            if (node.getLabel().equals(label)) {
                return node;
            }
        }
        return null;
    }

    public String getIncidenceMatrix() {
        StringBuilder matrixString = new StringBuilder();

        matrixString.append("\t");

        for (Edge edge : edges) {
            matrixString.append(edge.getLabel()).append("\t");
        }
        matrixString.append("\n");

        int[][] incidenceMatrix = new int[nodes.size()][edges.size()];

        for (int i = 0; i < nodes.size(); i++) {
            matrixString.append(nodes.get(i).getLabel()).append(":\t");

            for (int j = 0; j < edges.size(); j++) {
                Node node = nodes.get(i);
                Edge edge = edges.get(j);

                if (edge.getNodeA().equals(node) || edge.getNodeB().equals(node)) {
                    incidenceMatrix[i][j] = 1;
                }

                matrixString.append(incidenceMatrix[i][j]).append("\t");
            }
            matrixString.append("\n");
        }

        return matrixString.toString().trim();
    }

    public String checkIfGraphIsFull() {
        int numOfNodes = nodes.size();
        int maxPossibleEdges = numOfNodes * (numOfNodes - 1) / 2; // Formula for complete graph edges

        if (edges.size() == maxPossibleEdges) {
            return "Graph is full";
        } else {
            return "Graph is not full";
        }
    }

    public String findEulerCycle() {
        // Check if the graph has an Euler path or circuit
        if (!checkIfGraphHasEulerPath()) {
            return "No Euler path exists";
        }

        // Clone the list of edges to avoid modification issues
        List<Edge> remainingEdges = new ArrayList<>(edges);

        // Find a starting node for the Euler path
        Node startNode = findEulerStartNode();

        // If startNode is null, return an error message
        if (startNode == null) {
            return "No valid start node found";
        }

        StringBuilder eulerPath = new StringBuilder();
        eulerPath.append(startNode.getLabel());

        Node currentNode = startNode;

        // Iterate until there are no remaining edges
        while (!remainingEdges.isEmpty()) {
            Edge nextEdge = getNextEdge(currentNode, remainingEdges);

            if (nextEdge == null) {
                break; // No more edges to traverse
            }

            remainingEdges.remove(nextEdge); // Remove the edge from remaining edges
            Node nextNode;

            // Determine the next node based on the current node and edge
            if (nextEdge.getNodeA().equals(currentNode)) {
                nextNode = nextEdge.getNodeB();
            } else {
                nextNode = nextEdge.getNodeA();
            }

            eulerPath.append(" -> ").append(nextNode.getLabel());
            currentNode = nextNode;
        }

        return eulerPath.toString();
    }

    // Method to check if the graph has an Euler path
    private boolean checkIfGraphHasEulerPath() {
        int oddDegreeNodes = 0;
        for (int i = 0; i < nodes.size(); i++) {
            int degree = findNodeDegree(i);
            if (degree % 2 != 0) {
                oddDegreeNodes++;
            }
        }
        // For an Euler path, there should be either 0 or 2 nodes with odd degree
        return oddDegreeNodes == 0 || oddDegreeNodes == 2;
    }

    // Method to find a suitable starting node for the Euler path
    private Node findEulerStartNode() {
        for (Node node : nodes) {
            if (findNodeDegree(nodes.indexOf(node)) % 2 != 0) {
                return node;
            }
        }
        return nodes.get(0); // Return any node if no odd degree nodes are found
    }

    // Method to find the next edge to traverse from the current node
    private Edge getNextEdge(Node currentNode, List<Edge> remainingEdges) {
        for (Edge edge : remainingEdges) {
            if (edge.getNodeA().equals(currentNode) || edge.getNodeB().equals(currentNode)) {
                return edge;
            }
        }
        return null; // No valid edge found for the current node
    }

    private int findNodeDegree(int nodeIndex){
        Node node = nodes.get(nodeIndex);
        int degree = 0;
        for (Edge edge: edges) {
            if(edge.nodeA.equals(node) || edge.nodeB.equals(node)){
                degree++;
            }
        }
        return degree;
    }

    public List<Edge> makeGraphFull() {
        List<Edge> edgesToAdd = new ArrayList<>();
        int numOfNodes = nodes.size();
        int maxPossibleEdges = numOfNodes * (numOfNodes - 1) / 2;

        // If the graph is already full, no need to do anything
        if (edges.size() == maxPossibleEdges) {
            return null;
        }

        // Create a set to keep track of existing edges
        Set<String> existingEdges = new HashSet<>();
        for (Edge edge : edges) {
            existingEdges.add(edge.getLabel()); // Assuming edges have unique labels
        }

        // Add missing edges to make the graph full
        for (int i = 0; i < numOfNodes; i++) {
            for (int j = i + 1; j < numOfNodes; j++) {
                Node nodeA = nodes.get(i);
                Node nodeB = nodes.get(j);
                String edgeLabel = nodeA.getLabel() + "-" + nodeB.getLabel(); // Creating a unique label for the edge

                // Check if the edge doesn't exist, then add it
                if (!existingEdges.contains(edgeLabel)) {
                    Edge newEdge = new Edge(nodeA, nodeB, edgeLabel, EdgeType.BASIC_EDGE);
                    edges.add(newEdge);
                    edgesToAdd.add(newEdge);
                    existingEdges.add(edgeLabel);
                }
            }
        }
        return edgesToAdd;
    }

    public String getShortestPathBetweenTwoNodes(String nodeLabel1, String nodeLabel2) {
        // Find nodes with given labels
        Node startNode = getNodeByLabel(nodeLabel1);
        Node endNode = getNodeByLabel(nodeLabel2);

        // If start or end node doesn't exist, return null or handle it accordingly
        if (startNode == null || endNode == null) {
            return "Nodes not found";
        }

        // Queue for BFS
        Queue<Node> queue = new LinkedList<>();
        queue.add(startNode);

        // Map to store parent of each node for reconstructing the path
        Map<Node, Node> parentMap = new HashMap<>();
        parentMap.put(startNode, null);

        // Perform BFS
        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();

            // Check if we reached the end node
            if (currentNode.equals(endNode)) {
                // Reconstruct the path from endNode to startNode using the parent map
                List<Node> path = new ArrayList<>();
                Node node = endNode;
                while (node != null) {
                    path.add(node);
                    node = parentMap.get(node);
                }

                // Reverse the path as it was constructed from end to start
                Collections.reverse(path);

                // Construct the string representation of the path
                StringBuilder pathString = new StringBuilder();
                for (int i = 0; i < path.size(); i++) {
                    pathString.append(path.get(i).getLabel());
                    if (i < path.size() - 1) {
                        pathString.append(" -> ");
                    }
                }
                return pathString.toString();
            }

            // Iterate through neighbors of currentNode
            for (Edge edge : edges) {
                Node neighbor = null;
                if (edge.getNodeA().equals(currentNode)) {
                    neighbor = edge.getNodeB();
                } else if (edge.getNodeB().equals(currentNode)) {
                    neighbor = edge.getNodeA();
                }

                if (neighbor != null && !parentMap.containsKey(neighbor)) {
                    parentMap.put(neighbor, currentNode);
                    queue.add(neighbor);
                }
            }
        }
        return "No path found"; // If no path exists between the nodes
    }

    public String getAllPathsBetweenTwoNodes(String nodeLabel1, String nodeLabel2) {
        // Find nodes with given labels
        Node startNode = getNodeByLabel(nodeLabel1);
        Node endNode = getNodeByLabel(nodeLabel2);

        // If start or end node doesn't exist, return an empty list or handle it accordingly
        if (startNode == null || endNode == null) {
            return null;
        }

        List<String> allPaths = new ArrayList<>();
        List<Node> currentPath = new ArrayList<>();
        currentPath.add(startNode);

        // Start the recursive DFS to find all paths
        findAllPathsDFS(startNode, endNode, currentPath, allPaths);

        StringBuilder answer = new StringBuilder();
        for (String path : allPaths) {
            answer.append(path).append("\n");
        }
        return answer.toString();
    }

    private void findAllPathsDFS(Node currentNode, Node endNode, List<Node> currentPath, List<String> allPaths) {
        if (currentNode.equals(endNode)) {
            StringBuilder pathString = new StringBuilder();
            for (int i = 0; i < currentPath.size(); i++) {
                pathString.append(currentPath.get(i).getLabel());
                if (i < currentPath.size() - 1) {
                    pathString.append(" -> ");
                }
            }
            allPaths.add(pathString.toString());
            return;
        }

        for (Edge edge : edges) {
            Node neighbor = null;
            if (edge.getNodeA().equals(currentNode)) {
                neighbor = edge.getNodeB();
            } else if (edge.getNodeB().equals(currentNode)) {
                neighbor = edge.getNodeA();
            }

            if (neighbor != null && !currentPath.contains(neighbor)) {
                currentPath.add(neighbor);
                findAllPathsDFS(neighbor, endNode, currentPath, allPaths);
                currentPath.remove(currentPath.size() - 1); // Backtrack
            }
        }
    }

    public int[][] getMatrix(){
        int[][] incidenceMatrix = new int[nodes.size()][edges.size()];
        for (int i = 0; i < nodes.size(); i++) {
            for (int j = 0; j < edges.size(); j++) {
                Node node = nodes.get(i);
                Edge edge = edges.get(j);
                if (edge.getNodeA().equals(node) || edge.getNodeB().equals(node)) {
                    incidenceMatrix[i][j] = 1;
                }
            }
        }
        return incidenceMatrix;
    }
}


