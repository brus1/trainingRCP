package com.opti.rental.ui;

import org.eclipse.jface.viewers.IColorProvider;

public class Palette {
	
	private String id, idName;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	private IColorProvider provider;
	
	public String getIdName() {
		return idName;
	}
	public void setIdName(String idName) {
		this.idName = idName;
	}
	public IColorProvider getProvider() {
		return provider;
	}
	public void setProvider(IColorProvider provider) {
		this.provider = provider;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Palette other = (Palette) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	

}
