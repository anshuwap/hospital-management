package ece651.model;

import java.io.Serializable;

public class VisitationID implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1364923394564989389L;
	private Integer visitationId;
	private Integer versionNo;
	public Integer getVisitationId() {
		return visitationId;
	}
	public void setVisitationId(Integer visitationId) {
		this.visitationId = visitationId;
	}
	public Integer getVersionNo() {
		return versionNo;
	}
	public void setVersionNo(Integer versionNo) {
		this.versionNo = versionNo;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((versionNo == null) ? 0 : versionNo.hashCode());
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
		VisitationID other = (VisitationID) obj;
		if (versionNo == null) {
			if (other.versionNo != null)
				return false;
		} else if (!versionNo.equals(other.versionNo))
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
		return "VisitationID [visitationId=" + visitationId + ", versionNo="
				+ versionNo + "]";
	}

}
