package com.navigationasistance.modelo;

import javax.persistence.*;
import java.sql.Timestamp;

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
	private Timestamp fechaUltimaActualizacion;

	@Column(name = "emergency")
	private Boolean emergency;

	@Column(name = "bearing")
	private Integer bearing;

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

	public Timestamp getFechaUltimaActualizacion() {
		return fechaUltimaActualizacion;
	}

	public void setFechaUltimaActualizacion(Timestamp fechaUltimaActualizacion) {
		this.fechaUltimaActualizacion = fechaUltimaActualizacion;
	}

	public Boolean getEmergency() {
		return emergency;
	}

	public void setEmergency(Boolean emergency) {
		this.emergency = emergency;
	}

	public Integer getBearing() {
		return bearing;
	}

	public void setBearing(Integer bearing) {
		this.bearing = bearing;
	}
}