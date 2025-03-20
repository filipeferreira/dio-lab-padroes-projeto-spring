package one.digitalinnovation.gof.observer;

import one.digitalinnovation.gof.model.Cliente;


/**
 * Interface that defines the <b>Observer</b> pattern in the customer domain.
 */
public interface ClienteEventListener {

    void update(Cliente cliente);
}
