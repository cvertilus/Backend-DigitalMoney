package com.example.User_Service.User_Service.UserCardPackage.Service;

import com.example.User_Service.User_Service.UserCardPackage.Model.CardRequest;
import com.example.User_Service.User_Service.UserCardPackage.Model.Cards;
import com.example.User_Service.User_Service.UserCardPackage.Repository.FeingClientCards;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeignCardsService {

    private final FeingClientCards feingClientCards;

    public FeignCardsService(FeingClientCards feingClientCards) {
        this.feingClientCards = feingClientCards;
    }

    public ResponseEntity<List<Cards>> getCardsByUserId(String userId) {
        return feingClientCards.getCardsByUserId(userId);
    }

    public ResponseEntity<Cards> getCardsyIdCard (String cardId){
        return feingClientCards.getCardsByIdCards(cardId);
    }

    public ResponseEntity<Cards> deleteCardsById (String cardId){
        return feingClientCards.deleteCardByIdCard(cardId);
    }

    public ResponseEntity<Cards> createCard(String userId, CardRequest cardRequest) {
        return feingClientCards.createCards(userId,cardRequest);
    }


}
