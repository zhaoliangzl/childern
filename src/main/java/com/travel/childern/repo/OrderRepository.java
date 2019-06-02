package com.travel.childern.repo;




import com.travel.childern.model.Admin;
import com.travel.childern.model.Goods;
import com.travel.childern.model.Order;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {

        List<Order> findAllByUserIdAndOrderStatus(int userId,int status);

        int countAllByUserIdAndOrderStatus(int userId,int status);

        int countAllByOrderStatus(int status);

        @Query("select count(u) from Order u where u.time like CONCAT('%',?1,'%')")
        int findIOrderByTimeLike(String time);

        @Query("select count(u) from Order u where u.time like CONCAT('%',?1,'%') and u.orderStatus = ?2")
        int findIOrderByTimeLikeAndOrderStatus(String time,int orderStatus);

        List<Order> findAllByOrderStatus(int orderStatus);

        List<Order> findAllByGoodsId(int goodsId);

}
