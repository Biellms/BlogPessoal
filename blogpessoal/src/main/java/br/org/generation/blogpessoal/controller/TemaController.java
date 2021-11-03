package br.org.generation.blogpessoal.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.org.generation.blogpessoal.model.Tema;
import br.org.generation.blogpessoal.repository.TemaRepository;

@RestController
@RequestMapping("/tema")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TemaController {

	@Autowired
	private TemaRepository temaRepository;
	
	@GetMapping // SELECT * FROM tb_tema
	public ResponseEntity<List<Tema>> getAll() {
		return ResponseEntity.ok(temaRepository.findAll());
	}
	
	@GetMapping("/{id}") // SELECT * FROM tb_tema WHERE id = ?
	public ResponseEntity<Tema> getById(@PathVariable long id) {
		return temaRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/descricao/{descricao}") // SELECT * FROM tb_tema WHERE descricao LIKE '%?%';
	public ResponseEntity<List<Tema>> getByDescricao (@PathVariable String descricao) {
		return ResponseEntity.ok(temaRepository.findAllByDescricaoContainingIgnoreCase(descricao));
	}
	
	@PostMapping // Inserir dados na tb_tema
	public ResponseEntity<Tema> postTema (@RequestBody Tema tema) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(temaRepository.save(tema));
	}
	
	@PutMapping // Atualizar dados na tb_tema
	public ResponseEntity<Tema> putTema (@RequestBody Tema tema) {
		return temaRepository.findById(tema.getId()) 
				.map(resposta -> ResponseEntity.status(HttpStatus.OK).body(temaRepository.save(tema)))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}") // DELETE * FROM tb_postagem WHERE id = ?;
	public ResponseEntity<?> deletaTema(@PathVariable long id) {
		return temaRepository.findById(id)
				.map(resposta -> {
        			temaRepository.deleteById(id); // Se existir, será excluido
        			return ResponseEntity.ok().build();
        		}).orElse(ResponseEntity.notFound().build()); // Caso contrario exibira protocolo 404
	}
}
