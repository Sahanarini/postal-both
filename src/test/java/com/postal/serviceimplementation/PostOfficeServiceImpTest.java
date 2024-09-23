package com.postal.serviceimplementation;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.postal.hibrepoimplementation.PostOfficeRepoImp;
import com.postal.model.PostOfficeHead;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PostOfficeServiceImpTest {

    @InjectMocks
    private PostOfficeServiceImp postOfficeService;

    @Mock
    private PostOfficeRepoImp repo;

    private PostOfficeHead postOfficeHead;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        postOfficeHead = new PostOfficeHead();
        postOfficeHead.setPincode(123456); // Set a sample pincode
        // Initialize other properties as needed
    }

    @Test
    void testAddPo() {
        doNothing().when(repo).addPo(any(PostOfficeHead.class));

        postOfficeService.addPo(postOfficeHead);

        verify(repo, times(1)).addPo(any(PostOfficeHead.class));
    }

    @Test
    void testDelPo() {
        doNothing().when(repo).delPo(123456);

        postOfficeService.delPo(123456);

        verify(repo, times(1)).delPo(123456);
    }

    @Test
    void testUpdatePo() {
        doNothing().when(repo).updatePo(any(PostOfficeHead.class));

        postOfficeService.updatePo(postOfficeHead);

        verify(repo, times(1)).updatePo(any(PostOfficeHead.class));
    }

    @Test
    void testGetAllPos() {
        List<PostOfficeHead> postOfficeList = new ArrayList<>();
        postOfficeList.add(postOfficeHead);
        when(repo.getAllPos()).thenReturn(postOfficeList);

        List<PostOfficeHead> result = postOfficeService.getAllPos();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(postOfficeHead.getPincode(), result.get(0).getPincode());
        verify(repo, times(1)).getAllPos();
    }

    @Test
    void testFindByPincode() {
        when(repo.findByPincode(123456)).thenReturn(postOfficeHead);

        PostOfficeHead result = postOfficeService.findByPincode(123456);

        assertNotNull(result);
        assertEquals(postOfficeHead.getPincode(), result.getPincode());
        verify(repo, times(1)).findByPincode(123456);
    }

    @Test
    void testFindByCity() {
        when(repo.findByCity("SampleCity")).thenReturn(Optional.of(postOfficeHead));

        Optional<PostOfficeHead> result = postOfficeService.findByCity("SampleCity");

        assertTrue(result.isPresent());
        assertEquals(postOfficeHead.getPincode(), result.get().getPincode());
        verify(repo, times(1)).findByCity("SampleCity");
    }

    @Test
    void testFindByState() {
        when(repo.findByState("SampleState")).thenReturn(Optional.of(postOfficeHead));

        Optional<PostOfficeHead> result = postOfficeService.findByState("SampleState");

        assertTrue(result.isPresent());
        assertEquals(postOfficeHead.getPincode(), result.get().getPincode());
        verify(repo, times(1)).findByState("SampleState");
    }

    @Test
    void testLogin_Success() {
        when(repo.Login(123456, "password")).thenReturn(postOfficeHead);

        PostOfficeHead result = postOfficeService.Login(123456, "password");

        assertNotNull(result);
        assertEquals(postOfficeHead.getPincode(), result.getPincode());
        verify(repo, times(1)).Login(123456, "password");
    }

    @Test
    void testLogin_Failure() {
        when(repo.Login(123456, "wrongPassword")).thenThrow(new RuntimeException("Invalid credentials"));

        PostOfficeHead result = postOfficeService.Login(123456, "wrongPassword");

        assertNull(result);
        verify(repo, times(1)).Login(123456, "wrongPassword");
    }
}
