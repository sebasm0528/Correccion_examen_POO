public class Envio {
    private String guia;
    private String destinatario;
    private String ciudadDestino;
    private double pesoKg;

    public Envio(String guia, String destinatario, String ciudadDestino, double pesoKg) {
        this.guia = guia;
        this.destinatario = destinatario;
        this.ciudadDestino = ciudadDestino;
        setPesoKg(pesoKg);
    }

    public String getGuia() { return guia; }
    public void setGuia(String guia) { this.guia = guia; }

    public String getDestinatario() { return destinatario; }
    public void setDestinatario(String destinatario) { this.destinatario = destinatario; }

    public String getCiudadDestino() { return ciudadDestino; }
    public void setCiudadDestino(String ciudadDestino) { this.ciudadDestino = ciudadDestino; }

    public double getPesoKg() { return pesoKg; }


    public void setPesoKg(double pesoKg) {
        if (pesoKg > 0) {
            this.pesoKg = pesoKg;
        }
    }

    public int kilosFacturables() {
        int kilos = (int) pesoKg;
        if (pesoKg > kilos) {
            kilos = kilos + 1;
        }
        return kilos;
    }


    public double calcularCosto() {
        return kilosFacturables() * 2.00;
    }

    public String describir() {
        return "Guia: " + guia + " | Destinatario: " + destinatario
                + " | Ciudad: " + ciudadDestino + " | Peso: " + pesoKg + " kg";
    }
}
