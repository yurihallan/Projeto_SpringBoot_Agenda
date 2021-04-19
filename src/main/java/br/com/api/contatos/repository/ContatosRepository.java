package br.com.api.contatos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.api.contatos.entity.Contatos;

@Repository
public interface ContatosRepository  extends JpaRepository<Contatos, Long>{

    
}
