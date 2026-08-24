package br.com.novaconquista.gestaolicitanc.repository;

import br.com.novaconquista.gestaolicitanc.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Metodo mágico do Spring que busca no banco pelo e-mail/usuário
    Optional<Usuario> findByEmail(String email);
}