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
import java.util.UUID;

@Service
public class GiftService {

    private final GiftRepository giftRepository;
    private final WishListRepository wishListRepository;
    private final UserRepository userRepository;

    public GiftService(GiftRepository giftRepository, WishListRepository wishListRepository, UserRepository userRepository) {
        this.giftRepository = giftRepository;
        this.wishListRepository = wishListRepository;
        this.userRepository = userRepository;
    }

    public Iterable<Gift> getAllGifts() {
        return giftRepository.findAll();
    }

    public Optional<Gift> getGiftById(UUID id) {
        return giftRepository.findById(id);
    }

    public Gift saveGift(Gift gift) {
        return giftRepository.save(gift);
    }

    public ResponseEntity<Gift> reserveGift(UUID giftId, UUID wishlistId, UUID guestId) {
        Optional<Gift> gift = giftRepository.findById(giftId);
        Optional<WishList> wl = wishListRepository.findById(wishlistId);
        Optional<User> guest = userRepository.findById(guestId);
        if(gift.isPresent() && wl.isPresent() && guest.isPresent()) {
            Gift currentGift = gift.get();
            WishList currentWl = wl.get();
            User currentGuest = guest.get();
            if (currentWl.getGuests().contains(currentGuest)) {
                currentGift.setReserved(currentGift.getReserved());

                saveGift(currentGift);
                return new ResponseEntity<>(currentGift, HttpStatus.ACCEPTED);
            }

        }

        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

    }

    public void deleteGiftById(UUID id) {
        giftRepository.deleteById(id);
    }
}
