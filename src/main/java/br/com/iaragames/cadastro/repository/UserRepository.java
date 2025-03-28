package br.com.iaragames.cadastro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.iaragames.cadastro.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
