public class TreeFarm {
    int[] sapling = {0,0,0,0};
    int[] states = {0,0,0,0};
    int[][] dispenser ;
    int interval;
    int trees;
    int playercooldown = 0;
    int treefarmrunningcooldown = 0;
    int step = 0;
    public TreeFarm(int[][] dispenser,int interval){
        this.dispenser = dispenser; //描述发射器用的数组
        this.interval = interval; //树场运行间隔
    }
    public void ticking()  //模拟一个tick
    {
        playercooldown--; //玩家放置CD --
        if (playercooldown<=0){
            placesapling(); //玩家尝试放置树苗
        }
        step++; // step: 取值范围为0..3,描述发射器运行
        if (step>3){
            step=0;
        }
        for (int i=0;i<dispenser[step].length;i++){
            if (dispenser[step][i]==1){
                activeDispenser(i); // dispenser[i] 描述4n+i的时候激活的发射器,详细见主类
            }
        }
        treefarmrunningcooldown--; // 树场运行冷却

    }

    public void placesapling(){ //玩家尝试放置树苗
        for (int i=0;i<4;i++){  // 遍历一次树苗, 如果放置成功就跳出并且重置放置冷却,否则不采取任何动作
            if (sapling[i]==0){
                sapling[i]=1; // sapling[i]==1意味着该位置有树苗, 10描述东北角树苗, 00和11描述只被一个发射器催熟的树苗,01描述被两个发射器催熟的树苗
                states[i]=0; // states[i] 描述这个位置的树苗状态.
                playercooldown = 4;
                break;
            }
        }
    }
    public boolean isVaildtoGrow(){// 检测是否可以长成
        if ((sapling[0]+sapling[1]+sapling[2]+sapling[3])!=4){  //如果有一个位置缺少树苗,不能长成
            return false;
        }else if (treefarmrunningcooldown>0){ //如果树场还在运行,不能长成
            return false;
        }else{
            return true;
        }
    }
    public void activeDispenser(int i){ //激活发射器
        if (i==2){ //如果i 是 1或者2,都指向的是01这个树苗,进行重定向
            i=1;
        }
        if (i==3){
            i=2;
        }
        if (sapling[i]==0){ //如果没树苗,不执行操作

        }else {
            boolean success = ((int)(Math.random()*100))<45; // p 进入下一阶段 = 0.45
            if (success){ //如果成功进入下一阶段
                if (states[i]==0){ //如果状态为0, 进入状态1
                    states[i]=1;
                }else if (states[i]==1&&isVaildtoGrow()){ //如果进入状态1并且可以生长,成长
                    grow(); //生长
                    states[i]=0; //状态归位
                }
            }else {
            }
        }
    }
    public void grow(){ //生长
        treefarmrunningcooldown = interval; //树场运行冷却
        for (int i=0;i<4;i++){  //清空树苗和树苗状态
            states[i]=0;
            sapling[i]=0;
        }
        trees++; //长成的树木数量+1
    }

    public int getTrees() {
        return trees;
    }

    public void setDispenser(int[][] dispenser) {
        this.dispenser = dispenser;
    }

    public void setSapling(int[] sapling) {
        this.sapling = sapling;
    }

    public void setStates(int[] states) {
        this.states = states;
    }

}
