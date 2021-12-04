package br.com.spring.minhasfinancas.model.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;

import br.com.spring.minhasfinancas.model.entity.Usuario;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UsuarioRepositoryTest {

	@Autowired
	private UsuarioRepository repository;

	@Autowired
	TestEntityManager entityManager; // entity manager de teste.

	public static Usuario criarUsuario() {
		return Usuario.builder().
				nome("usuario").
				email("usuario@email.com").
				senha("senha").
				build();

	}

	@Test
	public void deveVerificarAExistenciaDeUmEmail() {

		// cenário

		  Usuario usuario = criarUsuario();
		  entityManager.persist(usuario);
		
		// Ação

		boolean result = repository.existsByEmail("usuario@email.com");

		// verificação

		assertTrue(result);

	}

	@Test
	public void deveRetornarFalsoQuandoNaoHouverUsuarioCadastrado() {

		// cenário

		// repository.deleteAll(); devido a utilização da classe TestEntityManager

		// Ação

		boolean result = repository.existsByEmail("usuario@email.com");

		// verificação

		assertFalse(result);

	}

	@Test
	public void devePersistirUmUsuarioNoBancoDeDados() {

		// cenario

		Usuario usuario = criarUsuario();

		// ação

		entityManager.persist(usuario);

		// verificação

		assertNotNull(usuario.getId()); // como o id é gerada no momento da persistência por isso a verificação.

	}

	@Test
	public void deveBuscarUsuarioPorEmail() {

		// cenario:

		Usuario usuario = criarUsuario();
		entityManager.persist(usuario);
		
		// açõa:

		Optional<Usuario> result = repository.findByEmail("usuario@email.com");

		// verificação:
		
		assertNotNull(result);

	}

	@Test
	public void deveRetornarVazioQuandoNaoHouverCadastroDoEmailPesquisado() {
		// cenario:

				
				
		// açõa:

		Optional<Usuario> result = repository.findByEmail("usuario@email.com");

		// verificação:
		
		assert(result).isEmpty();
				
		

	}
	
}
