package test;

import com.jaume.penjat.Tauler;
import com.jaume.penjat.Puntuacio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class TaulerTest {

    private Tauler tauler;
    private Puntuacio puntuacio;

    @BeforeEach
    void reiniciar(){
        this.puntuacio = new Puntuacio();
        this.tauler = new Tauler();
        this.tauler.inicialitzarPartida("paraula", 4);
    }

    @Test
    void inicialitzarPartidaParaulaSecreta() {
        assertArrayEquals(new char[]{'p', 'a', 'r', 'a', 'u', 'l', 'a'}, this.tauler.getParaulaSecreta());
    }

    @Test
    void inicialitzarPartidaNombreIntents() {
        assertEquals(4, this.tauler.getIntents());
    }

    @Test
    void verificarEntradaIncorrecte() {
        assertEquals("Lletra incorrecte",this.tauler.verificar("ll"));
    }

    @Test
    void verificarEntradaCorrecteEncertat() {
        this.tauler.verificar("a");
        assertArrayEquals(new String[]{ null,"a",null,"a",null,null,"a" },this.tauler.getPalabraEndevinada());
    }

    @Test
    void verificarEntradaCorrecteErrada() {
        this.tauler.verificar("n");
        assertEquals(3,this.tauler.getIntents());
    }

    @Test
    void imprimirCapEncert() {
        assertEquals("_______", this.tauler.imprimir());
    }

    @Test
    void imprimirAmbLletres() {
        this.tauler.verificar("a");
        assertEquals("_a_a__a", this.tauler.imprimir());
    }

    @Test
    void imprimirTotEncertat() {
        this.tauler.verificar("a");
        this.tauler.verificar("p");
        this.tauler.verificar("r");
        this.tauler.verificar("u");
        this.tauler.verificar("l");
        assertEquals("paraula", this.tauler.imprimir());
    }



    @Test
    void imprimirVidesPlural() {
        assertEquals("Et queden 4 vides de 4", this.tauler.imprimirVides());
    }

    @Test
    void imprimirVidesSingular() {
        this.tauler.verificar("n");
        this.tauler.verificar("n");
        this.tauler.verificar("n");
        assertEquals("Et queda 1 vida de 4", this.tauler.imprimirVides());
    }

    @Test
    void restarIntents() {
        this.tauler.verificar("n");
        assertEquals(3, this.tauler.getIntents());
    }

    @Test
    void hasGuanyatTrue() {
        this.tauler.verificar("a");
        this.tauler.verificar("p");
        this.tauler.verificar("r");
        this.tauler.verificar("u");
        this.tauler.verificar("l");
        assertTrue(this.tauler.hasGuanyat());
    }

    @Test
    void hasGuanyatFalse() {
        assertFalse(this.tauler.hasGuanyat());
    }
    @Test
    void getIntents(){
        assertEquals(0,this.puntuacio.getIntents());
    }

    @Test
    void getTemps(){
        assertEquals(this.puntuacio.getTemps(),0);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void getParaulaSecretaDificultat(int number) {
        this.puntuacio.getParaulaSecretaDificultat(number);
        int resultat = 0;
        switch (number) {
            case 1: number = 1;
                resultat = 5;
                break;
            case 2: number = 2;
                resultat = 4;
                break;
            case 3: number = 3;
                resultat = 3;
                break;
            default:;
                break;
        }
    }

}
