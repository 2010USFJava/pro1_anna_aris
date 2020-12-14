package com.revature.controller;

import javax.servlet.http.HttpServletRequest;

public class SimpleController {
	public static String goHome(HttpServletRequest req) {
		return "myresources/html/landingPage.html";
	}

}
