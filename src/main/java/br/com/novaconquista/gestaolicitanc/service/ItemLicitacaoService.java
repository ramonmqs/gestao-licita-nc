package br.com.novaconquista.gestaolicitanc.service;

import br.com.novaconquista.gestaolicitanc.dto.ItemLicitacaoRequestDTO;
import br.com.novaconquista.gestaolicitanc.dto.ItemLicitacaoResponseDTO;
import br.com.novaconquista.gestaolicitanc.model.ItemLicitacao;
import br.com.novaconquista.gestaolicitanc.model.Licitacao;
import br.com.novaconquista.gestaolicitanc.repository.ItemLicitacaoRepository;
import br.com.novaconquista.gestaolicitanc.repository.LicitacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemLicitacaoService {

    private final ItemLicitacaoRepository itemRepository;
    private final LicitacaoRepository licitacaoRepository;

    @Transactional
    public ItemLicitacaoResponseDTO adicionarItem(Long licitacaoId, ItemLicitacaoRequestDTO dto) {
        // 1. Verifica se o edital existe
        Licitacao licitacao = licitacaoRepository.findById(licitacaoId)
                .orElseThrow(() -> new RuntimeException("Licitação não encontrada com o ID: " + licitacaoId));

        // 2. Prepara o novo item (A trava dos 40% será acionada automaticamente pelo Hibernate ao salvar)
        ItemLicitacao item = new ItemLicitacao();
        item.setNumeroItem(dto.numeroItem());
        item.setDescricaoExataEdital(dto.descricaoExataEdital());
        item.setValorOriginal(dto.valorOriginal());
        item.setLicitacao(licitacao);

        // 3. Salva no banco e devolve o DTO formatado
        ItemLicitacao itemSalvo = itemRepository.save(item);
        return new ItemLicitacaoResponseDTO(itemSalvo);
    }

    public List<ItemLicitacaoResponseDTO> listarItensDaLicitacao(Long licitacaoId) {
        return itemRepository.findByLicitacaoIdOrderByNumeroItemAsc(licitacaoId)
                .stream()
                .map(ItemLicitacaoResponseDTO::new)
                .toList();
    }
    @Transactional
    public void excluirItem(Long idItem) {
        itemRepository.deleteById(idItem);
    }
}