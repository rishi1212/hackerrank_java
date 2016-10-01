/**
 * Created by Rishi on 01/10/16.
 */

import java.io.*;
        import java.util.*;
        import java.text.*;
        import java.math.*;

public class similarPair {

    public static LinkedList<Integer>[] nodes = new LinkedList[100002];
    static int n , t, root;



    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */

        Scanner scan = new Scanner(System.in);

        n = scan.nextInt();
        t = scan.nextInt();
        long[] stree = new long[4*n+1];

        for(int i=1;i<=n;i++)
            nodes[i] = new LinkedList<Integer>();

        int[] idegree = new int[n+1];

        for(int i=1;i<n;i++)
        {
            int par = scan.nextInt();
            int chd = scan.nextInt();

            nodes[par].addFirst(chd);
            idegree[chd]++;
        }

        for(int i=1;i<=n;i++)
        {
            if(idegree[i] == 0)
            {
                root = i;
                break;
            }
        }

        long[] pairs = new long[1];

        depthSearch(root,stree,pairs);

        System.out.println(pairs[0]);

    }

    public static void depthSearch(int nodeval, long[] stree, long[] pairs){

        int min = (nodeval - t < 1) ? 1 : nodeval - t;
        int max = (nodeval + t > n) ? n : nodeval + t;

        pairs[0] += query(stree,1,1,n,min, max);

        updateTree(stree,1,1,n,nodeval,1);

        for(int chd : nodes[nodeval]){
            depthSearch(chd, stree, pairs);
        }

        updateTree(stree,1,1,n,nodeval,-1);
    }


    public static void updateTree(long[] tree, int node,int tl, int tr, int val, long opt){
        if(val < tl || val > tr || tl > tr)
            return;

        tree[node] += opt;

        int m = (tl + tr) >> 1;

        if(tl == tr)
            return;
        else if(val <= m)
            updateTree(tree,node<<1,tl,m,val,opt);
        else
            updateTree(tree,node<<1|1,m+1,tr,val,opt);
    }


    public static long query(long[] tree, int node, int tl, int tr, int min, int max){

        if(max < tl || min > tr)
            return 0;

        else if(max == tr && min == tl)
            return tree[node];

        else{
            int mid = (tl + tr) >> 1;
            int lmax = (mid < max) ? mid : max;
            int rmin = (min > mid) ? min : mid + 1;
            return query(tree,node<<1, tl, mid, min, lmax) + query(tree,node<<1|1, mid+1, tr, rmin, max);
        }

    }
}