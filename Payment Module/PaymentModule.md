# 支付模块（会员卡系统）学习笔记：Java继承+多态+控制台项目（错误总结）
> 本文为Java基础项目实战总结，聚焦**会员卡消费系统**开发中踩过的坑，涵盖继承、多态、封装、控制台交互等核心知识点，适合Java初学者复盘与避坑。

## 目录
- [一、项目背景与核心代码](#一项目背景与核心代码)
- [二、构造方法相关错误](#二构造方法相关错误)
- [三、方法重写与逻辑判断错误](#三方法重写与逻辑判断错误)
- [四、继承与多态使用不熟练](#四继承与多态使用不熟练)
- [五、控制台交互与菜单逻辑错误](#五控制台交互与菜单逻辑错误)
- [六、switch 结构使用不规范](#六switch-结构使用不规范)
- [七、整体总结（核心要点）](#七整体总结核心要点)
- [八、GitHub 上传规范](#八github-上传规范)

---

## 一、项目背景与核心代码
本次实战开发**会员卡分级消费系统**，包含：
- 父类 `Card`（普通卡）：封装卡号、姓名、电话、余额，提供存款、消费、余额判断等基础方法
- 子类 `GoldCard`/`SilverCard`：继承 `Card`，重写消费逻辑实现折扣
- 测试类 `Test`：控制台菜单、用户交互、多态调用

**核心正确代码（Card类）**
```java
package com.payment;

public class Card {
    private String carId;
    private String name;
    private String phone;
    private double money;

    // 存款
    public void deposit(double money){
        setMoney(getMoney() + money);
    }

    // 消费（模板）
    public void consume(double money){
        if(!ifEnough(money)) return;
        System.out.println("使用普通卡消费：" + money);
        setMoney(getMoney() - money);
        System.out.println("当前余额：" + getMoney());
    }

    // 余额判断
    public boolean ifEnough(double money){
        if(money > this.money){
            System.out.println("余额不足，消费失败");
            return false;
        }
        System.out.println("余额充足");
        return true;
    }

    // 构造、Getter/Setter 完整
    public Card(){}
    public Card(String carId, String name, String phone, double money){
        this.carId = carId;
        this.name = name;
        this.phone = phone;
        this.money = money;
    }

    // 省略 Getter/Setter（完整实现）
    public double getMoney(){ return money; }
    public void setMoney(double money){ this.money = money; }
}
```

---

## 二、构造方法相关错误
### 1. 子类无对应构造，直接new报错
- **错误**：`GoldCard` 未写带参构造，直接 `new GoldCard(...)` 编译失败
- **原因**：子类**不自动继承**父类构造；若父类无默认无参构造，子类必须显式调用 `super(...)`
- **正确**：子类必须提供匹配父类参数的构造，并调用 `super`

```java
// 正确子类构造
public GoldCard(String carId, String name, String phone, double money){
    super(carId, name, phone, money);
}
```

### 2. 无参构造作用与位置不清
- **错误**：提示“找不到无参构造”，不理解子类默认调用逻辑
- **原理**：
  - 父类写 `public Card(){}` → 无参构造
  - 子类**不写构造**时，默认调用父类无参
  - 父类**只有带参构造**时，子类必须手动写构造并调用 `super(参数)`

---

## 三、方法重写与逻辑判断错误（最严重）
### 1. 消费条件完全写反
- **错误代码**
  ```java
  if(ifEnough(money)){ return; } // 充足→退出，不足→消费（逻辑颠倒）
  ```
- **正确代码**
  ```java
  if(!ifEnough(money)){ return; } // 余额不足才返回
  ```

### 2. ifEnough 语义理解错误
- **规则**：`ifEnough` 返回 `true`=充足、`false`=不足
- **影响**：直接决定子类 `consume` 重写逻辑，语义颠倒会导致全线崩溃

### 3. setMoney 与 this.money 误用
- **初期错误**：`setMoney(money) -= money;`（语法非法）
- **正确**：
  ```java
  setMoney(getMoney() - money); // 标准：先读原值→计算→赋值
  // 或 this.money = getMoney() - money;（直接访问私有成员）
  ```
- **区别**：
  - `this.money`：直接访问**私有成员变量**（类内部可用）
  - `setMoney(...)`：通过**公共方法**赋值（更规范，支持扩展校验）

---

## 四、继承与多态使用不熟练
### 1. 不会用父类引用指向子类对象
- **错误**：分别用金卡、银卡变量，代码重复、无法统一调用
- **正确（多态核心）**：
  ```java
  Card currentCard; // 父类引用
  currentCard = new GoldCard(...); // 指向子类
  currentCard.consume(100); // 自动调用子类重写方法
  ```

### 2. 流程混乱：先操作后选择
- **错误**：三张卡直接同时消费，不符合“选卡→消费”业务
- **正确流程**：
  1. 菜单选择卡类型
  2. `switch` 匹配 → 赋值给 `currentCard`
  3. 统一调用 `currentCard.consume(...)`

---

## 五、控制台交互与菜单逻辑错误
### 1. 菜单文字与代码功能不匹配
- **错误**：界面写“1消费 2查询 3退出”，代码却对应“金卡/银卡/普通卡”
- **规范**：UI文字必须与代码逻辑一一对应，用户可按提示操作

### 2. 代码重复，未抽取公共方法
- **错误**：金卡、银卡、普通卡分别写消费逻辑
- **正确**：抽取统一方法
  ```java
  public static void pay(Card card, double money){
      card.consume(money);
  }
  ```

### 3. 需求理解偏差
- **问题**：消费后强制打印余额，应仅在“查询余额”时输出
- **原则**：功能边界清晰：消费只提示结果，查询专门展示信息

---

## 六、switch 结构使用不规范
### 1. 不会在 switch 内给变量赋值
- **错误**：每个 `case` 单独写消费，无法统一后续操作
- **正确**：`switch` 内赋值给公共变量
  ```java
  Card currentCard = null;
  switch(choice){
      case 1: currentCard = goldCard; break;
      case 2: currentCard = silverCard; break;
  }
  if(currentCard != null) pay(currentCard, money);
  ```

### 2. 嵌套 switch 流程控制不清
- **场景**：外层主菜单、内层选卡
- **要点**：
  - 每层 `switch` 职责单一
  - 用 `break` 防止穿透
  - 变量作用域合理，避免重复定义

---

## 七、整体总结（核心要点）
1. **继承**：子类构造必须匹配父类，显式调用 `super`
2. **重写**：逻辑不能颠倒、权限不能缩小、返回值兼容
3. **多态**：父类引用指向子类对象，统一调用、动态绑定
4. **封装**：私有成员用 `Getter/Setter`，内部可直接用 `this.变量`
5. **控制台项目**：菜单与代码一致、流程清晰（选→判→执）
6. **代码规范**：公共逻辑抽取、减少重复、语义明确

---

## 八、GitHub 上传规范
### ✅ 正确结构（必须保留 src）
```
你的仓库/
├── src/
│   └── com/
│       └── payment/
│           ├── Card.java
│           ├── GoldCard.java
│           ├── SilverCard.java
│           └── Test.java
├── README.md（本文档）
└── .gitignore
```

### ❌ 错误结构（缺失 src）
```
仓库/
├── com/
│   └── payment/
│       └── ...
```
- 后果：包名 `com.payment` 与路径不匹配、IDE无法识别源码根、编译失败

### 上传步骤
1. 上传**整个 `src` 文件夹**
2. 新增 `README.md`（项目说明、技术栈、运行方式）
3. `.gitignore` 排除 `.idea`、`.iml`、`class` 文件
4. 提交信息规范：`feat: 会员卡系统完整代码`、`docs: 添加学习笔记`

---

**本文为实战踩坑总结，所有错误均来自真实开发过程，适合Java初学者对照自查、巩固面向对象核心知识点。**
