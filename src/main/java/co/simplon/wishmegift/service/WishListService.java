package co.simplon.wishmegift.service;

import co.simplon.wishmegift.entity.User;
import co.simplon.wishmegift.entity.WishList;
import co.simplon.wishmegift.repository.UserRepository;
import co.simplon.wishmegift.repository.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class WishListService {

    @Autowired
    private WishListRepository wishListRepository;
    @Autowired
    private UserRepository userRepository;


    public Iterable<WishList> getWishLists() {
        return wishListRepository.findAll();
    }

    public Optional<WishList> getWishListById(UUID id) {
        return wishListRepository.findById(id);
    }

    public ResponseEntity<WishList> createWishList(UUID userId, WishList wishList) {
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()) {
            User currentUser = user.get();

            wishList.setOwner(currentUser);
            Set<WishList> wl = currentUser.getLists();
            wl.add(wishList);

            wishListRepository.save(wishList);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    public ResponseEntity<WishList> addGuestToWishList(UUID userId, UUID wishListId, UUID guestId) {
        Optional<User> user = userRepository.findById(userId);
    }

}
