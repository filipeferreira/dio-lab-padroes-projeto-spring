package one.digitalinnovation.gof.observer;

import one.digitalinnovation.gof.enums.OperacaoEnum;
import one.digitalinnovation.gof.model.Cliente;
import one.digitalinnovation.gof.model.ClienteLog;
import one.digitalinnovation.gof.repository.ClienteLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ClienteLogService implements ClienteEventListener {

    @Autowired
    private ClienteLogRepository clienteLogRepository;

    @Override
    public void update(Cliente cliente) {

        var clienteLog = new ClienteLog();
        clienteLog.setId(cliente.getId());
        clienteLog.setCliente(cliente);
        clienteLog.setIdEndereco(cliente.getEndereco().getId());
        clienteLog.setNome(cliente.getNome());
        clienteLog.setOperacao(getOperacaoEnum(cliente));
        clienteLog.setDataHoraLog(LocalDateTime.now());
        clienteLog.setAtivo(cliente.isAtivo());

        clienteLogRepository.save(clienteLog);

        System.out.println("Cliente log salvo com sucesso!");

    }

    private OperacaoEnum getOperacaoEnum(Cliente cliente) {
        if (!cliente.isAtivo()) {
            return OperacaoEnum.DELETE;
        }

        if (clienteLogRepository.existsById(cliente.getId())) {
            return OperacaoEnum.UPDATE;
        }
        return OperacaoEnum.INSERT;
    }
}
