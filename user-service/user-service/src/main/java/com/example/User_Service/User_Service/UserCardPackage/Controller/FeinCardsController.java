package com.example.User_Service.User_Service.UserCardPackage.Controller;

import com.example.User_Service.User_Service.UserCardPackage.Model.CardRequest;
import com.example.User_Service.User_Service.UserCardPackage.Model.Cards;
import com.example.User_Service.User_Service.UserCardPackage.Service.FeignCardsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping ("/users")
public class FeinCardsController {
    private final FeignCardsService feignCardsService;


    public FeinCardsController(FeignCardsService feignCardsService) {
        this.feignCardsService = feignCardsService;
    }

    @Operation(summary = "Get all cards for a user",
            description = "Retrieve all cards associated with a specific user by userId. " +
                    "If the user does not have any cards, an empty list will be returned.")
    @ApiResponse(responseCode = "200", description = "Cards retrieved successfully")
    @ApiResponse(responseCode = "404", description = "User not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @GetMapping("/{userId}/cards")
    public ResponseEntity<List<Cards>> getUserCards (@PathVariable("userId") String userId){
        return feignCardsService.getCardsByUserId(userId);
    }

    @Operation(summary = "Get a specific card for a user",
            description = "Retrieve a specific card by cardId associated with a user. " +
                    "If the card does not exist, a 404 error will be returned.")
    @ApiResponse(responseCode = "200", description = "Card retrieved successfully")
    @ApiResponse(responseCode = "404", description = "Card not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @GetMapping ("/{userId}/cards/{cardId}")
    public ResponseEntity<Cards> getUserCard (@PathVariable("cardId") String cardId){
        return feignCardsService.getCardsyIdCard(cardId);
    }


    @Operation(summary = "Create a new card for a user",
            description = "Create a new card associated with a specific user by userId. " +
                    "The request body should contain the card details.")
    @ApiResponse(responseCode = "201", description = "Card created successfully")
    @ApiResponse(responseCode = "400", description = "Bad request, invalid input data")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @PostMapping ("/{userId}/cards")
    public ResponseEntity<Cards> createCards (@PathVariable("userId") String userId ,@Valid @RequestBody CardRequest cardRequest){
        return feignCardsService.createCard(userId, cardRequest);
    }


    @Operation(summary = "Delete an existing card for a user by CardId",
            description = "Delete a specific card by cardId associated with a user. " +
                    "If the card does not exist, a 404 error will be returned.")
    @ApiResponse(responseCode = "200", description = "Card deleted successfully")
    @ApiResponse(responseCode = "404", description = "Card not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @DeleteMapping("/{userId}/cards/{cardId}")
    public  ResponseEntity<Cards> deleteCard (@PathVariable("cardId") String cardId){
        return feignCardsService.deleteCardsById(cardId);
    }
}
