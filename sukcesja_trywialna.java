import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import static java.lang.Math.*;
public class sukcesja_trywialna {
    public static void main(String[] args) {
        //populacja
        //podaj wielkosc tablicy n x m - ciag binarny
        int n = 10;
        double A = 10, w = 20*3.14;
        double[][] x_tab = {{-1,1}, {-2,2}, {-2,2}, {-1,1}, {-2,2}};
        double[] d_tab = {1, 2, 3, 6, 7};
        double[] Fx_wynik = new double[n];
        int ilosc_funkcji = d_tab.length;//ilosc funkcji
        double[] Fx = new double[n];
        for(int z = 0; z<n; z++){
            Fx[z] = 0;
        }
        //wielkosc calej populacji na podstawie podanych d
        int M = 0, MM = 0;
        double wynik_pomocniczny = 0;
        for (int i = 0; i < d_tab.length; i++) {
            wynik_pomocniczny = (x_tab[i][1] - x_tab[i][0])* pow(10,d_tab[i]) + 1;
            while(true) {
                if (pow(2, MM) < wynik_pomocniczny) {
                    MM++;
                    continue;
                } else if (pow(2, MM) > wynik_pomocniczny) {
                    M += MM;
                    break;
                }
            }
        }
        //pusta tablica wynikowa dla Fx
        String [] tab_laczona = new String [n];
        for(int i = 0; i<n; i++){
            tab_laczona[i]="";
        }
        double[] m = new double [ilosc_funkcji];
        for (int iteracja = 0; iteracja < ilosc_funkcji; iteracja++) {
            int ilosc_w_zbiorze = (int) ((x_tab[iteracja][1] - x_tab[iteracja][0]) * pow(10, d_tab[iteracja]) + 1);
            //wyliczenie m
            for (int mm = 0; mm < ilosc_w_zbiorze; mm++) {
                if (pow(2, mm) < ilosc_w_zbiorze)
                    continue;
                else if (pow(2, mm) > ilosc_w_zbiorze)
                    m[iteracja] = mm;
                break;
            }
            int[][] tab = new int[n][(int) m[iteracja]];
            double rand = random();
            int rand2;
            //tworzenie nowej populacji
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m[iteracja]; j++) {
                    rand2 = (int) (random() * 2) + 1;
                    if (rand2 == 2) {
                        rand2 -= 1;
                    } else {
                        rand2 -= 1;
                    }
                    tab[i][j] = rand2;
                    tab_laczona[i] += Integer.toString(rand2) + " ";
                }
            }
            //System.out.println("OCENA OSOBNIKÓW");
            //ocena osobników
            double x_prim = 0;
            double X = 0;
            for (int i = 0; i < n; i++) {
                for (int k = (int)m[iteracja] - 1; k >= 0; k--) //k - potega i miejsce jednoczesnie
                {
                    x_prim += tab[i][k] * pow(2, (int)m[iteracja] - k - 1);
                }
                X += x_tab[iteracja][0];
                X += ( ( (x_tab[iteracja][1] - x_tab[iteracja][0] ) * x_prim ) / (pow(2, ((int)m[iteracja]) ) - 1) );
                Fx_wynik[i] = 10 + pow(X, 2) - 10 * cos(w * X);
                Fx[i] += Fx_wynik[i];
                x_prim = 0;
                X = 0;
            }
        }
        System.out.println("\nPEŁNA POPULACJI O DŁUGOŚCI m = "+tab_laczona[0].replace(" ", "").length());
        for(int i = 0; i < n; i++){
            System.out.println((i+1)+".\t"+tab_laczona[i]);
        }
        System.out.println("\nOCENA WSZYSTKICH POPULACJI ");
        for(int i=0; i<n; i++){
            System.out.print((i+1)+".\tF(");
            for(int j = 1; j <= ilosc_funkcji; j++){
                if (j>1) {System.out.print(", x"+j);}
                else {System.out.print("x"+j);}
            }
            System.out.print(") = "+Fx[i]+"\n");
        }
        Random r = new Random();
        System.out.println();////////////////////////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("Algorytm genetyczny - SUKCESJA TRYWIALNA");
        for (int epoka = 1; epoka <= 10; epoka++) {
            System.out.println("EPOKA NR " + epoka);
            System.out.println("AKTUALNA POPULACJA NA POCZĄTKU EPOKI NR"+epoka);
            for(int i = 0; i < n; i++){
                System.out.println((i+1)+".\t"+tab_laczona[i].replace("", ""));
            }
            int zmienna_pomocnicza = 0, zmienna_pomocnicza2 = 0, nr = 0, nr2 = 0;
            System.out.println("\nMETODA TURNIEJOWA MAXIMUM\nPARY ");
            double[][] turniej_array = new double[n][2];
            int[] turniej_array_max = new int[n]; //tablica zapisujaca nr indeksow wygranych populacji
            //wypisanie utworzonych par MAXIMUM
            for (int j = 0; j < 10; j++) {
                zmienna_pomocnicza = r.nextInt(10);
                while (true) {
                    turniej_array[j][0] = zmienna_pomocnicza;
                    zmienna_pomocnicza = r.nextInt(10);
                    turniej_array[j][1] = zmienna_pomocnicza;
                    if (zmienna_pomocnicza == turniej_array[j][0]) {
                        zmienna_pomocnicza = r.nextInt(10);
                    } else
                        break;
                }
                zmienna_pomocnicza = (int) turniej_array[j][0];
                nr = zmienna_pomocnicza + 1;
                System.out.print(nr+".\t"+ tab_laczona[zmienna_pomocnicza].replace("", ""));
                System.out.println("\tFx = "+Fx[zmienna_pomocnicza]);
                zmienna_pomocnicza2 = (int) turniej_array[j][1];
                nr2 = zmienna_pomocnicza2 + 1;
                System.out.print(nr2+".\t"+ tab_laczona[zmienna_pomocnicza2].replace("", ""));
                System.out.println("\tFx = "+Fx[zmienna_pomocnicza2]);
                if (Fx[zmienna_pomocnicza] > Fx[zmienna_pomocnicza2]) {
                    System.out.println("Wygrywa osobnik\tnr " + nr);
                    turniej_array_max[j] = zmienna_pomocnicza;
                } else {
                    System.out.println("Wygrywa osobnik\tnr " + nr2);
                    turniej_array_max[j] = zmienna_pomocnicza2;
                }
                System.out.println();
            }
            System.out.println("MAXIMUM");
            for (int i = 0; i < n; i++) {
                tab_laczona[i] = tab_laczona[turniej_array_max[i]];
                System.out.println((turniej_array_max[i] + 1) + ".\t" + tab_laczona[turniej_array_max[i]].replace("", ""));
            }
            //tworzenie tablicy do wykonania na niej mutacji
            int zakres_m = tab_laczona[0].replace(" ", "").length();
            double[][] mutacja_array = new double[n][zakres_m];
            double pm = 0.2; //prawdopodobienstwo mutacji
            //wypelnienie macierzy z populacja ze String na double
            int[][] populacja_array = new int[n][zakres_m];
            int zmienna_pomocnicza_int = 0;
            for (int i = 0; i<n; i++){
                for (int j = 0; j<zakres_m; j++){
                    zmienna_pomocnicza_int = (int)(tab_laczona[i].replace(" ", "").charAt(j));
                    if(zmienna_pomocnicza_int == 48) zmienna_pomocnicza_int = 0;
                    else zmienna_pomocnicza_int = 1;
                    populacja_array[i][j] = zmienna_pomocnicza_int;
                }
            }
            //wypelnienie macierzy mutacji wartosciami z przedzialu [0,1]
            Random rzeczywista = new Random();
            double random_rzeczywista = 0;
            for (int i = 0; i<n; i++){
                for (int j = 0; j<zakres_m; j++){
                    random_rzeczywista = rzeczywista.nextDouble();
                    mutacja_array[i][j] = random_rzeczywista;
                    if (random_rzeczywista < pm){
                        //zamien wartosc z tej pozycji na przeciwna
                        if(populacja_array[i][j] == 0) populacja_array[i][j] = 1;
                        else populacja_array[i][j] = 0;
                    }
                }
            }
            double value = 0;
            //wypisanie nowej tablicy liczb rzeczywistych
            //System.out.println("Tablica mutacji wartości rzeczywistych z przedziału [0,1] przy pm = "+pm);
            for (int i = 0; i<n; i++){
                for (int j = 0; j<zakres_m; j++){
                    value = mutacja_array[i][j];
                    //zaokraglane wartosci do pierwszego miejsca po rpzecinku
                    //System.out.print(Math.round(value*10.0)/10.0+" ");
                    //zmieniamy wartosc w tablicy populacji
                }
                 //System.out.println();
            }
            System.out.println("\nPopulacja po dokonaniu mutacji");
            //wypisanie nowej populacji
            for (int i = 0; i<n; i++){
                System.out.print("\t");
                for (int j = 0; j<zakres_m; j++){
                    System.out.print(populacja_array[i][j]+" ");
                }
                System.out.println();
            }

            //INWERSJA
            System.out.println("INWERSJA");
            double[] r_chromosom = new double[n];
            int pkt_1 = 0, pkt_2 = 0;
            double prawdopodobienstwo_inwersji = 0.2; //prawdopodobienstwo mutacji
            String lancuch_binarny = "";
            for (int i = 0; i<n; i++){
                random_rzeczywista = rzeczywista.nextDouble();
                r_chromosom[i] = random_rzeczywista;
                System.out.print("\t");
                for (int j = 0; j<zakres_m; j++){
                    System.out.print(populacja_array[i][j]+" ");
                }
                System.out.print("\tr"+(i+1)+" = "+r_chromosom[i]);
                if(r_chromosom[i]<prawdopodobienstwo_inwersji){
                    pkt_2 = r.nextInt(zakres_m);
                    if (pkt_2 == 0) pkt_2++;
                    pkt_1 = r.nextInt(pkt_2);
                    for (int k = pkt_1; k<pkt_2; k++){
                        lancuch_binarny += populacja_array[i][k];
                    }
                    StringBuilder potomek = new StringBuilder(lancuch_binarny).reverse();//odwrócony string
                    System.out.print("\twarunek r<pi spełniony, pkty("+pkt_1+", "+pkt_2+")"+" potomek "+potomek);
                    int potomek_index = 0;
                    for (int k = pkt_1; k<pkt_2; k++){
                        zmienna_pomocnicza_int = (int)potomek.charAt(potomek_index);
                        if(zmienna_pomocnicza_int == 48) zmienna_pomocnicza_int = 0;
                        else zmienna_pomocnicza_int = 1;
                        populacja_array[i][k] = zmienna_pomocnicza_int;
                        potomek_index++;
                    }
                }
                lancuch_binarny = "";
                System.out.println();
            }
            System.out.println("POPULACJA PO DOKONANIU INWERSJI");
            for (int i = 0; i<n; i++){
                System.out.print("\t");
                for (int j = 0; j<zakres_m; j++){
                    System.out.print(populacja_array[i][j]+" ");
                }
                System.out.println();
            }
            System.out.println("KRZYŻOWANIEwybór osobników:");
            double pk = 0.5;
            double r_krzyzowanie[] = new double[n];
            zmienna_pomocnicza_int = 0;
            int[] wylosowane = new int[n];
            for (int i = 0; i<n; i++){
                System.out.print(i+1+".\t");
                for (int j = 0; j<zakres_m; j++){
                    System.out.print(populacja_array[i][j]+" ");
                }
                System.out.print("\t");
                r_krzyzowanie[i] = r.nextDouble();
                System.out.print(r_krzyzowanie[i]);
                if(r_krzyzowanie[i] <= pk){
                    wylosowane[i] = i+1;
                    System.out.print("\t<=\t"+pk);
                }
                else{
                    System.out.print("\t>\t"+pk);
                }
                System.out.println();
            }
            System.out.print("Wybrano: ");
            zmienna_pomocnicza_int = 0;
            zmienna_pomocnicza2 = 0;
            Arrays.sort(wylosowane);
            for (int i = 0; i<n; i++){
                if(wylosowane[i] != 0) {
                    if (zmienna_pomocnicza_int == 0)
                        System.out.print(wylosowane[i]);
                    else
                        System.out.print(", "+wylosowane[i]);
                    zmienna_pomocnicza_int += 1;
                }
            }
            int tab_wylosowanych[] = new int[zmienna_pomocnicza_int];
            int iteratorek = 0;
            for (int i = 0; i<n; i++){
                if(wylosowane[i] != 0) {
                    tab_wylosowanych[iteratorek] = wylosowane[i];
                    iteratorek++;
                }
            }
            zmienna_pomocnicza_int = 0;
            if (iteratorek%2 == 1) {
                int iteratorek2 = r.nextInt(iteratorek) + 1;
                System.out.print(", nieparzysta liczba indeksów więc usuwam losowy o nr " + iteratorek2 + "\n");
                tab_wylosowanych[iteratorek2 - 1] = 0;
                System.out.print("Wybrano: ");
                for (int i = 0; i<tab_wylosowanych.length; i++){
                    if(tab_wylosowanych[i] != 0) {
                        if (zmienna_pomocnicza_int == 0)
                            System.out.print(tab_wylosowanych[i]);
                        else
                            System.out.print(", "+tab_wylosowanych[i]);
                        zmienna_pomocnicza_int += 1;
                    }
                }
            }
            int rozmiar_par = 0;

            if(tab_wylosowanych.length %2 == 1)
                rozmiar_par = tab_wylosowanych.length - 1;
            else
                rozmiar_par = tab_wylosowanych.length;
            Integer[] indeksy = new Integer[rozmiar_par];
            zmienna_pomocnicza = 0;
            for (int i = 0; i<tab_wylosowanych.length; i++){
                if(tab_wylosowanych[i] != 0){
                    indeksy[zmienna_pomocnicza] = tab_wylosowanych[i];
                    zmienna_pomocnicza += 1;
                }
            }
            int pary[][] = new int[rozmiar_par/2][2];
            int populacja_array_klon[][] = new int[n][zakres_m];
            for (int i = 0; i<n; i++){
                for (int j = 0; j<zakres_m; j++){
                    populacja_array_klon[i][j] = populacja_array[i][j];
                }
            }
            ////////////////////////////JEDNOPUNKTOWE/////////////////////////////////////////////
            System.out.println("\n\nKrzyżowanie jednopunktowe");
            System.out.println("Łączenie w pary:");
            int punkt;
            //wrzucenie wartosci tabvlicy indeksow do listy i przesortowanie losowo z uzyciem shuffle
            List<Integer> intList = Arrays.asList(indeksy);
            Collections.shuffle(intList);
            //zamiana listy spowrotem na tablice Integerow
            intList.toArray(indeksy);
            for (int i = 0; i<rozmiar_par/2; i++){
                punkt = r.nextInt(zakres_m);
                if (punkt == 0) punkt++;
                pary[i][0] = indeksy[i];
                pary[i][1] = indeksy[rozmiar_par - 1 - i];
                ////////////////////rodzice
                System.out.println("Rodzice\t\tnr "+(i+1)+" (punkt "+punkt+")");
                System.out.print(pary[i][0]+". ");
                zmienna_pomocnicza_int = pary[i][0] - 1;
                for (int j = 0; j<zakres_m; j++){
                    System.out.print(populacja_array[zmienna_pomocnicza_int][j]+" ");
                    if(j == punkt-1)
                        System.out.print("| ");
                }
                System.out.println();
                System.out.print(pary[i][1]+". ");
                zmienna_pomocnicza_int = pary[i][1] - 1;
                for (int j = 0; j<zakres_m; j++){
                    System.out.print(populacja_array[zmienna_pomocnicza_int][j]+" ");
                    if(j == punkt-1)
                        System.out.print("| ");
                }
                ////////////////////potomkowie
                System.out.println("\nPotomkowie\tnr "+(i+1));
                System.out.print(pary[i][0]+". ");
                zmienna_pomocnicza_int = pary[i][0] - 1;
                for (int j = 0; j<zakres_m; j++){
                    if(j>punkt-1)
                        populacja_array_klon[zmienna_pomocnicza_int][j] = populacja_array[pary[i][1] - 1][j];
                    System.out.print(populacja_array_klon[zmienna_pomocnicza_int][j]+" ");
                    if(j == punkt-1) {
                        System.out.print("| ");
                    }
                }
                System.out.println();
                System.out.print(pary[i][1]+". ");
                zmienna_pomocnicza_int = pary[i][1] - 1;
                for (int j = 0; j<zakres_m; j++){
                    if(j>punkt-1)
                        populacja_array_klon[zmienna_pomocnicza_int][j] = populacja_array[pary[i][0] - 1][j];
                    System.out.print(populacja_array_klon[zmienna_pomocnicza_int][j]+" ");
                    if(j == punkt-1) {
                        System.out.print("| ");
                    }
                }
                System.out.println("\n");
            }
            //zapisanie krzyzowania do glownej populacji
            System.out.println("Populacja po krzyżowaniu jednopunktowym dla epoki NR "+epoka);
            for (int i = 0; i<n; i++){
                for (int j = 0; j<zakres_m; j++){
                    populacja_array[i][j] = populacja_array_klon[i][j];
                }
            }
            for (int i = 0; i<n; i++){
                Fx[i] = 0.0;
            }
            int dziesietna = 0;
            int zakresik_m = zakres_m - 1;
            int zakres_m_po_odliczeniu = zakres_m;
            int ilosc_funkcji2 = ilosc_funkcji - 1;
            int potega = 0;
            double wynik = 0;
            double xprim = 0;
            ///////////////OCENA POPULACJI KONCOWEJ
            for (int i = 0; i < n; i++) {
                zakres_m_po_odliczeniu = zakres_m;
                zakresik_m = zakres_m - 1;
                //System.out.println((i+1)+". ");
                for (int k = ilosc_funkcji - 1; k >= 0; k--) {
                    zakres_m_po_odliczeniu -= m[k];
                    //System.out.print("DLA FUKCJI NR "+k+" => "+m[k]+" NOWY ZAKRES = "+zakres_m_po_odliczeniu+" ");
                    while (true) {
                        if (zakresik_m == zakres_m_po_odliczeniu || zakresik_m == -1)
                            break;
                        else {
                            dziesietna += populacja_array[i][zakresik_m] * Math.pow(2, potega);
                            potega++;
                            //System.out.print(krzyzowanie_array_min[i][zakresik_m] + " ");
                            zakresik_m--;
                        }
                    }
                    xprim = x_tab[k][0] + (((x_tab[k][1] - x_tab[k][0]) * dziesietna) / (Math.pow(2, m[k]) - 1));
                    //System.out.print("\nDla funkcji"+ k+ " => a="+x_tab[k][0]+", b => "+x_tab[k][1]+", m = "+ m[k] +" x_k = "+xprim+"\n");
                    wynik = pow(xprim, 2) - 10 * cos(w * xprim);
                    ;
                    Fx[i] += 10 + wynik;
//                krzyzowanie_array_min_fx[i] = ocena_potomkow[i];
                    //System.out.print("dziesietna = " + dziesietna +" -> "+(wynik+10)+" -> fx = "+ocena_potomkow[i]);
                    dziesietna = 0;
                    wynik = 0;
                    potega = 0;
                    xprim = 0;
                    //System.out.println();
                }
            }
            //zresetowanie poczatkowatej tablicy, a nastepnie nadpisanie jej
            for (int i = 0; i<n; i++){
                tab_laczona[i] = "";
            }
            for (int i = 0; i<n; i++){
                System.out.print("\t");
                for (int j = 0; j<zakres_m; j++){
                    tab_laczona[i] += populacja_array[i][j];
                    //System.out.print(populacja_array[i][j]+" ");
                }
                System.out.print(tab_laczona[i].replace("", " "));
                System.out.print("\tFx = "+Fx[i]);
                System.out.println();
            }
        }
    }
}
