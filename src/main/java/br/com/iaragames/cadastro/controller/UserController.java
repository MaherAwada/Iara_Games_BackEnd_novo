package br.com.iaragames.cadastro.controller;

import br.com.iaragames.cadastro.model.User;
import br.com.iaragames.cadastro.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<User> listarTodos() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<User> buscarPorId(@PathVariable Long id) {
        return userRepository.findById(id);
    }

    @PostMapping
    public User criar(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PutMapping("/{id}")
    public User atualizar(@PathVariable Long id, @RequestBody User usuarioAtualizado) {
        return userRepository.findById(id).map(user -> {
            user.setName(usuarioAtualizado.getName());
            user.setEmail(usuarioAtualizado.getEmail());
            user.setPassword(usuarioAtualizado.getPassword());
            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        userRepository.deleteById(id);
    }
}
