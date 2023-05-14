import java.util.Random;

public class transportowy {
    public static int min (int a, int b){
        if(a<b)
            return a;
        else
            return b;
    }
    public static void main(String[] args) {
        System.out.println("Problem transportowy");
        int ilosc_osobnikow = 5;
        double pi = 0.2; //prawdopodobienstwo inwersji
        double pm = 0.2; //mutacji
        int ilosc_punktow_nadania = 3; //ile jest punktow nadania
        int ilosc_punktow_odbioru = 4; //ile jest punktow odbioru
        int[][] tablica2 = new int[ilosc_punktow_nadania][ilosc_punktow_odbioru]; //tablica przewozów
        int[] tab_punkty_nadania = new int[ilosc_punktow_nadania]; //tablica w ktorej trzymamy punkty nadania
        int[] tab_punkty_odbioru = new int[ilosc_punktow_odbioru];//tablica w ktorej trzymamy punkty odbioru
        tab_punkty_nadania[0] = 15;
        tab_punkty_nadania[1] = 25;
        tab_punkty_nadania[2] = 5;
        tab_punkty_odbioru[0] = 5;
        tab_punkty_odbioru[1] = 15;
        tab_punkty_odbioru[2] = 15;
        tab_punkty_odbioru[3] = 10;
        int[][] tab_koszt = new int[ilosc_punktow_nadania][ilosc_punktow_odbioru]; //tablica kosztów
        tab_koszt[0][0] = 10;
        tab_koszt[0][1] = 0;
        tab_koszt[0][2] = 20;
        tab_koszt[0][3] = 11;
        tab_koszt[1][0] = 12;
        tab_koszt[1][1] = 7;
        tab_koszt[1][2] = 9;
        tab_koszt[1][3] = 20;
        tab_koszt[2][0] = 0;
        tab_koszt[2][1] = 14;
        tab_koszt[2][2] = 16;
        tab_koszt[2][3] = 18;
        int ilosc_pozycji = ilosc_punktow_nadania*ilosc_punktow_odbioru;
        System.out.println("Ilość nieodwiedzonych pozycji: "+ilosc_pozycji);
        int[][] populacja = new int[ilosc_osobnikow][ilosc_pozycji];
        Random r = new Random();
        for (int i = 0; i<populacja.length; i++){
            System.out.print(i+".\t");
            for (int j  = 0; j<populacja[i].length; j++){
                populacja[i][j] = r.nextInt(ilosc_pozycji);
                System.out.print(populacja[i][j]+"\t");
            }
            System.out.println();
        }
        System.out.println("Punkty nadania:");
        for (int i = 0; i<ilosc_punktow_nadania; i++){
            System.out.print(tab_punkty_nadania[i]+" ");
        }
        System.out.println();
        System.out.println("Punkty odbioru:");
        for (int i = 0; i<ilosc_punktow_odbioru; i++){
            System.out.print(tab_punkty_odbioru[i]+" ");
        }
        System.out.println();
        System.out.println("Tablica przed wypełnieniem z tablicą kosztów"); //tablica przewozów
        for (int i = 0; i<tablica2.length; i++){
            System.out.print((i+1)+".\t");
            for (int j  = 0; j<tablica2[i].length; j++){
                System.out.print(tablica2[i][j]+"\t");
            }
            System.out.print("\t\t\t"+(i+1)+".\t");
            for (int j  = 0; j<tab_koszt[i].length; j++){
                System.out.print(tab_koszt[i][j]+"\t");
            }
            System.out.println();
        }
        System.out.println();
        int licznik = 0, pn = 0, po = 0, minimum = 0;
        double[] tab_fx = new double[ilosc_osobnikow];


        int temp_i = 0, temp_j = 0;
        //int [] temp_zapas = new int[zapasy.length];
        //int [] temp_zapot = new int[zapotrzeb.length];
        int f_oceny [] = new int [ilosc_pozycji];
        int tablica [][][] = new int [ilosc_osobnikow][ilosc_punktow_nadania][ilosc_punktow_odbioru];

        for (int n = 0; n < ilosc_osobnikow; n++) {
            int[] temp_zapas =  { 15, 25, 5 };
            int[] temp_zapot = { 5, 15, 15, 10 };
            //temp_zapas = zapasy;
            //temp_zapot = zapotrzeb;
            //System.out.println("N =  " + temp_zapas[temp_i] + " " + temp_zapot[temp_j]);
            for (int k = 0; k < ilosc_pozycji; k++) {
                int temp_los = populacja[n][k];
                temp_i = 0;
                temp_j = 0;
                if (temp_los < ilosc_punktow_odbioru) {
                    temp_i = 0;
                    temp_j = temp_los;
                    //System.out.println(temp_i + " " + temp_j);
                }
                else {
                    while(temp_los >= ilosc_punktow_odbioru) {
                        temp_los -= ilosc_punktow_odbioru;
                        temp_i++;
                    }
                    temp_j = temp_los;
                    //System.out.println(temp_i + " " + temp_j);
                }
                tablica[n][temp_i][temp_j] = min(temp_zapas[temp_i], temp_zapot[temp_j]);

                temp_zapas[temp_i] -= tablica[n][temp_i][temp_j];
                temp_zapot[temp_j] -= tablica[n][temp_i][temp_j];
                f_oceny[n] += tablica[n][temp_i][temp_j] * tab_koszt[temp_i][temp_j];
            }
            for (int i = 0; i < ilosc_punktow_nadania; i++) {
                for (int j = 0; j < ilosc_punktow_odbioru; j++) {
                    System.out.print(tablica[n][i][j] + " ");
                }
                System.out.println();
            }
            System.out.println("Funkcja oceny: " + f_oceny[n] + "\n");
        }

//        int temp_i = 0, temp_j = 0;
//        int f_oceny [] = new int [ilosc_pozycji];
//        for (int i = 0; i< ilosc_pozycji; i++){
//            f_oceny[i] = 0;
//        }
//        int tablica [][][] = new int [ilosc_pozycji][ilosc_punktow_nadania][ilosc_punktow_odbioru];
//
//        for (int n = 0; n < ilosc_osobnikow; n++) {
//            int[] temp_zapas =  { 15, 25, 5 };
//            int[] temp_zapot = { 5, 15, 15, 10 };
//            for (int k = 0; k < ilosc_pozycji; k++) {
//                int temp_los = tab_wylosowane_liczby[n][k];
//                temp_i = 0;
//                temp_j = 0;
//                if (temp_los < ilosc_punktow_odbioru) {
//                    temp_i = 0;
//                    temp_j = temp_los;
//                    //System.out.println(temp_i + " " + temp_j);
//                }
//                else {
//                    while(temp_los >= ilosc_punktow_odbioru) {
//                        temp_los -= ilosc_punktow_odbioru;
//                        temp_i++;
//                    }
//                    temp_j = temp_los;
//                    //System.out.println(temp_i + " " + temp_j);
//                }
//                tablica[n][temp_i][temp_j] = min(temp_zapas[temp_i], temp_zapot[temp_j]);
//
//                temp_zapas[temp_i] -= tablica[n][temp_i][temp_j];
//                temp_zapot[temp_j] -= tablica[n][temp_i][temp_j];
////                System.out.print(tablica[n][temp_i][temp_j]+" * "+tab_koszt[temp_i][temp_j]);
//                f_oceny[n] += tablica[n][temp_i][temp_j] * tab_koszt[temp_i][temp_j];
//            }
//            for (int i = 0; i < ilosc_punktow_nadania; i++) {
//                for (int j = 0; j < ilosc_punktow_odbioru; j++) {
//                    System.out.print(tablica[n][i][j] + " ");
//                }
//                System.out.println();
//            }
//            System.out.println("Funkcja oceny: " + f_oceny[n] + "\n");
//        }

    }
}
