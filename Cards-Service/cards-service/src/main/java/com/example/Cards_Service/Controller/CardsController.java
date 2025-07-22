package com.example.Cards_Service.Controller;

import com.example.Cards_Service.Model.CardRequest;
import com.example.Cards_Service.Model.Tarjeta;
import com.example.Cards_Service.Service.CardsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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


    @Operation(summary = "Get card by userId",
            description = "Retrieve a list of cards associated with a specific userId. If no cards are found, an empty list will be returned."
    )
    @ApiResponse(responseCode = "200", description = "Cards retrieved successfully")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @GetMapping()
    public ResponseEntity<List<Tarjeta>> getCardByUserId(@RequestParam("userId") String userId){
        List<Tarjeta> tarjetas = cardsService.getUserCardByUserId(userId);
        return ResponseEntity.ok(tarjetas);
    }

    @Operation(summary = "Get card by cardId",
            description = "Retrieve a card using its cardId. If the card does not exist, a 404 error will be returned."
    )
    @ApiResponse(responseCode = "200", description = "Card retrieved successfully")
    @ApiResponse(responseCode = "404", description = "Card not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @GetMapping("/{cardId}")
    public  ResponseEntity<?> getCardByCardId (@PathVariable("cardId") String cardId){
        Tarjeta tarjeta = cardsService.getUserCardByIdCard(cardId);
        return ResponseEntity.ok(tarjeta);
    }

    @Operation(summary = "Delete card by cardId",
            description = "Delete a card using its cardId. If the card does not exist, a 404 error will be returned."
    )
    @ApiResponse(responseCode = "200", description = "Card deleted successfully")
    @ApiResponse(responseCode = "404", description = "Card not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @DeleteMapping("/{cardId}")
    public ResponseEntity<?> deleteCardByCardId(@PathVariable("cardId") String cardId){
        Tarjeta tarjeta = cardsService.deleteUserCardByIdCard(cardId);
        return ResponseEntity.ok(tarjeta);
    }

    @Operation(summary = "Create a new card",
            description = "Create a new card for a user. The request body should contain the card details."
    )
    @ApiResponse(responseCode = "200", description = "Card created successfully")
    @ApiResponse(responseCode = "400", description = "Bad request, invalid input data")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @PostMapping()
    public ResponseEntity<?> createCard(@RequestParam("userId") String userId, @RequestBody CardRequest cardRequest){
            return ResponseEntity.ok(cardsService.createCards(userId,cardRequest));

    }


}
