package org.px.model;

public class Product {

	private String id;
	private String sequence;
	private boolean hasSpecialSize;

	public Product(String id, String sequence) {
		this.id = id;
		this.sequence = sequence;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	public boolean isHasSpecialSize() {
		return hasSpecialSize;
	}

	public void setHasSpecialSize(boolean hasSpecialSize) {
		this.hasSpecialSize = hasSpecialSize;
	}

	@Override
	public int hashCode() {

		return id.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
