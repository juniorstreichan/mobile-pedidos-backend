package br.com.neja.repositories;

 

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.neja.domain.Cliente;
import br.com.neja.domain.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

	@Transactional(readOnly= true)
	Page<Pedido> findByCliente(Cliente obj,Pageable pageRequest);
	
}
