package co.edu.uco.pch.crosscutting.exceptions.custom;
import co.edu.uco.pch.crosscutting.exceptions.PCHException;
import co.edu.uco.pch.crosscutting.exceptions.enums.Lugar;

public final class InitializerPCHExceptions extends PCHException {
	
	private static final long serialVersionUID = -9112438537604124056L;

	public InitializerPCHExceptions(final String mensajeUsuario) {
		super(mensajeUsuario, Lugar.DATA);
	
	}

	public InitializerPCHExceptions(final String mensajeTecnico, final String mensajeUsuario, Throwable exceptionRaiz) {
		super(mensajeTecnico, mensajeUsuario, Lugar.DATA, exceptionRaiz);
	
	}

}
