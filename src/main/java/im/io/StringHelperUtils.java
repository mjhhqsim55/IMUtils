package im.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author hqs
 * i aa
 */
public class StringHelperUtils {

	public List<charEntity> StringCharCount(File file) {
		int[] chars = new int[65535];
		List<charEntity> charEntitys = new ArrayList<>();
		BufferedReader br = null ; 
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String s = null;

			while ((s = br.readLine()) != null) {
				int len = s.length();
				for (int i = 0; i < len; i++) {
					int charAt = s.charAt(i);
					chars[charAt] = chars[charAt] + 1;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				br.close();
			} catch (Exception e2) {
			}
		}

		for (int i = 0; i < chars.length; i++) {
			if (chars[i] != 0) {
				charEntitys.add(new charEntity((char) i, chars[i]));
			}
		}

		Collections.sort(charEntitys, new Comparator<charEntity>() {
			public int compare(charEntity o1, charEntity o2) {
				return o2.count - o1.count;
			}
		});
		return charEntitys;
	};

	public List<charEntity> StringCharCount(String s) {
		int[] chars = new int[65535];
		List<charEntity> charEntitys = new ArrayList<>();
		int len = s.length();
		for (int i = 0; i < len; i++) {
			int charAt = s.charAt(i);
			chars[charAt] = chars[charAt] + 1;
		}

		for (int i = 0; i < chars.length; i++) {
			if (chars[i] != 0) {
				charEntitys.add(new charEntity((char) i, chars[i]));
			}
		}

		Collections.sort(charEntitys, new Comparator<charEntity>() {
			public int compare(charEntity o1, charEntity o2) {
				return o2.count - o1.count;
			}
		});
		return charEntitys;
	}

	class charEntity {
		char c;
		int count;

		public charEntity(char c, int count) {
			this.c = c;
			this.count = count;
		}

		public String toString() {
			return c + " : " + count;
		}

	}
}