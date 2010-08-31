/*
 * Copyright (C) 2010 Bartosch Warzecha, Matthias Weßendorf.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.wessendorf.faces.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import net.wessendorf.addressbook.Contact;
import net.wessendorf.addressbook.dao.ContactDao;


@RequestScoped
@Named("NewContact")
public class NewContactController {

	// the Data Access Object
	@Inject ContactDao dao;
	
	private String firstname;
	private String secondname;

	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getSecondname() {
		return secondname;
	}
	public void setSecondname(String secondname) {
		this.secondname = secondname;
	}

	public String create()
	{

		Contact c = new Contact();
		c.setFirstname(firstname);
		c.setSecondname(secondname);
		
		dao.save(c);

		return "list_addresses";
	}
	
	
}
