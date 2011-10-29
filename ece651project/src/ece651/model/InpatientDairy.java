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
	private Integer patientId;
	private Integer nurseId;
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
		result = prime * result + ((nurseId == null) ? 0 : nurseId.hashCode());
		result = prime * result
				+ ((patientId == null) ? 0 : patientId.hashCode());
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
				+ ", inpatientId=" + inpatientId + ", patientId=" + patientId
				+ ", nurseId=" + nurseId + ", recordDate=" + recordDate
				+ ", dairyDescription=" + dairyDescription + "]";
	}

}
