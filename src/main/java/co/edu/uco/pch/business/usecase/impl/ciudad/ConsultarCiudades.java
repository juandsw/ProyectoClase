package co.edu.uco.pch.business.usecase.impl.ciudad;

import java.util.List;

import co.edu.uco.pch.business.assembler.entity.impl.CiudadAssemblerEntity;
import co.edu.uco.pch.business.domain.CiudadDomain;
import co.edu.uco.pch.business.usecase.UseCaseWithReturn;
import co.edu.uco.pch.crosscutting.exceptions.custom.BusinessPCHExceptions;
import co.edu.uco.pch.crosscutting.exceptions.messagecatalog.MessageCatalogStrategy;
import co.edu.uco.pch.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;
import co.edu.uco.pch.crosscutting.helpers.ObjectHelper;
import co.edu.uco.pch.data.dao.factory.DAOFactory;

public class ConsultarCiudades implements UseCaseWithReturn<CiudadDomain, List<CiudadDomain>> {

	
private DAOFactory factory;
	
	

	public ConsultarCiudades(final DAOFactory factory) {
		if(ObjectHelper.getObjectHelper().isNull(factory)) {
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00025);
			var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00029);
			
			throw new BusinessPCHExceptions(mensajeTecnico, mensajeUsuario);
		}
		
		this.factory = factory;
	}
	@Override
	public final List<CiudadDomain> execute(final CiudadDomain data) {
		var ciudadEntityFilter = 
				CiudadAssemblerEntity.getInstance().toEntity(data);
		var resultadosEntity = factory.getCiudadDAO().consultar(ciudadEntityFilter);
		
		var resultadosDomain = CiudadAssemblerEntity.getInstance().toDomainCollection(resultadosEntity);
		
		return resultadosDomain;
	}

}


