# 📝 Java 随机数与 StringBuilder 学习笔记
摘要：本笔记总结了 Java 中生成随机数的两种主要方式 (Math.random() 与 Random 类)，对比了 String 与 StringBuilder 的性能差异，并结合“验证码生成”实战案例展示了它们的综合应用。

##  目录
- [1. 两种随机数生成方式](#1-两种随机数生成方式)
- [1.1 `Math.random()`](#11-mathrandom)
- [1.2 `java.util.Random` 类](#12-javautilrandom-类)
- [2. `String` 与 `StringBuilder` 对比](#2-string-与-stringbuilder-对比)
- [3. 验证码项目实战](#3-验证码项目实战)
- [4. 易错点总结](#4-易错点总结)

---

## 1. 两种随机数生成方式

### 1.1 `Math.random()`
- **所属包**：`java.lang.Math`（无需手动导包，默认存在）
- **返回值类型**：`double`
- **数值范围**：`[0.0, 1.0)`，包含 `0.0`，**不包含 `1.0`**

####  通用公式
```java
// 生成 0 ~ n-1 的随机整数
(int)(Math.random() * n);
```

#### 项目实战（验证码逻辑）
```java
// 生成 0、1、2 三种类型（数字/小写/大写）
int type = (int)(Math.random() * 3);

// 数字 0~9
(int)(Math.random() * 10);

// 小写字母 a~z
(char)(Math.random() * 26 + 'a');

// 大写字母 A~Z
(char)(Math.random() * 26 + 'A');
```

---

### 1.2 `java.util.Random` 类
- **需要导包**：
  ```java
  import java.util.Random;
  ```
- **使用步骤**：先创建对象，再调用方法
  ```java
  Random r = new Random();
  ```
- **常用方法**：
  ```java
  r.nextInt(n);      // 生成 [0, n) 的随机整数
  r.nextDouble();   // 生成 [0.0, 1.0) 的 double，等价于 Math.random()
  ```

#### ️ 与 `Math.random()` 等价替换
```java
// 原来写法
(int)(Math.random() * 3);

// 替换为 Random 类写法 (推荐，可读性更强)
r.nextInt(3);
```

---

## 2. `String` 与 `StringBuilder` 对比

| 特性 | `String` | `StringBuilder` |
| :--- | :--- | :--- |
| **可变性** | **不可变**，每次修改都会新建对象 | **可变**，底层 char 数组原地修改 |
| **拼接效率** | 低（在循环中会频繁创建对象，消耗内存） | **高**，仅在必要时扩容数组 |
| **适用场景** | 少量字符串拼接、常量定义 | 循环拼接、大量字符串操作 |

### ️ 基础用法
```java
// 1. 创建 StringBuilder 对象
StringBuilder sb = new StringBuilder();

// 2. 追加内容（代替 String +=）
sb.append("验证码");
sb.append(123);

// 3. 转为 String 返回
String result = sb.toString();
```

---

## 3. 验证码项目实战（核心代码）

这是一个结合了 `Math.random()` 和 `StringBuilder` 的完整案例。

```java
import java.util.Scanner;

public class VerificationCode {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入验证码位数：");
        int n = sc.nextInt();
        
        System.out.println("生成的验证码：" + getCode(n));
    }

    // 生成验证码的方法
    public static String getCode(int n) {
        StringBuilder code = new StringBuilder();
        
        for (int i = 0; i < n; i++) {
            int type = (int)(Math.random() * 3);
            
            switch (type) {
                case 0:
                    // 数字 0~9
                    code.append((int)(Math.random() * 10));
                    break;
                case 1:
                    // 小写字母 a~z
                    code.append((char)(Math.random() * 26 + 'a'));
                    break;
                case 2:
                    // 大写字母 A~Z
                    code.append((char)(Math.random() * 26 + 'A'));
                    break;
            }
        }
        return code.toString();
    }
}
```

---

## 4. 易错点总结

1.  **边界问题**：`Math.random()` 永远取不到 `1.0`，因此 `(int)(Math.random() * n)` 的结果范围是 `0` 到 `n-1`，不会越界。
2.  **性能陷阱**：在循环中拼接字符串，**禁止使用 `String +=`**，必须使用 `StringBuilder`，否则会造成大量内存浪费。
3.  **类型转换**：`StringBuilder` 不能直接赋值给 `String` 变量，必须调用 `.toString()` 方法进行转换。
4.  **代码整洁**：如果使用了 `Random` 类，记得检查 `import` 语句；如果未使用，请及时清理。

