package com.cos.photogram.domain.subscribe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SubscribeRepository extends JpaRepository<Subscribe, Long> {

	@Modifying	//INSERT, DELETE, UPDATE를 native query로 작성하려면 해당 어노테이션이 필요
	@Query(value = "INSERT INTO subscribe(from_user_id, to_user_id, create_date) VALUES(:from_user_id, :to_user_id, now())", nativeQuery = true)
	void subscribe(Long from_user_id, Long to_user_id);
	
	@Modifying
	@Query(value = "DELETE FROM subscribe WHERE from_user_id = :from_user_id AND to_user_id = :to_user_id", nativeQuery = true)
	void unSubscribe(Long from_user_id, Long to_user_id);
	
	@Query(value = "SELECT COUNT(*) FROM subscribe WHERE from_user_id = :principal_id AND to_user_id = :page_user_id", nativeQuery = true)
	int subscribeState(Long principal_id, Long page_user_id);
	
	@Query(value = "SELECT COUNT(*) FROM subscribe WHERE from_user_id = :page_user_id", nativeQuery = true)
	int subscribeCount(Long page_user_id);
}
