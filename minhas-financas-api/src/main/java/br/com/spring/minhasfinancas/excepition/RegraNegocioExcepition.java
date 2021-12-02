package br.com.spring.minhasfinancas.excepition;

public class RegraNegocioExcepition extends RuntimeException {

	
	private static final long serialVersionUID = 1L;

	public RegraNegocioExcepition(String msg) {
		
		super(msg);
	}

}
