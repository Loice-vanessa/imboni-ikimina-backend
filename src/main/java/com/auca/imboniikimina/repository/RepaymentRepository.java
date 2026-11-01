package com.auca.imboniikimina.repository;

import com.auca.imboniikimina.model.Repayment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface RepaymentRepository extends JpaRepository<Repayment, UUID> {
}