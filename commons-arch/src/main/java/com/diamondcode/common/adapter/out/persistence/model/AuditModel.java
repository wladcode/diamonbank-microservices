package com.diamondcode.common.adapter.out.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.sql.Timestamp;

@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class AuditModel implements Serializable {
	
	private String status;
	
	private String creator;
	
	@Column(name = "created_date")
	private Timestamp createdDate;
	
	@Column(name = "updated_date")
	private Timestamp updatedDate;
	
	@Column(name = "deleted_date")
	private Timestamp deletedDate;

}
