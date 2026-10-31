package com.auca.imboniikimina.repository;

import com.auca.imboniikimina.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface MemberRepository extends JpaRepository<Member, UUID> {
}