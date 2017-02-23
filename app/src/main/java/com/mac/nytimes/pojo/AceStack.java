package com.mac.nytimes.pojo;

/**
 * Created by u1d090 on 2/20/2017.
 */

public class AceStack<T> {

	private class Node<T> {

		private T data;
		private Node next;

		Node(T data) {
			this.data = data;
		}
	}

	private int size;
	private Node<T> top;  // remove from here

	public boolean isEmpty() {
		return top == null;
	}

	public T peek() {
		return top.data;
	}

	public T pop() {
		if (top == null) {

		}
		T data = top.data;
		top = top.next;
		size--;
		return data;
	}

	public void printStack() {
		System.out.print("Stack: ");

		if (size == 0)
			System.out.print("Empty !");
		else
			for (Node<T> temp = top; temp != null; temp = temp.next)
				System.out.printf("%s ", temp.data);

		System.out.printf("\n");
	}

	public void push(T data) {
		Node<T> current = new Node(data);
		if (top != null) {
			current.next = top;
			top = current;
		} else {
			top = current;
		}

		size++;

	}

	public int size() {
		return size;
	}

}
