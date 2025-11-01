package stepdefinitions;

import io.cucumber.java.zh_cn.假如;
import io.cucumber.java.zh_cn.当;
import io.cucumber.java.zh_cn.那么;

public class LoginSteps {
    private String username;
    private String password;
    private boolean loginSuccessful;
    private String message;

    @假如("用户在登录页面")
    public void 用户在登录页面() {
        System.out.println("打开登录页面");
        loginSuccessful = false;
        message = "";
    }

    @当("用户输入用户名 {string}")
    public void 用户输入用户名(String username) {
        this.username = username;
        System.out.println("输入用户名: " + username);
    }

    @当("用户输入密码 {string}")
    public void 用户输入密码(String password) {
        this.password = password;
        System.out.println("输入密码: " + password);
    }

    @当("用户点击登录按钮")
    public void 用户点击登录按钮() {
        System.out.println("点击登录按钮");
        // 简单的登录逻辑模拟
        if ("testuser".equals(username) && "password123".equals(password)) {
            loginSuccessful = true;
            message = "欢迎, " + username + "!";
        } else {
            loginSuccessful = false;
            message = "用户名或密码错误";
        }
    }

    @那么("用户应该成功登录")
    public void 用户应该成功登录() {
        if (!loginSuccessful) {
            throw new AssertionError("登录失败");
        }
        System.out.println("登录成功");
    }

    @那么("用户应该看到欢迎消息 {string}")
    public void 用户应该看到欢迎消息(String expectedMessage) {
        if (!message.equals(expectedMessage)) {
            throw new AssertionError("期望消息: " + expectedMessage + ", 实际消息: " + message);
        }
        System.out.println("看到消息: " + message);
    }

    @那么("用户应该看到错误消息 {string}")
    public void 用户应该看到错误消息(String expectedMessage) {
        if (!message.equals(expectedMessage)) {
            throw new AssertionError("期望错误消息: " + expectedMessage + ", 实际消息: " + message);
        }
        System.out.println("看到错误消息: " + message);
    }
}