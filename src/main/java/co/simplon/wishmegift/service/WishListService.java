package co.simplon.wishmegift.service;

import co.simplon.wishmegift.dto.WishListDTO;
import co.simplon.wishmegift.entity.Gift;
import co.simplon.wishmegift.entity.User;
import co.simplon.wishmegift.entity.WishList;
import co.simplon.wishmegift.mapper.WishListMapper;
import co.simplon.wishmegift.repository.GiftRepository;
import co.simplon.wishmegift.repository.UserRepository;
import co.simplon.wishmegift.repository.WishListRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class WishListService {

    private final WishListRepository wishListRepository;
    private final UserRepository userRepository;
    private final GiftRepository giftRepository;
    private final WishListMapper wishListMapper;

    public WishListService(GiftRepository giftRepository, WishListRepository wishListRepository, UserRepository userRepository, WishListMapper wishListMapper) {
        this.giftRepository = giftRepository;
        this.wishListRepository = wishListRepository;
        this.userRepository = userRepository;
        this.wishListMapper = wishListMapper;
    }

    public List<WishListDTO> getWishLists() {
        return wishListRepository.findAll()
                .stream()
                .map(wishListMapper::toWishListDTO)
                .toList();
    }

    public List<WishListDTO> getGuestWishLists(UUID guestId) {
        Optional<User> guest = userRepository.findById(guestId);
        if (guest.isPresent()) {
            User currentGuest = guest.get();
            return currentGuest.getGuestLists()
                    .stream()
                    .map(wishListMapper::toWishListDTO)
                    .toList();
        }
        return null;
    }

    public Optional<WishListDTO> getWishListById(UUID id) {
        Optional<WishList> wl = wishListRepository.findById(id);
        return wl.map(wishListMapper::toWishListDTO);
    }

    public Optional<WishListDTO> createWishList(UUID userId, WishListDTO wishListDTO) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            User currentUser = user.get();
            WishList currentWl = wishListMapper.toWishList(wishListDTO);

            currentWl.setOwner(currentUser);

            wishListRepository.save(currentWl);
            return Optional.of(wishListMapper.toWishListDTO(currentWl));

        }
        return Optional.empty();
    }

    public Optional<WishListDTO> addGuestToWishList(UUID wishListId, UUID guestId) {
        Optional<User> guest = userRepository.findById(guestId);
        Optional<WishList> wl = wishListRepository.findById(wishListId);
        if (guest.isPresent() && wl.isPresent()) {
            WishList currentWl = wl.get();
            User currentGuest = guest.get();
            currentWl.getGuests().add(currentGuest);
            wishListRepository.save(currentWl);
            return Optional.of(wishListMapper.toWishListDTO(currentWl));


        }
        return Optional.empty();
    }

        public Optional<WishListDTO> addGiftToWishList (UUID wishListId, UUID ownerId, UUID giftId){
            Optional<WishList> wl = wishListRepository.findById(wishListId);
            Optional<User> owner = userRepository.findById(ownerId);
            Optional<Gift> gift = giftRepository.findById(giftId);
            if (gift.isPresent() && wl.isPresent() && owner.isPresent()) {
                User currentOwner = owner.get();
                WishList currentWl = wl.get();
                Gift currentGift = gift.get();
                if (currentWl.getOwner().equals(currentOwner)) {
                    currentWl.getGifts().add(currentGift);

                    wishListRepository.save(currentWl);
                    return Optional.of(wishListMapper.toWishListDTO(currentWl));
                }

            }
            return Optional.empty();
        }

        public Optional<WishListDTO> removeGiftToWishList (UUID wishListId, UUID ownerId, UUID giftId){
            Optional<WishList> wl = wishListRepository.findById(wishListId);
            Optional<User> owner = userRepository.findById(ownerId);
            Optional<Gift> gift = giftRepository.findById(giftId);
            if (gift.isPresent() && wl.isPresent() && owner.isPresent()) {
                User currentOwner = owner.get();
                WishList currentWl = wl.get();
                Gift currentGift = gift.get();
                if (currentWl.getOwner().equals(currentOwner)) {
                    currentWl.getGifts().remove(currentGift);
                    giftRepository.delete(currentGift);

                    wishListRepository.save(currentWl);
                    return Optional.of(wishListMapper.toWishListDTO(currentWl));
                }
            }
            return Optional.empty();
        }

    }

