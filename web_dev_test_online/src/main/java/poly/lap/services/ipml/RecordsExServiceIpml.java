/*
**======= Abouts us =======**
* System name : assigment5
* Verstion    : 1.0
* Create date : Mar 9, 2019 11:06:20 AM
* Description : Create by Linh Noob
* Copyright © 2019 Noob.com.All rights reserved.
**=========================**
*/
package poly.lap.services.ipml;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import poly.lap.models.RecordsModel;
import poly.lap.repostories.RecordsRepostory;
import poly.lap.services.RecordsExService;

// TODO: Auto-generated Javadoc

/**
 * The Class RecordsExServiceIpml.
 */
@Service
public class RecordsExServiceIpml implements RecordsExService{
    
    /** The records repostory. */
    @Autowired
    private RecordsRepostory recordsRepostory;

    /* (non-Javadoc)
     * @see poly.lap.services.RecordsExService#save(poly.lap.models.RecordsModel)
     */
    public <S extends RecordsModel> S save(S s) {
        return recordsRepostory.save(s);
    }

    /* (non-Javadoc)
     * @see poly.lap.services.RecordsExService#saveAll(java.lang.Iterable)
     */
    public <S extends RecordsModel> Iterable<S> saveAll(Iterable<S> itrbl) {
        return recordsRepostory.saveAll(itrbl);
    }

    /* (non-Javadoc)
     * @see poly.lap.services.RecordsExService#findById(java.lang.String)
     */
    public Optional<RecordsModel> findById(String id) {
        return recordsRepostory.findById(id);
    }

    /* (non-Javadoc)
     * @see poly.lap.services.RecordsExService#existsById(java.lang.String)
     */
    public boolean existsById(String id) {
        return recordsRepostory.existsById(id);
    }

    /* (non-Javadoc)
     * @see poly.lap.services.RecordsExService#findAll()
     */
    public Iterable<RecordsModel> findAll() {
        return recordsRepostory.findAll();
    }

    /* (non-Javadoc)
     * @see poly.lap.services.RecordsExService#findAllById(java.lang.Iterable)
     */
    public Iterable<RecordsModel> findAllById(Iterable<String> itrbl) {
        return recordsRepostory.findAllById(itrbl);
    }

    /* (non-Javadoc)
     * @see poly.lap.services.RecordsExService#count()
     */
    public long count() {
        return recordsRepostory.count();
    }

    /* (non-Javadoc)
     * @see poly.lap.services.RecordsExService#deleteById(java.lang.String)
     */
    public void deleteById(String id) {
        recordsRepostory.deleteById(id);
    }

    /* (non-Javadoc)
     * @see poly.lap.services.RecordsExService#delete(poly.lap.models.RecordsModel)
     */
    public void delete(RecordsModel t) {
        recordsRepostory.delete(t);
    }

    /* (non-Javadoc)
     * @see poly.lap.services.RecordsExService#deleteAll(java.lang.Iterable)
     */
    public void deleteAll(Iterable<? extends RecordsModel> itrbl) {
        recordsRepostory.deleteAll(itrbl);
    }

    /* (non-Javadoc)
     * @see poly.lap.services.RecordsExService#deleteAll()
     */
    public void deleteAll() {
        recordsRepostory.deleteAll();
    }
    
}
