package br.com.neja.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.neja.domain.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

}
