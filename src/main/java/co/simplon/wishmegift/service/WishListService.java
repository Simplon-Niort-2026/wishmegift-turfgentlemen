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

    public ResponseEntity<WishList> addGuestToWishList(UUID wishListId, UUID guestId) {
        Optional<User> guest = userRepository.findById(guestId);
        Optional<WishList> wl = wishListRepository.findById(wishListId);
        if (guest.isPresent() && wl.isPresent()) {
            WishList currentWishList = wl.get();
            User currentGuest = guest.get();

            Set<User> guestList = currentWishList.getGuests();
            guestList.add(currentGuest);

            wishListRepository.save(currentWishList);
            return new ResponseEntity<>(currentWishList, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        }

    }

    public ResponseEntity<WishList> addGiftToWishList(UUID wishListId, UUID giftId) {
        Optional<Gift> gift = giftRepository.findById(giftId);
        Optional<WishList> wl = wishListRepository.findById(wishListId);
        if (gift.isPresent() && wl.isPresent()) {
            Gift currentGift = gift.get();
            WishList currentWl = wl.get();

            Set<Gift> gifts = currentWl.getGifts();

            gifts.add(currentGift);
            wishListRepository.save(currentWl);
            return new ResponseEntity<>(currentWl, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

    }
}
