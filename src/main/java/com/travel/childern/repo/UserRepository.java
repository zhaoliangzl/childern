package com.travel.childern.repo;




import com.travel.childern.model.Admin;
import com.travel.childern.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    User findUserByUsername(String username);

}
