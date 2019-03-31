package vehiculo.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;


/**
 * The persistent class for the vehiculos database table.
 * 
 */
@Entity
@Table(name="vehiculos")
@NamedQuery(name="Vehiculo.findAll", query="SELECT v FROM Vehiculo v")
public class Vehiculo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="anio_fabricacion")
	private Integer anioFabricacion;

	@Column(name="capacidad_carga")
	private BigDecimal capacidadCarga;

	@Column(name="capacidad_tanque")
	private BigDecimal capacidadTanque;

	private Boolean estado;

	private String marca;

	private String modelo;

	@Column(name="nro_pasajeros")
	private BigDecimal nroPasajeros;

	//bi-directional many-to-one association to TipoVehiculo
	@ManyToOne
	@JoinColumn(name="tipo_vehiculo")
	@JsonIgnoreProperties("vehiculos")
	private TipoVehiculo tipoVehiculoBean;

	public Vehiculo() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAnioFabricacion() {
		return this.anioFabricacion;
	}

	public void setAnioFabricacion(Integer anioFabricacion) {
		this.anioFabricacion = anioFabricacion;
	}

	public BigDecimal getCapacidadCarga() {
		return this.capacidadCarga;
	}

	public void setCapacidadCarga(BigDecimal capacidadCarga) {
		this.capacidadCarga = capacidadCarga;
	}

	public BigDecimal getCapacidadTanque() {
		return this.capacidadTanque;
	}

	public void setCapacidadTanque(BigDecimal capacidadTanque) {
		this.capacidadTanque = capacidadTanque;
	}

	public Boolean getEstado() {
		return this.estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public String getMarca() {
		return this.marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return this.modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public BigDecimal getNroPasajeros() {
		return this.nroPasajeros;
	}

	public void setNroPasajeros(BigDecimal nroPasajeros) {
		this.nroPasajeros = nroPasajeros;
	}

	public TipoVehiculo getTipoVehiculoBean() {
		return this.tipoVehiculoBean;
	}

	public void setTipoVehiculoBean(TipoVehiculo tipoVehiculoBean) {
		this.tipoVehiculoBean = tipoVehiculoBean;
	}

}