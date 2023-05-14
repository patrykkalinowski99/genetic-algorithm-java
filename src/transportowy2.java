import java.util.*;

public class transportowy2 {
    public static int min(int a, int b) {
        if (a < b)
            return a;
        else
            return b;
    }
    public static int[] shuffle_array(int [] args){
        Integer[] array = new Integer[args.length];
        for (int i = 0; i<args.length; i++){
            array[i] = args[i];
        }
        List<Integer> list =Arrays.asList(array);
        Collections.shuffle(list);
        list.toArray(array);
        int[] array_return = new int[args.length];
        for (int i = 0; i<args.length; i++){
            array_return[i] = array[i];
        }
        return array_return;
    }
    public static void main(String[] args) {
        System.out.println("Problem transportowy");
        int ilosc_punktow_nadania = 3, ilosc_punktow_odbioru = 4, ilosc_wierszy_osobnika = 12;
//        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
//        System.out.print("Podaj ilość punktów nadania: ");
//        int ilosc_punktow_nadania = myObj.nextInt();  // Read user input
//        System.out.print("Podaj ilość puntków odbioru: ");
//        int ilosc_punktow_odbioru = myObj.nextInt();  // Read user input
//        System.out.print("Podaj ilość osobników w populacji: ");
//        int ilosc_wierszy_osobnika = myObj.nextInt();  // Read user input
//        System.out.print("Wprowadziłeś " + ilosc_punktow_nadania + " punktów nadania, ");  // Output user input
//        System.out.print(" " + ilosc_punktow_odbioru + " punkty odbioru, ");  // Output user input
//        System.out.print(" " + ilosc_wierszy_osobnika + " osobników\n");  // Output user input

        int ilosc_genow = 12; //ilosc_punktow_nadania * ilosc_punktow_odbioru;
//        int[][] tab_koszt = new int[ilosc_punktow_nadania][ilosc_punktow_odbioru];
        int[][] tab_koszt  = {
                {10, 0, 20, 11},
                {12, 7, 9, 20},
                {0, 14, 16, 18}
        };
//        System.out.print("Podaj "+ilosc_genow+" wartości dla tablicy kosztów: ");
//        int wartosc_koszt;
//        for (int i = 0; i<ilosc_punktow_nadania; i++){
//            for (int j = 0; j<ilosc_punktow_odbioru; j++) {
//                wartosc_koszt = myObj.nextInt();
//                tab_koszt[i][j] = wartosc_koszt;
//            }
//        }
          // Read user input
        int[] sour = {15,25,5}; //tablice punktów nadania
        int[] dest = {5,15,15,10};//odbioru
        //Losowanie osobnikow
        int ilosc_osobnikow = 2;
        int populacja [][][] = new int [ilosc_osobnikow][ilosc_wierszy_osobnika][ilosc_genow];
        Random r = new Random();
        for (int n = 0; n < ilosc_osobnikow; n++) {
            for (int i = 0; i < ilosc_wierszy_osobnika; i++) {
                Set<Integer> set = new LinkedHashSet<Integer>();
                while (set.size() < ilosc_genow) {
                    set.add(r.nextInt(ilosc_genow));
                }
                ArrayList<Integer> al = new ArrayList<>(set);
                int o = 0;
                for (int lczb : al) {
                    populacja[n][i][o] = lczb;
                    o++;
                }
            }
        }
        for (int n = 0; n < ilosc_osobnikow; n++) {
            System.out.println("\nOsobnik nr "+(n+1));
            System.out.print(" \\\t");
            for (int i = 0; i < ilosc_genow; i++) {
                System.out.print((i + 1) + ".\t");
            }
            System.out.println();
            for (int i = 0; i < ilosc_wierszy_osobnika; i++) {
                System.out.print((i + 1) + ".\t");
                for (int j = 0; j < ilosc_genow; j++) {
                    System.out.print(populacja[n][i][j] + "\t");
                }
                System.out.println();
            }
        }
        System.out.println("\nTablica kosztów");
        System.out.print(" \\\t");
        for (int i = 0; i<ilosc_punktow_odbioru; i++){
            System.out.print(dest[i]+"\t");
        }
        System.out.println();
        for (int i = 0; i<ilosc_punktow_nadania; i++){
            System.out.print(sour[i]+"\t");
            for (int j = 0; j<ilosc_punktow_odbioru; j++){
                System.out.print(tab_koszt[i][j]+"\t");
            }
            System.out.println();
        }
        System.out.println("\nTablice przewozów\n");
        int a = 0, b = 0;
        int ocena[][] = new int [ilosc_osobnikow][ilosc_genow];
        //przypisanie tablic przewozow do jednej tablicy
        int tablica [][][] = new int [ilosc_genow][sour.length][dest.length];
        for (int z = 0; z < ilosc_osobnikow; z++) {
            for (int n = 0; n < ilosc_wierszy_osobnika; n++) {
                int[] temp_punkty_nadania = {15, 25, 5};
                int[] temp_punkty_odbioru = {5, 15, 15, 10};
                for (int k = 0; k < ilosc_genow; k++) {
                    int pomocnicza = populacja[z][n][k];
                    a = 0;
                    b = 0;
                    if (pomocnicza < dest.length) {
                        a = 0;
                        b = pomocnicza;
                    } else {
                        while (pomocnicza >= dest.length) {
                            pomocnicza -= dest.length;
                            a++;
                        }
                        b = pomocnicza;
                    }
                    tablica[n][a][b] = min(temp_punkty_nadania[a], temp_punkty_odbioru[b]);
                    temp_punkty_nadania[a] -= tablica[n][a][b]; //aktualizowanie wartosci punktow nadania
                    temp_punkty_odbioru[b] -= tablica[n][a][b]; // aktualizowanie wartosci punktow odbioru
                    ocena[z][n] += tablica[n][a][b] * tab_koszt[a][b];
                }
                System.out.println("Tablica przewozów nr " + (n + 1) +" osobnika "+(z+1)+"ego");
                System.out.print(" \\\t");
                for (int i = 0; i < ilosc_punktow_odbioru; i++) {
                    System.out.print(dest[i] + "\t");
                }
                System.out.println();
                for (int i = 0; i < sour.length; i++) {
                    System.out.print(sour[i] + "\t");
                    for (int j = 0; j < dest.length; j++) {
                        System.out.print(tablica[n][i][j] + "\t");
                    }
                    System.out.println();
                }
                System.out.println("Funkcja oceny: " + ocena[z][n] + "\n");
            }
        }
        int wybrany_osobnik = r.nextInt(ilosc_osobnikow);
        System.out.println("MUTACJA dla osobnika nr "+(wybrany_osobnik+1));
        System.out.print(" \\\t");
        for (int i = 0; i<ilosc_genow; i++){
            System.out.print((i+1)+".\t");
        }
        System.out.println();
        for (int i = 0; i<ilosc_wierszy_osobnika; i++){
            System.out.print((i+1)+".\t");
            for (int j  = 0; j<ilosc_genow; j++){
                System.out.print(populacja[wybrany_osobnik][i][j]+"\t");
            }
            System.out.println();
        }
        //Losowanie podmacierz populacji
        int k1, k2, w1, w2, min;
        ArrayList<Integer> wiersze = new ArrayList<Integer>();
        ArrayList<Integer> kolumny = new ArrayList<Integer>();
//        min = ilosc_genow - 2 ; //ilosc wylosowanych musi odpowiadac tablicy ograniczen sour i dest
//        k2 = r.nextInt(((ilosc_genow) - min)+1)+min;
//        kolumny.add(k2);
//        min -= 2;
//        k1 = r.nextInt(((k2-1) - min)+1)+min;
//        kolumny.add(k1);
//        min -= 2;
//        k2 = r.nextInt(((k1-1) - min)+1)+min;
//        kolumny.add(k2);
//        min -= 2;
//        k1 = r.nextInt(((k2-1) - min)+1)+min;
//        kolumny.add(k1);
        kolumny.add(1);kolumny.add(2);kolumny.add(3);kolumny.add(4);
//        while(true){ //minimum 4 różne liczby
//            k2 = r.nextInt(k1-1);
//            k2 = k1;
//            if(k2 == 0){
//                break;
//            }
//            kolumny.add(k2);
//        }
        min = 2; //minimalna wylosowana wartosc pierwszego losowania wynosi polowa ilosci osobnikow
//        w2 = r.nextInt(((ilosc_wierszy_osobnika) - min)+1)+min;//wyjdzie co najmniej 2
//        w2 = r.nextInt(ilosc_wierszy_osobnika);
//        if (w2 == 0)
//            w2+=2;
//        wiersze.add(w2);
        wiersze.add(1);wiersze.add(2);
//        min = w2-1; //minimalna wylosowana wartosc pierwszego losowania wynosi polowa ilosci osobnikow
//        w1 = r.nextInt(min);//wyjdzie co najmniej 2
//        wiersze.add(w1);
//        if (w1 != 0){
//            min = w1; //minimalna wylosowana wartosc pierwszego losowania wynosi polowa ilosci osobnikow
//            w2 = r.nextInt(w1);//wyjdzie co najmniej 2
//            wiersze.add(w2);
//        }
        int it = 0;
//        while(true){
//            w1 = r.nextInt(w2);
//            w2 = w1;
//            if(w1 == 0 || it == sour.length-1){
//                break;
//            }
//            it++;
//            wiersze.add(w1);
////            wiersze.add(w1);
//        }
        Collections.sort(kolumny); //posortowanie indeksow rosnąco
        Collections.sort(wiersze);
        System.out.print("Wylosowano kolumny {");
        it = 0;
        for (int i = 0; i<kolumny.size(); i++){
            if(i==0 || it == dest.length)
                System.out.print(kolumny.get(i));
            else
                System.out.print(", "+kolumny.get(i));
                it++;
        }
        System.out.print("}");
        System.out.println();
        System.out.print("Wylosowano wiersze {");
        for (int i = 0; i<wiersze.size(); i++){
            if(i==0)
                System.out.print(wiersze.get(i));
            else
                System.out.print(", "+wiersze.get(i));
        }
        System.out.print("}\n");
        System.out.println("Podmacierz W wylosowanych punktów");
        int[][] mutacja = new int[wiersze.size()][kolumny.size()];
        System.out.print(" \\\t");
        for (int i = 0; i<kolumny.size(); i++){
            System.out.print(kolumny.get(i)+".\t");
        }
        System.out.println();
        for (int i = 0; i<wiersze.size(); i++){
            System.out.print(wiersze.get(i)+".\t");
            for (int j  = 0; j<kolumny.size(); j++){
                mutacja[i][j] = populacja[wybrany_osobnik][wiersze.get(i)-1][kolumny.get(j)-1];
                System.out.print(mutacja[i][j]+"\t");
            }
            System.out.println();
        }
        //ponowna inicjalizacja
        //ograniczenia
        int[] destw = new int[kolumny.size()]; //suma rzędu kolumn
        int[] sourw = new int[wiersze.size()]; //suma rzędu wierszy
        System.out.print("Zauważmy, że ");
        for (int i = 0; i<wiersze.size(); i++){
            sourw[i] = 0;
            for (int j = 0; j<kolumny.size(); j++){
                sourw[i] += mutacja[i][j];
            }
            if (i == 0)
                System.out.print("sourw["+(i+1)+"] = "+sourw[i]);
            else
                System.out.print(", sourw["+(i+1)+"] = "+sourw[i]);
        }
        for (int i = 0; i<kolumny.size(); i++){
            destw[i] = 0;
            for (int j = 0; j<wiersze.size(); j++){
                destw[i] += mutacja[j][i];
            }
            System.out.print(", destw["+(i+1)+"] = "+destw[i]);
        }
        System.out.println();
        a = 0;
        b = 0;
        int ocenaw[] = new int [ilosc_genow];
        for (int i = 0; i<ilosc_genow; i++){ocenaw[i] = 0;}//wyzerowanie tabicy kosztow
        int[][] tab_kosztw  = new int[sour.length][dest.length];
        for (int i = 0; i<mutacja.length; i++){
            for (int j = 0; j<mutacja[0].length; j++){
                tab_kosztw[i][j] = mutacja[i][j];
            }
        }
        //przypisanie tablic przewozow do jednej tablicy
        int tablicaw [][][] = new int [ilosc_genow][sour.length][dest.length];
        int najmniejsza_index = 0;
        int najmniejsza_wartosc = 10000;
        for (int n = 0; n < ilosc_wierszy_osobnika; n++) {
            int[] temp_punkty_nadania = new int[sour.length];
            for (int i = 0; i<sourw.length; i++){
                temp_punkty_nadania[i] = sourw[i];
            }
            int[] temp_punkty_odbioru = new int[dest.length];
            for (int i = 0; i<destw.length; i++){
                temp_punkty_odbioru[i] = destw[i];
            }
            for (int k = 0; k<ilosc_genow; k++) {
                int pomocnicza = populacja[wybrany_osobnik][n][k];
                a = 0;
                b = 0;
                if (pomocnicza < dest.length) {
                    a = 0;
                    b = pomocnicza;
                }
                else {
                    while(pomocnicza >= dest.length) {
                        pomocnicza -= dest.length;
                        a++;
                    }
                    b = pomocnicza;
                }
                tablicaw[n][a][b] = min(temp_punkty_nadania[a], temp_punkty_odbioru[b]);
                temp_punkty_nadania[a] -= tablicaw[n][a][b]; //aktualizowanie wartosci punktow nadania
                temp_punkty_odbioru[b] -= tablicaw[n][a][b]; // aktualizowanie wartosci punktow odbioru
                ocenaw[n] += tablicaw[n][a][b] * tab_kosztw[a][b];
            }
//            System.out.println("Tablica przewozów nr "+(n+1));
//            System.out.print(" \\\t");
            for (int i = 0; i<ilosc_punktow_odbioru; i++){
//                System.out.print(destw[i]+"\t");
            }
//            System.out.println();
            for (int i = 0; i < sourw.length; i++) {
//                System.out.print(sourw[i]+"\t");
                for (int j = 0; j < dest.length; j++) {
//                    System.out.print(tablicaw[n][i][j] + "\t");
                }
//                System.out.println();
            }
            if (ocenaw[n] < najmniejsza_wartosc){najmniejsza_wartosc = ocenaw[n]; najmniejsza_index = n;}
//            System.out.println("Funkcja oceny: " + ocenaw[n] + "\n");
        }
//        System.out.println("Najmniejsza ocena wynosi = "+najmniejsza_wartosc+" i odpowiada ona tablicy nr "+(najmniejsza_index+1));
        System.out.println("Po ponownej inicjalizacji macierzy W może ona przyjąć nastepującą postać");
        System.out.print(" \\\t");
        for (int i = 0; i<ilosc_punktow_odbioru; i++){
            System.out.print(kolumny.get(i)+".\t");
        }
        System.out.println();
        for (int i = 0; i < sourw.length; i++) {
            System.out.print(wiersze.get(i)+".\t");
            for (int j = 0; j < dest.length; j++) {
                System.out.print(tablicaw[najmniejsza_index][i][j] + "\t");
            }
            System.out.println();
        }
        //podmienić wartości po wykonanej mutacji w całej populacji
        for (int i = 0; i<sourw.length; i++){
            for (int j = 0; j<dest.length; j++){
//                System.out.println("("+wiersze.get(i)+", "+kolumny.get(j)+") = "+tablicaw[najmniejsza_index][i][j]);
                populacja[wybrany_osobnik][wiersze.get(i)-1][kolumny.get(j)-1] = tablicaw[najmniejsza_index][i][j];
            }
        }
        System.out.println("\nPopulacja nr "+(wybrany_osobnik+1)+" po wykonaniu mutacji");
        System.out.print(" \\\t");
        for (int i = 0; i<ilosc_genow; i++){
            System.out.print((i+1)+".\t");
        }
        System.out.println();
        for (int i = 0; i<ilosc_wierszy_osobnika; i++){
            System.out.print((i+1)+".\t");
            for (int j  = 0; j<ilosc_genow; j++){
                System.out.print(populacja[wybrany_osobnik][i][j]+"\t");
            }
            System.out.println();
        }
        int losowy_krzyzowania2 = r.nextInt(ilosc_osobnikow - 1)+1;
        int losowy_krzyzowania = r.nextInt(losowy_krzyzowania2);
        System.out.println("\nKRZYŻOWANIE dla osobnika "+(losowy_krzyzowania+1)+"ego z "+(losowy_krzyzowania2+1)+"im\n");
        int[][] V1 = new int [ilosc_wierszy_osobnika][ilosc_genow];
        int[][] V2 = new int [ilosc_wierszy_osobnika][ilosc_genow];
        int[][] DIV = new int [ilosc_wierszy_osobnika][ilosc_genow];
        int[][] REM = new int [ilosc_wierszy_osobnika][ilosc_genow];
        int[][] REM1 = new int [ilosc_wierszy_osobnika][ilosc_genow];
        int[][] REM2 = new int [ilosc_wierszy_osobnika][ilosc_genow];
        int[][] V3 = new int [ilosc_wierszy_osobnika][ilosc_genow];
        int[][] V4 = new int [ilosc_wierszy_osobnika][ilosc_genow];
        int[] sourm = new int[ilosc_wierszy_osobnika];
        int[] destm = new int[ilosc_genow];
        for (int i = 0; i<ilosc_wierszy_osobnika; i++){
            for (int j = 0; j<ilosc_genow; j++){
                //uzupełnienie wybranego osobnika dla macierzy V1, a potem drugiego do V2
                V1[i][j] = populacja[losowy_krzyzowania][i][j];
                V2[i][j] = populacja[losowy_krzyzowania2][i][j];
                //div = (v1+v2)/2
                DIV[i][j] = (V1[i][j] + V2[i][j])/2;
                sourm[i] += DIV[i][j];
                destm[j] += DIV[i][j];
                //rem = (v1+v2)%2
                REM[i][j] = (V1[i][j] + V2[i][j])%2;
                //wypelnienie zerami macierzy rem1 i rem2
//                if (j%2==0){
//                    REM2[i][j] = REM[i][j];
////                    REM1[i][j] = 0;
//                }
//                else{
//                    REM1[i][j] = REM[i][j];
////                    REM2[i][j] = 0;
//                }

            }
        }
        //wypisanie
        System.out.println("\t\t\t\tOSOBNIK NR"+(losowy_krzyzowania+1) + " - V1 \t\t\t\t\t\t\t\t\t\t\tOSOBNIK NR"+(losowy_krzyzowania2+1)+" - V2");
        System.out.print(" \\\t");
        for (int i = 0; i<ilosc_genow; i++){
            System.out.print((i+1)+".\t");
        }
        System.out.print("\t\t \\\t");
        for (int i = 0; i<ilosc_genow; i++){
            System.out.print((i+1)+".\t");
        }
        System.out.println();
        for (int i = 0; i<ilosc_wierszy_osobnika; i++){
            System.out.print((i+1)+".\t");
            for (int j  = 0; j<ilosc_genow; j++){
                System.out.print(V1[i][j]+"\t");
            }
            System.out.print("\t\t");
            System.out.print((i+1)+".\t");
            for (int j  = 0; j<ilosc_genow; j++){
                System.out.print(V2[i][j]+"\t");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("\t\t\t\tOSOBNIK NR"+(losowy_krzyzowania+1) + " - DIV \t\t\t\t\t\t\t\t\t\t\tOSOBNIK NR"+(losowy_krzyzowania2+1)+" - REM");
        System.out.print(" \\\t");
        for (int i = 0; i<ilosc_genow; i++){
            System.out.print((i+1)+".\t");
        }
        System.out.print("\t\t \\\t");
        for (int i = 0; i<ilosc_genow; i++){
            System.out.print((i+1)+".\t");
        }
        System.out.println();
        for (int i = 0; i<ilosc_wierszy_osobnika; i++){
            System.out.print((i+1)+".\t");
            for (int j  = 0; j<ilosc_genow; j++){
                System.out.print(DIV[i][j]+"\t");
            }
            System.out.print("\t\t");
            System.out.print((i+1)+".\t");
            for (int j  = 0; j<ilosc_genow; j++){
                System.out.print(REM[i][j]+"\t");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("\t\t\t\tOSOBNIK NR"+(losowy_krzyzowania+1) + " - REM1 \t\t\t\t\t\t\t\t\t\t\tOSOBNIK NR"+(losowy_krzyzowania2+1)+" - REM2");
        System.out.print(" \\\t");
        for (int i = 0; i<ilosc_genow; i++){
            System.out.print((i+1)+".\t");
        }
        System.out.print("\t\t \\\t");
        for (int i = 0; i<ilosc_genow; i++){
            System.out.print((i+1)+".\t");
        }
        System.out.println();
        for (int i = 0; i<ilosc_wierszy_osobnika; i++){
            System.out.print((i+1)+".\t");
            for (int j  = 0; j<ilosc_genow; j++){
                System.out.print(REM1[i][j]+"\t");
            }
            System.out.print("\t\t");
            System.out.print((i+1)+".\t");
            for (int j  = 0; j<ilosc_genow; j++){
                System.out.print(REM2[i][j]+"\t");
            }
            System.out.println();
        }
        System.out.println();
        //koniec wypisywania
    }
}
