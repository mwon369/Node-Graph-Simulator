package nz.ac.auckland.se281.a4.ds;

import java.util.NoSuchElementException;
//*******************************
//YOU SHOUD MODIFY THE SPECIFIED 
//METHODS  OF THIS CLASS
//HELPER METHODS CAN BE ADDED
//REQUIRED LIBRARIES ARE ALREADY 
//IMPORTED -- DON'T ADD MORE
//THIS CLASS ALSO HAS TO BE MADE 
//GENERIC
//*******************************

/**
 * The Linked List Class Has only one head pointer to the start node (head)
 * Nodes are indexed starting from 0. The list goes from 0 to size-1.
 *
 * @author Partha Roop
 */
public class LinkedList<T> {
	// the head of the linked list
	private Node<T> head;

	/**
	 * Constructor for LinkedList
	 */
	public LinkedList() {
		head = null;
	}

	/**
	 * This method returns a reference to a node whose position is at pos
	 * TODO: Complete this method
	 * 
	 * @param pos:
	 *            an integer specifying the position of the node to be located
	 * @return Node: the reference to the Node at position pos
	 * @throws InvalidPositionException
	 *             if position is less than 0 or greater than
	 *             size-1
	 * @throws NoSuchElementException
	 *             if the element does not exist in the
	 *             LinkedList
	 */
	private Node<T> locateNode(int pos) throws InvalidPositionException, NoSuchElementException {
		// throw exception for invalid indices
		if (pos < 0 || pos > this.size() - 1) {
			throw new InvalidPositionException();
		}
		
		// account for the position being the head node index
		if (pos == 0) {
			return head;
		}
		
		// variables that will be used to traverse the list & keep track of indices
		Node<T> temp = head;
		int index = 0;
		
		/*
		 *  loop through the nodes in the list until we reach the specified index
		 *  and return the node reference at that index
		 */
		while (index < pos) {
			temp = temp.getNext();
			index++;
		}
		
		return temp;
	}

	/**
	 * This method adds a node with specified data as the start node of the list
	 * TODO: Complete this method
	 *
	 * @param element
	 *            a parameter, which is the value of the node to be prepended
	 */
	public void prepend(T element) {
		// prepended node points to the old head and becomes the new head
		Node<T> n = new Node<T>(element);
		n.setNext(head);
		head = n;
	}

	/**
	 * This method adds a node with specified data as the end node of the list
	 * TODO: Complete this method
	 *
	 * @param element
	 *            a parameter, which is the value of the node to be appended
	 */

	// Note this method has been refactored using the helper methods
	// I will do this as a small ACP exercise in class
	public void append(T element) {
		Node<T> t = new Node<T>(element);
		
		// if the linked list is empty, the newly appended node is now the head.
		if (head == null) {
			head = t;
			return;
		}
		
		/*
		 * if the linked list is not empty, then loop through all nodes
		 * until we find the tail node and make the current tail node
		 * point to the newly appended node
		 */
		Node<T> temp = head;
		while (temp.getNext() != null) {
			temp = temp.getNext();
		}
		
		temp.setNext(t);
	}

	/**
	 * This method gets the value of a node at a given position
	 * TODO: Complete this method
	 *
	 * @param pos
	 *            an integer, which is the position
	 * @return the value at the position pos
	 * @throws InvalidPositionException
	 *             if position is less than 0 or greater than
	 *             size-1
	 */
	public T get(int pos) throws InvalidPositionException {
		// find the node reference at the specified pos and return its value
		Node<T> node = locateNode(pos);
		return node.getValue();
	}

	/**
	 * This method adds an node at a given position in the List
	 * TODO: Complete this method
	 * 
	 * @param pos:
	 *            an integer, which is the position
	 * @param element:
	 *            the element to insert
	 * @throws InvalidPositionException
	 *             if position is less than 0 or greater than
	 *             size-1
	 */
	public void insert(int pos, T element) throws InvalidPositionException {
		// throw exception for invalid indices
		if (pos < 0 || pos > this.size()) {
			throw new InvalidPositionException();
		}
		
		/*
		 *  inserting at the index of the head is the same as prepending
		 *  and inserting at the index after the tail is the same as appending
		 */
		if (pos == 0) {
			this.prepend(element);
			return;
		} else if (pos == this.size()) {
			this.append(element);
			return;
		}
		
		// initialize the variables that will be used to make the insertion
		Node<T> insertedNode = new Node<T>(element);
		// variables that will be used to traverse the list & keep track of indices
		Node<T> temp = head;
		int index = 0;
		
		// find the node at the index before the specified insert position
		while (index < pos - 1) {
			temp = temp.getNext();
			index++;
		}
		
		// change the pointers to insert the new node
		insertedNode.setNext(temp.getNext());
		temp.setNext(insertedNode);
	}

	/**
	 * This method removes an node at a given position
	 * TODO: Complete this method
	 *
	 * @param pos:
	 *            an integer, which is the position
	 */
	public void remove(int pos) throws InvalidPositionException {
		// throw exception for invalid indices
		if (pos < 0 || pos > this.size() - 1) {
			throw new InvalidPositionException();
		}
		
		// removing at the start of the list is the same as
		if (pos == 0) {
			head = head.getNext();
			return;
		}
		
		// variables that will be used to traverse the list & keep track of indices
		Node<T> temp = head;
		int index = 0;
		
		// find the node at the index before the specified insert position
		while (index < pos - 1) {
			temp = temp.getNext();
			index++;
		}
		
		// change the pointers to remove the specified node
		temp.setNext(temp.getNext().getNext());
	}

	/**
	 * This method returns the size of the Linked list
	 * TODO: Complete this method
	 *
	 * @return the size of the list
	 */
	public int size() {
		int count = 0;
		Node<T> temp = head;
		
		// loop and count each node until we find null indicating the end of the list
		while (temp != null) {
			temp = temp.getNext();
			count++;
		}
		
		return count;
	}

	/**
	 * This method is used for printing the data in the list from head till the last
	 * node
	 *
	 */
	public void print() {
		Node<T> node = head;
		while (node != null) {
			System.out.println(node);
			node = node.getNext();
		}
	}
}