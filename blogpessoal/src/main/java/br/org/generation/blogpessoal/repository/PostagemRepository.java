package br.org.generation.blogpessoal.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.generation.blogpessoal.model.Postagem;

@Repository
public interface PostagemRepository extends JpaRepository<Postagem, Long>{
	
	// Consulta do tipo LIKE ''
	public List <Postagem> findAllByTituloContainingIgnoreCase(String titulo);
	// SELECT * FROM tb_postagens WHERE titulo LIKE '%titulo%';
	}
