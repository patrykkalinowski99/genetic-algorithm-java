import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Random;
public class jeden_osobnik {
    public static int min(int a, int b) {
        if (a < b)
            return a;
        else
            return b;
    }
    public static void main(String[] args) {
        System.out.println("Problem transportowy");
        int ilosc_punktow_nadania = 4, ilosc_punktow_odbioru = 5;// ilosc_punktow_nadania = 3;
        int ilosc_genow = 5;//ilosc_punktow_nadania * ilosc_punktow_odbioru;
        int[][] tab_koszt  = {
                {0, 0, 5, 0, 3},
                {0, 4, 0, 0, 0},
                {0, 0, 5, 7, 0},
                {3, 1, 0, 0, 2}
        };
        int[] tab_punkty_nadania = {8,4,12,6};
        int[] tab_punkty_odbioru = {3,5,10,7,5};
        //Losowanie osobnikow
        int populacja [][] = {
                {0, 0, 5, 0, 3},
                {0, 4, 0, 0, 0},
                {0, 0, 5, 7, 0},
                {3, 1, 0, 0, 2}
        };//new int [ilosc_punktow_nadania][ilosc_genow];
        Random r = new Random();
//        for (int i = 0; i < ilosc_punktow_nadania; i++) {
//            Set<Integer>set = new LinkedHashSet<Integer>();
//            while (set.size() < ilosc_genow) {
//                set.add(r.nextInt(ilosc_punktow_nadania*ilosc_punktow_odbioru));
//            }
//            ArrayList<Integer> al = new ArrayList<>(set);
//            int o = 0;
//            for (int lczb : al) {
//                populacja[i][o] = lczb;
//                o++;
//            }
//        }
        System.out.print(" \\\t");
        for (int i = 0; i<ilosc_genow; i++){
            System.out.print((i+1)+".\t");
        }
        System.out.println();
        for (int i = 0; i<ilosc_punktow_nadania; i++){
            System.out.print((i+1)+".\t");
            for (int j  = 0; j<ilosc_genow; j++){
                System.out.print(populacja[i][j]+"\t");
            }
            System.out.println();
        }
        System.out.println("\nTablica kosztów");
        System.out.print(" \\\t");
        for (int i = 0; i<ilosc_punktow_odbioru; i++){
            System.out.print(tab_punkty_odbioru[i]+"\t");
        }
        System.out.println();
        for (int i = 0; i<ilosc_punktow_nadania; i++){
            System.out.print(tab_punkty_nadania[i]+"\t");
            for (int j = 0; j<ilosc_punktow_odbioru; j++){
                System.out.print(tab_koszt[i][j]+"\t");
            }
            System.out.println();
        }
        System.out.println("\nTablice przewozów");
        int a = 0, b = 0;
        int ocena[] = new int [ilosc_genow];
        //przypisanie tablic przewozow do jednej tablicy
        int tablica [][][] = new int [ilosc_genow][tab_punkty_nadania.length][tab_punkty_odbioru.length];

        for (int n = 0; n < ilosc_punktow_nadania; n++) {
            int[] temp_punkty_nadania =  {15, 25, 5};
            int[] temp_punkty_odbioru = {5, 15, 15, 10};
            for (int k = 0; k<ilosc_genow; k++) {
                int pomocnicza = populacja[n][k];
                a = 0;
                b = 0;
                if (pomocnicza < tab_punkty_odbioru.length) {
                    a = 0;
                    b = pomocnicza;
                }
                else {
                    while(pomocnicza >= tab_punkty_odbioru.length) {
                        pomocnicza -= tab_punkty_odbioru.length;
                        a++;
                    }
                    b = pomocnicza;
                }
                tablica[n][a][b] = min(temp_punkty_nadania[a], temp_punkty_odbioru[b]);
                temp_punkty_nadania[a] -= tablica[n][a][b]; //aktualizowanie wartosci punktow nadania
                temp_punkty_odbioru[b] -= tablica[n][a][b]; // aktualizowanie wartosci punktow odbioru
                ocena[n] += tablica[n][a][b] * tab_koszt[a][b];
            }
            System.out.println("Tablica przewozów nr "+(n+1));
            System.out.print(" \\\t");
            for (int i = 0; i<ilosc_punktow_odbioru; i++){
                System.out.print(tab_punkty_odbioru[i]+"\t");
            }
            System.out.println();
            for (int i = 0; i < tab_punkty_nadania.length; i++) {
                System.out.print(tab_punkty_nadania[i]+"\t");
                for (int j = 0; j < tab_punkty_odbioru.length; j++) {
                    System.out.print(tablica[n][i][j] + "\t");
                }
                System.out.println();
            }
            System.out.println("Funkcja oceny: " + ocena[n] + "\n");
        }
    }
}
