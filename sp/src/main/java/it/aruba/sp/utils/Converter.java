package it.aruba.sp.utils;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

import it.aruba.sp.dto.PraticaDto;
import it.aruba.sp.dto.VersionePraticaDto;
import it.aruba.sp.entity.Pratica;
import it.aruba.sp.entity.VersionePratica;

public class Converter {

	public static PraticaDto convertPraticaToPraticaDto(Pratica pratica) {

		ModelMapper modelMapper = creaConfiguraMapperVersionePraticaToVersionePraticaDto();
		return modelMapper.map(pratica, PraticaDto.class);

	}

	public static VersionePraticaDto convertVersionePraticaToVersionePraticaDto(VersionePratica versionePratica) {
		
		ModelMapper modelMapper = creaConfiguraMapperVersionePraticaToVersionePraticaDto();
		return modelMapper.map(versionePratica, VersionePraticaDto.class);
	}
	
	private static ModelMapper creaConfiguraMapperVersionePraticaToVersionePraticaDto() {
		ModelMapper modelMapper = new ModelMapper();

		TypeMap<VersionePratica, VersionePraticaDto> propertyMapper = 
				modelMapper.createTypeMap(VersionePratica.class, VersionePraticaDto.class);
		
		propertyMapper.addMappings(
				mapper -> mapper.map(src -> src.getStato().getDescrizione(), VersionePraticaDto::setStato));
		propertyMapper.addMappings(
				mapper -> mapper.map(src -> src.getRisultato().getDescrizione(), VersionePraticaDto::setRisultato));
		return modelMapper;
	}

}
