/*Author: Anna Carlson
 * Original Project: Revature BootCamp Project 1: Employee Reimbursement Application
 * Description: To Aid in the Log in process
 * Note: As of 12/10/2020. I have had no need to store this information on this side.
 */

package com.revature.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data @ToString @NoArgsConstructor @AllArgsConstructor
public class Login {
	@Getter @Setter private Employee employee;
	@Getter @Setter private String username;
	
	//I would like to add more password security in future updates
	@ToString.Exclude
	@Getter @Setter private String password;
	
	public boolean usernamePassCheck(String username) {
		if(this.username.toLowerCase().equals(username.toLowerCase())) {return true;}
		return false;
	}
	
	public boolean passwordPassCheck(String password) {
		if(this.password.equals(password)) {
			return true;
		}
		return false;
	}

}
