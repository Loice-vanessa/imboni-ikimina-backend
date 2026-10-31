package com.auca.imboniikimina.repository;

import com.auca.imboniikimina.model.Treasurer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface TreasurerRepository extends JpaRepository<Treasurer, UUID> {
}