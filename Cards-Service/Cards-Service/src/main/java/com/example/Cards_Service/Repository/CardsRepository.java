package com.example.Cards_Service.Repository;

import com.example.Cards_Service.Model.Tarjeta;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface CardsRepository extends JpaRepository<Tarjeta,String> {
    List<Tarjeta> findAllByUserId(String userId);
    boolean existsByNumber(String number);

}
