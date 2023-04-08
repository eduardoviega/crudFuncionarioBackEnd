package com.api.crudFuncionario.services;

import com.api.crudFuncionario.models.FuncionarioModel;
import com.api.crudFuncionario.repositories.FuncionarioRepository;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FuncionarioService {

	final FuncionarioRepository funcionarioRepository;
	
	public FuncionarioService(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}

	@Transactional
	public FuncionarioModel save(FuncionarioModel funcionarioModel) {
		return funcionarioRepository.save(funcionarioModel);
	}

	public boolean existsByEmail(String email){
		return funcionarioRepository.existsByEmail(email);
	}

	public List<FuncionarioModel> findAll() {
		return funcionarioRepository.findAll();
	}

	public Optional<FuncionarioModel> findById(UUID id) {
		return funcionarioRepository.findById(id);
	}

	@Transactional
	public void delete(FuncionarioModel funcionarioModel) {
		funcionarioRepository.delete(funcionarioModel);
	}
}
