package com.sqa.onlinepizzastore.entitites;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class HelloWorld {
     
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
     
    @NotBlank(message = "Name is mandatory")
    private String name;
     
    @NotBlank(message = "Email is mandatory")
    private String email;

 // standard constructors / setters / getters / toString
    
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public HelloWorld(long id, @NotBlank(message = "Name is mandatory") String name,
			@NotBlank(message = "Email is mandatory") String email) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
	}

	public HelloWorld() {
		super();
	}
 
    



}