package br.org.generation.blogpessoal.model;

import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_usuarios") // Dizer o nome da tabela
public class Usuario {

	// Atributos
	@Id // Chave Primaria
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Auto incremento
	private long id;
		
	@NotNull(message = "O atributo Nome é obrigatório!")
	@Size(min = 10, max = 255, message = "O Nome da completo deve conter no mínimo 10 caracteres e no máximo 255!")
	private String nome;
		
	@Email(message = "O atributo Usuário deve ser um email válido!")
	@NotNull(message = "O atributo usuário é obrigatório!")
	@Size(max = 255, message = "O usuário deve conter no máximo 255!")
	private String usuario;
	
	@NotBlank(message = "O atributo senha não deve conter espaços!")
	@NotNull(message = "O atributo senha é obrigatório!")
	@Size(min = 8, message = "O usuário deve conter no mínimo 8 caracteres!")
	private String senha;

	// Um usuario para muitas postagens
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("usuario")
	private List<Postagem> postagem;
	
	

	public Usuario(long id, String nome, String usuario, String senha) {
		this.id = id;
		this.nome = nome;
		this.usuario = usuario;
		this.senha = senha;
	}
	
	public Usuario() { }

	// Getters and Setters
	public long getId() { return id; }

	public void setId(long id) { this.id = id; }

	public String getNome() { return nome; }

	public void setNome(String nome) { this.nome = nome; }

	public String getUsuario() { return usuario; }

	public void setUsuario(String usuario) { this.usuario = usuario; }

	public String getSenha() { return senha; }

	public void setSenha(String senha) { this.senha = senha; }

	public List<Postagem> getPostagem() { return postagem; }

	public void setPostagem(List<Postagem> postagem) { this.postagem = postagem; }

}
