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
 * 字符串处理工具类 。 
 * @author hqs
 * i aa
 */
public class StringHelperUtils {

	/**
	 * 统计文本中单个字使用总数 。 统计结果将会封装为charEntity对象集 。 
	 * charEntity对象有：字符 ， 字符使用总数 。 并且会按照字符总数进行排序 。
	 * @param file 文本文件
	 * @return charEntity对象集
	 */
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

	/**
	 * 统计字符串中单个字使用总数 。 类似重载方法 StringCharCount(File file) 。
	 * @param s 字符串值
	 * @return charEntity对象集
	 */ 
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

	/**
	 * 用来存储统计结果
	 * @author hqs
	 *
	 */
	class charEntity {
		//字符
		char c;
		//字符使用总数
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