package br.com.eduardosatyra.cervejaria.services.validation;

/**
 * @author eduardosatyra
 *
 */
import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.eduardosatyra.cervejaria.domain.Cliente;
import br.com.eduardosatyra.cervejaria.domain.enums.TipoCliente;
import br.com.eduardosatyra.cervejaria.dto.ClienteNewDTO;
import br.com.eduardosatyra.cervejaria.repositories.ClienteRepository;
import br.com.eduardosatyra.cervejaria.resources.exceptions.FieldMessage;
import br.com.eduardosatyra.cervejaria.services.validation.br.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		if(objDto.getTipo().equals(TipoCliente.PESSOA_FISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CPF invalido"));
		}
		if(objDto.getTipo().equals(TipoCliente.PESSOA_JURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ invalido"));
		}
		Cliente cliente = clienteRepository.findByEmail(objDto.getEmail());
		
		if(cliente != null) {
			list.add(new FieldMessage("email", "E-mail já cadastrado"));
		}
		
		// inclua os testes aqui, inserindo erros na lista
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}