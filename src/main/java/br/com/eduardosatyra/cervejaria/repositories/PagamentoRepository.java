package br.com.eduardosatyra.cervejaria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.eduardosatyra.cervejaria.domain.Pagamento;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {

}
