package one.digitalinnovation.gof.repository;

import one.digitalinnovation.gof.model.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long> {

    boolean existsByIdAndAtivoIsTrue(Long id);

    Optional<Cliente> findByIdAndAtivoIsTrue(Long id);
}
