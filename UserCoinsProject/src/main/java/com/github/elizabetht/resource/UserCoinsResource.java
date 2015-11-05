package com.github.elizabetht.resource;

import java.text.ParseException;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.elizabetht.model.UserCoins;
import com.github.elizabetht.service.UserCoinsService;

@Component
@Path("UserCoinsResource")
@XmlRootElement
public class UserCoinsResource {
	@Autowired
	private UserCoinsService userCoinsService;

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String start() {
		return "hello: user coins";
	}

	@POST
	@Path("user/add")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String useradd(@FormParam("userid") int userid,
			@FormParam("coins") int coins) throws ParseException {
		if(userCoinsService.findByUserid(userid)!=(-1)) {
			return "user is existed!";
		}
		else if(coins<=0) {
			return "input of coins is invalid";
		}
		UserCoins userCoins = new UserCoins();
		userCoins.setUserid(userid);
		userCoins.setCoins(coins);

		userCoinsService.save(userCoins);

		return "user add success";
	}

	@POST
	@Path("coins/user")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String coinsuser(@FormParam("userid") int userid)
			throws ParseException {
		int coins = userCoinsService.findByUserid(userid);
		if(coins==-1){
			return "user is not existed";
		}
		return "user " + userid + " coins is " + coins;
	}

	/*
	 * @GET
	 * 
	 * @Path("coins/user/{userid}") public Response
	 * coinsuser(@PathParam("userid") int userid) {
	 * System.out.println("yooyoyyoyo"+userid); String coins =
	 * Integer.toString(userCoinsService.findByUserid(userid)); String output =
	 * "Coins: "+coins; return Response.status(200).entity(output).build(); }
	 */

	@POST
	@Path("transaction/transfer")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String transactiontransfer(
			@FormParam("from_user_id") int from_user_id,
			@FormParam("to_user_id") int to_user_id,
			@FormParam("coins") int coins) {
		
		int from_coins = userCoinsService.findByUserid(from_user_id);
		int to_coins = userCoinsService.findByUserid(to_user_id);
		if(from_coins==-1) {
			return "from user is not exist";
		}
		if(to_coins==-1) {
			return "to user is not exist";
		}
		
		int now_from_coins = from_coins-coins;
		if(now_from_coins<0) {
			return "from user coins is not enough";
		}
		int now_to_coins = to_coins+coins;

		userCoinsService.UpdateUserCoins(from_user_id, now_from_coins);
		userCoinsService.UpdateUserCoins(to_user_id, now_to_coins);

		return "transfer success";
	}

}
