/**
 * Implementation of ListADT using an array as the data structure.
 */

package implementations;

import java.util.NoSuchElementException;

import utilities.Iterator;
import utilities.ListADT;

public class MyArrayList<E> implements ListADT<E> 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	//Constants
	private final int Capacity = 10;
	private final int Multiplyer = 2;
	
	//Attributes
	private E[] array;  
	
	private int size; 
	
	public MyArrayList() 
	{
		array = (E[]) new Object[Capacity];
		size = 0;
	}

	@Override
	public int size()
	{
		return size;
	}

	@Override
	public void clear()
	{
		for(int i = 0; i < size; i++)
		{
			array[i] = null;
		}
		size = 0; 
	}

	@Override
	public boolean add(int index, E toAdd) throws NullPointerException, IndexOutOfBoundsException
	{
		if (index < 0 || index > size)
		{
			throw new IndexOutOfBoundsException();
		}
		if (toAdd == null) 
		{
			throw new NullPointerException(); 
		}
		checkCapacity();
		shiftToRight(index);
		array[index] = toAdd;
		size++;
		
		return true;
	}

	private void shiftToRight(int index)
	{
		for (int i = size; i > index; i--)
		{
			array[i] = array[i-1];
		}
		
	}

	private void checkCapacity()
	{
		if (size == array.length)
		{
			E[] newArray = (E[]) new Object[array.length * 2]; 
			for (int i =0; i < array.length; i++)
			{
				newArray[i] = array[i];
			}
			
			array = newArray; 
		}
	}

	@Override
	public boolean add(E toAdd) throws NullPointerException
	{
		if (toAdd == null) {
			throw new NullPointerException(); 
		}
		checkCapacity(); 
		array[size++] = toAdd;
		return true;
	}

	@Override
	public boolean addAll(ListADT<? extends E> toAdd) throws NullPointerException
	{
		if (toAdd == null)
		{
			throw new NullPointerException();
		}
		
		boolean modified = false;
		for(int i = 0; i < toAdd.size(); i++)
		{
			add(toAdd.get(i));
			modified = true;
		}
		return modified; 
	}

	@Override
	public E get(int index) throws IndexOutOfBoundsException
	{
		if (index < 0 || index >= size)
		{
			throw new IndexOutOfBoundsException();
		}
		return array[index];
	}

	@Override
	public E remove(int index) throws IndexOutOfBoundsException
	{
		if (index < 0 || index >= size)
		{
			throw new IndexOutOfBoundsException();
		}
		
		E element = array[index];
		for (int i = index; i < size -1; i++)
		{
			array[i] = array[i + 1];
		}
		array[size - 1] = null;
		size--;
		return element; 
	}

	@Override
	public E remove(E toRemove) throws NullPointerException
	{
		if (toRemove == null)
		{
			throw new NullPointerException();
		}
		
		for( int i =0; i < size; i++)
		{
			if(toRemove.equals(array[i]))
			{
				return remove(i);
			}
		}
		return null;
	}

	@Override
	public E set(int index, E toChange) throws NullPointerException, IndexOutOfBoundsException
	{
		if (toChange == null)
		{
			throw new NullPointerException();
		}
		if (index < 0 || index >= size)
		{
			throw new IndexOutOfBoundsException();
		}
		
		E oldElement = array[index];
		array[index] = toChange;
		return oldElement; 
	}

	@Override
	public boolean isEmpty()
	{
		return size == 0;
	}

	@Override
	public boolean contains(E toFind) throws NullPointerException
	{
		if (toFind == null)
		{
			throw new NullPointerException();
		}
		
		for (int i = 0; i < size; i++)
		{
			if (toFind.equals(array[i]))
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public E[] toArray(E[] toHold) throws NullPointerException
	{
		if (toHold == null)
		{
			throw new NullPointerException();
		}
		if (toHold.length < size) {
		    toHold = (E[]) java.lang.reflect.Array.newInstance(toHold.getClass().getComponentType(), size);
		}
		for (int i = 0; i < size; i++) {
		    toHold[i] = array[i];
		}
		return toHold;

	}

	@Override
	public Object[] toArray()
	{
		Object[] result = new Object[size];
		for (int i = 0; i < size; i++)
		{
			result[i] = array[i];
		}
		return result; 
	}

	@Override
	public Iterator<E> iterator()
	{
		return new ArrayListIterator(); 
	}
	
	private class ArrayListIterator implements Iterator<E> 
	{
		private int current = 0;
		private E[] iteratorArray;
		
		public ArrayListIterator()
		{
			iteratorArray = (E[]) new Object[size];
			for (int i =0; i < size; i++) 
			{
				iteratorArray[i] = array[i];
			}
		}
		
		@Override
		public boolean hasNext()
		{
			return current < size; 
		}
		
		@Override
        public E next() throws NoSuchElementException {
            if(!hasNext()) {
                throw new NoSuchElementException();
            }
            return iteratorArray[current++];
        }
	}

}
