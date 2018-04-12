package br.com.neja.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.neja.domain.Cliente;
import br.com.neja.domain.enums.TipoCliente;
import br.com.neja.dto.ClienteNewDTO;
import br.com.neja.repositories.ClienteRepository;
import br.com.neja.resources.exceptions.FieldMessage;
import br.com.neja.services.validation.util.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

	@Autowired
	private ClienteRepository repo;

	@Override
	public void initialize(ClienteInsert constraintAnnotation) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDTO, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		if (objDTO.getTipo().equals(TipoCliente.PESSOA_FISICA.getCod()) && !BR.isValidCPF(objDTO.getCpfOuCnpj())) {
			list.add(new FieldMessage("CpfOuCnpj", "CPF Inválido"));
		}

		if (objDTO.getTipo().equals(TipoCliente.PESSOA_JURIDICA.getCod()) && !BR.isValidCNPJ(objDTO.getCpfOuCnpj())) {
			list.add(new FieldMessage("CpfOuCnpj", "CNPJ Inválido"));
		}

		Cliente aux = repo.findByEmail(objDTO.getEmail());
		
		if (aux!=null) {
			list.add(new FieldMessage("email", "Esse email já está Cadastrado em nossa Base."));
		}

		for (FieldMessage fm : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(fm.getMessage()).addPropertyNode(fm.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}

}
