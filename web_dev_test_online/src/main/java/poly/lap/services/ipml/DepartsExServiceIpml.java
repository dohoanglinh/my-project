/*
**======= Abouts us =======**
* System name : assigment5
* Verstion    : 1.0
* Create date : Mar 9, 2019 11:05:12 AM
* Description : Create by Linh Noob
* Copyright © 2019 Noob.com.All rights reserved.
**=========================**
*/
package poly.lap.services.ipml;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poly.lap.models.DepartsModel;
import poly.lap.repostories.DepartsRepostory;
import poly.lap.services.DepartsExService;


// TODO: Auto-generated Javadoc
/**
 * The Class DepartsExServiceIpml.
 */
@Service
public class DepartsExServiceIpml implements DepartsExService{
    
    /** The departs repostory. */
    @Autowired
    private DepartsRepostory departsRepostory;

    /* (non-Javadoc)
     * @see poly.lap.services.DepartsExService#save(poly.lap.models.DepartsModel)
     */
    public <S extends DepartsModel> S save(S s) {
        return departsRepostory.save(s);
    }

    /* (non-Javadoc)
     * @see poly.lap.services.DepartsExService#saveAll(java.lang.Iterable)
     */
    public <S extends DepartsModel> Iterable<S> saveAll(Iterable<S> itrbl) {
        return departsRepostory.saveAll(itrbl);
    }

    /* (non-Javadoc)
     * @see poly.lap.services.DepartsExService#findById(java.lang.String)
     */
    public Optional<DepartsModel> findById(String id) {
        return departsRepostory.findById(id);
    }

    /* (non-Javadoc)
     * @see poly.lap.services.DepartsExService#existsById(java.lang.String)
     */
    public boolean existsById(String id) {
        return departsRepostory.existsById(id);
    }

    /* (non-Javadoc)
     * @see poly.lap.services.DepartsExService#findAll()
     */
    public Iterable<DepartsModel> findAll() {
        return departsRepostory.findAll();
    }

    /* (non-Javadoc)
     * @see poly.lap.services.DepartsExService#findAllById(java.lang.Iterable)
     */
    public Iterable<DepartsModel> findAllById(Iterable<String> itrbl) {
        return departsRepostory.findAllById(itrbl);
    }

    /* (non-Javadoc)
     * @see poly.lap.services.DepartsExService#count()
     */
    public long count() {
        return departsRepostory.count();
    }

    /* (non-Javadoc)
     * @see poly.lap.services.DepartsExService#deleteById(java.lang.String)
     */
    public void deleteById(String id) {
        departsRepostory.deleteById(id);
    }

    /* (non-Javadoc)
     * @see poly.lap.services.DepartsExService#delete(poly.lap.models.DepartsModel)
     */
    public void delete(DepartsModel t) {
        departsRepostory.delete(t);
    }

    /* (non-Javadoc)
     * @see poly.lap.services.DepartsExService#deleteAll(java.lang.Iterable)
     */
    public void deleteAll(Iterable<? extends DepartsModel> itrbl) {
        departsRepostory.deleteAll(itrbl);
    }

    /* (non-Javadoc)
     * @see poly.lap.services.DepartsExService#deleteAll()
     */
    public void deleteAll() {
        departsRepostory.deleteAll();
    }
    
}