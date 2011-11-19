package ece651.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public class Inpatient implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer inpatientId;
	private SystemUser issueDoctor;
	private SystemUser inpatientDoctor;
	private Patient patient;
	private SystemUser nurse;
	private Date inpatientDate;
	private Date dischargetDate;
	private String arrangementDescription;
	private String dischargeSummary;
	private Visitation visitation;
	private Set<InpatientDairy> inpatientDairySet;
	public Integer getInpatientId() {
		return inpatientId;
	}
	public void setInpatientId(Integer inpatientId) {
		this.inpatientId = inpatientId;
	}
	public SystemUser getIssueDoctor() {
		return issueDoctor;
	}
	public void setIssueDoctor(SystemUser issueDoctor) {
		this.issueDoctor = issueDoctor;
	}
	public SystemUser getInpatientDoctor() {
		return inpatientDoctor;
	}
	public void setInpatientDoctor(SystemUser inpatientDoctor) {
		this.inpatientDoctor = inpatientDoctor;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public SystemUser getNurse() {
		return nurse;
	}
	public void setNurse(SystemUser nurse) {
		this.nurse = nurse;
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
	public Set<InpatientDairy> getInpatientDairySet() {
		return inpatientDairySet;
	}
	public void setInpatientDairySet(Set<InpatientDairy> inpatientDairySet) {
		this.inpatientDairySet = inpatientDairySet;
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
		result = prime
				* result
				+ ((inpatientDairySet == null) ? 0 : inpatientDairySet
						.hashCode());
		result = prime * result
				+ ((inpatientDate == null) ? 0 : inpatientDate.hashCode());
		result = prime * result
				+ ((inpatientDoctor == null) ? 0 : inpatientDoctor.hashCode());
		result = prime * result
				+ ((inpatientId == null) ? 0 : inpatientId.hashCode());
		result = prime * result
				+ ((issueDoctor == null) ? 0 : issueDoctor.hashCode());
		result = prime * result + ((nurse == null) ? 0 : nurse.hashCode());
		result = prime * result + ((patient == null) ? 0 : patient.hashCode());
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
		if (inpatientDairySet == null) {
			if (other.inpatientDairySet != null)
				return false;
		} else if (!inpatientDairySet.equals(other.inpatientDairySet))
			return false;
		if (inpatientDate == null) {
			if (other.inpatientDate != null)
				return false;
		} else if (!inpatientDate.equals(other.inpatientDate))
			return false;
		if (inpatientDoctor == null) {
			if (other.inpatientDoctor != null)
				return false;
		} else if (!inpatientDoctor.equals(other.inpatientDoctor))
			return false;
		if (inpatientId == null) {
			if (other.inpatientId != null)
				return false;
		} else if (!inpatientId.equals(other.inpatientId))
			return false;
		if (issueDoctor == null) {
			if (other.issueDoctor != null)
				return false;
		} else if (!issueDoctor.equals(other.issueDoctor))
			return false;
		if (nurse == null) {
			if (other.nurse != null)
				return false;
		} else if (!nurse.equals(other.nurse))
			return false;
		if (patient == null) {
			if (other.patient != null)
				return false;
		} else if (!patient.equals(other.patient))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Inpatient [inpatientId=" + inpatientId + ", issueDoctor="
				+ issueDoctor + ", inpatientDoctor=" + inpatientDoctor
				+ ", patient=" + patient + ", nurse=" + nurse
				+ ", inpatientDate=" + inpatientDate + ", dischargetDate="
				+ dischargetDate + ", arrangementDescription="
				+ arrangementDescription + ", dischargeSummary="
				+ dischargeSummary + ", inpatientDairySet=" + inpatientDairySet
				+ "]";
	}

}
