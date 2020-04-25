import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;


public class Client {
    public static final int TYPE_OF_TRIANGLE_PORT = 3029;
    public static void main(String[] args){
        try {
            BufferedReader br = new BufferedReader(new FileReader("points.txt"));
            String contentFile=br.readLine();
            int numberOfTriangles=Integer.parseInt(contentFile);
            String tName;
            String[] temp1,temp2,temp3;
            Point p1,p2,p3;
            ArrayList<Triangle> triangleArrayList = new ArrayList<>(numberOfTriangles);
            for (int i=0;i<numberOfTriangles;i++){
                tName=br.readLine();
                temp1= br.readLine().split(" ");
                p1 = new Point(Float.parseFloat(temp1[0]),Float.parseFloat(temp1[1]));
                temp2= br.readLine().split(" ");
                p2 = new Point(Float.parseFloat(temp2[0]),Float.parseFloat(temp2[1]));
                temp3= br.readLine().split(" ");
                p3 = new Point(Float.parseFloat(temp3[0]),Float.parseFloat(temp3[1]));
                Triangle T = new Triangle(p1,p2,p3,tName);
                triangleArrayList.add(T);
            }
            Socket s = new Socket("localhost", TYPE_OF_TRIANGLE_PORT);
            Scanner messageScanner = new Scanner(new InputStreamReader(s.getInputStream()));
            Scanner inputs = new Scanner(System.in);
            int count=0;
            while (numberOfTriangles !=count ) {
                Triangle T =triangleArrayList.get(count);
                System.out.println("Sending " + T.name);
                ObjectOutputStream outObject = new ObjectOutputStream(s.getOutputStream());
                outObject.writeObject(T);
                String respond = messageScanner.nextLine();
                System.out.println(respond);
                System.out.println("Press any key to continue");
                String input = inputs.nextLine();
                count++;
            }
            ObjectOutputStream outObject = new ObjectOutputStream(s.getOutputStream());
            outObject.writeObject(null);
            messageScanner.close();
            s.close();
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
