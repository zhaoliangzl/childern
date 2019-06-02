package com.travel.childern.repo;




import com.travel.childern.model.Goods;
import com.travel.childern.model.Line;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsRepository extends JpaRepository<Goods,Integer> {

    @Query("select u from Goods u where u.guideList like CONCAT('%',?1,'%')")
    List<Goods> findGoodsByGuideListLike(String guidelist);

}
