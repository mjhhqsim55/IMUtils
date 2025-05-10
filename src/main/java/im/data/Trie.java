package im.data;

import java.util.ArrayList;
import java.util.List;

/**
 * 单词树实现
 * 
 * @author hqs i aa
 */
public class Trie {

	public static final int SIZE = 2 << 15;
	Element start = null;

	public Trie() {
		start = new Element((char) (56));
	}

	public void push(String string) {
		char[] charArray = string.toCharArray(); 
		Element[] elements = start.elements;
		Element e = null;
		for (char data : charArray) {
			e = elements[data];
			if (e == null) {
				e = new Element(data);
				elements[data] = e;
			}
			elements = e.elements;
		}
		e.isend = true;
	}

	public List<String> getStrings(String string) {
		List<String> strings = new ArrayList<String>();
		if (string != null) {
			char[] charArray = string.toCharArray(); 
            Element[] elements = start.elements; 
			Element e = null;
			for (char data : charArray) {
				e = elements[data];
				if (e == null)
					break;
				elements = e.elements;
			}
			if (e != null) {
				strings.add(string) ; 
				t(elements, string, strings);
			}
		}
		return strings;
	}

	public void t(Element[] elements, String string, List<String> strings) {
		for (Element element : elements) {
			String str = new String(string) ; 
			if (element != null) {
				if (element.isend) {
					str += element.data; 
					strings.add(str); 
				} else {
					str += element.data; 
				}
				t(element.elements, str , strings); 
			}
		}
	}

	class Element {
		Element[] elements = null;
		char data;
		boolean isend = false;

		public Element(char i) {
			this.data = i;
			elements = new Element[SIZE];
		}
	}
}
