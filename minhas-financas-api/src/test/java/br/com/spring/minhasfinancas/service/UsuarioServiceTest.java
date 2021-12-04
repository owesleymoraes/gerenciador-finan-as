package br.com.spring.minhasfinancas.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.spring.minhasfinancas.model.entity.Usuario;
import br.com.spring.minhasfinancas.model.repository.UsuarioRepository;


@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UsuarioServiceTest {

	@Autowired
	UsuarioService service;

	@Autowired
	UsuarioRepository repository;

	@Test
	public void deveValidarEmail() {

		// cenário

		repository.deleteAll();

		// ação

		service.validarEmail("email@email.com");

	}

	@Test
	public void deveLacarErroQuandoValidarEmailExistente() {

		// cenario:

		Usuario usuario = Usuario.builder().nome("usuario").email("email@email.com").build();

		// ação:

		service.validarEmail("email@email.com");

	}

}