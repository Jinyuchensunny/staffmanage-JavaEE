package web.action;

import java.io.IOException;

public interface DeptAction {
    //获取所有部门对象的列表
    public void findAllDepts() throws IOException;

    //获取指定部门
    public void findDeptById() throws IOException;

    //新增
    public String addDept() throws IOException;
}
