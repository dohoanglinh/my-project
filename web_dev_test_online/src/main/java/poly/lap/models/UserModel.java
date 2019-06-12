/*
**======= Abouts us =======**
* System name : assigment5
* Verstion    : 1.0
* Create date : Mar 9, 2019 9:24:43 AM
* Description : Create by Linh Noob
* Copyright © 2019 Noob.com.All rights reserved.
**=========================**
*/
package poly.lap.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;


	@Entity
	@Table(name="users")
	public class UserModel {

	    @Id
	    @NotEmpty(message = "không được bỏ trống")
	    private String Username;
	    @NotBlank(message = "không được bỏ trống!!")
	    private String Password;
	    @NotBlank(message = "không được bỏ trống!!")
	    private String Fullname;

	    /**
		 * Instantiates a new users DB.
		 *
		 * @param username the username
		 * @param password the password
		 * @param fullname the fullname
		 */
	    public UserModel(String username, String password, String fullname) {
	        this.Username = username;
	        this.Password = password;
	        this.Fullname = fullname;
	    }
	    public UserModel() {
	    }

		/**
		 * Gets the username.
		 *
		 * @return the username
		 */
		public String getUsername() {
			return Username;
		}

		

		public void setUsername(String username) {
			Username = username;
		}

		public String getPassword() {
			return Password;
		}

		public void setPassword(String password) {
			Password = password;
		}

		public String getFullname() {
			return Fullname;
		}

		public void setFullname(String fullname) {
			Fullname = fullname;
		}
}
