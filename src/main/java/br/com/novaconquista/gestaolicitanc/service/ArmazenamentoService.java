package br.com.novaconquista.gestaolicitanc.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.core.sync.RequestBody;

import java.io.IOException;
import java.util.UUID;

@Service
public class ArmazenamentoService {


    public String fazerUploadPdf(MultipartFile arquivo) {
        try {
            // 1. Cria um nome único para o arquivo para evitar substituição (ex: 8f3a-edital.pdf)
            String nomeUnico = UUID.randomUUID() + "-" + arquivo.getOriginalFilename();

            // 2. Preparação do pacote para a nuvem (Opcional por enquanto, até configurarmos o S3Client)
            /*
            PutObjectRequest putOb = PutObjectRequest.builder()
                    .bucket("licitagestor-editais")
                    .key(nomeUnico)
                    .contentType("application/pdf")
                    .build();

            s3Client.putObject(putOb, RequestBody.fromInputStream(arquivo.getInputStream(), arquivo.getSize()));
            */

            // 3. Retorna a URL fictícia para testarmos a ligação com o banco de dados primeiro
            return "https://storage.oraclecloud.com/licitagestor-editais/" + nomeUnico;

        } catch (Exception e) {
            throw new RuntimeException("Falha ao processar o arquivo PDF: " + e.getMessage());
        }
    }
}