package org.dao;
import org.model.Dept;
import java.util.List;

public interface DeptDao {
    // 获得所有部门对象的列表
    public List<Dept> findAllDepts();

    // 获得指定编号的部门对象
    public Dept findDeptById(int deptId);

    // 新增一个部门
    public void addDept(Dept dept);
}
