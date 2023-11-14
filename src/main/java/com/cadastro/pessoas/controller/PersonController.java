package com.cadastro.pessoas.controller;

import com.cadastro.pessoas.dto.PersonRecordDto;
import com.cadastro.pessoas.model.PersonModel;
import com.cadastro.pessoas.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;


@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService service;

    @PostMapping
    public ResponseEntity<Object> saveUser(@RequestBody @Valid PersonRecordDto dto){
        if (service.existsByCpf(dto.cpf())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("CONFLICT: Cpf exists in the database");
        }

        var personModel = new PersonModel();
        BeanUtils.copyProperties(dto,personModel);
        personModel.setDateCreated(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveUser(personModel));
    }
}

