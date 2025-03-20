package one.digitalinnovation.gof.model;

import one.digitalinnovation.gof.enums.OperacaoEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class ClienteLog {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_cliente", nullable = false)
	private Cliente cliente;

	@Column(nullable = false)
	private String nome;

	@ManyToOne
	@JoinColumn(name = "id_endereco", nullable = false)
	private Endereco endereco;

	@Column(name = "data_hora_log", nullable = false)
	private LocalDateTime dataHoraLog;

	@Column(nullable = false)
	private OperacaoEnum operacao;

	@Column(nullable = false)
	private boolean ativo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public LocalDateTime getDataHoraLog() {
		return dataHoraLog;
	}

	public void setDataHoraLog(LocalDateTime dataHoraLog) {
		this.dataHoraLog = dataHoraLog;
	}

	public OperacaoEnum getOperacao() {
		return operacao;
	}

	public void setOperacao(OperacaoEnum operacao) {
		this.operacao = operacao;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
}
