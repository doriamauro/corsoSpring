package it.corso.java.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.StaleStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import it.corso.java.dto.ContoCorrenteDTO;
import it.corso.java.dto.ErroreDTO;
import it.corso.java.dto.ErroreStaleObject;
import it.corso.java.dto.MovimentoDTO;
import it.corso.java.entity.ContoCorrente;
import it.corso.java.entity.ContoCorrenteOLD;
import it.corso.java.entity.Movimento;
import it.corso.java.entity.MovimentoOLD;
import it.corso.java.service.ContoCorrenteService;

@RestController
@RequestMapping("/conti")
public class ContoCorrenteController {

	@Autowired
	private ContoCorrenteService serviceCC;
	
	@DeleteMapping(path="/{numero}")
	public void cancellaConto(@PathVariable Integer numero) {
		serviceCC.cancellaConto(numero);
	}
	
	@PutMapping(path="/{numero}", produces = "application/json")
	public ContoCorrenteDTO cancellaMovimenti(@PathVariable Integer numero) {
		ContoCorrente cc = serviceCC.rimuoviMovimenti(numero);
		return CCtoCCDTO(cc);
		
	}
	
	@PostMapping(produces = "application/json")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ContoCorrenteDTO apriConto(Integer userIdInt, Integer userIdCoint, Double importo) {
		//validazione di primo livello
		ContoCorrente cc = serviceCC.apriConto(importo, userIdInt, userIdCoint);
		return CCtoCCDTO(cc);
	}
	
	@PatchMapping(path="/{numero}", produces = "application/json")
	public ContoCorrenteDTO modificaSaldo(@PathVariable Integer numero, Double saldo, Integer idOperatore, Integer versione) {
		ContoCorrente cc = serviceCC.modificaSaldo(numero, saldo, idOperatore, versione);
		return CCtoCCDTO(cc);
	}
	
	@GetMapping(path="/{numero}", produces = "application/json")
	public ContoCorrenteDTO leggiConto(@PathVariable Integer numero) {
		ContoCorrente cc = serviceCC.leggiConto(numero);
		return CCtoCCDTO(cc);
	}
	
	@ExceptionHandler(StaleStateException.class)
	public ResponseEntity<ErroreStaleObject> exceptionHForStaleObject(StaleStateException e, HttpServletRequest request){
		ErroreStaleObject eso =new ErroreStaleObject();
		
//		System.out.println("getContextPath --> " + request.getContextPath());
//		System.out.println("getServletPath --> " + request.getPathInfo());
		
		eso.setMsg(e.getMessage());
		String s = request.getPathInfo();
//		System.out.println(Integer.parseInt(s.substring(s.lastIndexOf("/")+1)));
		ContoCorrente cc = serviceCC.leggiConto(Integer.parseInt(s.substring(s.lastIndexOf("/")+1)));
		eso.setConto(CCtoCCDTO(cc));
		
		ResponseEntity<ErroreStaleObject> re = new ResponseEntity<>(eso, HttpStatus.CONFLICT);
		return re;
		
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErroreDTO> exceptionH(Exception e){
		ErroreDTO dtoE = new ErroreDTO();
		dtoE.setMessaggio(e.getMessage());
		dtoE.setDataErrore(new Date());
		ResponseEntity<ErroreDTO> re = new ResponseEntity<>(dtoE, HttpStatus.INTERNAL_SERVER_ERROR);
		return re;
		
	}
	
	private ContoCorrente CCDTOtoCC(ContoCorrenteDTO ccDto) {
		ContoCorrente cc = new ContoCorrente();
		cc.setNumero(ccDto.getNumeroConto());
		cc.setDataApertura(ccDto.getDataApertura());
		cc.setSaldo(ccDto.getSaldo());
		
		List<Movimento> movimenti = new ArrayList<>();
		
		for (MovimentoDTO mDto : ccDto.getListaOperazioni()) {
			Movimento m = new Movimento();
			
			m.setDataOperazione(mDto.getDataOperazione());
			m.setImporto(mDto.getImporto());
			m.setTipo(mDto.getTipo());
			//m.setOperatore(mDto.getIdOperatore());
			
			movimenti.add(m);
		}
		
		cc.setListaOperazioni(movimenti);
		
		return cc;

	}
	
	private ContoCorrenteDTO CCtoCCDTO(ContoCorrente cc) {
		ContoCorrenteDTO ccDto = new ContoCorrenteDTO();
		ccDto.setNumeroConto(cc.getNumero());
		ccDto.setDataApertura(cc.getDataApertura());
		ccDto.setSaldo(cc.getSaldo());
		ccDto.setVersione(cc.getVersione());
		
		List<MovimentoDTO> lDto = new ArrayList<>();
		
		for (Movimento m : cc.getListaOperazioni()) {
			MovimentoDTO mDto = new MovimentoDTO();
//			mDto.setId(m.getId());
			mDto.setImporto(m.getImporto());
			mDto.setDataOperazione(m.getDataOperazione());
			mDto.setTipo(m.getTipo());

			lDto.add(mDto);
		}
		ccDto.setListaOperazioni(lDto);
		return ccDto;
	}
	
}
