/*Original Source Code: J. "Matt" Knighten
 * Modifier: Anna Carlson
 * Modifican: Switch Statement changed from Strings to Enums
 * Original Project: Revature BootCamp Project 1: Employee Reimbursement Application
 * Description: LogManager functions to improve utility.
 * Note: LogManager is used to display information while the program is
 * running. It takes up fewer resources then System.out.println() during runtime
 * and save the data in a file: in this case called logging.log
 */

package com.revature.meta;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogThis {
	public enum LevelEnum{
		DEBUG,
		WARN,
		ERROR,
		FATAL,
		INFO,
		TRACE;
	}
	
	static Logger logger= LogManager.getLogger();
	
	public static void logIt(LevelEnum level,String message) {
		switch(level) {
		case DEBUG:
			logger.debug(message);
			break;
		case WARN:
			logger.warn(message);
			break;
		case ERROR:
			logger.error(message);
			break;	
		case FATAL:
			logger.fatal(message);
			break;
		case INFO:
			logger.info(message);
			break;
		case TRACE:
			logger.trace(message);
			break;
		default:
			System.err.println("Case "+level+" not included in logger");
		}
	}
}
