package br.org.generation.blogpessoal.model;

import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "tb_tema")
public class Tema {
	
	// Atributos
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@Size(min = 1, max = 100)
	private String descricao;
	
	@OneToMany(mappedBy = "tema", cascade = CascadeType.ALL) // CascadeType.ALL apagara tudo relacionado ao tema
	private List<Postagem> postagem;
	
	// Getters and Setters
	public long getId() { return id; }
	
	public void setId(long id) { this.id = id; }
	
	public String getDescricao() { return descricao; }
	
	public void setDescricao(String descricao) { this.descricao = descricao; }
	
	public List<Postagem> getPostagem() { return postagem; }
	
	public void setPostagem(List<Postagem> postagem) { this.postagem = postagem; } 
	
	
}
