package web.action;

import java.io.IOException;

public interface EmpAction {

    public void findAllEmps() throws IOException;

    public void findEmpsByDeptId()throws IOException;

    public void findEmpById() throws IOException;

    public String addEmp()throws IOException;

    public String showaddEmp()throws IOException;
}
