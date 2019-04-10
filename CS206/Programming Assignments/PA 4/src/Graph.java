import java.util.*;

/**
 * Implements a Graph Data Structure
 *
 * @author Tom Giagtzoglou
 */
public class Graph {
    /**
     * An array containing the vertices for this graph
     */
    private Vertex[] vertices;

    /**
     * A 2D array to hold the edges and their weights
     * <p>
     * An int at [1][2] with value 2 is an edge directed from vertex 1 to vertex 2, with weight 2
     */
    private int[][] edges;

    /**
     * Constructs a Graph with v vertices and no edges
     *
     * @param v Number of vertices
     */
    public Graph(int v) {
        vertices = new Vertex[v];
        edges = new int[v][v];
    }

    public void addVertex(int n, String name) throws IllegalArgumentException {
        if (n < 0 || n > vertices.length) {
            throw new IllegalArgumentException("Invalid vertex");
        }

        vertices[n] = new Vertex(name);
    }

    /**
     * Adds a directed edge from v1 to v2
     *
     * @param v1 The first Vertex
     * @param v2 The second Vertex
     * @throws java.lang.IllegalArgumentException - if either vertex is not a valid vertex
     *                                            number, or if the weight is not greater than zero.
     */
    public void addDirectedEdge(int v1, int v2) {
        if (v1 < 0 || v1 >= vertices.length) {
            throw new IllegalArgumentException("Invalid starting index");
        }
        if (v2 < 0 || v2 >= vertices.length) {
            throw new IllegalArgumentException("Invalid starting index");
        }

        edges[v1][v2] = 1;
    }

    /**
     * Adds a directed edge from v1 to v2 with weight
     *
     * @param v1 The first Vertex
     * @param v2 The second Vertex
     * @param w  The edge's weight
     * @throws java.lang.IllegalArgumentException - if either vertex is not a valid vertex
     *                                            number, or if the weight is not greater than zero.
     */
    public void addDirectedEdgeWithWeight(int v1, int v2, int w) {
        if (v1 < 0 || v1 >= vertices.length) {
            throw new IllegalArgumentException("Invalid starting index");
        }
        if (v2 < 0 || v2 >= vertices.length) {
            throw new IllegalArgumentException("Invalid starting index");
        }
        if (w < 0) {
            throw new IllegalArgumentException("Invalid starting index");
        }

        edges[v1][v2] = w;
    }

    /**
     * Adds an edge between v1 and v2
     *
     * @param v1 The first Vertex
     * @param v2 The second Vertex
     * @throws java.lang.IllegalArgumentException - if either vertex is not a valid vertex
     *                                            number, or if the weight is not greater than zero.
     */
    public void addEdge(int v1, int v2) {
        if (v1 < 0 || v1 >= vertices.length) {
            throw new IllegalArgumentException("Invalid starting index");
        }
        if (v2 < 0 || v2 >= vertices.length) {
            throw new IllegalArgumentException("Invalid starting index");
        }

        edges[v1][v2] = 1;
        edges[v2][v1] = 1;
    }

    /**
     * Adds an edge between v1 and v2 with weight
     *
     * @param v1 The first Vertex
     * @param v2 The second Vertex
     * @param w  The edge's weight
     * @throws java.lang.IllegalArgumentException - if either vertex is not a valid vertex
     *                                            number, or if the weight is not greater than zero.
     */
    public void addEdgeWithWeight(int v1, int v2, int w) {
        if (v1 < 0 || v1 >= vertices.length) {
            throw new IllegalArgumentException("Invalid starting index");
        }
        if (v2 < 0 || v2 >= vertices.length) {
            throw new IllegalArgumentException("Invalid starting index");
        }
        if (w < 0) {
            throw new IllegalArgumentException("Invalid starting index");
        }

        edges[v1][v2] = w;
        edges[v2][v1] = w;
    }

    /**
     * Returns a string representation of the graph vertices visited in breadth first order, starting at the given vertex
     *
     * @param start The vertex to start from
     * @return A string representation of the graph vertices traversed in breadth first order;
     * For each node includes node name and vertex number
     * @throws java.lang.IllegalArgumentException - if start is not a valid vertex number.
     */
    public String bfs(int start) {
        if (start < 0 || start >= vertices.length) {
            throw new IllegalArgumentException("Invalid starting index");
        }

        setVerticesUnvisited();

        String output = "";
        Queue<Integer> q = new LinkedList<Integer>();

        q.offer(start);

        while (!q.isEmpty()) {
            int v = q.poll();
            vertices[v].setVisited();
            output += "[" + vertices[v].getName() + ", " + v + "]; ";
            for (int i = 0; i < edges.length; i++) {
                if (edges[v][i] != 0 && vertices[i].isUnvisited()) {
                    q.offer(i);
                    vertices[i].setWaiting();
                }
            }
        }
        return output;
    }

    /**
     * Returns a string representation of the graph vertices visited in depth first order, starting at the given vertex
     *
     * @param start The vertex to start from
     * @return A string representation of the graph vertices traversed in bredth first order;
     * For each node includes node name and vertex number
     * @throws java.lang.IllegalArgumentException - if start is not a valid vertex number.
     */
    public String dfs(int start) {
        if (start < 0 || start >= vertices.length) {
            throw new IllegalArgumentException("Invalid starting index");
        }

        setVerticesUnvisited();

        String output = "";
        Deque<Integer> stack = new LinkedList<Integer>();

        stack.push(start);
        output += "[" + vertices[start].getName() + ", " + start + "]; ";
        while (!stack.isEmpty()) {
            int v = stack.peek();
            vertices[v].setVisited();
            boolean found = false;
            for (int i = 0; i < edges.length && !found; i++) {
                if (edges[v][i] != 0 && vertices[i].isUnvisited()) {
                    stack.push(i);
                    vertices[i].setVisited();
                    output += "[" + vertices[i].getName() + ", " + i + "]; ";
                    found = true;
                }
            }
            if (!found) {
                stack.pop();
            }
        }
        return output;
    }

    /**
     * Returns a string that contains information about the shortest path from the given node to every node in
     * the graph, including the path from the given node to itself. This method uses Dijkstra's algorithm for
     * finding the shortest path assuming the edges are weighted.
     *
     * @param start The vertex to start from
     * @return A string containing information about the shortest path from the given node to
     * every node in the graph.
     * @throws java.lang.IllegalArgumentException - if start is not a valid vertex number.
     */
    public String dijkstras(int start) {
        if (start < 0 || start >= vertices.length) {
            throw new IllegalArgumentException("Invalid starting index");
        }

        int[] pathLength = new int[vertices.length];

        //Stores strings containing the nodes for the shortest path
        ArrayList<Integer>[] paths = new ArrayList[vertices.length];

        for (int i = 0; i < paths.length; i++) {
            paths[i] = new ArrayList<Integer>();
        }

        setPathLengthsToInf(pathLength);
        setVerticesUnvisited();
        pathLength[start] = 0;
        paths[start].add(start);

        PriorityQueue<VertexPriority> q = new PriorityQueue<VertexPriority>();

        q.offer(new VertexPriority(start, 0));


        while (!q.isEmpty()) {
            VertexPriority currV = q.poll();
            int v = currV.getVertex();
            int w = currV.getPriority();
            vertices[v].setVisited();

            for (int i = 0; i < edges[v].length; i++) {
                int weight = edges[v][i];
                if (weight != 0) {
                    if (pathLength[v] + weight < pathLength[i]) {
                        pathLength[i] = pathLength[v] + weight;
                        paths[i] = (ArrayList<Integer>) paths[v].clone();
                        paths[i].add(i);
                    }
                    q.offer(new VertexPriority(i, pathLength[v] + weight));
                    vertices[i].setWaiting();
                }
            }
        }
        String output = "";
        for (int i = 0; i < pathLength.length; i++) {
            output += vertices[start].getName() + " " + start + " to " + vertices[i].getName() + " " + i + " is " + pathLength[i] + ", path: " + pathArrayToString(paths[i]) + "\n";
        }


        return output;
    }

    /**
     * Returns a string that contains information about the shortest path from the given node to every node in
     * the graph, including the path from the given node to itself. This method considers the shortest path
     * assuming the edges are unweighted. The string should have an end of line after each path from the given
     * node to another node.
     *
     * @param start The vertex to start from
     * @return A string containing information about the shortest path from the given node to
     * every node in the graph.
     * @throws IllegalArgumentException - if start value is not valid
     */
    public String shortestPath(int start) {
        if (start < 0 || start >= vertices.length) {
            throw new IllegalArgumentException("Invalid starting index");
        }
        int[] pathLength = new int[vertices.length];

        //Stores strings containing the nodes for the shortest path
        ArrayList<Integer>[] paths = new ArrayList[vertices.length];
        Queue<Integer> q = new LinkedList<Integer>();


        for (int i = 0; i < paths.length; i++) {
            paths[i] = new ArrayList<Integer>();
        }

        setPathLengthsToInf(pathLength);
        setVerticesUnvisited();

        pathLength[start] = 0;
        paths[start].add(start);


        q.offer(start);

        while (!q.isEmpty()) {
            int v = q.poll();
            vertices[v].setVisited();

            for (int i = 0; i < edges[v].length; i++) {
                if (edges[v][i] != 0 && vertices[i].isUnvisited()) {
                    if (pathLength[v] + 1 < pathLength[i]) {
                        pathLength[i] = pathLength[v] + 1;
                        paths[i] = (ArrayList<Integer>) paths[v].clone();
                        paths[i].add(i);
                    }
                    q.offer(i);
                    vertices[i].setWaiting();
                }
            }
        }
        String output = "";
        for (int i = 0; i < pathLength.length; i++) {
            output += vertices[start].getName() + " " + start + " to " + vertices[i].getName() + " " + i + " is " + pathLength[i] + ", path: " + pathArrayToString(paths[i]) + "\n";
        }
        return output;
    }

    /**
     * Sets all vertices as unvisited
     */
    private void setVerticesUnvisited() {
        for (Vertex v : vertices) {
            v.setUnvisited();
        }
    }

    /**
     * Sets all path lengths to Integer.MAX_VALUE
     *
     * @param pl an integer array of path lengths
     */
    private void setPathLengthsToInf(int[] pl) {
        for (int i = 0; i < pl.length; i++) {
            pl[i] = Integer.MAX_VALUE;
        }
    }

    /**
     * Converts an arraylist representing the shortest path to a strinng
     *
     * @param list the array list to be converted to a string
     * @return the string representing the path
     */
    private String pathArrayToString(ArrayList<Integer> list) {
        String output = "";
        for (int i = 0; i < list.size(); i++) {
            Vertex currVert = vertices[list.get(i)];
            output += (i == 0 ? "" : "to ") + currVert.getName() + " " + list.get(i) + " ";
        }
        return output;
    }

}
