package Lab2IC_SDMC;

import Lab2IC_SDMC.domain.Student;
import Lab2IC_SDMC.domain.Tema;
import Lab2IC_SDMC.repository.NotaXMLRepo;
import Lab2IC_SDMC.repository.StudentXMLRepo;
import Lab2IC_SDMC.repository.TemaXMLRepo;
import Lab2IC_SDMC.service.Service;
import Lab2IC_SDMC.validation.NotaValidator;
import Lab2IC_SDMC.validation.StudentValidator;
import Lab2IC_SDMC.validation.TemaValidator;
import Lab2IC_SDMC.validation.ValidationException;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.Assert.assertEquals;

public class ServiceTest {

    Service service;

    @Before
    public void runBeforeTestMethod() {
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        String filenameStudent = "fisiere/Studenti.xml";
        String filenameTema = "fisiere/Teme.xml";
        String filenameNota = "fisiere/Note.xml";
        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
        service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);
    }

    @Test
    public void addStudent1() {
        Student student = new Student("1123", "NumePrenume01", 934, "numeprenume@email.com");
        int size = 0;
        List<Student> studenti = StreamSupport
                .stream(service.getAllStudenti().spliterator(), false)
                .collect(Collectors.toList());
        size = studenti.size();

        service.addStudent(student);
        List<Student> studentiNew = StreamSupport
                .stream(service.getAllStudenti().spliterator(), false)
                .collect(Collectors.toList());
        int sizeNew = studentiNew.size();
        service.deleteStudent("1123");
        assertEquals(size + 1, sizeNew);

    }

    @Test
    public void addStudent2() {
        Student student = new Student("1", "NumePrenume02", 934, "nume@email.com");
        int size = 0;
        List<Student> studenti = StreamSupport
                .stream(service.getAllStudenti().spliterator(), false)
                .collect(Collectors.toList());
        size = studenti.size();

        service.addStudent(student);
        int sizeNew = 0;
        List<Student> studenti2 = StreamSupport
                .stream(service.getAllStudenti().spliterator(), false)
                .collect(Collectors.toList());
        sizeNew = studenti2.size();
        assertEquals(size, sizeNew);
    }
    @Test
    public void addTema1() {
        Tema tema = new Tema("123", "O tema frumoasa", 7, 6);
        int size = 0;
        List<Tema> teme = StreamSupport.stream(service.getAllTeme().spliterator(), false)
                .collect(Collectors.toList());
        size = teme.size();


        service.addTema(tema);
        List<Tema> temeNew = StreamSupport.stream(service.getAllTeme().spliterator(), false)
                .collect(Collectors.toList());
        int sizeNew = temeNew.size();
        service.deleteTema("123");
        assertEquals(size + 1, sizeNew);
    }

    @Test(expected = ValidationException.class)
    public void addTema2() {
        Tema tema = new Tema("", "O tema frumoasa", 7, 6);
        int size = 0;
        List<Tema> teme = StreamSupport.stream(service.getAllTeme().spliterator(), false)
                .collect(Collectors.toList());
        size = teme.size();


        service.addTema(tema);
    }
}
