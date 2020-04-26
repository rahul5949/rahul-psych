package com.psych.game.auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.psych.game.exceptions.NoSuchUserException;
import com.psych.game.model.User;
import com.psych.game.repositories.UserRepository;

import lombok.SneakyThrows;

@Service
public class CustomUserDetailService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
    @SneakyThrows
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByEmail(email);
		if(user.isEmpty())
			throw new NoSuchUserException("No user registered with " + email);
		return new CustomUserDetails(user.get());
	}
	

}
