/*
**======= Abouts us =======**
* System name : assigment5
* Verstion    : 1.0
* Create date : Mar 9, 2019 10:39:09 AM
* Description : Create by Linh Noob
* Copyright © 2019 Noob.com.All rights reserved.
**=========================**
*/
package poly.lap.repostories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import poly.lap.models.DepartsModel;


/**
 * The Interface DepartsRepostory.
 */
@Repository
public interface DepartsRepostory extends CrudRepository<DepartsModel, String>{
    
}
