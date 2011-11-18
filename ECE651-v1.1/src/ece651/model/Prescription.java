package ece651.model;

import java.io.Serializable;
import java.util.Date;

public class Prescription implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer PrescriptionId;
	private Patient patient;
	private SystemUser doctor;
	private String  PrescriptionDescription;
	private Date    PrescriptionDate;
	private Visitation visitation;
	public Integer getPrescriptionId() {
		return PrescriptionId;
	}
	public void setPrescriptionId(Integer prescriptionId) {
		PrescriptionId = prescriptionId;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public SystemUser getDoctor() {
		return doctor;
	}
	public void setDoctor(SystemUser doctor) {
		this.doctor = doctor;
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
	public Visitation getVisitation() {
		return visitation;
	}
	public void setVisitation(Visitation visitation) {
		this.visitation = visitation;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((PrescriptionDate == null) ? 0 : PrescriptionDate.hashCode());
		result = prime
				* result
				+ ((PrescriptionDescription == null) ? 0
						: PrescriptionDescription.hashCode());
		result = prime * result
				+ ((PrescriptionId == null) ? 0 : PrescriptionId.hashCode());
		result = prime * result + ((doctor == null) ? 0 : doctor.hashCode());
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
		Prescription other = (Prescription) obj;
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
		if (doctor == null) {
			if (other.doctor != null)
				return false;
		} else if (!doctor.equals(other.doctor))
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
		return "Prescription [PrescriptionId=" + PrescriptionId + ", patient="
				+ patient + ", doctor=" + doctor + ", PrescriptionDescription="
				+ PrescriptionDescription + ", PrescriptionDate="
				+ PrescriptionDate + "]";
	}
}
