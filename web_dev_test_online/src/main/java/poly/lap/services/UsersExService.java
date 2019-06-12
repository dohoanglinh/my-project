/*
**======= Abouts us =======**
* System name : assigment5
* Verstion    : 1.0
* Create date : Mar 9, 2019 10:37:47 AM
* Description : Create by Linh Noob
* Copyright © 2019 Noob.com.All rights reserved.
**=========================**
*/
package poly.lap.services;

import java.util.Optional;
import poly.lap.models.UserModel;;

// TODO: Auto-generated Javadoc
/**
 * The Interface UsersExService.
 */
public interface UsersExService {

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
    void delete(UserModel t);

    /**
	 * Delete all.
	 */
    void deleteAll();

    /**
	 * Delete all.
	 *
	 * @param itrbl the itrbl
	 */
    void deleteAll(Iterable<? extends UserModel> itrbl);

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
    Iterable<UserModel> findAll();

    /**
	 * Find all by id.
	 *
	 * @param itrbl the itrbl
	 * @return the iterable
	 */
    Iterable<UserModel> findAllById(Iterable<String> itrbl);

    /**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the optional
	 */
    Optional<UserModel> findById(String id);

    /**
	 * Save.
	 *
	 * @param   <S> the generic type
	 * @param s the s
	 * @return the s
	 */
    <S extends UserModel> S save(S s);

    /**
	 * Save all.
	 *
	 * @param       <S> the generic type
	 * @param itrbl the itrbl
	 * @return the iterable
	 */
    <S extends UserModel> Iterable<S> saveAll(Iterable<S> itrbl);
    
}
