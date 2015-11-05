package com.github.elizabetht.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;

@Component
@XmlRootElement(name="coins")
@Entity
@Table(name="coins")
public class UserCoins {
	@Id
	@GeneratedValue
	private int id;
	
	@NotNull
	private int user_id;
	

	@NotNull
	private int coins;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserid() {
		return user_id;
	}

	public void setUserid(int userid) {
		this.user_id = userid;
	}

	public int getCoins() {
		return coins;
	}

	public void setCoins(int coins) {
		this.coins = coins;
	}

}
