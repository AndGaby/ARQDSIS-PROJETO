package br.com.usjt.TO;

public class SaqueTO {
	
	private int conta, agencia;
	private Double saque;

	public int getConta() {
		return conta;
	}

	public int getAgencia() {
		return agencia;
	}

	public void setConta(int conta) {
		this.conta = conta;
	}

	public void setAgencia(int agencia) {
		this.agencia = agencia;
	}

	public Double getSaque() {
		return saque;
	}

	public void setSaque(Double saque) {
		this.saque = saque;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + agencia;
		result = prime * result + conta;
		result = prime * result + ((saque == null) ? 0 : saque.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SaqueTO other = (SaqueTO) obj;
		if (agencia != other.agencia)
			return false;
		if (conta != other.conta)
			return false;
		if (saque == null) {
			if (other.saque != null)
				return false;
		} else if (!saque.equals(other.saque))
			return false;
		return true;
	}
}