
package co.edu.uco.pch.business.assembler.entity.impl;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.pch.business.assembler.entity.AssemblerEntity;
import co.edu.uco.pch.business.domain.CiudadDomain;
import co.edu.uco.pch.business.domain.DepartamentoDomain;
import co.edu.uco.pch.crosscutting.helpers.ObjectHelper;
import co.edu.uco.pch.entity.CiudadEntity;
import co.edu.uco.pch.entity.DepartamentoEntity;

public final class CiudadAssemblerEntity implements AssemblerEntity<CiudadDomain, CiudadEntity> {
	
	private static final AssemblerEntity<CiudadDomain, CiudadEntity> instance = new CiudadAssemblerEntity();
	
	private static final AssemblerEntity<DepartamentoDomain, DepartamentoEntity> DepartamentoAssembler = DepartamentoAssemblerEntity.getInstance();
	
	

	private CiudadAssemblerEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public static final AssemblerEntity<CiudadDomain, CiudadEntity> getInstance(){
		return instance;
	}
	

	@Override
	public CiudadDomain toDomain(CiudadEntity data) {
		// TODO Auto-generated method stub
		var ciudadEntityTmp = ObjectHelper.getObjectHelper().getDefaultValue(data, CiudadEntity.build());
		var departamentoDomain = DepartamentoAssembler.toDomain(ciudadEntityTmp.getDepartamento());
		return CiudadDomain.build(ciudadEntityTmp.getId(), ciudadEntityTmp.getNombre(), departamentoDomain);
	}

	@Override
	public CiudadEntity toEntity(CiudadDomain domain) {
		// TODO Auto-generated method stub
		var ciudadDomainTmp = ObjectHelper.getObjectHelper().getDefaultValue(domain, CiudadDomain.build());
		var departamentoEntity = DepartamentoAssembler.toEntity(ciudadDomainTmp.getDepartamento());
		return CiudadEntity.build().setId(ciudadDomainTmp.getId()).setNombre(ciudadDomainTmp.getNombre())
				.setDepartamento(departamentoEntity);
	}

	@Override
	public final List<CiudadDomain> toDomainCollection(List<CiudadEntity> entityCollection) {
		
		var entityCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(entityCollection, new ArrayList<CiudadEntity>());
		
		return entityCollectionTmp.stream().map(this::toDomain).toList();
		
	}
	
	@Override
	public List<CiudadEntity> toEntityCollection(List<CiudadDomain> domainCollection) {
		var domainCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(domainCollection,
				new ArrayList<CiudadDomain>());
		return domainCollectionTmp.stream().map(this::toEntity).toList();
	}

}


