package br.com.novaconquista.gestaolicitanc.service;

import br.com.novaconquista.gestaolicitanc.model.ItemLicitacao;
import br.com.novaconquista.gestaolicitanc.repository.ItemLicitacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemLicitacaoService {

    @Autowired
    private ItemLicitacaoRepository repository;

    public ItemLicitacao salvar(ItemLicitacao item) {
        return repository.save(item);
    }
}