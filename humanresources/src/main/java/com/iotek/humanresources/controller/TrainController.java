package com.iotek.humanresources.controller;

import com.iotek.humanresources.model.Department;
import com.iotek.humanresources.model.EmpToTr;
import com.iotek.humanresources.model.Employee;
import com.iotek.humanresources.model.Train;
import com.iotek.humanresources.service.DepartmentService;
import com.iotek.humanresources.service.EmpToTrService;
import com.iotek.humanresources.service.EmployeeService;
import com.iotek.humanresources.service.TrainService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * Created by grzha on 2018/8/1.
 */
@Controller
public class TrainController {
    @Resource
    private TrainService trainService;
    @Resource
    private DepartmentService departmentService;
    @Resource
    private EmployeeService employeeService;
    @Resource
    private EmpToTrService empToTrService;

    @RequestMapping("/trainManage")
    public String trainManage(HttpSession session){
        List<Train> trainList=trainService.getAllTrain();

        session.setAttribute("trainList",trainList);
        session.setAttribute("withdrawTrainError","");//��������Ϣ���
        return "trainManage";
    }

    @RequestMapping("/addTrain")
    public String addTrain(HttpSession session){
        List<Department> departmentList=departmentService.getAllDepartment();
        session.setAttribute("addTrainDepartmentList",departmentList);
        session.setAttribute("addTrainError","");//���֮ǰ�Ĵ����¼

        return "addTrain";
    }

    @RequestMapping("/addTrain1")
    public String addTrain1(String theme,String content,String beginTime,String endTime,String address,String selectDep,String button1,String traindate,String button2,HttpSession session){
        //System.out.println(theme+"--"+content+"--"+beginTime+"--"+endTime+"--"+address);
        //System.out.println(selectDep+"||"+button1+"||"+traindate+"||"+button2);

        if(theme.equals("")||content.equals("")||beginTime.equals("")||endTime.equals("")||address.equals("")){
            session.setAttribute("addTrainError","�����������");
            return "addTrain";
        }
        Date date=UtilController.StringToDate(beginTime);
        Date date1=UtilController.StringToDate(endTime);
        boolean flag=UtilController.compareDate(date,date1);
        if(!flag){
            session.setAttribute("addTrainError","��ѵʱ������");
            return "addTrain";
        }
        if(button1!=null){
            if(selectDep.equals("")){
                session.setAttribute("addTrainError","�����������");
                return "addTrain";
            }
            int state=0;//����δ����
            Train train=new Train(theme,content,UtilController.StringToDate1(beginTime),UtilController.StringToDate1(endTime),address,state);
            trainService.addNewTrain(train);
            Train trainAfter=trainService.getTrainByThemeContentAddress(train);

            Department temp=new Department(Integer.parseInt(selectDep));
            List<Employee> employeeList=employeeService.getEmployeeByDEPID(temp);
            for(Employee tempEmp:employeeList){
                EmpToTr empToTr=new EmpToTr();
                empToTr.setEmpid(tempEmp.getId());
                empToTr.setTid(trainAfter.getId());

                empToTrService.addNewEmpToTr(empToTr);
            }
            return "managerWelcome";

        }
        if(button2!=null){
            if(traindate.equals("")){
                session.setAttribute("addTrainError","�����������");
                return "addTrain";
            }



        }


        return null;
    }

    @RequestMapping("/showTrainInfo")
    public String showTrainInfo(int trainInfoId,HttpSession session){
        Train temp=new Train(trainInfoId);
        Train train=trainService.getTrainById(temp);
        List<Employee> employeeList=train.getEmployeeList();

        session.setAttribute("showTrainInfomation",train);
        session.setAttribute("showTrainInfomationEmployeeList",employeeList);
        return "showTrainInfo";
    }

    @RequestMapping("/modifyTrain")
    public String modifyTrain(int modifyTrainId,HttpSession session){
        Train temp=new Train(modifyTrainId);
        Train train=trainService.getTrainById(temp);

        session.setAttribute("modifyTrainobj",train);
        return "modifyTrain";
    }

    @RequestMapping("/modifyTrain1")
    public String modifyTrain1(String theme,String content,String beginTime,String endTime,String address,HttpSession session){
        Date begin=UtilController.StringToDate1(beginTime);
        Date end=UtilController.StringToDate1(endTime);
        Train train= (Train) session.getAttribute("modifyTrainobj");
        train.setTheme(theme);
        train.setContent(content);
        train.setBegintime(begin);
        train.setEndtime(end);
        train.setAddress(address);

        trainService.modifyTrainThemeContentBegintimeEndtimeAddressById(train);
        return "managerWelcome";
    }

    @RequestMapping("/releaseTrain")
    public String releaseTrain(int releaseTrainId,HttpSession session){
        Train temp=new Train(releaseTrainId);
        Train train=trainService.getTrainById(temp);
        int state=1;//������ѵ����
        train.setState(state);
        train.setReleasetime(new Date());
        trainService.modifyTrainReleasetimeStateById(train);
        //����session
        List<Train> trainList=trainService.getAllTrain();

        session.setAttribute("trainList",trainList);
        return "trainManage";

    }

    @RequestMapping("/withdrawTrain")
    public String withdrawTrain(int withdrawTrainId,HttpSession session){
        Train temp=new Train(withdrawTrainId);
        Train train=trainService.getTrainById(temp);
        Date releasetime=train.getReleasetime();
        Date currenttime=new Date();
        boolean flag=UtilController.compareTimeTenMinute(releasetime,currenttime);
        if(flag){
            session.setAttribute("withdrawTrainError","����ʱ�䳬��10���ӣ��޷�����");
            return "trainManage";
        }
        int state=0;//�������ط���  ״̬���±�Ϊδ����
        train.setState(state);
        train.setReleasetime(null);//��oracle���ݿ�������null�����У���Ҫ��sql�����ֱ����null���޷�ʹ��null�������Ժ������

        trainService.modifyTrainWithdrawTrain(train);
        //����session
        List<Train> trainList=trainService.getAllTrain();

        session.setAttribute("trainList",trainList);
        session.setAttribute("withdrawTrainError","");
        return "trainManage";
    }





}