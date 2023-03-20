package me.ding;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class test {
	public static void main(String[] args) {
		Map<String, String> map = new HashMap<>();
		map.put("扒出", "rake out");
		map.put("燃烧着的煤", "live coals");
		map.put("公鸡在打鸣", "cocks are crowing");

		Scanner scanner = new Scanner(System.in);

		for (String key : map.keySet()) {
			System.out.println("请回答以下问题的value: " + key);
			String userAnswer = scanner.nextLine();

			while (!userAnswer.equals(map.get(key))) {
				System.out.println("答案不正确，请重新输入: " + key);
				userAnswer = scanner.nextLine();
			}

			System.out.println("回答正确，下一个问题。");
		}

		System.out.println("恭喜你，回答了所有问题！");
		scanner.close();
	}
}

