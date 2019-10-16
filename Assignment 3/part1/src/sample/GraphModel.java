package sample;

import java.util.ArrayList;

import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;

public class GraphModel {

    private SimpleListProperty<Vertex> vertexListProperty;
    private ArrayList<Edge> edges;
    private int vertexRadius;
    private int numVerticies;

    public GraphModel(int vertexRadius) {
        ArrayList<Vertex> list = new ArrayList<Vertex>();
        ObservableList<Vertex> observableList = (ObservableList<Vertex>) FXCollections.observableArrayList(list);
        vertexListProperty = new SimpleListProperty<Vertex>(observableList);

        this.edges = new ArrayList<>();
        this.numVerticies = 1;
        this.vertexRadius = vertexRadius;
    }

    public SimpleListProperty<Vertex> vertexListProperty(){
        return vertexListProperty;
    }

    public int getVertexRadius() {return vertexRadius;}

    public void addVertex(int x, int y) {
        Vertex newVertex = new Vertex(numVerticies++, x, y, vertexRadius);
        System.out.println("newVertex: " + newVertex);
        vertexListProperty.add(newVertex);
    }

    public void deleteVertexAt(int x, int y) {
        Vertex delVertex = getVertexAt(x, y);
        System.out.println("delVertex: " + delVertex);
        vertexListProperty.remove(delVertex);
        numVerticies--;
        numVerticies = Math.min(maxID(), numVerticies) + 1;
    }

    public int maxID() {
        int max = 0;
        for (Vertex v : vertexListProperty) {
            if (v.getId() > max) {
                max = v.getId();
            }
        }
//        System.out.println("maxID:\t" + max);
        return max;
    }


    public Vertex getVertexAt(int x, int y) {
        Vertex vertex = null;

        for (Vertex v : vertexListProperty) {
            if (v.containsPoint(x, y)) {
                vertex = v;
            }
        }
        return vertex;
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public void addEdge(int x1, int y1, int x2, int y2) {
        System.out.println("Adding edge");
        Vertex a = getVertexAt(x1, y1);
        Vertex b = getVertexAt(x2, y2);
        if (b != null && a != b) {
            Point2D bPos = b.getPosition();
            x2 = (int) bPos.getX();
            y2 = (int) bPos.getY();
            Edge e = new Edge(a, b, x1, y1, x2, y2);
            edges.add(e);
            System.out.println("Creating edge between:\n\ta:\t" + a + "\n\tb:\t" + b);
        }
    }
}
