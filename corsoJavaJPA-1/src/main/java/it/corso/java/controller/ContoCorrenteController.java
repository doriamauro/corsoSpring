package it.corso.java.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import it.corso.java.dto.ContoCorrenteDTO;
import it.corso.java.dto.ErroreDTO;
import it.corso.java.dto.MovimentoDTO;
import it.corso.java.entity.ContoCorrente;
import it.corso.java.entity.Movimento;
import it.corso.java.service.ContoCorrenteService;

@RestController
@RequestMapping("/conti")
public class ContoCorrenteController {

	@Autowired
	private ContoCorrenteService serviceCC;
	
	@PostMapping(produces = "application/json")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ContoCorrenteDTO apriConto(Integer userId, Double importo) {
		//validazione di primo livello
		ContoCorrente cc = serviceCC.apriConto(importo, userId);
		return CCtoCCDTO(cc);
	}
	
	@PatchMapping(path="/{numero}", produces = "application/json")
	public ContoCorrenteDTO modificaSaldo(@PathVariable Integer numero, Double saldo) {
		ContoCorrente cc = serviceCC.modificaSaldo(numero, saldo);
		return CCtoCCDTO(cc);
	}
	
	@GetMapping(path="/{numero}", produces = "application/json")
	public ContoCorrenteDTO leggiConto(@PathVariable Integer numero) {
		ContoCorrente cc = serviceCC.leggiConto(numero);
		return CCtoCCDTO(cc);
	}
	
	@ExceptionHandler
	public ResponseEntity<ErroreDTO> exceptionH(Exception e){
		ErroreDTO dtoE = new ErroreDTO();
		dtoE.setMessaggio(e.getMessage());
		dtoE.setDataErrore(new Date());
		ResponseEntity<ErroreDTO> re = new ResponseEntity<>(dtoE, HttpStatus.INTERNAL_SERVER_ERROR);
		return re;
		
	}
	
	private ContoCorrenteDTO CCtoCCDTO(ContoCorrente cc) {
		ContoCorrenteDTO ccDto = new ContoCorrenteDTO();
		ccDto.setNumeroConto(cc.getNumero());
		ccDto.setDataApertura(cc.getDataApertura());
		ccDto.setSaldo(cc.getSaldo());
		
		List<MovimentoDTO> lDto = new ArrayList<>();
		
		for (Movimento m : cc.getListaOperazioni()) {
			MovimentoDTO mDto = new MovimentoDTO();
			mDto.setId(m.getId());
			mDto.setImporto(m.getImporto());
			mDto.setDataOperazione(m.getDataOperazione());
			mDto.setTipo(m.getTipo());

			lDto.add(mDto);
		}
		ccDto.setListaOperazioni(lDto);
		return ccDto;
	}
	
}
