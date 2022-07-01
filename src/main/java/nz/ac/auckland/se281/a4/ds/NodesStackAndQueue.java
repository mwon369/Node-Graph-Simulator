package nz.ac.auckland.se281.a4.ds;

import java.util.EmptyStackException;
//*******************************
//YOU SHOUD MODIFY THE SPECIFIED 
//METHODS  OF THIS CLASS
//HELPER METHODS CAN BE ADDED
//REQUIRED LIBRARIES ARE ALREADY 
//IMPORTED -- DON'T ADD MORE
//*******************************

public class NodesStackAndQueue<T> {

	private Node<T> head; // You should use this variable in your methods

	public NodesStackAndQueue() {
		head = null;
	}

	/**
	 * Checks if the stack / queue is empty
	 * 
	 * @return true if the stack / queue is empty
	 */
	public boolean isEmpty() {
		
		/* 
		 * the head = null indicates that there are no elements in the stack/queue
		 * so return true if that is the case, otherwise false
		 */
		if (head == null) {
			return true;
		}
		
		return false;
	}

	/**
	 * Push operation refers to inserting an element in the Top of the stack.
	 * TODO: Complete this method
	 * 
	 * @param element
	 *            the element to be "pushed"
	 */
	public void push(T element) {
		Node<T> n = new Node<T>(element);
		
		// new node points to the old head and becomes the new head
		n.setNext(head);
		head = n;
	}

	/**
	 * pop an element from the top of the stack (removes and returns the tope
	 * element)
	 * TODO: Complete this method (Note: You may have to change the return type)
	 * 
	 * @return object of the top element
	 * @throws EmptyStackException
	 *             if the stack is empty
	 */
	public T pop() throws EmptyStackException {
		
		// exception handling for if we have an empty queue/stack
		if (this.isEmpty()) {
			throw new EmptyStackException();
		}
		
		// the head represents the top of the stack/front of the queue
		Node<T> popElement = head;
		
		/* 
		 * set the head to the element below/behind the top/front element
		 * since it is being removed
		 */
		head = head.getNext();
		return popElement.getValue();
	}

	/**
	 * get the element from the top of the stack without removing it
	 * TODO: Complete this method (Note: You may have to change the return type)
	 *
	 * @return the value of the top element
	 * @throws EmptyStackException
	 *             if the stack is empty
	 */
	public T peek() throws EmptyStackException {
		
		// exception handling for if we have an empty queue/stack
		if (this.isEmpty()) {
			throw new EmptyStackException();
		}
		
		return head.getValue();
	}

	/**
	 * append an element at the end of the queue
	 * TODO: Complete this method
	 *
	 * @param element
	 *            the element to be appended
	 */
	public void append(T element) {
		Node<T> n = new Node<T>(element);
		
		/*
		 *  if the queue is empty, the element being appended will be the first element,
		 *  so it should become the head
		 */
		if (this.isEmpty()) {
			head = n;
			return;
		}
		
		/*
		 * if the queue has at least 1 element, then we will need to traverse/loop 
		 * through all nodes in the queue until we find the last node and make it point
		 * to the newly appended node; (last node is indicated if it points to null)
		 */
		Node<T> temp = head;
		while (temp.getNext() != null) {
			temp = temp.getNext();
		}
		
		temp.setNext(n);
	}
}
