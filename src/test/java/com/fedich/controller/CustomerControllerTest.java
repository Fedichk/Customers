package com.fedich.controller;

import com.fedich.model.Customer;
import com.fedich.repository.CustomerJPARepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerTest {

    @Mock
    private CustomerJPARepository customerDAO;

    @InjectMocks
    private CustomerController customerController;

    @Test
    public void testGetAll() throws Exception {
        List<Customer> customers = customerController.getAll();
        verify(customerDAO).findAll();
    }
}