public class VertexPriority implements Comparable<VertexPriority> {
    private int vertex;
    private int priority;

    /**
     *
     * Constructor.
     *
     * @param v The name.
     * @param p The priority.
     */
    public VertexPriority(int v, int p) {
        vertex = v;
        priority = p;
    }

    /**
     *
     * Accessor for name.
     *
     * @return The name.
     */
    public int getVertex() {
        return vertex;
    }

    /**
     *
     * Accessor for priority.
     *
     * @return The priority.
     */
    public int getPriority() {
        return priority;
    }

    /**
     * Implements comparable, smaller values indicate a higher priority
     * @param other Another Vertex with Priority
     * @return 0 if the vertices have the same priority,
     * 1 if this vertex has the higher priority,
     * -1 if this vertex has lower priority
     *
     */
    public int compareTo(VertexPriority other) {

        if (this.getPriority() < other.getPriority()){
            return 1;
        } else if (this.getPriority() > other.getPriority()){
            return -1;
        }

        return 0;

    }

}