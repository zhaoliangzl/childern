package com.travel.childern.repo;




import com.travel.childern.model.Banner;
import com.travel.childern.model.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BannersRepository extends JpaRepository<Banner,Integer> {

    List<Banner> findAllByEnableIsTrue();


}
