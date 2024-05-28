package co.edu.uco.pch.business.facade.impl.ciudad;

import java.util.List;

import co.edu.uco.pch.business.assembler.dto.impl.CiudadAssemblerDTO;
import co.edu.uco.pch.business.facade.FacadeWithReturn;
import co.edu.uco.pch.business.usecase.impl.ciudad.ConsultarCiudades;
import co.edu.uco.pch.crosscutting.exceptions.PCHException;
import co.edu.uco.pch.crosscutting.exceptions.custom.BusinessPCHExceptions;
import co.edu.uco.pch.crosscutting.exceptions.messagecatalog.MessageCatalogStrategy;
import co.edu.uco.pch.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;
import co.edu.uco.pch.data.dao.factory.DAOFactory;
import co.edu.uco.pch.dto.CiudadDTO;

public class ConsultarCiudadesFacade implements FacadeWithReturn<CiudadDTO, List<CiudadDTO>> {

	private DAOFactory daoFactory;
	
	public ConsultarCiudadesFacade() {
		daoFactory = DAOFactory.getFactory();	}
	
	@Override
	public final List<CiudadDTO> execute(final CiudadDTO dto) {
		daoFactory.iniciarTransaccion();
		try {
			
			var useCase = new ConsultarCiudades(daoFactory);
			var ciudadDomain = CiudadAssemblerDTO.getInstance().toDomain(dto);
			var resultadosDomain = useCase.execute(ciudadDomain);
			
			return CiudadAssemblerDTO.getInstance().toDTOCollection(resultadosDomain);
			
		} catch (final PCHException excepcion) {
			daoFactory.cancelarTransaccion();
			throw excepcion;
			
		}catch (final Exception excepcion) {
			daoFactory.cancelarTransaccion();
			
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00025);
			var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00026);
			
			throw new BusinessPCHExceptions(mensajeTecnico, mensajeUsuario);
		
		}finally {
			
			daoFactory.cerrarConexion();
			
		}
		
	}

}
