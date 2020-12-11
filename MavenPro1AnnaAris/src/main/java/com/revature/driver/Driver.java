package com.revature.driver;

import com.revature.util.LogThis;
import com.revature.util.LogThis.LevelEnum;

public class Driver {

	public static void main(String[] args) {
		LogThis.logIt(LevelEnum.DEBUG, "Base Logger Test");
		System.out.println("Hello World");

	}

}
