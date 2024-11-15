package implementations;

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
	}

	@Override
	public int size()
	{
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public void clear()
	{
		// TODO Auto-generated method stub
		return 
		
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
		return false;
	}

	@Override
	public boolean addAll(ListADT<? extends E> toAdd) throws NullPointerException
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E get(int index) throws IndexOutOfBoundsException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E remove(int index) throws IndexOutOfBoundsException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E remove(E toRemove) throws NullPointerException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E set(int index, E toChange) throws NullPointerException, IndexOutOfBoundsException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmpty()
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(E toFind) throws NullPointerException
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E[] toArray(E[] toHold) throws NullPointerException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<E> iterator()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
