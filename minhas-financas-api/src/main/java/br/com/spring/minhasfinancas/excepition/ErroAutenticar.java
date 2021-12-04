package br.com.spring.minhasfinancas.excepition;

public class ErroAutenticar extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ErroAutenticar(String mensagem) {
		super(mensagem);
	}

}
