package br.com.contatos.rest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.contatos.rest.model.Contato;
@Service("ContatoService")
@Transactional
public class ContatoServiceImpl implements ContatoService {

	private static final AtomicLong counter = new AtomicLong();
	private List<Contato> listaContatos = new ArrayList<Contato>();
	
	
	@Override
	public List<Contato> listarContatos() {		
		return preencherListaContatos();
	}
	
	private List<Contato> preencherListaContatos(){
		listaContatos.add(new Contato(counter.incrementAndGet(), "Deivison", "81 1234-4321"));
		listaContatos.add(new Contato(counter.incrementAndGet(), "Francisco", "81 6789-9876"));
		return listaContatos;
	}

	@Override
	public void cadastrarContato(Contato contato) {
		listaContatos.add(contato);

	}

	@Override
	public void deletarContato(Contato contato) {
		// TODO Auto-generated method stub

	}

	@Override
	public void alterarContato(Contato contato) {
		// TODO Auto-generated method stub

	}

	@Override
	public Contato buscarContato(String telefone) {
		// TODO Auto-generated method stub
		return null;
	}

}
