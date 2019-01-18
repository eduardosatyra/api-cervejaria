package br.com.eduardosatyra.cervejaria.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eduardosatyra.cervejaria.domain.Categoria;
import br.com.eduardosatyra.cervejaria.repositories.CategoriaRepository;
import br.com.eduardosatyra.cervejaria.services.exceptions.ObjNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;

	/**
	 * 
	 * @param id
	 * @return
	 */
	public Categoria buscar(Integer id) {
		Optional<Categoria> obj = repo.findById(id);

		return obj.orElseThrow(() -> new ObjNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}

}
