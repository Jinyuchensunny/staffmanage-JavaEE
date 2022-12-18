package web.action.imp;

import web.action.DeptAction;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.dao.DeptDao;
import org.model.Dept;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class DeptActionImp extends ActionSupport implements DeptAction, ServletResponseAware, ServletRequestAware {
    public DeptDao getDeptDao() {
        return deptDao;
    }

    private DeptDao deptDao;
    public void setDeptDao(DeptDao deptDao) {
        this.deptDao = deptDao;
    }

    private HttpServletRequest request;
    private HttpServletResponse response;
    // 回调函数，当tomcat服务器端发送响应给移动端时，自动调用
    public void setServletResponse(HttpServletResponse res) {
        this.response = res;
    }
    // 回调函数，当移动端向tomcat服务器端发送请求时，自动调用
    public void setServletRequest(HttpServletRequest req) {
        this.request = req;
    }

    public void makeJson(List<Dept>list)throws IOException{
        response.setHeader("Content-Type", "text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        // 构造json输出字符串
        JSONArray jsonArray = new JSONArray();
        for (Dept dept:list){
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("DeptId",dept.getDeptId());
            jsonObj.put("DeptName",dept.getDeptName());
            jsonArray.add(jsonObj);
        }
        System.out.println(jsonArray.toString());
        JSONObject root = new JSONObject();
        root.put("result", jsonArray);
        out.write(root.toString());
        out.flush();
        out.close();
    }

    @Override
    public void findAllDepts() throws IOException {
        List<Dept>list = deptDao.findAllDepts();
        makeJson(list);
    }

    @Override
    public void findDeptById() throws IOException {
        String deptId = request.getParameter("deptId");
        Dept dept =deptDao.findDeptById(new Integer(deptId));
        List<Dept>list = new ArrayList<Dept>();
        list.add(dept);
        makeJson(list);
    }

    @Override
    public String addDept() throws IOException {
        String deptName = request.getParameter("addDeptName");
        Dept dept = new Dept();
        dept.setDeptName(deptName);
        deptDao.addDept(dept);
        return "success";
    }
}
