package ece651.model;

import java.io.Serializable;
import java.util.Date;

public class Surgery implements Serializable{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer surgeryId;
	private Integer visitationId;
	private Date    surgeryDate;
	private Integer issueDoctorId;
	private Integer surgetyDoctorId;
	private Integer nurserId;
	private String arrangementDescription;
	private String surgerySummary;
	private Integer patientId;
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
	public Date getSurgeryDate() {
		return surgeryDate;
	}
	public void setSurgeryDate(Date surgeryDate) {
		this.surgeryDate = surgeryDate;
	}
	public Integer getIssueDoctorId() {
		return issueDoctorId;
	}
	public void setIssueDoctorId(Integer issueDoctorId) {
		this.issueDoctorId = issueDoctorId;
	}
	public Integer getSurgetyDoctorId() {
		return surgetyDoctorId;
	}
	public void setSurgetyDoctorId(Integer surgetyDoctorId) {
		this.surgetyDoctorId = surgetyDoctorId;
	}
	public Integer getNurserId() {
		return nurserId;
	}
	public void setNurserId(Integer nurserId) {
		this.nurserId = nurserId;
	}
	public String getArrangementDescription() {
		return arrangementDescription;
	}
	public void setArrangementDescription(String arrangementDescription) {
		this.arrangementDescription = arrangementDescription;
	}
	public String getSurgerySummary() {
		return surgerySummary;
	}
	public void setSurgerySummary(String surgerySummary) {
		this.surgerySummary = surgerySummary;
	}
	public Integer getPatientId() {
		return patientId;
	}
	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
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
		result = prime * result
				+ ((issueDoctorId == null) ? 0 : issueDoctorId.hashCode());
		result = prime * result
				+ ((nurserId == null) ? 0 : nurserId.hashCode());
		result = prime * result
				+ ((patientId == null) ? 0 : patientId.hashCode());
		result = prime * result
				+ ((surgeryDate == null) ? 0 : surgeryDate.hashCode());
		result = prime * result
				+ ((surgeryId == null) ? 0 : surgeryId.hashCode());
		result = prime * result
				+ ((surgerySummary == null) ? 0 : surgerySummary.hashCode());
		result = prime * result
				+ ((surgetyDoctorId == null) ? 0 : surgetyDoctorId.hashCode());
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
		Surgery other = (Surgery) obj;
		if (arrangementDescription == null) {
			if (other.arrangementDescription != null)
				return false;
		} else if (!arrangementDescription.equals(other.arrangementDescription))
			return false;
		if (issueDoctorId == null) {
			if (other.issueDoctorId != null)
				return false;
		} else if (!issueDoctorId.equals(other.issueDoctorId))
			return false;
		if (nurserId == null) {
			if (other.nurserId != null)
				return false;
		} else if (!nurserId.equals(other.nurserId))
			return false;
		if (patientId == null) {
			if (other.patientId != null)
				return false;
		} else if (!patientId.equals(other.patientId))
			return false;
		if (surgeryDate == null) {
			if (other.surgeryDate != null)
				return false;
		} else if (!surgeryDate.equals(other.surgeryDate))
			return false;
		if (surgeryId == null) {
			if (other.surgeryId != null)
				return false;
		} else if (!surgeryId.equals(other.surgeryId))
			return false;
		if (surgerySummary == null) {
			if (other.surgerySummary != null)
				return false;
		} else if (!surgerySummary.equals(other.surgerySummary))
			return false;
		if (surgetyDoctorId == null) {
			if (other.surgetyDoctorId != null)
				return false;
		} else if (!surgetyDoctorId.equals(other.surgetyDoctorId))
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
		return "Surgery [surgeryId=" + surgeryId + ", visitationId="
				+ visitationId + ", surgeryDate=" + surgeryDate
				+ ", issueDoctorId=" + issueDoctorId + ", surgetyDoctorId="
				+ surgetyDoctorId + ", nurserId=" + nurserId
				+ ", arrangementDescription=" + arrangementDescription
				+ ", surgerySummary=" + surgerySummary + ", patientId="
				+ patientId + "]";
	}

}
