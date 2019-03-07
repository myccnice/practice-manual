package com.myccnice.manual.pdf;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface PdfGenerator {

    void generatePdf(PDFGeneratorDto generator, HttpServletRequest request, HttpServletResponse response);

}