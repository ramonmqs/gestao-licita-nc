package br.com.novaconquista.gestaolicitanc.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*") // Permite que o React converse com esta rota
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<?> fazerLogin(@RequestBody Map<String, String> credenciais) {
        String email = credenciais.get("email");
        String senha = credenciais.get("senha");

        // Validação no servidor (futuramente, isso buscará na tabela de Usuários do banco)
        if ("admin@novaconquista.com.br".equals(email) && "admin123".equals(senha)) {
            // Emite um crachá temporário de sucesso
            return ResponseEntity.ok(Map.of(
                    "status", "sucesso",
                    "mensagem", "Acesso Autorizado",
                    "token", "NC-AUTH-TOKEN-2026"
            ));
        } else {
            // Retorna erro 401 (Não Autorizado)
            return ResponseEntity.status(401).body(Map.of(
                    "erro", "Credenciais inválidas. Verifique seu e-mail e senha."
            ));
        }
    }
}