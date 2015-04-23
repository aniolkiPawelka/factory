import java.util.*;

/* produkt koncowy */
class ZestawKomputerowy {
    private String monitor;
    private String procesor;
    private String grafika;
    private String ram;
    private String hdd;
    
    public void setMonitor(String monitor){
        this.monitor = monitor ; 
    }
    
    public void setProcesor(String procesor){
        this.procesor = procesor;
    }
    
    public void setGrafika(String grafika){
        this.grafika = grafika;
    }
    
    public void setRam(String ram){
        this.ram = ram;
    }
    
    public void setHdd(String hdd){
        this.hdd = hdd;
    }
    
    public void show(){
        if(monitor!=null) System.out.println("Monitor = " + monitor);
        if(procesor!=null) System.out.println("Procesor = " + procesor);
        if(grafika!=null) System.out.println("Grafika = " + grafika);
        if(ram!=null) System.out.println("RAM = " + ram);
        if(hdd!=null) System.out.println("HDD = " + hdd);
    }
}

/* nasz glowny interface */
abstract class Builder {
    protected ZestawKomputerowy zestawKomputerowy;
    
    public void newZestaw(){
        zestawKomputerowy = new ZestawKomputerowy();
    }
    
    public ZestawKomputerowy getZestaw(){
        return zestawKomputerowy;
    }
    
    public abstract void buildMonitor();
    public abstract void buildProcesor();
    public abstract void buildGrafika();
    public abstract void buildRam();
    public abstract void buildHdd();
}


class ZestawXT001 extends Builder {
    
   
    public void buildMonitor(){
        zestawKomputerowy.setMonitor("Benq 19");
    }
    
    public void buildProcesor(){
        zestawKomputerowy.setProcesor("amd");
    }

    public void buildGrafika(){
        zestawKomputerowy.setGrafika("ATI");
}

    public void buildRam(){
        zestawKomputerowy.setRam("DDR3");
}

    public void buildHdd(){
        
        Scanner in = new Scanner(System.in);
        
        int t;
        while(true){
            System.out.println("Dysk do wyboru: (1) Samsung, (2) Segate, (3) Caviar");
            t = in.nextInt();
            if(t>0 && t<4) break;
        }
        
        String wynik="";
        if(t==1) wynik = "Samsung";
        else if(t==2) wynik = "Segate";
        else if(t==3) wynik = "Caviar";
        
        zestawKomputerowy.setHdd(wynik);
    
}
}



class ZestawABC996 extends Builder {
    
    public void buildMonitor(){
        zestawKomputerowy.setMonitor("LG");
    }
    
    public void buildProcesor(){
        zestawKomputerowy.setProcesor("INTEL");
    }

    public void buildGrafika(){
        //zestaw nie obejmuje karty graficznej
}

    public void buildRam(){
        zestawKomputerowy.setRam("DDR");
}

    public void buildHdd(){
        zestawKomputerowy.setHdd("Samsung");
}
}


/* keirownik */
class Director {
    private Builder builder;
    
    public void setBuilder(Builder builder){
        this.builder = builder;
    }
    
    public ZestawKomputerowy getZestaw(){
        return builder.getZestaw();
    }
    
    public void skladaj(){
        builder.newZestaw();
        builder.buildMonitor();
        builder.buildProcesor();
        builder.buildHdd();
        builder.buildRam();
        builder.buildGrafika();
    }
}


public class Main {
    public static void main(String[]args){
        
        Director szef = new Director();
        Builder builder = new ZestawXT001();
        Builder builder2 = new ZestawABC996();
        
        System.out.println("\nZESTAW1");
        szef.setBuilder(builder);
        szef.skladaj();
        ZestawKomputerowy zestaw1 = szef.getZestaw();
        
        
        szef.setBuilder(builder2);
        szef.skladaj();
        ZestawKomputerowy zestaw2 = szef.getZestaw();
        
        
        zestaw1.show();
        System.out.println("\n\nZESTAW2");
        zestaw2.show();
    }
}