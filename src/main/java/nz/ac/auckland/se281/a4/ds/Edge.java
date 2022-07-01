package nz.ac.auckland.se281.a4.ds;

import java.util.Objects;
// *******************************
// YOU CANNOT MODIFY THIS CLASS
// *******************************

public class Edge<T> {

	private T source;
	private T target;

	/**
	 * Constructor for Edge
	 * 
	 * @param source
	 *            the source of the edge
	 * @param target
	 *            the target of the edge
	 */
	public Edge(T source, T target) {
		this.source = source;
		this.target = target;
	}

	/**
	 * Constructor for Edge
	 */
	public Edge() {
		this(null, null);
	}

	/**
	 * Getter for source
	 * 
	 * @return source
	 */
	public T getSource() {
		return source;
	}

	/**
	 * Getter for target
	 * 
	 * @return target
	 */
	public T getTarget() {
		return target;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Edge{");
		sb.append("source=").append(source);
		sb.append(", target=").append(target);
		sb.append('}');
		return sb.toString();
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Edge))
			return false;
		Edge<T> edge = (Edge<T>) o;
		return Objects.equals(getSource(), edge.getSource()) && Objects.equals(getTarget(), edge.getTarget());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getSource(), getTarget());
	}
}
