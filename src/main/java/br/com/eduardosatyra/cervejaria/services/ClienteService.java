/**
 * 
 */
package br.com.eduardosatyra.cervejaria.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eduardosatyra.cervejaria.domain.Cliente;
import br.com.eduardosatyra.cervejaria.repositories.ClienteRepository;
import br.com.eduardosatyra.cervejaria.services.exceptions.ObjNotFoundException;

/**
 * @author eduardosatyra
 *
 */

@Service
public class ClienteService {
	
	@Autowired
	ClienteRepository clienteRepository;
	
	public Cliente buscar(Integer id) {
		Optional<Cliente> obj = clienteRepository.findById(id);

		return obj.orElseThrow(() -> new ObjNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}

}
