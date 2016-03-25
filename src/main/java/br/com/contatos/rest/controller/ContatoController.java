package br.com.contatos.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

}
