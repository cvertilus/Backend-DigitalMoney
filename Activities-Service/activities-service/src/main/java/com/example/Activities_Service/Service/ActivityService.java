package com.example.Activities_Service.Service;

import com.example.Activities_Service.Model.Activity;
import com.example.Activities_Service.Model.ActivityRequest;
import com.example.Activities_Service.Repository.ActivityRepository;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Stream;



@Service
public class ActivityService {
    @Autowired
    private ActivityRepository activityRepository;

    public Activity saveActivity(String userId, ActivityRequest activityRequest){
        Activity activity = Activity.builder()
                .amount(activityRequest.getAmount())
                .type(activityRequest.getType())
                .name(activityRequest.getName())
                .userId(userId)
                .description(activityRequest.getDescription())
                .origin(activityRequest.getOrigin())
                .destination(activityRequest.getDestination())
                .build();

        return activityRepository.save(activity);
    }

    public Activity getActivityById(String id){
        return activityRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("El Actividad no exite "+id));
    }
    public List<Activity> getActivityByUserId(String userId){

        return activityRepository.findAllByUserId(userId);
    }

    public List<Activity> getActivityByDateRange(String start, String end, String userId) {
        return activityRepository.findAllByUserIdAndDateBetween(userId,LocalDateTime.parse(start), LocalDateTime.parse(end));
    }

    /**
     * Generates a PDF report of the last 10 activities.
     * @return byte array containing the PDF report.
     */
    public byte[] getActivityLast10(String userId){
        List<Activity> activities = activityRepository.findTop10AllByUserIdOrderByDateDesc(userId);
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        Document document = new Document(PageSize.A4.rotate());
        PdfWriter.getInstance(document, out);
        document.open();

        Font titleFont = new Font(Font.HELVETICA, 18, Font.BOLD);
        Font headerFont = new Font(Font.HELVETICA, 12, Font.BOLD);
        Font cellFont = new Font(Font.HELVETICA, 10);
        Color headerColor = new Color(190, 200, 255);

        document.add(new Paragraph("Reporte de Actividades Recientes", titleFont));
        document.add(Chunk.NEWLINE);

        PdfPTable table = new PdfPTable(8);
        table.setWidthPercentage(100);

        Stream.of("Fecha", "Usuario", "Nombre", "Monto", "Tipo", "Origen", "Destino", "Descripción")
                .forEach(col -> {
                    PdfPCell cell = new PdfPCell(new Phrase(col, headerFont));
                    cell.setBackgroundColor(new Color(230, 230, 250));
                    table.addCell(cell);
                });

        for (Activity act : activities) {
            table.addCell(new Phrase(formatDate(act.getDate()), cellFont));
            table.addCell(new Phrase(act.getUserId(), cellFont));
            table.addCell(new Phrase(act.getName(), cellFont));
            table.addCell(new Phrase(String.format("$ %.2f", act.getAmount()), cellFont));
            table.addCell(new Phrase(act.getType(), cellFont));
            table.addCell(new Phrase(nullSafe(act.getOrigin()), cellFont));
            table.addCell(new Phrase(nullSafe(act.getDestination()), cellFont));
            table.addCell(new Phrase(nullSafe(act.getDescription()), cellFont));
        }

        document.add(table);
        document.close();

        return out.toByteArray();
    }

    private String nullSafe(String value) {
        return value != null ? value : "N/A";
    }
    private String formatDate(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

}
