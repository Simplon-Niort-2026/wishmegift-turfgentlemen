package co.simplon.wishmegift.service;

import co.simplon.wishmegift.entity.Gift;
import co.simplon.wishmegift.entity.User;
import co.simplon.wishmegift.entity.WishList;
import co.simplon.wishmegift.repository.GiftRepository;
import co.simplon.wishmegift.repository.UserRepository;
import co.simplon.wishmegift.repository.WishListRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class WishListService {

    private final WishListRepository wishListRepository;
    private final UserRepository userRepository;
    private final GiftRepository giftRepository;

    public WishListService(WishListRepository wishListRepository, UserRepository userRepository, GiftRepository giftRepository) {
        this.wishListRepository = wishListRepository;
        this.userRepository = userRepository;
        this.giftRepository = giftRepository;
    }

    public Iterable<WishList> getWishLists() {
        return wishListRepository.findAll();
    }

    public Iterable<WishList> getGuestWishLists(UUID guestId) {
        Optional<User> guest = userRepository.findById(guestId);
        if(guest.isPresent()) {
            User currentGuest = guest.get();
            currentGuest.getGuestLists();
            wishListRepository.findAll();
        }
       return null;
    }

    public Optional<WishList> getWishListById(UUID id) {
        return wishListRepository.findById(id);
    }

    public ResponseEntity<WishList> createWishList(UUID userId, WishList wishList) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
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

    public ResponseEntity<WishList> addGuestToWishList(UUID wishListId, UUID guestId, String email) {
        Optional<User> guest = userRepository.findById(guestId);
        Optional<WishList> wl = wishListRepository.findById(wishListId);
        if (guest.isPresent() && wl.isPresent() ) {
                WishList currentWishList = wl.get();
                User currentGuest = guest.get();
                if (currentGuest.getEmail().equals(email)) {
                    currentWishList.getGuests().add(currentGuest);

                    wishListRepository.save(currentWishList);
                    return new ResponseEntity<>(HttpStatus.ACCEPTED);
                }


        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

    }

}
