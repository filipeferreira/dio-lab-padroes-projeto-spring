package one.digitalinnovation.gof.observer;

import one.digitalinnovation.gof.model.Cliente;
import org.springframework.stereotype.Service;

/** *
 * Mocking an email creation when the client is removed or has its address changed.
 */
@Service
public class EnviarEmailClienteService implements ClienteEventListener {

    @Override
    public void update(Cliente cliente) {
        if (cliente.isAtivo()) {
            System.out.println("Enviando email com o texto: Informar aos funcionários alteração de endereço do cliente " + cliente.getNome());
        } else {
            System.out.println("Enviando email com o texto: Verificar o motivo de o cliente " + cliente.getNome() + " ser removido da base de clientes.");
        }
    }
}
