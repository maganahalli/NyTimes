package com.mac.nytimes.pojo;

/** Using two stacks
 * Created by u1d090 on 2/21/2017.
 */

public class MyQueue<T> {

	private AceStack<T> inputStack;      // for enqueue
	private AceStack<T> outputStack;     // for dequeue
	private int size;

	public MyQueue() {
		inputStack = new AceStack<T>();
		outputStack = new AceStack<T>();
	}

	public T dequeue() {
		// fill out all the Input if output stack is empty
		if (outputStack.isEmpty())
			while (!inputStack.isEmpty())
				outputStack.push(inputStack.pop());

		T temp = null;
		if (!outputStack.isEmpty()) {
			temp = outputStack.pop();
			size--;
		}

		return temp;
	}

	public void enqueue(T e) {
		inputStack.push(e);
		size++;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public T peek() {
		return outputStack.peek();
	}

}
