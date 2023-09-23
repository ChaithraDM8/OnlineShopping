package com.lvg.spsec.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.lvg.spsec.dto.CustomUserDetails;
import com.lvg.spsec.entity.Users;
import com.lvg.spsec.repository.UsersRepository;

@Component
public class CustomUserDetailsService implements UserDetailsService
{
	 @Autowired
	 UsersRepository usersRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
	{
		Optional<Users> user = usersRepository.findById(username);
		return user.map(CustomUserDetails::new).orElseThrow(()-> new UsernameNotFoundException("User Does not Exist"));
	}
}
