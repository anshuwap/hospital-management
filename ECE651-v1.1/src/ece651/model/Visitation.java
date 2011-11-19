package ece651.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

import ece651.model.Appointment;

public class Visitation implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer visitationId;
	private Patient patient;
	private SystemUser doctor;
	private String visitationType;
	private Date   visitationDate;
	private String symptomDescription;
	private String diagnosisResult;
	private String startTime;
	private String endTime;
	private Appointment appointment;
	private Set<DiagnosisTest> diagnosisTestSet;
	private Prescription prescription;
	private Surgery surgery;
	private Inpatient inpatient;
	public Integer getVisitationId() {
		return visitationId;
	}
	public void setVisitationId(Integer visitationId) {
		this.visitationId = visitationId;
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
	public String getVisitationType() {
		return visitationType;
	}
	public void setVisitationType(String visitationType) {
		this.visitationType = visitationType;
	}
	public Date getVisitationDate() {
		return visitationDate;
	}
	public void setVisitationDate(Date visitationDate) {
		this.visitationDate = visitationDate;
	}
	public String getSymptomDescription() {
		return symptomDescription;
	}
	public void setSymptomDescription(String symptomDescription) {
		this.symptomDescription = symptomDescription;
	}
	public String getDiagnosisResult() {
		return diagnosisResult;
	}
	public void setDiagnosisResult(String diagnosisResult) {
		this.diagnosisResult = diagnosisResult;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Appointment getAppointment() {
		return appointment;
	}
	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}
	public Set<DiagnosisTest> getDiagnosisTestSet() {
		return diagnosisTestSet;
	}
	public void setDiagnosisTestSet(Set<DiagnosisTest> diagnosisTestSet) {
		this.diagnosisTestSet = diagnosisTestSet;
	}
	public Prescription getPrescription() {
		return prescription;
	}
	public void setPrescription(Prescription prescription) {
		this.prescription = prescription;
	}
	public Surgery getSurgery() {
		return surgery;
	}
	public void setSurgery(Surgery surgery) {
		this.surgery = surgery;
	}
	public Inpatient getInpatient() {
		return inpatient;
	}
	public void setInpatient(Inpatient inpatient) {
		this.inpatient = inpatient;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((appointment == null) ? 0 : appointment.hashCode());
		result = prime * result
				+ ((diagnosisResult == null) ? 0 : diagnosisResult.hashCode());
		result = prime
				* result
				+ ((diagnosisTestSet == null) ? 0 : diagnosisTestSet.hashCode());
		result = prime * result + ((doctor == null) ? 0 : doctor.hashCode());
		result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
		result = prime * result
				+ ((inpatient == null) ? 0 : inpatient.hashCode());
		result = prime * result + ((patient == null) ? 0 : patient.hashCode());
		result = prime * result
				+ ((prescription == null) ? 0 : prescription.hashCode());
		result = prime * result
				+ ((startTime == null) ? 0 : startTime.hashCode());
		result = prime * result + ((surgery == null) ? 0 : surgery.hashCode());
		result = prime
				* result
				+ ((symptomDescription == null) ? 0 : symptomDescription
						.hashCode());
		result = prime * result
				+ ((visitationDate == null) ? 0 : visitationDate.hashCode());
		result = prime * result
				+ ((visitationId == null) ? 0 : visitationId.hashCode());
		result = prime * result
				+ ((visitationType == null) ? 0 : visitationType.hashCode());
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
		Visitation other = (Visitation) obj;
		if (appointment == null) {
			if (other.appointment != null)
				return false;
		} else if (!appointment.equals(other.appointment))
			return false;
		if (diagnosisResult == null) {
			if (other.diagnosisResult != null)
				return false;
		} else if (!diagnosisResult.equals(other.diagnosisResult))
			return false;
		if (diagnosisTestSet == null) {
			if (other.diagnosisTestSet != null)
				return false;
		} else if (!diagnosisTestSet.equals(other.diagnosisTestSet))
			return false;
		if (doctor == null) {
			if (other.doctor != null)
				return false;
		} else if (!doctor.equals(other.doctor))
			return false;
		if (endTime == null) {
			if (other.endTime != null)
				return false;
		} else if (!endTime.equals(other.endTime))
			return false;
		if (inpatient == null) {
			if (other.inpatient != null)
				return false;
		} else if (!inpatient.equals(other.inpatient))
			return false;
		if (patient == null) {
			if (other.patient != null)
				return false;
		} else if (!patient.equals(other.patient))
			return false;
		if (prescription == null) {
			if (other.prescription != null)
				return false;
		} else if (!prescription.equals(other.prescription))
			return false;
		if (startTime == null) {
			if (other.startTime != null)
				return false;
		} else if (!startTime.equals(other.startTime))
			return false;
		if (surgery == null) {
			if (other.surgery != null)
				return false;
		} else if (!surgery.equals(other.surgery))
			return false;
		if (symptomDescription == null) {
			if (other.symptomDescription != null)
				return false;
		} else if (!symptomDescription.equals(other.symptomDescription))
			return false;
		if (visitationDate == null) {
			if (other.visitationDate != null)
				return false;
		} else if (!visitationDate.equals(other.visitationDate))
			return false;
		if (visitationId == null) {
			if (other.visitationId != null)
				return false;
		} else if (!visitationId.equals(other.visitationId))
			return false;
		if (visitationType == null) {
			if (other.visitationType != null)
				return false;
		} else if (!visitationType.equals(other.visitationType))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Visitation [visitationId=" + visitationId + ", patient="
				+ patient + ", doctor=" + doctor + ", visitationType="
				+ visitationType + ", visitationDate=" + visitationDate
				+ ", symptomDescription=" + symptomDescription
				+ ", diagnosisResult=" + diagnosisResult + ", startTime="
				+ startTime + ", endTime=" + endTime + ", appointment="
				+ appointment + ", diagnosisTestSet=" + diagnosisTestSet
				+ ", prescription=" + prescription + ", surgery=" + surgery
				+ ", inpatient=" + inpatient + "]";
	}

}
