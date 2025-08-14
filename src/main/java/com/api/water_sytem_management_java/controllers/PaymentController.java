package com.api.water_sytem_management_java.controllers;

import com.api.water_sytem_management_java.controllers.dtos.PaymentInput;
import com.api.water_sytem_management_java.controllers.dtos.PaymentOutput;
import com.api.water_sytem_management_java.controllers.dtos.StudentOutput;
import com.api.water_sytem_management_java.models.Customer;
import com.api.water_sytem_management_java.models.Payment;
import com.api.water_sytem_management_java.repositories.CustomerRepository;
import com.api.water_sytem_management_java.repositories.PaymentRepository;
import com.api.water_sytem_management_java.services.PaymentService;
import com.api.water_sytem_management_java.services.ReciboService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;
    private final CustomerRepository customerRepository;

    private final PaymentRepository paymentRepository;
    private final ReciboService reciboService;


    public PaymentController(PaymentService paymentService,
                             CustomerRepository customerRepository,

                             PaymentRepository paymentRepository, ReciboService reciboService) {
        this.paymentService = paymentService;
        this.customerRepository = customerRepository;

        this.paymentRepository = paymentRepository;
        this.reciboService = reciboService;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Payment> updatePayment(@PathVariable UUID id, @RequestBody PaymentInput paymentInput) {
        Optional<Customer> customerOptional = customerRepository.findById(paymentInput.customerId());
        if (customerOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Customer customer = customerOptional.get();

        Payment updatedPayment = paymentService.updatePayment(id, paymentInput, customer);
        return ResponseEntity.ok(updatedPayment);
    }


    @PostMapping
    public ResponseEntity<Payment> createPayment(@RequestBody PaymentInput paymentInput) throws IOException {
        Customer customer = customerRepository.findById(paymentInput.customerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Payment payment = paymentInput.toPayment(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentService.savePayment(payment));
    }


    @GetMapping("/invoice/{id}")
    ResponseEntity<StudentOutput> createPayment(@PathVariable UUID id) throws IOException {
        reciboService.atualizarStudentRecipt(id);
        return null;
    }


    @GetMapping("/recibo/{id}")
    ResponseEntity<StudentOutput> generateRecipt(@PathVariable UUID id) throws IOException {
        reciboService.atualizarStudentRecipt(id);
        //  reciboService.imprimir(recibo);
        return null;
    }

    @GetMapping
    public ResponseEntity<List<PaymentOutput>> getPayments() {
        return ResponseEntity.status(HttpStatus.OK).body(paymentService.getPayments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Payment>> getPaymentById(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(paymentService.getPaymentById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable UUID id) {
        paymentService.deletePayment(id);
        return ResponseEntity.noContent().build();
    }
}
