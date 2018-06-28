package com.ufc.br.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ufc.br.model.Produto;
import com.ufc.br.repository.ProdutoRepository;
import com.ufc.br.util.ArquivoUtils;

@Service
public class ProdutoService {


	@Autowired
	private ProdutoRepository produtoRepository;
	
	
	public void adicionarProduto(Produto produto, MultipartFile imagem) {
		
		produtoRepository.save(produto);
		String caminho = "Imagem/" + produto.getId() + ".jpg";
		ArquivoUtils.salvarImagem(caminho,imagem);
		
		
	}
	public List<Produto> retornarTodosOsProdutos() {
		
		return produtoRepository.findAll();
	}
	public void removerProduto(Long id) {
		produtoRepository.deleteById(id);
		
	}
	public Produto buscarPorId(Long id) {
		return produtoRepository.getOne(id);
	}

	

	
}