package br.com.contatos.rest.service;

import java.util.ArrayList;
import java.util.Iterator;
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
		listaContatos.add(new Contato(counter.incrementAndGet(), "Deivison", "8112344321"));
		listaContatos.add(new Contato(counter.incrementAndGet(), "Francisco", "8167899876"));
		return listaContatos;
	}

	@Override
	public void cadastrarContato(Contato contato) {
		listaContatos.add(contato);

	}

	@Override
	public void deletarContato(Contato contato) {
		listaContatos.remove(contato);
	}

	@Override
	public void alterarContato(Contato contato) {
		int index = listaContatos.indexOf(contato);
		listaContatos.set(index, contato);
	}

	@Override
	public Contato buscarContatoPorTelefone(String telefone) {
		Iterator<Contato> iterator = listaContatos.iterator();
		while (iterator.hasNext()) {
			Contato contato = iterator.next();
			if (contato.getTelefone().equals(telefone)) {
				return contato;
			}
		}
		return null;
	}

	@Override
	public Contato buscarContatoPorId(long _id) {
		Iterator<Contato> iterator = listaContatos.iterator();
		while (iterator.hasNext()) {
			Contato contato = iterator.next();
			if (contato.get_id().equals(_id)) {
				return contato;
			}
		}
		return null;
	}

}
