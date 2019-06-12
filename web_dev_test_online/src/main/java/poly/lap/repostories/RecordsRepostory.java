/*
**======= Abouts us =======**
* System name : assigment5
* Verstion    : 1.0
* Create date : Mar 9, 2019 10:39:52 AM
* Description : Create by Linh Noob
* Copyright © 2019 Noob.com.All rights reserved.
**=========================**
*/
package poly.lap.repostories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import poly.lap.models.RecordsModel;;

/**
 * The Interface RecordsRepostory.
 */
@Repository
public interface RecordsRepostory extends CrudRepository<RecordsModel, String>{
    
}
