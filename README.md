# LeetCode 刷题项目

Java 算法刷题项目，基于 Maven + JUnit 5。

## 环境

- **Java**: 23.0.1
- **Maven**: 3.9.11
- **测试框架**: JUnit 5.11.4

## 项目结构

```
src/
├── main/java/com/leetcode/
│   ├── common/          # 通用数据结构
│   │   ├── ListNode.java    # 链表节点
│   │   └── TreeNode.java    # 二叉树节点
│   └── solutions/       # 解题代码
│       ├── Solution.java    # 答题模板
│       └── LC1_TwoSum.java  # 示例：1. Two Sum
└── test/java/com/leetcode/
    └── solutions/       # 测试代码
        └── LC1_TwoSumTest.java
```

## 使用方式

### 刷一道新题

1. 复制 `src/main/java/com/leetcode/solutions/Solution.java` 为 `LC{题号}_{题目名}.java`
2. 实现解题方法
3. 在 `src/test/java/com/leetcode/solutions/` 下创建对应的测试类
4. 运行测试验证

### 运行测试

```bash
# 全部测试
mvn test

# 运行单个测试类
mvn test -Dtest=LC1_TwoSumTest
```

### 编译

```bash
mvn compile
```

## 命名规范

| 类型 | 格式 | 示例 |
|------|------|------|
| 解题类 | LC{题号}_{题目名}.java | LC1_TwoSum.java |
| 测试类 | LC{题号}_{题目名}Test.java | LC1_TwoSumTest.java |