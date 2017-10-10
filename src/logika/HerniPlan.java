package logika;

/**
 *  Class HerniPlan - třída představující mapu a stav adventury.
 * 
 *  Tato třída inicializuje prvky ze kterých se hra skládá:
 *  vytváří všechny prostory,
 *  propojuje je vzájemně pomocí východů 
 *  a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 *  vytvaří všechny věci,
 *  umisťuje je do mistnosti nebo do jiných veci(truhl),
 *  rozhoduje o jejích přenositelnosti a hmotnosti.
 *  
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova
 *@author     Eduard Zeldin
 *@version    pro školní rok 2016/2017
 *@version    6.5.2017
 */
public class HerniPlan {
    private Prostor aktualniProstor;
    private Prostor viteznyProstor;
    private Batoh batuzek;
    private Hra hra;
    private boolean kouzlo;
    private boolean pes;
    private boolean vez;
    private boolean vyhra; // Pouze pro testovací účely ve třídě HraTest
     /**
     *  Konstruktor který vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví halu.
     */
    public HerniPlan(Hra hra) {
        zalozProstoryHry();
        batuzek = new Batoh();
        this.hra = hra;
        kouzlo = true;
        pes = true;
        vez = true;
        vyhra = false;
    }
    /**
     *  Vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví domeček.
     */
    private void zalozProstoryHry() {
        // vytvářejí se jednotlivé prostory
        Prostor loznice = new Prostor("Loznice","Pánská ložnice Griffindoru");
        Prostor obyvak = new Prostor("Obyvak","Obývací místnost Griffindoru");
        Prostor patro1 = new Prostor("1.patro","První patro. Procházíté mimo školníka Filče a jeho kočky missis Noris");
        Prostor patro2 = new Prostor("2.patro","Druhé patro.Oooch, schodiště pořad mění směr");
        Prostor patro3 = new Prostor("3.patro","Třetí patro");
        Prostor komora = new Prostor("Komora","Komora. Vstup do další mistnosti je překrýt obrovským tříhlavým psem");
        Prostor vez = new Prostor("Vez","Věž.Prostor je plný létajících klíčů");
        Prostor podzemi = new Prostor ("Podzemi","Tajemná komnata Voldemorta. Voldemort vynucuje Vás podívat se do zrcadla. A zvedá kouzelnou svoji hůl!");
        
        
        // přiřazují se průchody mezi prostory (sousedící prostory)
        loznice.setVychod(obyvak);
        obyvak.setVychod(loznice);
        obyvak.setVychod(patro1);
        patro1.setVychod(obyvak);
        patro1.setVychod(patro2);
        patro2.setVychod(patro1);
        patro2.setVychod(patro3);
        patro3.setVychod(patro2);
        patro3.setVychod(komora);
        komora.setVychod(patro3);
        komora.setVychod(vez);
        vez.setVychod(komora);
        vez.setVychod(podzemi);
        
                
        aktualniProstor = obyvak;  // hra začíná v místnosti ložnice
        
        Vec koste = new Vec("letajici_koste",true,100);
        Vec gauc = new Vec("gauc",false,0);
        Vec skrinO = new Vec("skrin",false,0);
        Vec lektvarZ = new Vec("lektvar_zdravi",true,200);
        Vec lektvarM = new Vec("lektvar_moci",true,200);
        
        Vec skrinL = new Vec("skrin",false,0);
        Vec kouzelnaHul = new Vec("kouzelna_hul",true,100);
        Vec plast = new Vec("plast_neviditelnosti",true,100);
        Vec postel = new Vec("postel",false,0);
        Vec kufr = new Vec("kufr",false,0);
        
        Vec harfa = new Vec("harfa",false,0);
        
        Vec klic1 = new Vec("velky_letajici_klic",true,50);
        Vec klic2 = new Vec("stredni_letajici_klic",true,30);
        Vec klic3 = new Vec("maly_letajici_klic",true,20);
        
        Vec zrcadlo = new Vec ("zrcadlo",false,0);
        Vec kamen = new Vec("kamen_mudrcu",true,0);
        
        
        Vec boty = new Vec("kouzelne_boty",true,100);
        Vec sala = new Vec("nebelvirska_sala",true,100);
        Vec pero = new Vec ("pero",true,50);
        Vec svetr = new Vec ("svetr",true,100);
        Vec rukavice = new Vec ("rukavice",true,100);
        
        obyvak.vlozVec(gauc);
        obyvak.vlozVec(skrinO);
        obyvak.vlozVec(koste);
        
        loznice.vlozVec(postel);
        loznice.vlozVec(kufr);
        loznice.vlozVec(skrinL);
        
        komora.vlozVec(harfa);
        
        vez.vlozVec(klic1);
        vez.vlozVec(klic2);
        vez.vlozVec(klic3);
        
        podzemi.vlozVec(zrcadlo);
        
        
        skrinO.vlozVec(sala);
        skrinO.vlozVec(svetr);
        skrinO.vlozVec(lektvarZ);
        skrinO.vlozVec(lektvarM);
        
        skrinL.vlozVec(pero);
        skrinL.vlozVec(rukavice);
        skrinL.vlozVec(boty);
        skrinL.vlozVec(kouzelnaHul);
        kufr.vlozVec(plast);
        
        zrcadlo.vlozVec(kamen);
    }
    
    /**
     *  Metoda vrací odkaz na aktuální prostor, ve ktetém se hráč právě nachází.
     *
     *@return     aktuální prostor
     */
    
    public Prostor getAktualniProstor() {
        return aktualniProstor;

    }
    
    /**
     *  Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi prostory
     *
     *@param  prostor nový aktuální prostor
     */
    public void setAktualniProstor(Prostor prostor) {
       aktualniProstor = prostor;
    }
    // Metoda vrací batoh
    public Batoh getBatoh() 
    {
       return this.batuzek;
    }
    // Metoda vrací hru kterou hrajeme
    public Hra getHra()
    {
       return this.hra;
    }
    // Metoda otevírá místnost
    public void Caruj()
    {
        this.kouzlo = false;
    }
    // Metoda vrací stav zavřené místnosti
    public boolean getKouzlo()
    {
        return this.kouzlo;
    }
    // Metoda uspí psa
    public void Uspi()
    {
        this.pes = false;
    }
    // Metoda vrací stav psa
    public boolean getPes()
    {
        return this.pes;
    }
    
    // Metoda otvírá věž
    public void otevriVez()
    {
        this.vez = false;
    }
    // Metoda vrací stav věže
    public boolean getVez()
    {
        return this.vez;
    }
    
    
    // Následující dvě metody fungují pouze pro účely testování ve třídě HraTest abychom zjistili zda hra skončila výhrou, nebo ne.
    
    // Metoda nastavuje že jsme vyhráli
    public void setVyhra()
    {
        this.vyhra = true;
    }
    // Metoda vrací zda jsme na konci vyhráli ( true ) nebo prohráli ( false ) 
    public boolean getVyhra()
    {
        return this.vyhra;
    }
    }
