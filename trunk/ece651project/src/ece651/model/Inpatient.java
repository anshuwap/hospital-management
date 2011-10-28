package ece651.model;

import java.io.Serializable;
import java.util.Date;

public class Inpatient implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer inpatientId;
	private Integer visitationId;
	private Integer issueDoctorId;
	private Integer inpatientDoctorId;
	private Integer patientId;
	private Integer nurseId;
	private Date inpatientDate;
	private Date dischargetDate;
	private String arrangementDescription;
	private String dischargeSummary;
	private Visitation visitation;
	public Integer getInpatientId() {
		return inpatientId;
	}
	public void setInpatientId(Integer inpatientId) {
		this.inpatientId = inpatientId;
	}
	public Integer getVisitationId() {
		return visitationId;
	}
	public void setVisitationId(Integer visitationId) {
		this.visitationId = visitationId;
	}
	public Integer getIssueDoctorId() {
		return issueDoctorId;
	}
	public void setIssueDoctorId(Integer issueDoctorId) {
		this.issueDoctorId = issueDoctorId;
	}
	public Integer getInpatientDoctorId() {
		return inpatientDoctorId;
	}
	public void setInpatientDoctorId(Integer inpatientDoctorId) {
		this.inpatientDoctorId = inpatientDoctorId;
	}
	public Integer getPatientId() {
		return patientId;
	}
	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}
	public Integer getNurseId() {
		return nurseId;
	}
	public void setNurseId(Integer nurseId) {
		this.nurseId = nurseId;
	}
	public Date getInpatientDate() {
		return inpatientDate;
	}
	public void setInpatientDate(Date inpatientDate) {
		this.inpatientDate = inpatientDate;
	}
	public Date getDischargetDate() {
		return dischargetDate;
	}
	public void setDischargetDate(Date dischargetDate) {
		this.dischargetDate = dischargetDate;
	}
	public String getArrangementDescription() {
		return arrangementDescription;
	}
	public void setArrangementDescription(String arrangementDescription) {
		this.arrangementDescription = arrangementDescription;
	}
	public String getDischargeSummary() {
		return dischargeSummary;
	}
	public void setDischargeSummary(String dischargeSummary) {
		this.dischargeSummary = dischargeSummary;
	}
	public Visitation getVisitation() {
		return visitation;
	}
	public void setVisitation(Visitation visitation) {
		this.visitation = visitation;
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
				+ ((arrangementDescription == null) ? 0
						: arrangementDescription.hashCode());
		result = prime
				* result
				+ ((dischargeSummary == null) ? 0 : dischargeSummary.hashCode());
		result = prime * result
				+ ((dischargetDate == null) ? 0 : dischargetDate.hashCode());
		result = prime * result
				+ ((inpatientDate == null) ? 0 : inpatientDate.hashCode());
		result = prime
				* result
				+ ((inpatientDoctorId == null) ? 0 : inpatientDoctorId
						.hashCode());
		result = prime * result
				+ ((inpatientId == null) ? 0 : inpatientId.hashCode());
		result = prime * result
				+ ((issueDoctorId == null) ? 0 : issueDoctorId.hashCode());
		result = prime * result + ((nurseId == null) ? 0 : nurseId.hashCode());
		result = prime * result
				+ ((patientId == null) ? 0 : patientId.hashCode());
		result = prime * result
				+ ((visitation == null) ? 0 : visitation.hashCode());
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
		Inpatient other = (Inpatient) obj;
		if (arrangementDescription == null) {
			if (other.arrangementDescription != null)
				return false;
		} else if (!arrangementDescription.equals(other.arrangementDescription))
			return false;
		if (dischargeSummary == null) {
			if (other.dischargeSummary != null)
				return false;
		} else if (!dischargeSummary.equals(other.dischargeSummary))
			return false;
		if (dischargetDate == null) {
			if (other.dischargetDate != null)
				return false;
		} else if (!dischargetDate.equals(other.dischargetDate))
			return false;
		if (inpatientDate == null) {
			if (other.inpatientDate != null)
				return false;
		} else if (!inpatientDate.equals(other.inpatientDate))
			return false;
		if (inpatientDoctorId == null) {
			if (other.inpatientDoctorId != null)
				return false;
		} else if (!inpatientDoctorId.equals(other.inpatientDoctorId))
			return false;
		if (inpatientId == null) {
			if (other.inpatientId != null)
				return false;
		} else if (!inpatientId.equals(other.inpatientId))
			return false;
		if (issueDoctorId == null) {
			if (other.issueDoctorId != null)
				return false;
		} else if (!issueDoctorId.equals(other.issueDoctorId))
			return false;
		if (nurseId == null) {
			if (other.nurseId != null)
				return false;
		} else if (!nurseId.equals(other.nurseId))
			return false;
		if (patientId == null) {
			if (other.patientId != null)
				return false;
		} else if (!patientId.equals(other.patientId))
			return false;
		if (visitation == null) {
			if (other.visitation != null)
				return false;
		} else if (!visitation.equals(other.visitation))
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
		return "Inpatient [inpatientId=" + inpatientId + ", visitationId="
				+ visitationId + ", issueDoctorId=" + issueDoctorId
				+ ", inpatientDoctorId=" + inpatientDoctorId + ", patientId="
				+ patientId + ", nurseId=" + nurseId + ", inpatientDate="
				+ inpatientDate + ", dischargetDate=" + dischargetDate
				+ ", arrangementDescription=" + arrangementDescription
				+ ", dischargeSummary=" + dischargeSummary + ", visitation="
				+ visitation + "]";
	}

}
