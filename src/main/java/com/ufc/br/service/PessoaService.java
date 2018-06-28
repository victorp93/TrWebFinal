package com.ufc.br.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ufc.br.model.Pessoa;
import com.ufc.br.repository.PessoaRepository;

@Service
public class PessoaService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	
	public void adicionarPessoa(Pessoa pessoa) {
		pessoa.setSenha(new BCryptPasswordEncoder().encode(pessoa.getSenha()));		
		pessoaRepository.save(pessoa);
	}


	public List<Pessoa> retornarTodasAsPessoas() {
		
		return pessoaRepository.findAll();
	}
	
	public Pessoa buscarPorId(Long id) {
		return pessoaRepository.getOne(id);
	}


	public void removerPessoa(Long id) {
		pessoaRepository.deleteById(id);
		
	}
}