/*
**======= Abouts us =======**
* System name : assigment5
* Verstion    : 1.0
* Create date : Mar 9, 2019 11:07:41 AM
* Description : Create by Linh Noob
* Copyright © 2019 Noob.com.All rights reserved.
**=========================**
*/
package poly.lap.services.ipml;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import poly.lap.models.StaffsModel;
import poly.lap.repostories.StaffsRepostory;
import poly.lap.services.StaffsExService;


// TODO: Auto-generated Javadoc
/**
 * The Class StaffsExServiceIpml.
 */
@Service
public class StaffsExServiceIpml implements StaffsExService{
    
    /** The staffs repostory. */
    @Autowired
    private StaffsRepostory staffsRepostory;

    /* (non-Javadoc)
     * @see poly.lap.services.StaffsExService#save(poly.lap.models.StaffsModel)
     */
    public <S extends StaffsModel> S save(S s) {
        return staffsRepostory.save(s);
    }

    /* (non-Javadoc)
     * @see poly.lap.services.StaffsExService#saveAll(java.lang.Iterable)
     */
    public <S extends StaffsModel> Iterable<S> saveAll(Iterable<S> itrbl) {
        return staffsRepostory.saveAll(itrbl);
    }

    /* (non-Javadoc)
     * @see poly.lap.services.StaffsExService#findById(java.lang.String)
     */
    public Optional<StaffsModel> findById(String id) {
        return staffsRepostory.findById(id);
    }

    /* (non-Javadoc)
     * @see poly.lap.services.StaffsExService#existsById(java.lang.String)
     */
    public boolean existsById(String id) {
        return staffsRepostory.existsById(id);
    }

    /* (non-Javadoc)
     * @see poly.lap.services.StaffsExService#findAll()
     */
    public Iterable<StaffsModel> findAll() {
        return staffsRepostory.findAll();
    }

    /* (non-Javadoc)
     * @see poly.lap.services.StaffsExService#findAllById(java.lang.Iterable)
     */
    public Iterable<StaffsModel> findAllById(Iterable<String> itrbl) {
        return staffsRepostory.findAllById(itrbl);
    }

    /* (non-Javadoc)
     * @see poly.lap.services.StaffsExService#count()
     */
    public long count() {
        return staffsRepostory.count();
    }

    /* (non-Javadoc)
     * @see poly.lap.services.StaffsExService#deleteById(java.lang.String)
     */
    public void deleteById(String id) {
        staffsRepostory.deleteById(id);
    }

    /* (non-Javadoc)
     * @see poly.lap.services.StaffsExService#delete(poly.lap.models.StaffsModel)
     */
    public void delete(StaffsModel t) {
        staffsRepostory.delete(t);
    }

    /* (non-Javadoc)
     * @see poly.lap.services.StaffsExService#deleteAll(java.lang.Iterable)
     */
    public void deleteAll(Iterable<? extends StaffsModel> itrbl) {
        staffsRepostory.deleteAll(itrbl);
    }

    /* (non-Javadoc)
     * @see poly.lap.services.StaffsExService#deleteAll()
     */
    public void deleteAll() {
        staffsRepostory.deleteAll();
    }
    
}
