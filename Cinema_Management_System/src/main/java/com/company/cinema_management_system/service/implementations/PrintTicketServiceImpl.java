package com.company.cinema_management_system.service.implementations;

import com.company.cinema_management_system.entity.Ticket;
import com.company.cinema_management_system.repository.TicketRepository;
import com.company.cinema_management_system.service.PrintTicketService;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


@Service
public class PrintTicketServiceImpl implements PrintTicketService {
    private List<Ticket> listTicket;
    private final TicketRepository ticketRepository;

    public PrintTicketServiceImpl(List<Ticket> listTicket, TicketRepository ticketRepository) {
        this.listTicket = listTicket;
        this.ticketRepository = ticketRepository;
    }

    @Override
    public Ticket addTicket(Ticket ticket) {
        Optional<Ticket> ticket1=ticketRepository.findById(ticket.getId());
        if (ticket1.isPresent()) {
            if (ticket1.get().isPaid()) {
                return ticketRepository.save(ticket);
            }
        }
        return null;

    }

    @Override
    public Ticket get(Integer id) {
        Optional<Ticket>optionalTicket=ticketRepository.findById(id);

        return optionalTicket.orElseGet(Ticket::new);
    }

    @Override
    public List<Ticket> getAll() {
        return ticketRepository.findAll();
    }




    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("Ticket ID", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Movie name", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Booking date", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Film date", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Session date", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Seat", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Paid", font));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table) {
        for (Ticket ticket : listTicket) {
            table.addCell(String.valueOf(ticket.getId()));
            table.addCell(ticket.getMovie().getName());
            table.addCell(String.valueOf(ticket.getBookingDate()));
            table.addCell(String.valueOf(ticket.getMovie().getBeginDate()));
            table.addCell(String.valueOf(ticket.getMovie().getSessionDate()));
            table.addCell(String.valueOf(ticket.getMovie().getSessionDate()));
            table.addCell(String.valueOf(ticket.getSeat()));
            table.addCell(String.valueOf(ticket.isPaid()));
        }
    }
@Override
    public void export(HttpServletResponse response) throws Exception, IOException {
        Document document = new Document(PageSize.A4.rotate());
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);

        Paragraph p = new Paragraph("Ticket", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(7);
        table.setWidthPercentage(100f);
        table.setWidths(new float[]{1.5f, 3.5f, 3.0f, 3.0f, 3.0f,3.0f,1.5f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();

    }
}
