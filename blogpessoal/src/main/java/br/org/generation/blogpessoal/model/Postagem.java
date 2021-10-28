package br.org.generation.blogpessoal.model;

import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "tb_postagens") // Dizer o nome da tabela
public class Postagem {

	@Id // Chave Primaria
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull(message = "O atributo título é obrigatório!")
	@Size(min = 5, max = 100, message = "O título deve conter no mínimo 5 caracteres e no máximo 100!")
	private String titulo;
	
	@NotNull(message = "O atributo texto é obrigatório!")
	@Size(min = 10, max = 1000, message = "O texto deve conter no mínimo 10 caracteres e no máximo 1000!")
	private String texto;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date data = new java.sql.Date(System.currentTimeMillis());
	
	// Getters and Setters
	public long getId() { return id; }
	
	public void setId(long id) { this.id = id; }
	
	public String getTitulo() { return titulo; }
	
	public void setTitulo(String titulo) { this.titulo = titulo; }
	
	public String getTexto() { return texto; }
	
	public void setTexto(String texto) { this.texto = texto; }
	
	public Date getData() { return data; }
	
	public void setData(Date data) { this.data = data; }
	
}

