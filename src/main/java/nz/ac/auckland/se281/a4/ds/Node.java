package nz.ac.auckland.se281.a4.ds;

import java.util.Objects;
// *******************************
// YOU CANNOT MODIFY THIS CLASS
// *******************************

public class Node<T> {

	private T value;
	private Node<T> next;

	/**
	 * Constructor for Node
	 * 
	 * @param v
	 *            the value of the node
	 */
	public Node(T v) {
		value = v;
		next = null;
	}

	/**
	 * Setter for next node
	 * 
	 * @param n
	 *            the next node
	 */
	public void setNext(Node<T> n) {
		next = n;
	}

	/**
	 * Getter for next node
	 * 
	 * @return next node
	 */
	public Node<T> getNext() {
		return next;
	}

	/**
	 * Getter for value
	 * 
	 * @return value
	 */
	public T getValue() {
		return value;
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Node))
			return false;

		Node<T> node = (Node<T>) o;
		return Objects.equals(value, node.value);
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Node{");
		sb.append("value='").append(value).append('\'');
		sb.append('}');
		return sb.toString();
	}
}