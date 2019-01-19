package br.com.eduardosatyra.cervejaria.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.eduardosatyra.cervejaria.domain.Categoria;
import br.com.eduardosatyra.cervejaria.repositories.CategoriaRepository;
import br.com.eduardosatyra.cervejaria.services.exceptions.DataIntegrityException;
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
	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);

		return obj.orElseThrow(() -> new ObjNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}

	/**
	 * 
	 * @param obj
	 * @return
	 */
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	/**
	 * @param obj
	 * @return
	 */
	public Categoria update(Categoria obj) {
		find(obj.getId());
		return repo.save(obj);
	}

	/**
	 * 
	 * @param id
	 */
	public void delete(Integer id) {
		find(id);

		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException(
					"Não é possível deletar essa categoria, exitem produtos associados a ela.");
		}
	}

	/**
	 * @return
	 */
	public List<Categoria> findAll() {
		return repo.findAll();
	}

}
