package im.data;

import java.util.ArrayList;
import java.util.List;

/**单词树实现
 * @author hqs
 * i aa
 */
public class Trie {

  public static final int SIZE =  2<<15 ;  	  
  Element start = null ; 
  
  public Trie() {
	  start = new Element( (char) (56)) ; 
  }
	 
  public void push(String string) {
	char[] charArray = string.toCharArray() ;    
	Element[] elements = start.elements ; 
	Element e = null  ; 
	for(char data : charArray) {
	  
      e = elements[data] ; 
	  if(e==null) {
		  e = new Element(data) ; 
		  elements[data] = e ; 
	  } 
	  elements = e.elements ; 
	}
	e.isend = true ;  
  }
  public List<String> getStrings(String string) {
	  List<String> strings = new ArrayList<String>() ;
	   if(string!=null) {
		 char[] charArray = string.toCharArray() ;   
	     
		 Element[] elements = start.elements ; 
		 Element e = null  ; 
		 for(char data : charArray) {
		  e = elements[data] ;                               
		  if(e==null) break ; 
		  elements = e.elements ; 
		 }
		 
		 if(e!=null) t(elements , string ,  strings) ;  
	   }
	  return strings ; 
  }
  
  public void t(Element[] elements ,  String string ,  List<String> strings) {
	for(Element element : elements) {
		if(element != null) {
		 if(element.isend) {
		   string += element.data ; 
		   strings.add(string) ; 
			
		 }else  string += element.data ; 
		 t(element.elements , string , strings) ; 
	   }
	}
  }
  
  class Element{
	 Element[] elements = null ;  
	 char data  ; 
	 boolean isend = false ; 
	 public Element(char i) {
		 this.data = i ; 
		 elements = new Element[SIZE] ; 
	 }
 
  
  }
	 
	 
	 
}
