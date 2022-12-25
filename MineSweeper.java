import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class MineSweeper {
    int row;
    int col;

    public MineSweeper(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public void mineMap() { // Mayın haritasını oluşturmak için metot
        String[][] map = new String[this.row][this.col]; // Haritayı oluşturmak için bir matris oluşturdum.
        int numberOfMines = (row * col) / 4; // Mayın sayısını belirledim.

        for (int i = 0; i < map.length; i++) { // Haritayı önce çizgilerle oluşturdum.
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = "-";
            }
        }
        Random random = new Random(); // Random değerler üretmek için random adlı nesnemi oluşturdum.
        while (numberOfMines != countStars(map)) { // Yıldızları saymak için bir metot oluşturdum. Yıldız sayısı ve mayın sayısı eşit olmadıkça döngü dönsün dedim.
            int r = random.nextInt(map.length); // Satırlar için oluşturduğum matrisin uzunluğu kadar değer ürettim.
            int c = random.nextInt(map[0].length); // Aynısını sütun için yaptım.
            map[r][c] = "*"; // Üretilen rastgele satır ve sütun değerinin karşılık geldiği yere yıldız basmasını istedim.
        }

        // printMineMap(map);
        checkMine(map);

    }

    public void printMineMap(String[][] arr) { // Mayın haritasını yazdırıp kontrol etmek için
        for (String[] i : arr) {
            for (String k : i) {
                System.out.print(k);
            }
            System.out.println();
        }
    }

    public int countStars(String[][] arr) { // Yıldızları saymak için metot
        int count = 0;
        for (String[] strings : arr) {
            for (int j = 0; j < strings.length; j++) {
                if (Objects.equals(strings[j], "*")) {
                    count++;
                }
            }
        }
        return count;
    }

    public void checkMine(String[][] arr) {
        Scanner input = new Scanner(System.in); // Satır ve sütun sayısını almak için
        String[][] arr1 = new String[this.row][this.col]; // Mayınsız bir yere tıkladığımızda sayıyı yazdırmak için yeni bir dizi
        int count1 = 0; // Kaç sayı girildiğini saymak için
        for(int i = 0; i < arr1.length; i++) {
            for(int j = 0; j < arr1.length; j++) {
                arr1[i][j] = "-";
            }
        }
        int x = arr.length - 1;
        int z = 0; // Döngüyü başlatıp ve devam ettirmek için

        while(z == 0) {
            int count = 0;
            System.out.print("Satır: ");
            int r = input.nextInt();
            System.out.print("Sütun: ");
            int c = input.nextInt();

            if (arr[r][c] == "*") {
                System.out.println("Game over!");
                break;
            } else {
                if (r == 0 && c == 0) {
                    for (int i = r; i < r + 2; i++) {
                        for (int j = c; j < c + 2; j++) {
                            if (arr[i][j].contains("*")) {
                                count++;
                            }
                        }
                    }
                } else if (r == x && c == x) {
                    for (int i = x; i > x - 2; i--) {
                        for (int j = x; j > x - 2; j--) {
                            if (arr[i][j].contains("*")) {
                                count++;
                            }
                        }
                    }
                } else if (r == 0 && c == x) {
                    for (int i = 0; i < 2; i++) {
                        for (int j = x; j > x - 2; j--) {
                            if (arr[i][j].contains("*")) {
                                count++;
                            }
                        }
                    }
                } else if (r == x && c == 0) {
                    for (int i = x; i > x - 2; i--) {
                        for (int j = 0; j < 2; j++) {
                            if (arr[i][j].contains("*")) {
                                count++;
                            }
                        }
                    }
                } else if (r == 0) {
                    for (int i = 0; i < 2; i++) {
                        for (int j = c - 1; j < c + 2; j++) {
                            if (arr[i][j].contains("*")) {
                                count++;
                            }
                        }
                    }
                } else if (r == x) {
                    for (int i = x - 1; i < x + 1; i++) {
                        for (int j = c - 1; j < c + 2; j++) {
                            if (arr[i][j].contains("*")) {
                                count++;
                            }
                        }
                    }
                } else if (c == 0) {
                    for (int i = r - 1; i < r + 2; i++) {
                        for (int j = 0; j < 2; j++) {
                            if (arr[i][j].contains("*")) {
                                count++;
                            }
                        }
                    }
                } else if (c == x) {
                    for (int i = r - 1; i < r + 2; i++) {
                        for (int j = x - 1; j < x + 1; j++) {
                            if (arr[i][j].contains("*")) {
                                count++;
                            }
                        }
                    }
                } else {
                    for (int i = r - 1; i < r + 2; i++) {
                        for (int j = c - 1; j < c + 2; j++) {
                            if (arr[i][j].contains("*")) {
                                count++;
                            }
                        }
                    }
                }
                String y = String.valueOf(count);
                arr1[r][c] = y;

                printMineMap(arr1);
                System.out.println("***********************************************");
            }

            if(arr[r][c] == "-") {
                z = 0;
            }

            count1++; // Her sayı girdikçe döngü artıyor
            if(count1 == (this.row * this.col * 3 / 4)) { // Matristeki elemanların çeyreği kadar mayın varsa 3/4'ü kadar sayı girmeliyiz.
                System.out.println("Tebrikler!");
                break;
            }
        }
    }
}

