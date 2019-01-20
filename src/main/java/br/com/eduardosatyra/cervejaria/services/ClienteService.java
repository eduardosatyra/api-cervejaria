/**
 * 
 */
package br.com.eduardosatyra.cervejaria.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.eduardosatyra.cervejaria.domain.Cidade;
import br.com.eduardosatyra.cervejaria.domain.Cliente;
import br.com.eduardosatyra.cervejaria.domain.Endereco;
import br.com.eduardosatyra.cervejaria.domain.enums.TipoCliente;
import br.com.eduardosatyra.cervejaria.dto.ClienteDTO;
import br.com.eduardosatyra.cervejaria.dto.ClienteNewDTO;
import br.com.eduardosatyra.cervejaria.repositories.ClienteRepository;
import br.com.eduardosatyra.cervejaria.repositories.EnderecoRepository;
import br.com.eduardosatyra.cervejaria.services.exceptions.DataIntegrityException;
import br.com.eduardosatyra.cervejaria.services.exceptions.ObjNotFoundException;

/**
 * @author eduardosatyra
 *
 */

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	public Cliente find(Integer id) {
		Optional<Cliente> obj = clienteRepository.findById(id);

		return obj.orElseThrow(() -> new ObjNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}

	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = clienteRepository.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}

	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return clienteRepository.save(newObj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			clienteRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível deletar porque a pedidos relacionadas");
		}
	}

	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return clienteRepository.findAll(pageRequest);
	}

	public Cliente parseToDto(ClienteDTO clienteDto) {
		return new Cliente(clienteDto.getId(), clienteDto.getNome(), clienteDto.getEmail(), null, null);
	}

	public Cliente parseToDto(ClienteNewDTO clienteDto) {
		Cliente cliente = new Cliente(null, clienteDto.getNome(), clienteDto.getEmail(), clienteDto.getCpfOuCnpj(),
				TipoCliente.toEnum(clienteDto.getTipo()));
		Cidade cid = new Cidade(clienteDto.getCidadeId(), null, null);
		Endereco end = new Endereco(null, clienteDto.getLogradouro(), clienteDto.getNumero(),
				clienteDto.getComplemento(), clienteDto.getBairro(), clienteDto.getCep(), cliente, cid);
		cliente.getEnderecos().add(end);
		cliente.getTelefones().add(clienteDto.getTelefone1());
		if (clienteDto.getTelefone2() != null) {
			cliente.getTelefones().add(clienteDto.getTelefone2());
		}
		if (clienteDto.getTelefone3() != null) {
			cliente.getTelefones().add(clienteDto.getTelefone3());
		}
		return cliente;
	}

	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
}
