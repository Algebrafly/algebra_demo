package dao;

import entity.SysDictionaryEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author al
 * @date 2021/2/5 10:50
 * @description
 */
@Repository("SysDictionaryDao")
public class SysDictionaryDaoImpl implements SysDictionaryDao{

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    @Override
    public SysDictionaryEntity selectByType(String type) {
        Session currentSession = sessionFactory.getCurrentSession();
        return currentSession.get(SysDictionaryEntity.class, type);
    }
}
