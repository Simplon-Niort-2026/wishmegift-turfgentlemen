package co.simplon.wishmegift.repository;

import co.simplon.wishmegift.entity.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.UUID;

@Repository
public interface WishListRepository extends JpaRepository<WishList, UUID> {
}
