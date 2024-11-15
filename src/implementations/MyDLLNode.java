package implementations;

public class MyDLLNode<E>
{
	private E element;
	private MyDLLNode<E> prev, next;
	
	public MyDLLNode (E elem, MyDLLNode<E> prev, MyDLLNode<E> next) {
		this.element = elem;
		this.prev = prev;
		this.next = next;
	}
	
	public MyDLLNode(E elem) {
		this.element = elem;
	}

	/**
	 * @return the element
	 */
	public E getElement()
	{
		return element;
	}

	/**
	 * @return the prev
	 */
	public MyDLLNode<E> getPrev()
	{
		return prev;
	}

	/**
	 * @return the next
	 */
	public MyDLLNode<E> getNext()
	{
		return next;
	}

	/**
	 * @param element the element to set
	 */
	public void setElement(E element)
	{
		this.element = element;
	}

	/**
	 * @param prev the prev to set
	 */
	public void setPrev(MyDLLNode<E> prev)
	{
		this.prev = prev;
	}

	/**
	 * @param next the next to set
	 */
	public void setNext(MyDLLNode<E> next)
	{
		this.next = next;
	}

	
	
	

}
