package com.cadastro.pessoas.service;

import com.cadastro.pessoas.model.PersonModel;
import com.cadastro.pessoas.repository.PersonRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {

    @Autowired
    private PersonRepository repository;



    @Transactional
    public PersonModel saveUser(PersonModel model){
        return repository.save(model);
    }


    public List<PersonModel> getUsers()
    {
        return repository.findAll();
    }
    public Optional<PersonModel> findOneUser(UUID id){
        return repository.findById(id);
    }


    public boolean existsByCpf(String cpf){
        return repository.existsByCpf(cpf);
    }
}
