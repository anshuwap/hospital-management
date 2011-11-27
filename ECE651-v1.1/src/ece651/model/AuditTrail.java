package ece651.model;

import java.io.Serializable;

public class AuditTrail implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer auditId;
	private String tableName;
	private String actionType;
	private Integer oldVersion;
	private Integer newVersion;
	private String tableKey;
	private String columnName;
	private String oldValue;
	private String newValue;
	private String dbUser;
	private String lastUpdtUser;
	private String time;
	public Integer getAuditId() {
		return auditId;
	}
	public void setAuditId(Integer auditId) {
		this.auditId = auditId;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getActionType() {
		return actionType;
	}
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	public Integer getOldVersion() {
		return oldVersion;
	}
	public void setOldVersion(Integer oldVersion) {
		this.oldVersion = oldVersion;
	}
	public Integer getNewVersion() {
		return newVersion;
	}
	public void setNewVersion(Integer newVersion) {
		this.newVersion = newVersion;
	}
	public String getTableKey() {
		return tableKey;
	}
	public void setTableKey(String tableKey) {
		this.tableKey = tableKey;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getOldValue() {
		return oldValue;
	}
	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}
	public String getNewValue() {
		return newValue;
	}
	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}
	public String getDbUser() {
		return dbUser;
	}
	public void setDbUser(String dbUser) {
		this.dbUser = dbUser;
	}
	public String getLastUpdtUser() {
		return lastUpdtUser;
	}
	public void setLastUpdtUser(String lastUpdtUser) {
		this.lastUpdtUser = lastUpdtUser;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((actionType == null) ? 0 : actionType.hashCode());
		result = prime * result + ((auditId == null) ? 0 : auditId.hashCode());
		result = prime * result
				+ ((columnName == null) ? 0 : columnName.hashCode());
		result = prime * result + ((dbUser == null) ? 0 : dbUser.hashCode());
		result = prime * result
				+ ((lastUpdtUser == null) ? 0 : lastUpdtUser.hashCode());
		result = prime * result
				+ ((newValue == null) ? 0 : newValue.hashCode());
		result = prime * result
				+ ((newVersion == null) ? 0 : newVersion.hashCode());
		result = prime * result
				+ ((oldValue == null) ? 0 : oldValue.hashCode());
		result = prime * result
				+ ((oldVersion == null) ? 0 : oldVersion.hashCode());
		result = prime * result
				+ ((tableKey == null) ? 0 : tableKey.hashCode());
		result = prime * result
				+ ((tableName == null) ? 0 : tableName.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
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
		AuditTrail other = (AuditTrail) obj;
		if (actionType == null) {
			if (other.actionType != null)
				return false;
		} else if (!actionType.equals(other.actionType))
			return false;
		if (auditId == null) {
			if (other.auditId != null)
				return false;
		} else if (!auditId.equals(other.auditId))
			return false;
		if (columnName == null) {
			if (other.columnName != null)
				return false;
		} else if (!columnName.equals(other.columnName))
			return false;
		if (dbUser == null) {
			if (other.dbUser != null)
				return false;
		} else if (!dbUser.equals(other.dbUser))
			return false;
		if (lastUpdtUser == null) {
			if (other.lastUpdtUser != null)
				return false;
		} else if (!lastUpdtUser.equals(other.lastUpdtUser))
			return false;
		if (newValue == null) {
			if (other.newValue != null)
				return false;
		} else if (!newValue.equals(other.newValue))
			return false;
		if (newVersion == null) {
			if (other.newVersion != null)
				return false;
		} else if (!newVersion.equals(other.newVersion))
			return false;
		if (oldValue == null) {
			if (other.oldValue != null)
				return false;
		} else if (!oldValue.equals(other.oldValue))
			return false;
		if (oldVersion == null) {
			if (other.oldVersion != null)
				return false;
		} else if (!oldVersion.equals(other.oldVersion))
			return false;
		if (tableKey == null) {
			if (other.tableKey != null)
				return false;
		} else if (!tableKey.equals(other.tableKey))
			return false;
		if (tableName == null) {
			if (other.tableName != null)
				return false;
		} else if (!tableName.equals(other.tableName))
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "AuditTrail [auditId=" + auditId + ", tableName=" + tableName
				+ ", actionType=" + actionType + ", oldVersion=" + oldVersion
				+ ", newVersion=" + newVersion + ", tableKey=" + tableKey
				+ ", columnName=" + columnName + ", oldValue=" + oldValue
				+ ", newValue=" + newValue + ", dbUser=" + dbUser
				+ ", lastUpdtUser=" + lastUpdtUser + ", time=" + time + "]";
	}
	
	
}
