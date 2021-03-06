package br.com.dlima.crud.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.dlima.crud.model.Usuario;
import br.com.dlima.crud.repository.UsuarioRepository;

@RestController
public class GreetingsController {
	
	@Autowired /* IC/CD ou CDI - Injeção de dependencias */
	private UsuarioRepository usuarioRepository;
	
	// http://localhost:8080/crud/mostrarnome/digitar string name
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
	
	/* Primeiro metodo de API */
	@GetMapping(value = "listatodos")
	@ResponseBody // Retorna os dados para o corpo 
	public ResponseEntity<List<Usuario>> listaUsuarios() {
		List<Usuario> usuarios = usuarioRepository.findAll(); // executar consulta no banco de dados
				
		return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK); // Retornar a lista em JSON
	}
	
	@PostMapping(value = "salvar") // mapeia a URL
	@ResponseBody // descriçao da resposta
	public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario) { // Recebe os dados para salvar
		Usuario usuario2 = usuarioRepository.save(usuario);
		
		return new ResponseEntity<Usuario>(usuario2, HttpStatus.CREATED);
	}
	
	@PutMapping(value = "atualizar") // mapeia a URL
	@ResponseBody // descriçao da resposta
	public ResponseEntity<?> atualizar(@RequestBody Usuario usuario) { // Recebe os dados para salvar
		
		if (usuario.getId() == null) {
			return new ResponseEntity<String>("ID não foi informado para atualizacao", HttpStatus.OK);
		}
		
		Usuario usuario2 = usuarioRepository.saveAndFlush(usuario);
		
		return new ResponseEntity<Usuario>(usuario2, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "delete") // mapeia a URL
	@ResponseBody // descriçao da resposta
	public ResponseEntity<String> delete(@RequestParam Long id) { // Recebe os dados para deletar
		usuarioRepository.deleteById(id);
		
		return new ResponseEntity<String>("Usuario deletado com sucesso!", HttpStatus.OK);
	}
	
	@GetMapping(value = "buscarId") // mapeia a URL
	@ResponseBody // descriçao da resposta
	public ResponseEntity<Usuario> buscarId(@RequestParam(name = "id") Long id) { // Recebe os dados para consultar por ai
		Usuario usuario = usuarioRepository.findById(id).get();
		
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}
	
	@GetMapping(value = "buscarPorNome") // mapeia a URL
	@ResponseBody // descriçao da resposta
	public ResponseEntity<List<Usuario>> buscarPorNome(@RequestParam(name = "nome") String nome) { // Recebe os dados para consultar por ai
		List<Usuario> usuarios = usuarioRepository.buscarPorNome(nome.trim().toUpperCase()); // trim retirar espaço
		
		return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
	}

}
