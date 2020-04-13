package Lab2IC_SDMC;

import Lab2IC_SDMC.domain.Nota;
import Lab2IC_SDMC.domain.Student;
import Lab2IC_SDMC.domain.Tema;
import Lab2IC_SDMC.repository.NotaXMLRepo;
import Lab2IC_SDMC.repository.StudentXMLRepo;
import Lab2IC_SDMC.repository.TemaXMLRepo;
import Lab2IC_SDMC.service.Service;
import Lab2IC_SDMC.validation.NotaValidator;
import Lab2IC_SDMC.validation.StudentValidator;
import Lab2IC_SDMC.validation.TemaValidator;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.Assert.assertEquals;

public class BigBangTest {
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
    public void addStudent() {
        Student student = new Student("113", "NumePrenume112", 934, "numeprenume112@email.com");
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
        service.deleteStudent("113");
        assertEquals(size + 1, sizeNew);
    }

    @Test
    public void addTema() {
        Tema tema = new Tema("10", "Tema nouaaaaa!!!", 9, 7);
        int size = 0;
        List<Tema> teme = StreamSupport.stream(service.getAllTeme().spliterator(), false)
                .collect(Collectors.toList());
        size = teme.size();

        service.addTema(tema);
        List<Tema> temeNew = StreamSupport.stream(service.getAllTeme().spliterator(), false)
                .collect(Collectors.toList());
        int sizeNew = temeNew.size();
        service.deleteTema("10");
        assertEquals(size + 1, sizeNew);
    }

    @Test
    public void addNota(){
        LocalDate date = LocalDate.of(2018, Month.OCTOBER, 11);
        Nota nota = new Nota("10", "1", "1", 10.0, date);
        int size = 0;
        List<Nota> note = StreamSupport.stream(service.getAllNote().spliterator(), false)
                .collect(Collectors.toList());
        size = note.size();

        service.addNota(nota, "Super");
        List<Nota> notaNew = StreamSupport.stream(service.getAllNote().spliterator(), false)
                .collect(Collectors.toList());
        int sizeNew = notaNew.size();
        service.deleteNota("10");
        assertEquals(size + 1, sizeNew);
    }

    @Test
    public void bigBang(){
        addStudent();
        addTema();
        addNota();
    }

}
