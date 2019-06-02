package com.travel.childern.repo;




import com.travel.childern.model.Admin;
import com.travel.childern.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact,Integer> {

    Contact findContactByTypeAndAgeAndPhoneAndName(int type,int age,String phone,String name);

}
