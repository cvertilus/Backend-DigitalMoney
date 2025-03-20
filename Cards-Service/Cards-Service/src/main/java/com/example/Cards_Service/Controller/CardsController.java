package com.example.Cards_Service.Controller;

import com.example.Cards_Service.Model.CardRequest;
import com.example.Cards_Service.Model.Tarjeta;
import com.example.Cards_Service.Service.CardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/cards")
public class CardsController {

    @Autowired
    private CardsService cardsService;

    @GetMapping()
    public ResponseEntity<?> getCardByUserId(@RequestParam("userId") String userId){
        try {
            return ResponseEntity.ok(cardsService.getUserCardByIdCard(userId));
        }catch (RuntimeException r){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(r.getMessage());
        }
    }
    @GetMapping("/{cardId}")
    public  ResponseEntity<?> getCardByCardId (@PathVariable("cardId") String cardId){
        try{
            return ResponseEntity.ok(cardsService.getUserCardByIdCard(cardId));
        } catch (RuntimeException r) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(r.getMessage());
        }
    }

    @DeleteMapping("/{cardId}")
    public ResponseEntity<?> deleteCardByCardId(@PathVariable("cardId") String cardId){
        try {
            return ResponseEntity.ok(cardsService.deleteUserCardByIdcard(cardId));
        } catch (RuntimeException r) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(r.getMessage());
        }
    }

    @PostMapping()
    public ResponseEntity<?> createCard(@RequestParam("userId") String userId, @RequestBody CardRequest cardRequest){
        try {
            return ResponseEntity.ok(cardsService.createCards(userId,cardRequest));
        } catch (RuntimeException e) {
          return ResponseEntity.badRequest().body(e.getMessage());
        }
    }





}
