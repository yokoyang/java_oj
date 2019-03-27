import java.util.Scanner;

public class WY_5 {
    // 时间复杂度O(N)
// 将除数y从k+1 开始计算，除数为y时，数对的个数包括两部分： n/y*(y-k) 和多出来的另一部分，这部分需要看
// n%y 和k的大小关系

    public static void main(String[] arsg){
        Scanner sc= new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        long sum = 0;
        int t = 0;
        int tt = 0;
        for(int i=k+1;i<=n;i++){
            t++;
            tt = (n%i - k + 1) >0 ? (n%i - k + 1):0;
            sum+=n/i*t+tt;
        }
        if(k == 0) sum-=n;// k=0 特殊情况  多计算了n次
        System.out.print(sum);
    }
}
