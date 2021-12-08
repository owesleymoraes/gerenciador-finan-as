package br.com.spring.minhasfinancas.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spring.minhasfinancas.excepition.ErroAutenticar;
import br.com.spring.minhasfinancas.excepition.RegraNegocioExcepition;
import br.com.spring.minhasfinancas.model.entity.Usuario;
import br.com.spring.minhasfinancas.model.repository.UsuarioRepository;
import br.com.spring.minhasfinancas.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	public UsuarioServiceImpl(UsuarioRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public Usuario autenticar(String email, String senha) {

		Optional<Usuario> usuario = repository.findByEmail(email);

		if (!usuario.isPresent()) {
			throw new ErroAutenticar("Usuário não encontrado.");
		}
		if (!usuario.get().getSenha().equals(senha)) {

			throw new ErroAutenticar("Senha Invalida.");

		}

		return usuario.get();
	}

	@Override
	public Usuario salvarUsuario(Usuario usuario) {

		validarEmail(usuario.getEmail());

		return repository.save(usuario);
	}

	@Override
	public void validarEmail(String email) {

		boolean existe = repository.existsByEmail(email);

		if (existe) {
			throw new RegraNegocioExcepition("Já existe um usuário cadastrado com esse email.");
		}

	}

}
