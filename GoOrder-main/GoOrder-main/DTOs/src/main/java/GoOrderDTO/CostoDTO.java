package GoOrderDTO;

public class CostoDTO {

    private double subtotal;
    private double iva;
    private double descuento;
    private double total;

    public CostoDTO(double subtotal, double iva, double descuento, double total) {
        this.subtotal = subtotal;
        this.iva = iva;
        this.descuento = descuento;
        this.total = total;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getIva() {
        return iva;
    }

    public double getDescuento() {
        return descuento;
    }

    public double getTotal() {
        return total;
    }
}
