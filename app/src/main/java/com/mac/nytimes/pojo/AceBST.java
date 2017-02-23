package com.mac.nytimes.pojo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

import android.util.Log;

/**
 * Created by u1d090 on 2/21/2017.
 */

public class AceBST<Key extends Comparable<Key>, Value> {

	private class Node {

		private Key key;           // sorted by key
		private Node left, right;  // left and right subtrees
		private int size;          // number of nodes in subtree
		private Value val;         // associated data

		public Node(Key key, Value val, int size) {
			this.key = key;
			this.val = val;
			this.size = size;
		}
	}

	private static String BSTTAG = "AceBST";
	private Node root;

	/**
	 * Initializes an empty symbol table.
	 */
	public AceBST() {
	}

	public void bfsTraverse() {

		StringBuilder builder = new StringBuilder();
		int levelNodes = 0;
		Queue<Node> q = new LinkedList<Node>();
		if (root == null) {
			return;
		}
		q.add(root);
		while (!q.isEmpty()) {
			levelNodes = q.size();
			while (levelNodes > 0) {
				Node n = (Node) q.remove();
				builder.append(" " + n.val);
				if (n.left != null) {
					q.add(n.left);
				}
				if (n.right != null) {
					q.add(n.right);
				}
				levelNodes--;
			}
			builder.append("\n");
			String str = builder.toString();
			Log.v(BSTTAG, str);
			builder.delete(0, str.length());
		}

	}

	public void bfsTraverseWithSprialPattern() {

		ArrayList<Integer> arrList = new ArrayList();
		boolean evenLevel = false;
		StringBuilder builder = new StringBuilder();
		int levelNodes = 0;
		Queue<Node> q = new LinkedList<Node>();
		if (root == null) {
			return;
		}
		q.add(root);
		while (!q.isEmpty()) {
			levelNodes = q.size();
			arrList.clear();
			while (levelNodes > 0) {
				Node n = (Node) q.remove();
				Integer value = (Integer) n.val;
				arrList.add(value);
				if (n.left != null) {
					q.add(n.left);
				}
				if (n.right != null) {
					q.add(n.right);
				}
				levelNodes--;
			}

			if (evenLevel) {
				Log.v(BSTTAG, arrList.toString());
				evenLevel = !evenLevel;
			} else {
				evenLevel = !evenLevel;
				Collections.reverse(arrList);
				Log.v(BSTTAG, arrList.toString());
			}

		}

	}

	/*************************************************************************
	 *  Check integrity of BST data structure.
	 ***************************************************************************/
	private boolean check() {
	/*	if (!isBST()) StdOut.println("Not in symmetric order");
		if (!isSizeConsistent()) StdOut.println("Subtree counts not consistent");
		if (!isRankConsistent()) StdOut.println("Ranks not consistent");
		*/
		return isBST() && isSizeConsistent() && isRankConsistent();
	}

	/**
	 * Does this symbol table contain the given key?
	 *
	 * @param  key the key
	 * @return {@code true} if this symbol table contains {@code key} and
	 *         {@code false} otherwise
	 * @throws IllegalArgumentException if {@code key} is {@code null}
	 */
	public boolean contains(Key key) {
		if (key == null) throw new IllegalArgumentException("argument to contains() is null");
		return get(key) != null;
	}

	/* Removes the specified key and its associated value from this symbol table
			 * (if the key is in this symbol table).
	     *
	     * @param  key the key
	     * @throws IllegalArgumentException if {@code key} is {@code null}
	     */
	public void delete(Key key) {
		if (key == null) {
			throw new IllegalArgumentException("argument to delete() is null");
		}
		root = delete(root, key);
		//assert check();
	}

	public Node delete(Node x, Key key) {
		if (x == null) {
			return null;
		}

		int cmp = key.compareTo(x.key);
		if (cmp < 0) {
			return delete(x.left, key);
		} else if (cmp > 0) {
			return delete(x.right, key);
		} else {
			if (x.right == null) {
				return x.left;
			}

			if (x.left == null) {
				return x.right;
			}

			Node tmp = x;
			x = min(tmp.right);
			x.right = deleteMin(tmp.right);
			x.left = tmp.left;
		}

		x.size = size(x.left) + size(x.right) + 1;
		return x;
	}

	/**
	 * Removes the smallest key and associated value from the symbol table.
	 *
	 * @throws NoSuchElementException if the symbol table is empty
	 */
	public void deleteMin() {
		if (isEmpty()) throw new NoSuchElementException("Symbol table underflow");
		root = deleteMin(root);
		assert check();
	}

	private Node deleteMin(Node x) {
		if (x.left == null) return x.right;
		x.left = deleteMin(x.left);
		x.size = size(x.left) + size(x.right) + 1;
		return x;
	}

	public String displayNodeValueAtDistance(int distance) {
		if (root != null) {
			return displayNodeValueAtDistance(root, distance);
		}
		return "";
	}

	private String displayNodeValueAtDistance(Node root, int distance) {
		if (root != null) {
			displayNodeValueAtDistance(root.left, --distance);
			displayNodeValueAtDistance(root.right, distance);
			return ("Venky:" + root.val);
		}
		return "";
	}

	private Value get(Node x, Key key) {
		if (x == null) return null;
		int cmp = key.compareTo(x.key);
		if (cmp < 0) return get(x.left, key);
		else if (cmp > 0) return get(x.right, key);
		else return x.val;
	}

	/**
	 * Returns the value associated with the given key.
	 *
	 * @param  key the key
	 * @return the value associated with the given key if the key is in the symbol table
	 *         and {@code null} if the key is not in the symbol table
	 * @throws IllegalArgumentException if {@code key} is {@code null}
	 */
	public Value get(Key key) {
		return get(root, key);
	}

	/**
	 * Returns the height of the BST (for debugging).
	 *
	 * @return the height of the BST (a 1-node tree has height 0)
	 */
	public int height() {
		return height(root);
	}

	private int height(Node x) {
		if (x == null) return -1;
		return 1 + Math.max(height(x.left), height(x.right));
	}

	// does this binary tree satisfy symmetric order?
	// Note: this test also ensures that data structure is a binary tree since order is strict
	private boolean isBST() {
		return isBST(root, null, null);
	}

	// is the tree rooted at x a BST with all keys strictly between min and max
	// (if min or max is null, treat as empty constraint)
	// Credit: Bob Dondero's elegant solution
	private boolean isBST(Node x, Key min, Key max) {
		if (x == null) return true;
		if (min != null && x.key.compareTo(min) <= 0) return false;
		if (max != null && x.key.compareTo(max) >= 0) return false;
		return isBST(x.left, min, x.key) && isBST(x.right, x.key, max);
	}

	/**
	 * Returns true if this symbol table is empty.
	 * @return {@code true} if this symbol table is empty; {@code false} otherwise
	 */
	public boolean isEmpty() {
		return size() == 0;
	}

	// check that ranks are consistent
	private boolean isRankConsistent() {
		for (int i = 0; i < size(); i++)
			if (i != rank(select(i))) return false;
		for (Key key : keys())
			if (key.compareTo(select(rank(key))) != 0) return false;
		return true;
	}

	// are the size fields correct?
	private boolean isSizeConsistent() {
		return isSizeConsistent(root);
	}

	private boolean isSizeConsistent(Node x) {
		if (x == null) return true;
		if (x.size != size(x.left) + size(x.right) + 1) return false;
		return isSizeConsistent(x.left) && isSizeConsistent(x.right);
	}

	/**
	 * Returns all keys in the symbol table as an {@code Iterable}.
	 * To iterate over all of the keys in the symbol table named {@code st},
	 * use the foreach notation: {@code for (Key key : st.keys())}.
	 *
	 * @return all keys in the symbol table
	 */
	public Iterable<Key> keys() {
		return keys(min(), max());
	}

	/**
	 * Returns all keys in the symbol table in the given range,
	 * as an {@code Iterable}.
	 *
	 * @param  lo minimum endpoint
	 * @param  hi maximum endpoint
	 * @return all keys in the symbol table between {@code lo}
	 *         (inclusive) and {@code hi} (inclusive)
	 * @throws IllegalArgumentException if either {@code lo} or {@code hi}
	 *         is {@code null}
	 */
	public Iterable<Key> keys(Key lo, Key hi) {
		if (lo == null) throw new IllegalArgumentException("first argument to keys() is null");
		if (hi == null) throw new IllegalArgumentException("second argument to keys() is null");

		BstQueue<Key> queue = new BstQueue<Key>();
		keys(root, queue, lo, hi);
		return queue;
	}

	private void keys(Node x, BstQueue<Key> queue, Key lo, Key hi) {
		if (x == null) return;
		int cmplo = lo.compareTo(x.key);
		int cmphi = hi.compareTo(x.key);
		if (cmplo < 0) keys(x.left, queue, lo, hi);
		if (cmplo <= 0 && cmphi >= 0) queue.enqueue(x.key);
		if (cmphi > 0) keys(x.right, queue, lo, hi);
	}

	/**
	 * Returns the keys in the BST in level order (for debugging).
	 *
	 * @return the keys in the BST in level order traversal
	 */
	public Iterable<Key> levelOrder() {
		BstQueue<Key> keys = new BstQueue<Key>();
		BstQueue<Node> queue = new BstQueue<Node>();
		queue.enqueue(root);
		while (!queue.isEmpty()) {
			Node x = queue.dequeue();
			if (x == null) continue;
			keys.enqueue(x.key);
			queue.enqueue(x.left);
			queue.enqueue(x.right);
		}
		return keys;
	}

	/**
	 * Returns the largest key in the symbol table.
	 *
	 * @return the largest key in the symbol table
	 * @throws NoSuchElementException if the symbol table is empty
	 */
	public Key max() {
		if (isEmpty()) throw new NoSuchElementException("called max() with empty symbol table");
		return max(root).key;
	}

	private Node max(Node x) {
		if (x.right == null) return x;
		else return max(x.right);
	}

	/**
	 * Returns the smallest key in the symbol table.
	 *
	 * @return the smallest key in the symbol table
	 * @throws NoSuchElementException if the symbol table is empty
	 */
	public Key min() {
		if (isEmpty()) throw new NoSuchElementException("called min() with empty symbol table");
		return min(root).key;
	}

	private Node min(Node x) {
		if (x.left == null) return x;
		else return min(x.left);
	}

	public Node put(Node current, Key key, Value val) {
		if (current == null) {
			return new Node(key, val, 1);
		}
		int cmp = key.compareTo(current.key);
		if (cmp < 0) { // left nodes
			current.left = put(current.left, key, val);
		} else if (cmp > 0) { // right nodes
			current.right = put(current.right, key, val);
		} else {
			current.val = val;
			current.size = 1 + size(current.left) + size(current.right);
		}
		return current;
	}

	/**
	 * Inserts the specified key-value pair into the symbol table, overwriting the old
	 * value with the new value if the symbol table already contains the specified key.
	 * Deletes the specified key (and its associated value) from this symbol table
	 * if the specified value is {@code null}.
	 *
	 * @param  key the key
	 * @param  val the value
	 * @throws IllegalArgumentException if {@code key} is {@code null}
	 */
	public void put(Key key, Value val) {
		if (key == null) {
			throw new IllegalArgumentException("first argument to put() is null");
		}
		if (val == null) {
			delete(key);
			return;
		}
		root = put(root, key, val);
		assert check();
	}

	/**
	 * Return the number of keys in the symbol table strictly less than {@code key}.
	 *
	 * @param  key the key
	 * @return the number of keys in the symbol table strictly less than {@code key}
	 * @throws IllegalArgumentException if {@code key} is {@code null}
	 */
	public int rank(Key key) {
		if (key == null) throw new IllegalArgumentException("argument to rank() is null");
		return rank(key, root);
	}

	// Number of keys in the subtree less than key.
	private int rank(Key key, Node x) {
		if (x == null) return 0;
		int cmp = key.compareTo(x.key);
		if (cmp < 0) return rank(key, x.left);
		else if (cmp > 0) return 1 + size(x.left) + rank(key, x.right);
		else return size(x.left);
	}

	/**
	 * Return the kth smallest key in the symbol table.
	 *
	 * @param  k the order statistic
	 * @return the {@code k}th smallest key in the symbol table
	 * @throws IllegalArgumentException unless {@code k} is between 0 and
	 *        <em>n</em>–1
	 */
	public Key select(int k) {
		if (k < 0 || k >= size()) {
			throw new IllegalArgumentException("called select() with invalid argument: " + k);
		}
		Node x = select(root, k);
		return x.key;
	}

	// Return key of rank k.
	private Node select(Node x, int k) {
		if (x == null) return null;
		int t = size(x.left);
		if (t > k) return select(x.left, k);
		else if (t < k) return select(x.right, k - t - 1);
		else return x;
	}

	/**
	 * Returns the number of key-value pairs in this symbol table.
	 * @return the number of key-value pairs in this symbol table
	 */
	public int size() {
		return size(root);
	}

	// return number of key-value pairs in BST rooted at x
	private int size(Node x) {
		if (x == null) return 0;
		else return x.size;
	}

}
