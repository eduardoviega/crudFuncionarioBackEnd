package com.api.crudFuncionario.repositories;

import java.util.UUID;

import com.api.crudFuncionario.models.FuncionarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FuncionarioRepository extends JpaRepository<FuncionarioModel, UUID> {

    boolean existsByEmail(String email);
}
