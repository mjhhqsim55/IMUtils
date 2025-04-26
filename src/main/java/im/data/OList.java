package im.data;

import java.util.Iterator;
/**
 * @author hqs
 * i aa
 */
public class OList<T> implements Iterable<T> {

	private Element root;

	public int size() {
		int size = 0;
		Element e = root;

		while (e != null) {
          e = e.next ; 
          size++ ; 
		}
		return size;
	}

	public void remove(int index) {
		Element e = get(index, true);

		if (index == 0) {
			root = e.next;
			e.next.prev = null;
			e = null;
		} else {
			e.prev.next = e.next;
			e.next.prev = e.prev;
			e = null;
		}
	}

	public void insert(int index, T data) {
		Element e = get(index, true);
		Element eNew = new Element(data);

		if (e.prev == null) {
			eNew.next = e;
			e.prev = eNew;
			root = eNew;
		} else {
			e.prev.next = eNew;
			eNew.prev = e.prev;
			eNew.next = e;
			e.prev = eNew;
		}
	}

	public T prev(int index) {
		if (index < 1) {
			throw new IndexOutOfBoundsException();
		}
		return get(index, true).prev.data;
	}

	private Element get(int index, boolean is) {

		if (index < 0) {
			throw new IndexOutOfBoundsException();
		}

		Element e = root;
		for (int i = 0; i < index; i++) {
			if (e == null) {
				throw new IndexOutOfBoundsException();
			}
			e = e.next;
		}
		return e;
	}

	public T get(int index) {
		Element e = root;
		for (int i = 0; i < index; i++) {
			if (e == null) {
				throw new IndexOutOfBoundsException();
			}
			e = e.next;
		}
		return e.data;
	}

	public void add(T obj) {
		if (root == null) {
			root = new Element(obj);
		} else {
			Element e = root;
			while (true) {
				if (e.next == null) {
					e.next = new Element(obj);
					e.next.prev = e;
					break;
				} else {
					e = e.next;
				}
			}
		}
	}

	class Element {

		T data;

		Element prev;
		Element next;

		public Element(T data) {
			this.data = data;
		}

	}
    //
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			Element e = root;
			boolean first = true;
            int count = 0 ; 
            int size = size() ; 
			public T next() {
				if (first) {
					e = root;
					first = false;
				} else {
					e = e.next;
				}
				count++ ; 
				return e.data;
			}

			public boolean hasNext() {
				return count < size ; 
			}
		};
	}

}
