package org.dao;
import org.model.Emp;
import java.util.List;

public interface EmpDao {
    // 获取所有员工的列表
    public List<Emp>findAllEmps();

    // 获取指定编号的部门员工列表
    public List<Emp>findEmpsByDeptId(int deptId);

    // 获取指定编号的员工
    public Emp findEmpById(int empId);

    // 新增员工
    public void addEmp(Emp emp);

}
