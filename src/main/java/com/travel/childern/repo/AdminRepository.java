package com.travel.childern.repo;




import com.travel.childern.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Integer> {

    Admin findAdminByUsername(String admin);

}
