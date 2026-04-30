package co.simplon.wishmegift.controller;

import co.simplon.wishmegift.entity.Gift;
import co.simplon.wishmegift.service.GiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/gift")
public class GiftController {

    private final GiftService giftService;

    public GiftController(GiftService giftService) {
        this.giftService = giftService;
    }

    @GetMapping()
    public Iterable<Gift> getAllGifts() {
        return giftService.getAllGifts();
    }

    @GetMapping("/{id}")
    public Optional<Gift> getGiftById(@PathVariable UUID id) {
        return giftService.getGiftById(id);
    }

    @PostMapping()
    public Gift createGift(@RequestBody Gift gift) {
        return giftService.saveGift(gift);
    }

    @PatchMapping("/{giftId}/wishlist/{wishlistId}/guest/{guestId}")
    public ResponseEntity<Gift> reserveGift(@PathVariable UUID giftId, @PathVariable UUID wishlistId, @PathVariable UUID guestId) {
        return giftService.reserveGift(giftId, wishlistId, guestId);
    }

    @DeleteMapping("/{id}")
    public void deleteGift(@PathVariable UUID id) {
        giftService.deleteGiftById(id);
    }


}
