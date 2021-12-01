package br.com.spring.minhasfinancas.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.spring.minhasfinancas.model.entity.Usuario;

public interface LancamentoRepository extends JpaRepository<Usuario, Long> {

}
