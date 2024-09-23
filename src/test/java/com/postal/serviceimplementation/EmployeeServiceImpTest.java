package com.postal.serviceimplementation;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.postal.hibrepoimplementation.EmployeeRepoImp;
import com.postal.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceImpTest {

    @InjectMocks
    private EmployeeServiceImp employeeService;

    @Mock
    private EmployeeRepoImp repo;

    private Employee employee;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        employee = new Employee(); // Initialize your Employee object here
        employee.setEmpId(1); // Set the ID for testing
        // Set other properties of the Employee as needed
    }

    @Test
    void testAddEmp() {
        doNothing().when(repo).addEmp(any(Employee.class));

        employeeService.addEmp(employee);

        verify(repo, times(1)).addEmp(any(Employee.class));
    }

    @Test
    void testDelEmp() {
        doNothing().when(repo).delEmp(1);

        employeeService.delEmp(1);

        verify(repo, times(1)).delEmp(1);
    }

    @Test
    void testUpdateEmp() {
        doNothing().when(repo).updateEmp(any(Employee.class));

        employeeService.updateEmp(employee);

        verify(repo, times(1)).updateEmp(any(Employee.class));
    }

    @Test
    void testGetAllEmployee() {
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee);
        when(repo.getAllEmployee()).thenReturn(employeeList);

        List<Employee> result = employeeService.getAllEmployee();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(employee.getEmpId(), result.get(0).getEmpId());
        verify(repo, times(1)).getAllEmployee();
    }

    @Test
    void testFindById() {
        when(repo.findById(1)).thenReturn(employee);

        Employee result = employeeService.findById(1);

        assertNotNull(result);
        assertEquals(employee.getEmpId(), result.getEmpId());
        verify(repo, times(1)).findById(1);
    }

    @Test
    void testLogin() {
        String email = "test@example.com";
        String password = "password";

        when(repo.Login(email, password)).thenReturn(employee);

        Employee result = employeeService.Login(email, password);

        assertNotNull(result);
        assertEquals(employee.getEmpId(), result.getEmpId());
        verify(repo, times(1)).Login(email, password);
    }

    @Test
    void testAddEmpp() {
        doNothing().when(repo).addEmpp(anyString(), anyLong(), anyString(), anyInt(), any(LocalDate.class),
                any(LocalTime.class), anyString(), anyString(), anyInt());

        employeeService.addEmpp("John Doe", 1234567890L, "Some Area", 1, LocalDate.now(),
                LocalTime.now(), "john@example.com", "password", 123456);

        verify(repo, times(1)).addEmpp(anyString(), anyLong(), anyString(), anyInt(),
                any(LocalDate.class), any(LocalTime.class), anyString(), anyString(), anyInt());
    }

    @Test
    void testFetchAllEmp() {
        List<Integer> empIds = new ArrayList<>();
        empIds.add(1);
        when(repo.fetchAllEmp()).thenReturn(empIds);

        List<Integer> result = employeeService.fetchAllEmp();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1, result.get(0));
        verify(repo, times(1)).fetchAllEmp();
    }

    @Test
    void testGetEmployeeByPincode() {
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee);
        when(repo.getEmployeeByPincode(123456)).thenReturn(employeeList);

        List<Employee> result = employeeService.getEmployeeByPincode(123456);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(employee.getEmpId(), result.get(0).getEmpId());
        verify(repo, times(1)).getEmployeeByPincode(123456);
    }

    @Test
    void testGetEmployeesByToPincode() {
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee);
        when(repo.getEmployeesByToPincode(654321)).thenReturn(employeeList);

        List<Employee> result = employeeService.getEmployeesByToPincode(654321);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(employee.getEmpId(), result.get(0).getEmpId());
        verify(repo, times(1)).getEmployeesByToPincode(654321);
    }
}
