package implementations;

import java.util.NoSuchElementException;

import utilities.Iterator;
import utilities.ListADT;

public class MyDLL<E> implements ListADT<E>
{
	
	private MyDLLNode<E> head;
	private MyDLLNode<E> tail; 
	private int size;
	
	public MyDLL()
	{
		this.head = null;
		this.tail = null;
		this.size = 0;
	}
	
	@Override
	public boolean isEmpty()
	{
		return size == 0;
	}
	
	@Override
	public int size()
	{
		return size;
	}

	@Override
	public void clear()
	{
		head = null;
		tail = null;
		size = 0;
		
	}

	@Override
	public boolean add(int index, E toAdd) throws NullPointerException, IndexOutOfBoundsException 
	{
	   if (toAdd == null) 
	   {
	       throw new NullPointerException("Cannot add null elements to the list.");
	   }
	   if (index < 0 || index > size) 
	   {
	       throw new IndexOutOfBoundsException("Index out of bounds.");
	   }

	   MyDLLNode<E> newNode = new MyDLLNode<>(toAdd);
	   if (index == 0) 
	   {
	       if (head == null) 
	       {
	           head = tail = newNode;
	       } else 
	       {
	           newNode.setNext(head);
	           head.setPrev(newNode);
	           head = newNode;
	       }
	   } else if (index == size) 
	   {
	       tail.setNext(newNode);
	       newNode.setPrev(tail);
	       tail = newNode;
	   } else 
	   {
	       MyDLLNode<E> current = head;
	       for (int i = 0; i < index - 1; i++) 
	       {
	           current = current.getNext();
	       }
	       newNode.setNext(current.getNext());
	       newNode.setPrev(current);
	       current.getNext().setPrev(newNode);
	       current.setNext(newNode);
	   }
	   
	   size++;
	   return true;
	}

	@Override
	public boolean add(E toAdd) throws NullPointerException
	{
		if (toAdd == null) 
		{
			throw new NullPointerException("Cannot add null elements to the list.");
		}
		
		MyDLLNode<E> newNode = new MyDLLNode<>(toAdd);
		if (head == null)
		{
			head = tail = newNode;
		} else 
		{
			tail.setNext(newNode);
			newNode.setPrev(tail);
			tail = newNode;
		}
		size++;
		return true;
	}

	@Override
	public boolean addAll(ListADT<? extends E> toAdd) throws NullPointerException 
	{
	   if (toAdd == null) 
	   {
	       throw new NullPointerException("Cannot add elements from a null list.");
	   }
	   if (toAdd.isEmpty()) 
	   {
	       return false;
	   }

	   Iterator<? extends E> iterator = toAdd.iterator();
	   boolean modified = false;
	   while (iterator.hasNext()) 
	   {
	       E element = iterator.next();
	       this.add(element);
	       modified = true;
	   }
	   return modified;
	}

	@Override
	public E get(int index) throws IndexOutOfBoundsException 
	{
	   if (index < 0 || index >= size) 
	   {
	       throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
	   }
	   MyDLLNode<E> currentNode;
	   if (index < size / 2) {
	       currentNode = head;
	       for (int i = 0; i < index; i++) 
	       {
	           currentNode = currentNode.getNext();
	       }
	   } else {
	       currentNode = tail;
	       for (int i = size - 1; i > index; i--) 
	       {
	           currentNode = currentNode.getPrev();
	       }
	   }
	   return currentNode.getElement();
	}


	@Override
	public E remove(int index) throws IndexOutOfBoundsException 
	{
	   if (index < 0 || index >= size) 
	   {
	       throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
	   }

	   MyDLLNode<E> nodeToRemove;
	   if (index < size / 2) {
	       nodeToRemove = head;
	       for (int i = 0; i < index; i++) 
	       {
	           nodeToRemove = nodeToRemove.getNext();
	       }
	   } else 
	   {
	       nodeToRemove = tail;
	       for (int i = size - 1; i > index; i--) 
	       {
	           nodeToRemove = nodeToRemove.getPrev();
	       }
	   }

	   E removedElement = nodeToRemove.getElement();
	   if (nodeToRemove.getPrev() != null) 
	   {
	       nodeToRemove.getPrev().setNext(nodeToRemove.getNext());
	   } else 
	   {
	       head = nodeToRemove.getNext();
	   }

	   if (nodeToRemove.getNext() != null) 
	   {
	       nodeToRemove.getNext().setPrev(nodeToRemove.getPrev());
	   } else 
	   {
	       tail = nodeToRemove.getPrev();
	   }
	   size--;
	   return removedElement;
	}

	@Override
	public E remove(E toRemove) throws NullPointerException 
	{
	   if (toRemove == null) 
	   {
	       throw new NullPointerException("Cannot remove null elements from the list");
	   }

	   MyDLLNode<E> current = head;
	   while (current != null) 
	   {
	       if (current.getElement().equals(toRemove)) 
	       {
	           E removedElement = current.getElement();

	           if (current.getPrev() != null) 
	           {
	               current.getPrev().setNext(current.getNext());
	           } else 
	           {
	               head = current.getNext();
	           }

	           if (current.getNext() != null) 
	           {
	               current.getNext().setPrev(current.getPrev());
	           } else 
	           {
	               tail = current.getPrev();
	           }
	           size--;
	           return removedElement;
	       }
	       current = current.getNext();
	   }
	   return null;
	}

	@Override
	public E set(int index, E toChange) throws NullPointerException, IndexOutOfBoundsException 
	{
	   if (toChange == null) 
	   {
	       throw new NullPointerException("Cannot set a null element in the list");
	   }
	   if (index < 0 || index >= size) 
	   {
	       throw new IndexOutOfBoundsException("Index out of bounds");
	   }

	   MyDLLNode<E> current = head;
	   for (int i = 0; i < index; i++) 
	   {
	       current = current.getNext();
	   }
	   E previousElement = current.getElement();
	   current.setElement(toChange);
	   return previousElement;
	}


	@Override
	public boolean contains(E toFind) throws NullPointerException 
	{
	   if (toFind == null) 
	   {
	       throw new NullPointerException("Cannot search for a null element in the list");
	   }

	   MyDLLNode<E> current = head;
	   while (current != null) 
	   {
	       if (current.getElement().equals(toFind)) 
	       {
	           return true;
	       }
	       current = current.getNext();
	   }
	   return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E[] toArray(E[] toHold) throws NullPointerException 
	{
	   if (toHold == null) 
	   {
	       throw new NullPointerException("Cannot create an array of null");
	   }
	   if (toHold.length < size) 
	   {
	       toHold = (E[]) java.lang.reflect.Array.newInstance(toHold.getClass().getComponentType(), size);
	   }

	   MyDLLNode<E> current = head;
	   for (int i = 0; i < size; i++) 
	   {
	       toHold[i] = current.getElement();
	       current = current.getNext();
	   }
	   if (toHold.length > size) 
	   {
	       toHold[size] = null;
	   }
	   return toHold;
	}


	@Override
	public Object[] toArray() 
	{
	   Object[] objArray = new Object[size];
	   MyDLLNode<E> current = head;

	   for (int i = 0; i < size; i++) 
	   {
	       objArray[i] = current.getElement();
	       current = current.getNext();
	   }
	   return objArray;
	}

	@Override
	public Iterator<E> iterator() 
	{
	   return new MyDLLIterator();
	}

	private class MyDLLIterator implements Iterator<E> 
	{
	   private MyDLLNode<E> current;

	   public MyDLLIterator() 
	   {
	       this.current = head;
	   }

	   @Override
	   public boolean hasNext() 
	   {
	       return current != null;
	   }

	   @Override
	   public E next() throws NoSuchElementException
	   {
	       if (!hasNext()) 
	       {
	           throw new NoSuchElementException();
	       }
	       E element = current.getElement();
	       current = current.getNext();
	       return element;
	   }
	}

	

}
