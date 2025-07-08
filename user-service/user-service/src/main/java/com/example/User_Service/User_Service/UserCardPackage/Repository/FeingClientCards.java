package com.example.User_Service.User_Service.UserCardPackage.Repository;

import com.example.User_Service.User_Service.Configuration.FeignClientConfiguration;
import com.example.User_Service.User_Service.UserCardPackage.Model.Cards;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient (
        name = "cards-service",
        url = "http://localhost:8085",
        configuration = FeignClientConfiguration.class
)
public interface FeingClientCards {

    @RequestMapping(method = RequestMethod.GET, value = "/cards")
    ResponseEntity<List<Cards>> getCardsByUserId(@RequestParam("userId") String userId);

    @RequestMapping(method = RequestMethod.GET, value = "/cards/{id}")
    ResponseEntity<Cards> getCardsByIdCards(@PathVariable("id") String id);

    @RequestMapping(method = RequestMethod.DELETE, value = "/cards/{id}")
    ResponseEntity<Cards> deleteCardByIdCard (@PathVariable("id") String id);

    @RequestMapping(method = RequestMethod.POST, value = "/cards")
    ResponseEntity<Cards> createCards (@RequestParam("userId") String userId,@RequestBody Cards cards);



}
