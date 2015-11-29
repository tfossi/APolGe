/**
 * TerminArray.java
 * Branch time
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.time;

import static tfossi.apolge.time.pit.ConstCPPit.*;
import static tfossi.apolge.common.constants.ConstValueExtension.*;
import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
 
import tfossi.apolge.events.Event;
import tfossi.apolge.events.IEvent;
import tfossi.apolge.time.pit.CPiT;

import org.apache.log4j.Logger;

/**
 * TODO Comment
 *
 * @author tfossi
 * @version 17.08.2014
 * @modified -
 * @since Java 1.6
 * @param <T> -
 */
public class TerminArray<T extends IEvent> extends AbstractList<IEvent>
		implements Serializable {
	{	if (LOGGER)
		System.out.println(this.getClass().getSimpleName()+" V" + serialVersionUID);
	}
	/** RESIZE_FACTOR */
	private final int RESIZE_FACTOR = 2;
	/** elementData */
	private IEvent elementData[];

//	transient private ITimesController tc;

	// Strictly speaking, we don't need to keep a handle to size,
	// as it can be calculated programmatically, but keeping it
	// makes the algorithms faster.
	/** size */
	private int size;

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public boolean add(final IEvent e) {
		
		assert e != null : "Event ist null!";
		// assert this.tc != null : "this.tc!=null";
		// assert e.getPiT() != null : "e.getPiT()!=null";
		// assert this.tc.getActualdate() != null :
		// "this.tc.getActualdate()!=null";
		// System.out.println(e.getName() + " 1 " + this.size);
		// if (before(e.getPiT(), this.tc.getActualdate()))
		// throw new Exception("Event" + NTAB
		// + e.getPiT().getDatetime(DATE | TIME | SHIFT) + NTAB
		// + "ist in der Vergangenheit!");
		// System.out.println(e.getName() + " 2 " + this.size);
		int index = -1;

		index = sort(e);
		assert index != -1;
		//
		// // System.out.println(e.getName() + " 3 "+this.size);
		this.add(index, e);
		// // System.out.println(e.getName() + " 4 "+this.size);
		// // TODO this.modCount++;
		// We have to have at least one empty space
		this.ensureCapacity(this.size + 1 + 1);
		// this.elementData[this.size] = e;
		// // tail = (tail + 1) % elementData.length;
		// // this.size++;
		//
		// // } catch (Exception e1) {
		// // e1.printStackTrace();
		// // }
		// assert this.size() == 1;
		if(LOGGER) logger.trace(Arrays.toString(this.elementData));
		return true;
	}

	@Override
	public void add(int index, IEvent e) {
		// try {
		 if(LOGGER) logger.debug(e.getName() + " nach " + index);
		// First make sure the index is valid
		if (index > this.size() || index < 0)
			throw new IndexOutOfBoundsException(NTAB+e.getName()+NTAB+index+" "+this.size());

		// if the list is full, resize it
		if (this.size() == this.elementData.length)
			resize();

		// if(LOGGER) logger.trace(e.getName() + " nach 1/" + index);
		// shift the elements starting at index to the right one position
		for (int index2 = this.size(); index2 > index; index2--)
			this.elementData[index2] = this.elementData[index2 - 1];

		// if(LOGGER) logger.trace(e.getName() + " nach 2/" + index);
		// add the new element at index
		this.elementData[index] = e;
		if(LOGGER) logger.debug((index==0 ? "- ": this.elementData[index-1]+" "+this.elementData[index-1].getPiT())+
				" -> "+e +" "+e.getPiT()+" ->"+
				(index<this.size ? this.elementData[index+1]+" "+this.elementData[index+1].getPiT():" -"));

		// if(LOGGER) logger.trace(e.getName() + " nach 3/" + index);
		// adjust the number of elements
		this.size++;
		// } catch (Exception e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// }
	}


	@Override
	public boolean removeAll(Collection <?> liste) {
		
		for(Object o : liste ){
			if(o!=null){
				for(int i = 0; i < this.size;i++){
					if(this.elementData[i].equals(o)){
						this.remove(i);
						break;
					}
				}
			}
		}
		return true;
	}
	
	@Override
	public IEvent remove(int indexIn) {
		int index = indexIn;
		if (index >= this.size || index < 0)
			throw new IndexOutOfBoundsException();

		// Save the elemet, but remove it from the list.
		IEvent temp = this.elementData[index];
//		this.elementData[index] = null;
		index++;

		// shift all subsequent elements toward the front of the list.
		while (index < this.size) {
			this.elementData[index - 1] = this.elementData[index];
			index++;
		}
		this.elementData[index] = null;

		// adjust the number of elements
		this.size--;

		// return the element that was removed
		return temp;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.AbstractList#get(int)
	 */
	@Override
	public IEvent get(int index) {
		if (index < 0 || index >= this.size)
			throw new IndexOutOfBoundsException("Index = " + index);
		return this.elementData[index];
	}

	/**
	 * Resizes the list to twice its current length.
	 */
	private void resize() {
		// Calculate the new length, which is the current length multiplied by
		// the resizing factor.
		int newLength = this.size() * this.RESIZE_FACTOR;

		// Create a new list.
		// Note: This statement will cause a compiler warning. It is a necessary
		// work-around because we cannot instantiate an array of a generic type.
		IEvent[] tempList = new Event[newLength];

		// Copy the existing elements to the new list.
		for (int index = 0; index < this.size(); index++)
			tempList[index] = this.elementData[index];

		// replace the existing array with the new one.
		this.elementData = tempList;
	}

	/**
	 * TODO Comment
	 * @param e -
	 * @return -
	 * @modified - 
	 */
	private int sort(IEvent e) {
	
		assert e!=null;
		assert e.getPiT()!=null;
		
		// Wenn noch kein Element eingetragen ist, dann kann es nur die Nummer 0 sein!
		if (this.size() == 0) return 0;
	
		CPiT pit = e.getPiT();

		if(LOGGER) logger.trace(this.size+" -> SIZE = "+this.elementData.length);

		// Gehe die Liste solange runter, bis das Vergleichselement "after" ist 
		for(int index = this.size-1; index >-1; index--){
			IEvent e2 = this.elementData[index];
			assert e2!=null;
			assert e2.getPiT()!=null;
			
			if(LOGGER)logger.debug("check: Ist Index: "+index+" PiT: "+pit+" <NACH> "+e2.getPiT());
		 
			if(index==-1){

				if(LOGGER) logger.debug("e2 "+e2+"  index="+index);
				return index;
			}
			
			assert e2 != null : e + NTAB + Arrays.toString(this.elementData)
					+ "--> " + this.elementData.length + " / " + this.size();
			if (after(pit, e2.getPiT())) {

				if(LOGGER) logger.debug(pit+" liegt NACH "+e2.getPiT());

				return index+1;
			}
		}
		// Kann dann nur noch 0 sein!
		return 0;
	}

	// We use this method to ensure that the capacity of the
	// list will suffice for the number of elements we want to
	// insert. If it is too small, we make a new, bigger array
	// and copy the old elements in.
	/**
	 * TODO Comment
	 * @param minCapacity -
	 * @modified - 
	 */
	public void ensureCapacity(int minCapacity) {
		int oldCapacity = this.elementData.length;
		if (minCapacity > oldCapacity) {
			int newCapacity = (oldCapacity * 3) / 2 + 1;
			if (newCapacity < minCapacity)
				newCapacity = minCapacity;
			Event[] newData = new Event[newCapacity];
			toArray(newData);
			this.elementData = newData;
		}
	}

	// ---- Selbstverwaltung --------------------------------------------------

	/** serialVersionUID */
	private final static long serialVersionUID = VERSION;
	/** logger */
	private final static Logger logger = Logger.getLogger(TerminArray.class
			.getPackage().getName()+".TerminArray.class");

	/**
	 * TODO Comment
	 * @modified -
	 */
	public TerminArray() {
		this(1);
	}

	/**
	 * TODO Comment
	 * @param size -
	 * @modified -
	 */
	public TerminArray(int size) {
		this.elementData = new Event[size];
	}

	/**
	 * TODO Comment
	 * @param c -
	 * @modified -
	 */
	public TerminArray(Collection<Event> c) {
		this.elementData = new Event[c.size()];
		c.toArray(this.elementData);
	}
}

//
// //
// // // @Override
// // // public boolean addAll(Collection<? extends IEvent> c) {
// // // // if (c == null)
// // // // throw new Exception("Terminliste ist null!");
// // // for (IEvent e : c) {
// // // // if (e == null)
// // // // throw new Exception("Event ist null!");
// // // // if (before(e.getPiT(), this.tc.getActualdate()))
// // // // throw new Exception("Event" + NTAB
// // // // + e.getPiT().getDatetime(DATE | TIME | SHIFT) + NTAB
// // // // + " ist in der Vergangenheit!");
// // // try {
// // // super.add(sort(e), e);
// // // } catch (Exception e1) {
// // // e1.printStackTrace();
// // // }
// // // }
// // // return false;
// // // }
// //
// // // addAll modifizieren, um Terminlisten nach
// // // Zeitpunkt der Ausführung
// // // einzusortieren.
// // @Override
// // public boolean addAll(Collection<? extends IEvent> c) {
// // assert c != null;
// // for (IEvent e : c) {
// // try {
// // e.prepare();
// // add(sort(e), e);
// // } catch (Exception e1) {
// // e1.printStackTrace();
// // }
// // }
// // return false;
// //
// // }
// //
// // public boolean remove(IEvent element) {
// // int index = 0; // Index counter
// // boolean found = false; // search flag
// // //
// // // perform a sequential search for element. When it is found, remove it
// // // and
// // // stop searching.
// // //
// // while (!found && index < size) {
// // if (this.elementData[index].equals(element)) {
// // this.elementData[index] = null;
// // found = true;
// // }
// // index++;
// // }
// //
// // // if the value was found, shift all subsequent elements toward the
// // // front of the list.
// // if (found) {
// // while (index < size) {
// // this.elementData[index - 1] = this.elementData[index];
// // index++;
// // }
// // // adjust the number of elements
// // size--;
// // }
// // return found;
// // }
// //
// // public boolean removeAll(Collection<?> c) {
// // for (Object e : c) {
// // this.remove((IEvent) e);
// // }
// // return true;
// // }
// //

//

//
// //
// // }
// //
// // // // private void writeObject(java.io.ObjectOutputStream out) throws
// // // IOException {
// // // // int expectedModCount = this.modCount;
// // // // out.defaultWriteObject();
// // // // out.writeInt(this.elementData.length);
// // // // for (int i = 0; i < this.size; i++)
// // // // out.writeObject(this.elementData[i]);
// // // // if (this.modCount != expectedModCount)
// // // // throw new ConcurrentModificationException();
// // // //
// // // // }
// // // //
// // // // private void readObject(java.io.ObjectInputStream in) throws
// // IOException,
// // // // ClassNotFoundException {
// // // // in.defaultReadObject();
// // // // this.size = in.readInt();
// // // // Object[] a = this.elementData = (IEvent[]) new Object[this.size];
// // // // for (int i = 0; i < this.size; i++)
// // // // a[i] = in.readObject();
// // // // }
// // // //
// //
// // // //
// // // // /**
// // // // * @param <T>
// // // // * @param o
// // // // * @return
// // // // * @throws Exception
// // // // */
// // // // @SuppressWarnings("unchecked")
// // // // public <T> T deepCopy(T o) throws Exception {
// // // // ByteArrayOutputStream baos = new ByteArrayOutputStream();
// // // // new ObjectOutputStream(baos).writeObject(o);
// // // //
// // // // ByteArrayInputStream bais = new
// // ByteArrayInputStream(baos.toByteArray());
// // // // Object p = new ObjectInputStream(bais).readObject();
// // // //
// // // // return (T) p;
// // // // }
// //
// // //
// // //
// // //
// // // /**
// // // * @return the elementData
// // // */
// // // public final IEvent[] getElementData() {
// // // return this.elementData;
// // // }
// // //
// // // /**
// // // * @param elementData
// // // * the elementData to set
// // // */
// // // public final void setElementData(IEvent[] elementData) {
// // // this.elementData = elementData;
// // // }
// // //
// // // /**
// // // * @return the size
// // // */
// // // public final int getSize() {
// // // return this.size;
// // // }
// // //
// // // /**
// // // * @param size
// // // * the size to set
// // // */
// // // public final void setSize(int size) {
// // // this.size = size;
// // // }
// // // }
// // // // {
// // // //
// // // // private static final long serialVersionUID = 1L;
// // // //
// // // // // add modifizieren, um Termine nach
// // // // // Zeitpunkt der Ausführung
// // // // // einzusortieren.
// // // // @Override
// // // // public boolean add(IEvent e) {
// // // // assert e != null;
// // // // int index = -1;
// // // // try {
// // // // e.prepare();
// // // // index = sort(e);
// // // // } catch (Exception e1) {
// // // // e1.printStackTrace();
// // // // }
// // // // super.add(index, e);
// // // // return true;
// // // // }
// // // //
// //
// // // //
// // // // // Sortierer.
// // // // private int sort(IEvent e) throws Exception {
// // // // assert e != null;
// // // // int index = 0;
// // // // PiT pit = e.getPiT();
// // // // for (IEvent e2 : this) {
// // // // if (before(pit, e2.getPiT()))
// // // // return index;
// // // // index++;
// // // // }
// // // // return index;
// // // // }
// // // // };
// // //
// // // //package List;
// // // //
// // // ///**
// // // // * The ListType class is a concrete generic class for storing a list
// // of
// // // objects.
// // // // */
// // // //public class ListType<E> implements GeneralList<E>
// // // //{
// // // //
// // // // //Constants for the default capacity and resizing factor.
// // // // private final int DEFAULT_CAPACITY = 10;
// // // // private final int RESIZE_FACTOR = 2;
// // // //
// // // // //Private fields
// // // // private E[] list; // The list
// // // // private int elements; // number of elements stored
// // // //
// // // // /**
// // // // * This is the default constructor that creates an empty list of the
// // // default
// // // // * capacity
// // // // */
// // // // public ListType()
// // // // {
// // // // //The following statement causes a compiler warning.
// // // // //It is necessary work-around because we cannot
// // // // // instantiate an array of a generic type.
// // // // list = (E[])(new Object[DEFAULT_CAPACITY]);
// // // // elements = 0;
// // // // }
// // // //
// // // // /**
// // // // * This constructor creates an empty list of the specified capacity
// // // // * @param capacity The initial capacity.
// // // // * @exception IllegalArgumentException if the specified capacity is
// // less
// // // than
// // // // * one.
// // // // */
// // // // public ListType(int capacity)
// // // // {
// // // // if(capacity < 1)
// // // // throw new IllegalArgumentException();
// // // // // The following statement causes a compiler warning.
// // // // // It is a necessary work-around because we cannot instantiate
// // // // // and array of generic type.
// // // // list = (E[])(new Object[capacity]);
// // // // elements = 0;
// // // // }
// // // //
// // // //
// // // // public void add(E element)
// // // // {
// // // // //If the list is full, resize it.
// // // // if(elements == list.length)
// // // // resize();
// // // //
// // // // //Add elements to the end of the list.
// // // // list[elements] = element;
// // // //
// // // // //adjust the number of elements
// // // // elements++;
// // // // }
// // // //
// // // // public void add(int index, E element)
// // // // {
// // // // //First make sure the index is valid
// // // // if(index > elements || index < 0)
// // // // throw new IndexOutOfBoundsException();
// // // //
// // // // //if the list is full, resize it
// // // // if(elements == list.length)
// // // // resize();
// // // //
// // // // //shift the elements starting at index to the right one position
// // // // for(int index2 = elements; index2 > index; index2--)
// // // // list[index2] = list[index2-1];
// // // //
// // // // //add the new element at index
// // // // list[index] = element;
// // // //
// // // // //adjust the number of elements
// // // // elements++;
// // // // }
// // // //
// // // //
// //
// // // //
// // // // public boolean contains(E element)
// // // // {
// // // // int index = 0; //Index counter
// // // // boolean found = false; //Search flag
// // // //
// // // // //step through the list. when the element is found, set found to
// // true and
// // // stop.
// // // // while(!found && index < elements)
// // // // {
// // // // if(list[index].equals(element))
// // // // found = true;
// // // // index++;
// // // // }
// // // // //return the status of the search
// // // // return found;
// // // // }
// // // //
// // // // public E get(int index)
// // // // {
// // // // if(index >= elements || index < 0)
// // // // throw new IndexOutOfBoundsException();
// // // // return list[index];
// // // // }
// // // //
// // // // public int indexOf(E element)
// // // // {
// // // // int index = 0; //Index counter
// // // // boolean found = false; // search flag
// // // //
// // // // //step through the list. When the element is found, set found to
// // true and
// // // stop.
// // // // while(!found && index < elements)
// // // // {
// // // // if(list[index].equals(element))
// // // // found = true;
// // // // else
// // // // index++;
// // // // }
// // // //
// // // // //return the index of the element or -1
// // // // if(!found)
// // // // index = -1;
// // // //
// // // // return index;
// // // // }
// // // //
// // // // public boolean isEmpty()
// // // // {
// // // // return (elements == 0);
// // // // }
// // // //
// // // // public E remove(int index)
// // // // {
// // // // if(index >= elements || index < 0)
// // // // throw new IndexOutOfBoundsException();
// // // //
// // // // //Save the elemet, but remove it from the list.
// // // // E temp = list[index];
// // // // list[index] = null;
// // // // index++;
// // // //
// // // // // shift all subsequent elements toward the front of the list.
// // // // while(index < elements)
// // // // {
// // // // list[index - 1] = list[index];
// // // // index++;
// // // // }
// // // //
// // // // //adjust the number of elements
// // // // elements--;
// // // //
// // // // //return the element that was removed
// // // // return temp;
// // // // }
// // // //
// // // // public boolean remove(E element)
// // // // {
// // // // int index = 0; // Index counter
// // // // boolean found = false; // search flag
// // // //
// // // // // perform a sequential search for element. When it is found,
// // remove it
// // // and
// // // // // stop searching.
// // // //
// // // // while(!found && index < elements)
// // // // {
// // // // if(list[index].equals(element))
// // // // {
// // // // list[index] = null;
// // // // found = true;
// // // // }
// // // // index++;
// // // // }
// // // //
// // // // //if the value was found, shift all subsequent elements toward the
// // front
// // // // // of the list.
// // // // if(found)
// // // // {
// // // // while(index < elements)
// // // // {
// // // // list[index - 1] = list[index];
// // // // index++;
// // // // }
// // // //
// // // // //adjust the number of elements
// // // // elements--;
// // // // }
// // // //
// // // // return found;
// // // // }
// // // //
// // // // public E set(int index, E element)
// // // // {
// // // // if(index >= elements || index < 0)
// // // // throw new IndexOutOfBoundsException();
// // // //
// // // // //Saving the existing element at that index
// // // // E temp = list[index];
// // // //
// // // // //replace the element with element
// // // // list[index] = element;
// // // //
// // // // //return the previously stored element
// // // // return temp;
// // // // }
// // // //
// // // // public int size()
// // // // {
// // // // return elements;
// // // // }
// // //
// // //
// // //
// // // // public String[] toStringArray()
// // // // {
// // // // //create a String array large enough to hold all the elements of
// // the list.
// // // // String [] strArray = new String[elements];
// // // //
// // // // //store each element's toString() return value as an element in the
// // string
// // // // // array.
// // // // for(int index = 0; index < elements; index++)
// // // // strArray[index] = list[index].toString();
// // // //
// // // // //return the string array
// // // // return strArray;
// // // // }
// // // //
// //
//

