package tr.ozaytunctan.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import tr.ozaytunctan.helper.UserPrincible;
import tr.ozaytunctan.model.User;
import tr.ozaytunctan.repository.UserRepository;
import tr.ozaytunctan.service.spec.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.userRepository.findUserByUsername(username);
		if (Objects.isNull(user))
			throw new UsernameNotFoundException("Username not found");

		return new UserPrincible(user);
	}

}
