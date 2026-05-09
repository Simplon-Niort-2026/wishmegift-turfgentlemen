package co.simplon.wishmegift.controller;

import co.simplon.wishmegift.dto.WishListDTO;
import co.simplon.wishmegift.entity.WishList;
import co.simplon.wishmegift.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/wishlist")
public class WishListController {

    private final WishListService wishListService;

    public WishListController(WishListService wishListService) {
        this.wishListService = wishListService;
    }

    @GetMapping
    public ResponseEntity<List<WishListDTO>> getWishList() {
        return ResponseEntity.ok(wishListService.getWishLists());
    }

    @GetMapping("/guest/{guestId}")
    public ResponseEntity<List<WishListDTO>> getGuestWishLists(@PathVariable UUID guestId){
        return ResponseEntity.ok(wishListService.getGuestWishLists(guestId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<WishListDTO> getWishListById(@PathVariable UUID id) {
        Optional<WishListDTO> wishListDTOOptional = wishListService.getWishListById(id);
        return wishListDTOOptional.map(wishListDTO -> new ResponseEntity<>(wishListDTO, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{ownerId}")
    public ResponseEntity<WishListDTO> createWishList(@PathVariable UUID ownerId, @RequestBody WishListDTO wishListDTO) {
        Optional<WishListDTO> wishListDTOOptional = wishListService.createWishList(ownerId, wishListDTO);
        return wishListDTOOptional.map(wishList -> new ResponseEntity<>(wishList, HttpStatus.CREATED)).orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @PatchMapping("/share/{wishListId}/{guestId}")
    public ResponseEntity<WishListDTO> addGuestToWishList(@PathVariable UUID wishListId, @PathVariable UUID guestId) {
        Optional<WishListDTO> wishListDTOOptional = wishListService.addGuestToWishList(wishListId, guestId);
        return wishListDTOOptional.map(wishlist -> new ResponseEntity<>(wishlist, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @PatchMapping("/{wishListId}/owner/{ownerId}/addgift/{giftId}")
    public ResponseEntity<WishListDTO> addGiftToWishList(@PathVariable UUID wishListId, @PathVariable UUID ownerId, @PathVariable UUID giftId) {
        Optional<WishListDTO> wishListDTOOptional = wishListService.addGiftToWishList(wishListId, ownerId,  giftId);
        return wishListDTOOptional.map(wishlist -> new ResponseEntity<>(wishlist, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @PatchMapping("/{wishListId}/owner/{ownerId}/removegift/{giftId}")
    public ResponseEntity<WishListDTO> removeGiftToWishList(@PathVariable UUID wishListId, @PathVariable UUID ownerId, @PathVariable UUID giftId) {
        Optional<WishListDTO> wishListDTOOptional = wishListService.removeGiftToWishList(wishListId, ownerId,  giftId);
        return wishListDTOOptional.map(wishlist -> new ResponseEntity<>(wishlist, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

}
