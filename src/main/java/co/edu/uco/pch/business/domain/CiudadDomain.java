package co.edu.uco.pch.business.domain;

import java.util.UUID;

import co.edu.uco.pch.crosscutting.helpers.ObjectHelper;
import co.edu.uco.pch.crosscutting.helpers.TextHelper;
import co.edu.uco.pch.crosscutting.helpers.UUIDHelper;

public class CiudadDomain {
	
	private UUID id;
	private String nombre;
	private DepartamentoDomain departamento;
	
	private CiudadDomain(UUID id, String nombre, DepartamentoDomain departamento) {
		setId(id);
		setNombre(nombre);
		setDepartamento(departamento);
	}
	
	public static final CiudadDomain build(final UUID id, final String nombre, final DepartamentoDomain departamento) {
		return new CiudadDomain(id, nombre, departamento);
	}

	public static final CiudadDomain build(final UUID id) {
		return new CiudadDomain(id, TextHelper.EMPTY, DepartamentoDomain.build());
	}
	
	public static final CiudadDomain build() {
		return new CiudadDomain(UUIDHelper.getDefault(),TextHelper.EMPTY,DepartamentoDomain.build());
	}
	// Getters
	
	public final UUID getId() {
		return id;
	}

	public final String getNombre() {
		return nombre;
	}

	public final DepartamentoDomain getDepartamento() {
		return departamento;
	}

	// Setters
	private final void setId(final UUID id) {
		this.id = UUIDHelper.getDafault(id, UUIDHelper.getDefault());
	}
	
	private final void setNombre(final String nombre) {
		this.nombre = TextHelper.applyTrim(nombre);
	}
	
	private final void setDepartamento(final DepartamentoDomain departamento) {
		this.departamento = ObjectHelper.getObjectHelper().getDefaultValue(departamento, DepartamentoDomain.build()) ;
	}
	
	

}

