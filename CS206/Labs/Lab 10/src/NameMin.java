/**
* Class that contains (Name Priority) pairs. The lowest value for priority is
* considered to be the most important.
*/
public class NameMin implements Comparable<NameMin> {
    private String name;
    private int priority;

    /**
    * DO NOT MODIFY THIS METHOD
    *
    * Constructor.
    *
    * @param n The name.
    * @param p The priority.
    */
    public NameMin(String n, int p) {
        name = n;
        priority = p;
    }

    /**
    * DO NOT MODIFY THIS METHOD
    *
    * Accessor for name.
    *
    * @return The name.
    */
    public String getName() {
        return name;
    }

    /**
    * DO NOT MODIFY THIS METHOD
    *
    * Accessor for priority.
    *
    * @return The priority.
    */
    public int getPriority() {
        return priority;
    }

    /**
    * Lab 10: STUDENT MUST COMPLETE THIS METHOD
    *
    * Implement compareTo so that when this class is used in a priority queue,
    * the lowest value of priority will be the first removed from the queue.
    */
    public int compareTo(NameMin other) {
        // add code to complete this method.
        if (this.getPriority() < other.getPriority()){
            return 1;
        } else if (this.getPriority() > other.getPriority()){
            return -1;
        }

        return 0;

    }

}
