/**
 * Implementation of StackADT using MyArrayList.
 */

package implementations;

import java.util.EmptyStackException;
import java.util.NoSuchElementException;
import utilities.Iterator;
import utilities.StackADT;

public class MyStack<E> implements StackADT<E> {
    private MyArrayList<E> list;
    
    public MyStack() {
        list = new MyArrayList<>();
    }
    
    @Override
    public void push(E toAdd) throws NullPointerException {
        if(toAdd == null) {
            throw new NullPointerException();
        }
        list.add(toAdd);
    }
    
    @Override
    public E pop() throws EmptyStackException {
        if(isEmpty()) {
            throw new EmptyStackException();
        }
        return list.remove(list.size() - 1);
    }
    
    @Override
    public E peek() throws EmptyStackException {
        if(isEmpty()) {
            throw new EmptyStackException();
        }
        return list.get(list.size() - 1);
    }
    
    @Override
    public void clear() {
        list.clear();
    }
    
    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[list.size()];
        for(int i = 0; i < list.size(); i++) {
            array[i] = list.get(list.size() - 1 - i);
        }
        return array;
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public E[] toArray(E[] holder) throws NullPointerException {
        if(holder == null) {
            throw new NullPointerException();
        }
        
        if(holder.length < list.size()) {
            holder = (E[]) java.lang.reflect.Array.newInstance(
                holder.getClass().getComponentType(), list.size());
        }
        
        for(int i = 0; i < list.size(); i++) {
            holder[i] = list.get(list.size() - 1 - i);
        }
        
        if(holder.length > list.size()) {
            holder[list.size()] = null;
        }
        
        return holder;
    }
    
    @Override
    public boolean contains(E toFind) throws NullPointerException {
        if(toFind == null) {
            throw new NullPointerException();
        }
        return list.contains(toFind);
    }
    
    @Override
    public int search(E toFind) {
        if(toFind == null) {
            return -1;
        }
        for(int i = list.size() - 1; i >= 0; i--) {
            if(toFind.equals(list.get(i))) {
                return list.size() - i;
            }
        }
        return -1;
    }
    
    @Override
    public Iterator<E> iterator() {
        return new StackIterator();
    }
    
    private class StackIterator implements Iterator<E> {
        private int current = list.size() - 1;
        
        @Override
        public boolean hasNext() {
            return current >= 0;
        }
        
        @Override
        public E next() throws NoSuchElementException {
            if(!hasNext()) {
                throw new NoSuchElementException();
            }
            return list.get(current--);
        }
    }
    
    @Override
    public boolean equals(StackADT<E> that) {
        if(that == null || that.size() != this.size()) {
            return false;
        }
        
        Iterator<E> thisIt = this.iterator();
        Iterator<E> thatIt = that.iterator();
        
        while(thisIt.hasNext() && thatIt.hasNext()) {
            E thisElem = thisIt.next();
            E thatElem = thatIt.next();
            if(!thisElem.equals(thatElem)) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public int size() {
        return list.size();
    }
    
    @Override
    public boolean stackOverflow() {
        return false; 
    }
}