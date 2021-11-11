package br.org.generation.blogpessoal.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.org.generation.blogpessoal.model.Postagem;
import br.org.generation.blogpessoal.repository.PostagemRepository;

@RestController
@RequestMapping("/postagens")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostagemController {

	@Autowired // injeção de dependencia 
	private PostagemRepository postagemRepository;
	
	@GetMapping
	public ResponseEntity<List<Postagem>> getAll() {
		return ResponseEntity.ok(postagemRepository.findAll()); // Selecionar tudo
		// SELECT * FROM tb_postagens;
	}
	
	@GetMapping("/{id}") // --> {} indica que é uma variável
	public ResponseEntity<Postagem> getById(@PathVariable long id) {
		return postagemRepository.findById(id) 
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.notFound().build()); 
		// SELECT * FROM tb_postagens WHERE id = ?
	}

	@GetMapping("/titulo/{titulo}") // Para a assinatura não ficar parecida com a anterior
	public ResponseEntity<List<Postagem>> getByTitulo(@PathVariable String titulo) { // Encontrar Titulo
		return ResponseEntity.ok(postagemRepository.findAllByTituloContainingIgnoreCase(titulo));
		// SELECT * FROM tb_postagens WHERE titulo LIKE '%titulo%';
	}
	
	@PostMapping // Inserir dados na tb_postagem
	public ResponseEntity<Postagem> postPostagem(@RequestBody Postagem postagem) {
		return ResponseEntity.status(HttpStatus.CREATED).body(postagemRepository.save(postagem));
	}
	
	@PutMapping // Atualizar dados na tb_postagem
	public ResponseEntity<Postagem> PutPostagem(@RequestBody Postagem postagem) {
		return postagemRepository.findById(postagem.getId()) 
				.map(resposta -> ResponseEntity.status(HttpStatus.OK).body(postagemRepository.save(postagem)))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}") // Deletar dados da tabela
	public ResponseEntity<?> deletaPostagem(@PathVariable long id) {
        return postagemRepository.findById(id) // Verifica se o id existe
        		.map(resposta -> {
        			postagemRepository.deleteById(id); // Se existir, será excluido
        			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        		}).orElse(ResponseEntity.notFound().build()); // Caso contrario apresentará protocolo 404
		// DELETE * FROM tb_postagem WHERE id = ?;
	}
}
