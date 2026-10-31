package com.auca.imboniikimina.service;

import com.auca.imboniikimina.model.Member;
import com.auca.imboniikimina.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public String saveMember(Member member) {
        memberRepository.save(member);
        return "Member saved successfully";
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> getMemberById(UUID id) {
        return memberRepository.findById(id);
    }

    public String updateMember(UUID id, Member newData) {
        Optional<Member> existing = memberRepository.findById(id);
        if (existing.isEmpty()) {
            return "The member with that id does not exist";
        }
        Member member = existing.get();
        member.setFirstName(newData.getFirstName());
        member.setLastName(newData.getLastName());
        member.setMemberNumber(newData.getMemberNumber());
        member.setPhone(newData.getPhone());
        member.setVillage(newData.getVillage());
        memberRepository.save(member);
        return "Member updated successfully";
    }

    public String deleteMember(UUID id) {
        if (!memberRepository.existsById(id)) {
            return "The member with that id does not exist";
        }
        memberRepository.deleteById(id);
        return "Member deleted successfully";
    }
}
