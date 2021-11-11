package br.org.generation.blogpessoal.model;

import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_temas") // Dizer o nome da tabela
public class Tema {
	
	// Atributos
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull(message = "O atributo Descrição é obrigatório")
	@Size(min = 1, max = 100)
	private String descricao;
	
	// Um tema para muitas postagens
	@OneToMany(mappedBy = "tema", cascade = CascadeType.ALL) // CascadeType.ALL apagara tudo relacionado ao tema
	@JsonIgnoreProperties("tema")
	private List<Postagem> postagem;
	
	// Getters and Setters
	public long getId() { return id; }
	
	public void setId(long id) { this.id = id; }
	
	public String getDescricao() { return descricao; }
	
	public void setDescricao(String descricao) { this.descricao = descricao; }
	
	public List<Postagem> getPostagem() { return postagem; }
	
	public void setPostagem(List<Postagem> postagem) { this.postagem = postagem; } 
	
}
