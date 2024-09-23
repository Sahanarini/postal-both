package com.postal.serviceimplementation;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.postal.hibrepoimplementation.UserRepoImp;
import com.postal.model.Mail;
import com.postal.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplementationTest {

    @InjectMocks
    private UserServiceImplementation userService;

    @Mock
    private UserRepoImp repo;

    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setUserId(1); // Set a sample ID
        user.setEmail("test@example.com"); // Set a sample email
        // Initialize other properties as needed
    }

    @Test
    void testAddUser() {
        doNothing().when(repo).addUser(any(User.class));

        userService.addUser(user);

        verify(repo, times(1)).addUser(any(User.class));
    }

    @Test
    void testDelUser() {
        doNothing().when(repo).delUser(1);

        userService.delUser(1);

        verify(repo, times(1)).delUser(1);
    }

    @Test
    void testUpdateUser() {
        doNothing().when(repo).updateUser(any(User.class));

        userService.updateUser(user);

        verify(repo, times(1)).updateUser(any(User.class));
    }

    @Test
    void testGetAllUsers() {
        List<User> userList = new ArrayList<>();
        userList.add(user);
        when(repo.getAllUsers()).thenReturn(userList);

        List<User> result = userService.getAllUsers();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(user.getUserId(), result.get(0).getUserId());
        verify(repo, times(1)).getAllUsers();
    }

    @Test
    void testFindById() {
        when(repo.findById(1)).thenReturn(user);

        User result = userService.findById(1);

        assertNotNull(result);
        assertEquals(user.getUserId(), result.getUserId());
        verify(repo, times(1)).findById(1);
    }

    @Test
    void testFindByEmail() {
        when(repo.findByEmail("test@example.com")).thenReturn(Optional.of(user));

        Optional<User> result = userService.findByEmail("test@example.com");

        assertTrue(result.isPresent());
        assertEquals(user.getUserId(), result.get().getUserId());
        verify(repo, times(1)).findByEmail("test@example.com");
    }

    @Test
    void testLogin_Success() {
        when(repo.Login("test@example.com", "password")).thenReturn(user);

        User result = userService.Login("test@example.com", "password");

        assertNotNull(result);
        assertEquals(user.getUserId(), result.getUserId());
        verify(repo, times(1)).Login("test@example.com", "password");
    }

    @Test
    void testLogin_Failure() {
        when(repo.Login("test@example.com", "wrongPassword")).thenThrow(new RuntimeException("Invalid credentials"));

        User result = userService.Login("test@example.com", "wrongPassword");

        assertNull(result);
        verify(repo, times(1)).Login("test@example.com", "wrongPassword");
    }

    @Test
    void testGetAllUserMails() {
        List<Mail> mailList = new ArrayList<>();
        when(repo.getAllUserMails(1)).thenReturn(mailList);

        List<Mail> result = userService.getAllUserMails(1);

        assertNotNull(result);
        assertEquals(mailList, result);
        verify(repo, times(1)).getAllUserMails(1);
    }
}
