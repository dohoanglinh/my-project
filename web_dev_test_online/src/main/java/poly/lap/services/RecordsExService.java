/*
**======= Abouts us =======**
* System name : assigment5
* Verstion    : 1.0
* Create date : Mar 9, 2019 10:32:33 AM
* Description : Create by Linh Noob
* Copyright © 2019 Noob.com.All rights reserved.
**=========================**
*/
package poly.lap.services;

import java.util.Optional;
import poly.lap.models.RecordsModel;


// TODO: Auto-generated Javadoc
/**
 * The Interface RecordsExService.
 */
public interface RecordsExService {

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
    void delete(RecordsModel t);

    /**
	 * Delete all.
	 */
    void deleteAll();

    /**
	 * Delete all.
	 *
	 * @param itrbl the itrbl
	 */
    void deleteAll(Iterable<? extends RecordsModel> itrbl);

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
    Iterable<RecordsModel> findAll();

    /**
	 * Find all by id.
	 *
	 * @param itrbl the itrbl
	 * @return the iterable
	 */
    Iterable<RecordsModel> findAllById(Iterable<String> itrbl);

    /**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the optional
	 */
    Optional<RecordsModel> findById(String id);

    /**
	 * Save.
	 *
	 * @param   <S> the generic type
	 * @param s the s
	 * @return the s
	 */
    <S extends RecordsModel> S save(S s);

    /**
	 * Save all.
	 *
	 * @param       <S> the generic type
	 * @param itrbl the itrbl
	 * @return the iterable
	 */
    <S extends RecordsModel> Iterable<S> saveAll(Iterable<S> itrbl);
    
}
