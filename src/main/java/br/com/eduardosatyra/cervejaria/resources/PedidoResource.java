/**
 * 
 */
package br.com.eduardosatyra.cervejaria.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.eduardosatyra.cervejaria.domain.Pedido;
import br.com.eduardosatyra.cervejaria.services.PedidoService;

/**
 * @author eduardosatyra
 *
 */

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {

	@Autowired
	PedidoService pedidoService;

	/**
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Pedido> find(@PathVariable Integer id) {
		Pedido cliente = pedidoService.find(id);

		return ResponseEntity.ok(cliente);
	}

}
