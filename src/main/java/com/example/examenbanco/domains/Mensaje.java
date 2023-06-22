package com.example.examenbanco.domains;

public class Mensaje {
    long id;
    String mensaje;

    public Mensaje(long id, String mensaje) {
        this.id = id;
        this.mensaje = mensaje;
    }

    public Mensaje() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public String toString() {
        return "Mensaje{" +
                "id=" + id +
                ", mensaje='" + mensaje + '\'' +
                '}';
    }
}
