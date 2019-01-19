package br.com.eduardosatyra.cervejaria.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eduardosatyra.cervejaria.domain.Pedido;
import br.com.eduardosatyra.cervejaria.repositories.PedidoRepository;
import br.com.eduardosatyra.cervejaria.services.exceptions.ObjNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;

	/**
	 * 
	 * @param id
	 * @return
	 */
	public Pedido find(Integer id) {
		Optional<Pedido> obj = repo.findById(id);

		return obj.orElseThrow(() -> new ObjNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}

}
