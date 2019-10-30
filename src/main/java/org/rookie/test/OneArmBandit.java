package org.rookie.test;

import java.time.Duration;
import java.time.Instant;

/**
 * @author heqiang
 * @date 18-6-26 下午6:20
 * @description
 * @modified by
 */
public class OneArmBandit {
    public static void main(String[] args) {
        int[][] kinds = OneArmBandit.genKinds(3);
        int len = kinds.length;
        Instant start = Instant.now();
        int[][] arr = new int[3][5];
        double sum = 0;
        for (int a = 0; a < len; a++) {
            for (int b = 0; b < len; b++) {
                for (int c = 0; c < len; c++) {
                    for (int d = 0; d < len; d++) {
                        for (int e = 0; e < len; e++) {
                            int[][] temp = new int[5][3];
                            temp[0] = kinds[a];
                            temp[1] = kinds[b];
                            temp[2] = kinds[c];
                            temp[3] = kinds[d];
                            temp[4] = kinds[e];
                            for (int k = 0; k < arr.length; k++) {
                                for (int f = 0; f < arr[k].length; f++) {
                                    arr[k][f] = temp[f][k];
                                }
                            }
//                            System.out.println(Arrays.deepToString(arr));
                            double tempSum = 0;
                            for (PayLine f : PayLine.values()) {
                                tempSum += f.handle(arr);
                            }
//                            System.out.println(tempSum);
//                            System.out.println("------------------------------------");
                            sum += tempSum;
                        }
                    }
                }
            }
        }
        Instant end = Instant.now();
        System.out.println(Duration.between(start, end).toMillis());
        System.out.println(Math.pow(kinds.length, 5));
        System.out.println(sum);
    }

    public static int[][] genKinds(int num) {
        int tempNum = num;
        int kinds = 1;
        for (int i = 0; i < 3; i++) {
            kinds *= tempNum--;
        }
        int[][] result = new int[kinds][3];
        int count = 0;
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                if (j != i) {
                    for (int k = 0; k < num; k++) {
                        if (k != j && k != i) {
                            result[count][0] = i;
                            result[count][1] = j;
                            result[count][2] = k;
                            count++;
                        }
                    }
                }

            }
        }
        return result;
    }

    private enum PayLine {

        PAY_LINE1(new int[][]{{1, 0}, {1, 1}, {1, 2}, {1, 3}, {1, 4}}),
        PAY_LINE2(new int[][]{{0, 0}, {0, 1}, {0, 2}, {0, 3}, {0, 4}}),
        PAY_LINE3(new int[][]{{2, 0}, {2, 1}, {2, 2}, {2, 3}, {2, 4}}),
        PAY_LINE4(new int[][]{{0, 0}, {1, 1}, {2, 2}, {1, 3}, {0, 4}}),
        PAY_LINE5(new int[][]{{2, 0}, {1, 1}, {0, 2}, {1, 3}, {2, 4}}),
        PAY_LINE6(new int[][]{{1, 0}, {0, 1}, {0, 2}, {0, 3}, {1, 4}}),
        PAY_LINE7(new int[][]{{1, 0}, {2, 1}, {2, 2}, {2, 3}, {1, 4}}),
        PAY_LINE8(new int[][]{{0, 0}, {0, 1}, {1, 2}, {2, 3}, {2, 4}}),
        PAY_LINE9(new int[][]{{2, 0}, {2, 1}, {1, 2}, {0, 3}, {0, 4}}),
        PAY_LINE10(new int[][]{{1, 0}, {2, 1}, {1, 2}, {0, 3}, {1, 4}}),
        PAY_LINE11(new int[][]{{1, 0}, {0, 1}, {1, 2}, {2, 3}, {1, 4}}),
        PAY_LINE12(new int[][]{{0, 0}, {1, 1}, {1, 2}, {1, 3}, {0, 4}}),
        PAY_LINE13(new int[][]{{2, 0}, {1, 1}, {1, 2}, {1, 3}, {2, 4}}),
        PAY_LINE14(new int[][]{{0, 0}, {1, 1}, {0, 2}, {1, 3}, {0, 4}}),
        PAY_LINE15(new int[][]{{2, 0}, {1, 1}, {2, 2}, {1, 3}, {2, 4}}),
        PAY_LINE16(new int[][]{{1, 0}, {1, 1}, {0, 2}, {1, 3}, {1, 4}}),
        PAY_LINE17(new int[][]{{1, 0}, {1, 1}, {2, 2}, {1, 3}, {1, 4}}),
        PAY_LINE18(new int[][]{{0, 0}, {0, 1}, {2, 2}, {0, 3}, {0, 4}}),
        PAY_LINE19(new int[][]{{2, 0}, {2, 1}, {0, 2}, {2, 3}, {2, 4}}),
        PAY_LINE20(new int[][]{{0, 0}, {2, 1}, {2, 2}, {2, 3}, {0, 4}}),
        PAY_LINE21(new int[][]{{2, 0}, {0, 1}, {0, 2}, {0, 3}, {2, 4}}),
        PAY_LINE22(new int[][]{{1, 0}, {2, 1}, {0, 2}, {2, 3}, {1, 4}}),
        PAY_LINE23(new int[][]{{1, 0}, {0, 1}, {2, 2}, {0, 3}, {1, 4}}),
        PAY_LINE24(new int[][]{{0, 0}, {2, 1}, {0, 2}, {2, 3}, {0, 4}}),
        PAY_LINE25(new int[][]{{2, 0}, {0, 1}, {2, 2}, {0, 3}, {2, 4}}),
        PAY_LINE26(new int[][]{{0, 0}, {2, 1}, {1, 2}, {0, 3}, {2, 4}}),
        PAY_LINE27(new int[][]{{2, 0}, {0, 1}, {1, 2}, {2, 3}, {0, 4}}),
        PAY_LINE28(new int[][]{{1, 0}, {0, 1}, {2, 2}, {1, 3}, {2, 4}}),
        PAY_LINE29(new int[][]{{0, 0}, {2, 1}, {1, 2}, {2, 3}, {0, 4}}),
        PAY_LINE30(new int[][]{{2, 0}, {1, 1}, {0, 2}, {0, 3}, {1, 4}});

        //7(0),A(1),K(2),Q(3),J(4),10(5),9(6),8(7)
        /**
         * 赔率信息（3个，4个，5个）
         */
        public static final double[][] oddsArr = {{2, 10, 100}, {2, 15, 50}, {0.8, 3, 17}, {0.7, 2, 13}, {0.6, 1.2, 8}, {0.3, 0.8, 2}, {0.2, 0.6, 1.6}, {0.1, 0.2, 12}};


        public double handle(int[][] origin) {
            int len = position.length;
            int[] temp = new int[len];
            for (int i = 0; i < len; i++) {
                temp[i] = origin[position[i][0]][position[i][1]];
            }
            return getOdds(temp, oddsArr);
        }

        private double getOdds(int[] arr, double[][] oddsArr) {
            int midVal = arr[2];
            int count = 0;
            if (arr[1] == midVal) {
                if (arr[0] == midVal) {
                    count = 3;
                    if (arr[3] == midVal) {
                        count = 4;
                        if (arr[4] == midVal) {
                            count = 5;
                        }
                    }
                } else if (arr[3] == midVal) {
                    count = 3;
                    if (arr[4] == midVal) {
                        count = 4;
                    }
                }
            } else if (arr[3] == midVal) {
                if (arr[4] == midVal) {
                    count = 3;
                }
            }
            double odds = 0;
            switch (count) {
                case 3:
                    odds = oddsArr[midVal][0];
                    break;
                case 4:
                    odds = oddsArr[midVal][1];
                    break;
                case 5:
                    odds = oddsArr[midVal][2];
                    break;
            }
            return odds;
        }

        private int[][] position;

        PayLine(int[][] position) {
            this.position = position; }
    }


}
