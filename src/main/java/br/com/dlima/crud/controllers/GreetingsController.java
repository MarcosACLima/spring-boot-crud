package br.com.dlima.crud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.dlima.crud.model.Usuario;
import br.com.dlima.crud.repository.UsuarioRepository;

@RestController
public class GreetingsController {
	
	@Autowired /* IC/CD ou CDI - Injeção de dependencias */
	private UsuarioRepository usuarioRepository;
	
	// http://localhost:8080/mostrarnome/digitar string name
	@RequestMapping(value = "/mostrarnome/{name}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String greetingsText(@PathVariable String name) {
		return "CRUD Spring Boot API " + name + "!";
	}
	
	@RequestMapping(value = "/olamundo/{msg}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String retornarOlaMundo(@PathVariable String msg) {
		
		Usuario usuario = new Usuario();
		usuario.setNome(msg); // set nome
		
		usuarioRepository.save(usuario); // gravar no banco de dados
		
		return "Ola mundo " + msg;
	}

}
