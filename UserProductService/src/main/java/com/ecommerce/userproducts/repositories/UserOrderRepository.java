package com.ecommerce.userproducts.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.userproducts.entities.UserOrder;

@Repository
public interface UserOrderRepository extends JpaRepository<UserOrder, Integer>  {


	UserOrder findByproductname(String ordername);

}
