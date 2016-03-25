package br.com.contatos.rest.model;

import javax.persistence.Entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
public class Contato {

	private Long _id;
	private String nome;
	private String telefone;

	public Contato() {

	}

	public Contato(Long _id, String nome, String telefone) {
		this._id = _id;
		this.nome = nome;
		this.telefone = telefone;
	}

	public Long get_id() {
		return _id;
	}

	public void set_id(Long _id) {
		this._id = _id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		boolean equals = false;
		if (obj == this) {
			equals = true;
		} else if (obj instanceof Contato) {
			Contato object = (Contato) obj;
			equals = new EqualsBuilder().append(_id, object._id)						
					.isEquals();
		}
		return equals;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(_id).toHashCode();
	}

}
