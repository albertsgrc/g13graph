package g13;

import java.util.*;

/**
 * Created by miquel on 20/03/15.
 */
public abstract class Graph {
    private static final String ERR_ADD_EXISTING_NODE =
            "Cannot add a node that already exists in the graph!";
    private static final String ERR_INEX_NODE =
            "The node doesn't belong to the graph";

    private Map<Node, Set<Edge>> G;

    /**
     * @pre True
     * @post An empty graph is created
	 * Creates an empty graph
	 */
    public Graph() {
        G = new LinkedHashMap<Node, Set<Edge>>();
    }

    /**
     * g13.Graph getter for subclasses
     */
    protected Map<Node, Set<Edge>> getGraph() {
        return G;
    }

    public int getOrder() {
        return G.keySet().size();
    }

    public int getEdgeCount() {
        return G.size()/2;
    }


    /**
     * @pre n is part of the graph
     * @post Returns the set of edges of a node
     * @param n
     * @return
     * returns the set of edges of that node DO NOT MODIFY IT DIRECTLY
     */
    public Set<Edge> getAdjacencyList(Node n) {
        return Collections.unmodifiableSet(getModifiableAdjacencyList(n));
    }

    private Set<Edge> getModifiableAdjacencyList(Node n) {
        Set<Edge> s = G.get(n);

        if (s == null) throw new IllegalArgumentException(ERR_INEX_NODE);

        return s;
    }

	/**
     * @pre True
     * @post Returns true if the node is in the graph, false otherwise
	 * @param n
	 * @return
     * Returns true if the node is in the graph, false otherwise
	 */
    public boolean hasNode(Node n) {
        return G.containsKey(n);
    }

    public int getNodeDegree(Node n) {
        return getAdjacencyList(n).size();
    }

    public boolean containsEdge(Edge e) {
        return getAdjacencyList(e.getNode()).contains(e);
    }

    @Override public String toString() {
        final String NEW_LINE = System.getProperty("line.separator");
        StringBuilder s = new StringBuilder();

        s.append("Order: ").append(getOrder())
                .append(" Number of edges: ").append(getEdgeCount())
                .append(NEW_LINE).append(NEW_LINE)
                .append("Nodes:").append(NEW_LINE);

        for (Node n : G.keySet()) s.append("    ").append(n).append(NEW_LINE);

        s.append(NEW_LINE).append("Edges:").append(NEW_LINE);

        for (Set<Edge> set : G.values())
            for (Edge e : set) s.append("    ").append(e).append(NEW_LINE);

        return s.toString();
    }

	/**
     * @pre The Node n is not part of the graph
     * @post The Node n is part of the graph
     * @param n
	 * The argument is added to the graph, without adjacencies
	 */
    public void addNode(Node n) {
        if (this.hasNode(n))
            throw new IllegalArgumentException(ERR_ADD_EXISTING_NODE);
        else G.put(n, new LinkedHashSet<Edge>());
    }

	/**
     * @pre The Node n is part of the graph
     * @post The Node n is not a part of the graph
     * @param n
	 * The node n is deleted, so are all its edges, including its neighbors
     * edges
	 */
    public void removeNode(Node n) {
        G.remove(n);
    }

	/**
     * @pre Both Nodes from the Edge e are part of the graph
     * @post The Edge e is added to the graph iff it was not already present
     * @param e
	 */
    public void addEdge(Edge e) {
        Node n1 = e.getNode();
        Node n2 = e.getNeighbor(n1);

        getModifiableAdjacencyList(n1).add(e);
        getModifiableAdjacencyList(n2).add(e);
    }

	/**
     * @pre The nodes of e are part of the graph
     * @post Returns true if and only if e was removed from the graph
     * @param e
	 */
    public boolean removeEdge(Edge e) {
        Node n1 = e.getNode();
        Node n2 = e.getNeighbor(n1);

        return getModifiableAdjacencyList(n1).remove(e) &&
                getModifiableAdjacencyList(n2).remove(e);
    }
}
