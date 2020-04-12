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

    @Test
    public void addStudent3() {
        Student student = new Student("30", "NumePrenume03", 934, "numeprenume3@email.com");
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


        assertEquals(size + 1, sizeNew);
    }

    @Test
    public void addStudent4() {
        Student student = new Student("4", "NumePrenume04", -934, "nume4@email.com");
        int size = 0;
        List<Student> studenti = StreamSupport
                .stream(service.getAllStudenti().spliterator(), false)
                .collect(Collectors.toList());
        size = studenti.size();

        try {
            service.addStudent(student);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

        int sizeNew = 0;
        List<Student> studenti2 = StreamSupport
                .stream(service.getAllStudenti().spliterator(), false)
                .collect(Collectors.toList());
        sizeNew = studenti2.size();
        assertEquals(size, sizeNew);
    }

    @Test
    public void addStudent5() {
        Student student = new Student("51", "NumePrenume05", 934, "numeprenume5@email.com");
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

        assertEquals(size + 1, sizeNew);
    }

    @Test
    public void addStudent6() {
        Student student = new Student("6", "", 934, "nume@email.com");
        int size = 0;
        List<Student> studenti = StreamSupport
                .stream(service.getAllStudenti().spliterator(), false)
                .collect(Collectors.toList());
        size = studenti.size();

        try {
            service.addStudent(student);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

        int sizeNew = 0;
        List<Student> studenti2 = StreamSupport
                .stream(service.getAllStudenti().spliterator(), false)
                .collect(Collectors.toList());
        sizeNew = studenti2.size();
        assertEquals(size, sizeNew);
    }

    @Test
    public void addStudent7() {
        Student student = new Student("6", null, 934, "nume@email.com");
        int size = 0;
        List<Student> studenti = StreamSupport
                .stream(service.getAllStudenti().spliterator(), false)
                .collect(Collectors.toList());
        size = studenti.size();

        try {
            service.addStudent(student);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        int sizeNew = 0;
        List<Student> studenti2 = StreamSupport
                .stream(service.getAllStudenti().spliterator(), false)
                .collect(Collectors.toList());
        sizeNew = studenti2.size();
        assertEquals(size, sizeNew);
    }

    @Test
    public void addStudent8() {
        Student student = new Student("8", "NumePrenume08", 934, "numeprenume8@email.com");
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

        assertEquals(size + 1, sizeNew);
    }

    @Test
    public void addStudent9() {
        Student student = new Student("9", "Nume9", 934, "");
        int size = 0;
        List<Student> studenti = StreamSupport
                .stream(service.getAllStudenti().spliterator(), false)
                .collect(Collectors.toList());
        size = studenti.size();

        try {
            service.addStudent(student);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        int sizeNew = 0;
        List<Student> studenti2 = StreamSupport
                .stream(service.getAllStudenti().spliterator(), false)
                .collect(Collectors.toList());
        sizeNew = studenti2.size();
        assertEquals(size, sizeNew);
    }

    @Test
    public void addStudent10() {
        Student student = new Student("9", "Nume10", 934, null);
        int size = 0;
        List<Student> studenti = StreamSupport
                .stream(service.getAllStudenti().spliterator(), false)
                .collect(Collectors.toList());
        size = studenti.size();

        try {
            service.addStudent(student);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        int sizeNew = 0;
        List<Student> studenti2 = StreamSupport
                .stream(service.getAllStudenti().spliterator(), false)
                .collect(Collectors.toList());
        sizeNew = studenti2.size();
        assertEquals(size, sizeNew);
    }



    @Test(expected = ValidationException.class)
    public void addTema3() {
        Tema tema = new Tema("1234", "", 7, 6);
        int size = 0;
        List<Tema> teme = StreamSupport.stream(service.getAllTeme().spliterator(), false)
                .collect(Collectors.toList());
        size = teme.size();


        service.addTema(tema);
    }


    @Test(expected = ValidationException.class)
    public void addTema4() {
        Tema tema = new Tema("12345", "O tema frumuoasa", 0, 6);
        int size = 0;
        List<Tema> teme = StreamSupport.stream(service.getAllTeme().spliterator(), false)
                .collect(Collectors.toList());
        size = teme.size();


        service.addTema(tema);
    }


    @Test(expected = ValidationException.class)
    public void addTema5() {
        Tema tema = new Tema("12345", "O tema frumuoasa", 20, 6);
        int size = 0;
        List<Tema> teme = StreamSupport.stream(service.getAllTeme().spliterator(), false)
                .collect(Collectors.toList());
        size = teme.size();


        service.addTema(tema);
    }
    @Test(expected = ValidationException.class)
    public void addTema6() {
        Tema tema = new Tema("12345", "O tema frumuoasa", 1, 0);
        int size = 0;
        List<Tema> teme = StreamSupport.stream(service.getAllTeme().spliterator(), false)
                .collect(Collectors.toList());
        size = teme.size();


        service.addTema(tema);
    }


    @Test(expected = ValidationException.class)
    public void addTema7() {
        Tema tema = new Tema("12345", "O tema frumuoasa", 1, 20);
        int size = 0;
        List<Tema> teme = StreamSupport.stream(service.getAllTeme().spliterator(), false)
                .collect(Collectors.toList());
        size = teme.size();


        service.addTema(tema);
    }
}
