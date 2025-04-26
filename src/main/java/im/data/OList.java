package im.data;

import java.util.Iterator;
/**
 * 一款实用链表类 ， 拥有list接口大部分功能 。 其特点是其较其他数据容器类来说较轻量 。 
 * 极其适合网络传输 。 
 * 
 * @author hqs
 * @see 1.5
 * i aa
 */
public class OList implements Iterable<Object> {

	private Element root;

	/**
	 * OList从放对象总数
	 * @return int整数数值 。 
	 */
	public int size() {
		int size = 0;
		Element e = root;

		while (e != null) {
          e = e.next ; 
          size++ ; 
		}
		return size;
	}

	/**
	 * 按照索引删除对象 。 
	 * @param index 索引值
	 */
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

	/**
	 * 向索引前一个元素插入元素
	 * @param index 索引值
	 * @param data 插入对象
	 */
	public void insert(int index, Object data) {
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

	/**
	 * 返回当前索引前一个元素 。 
	 * @param index 索引值
	 * @return 返回元素对象 。  
	 */
	public Object prev(int index) {
		if (index < 1) {
			throw new IndexOutOfBoundsException();
		}
		return get(index, true).prev.data;
	}

	/**
	 * 内部方法 。 
	 * @param index 索引值
	 * @param is 
	 * @return 返回元素Element
	 */
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

	/**
	 * 返回当前索引元素 。 
	 * @param index 索引值
	 * @return 返回元素对象 。  
	 */
	public Object get(int index) {
		Element e = root;
		for (int i = 0; i < index; i++) {
			if (e == null) {
				throw new IndexOutOfBoundsException();
			}
			e = e.next;
		}
		return e.data;
	}

	/**
	 * 向OList追加对象
	 * @param obj 追加对象
	 */
	public void add(Object obj) {
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

		Object data;

		Element prev;
		Element next;

		public Element(Object data) {
			this.data = data;
		}

	}
    
	/**
	 * 返回迭代器对象
	 */
	public Iterator<Object> iterator() {
		return new Iterator<Object>() {
			Element e = root;
			boolean first = true;
            int count = 0 ; 
            int size = size() ; 
			public Object next() {
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
