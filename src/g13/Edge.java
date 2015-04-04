package g13;

/**
 * Created by miquel on 20/03/15.
 *
 * Eventhough it is named 'Edge' this class models undirected weighted edges.
 */
public abstract class Edge {
    private static final String ERR_NOT_PART_EDGE =
            "The node is not part of the edge!";

    // Both nodes are declared final. This is a good practice
    // when they're meant to be assigned only once, plus it is needed
    // to maintain coherence when using them as set keys
    private final Node n1;
    private final Node n2;
    private int weight;


	public int getWeight() {
		return weight;
	}

    // TODO: Do we accept zero or negative weight edges?
	public void setWeight(int weight) {
		this.weight = weight;
	}

    // TODO: Do we accept edges where m1 == m2? And again negative or 0 weights?
    /**
     * @pre Both m1 and m2 are non-null
     * @post An Edge between the nodes n1 and n2 with weight w is created
     * @param m1
     * @param m2
     * @param w
     */
    public Edge(Node m1, Node m2, int w) {
        if (m1 == null || m2 == null) throw new NullPointerException();

        if (m2.isGreater(m1)) {
            n1 = m1;
            n2 = m2;
        }
        else {
            n1 = m2;
            n2 = m1;
        }

        weight = w;
    }

    /**
     *
     * @param m1
     * @param m2
     * @pre
     * @post
     */
    public Edge(Node m1, Node m2) {
        this(m1, m2, 1);
    }

	/**
     * @pre The Node n is part of the Edge
     * @post The other Node from the Edge is returned
     * @param n
     * @return
	 * Given one of the two nodes of the Edge, it returns the other one.
     * It is implemented like this due to the fact that it is not a directed
     * edge therefore there is no "origin" nor "destiny" node.
     *
     * Note that this function may only be used with a node n obtained with the
     * getNode() operation. Otherwise this method wouldn't work as expected,
     * because it compares the references, not the data.
	 */
    public Node getNeighbor(Node n) {
        if (n == n1) return n2;
        else if (n == n2) return n1;
        else throw new IllegalArgumentException(ERR_NOT_PART_EDGE);
    }

	/**
	 * @return A node of the edge
	 */
    public Node getNode() {
        return n1;
    }

    /**
     * @param o
     * @return
     * Returns true if and only if the edge e has equal
     * nodes as this Edge (no matter the order)
     */
    @Override public boolean equals(Object o) {
        // We don't check the weight as we don't expect to have two edges
        // with different weights joining the same pair of nodes in the graph
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Edge e = (Edge) o;

        return e.n1.equals(n1) && e.n2.equals(n2);
    }

    /**
     *
     * @return Returns equal integers for equal edges (as defined in
     *         the equals method).
     * This method is needed for using Edges as HashSet or HashMap keys
     */
    @Override public int hashCode() {
        int hash = n1.hashCode();
        hash = 31*hash + n2.hashCode();

        return hash;
    }

    /**
     *
     * @return Returns a String representing an Edge in a human readable format.
     *         Note that the nodes of the edge are printed in lexicographical
     *         order, so both Edge 3,2 and Edge 2,3 would be printed as 2,3
     *
     * Implement this function as you fancy
     */
    @Override public String toString() {
        return n1.toString() + ' ' + n2.toString() + ' ' + weight;
    }
}
