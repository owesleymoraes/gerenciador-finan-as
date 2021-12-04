package br.com.spring.minhasfinancas.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.spring.minhasfinancas.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	//Optional<Usuario> findByEmail(String email); retorna um usuario quando esse email existe
	
	boolean existsByEmail(String email);
	
	Optional<Usuario> findByEmail(String email);
	
	
	//existsByEmail e findByEmail é uma forma que jpa reconhece para fazer seus
	// query-metodos ou seja essa nomeclatura terá uma relação direta com o banco.
	
	

}
