package co.simplon.wishmegift.controller;

import co.simplon.wishmegift.dto.GiftDTO;
import co.simplon.wishmegift.entity.Gift;
import co.simplon.wishmegift.service.GiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public List<GiftDTO> getAllGifts() {
        return giftService.getAllGifts();
    }

    @GetMapping("/{id}")
    public Optional<GiftDTO> getGiftById(@PathVariable UUID id) {
        return giftService.getGiftById(id);
    }

    @PostMapping()
    public ResponseEntity<GiftDTO> createGift(@RequestBody GiftDTO giftDTO) {
        GiftDTO newGift = giftService.createGift(giftDTO);
        return new ResponseEntity<>(newGift, HttpStatus.CREATED);
    }

    @PatchMapping("/{giftId}/wishlist/{wishlistId}/guest/{guestId}")
    public ResponseEntity<GiftDTO> reserveGift(@PathVariable UUID giftId, @PathVariable UUID wishlistId, @PathVariable UUID guestId) {
        Optional<GiftDTO> giftDTOOptional = giftService.reserveGift(giftId, wishlistId, guestId);
        return giftDTOOptional.map(gift -> new ResponseEntity<>(gift, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping("/{id}")
    public void deleteGift(@PathVariable UUID id) {
        giftService.deleteGiftById(id);
    }


}
