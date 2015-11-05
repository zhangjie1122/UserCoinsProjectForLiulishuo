package com.github.elizabetht.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.github.elizabetht.model.UserCoins;

@Repository("UserCoinsRepository")
public interface UserCoinsRepository extends JpaRepository<UserCoins, Long>{
	@Query("select s from UserCoins s where s.user_id = :userid")
	UserCoins findByUserid(@Param("userid") int userid);
	
	@Modifying
	@Query("update UserCoins u set u.coins = :coins where u.user_id = :userid")
	@Transactional
	void UpdateUserCoins(@Param("userid")int userid, @Param("coins")int coins);
}
