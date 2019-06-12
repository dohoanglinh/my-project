/*
**======= Abouts us =======**
* System name : assigment5
* Verstion    : 1.0
* Create date : Mar 9, 2019 10:16:24 AM
* Description : Create by Linh Noob
* Copyright © 2019 Noob.com.All rights reserved.
**=========================**
*/
package poly.lap.services;

import java.util.Optional;

import poly.lap.models.DepartsModel;

// TODO: Auto-generated Javadoc

/**
 * The Interface DepartsExService.
 */
public interface DepartsExService {

    /**
	 * Count.
	 *
	 * @return the long
	 */
    long count();

    /**
	 * Delete.
	 *
	 * @param t the t
	 */
    void delete(DepartsModel t);

    /**
	 * Delete all.
	 */
    void deleteAll();

    /**
	 * Delete all.
	 *
	 * @param itrbl the itrbl
	 */
    void deleteAll(Iterable<? extends DepartsModel> itrbl);

    /**
	 * Delete by id.
	 *
	 * @param id the id
	 */
    void deleteById(String id);

    /**
	 * Exists by id.
	 *
	 * @param id the id
	 * @return true, if successful
	 */
    boolean existsById(String id);

    /**
	 * Find all.
	 *
	 * @return the iterable
	 */
    Iterable<DepartsModel> findAll();

    /**
	 * Find all by id.
	 *
	 * @param itrbl the itrbl
	 * @return the iterable
	 */
    Iterable<DepartsModel> findAllById(Iterable<String> itrbl);

    /**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the optional
	 */
    Optional<DepartsModel> findById(String id);

    /**
	 * Save.
	 *
	 * @param   <S> the generic type
	 * @param s the s
	 * @return the s
	 */
    <S extends DepartsModel> S save(S s);

    /**
	 * Save all.
	 *
	 * @param       <S> the generic type
	 * @param itrbl the itrbl
	 * @return the iterable
	 */
    <S extends DepartsModel> Iterable<S> saveAll(Iterable<S> itrbl);
    
}
