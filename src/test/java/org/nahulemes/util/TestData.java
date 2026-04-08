package org.nahulemes.util;

public final class TestData {

    // ── Base URL ────────────────────────────────────────────────────────────
    public static final String APP_URL           = "http://localhost:3000";

    // ── Rutas de la aplicación ──────────────────────────────────────────────
    public static final String LOGIN_PATH        = "/login";
    public static final String REGISTER_PATH     = "/register";
    public static final String ORDER_PATH        = "/";
    public static final String RESULTS_PATH      = "/results";
    public static final String CONFIRMATION_PATH = "/confirmation";
    public static final String HISTORY_PATH      = "/history";

    // ── Usuario de prueba (debe existir precreado en Docker) ────────────────
    public static final String TEST_USER_NAME     = "Usuario QA";
    public static final String TEST_USER_EMAIL    = "qa.usuario@example.com";
    public static final String TEST_USER_PASSWORD = "Password123";

    // ── Usuario secundario (para aislamiento de sesión) ─────────────────────
    public static final String TEST_USER_B_EMAIL    = "qa.usuariob@example.com";
    public static final String TEST_USER_B_PASSWORD = "Password123";
    public static final String TEST_USER_B_NAME     = "QA Usuario B";

    // ── Registro único (sufijo timestamp para evitar duplicados) ─────────────
    public static final String REGISTER_NAME          = "Usuario QA";
    public static final String REGISTER_EMAIL_PREFIX  = "qa.reg.";
    public static final String REGISTER_EMAIL_DOMAIN  = "@example.com";
    public static final String REGISTER_PASSWORD      = "Password123";

    // ── Datos de pedido ──────────────────────────────────────────────────────
    public static final String ORIGIN_SEARCH_TEXT      = "Bogotá";
    public static final String DESTINATION_SEARCH_TEXT = "Medellín";
    public static final String WEIGHT_VALUE            = "5";
    public static final String EXPECTED_COUNTRY        = "Colombia";

    // ── Autocompletado ───────────────────────────────────────────────────────
    public static final String AUTOCOMPLETE_QUERY      = "Bog";

    // ── Prioridades ──────────────────────────────────────────────────────────
    public static final String PRIORITY_COST = "COST";
    public static final String PRIORITY_TIME = "TIME";

    private TestData() {
    }
}
