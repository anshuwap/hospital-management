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
	private SystemUser issueDoctor;
	private SystemUser surgetyDoctor;
	private SystemUser nurser;
	private Patient patient;
	private String arrangementDescription;
	private String surgerySummary;
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
	public SystemUser getIssueDoctor() {
		return issueDoctor;
	}
	public void setIssueDoctor(SystemUser issueDoctor) {
		this.issueDoctor = issueDoctor;
	}
	public SystemUser getSurgetyDoctor() {
		return surgetyDoctor;
	}
	public void setSurgetyDoctor(SystemUser surgetyDoctor) {
		this.surgetyDoctor = surgetyDoctor;
	}
	public SystemUser getNurser() {
		return nurser;
	}
	public void setNurser(SystemUser nurser) {
		this.nurser = nurser;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
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
				+ ((issueDoctor == null) ? 0 : issueDoctor.hashCode());
		result = prime * result + ((nurser == null) ? 0 : nurser.hashCode());
		result = prime * result + ((patient == null) ? 0 : patient.hashCode());
		result = prime * result
				+ ((surgeryDate == null) ? 0 : surgeryDate.hashCode());
		result = prime * result
				+ ((surgeryId == null) ? 0 : surgeryId.hashCode());
		result = prime * result
				+ ((surgerySummary == null) ? 0 : surgerySummary.hashCode());
		result = prime * result
				+ ((surgetyDoctor == null) ? 0 : surgetyDoctor.hashCode());
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
		if (issueDoctor == null) {
			if (other.issueDoctor != null)
				return false;
		} else if (!issueDoctor.equals(other.issueDoctor))
			return false;
		if (nurser == null) {
			if (other.nurser != null)
				return false;
		} else if (!nurser.equals(other.nurser))
			return false;
		if (patient == null) {
			if (other.patient != null)
				return false;
		} else if (!patient.equals(other.patient))
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
		if (surgetyDoctor == null) {
			if (other.surgetyDoctor != null)
				return false;
		} else if (!surgetyDoctor.equals(other.surgetyDoctor))
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
				+ ", issueDoctor=" + issueDoctor + ", surgetyDoctor="
				+ surgetyDoctor + ", nurser=" + nurser + ", patient=" + patient
				+ ", arrangementDescription=" + arrangementDescription
				+ ", surgerySummary=" + surgerySummary + "]";
	}

}
