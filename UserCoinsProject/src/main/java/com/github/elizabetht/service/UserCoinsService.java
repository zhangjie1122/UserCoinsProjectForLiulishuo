package com.github.elizabetht.service;

import com.github.elizabetht.model.UserCoins;

public interface UserCoinsService {
	UserCoins save(UserCoins userCoins);
	int findByUserid(int userid);
	void UpdateUserCoins(int userid, int now_coins);
}
