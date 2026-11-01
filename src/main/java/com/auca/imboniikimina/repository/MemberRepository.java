package com.auca.imboniikimina.repository;

import com.auca.imboniikimina.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface MemberRepository extends JpaRepository<Member, UUID> {

    List<Member> findByLastNameIgnoreCaseOrderByFirstNameAsc(String lastName);

    @Query("SELECT DISTINCT l.member FROM Loan l WHERE l.treasurer.id = :treasurerId")
    List<Member> findMembersOfTreasurer(@Param("treasurerId") UUID treasurerId);
}