package servlet.doctor;

import bean.Doctor;
import bean.PatientCase;
import dao.PatientCaseDao;
import util.Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet("/doctor/updatePatientCase")
public class UpdatePatientCase extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Doctor doctor = (Doctor) req.getSession().getAttribute("doctor");
        String id = Util.nullToString(req.getParameter("id"));
        String pid = Util.nullToString(req.getParameter("pid"));
        String pname = Util.nullToString(req.getParameter("pname"));
        String pathogen = Util.nullToString(req.getParameter("pathogen"));
        String suggest = Util.nullToString(req.getParameter("suggest"));
        PatientCaseDao patientCaseDao = new PatientCaseDao();
        /*String where="where pid=? order by ordertime desc";
        List<Recode> list = recodeDao.query(where, new Object[]{patient.getId()});*/
        String set ="set pname=?,pathogen=?,suggest=?,dname=?,update_date=? where id=?";
        if(patientCaseDao.update(set, new Object[]{pname,pathogen,suggest,doctor.getDname(),new Date(),id})){
            req.getRequestDispatcher("/doctor/patientCaseList?pid="+pid).forward(req,resp);
        }
    }
}
