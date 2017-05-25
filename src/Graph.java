import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by denis__larin on 16.05.17.
 */
public class Graph {
    private static final int MAXW = 100000*100000;
    private double [][] list;//матрица смежности
    private int [][] primList;
    private int n;//количество вершин



    public Graph(int n) {
        this.n = n;
        list = new double[n][n];
        primList = new int[n][n];
    }

    public Graph(double[][] list) {
        this.list = list;
        this.n = list[0].length;
    }

    public Graph(int[][] primList) {
        this.primList = primList;
        this.n = primList[0].length;
    }

    public void add(int from, int to, double data){
        list[from][to] = data;
    }
    public void widthFind(int from) {
        boolean[] visited = new boolean[this.n];
        Queue<Integer> turn = new LinkedList<>();
        turn.add(from);
        visited[from] = true;
        while (turn.size() != 0) {
            int index = turn.remove();
            System.out.println(index);
            for (int i = 0; i < this.n; i++) {
                if (list[index][i] != 0 && !visited[i]) {
                    visited[i] = true;
                    turn.add(i);
                }
            }
        }
    }
    public void heightRFind(int from){
        heightRFind(from,new boolean[this.n]);
    }
    public void heightSFind(int from){
        boolean[] visited = new boolean[this.n];
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(from);
        visited[from] = true;
        while (stack.size()!=0){
            int index = stack.pop();
            System.out.println(index);
            for (int i = 0; i < this.n; i++) {
                if(!visited[i] && list[index][i]!=0){
                    stack.push(i );
                    visited[i] = true;
                }
            }
        }

    }

    private void heightRFind(int from, boolean[] visited) {
        visited[from] = true;
        System.out.println(from);
        for (int i = 0; i < this.n; i++) {
            if(list[from][i]!=0 && !visited[i]){
                heightRFind(i,visited);
            }
        }
    }
    //принимает номер вершины
    public int degreeofVertex(int vertex){
        int deg = 0;
        for (int i = 0; i < this.n; i++) {
            if(list[vertex][i]!=0){
                deg++;
            }
        }
        return deg;
    }
    public void wave(int[][] arr, int x, int y, int lineSize, int columnSize){
        if(arr[x][y] == 0)
            return;
        int burned = 2;
        arr[x][y] = burned;
        boolean flag  = true;
        while (flag) {
            flag = false;
            burned++;
            for (int i = 0; i < lineSize; i++) {
                for (int j = 0; j < columnSize; j++) {
                    if (arr[i][j] == burned-1) {
                        flag = true;
                        waveCheck(arr, burned, i, j, lineSize, columnSize);
                    }
                }
            }
        }
    }

    private void waveCheck(int[][] arr, int burned, int i, int j, int lineSize, int columnSize) {
        if (isValid(i-1,j,lineSize,columnSize) && arr[i - 1][j] == 1) {
            arr[i - 1][j] = burned;
        }
        if (isValid(i-1,j+1,lineSize,columnSize) && arr[i - 1][j + 1] == 1) {
            arr[i - 1][j + 1] = burned;
        }
        if (isValid(i,j+1,lineSize,columnSize) && arr[i][j + 1] == 1) {
            arr[i][j + 1] = burned;
        }
        if (isValid(i+1,j+1,lineSize,columnSize) && arr[i + 1][j + 1] == 1) {
            arr[i + 1][j + 1] = burned;
        }
        if ( isValid(i+1,j,lineSize,columnSize) && arr[i + 1][j] == 1  ) {
            arr[i + 1][j] = burned;
        }
        if ( isValid(i+1,j-1,lineSize,columnSize)&& arr[i + 1][j - 1] == 1  ) {
            arr[i + 1][j - 1] = burned;
        }
        if ( isValid(i,j-1,lineSize,columnSize)&& arr[i][j - 1] == 1  ) {
            arr[i][j - 1] = burned;
        }
        if ( isValid(i-1,j-1,lineSize,columnSize)&& arr[i - 1][j - 1] == 1  ) {
            arr[i - 1][j - 1] = burned;
        }
    }

    private boolean isValid(int i, int j, int lineSize, int columnSize) {
        return (i>=0 && i<lineSize && j>=0 && j<columnSize);
    }
    public void prim(){
        for (int i = 0; i < n; i++) {
            System.out.println(prim(i));
        }
    }

    private int prim(int i) {
        boolean[] isused = new boolean[n];
        int[] temp =  new int[n];
        Arrays.fill(temp,MAXW);
        temp[i] = 0;
        while (true) {
            int k = -1;
            for (int j = 0; j < n; j++) {
                if (!isused[j] && temp[j] < MAXW && (k == -1 || temp[k] > temp[j]))
                    k = j;
            }
            if(k == -1)
                break;
            isused[k] = true;

            for (int nv = 0; nv < n; nv++) {
                if (!isused[nv] && primList[k][nv] < MAXW) {
                    temp[nv] = (int) Math.min(temp[nv], primList[k][nv]);
                }
            }
        }
        int r = 0;
        for (int k = 0; k < n; k++) {
            r+=temp[k];
        }
        return r;
    }
}
