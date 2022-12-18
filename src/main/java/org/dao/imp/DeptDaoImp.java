package org.dao.imp;
import org.dao.DeptDao;
import org.model.Dept;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

public class DeptDaoImp extends HibernateDaoSupport implements DeptDao {
    @Override
    public List<Dept> findAllDepts() {
        List<Dept> list = getHibernateTemplate().find("from org.model.Dept");
        return list;
    }

    @Override
    public Dept findDeptById(int deptId) {
        List<Dept> list = getHibernateTemplate().find("from Dept where deptId = ?", deptId);
        Dept d = list.get(0);
        return d;
    }

    @Override
    public void addDept(Dept dept) {
        getHibernateTemplate().save(dept);
    }
}
