package co.simplon.wishmegift.service;

import co.simplon.wishmegift.dto.GiftDTO;
import co.simplon.wishmegift.entity.Gift;
import co.simplon.wishmegift.entity.User;
import co.simplon.wishmegift.entity.WishList;
import co.simplon.wishmegift.mapper.GiftMapper;
import co.simplon.wishmegift.repository.GiftRepository;
import co.simplon.wishmegift.repository.UserRepository;
import co.simplon.wishmegift.repository.WishListRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GiftService {

    private final GiftRepository giftRepository;
    private final WishListRepository wishListRepository;
    private final UserRepository userRepository;
    private final GiftMapper giftMapper;

    public GiftService(GiftRepository giftRepository, WishListRepository wishListRepository, UserRepository userRepository,  GiftMapper giftMapper) {
        this.giftRepository = giftRepository;
        this.wishListRepository = wishListRepository;
        this.userRepository = userRepository;
        this.giftMapper = giftMapper;
    }

    public List<GiftDTO> getAllGifts() {
        return giftRepository.findAll()
                .stream()
                .map(giftMapper::toGiftDTO)
                .toList();
    }

    public Optional<GiftDTO> getGiftById(Long GiftId) {
        Optional<Gift> gift = giftRepository.findById(GiftId);
        return gift.map(giftMapper::toGiftDTO);
    }

    public Optional<GiftDTO> createGift(Long userId, GiftDTO giftDTO) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            Gift gift = giftMapper.toGift(giftDTO);
            giftRepository.save(gift);
            return Optional.of(giftMapper.toGiftDTO(gift));
        }
        return Optional.empty();
    }

    public Optional<GiftDTO> reserveGift(Long giftId, Long wishlistId, Long guestId) {
        Optional<Gift> gift = giftRepository.findById(giftId);
        Optional<WishList> wl = wishListRepository.findById(wishlistId);
        Optional<User> guest = userRepository.findById(guestId);
        if (gift.isPresent() && wl.isPresent() && guest.isPresent()) {
            Gift currentGift = gift.get();
            WishList currentWl = wl.get();
            User currentGuest = guest.get();
            if (!currentWl.getOwner().equals(currentGuest) && currentWl.getGuests().contains(currentGuest)) {
                currentGift.setReserved(currentGift.getReserved());

                giftRepository.save(currentGift);
                return Optional.of(giftMapper.toGiftDTO(currentGift));
            }
        }

        return Optional.empty();

    }

    public void deleteGiftById(Long id) {
        giftRepository.deleteById(id);
    }
}
