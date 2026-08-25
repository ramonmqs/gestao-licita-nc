package br.com.novaconquista.gestaolicitanc.repository;

import br.com.novaconquista.gestaolicitanc.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
}