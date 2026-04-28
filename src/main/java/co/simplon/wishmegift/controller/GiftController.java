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


    @Autowired
    private GiftService giftService;

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

    @PatchMapping("/{id}")
    public ResponseEntity<Gift> reserveGift(@PathVariable UUID id, @RequestBody Gift gift) {
        return giftService.updateGift(id, gift);
    }

    @DeleteMapping("/{id}")
    public void deleteGift(@PathVariable UUID id) {
        giftService.deleteGiftById(id);
    }


}
