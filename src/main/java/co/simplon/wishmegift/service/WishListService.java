package co.simplon.wishmegift.service;

import co.simplon.wishmegift.entity.WishList;
import co.simplon.wishmegift.repository.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;
import java.util.UUID;

@Service
public class WishListService {

    @Autowired
    private WishListRepository wishListRepository;

    public Iterable<WishList> getWishLists() {
        return wishListRepository.findAll();
    }

    public Optional<WishList> getWishListById(UUID id) {
        return wishListRepository.findById(id);
    }

    public WishList createWishList(@RequestBody WishList wishList) {
        return wishListRepository.save(wishList);
    }
}
