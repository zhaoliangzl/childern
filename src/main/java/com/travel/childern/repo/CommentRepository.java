package com.travel.childern.repo;




import com.travel.childern.model.Comment;
import com.travel.childern.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer> {

    List<Comment> findAllByGoodsIdAndEnableIsTrue(int goodsId);

    @Query(value = "select avg(c.score) from Comment c where c.enable = true ")
    public float getAverageScore();

}
