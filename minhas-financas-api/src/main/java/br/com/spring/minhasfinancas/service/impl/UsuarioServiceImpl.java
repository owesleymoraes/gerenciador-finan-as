package br.com.spring.minhasfinancas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spring.minhasfinancas.excepition.RegraNegocioExcepition;
import br.com.spring.minhasfinancas.model.entity.Usuario;
import br.com.spring.minhasfinancas.model.repository.UsuarioRepository;
import br.com.spring.minhasfinancas.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	@Override
	public Usuario autenticar(String email, String senha) {

		return null;
	}

	@Override
	public Usuario salvarUsuario(Usuario usuario) {

		return null;
	}

	@Override
	public void validarEmail(String email) {
		
		boolean existe = repository.existsByEmail(email);
		
		if(existe) {
			throw new RegraNegocioExcepition("Já existe um usuário cadastrado com esse email.");
		}
			

	}

}
