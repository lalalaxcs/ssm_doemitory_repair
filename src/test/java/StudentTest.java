import com.xvchushun.config.SpringConfig;
import com.xvchushun.domain.Repair;
import com.xvchushun.domain.Student;
import com.xvchushun.service.StudentService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class StudentTest {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        StudentService studentService = (StudentService) applicationContext.getBean("studentServiceImpl");
        Student student = studentService.findStudentById(202009511);
        int id = studentService.findStudentId("小明");
        List<Repair> repairs = studentService.findRepairByStudentId(202009511);
        System.out.println(repairs);
        System.out.println(id);
        System.out.println(student);
    }

}
