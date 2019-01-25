package br.com.eduardosatyra.cervejaria.dto;

import java.util.Date;

import br.com.eduardosatyra.cervejaria.domain.Pedido;

/**
 * @author eduardosatyra
 *
 */

//criar para estudo
public class PedidoDTO {

	private int id;
	private Date instante;
	private Double pagamento;
	
	public PedidoDTO() {
	}
	
	public PedidoDTO(Pedido pedido) {
		this.id = pedido.getId();
		this.instante = pedido.getInstante();
		//this.pagamento = pedido.getPagamento();
	}
}
