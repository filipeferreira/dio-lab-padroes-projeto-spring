package one.digitalinnovation.gof.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

/**
 * Os atributos desse modelo foram gerados automaticamente pelo site
 * jsonschema2pojo.org. Para isso, usamos o JSON de retorno da API do ViaCEP.
 * 
 * @see <a href="https://www.jsonschema2pojo.org">jsonschema2pojo.org</a>
 * @see <a href="https://viacep.com.br">ViaCEP</a>
 * 
 * @author falvojr
 */
@Entity
public class Endereco {

	@Id
	private String cep;
	private String logradouro;
	private String complemento;
	private String bairro;
	private String localidade;
	private String uf;
	private String ibge;
	private String gia;
	private String ddd;
	private String siafi;

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getIbge() {
		return ibge;
	}

	public void setIbge(String ibge) {
		this.ibge = ibge;
	}

	public String getGia() {
		return gia;
	}

	public void setGia(String gia) {
		this.gia = gia;
	}

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public String getSiafi() {
		return siafi;
	}

	public void setSiafi(String siafi) {
		this.siafi = siafi;
	}

	@Override
	public String toString() {
		return "Endereco{" +
				"cep='" + cep + '\'' +
				", logradouro='" + logradouro + '\'' +
				", complemento='" + complemento + '\'' +
				", bairro='" + bairro + '\'' +
				", localidade='" + localidade + '\'' +
				", uf='" + uf + '\'' +
				", ibge='" + ibge + '\'' +
				", gia='" + gia + '\'' +
				", ddd='" + ddd + '\'' +
				", siafi='" + siafi + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Endereco)) return false;
		Endereco endereco = (Endereco) o;
		return Objects.equals(cep, endereco.cep) && Objects.equals(logradouro, endereco.logradouro) && Objects.equals(complemento, endereco.complemento) && Objects.equals(bairro, endereco.bairro) && Objects.equals(localidade, endereco.localidade) && Objects.equals(uf, endereco.uf) && Objects.equals(ibge, endereco.ibge) && Objects.equals(gia, endereco.gia) && Objects.equals(ddd, endereco.ddd) && Objects.equals(siafi, endereco.siafi);
	}

	@Override
	public int hashCode() {
		return Objects.hash(cep, logradouro, complemento, bairro, localidade, uf, ibge, gia, ddd, siafi);
	}
}
