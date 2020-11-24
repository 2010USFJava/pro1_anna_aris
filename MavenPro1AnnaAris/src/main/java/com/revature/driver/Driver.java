package com.revature.driver;

import com.revature.meta.LogThis;
import com.revature.meta.LogThis.LevelEnum;

public class Driver {

	public static void main(String[] args) {
		LogThis.logIt(LevelEnum.DEBUG, "Base Logger Test");
		System.out.println("Hello World");

	}

}
