package com.auca.imboniikimina.service;

import com.auca.imboniikimina.model.LoanStatus;
import com.auca.imboniikimina.repository.LoanRepository;
import com.auca.imboniikimina.repository.MemberRepository;
import com.auca.imboniikimina.repository.RepaymentRepository;
import com.auca.imboniikimina.repository.SavingsGroupRepository;
import com.auca.imboniikimina.repository.TreasurerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class DashboardService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private TreasurerRepository treasurerRepository;

    @Autowired
    private SavingsGroupRepository savingsGroupRepository;

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private RepaymentRepository repaymentRepository;

    public Map<String, Long> getSummary() {
        Map<String, Long> summary = new LinkedHashMap<>();
        summary.put("members", memberRepository.count());
        summary.put("treasurers", treasurerRepository.count());
        summary.put("freeGroups", (long) savingsGroupRepository.findFreeSavingsGroups().size());
        summary.put("loansToday", loanRepository.countByRequestDate(LocalDate.now()));
        summary.put("requested", loanRepository.countByStatus(LoanStatus.REQUESTED));
        summary.put("repayments", repaymentRepository.count());
        return summary;
    }
}