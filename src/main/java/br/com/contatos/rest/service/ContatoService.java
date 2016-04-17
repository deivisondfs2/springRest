package br.com.contatos.rest.service;

import java.util.List;

import br.com.contatos.rest.model.Contato;

public interface ContatoService {

	public List<Contato> listarContatos();
	
	public void cadastrarContato(Contato contato);
	
	public void deletarContato(Contato contato);
	
	public void alterarContato(Contato contato);
	
	public Contato buscarContatoPorTelefone(String telefone);
	
	public Contato buscarContatoPorId(long _id);
}
