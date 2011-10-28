package ece651.model;

import java.io.Serializable;
import java.util.Date;

public class DiagnosisTest implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer diagnosisTestId;
	private Integer visitationId;
	private Integer patientId;
	private Integer doctorId;
	private Integer nurseId;
	private String testType;
	private Date issueDate;
	private String testRequestDescription;
	private String testResultDescription;
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
	public Integer getPatientId() {
		return patientId;
	}
	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}
	public Integer getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}
	public Integer getNurseId() {
		return nurseId;
	}
	public void setNurseId(Integer nurseId) {
		this.nurseId = nurseId;
	}
	public String getTestType() {
		return testType;
	}
	public void setTestType(String testType) {
		this.testType = testType;
	}
	public Date getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}
	public String getTestRequestDescription() {
		return testRequestDescription;
	}
	public void setTestRequestDescription(String testRequestDescription) {
		this.testRequestDescription = testRequestDescription;
	}
	public String getTestResultDescription() {
		return testResultDescription;
	}
	public void setTestResultDescription(String testResultDescription) {
		this.testResultDescription = testResultDescription;
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
				+ ((doctorId == null) ? 0 : doctorId.hashCode());
		result = prime * result
				+ ((issueDate == null) ? 0 : issueDate.hashCode());
		result = prime * result + ((nurseId == null) ? 0 : nurseId.hashCode());
		result = prime * result
				+ ((patientId == null) ? 0 : patientId.hashCode());
		result = prime
				* result
				+ ((testRequestDescription == null) ? 0
						: testRequestDescription.hashCode());
		result = prime
				* result
				+ ((testResultDescription == null) ? 0 : testResultDescription
						.hashCode());
		result = prime * result
				+ ((testType == null) ? 0 : testType.hashCode());
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
		DiagnosisTest other = (DiagnosisTest) obj;
		if (diagnosisTestId == null) {
			if (other.diagnosisTestId != null)
				return false;
		} else if (!diagnosisTestId.equals(other.diagnosisTestId))
			return false;
		if (doctorId == null) {
			if (other.doctorId != null)
				return false;
		} else if (!doctorId.equals(other.doctorId))
			return false;
		if (issueDate == null) {
			if (other.issueDate != null)
				return false;
		} else if (!issueDate.equals(other.issueDate))
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
		if (testRequestDescription == null) {
			if (other.testRequestDescription != null)
				return false;
		} else if (!testRequestDescription.equals(other.testRequestDescription))
			return false;
		if (testResultDescription == null) {
			if (other.testResultDescription != null)
				return false;
		} else if (!testResultDescription.equals(other.testResultDescription))
			return false;
		if (testType == null) {
			if (other.testType != null)
				return false;
		} else if (!testType.equals(other.testType))
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
		return "DiagnosisTest [diagnosisTestId=" + diagnosisTestId
				+ ", visitationId=" + visitationId + ", patientId=" + patientId
				+ ", doctorId=" + doctorId + ", nurseId=" + nurseId
				+ ", testType=" + testType + ", issueDate=" + issueDate
				+ ", testRequestDescription=" + testRequestDescription
				+ ", testResultDescription=" + testResultDescription + "]";
	}

}
