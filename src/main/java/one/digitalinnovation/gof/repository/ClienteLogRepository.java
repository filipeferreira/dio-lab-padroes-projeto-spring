package one.digitalinnovation.gof.repository;

import one.digitalinnovation.gof.model.ClienteLog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteLogRepository extends CrudRepository<ClienteLog, Long> {

}
