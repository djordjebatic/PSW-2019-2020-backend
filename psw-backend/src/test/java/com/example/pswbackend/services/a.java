/*package rs.ac.uns.ftn.kts.students.service;

import static org.assertj.core.api.Assertions.assertThat;
import static rs.ac.uns.ftn.kts.students.constants.StudentConstants.DB_CARD_NUMBER;
import static rs.ac.uns.ftn.kts.students.constants.StudentConstants.DB_COUNT;
import static rs.ac.uns.ftn.kts.students.constants.StudentConstants.DB_COUNT_WITH_LAST_NAME;
import static rs.ac.uns.ftn.kts.students.constants.StudentConstants.DB_FIRST_NAME;
import static rs.ac.uns.ftn.kts.students.constants.StudentConstants.DB_ID;
import static rs.ac.uns.ftn.kts.students.constants.StudentConstants.DB_ID_REFERENCED;
import static rs.ac.uns.ftn.kts.students.constants.StudentConstants.DB_LAST_NAME;
import static rs.ac.uns.ftn.kts.students.constants.StudentConstants.NEW_CARD_NUMBER;
import static rs.ac.uns.ftn.kts.students.constants.StudentConstants.NEW_FIRST_NAME;
import static rs.ac.uns.ftn.kts.students.constants.StudentConstants.NEW_LAST_NAME;
import static rs.ac.uns.ftn.kts.students.constants.StudentConstants.PAGE_SIZE;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import rs.ac.uns.ftn.kts.students.model.Student;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class StudentServiceTest {
    @Autowired
    StudentService studentService;

    @Test
    public void testFindAll() {
        List<Student> students = studentService.findAll();
        assertThat(students).hasSize(DB_COUNT);
    }

    @Test
    public void testFindAllPageable() {
        PageRequest pageRequest = PageRequest.of(1, PAGE_SIZE); //second page
        Page<Student> students = studentService.findAll(pageRequest);
        assertThat(students).hasSize(PAGE_SIZE);
    }

    @Test
    public void testFindOne() {
        Student dbStudent = studentService.findOne(DB_ID);
        assertThat(dbStudent).isNotNull();

        assertThat(dbStudent.getId()).isEqualTo(DB_ID);
        assertThat(dbStudent.getFirstName()).isEqualTo(DB_FIRST_NAME);
        assertThat(dbStudent.getLastName()).isEqualTo(DB_LAST_NAME);
        assertThat(dbStudent.getCardNumber()).isEqualTo(DB_CARD_NUMBER);
    }

    @Test
    @Transactional
    @Rollback(true) //it can be omitted because it is true by default
    public void testAdd() {
        Student student = new Student();
        student.setFirstName(NEW_FIRST_NAME);
        student.setLastName(NEW_LAST_NAME);
        student.setCardNumber(NEW_CARD_NUMBER);

        int dbSizeBeforeAdd = studentService.findAll().size();

        Student dbStudent = studentService.save(student);
        assertThat(dbStudent).isNotNull();

        // Validate that new student is in the database
        List<Student> students = studentService.findAll();
        assertThat(students).hasSize(dbSizeBeforeAdd + 1);
        dbStudent = students.get(students.size() - 1); //get last student
        assertThat(dbStudent.getFirstName()).isEqualTo(NEW_FIRST_NAME);
        assertThat(dbStudent.getLastName()).isEqualTo(NEW_LAST_NAME);
        assertThat(dbStudent.getCardNumber()).isEqualTo(NEW_CARD_NUMBER);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testUpdate() {
        Student dbStudent = studentService.findOne(DB_ID);

        dbStudent.setFirstName(NEW_FIRST_NAME);
        dbStudent.setLastName(NEW_LAST_NAME);
        dbStudent.setCardNumber(NEW_CARD_NUMBER);

        dbStudent = studentService.save(dbStudent);
        assertThat(dbStudent).isNotNull();

        //verify that database contains updated data
        dbStudent = studentService.findOne(DB_ID);
        assertThat(dbStudent.getFirstName()).isEqualTo(NEW_FIRST_NAME);
        assertThat(dbStudent.getLastName()).isEqualTo(NEW_LAST_NAME);
        assertThat(dbStudent.getCardNumber()).isEqualTo(NEW_CARD_NUMBER);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testRemove() {
        int dbSizeBeforeRemove = studentService.findAll().size();
        studentService.remove(DB_ID);

        List<Student> students = studentService.findAll();
        assertThat(students).hasSize(dbSizeBeforeRemove - 1);

        Student dbStudent = studentService.findOne(DB_ID);
        assertThat(dbStudent).isNull();
    }

    @Test
    public void testFindByCard() {
        Student dbStudent = studentService.findByCard(DB_CARD_NUMBER);
        assertThat(dbStudent).isNotNull();

        assertThat(dbStudent.getId()).isEqualTo(DB_ID);
        assertThat(dbStudent.getFirstName()).isEqualTo(DB_FIRST_NAME);
        assertThat(dbStudent.getLastName()).isEqualTo(DB_LAST_NAME);
        assertThat(dbStudent.getCardNumber()).isEqualTo(DB_CARD_NUMBER);
    }

    @Test
    public void testFindByLastName() {
        List<Student> students = studentService.findByLastName(DB_LAST_NAME);
        assertThat(students).hasSize(DB_COUNT_WITH_LAST_NAME);
    }

    //Negative tests


    @Test(expected = DataIntegrityViolationException.class)
    @Transactional
    @Rollback(true)
    public void testAddNonUniqueCardNumber() {
        Student student = new Student();
        student.setFirstName(NEW_FIRST_NAME);
        student.setLastName(NEW_LAST_NAME);
        student.setCardNumber(DB_CARD_NUMBER); //existing card number

        studentService.save(student);
    }

    @Test(expected = DataIntegrityViolationException.class)
    @Transactional
    @Rollback(true)
    public void testAddNullCardNumber() {
        Student student = new Student();
        student.setFirstName(NEW_FIRST_NAME);
        student.setLastName(NEW_LAST_NAME);

        studentService.save(student);
    }

    @Test(expected = DataIntegrityViolationException.class)
    @Transactional
    @Rollback(true)
    public void testRemoveNegative() {
        int dbSizeBeforeRemove = studentService.findAll().size();
        studentService.remove(DB_ID_REFERENCED);

        List<Student> students = studentService.findAll();
        assertThat(students).hasSize(dbSizeBeforeRemove - 1);

        Student dbStudent = studentService.findOne(DB_ID_REFERENCED);
        assertThat(dbStudent).isNull();
    }

    @Test
    public void testCalculateGrade() {
        //equivalence class partitioning
        assertThat(studentService.calculateGrade(97)).isEqualTo(10);
        assertThat(studentService.calculateGrade(92)).isEqualTo(9);
        assertThat(studentService.calculateGrade(78)).isEqualTo(8);
        assertThat(studentService.calculateGrade(71)).isEqualTo(7);
        assertThat(studentService.calculateGrade(63)).isEqualTo(6);
        assertThat(studentService.calculateGrade(43)).isEqualTo(5);

        //boundary value analysis
        assertThat(studentService.calculateGrade(95)).isEqualTo(10);
        assertThat(studentService.calculateGrade(94)).isEqualTo(9);

        assertThat(studentService.calculateGrade(85)).isEqualTo(9);
        assertThat(studentService.calculateGrade(84)).isEqualTo(8);

        assertThat(studentService.calculateGrade(75)).isEqualTo(8);
        assertThat(studentService.calculateGrade(74)).isEqualTo(7);

        assertThat(studentService.calculateGrade(65)).isEqualTo(7);
        assertThat(studentService.calculateGrade(64)).isEqualTo(6);

        assertThat(studentService.calculateGrade(55)).isEqualTo(6);
        assertThat(studentService.calculateGrade(54)).isEqualTo(5);
    }

    @Test(expected = NumberFormatException.class)
    public void testCalculateGradeNegative1() {
        //equivalence class partitioning
        studentService.calculateGrade(-17);
    }

    @Test(expected = NumberFormatException.class)
    public void testCalculateGradeNegative2() {
        //equivalence class partitioning
        studentService.calculateGrade(192);
    }
}
*/