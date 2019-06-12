/*
**======= Abouts us =======**
* System name : assigment5
* Verstion    : 1.0
* Create date : Mar 9, 2019 11:09:12 AM
* Description : Create by Linh Noob
* Copyright © 2019 Noob.com.All rights reserved.
**=========================**
*/
package poly.lap.services.ipml;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import poly.lap.models.UserModel;
import poly.lap.repostories.UsersRepostory;
import poly.lap.services.UsersExService;

 
// TODO: Auto-generated Javadoc
/**
 * The Class UsersExServiceIpml.
 */
@Service
public class UsersExServiceIpml implements UsersExService{
    
    /** The users repostory. */
    @Autowired
    private UsersRepostory usersRepostory;

    /* (non-Javadoc)
     * @see poly.lap.services.UsersExService#save(poly.lap.models.UserModel)
     */
    public <S extends UserModel> S save(S s) {
        return usersRepostory.save(s);
    }

    /* (non-Javadoc)
     * @see poly.lap.services.UsersExService#saveAll(java.lang.Iterable)
     */
    public <S extends UserModel> Iterable<S> saveAll(Iterable<S> itrbl) {
        return usersRepostory.saveAll(itrbl);
    }

    /* (non-Javadoc)
     * @see poly.lap.services.UsersExService#findById(java.lang.String)
     */
    public Optional<UserModel> findById(String id) {
        return usersRepostory.findById(id);
    }

    /* (non-Javadoc)
     * @see poly.lap.services.UsersExService#existsById(java.lang.String)
     */
    public boolean existsById(String id) {
        return usersRepostory.existsById(id);
    }

    /* (non-Javadoc)
     * @see poly.lap.services.UsersExService#findAll()
     */
    public Iterable<UserModel> findAll() {
        return usersRepostory.findAll();
    }

    /* (non-Javadoc)
     * @see poly.lap.services.UsersExService#findAllById(java.lang.Iterable)
     */
    public Iterable<UserModel> findAllById(Iterable<String> itrbl) {
        return usersRepostory.findAllById(itrbl);
    }

    /* (non-Javadoc)
     * @see poly.lap.services.UsersExService#count()
     */
    public long count() {
        return usersRepostory.count();
    }

    /* (non-Javadoc)
     * @see poly.lap.services.UsersExService#deleteById(java.lang.String)
     */
    public void deleteById(String id) {
        usersRepostory.deleteById(id);
    }

    /* (non-Javadoc)
     * @see poly.lap.services.UsersExService#delete(poly.lap.models.UserModel)
     */
    public void delete(UserModel t) {
        usersRepostory.delete(t);
    }

    /* (non-Javadoc)
     * @see poly.lap.services.UsersExService#deleteAll(java.lang.Iterable)
     */
    public void deleteAll(Iterable<? extends UserModel> itrbl) {
        usersRepostory.deleteAll(itrbl);
    }

    /* (non-Javadoc)
     * @see poly.lap.services.UsersExService#deleteAll()
     */
    public void deleteAll() {
        usersRepostory.deleteAll();
    }
    
}
