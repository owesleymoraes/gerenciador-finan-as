package br.com.spring.minhasfinancas.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import br.com.spring.minhasfinancas.excepition.ErroAutenticar;
import br.com.spring.minhasfinancas.excepition.RegraNegocioExcepition;
import br.com.spring.minhasfinancas.model.entity.Usuario;
import br.com.spring.minhasfinancas.model.repository.UsuarioRepository;
import br.com.spring.minhasfinancas.service.impl.UsuarioServiceImpl;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UsuarioServiceTest {

	@SpyBean
	UsuarioServiceImpl service;

	@MockBean
	UsuarioRepository repository;

	@Test
	public void deveSalvarUsuario() {

		// cenario:

		Mockito.doNothing().when(service).validarEmail(Mockito.anyString());

		Usuario usuario = Usuario.builder().id(1l).nome("usuario").email("email@email.com").senha("senha").build();

		Mockito.when(repository.save(Mockito.any(Usuario.class))).thenReturn(usuario);

		// ação:

		Usuario usuarioSalvo = service.salvarUsuario(usuario);

		// verificação:

		assertNotNull(usuarioSalvo);
		assertEquals(usuarioSalvo.getId(), 1l);
		assertEquals(usuarioSalvo.getNome(), "usuario");
		assertEquals(usuarioSalvo.getEmail(), "email@email.com");
		assertEquals(usuarioSalvo.getSenha(), "senha");

	}

	@Test
	public void naoDeveSalvarUsuarioComOEmailJaCadastrado() {
		// cenario:

		Usuario usuario = Usuario.builder().email("usuario@email.com").build();

		Mockito.doThrow(RegraNegocioExcepition.class).when(service).validarEmail("usuario@email.com");

		// ação:

		service.salvarUsuario(usuario);

		// verificação:

		Mockito.verify(repository, Mockito.never()).save(usuario);

	}

	@Test
	public void deveAutenticarUmUsuarioComSucesso() {

		// cenario

		String email = "email@email.com";
		String senha = "senha";

		Usuario usuario = Usuario.builder().email(email).senha(senha).id(1l).build();

		Mockito.when(repository.findByEmail(email)).thenReturn(Optional.of(usuario));

		// açao

		Usuario result = service.autenticar(email, senha);

		// verificação

		assertNotNull(result);

	}

	@Test
	public void deveLancarErroQuandoNaoEncontrarUsuarioComOEmailCadastrado() {

		// cenario:

		Mockito.when(repository.findByEmail(Mockito.anyString())).thenReturn(Optional.empty());

		// Ação:

		Assertions.assertThrows(ErroAutenticar.class, () -> service.autenticar("email@email.com", "senha"));

		// Assertions.assertInstanceOf(ErroAutenticar.class,
		// exception.getMessage().compareTo("Usuário não encontrado"));

	}

	@Test
	public void deveLancarErroQuandoNaoEncontrarUsuarioComASenhaCadastrada() {

		// cenario:

		String senha = "senha";

		Usuario usuario = Usuario.builder().email("email@email.com").senha(senha).build();
		Mockito.when(repository.findByEmail(Mockito.anyString())).thenReturn(Optional.of(usuario));

		// ação:

		Assertions.assertThrows(ErroAutenticar.class, () -> service.autenticar("email@email.com", "123"));

		// assertInstanceOf(ErroAutenticar.class,exception.getMessage().compareTo("Senha
		// Incorreta"));

	}

	@Test
	public void deveValidarEmail() {

		// cenário

		Mockito.when(repository.existsByEmail(Mockito.anyString())).thenReturn(false);

		// ação

		service.validarEmail("email@email.com");

	}

	@Test
	public void deveLacarErroQuandoValidarEmailExistente() {

		// cenario:

		Mockito.when(repository.existsByEmail(Mockito.anyString())).thenReturn(true);

		// ação:

	}

}