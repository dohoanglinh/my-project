/*
**======= Abouts us =======**
* System name : assigment5
* Verstion    : 1.0
* Create date : Mar 9, 2019 9:55:22 AM
* Description : Create by Linh Noob
* Copyright © 2019 Noob.com.All rights reserved.
**=========================**
*/
package poly.lap.models;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;


// TODO: Auto-generated Javadoc
/**
 * The Class DepartsModel.
 */
@Entity
@Table(name="departs")
public class DepartsModel {

    /** The Id. */
    @Id
    @NotEmpty(message = "không được bỏ trống")
    private String Id;
    
    /** The Name. */
    @NotBlank(message = "không được bỏ trống!!")
    private String Name;
    
    /** The staffs. */
    @OneToMany (mappedBy = "departss", fetch = FetchType.EAGER)
    private Collection<StaffsModel> staffs;

    /**
	 * Instantiates a new departs model.
	 */
    public DepartsModel() {
    }

    /**
	 * Instantiates a new departs model.
	 *
	 * @param departId   the depart id
	 * @param nameDepart the name depart
	 * @param staffs     the staffs
	 */
    public DepartsModel(String departId, String nameDepart, Collection<StaffsModel> staffs) {
        this.Id = departId;
        this.Name = nameDepart;
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
	 * Gets the staffs.
	 *
	 * @return the staffs
	 */
	public Collection<StaffsModel> getStaffs() {
		return staffs;
	}

	/**
	 * Sets the staffs.
	 *
	 * @param staffs the new staffs
	 */
	public void setStaffs(Collection<StaffsModel> staffs) {
		this.staffs = staffs;
	}}


