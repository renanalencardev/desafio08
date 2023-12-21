package usa.ggti.desafio08.model.pessoa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    boolean existsByEmail(String email);

    List<Pessoa> findAllByAtivoTrue();
}
