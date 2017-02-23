package com.mac.nytimes.pojo;

/** Basic Queue
 * Created by u1d090 on 2/20/2017.
 */

public class AceQueue {

	private class Node {

		private int data;
		private Node next;

		Node(int data) {
			this.data = data;
		}
	}

	private Node head;  // remove from here
	private Node tail;  // add from here

	public void add(int data) {
		Node current = new Node(data);
		if (tail != null) {
			tail.next = current;
		}
		tail = current;
		if (head == null) {
			head = current;
		}

	}

	public boolean isEmpty() {
		return head == null;
	}

	public int peek() {
		return head.data;
	}

	public int remove() {
		int data = head.data;
		head = head.next;
		if (head == null) {
			tail = null;
		}
		return data;
	}

}
