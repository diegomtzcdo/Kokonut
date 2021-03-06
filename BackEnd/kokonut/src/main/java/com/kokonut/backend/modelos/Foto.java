package com.kokonut.backend.modelos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "fotos")
public class Foto implements Serializable {
	
	private static final long serialVersionUID = 6371068334783110012L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_foto")
	private Long id;
	
	@Column(name = "name")
	@NaturalId
	private String name;
	
	@Column(name = "latitude")
	private Double Latitude;
	
	@Column(name = "longitude")
	private Double Longitude;
	
	@Column(name = "enabled")
	private Boolean enabled;

}
