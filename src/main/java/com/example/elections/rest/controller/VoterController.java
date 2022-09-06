package com.example.elections.rest.controller;

import com.example.elections.security.util.PasswordUtil;
import com.example.elections.model.PasswordResetToken;
import com.example.elections.model.Voter;
import com.example.elections.rest.dtos.VoterDto;
import com.example.elections.rest.exception.ResourceNotFound;
import com.example.elections.rest.mapper.VoterMapper;
import com.example.elections.service.PasswordTokenService;
import com.example.elections.service.VoterService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class VoterController {

    @Autowired
    private VoterMapper voterMapper;

    @Autowired
    private VoterService voterService;

    @Autowired
    private PasswordTokenService passwordTokenService;

    @Autowired
    private PasswordUtil passwordUtil;

    @GetMapping
    public ResponseEntity<List<VoterDto>> getAllVoters() {
        List<VoterDto> voterDtoList = voterMapper.toVoterDtos(voterService.getAllVoters());
        return ResponseEntity.ok(voterDtoList);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> findByEmail(@PathVariable String email){
        Voter voter = voterService.findByEmail(email);
        if (voter == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found");
        return ResponseEntity.ok().body(voter);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getVoter(@PathVariable Long id)
            throws ResourceNotFound {
        Voter voter = voterService.getVoter(id)
                .orElseThrow(() -> new ResourceNotFound("Voter Not Found"));
        VoterDto voterDto = voterMapper.toVoterDto(voter);
        return ResponseEntity.ok(voterDto);
    }

    @PostMapping("/register")
    public ResponseEntity<?> createVoter(@RequestBody VoterDto voterDto) {
        Voter voter = voterMapper.toVoter(voterDto);
        voter.setCreatedBy(voterDto.getName());
        voterService.register(voter);
        return ResponseEntity.status(HttpStatus.CREATED).body(voter);
    }

    @PostMapping("/forgetPassword")
    public ResponseEntity<?> forgetPassword(@RequestBody Voter voter, HttpServletRequest request )
            throws ResourceNotFound {
        Voter user = voterService.findByEmail(voter.getEmail());
        if (user == null) {
            throw new ResourceNotFound("Not Found");
        }
        String token = UUID.randomUUID().toString();
        passwordTokenService.createPasswordResetTokenForUser(user, token);
        return ResponseEntity.ok().body(token);
    }

    @PutMapping ("/resetpassword/{resetToken}")
    public ResponseEntity<?> resetPassword(@RequestBody Voter voter, @PathVariable String resetToken){

        PasswordResetToken token = passwordTokenService.getResetToken(resetToken);
        if(token.getToken().isEmpty()){
            return ResponseEntity.status(404).body("This reset token not found......");
        }
        String result = passwordUtil.validatePasswordResetToken(resetToken);
        if(result != null){
            return  ResponseEntity.status(401).body("Reset Token Expired......");
        }
        String email = token.getVoter().getEmail();
        voterService.updatePassword(email,voter.getPassword());
        return ResponseEntity.status(200).body("the password changed.....");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,	@RequestBody VoterDto voterDto)
            throws ResourceNotFound {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Voter user = voterService.findByEmail(email);

        Voter voter = voterMapper.toVoter(voterDto);
        Voter voterId = voterService.getVoter(id)
                .orElseThrow(()->new ResourceNotFound("Voter Not Found"));
        voterId.setName(voter.getName()!=null ? voter.getName() : voterId.getName());
        voterId.setEmail(voter.getEmail()!=null ? voter.getEmail() : voterId.getEmail());
        voterId.setNational_id(voter.getNational_id()!=null ? voter.getNational_id()
                : voterId.getNational_id());
        voterId.setUpdatedBy(user.getName());
        voterService.save(voterId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(voterId);
    }

    @PatchMapping("/updatePassword/{newPassword}")
    public ResponseEntity<?> changeUserPassword(@RequestBody Voter voter,@PathVariable String newPassword) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Voter voterId = voterService.findByEmail(email);
        BCryptPasswordEncoder passwordEncoder =new BCryptPasswordEncoder();
        if(passwordEncoder.matches(voter.getPassword(),voterId.getPassword())){
            voterService.updatePassword(email,newPassword);
            return ResponseEntity.status(200).body("successfully updated......");
        }
        else return ResponseEntity.status(403).body("incorrect password");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        voterService.delete(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

}
