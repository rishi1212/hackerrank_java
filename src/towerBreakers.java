/**
 * Created by Rishi on 31/01/17.
 */
//public class towerBreakers {
//}
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class towerBreakers {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t=sc.nextInt();
        while(t-->0)
        {
            int n=sc.nextInt();
            int m=sc.nextInt();
            if( n%2==0 || m==1)
            {
                System.out.println("2");
            }
            else
                System.out.println("1");
        }
    }
}
