package com.minhas.midias.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.minhas.midias.entidade.Midia;
import com.minhas.midias.repository.MidiaRepository;

@Controller
public class MidiaController {
	
	@Value("${app.version}")
	private String appVersion;

    @Autowired
    private MidiaRepository midiaRepository;

    private static final String UPLOAD_DIR = "C:/ProjetoPessoalJava/midias/uploads/";

    @GetMapping("/")
    public String listar(Model model) {
        model.addAttribute("midias", midiaRepository.findAll());
        model.addAttribute("appVersion", appVersion);
        return "index";
    }

    @GetMapping("/nova")
    public String novaMidiaForm(Model model) {
        model.addAttribute("midia", new Midia());
        return "form";
    }
    
    @PostMapping("/atualizar")
    public String atualizar(@ModelAttribute Midia midiaForm, @RequestParam(value = "imagem", required = false) MultipartFile imagem) throws IOException {
        // Primeiro busca a mídia que já existe no banco
        Midia midiaExistente = midiaRepository.findById(midiaForm.getId())
                .orElseThrow(() -> new IllegalArgumentException("ID inválido: " + midiaForm.getId()));

        // Atualiza campos de texto
        midiaExistente.setTitulo(midiaForm.getTitulo());
        midiaExistente.setDescricao(midiaForm.getDescricao());
        midiaExistente.setTipo(midiaForm.getTipo());
        midiaExistente.setVisto(midiaForm.isVisto());

        // atualize outros campos aqui se tiver

        // Se enviou uma nova imagem
        if (imagem != null && !imagem.isEmpty()) {
            // Apaga a imagem antiga
            if (midiaExistente.getCapaPath() != null) {
                String nomeArquivoAntigo = midiaExistente.getCapaPath().replace("/uploads/", "");
                Path caminhoImagemAntiga = Paths.get(UPLOAD_DIR + nomeArquivoAntigo);
                try {
                    Files.deleteIfExists(caminhoImagemAntiga);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            // Cria um novo nome para a imagem
            String nomeArquivoNovo = UUID.randomUUID() + "_" + imagem.getOriginalFilename();
            Path uploadPath = Paths.get(UPLOAD_DIR);
            Files.createDirectories(uploadPath); // garante que a pasta existe

            Path caminhoCompleto = uploadPath.resolve(nomeArquivoNovo);
            Files.copy(imagem.getInputStream(), caminhoCompleto, StandardCopyOption.REPLACE_EXISTING);

            // Atualiza o caminho da nova imagem
            midiaExistente.setCapaPath("/uploads/" + nomeArquivoNovo);
        }

        // Salva o objeto atualizado
        midiaRepository.save(midiaExistente);
        return "redirect:/";
    }


    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Midia midia, @RequestParam("imagem") MultipartFile imagem) throws IOException {
        if (!imagem.isEmpty()) {
            String nomeArquivo = UUID.randomUUID() + "_" + imagem.getOriginalFilename();
            
            Path uploadPath = Paths.get("C:/ProjetoPessoalJava/midias/uploads/");
            Files.createDirectories(uploadPath); // Garante que a pasta existe

            Path caminhoCompleto = uploadPath.resolve(nomeArquivo);
            Files.copy(imagem.getInputStream(), caminhoCompleto, StandardCopyOption.REPLACE_EXISTING);
            
            // Esse será o caminho da imagem visível na URL (relativo ao static-locations configurado)
            midia.setCapaPath("/uploads/" + nomeArquivo);
        }

        midiaRepository.save(midia);
        return "redirect:/";
    }
    
    @GetMapping("/editar/{id}")
    public String mostrarFormularioDeEdicao(@PathVariable Long id, Model model) {
        Midia midia = midiaRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));
        
        model.addAttribute("midia", midia);
        return "editar"; // o nome do seu template HTML para edição (editar.html)
    }

    
    @GetMapping("/remover/{id}")
    public String remover(@PathVariable Long id) {
        Optional<Midia> midiaOptional = midiaRepository.findById(id);
        if (midiaOptional.isPresent()) {
            Midia midia = midiaOptional.get();
            
            // Apagar imagem da pasta
            if (midia.getCapaPath() != null) {
                String nomeArquivo = midia.getCapaPath().replace("/uploads/", "");
                Path caminhoImagem = Paths.get(UPLOAD_DIR + nomeArquivo);
                try {
                    Files.deleteIfExists(caminhoImagem);
                } catch (IOException e) {
                    e.printStackTrace(); // ou loga com Logger
                }
            }

            midiaRepository.deleteById(id);
        }
        return "redirect:/";
    }

    
	/*
	 * @GetMapping("/remover/{id}") public String remover(@PathVariable Long id) {
	 * midiaRepository.deleteById(id); return "redirect:/"; }
	 */
}

