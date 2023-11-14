package com.cadastro.pessoas.controller;

import com.cadastro.pessoas.dto.PersonRecordDto;
import com.cadastro.pessoas.model.PersonModel;
import com.cadastro.pessoas.service.PersonService;
import jakarta.persistence.PostUpdate;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


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

    @GetMapping
    public ResponseEntity<List<PersonModel>> getAllUser()
    {
        List<PersonModel> list = service.getUsers();
        if (!list.isEmpty()){
            for (PersonModel person:list)
            {
                UUID id = person.getId();
                person.add(linkTo(methodOn(PersonController.class).getOneUser(id)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneUser(@PathVariable(value = "id")UUID id){
        Optional<PersonModel> optional = service.findOneUser(id);
        if (optional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        optional.get().add(linkTo(methodOn(PersonController.class).getAllUser()).withSelfRel());
        return ResponseEntity.status(HttpStatus.OK).body(optional.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable(value = "id")UUID id,@RequestBody @Valid PersonRecordDto dto){

        Optional<PersonModel> optional = service.findOneUser(id);
        if (optional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        PersonModel person = optional.get();
        BeanUtils.copyProperties(dto,person);
        person.setDateCreated(person.getDateCreated());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(service.saveUser(person));
    }
}

