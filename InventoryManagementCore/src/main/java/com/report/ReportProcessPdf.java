package com.report;

import com.entity.InventoryState;
import com.google.common.base.Throwables;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository("reportProcessPDF")
public class ReportProcessPdf implements ReportProcess {

  private ReportProcessHtml reportProcessHtml = new ReportProcessHtml();

  @Override
  public void writeData(List<InventoryState> inventoryStates,
                        File file) throws IOException, DocumentException {
    File htmlFile = reportProcessHtml.generateHtmlReport(inventoryStates);
    Document document = new Document();
    PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
    document.open();
    FileInputStream fio = new FileInputStream(htmlFile);
    try {
      XMLWorkerHelper.getInstance().parseXHtml(writer, document, fio);
    } catch (IOException e) {
      throw Throwables.propagate(e);
    } finally {
      fio.close();
      document.close();
    }
  }
}
