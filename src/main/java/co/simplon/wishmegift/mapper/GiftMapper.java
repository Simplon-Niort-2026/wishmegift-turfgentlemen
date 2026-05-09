package co.simplon.wishmegift.mapper;

import co.simplon.wishmegift.dto.GiftDTO;
import co.simplon.wishmegift.entity.Gift;
import org.springframework.stereotype.Component;

@Component
public class GiftMapper {

    public Gift toGift(GiftDTO giftDTO) {
        Gift gift = new Gift();
        gift.setGiftId(giftDTO.getId());
        gift.setGiftName(giftDTO.getGiftName());
        gift.setGiftDescription(giftDTO.getGiftDescription());
        gift.setGiftLink(giftDTO.getGiftLink());
        gift.setPrice(giftDTO.getPrice());
        gift.setReserved(giftDTO.getReserved());
        gift.setWishList(giftDTO.getWishList());
        return gift;
    }

    public GiftDTO toGiftDTO(Gift gift) {
        GiftDTO giftDTO = new GiftDTO();
        giftDTO.setId(gift.getGiftId());
        giftDTO.setGiftName(gift.getGiftName());
        giftDTO.setGiftDescription(gift.getGiftDescription());
        giftDTO.setGiftLink(gift.getGiftLink());
        giftDTO.setPrice(gift.getPrice());
        giftDTO.setReserved(gift.getReserved());
        giftDTO.setWishList(gift.getWishList());
        return giftDTO;
    }
}
