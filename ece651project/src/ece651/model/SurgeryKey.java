package ece651.model;

import java.io.Serializable;

public class SurgeryKey implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer surgeryId;
	private Integer visitationId;
	public Integer getSurgeryId() {
		return surgeryId;
	}
	public void setSurgeryId(Integer surgeryId) {
		this.surgeryId = surgeryId;
	}
	public Integer getVisitationId() {
		return visitationId;
	}
	public void setVisitationId(Integer visitationId) {
		this.visitationId = visitationId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((surgeryId == null) ? 0 : surgeryId.hashCode());
		result = prime * result
				+ ((visitationId == null) ? 0 : visitationId.hashCode());
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
		SurgeryKey other = (SurgeryKey) obj;
		if (surgeryId == null) {
			if (other.surgeryId != null)
				return false;
		} else if (!surgeryId.equals(other.surgeryId))
			return false;
		if (visitationId == null) {
			if (other.visitationId != null)
				return false;
		} else if (!visitationId.equals(other.visitationId))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "SurgeryKey [surgeryId=" + surgeryId + ", visitationId="
				+ visitationId + "]";
	}

}
