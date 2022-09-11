package com.example.elections.service;

import com.example.elections.model.Role;
import com.example.elections.model.Station;
import com.example.elections.model.Voter;
import com.example.elections.repository.StationRepository;
import com.example.elections.repository.VoterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class VoterService implements UserDetailsService {

    @Autowired
    private VoterRepository voterRepository;

    @Autowired
    private StationRepository stationRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Voter voter = voterRepository.findByEmail(username);
        Station station = stationRepository.findById(1L).get();

        if (voter == null) {
            throw new UsernameNotFoundException("No user found with username: " + username);
        }
        return new User(voter.getEmail(), voter.getPassword(), getAuthorities(voter.getRoles()));
    }

    public Collection<? extends GrantedAuthority> getAuthorities(
            Collection<Role> roles) {
        List<GrantedAuthority> authorities
                = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
            role.getName();
        }
        return authorities;
    }


    public Voter register(Voter voter) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodePassword = passwordEncoder.encode(voter.getPassword());
        voter.setPassword(encodePassword);
        return voterRepository.save(voter);
    }

    public void updatePassword(String email, String password) {
        Voter voter = voterRepository.findByEmail(email);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodePassword = passwordEncoder.encode(password);
        voter.setPassword(encodePassword);
        voterRepository.save(voter);
    }

    public List<Voter> getAllVoters() {
        return voterRepository.findAll();
    }

    public Optional<Voter> getVoter(Long id) {
        return voterRepository.findById(id);
    }

    public void delete(Long id) {
        voterRepository.deleteById(id);
    }

    public Voter save(Voter voter) {
        return voterRepository.save(voter);
    }

    public Voter findByEmail(String email) {
        return voterRepository.findByEmail(email);
    }

}
