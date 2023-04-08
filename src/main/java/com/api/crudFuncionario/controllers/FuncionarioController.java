package com.api.crudFuncionario.controllers;

import com.api.crudFuncionario.dtos.FuncionarioDTO;
import com.api.crudFuncionario.models.FuncionarioModel;
import com.api.crudFuncionario.services.FuncionarioService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/funcionario")
public class FuncionarioController {
	
	final FuncionarioService funcionarioService;

	public FuncionarioController(FuncionarioService funcionarioService) {
		this.funcionarioService = funcionarioService;
	}
	
	@PostMapping
    public ResponseEntity<Object> saveFuncionario(@RequestBody @Valid FuncionarioDTO funcionarioDTO){
		if(funcionarioService.existsByEmail(funcionarioDTO.getEmail())){
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: email is already in use!");
		}
		var funcionarioModel = new FuncionarioModel();
        BeanUtils.copyProperties(funcionarioDTO, funcionarioModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(funcionarioService.save(funcionarioModel));
    }

	@GetMapping
	public ResponseEntity<List<FuncionarioModel>> getAllFuncionarios(){
		return ResponseEntity.status(HttpStatus.OK).body(funcionarioService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getOneFuncionario(@PathVariable(value = "id") UUID id){
		Optional<FuncionarioModel> funcionarioModelOptional = funcionarioService.findById(id);
		if (!funcionarioModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Funcionario not found.");
		}
		return ResponseEntity.status(HttpStatus.OK).body(funcionarioModelOptional.get());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteFuncionario(@PathVariable(value = "id") UUID id){
		Optional<FuncionarioModel> funcionarioModelOptional = funcionarioService.findById(id);
		if (!funcionarioModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Funcionario not found.");
		}
		funcionarioService.delete(funcionarioModelOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Funcionario deleted successfully.");
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> updateParkingSpot(@PathVariable(value = "id") UUID id,
													@RequestBody @Valid FuncionarioDTO funcionarioDTO){
		Optional<FuncionarioModel> funcionarioModelOptional = funcionarioService.findById(id);
		if (!funcionarioModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Funcionario not found.");
		}
		var funcionarioModel = new FuncionarioModel();
		BeanUtils.copyProperties(funcionarioDTO, funcionarioModel);
		funcionarioModel.setId(funcionarioModelOptional.get().getId());
		return ResponseEntity.status(HttpStatus.OK).body(funcionarioService.save(funcionarioModel));
	}
}
