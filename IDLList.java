//I pledge my honor that I have abided by the Stevens Honor System - Aparajita Rana
// arana3- Aparajita Rana
package general;

import java.util.ArrayList;

public class IDLList<E> {
	private Node<E> head;
	private Node<E> tail;
	private int size;
	private ArrayList<Node<E>> indices;
	
	class Node<E>{
		public E data;
		public Node<E> next;
		public Node<E> prev;
		
		Node (E elem)
		{
			data=elem;
			next=null;
			prev=null;
		}
		
		Node (E elem, Node<E> prev, Node<E> next)
		{
			data=elem;
			this.prev=prev;
			this.next=next;
		}
}
	//creates an empty double-linked list
	public IDLList() 
	{
		indices = new ArrayList<Node<E>>(1);
		head = new Node<E>(null);
		tail = new Node<E>(null);
		head.next=tail;
		tail.prev=head;
		size=indices.size();
		
	}
	
	//that adds elem at the head
	public boolean add(E elem) 
	{
		if (size == 0) 
		{
			Node<E> one = new Node<E>(elem);
			head=one;
			tail=head;
			size++;
			indices.add(0,one);
			return true;
		} 
		
		else 
		{
			Node<E> two = new Node<E>(elem);
			two.next=head;
			head.prev=two;
			head=two;
			size++;
			indices.add(0,two);
			return true;
		}
	}

	//that adds elem at position index - it uses the index for fast access
	public boolean add(int index, E elem) 
		{
		if (index<0||index>size||elem==null) 
		{
			throw new IllegalArgumentException();
		}
		
		else if (index==size) 
		{
			append(elem);
			return true;
		}
		
		else if (index==0)
		{
			add(elem);
			return true;
		}
		
		else 
		{
			Node<E> previous = indices.get(index-1);
			Node<E> start = new Node<E>(elem, previous, previous.next);
			previous.next.prev = start;
			previous.next = start;
			indices.add(index, start);
			size++;
		}
		
		return true;
	}
	
	//adds elem as the new last element of the list
	public boolean append(E elem) 
	{
		if (elem==null) 
		{
			throw new IllegalArgumentException();
		}
		
		else if (tail == null) 
		{
			add(elem);
		}
		
		else 
		{
			Node<E> bac = tail;
			tail = new Node<E>(elem, tail, null);
			bac.next = tail;
			indices.add(tail);
			size++;
		}
		
		return true;
	}
	
	//that returns the object at position index from the head
	public E get(int index)
	{
		if(index>size) 
		{
			throw new ArrayIndexOutOfBoundsException();
		} 
		
		else
		{
			return indices.get(index).data;
		}
		
	}
	
	//that returns the object at the head
	public E getHead() 
	{
		return head.data;
	}
	
	//that returns the object at the tail.
	public E getLast() 
	{
		return tail.data;
	}
	
	//that returns the list size.
	public int size() 
	{
		return size;
	}
	
	//removes and returns the element at the head
	public E remove()
	{
		if (head == null) 
		{
			throw new IllegalArgumentException();
		}
		
		else if(head == tail) 
		{
			Node<E> temp = head;
			head=null;
			tail=null;
			size--;
			indices.clear();
			return temp.data;
		} 
		else 
		{
			Node<E> x=head;
			head=head.next;
			size--;
			indices.remove(0);
			return x.data;
		}
		}
	
	//removes and returns the element at the tail
	public E removeLast() 
	{
		if (tail == null) 
		{
			throw new NullPointerException();
		}
		
		else if (head == tail) 
		{
			Node<E> temp = tail;
			head = null;
			tail = null;
			indices.clear();
			size--;
			return temp.data;
		}
		
		else 
		{
			Node<E> temp = tail;
			tail = tail.prev;
			tail.next = null;
			indices.remove(size - 1);
			size--;
			return temp.data;
		}
	}
	
	//removes and returns the element at the index index.
	public E removeAt(int index) 
	{
		if (head == null || tail == null) 
		{
			throw new NullPointerException();
		}
		
		if (index < 0 || index >= size) 
		{
			throw new IndexOutOfBoundsException();
		}
		
		else if (index == size-1) 
		{
			E elem = removeLast();
			return elem;
		}
		
		else if (index == 0) 
		{
			E elem = remove();
			return elem;
		}
		else 
		{
			Node<E> temp = indices.get(index);
			Node<E> prev = temp.prev;
			Node<E> next = temp.next;
			prev.next = next;
			next.prev = prev;			
			indices.remove(index);
			size--;
			return temp.data;
		}
	}
	
	//that removes the first occurrence of elem in the list and returns true. Return false if elem was not in the list.
	public boolean remove(E elem) {
		if (head == null) 
		{
			throw new NullPointerException();
		}
		
		int index = 0;
		boolean check = false;
		
		while(index < size && check==false) {
			if (indices.get(index).data.equals(elem))
			{
				removeAt(index);
				check = true;
			}
			else 
			{
				index++;
			}
		}
		return check;
	}
	
	//a string representation of the list.
	public String toString() {
		String val="[";
		Node<E> curr=this.head;
		while (curr.next!=null)
		{
			val+=curr.data.toString() + ",";
			curr=curr.next;
		}
		val+=curr.data + "]" ;
		return val;
	}
	
}
