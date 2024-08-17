package com.br.cadastro.service;

import com.br.cadastro.exception.CustomExceptionHandler;
import com.br.cadastro.model.ClienteModel;
import com.br.cadastro.repository.ClienteRepository;
import com.br.cadastro.util.Util;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    private static final Logger logger = Logger.getLogger(ClienteServiceImpl.class.getName());

    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public ClienteModel salvarCliente(ClienteModel cliente) {

        String cpf = cliente.getCpf().replaceAll("\\D", "");

        if (cliente.getCpf() != null && !Util.validarCpf(cpf)) {
            logger.info(cliente.getCpf() + "CPF inválido");
            throw new CustomExceptionHandler.CpfInvalidoException("CPF inválido");
        }

        if (cliente.getCpf() != null && clienteRepository.findByCpf(cpf) != null) {
            logger.info(cliente.getCpf() + "CPF já cadastrado");
            throw new CustomExceptionHandler.CpfInvalidoException("CPF já cadastrado");
        }

        cliente.setCpf(cpf);

        return clienteRepository.save(cliente);
    }

    public ClienteModel atualizarCliente(String id, ClienteModel cliente) {
        if (!clienteRepository.existsById(id)) {
            logger.info("Cliente não encontrado");
            throw new CustomExceptionHandler.ClienteNaoEncontratoException("Cliente não encontrado");
        }
        cliente.setId(id);
        return clienteRepository.save(cliente);
    }

    @Override
    public ClienteModel buscarClientePorId(String id) {
        return clienteRepository.findById(id).orElseThrow(() -> new CustomExceptionHandler.ClienteNaoEncontratoException("Cliente não encontrado"));
    }

    @Override
    public ClienteModel buscarClientePorCpf(String cpf) {
        cpf = cpf.replaceAll("\\D", "");
        ClienteModel cliente = clienteRepository.findByCpf(cpf);
        if (cliente == null) {
            logger.info("Cliente não encontrado com o CPF: " + cpf);
            throw new CustomExceptionHandler.ClienteNaoEncontratoException("Cliente não encontrado com o CPF: " + cpf);
        }
        return cliente;
    }

    @Override
    public List<ClienteModel> listarClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public void deletarCliente(String id) {
        clienteRepository.deleteById(id);
    }

}