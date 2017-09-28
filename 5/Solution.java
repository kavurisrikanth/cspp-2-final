/*
    Matrix
*/

import java.util.Scanner;
import java.lang.Exception;

public class Solution {
    public static void main(String[] args) {
        int num_rows = 0, num_cols = 0;
        int[][] mat;
        Matrix matrices[] = new Matrix[2];

        Scanner sc = new Scanner(System.in);
        for(int k = 0; k < 2; k++) {
            num_rows = sc.nextInt();
            num_cols = sc.nextInt();
            mat = new int[num_rows][num_cols];
            for(int i = 0; i < num_rows; i++) {
                for(int j = 0; j < num_cols; j++)
                    mat[i][j] = sc.nextInt();
            }
            matrices[k] = new Matrix(mat, num_rows, num_cols);
            // k++;
        }

        int tests = sc.nextInt();
        sc.nextLine();
        String[] ops = new String[tests];
        for(int i = 0; i < tests; i++)
            ops[i] = sc.nextLine();

        Matrix ans;
        int det = 0;
        for(int i = 0; i < tests; i++) {
            // split by space
            String[] temp = ops[i].split("\\s");

            // System.out.println("at 0: " + temp[0]);
            // for(int x = 0; x < temp.length; x++)
            //     System.out.print(temp[x] + "_");
            // System.out.println("\n\n\n");

            if(temp[0].equals("Determinant")) {
                // determinant
                System.out.println("Determinant");
                try {
                    if(temp[1].equals("A"))
                        det = matrices[0].determinant();
                    else
                        det = matrices[1].determinant();

                    System.out.println(det);
                } catch (Exception e) {
                    System.out.println("Determinant not possible.");
                    // System.out.println(e);
                    // throw e;
                }
            } else {
                if(temp[0].equals("Add")) {
                    // Matrix addition
                    System.out.println("Addition");
                    try {
                        // Addition has 2 args.
                        // A + B = B + A
                        ans = matrices[0].add(matrices[1]);

                        System.out.print(ans);
                    } catch(Exception e) {
                        System.out.println("Matrix addition not possible.");
                    }
                } else {
                    if(temp[0].equals("Subtract")) {
                        // Matrix subtraction
                        System.out.println("Subtraction");
                        try {
                            // 2 args. But A - B is NOT equal to B - A
                            // So check the second and third args.
                            if(temp[2].equals("A")) {
                                // B - A
                                ans = matrices[1].subtract(matrices[0]);
                            } else {
                                // A - B
                                ans = matrices[0].subtract(matrices[1]);
                            }

                            System.out.print(ans);
                        } catch(Exception e) {
                            System.out.println("Matrix subtraction not possible.");
                        }
                    } else {
                        // Matrix transpose
                        if(temp[0].equals("Transpose")) {
                            System.out.println("Transpose");
                            try {
                                // Only one arg
                                if(temp[1].equals("A"))
                                    ans = matrices[0].transpose();
                                else
                                    ans = matrices[1].transpose();

                                System.out.print(ans);
                            } catch(Exception e) {
                                System.out.println("something went wrong.");
                            }
                        }
                    }
                }
            }

        }
        sc.close();
    }
}

class Matrix {
    private int[][] data;
    private int num_rows, num_cols;

    public Matrix(int data[][], int num_rows, int num_cols) {
        this.data = data;
        this.num_rows = num_rows;
        this.num_cols = num_cols;
    }

    public int determinant() throws Exception {
        // Assume 3x3 for now
        if(this.num_rows != this.num_cols)
            throw new Exception("Invalid");

        if(this.num_rows == 2) {
            return (this.data[0][0] * this.data[1][1]) - (this.data[0][1] * this.data[1][0]);
        } else {
            int ans = 0, i = 0;
            int ins_x = 0, ins_y = 0;
            int[][] new_data = new int[this.num_rows - 1][this.num_cols - 1];

            while(i < this.num_cols) {
                ins_x = 0;
                ins_y = 0;
                for(int j = 0; j < this.num_rows; j++) {
                    if(j == i)
                        continue;
                    ins_y = 0;
                    for(int k = 0; k < this.num_cols; k++) {
                        if(k == i)
                            continue;

                        // System.out.println("j: " + j + ", k: " + k);
                        // System.out.println("x: " + ins_x + ", y: " + ins_y);
                        new_data[ins_x][ins_y] = data[j][k];

                        ins_y++;
                    }
                    ins_x++;
                }
                Matrix mat = new Matrix(new_data, this.num_rows - 1, this.num_cols - 1);
                ans += this.data[0][i] * mat.determinant();
                i++;
            }
            return ans;
        }
    }

    public int getNumRows() {
        return this.num_rows;
    }

    public int getNumCols() {
        return this.num_cols;
    }

    public int[][] getData() {
        return this.data;
    }

    public Matrix add(Matrix other) throws Exception {
        if(!(this.num_rows == other.getNumRows() && this.num_cols == other.getNumCols()))
            throw new Exception("Invalid");

        int[][] ans = new int[this.num_rows][this.num_cols],
                other_data = other.getData();
        for(int i = 0; i < this.num_rows; i++) {
            for(int j = 0; j < this.num_cols; j++)
                ans[i][j] = this.data[i][j] + other_data[i][j];
        }

        return new Matrix(ans, this.num_rows, this.num_cols);
    }

    public Matrix subtract(Matrix other) throws Exception {
        if(!(this.num_rows == other.getNumRows() && this.num_cols == other.getNumCols()))
            throw new Exception("Invalid");

        int[][] ans = new int[this.num_rows][this.num_cols],
                other_data = other.getData();
        for(int i = 0; i < this.num_rows; i++) {
            for(int j = 0; j < this.num_cols; j++)
                ans[i][j] = this.data[i][j] - other_data[i][j];
        }

        return new Matrix(ans, this.num_rows, this.num_cols);
    }

    public Matrix transpose() {
        int[][] ans = new int[this.num_cols][this.num_rows];
        for(int i = 0; i < this.num_rows; i++) {
            for(int j = 0; j < this.num_cols; j++)
                ans[j][i] = this.data[i][j];
        }

        return new Matrix(ans, this.num_cols, this.num_rows);
    }

    public String toString() {
        String ans = "";
        for(int i = 0; i < this.num_rows; i++) {
            for(int j = 0; j < this.num_cols; j++) {
                ans += this.data[i][j];
                if(j != this.num_cols - 1)
                    ans += " ";
            }
            ans += "\n";
        }

        return ans;
    }
}
