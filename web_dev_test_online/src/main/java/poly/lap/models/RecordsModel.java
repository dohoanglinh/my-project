/*
**======= Abouts us =======**
* System name : assigment5
* Verstion    : 1.0
* Create date : Mar 9, 2019 9:59:05 AM
* Description : Create by Linh Noob
* Copyright © 2019 Noob.com.All rights reserved.
**=========================**
*/
package poly.lap.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import poly.lap.models.StaffsModel;

// TODO: Auto-generated Javadoc
/**
 * The Class RecordsModel.
 */
@Entity
@Table(name = "records")
public class RecordsModel {

    /** The Id. */
    @Id
    private String Id;
    
    /** The Type. */
    private boolean Type;
    
    /** The Reason. */
    private String Reason;
    
    /** The Date. */
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date Date;
    
    /** The Staffs id. */
    private String StaffsId;
    
    /** The staffs. */
    @ManyToOne
    @JoinColumn(name = "staffsID", referencedColumnName = "staffsID", insertable = false, updatable = false)
    private StaffsModel staffs;

    /**
	 * Instantiates a new records model.
	 */
    public RecordsModel() {
    }

    /**
	 * Instantiates a new records model.
	 *
	 * @param recordID the record ID
	 * @param type     the type
	 * @param reason   the reason
	 * @param date     the date
	 * @param staffsID the staffs ID
	 * @param staffs   the staffs
	 */
    public RecordsModel(String recordID, boolean type, String reason, Date date, String staffsID, StaffsModel staffs) {
        this.Id = recordID;
        this.Type = type;
        this.Reason = reason;
        this.Date = date;
        this.StaffsId = staffsID;
        this.staffs = staffs;
    }

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return Id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(String id) {
		Id = id;
	}

	/**
	 * Checks if is type.
	 *
	 * @return true, if is type
	 */
	public boolean isType() {
		return Type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(boolean type) {
		Type = type;
	}

	/**
	 * Gets the reason.
	 *
	 * @return the reason
	 */
	public String getReason() {
		return Reason;
	}

	/**
	 * Sets the reason.
	 *
	 * @param reason the new reason
	 */
	public void setReason(String reason) {
		Reason = reason;
	}

	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public Date getDate() {
		return Date;
	}

	/**
	 * Sets the date.
	 *
	 * @param date the new date
	 */
	public void setDate(Date date) {
		Date = date;
	}

	/**
	 * Gets the staffs id.
	 *
	 * @return the staffs id
	 */
	public String getStaffsId() {
		return StaffsId;
	}

	/**
	 * Sets the staffs id.
	 *
	 * @param staffsId the new staffs id
	 */
	public void setStaffsId(String staffsId) {
		StaffsId = staffsId;
	}

	/**
	 * Gets the staffs.
	 *
	 * @return the staffs
	 */
	public StaffsModel getStaffs() {
		return staffs;
	}

	/**
	 * Sets the staffs.
	 *
	 * @param staffs the new staffs
	 */
	public void setStaffs(StaffsModel staffs) {
		this.staffs = staffs;
	}}
