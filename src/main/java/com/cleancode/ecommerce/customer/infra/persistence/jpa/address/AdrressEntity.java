package com.cleancode.ecommerce.customer.infra.persistence.jpa.address;

import java.util.Objects;

import com.cleancode.ecommerce.customer.infra.persistence.jpa.customer.CustomerEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "address")
public abstract class AdrressEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "db_id")
	protected Long dbId;
	
	protected String id;
	protected String receiver;
	protected String street;
	protected String number;
	protected String neighborhood;

	@Column(name = "zip_code")
	protected String zipCode;
	protected String observation;

	@Column(name = "street_type")
	protected String streetType;

	@Column(name = "type_residence")
	protected String typeResidence;

	protected String city;
	protected String state;
	protected String country;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	protected CustomerEntity customer;

	@Override
	public int hashCode() {
		return Objects.hash(dbId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AdrressEntity other = (AdrressEntity) obj;
		return Objects.equals(dbId, other.dbId);
	}
}