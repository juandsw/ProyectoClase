package co.edu.uco.pch.business.assembler.entity.impl;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.pch.business.assembler.entity.AssemblerEntity;
import co.edu.uco.pch.business.domain.PaisDomain;
import co.edu.uco.pch.crosscutting.helpers.ObjectHelper;
import co.edu.uco.pch.entity.PaisEntity;

public final class PaisAssemblerEntity implements AssemblerEntity< PaisDomain, PaisEntity > {
	
	private static final AssemblerEntity< PaisDomain, PaisEntity > instance = new PaisAssemblerEntity();

	private PaisAssemblerEntity() {
		super();
	}
	
	public static final AssemblerEntity< PaisDomain, PaisEntity > getInstance(){
		return instance;
	}
	
	@Override
	public PaisDomain toDomain(PaisEntity data) {
		// TODO Auto-generated method stub
		var paisEntityTmp = ObjectHelper.getObjectHelper().getDefaultValue(data, PaisEntity.build());
		return PaisDomain.build(data.getId(), paisEntityTmp.getNombre());
	}

	@Override
	public PaisEntity toEntity(PaisDomain domain) {
		// TODO Auto-generated method stub
		var paisDomainTmp = ObjectHelper.getObjectHelper().getDefaultValue(domain, PaisDomain.build());
		// TODO Auto-generated method stub
		return PaisEntity.build().setId(paisDomainTmp.getId()).setNombre(paisDomainTmp.getNombre());
	}

	@Override
	public List<PaisDomain> toDomainCollection(List<PaisEntity> entityCollection) {
		// TODO Auto-generated method stub
		var entityCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(entityCollection,
				new ArrayList<PaisEntity>());
		return entityCollectionTmp.stream().map(this::toDomain).toList();
	}

	@Override
	public List<PaisEntity> toEntityCollection(List<PaisDomain> domainCollection) {
		// TODO Auto-generated method stub
		var domainCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(domainCollection,
				new ArrayList<PaisDomain>());
		return domainCollectionTmp.stream().map(this::toEntity).toList();
	}

}
