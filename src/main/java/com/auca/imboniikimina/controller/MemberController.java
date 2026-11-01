package com.auca.imboniikimina.controller;


import com.auca.imboniikimina.model.Member;
import com.auca.imboniikimina.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.auca.imboniikimina.model.Treasurer;
import com.auca.imboniikimina.service.TreasurerService;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

       @Autowired
    private TreasurerService treasurerService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Member member) {
        String message = memberService.saveMember(member);
        if (message.equals("Member saved successfully")) {
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Member>> getAll() {
        return new ResponseEntity<>(memberService.getAllMembers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        Optional<Member> member = memberService.getMemberById(id);
        if (member.isPresent()) {
            return new ResponseEntity<>(member.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("The member with that id does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody Member member) {
        String message = memberService.updateMember(id, member);
        if (message.equals("Member updated successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else if (message.equals("The member with that id does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = memberService.deleteMember(id);
        if (message.equals("Member deleted successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

      @GetMapping("/by-last-name")
    public ResponseEntity<List<Member>> getByLastName(@RequestParam String lastName) {
        return new ResponseEntity<>(memberService.getMembersByLastName(lastName), HttpStatus.OK);
    }

    @GetMapping("/of-treasurer/{treasurerId}")
    public ResponseEntity<?> getMembersOfTreasurer(@PathVariable UUID treasurerId) {
        Optional<Treasurer> treasurer = treasurerService.getTreasurerById(treasurerId);
        if (treasurer.isEmpty()) {
            return new ResponseEntity<>("The treasurer with that id does not exist", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(memberService.getMembersOfTreasurer(treasurerId), HttpStatus.OK);
        }
    }
}
