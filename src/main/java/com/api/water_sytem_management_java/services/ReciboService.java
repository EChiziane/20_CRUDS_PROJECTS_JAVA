package com.api.water_sytem_management_java.services;

import com.api.water_sytem_management_java.controllers.dtos.ReciboInput;
import com.api.water_sytem_management_java.controllers.dtos.ReciboOutPut;
import com.api.water_sytem_management_java.models.*;
import com.api.water_sytem_management_java.repositories.PaymentRepository;
import com.api.water_sytem_management_java.repositories.ReciboRepository;
import com.api.water_sytem_management_java.repositories.StudentRepository;
import jakarta.transaction.Transactional;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ReciboService {

    private final StudentRepository studentRepository;
    private final ReciboRepository reciboRepository;
private final PaymentRepository paymentRepository;

    public ReciboService(StudentRepository studentRepository, ReciboRepository reciboRepository, PaymentRepository paymentRepository) {
        this.studentRepository = studentRepository;
        this.reciboRepository = reciboRepository;
        this.paymentRepository = paymentRepository;
    }

    public void imprimir(File file) throws IOException {
     //   Desktop desktop = Desktop.getDesktop();
    //    desktop.print(file); // Isso envia para a impressora padrão
    }


    public void atualizarStudentRecipt(UUID id) throws IOException {
    //    Student student = studentRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Student not found"));
        Payment payment = paymentRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Payment not found"));
        Customer customer= payment.getCustomer();

        File reciboPayment= atualizarReciboPayment(customer,payment);



    }



    public File atualizarReciboPayment(Customer customer, Payment payment) throws IOException {
        try (FileInputStream fis = new FileInputStream("templates/recibo_template.xlsx");
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);

            // Preencher informações do cliente e pagamento
            sheet.getRow(7).getCell(1).setCellValue(customer.getName());
            sheet.getRow(8).getCell(1).setCellValue(customer.getContact());
            sheet.getRow(9).getCell(1).setCellValue(payment.getCreatedAt()
                    .toLocalDate().toString());
            sheet.getRow(10).getCell(1).setCellValue(customer.getAddress());
            sheet.getRow(11).getCell(1).setCellValue(customer.getContact());
            sheet.getRow(12).getCell(1).setCellValue("cliente@email.com");

            // Informações da fatura/pagamento
            sheet.getRow(15).getCell(1).setCellValue(payment.getReferenceMonth());
            sheet.getRow(15).getCell(3).setCellValue(payment.getNumMonths());
            sheet.getRow(15).getCell(4).setCellValue(payment.getAmount());
            sheet.getRow(15).getCell(5).setCellValue(payment.getAmount());
            sheet.getRow(33).getCell(5).setCellValue(payment.getAmount());

            // Nome formatado com minutos
            String formattedName = customer.getName().replaceAll("\\s+", "_");
            String formattedDateTime = payment.getCreatedAt()
                    .format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm"));
            String fileName = String.format("Invoice_%s_%s.xlsx", formattedName, formattedDateTime);

            // Criar ficheiro
            File outputFile = new File("recibos/" + fileName);
            outputFile.getParentFile().mkdirs();
            try (FileOutputStream fos = new FileOutputStream(outputFile)) {
                workbook.write(fos);
            }

            return outputFile;
        }
    }





    public File atualizarRecibo(String nome, String endereco, String data) throws IOException {
        // Carregar o template
        FileInputStream fis = new FileInputStream("templates/recibo_template.xlsx");
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);

        // Atualizar as células específicas
        sheet.getRow(7).getCell(1).setCellValue(nome);      // B8 (linha 7, coluna 1)
        sheet.getRow(8).getCell(1).setCellValue(endereco);  // B9 (linha 8, coluna 1)
        sheet.getRow(9).getCell(1).setCellValue(data);      // B10 (linha 9, coluna 1)

        fis.close();

        // Criar novo arquivo atualizado
        File outputFile = new File("recibos/recibo_atualizado.xlsx");
        outputFile.getParentFile().mkdirs(); // Garantir pasta
        FileOutputStream fos = new FileOutputStream(outputFile);
        workbook.write(fos);
        fos.close();
        workbook.close();

        return outputFile;
    }

    @Transactional
    public Recibo createRecibo(Recibo recibo) {
        return reciboRepository.save(recibo);
    }

    public List<ReciboOutPut> getAllRecibos() {
        return reciboRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"))
                .stream()
                .map(Recibo::toReciboOutPut)
                .collect(Collectors.toList());
    }

    public void deleteRecibo(UUID id) {
        reciboRepository.deleteById(id);
    }

    @Transactional
    public Optional<ReciboOutPut> updateRecibo(UUID id, ReciboInput input) {
        return reciboRepository.findById(id)
                .map(existing -> {
                    Recibo updated = input.toRecibo();
                    updated.setId(existing.getId());
                    return reciboRepository.save(updated).toReciboOutPut();
                });
    }

}
