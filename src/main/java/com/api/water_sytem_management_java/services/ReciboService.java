package com.api.water_sytem_management_java.services;

import com.api.water_sytem_management_java.models.Sprint;
import com.api.water_sytem_management_java.models.Student;
import com.api.water_sytem_management_java.repositories.StudentRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ReciboService {

    private final StudentRepository studentRepository;

    public ReciboService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void imprimir(File file) throws IOException {
     //   Desktop desktop = Desktop.getDesktop();
    //    desktop.print(file); // Isso envia para a impressora padrão
    }


    public void atualizarStudentRecipt(UUID id) throws IOException {
        Student student = studentRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Student not found"));
        File recibo = atualizarRecibo(
                student.getNome(),
                student.getEndereco()
                , LocalDateTime.now().toString());

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



    public File atualizarReciboPayment(String nome, String endereco, String data) throws IOException {
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

}
