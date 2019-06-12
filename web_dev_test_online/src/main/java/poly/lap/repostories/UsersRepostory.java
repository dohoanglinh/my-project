/*
**======= Abouts us =======**
* System name : assigment5
* Verstion    : 1.0
* Create date : Mar 9, 2019 10:42:43 AM
* Description : Create by Linh Noob
* Copyright © 2019 Noob.com.All rights reserved.
**=========================**
*/
package poly.lap.repostories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import poly.lap.models.UserModel;


/**
 * The Interface UsersRepostory.
 */
@Repository
public interface UsersRepostory  extends CrudRepository<UserModel, String>{
    
}
