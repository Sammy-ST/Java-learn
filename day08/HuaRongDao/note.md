
# Java 石子迷阵（数字华容道）学习笔记
 | 技术：Java Swing 桌面小游戏

## 目录

- [Java 石子迷阵（数字华容道）学习笔记](#java-石子迷阵数字华容道学习笔记)
  - [一、整体项目概述](#一整体项目概述)
  - [二、包与导包说明](#二包与导包说明)
  - [三、主类与全局变量](#三主类与全局变量)
    - [1. 类定义](#1-类定义)
    - [2. 全局常量与数据容器](#2-全局常量与数据容器)
  - [四、构造方法（程序入口）](#四构造方法程序入口)
  - [五、逐个功能方法详解](#五逐个功能方法详解)
    - [1. initFrame() 窗口初始化](#1-initframe-窗口初始化)
    - [2. initMenu() 顶部菜单栏](#2-initmenu-顶部菜单栏)
    - [3. randomInitData() 随机打乱方块](#3-randominitdata-随机打乱方块)
    - [4. initView() 界面绘制（核心渲染方法）](#4-initview-界面绘制核心渲染方法)
    - [5. 四大移动方法（游戏核心逻辑）](#5-四大移动方法游戏核心逻辑)
    - [6. bindKeyEvent() 键盘监听](#6-bindkeyevent-键盘监听)
    - [7. checkWin() 胜利判断（自定义方法）](#7-checkwin-胜利判断自定义方法)
  - [六、核心知识点总结（易错点）](#六核心知识点总结易错点)
  - [七、常见报错提醒](#七常见报错提醒)

## 一、整体项目概述
这是一个基于 Java Swing 实现的 4×4 数字华容道（石子迷阵）小游戏，核心依靠**二维数组**存储方块数据，以空白格（数值 `0`）移动实现方块滑动，搭配窗口、菜单、键盘监听、步数统计、胜利判断等功能。我逐行梳理了代码逻辑与知识点，整理如下。

## 二、包与导包说明
```java
package com.itheima;
```
1. `package`：定义代码所在包路径，相当于项目文件夹命名，属于 Java 基础规范。

```java
import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
```
2. `import javax.swing.*`：导入 Swing 全套组件，用于创建窗口、标签、图片、菜单等界面元素。
3. `import 键盘相关包`：用来监听键盘按键，实现方向键控制方块移动。
4. `import java.util.Random`：导入随机数工具类，用于开局自动打乱方块。

## 三、主类与全局变量
### 1. 类定义
```java
public class MainFrame extends JFrame
```
我自定义了 `MainFrame` 类，并继承 `JFrame`，**`JFrame` 就是 Java 的窗口本体**，继承后这个类就拥有了窗口所有能力。

### 2. 全局常量与数据容器
```java
public static final String IMAGE_PATH = "stone-maze/src/image/";
```
- 定义图片路径常量，统一管理图片文件夹地址，后续加载图片直接复用，修改路径只需要改这一处。

```java
private int[][] imageData = { 4行4列二维数组 };
```
- **核心数据**：用二维数组模拟 4×4 游戏棋盘。
- 规则：数字 1~15 代表普通方块，**数字 0 代表空白格**，所有移动本质都是交换 `0` 和相邻数字。

```java
private int emptyRow = 0;
private int emptyCol = 3;
```
- 专门记录**空白格（0）** 当前所在的行、列坐标，所有移动逻辑都依赖这两个变量。

```java
private int stepCount = 0;
private JLabel stepLabel;
```
- `stepCount`：整型变量，统计玩家移动步数。
- `stepLabel`：界面标签组件，用来在窗口上展示步数文字。

## 四、构造方法（程序入口）
```java
public MainFrame() {
    initFrame();       // 初始化窗口基础属性
    initMenu();        // 初始化顶部菜单
    randomInitData();  // 随机打乱方块
    initView();        // 绘制界面、图片、文字
    bindKeyEvent();    // 绑定键盘监听
    this.setVisible(true); // 显示窗口
}
```
1. 构造方法是类创建对象时**自动执行**的方法，也是整个游戏的启动流程。
2. 执行顺序**绝对不能颠倒**：先配置所有内容，最后再调用 `setVisible(true)`。
3. `this.setVisible(true)`：相当于窗口的**电源开关**，前面所有配置都是“组装零件”，最后执行这行窗口才会显示，写在前面会出现空白窗口。

## 五、逐个功能方法详解
### 1. initFrame() 窗口初始化
```java
private void initFrame() {
    this.setTitle("石子迷阵 v1.0"); // 窗口标题
    this.setSize(465, 560);        // 窗口宽、高
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 点击叉号关闭程序
    this.setLocationRelativeTo(null); // 窗口居中显示
    this.setLayout(null); // 关闭自动布局，开启绝对定位（才能用setBounds）
    this.setAlwaysOnTop(true); // 窗口置顶
}
```
- 该方法统一设置窗口外观、大小、关闭规则、布局模式。
- `setLayout(null)` 是关键：只有关闭自动布局，后续才能手动设置组件位置和大小。

### 2. initMenu() 顶部菜单栏
涉及三个菜单组件，三者是**包含关系**，并非父子类：
1. `JMenuBar`：**整条顶部菜单栏**（最大容器）。
2. `JMenu`：菜单栏上的主菜单按钮（如“系统菜单”）。
3. `JMenuItem`：点击主菜单后弹出的子选项（重新开始、退出游戏）。

核心代码拆解：
```java
// 1. 创建菜单组件
JMenuBar menuBar = new JMenuBar();
JMenu systemMenu = new JMenu("系统菜单");
JMenuItem restartItem = new JMenuItem("重新开始");
JMenuItem exitItem = new JMenuItem("退出游戏");

// 2. 绑定点击事件
// 重新开始：新建窗口 + 关闭当前旧窗口
restartItem.addActionListener(e -> {
    new MainFrame();
    this.dispose();
});
// 退出游戏：直接关闭整个程序
exitItem.addActionListener(e -> System.exit(0));

// 3. 层级嵌套：子项→主菜单→菜单栏
systemMenu.add(restartItem);
systemMenu.add(exitItem);
menuBar.add(systemMenu);

// 4. 将菜单栏挂载到窗口（专用方法，不能用普通add）
this.setJMenuBar(menuBar);
```
- `addActionListener`：给菜单选项绑定**点击监听**，点击后执行对应代码。
- `this.dispose()`：关闭当前窗口；`System.exit(0)`：正常退出整个程序。
- `setJMenuBar()`：窗口专属挂载菜单栏的方法，区别于普通组件的 `add()`。

### 3. randomInitData() 随机打乱方块
```java
Random r = new Random();
for (int i = 0; i < 100; i++) {
    int direction = r.nextInt(4);
    switch (direction) {
        case 0: moveUp(); break;
        case 1: moveDown(); break;
        case 2: moveLeft(); break;
        case 3: moveRight(); break;
    }
}
stepCount = 0;
```
1. `Random`：随机数对象，用来生成随机方向。
2. `r.nextInt(4)`：**重点**：括号内数字代表取值范围，`nextInt(4)` 只会随机生成 `0、1、2、3` 四个数字，分别对应上下左右 4 个方向。
3. 循环执行 100 次随机移动，实现方块打乱，且这种打乱方式保证谜题一定有解。
4. 打乱后重置步数为 0。

### 4. initView() 界面绘制（核心渲染方法）
每次移动方块后都会调用该方法，**重新刷新整个界面**。
```java
getContentPane().removeAll(); // 先清空旧组件，防止重叠
```
- `getContentPane()`：获取窗口的**内容面板**。窗口不能直接放置图片、标签等组件，所有内容都要放在内容面板上。
- `removeAll()`：清空面板上所有旧内容，避免刷新后画面重叠。

#### （1）绘制方块图片
```java
JLabel jLabel=new JLabel(new ImageIcon(IMAGE_PATH + num + ".png"));
```
分层拆解：
1. 最内层 `IMAGE_PATH + num + ".png"`：拼接图片完整路径字符串。
2. `new ImageIcon(路径)`：根据路径读取图片文件，转为 Java 可识别的图片对象。
3. `new JLabel(图片对象)`：创建标签组件，用来展示图片。

```java
jLabel.setBounds(50 + j * 100, 100 + i * 100, 100, 100);
```
- `setBounds(x, y, 宽度, 高度)`：**绝对定位方法**。
- 参数含义：`x` 距离左侧像素、`y` 距离顶部像素、组件宽度、组件高度。依靠该方法排布 4×4 的方块。

#### （2）绘制步数标签
```java
stepLabel = new JLabel("移动步数：" + stepCount);
stepLabel.setBounds(50, 20, 150, 30);
```
- 创建文字标签展示步数，同样使用 `setBounds` 设置位置和大小。

#### （3）绘制背景 & 胜利判断
1. 加载背景图片标签，铺满窗口底层。
2. 调用自定义方法 `checkWin()` 判断是否通关，若返回 `true`，则展示胜利图片。

### 5. 四大移动方法（游戏核心逻辑）
所有移动**只操作空白格（0）**，数字方块只是被动换位，逻辑统一：
1. 判断边界：防止空白格移出 4×4 棋盘。
2. 交换空白格与相邻数字。
3. 更新空白格的行/列坐标。
4. 步数 +1。

```java
// 空白格向下移动
private void moveUp() {
    if (emptyRow < 3) {
        imageData[emptyRow][emptyCol] = imageData[emptyRow + 1][emptyCol];
        imageData[emptyRow + 1][emptyCol] = 0;
        emptyRow++;
        stepCount++;
    }
}
// 空白格向上移动
private void moveDown() { ... }
// 空白格向右移动
private void moveLeft() { ... }
// 空白格向左移动
private void moveRight() { ... }
```
- 因为控制的是空白格，所以**按键方向和空白格移动方向相反**。

### 6. bindKeyEvent() 键盘监听
```java
this.addKeyListener(new KeyAdapter() {
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_UP: moveDown(); break;
            case KeyEvent.VK_DOWN: moveUp(); break;
            case KeyEvent.VK_LEFT: moveRight(); break;
            case KeyEvent.VK_RIGHT: moveLeft(); break;
        }
        initView(); // 移动后刷新界面
    }
});
```
1. `addKeyListener`：给窗口添加键盘监听器，相当于给窗口装“耳朵”，监听按键动作。
2. `keyPressed`：重写方法，**按下任意按键时自动触发**。
3. `e.getKeyCode()`：获取当前按下按键的编号。
4. `KeyEvent.VK_XXX`：
   - `VK` 全称 Virtual Key（虚拟按键），是 Java 给键盘按键定义的常量代号。
   - `VK_UP`=上方向键、`VK_DOWN`=下方向键、`VK_LEFT`=左方向键、`VK_RIGHT`=右方向键。
5. 匹配按键后，调用对应移动方法，最后执行 `initView()` 刷新画面。

### 7. checkWin() 胜利判断（自定义方法）
```java
private boolean checkWin() {
    // 定义通关目标数组
    int[][] target = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,0}};
    // 双重循环对比当前数组和目标数组
    for (int i = 0; i < 4; i++) {
        for (int j = 0; j < 4; j++) {
            if (imageData[i][j] != target[i][j]) {
                return false; // 有位置不一致，未通关
            }
        }
    }
    return true; // 全部一致，通关成功
}
```
- 这是**我自己编写的自定义方法**，不是系统自带。
- 逻辑：定义正确排序的目标数组，逐行逐列对比游戏当前数组，完全相同则判定胜利。
- 返回值为布尔类型，`true`=胜利，`false`=未胜利。

## 六、核心知识点总结（易错点）
1. **核心原理**：华容道不靠移动数字，只移动**空白格 0**，数字被动换位。
2. **窗口执行顺序**：先配置所有组件 → 最后执行 `setVisible(true)` 显示窗口。
3. **布局规则**：`setLayout(null)` 开启绝对定位，才能使用 `setBounds(x,y,w,h)`。
4. **菜单层级**：`JMenuBar`（菜单栏）→ `JMenu`（主菜单）→ `JMenuItem`（子选项），使用 `setJMenuBar` 挂载菜单栏。
5. **图片加载流程**：拼接路径 → `ImageIcon` 加载图片 → `JLabel` 展示图片。
6. **键盘按键**：`VK_` 开头常量代表键盘虚拟按键，方向键控制逻辑和空白格移动方向相反。
7. **界面刷新**：每次移动方块必须调用 `initView()` 重新绘制界面，否则画面不会更新。

## 七、常见报错提醒
1. 变量/方法名**大小写敏感**，拼写不一致会报“无法解析符号”。
2. 自定义方法（`moveUp`、`checkWin` 等）必须写在主类内部，否则无法调用。
3. 使用 `setBounds` 必须先设置 `setLayout(null)`，否则定位失效。

