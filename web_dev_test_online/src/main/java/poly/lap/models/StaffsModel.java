/*
**======= Abouts us =======**
* System name : assigment5
* Verstion    : 1.0
* Create date : Mar 9, 2019 10:09:33 AM
* Description : Create by Linh Noob
* Copyright © 2019 Noob.com.All rights reserved.
**=========================**
*/
package poly.lap.models;

import java.util.Base64;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;


import poly.lap.models.DepartsModel;
import poly.lap.models.RecordsModel;

// TODO: Auto-generated Javadoc
/**
 * The Class StaffsModel.
 */
@Entity
@Table(name = "Staffs")
public class StaffsModel {

    /** The staffs ID. */
    @Id
    private String Id;
    
    /** The name. */
    private String Name;
    
    /** The gender. */
    private boolean Gender;
    
    /** The birthday. */
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date Birthday;

    /** The photo. */
    private byte[] Photo;
    
    /** The email. */
    private String Email;
    
    /** The phone. */
    private String Phone;
    
    /** The salary. */
    private float Salary;
    
    /** The notes. */
    private String Notes;
    
    /** The depart id. */
    private String DepartId;
    
    /** The departs. */
    @ManyToOne
    @JoinColumn(name = "DepartId", referencedColumnName = "Id", insertable = false, updatable = false)
    private DepartsModel departs;
    
    /** The recoreds. */
    @OneToMany(mappedBy = "staffss", fetch = FetchType.LAZY)
    private Collection<RecordsModel> recoreds;

    /**
	 * Staffs DB.
	 */
    public StaffsModel() {
    }

    /**
	 * Image.
	 *
	 * @param photo the photo
	 * @return the string
	 */
    public String image(byte[] photo) {
        return Base64.getEncoder().encodeToString(photo);
    }   
   /**
	 * Instantiates a new staffs model.
	 *
	 * @param staffsID the staffs ID
	 * @param name     the name
	 * @param gender   the gender
	 * @param birthday the birthday
	 * @param photo    the photo
	 * @param email    the email
	 * @param phone    the phone
	 * @param salary   the salary
	 * @param notes    the notes
	 * @param departId the depart id
	 * @param departs  the departs
	 * @param recoreds the recoreds
	 */
   public StaffsModel(String staffsID, String name, boolean gender, Date birthday, byte[] photo, String email, String phone, float salary, String notes, String departId, DepartsModel departs, Collection<RecordsModel> recoreds) {
        this.Email = staffsID;
        this.Name = name;
        this.Gender = gender;
        this.Birthday = birthday;
        this.Photo = photo;
        this.Email = email;
        this.Phone = phone;
        this.Salary = salary;
        this.Notes = notes;
        this.DepartId = departId;
        this.departs = departs;
        this.recoreds = recoreds;
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
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return Name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		Name = name;
	}

	/**
	 * Checks if is gender.
	 *
	 * @return true, if is gender
	 */
	public boolean isGender() {
		return Gender;
	}

	/**
	 * Sets the gender.
	 *
	 * @param gender the new gender
	 */
	public void setGender(boolean gender) {
		Gender = gender;
	}

	/**
	 * Gets the birthday.
	 *
	 * @return the birthday
	 */
	public Date getBirthday() {
		return Birthday;
	}

	/**
	 * Sets the birthday.
	 *
	 * @param birthday the new birthday
	 */
	public void setBirthday(Date birthday) {
		Birthday = birthday;
	}

	/**
	 * Gets the photo.
	 *
	 * @return the photo
	 */
	public byte[] getPhoto() {
		return Photo;
	}

	/**
	 * Sets the photo.
	 *
	 * @param photo the new photo
	 */
	public void setPhoto(byte[] photo) {
		Photo = photo;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return Email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		Email = email;
	}

	/**
	 * Gets the phone.
	 *
	 * @return the phone
	 */
	public String getPhone() {
		return Phone;
	}

	/**
	 * Sets the phone.
	 *
	 * @param phone the new phone
	 */
	public void setPhone(String phone) {
		Phone = phone;
	}

	/**
	 * Gets the salary.
	 *
	 * @return the salary
	 */
	public float getSalary() {
		return Salary;
	}

	/**
	 * Sets the salary.
	 *
	 * @param salary the new salary
	 */
	public void setSalary(float salary) {
		Salary = salary;
	}

	/**
	 * Gets the notes.
	 *
	 * @return the notes
	 */
	public String getNotes() {
		return Notes;
	}

	/**
	 * Sets the notes.
	 *
	 * @param notes the new notes
	 */
	public void setNotes(String notes) {
		Notes = notes;
	}

	/**
	 * Gets the depart id.
	 *
	 * @return the depart id
	 */
	public String getDepartId() {
		return DepartId;
	}

	/**
	 * Sets the depart id.
	 *
	 * @param departId the new depart id
	 */
	public void setDepartId(String departId) {
		DepartId = departId;
	}

	/**
	 * Gets the departs.
	 *
	 * @return the departs
	 */
	public DepartsModel getDeparts() {
		return departs;
	}

	/**
	 * Sets the departs.
	 *
	 * @param departs the new departs
	 */
	public void setDeparts(DepartsModel departs) {
		this.departs = departs;
	}

	/**
	 * Gets the recoreds.
	 *
	 * @return the recoreds
	 */
	public Collection<RecordsModel> getRecoreds() {
		return recoreds;
	}

	/**
	 * Sets the recoreds.
	 *
	 * @param recoreds the new recoreds
	 */
	public void setRecoreds(Collection<RecordsModel> recoreds) {
		this.recoreds = recoreds;
	}}

