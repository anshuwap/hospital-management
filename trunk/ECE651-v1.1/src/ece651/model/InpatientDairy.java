package ece651.model;

import java.io.Serializable;
import java.util.Date;

public class InpatientDairy implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer inpatientDairyId;
	private Integer inpatientId;
	private Patient patient;
	private SystemUser nurse;
	private Date recordDate;
	private String dairyDescription;
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
	public Date getRecordDate() {
		return recordDate;
	}
	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}
	public String getDairyDescription() {
		return dairyDescription;
	}
	public void setDairyDescription(String dairyDescription) {
		this.dairyDescription = dairyDescription;
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
				+ ((dairyDescription == null) ? 0 : dairyDescription.hashCode());
		result = prime
				* result
				+ ((inpatientDairyId == null) ? 0 : inpatientDairyId.hashCode());
		result = prime * result
				+ ((inpatientId == null) ? 0 : inpatientId.hashCode());
		result = prime * result + ((nurse == null) ? 0 : nurse.hashCode());
		result = prime * result + ((patient == null) ? 0 : patient.hashCode());
		result = prime * result
				+ ((recordDate == null) ? 0 : recordDate.hashCode());
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
		InpatientDairy other = (InpatientDairy) obj;
		if (dairyDescription == null) {
			if (other.dairyDescription != null)
				return false;
		} else if (!dairyDescription.equals(other.dairyDescription))
			return false;
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
		if (recordDate == null) {
			if (other.recordDate != null)
				return false;
		} else if (!recordDate.equals(other.recordDate))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "InpatientDairy [inpatientDairyId=" + inpatientDairyId
				+ ", inpatientId=" + inpatientId + ", patient=" + patient
				+ ", nurse=" + nurse + ", recordDate=" + recordDate
				+ ", dairyDescription=" + dairyDescription + "]";
	}

}
