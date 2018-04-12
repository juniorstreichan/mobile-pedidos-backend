package br.com.neja.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import br.com.neja.domain.Cliente;
import br.com.neja.dto.ClienteDTO;
import br.com.neja.repositories.ClienteRepository;
import br.com.neja.resources.exceptions.FieldMessage;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {

	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private ClienteRepository repo;

	@Override
	public void initialize(ClienteUpdate constraintAnnotation) {
	}

	@Override
	public boolean isValid(ClienteDTO objDTO, ConstraintValidatorContext context) {
		@SuppressWarnings("unchecked")
		Map<String,String> map =(Map<String,String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		List<FieldMessage> list = new ArrayList<>();
		Integer idUri = Integer.parseInt(map.get("id"));
		
		
		Cliente aux = repo.findByEmail(objDTO.getEmail());
		
		if (aux!=null && !aux.getId().equals(idUri)) {
			list.add(new FieldMessage("email", "Este email pertence a outro cliente de nossa Base."));
		}

		for (FieldMessage fm : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(fm.getMessage()).addPropertyNode(fm.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}

}
