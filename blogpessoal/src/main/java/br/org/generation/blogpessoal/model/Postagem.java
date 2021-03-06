package br.org.generation.blogpessoal.model;

import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_postagens") // Dizer o nome da tabela
public class Postagem {

	// Atributos
	@Id // Chave Primaria
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull(message = "O atributo título é obrigatório!")
	@Size(max = 100, message = "O título deve conter no mínimo 5 caracteres e no máximo 100!")
	private String titulo;
	
	@NotNull(message = "O atributo texto é obrigatório!")
	@Size(min = 10, max = 1000, message = "O texto deve conter no mínimo 10 caracteres e no máximo 1000!")
	private String texto;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date data = new java.sql.Date(System.currentTimeMillis());
	
	@ManyToOne // Muitas postagens para um tema
	@JsonIgnoreProperties("postagem") // Para não criar um efeito cascata
	private Tema tema;
	
	@ManyToOne // Muitos usuarios para um tema
	@JsonIgnoreProperties("postagem")
	private Usuario usuario;
	
	// Getters and Setters
	public long getId() { return id; }
	
	public void setId(long id) { this.id = id; }
	
	public String getTitulo() { return titulo; }
	
	public void setTitulo(String titulo) { this.titulo = titulo; }
	
	public String getTexto() { return texto; }
	
	public void setTexto(String texto) { this.texto = texto; }
	
	public Date getData() { return data; }
	
	public void setData(Date data) { this.data = data; }

	public Tema getTema() { return tema; }

	public void setTema(Tema tema) { this.tema = tema; }

	public Usuario getUsuario() { return usuario; }

	public void setUsuario(Usuario usuario) { this.usuario = usuario; }
	
}

