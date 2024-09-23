package com.postal.serviceimplementation;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.postal.hibrepoimplementation.AddressRepoImp;
import com.postal.model.Address;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class AddressServiceImpTest {

    @InjectMocks
    private AddressServiceImp addressService;

    @Mock
    private AddressRepoImp repo;

    private Address address;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        address = new Address(); // Initialize your Address object here
        address.setId(1); // Set the ID for testing
        // Set other properties of the Address as needed
    }

    @Test
    void testAddAddress() {
        when(repo.addAddress(any(Address.class))).thenReturn(address);

        Address result = addressService.addAddress(address);

        assertNotNull(result);
        assertEquals(address.getId(), result.getId());
        verify(repo, times(1)).addAddress(any(Address.class));
    }

    @Test
    void testUpdateAddress() {
        doNothing().when(repo).updateAddress(any(Address.class));

        addressService.updateAddress(address);

        verify(repo, times(1)).updateAddress(any(Address.class));
    }

    @Test
    void testFindByaddId() {
        when(repo.findByaddId(1)).thenReturn(address);

        Address result = addressService.findByaddId(1);

        assertNotNull(result);
        assertEquals(address.getId(), result.getId());
        verify(repo, times(1)).findByaddId(1);
    }
}
