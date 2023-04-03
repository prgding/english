package me.ding;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
	public static void main(String[] args) {
		// 创建一个空的HashMap
		Map<String, String> myMap = new HashMap<>();

		// 读取txt文件内容并存入Map
		try {
			File file = new File("input-front50.txt"); // 文件路径
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] kv = line.split(":");
				if (kv.length == 2) {
					myMap.put(kv[0], kv[1]);
				}
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		Map<String, Boolean> askedMap = new HashMap<>();
		for (String key : myMap.keySet()) {
			askedMap.put(key, false);
		}

		int count = 1;
		String strange = "";

		// 不断循环，直到用户输入"exit"为止
		while (true) {
			// 创建一个随机数生成器
			Random random = new Random();
			// 创建一个Scanner对象，读取用户输入
			Scanner scanner = new Scanner(System.in);
			// 生成一个随机数，获取对应的key和value
			String key = getRandomUnaskedKey(myMap, askedMap, random, strange);
			String value = myMap.get(key);
			askedMap.put(key, true);

			// 输出题目
			System.out.println(key + " [" + count + "]");

			// 读取用户输入
			String input = scanner.nextLine();

			// 如果用户输入"exit"，则退出程序
			if (input.equals("exit")) {
				scanner.close();
				break;
			}
			if (input.equals("1")) {
				// 加入不熟悉队列
				strange = mark(strange, key, value);
				continue;
			}
			if (input.equals("strange")) {
				System.out.println(strange);
			}

			while (!input.equals(value)) {
				System.out.println("不对，请重新输入: " + key + " [" + count + "]");
				strange = mark(strange, key, value);
				input = scanner.nextLine();
				if (input.equals("v")) {
					System.out.println(value);
				}
			}

			System.out.println("对了!");
			count++;
		}
	}

	private static String mark(String strange, String key, String value) {
		String single = "陌生的词组: " + key + ":" + value;
		strange += single + '\n';
		return strange;
	}

	// 获取一个尚未被提问过的key
	private static String getRandomUnaskedKey(Map<String, String> myMap, Map<String, Boolean> askedMap, Random random
			, String strange) {
		String[] unaskedKeys = askedMap.keySet().stream().filter(key -> !askedMap.get(key)).toArray(String[]::new);
		if (unaskedKeys.length > 0) {
			return unaskedKeys[random.nextInt(unaskedKeys.length)];
		} else {
			// 如果所有的key都已经被提问过，则重新设置所有key的标记，并返回一个随机的key
			System.out.println("全部提问过了");
			System.out.println(strange);
			System.exit(0);

//			for (String key : myMap.keySet()) {
//				askedMap.put(key, false);
//			}
//			return myMap.keySet().toArray(new String[0])[random.nextInt(myMap.size())];
		}
		return null;
	}
}