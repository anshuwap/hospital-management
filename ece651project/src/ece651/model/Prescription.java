package ece651.model;

import java.io.Serializable;
import java.util.Date;

public class Prescription implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer PrescriptionId;
	private Integer DoctorId;
	private Integer PatientId;
	private String  PrescriptionDescription;
	private Date    PrescriptionDate;
	public Integer getPrescriptionId() {
		return PrescriptionId;
	}
	public void setPrescriptionId(Integer prescriptionId) {
		PrescriptionId = prescriptionId;
	}
	public Integer getDoctorId() {
		return DoctorId;
	}
	public void setDoctorId(Integer doctorId) {
		DoctorId = doctorId;
	}
	public Integer getPatientId() {
		return PatientId;
	}
	public void setPatientId(Integer patientId) {
		PatientId = patientId;
	}
	public String getPrescriptionDescription() {
		return PrescriptionDescription;
	}
	public void setPrescriptionDescription(String prescriptionDescription) {
		PrescriptionDescription = prescriptionDescription;
	}
	public Date getPrescriptionDate() {
		return PrescriptionDate;
	}
	public void setPrescriptionDate(Date prescriptionDate) {
		PrescriptionDate = prescriptionDate;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((DoctorId == null) ? 0 : DoctorId.hashCode());
		result = prime * result
				+ ((PatientId == null) ? 0 : PatientId.hashCode());
		result = prime
				* result
				+ ((PrescriptionDate == null) ? 0 : PrescriptionDate.hashCode());
		result = prime
				* result
				+ ((PrescriptionDescription == null) ? 0
						: PrescriptionDescription.hashCode());
		result = prime * result
				+ ((PrescriptionId == null) ? 0 : PrescriptionId.hashCode());
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
		Prescription other = (Prescription) obj;
		if (DoctorId == null) {
			if (other.DoctorId != null)
				return false;
		} else if (!DoctorId.equals(other.DoctorId))
			return false;
		if (PatientId == null) {
			if (other.PatientId != null)
				return false;
		} else if (!PatientId.equals(other.PatientId))
			return false;
		if (PrescriptionDate == null) {
			if (other.PrescriptionDate != null)
				return false;
		} else if (!PrescriptionDate.equals(other.PrescriptionDate))
			return false;
		if (PrescriptionDescription == null) {
			if (other.PrescriptionDescription != null)
				return false;
		} else if (!PrescriptionDescription
				.equals(other.PrescriptionDescription))
			return false;
		if (PrescriptionId == null) {
			if (other.PrescriptionId != null)
				return false;
		} else if (!PrescriptionId.equals(other.PrescriptionId))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Prescription [PrescriptionId=" + PrescriptionId + ", DoctorId="
				+ DoctorId + ", PatientId=" + PatientId
				+ ", PrescriptionDescription=" + PrescriptionDescription
				+ ", PrescriptionDate=" + PrescriptionDate + "]";
	}

	
}
