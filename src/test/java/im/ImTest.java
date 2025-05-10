package im;

import im.data.Trie;

public class ImTest {

	public static void main(String[] args) {
    
		Trie trie = new Trie() ; 
		trie.push("15");
		trie.push("152345");
		trie.push("15245");
		trie.push("1524521");
		trie.push("15245212");
		trie.push("145");
		trie.push("2345");
		trie.push("245");
		System.out.println(trie.getStrings("15"));

	}

}
