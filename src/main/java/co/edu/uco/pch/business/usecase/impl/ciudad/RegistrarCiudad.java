
package co.edu.uco.pch.business.usecase.impl.ciudad;

import java.util.UUID;
import co.edu.uco.pch.business.assembler.entity.impl.DepartamentoAssemblerEntity;
import co.edu.uco.pch.business.domain.CiudadDomain;
import co.edu.uco.pch.business.usecase.UseCaseWithoutReturn;
import co.edu.uco.pch.crosscutting.helpers.ObjectHelper;
import co.edu.uco.pch.crosscutting.helpers.TextHelper;
import co.edu.uco.pch.crosscutting.helpers.UUIDHelper;
import co.edu.uco.pch.data.dao.factory.DAOFactory;
import co.edu.uco.pch.entity.CiudadEntity;
import co.edu.uco.pch.entity.DepartamentoEntity;
import co.edu.uco.pch.crosscutting.exceptions.custom.BusinessPCHExceptions;
import co.edu.uco.pch.crosscutting.exceptions.messagecatalog.MessageCatalogStrategy;
import co.edu.uco.pch.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;

public final class RegistrarCiudad implements UseCaseWithoutReturn<CiudadDomain> {
	
	private DAOFactory factory;
	private static final int MAX_LENGTH_NOMBRE_CIUDAD = 30;
	
	

	public RegistrarCiudad(final DAOFactory factory) {
		if(ObjectHelper.getObjectHelper().isNull(factory)) {
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00027);
			var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00030);
			throw new BusinessPCHExceptions(mensajeTecnico, mensajeUsuario);
		}
		
		this.factory = factory;
	}



	@Override
	public void execute(final CiudadDomain data) {
		// 1. Validar que los datos requeridos por el caso de uso sean correctos de tipo de dato, longitud, obligotariedad, formato, rango
		
		// 2. Validar que no exista otra ciudad con el mismo nombre para el mismo departamento
		
		validarDatos(data);
		
		validarCiudadMismoNombreMismoDepartamento(data.getNombre(), data.getDepartamento().getId());
		
		// 3. Validar que no exista otra ciudad con el mismo identificador
		
		var ciudadEntity = CiudadEntity.build().setId(generarIdentificadorCiudad()).setNombre(data.getNombre()).setDepartamento(DepartamentoAssemblerEntity.getInstance().toEntity(data.getDepartamento())); 
		
		// 4. guardar la nueva ciudad
		
		factory.getCiudadDAO().crear(ciudadEntity);
		
	}
	
	private final UUID generarIdentificadorCiudad() {
		UUID id = UUIDHelper.generate();
		boolean existeId = true;
		
		while(existeId) {
			id = UUIDHelper.generate();
			var ciudadEntity = CiudadEntity.build().setId(id);
			var resultados = factory.getCiudadDAO().consultar(ciudadEntity);
			
			existeId = !resultados.isEmpty();
		
		}
		return id;
	}
	
	private final  void validarCiudadMismoNombreMismoDepartamento(final String nombreCiudad, final UUID idDepartamento) {
		var ciudadEntity = CiudadEntity.build().setNombre(nombreCiudad)
				.setDepartamento(DepartamentoEntity.build().setId(idDepartamento));
		
		var resultados = factory.getCiudadDAO().consultar(ciudadEntity);
		
		if(!resultados.isEmpty()) {
			var mensajeUsuario = "Ya existe una ciudad con el nombre \"${1}\" asociada con el departamento deseado...";
			throw new BusinessPCHExceptions(mensajeUsuario);
		}
	}
	
	private void validarDatos(final CiudadDomain data) {

		if (TextHelper.isNull(data.getNombre())) {
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00033);
			var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00034);
			throw new BusinessPCHExceptions(mensajeUsuario,mensajeTecnico);
		}

		if (ObjectHelper.getObjectHelper().isNull(data.getDepartamento())) {
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00035);
			var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00036);
			throw new BusinessPCHExceptions(mensajeTecnico,mensajeUsuario);
		}

		if (data.getNombre().length() > MAX_LENGTH_NOMBRE_CIUDAD) {
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00037);
			var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00038);
			throw new BusinessPCHExceptions(mensajeTecnico,mensajeUsuario);
		}

		if (!isValidCityNameFormat(data.getNombre())) {
			var mensajeUsuario= MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00039);
			var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00040);
			throw new BusinessPCHExceptions(mensajeTecnico,mensajeUsuario);
		}
	}

	private boolean isValidCityNameFormat(String nombre) {

		return nombre.matches("[a-zA-Z]+");
}

}



