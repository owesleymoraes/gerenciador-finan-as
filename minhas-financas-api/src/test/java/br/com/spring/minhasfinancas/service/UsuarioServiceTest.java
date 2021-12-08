package br.com.spring.minhasfinancas.service;


import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import br.com.spring.minhasfinancas.excepition.ErroAutenticar;
import br.com.spring.minhasfinancas.model.entity.Usuario;
import br.com.spring.minhasfinancas.model.repository.UsuarioRepository;
import br.com.spring.minhasfinancas.service.impl.UsuarioServiceImpl;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UsuarioServiceTest {

	UsuarioService service;
	UsuarioRepository repository;

	@BeforeEach
	public void setUp() {
		repository = Mockito.mock(UsuarioRepository.class);
		service = new UsuarioServiceImpl(repository);
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
		
        Throwable excection = Assertions.assertThrows(ErroAutenticar.class, () -> service.validarEmail("email@email.com"));
        
        
		
	}

	@Test
	public void deveLancarErroQuandoNaoEncontrarUsuarioComASenhaCadastrada() {
		
		// cenario:
		
		String senha = "senha";
		
		Usuario usuario = Usuario.builder().email("email@email.com").senha(senha).build();
		Mockito.when(repository.findByEmail(Mockito.anyString())).thenReturn(Optional.of(usuario));
		 
		 //ação:
		
		Assertions.assertThrows(ErroAutenticar.class, () -> service.autenticar("email@email.com", "123"));	 
		
		
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