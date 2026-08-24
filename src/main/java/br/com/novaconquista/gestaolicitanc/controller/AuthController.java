package br.com.novaconquista.gestaolicitanc.controller;

import br.com.novaconquista.gestaolicitanc.model.Usuario;
import br.com.novaconquista.gestaolicitanc.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Ferramenta que lê a senha criptografada do banco
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostMapping("/login")
    public ResponseEntity<?> fazerLogin(@RequestBody Map<String, String> credenciais) {
        String email = credenciais.get("email");
        String senha = credenciais.get("senha");

        // 1. Vai no banco (Neon) e procura o usuário ('ramon' ou 'marivaldo')
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(email);

        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();

            // 2. Compara a senha digitada com a criptografia do banco
            if (passwordEncoder.matches(senha, usuario.getSenha())) {
                return ResponseEntity.ok(Map.of(
                        "status", "sucesso",
                        "mensagem", "Acesso Autorizado",
                        "nome", usuario.getNome(),
                        "role", usuario.getRole(),
                        "token", "NC-AUTH-TOKEN-2026"
                ));
            }
        }

        // Se errou o usuário ou a senha, barra o acesso
        return ResponseEntity.status(401).body(Map.of(
                "erro", "Credenciais inválidas. Verifique seu usuário e senha."
        ));
    }
}