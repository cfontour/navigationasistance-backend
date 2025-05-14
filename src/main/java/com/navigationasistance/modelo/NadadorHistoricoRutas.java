package com.navigationasistance.modelo;

import javax.persistence.*;
import java.time.LocalDate;
import java.sql.Timestamp;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

@Table(name = "nadadorhistoricorutas")
@Entity
public class NadadorHistoricoRutas {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;

	@Column(name = "usuario_id")
	@JsonProperty("usuario_id")
	private String usuarioid;

	@Column(name = "recorrido_id")
	@JsonProperty("recorrido_id")
	private UUID recorridoid;

	@Column(name = "nadadorfecha")
	private LocalDate nadadorfecha;

	@Column(name = "nadadorhora")
	private Timestamp nadadorhora;

	@Column(name = "secuencia")
	private Integer secuencia;

	@Column(name = "nadadorlat")
	private String nadadorlat;

	@Column(name = "nadadorlng")
	private String nadadorlng;

	public NadadorHistoricoRutas() {
		// Constructor por defecto
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsuarioid() {
		return usuarioid;
	}

	public void setUsuarioid(String usuarioid) {
		this.usuarioid = usuarioid;
	}

	public UUID getRecorridoid() {
		return recorridoid;
	}

	public void setRecorridoid(UUID recorridoid) {
		this.recorridoid = recorridoid;
	}

	public LocalDate getNadadorfecha() {
		return nadadorfecha;
	}

	public void setNadadorfecha(LocalDate nadadorfecha) {
		this.nadadorfecha = nadadorfecha;
	}

	public Timestamp getNadadorhora() {
		return nadadorhora;
	}

	public void setNadadorhora(Timestamp nadadorhora) {
		this.nadadorhora = nadadorhora;
	}

	public Integer getSecuencia() {
		return secuencia;
	}

	public void setSecuencia(Integer secuencia) {
		this.secuencia = secuencia;
	}

	public String getNadadorlat() {
		return nadadorlat;
	}

	public void setNadadorlat(String nadadorlat) {
		this.nadadorlat = nadadorlat;
	}

	public String getNadadorlng() {
		return nadadorlng;
	}

	public void setNadadorlng(String nadadorlng) {
		this.nadadorlng = nadadorlng;
	}
}
