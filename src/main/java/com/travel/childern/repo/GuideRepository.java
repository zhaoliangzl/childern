package com.travel.childern.repo;




import com.travel.childern.model.Guide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuideRepository extends JpaRepository<Guide,Integer> {

    Guide findGuideByUsername(String username);

}
