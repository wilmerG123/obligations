package com.garnicsoft.obligations.services.pagos;

import com.garnicsoft.obligations.models.entitys.Obligation;
import com.garnicsoft.obligations.models.entitys.Pago;

import java.io.ByteArrayOutputStream;
import java.util.List;

public interface PagoServices {

    public ByteArrayOutputStream createPago (Obligation obligation);
    public Pago getPagoById(Long id);
    public List<Pago> getAllPagos();
    public List<Pago> getPagosByPlayerName(String playerName);
    public ByteArrayOutputStream createRecibo (Long id);
    public Pago editPago (Pago pago, Long id);
    public void deletePago (Long id);
}
