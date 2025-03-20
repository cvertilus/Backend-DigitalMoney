package com.example.Cards_Service.Service;

import com.example.Cards_Service.Model.CardRequest;
import com.example.Cards_Service.Model.Tarjeta;
import com.example.Cards_Service.Repository.CardsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CardsService {
    @Autowired
    private CardsRepository cardsRepository;

    public Tarjeta createCards(String userId, CardRequest cardRequest ){
        if(cardsRepository.existsByNumber(cardRequest.getNumber()))
            throw new RuntimeException("Ya existe una tarjeta con ese Numero " + cardRequest.getNumber());
        Tarjeta tarjeta =  Tarjeta.builder()
                .cvc(cardRequest.getCvc())
                .name(cardRequest.getName())
                .expiration(cardRequest.getExpiration())
                .number(cardRequest.getNumber())
                .userId(userId)
                .build();
        return cardsRepository.save(tarjeta);
    }

    public Tarjeta getUserCardByIdCard (String cardId){
        return cardsRepository.findById(cardId).orElseThrow(()-> new RuntimeException("No existe el usuario " + cardId));
    }

    public Tarjeta deleteUserCardByIdcard (String cardId){
        Tarjeta card = getUserCardByIdCard(cardId);
        if(card == null) throw  new RuntimeException("la tarjeta no exite");
        cardsRepository.deleteById(cardId);
        return card;
    }

    public List<Tarjeta> getUserCardByUserId(String userId){
        return cardsRepository.findByUserId(userId).orElseThrow(() -> new RuntimeException("no exite el user id " + userId));
    }



}
