package com.br.cadastro.service;

import com.br.cadastro.model.ClienteModel;
import com.br.cadastro.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;

class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteServiceImpl clienteService; // Use a implementação concreta

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSalvarCliente() {

        ClienteModel cliente = new ClienteModel();
        cliente.setCpf("73481764022");

        when(clienteRepository.findByCpf(cliente.getCpf())).thenReturn(null);
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        ClienteModel resultado = clienteService.salvarCliente(cliente);

        assertNotNull(resultado);
        assertEquals(cliente.getCpf(), resultado.getCpf());
    }

    @Test
    void testAtualizarCliente() {

        ClienteModel clienteExistente = new ClienteModel();
        clienteExistente.setId("1");
        clienteExistente.setCpf("73481764022");
        clienteExistente.setNome("Usuario teste");

        ClienteModel clienteAtualizado = new ClienteModel();
        clienteAtualizado.setId("1");
        clienteAtualizado.setNome("Usuario Atualizado");

        when(clienteRepository.existsById("1")).thenReturn(true);
        when(clienteRepository.findById("1")).thenReturn(Optional.of(clienteExistente));
        when(clienteRepository.save(clienteAtualizado)).thenReturn(clienteAtualizado);

        ClienteModel resultado = clienteService.atualizarCliente("1", clienteAtualizado);

        assertNotNull(resultado);
        assertEquals("Usuario Atualizado", resultado.getNome());
    }

    @Test
    void testBuscarClientePorId() {

        ClienteModel cliente = new ClienteModel();
        cliente.setId("1");
        cliente.setNome("Teste");

        when(clienteRepository.findById("1")).thenReturn(Optional.of(cliente));

        ClienteModel resultado = clienteService.buscarClientePorId("1");

        assertNotNull(resultado);
        assertEquals("Teste", resultado.getNome());
    }

    @Test
    void testBuscarClientePorCpf() {

        ClienteModel cliente = new ClienteModel();
        cliente.setCpf("73481764022");
        cliente.setNome("teste");

        when(clienteRepository.findByCpf("73481764022")).thenReturn(cliente);

        ClienteModel resultado = clienteService.buscarClientePorCpf("73481764022");

        assertNotNull(resultado);
        assertEquals("teste", resultado.getNome());

    }

}