package com.sparta.baro_15.repository;

import com.sparta.baro_15.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long>, UserRepository {

}
