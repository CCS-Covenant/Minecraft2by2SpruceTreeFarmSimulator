import java.util.Scanner;

public class STFsimulator {
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        System.out.println("输入需要模拟的间隔: ");
        int targetTick = -1;
         targetTick = sc.nextInt();
        if (targetTick<=0){
            targetTick = 72000;
        }
        // dipanser[][]以一个二维数组来描述发射器行为
        // 10描述东北角树苗, 00和11描述只被一个发射器催熟的树苗,01描述被两个发射器催熟的树苗
        // 左上角发射器为0,顺时针依次为1,2,3
        //
        // disanser[1] 代表在4gt周期内应该激活的发射器
        //{0,0,0,0} 代表没有发射器会激活
        // A B C 分别指三个树苗
        // A B1 B2 指的是有一个发射器对着树苗A,两个对着B

        //
        int[][] dis1 = {{1,0,0,0},{0,0,0,0},{0,0,1,0},{0,0,0,1}};  //3发射器,A B C 布置,交叉催熟 12/16gt
        TreeFarm TreeDispanser1 = new TreeFarm(dis1,12);
        TreeFarm TreeDispanser2 = new TreeFarm(dis1,16);
        int[][] dis2 =  {{1,0,0,0},{0,1,0,0},{0,0,1,0},{0,0,0,0}}; //3发射器,A B1 B2 布置,交叉催熟 12/16gt
        TreeFarm TreeDispanser3 = new TreeFarm(dis2,12);
        TreeFarm TreeDispanser4 = new TreeFarm(dis2,16);
        int[][] dis3 =  {{1,0,0,0},{0,1,0,0},{0,0,1,0},{0,0,0,1}}; //4发射器,A B1 B2 C 布置,交叉催熟 12/16gt
        TreeFarm TreeDispanser5 = new TreeFarm(dis3,12);
        TreeFarm TreeDispanser6 = new TreeFarm(dis3,16);
        int[][] dis4 = {{1,1,1,1},{0,0,0,0},{0,0,0,0},{0,0,0,0}}; //4发射器,A B1 B2 C 布置,无交叉催熟 12/16gt
        TreeFarm TreeDispanser7 = new TreeFarm(dis4,12);
        TreeFarm TreeDispanser8 = new TreeFarm(dis4,16);
        int[][] dis5 = {{1,0,0,0},{0,0,0,0},{0,0,1,0},{0,0,0,0}}; //双发射器,A B 布置,交叉催熟 12/16gt
        TreeFarm TreeDispanser9 = new TreeFarm(dis5,12);
        TreeFarm TreeDispanser10 = new TreeFarm(dis5,16);
        int[][] dis6 = {{1,1,0,1},{0,0,0,0},{0,0,0,0},{0,0,0,0}}; //3发射器,A B C 布置,无交叉催熟 12/16gt
        TreeFarm TreeDispanser11 = new TreeFarm(dis6,12);
        TreeFarm TreeDispanser12 = new TreeFarm(dis6,16);
        for (int i=0;i<targetTick;i++){
            TreeDispanser1.ticking();
            TreeDispanser2.ticking();
            TreeDispanser3.ticking();
            TreeDispanser4.ticking();
            TreeDispanser5.ticking();
            TreeDispanser6.ticking();
            TreeDispanser7.ticking();
            TreeDispanser8.ticking();
            TreeDispanser9.ticking();
            TreeDispanser10.ticking();
            TreeDispanser12.ticking();
            TreeDispanser11.ticking();
        }
        System.out.println("双发射器,A B 布置,交叉催熟 12/16gt");
        PrintRate(TreeDispanser9,targetTick);
        PrintRate(TreeDispanser10,targetTick);
        System.out.println("3发射器,A B C 布置,无交叉催熟 12/16gt");
        PrintRate(TreeDispanser11,targetTick);
        PrintRate(TreeDispanser12,targetTick);
        System.out.println("3发射器,A B C 布置,交叉催熟 12/16gt");
        PrintRate(TreeDispanser1,targetTick);
        PrintRate(TreeDispanser2,targetTick);
        System.out.println("3发射器,A B1 B2 布置,交叉催熟 12/16gt");
        PrintRate(TreeDispanser3,targetTick);
        PrintRate(TreeDispanser4,targetTick);
        System.out.println("4发射器,A B1 B2 C 布置,交叉催熟 12/16gt");
        PrintRate(TreeDispanser5,targetTick);
        PrintRate(TreeDispanser6,targetTick);
        System.out.println("4发射器,A B1 B2 C 布置,无交叉催熟 12/16gt");
        PrintRate(TreeDispanser7,targetTick);
        PrintRate(TreeDispanser8,targetTick);


    }
    public static void PrintRate(TreeFarm tf,int tick){
        double rate = ((double)tf.getTrees()*21.5*4)/((double)tick/(double) 72000);
        System.out.println("Rate is" + rate + " logs/h");
    }


}
