package web.action.imp;

import web.action.EmpAction;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.dao.DeptDao;
import org.dao.EmpDao;
import org.model.Dept;
import org.model.Emp;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EmpActionImp extends ActionSupport implements EmpAction, ServletResponseAware, ServletRequestAware {
    public EmpDao getEmpDao() {
        return empDao;
    }

    public void setEmpDao(EmpDao empDao) {
        this.empDao = empDao;
    }

    private EmpDao empDao;

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

    public void makeJson(List<Emp>list)throws IOException{
        response.setHeader("Content-Type", "text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        // 构造json输出字符串
        JSONArray jsonArray = new JSONArray();
        for (Emp emp:list){
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("EmpId",emp.getdId());
            jsonObj.put("EmpName",emp.getEmpName());
            jsonObj.put("DId",emp.getdId());
            jsonObj.put("Photo",emp.getPhoto());
            jsonObj.put("Brief",emp.getBrief());
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
    public void findAllEmps() throws IOException {
        List<Emp>list = empDao.findAllEmps();
        makeJson(list);
    }

    @Override
    public void findEmpsByDeptId() throws IOException {
        String deptId = request.getParameter("deptId");
        List<Emp>list = empDao.findEmpsByDeptId(Integer.valueOf(deptId));
        makeJson(list);
    }

    @Override
    public void findEmpById() throws IOException {
        String empId = request.getParameter("empId");
        Emp emp= empDao.findEmpById(new Integer(empId));
        List<Emp>list = new ArrayList<Emp>();
        list.add(emp);
        makeJson(list);
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpDept() {
        return empDept;
    }

    public void setEmpDept(String empDept) {
        this.empDept = empDept;
    }

    public String getEmpPhotoFileName() {
        return empPhotoFileName;
    }

    public void setEmpPhotoFileName(String empPhotoFileName) {
        this.empPhotoFileName = empPhotoFileName;
    }

    public File getEmpPhoto() {
        return empPhoto;
    }

    public void setEmpPhoto(File empPhoto) {
        this.empPhoto = empPhoto;
    }

    public String getEmpMainBody() {
        return empMainBody;
    }

    public void setEmpMainBody(String empMainBody) {
        this.empMainBody = empMainBody;
    }

    private String empName;

    private String empDept;

    private String empPhotoFileName;

    private File empPhoto;

    private String empMainBody;

    private DeptDao deptDao;

    public DeptDao getDeptDao() {
        return deptDao;
    }

    public void setDeptDao(DeptDao deptDao) {
        this.deptDao = deptDao;
    }

    @Override
    public String addEmp() throws IOException {
        Emp emp = new Emp();
//        empDept = (String) request.getAttribute("empDept");
        // 用<x>和</x>对，empId、empName和empBrief这3个内容，拼接成字符串
        String empContent = "<x>" + empName + "</x>" + "<x>" + empDept + "</x>" + "<x>" + empMainBody + "</x>";
        emp.setBrief(empContent);
        emp.setEmpName(empName);
        Dept dept = deptDao.findDeptById(new Integer(empDept));
        emp.setDept(dept);

        // empPhotoFileName是本地标题图片文件名(如C:\demo.png)，将把该图片上传到web服务器上
        if (empPhotoFileName != null) {
            // realPath是Tomcat的webapps\StaffManage\image目录
            String realPath = ServletActionContext.getRequest().getRealPath("/image");
            String hz = empPhotoFileName.substring(empPhotoFileName.lastIndexOf("."));

            // newFileName是web服务器上新建的标题图片文件名，如4874d660-33e4-4816-a6b5-36b76fdc049f.png
            String newFileName = UUID.randomUUID().toString() + hz;

            // 在webapps\StaffManage\image目录下新建1个文件4874d660-33e4-4816-a6b5-36b76fdc049f.png
            OutputStream os = new FileOutputStream(new File(realPath, newFileName));

            // titleImageFullName是存放到数据库表中的标题图片文件的相对路径名
            String titleImageFullName = "/StaffManage/image/" + newFileName;

            // 设置简介图片，只有1张图片
            emp.setPhoto(titleImageFullName);

            // 打开本地标题图片文件对象empPhoto，empPhoto是File类型
            InputStream is = new FileInputStream(empPhoto);

            // 通过字节流方式，每次1K读取本地图片，然后写到web服务器webapps\DeviceManage\image下的标题图片文件4874d660-33e4-4816-a6b5-36b76fdc049f.png
            byte flush[] = new byte[1024];
            int len = 0;
            while (0 <= (len = is.read(flush))) {
                os.write(flush, 0, len);
            }
            is.close();
            os.close();
        } else {
            System.out.println("未上传图片");
        }
        empDao.addEmp(emp);

        return "success";
    }

    private List<Dept> addempDept;

    public List<Dept> getAddempDept() {
        return addempDept;
    }

    public void setAddempDept(List<Dept> addempDept) {
        this.addempDept = addempDept;
    }

    public String showaddEmp(){
        addempDept = deptDao.findAllDepts();
        return "success";
    }
}
