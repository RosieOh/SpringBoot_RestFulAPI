package com.pro06.repository;

import com.pro06.entity.Member;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


public interface MemberRepository extends JpaRepository<Member, String> {

    @EntityGraph(attributePaths = "roleSet")
    @Query("select m from Member m where m.id = :id and m.social = false")
    Optional<Member> getWithRoles(String mid);

    @EntityGraph(attributePaths = "roleSet")
    Member findByEmail(String email);

    @Modifying
    @Transactional
    @Query("update Member m set m.pw =:pw where m.id = :id ")
    void updatePassword(@Param("pw") String password, @Param("id") String id);

    @Query("select m from Member m where m.id =:id")
    Member findByMid(@Param("id") String id);
}