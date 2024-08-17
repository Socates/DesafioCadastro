package com.br.cadastro.repository;


import com.br.cadastro.model.ClienteModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends MongoRepository<ClienteModel, String> {
   ClienteModel findByCpf(String cpf);
}