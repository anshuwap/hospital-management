package ece651.model;

import java.io.Serializable;

public class InpatientDairyKey implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer inpatientDairyId;
	private Integer inpatientId;
	public Integer getInpatientDairyId() {
		return inpatientDairyId;
	}
	public void setInpatientDairyId(Integer inpatientDairyId) {
		this.inpatientDairyId = inpatientDairyId;
	}
	public Integer getInpatientId() {
		return inpatientId;
	}
	public void setInpatientId(Integer inpatientId) {
		this.inpatientId = inpatientId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((inpatientDairyId == null) ? 0 : inpatientDairyId.hashCode());
		result = prime * result
				+ ((inpatientId == null) ? 0 : inpatientId.hashCode());
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
		InpatientDairyKey other = (InpatientDairyKey) obj;
		if (inpatientDairyId == null) {
			if (other.inpatientDairyId != null)
				return false;
		} else if (!inpatientDairyId.equals(other.inpatientDairyId))
			return false;
		if (inpatientId == null) {
			if (other.inpatientId != null)
				return false;
		} else if (!inpatientId.equals(other.inpatientId))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "InpatientDairyKey [inpatientDairyId=" + inpatientDairyId
				+ ", inpatientId=" + inpatientId + "]";
	}

}
