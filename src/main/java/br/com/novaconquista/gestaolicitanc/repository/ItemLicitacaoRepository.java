package br.com.novaconquista.gestaolicitanc.repository;

import br.com.novaconquista.gestaolicitanc.model.ItemLicitacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemLicitacaoRepository extends JpaRepository<ItemLicitacao, Long> {

    // Alinhado para usar 'numero' em vez de 'numeroItem'
    List<ItemLicitacao> findByLicitacaoIdOrderByNumeroAsc(Long licitacaoId);
}