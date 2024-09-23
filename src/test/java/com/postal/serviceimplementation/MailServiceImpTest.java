package com.postal.serviceimplementation;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.postal.hibrepoimplementation.AddressRepoImp;
import com.postal.hibrepoimplementation.MailRepoImp;
import com.postal.hibrepoimplementation.UserRepoImp;
import com.postal.model.Address;
import com.postal.model.Employee;
import com.postal.model.Mail;
import com.postal.model.User;
import com.postal.repository.EmployeeRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MailServiceImpTest {

    @InjectMocks
    private MailServiceImp mailService;

    @Mock
    private MailRepoImp mailRepo;

    @Mock
    private EmployeeRepo employeeRepo;

    @Mock
    private UserRepoImp userRepo;

    @Mock
    private AddressRepoImp addressRepo;

    private Mail mail;
    private Employee employee;
    private User user;
    private Address address;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mail = new Mail();
        mail.setmId(1); // Set the ID for testing
        // Initialize other properties as needed

        employee = new Employee();
        employee.setEmpId(1);

        user = new User();
        user.setUserId(1);

        address = new Address();
        address.setId(1);
    }

    @Test
    void testDelMail() {
        doNothing().when(mailRepo).delMail(1);

        mailService.delMail(1);

        verify(mailRepo, times(1)).delMail(1);
    }

    @Test
    void testUpdateMail() {
        doNothing().when(mailRepo).updateMail(any(Mail.class));

        mailService.updateMail(mail);

        verify(mailRepo, times(1)).updateMail(any(Mail.class));
    }

    @Test
    void testGetAllMail() {
        List<Mail> mailList = new ArrayList<>();
        mailList.add(mail);
        when(mailRepo.getAllMail()).thenReturn(mailList);

        List<Mail> result = mailService.getAllMail();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(mail.getmId(), result.get(0).getmId());
        verify(mailRepo, times(1)).getAllMail();
    }

    @Test
    void testFindMailbyMailId() {
        when(mailRepo.findMailbyMailId(1)).thenReturn(mail);

        Mail result = mailService.findMailbyMailId(1);

        assertNotNull(result);
        assertEquals(mail.getmId(), result.getmId());
        verify(mailRepo, times(1)).findMailbyMailId(1);
    }

    @Test
    void testAddMal() {
        when(mailRepo.addMal(anyString(), anyString(), anyString(), any(LocalDate.class), 
                             anyInt(), anyInt(), anyInt(), anyInt(), 
                             anyInt(), anyInt(), anyString(), anyString(), 
                             anyString(), anyInt(), anyInt())).thenReturn(mail);

        Integer resultId = mailService.addMal("service", "articleType", "content", LocalDate.now(), 
                                              10, 5, 15, 10, 5, 100, "collectionDate", 
                                              "12:00", "status", 1, 1);

        assertNotNull(resultId);
        assertEquals(mail.getmId(), resultId);
        verify(mailRepo, times(1)).addMal(anyString(), anyString(), anyString(), any(LocalDate.class), 
                                           anyInt(), anyInt(), anyInt(), anyInt(), 
                                           anyInt(), anyInt(), anyString(), anyString(), 
                                           anyString(), anyInt(), anyInt());
    }

    @Test
    void testAssignEmployeeToMail() {
        when(mailRepo.findMailbyMailId(1)).thenReturn(mail);
        when(employeeRepo.findById(1)).thenReturn(employee);
        doNothing().when(mailRepo).updateMail(any(Mail.class));

        mailService.assignEmployeeToMail(1, 1);

        assertTrue(mail.getEmployees().contains(employee));
        verify(mailRepo, times(1)).updateMail(any(Mail.class));
    }

    @Test
    void testAssignEmployeeToMail_MailNotFound() {
        when(mailRepo.findMailbyMailId(1)).thenReturn(null);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            mailService.assignEmployeeToMail(1, 1);
        });

        assertEquals("Mail not found", exception.getMessage());
    }

    @Test
    void testAssignEmployeeToMail_EmployeeNotFound() {
        when(mailRepo.findMailbyMailId(1)).thenReturn(mail);
        when(employeeRepo.findById(1)).thenReturn(null);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            mailService.assignEmployeeToMail(1, 1);
        });

        assertEquals("Employee not found", exception.getMessage());
    }

    @Test
    void testUpdatestatus() {
        when(mailRepo.findMailbyMailId(1)).thenReturn(mail);
        doNothing().when(mailRepo).updateMail(any(Mail.class));

        boolean result = mailService.updatestatus(1, "Delivered");

        assertTrue(result);
        assertEquals("Delivered", mail.getStatus());
        verify(mailRepo, times(1)).updateMail(any(Mail.class));
    }

    @Test
    void testUpdatestatus_MailNotFound() {
        when(mailRepo.findMailbyMailId(1)).thenReturn(null);

        boolean result = mailService.updatestatus(1, "Delivered");

        assertFalse(result);
        verify(mailRepo, never()).updateMail(any(Mail.class));
    }
}
