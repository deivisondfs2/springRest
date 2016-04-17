package br.com.contatos.rest.controller;

import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.contatos.rest.model.Contato;
import br.com.contatos.rest.service.ContatoService;

@RestController
@RequestMapping(value = "/contatos")
public class ContatoController {

	@Autowired
	ContatoService contatoService;

	public List<Contato> listaDeContatos;

	@RequestMapping(value = "/listaContatos", method = RequestMethod.GET)
	public ResponseEntity<List<Contato>> listContatos() {
		listaDeContatos = contatoService.listarContatos();
		if (listaDeContatos.isEmpty()) {
			return new ResponseEntity<List<Contato>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Contato>>(listaDeContatos, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/cadastrar", method = RequestMethod.POST)
	public ResponseEntity<Void> cadastrarContato(@RequestBody Contato contato, UriComponentsBuilder ucbBuilder){
		
		contatoService.cadastrarContato(contato);
		
		HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucbBuilder.path("/contatos/listaContatos").buildAndExpand().toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/atualizar/{_id}", method = RequestMethod.PUT)
	public ResponseEntity<Contato> updateContato(@PathVariable("_id") long _id, @RequestBody Contato contato){
		
		Contato contatoConsultado = contatoService.buscarContatoPorId(_id);
		
		if (ObjectUtils.notEqual(contatoConsultado, null)) {
			contatoConsultado.setNome(contato.getNome());
			contatoConsultado.setTelefone(contato.getTelefone());
			contatoService.alterarContato(contatoConsultado);
			return new ResponseEntity<Contato>(contatoConsultado, HttpStatus.OK);
		}
		
		return new ResponseEntity<Contato>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="/{telefone}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Contato> getContato(@PathVariable("telefone") String telefone){
		Contato contato = contatoService.buscarContatoPorTelefone(telefone);
		if (ObjectUtils.notEqual(contato, null)) {
			return new ResponseEntity<Contato>(contato, HttpStatus.OK);
		}
		return new ResponseEntity<Contato> (HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "/remove/{_id}", method = RequestMethod.DELETE)
	public ResponseEntity<Contato> removerContato(@PathVariable("_id") long _id){
		Contato contato = contatoService.buscarContatoPorId(_id);
		
		if (ObjectUtils.notEqual(contato, null)) {
			contatoService.deletarContato(contato);
			return new ResponseEntity<Contato>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Contato>(HttpStatus.NOT_FOUND);
		
	}
	

}
