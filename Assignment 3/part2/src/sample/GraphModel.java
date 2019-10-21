package sample;

import java.util.*;

import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;

class GraphModel {

    private SimpleListProperty<Vertex> vertexListProperty;
    private HashMap<String, Edge> edges;
    private ArrayList<ArrayList<Integer>> adjacencyMatrix;
    private int vertexRadius;
    private int numVertices;

    GraphModel(int vertexRadius) {
        ArrayList<Vertex> list = new ArrayList<>();
        ObservableList<Vertex> observableList = FXCollections.observableArrayList(list);
        vertexListProperty = new SimpleListProperty<>(observableList);

        this.edges = new HashMap<>();
        this.adjacencyMatrix = new ArrayList<>();
        this.numVertices = 1;
        this.vertexRadius = vertexRadius;
    }

    SimpleListProperty<Vertex> vertexListProperty(){
        return vertexListProperty;
    }

    void addVertex(int x, int y) {
        Vertex newVertex = new Vertex(numVertices++, x, y, vertexRadius, Main.interactionModel.getSelectedColor());
        addVertexAdjMatrix();
        vertexListProperty.add(newVertex);
    }

    void deleteVertexAt(int x, int y) {
        Vertex delVertex = getVertexAt(x, y);
        if (delVertex != null) {
            int id = delVertex.getId();
            removeEdgesToVertex(delVertex);
            unMarkAdjMatrix(delVertex);
            vertexListProperty.remove(delVertex);
            reLabelVertices(id);
            numVertices--;
            numVertices = Math.min(maxID(), numVertices) + 1;
        }
    }

    private int maxID() {
        int max = 0;
        for (Vertex v : vertexListProperty) {
            if (v.getId() > max) {
                max = v.getId();
            }
        }
        return max;
    }


    Vertex getVertexAt(int x, int y) {
        Vertex vertex = null;
        for (Vertex v : vertexListProperty) {
            if (v.containsPoint(x, y)) {
                vertex = v;
            }
        }
        return vertex;
    }

    HashMap<String, Edge> getEdges() {
        return edges;
    }

    void addEdge(int x1, int y1, int x2, int y2) {
        Vertex a = getVertexAt(x1, y1);
        Vertex b = getVertexAt(x2, y2);
        if (b != null && a != b) {
            Point2D bPos = b.getPosition();
            x2 = (int) bPos.getX();
            y2 = (int) bPos.getY();
            String edgeId = genEdgeID(a, b);
            String reverseEdgeId = genEdgeID(b, a);
            if (!edges.containsKey(edgeId) && !edges.containsKey(reverseEdgeId)) {
                markAdjacencyMatrix(a, b);
                Edge e = new Edge(a, b, x1, y1, x2, y2);
                edges.put(edgeId, e);
                System.out.println("Creating edge between:\n\ta:\t" + a + "\n\tb:\t" + b);
            }
        }
        System.out.println("\tADDING EDGE");
        printAdjMatrix();
    }

    private String genEdgeID(Vertex a, Vertex b) {
        return a.getId() + "-" + b.getId();
    }

    private void addVertexAdjMatrix() {
        for (int r = 0; r < numVertices - 2; r++) {
            ArrayList<Integer> col = adjacencyMatrix.get(r);
            for (int i = 0; i < (numVertices - col.size()) - 1; i++) {
                col.add(0);
            }
        }
        for (int i = numVertices - 2; i < adjacencyMatrix.size(); i++) {
            adjacencyMatrix.get(i).clear();
        }
        ArrayList<Integer> vertexColumn = new ArrayList<>();
        for (int i = 0; i < numVertices - 1; i++) {
            vertexColumn.add(0);
        }
        adjacencyMatrix.add(vertexColumn);
        for (int i = adjacencyMatrix.size() - 1; i >= 0; i--) {
            ArrayList<Integer> row = adjacencyMatrix.get(i);
            if (row.size() == 0) {
                adjacencyMatrix.remove(row);
            }
        }
        for (ArrayList<Integer> row : adjacencyMatrix) {
            for (int c = row.size() - 1; c >= 0; c--) {
                if (c > numVertices - 2) {
                    row.remove(c);
                }
            }
        }
    }

    private void markAdjacencyMatrix(Vertex a, Vertex b) {
        int aIdx = a.getId() - 1;
        int bIdx = b.getId() - 1;
        System.out.println("marking: [" + aIdx + ", " + bIdx + "], and [" + bIdx + ", " + aIdx + "]");
        adjacencyMatrix.get(aIdx).set(bIdx, 1);
        adjacencyMatrix.get(bIdx).set(aIdx, 1);
    }

    private void unMarkAdjMatrix(Vertex delVertex) {
        int vIDx = delVertex.getId() - 1;
        ArrayList<Integer> clearRow = new ArrayList<>();
        for (ArrayList<Integer> matrix : adjacencyMatrix) {
            matrix.set(vIDx, 0);
            clearRow.add(0);
        }
        adjacencyMatrix.get(vIDx).clear();
        adjacencyMatrix.get(vIDx).addAll(clearRow);
        System.out.println("\tREMOVING EDGES");
        printAdjMatrix();
    }

    private void removeEdgesToVertex(Vertex delVertex) {
        int vIDx = delVertex.getId() - 1;
        System.out.println("deleting: " + vIDx);
        ArrayList<Integer> row = adjacencyMatrix.get(vIDx);
        for (int i = 0; i < row.size(); i++) {
            if (row.get(i) == 1) {
                String id = (vIDx + 1) + "-" + (i + 1);
                String revID = (i + 1) + "-" + (vIDx + 1);
                System.out.println("trying to remove edges: " + id + ", and " + revID);
                edges.remove(id);
                edges.remove(revID);
            }
        }
    }

    private void printAdjMatrix() {
        for (ArrayList<Integer> row : adjacencyMatrix) {
            System.out.println(row);
        }
        Set<Map.Entry<String, Edge>> edgeEntries = edges.entrySet();
        System.out.println("THERE ARE " + edges.size() + " edges:");
        for (Object o: edgeEntries) {
            Map.Entry entry = (Map.Entry) o;
            String key = (String) entry.getKey();
            Edge e = (Edge) entry.getValue();
            System.out.println(key + "\t->\t" + e);
        }
//        System.out.println(edges);
    }

    void updateEdgePositions(Vertex movingVertex) {
        Set<Map.Entry<String, Edge>> edgeEntries = edges.entrySet();
        for (Object o: edgeEntries) {
            Map.Entry entry = (Map.Entry) o;
            String key = (String) entry.getKey();
            String[] keys = key.split("-", 2);
            Edge e = (Edge) entry.getValue();
            int a = Integer.parseInt(keys[0]);
            int b = Integer.parseInt(keys[1]);
            if (movingVertex.getId() == a || movingVertex.getId() == b) {
                int indexID = key.indexOf(Integer.toString(movingVertex.getId()));
                int indexSpace = key.indexOf("-");
//                System.out.println("movingVertex: " + movingVertex.getId() + ", a: " + a + ", b: " + b + ", key: " + key + ", indexID: " + indexID + ", indexSpace: " + indexSpace);
                if (movingVertex.getId() == a) {
                    e.setStartPosition(new Point2D(movingVertex.getX(), movingVertex.getY()));
                }
                else {
                    e.setEndPosition(new Point2D(movingVertex.getX(), movingVertex.getY()));
                }
            }
        }
    }

    // this is going to mess up how the edges work
    void reLabelVertices(int delId) {
        System.out.println("deleting node: " + delId);
        int n = vertexListProperty.size();
        for (int i = delId - 1; i < n; i++) {
            System.out.println("setting: " + vertexListProperty.get(i).getId() + ", to " + (i + 1));
            vertexListProperty.get(i).setId(i + 1);
        }
        reLabelEdgeEntries(delId);
    }

    void reLabelEdgeEntries(int delId) {
        Set<Map.Entry<String, Edge>> edgeEntries = edges.entrySet();
        HashMap<String, Edge> newEdges = new HashMap<>();
        for (Object o: edgeEntries) {
            Map.Entry entry = (Map.Entry) o;
            String key = (String) entry.getKey();
            String[] keys = key.split("-", 2);
            Edge e = (Edge) entry.getValue();
            int a = Integer.parseInt(keys[0]);
            int b = Integer.parseInt(keys[1]);
            a = ((a >= delId)? (a - 1) : a);
            b = ((b >= delId)? (b - 1) : b);
            String newKey = a + "-" + b;
            String oldKey = key;
            if (a >= delId || b >= delId) {
                key = newKey;
                System.out.println("updating: " + oldKey + ", to " + key);
            }
            newEdges.put(key, e);
        }
        edges.clear();
        edges.putAll(newEdges);
    }
}
