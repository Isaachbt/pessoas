package com.cadastro.pessoas.service;

import com.cadastro.pessoas.model.PersonModel;
import com.cadastro.pessoas.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private PersonRepository repository;



    public PersonModel saveUser(PersonModel model){
        return repository.save(model);
    }


    public boolean existsByCpf(String cpf){
        return repository.existsByCpf(cpf);
    }
}
