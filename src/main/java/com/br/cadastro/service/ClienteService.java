package com.br.cadastro.service;

import com.br.cadastro.model.ClienteModel;

import java.util.List;

public interface ClienteService {

    ClienteModel salvarCliente(ClienteModel cliente);
    ClienteModel atualizarCliente(String id, ClienteModel cliente);
    ClienteModel buscarClientePorId(String id);
    ClienteModel buscarClientePorCpf(String cpf);
    List<ClienteModel> listarClientes();
    void deletarCliente(String id);

}