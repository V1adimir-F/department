package com.department.proxy;

import com.department.dto.request.PaymentRequestDTO;
import com.department.dto.response.PaymentResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * @author Vladimir F. 20.09.2022 22:14
 */

@FeignClient(name = "cash", url = "${service.cash.url}")
public interface CashProxy {

    @PostMapping()
    PaymentResponseDTO createPayment(@RequestBody PaymentRequestDTO paymentRequestDTO);
}
