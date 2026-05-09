package co.simplon.wishmegift.controller;

import co.simplon.wishmegift.dto.GiftDTO;
import co.simplon.wishmegift.exception.ResourceNotFoundException;
import co.simplon.wishmegift.service.GiftService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/gift")
public class GiftController {

    private final GiftService giftService;

    public GiftController(GiftService giftService) {
        this.giftService = giftService;
    }

    @GetMapping()
    public ResponseEntity<List<GiftDTO>> getAllGifts() {
        return ResponseEntity.ok(giftService.getAllGifts());
    }

    @GetMapping("/{giftId}")
    public ResponseEntity<GiftDTO> getGiftById(@PathVariable Long giftId) {
        Optional<GiftDTO> giftDTOOptional = giftService.getGiftById(giftId);
        return giftDTOOptional.map(gift -> new ResponseEntity<>(gift, HttpStatus.OK) ).orElseThrow(() -> new ResourceNotFoundException("Aucun cadeau trouvé"));
    }

    @PostMapping("/{userId}")
    public ResponseEntity<GiftDTO> createGift(@PathVariable Long userId, @RequestBody GiftDTO giftDTO) {
        Optional<GiftDTO> giftDTOOptional = giftService.createGift(userId, giftDTO);
        return giftDTOOptional.map(gift -> new ResponseEntity<>(gift, HttpStatus.CREATED)).orElseThrow(() -> new ResourceNotFoundException("Vous devez être un utilisateur enregistré pour créer un cadeau"));
    }

    @PatchMapping("/{giftId}/wishlist/{wishlistId}/guest/{guestId}")
    public ResponseEntity<GiftDTO> reserveGift(@PathVariable Long giftId, @PathVariable Long wishlistId, @PathVariable Long guestId) {
        Optional<GiftDTO> giftDTOOptional = giftService.reserveGift(giftId, wishlistId, guestId);
        return giftDTOOptional.map(gift -> new ResponseEntity<>(gift, HttpStatus.OK)).orElseThrow(() -> new RuntimeException("Autorisation refusée"));
    }

    @DeleteMapping("/{giftId}")
    public void deleteGift(@PathVariable Long giftId) {
        giftService.deleteGiftById(giftId);
    }


}
