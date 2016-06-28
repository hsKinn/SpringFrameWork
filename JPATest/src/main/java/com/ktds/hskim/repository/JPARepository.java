package com.ktds.hskim.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ktds.hskim.domain.JPA;

@Repository("jpaRepository")
public interface JPARepository extends JpaRepository<JPA, Integer> {

}



