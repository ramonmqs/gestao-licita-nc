package br.com.novaconquista.gestaolicitanc.repository;

import br.com.novaconquista.gestaolicitanc.model.Licitacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LicitacaoRepository extends JpaRepository<Licitacao, Long> {
}