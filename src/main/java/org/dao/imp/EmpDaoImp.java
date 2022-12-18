package org.dao.imp;

import org.dao.EmpDao;
import org.model.Emp;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

public class EmpDaoImp extends HibernateDaoSupport implements EmpDao {
    @Override
    public List<Emp> findAllEmps() {
        List<Emp>list = getHibernateTemplate().find("from org.model.Emp");
        return list;
    }

    @Override
    public List<Emp> findEmpsByDeptId(int deptId) {
        List<Emp>list = getHibernateTemplate().find("from org.model.Emp where dept.deptId=?",deptId);
        return list;
    }

    @Override
    public Emp findEmpById(int empId) {
        List<Emp>list = getHibernateTemplate().find("from Emp where empId=?",empId);
        Emp e = list.get(0);
        return e;
    }

    @Override
    public void addEmp(Emp emp) {
        getHibernateTemplate().save(emp);
    }
}
