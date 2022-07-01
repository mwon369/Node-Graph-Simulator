package nz.ac.auckland.se281.a4.ds;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nz.ac.auckland.se281.a4.TwitterHandle;
//*******************************
//YOU SHOUD MODIFY THE SPECIFIED 
//METHODS  OF THIS CLASS
//HELPER METHODS CAN BE ADDED
//REQUIRED LIBRARIES ARE ALREADY 
//IMPORTED -- DON'T ADD MORE
//*******************************

public class Graph {

	/**
	 * Each node maps to a list of all the outgoing edges from that node
	 */
	protected Map<Node<String>, LinkedList<Edge<Node<String>>>> adjacencyMap;
	/**
	 * root of the graph, to know where to start the DFS or BFS
	 */
	protected Node<String> root;

	/**
	 * !!!!!! You cannot change this method !!!!!!!
	 */

	/**
	 * Creates a Graph
	 * 
	 * @param edges
	 *            a list of edges to be added to the graph
	 */
	public Graph(List<String> edges) {
		adjacencyMap = new LinkedHashMap<>();
		int i = 0;
		if (edges.isEmpty()) {
			throw new IllegalArgumentException("edges are empty");
		}

		for (String edge : edges) {
			String[] split = edge.split(",");
			Node<String> source = new Node<String>(split[0]);
			Node<String> target = new Node<String>(split[1]);
			Edge<Node<String>> edgeObject = new Edge<Node<String>>(source, target);

			if (!adjacencyMap.containsKey(source)) {
				adjacencyMap.put(source, new LinkedList<Edge<Node<String>>>());
			}
			adjacencyMap.get(source).append(edgeObject);

			if (i == 0) {
				root = source;
			}
			i++;
		}
	}

	/**
	 * This method returns a boolean based on whether the
	 * input sets are reflexive.
	 * 
	 * TODO: Complete this method (Note a set is not passed in as a parameter but a
	 * list)
	 * 
	 * @param set
	 *            A list of TwitterHandles
	 * @param relation
	 *            A relation between the TwitterHandles
	 * @return true if the set and relation are reflexive
	 */
	public boolean isReflexive(List<String> set, List<String> relation) {
		int count = 0;

		/*
		 *  for each twitter handle, loop over the relation and check if there 
		 *  is a string that contains the current twitter handle.
		 *  
		 *  if there is, then we want to increment our count variable if the 
		 *  first and last character are the same
		 */
		for (String handle : set) {
			for (String handleRelation : relation) {
				if (handleRelation.contains(handle)) {
					if (handleRelation.startsWith(handle) && handleRelation.endsWith(handle)) {
						count++;
					}
				}
			}
		}

		// reflexivity = true if the number of reflexive relations = number of handles
		return count == set.size();
	}

	/**
	 * This method returns a boolean based on whether the
	 * input set is symmetric.
	 * 
	 * If the method returns false, then the following must
	 * be printed to the console:
	 * "For the graph to be symmetric tuple: " + requiredElement + " MUST be
	 * present"
	 * 
	 * TODO: Complete this method (Note a set is not passed in as a parameter but a
	 * list)
	 * 
	 * @param relation
	 *            A relation between the TwitterHandles
	 * @return true if the relation is symmetric
	 */
	public boolean isSymmetric(List<String> relation) {
		// object used to find build relation ac & return false message
		StringBuilder sb = new StringBuilder();

		/*
		 * for each string in the relation, first check if the 
		 * first and last characters are different.
		 * 
		 * if they are, then we should check if the reverse of 
		 * that string exists in the relation
		 */
		for (String handleRelation : relation) {
			if (handleRelation.charAt(0) != handleRelation.charAt(2)) {
				// convert to charArray to swap the first and last characters around
				char[] charArray = handleRelation.toCharArray();
				char temp = charArray[2];
				charArray[2] = charArray[0];
				charArray[0] = temp;
				// check if the reverse pair is in the relation of strings
				String reversedRelation = String.valueOf(charArray);
				if (!relation.contains(reversedRelation)) {
					sb.append("For the graph to be symmetric tuple: ");
					sb.append(reversedRelation);
					sb.append(" MUST be present");
					System.out.println(sb);
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * This method returns a boolean based on whether the
	 * input set is transitive.
	 * 
	 * If the method returns false, then the following must
	 * be printed to the console:
	 * "For the graph to be transitive tuple: " + requiredElement + " MUST be
	 * present"
	 * 
	 * TODO: Complete this method (Note a set is not passed in as a parameter but a
	 * list)
	 * 
	 * @param relation
	 *            A relation between the TwitterHandles
	 * @return true if the relation is transitive
	 */
	public boolean isTransitive(List<String> relation) {
		// object used to find build relation ac & return false message
		StringBuilder sb = new StringBuilder();

		/*
		 * for each relation ab, we need to look for some relation bc.
		 * 
		 * we then need to establish the relation ac and check if it
		 * exists in the input relation set, and if at any point it doesn't,
		 * then the relation set cannot be transitive, so return false
		 */
		for (String relationAb : relation) {
			/*
			 * we only need to check transitivity for pairs where the elements
			 * are not equal, since reflexive pairs will always hold transitivity
			 */
			if (relationAb.charAt(0) != relationAb.charAt(2)) {
				char a = relationAb.charAt(0);
				char b = relationAb.charAt(2);
				for (String relationBc : relation) {
					if (relationBc.charAt(0) == b) {
						char c = relationBc.charAt(2);
						sb.append(a);
						sb.append(",");
						sb.append(c);
						String relationAc = sb.toString();
						if (!relation.contains(relationAc)) {
							sb.setLength(0);
							sb.append("For the graph to be transitive tuple: ");
							sb.append(relationAc);
							sb.append(" MUST be present");
							System.out.println(sb);
							return false;
						} else {
							sb.setLength(0);
						}
					}
				}
			}
		}

		return true;
	}

	/**
	 * This method returns a boolean based on whether the
	 * input sets are anti-symmetric
	 * TODO: Complete this method (Note a set is not passed in as a parameter but a
	 * list)
	 * 
	 * @param set
	 *            A list of TwitterHandles
	 * @param relation
	 *            A relation between the TwitterHandles
	 * @return true if the set and relation are anti-symmetric
	 */
	public boolean isEquivalence(List<String> set, List<String> relation) {
		return isReflexive(set, relation) && isSymmetric(relation) && isTransitive(relation);
	}

	/**
	 * This method returns a List of the equivalence class
	 * 
	 * If the method can not find the equivalence class, then
	 * The following must be printed to the console:
	 * "Can't compute equivalence class as this is not an equivalence relation"
	 * 
	 * TODO: Complete this method (Note a set is not passed in as a parameter but a
	 * list)
	 * 
	 * @param node
	 *            A "TwitterHandle" in the graph
	 * @param set
	 *            A list of TwitterHandles
	 * @param relation
	 *            A relation between the TwitterHandles
	 * @return List that is the equivalence class
	 */
	public List<String> computeEquivalence(String node, List<String> set, List<String> relation) {
		// check for equivalence relation first
		if (!isEquivalence(set, relation)) {
			System.out.println("Can't compute equivalence class as this is not an equivalence relation");
			return null;
		} else {
			// variables required to process and return equivalence class
			List<String> equivalenceClass = new ArrayList<>();
			StringBuilder sb = new StringBuilder();
			String equivalenceNode;
			/*
			 *  for each string in the relation, check if the 2nd element is
			 *  equivalent to the node passed in as input. If it is, then add
			 *  the 1st element in the string to the output list
			 */
			for (String handleRelation : relation) {
				if (handleRelation.charAt(0) == node.charAt(0)) {
					sb.append(handleRelation.charAt(2));
					equivalenceNode = sb.toString();
					equivalenceClass.add(equivalenceNode);
					sb.setLength(0);
				}
			}
			
			return equivalenceClass;
		}
	}

	/**
	 * This method returns a List nodes using
	 * the BFS (Breadth First Search) algorithm
	 * 
	 * @return List of nodes (as strings) using the BFS algorithm
	 */
	public List<Node<String>> breadthFirstSearch() {
		return breadthFirstSearch(root, false);
	}

	/**
	 * This method returns a List nodes using
	 * the BFS (Breadth First Search) algorithm
	 * 
	 * @param start
	 *            A "TwitterHandle" in the graph
	 * @return List of nodes (as strings) using the BFS algorithm
	 */
	public List<Node<String>> breadthFirstSearch(Node<String> start, boolean rooted) {// name to breadthFirstSearch
		// queue will keep track of the nodes we need to visit in FIFO order
		NodesStackAndQueue<Node<String>> queue = new NodesStackAndQueue<>();

		// list will keep track of visited nodes during the search/traversal
		List<Node<String>> visitedNodes = new ArrayList<>();

		// variable to keep track of the current node during each iteration
		Node<String> currentNode;
		
		if (!rooted) {
			/*
			 * for loop will keep the BFS algorithm running for disconnected graphs,
			 * since BFS on its own will not account for exploring nodes that are
			 * unreachable from the given starting node
			 */
			for (Node<String> startNode : adjacencyMap.keySet()) {
				// initialize queue with the starting node since we need to visit it first
				queue.append(startNode);
				// if the queue is not empty, there are more unique nodes to visit so keep iterating
				while (!queue.isEmpty()) {
					currentNode = queue.pop();
					// mark the current node as visited if it hasn't been already
					if (!visitedNodes.contains(currentNode)) {
						visitedNodes.add(currentNode);
						// loop through all edges where the current node is the source node
						for (int i = 0; i < adjacencyMap.get(currentNode).size(); i++) {
							/*
							 * check for neighbours (target nodes) of the current/source node that 
							 * haven't been visited yet and append them to the queue
							 */
							if (!visitedNodes.contains(adjacencyMap.get(currentNode).get(i).getTarget())) {
								queue.append(adjacencyMap.get(currentNode).get(i).getTarget());
							}
						}
					}
				}
			}

			return visitedNodes;
		} else {
			// initialize queue with the starting node since we need to visit it first
			queue.append(start);
			// if the queue is not empty, there are more unique nodes to visit so keep iterating
			while (!queue.isEmpty()) {
				currentNode = queue.pop();
				// mark the current node as visited if it hasn't been already
				if (!visitedNodes.contains(currentNode)) {
					visitedNodes.add(currentNode);
					// loop through all edges where the current node is the source node
					for (int i = 0; i < adjacencyMap.get(currentNode).size(); i++) {
						/*
						 * check for neighbours (target nodes) of the current/source node that 
						 * haven't been visited yet and append them to the queue
						 */
						if (!visitedNodes.contains(adjacencyMap.get(currentNode).get(i).getTarget())) {
							queue.append(adjacencyMap.get(currentNode).get(i).getTarget());
						}
					}
				}
			}
			
			return visitedNodes;
		}
	}

	/**
	 * This method returns a List nodes using
	 * the DFS (Depth First Search) algorithm
	 * 
	 * @return List of nodes (as strings) using the DFS algorithm
	 */
	public List<Node<String>> depthFirstSearch() {
		return depthFirstSearch(root, false);
	}

	/**
	 * This method returns a List nodes using
	 * the DFS (Depth First Search) algorithm
	 * 
	 * @param start
	 *            A "TwitterHandle" in the graph
	 * @return List of nodes (as strings) using the DFS algorithm
	 */
	public List<Node<String>> depthFirstSearch(Node<String> start, boolean rooted) {
		// stack will keep track of the nodes we need to visit in LIFO order
		NodesStackAndQueue<Node<String>> stack = new NodesStackAndQueue<>();

		// list will keep track of visited nodes during the search/traversal
		List<Node<String>> visitedNodes = new ArrayList<>();

		// variable to keep track of the current node during each iteration
		Node<String> currentNode;
		
		if (!rooted) {
			/*
			 * for loop will keep the DFS algorithm running for disconnected graphs,
			 * since DFS on its own will not account for exploring nodes that are
			 * unreachable from the given starting node
			 */
			for (Node<String> startNode : adjacencyMap.keySet()) {
				// initialize stack with the starting node since we need to visit it first
				stack.push(startNode);
				// if the stack is not empty, there are more unique nodes to visit so keep iterating
				while (!stack.isEmpty()) {
					currentNode = stack.pop();
					// mark the current node as visited if it hasn't been already
					if (!visitedNodes.contains(currentNode)) {
						visitedNodes.add(currentNode);
						// loop through all edges where the current node is the source node
						for (int i = 0; i < adjacencyMap.get(currentNode).size(); i++) {
							/*
							 * check for neighbours (target nodes) of the current/source node that 
							 * haven't been visited yet and push them to the stack
							 */
							if (!visitedNodes.contains(adjacencyMap.get(currentNode).get(i).getTarget())) {
								stack.push(adjacencyMap.get(currentNode).get(i).getTarget());
							}
						}
					}
				}
			}

			return visitedNodes;
		} else {
			// initialize stack with the starting node since we need to visit it first
			stack.push(start);
			// if the stack is not empty, there are more unique nodes to visit so keep iterating
			while (!stack.isEmpty()) {
				currentNode = stack.pop();
				// mark the current node as visited if it hasn't been already
				if (!visitedNodes.contains(currentNode)) {
					visitedNodes.add(currentNode);
					// loop through all edges where the current node is the source node
					for (int i = 0; i < adjacencyMap.get(currentNode).size(); i++) {
						/*
						 * check for neighbours (target nodes) of the current/source node that 
						 * haven't been visited yet and push them to the stack
						 */
						if (!visitedNodes.contains(adjacencyMap.get(currentNode).get(i).getTarget())) {
							stack.push(adjacencyMap.get(currentNode).get(i).getTarget());
						}
					}
				}
			}
			
			return visitedNodes;
		}
	}

	/**
	 * @return returns the set of all nodes in the graph
	 */
	public Set<Node<String>> getAllNodes() {

		Set<Node<String>> out = new HashSet<>();
		out.addAll(adjacencyMap.keySet());
		for (Node<String> n : adjacencyMap.keySet()) {
			LinkedList<Edge<Node<String>>> list = adjacencyMap.get(n);
			for (int i = 0; i < list.size(); i++) {
				out.add(list.get(i).getSource());
				out.add(list.get(i).getTarget());
			}
		}
		return out;
	}

	/**
	 * @return returns the set of all edges in the graph
	 */
	protected Set<Edge<Node<String>>> getAllEdges() {
		Set<Edge<Node<String>>> out = new HashSet<>();
		for (Node<String> n : adjacencyMap.keySet()) {
			LinkedList<Edge<Node<String>>> list = adjacencyMap.get(n);
			for (int i = 0; i < list.size(); i++) {
				out.add(list.get(i));
			}
		}
		return out;
	}

	/**
	 * @return returns the set of twitter handles in the graph
	 */
	public Set<TwitterHandle> getUsersFromNodes() {
		Set<TwitterHandle> users = new LinkedHashSet<TwitterHandle>();
		for (Node<String> n : getAllNodes()) {
			users.add(new TwitterHandle(n.getValue()));
		}
		return users;
	}

}
