package tr.ozaytunctan.startup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import tr.ozaytunctan.model.User;
import tr.ozaytunctan.repository.UserRepository;

@Component
public class Startup implements CommandLineRunner {

	@Autowired
	private UserRepository repository;

	@Autowired
	private BCryptPasswordEncoder byBCryptPasswordEncoder;

	private void createSystemUser() {
		User user = new User();
		user.setUsername("ozaytunctan");
		user.setPassword(byBCryptPasswordEncoder.encode("1234"));
		user.setRoles("ROLE_ADMIN,ROLE_USER");
		user.setPermissions("FULL,R,W,RW");
		User u2 = repository.findUserByUsername(user.getUsername());
		if (u2 == null)
			repository.save(user);

	}

	@Override
	public void run(String... args) throws Exception {
		createSystemUser();

	}

}
