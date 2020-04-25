import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static final int TYPE_OF_TRIANGLE_PORT = 3029;
    public static void main(String[] args){
        System.out.println("The server started .. ");

        new Thread() {
            public void run() {
                try {
                    ServerSocket ss = new ServerSocket(TYPE_OF_TRIANGLE_PORT);
                    while (true) {
                        new DetermineTypeOfTriangle(ss.accept()).start();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
    private static class DetermineTypeOfTriangle extends Thread {
        Socket socket;

        public DetermineTypeOfTriangle(Socket socket) {
            this.socket = socket;
        }
        public void run(){
            try {
                PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
                float side1,side2,side3;
                String Str;
                while(socket.getInputStream()!=null) {
                    ObjectInputStream inObject =new ObjectInputStream(socket.getInputStream());
                    Triangle T =(Triangle)inObject.readObject();
                    if (T != null) {
                        System.out.println(T.name);
                        side1 = (float) Math.sqrt(Math.pow((T.P2.getPoint_x()) - (T.P1.getPoint_x()), 2) + Math.pow((T.P2.getPoint_y()) - (T.P1.getPoint_y()), 2));
                        side2 = (float) Math.sqrt(Math.pow((T.P2.getPoint_x()) - (T.P3.getPoint_x()), 2) + Math.pow((T.P2.getPoint_y()) - (T.P3.getPoint_y()), 2));
                        side3 = (float) Math.sqrt(Math.pow((T.P3.getPoint_x()) - (T.P1.getPoint_x()), 2) + Math.pow((T.P3.getPoint_y()) - (T.P1.getPoint_y()), 2));
                        if (side1 == side2 && side2 == side3) {
                            Str = "The type of " + T.name + " is Equilateral";
                            out.println(Str);
                            System.out.println(side1 + " " + side2 + " " + side3);
                        } else if ((side1 == side2 && side2 != side3) || (side1 != side2 && side3 == side1) || (side3 == side2 && side3 != side1)) {
                            Str = "The type of " + T.name + " is Isosceles";
                            out.println(Str);
                            System.out.println(side1 + " " + side2 + " " + side3);
                        } else if (side1 != side2 && side2 != side3 && side3 != side1) {
                            Str = "The type of " + T.name + " is Scalene";
                            out.println(Str);
                            System.out.println(side1 + " " + side2 + " " + side3);
                        }
                    } else {
                        break;
                    }
                }
                out.close();
                socket.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
    }
}
