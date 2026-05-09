package co.simplon.wishmegift.mapper;

import co.simplon.wishmegift.dto.WishListDTO;
import co.simplon.wishmegift.entity.WishList;
import org.springframework.stereotype.Component;

@Component
public class WishListMapper {

    public WishListDTO toWishListDTO(WishList wishList) {
        WishListDTO wishListDTO = new WishListDTO();
        wishListDTO.setId(wishList.getId());
        wishListDTO.setListName(wishList.getListName());
        wishListDTO.setDescription(wishList.getDescription());
        wishListDTO.setEventDate(wishList.getEventDate());
        wishListDTO.setTheme(wishList.getTheme());
        return wishListDTO;
    }

    public WishList toWishList(WishListDTO wishListDTO) {
        WishList wishList = new WishList();
        wishList.setId(wishListDTO.getId());
        wishList.setListName(wishListDTO.getListName());
        wishList.setDescription(wishListDTO.getDescription());
        wishList.setEventDate(wishListDTO.getEventDate());
        wishList.setTheme(wishListDTO.getTheme());
        return wishList;
    }
}
