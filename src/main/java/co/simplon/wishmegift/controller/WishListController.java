package co.simplon.wishmegift.controller;

import co.simplon.wishmegift.dto.WishListDTO;
import co.simplon.wishmegift.entity.WishList;
import co.simplon.wishmegift.exception.ResourceNotFoundException;
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
    public ResponseEntity<List<WishListDTO>> getGuestWishLists(@PathVariable Long guestId){
        return ResponseEntity.ok(wishListService.getGuestWishLists(guestId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<WishListDTO> getWishListById(@PathVariable Long id) {
        Optional<WishListDTO> wishListDTOOptional = wishListService.getWishListById(id);
        return wishListDTOOptional.map(wishList -> new ResponseEntity<>(wishList, HttpStatus.OK)).orElseThrow(() -> new ResourceNotFoundException("Aucune liste trouvée"));
    }

    @PostMapping("/{ownerId}")
    public ResponseEntity<WishListDTO> createWishList(@PathVariable Long ownerId, @RequestBody WishListDTO wishListDTO) {
        Optional<WishListDTO> wishListDTOOptional = wishListService.createWishList(ownerId, wishListDTO);
        return wishListDTOOptional.map(wishList -> new ResponseEntity<>(wishList, HttpStatus.CREATED)).orElseThrow(() -> new ResourceNotFoundException("Vous devez être un utilisateur enregistré pour créer une liste"));
    }

    @PatchMapping("/share/{wishListId}/{guestId}")
    public ResponseEntity<WishListDTO> addGuestToWishList(@PathVariable Long wishListId, @PathVariable Long guestId) {
        Optional<WishListDTO> wishListDTOOptional = wishListService.addGuestToWishList(wishListId, guestId);
        return wishListDTOOptional.map(wishlist -> new ResponseEntity<>(wishlist, HttpStatus.OK)).orElseThrow(() ->new ResourceNotFoundException("Liste ou cadeau introuvable"));
    }

    @PatchMapping("/{wishListId}/owner/{ownerId}/addgift/{giftId}")
    public ResponseEntity<WishListDTO> addGiftToWishList(@PathVariable Long wishListId, @PathVariable Long ownerId, @PathVariable Long giftId) {
        Optional<WishListDTO> wishListDTOOptional = wishListService.addGiftToWishList(wishListId, ownerId,  giftId);
        return wishListDTOOptional.map(wishlist -> new ResponseEntity<>(wishlist, HttpStatus.OK)).orElseThrow(() -> new RuntimeException("Autorisation refusée"));
    }

    @PatchMapping("/{wishListId}/owner/{ownerId}/removegift/{giftId}")
    public ResponseEntity<WishListDTO> removeGiftToWishList(@PathVariable Long wishListId, @PathVariable Long ownerId, @PathVariable Long giftId) {
        Optional<WishListDTO> wishListDTOOptional = wishListService.removeGiftToWishList(wishListId, ownerId,  giftId);
        return wishListDTOOptional.map(wishlist -> new ResponseEntity<>(wishlist, HttpStatus.OK)).orElseThrow(() -> new RuntimeException("Autorisation refusée"));
    }

}
