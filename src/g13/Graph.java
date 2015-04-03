package g13;

import java.util.*;

/**
 * Created by miquel on 20/03/15.
 */
public abstract class Graph {
    private static final String ERR_CANNOT_ADD_EXISTING_NODE =
            "Cannot add a node that already exists in the graph!";
    private static final String ERR_CANNOT_DEL_INEX_NODE =
            "Cannot delete a node which doesn't belong to the graph!";
    private static final String ERR_CANNOT_ADD_EDGE_INEX_NODE =
            "Cannot add an edge containing nodes which don't belong to " +
                    "the graph!";
    private static final String ERR_CANNOT_REMOVE_EDGE_INEX_NODE =
            "Cannot remove an edge containing nodes which don't belong to " +
                    "the graph";
    private static final String ERR_CANNOT_GET_ADJ_INEX_NODE =
            "Cannot get the adjacency set of a node which is not part of the" +
                    " graph!";

    /**
     * As you can see the graph is created using the interfaces Map and Set
       (if you don't know what a java interface is google it), this way if you
       need to keep the edges or the nodes in a certain order you can use
       the type of Map/Set (See java.utils documentation) according to your
       needs, n this implementation I will use LinkedHashedMap because
       I don't care much about the order so it uses the entry one.
       If you are interested in a certain ordering consider using TreeMaps
       (all valid for sets too)
     */
    private Map<Node, Set<Edge>> G;

    /**
     * g13.Graph getter for subclasses
     */
    protected Map<Node, Set<Edge>> getGraph() {
        return G;
    }

    /**
     * @pre True
     * @post An empty graph is created
	 * Creates an empty graph
	 */
    public Graph() {
        G = new LinkedHashMap<Node, Set<Edge>>();
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

	/**
     * @pre The Node n is not part of the graph
     * @post The Node n is part of the graph
     * @param n
	 * The argument is added to the graph, without adjacencies
	 */
    public void addNode(Node n) {
        if (this.hasNode(n))
            throw new IllegalArgumentException(ERR_CANNOT_ADD_EXISTING_NODE);
        else G.put(n, new LinkedHashSet<Edge>());
    }

	/**
     * @pre The Node n is part of the graph
     * @post The Node n is not a part of the graph
     * @param n
	 * The node n is deleted, so are all its edges, including its neighbors
     * edges
	 */
    public void deleteNode(Node n) {
        Set<Edge> s = G.get(n);

        if (s == null)
            throw new IllegalArgumentException(ERR_CANNOT_DEL_INEX_NODE);

        s.clear(); // Is this really necessary? Wouldn't the Garbage
                          // Collector just remove the set from memory if it's
                          // no longer referenced by anyone after removing
                          // the key from the map?
        G.remove(n);
    }

	/**
     * @pre Both Nodes from the Edge e are part of the graph
     * @post The Edge e is added to the graph
     * @param e
	 */
    public void addEdge(Edge e) {
        Node n1 = e.getNode();
        Node n2 = e.getNeighbor(n1);

        Set<Edge> s1 = G.get(n1);
        Set<Edge> s2 = G.get(n2);

        if (s1 == null || s2 == null)
            throw new IllegalArgumentException(ERR_CANNOT_ADD_EDGE_INEX_NODE);

        s1.add(e);
        s2.add(e);
    }

	/**
     * @pre The nodes of e are part of the graph
     * @post Returns true if and only if e was removed from the graph
     * @param e
	 */
    public boolean removeEdge(Edge e) {
        Node n1 = e.getNode();
        Node n2 = e.getNeighbor(n1);

        Set<Edge> s1 = G.get(n1);
        Set<Edge> s2 = G.get(n2);

        if (s1 == null || s2 == null)
            throw new IllegalArgumentException(ERR_CANNOT_REMOVE_EDGE_INEX_NODE);

        return s1.remove(e) && s2.remove(e);
    }

	/**
     * @pre n is part of the graph
     * @post Returns the set of edges of a node
     * @param n
     * @return
	 * returns the set of edges of that node DO NOT MODIFY IT DIRECTLY
	 */
    public Set<Edge> getAdjacencySet(Node n) {
        Set<Edge> s = G.get(n);

        if (s == null)
            throw new IllegalArgumentException(ERR_CANNOT_GET_ADJ_INEX_NODE);

        return s;
    }

}
