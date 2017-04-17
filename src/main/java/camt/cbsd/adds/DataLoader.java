package camt.cbsd.adds;

import camt.cbsd.dao.CourseDao;
import camt.cbsd.dao.StudentDao;
import camt.cbsd.entity.Course;
import camt.cbsd.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

/**
 * Created by ariya_indhabhandhu on 10/4/2560.
 */
@ConfigurationProperties(prefix = "server")
@Component
public class DataLoader implements ApplicationRunner {
    StudentDao studentDao;
    @Autowired
    public void setStudentDao(StudentDao studentDao){
        this.studentDao=studentDao;
    }
    String baseUrl;
    String imageUrl;
    String imageBaseUrl;

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Autowired
    CourseDao courseDao;

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        imageBaseUrl = baseUrl + imageUrl;
        Student student1=Student.builder().studentId("SE-001")
                .name("Mitsuha").surname("Miyamizu").gpa(2.15)
                .image(imageBaseUrl + "mitsuha.jpg").feature(true).penAmount(0).description("The most beloved one").build();
        Student student2=Student.builder().studentId("SE-002")
                .name("Prayuth").surname("The minister").gpa(3.59)
                .image(imageBaseUrl + "tu.gif").feature(false).penAmount(15).description("The great man ever!!!!").build();
        Student student3=Student.builder().studentId("SE-003")
                .name("Jurgen").surname("Kloop").gpa(2.15)
                .image(imageBaseUrl + "Kloop.gif").feature(true).penAmount(2).description("The most beloved one").build();

        Course course1 =
                Course.builder().courseId("953331").courseName("CBSD").build();
        Course course2 =
                Course.builder().courseId("953323").courseName("Software Construction").build();
        Course course3 =
                Course.builder().courseId("953491").courseName("Software Project").build();

        courseDao.add(course1);
        courseDao.add(course2);
        courseDao.add(course3);
        studentDao.addStudent(student1);
        studentDao.addStudent(student2);
        studentDao.addStudent(student3);

        student1.addCourse(course1);
        student1.addCourse(course2);
        student2.addCourse(course2);
        student2.addCourse(course3);
        student3.addCourse(course1);
        student3.addCourse(course3);
    }
}
