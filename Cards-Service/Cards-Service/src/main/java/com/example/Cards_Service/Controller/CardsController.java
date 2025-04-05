package com.example.Cards_Service.Controller;

import com.example.Cards_Service.Model.CardRequest;
import com.example.Cards_Service.Model.Tarjeta;
import com.example.Cards_Service.Service.CardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/cards")
public class CardsController {

    @Autowired
    private CardsService cardsService;

    @GetMapping()
    public ResponseEntity<List<Tarjeta>> getCardByUserId(@RequestParam("userId") String userId){
        List<Tarjeta> tarjetas = cardsService.getUserCardByUserId(userId);
        return ResponseEntity.ok(tarjetas);
    }

    @GetMapping("/{cardId}")
    public  ResponseEntity<?> getCardByCardId (@PathVariable("cardId") String cardId){
        Tarjeta tarjeta = cardsService.getUserCardByIdCard(cardId);
        return ResponseEntity.ok(tarjeta);
    }

    @DeleteMapping("/{cardId}")
    public ResponseEntity<?> deleteCardByCardId(@PathVariable("cardId") String cardId){
        Tarjeta tarjeta = cardsService.deleteUserCardByIdcard(cardId);
        return ResponseEntity.ok(tarjeta);
    }

    @PostMapping()
    public ResponseEntity<?> createCard(@RequestParam("userId") String userId, @RequestBody CardRequest cardRequest){
            return ResponseEntity.ok(cardsService.createCards(userId,cardRequest));

    }





}
