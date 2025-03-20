package one.digitalinnovation.gof.observer;

import one.digitalinnovation.gof.enums.ClienteEventEnum;
import one.digitalinnovation.gof.model.Cliente;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Publisher class that notifies all listeners when a client event occurs.
 */
@Service
public class ClienteNotifierService {

    private Map<ClienteEventEnum, List<ClienteEventListener>> listeners;

    @Autowired
    private BeanFactory beanFactory;

    @PostConstruct
    public void init(){
        listeners = new HashMap<>();
        listeners.put(ClienteEventEnum.LOG, new ArrayList<>());
        listeners.put(ClienteEventEnum.ENVIAR_EMAIL, new ArrayList<>());
        subscribe(ClienteEventEnum.LOG, beanFactory.getBean(ClienteLogService.class));
        subscribe(ClienteEventEnum.ENVIAR_EMAIL, beanFactory.getBean(EnviarEmailClienteService.class));
    }

    public void subscribe(final ClienteEventEnum eventType, ClienteEventListener listener){
        var selectedListeners = listeners.get(eventType);
        selectedListeners.add(listener);
    }

    public void notify(final ClienteEventEnum eventType, final Cliente cliente){
        listeners.get(eventType).forEach(l -> l.update(cliente));
    }

    public void unsubscribe(final ClienteEventEnum eventType, ClienteEventListener listener){
        var selectedListeners = listeners.get(eventType);
        selectedListeners.remove(listener);
    }
}
