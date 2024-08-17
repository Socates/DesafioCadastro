package com.br.cadastro.controller;

import com.br.cadastro.model.ClienteModel;
import com.br.cadastro.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Cliente", description = "Endpoints para gerenciar clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping("/cliente")
    @Operation(summary = "Cria um novo cliente", description = "Cria um novo cliente no sistema.")
    @ApiResponse(responseCode = "201", description = "Cliente criado com sucesso")
    @ApiResponse(responseCode = "400", description = "Erro na validação do cliente")
    public ResponseEntity<ClienteModel> criarCliente(@RequestBody ClienteModel cliente) {
        ClienteModel novoCliente = clienteService.salvarCliente(cliente);
        return new ResponseEntity<>(novoCliente, HttpStatus.CREATED);
    }

    @PutMapping("/cliente/{id}")
    @Operation(summary = "Atualiza um cliente existente", description = "Atualiza as informações de um cliente existente.")
    @ApiResponse(responseCode = "200", description = "Cliente atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    public ResponseEntity<ClienteModel> atualizarCliente(@PathVariable String id, @RequestBody ClienteModel cliente) {
        ClienteModel clienteAtualizado = clienteService.atualizarCliente(id, cliente);
        return new ResponseEntity<>(clienteAtualizado, HttpStatus.OK);
    }

    @GetMapping("/cliente/{id}")
    @Operation(summary = "Obtém um cliente pelo ID", description = "Retorna as informações de um cliente com base no ID fornecido.")
    @ApiResponse(responseCode = "200", description = "Cliente encontrado")
    @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    public ResponseEntity<ClienteModel> obterCliente(@PathVariable String id) {
        ClienteModel cliente = clienteService.buscarClientePorId(id);
        return ResponseEntity.ok(cliente);
    }

    @GetMapping("/cliente/cpf/{cpf}")
    @Operation(summary = "Obtém um cliente pelo CPF", description = "Retorna as informações de um cliente com base no CPF fornecido.")
    @ApiResponse(responseCode = "200", description = "Cliente encontrado")
    @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    public ResponseEntity<ClienteModel> obterClientePorCpf(@PathVariable String cpf) {
        ClienteModel cliente = clienteService.buscarClientePorCpf(cpf);
        return ResponseEntity.ok(cliente);
    }

    @GetMapping("/cliente")
    @Operation(summary = "Lista todos os clientes", description = "Retorna uma lista de todos os clientes cadastrados.")
    @ApiResponse(responseCode = "200", description = "Lista de clientes retornada com sucesso")
    public ResponseEntity<List<ClienteModel>> listarClientes() {
        List<ClienteModel> clientes = clienteService.listarClientes();
        return ResponseEntity.ok(clientes);
    }

    @DeleteMapping("/cliente/{id}")
    @Operation(summary = "Deleta um cliente", description = "Remove um cliente do sistema com base no ID fornecido.")
    @ApiResponse(responseCode = "204", description = "Cliente removido com sucesso")
    @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    public ResponseEntity<Void> deletarCliente(@PathVariable String id) {
        clienteService.deletarCliente(id);
        return ResponseEntity.noContent().build();
    }
}
