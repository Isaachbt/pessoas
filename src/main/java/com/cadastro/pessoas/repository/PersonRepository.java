package com.cadastro.pessoas.repository;

import com.cadastro.pessoas.model.PersonModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PersonRepository extends JpaRepository<PersonModel, UUID> {
   boolean existsByCpf(String cpf);
}
