package g13;

import java.io.IOException;
import java.util.Scanner;

public class DriverGraph {

    private static class NodePair {
        public Node first;
        public Node second;

        public NodePair(Node first, Node second) {
            this.first = first;
            this.second = second;
        }
    }

    private static Scanner cin;

    private enum Option {
        ADD_NODE, REMOVE_NODE, ADD_EDGE, REMOVE_EDGE,
        HAS_EDGE, HAS_VALID_EDGE, HAS_NODE, GET_ORDER,
        GET_EDGE_COUNT, GET_VALID_EDGE_COUNT, GET_EDGES,
        GET_VALID_EDGES, GET_NODES, GET_ADJACENCY_LIST,
        GET_VALID_ADJACENCY_LIST, GET_NODE_DEGREE,
        GET_VALID_NODE_DEGREE, GET_EDGE, REMOVE_ALL_NODE_EDGES,
        INVALIDATE_ALL_EDGES, SET_EDGE_WEIGHT, SET_EDGE_VALIDITY, PRINT_GRAPH,
        GET_NEIGHBOURS, GET_VALID_NEIGHBOURS, GET_EDGE_VALIDITY,
        GET_EDGE_WEIGHT,

        PRINT_OPTIONS, PRINT, EXIT

    };

    public static void print(String s) {
        System.out.println(s);
    }

    public static void printInteger(int x) {
        print(Integer.toString(x));
    }

    public static void printDouble(double x) {
        print(Double.toString(x));
    }

    public static void printBoolean(boolean x) {
        print(Boolean.toString(x));
    }

    public static void printOptions() {
        print("Option list:");
        for (Option op : Option.values()) print("* " + op);
        print("");
    }

    private static void printErr(String s) {
        print("!!! ERR: " + s);
    }

    private static String readStringWithSpaces() {
        String line = cin.nextLine().trim();
        while (line.isEmpty()) line = cin.nextLine().trim();
        return line;
    }

    private static NodePair readNodePair() {
        print("Enter the first node:\n");
        tNode n1 = readNode();
        print("Enter the second node:\n");
        tNode n2 = readNode();
        return new NodePair(n1, n2);
    }

    private static tEdge readEdge() {
        NodePair e = readNodePair();
        print("Enter the edge weight (double): ");
        return new tEdge(e.first, e.second, cin.nextDouble(), true);
    }

    private static tNode readNode() {
        print("Enter the node key (int): ");
        return new tNode(cin.nextInt());
    }

    private static Option readOption() throws IOException {
        Option res = null;

        boolean valid = false;
        while (!valid) {
            print("Enter an option: ");
            String opt = readStringWithSpaces();
            try {
                res = Option.valueOf(opt.toUpperCase());
                valid = true;
            } catch (IllegalArgumentException e) {
                printErr("Invalid option name");
                print("Enter " + Option.PRINT_OPTIONS +
                        " to list available options\n");
            }
        }

        return res;
    }
    
    public static void main(String args[]) throws IOException {
        tGraph G = new tGraph();
        cin = new Scanner(System.in);

        printOptions();

        Option op = readOption();
        while (op != Option.EXIT) {
            switch(op) {
                case ADD_NODE:
                    boolean added = G.addNode(readNode());
                    if (!added) print("The node wasn't added because it " +
                            "already belonged to the graph");
                    break;
                case REMOVE_NODE:
                    boolean removed = G.removeNode(readNode());
                    if (!removed) print("The node wasn't removed because it " +
                            "didn't belong to the graph");
                    break;
                case ADD_EDGE:
                    try {
                        boolean added2 = G.addEdge(readEdge());
                        if (!added2) print("The edge wasn't added because it " +
                                "already belonged to the graph");
                    } catch (IllegalArgumentException e) {
                        print("An error occured while adding the edge: " +
                                e.getMessage());
                    }
                    break;
                case REMOVE_EDGE:
                    NodePair np = readNodePair();
                    try {
                        boolean removed2 = G.removeEdge(np.first, np.second);
                        if (!removed2)
                            print("The edge wasn't removed because " +
                                    "it didn't belong to the graph");
                    } catch(IllegalArgumentException e) {
                        print("An error occured while trying to remove the " +
                                "edge: " + e.getMessage());
                    }
                    break;
                case HAS_EDGE:
                    NodePair np2 = readNodePair();
                    try {
                        printBoolean(G.hasEdge(np2.first, np2.second));
                    } catch(IllegalArgumentException e) {
                        print("An error occured while checking the edge: " +
                                e.getMessage());
                    }
                    break;
                case HAS_NODE:
                    printBoolean(G.hasNode(readNode()));
                    break;
                case GET_ORDER:
                    printInteger(G.getOrder());
                    break;
                case GET_EDGE_COUNT:
                    printInteger(G.getEdgeCount());
                    break;
                case GET_VALID_EDGE_COUNT:
                    printInteger(G.getValidEdgeCount());
                    break;
                case GET_EDGES:
                    print(G.getEdges().toString());
                    break;
                case GET_NODES:
                    print(G.getNodes().toString());
                    break;
                case GET_VALID_EDGES:
                    print(G.getValidEdges().toString());
                    break;
                case GET_ADJACENCY_LIST:
                    try {
                        print(G.getAdjacencyList(readNode()).toString());
                    } catch (IllegalArgumentException e) {
                        print("An error occured while getting the adjacency " +
                                "list: " + e.getMessage());
                    }
                    break;
                case GET_VALID_ADJACENCY_LIST:
                    try {
                        print(G.getValidAdjacencyList(readNode()).toString());
                    } catch (IllegalArgumentException e) {
                        print("An error occured while getting the valid " +
                                "adjacency list: " + e.getMessage());
                    }
                    break;
                case GET_NODE_DEGREE:
                    try {
                        printInteger(G.getNodeDegree(readNode()));
                    }
                    catch(IllegalArgumentException e) {
                        print("An error occured while retrieving the node " +
                                "degree: " + e.getMessage());
                    }
                    break;
                case GET_VALID_NODE_DEGREE:
                    try {
                        printInteger(G.getValidNodeDegree(readNode()));
                    } catch(IllegalArgumentException e) {
                        print("An error occured while retrieving the valid " +
                                "node degree: " + e.getMessage());
                    }
                    break;
                case GET_EDGE:
                    NodePair np3 = readNodePair();
                    try {
                        Edge e = G.getEdge(np3.first, np3.second);
                        if (e == null)
                            print("The edge doesn't belong to the graph");
                        else print(e.toString());
                    } catch (IllegalArgumentException e) {
                        print("An error occured while retrieving the edge: "
                                + e.getMessage());
                    }
                    break;
                case HAS_VALID_EDGE:
                    NodePair np4 = readNodePair();
                    try {
                        printBoolean(G.hasValidEdge(np4.first, np4.second));
                    } catch (IllegalArgumentException e) {
                        print("An error occured while checking the valid edge: "
                                + e.getMessage());
                    }
                    break;
                case REMOVE_ALL_NODE_EDGES:
                    try {
                        G.removeAllNodeEdges(readNode());
                    } catch (IllegalArgumentException e) {
                        print("An error occured while removing the node edges: "
                                + e.getMessage());
                    }
                    break;
                case INVALIDATE_ALL_EDGES:
                    G.invalidateAllEdges();
                    break;
                case SET_EDGE_WEIGHT:
                    Edge edge = readEdge();
                    try {
                        Edge graphEdge = G.getEdge(edge.getNode(),
                                edge.getNeighbor(edge.getNode()));
                        if (graphEdge == null)
                            print("The edge doesn't belong to the graph");
                        else graphEdge.setWeight(edge.getWeight());
                    } catch (IllegalArgumentException e) {
                        print("There was an error while setting the edge " +
                                "weight: " + e.getMessage());
                    }
                    break;
                case SET_EDGE_VALIDITY:
                    NodePair np5 = readNodePair();
                    print("Enter the desired validity (true/false):");
                    try {
                        Edge edge2 = G.getEdge(np5.first, np5.second);
                        if (edge2 == null)
                            print("The edge doesn't belong to the graph");
                        else edge2.setValidity(cin.nextBoolean());
                    } catch (IllegalArgumentException e) {
                        print("There was an error while setting the edge " +
                                "validity: " + e.getMessage());
                    }
                    break;
                case GET_EDGE_WEIGHT:
                    NodePair np6 = readNodePair();
                    try {
                        Edge edge2 = G.getEdge(np6.first, np6.second);
                        if (edge2 == null)
                            print("The edge doesn't belong to the graph");
                        else printDouble(edge2.getWeight());
                    } catch (IllegalArgumentException e) {
                        print("An error occured while retrieving the edge " +
                                "weight: " + e.getMessage());
                    }
                    break;
                case GET_NEIGHBOURS:
                    try {
                        print(G.getNeighbours(readNode()).toString());
                    } catch (IllegalArgumentException e) {
                        print("An error occured while getting the node " +
                                "neighbours: " + e.getMessage());
                    }
                    break;
                case GET_VALID_NEIGHBOURS:
                    try {
                        print(G.getValidNeighbours(readNode()).toString());
                    } catch (IllegalArgumentException e) {
                        print("An error occured while getting the node " +
                                "valid neighbours: " + e.getMessage());
                    }
                    break;
                case GET_EDGE_VALIDITY:
                    NodePair np7 = readNodePair();
                    try {
                        Edge edge2 = G.getEdge(np7.first, np7.second);
                        if (edge2 == null)
                            print("The edge doesn't belong to the graph");
                        else printBoolean(edge2.isValid());
                    } catch (IllegalArgumentException e) {
                        print("An error occured while retrieving the edge " +
                                "validity: " + e.getMessage());
                    }
                    break;
                case PRINT:
                    print(readStringWithSpaces());
                    break;
                case PRINT_GRAPH:
                    print(G.toString());
                    break;
                case PRINT_OPTIONS:
                    printOptions();
                    break;
            }
            op = readOption();
        }
    }
}
