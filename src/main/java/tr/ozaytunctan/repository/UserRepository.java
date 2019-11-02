package tr.ozaytunctan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tr.ozaytunctan.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	User findUserByUsername(String username);

}
