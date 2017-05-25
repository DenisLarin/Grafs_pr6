/**
 * Created by denis__larin on 16.05.17.
 */
public class Main {
    public static void main(String[] args) {
        double[][] list = {
                {0,1,0,1},
                {1,0,1,0},
                {0,1,0,0},
                {1,0,0,0}
        };
       /* Graph graph = new Graph(list);
        graph.widthFind(1);
        System.out.println("___");
        graph.heightRFind(1  );
        System.out.println("___");
        graph.heightSFind(1  );*/
        int[][] arr = {
                {1,1,3,1},
                {1,2,1,3},
                {0,1,0,0},
                {1,0,3,0}
        };
       /* graph.wave(arr,0,1,4,4);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(arr[i][j]);
            }
            System.out.println( );
        }

        System.out.println("______");*/


        Graph graph1 = new Graph(arr);
        graph1.prim();
    }
}
