package domain.professional;

/**
 * Marca o salón que respalda a la profesional (branding y logo).
 */
public class Brand {

    private String name;
    private String logoUrl;

    /**
     * Crea una marca con nombre y logotipo públicos.
     *
     * @param name    nombre del salón o marca personal
     * @param logoUrl URL pública del logotipo
     */
    public Brand(String name, String logoUrl) {
        this.name = name;
        this.logoUrl = logoUrl;
    }

    /**
     * Constructor requerido por frameworks de persistencia.
     */
    public Brand() {}

    /**
     * @return nombre comercial
     */
    public String getName() {
        return name;
    }

    /**
     * @return URL del logo que se mostrará en la app
     */
    public String getLogoUrl() {
        return logoUrl;
    }
}

