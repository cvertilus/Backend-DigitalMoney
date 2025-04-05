package com.example.User_Service.User_Service.UserCardPackage.Controller;

import com.example.User_Service.User_Service.UserCardPackage.Model.Cards;
import com.example.User_Service.User_Service.UserCardPackage.Service.FeignCardsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/users")
public class FeinCardsController {
    private final FeignCardsService feignCardsService;


    public FeinCardsController(FeignCardsService feignCardsService) {
        this.feignCardsService = feignCardsService;
    }

    @GetMapping("/{userId}/cards")
    public ResponseEntity<List<Cards>> getUserCards (@PathVariable("userId") String userId){
        return feignCardsService.getCardsByUserId(userId);
    }

    @GetMapping ("/{userId}/cards/{cardId}")
    public ResponseEntity<Cards> getUserCard (@PathVariable("cardId") String cardId){
        return feignCardsService.getCardsyIdCard(cardId);
    }

    @PostMapping ("/{userId}/cards")
    public ResponseEntity<Cards> createCards (@PathVariable("userId") String userId , @RequestBody Cards cards){
        return feignCardsService.createCard(userId, cards);
    }

    @DeleteMapping("/{userId}/cards/{cardId}")
    public  ResponseEntity<Cards> deleteCard (@PathVariable("cardId") String cardId){
        return feignCardsService.deleteCardsById(cardId);
    }
}
