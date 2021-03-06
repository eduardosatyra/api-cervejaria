package br.com.eduardosatyra.cervejaria.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.eduardosatyra.cervejaria.domain.Categoria;
import br.com.eduardosatyra.cervejaria.domain.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

	/**
	 * 
	 * @param nome
	 * @param categorias
	 * @param pageRequest
	 * @return
	 */

	@Transactional(readOnly = true)
	Page<Produto> findDistinctByNomeContainingAndCategoriasIn(String nome, List<Categoria> categorias,
			Pageable pageRequest);

}
