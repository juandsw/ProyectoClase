
package co.edu.uco.pch.business.assembler.entity.impl;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.pch.business.assembler.entity.AssemblerEntity;
import co.edu.uco.pch.business.domain.DepartamentoDomain;
import co.edu.uco.pch.business.domain.PaisDomain;
import co.edu.uco.pch.crosscutting.helpers.ObjectHelper;
import co.edu.uco.pch.entity.DepartamentoEntity;
import co.edu.uco.pch.entity.PaisEntity;

public final class DepartamentoAssemblerEntity implements AssemblerEntity<DepartamentoDomain, DepartamentoEntity> {
	
	private static final AssemblerEntity<DepartamentoDomain, DepartamentoEntity> instance = new DepartamentoAssemblerEntity();
	
	private static final AssemblerEntity< PaisDomain, PaisEntity > PaisAssembler = PaisAssemblerEntity.getInstance();
	
	

	protected DepartamentoAssemblerEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public static final AssemblerEntity<DepartamentoDomain, DepartamentoEntity> getInstance() {
		return instance;
	}

	@Override
	public DepartamentoDomain toDomain(DepartamentoEntity data) {
		// TODO Auto-generated method stub
		var departamemtoEntityTmp = ObjectHelper.getObjectHelper().getDefaultValue(data, DepartamentoEntity.build());
		var paisDomain = PaisAssembler.toDomain(departamemtoEntityTmp.getPais());
		return DepartamentoDomain.build(departamemtoEntityTmp.getId(),departamemtoEntityTmp.getNombre(),paisDomain);
	}

	@Override
	public DepartamentoEntity toEntity(DepartamentoDomain domain) {
		// TODO Auto-generated method stub
		var departamentoDomainTmp = ObjectHelper.getObjectHelper().getDefaultValue(domain, DepartamentoDomain.build());
		var paisEntity = PaisAssembler.toEntity(departamentoDomainTmp.getPais());
		return DepartamentoEntity.build().setId(departamentoDomainTmp.getId()).setNombre(departamentoDomainTmp.getNombre()).setPais(paisEntity);
	}

	@Override
	public List<DepartamentoDomain> toDomainCollection(List<DepartamentoEntity> entityCollection) {
		// TODO Auto-generated method stub
		var entityCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(entityCollection, new ArrayList<DepartamentoEntity>());
		return entityCollectionTmp.stream().map(this::toDomain).toList();
	}

	@Override
	public List<DepartamentoEntity> toEntityCollection(List<DepartamentoDomain> domainCollection) {
		var domainCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(domainCollection,
				new ArrayList<DepartamentoDomain>());
		return domainCollectionTmp.stream().map(this::toEntity).toList();
	}
	

}


