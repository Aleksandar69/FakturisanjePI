package com.aleksandar.fakturisanje.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.aleksandar.fakturisanje.dto.GrupaRobeDto;
import com.aleksandar.fakturisanje.model.GrupaRobe;

@Component
public class GrupaRobeToGrupaRobeDto implements Converter<GrupaRobe, GrupaRobeDto> {

	@Override
	public GrupaRobeDto convert(GrupaRobe source) {
		GrupaRobeDto grupaRobeDto = new GrupaRobeDto();

		grupaRobeDto.setId(source.getId());
		grupaRobeDto.setNazivGrupe(source.getGrupaNaziv());
		grupaRobeDto.setPreduzece(source.getPreduzece().getId());
		grupaRobeDto.setPdv(source.getPdv().getId());

		return grupaRobeDto;
	}

	public List<GrupaRobeDto> convert(List<GrupaRobe> grupaRobeList) {
		List<GrupaRobeDto> grupaRobeDtos = new ArrayList<>();
		for (GrupaRobe gr : grupaRobeList) {
			grupaRobeDtos.add(convert(gr));
		}
		return grupaRobeDtos;
	}
}
