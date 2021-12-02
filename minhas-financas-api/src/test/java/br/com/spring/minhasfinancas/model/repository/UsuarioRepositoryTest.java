package br.com.spring.minhasfinancas.model.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import br.com.spring.minhasfinancas.model.entity.Usuario;


@SpringBootTest
@ExtendWith(SpringExtension.class)
public class UsuarioRepositoryTest {
	
	@Autowired
	private UsuarioRepository repository;
	
	@Test
	public void deveVerificarAExitenciaDeUmEmail() {
		
		//cenário
		
		Usuario usuario = Usuario.builder().nome("usuario").email("usuario@email.com").build();
		repository.save(usuario);
		
		
		//execursão
		
		boolean result = repository.existsByEmail("usuario@email.com");
		
		
		// verificação
		
		assertTrue(result);
		
	}

}
