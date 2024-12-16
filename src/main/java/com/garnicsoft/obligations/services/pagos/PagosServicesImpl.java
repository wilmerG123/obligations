package com.garnicsoft.obligations.services.pagos;

import com.garnicsoft.obligations.models.entitys.Obligation;
import com.garnicsoft.obligations.models.entitys.Pago;
import com.garnicsoft.obligations.models.enums.Status;
import com.garnicsoft.obligations.repository.ObligationRepository;
import com.garnicsoft.obligations.repository.PagoRepository;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.stereotype.Service;


import java.io.ByteArrayOutputStream;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PagosServicesImpl implements PagoServices {

    private PagoRepository pagoRepository;
    private ObligationRepository obligationRepository;

    public PagosServicesImpl(PagoRepository pagoRepository, ObligationRepository obligationRepository) {
        this.pagoRepository = pagoRepository;
        this.obligationRepository = obligationRepository;
    }

    @Override
    public ByteArrayOutputStream createPago(Obligation obligation) {

        if (obligation.getId() != null) {
            Optional<Obligation> obligationToCancelled = obligationRepository.findById(obligation.getId());
            if (obligationToCancelled.isPresent()) {
                obligationToCancelled.get().setStatus(Status.CANCELADO);
                obligationRepository.save(obligationToCancelled.get());
                Pago pago = new Pago(obligationToCancelled.get());
                pagoRepository.save(pago);
                return createReciboPago(pago);
            }
        }
        return null;
    }

    @Override
    public Pago getPagoById(Long id) {
        return pagoRepository.findById(id).orElse(null);
    }

    private ByteArrayOutputStream createReciboPago(Pago pago) {
        // Crear un ByteArrayOutputStream para almacenar el PDF en memoria
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        // Crear el escritor PDF que escribe en el ByteArrayOutputStream
        PdfWriter writer = new PdfWriter(byteArrayOutputStream);

        // Crear el objeto PdfDocument usando el escritor
        PdfDocument pdfDocument = new PdfDocument(writer);

        // Crear el objeto Document, que es la representación de un documento PDF
        Document document = new Document(pdfDocument);

        // Agregar contenido al documento PDF (recibo de pago)
        document.add(new Paragraph("Recibo de Pago  " + pago.getId()));
        document.add(new Paragraph("Fecha: " + pago.getCancelledDate()));
        document.add(new Paragraph("Valor: $" + pago.getObligation().getValue()));
        document.add(new Paragraph("Nombre Pagador: " + pago.getObligation().getPlayer().getName() + " " + pago.getObligation().getPlayer().getLastName()));
        document.add(new Paragraph("Correo: " + pago.getObligation().getPlayer().getMail()));

        // Cerrar el documento
        document.close();

        // Retornar el PDF como un ByteArrayOutputStream
        return byteArrayOutputStream;
    }

    @Override
    public List<Pago> getAllPagos() {
        return pagoRepository.findAll();
    }

    @Override
    public List<Pago> getPagosByPlayerName(String playerName) {
        return pagoRepository.findByObligationPlayerName(playerName);
    }

    @Override
    public ByteArrayOutputStream createRecibo(Long id) {
        // Verificar si el id es nulo o inválido
        if (id == null) {
            throw new IllegalArgumentException("El ID del pago no puede ser nulo.");
        }
        // Consultar el pago en el repositorio, si no se encuentra lanzar una excepción o devolver un valor predeterminado
        Pago pago = pagoRepository.findById(id).orElse(null);
        // Verificar si el pago existe
        if (pago == null) {
            throw new IllegalArgumentException("El pago con el ID " + id + " no existe.");
        }
        // Si el pago es válido, proceder con la creación del recibo
        return createReciboPago(pago);
    }

    @Override
    public Pago editPago(Pago pago, Long id) {
        pago.setId(id);
        return pagoRepository.save(pago);
    }

    @Override
    public void deletePago(Long id) {
        pagoRepository.deleteById(id);
    }
}
