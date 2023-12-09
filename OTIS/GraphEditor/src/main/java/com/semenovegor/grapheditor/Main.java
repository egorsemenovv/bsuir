package com.semenovegor.grapheditor;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.*;

public class Main extends Application {

    Graph graph = new Graph("test");

    private final Map<String, Circle> nodesMap = new HashMap<>();
    private final Map<String, Text> nodesLabelMap = new HashMap<>();

    private final Map <Path, Text> edgeTextMap = new HashMap<>();

    private final Map<Path, List<Circle>> edgeNodesMap = new HashMap<>();


    @Override
    public void start(Stage stage) {
        Pane rootPane = new Pane();
        Pane nodesRoot = new Pane();
        Pane edgesRoot = new Pane();
        VBox graphVBox = new VBox();
        VBox fileVBox = new VBox();
        VBox pathVBox = new VBox();
        HBox hBox = new HBox();
        Scene scene = new Scene(rootPane,800, 400);

        Button addNodeButton = new Button("Add node");
        addNodeButton.autosize();
        Button deleteNodeButton = new Button("Delete node");
        deleteNodeButton.autosize();
        Button addEdgeButton = new Button("Add Edge");
        Button addOrientedEdgeButton = new Button("Add oriented edge");
        Button removeEdgeButton = new Button("Remove Edge");
        Button showGraphInfoButton = new Button("Show information about graph");
        Button showEulerCycleButton = new Button("Show euler cycle");
        Button makeGraphFullButton = new Button("Make graph full");
        Button showShortestPathBetweenTwoNodes = new Button("Show shortest path");
        Button showAllPathsBetweenTwoNodes = new Button("Show all paths");
        Button saveGraphButton = new Button("Save graph");
        Button loadGraphButton = new Button("Load graph");

        addNodeButton.setStyle("-fx-background-color: #f0f0f0;");
        deleteNodeButton.setStyle("-fx-background-color: #f0f0f0;");
        addEdgeButton.setStyle("-fx-background-color: #f0f0f0;");
        addOrientedEdgeButton.setStyle("-fx-background-color: #f0f0f0;");
        removeEdgeButton.setStyle("-fx-background-color: #f0f0f0;");
        showGraphInfoButton.setStyle("-fx-background-color: #f0f0f0;");
        showEulerCycleButton.setStyle("-fx-background-color: #f0f0f0;");
        makeGraphFullButton.setStyle("-fx-background-color: #f0f0f0;");
        showShortestPathBetweenTwoNodes.setStyle("-fx-background-color: #f0f0f0;");
        showAllPathsBetweenTwoNodes.setStyle("-fx-background-color: #f0f0f0;");
        saveGraphButton.setStyle("-fx-background-color: #f0f0f0;");
        loadGraphButton.setStyle("-fx-background-color: #f0f0f0;");

        addNodeButton.setOnMousePressed(mouseEvent -> addNodeButton.setStyle("-fx-background-color: #ccc;"));
        addNodeButton.setOnMouseReleased(mouseEvent -> addNodeButton.setStyle("-fx-background-color: #f0f0f0;"));

        deleteNodeButton.setOnMousePressed(mouseEvent -> deleteNodeButton.setStyle("-fx-background-color: #ccc;"));
        deleteNodeButton.setOnMouseReleased(mouseEvent -> deleteNodeButton.setStyle("-fx-background-color: #f0f0f0;"));

        addEdgeButton.setOnMousePressed(mouseEvent -> addEdgeButton.setStyle("-fx-background-color: #ccc;"));
        addEdgeButton.setOnMouseReleased(mouseEvent -> addEdgeButton.setStyle("-fx-background-color: #f0f0f0;"));

        addOrientedEdgeButton.setOnMousePressed(mouseEvent -> addOrientedEdgeButton.setStyle("-fx-background-color: #ccc;"));
        addOrientedEdgeButton.setOnMouseReleased(mouseEvent -> addOrientedEdgeButton.setStyle("-fx-background-color: #f0f0f0;"));

        removeEdgeButton.setOnMousePressed(mouseEvent -> removeEdgeButton.setStyle("-fx-background-color: #ccc;"));
        removeEdgeButton.setOnMouseReleased(mouseEvent -> removeEdgeButton.setStyle("-fx-background-color: #f0f0f0;"));

        showGraphInfoButton.setOnMousePressed(mouseEvent -> showGraphInfoButton.setStyle("-fx-background-color: #ccc;"));
        showGraphInfoButton.setOnMouseReleased(mouseEvent -> showGraphInfoButton.setStyle("-fx-background-color: #f0f0f0;"));

        showEulerCycleButton.setOnMousePressed(mouseEvent -> showEulerCycleButton.setStyle("-fx-background-color: #ccc;"));
        showEulerCycleButton.setOnMouseReleased(mouseEvent -> showEulerCycleButton.setStyle("-fx-background-color: #f0f0f0;"));

        makeGraphFullButton.setOnMousePressed(mouseEvent -> makeGraphFullButton.setStyle("-fx-background-color: #ccc;"));
        makeGraphFullButton.setOnMouseReleased(mouseEvent -> makeGraphFullButton.setStyle("-fx-background-color: #f0f0f0;"));

        showShortestPathBetweenTwoNodes.setOnMousePressed(mouseEvent -> showShortestPathBetweenTwoNodes.setStyle("-fx-background-color: #ccc;"));
        showShortestPathBetweenTwoNodes.setOnMouseReleased(mouseEvent -> showShortestPathBetweenTwoNodes.setStyle("-fx-background-color: #f0f0f0;"));

        showAllPathsBetweenTwoNodes.setOnMousePressed(mouseEvent -> showAllPathsBetweenTwoNodes.setStyle("-fx-background-color: #ccc;"));
        showAllPathsBetweenTwoNodes.setOnMouseReleased(mouseEvent -> showAllPathsBetweenTwoNodes.setStyle("-fx-background-color: #f0f0f0;"));

        saveGraphButton.setOnMousePressed(mouseEvent -> saveGraphButton.setStyle("-fx-background-color: #ccc;"));
        saveGraphButton.setOnMouseReleased(mouseEvent -> saveGraphButton.setStyle("-fx-background-color: #f0f0f0;"));

        loadGraphButton.setOnMousePressed(mouseEvent -> loadGraphButton.setStyle("-fx-background-color: #ccc;"));
        loadGraphButton.setOnMouseReleased(mouseEvent -> loadGraphButton.setStyle("-fx-background-color: #f0f0f0;"));

        graphVBox.getChildren().addAll(addNodeButton, deleteNodeButton, addEdgeButton, addOrientedEdgeButton, removeEdgeButton, showGraphInfoButton, makeGraphFullButton);
        pathVBox.getChildren().addAll(showEulerCycleButton, showShortestPathBetweenTwoNodes, showAllPathsBetweenTwoNodes);
        fileVBox.getChildren().addAll(saveGraphButton, loadGraphButton);
        TitledPane graphTitledPane = new TitledPane("Graph", graphVBox);
        TitledPane pathTitledPane = new TitledPane("Path", pathVBox);
        TitledPane fileTitledPane = new TitledPane("File", fileVBox);
        graphTitledPane.setStyle("-fx-background-color: #f0f0f0;");
        graphTitledPane.setExpanded(false);
        pathTitledPane.setStyle("-fx-background-color: #f0f0f0;");
        pathTitledPane.setExpanded(false);
        fileTitledPane.setStyle("-fx-background-color: #f0f0f0;");
        fileTitledPane.setExpanded(false);
        hBox.getChildren().addAll(graphTitledPane, pathTitledPane, fileTitledPane);
        rootPane.getChildren().addAll(edgesRoot, nodesRoot, hBox);
        stage.setScene(scene);
        stage.show();

        addNodeButton.setOnMouseClicked(mouseEvent -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Enter Label");
            dialog.setHeaderText(null);
            dialog.setContentText("Enter label for the node:");

            dialog.showAndWait().ifPresent(label -> {
                Circle circle = new Circle(8, Color.GRAY);
                circle.setFill(Color.WHITE);
                circle.setStroke(Color.BLACK);
                Text labelTxt = new Text(label);
                circle.setCenterX(400);
                circle.setCenterY(200);
                labelTxt.setX(circle.getCenterX() - 4);
                labelTxt.setY(circle.getCenterY() - 15);
                nodesRoot.getChildren().addAll(circle, labelTxt);

                nodesMap.put(label, circle);
                nodesLabelMap.put(label, labelTxt);
                Node node = new Node(label);
                graph.addNode(new Node(label));

                circle.setOnMouseDragged(event -> {
                    circle.setCenterX(event.getX());
                    circle.setCenterY(event.getY());
                    Node eventNode = graph.getNodeByLabel(label);
                    eventNode.setX(event.getX());
                    eventNode.setY(event.getY());
                    labelTxt.setX(circle.getCenterX() - 4);
                    labelTxt.setY(circle.getCenterY() - 15);
                    updatePathForAll();
                });
            });
        });

        deleteNodeButton.setOnMouseClicked(mouseEvent -> {
            TextInputDialog delDialog = new TextInputDialog();
            delDialog.setTitle("Delete Label");
            delDialog.setHeaderText(null);
            delDialog.setContentText("Enter label of the node to delete:");

            Optional<String> result = delDialog.showAndWait();
            result.ifPresent(delLabel -> {
                Circle nodeToRemove = nodesMap.get(delLabel);
                Text textToRemove = nodesLabelMap.get(delLabel);

                if (nodeToRemove != null && textToRemove != null) {
                    nodesRoot.getChildren().removeAll(nodeToRemove, textToRemove);
                    nodesMap.remove(delLabel);
                    nodesLabelMap.remove(delLabel);
                    graph.removeNode(delLabel);
                    List<Path> pathToRemove = new ArrayList<>();
                    for(int i = 0; i < edgeNodesMap.size(); i++){
                        Set<Path> paths = edgeNodesMap.keySet();
                        for (Path path : paths) {
                            if (edgeNodesMap.get(path).get(0).equals(nodeToRemove) || edgeNodesMap.get(path).get(1).equals(nodeToRemove)){
                                pathToRemove.add(path);
                            }
                        }
                    }
                    for (Path path: pathToRemove) {
                        edgesRoot.getChildren().remove(path);
                        nodesRoot.getChildren().remove(edgeTextMap.get(path));
                        edgeNodesMap.remove(path);
                        edgeTextMap.remove(path);
                    }
                }
            });
        });

        addEdgeButton.setOnMouseClicked(mouseEvent -> {
            if (nodesMap.size() >= 2) {
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Add Edge");
                dialog.setHeaderText(null);
                dialog.setContentText("Enter labels of two nodes to connect and edge label:");

                dialog.showAndWait().ifPresent(labels -> {
                    String[] controlLabels = labels.split(" ");
                    if (controlLabels.length == 3) {
                        Circle node1 = nodesMap.get(controlLabels[0]);
                        Circle node2 = nodesMap.get(controlLabels[1]);
                        Text controlText = new Text(controlLabels[2]);
                        controlText.setFill(Color.GREEN);
                        controlText.setFont(Font.font(12));

                        if (node1 != null && node2 != null) {
                            controlText.setX((node1.getCenterX() + node2.getCenterX()) / 2);
                            controlText.setY((node1.getCenterY() + node2.getCenterY()) / 2);
                            Path edge = new Path();
                            edge.getElements().add(new MoveTo(node1.getCenterX(), node1.getCenterY()));
                            edge.getElements().add(new QuadCurveTo(controlText.getX(), controlText.getY(),
                                    node2.getCenterX(), node2.getCenterY()));
                            controlText.setOnMouseDragged(event -> {
                                controlText.setX(event.getX());
                                controlText.setY(event.getY());
                                updatePath(edge, controlText, node1, node2);
                            });
                            edgeTextMap.put(edge, controlText);
                            edgeNodesMap.put(edge, List.of(node1, node2));
                            Node nodeA = graph.getNodeByLabel(controlLabels[0]);
                            Node nodeB = graph.getNodeByLabel(controlLabels[1]);
                            graph.addEdge(new Edge(nodeA, nodeB, controlLabels[2], EdgeType.BASIC_EDGE));
                            edgesRoot.getChildren().add(edge);
                            nodesRoot.getChildren().add(controlText);
                        }
                    }
                });
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("At least two nodes are required to create an edge.");
                alert.showAndWait();
            }
        });

        addOrientedEdgeButton.setOnMouseClicked(mouseEvent -> {
            if (nodesMap.size() >= 2) {
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Add Oriented Edge");
                dialog.setHeaderText(null);
                dialog.setContentText("Enter labels of two nodes to connect and edge label:");

                dialog.showAndWait().ifPresent(labels -> {
                    String[] controlLabels = labels.split(" ");
                    if (controlLabels.length == 3) {
                        Circle node1 = nodesMap.get(controlLabels[0]);
                        Circle node2 = nodesMap.get(controlLabels[1]);
                        Text controlText = new Text(controlLabels[2]);
                        controlText.setFill(Color.GREEN);
                        controlText.setFont(Font.font(12));

                        if (node1 != null && node2 != null) {
                            controlText.setX((node1.getCenterX() + node2.getCenterX()) / 2);
                            controlText.setY((node1.getCenterY() + node2.getCenterY()) / 2);
                            Path edge = new Arrow(node1.getCenterX(), node1.getCenterY(), node2.getCenterX(), node2.getCenterY());
                            edgeTextMap.put(edge, controlText);
                            edgeNodesMap.put(edge, List.of(node1, node2));
                            Node nodeA = graph.getNodeByLabel(controlLabels[0]);
                            Node nodeB = graph.getNodeByLabel(controlLabels[1]);
                            graph.addEdge(new Edge(nodeA, nodeB, controlLabels[2], EdgeType.SPECIAL_EDGE));
                            edgesRoot.getChildren().add(edge);
                            nodesRoot.getChildren().add(controlText);
                        }
                    }
                });
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("At least two nodes are required to create an edge.");
                alert.showAndWait();
            }
        });

        removeEdgeButton.setOnMouseClicked(mouseEvent -> {
            TextInputDialog delDialog = new TextInputDialog();
            delDialog.setTitle("Delete Label");
            delDialog.setHeaderText(null);
            delDialog.setContentText("Enter label of the edge to delete:");

            Optional<String> result = delDialog.showAndWait();
            result.ifPresent(delLabel -> {
                Set<Path> paths = edgeTextMap.keySet();
                Path pathToRemove = null;
                for (Path path: paths) {
                    if(edgeTextMap.get(path).getText().equals(delLabel)){
                        pathToRemove = path;
                    }
                }

                if (pathToRemove!=null) {
                    edgesRoot.getChildren().remove(pathToRemove);
                    nodesRoot.getChildren().remove(edgeTextMap.get(pathToRemove));
                    graph.removeEdge(delLabel);
                    edgeNodesMap.remove(pathToRemove);
                    edgeTextMap.remove(pathToRemove);
                }
            });
        });
        showGraphInfoButton.setOnMouseClicked(mouseEvent -> {
            Alert showDialog = new Alert(Alert.AlertType.INFORMATION);
            showDialog.setTitle("Information about graph");
            showDialog.setHeaderText(null);
            showDialog.setContentText("\t"+graph.getIncidenceMatrix()+"\n"+graph.checkIfGraphIsFull());
            showDialog.showAndWait();
        });

        showEulerCycleButton.setOnMouseClicked(mouseEvent -> {
            Alert showDialog = new Alert(Alert.AlertType.INFORMATION);
            showDialog.setTitle("Eulerian path");
            showDialog.setHeaderText(null);
            showDialog.setContentText(graph.findEulerCycle());
            showDialog.showAndWait();
        });

        makeGraphFullButton.setOnMouseClicked(mouseEvent -> {
            List<Edge> edgesToAdd = graph.makeGraphFull();
            if(edgesToAdd == null){
                return;
            }
            for (Edge edge : edgesToAdd) {
                Circle node1 = nodesMap.get(edge.nodeA.label);
                Circle node2 = nodesMap.get(edge.nodeB.label);
                Text controlText = new Text(edge.label);
                controlText.setFill(Color.GREEN);
                controlText.setFont(Font.font(12));

                if (node1 != null && node2 != null) {
                    controlText.setX((node1.getCenterX() + node2.getCenterX()) / 2);
                    controlText.setY((node1.getCenterY() + node2.getCenterY()) / 2);
                    Path path = new Path();
                    path.getElements().add(new MoveTo(node1.getCenterX(), node1.getCenterY()));
                    path.getElements().add(new QuadCurveTo(controlText.getX(), controlText.getY(),
                            node2.getCenterX(), node2.getCenterY()));
                    controlText.setOnMouseDragged(event -> {
                        controlText.setX(event.getX());
                        controlText.setY(event.getY());
                        updatePath(path, controlText, node1, node2);
                    });
                    edgeTextMap.put(path, controlText);
                    edgeNodesMap.put(path, List.of(node1, node2));
                    edgesRoot.getChildren().add(path);
                    nodesRoot.getChildren().add(controlText);
                }
            }
        });

        showShortestPathBetweenTwoNodes.setOnMouseClicked(mouseEvent -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Shortest path");
            dialog.setHeaderText(null);
            dialog.setContentText("Enter label of the two nodes:");
            dialog.showAndWait().ifPresent(labels -> {
                String[] controlLabels = labels.split(" ");
                if (controlLabels.length == 2) {
                    Alert showDialog = new Alert(Alert.AlertType.INFORMATION);
                    showDialog.setTitle("Shortest path");
                    showDialog.setHeaderText(null);
                    String temp = graph.getShortestPathBetweenTwoNodes(controlLabels[0], controlLabels[1]);
                    temp = temp.replace("->", "");
                    temp = temp.replace(" ", "");
                    showDialog.setContentText(graph.getShortestPathBetweenTwoNodes(controlLabels[0], controlLabels[1]) + " " + (temp.length()-1));
                    showDialog.showAndWait();
                }
            });
        });

        showAllPathsBetweenTwoNodes.setOnMouseClicked(mouseEvent -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("All paths");
            dialog.setHeaderText(null);
            dialog.setContentText("Enter label of the two nodes:");
            dialog.showAndWait().ifPresent(labels -> {
                String[] controlLabels = labels.split(" ");
                if (controlLabels.length == 2) {
                    Alert showDialog = new Alert(Alert.AlertType.INFORMATION);
                    showDialog.setTitle("All paths");
                    showDialog.setHeaderText(null);
                    showDialog.setContentText(graph.getAllPathsBetweenTwoNodes(controlLabels[0], controlLabels[1]));
                    showDialog.showAndWait();
                }
            });
        });

        saveGraphButton.setOnMouseClicked(mouseEvent -> {
            TextInputDialog dialog = new TextInputDialog();
            String fileName;
            dialog.setTitle("Save graph");
            dialog.setHeaderText(null);
            dialog.setContentText("Enter name of file:");
            dialog.showAndWait().ifPresent(name -> {
                try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("src\\main\\resources\\"+name+".ser"))) {
                    outputStream.writeObject(graph);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information");
                    alert.setHeaderText(null);
                    alert.setContentText("Graph has been saved to file.");
                    alert.showAndWait();
                } catch (IOException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("All paths");
                    alert.setHeaderText(null);
                    alert.setContentText("Some problems... Try again.");
                    alert.showAndWait();
                }
            });

        });

        loadGraphButton.setOnMouseClicked(mouseEvent -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Load Graph");
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Serialized files (*.ser)", "*.ser");
            fileChooser.getExtensionFilters().add(extFilter);
            File file = fileChooser.showOpenDialog(null);

            if (file != null) {
                try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
                    Graph loadedGraph = (Graph) inputStream.readObject();

                    nodesRoot.getChildren().clear();
                    edgesRoot.getChildren().clear();

                    nodesMap.clear();
                    nodesLabelMap.clear();
                    edgeNodesMap.clear();
                    edgeTextMap.clear();
                    graph = loadedGraph;

                    for (Node node : graph.getNodes()) {
                        Circle circle = new Circle(8, Color.GRAY);
                        circle.setFill(Color.WHITE);
                        circle.setStroke(Color.BLACK);
                        Text labelTxt = new Text(node.getLabel());
                        circle.setCenterX(node.getX());
                        circle.setCenterY(node.getY());
                        labelTxt.setX(circle.getCenterX() - 4);
                        labelTxt.setY(circle.getCenterY() - 15);
                        nodesRoot.getChildren().addAll(circle, labelTxt);

                        nodesMap.put(node.getLabel(), circle);
                        nodesLabelMap.put(node.getLabel(), labelTxt);

                        circle.setOnMouseDragged(event -> {
                            circle.setCenterX(event.getX());
                            circle.setCenterY(event.getY());
                            node.setX(circle.getCenterX());
                            node.setY(circle.getCenterY());
                            labelTxt.setX(circle.getCenterX() - 4);
                            labelTxt.setY(circle.getCenterY() - 15);
                            updatePathForAll();
                        });
                    }

                    for (Edge edge : graph.getEdges()) {
                        Circle node1 = nodesMap.get(edge.nodeA.getLabel());
                        Circle node2 = nodesMap.get(edge.nodeB.getLabel());
                        Text controlText = new Text(edge.getLabel());
                        controlText.setFill(Color.GREEN);
                        controlText.setFont(Font.font(12));

                        if (node1 != null && node2 != null) {
                            controlText.setX((node1.getCenterX() + node2.getCenterX()) / 2);
                            controlText.setY((node1.getCenterY() + node2.getCenterY()) / 2);
                            Path edge1 = new Path();
                            edge1.getElements().add(new MoveTo(node1.getCenterX(), node1.getCenterY()));
                            edge1.getElements().add(new QuadCurveTo(controlText.getX(), controlText.getY(),
                                    node2.getCenterX(), node2.getCenterY()));
                            controlText.setOnMouseDragged(event -> {
                                controlText.setX(event.getX());
                                controlText.setY(event.getY());
                                updatePath(edge1, controlText, node1, node2);
                            });
                            edgeTextMap.put(edge1, controlText);
                            edgeNodesMap.put(edge1, List.of(node1, node2));
                            edgesRoot.getChildren().add(edge1);
                            nodesRoot.getChildren().add(controlText);
                        }
                    }
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information");
                    alert.setHeaderText(null);
                    alert.setContentText("Graph has been loaded from file.");
                    alert.showAndWait();
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void updatePath(Path edge, Text controlText, Circle node1, Circle node2) {
        edge.getElements().clear();
        edge.getElements().add(new MoveTo(node1.getCenterX(), node1.getCenterY()));
        edge.getElements().add(new QuadCurveTo(controlText.getX(), controlText.getY(),
                node2.getCenterX(), node2.getCenterY()));
    }

    private void updatePathForAll(){
        for(int i = 0; i< edgeNodesMap.size(); i++){
            Set<Path> paths = edgeNodesMap.keySet();
            for (Path path : paths) {
                updatePath(path, edgeTextMap.get(path), edgeNodesMap.get(path).get(0), edgeNodesMap.get(path).get(1));
            }
        }
    }

    // Function to create a custom arrow shape
    private Path createArrow() {
        Path arrow = new Path();

        // Define the arrow shape using Path elements
        arrow.getElements().addAll(
                new javafx.scene.shape.MoveTo(0, 0),
                new javafx.scene.shape.LineTo(20, 0),
                new javafx.scene.shape.LineTo(20, -5),
                new javafx.scene.shape.LineTo(40, 5),
                new javafx.scene.shape.LineTo(20, 15),
                new javafx.scene.shape.LineTo(20, 10),
                new javafx.scene.shape.LineTo(0, 10),
                new javafx.scene.shape.ClosePath()
        );
        return arrow;
    }

    public static void main(String[] args) {
        launch();
    }
}