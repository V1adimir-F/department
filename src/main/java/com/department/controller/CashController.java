package com.department.controller;

import com.department.dto.request.PaymentRequestDTO;
import com.department.dto.response.PaymentResponseDTO;
import com.department.proxy.CashProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Vladimir F. 23.09.2022 16:28
 */

@RestController
public class CashController {

    private final CashProxy cashProxy;

    @Autowired
    public CashController(CashProxy cashProxy) {
        this.cashProxy = cashProxy;
    }

    @PostMapping("/payment")
    PaymentResponseDTO createPayment(@RequestBody PaymentRequestDTO paymentRequestDTO) {
        return cashProxy.createPayment(paymentRequestDTO);
    }
}
