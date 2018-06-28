package com.ufc.br.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class PaginaInicialController {
	
	@RequestMapping("/index")
	public String paginaInicial() {
		return "PolosCloset";
	}
	@RequestMapping("/Contato")
	public String contato() {
		return "contato";
	}
	
	@RequestMapping("/Sobre")
	public String sobre() {
		return "Sobre";
	}
	@RequestMapping("/Galeria")
	public String galeria() {
		return "Galeria";
	}

}

