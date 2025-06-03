package com.navigationasistance.modelo;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "nadadorposicion")
public class NadadorPosicion {

	@Id
	@Column(name = "usuario_id")
	private String usuarioid;

	@Column(name = "nadadorlat")
	private String nadadorlat;

	@Column(name = "nadadorlng")
	private String nadadorlng;

	@Column(name = "fecha_ultima_actualizacion")
	private LocalDateTime fechaUltimaActualizacion;

	@Column(name = "emergency")
	private Boolean emergency;

	public NadadorPosicion() {}

	public String getUsuarioid() {
		return usuarioid;
	}

	public void setUsuarioid(String usuarioid) {
		this.usuarioid = usuarioid;
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

	public LocalDateTime getFechaUltimaActualizacion() {
		return fechaUltimaActualizacion;
	}

	public void setFechaUltimaActualizacion(LocalDateTime fechaUltimaActualizacion) {
		this.fechaUltimaActualizacion = fechaUltimaActualizacion;
	}

	public Boolean getEmergency() {
		return emergency;
	}

	public void setEmergency(Boolean emergency) {
		this.emergency = emergency;
	}
}