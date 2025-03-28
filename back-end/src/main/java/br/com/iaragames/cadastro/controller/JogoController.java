package br.com.iaragames.cadastro.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.iaragames.cadastro.model.Jogo;
import br.com.iaragames.cadastro.repository.JogoRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/jogos")
@CrossOrigin(origins = "*") // Libera requisições de qualquer origem (ex: seu frontend)
public class JogoController {

    private final JogoRepository jogoRepository;

    public JogoController(JogoRepository jogoRepository) {
        this.jogoRepository = jogoRepository;
    }

    @GetMapping
    public List<Jogo> listarTodos() {
        return jogoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Jogo> buscarPorId(@PathVariable Long id) {
        return jogoRepository.findById(id);
    }

    @PostMapping
    public Jogo criar(@RequestBody Jogo jogo) {
        return jogoRepository.save(jogo);
    }

    @PutMapping("/{id}")
    public Jogo atualizar(@PathVariable Long id, @RequestBody Jogo jogoAtualizado) {
        return jogoRepository.findById(id).map(jogo -> {
            jogo.setName(jogoAtualizado.getName());
            jogo.setGenre(jogoAtualizado.getGenre());
            jogo.setReleaseDate(jogoAtualizado.getReleaseDate());
            jogo.setProducer(jogoAtualizado.getProducer());
            return jogoRepository.save(jogo);
        }).orElseThrow(() -> new RuntimeException("Jogo não encontrado"));
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        jogoRepository.deleteById(id);
    }
}
