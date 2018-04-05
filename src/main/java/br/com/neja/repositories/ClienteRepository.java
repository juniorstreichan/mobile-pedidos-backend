package br.com.neja.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.neja.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
