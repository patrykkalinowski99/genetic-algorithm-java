import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import static java.lang.Math.*;
public class ranking_max_ruletka_3os {
    public static void main(String[] args)
    {
        //podaj wielkosc tablicy n x m - ciag binarny
        int n = 100;
        double A = 10, w = 20*3.14;
        double[][] x_tab = {{-1,1}, {-2,2}, {-3,3}};
        double[] d_tab = {1, 2, 3};
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
            //Dane wejściowe
//            System.out.println("Dane wejściowe dla funkcji:");
//            System.out.println("\t\tA = " + A);
//            System.out.println("\t\tw*pi = " + w);
//            System.out.println("\t\t(a,b) = (" + x_tab[iteracja][0] + ", " + x_tab[iteracja][1] + ")");
//            System.out.println("\t\td = " + d_tab[iteracja]);
//            System.out.println("\t\tm = " + m[iteracja]);
//            System.out.println("\t\tn = " + n);
            int[][] tab = new int[n][(int)m[iteracja]];
            double rand = random();
            int rand2;
            //tworzenie nowej populacji
            //System.out.println("Tworzenie nowej populacji:");
            for (int i = 0; i < n; i++) {
                //System.out.print("\t\t");

                for (int j = 0; j < m[iteracja]; j++) {
                    rand2 = (int) (random() * 2) + 1;
                    if (rand2 == 2) {
                        rand2 -= 1;
                    } else {
                        rand2 -= 1;
                    }
                    tab[i][j] = rand2;
                    tab_laczona[i] += Integer.toString(rand2)+" ";
                    //System.out.print(tab[i][j] + " ");

                }
                //System.out.println();
            }
            //System.out.println("OCENA OSOBNIKÓW");
            //ocena osobników
            double x_prim = 0;
            double X = 0;
            //double Fx_wynik = 0;
            for (int i = 0; i < n; i++) {
                for (int k = (int)m[iteracja] - 1; k >= 0; k--) //k - potega i miejsce jednoczesnie
                {
                    x_prim += tab[i][k] * pow(2, (int)m[iteracja] - k - 1);
                }
                X += x_tab[iteracja][0];
                X += ( ( (x_tab[iteracja][1] - x_tab[iteracja][0] ) * x_prim ) / (pow(2, ((int)m[iteracja]) ) - 1) );
                Fx_wynik[i] = 10 + pow(X, 2) - 10 * cos(w * X);
                Fx[i] += Fx_wynik[i];
                //System.out.print("\t\t" + x_prim + " -> x = " + X + " -> F(x"+(iteracja+1)+") = " + Fx_wynik[i]);
                //System.out.println("DODANO DO TABLICY WYNIKOWE FX[i] --------> "+Fx[i]);
                //System.out.println();
                x_prim = 0;
                X = 0;
                //Fx_wynik = 0;
            }

//            System.out.println("_____________________________________________________________________" +
//                    "_________________________________________________________________________________" +
//                    "____________________________________________________________________");
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
        int zmienna_pomocnicza = 0, zmienna_pomocnicza2 = 0, nr = 0, nr2 = 0, nr3 = 0, zmienna_pomocnicza3 = 0;
        System.out.println("\nMETODA TURNIEJOWA \nPARY ");
        double[][] turniej_array = new double[n][3];
        int[] turniej_array_max = new int [n]; //tablica zapisujaca nr indeksow wygranych populacji
        //wypisanie utworzonych par MAXIMUM
        for (int j = 0; j < n; j++) {
            zmienna_pomocnicza = r.nextInt(n);
            if (zmienna_pomocnicza < (n/2))
                zmienna_pomocnicza+=(n/2);

                turniej_array[j][0] = zmienna_pomocnicza;
                zmienna_pomocnicza = r.nextInt(zmienna_pomocnicza);
                if (zmienna_pomocnicza < (n/3))
                    zmienna_pomocnicza += (n/3);
                turniej_array[j][1] = zmienna_pomocnicza;
                zmienna_pomocnicza = r.nextInt(zmienna_pomocnicza);
                turniej_array[j][2] = zmienna_pomocnicza;

            zmienna_pomocnicza = (int)turniej_array[j][0];
            nr = zmienna_pomocnicza+1;
            System.out.println(nr+".\t"+ tab_laczona[zmienna_pomocnicza].replace(" ", "")+"\tFx = "+Fx[zmienna_pomocnicza]);
            zmienna_pomocnicza2 = (int)turniej_array[j][1];
            nr2 = zmienna_pomocnicza2+1;
            System.out.println(nr2+".\t"+ tab_laczona[zmienna_pomocnicza2].replace(" ", "")+"\tFx = "+Fx[zmienna_pomocnicza2]);
            zmienna_pomocnicza3 = (int)turniej_array[j][2];
            nr3 = zmienna_pomocnicza3+1;
            System.out.println(nr3+".\t"+ tab_laczona[zmienna_pomocnicza3].replace(" ", "")+"\tFx = "+Fx[zmienna_pomocnicza3]);

            if (Fx[zmienna_pomocnicza] > Fx[zmienna_pomocnicza2]) {
                if (Fx[zmienna_pomocnicza] > Fx[zmienna_pomocnicza3]){
                    System.out.println("Wygrywa osobnik\tnr " + nr + ". Fx = " + Fx[zmienna_pomocnicza]);
                    turniej_array_max[j] = zmienna_pomocnicza;
                }
                else {
                    System.out.println("Wygrywa osobnik\tnr " + nr3 + ". Fx = " + Fx[zmienna_pomocnicza3]);
                    turniej_array_max[j] = zmienna_pomocnicza3;
                }
            }
            else {
                if (Fx[zmienna_pomocnicza2] > Fx[zmienna_pomocnicza3]){
                    System.out.println("Wygrywa osobnik\tnr " + nr2 + ". Fx = " + Fx[zmienna_pomocnicza2]);
                    turniej_array_max[j] = zmienna_pomocnicza2;
                }
                else {
                    System.out.println("Wygrywa osobnik\tnr " + nr3 + ". Fx = " + Fx[zmienna_pomocnicza3]);
                    turniej_array_max[j] = zmienna_pomocnicza3;
                }
            }
            System.out.println();
        }
        double suma = 0;
        //System.out.println("MAXIMUM");
        for(int i = 0; i<n; i++){
            System.out.println((turniej_array_max[i]+1)+".\t"+tab_laczona[turniej_array_max[i]].replace(" ", "")+"\tFx = "+ Fx[turniej_array_max[i]]);
            suma += Fx[turniej_array_max[i]];
        }
        suma /= n;
        System.out.println("Srednia Fx = "+suma);
        /////////////////////////////////////////MINIMUM
        //System.out.println();
        for (int j = 0; j <n; j++) {
            zmienna_pomocnicza = r.nextInt(n);
            while(true) {
                turniej_array[j][0] = zmienna_pomocnicza;
                zmienna_pomocnicza = r.nextInt(n);
                turniej_array[j][1] = zmienna_pomocnicza;
                if (zmienna_pomocnicza == turniej_array[j][0]) {
                    zmienna_pomocnicza = r.nextInt(n);
                }
                else
                    break;
            }
            zmienna_pomocnicza = (int)turniej_array[j][0];
            nr = zmienna_pomocnicza+1;
            //System.out.println(nr+".\t"+ tab_laczona[zmienna_pomocniczna].replace(" ", "")+"\tFx = "+Fx[zmienna_pomocniczna]);
            zmienna_pomocnicza2 = (int)turniej_array[j][1];
            nr2 = zmienna_pomocnicza2+1;
            //System.out.println(nr2+".\t"+ tab_laczona[zmienna_pomocnicza2].replace(" ", "")+"\tFx = "+Fx[zmienna_pomocnicza2]);
            if (Fx[zmienna_pomocnicza] < Fx[zmienna_pomocnicza2]) {
                //System.out.println("Wygrywa osobnik\tnr " + nr + ". Fx = " + Fx[zmienna_pomocniczna]);
                turniej_array_max[j] = zmienna_pomocnicza;
            }
            else{
                //System.out.println("Wygrywa osobnik\tnr " + nr2 + ". Fx = " + Fx[zmienna_pomocnicza2]);
                turniej_array_max[j] = zmienna_pomocnicza2;
            }
            //System.out.println();
        }
        //System.out.println("MINIMUM");
        for(int i = 0; i<n; i++){
            //System.out.println((turniej_array_max[i]+1)+".\t"+tab_laczona[turniej_array_max[i]].replace(" ", "")+"\tFx = "+ Fx[turniej_array_max[i]]);
        }
        //METODA RANKINGOWA SORTOWANIE MINIMUM
        //System.out.println("\nMETODA RANKINGOWA");
        double[][] ranking_array = new double[n][2]; //dwuwymiarowa tablica, pierwsza kolumna nr populacji, druga wartosc populacji
        double[] ranking_fx = new double[n];
        double[][] ranking_array_min = new double[n][2];
        double[][] ranking_array_max = new double[n][2];
        for (int i = 0; i<n;i++){
            //turniej_array_max[i] - pozycja wartosci Fx, Fx[turniej_array_max[i]] na podstawie pozycji wartosci
            ranking_array[i][0] = turniej_array_max[i];
            ranking_array[i][1] = Fx[turniej_array_max[i]];
            ranking_fx[i] =  Fx[turniej_array_max[i]];
        }
        //sortowanie rosnaco
        Arrays.sort(ranking_fx);//posortowanie samych wartosci Fx
        //przyporzadkowanie odpowiednich numerow populacji zaleznytch od wartosci fx
        for(int i = 0; i<n;i++){
            ranking_array_min[i][1] = ranking_fx[i];
            for(int j = 0; j<n; j++){
                if (ranking_fx[i] == ranking_array[j][1]){
                    ranking_array_min[i][0] = ranking_array[j][0];
                }
            }
        }
        //odwrocenie tablicy minimum w celu uzyskania maximum
        int z = 0;
        for(int i = n-1; i>=0;i--){
            ranking_array_max[i][0] = ranking_array_min[z][0];
            ranking_array_max[i][1] = ranking_array_min[z][1];
            z++;
        }
        //wypisanie posortowanej populacji rosnąco
        //System.out.println("Uporządkowana populacja rosnąco - MIN");
        for(int i = 0; i<n; i++){
            //System.out.println((int)(ranking_array_min[i][0]+1)+".\t"+tab_laczona[(int)ranking_array_min[i][0]].replace(" ", "")+"\tFx = "+ranking_array_min[i][1]);
        }
        //System.out.println("Nowa populacja na przykładzie minimum");
        double ranking_array_min_random[][] = new double [n][2];
        Random random2 = new Random();
        for(int i = 0; i<n; i++){
            zmienna_pomocnicza2 = random2.nextInt(n-1)+1;//unikniecie wylosowania 0
            zmienna_pomocnicza2 = random2.nextInt(zmienna_pomocnicza2);
            ranking_array_min_random[i][0] = ranking_array_min[zmienna_pomocnicza2][0];
            ranking_array_min_random[i][1] = ranking_array_min[zmienna_pomocnicza2][1];
        }
        for(int i = 0; i<n; i++){
            //System.out.println((int)(ranking_array_min_random[i][0]+1)+".\t"+tab_laczona[(int)ranking_array_min_random[i][0]].replace(" ", "")+"\tFx = "+ranking_array_min_random[i][1]);
        }
//        System.out.println("\nUporządkowana populacja malejąco - MAX");
        for(int i = 0; i<n; i++){
//            System.out.println((int)(ranking_array_max[i][0]+1)+".\t"+tab_laczona[(int)ranking_array_max[i][0]].replace(" ", "")+"\tFx = "+ranking_array_max[i][1]);
        }
//        System.out.println("Nowa populacja na przykładzie maximum");
        suma = 0;
        double ranking_array_max_random[][] = new double [n][2];
        for(int i = 0; i<n; i++){
            zmienna_pomocnicza2 = random2.nextInt(n-1)+1;//unikniecie wylosowania 0
            zmienna_pomocnicza2 = random2.nextInt(zmienna_pomocnicza2);
            ranking_array_max_random[i][0] = ranking_array_max[zmienna_pomocnicza2][0];
            ranking_array_max_random[i][1] = ranking_array_max[zmienna_pomocnicza2][1];
        }
        for(int i = 0; i<n; i++){
//            System.out.println((int)(ranking_array_max_random[i][0]+1)+".\t"+tab_laczona[(int)ranking_array_max_random[i][0]].replace(" ", "")+"\tFx = "+ranking_array_max_random[i][1]);
            suma += ranking_array_max_random[i][1];
        }
        suma /= n;
//        System.out.println("Srednia Fx = "+suma);
        //metoda ruletki
//        System.out.println("\nMETODA RULETKI - na przykladzie populacji funkcji Rastringa");
        double fx_dopasowanie = 0; //SUMA
        for(int i = 0; i<n; i++){
            fx_dopasowanie += Fx[i];
        }
        double[] pi = new double[n];
        double q = 0;
        double[] qi = new double[n];
        double[] r_tab = new double[n];
        Random rr = new Random();
        for(int i = 0; i<n; i++){
            r_tab[i] = rr.nextDouble();
            pi[i] = Fx[i]/fx_dopasowanie;
            q += pi[i];
            qi[i] = q;
//            System.out.println((i+1)+".\t"+tab_laczona[i].replace(" ", "")+"\t Fx = "+Fx[i]+"\t\t p"+(i+1)+" = "+pi[i]+"\t q"+(i+1)+" = "+qi[i]+"\t\t r"+(i+1)+" = "+r_tab[i]);
        }
//        System.out.println("\t\t\t Suma: "+fx_dopasowanie+"\n");
        fx_dopasowanie /= n;
//        System.out.println("\t\t\t Srednia: "+fx_dopasowanie+"\n");
        String result = "";
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++) {
                if (r_tab[i] <= qi[j]){
//                    System.out.print("r"+(i+1));
                    result = String.format("%.15f", r_tab[i]);
//                    System.out.print("\t=\t"+result);
//                    System.out.print("\t\tq" + (j) + " < r" + (i + 1) + " <= q" + (j + 1));
                    result = String.format("%.15f", Fx[i]);
//                    System.out.print("\t\t"+(i+1)+".\t"+tab_laczona[i].replace(" ", "")+"\t Fx = "+result);
//                    System.out.println();
                    break;
                }
            }
        }
        //MUTACJA
        //System.out.print("\nMUTACJA\n");
        int zakres_m = tab_laczona[0].replace(" ", "").length();
        double[][] mutacja_array = new double[n][zakres_m];
        double pm = 0.2; //prawdopodobienstwo mutacji
        //System.out.println("Populacja wielkości n = "+n+" i m = "+tab_laczona[0].replace(" ", "").length());
        for (int i = 0; i<n; i++){
//            tab_laczona[0].replace(" ", "").length()
            //System.out.println(tab_laczona[i]);
        }
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
        //System.out.println("Tablica mutacji wartości rzeczywistych z przedziału [0,1]");
        for (int i = 0; i<n; i++){
            for (int j = 0; j<zakres_m; j++){
                value = mutacja_array[i][j];
                //System.out.print(Math.round(value * 100.0)/100.0+" ");
                //zmieniamy wartosc w tablicy populacji
            }
            // System.out.println();
        }
        // System.out.println("\nPopulacja po dokonaniu mutacji");
        //wypisanie nowej populacji
        for (int i = 0; i<n; i++){
            for (int j = 0; j<zakres_m; j++){
                //   System.out.print(populacja_array[i][j]+" ");
            }
            //  System.out.println();
        }
        //INWERSJA
        //System.out.println("\nINWERSJA\n");
        double[] r_chromosom = new double[n];
        int pkt_1 = 0, pkt_2 = 0;
        double prawdopodobienstwo_inwersji = 0.2; //prawdopodobienstwo mutacji
        String lancuch_binarny = "";
        for (int i = 0; i<n; i++){
            random_rzeczywista = rzeczywista.nextDouble();
            r_chromosom[i] = random_rzeczywista;
            for (int j = 0; j<zakres_m; j++){
                // System.out.print(populacja_array[i][j]+" ");
            }
            // System.out.print("\tr"+(i+1)+" = "+r_chromosom[i]);
            if(r_chromosom[i]<prawdopodobienstwo_inwersji){
                pkt_2 = r.nextInt(zakres_m);
                if (pkt_2 == 0) pkt_2++;
                pkt_1 = r.nextInt(pkt_2);
                for (int k = pkt_1; k<pkt_2; k++){
                    lancuch_binarny += populacja_array[i][k];
                }
                StringBuilder potomek = new StringBuilder(lancuch_binarny).reverse();//odwrócony string
                // System.out.print("\twarunek r<pi spełniony, pkty("+pkt_1+", "+pkt_2+")"+" potomek "+potomek);
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
            //System.out.println();
        }
        //System.out.println("\nPOPULACJA PO DOKONANIU INWERSJI\n");
        for (int i = 0; i<n; i++){
            for (int j = 0; j<zakres_m; j++){
                //System.out.print(populacja_array[i][j]+" ");
            }
            //System.out.println();
        }
//        System.out.println("\nKRZYŻOWANIE\nwybór osobników:");
        double pk = 0.5;
        double r_krzyzowanie[] = new double[n];
        zmienna_pomocnicza_int = 0;
        int[] wylosowane = new int[n];
        for (int i = 0; i<n; i++){
//            System.out.print(i+1+".\t");
            for (int j = 0; j<zakres_m; j++){
//                System.out.print(populacja_array[i][j]+" ");
            }
//            System.out.print("\t");
            r_krzyzowanie[i] = r.nextDouble();
//            System.out.print(r_krzyzowanie[i]);
            if(r_krzyzowanie[i] <= pk){
                wylosowane[i] = i+1;
//                System.out.print("\t<=\t"+pk);
            }
//            else{
//                System.out.print("\t>\t"+pk);
//            }
//            System.out.println();
        }
//        System.out.print("Wybrano: ");
        zmienna_pomocnicza_int = 0;
        zmienna_pomocnicza2 = 0;
        Arrays.sort(wylosowane);
        for (int i = 0; i<n; i++){
            if(wylosowane[i] != 0) {
//                if (zmienna_pomocnicza_int == 0)
//                    System.out.print(wylosowane[i]);
//                else
//                    System.out.print(", "+wylosowane[i]);
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
//            System.out.print(", nieparzysta liczba indeksów więc usuwam losowy o nr " + iteratorek2 + "\n");
            tab_wylosowanych[iteratorek2 - 1] = 0;
//            System.out.print("Wybrano: ");
            for (int i = 0; i<tab_wylosowanych.length; i++){
                if(tab_wylosowanych[i] != 0) {
//                    if (zmienna_pomocnicza_int == 0)
//                        System.out.print(tab_wylosowanych[i]);
//                    else
//                        System.out.print(", "+tab_wylosowanych[i]);
                    zmienna_pomocnicza_int += 1;
                }
            }
        }
        int rozmiar_par = 0;

        if(tab_wylosowanych.length %2 == 1)
            rozmiar_par = tab_wylosowanych.length - 1;
        else
            rozmiar_par = tab_wylosowanych.length;
        int[] indeksy = new int[rozmiar_par];
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
//        System.out.println("\n\nKrzyżowanie jednopunktowe");
//        System.out.println("Łączenie w pary:");
        int punkt;
        for (int i = 0; i<rozmiar_par/2; i++){
            punkt = r.nextInt(zakres_m);
            if (punkt == 0) punkt++;
            pary[i][0] = indeksy[i];
            pary[i][1] = indeksy[rozmiar_par - 1 - i];
            ////////////////////rodzice
//            System.out.println("Rodzice\t\tnr "+(i+1)+" (punkt "+punkt+")");
//            System.out.print(pary[i][0]+". ");
            zmienna_pomocnicza_int = pary[i][0] - 1;
            for (int j = 0; j<zakres_m; j++){
//                System.out.print(populacja_array[zmienna_pomocnicza_int][j]+" ");
//                if(j == punkt-1)
//                    System.out.print("| ");
            }
//            System.out.println();
//            System.out.print(pary[i][1]+". ");
            zmienna_pomocnicza_int = pary[i][1] - 1;
            for (int j = 0; j<zakres_m; j++){
//                System.out.print(populacja_array[zmienna_pomocnicza_int][j]+" ");
//                if(j == punkt-1)
//                    System.out.print("| ");
            }
            ////////////////////potomkowie
//            System.out.println("\nPotomkowie\tnr "+(i+1));
//            System.out.print(pary[i][0]+". ");
            zmienna_pomocnicza_int = pary[i][0] - 1;
            for (int j = 0; j<zakres_m; j++){
                if(j>punkt-1)
                    populacja_array_klon[zmienna_pomocnicza_int][j] = populacja_array[pary[i][1] - 1][j];
//                System.out.print(populacja_array_klon[zmienna_pomocnicza_int][j]+" ");
//                if(j == punkt-1) {
//                    System.out.print("| ");
//                }
            }
//            System.out.println();
//            System.out.print(pary[i][1]+". ");
            zmienna_pomocnicza_int = pary[i][1] - 1;
            for (int j = 0; j<zakres_m; j++){
                if(j>punkt-1)
                    populacja_array_klon[zmienna_pomocnicza_int][j] = populacja_array[pary[i][0] - 1][j];
//                System.out.print(populacja_array_klon[zmienna_pomocnicza_int][j]+" ");
//                if(j == punkt-1) {
//                    System.out.print("| ");
//                }
            }
//            System.out.println("\n");
        }
        /////////////////////////////////////////////////////////////////////////////////////////////////
        /////////////////////DWUPUNKTOWE//////////////////////////////////////////////////////////////////
        //zresetowanie klona populacji
        for (int i = 0; i<n; i++){
            for (int j = 0; j<zakres_m; j++){
                populacja_array_klon[i][j] = populacja_array[i][j];
            }
        }
        int punkt2, sprawdzajaca;
//        System.out.println("\n\nKrzyżowanie dwupunktowe");
//        System.out.println("Łączenie w pary:");
        for (int i = 0; i<rozmiar_par/2; i++){
            punkt = r.nextInt(zakres_m);
            if (punkt == 0)
                punkt++;
            punkt2 = r.nextInt(zakres_m);
            if (punkt2 == 0)
                punkt2++;
            if (punkt2 < punkt){
                sprawdzajaca = punkt2;
                punkt2 = punkt;
                punkt = sprawdzajaca;
            }
            pary[i][0] = indeksy[i];
            pary[i][1] = indeksy[rozmiar_par - 1 - i];
            ////////////////////rodzice
//            System.out.println("Rodzice\t\tnr "+(i+1)+" (punkt "+punkt+", "+punkt2+")");
//            System.out.print(pary[i][0]+". ");
            zmienna_pomocnicza_int = pary[i][0] - 1;
            for (int j = 0; j<zakres_m; j++){
//                System.out.print(populacja_array[zmienna_pomocnicza_int][j]+" ");
//                if(j == punkt-1 || j == punkt2-1)
//                    System.out.print("| ");
            }
//            System.out.println();
//            System.out.print(pary[i][1]+". ");
            zmienna_pomocnicza_int = pary[i][1] - 1;
            for (int j = 0; j<zakres_m; j++){
//                System.out.print(populacja_array[zmienna_pomocnicza_int][j]+" ");
//                if(j == punkt-1 || j== punkt2-1)
//                    System.out.print("| ");
            }
            ////////////////////potomkowie
//            System.out.print(pary[i][0]+". ");
            zmienna_pomocnicza_int = pary[i][0] - 1;
            for (int j = 0; j<zakres_m; j++){
                if(j>punkt-1 && j<punkt2)
                    populacja_array_klon[zmienna_pomocnicza_int][j] = populacja_array[pary[i][1] - 1][j];
//                System.out.print(populacja_array_klon[zmienna_pomocnicza_int][j]+" ");
//                if(j == punkt-1 || j== punkt2-1) {
//                    System.out.print("| ");
//                }
            }
//            System.out.println();
//            System.out.print(pary[i][1]+". ");
            zmienna_pomocnicza_int = pary[i][1] - 1;
            for (int j = 0; j<zakres_m; j++){
                if(j>punkt-1 && j<punkt2)
                    populacja_array_klon[zmienna_pomocnicza_int][j] = populacja_array[pary[i][0] - 1][j];
//                System.out.print(populacja_array_klon[zmienna_pomocnicza_int][j]+" ");
//                if(j == punkt-1 || j== punkt2-1) {
//                    System.out.print("| ");
//                }
            }
//            System.out.println("\n");
        }
        /////////////////////////////////////////////////////////////////////////////////////////////////
        /////////////////////WIELOPUNKTOWE//////////////////////////////////////////////////////////////////
        //zresetowanie klona populacji
        for (int i = 0; i<n; i++){
            for (int j = 0; j<zakres_m; j++){
                populacja_array_klon[i][j] = populacja_array[i][j];
            }
        }
//        System.out.println("\n\nKrzyżowanie wielopunktowe");
//        System.out.println("Łączenie w pary:");
        int numer_indeksu = 1;
        for (int i = 0; i<rozmiar_par/2; i++){
            int ilosc_punktow = r.nextInt(n);//zmienic na m
            if (ilosc_punktow == 0) ilosc_punktow++;
            int[] losowe_punkty = new int[ilosc_punktow];
            for (int j = 0; j<ilosc_punktow; j++){
                losowe_punkty[j] = r.nextInt(zakres_m) + 1;
            }
//            punkt = r.nextInt(zakres_m);
//            if (punkt == 0) punkt++;
//            punkt2 = r.nextInt(zakres_m);
//            if (punkt2 == 0) punkt2++;
            pary[i][0] = indeksy[i];
            pary[i][1] = indeksy[rozmiar_par - 1 - i];
            ////////////////////rodzice
//            System.out.print("Rodzice\t\tnr "+(i+1)+" (punkt ");
            //wypisanie wylosowanych punktow
            Arrays.sort(losowe_punkty);
            for (int j = 0; j<ilosc_punktow; j++){
//                if (j==0)
//                    System.out.print(losowe_punkty[j]);
//                else
//                    System.out.print(", "+losowe_punkty[j]);
            }
//            System.out.println("):");
//            System.out.print(pary[i][0]+". ");
            zmienna_pomocnicza_int = pary[i][0] - 1;
            zmienna_pomocnicza = 0;
            for (int j = 0; j<zakres_m; j++){
//                System.out.print(populacja_array[zmienna_pomocnicza_int][j]+" ");
                if (losowe_punkty[zmienna_pomocnicza] > 0) {
                    if (j == (losowe_punkty[zmienna_pomocnicza] - 1)) {
//                        System.out.print("| ");
                        if (losowe_punkty.length - 1 != zmienna_pomocnicza)
                            zmienna_pomocnicza++;
                    }
                }
            }
//            System.out.println();
//            System.out.print(pary[i][1]+". ");
            zmienna_pomocnicza_int = pary[i][1] - 1;
            zmienna_pomocnicza = 0;
            for (int j = 0; j<zakres_m; j++){
//                System.out.print(populacja_array[zmienna_pomocnicza_int][j]+" ");
                if(j == losowe_punkty[zmienna_pomocnicza] - 1) {
//                    System.out.print("| ");
                    if (losowe_punkty.length - 1 != zmienna_pomocnicza)
                        zmienna_pomocnicza++;
                }
            }
            ////////////////////potomkowie
//            System.out.println("\nPotomkowie\tnr "+(i+1));
//            System.out.print(pary[i][0]+". ");
            zmienna_pomocnicza_int = pary[i][0] - 1;
            zmienna_pomocnicza = 0;
            for (int j = 0; j<zakres_m; j++){////////////////////////DOKONCZYC////////////////////////////////////////////////////////////////////////////
                if (numer_indeksu%2==1)
                    populacja_array_klon[zmienna_pomocnicza_int][j] = populacja_array[pary[i][0] - 1][j];
                else
                    populacja_array_klon[zmienna_pomocnicza_int][j] = populacja_array[pary[i][1] - 1][j];
//                System.out.print(populacja_array_klon[zmienna_pomocnicza_int][j]+" ");
                if(j == losowe_punkty[zmienna_pomocnicza] - 1) {
//                    System.out.print("| "+"<"+numer_indeksu+"> ");
//                    System.out.print("| ");
                    numer_indeksu++;
                    if (losowe_punkty.length - 1 != zmienna_pomocnicza)
                        zmienna_pomocnicza++;
                }
            }
//            System.out.println();
            numer_indeksu = 1;
//            System.out.print(pary[i][1]+". ");
            zmienna_pomocnicza_int = pary[i][1] - 1;
            zmienna_pomocnicza = 0;
            for (int j = 0; j<zakres_m; j++){
                if (numer_indeksu%2==1)
                    populacja_array_klon[zmienna_pomocnicza_int][j] = populacja_array[pary[i][1] - 1][j];
                else
                    populacja_array_klon[zmienna_pomocnicza_int][j] = populacja_array[pary[i][0] - 1][j];
//                System.out.print(populacja_array_klon[zmienna_pomocnicza_int][j]+" ");
                if(j == losowe_punkty[zmienna_pomocnicza] - 1) {
//                    System.out.print("| "+"<"+numer_indeksu+"> ");
//                    System.out.print("| ");
                    numer_indeksu++;
                    if (losowe_punkty.length - 1 != zmienna_pomocnicza)
                        zmienna_pomocnicza++;
                }
            }
            numer_indeksu = 1;
//            System.out.println("\n");
        }
        //////////////////////////////////////ROWNOMIERNE//////////////////////////////////////
//        System.out.println("\n\nKrzyżowanie równomierne");
//        System.out.println("Łączenie w pary:");
        for (int i = 0; i<rozmiar_par/2; i++){
            punkt = r.nextInt(zakres_m);
            if (punkt == 0) punkt++;
            pary[i][0] = indeksy[i];
            pary[i][1] = indeksy[rozmiar_par - 1 - i];
            ////////////////////rodzice
//            System.out.println("Rodzice\t\tnr "+(i+1));
//            System.out.print(pary[i][0]+". ");
            zmienna_pomocnicza_int = pary[i][0] - 1;
            for (int j = 0; j<zakres_m; j++){
                System.out.print(populacja_array[zmienna_pomocnicza_int][j]+" ");
            }
//            System.out.println();
//            System.out.print(pary[i][1]+". ");
            zmienna_pomocnicza_int = pary[i][1] - 1;
            for (int j = 0; j<zakres_m; j++){
//                System.out.print(populacja_array[zmienna_pomocnicza_int][j]+" ");
            }
            ////////////////////wzorzec
            int wzorzec[][] = new int[1][zakres_m];
            ////////////////////wypelnienie wzorca
            for (int j = 0; j<zakres_m; j++){
                wzorzec[0][j] = r.nextInt(2);
            }
//            System.out.println("\nWzorzec:");
//            System.out.print("   ");
            for (int j = 0; j<zakres_m; j++){
//                System.out.print(wzorzec[0][j]+" ");
            }
            ////////////////////potomkowie
//            System.out.println("\nPotomkowie\tnr "+(i+1));
//            System.out.print(pary[i][0]+". ");
            zmienna_pomocnicza_int = pary[i][0] - 1;
            //pierwszy potomek
            for (int j = 0; j<zakres_m; j++){
                if (wzorzec[0][j] == 0) //0 z rodzica pierwszego
                    populacja_array_klon[zmienna_pomocnicza_int][j] = populacja_array[pary[i][0] - 1][j];
                else //1 z rodzica drugiego
                    populacja_array_klon[zmienna_pomocnicza_int][j] = populacja_array[pary[i][1] - 1][j];
//                System.out.print(populacja_array_klon[zmienna_pomocnicza_int][j]+" ");
            }
//            System.out.println();
//            System.out.print(pary[i][1]+". ");
            zmienna_pomocnicza_int = pary[i][1] - 1;
            //drugi potomek
            for (int j = 0; j<zakres_m; j++){
                if (wzorzec[0][j] == 0) //0 z rodzica drugiego
                    populacja_array_klon[zmienna_pomocnicza_int][j] = populacja_array[pary[i][1] - 1][j];
                else //1 z rodzica pierwszego
                    populacja_array_klon[zmienna_pomocnicza_int][j] = populacja_array[pary[i][0] - 1][j];
//                System.out.print(populacja_array_klon[zmienna_pomocnicza_int][j]+" ");
            }
//            System.out.println("\n");
        }
    }
}
