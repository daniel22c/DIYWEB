package com.daniel22c.DIYWEB.service;

import com.daniel22c.DIYWEB.dao.DIYDao;
import com.daniel22c.DIYWEB.model.DIY;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Myungho on 4/14/2017.
 */
@Service
public class DIYServiceImpl implements DIYService{
    @Autowired
    private DIYDao diyDao;

    @Override
    public Iterable<DIY> findAll() {
        return diyDao.findAll();
    }

    @Override
    public DIY findOne(Long id) {
        return diyDao.findOne(id);
    }

    @Override
    public DIY findDIYByTitle(String title) {
        return diyDao.findDIYByTitle(title);
    }

    //    @Override
//    public DIY findDIYById(Long id){
//        return diyDao.findDIYById(id);
//    }
    @Override
    public void save(DIY diy) {
        diyDao.save(diy);
    }

//    @Override
//    public List<DIY> getDIYsFromDIY_Ids(List<Long> diy_ids) {
//        List<DIY> diys = new ArrayList();
//        for(Long id: diy_ids){
//            diys.add(findDIYById(id));
//        }
//        return diys;
//    }
}
