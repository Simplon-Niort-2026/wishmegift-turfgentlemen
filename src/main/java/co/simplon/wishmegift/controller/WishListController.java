package co.simplon.wishmegift.controller;

import co.simplon.wishmegift.entity.WishList;
import co.simplon.wishmegift.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/wishlist")
public class WishListController {
    @Autowired
    private WishListService wishListService;

    @GetMapping
    public Iterable<WishList> getWishList() {
        return wishListService.getWishLists();
    }

    @GetMapping("/{id}")
    public Optional<WishList> getWishListById(@PathVariable UUID id) {
        return wishListService.getWishListById(id);
    }

    @PostMapping("/{ownerId}")
    public ResponseEntity<WishList> createWishList(@PathVariable UUID ownerId, @RequestBody WishList wishList) {

        return wishListService.createWishList(id,wishList);
    }

    @PatchMapping("/share/{wishListId}/{guestId}")
    public ResponseEntity<WishList> addGuestToWishList(@PathVariable UUID wishListId, @PathVariable UUID guestId ) {
        return wishListService.addGuestToWishList(wishListId,guestId);
    }

}
