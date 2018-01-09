package com.report;

import com.entity.InventoryState;
import com.google.common.base.Throwables;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import org.springframework.stereotype.Repository;
import java.io.*;
import java.util.List;

@Repository("reportProcessPDF")
public class ReportProcessPDF implements ReportProcess {

    private ReportProcessHTML reportProcessHTML = new ReportProcessHTML();

    @Override
    public void writeData(List<InventoryState> inventoryStates, File file) throws IOException, DocumentException {
        File htmlFile = reportProcessHTML.generateHTMLReport(inventoryStates);
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
        document.open();
        FileInputStream fio = new FileInputStream(htmlFile);
        try{
            XMLWorkerHelper.getInstance().parseXHtml(writer, document, fio);
        }catch (IOException e) {
            throw Throwables.propagate(e);
        }finally {
            fio.close();
            document.close();
        }
    }
}
