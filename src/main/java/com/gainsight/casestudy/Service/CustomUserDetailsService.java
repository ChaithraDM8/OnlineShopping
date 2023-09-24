package com.gainsight.casestudy.Service;
import com.gainsight.casestudy.CustomUserDetails;
import com.gainsight.casestudy.Entity.User;
import com.gainsight.casestudy.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomUserDetailsService implements UserDetailsService
{
	 @Autowired
	 UserRepository usersRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		Optional<User> user = usersRepository.findById(username);
		return user.map(CustomUserDetails::new).orElseThrow(()-> new UsernameNotFoundException("User Does not Exist"));
	}

}
