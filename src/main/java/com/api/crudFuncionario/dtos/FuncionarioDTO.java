package com.api.crudFuncionario.dtos;

import jakarta.validation.constraints.*;

public class FuncionarioDTO {

	@NotBlank
	@Size(min = 2, max = 30)
	private String nome;
	@NotBlank
	@Size(min = 2, max = 50)
    private String sobrenome;
	@NotBlank
	@Email
    private String email;
	@NotNull
    private Long numeroNis;
    
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getNumeroNis() {
		return numeroNis;
	}
	public void setNumeroNis(Long numeroNis) {
		this.numeroNis = numeroNis;
	}
}
