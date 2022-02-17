package br.com.dlima.crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.dlima.crud.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	@Query(value = "SELECT u FROM Usuario u WHERE 	UPPER(TRIM(u.nome)) LIKE %?1%") // ?1 usar primeiro parametro do metodo
	List<Usuario> buscarPorNome(String nome);
	
}
