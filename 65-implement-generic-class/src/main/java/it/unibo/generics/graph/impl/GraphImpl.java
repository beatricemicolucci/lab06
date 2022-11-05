package it.unibo.generics.graph.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import it.unibo.generics.graph.api.Graph;

public class GraphImpl<N> implements Graph<N> {

    private final Map<N, Set<N>> edges;

    public GraphImpl() {
        this.edges = new HashMap<>();
    }

    /**
     * Adds a node: nothing happens if node is null or already there.
     * 
     * @param node
     *            the node to add
     */
    public void addNode(final N node) {
        edges.putIfAbsent(node, new HashSet<>());
    }

    /**
     * Adds an edge: nothing happens if source or target are null.
     * 
     * @param source
     *            starting node
     * @param target
     *            ending node
     */
    public void addEdge(final N source, final N target) {
        if (nodeExists(source) && nodeExists(target)) {
            edges.get(source).add(target);
        }
    }

    private boolean nodeExists(final N node) {
        if(this.edges.containsKey(node) == false) {
            throw new IllegalArgumentException("The node " + node + " does not exist.");
        }
        return true;
    }


    /**
     * @return all the nodes
     */
    public Set<N> nodeSet() {
        return this.edges.keySet();
    }

    /**
     * Returns all the nodes directly targeted from a node.
     * 
     * @param node
     *            the node
     * @return all the nodes directly targeted from the passed node
     */
    public Set<N> linkedNodes(final N node) {
        return this.edges.get(node);
    }

    /**
     * Gets one sequence of nodes connecting source to target.
     * 
     * @param source
     *            the source node
     * @param target
     *            the target node
     * @return a sequence of nodes connecting sources and target
     */
    public List<N> getPath(final N source, final N target) {
        //BFS
        final Queue<N> queue = new LinkedList<>();
        final Map<N, N> predecessors = new HashMap<>();
        final List<N> path = new LinkedList<>();
        final Set<N> visited = new HashSet<>();
        N curr_node;

        visited.add(source);
        queue.add(source);
        predecessors.put(source, null);

        while (!queue.isEmpty()) {
            curr_node = queue.remove();
            if (curr_node.equals(target)) {
                break;
            }
            for (final N n : edges.get(curr_node)) {
                if (!visited.contains(n)) {
                    visited.add(n);
                    predecessors.put(n, curr_node);
                    queue.add(n);
                }                
            }
        }

        curr_node = target;

        while(predecessors.get(curr_node) != null) {
            path.add(curr_node);
            curr_node = predecessors.get(curr_node);
        }

        path.add(source);
        Collections.reverse(path);

        return path;

    }
    
}
