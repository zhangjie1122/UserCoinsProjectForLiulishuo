package com.github.elizabetht.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.elizabetht.model.UserCoins;
import com.github.elizabetht.repository.UserCoinsRepository;

@Service("UserCoinsService")
public class UserCoinsServiceImpl implements UserCoinsService{
	@Autowired
	private UserCoinsRepository userCoinsRepository;
	
	@Transactional
	public UserCoins save(UserCoins userCoins) {
		return userCoinsRepository.save(userCoins);
	}

	public int findByUserid(int userid) {
		UserCoins stud = userCoinsRepository.findByUserid(userid);
		
		if(stud != null) {
			return stud.getCoins();
		}
		
		return -1;
	}
	
	public java.util.List<UserCoins> findAll() {
		return userCoinsRepository.findAll();
	}
	
	public void UpdateUserCoins(int userid, int coins) {
		userCoinsRepository.UpdateUserCoins(userid, coins);
	}

}
