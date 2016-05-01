package ohtu;

import javax.swing.JTextField;

public abstract class Operaatio implements Komento {
    
    protected Sovelluslogiikka sovellus;
    protected JTextField tuloskentta;
    protected JTextField syotekentta;
    protected int edellinenTulos;
    
    public Operaatio(Sovelluslogiikka sovellus, JTextField tuloskentta, JTextField syotekentta){
        this.sovellus = sovellus;
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
    }
    
    protected int poimiSyoteArvo() {
        int luku = 0;
        try {
            luku = Integer.parseInt(syotekentta.getText());
        } catch (Exception exc) {
        }
        return luku;
    }
    
    private void poimiEdellinenTulos() {
        this.edellinenTulos = 0;
        try {
            this.edellinenTulos = Integer.parseInt(tuloskentta.getText());
        } catch (Exception exc) {
        }
    }
    
    protected void asetaTulosTeksti() {  
        poimiEdellinenTulos();
        int tulos = sovellus.tulos();
        syotekentta.setText("");
        tuloskentta.setText("" + tulos); 
    }
    
    @Override
    public void peru() {
        sovellus.palautaTulos(edellinenTulos);
        syotekentta.setText("");
        tuloskentta.setText("" + sovellus.tulos());
    }
}
