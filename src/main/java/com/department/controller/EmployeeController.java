package com.department.controller;

import com.department.dto.request.EmployeeRequestDTO;
import com.department.dto.response.AllEmployeeResponseDTO;
import com.department.dto.response.EmployeeResponseDTO;
import com.department.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;

/**
 * @author Vladimir F. 13.09.2022 13:40
 */

@RestController
@RequestMapping(value = "/department/employee")
@CrossOrigin(origins = "${client.host}")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * Returns response with list of employees on <code>pageNumber</code>
     * @param rankId filters employees by <code>rankId</code>
     * @param postId filters employees by <code>postId</code>
     * @param pageNumber number of page on client (pagination)
     * @param countOnPage count employees on page (pagination)
     * @return Response with list of employees and count of employees on all pages (pagination)
     */
    @GetMapping()
    public ResponseEntity<?> getAllOnPage(
            @RequestParam(name = "rankId", required = false) Long rankId,
            @RequestParam(name = "postId", required = false) Long postId,
            @RequestParam(name = "page", defaultValue = "0") Integer pageNumber,
            @RequestParam(name = "limit", defaultValue = "2") Integer countOnPage
    ) {
        AllEmployeeResponseDTO response = employeeService.getEmployeesOnPage(
                rankId,
                postId,
                pageNumber,
                countOnPage);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getOne(@PathVariable(name = "id") Long employeeId) {
        EmployeeResponseDTO employeeResponseDTO = employeeService.getOneEmployee(employeeId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(employeeResponseDTO);
    }

    @PostMapping()
    public ResponseEntity<?> add(@RequestBody EmployeeRequestDTO employeeRequestDTO) {
        EmployeeResponseDTO employeeResponseDTO = employeeService.createEmployee(employeeRequestDTO);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(employeeResponseDTO);
    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") Long employeeId,
                                    @RequestBody EmployeeRequestDTO employeeRequestDTO) {
        EmployeeResponseDTO employeeResponseDTO = employeeService.updateEmployee(employeeRequestDTO, employeeId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(employeeResponseDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long employeeId) {
        EmployeeResponseDTO employeeResponseDTO = employeeService.deleteEmployee(employeeId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(employeeResponseDTO);
    }
}
