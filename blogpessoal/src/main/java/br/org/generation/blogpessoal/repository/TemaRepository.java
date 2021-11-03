package br.org.generation.blogpessoal.repository;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;

import br.org.generation.blogpessoal.model.Tema;

public interface TemaRepository extends JpaRepository<Tema, Long> {

	// SELECT * FROM tb_tema
	public List<Tema> findAllByDescricaoContainingIgnoreCase(String descricao);
	
}

