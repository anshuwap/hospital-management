package ece651.model;

import java.io.Serializable;

public class DiagnosisTestKey implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer diagnosisTestId;
	private Integer visitationId;	
	public Integer getDiagnosisTestId() {
		return diagnosisTestId;
	}
	public void setDiagnosisTestId(Integer diagnosisTestId) {
		this.diagnosisTestId = diagnosisTestId;
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
				+ ((diagnosisTestId == null) ? 0 : diagnosisTestId.hashCode());
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
		DiagnosisTestKey other = (DiagnosisTestKey) obj;
		if (diagnosisTestId == null) {
			if (other.diagnosisTestId != null)
				return false;
		} else if (!diagnosisTestId.equals(other.diagnosisTestId))
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
		return "DiagnosisTestKey [diagnosisTestId=" + diagnosisTestId
				+ ", visitationId=" + visitationId + "]";
	}

}
