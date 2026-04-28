package co.simplon.wishmegift.service;

import co.simplon.wishmegift.entity.Gift;
import co.simplon.wishmegift.repository.GiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class GiftService {

    @Autowired
    private GiftRepository giftRepository;

    public Iterable<Gift> getAllGifts() {
        return giftRepository.findAll();
    }

    public Optional<Gift> getGiftById(UUID id) {
        return giftRepository.findById(id);
    }

    public Gift saveGift(Gift gift) {
        return giftRepository.save(gift);
    }

    public ResponseEntity<Gift> updateGift(UUID id, Gift gift) {
        Optional<Gift> g = giftRepository.findById(id);
        if(g.isPresent()) {
            Gift currentGift = g.get();

            currentGift.setReserved(gift.getReserved());
            saveGift(currentGift);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);

        }
        else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    public void deleteGiftById(UUID id) {
        giftRepository.deleteById(id);
    }
}
