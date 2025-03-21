package one.digitalinnovation.gof.service.impl;

import one.digitalinnovation.gof.enums.ClienteEventEnum;
import one.digitalinnovation.gof.model.Cliente;
import one.digitalinnovation.gof.model.Endereco;
import one.digitalinnovation.gof.observer.ClienteNotifierService;
import one.digitalinnovation.gof.repository.ClienteRepository;
import one.digitalinnovation.gof.repository.EnderecoRepository;
import one.digitalinnovation.gof.service.ClienteService;
import one.digitalinnovation.gof.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

/**
 * Implementação da <b>Strategy</b> {@link ClienteService}, a qual pode ser
 * injetada pelo Spring (via {@link Autowired}). Com isso, como essa classe é um
 * {@link Service}, ela será tratada como um <b>Singleton</b>.
 * 
 * @author falvojr
 */
@Service
public class ClienteServiceImpl implements ClienteService {

	// Singleton: Injetar os componentes do Spring com @Autowired.
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private ViaCepService viaCepService;
	@Autowired
	private ClienteNotifierService clienteNotifierService;
	
	// Strategy: Implementar os métodos definidos na interface.
	// Facade: Abstrair integrações com subsistemas, provendo uma interface simples.

	@Override
	public Iterable<Cliente> buscarTodos() {
		// Buscar todos os Clientes.
		return clienteRepository.findAll();
	}

	@Override
	public Cliente buscarPorId(Long id) {
		// Buscar Cliente por ID.
		Optional<Cliente> cliente = clienteRepository.findByIdAndAtivoIsTrue(id);
		return cliente.get();
	}

	@Override
	@Transactional
	public void inserir(Cliente cliente) {
		salvarClienteComCep(cliente);
	}

	@Override
	@Transactional
	public void atualizar(Long id, Cliente cliente) {
		if (clienteRepository.existsByIdAndAtivoIsTrue(id)) {
			cliente.setId(id);
			salvarClienteComCep(cliente);
		}
	}

	@Override
	@Transactional
	public void deletar(Long id) {
		// Deletar Cliente por ID.
		Cliente cliente = clienteRepository.findByIdAndAtivoIsTrue(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"Cliente não encontrado"));

		cliente.setAtivo(false);
		clienteRepository.save(cliente);
		clienteNotifierService.notify(ClienteEventEnum.LOG, cliente);
		clienteNotifierService.notify(ClienteEventEnum.ENVIAR_EMAIL, cliente);
	}

	private void salvarClienteComCep(Cliente cliente) {
		// Verificar se o Endereco do Cliente já existe (pelo CEP).
		String cep = cliente.getEndereco().getCep();
		Endereco endereco = enderecoRepository.findByCep(cep).orElseGet(() -> {
			// Caso não exista, integrar com o ViaCEP e persistir o retorno.
			Endereco novoEndereco = viaCepService.consultarCep(cep);
			if (cliente.getId() != null) {
				clienteNotifierService.notify(ClienteEventEnum.ENVIAR_EMAIL, cliente);
			}
			enderecoRepository.save(novoEndereco);
			return novoEndereco;
		});
		cliente.setEndereco(endereco);
		// Inserir Cliente, vinculando o Endereco (novo ou existente).
		clienteRepository.save(cliente);
		clienteNotifierService.notify(ClienteEventEnum.LOG, cliente);
	}

}
