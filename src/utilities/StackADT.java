package utilities;

import java.util.NoSuchElementException;

public interface StackADT<T>
{
	
	/**
	 * Adds the specific element to the top of the stack
	 * @param item the element to add
	 */
	void push (T item); 
	
	/**
	 * removes and returned the top element from the stack
	 * @return the top element
	 * @throws NoSuchElementException if the stack is empty
	 */
	T pop() throws NoSuchElementException;
	
	/**
	 * returns the top element of the stack with our removing or changing
	 * @return the top element of the stack
	 * @throws NoSuchElementException if the stack is empty
	 */
	T peek() throws NoSuchElementException;

	/**
	 * returns the number of elements in the stack
	 * @return the number of elements in the stack
	 */
	int size();
	
	/**
	 * returns true if the stack has no elements
	 * @return true if the stack is empty, false if anything else
	 */
	boolean isEmpty();
}
