package org.px.model;

public class Talla {

	private String id_producto;
	private String id_talla;
	private boolean talla_especial;
	private boolean backsoon;
	private int unidades;

	public Talla(String id_talla, String id_producto, boolean backsoon, boolean talla_especial) {
		this.id_producto = id_producto;
		this.id_talla = id_talla;
		this.talla_especial = talla_especial;
		this.backsoon = backsoon;
	}

	public String getId_talla() {
		return id_talla;
	}

	public void setId_talla(String id_talla) {
		this.id_talla = id_talla;
	}

	public boolean isTalla_especial() {
		return talla_especial;
	}

	public void setTalla_especial(boolean talla_especial) {
		this.talla_especial = talla_especial;
	}

	public int getUnidades() {
		return unidades;
	}

	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}

	public boolean isBacksoon() {
		return backsoon;
	}

	public void setBacksoon(boolean backsoon) {
		this.backsoon = backsoon;
	}


	@Override
	public String toString() {
		return "Talla [id_producto=" + id_producto + ", id_talla=" + id_talla + ", talla_especial=" + talla_especial
				+ ", backsoon=" + backsoon + ", unidades=" + unidades + "]";
	}

	public String getId_producto() {
		return id_producto;
	}

	public void setId_producto(String id_producto) {
		this.id_producto = id_producto;
	}

	@Override
	public int hashCode() {

		return id_talla.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Talla other = (Talla) obj;
		if (id_talla == null) {
			if (other.id_talla != null)
				return false;
		} else if (!id_talla.equals(other.id_talla))
			return false;
		return true;
	}

}
