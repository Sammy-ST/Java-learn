package com.itheima;

// 导入做窗口、按钮、图片的工具。
import javax.swing.*;

//导入键盘监听工具（让方向键能动）
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.util.Random;



public class MainFrame extends JFrame{

    public static final String IMAGE_PATH="E:\\code-java\\bookLearn\\day08-1\\src\\image";

    private int[][]imageData={
            {13,10,11,0},
            {4,3,6,1},
            {9,14,8,15},
            {7,5,12,2}
    };

    //空白格0.png的初始位置
    private int emptyRow=0;
    private int emptyCol=3;

    //步数 + 步数显示标签
    private int stepCnt=0;
    private JLabel stepLabel;

    public MainFrame(){
        //初始化窗口
        initFrame();
        //初始化菜单
        initMenu();
        //打乱方块
        randomInitData();
        //初始化界面组件
        initView();
        // 绑定键盘监听
        bindKeyEvent();
        // 显示窗口.一定要放在最后！！
        this.setVisible(true);
    }

    private void initFrame(){
        this.setTitle("石子迷阵 v1.0");//标题
        this.setSize(465, 560);//窗口大小
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//点×关闭
        this.setLocationRelativeTo(null);//居中
        this.setLayout(null);//自由定位
        this.setAlwaysOnTop(true);//窗口置顶
    }

    private void initMenu(){
        JMenuBar menuBar=new JMenuBar();
        JMenu systemMenu=new JMenu("系统菜单");

        JMenuItem restartItem=new JMenuItem("重新开始");
        restartItem.addActionListener(e-> {
            new MainFrame();
            this.dispose();
        });

        JMenuItem exitItem=new JMenuItem("退出游戏");
        exitItem.addActionListener(e->System.exit(0));

        systemMenu.add(restartItem);
        systemMenu.add(exitItem);
        menuBar.add(systemMenu);
        this.setJMenuBar(menuBar);// “挂上” 菜单栏
    }

    private void randomInitData(){
        Random r=new Random();
        for(int i=0;i<100;i++){
            int direction=r.nextInt(4);
            switch(direction){
                case 0: moveUp(); break;
                case 1: moveDown(); break;
                case 2: moveLeft(); break;
                case 3: moveRight(); break;
            }
        }
        stepCnt=0;
    }

    private void initView(){
        getContentPane().removeAll();

        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                int num=imageData[i][j];
                if(num!=0){
                    //ImageIcon是Swing的图片加载器/图片包装器
                    JLabel jLabel=new JLabel(new ImageIcon(IMAGE_PATH + num + ".png"));
//                    拆成三行等价写法
//                    1. 拼路径
//                    String path = IMAGE_PATH + num + ".png";
//                    2. 加载图片
//                    ImageIcon icon = new ImageIcon(path);
//                    3. 创建标签显示图片
//                    JLabel jLabel = new JLabel(icon);

                    jLabel.setBounds(50+j*100,100+i*100,100,100);
                    this.add(jLabel);
                }
            }

            stepLabel=new JLabel("移动步数："+stepCnt);
            stepLabel.setBounds(50,20,150,30);
            this.add(stepLabel);

            //绘制背景
            JLabel background=new JLabel(new ImageIcon(IMAGE_PATH+"background.png"));
            background.setBounds(0,0,450,484);
            this.add(background);

            //胜利判断
            if(checkWin()){
                JLabel winLabel=new JLabel(new ImageIcon(IMAGE_PATH+"win.png"));
                winLabel.setBounds(100,200,250,100);
                this.add(winLabel);
            }
        }
    }

    private void bindKeyEvent(){
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                switch (keyCode) {
                    case KeyEvent.VK_UP:
                        moveDown();
                        break;
                    case KeyEvent.VK_DOWN:
                        moveUp();
                        break;
                    case KeyEvent.VK_LEFT:
                        moveRight();
                        break;
                    case KeyEvent.VK_RIGHT:
                        moveLeft();
                        break;
                }
                initView(); // 移动后刷新界面
            }
        });
    }

    // 向上移动（空白块向下移动）
    private void moveUp(){
        if(emptyRow<3){
            imageData[emptyRow][emptyCol]=imageData[emptyRow + 1][emptyCol];
            imageData[emptyRow + 1][emptyCol] = 0;
            emptyRow++;
            stepCnt++;
        }
    }

    // 向下移动（空白块向上移动）
    private void moveDown(){
        if(emptyRow>0){
            imageData[emptyRow][emptyCol] = imageData[emptyRow - 1][emptyCol];
            imageData[emptyRow - 1][emptyCol] = 0;
            emptyRow--;
            stepCnt++;
        }
    }

    private void moveLeft(){
        if(emptyCol<3){
            imageData[emptyRow][emptyCol] = imageData[emptyRow][emptyCol + 1];
            imageData[emptyRow][emptyCol + 1] = 0;
            emptyCol++;
            stepCnt++;
        }
    }

    private void moveRight() {
        if (emptyCol > 0) {
            imageData[emptyRow][emptyCol] = imageData[emptyRow][emptyCol - 1];
            imageData[emptyRow][emptyCol - 1] = 0;
            emptyCol--;
            stepCnt++;
        }
    }

    private boolean checkWin(){
        int[][] target={
                {1,2,3,4},
                {5,6,7,8},
                {9, 10, 11, 12},
                {13, 14, 15, 0}
        };
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (imageData[i][j] != target[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
