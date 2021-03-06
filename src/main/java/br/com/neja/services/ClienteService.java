package br.com.neja.services;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.neja.domain.Cidade;
import br.com.neja.domain.Cliente;
import br.com.neja.domain.Endereco;
import br.com.neja.domain.enums.Perfil;
import br.com.neja.domain.enums.TipoCliente;
import br.com.neja.dto.ClienteDTO;
import br.com.neja.dto.ClienteNewDTO;
import br.com.neja.repositories.ClienteRepository;
import br.com.neja.repositories.EnderecoRepository;
import br.com.neja.security.UserSS;
import br.com.neja.services.exception.AuthorizationException;
import br.com.neja.services.exception.DataIntegrityException;
import br.com.neja.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {
	@Value("${img.prefix.client.profile}")
	private String prefixoImg;

	@Value("${img.profile.size}")
	private Integer sizeImgProfile;

	@Autowired
	private ClienteRepository repo;

	@Autowired
	private EnderecoRepository endRepo;

	@Autowired
	private BCryptPasswordEncoder crypt;

	@Autowired
	private S3Service s3;

	@Autowired
	private ImageService imageService;

	public Cliente find(Integer id) {

		UserSS user = UserService.authenticated();

		if (user == null || !user.hashRole(Perfil.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso Negado");
		}

		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id:" + id + " Tipo: " + Cliente.class.getSimpleName()));
	}

	public List<Cliente> findAll() {
		return repo.findAll();
	}


	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = repo.save(obj);
		endRepo.saveAll(obj.getEnderecos());
		return obj;
	}

	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Integer id) {
		Cliente c = find(id);
		try {
			repo.deleteById(c.getId());
		} catch (DataIntegrityViolationException ex) {
			throw new DataIntegrityException(
					"Não é possível fazer a operação DELETE :" + c.getNome() + ", existem pedidos relacionados.");
		}

	}

	public Page<Cliente> findPage(
			// parametros
			Integer page, Integer linesPerPage, String orderBy, String direction
	// fim parametros
	) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);

		return repo.findAll(pageRequest);
	}

	public Cliente fromDTO(ClienteDTO objDTO) {
		return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null, null);
	}

	public Cliente fromDTO(ClienteNewDTO objDTO) {

		Cliente cli = new Cliente(null, objDTO.getNome(), objDTO.getEmail(), objDTO.getCpfOuCnpj(),
				TipoCliente.toEnum(objDTO.getTipo()), crypt.encode(objDTO.getSenha()));

		Endereco end = new Endereco(null, objDTO.getLogradouro(), objDTO.getNumero(), objDTO.getComplemento(),
				new Cidade(objDTO.getCidadeId(), null, null), objDTO.getBairro(), objDTO.getCep(), cli);

		cli.getEnderecos().add(end);
		cli.getTelefones().addAll(objDTO.getTelefones());
		return cli;
	}

	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}

	public Cliente findByEmail(String email) {
		if (email == null)
			return null;
		return repo.findByEmail(email);
	}

	public Cliente findByEmailResource(String email) {
		if (email == null)
			return null;

		UserSS user = UserService.authenticated();
		if (user == null || !user.hashRole(Perfil.ADMIN) && !email.equals(user.getUsername()))
			throw new AuthorizationException("Acesso negado");

		Cliente ret = repo.findByEmail(email);
		if (ret == null)
			throw new ObjectNotFoundException(
					"Objeto não encontrado : " + user.getId() + "  " + Cliente.class.getName());
		return ret;
	}

	public Cliente updateForgotEmail(Cliente obj) {
		return repo.save(obj);
	}

	public URI uploadProfilePicture(MultipartFile multipartFile) {

		UserSS user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Acesso negado");
		}

		BufferedImage imgJpg = imageService.getJpgFromFile(multipartFile);
		imgJpg = imageService.cropSquareImage(imgJpg);
		imgJpg = imageService.resize(imgJpg, sizeImgProfile);
		String fileName = prefixoImg + user.getId() + ".jpg";

		return s3.uploadFile(imageService.getInputStream(imgJpg, "jpg"), fileName, "image");

	}

}
